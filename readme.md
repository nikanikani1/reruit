### 项目介绍

本项目是一个在线求职招聘平台的系统。功能包括

1. 用户注册与角色区分（求职者/招聘者）；

2. 职位搜索与匹配推荐；
3. 面试时间预约与通知；
4. 数据分析报告



### 环境依赖

jdk1.8

### 目录结构描述

```
├─sql 数据库初始化文件
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─nika
│  │  │          └─recruit
│  │  │              ├─aspect  切面代码
│  │  │              ├─base    基础公共代码，如分页对象，请求返回体
│  │  │              ├─config  配置代码
│  │  │              ├─constants 常量信息
│  │  │              ├─controller  控制类
│  │  │              ├─enums 枚举类
│  │  │              ├─exception  异常类
│  │  │              ├─job  定时任务
│  │  │              ├─mapper  
│  │  │              ├─model
│  │  │              │  ├─dto 数据传输对象（请求类等）
│  │  │              │  ├─entity  数据库对应实体类
│  │  │              │  ├─enums  枚举类
│  │  │              │  └─vo  展示类（用于前端展示）
│  │  │              ├─service
│  │  │              │  └─impl 
│  │  │              └─utils 工具类
│  │  └─resources
│  │      └─mapper
```

