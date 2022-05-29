# 基于SpringBoot+Vue实现的物业报修管理系统


### 介绍

本项目是前后端分离项目，前端基于Vue+Element UI组件，后端基于SpringBoot，前后端使用json进行信息交互。



系统只有后台管理部分，用户端暂时未实现。系统包含报修列表、公告、用户三个模块，是一个简单的SpringBoot、SpringSecurity、Mybatis-Plus练手项目，其中登录部分依赖Redis缓存实现。

### 文件说明

* ***repairProject***：SpringBoot项目文件，导入idea即可
* ***springboot_repair.sql***：数据库文件，需要导入到mysql数据库
* ***物业报修管理系统 -小论文.doc*** ：系统详细介绍，小论文一篇

### 安装教程

1. MySQL中新建一个数据库，将SQL文件导入。
2. 把工程导入到idea中（可能会需要在settings中将maven仓库修改成自己地址）。
3. 将配置文件application-dev.yml中的数据源信息修改成自己对应的。
4. 将配置文件application-dev.yml中的redis修改成自己对应的地址和端口（默认地址：localhost，端口：6379）。
5. 启动项目即可，默认访问路径为：http://localhost:8080/web/index.html，出现登录页面表示项目启动成功（导入的数据库中默认账户：admin，密码：123）。

> **注意：**
>
> * 导入工程后Maven报错无法刷新，检查settings中Maven仓库地址是否修改成自己的地址。
> * 看到登录页面登录失败，检查Redis是否启动，检查数据库密码等信息是否在配置文件中修改过来了。
> * 数据库 *sys_user*  表中的密码是加密存储的，不要随意删除和修改admin账户密码信息

### 注意

项目默认访问路径：http://localhost:8080/web/index.html

用户名：admin

密码：123

![](https://cdn.jsdelivr.net/gh/CheckWk/imageHome/UsualNotes/image-20220529194804790.png)

![image-20220529194804790](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220529194804790.png)

