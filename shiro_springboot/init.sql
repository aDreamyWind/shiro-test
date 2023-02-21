create table shiro_user
(
    id   bigint(20) not null auto_increment comment '编号',
    name varchar(30) default null comment '用户名',
    pwd  varchar(50) default null comment '密码',
    rid  bigint(20)  default null comment '角色编号',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3803
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

insert shiro_user values (null, 'zhangsan', '7174f64b13022acd3c56e2781e098a5f', null);
insert shiro_user values (null, 'lisi', '7174f64b13022acd3c56e2781e098a5f', null);

select * from shiro_user;