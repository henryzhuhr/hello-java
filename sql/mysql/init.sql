DROP TABLE IF EXISTS `user`;
-- 创建数据库
CREATE TABLE `user` (
    `id` varchar(255) NOT NULL COMMENT 'uuid',
    `email` varchar(255) NOT NULL COMMENT '邮箱',
    `username` varchar(255) NOT NULL COMMENT '用户名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);


-- 随机生成一些数据
INSERT INTO
    `user` (
        `id`,
        `username`,
        `password`,
        `email`
    )
VALUES (
        '47bf8a21-9e95-40c9-a3b4-8eecc455f90d',
        'admin',
        'admin',
        'a@gmail.com'
    );