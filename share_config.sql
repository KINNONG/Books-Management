-- ----------------------------
-- Table structure for share_config
-- ----------------------------
DROP TABLE IF EXISTS `share_config`;
CREATE TABLE `share_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `config_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置名称',
  `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'string' COMMENT '配置类型：string, json',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `enable` tinyint DEFAULT '1' COMMENT '是否启用，0：禁用，1：启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_config_key` (`config_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='分享配置表';

-- ----------------------------
-- Records of share_config
-- ----------------------------
INSERT INTO `share_config` VALUES 
(1, 'tencent_meeting_modal_title', '腾讯会议弹窗标题', '复制成功', 'string', '腾讯会议链接复制后弹窗的标题文案', 1, NOW(), NOW()),
(2, 'tencent_meeting_modal_content', '腾讯会议弹窗内容', '腾讯会议下载链接已复制到剪贴板，请在浏览器中打开下载', 'string', '腾讯会议链接复制后弹窗的内容文案', 1, NOW(), NOW()),
(3, 'tencent_meeting_confirm_text', '腾讯会议确认按钮文案', '确定', 'string', '腾讯会议弹窗确认按钮的文案', 1, NOW(), NOW()),
(4, 'tencent_meeting_download_url', '腾讯会议下载地址', 'https://meeting.tencent.com/download-center.html', 'string', '腾讯会议的下载地址', 1, NOW(), NOW()); 