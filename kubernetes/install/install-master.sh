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
echo "Installing kubernetes master node on Ubuntu 18.04..."
echo "##################################################"
echo ""
echo ""
./prepare.sh
echo ""
echo ""

echo "##################################################"
echo "Installing kubernetes master..."
echo "##################################################"
kubeadm init --pod-network-cidr=10.244.0.0/16 --service-cidr=10.96.0.0/12 --apiserver-advertise-address=0.0.0.0 --v=5
mkdir -p $HOME/.kube
cp -f /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
# allowing create pod on master
kubectl taint nodes --all node-role.kubernetes.io/master-
echo ""
echo "##################################################"
echo "Kubernetes master node is installed successfully"
echo "##################################################"