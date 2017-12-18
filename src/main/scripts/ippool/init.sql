CREATE TABLE `ip_crawler` (
  `ip` varchar(80) DEFAULT NULL,
  `port` varchar(10) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '未处理[0]/废弃[-1]/处理中[1]',
  `acquisition_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '最近一次评测时间',
  `perform_flage` varchar(20) DEFAULT NULL,
  `evaluation_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '最近一次评测时间',
  `creation_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;