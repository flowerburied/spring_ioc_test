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

systemctl restart mysqld //重启mysql服务

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

#### 主从复制
主
GRANT REPLICATION SLAVE ON *.* to 'xiaoming'@'%' identified by 'Root@123456';

show master status;

从

change master to master_host='192.168.209.128',master_user='xiaoming',master_password='Root@123456',master_log_file='mysql-bin.000005',master_log_pos=154;

##### 停止线程 
stop slave;

start slave;

##### 查看从库
show slave status;

!!!!!! 初始库必须一样 !!!!!!!
否则会Slave_SQL_Running no


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
 cd /etc/sysconfig/network-scripts/

vim ifcfg-ens33

重启网络服务
systemctl restart network


## Redis

redis是一个基于内存的key-value结构数据库
相对于mysql的磁盘数据库 ，读写性能高
适合存储热点数据（热点商品、资讯、新闻）

官网：https://redis.io
理论查询十万+的QPS
NoSql指非关系型数据库

应用场景
缓存
任务队列
信息队列
分布式锁

下载安装
windows版下载地址: https://github.com/microsoftarchive/redis/releases
Linux版下载地址: https://download.redis.io/releases/

tar -zxvf redis-4.0.0.tar.gz -C /usr/local

安装Redis的依赖环境gcc,命令：yum install gcc-c++

进入redis目录, make  命令进行编译

进入src目录，进行安装，make install

### 启动和停止Redis
./redis-server 
新窗口
./redis-cli 

查看redis中有哪些key
keys *

#### 修改redis配置文件在后台运行
vim redis.conf 
/dae

修改daemonize no为后台运行
运行
 src/redis-server ./redis.conf 

#### Redis数据类型
字符串 string
哈希 hash
列表 list
集合 set
有序集合 sorted set

##### redis命令
###### String常用指令
SET key value  //设置指定key的值
Get key   //获取指定key的值
SETEX key seconds value  //设置指定key的值，并将key的过期时间设为seconds秒
SETNX key value  //只有在key不存在时设置key的值
###### hash常用指令  (hash)
HSET key field value  //将哈希表key中的字段field的值设为value
HGET key field  //获取存储造哈希表中指定字段的值
HDEL key field   //删除存储在哈希表中的指定字段
HKEYS key  //获取哈希表中所有字段
HVALS key  //获取哈希表中所有值
HGETALL key  //获取在哈希表中指定key的所有字段和值
###### list常用指令
LPUSH key value1[value2]  //将一个或多个值插入到列表头部
LPANGE key start stop  //获取列表指定范围内的元素  lrange mylist 0 -1（查所有）
RPOP key  //移除并获取列表最后一个元素
LLEN key  //获取列表长度
BRPOP key1[key2] timeout //移出并获取列表的最后一个元素，如果列表没有元素会阻塞
列表直到等待超时或发现可弹出元素为止   （可做任务队列）
###### 集合set常用指令  （set）
SADD key member1[member2]  //向集合添加一个或多个成员  ， 成员不可重复
SMEMBERS key  //返回集合中的所有成员
SCARD key  //获取集合的成员数
SINTER key1[key2]  //返回给定所有集合的交集  sinter myset myset2
SUNION key1[key2]  //返回所有给定集合的并集
SDIFF key1[key2]  //返回给定所有集合的差集
SREM key member1[menber2]  //移除集合中一个或多个成员
###### 有序集合sorted set常用指令  （zset）
ZADD key score1 member1 [score2 member2]  //向有序集合添加一个或多个成员，或者更新
已存在成员的分数   zadd myset3 10.0 a 9.0 b
ZRANGE key start stop [WITHSCORES]  //通过索引区间返回有序集合中指定区间内的成员
    zrange myset3 0 -1
    zrange myset3 0 -1 withscores
ZINCRBY key increment member  //有序集合中指定成员的分数加上增量increment
    zincrby myset3 20 b
ZREM key member [member ...]  //移除有序集合中一个或多个成员
    zrem myset3 myset3 b

###### 通用命令
KEYS pattern  //查找所有符合给定模式（pattern）的key  keys *
EXISTS key  //检查给定key是否存在  exists myset3
TYPE key  //返回key所存储的值的类型  type myset3
TTL key  //返回给定key的剩余生存时间（TTL,time to live）,以秒为单位  
    ttl myset3  
    (integer) -1   (永久)
DEL key  //该命令用于在key存在是删除key  
    del myset3 001  (可删除多个)
 


https://www.redis.net.cn

##### 在JAVA中操作redis

技术
Jedis
Spring Data Redis

redis包
     <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.5.2</version>
        </dependency>

单元测试包
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.12</version>
</dependency>



select 1 //切换数据库

##### 重大bug 不能同时存在，不然取不出！！！！！
127.0.0.1:6379> smembers myset
1) "d"
2) "c"
3) "\xac\xed\x00\x05t\x00\x01b"
4) "\xac\xed\x00\x05t\x00\x01a"
5) "\xac\xed\x00\x05t\x00\x01c"

127.0.0.1:6379> srem myset d c
(integer) 2

