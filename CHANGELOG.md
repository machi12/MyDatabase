# 更新日志

### 1.1

实现了仿照MySQL命令行的输入形式,在控制台输入命令后以";"作为命令的结束,并且实现了获取命令后将命令格式化,如去掉多余空格等.

### 1.2

实现了create database 数据库名称;命令.

### 1.3

实现了show databases;命令.

### 1.4

实现了show table;命令以及create table的部分命令,修复了指令识别的一个bug.

### 1.5

实现了create table 表名(.......);指令.

在该数据库中,默认使用文本文件存储表,文本文件的前三行存放表的控制信息,之后的行用来存储数据信息(这部分还未实现,之后会慢慢实现),其中第一行存放列名,第二行存放列的数据类型,第三行用来存放其余控制信息(像not null之类的).实现该部分时,比较麻烦的是对输入命令的解析,需要提取出需要的各个部分,这里主要使用正则表达式和String类的相关方法.

### 1.6

实现了describe 表名;指令.

打印表的相关控制信息,这部分自己实现的并不好,主要是没有把一些控制信息详细的分类而都放在了打印出来的最后一列.之后可能还会改进.

其实进行到这的时候,自己对于数据库最基本的存储方面有了一定的理解,知道在实际写一个DBMS时那部分是最难实现的,但是由于这些都是自己摸索这些的,当然也有一位大佬的帮助(站在巨人的肩膀上),所以在实现的过程中没有考虑到效率的问题,只是做出来能用.

### 1.7

实现了insert into 表名 (字段) values (值);<br/>
这部分基本对应存储方面实现了,但是对于各个字段的类型检查没有实现.

### 1.8

实现了select * from 表名;<br/>
对于select语句只实现了这一种查询方式,之后可能会再加上where子句的查询方式.查询是数据库操作中最复杂的一部分,所以感觉自己还是太菜,对于其他查询方式没有好的实现方法.

### 1.9

实现了drop database 数据库名; 和 drop table 表名;

### 最后

写到这里最基本的东西已经写完了,这个控制台DBMS也就告一段落了,之后打算写一篇博文来总结一下写这个程序的整个过程.<br/>
算是尊重作者劳动吧,希望大家在Fork前先点star.对这个程序有什么问题或者想要讨论更好的实现的,可以通过我GitHub上的邮箱来联系我,希望大家互相学习.