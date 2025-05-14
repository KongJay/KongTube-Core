CREATE TABLE `category_info`(
    `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `category_code` varchar(30) NOT NULL COMMENT '分类编码',
    `category_name` varchar(30) NOT NULL COMMENT '分类名称',
    `p_category_id` int(11) NOT NULL COMMENT '父分类id',
    `icon` varchar(50) DEFAULT NULL COMMENT '图标',
    `background` varchar(50) DEFAULT NULL COMMENT '背景图',
    `sort` tinyint(4) NOT NULL COMMENT '排序号',
    PRIMARY KEY (`category_id`) USING BTREE,
    UNIQUE KEY `idx_key_category_code` (`category_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类信息表';