create table "springboot-demo".system_user
(
    id            serial                                                 not null
        constraint system_user_pk
            primary key,
    user_name     varchar(20)              default ''::character varying not null,
    user_password varchar(20)              default ''::character varying not null,
    create_time   timestamp with time zone default CURRENT_TIMESTAMP     not null,
    modify_time   timestamp with time zone,
    is_deleted    smallint                 default 0                     not null,
    version       integer                  default 1                     not null
);

alter table "springboot-demo".system_user owner to postgres;