127.0.0.1:6379> smembers myset
1) "\xac\xed\x00\x05t\x00\x01b"
2) "\xac\xed\x00\x05t\x00\x01a"
3) "\xac\xed\x00\x05t\x00\x01c"
127.0.0.1:6379>


### Redis 优化
maven坐标

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

配置文件

spring:
    redis:
        host: localhost
        port: 6379
        database: 0  #0号数据库  默认16个数据库
        jedis:
        #Redis连接池配置
          pool:
            max-active: 8 #最大连接数
            max-wait: 1ms  #连接池最大阻塞等待时间
            max-idle: 4 #连接池中最大空闲连接
            min-idle: 0 #连接池的最小空闲连接

condig中的配置类RedisConfig



ctrl+alt+鼠标左键看接口的实现类




## Docker
开启Docker
systemctl start docker

查看docker状态
systemctl status docker
关闭Docker
systemctl stop docker
重启
systemctl restart docker
开机启动
systemctl enable docker

### Docker镜像相关命令

列出本地镜像。
docker images

查看指定线上镜像
docker search redis

从线上拉去 redis  :后面为版本号
docker pull redis
docker pull redis:5.0

删除指定镜像  c5da061a611a为IMAGE ID 
docker rmi c5da061a611a

删除 也可以通过  REPOSITORY:TAG 来指定删除
docker rmi redis:latest

查看所有ID
docker images -q

!!!删除所有镜像！！！
docker rmi `docker images -q`

### Docker 容器

创建容器  -i表示该软件一直保存运行 t分配终端
--name表示给容器起名字
redis:latest 软件的 REPOSITORY:TAG
/bin/bash  打开脚本一样的东西

#### 第一种创建容器
docker run -it --name=c1 centos:7 /bin/bash

退出容器
exit

查看现在正在运行的容器
docker ps

查看现在历史运行的容器
docker ps -a
#### 第二种创建容器

d代表后台运行
docker run -id --name=c2 centos:7 /bin/bash

进入后台容器
docker exec -it c2 /bin/bash


### 查看版本
docker inspect jenkins

docker pull jenkins:2.32.3


docker run -u root -d --name jenkins_01 -p 5000:8080 -p 50000:50000 -v /home/jenkins_home:/var/jenkins_home jenkins:2.32.3

#### docker 停止容器
docker stop jenkins_01
#### docker 启动容器
docker start jenkins_01

#### docker 删除容器
docker rm jenkins_01

### 数据卷

启动 挂载
docker run -it --name=c1 -v /root/data:/root/data_container centos:7 /bin/bash

#### 学习测试数据卷

创建
touch itheima.txt

创建并写入
echo itcast > a.txt

查看文件内容
cat a.txt 


docker run -it --name=c1 -v 




tar -xvf node-v14.17.0-linux-x64.tar.xz

export PATH=$PATH:/usr/local/node/node-v14.17.0-linux-x64/bin

ln -s /usr/local/node/node-v14.17.0-linux-x64/bin/node /usr/local/bin/
ln -s /usr/local/node/node-v14.17.0-linux-x64/bin/npm /usr/local/bin/




## Nginx 学习

### 下载安装
安装依赖包


 yum -y install gcc pcre-devel zlib-devel openssl openssl-devel
 
 安装wget
 yum install wget
 
 下载nginx安装包
 wget https://nginx.org/download/nginx-1.16.1.tar.gz
 
 解压
 tar -zxvf nginx-1.16.1.tar.gz 
 
 创建nginx目录
 mkdir -p /usr/local/nginx
 
 ./conf
 安装到指定目录
 ./configure --prefix=/usr/local/nginx
 
 正式安装
 make && make install
 
 安装Tree命令
yum install tree
 
在sbin目录下查看nginx版本
./nginx -v

查看nginx配置文件正确性
./nginx -t

启动nginx
./nginx

停止nginx
./nginx -s stop

启动完成查看nginx进程
ps -ef|grep nginx

查看nginx   默认80端口
http://192.168.209.128/

查看日志
more access.log

查看进程id文件
-rw-r--r--. 1 root root   5 Jan  9 22:15 nginx.pid

重新加载配置文件
./nginx -s reload

配置Nginx环境变量
vim /etc/profile

重启配置文件
source /etc/profile
 
 
 拷贝文件到指定目录
 cp hello.html /usr/local/nginx/html/
 
 
 文件内编辑查看行数
 set nu
 ### 配置文件讲解
 

 
  #### nginx运行相关的全局配置
 worker_processes  1;
 
  #### 网络连接相关配置
 events {
     worker_connections  1024;
 }
 
  #### nginx运行相关的全局配置
 http {
     include       mime.types;
     default_type  application/octet-stream;
     sendfile        on;
     keepalive_timeout  65;
     server {
        listen       80;  //监听80端口
        server_name  localhost;  //指定服务器名称、域名
         location / {  #匹配客户端请求url
            root   html;  //默认加载html目录中的资源
            index  index.html index.htm;  //默认首页
         }
          error_page   500 502 503 504  /50x.html;
            location = /50x.html {
            root   html;
          }
    }
 }
 
 
 ### 反向代理
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 