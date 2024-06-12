包含三个文件夹：
work1：服务端，包含作业一和作业二的一共四个接口，在本地可直接启动进行部署。
collector: 作业一的collector,可在linux虚拟机或者docker中部署运行
collector_all: 包含作业一和作业二的collector,目前可在本地启动，定时收集本地Resource/test目录下的日志文件的新增内容，并上传到数据库

作业一数据库表metrics:
CREATE table metrics
(
id bigint AUTO_INCREMENT PRIMARY KEY,
metric varchar(20),
endpoint varchar(20),
timestamp bigint,
step bigint,
value double
);



作业二数据库表loglog:
CREATE table loglog
(
id int(64) AUTO_INCREMENT PRIMARY KEY,
hostname varchar(20),
file varchar(200),
logs longtext
);
