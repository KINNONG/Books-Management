# Welcome to Books-Management 👋



## 前言

`Books-Management` 项目致力于为学生打造一个简单、方便、快捷、易于二次开发的毕设项目



## 项目介绍

`Books-Management` 项目是一套图书馆信息管理系统，包括用户小程序以及后台管理系统，基于SpringBoot+MyBatis实现。前台商城系统包含用户注册登录、首页门户、图书查询、在线借阅、个人中心、我的信息、我的借阅、押金充值。后台管理系统包含统计分析、用户管理、分类管理、图书管理、借阅管理、管理员管理、统计报表、设置等模块。



### 项目演示

#### 后台管理系统

前端项目 `Books-Server` 地址：https://github.com/KINNONG/Books-Management.git
![4d679fcf6a8241489c313f82e8a5d08](https://user-images.githubusercontent.com/57580672/207652410-f6ca8dc5-f10a-4216-868d-abdfa4c339f3.png)



#### 小程序

前端项目 `Books-Miniapp` 地址：https://github.com/KINNONG/Books-Miniapp.git

![image](https://user-images.githubusercontent.com/57580672/207651966-0514a923-8dfe-4bfd-9600-e50f9a150ab0.png)



#### 技术选型

##### 后端技术

|      技术      |      说明       |                       官网                        |
| :------------: | :-------------: | :-----------------------------------------------: |
|   SpringBoot   | Web应用开发框架 |      https://spring.io/projects/spring-boot       |
| SpringSecurity | 认证和授权框架  |    https://spring.io/projects/spring-security     |
|    MyBatis     |     ORM框架     |  http://www.mybatis.org/mybatis-3/zh/index.html   |
|      JWT       |   JWT登录支持   |           https://github.com/jwtk/jjwt            |
|     Docker     |  应用容器引擎   | [https://www.docker.com](https://www.docker.com/) |
|     Maven      |  项目管理工具   |             https://maven.apache.org/             |



##### 前端技术

| 技术      | 说明                               | 官网                                     |
| --------- | ---------------------------------- | ---------------------------------------- |
| Vue       | 前端框架                           | https://vuejs.org/                       |
| JQuery    | 前端框架                           | https://jquery.com/                      |
| Bootstrap | 前端框架                           | https://getbootstrap.com/                |
| ColorUI   | 前端UI框架                         | https://github.com/weilanwl/ColorUI      |
| Ajax      | 前端HTTP框架                       | http://ajax.p2hp.com/                    |
| Echarts   | 基于 JavaScript 的开源可视化图表库 | https://echarts.apache.org/zh/index.html |



#### 架构图

<img width="416" alt="image" src="https://user-images.githubusercontent.com/57580672/207651841-047560e8-f753-4722-8c1c-a9e8deb80ca7.png">



## 环境搭建

### 开发工具

| 工具    | 说明           | 官网                                    |
| ------- | -------------- | --------------------------------------- |
| IDEA    | 开发IDE        | https://www.jetbrains.com/idea/download |
| Navicat | 数据库连接工具 | http://www.formysql.com/xiazai.html     |
| Typora  | Markdown编辑器 | https://typora.io/                      |

### 开发环境

| 工具           | 版本号         | 下载                                                         |
| -------------- | -------------- | ------------------------------------------------------------ |
| JDK            | 1.8            | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql          | 5.7            | https://www.mysql.com/                                       |
| 微信开发者工具 | 稳定版         | https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html |
| Navicat        | 数据库连接工具 | http://www.formysql.com/xiazai.html                          |



###  如何使用

#### 后端项目

```
//1、克隆该项目
git clone https://github.com/KINNONG/Books-Management.git

//2、导入idea中打开项目
//3、打开Navicat,运行a_book.sql文件
//4、运行项目
//5、localhost:8080
```



#### 前端项目

```
//1、克隆该项目
git clone https://github.com/KINNONG/Books-Miniapp.git

//2、导入微信开发者工具中打开项目
```

### ⚠提示
如需使用小程序授权登录，请在`Books-Management` 中的 `application.yml` 文件中修改 `appid` 和 `appsecret`
<img width="494" alt="ab63d5b23237500cb80fe096f58905b" src="https://user-images.githubusercontent.com/57580672/207667746-d33d2b90-928b-4043-acf1-1bfb56eacd6a.png">
![image](https://user-images.githubusercontent.com/57580672/207667375-0f18a7d8-42ea-4bc6-9fb6-cb5490fcaf7c.png)
