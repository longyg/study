#!/bin/bash
echo "##################################################"
echo "Preparing system for installing kubernetes cluster..."
echo "##################################################"

# provide proxy address if any
PROXY_HOST="http://<ip>:<port>/"
NO_PROXY_HOST="localhost,127.0.0.0/8,10.244.0.0/16,10.96.0.0/12,10.0.2.0/24"

cpu_number=`cat /proc/cpuinfo | grep processor | wc -l`
echo "CPUs: $cpu_number"
echo ""
echo ""

# disable swap
echo "##################################################"
echo "Disabling swap..."
echo "##################################################"
swapoff -a
sed -i 's/^[^#].*swap/#&/g' /etc/fstab
echo "Swap is disabled successfully..."
echo ""
echo ""

# configure proxy for apt
echo "##################################################"
echo "Configuring proxy..."
echo "##################################################"
cat <<EOF >/etc/apt/apt.conf
Acquire::http::Proxy "$PROXY_HOST";
EOF
if [ `grep -c "export http_proxy=" /etc/profile` -eq 1 ];then
	echo "Proxy is already configured in /etc/profile"
else
	echo "export http_proxy=$PROXY_HOST" >> /etc/profile
	echo "export https_proxy=$PROXY_HOST" >> /etc/profile
	echo "export no_proxy=$NO_PROXY_HOST" >> /etc/profile
	source /etc/profile
fi
echo "Proxy is configured successfully"
echo ""
echo ""

# install docker
echo "##################################################"
echo "Installing docker..."
echo "##################################################"
docker -v > /dev/null 2>&1
ret=$(echo $?)
if [ $ret == '0' ];then
	echo "Docker is installed already"
else
	apt-get update && apt-get install -y docker.io
	echo "Docker is installed successfully"
fi
echo ""
echo ""

# configure docker
echo "##################################################"
echo "Configuring docker..."
echo "##################################################"
mkdir -p /etc/systemd/system/docker.service.d
cat <<EOF >/etc/systemd/system/docker.service.d/http-proxy.conf
[Service]
Environment="HTTP_PROXY=$PROXY_HOST"
Environment="HTTPS_PROXY=$PROXY_HOST"
Environment="NO_PROXY=$NO_PROXY_HOST"
EOF
sed -i 's#--containerd=/run/containerd/containerd.sock#-H tcp://0.0.0.0:2375#' /lib/systemd//system/docker.service
driver=$(docker info -f {{.CgroupDriver}})
if [ $driver == "systemd" ];then
	echo "Docker is already running as 'systemd'"
else
	cat <<-EOF >/etc/docker/daemon.json
	{
		"exec-opts": ["native.cgroupdriver=systemd"]
	}
	EOF
fi
systemctl daemon-reload
systemctl restart docker
docker info -f {{.CgroupDriver}}
systemctl enable docker.service
echo "Docker is configured successfully"
echo ""
echo ""

echo "##################################################"
echo "Installing kubeadm/kubelet/kubectl..."
echo "##################################################"
curl -x $PROXY_HOST -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb http://apt.kubernetes.io/ kubernetes-xenial main
EOF
kubeadm version > /dev/null 2>&1
ret2=$(echo $?)
if [ $ret2 == '0' ];then
	echo "Kubeadm is already installed"
else
	apt-get update && apt-get install -y kubelet kubeadm kubectl
	echo "kubeadm/kubelet/kubectl is installed successfully"
fi
echo ""
echo ""

echo ""
echo "##################################################"
echo "System is prepared successfully"
echo "##################################################"
