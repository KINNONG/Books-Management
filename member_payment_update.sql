-- 更新base_member表，添加收款账户相关字段
-- 执行前请先备份数据库

ALTER TABLE `base_member`
ADD COLUMN `id_card` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '身份证号' AFTER `create_time`,
ADD COLUMN `payment_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '支付类型：wechat-微信，alipay-支付宝，bank-银行卡' AFTER `id_card`,
ADD COLUMN `wechat_account` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '微信账户' AFTER `payment_type`,
ADD COLUMN `alipay_account` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '支付宝账户' AFTER `wechat_account`,
ADD COLUMN `bank_card_number` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '银行卡号' AFTER `alipay_account`,
ADD COLUMN `bank_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '银行名称' AFTER `bank_card_number`,
ADD COLUMN `cardholder_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '持卡人姓名' AFTER `bank_name`;

-- 为已存在的测试用户添加示例数据
UPDATE `base_member` SET 
    `payment_type` = 'wechat',
    `wechat_account` = 'test_wx'
WHERE `id` = 68; 