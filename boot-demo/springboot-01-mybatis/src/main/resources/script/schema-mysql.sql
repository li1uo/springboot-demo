create table `springboot-demo`.system_user
(
    id            int auto_increment
        primary key,
    user_name     varchar(20) default ''                not null,
    user_password varchar(50) default ''                not null,
    create_time   datetime    default CURRENT_TIMESTAMP not null,
    modify_time   datetime                              null,
    is_deleted    tinyint(1)  default 0                 not null,
    version       int         default 1                 not null
);

create index idx_user_01 on `springboot-demo`.system_user (user_name, user_password);