create database if not exists mybatis_database;

use mybatis_database;

create table if not exists user(
    id int unsigned primary key auto_increment comment 'ID',
    name varchar(20) comment '姓名',
    age tinyint unsigned comment '年龄',
    gender tinyint unsigned comment '性别 1:男 2:女',
    phone varchar(11) comment '手机号'
) comment '用户表';

show tables
from
    mybatis_database;

SHOW COLUMNS
FROM
    user;

SELECT
    *
FROM
    user;

insert into
    user(id, name, age, gender, phone)
values
    (null, 'jack', 18, 1, '12345678901');

insert into
    user(id, name, age, gender, phone)
values
    (null, 'jane', 18, 2, '12345678902');

insert into
    user(id, name, age, gender, phone)
values
    (null, 'tom', 18, 1, '12345678903');

insert into
    user(id, name, age, gender, phone)
values
    (null, 'lucy', 18, 2, '12345678904');

SELECT
    *
FROM
    user;




ALTER USER 'root' @'localhost' IDENTIFIED WITH mysql_native_password BY 'mysql123789';

FLUSH PRIVILEGES;

-- 查看密码强度
SHOW VARIABLES LIKE 'validate_password%';

-- 设置密码强度
set global validate_password.policy=LOW;