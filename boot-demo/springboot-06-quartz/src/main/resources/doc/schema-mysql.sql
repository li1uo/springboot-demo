create table `springboot-demo`.schedule_log
(
    id            int auto_increment
        primary key,
    task_id       varchar(100)                       not null,
    status        tinyint(1)                         not null,
    exec_status   tinyint(1)                         not null,
    exec_detail   text                               null,
    error_message text                               null,
    exec_time     int                                null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    affect_row    int      default 0                 not null
)
    comment '定时任务日志';

create index idx_schedule_log_01
    on `springboot-demo`.schedule_log (create_time, task_id);

create table `springboot-demo`.system_schedule
(
    id              int                                    not null
        primary key,
    class_name      varchar(100)                           not null,
    cron_expression varchar(50)                            not null,
    job_name        varchar(50)                            not null,
    job_group       varchar(20)  default 'DEFAULT'         not null,
    status          tinyint(1)                             not null comment '任务状态(1 正常　0 删除　-1 暂停)',
    description     varchar(100) default ''                not null,
    create_time     datetime     default CURRENT_TIMESTAMP not null,
    modify_time     datetime                               null
)
    comment '定时任务';

INSERT INTO `springboot-demo`.system_schedule (id, class_name, cron_expression, job_name, job_group, status, description, create_time, modify_time) VALUES (1, 'demo.springboot.config.schedule.job.Task', '0/10 * * * * ? ', '定时任务1', 'DEFAULT', 1, '测试定时任务', '2020-03-09 14:48:41', null);
