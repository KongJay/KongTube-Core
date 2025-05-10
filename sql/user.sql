CREATE TABLE `user_info` (
    -- 必填字段（NOT NULL）
                                 `user_id` varchar(10) NOT NULL COMMENT '用户ID',
                                 `nick_name` varchar(20) NOT NULL COMMENT '用户昵称',
                                 `email` varchar(150) NOT NULL COMMENT '邮箱',
                                 `password` varchar(100) NOT NULL COMMENT '密码(存储加密后的值)',
                                 `join_time` datetime NOT NULL COMMENT '注册时间',
                                 `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0禁用 1正常',
                                 `total_coin_count` int(11) NOT NULL DEFAULT '0' COMMENT '硬币总数量',
                                 `current_coin_count` int(11) NOT NULL DEFAULT '0' COMMENT '当前硬币数量',
                                 `theme` tinyint(1) NOT NULL DEFAULT '1' COMMENT '主题(默认1)',

    -- 可选字段（NULLABLE）
                                 `sex` tinyint(1) DEFAULT '2' COMMENT '性别：0女 1男 2未知',
                                 `birthday` varchar(10) DEFAULT NULL COMMENT '出生日期',
                                 `school` varchar(150) DEFAULT NULL COMMENT '学校',
                                 `person_introduction` varchar(200) DEFAULT NULL COMMENT '个人简介',
                                 `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
                                 `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后登录IP',
                                 `notice_info` varchar(300) DEFAULT NULL COMMENT '空间公告',

    -- 索引
                                 PRIMARY KEY (`user_id`),
                                 UNIQUE KEY `uk_email` (`email`),
                                 UNIQUE KEY `uk_nickname` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';