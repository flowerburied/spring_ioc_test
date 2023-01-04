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


