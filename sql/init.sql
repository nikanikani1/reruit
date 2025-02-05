-- 创建库
create database if not exists recruit;

-- 切换库
use recruit;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'jobseeker'            not null comment '用户角色：jobseeker/jobfinder',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;


-- 简历表
CREATE TABLE resume (
    resumeId bigint AUTO_INCREMENT PRIMARY KEY COMMENT '简历的唯一标识符',
    userId bigint NOT NULL COMMENT '关联的用户ID，指向users表',
    realName VARCHAR(255) NOT NULL COMMENT '真实姓名',
    summary TEXT COMMENT '个人简介或职业概述',
    experience TEXT COMMENT '工作经历，JSON格式存储详细经历',
    education TEXT COMMENT '教育背景，JSON格式存储详细信息',
    skills TEXT COMMENT '技能列表，JSON格式存储',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) COMMENT='简历表，存储用户的简历信息';


CREATE TABLE notification (
    id bigint AUTO_INCREMENT PRIMARY KEY COMMENT '自动生成的主键ID', -- 自动生成的主键
    title VARCHAR(255) NOT NULL COMMENT '通知的标题', -- 通知的标题
    content TEXT NOT NULL COMMENT '通知的内容', -- 通知的内容
    senderId bigint NOT NULL COMMENT '发送者的用户名或ID', -- 发送者
    receiverId bigint NOT NULL COMMENT '接收者的用户名或ID', -- 接收者
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间', -- 创建时间，默认当前时间
    isDelete     tinyint      default 0                 not null comment '是否删除' -- 是否删除，0表示未删除，1表示已删除
) COMMENT='存储通知信息的表';


CREATE TABLE `job` (
    `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '自动生成的主键ID', -- 自动生成的主键
    `title` VARCHAR(255) NOT NULL COMMENT '职位名称',
    `description` TEXT NOT NULL COMMENT '职位描述',
    `requirements` TEXT COMMENT '最低职位要求，用于粗筛，存储为JSON字符串',
    `location` VARCHAR(255) NOT NULL COMMENT '工作地点',
    `salaryRange` VARCHAR(255) NOT NULL COMMENT '薪资范围',
    `companyName` VARCHAR(255) NOT NULL COMMENT '公司名称',
    `benefits` VARCHAR(1024) NOT NULL COMMENT '额外福利，如有度假，五险一金等',
    `posterId` bigint NOT NULL COMMENT '发布者ID，关联用户表',
    `status` VARCHAR(50) COMMENT '职位状态（例如：活跃、已关闭）',
    `createTime`   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete`     tinyint      default 0                 not null comment '是否删除'
) COMMENT='职位表';


create table vote
(
    id         bigint auto_increment comment '自动生成的主键ID'
        primary key,
    jobId      bigint                             not null comment '发布者ID，关联用户表',
    voterId    bigint                             not null comment '求职者ID，关联用户表',
    matchScore int                                null comment '匹配分数0-100,由简历和职位描述由AI得出',
    advice     text                               null comment 'AI根据职位描述，个性化的提出的建议',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    constraint vote_pk
        unique (voterId, jobId)
)
    comment '简历投递关系表';


