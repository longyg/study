#!/bin/bash
#################################################
#
# Kubernetes cluster installation script
#
# This installation script is applicable for Ubuntu 18.04
#
## Please execute this script as root user
#################################################
echo "##################################################"
echo "Installing kubernetes worker node on Ubuntu 18.04..."
echo "##################################################"
echo ""
echo ""
worker_ip=$1
scp ./prepare.sh root@$worker_ip://root/
ssh root@$worker_ip "chmod +x ./prepare.sh; ./prepare.sh"
echo ""
echo ""

echo "##################################################"
echo "Installing kubernetes worker..."
echo "##################################################"
kubeadm version > /dev/null 2>&1
re=$(echo $?)
if [ ! $re == '0' ];then
	echo "kubeadm is not installed on this system, exit"
	exit 1
fi
token=$(kubeadm token list 2>/dev/null 2>&1 | awk '{print $1}' | sed -n '2p')
ret=$(echo $?)
if [ ! $ret == '0' ];then
	echo "Get token failed, exit"
	exit 2
else
	if [ -z "$token" ];then
		echo "Token is not create yet, create first"
		kubeadm token create > /dev/null 2>&1
		ret2=$(echo $?)
		if [ ! ret2 == '0' ];then
			echo "Can't create token, exit"
			exit 3
		else
			echo "Token is created successfully"
			token=$(kubeadm token list | awk '{print $1}' | sed -n '2p')
		fi
	else
		echo "Token is get"
	fi
fi
echo "Token: $token"
hash=$(openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //')
master_ip=$(hostname -i)
ssh root@$worker_ip "kubeadm join $master_ip:6443 --token $token --discovery-token-ca-cert-hash sha256:$hash"

ssh root@$worker_ip "mkdir -p $HOME/.kube"
scp /etc/kubernetes/admin.conf root@$worker_ip:/$HOME/.kube/config
ssh root@$worker_ip "chown $(id -u):$(id -g) $HOME/.kube/config"

echo ""
echo "##################################################"
echo "Kubernetes worker node is installed successfully"
echo "##################################################"