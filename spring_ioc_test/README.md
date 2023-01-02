# 工程简介



# 延伸阅读

## 不能全部复制黏贴
看这个导入是否正确
package com.example.spring_ioc_test01.service;


##  不能给前端超过25位的数字
前端会精度缺失 
转成字符串

## Linux

使用CentOS :Redhat的社区版

虚拟机软件 VMWare

### 压缩包命令

tar -cvf test.tar test/   //压缩test目录为 test.tar压缩包

tar -zcvf test.tar.gz test   //压缩test目录为 test.tar.gz压缩包

tar -zxvf test.tar.gz     //解压test.tar.gz  到当前目录

### 软件的安装
安装jdk
Tomcat
MySQL
lrzsz


tar -zxvf jdk-8u171-linux-x64.tar.gz -C /usr/local

vim /etc/prof

source /etc/profile //重新加载让配置生效

 tar -zxvf apache-tomcat-7.0.57.tar.gz -C /usr/local/  //安装tomcat

sh startup.sh  //启动tomcat

ps -ef //查看进程
ps -ef | grep tomcat  //查看指定程序

#### 防火墙

systemctl status firewalld  //查看防火墙运行状态
firewall-cmd --state   //查看防火墙运行状态（简）

systemctl stop firewalld //暂时停止防火墙
systemctl disable firewalld //永久关闭防火墙

systemctl start firewalld //开启防火墙
//开启指定端口
firewall-cmd --zone=public --add-port=8080/tcp --permanent  
//关闭指定端口
firewall-cmd --zone=public --remove-port=8080/tcp --permanent  

firewall-cmd --reload //立即生效
firewall-cmd --zone=public --list-ports //查看开发端口


#### 安装MySQL
//Contos7中有和mysql冲突的mariadb 需要检查
rpm -qa | grep mysql
rpm -qa | grep mariadb

//卸载冲突的软件
 rpm -e --nodeps mariadb-libs-5.5.60-1.el7_5.x86_64

//移动到指定目录
mv mysql-5.7.25-1.el7.x86_64.rpm-bundle.tar.gz /usr/local/mysql
//解压文件
tar -zxvf mysql-5.7.25-1.el7.x86_64.rpm-bundle.tar.gz 

//按照顺序安装mysql
rpm -ivh mysql-community-common-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-libs-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-devel-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-libs-compat-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-client-5.7.25-1.el7.x86_64.rpm
yum install net-tools
rpm -ivh  mysql-community-server-5.7.25-1.el7.x86_64.rpm

//升级现有软件及系统
yum update

//启动mysql

systemctl status mysqld //查看mysql服务状态
systemctl start mysqld //启动mysql服务

systemctl enable mysqld //开机启动mysql服务

netstat -tunlp  //查看已启动的服务
netstat -tunlp | grep mysql

ps -ef | grep mysql //查看mysql进程

cat /var/log/mysqld.log //查看文件内容
//查看文件中包含password的行信息
cat /var/log/mysqld.log | grep password 
  
//临时密码  #a?I-n0kmr<A

##### 操作mysql
mysql -uroot -p //登录mysql（使用临时密码）

//mysql命令

//设置密码长度最低位数
set global validate_password_length=4;
//设置密码安全等级低，便于密码可以修改成 root
set global validate_password_policy=LOW;
//设置密码为root
set password=password('root');

//开启访问权限
grant all on *.* to 'root'@'%' identified by 'root';
flush privileges; //刷新立即生效

exit //退出

show databases;  //查看数据库

#### 安装lrzsz

yum list lrzsz //搜索lrzsz安装包
yum install lrzsz.x86_64 //安装yum命令在线安装

rz //上传快捷键

#### 手工打包
maven 中项目 Lifecycle 中 package

##### 创建目录，将项目jar包放此目录
mkdir /usr/local/app
##### 运行项目
java -jar reggie_take_out-1.0-SNAPSHOT.jar
##### 后台运行项目,并将日志输出到hello.log文件
nohup java -jar reggie_take_out-1.0-SNAPSHOT.jar &> hello.log &

##### 停止SpringBoot
ps -ef | grep java

kill -9 116639    // 116639 线程ID

#### 安装git
yum list git //列出git安装包
yum install git //在线安装git


#### 安装mavan
tar -zxvf 
配置环境变量
vim /etc/profile

export MAVEN_HOME=/usr/local/apache-maven-3.5.4
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
重新加载文件
source /etc/profile

mvn -version


<localRepository>/usr/local/repo</localRepository>


#### 使用Shell脚本文件
pwd  查看当前目录

读（r）、写（w）、执行（x）
三级：文件所有者（Owner）、用户组（Group）、其他用户（Other User）
-rw-r--r--. 代表都有啥权限

##### 为用户授权
chmod 777 bootStart.sh

 ./bootStart.sh   执行脚本

查找输出日志 
find / -name helloworld.log

more ///helloworld.log

##### 设置静态IP







