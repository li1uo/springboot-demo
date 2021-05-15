DECLARE
vn_count PLS_INTEGER;
BEGIN
SELECT COUNT(*) INTO vn_count FROM user_tables WHERE upper(table_name) = upper('HDSI_LOG');
IF vn_count = 0 THEN
          EXECUTE IMMEDIATE 'create table HDSI_LOG
             (
                  ID            VARCHAR2(40)               not null
                                             constraint HDSI_LOG_PK
                                             primary key,
                  TYPE_NO       VARCHAR2(10)               not null,
                  STATUS        NUMBER(1)  default 1       not null,
                  EXEC_STATUS   NUMBER(1)                  not null,
                  EXEC_DETAIL   CLOB,
                  ERROR_MESSAGE CLOB,
                  EXEC_TIME     NUMBER(10) default 0       not null,
                  CREATE_TIME   DATE       default sysdate not null,
                  AFFECT_ROW    NUMBER(8)  default 0       not null
             )';
EXECUTE IMMEDIATE 'comment on table HDSI_LOG is ''wms中间件日志表''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.TYPE_NO is ''任务编号''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.STATUS is ''定时任务执行状态(1 成功 0 失败)''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.EXEC_STATUS is ''数据同步情况(1 已同步数据 0 未同步数据)''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.EXEC_DETAIL is ''详细日志''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.ERROR_MESSAGE is ''异常日志''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.EXEC_TIME is ''执行花费时间(毫秒)''';
EXECUTE IMMEDIATE 'comment on column HDSI_LOG.CREATE_TIME is ''执行时间''';
EXECUTE IMMEDIATE 'create index HDSI_LOG_01 on HDSI_LOG (TYPE_NO)';
EXECUTE IMMEDIATE 'create index HDSI_LOG_02 on HDSI_LOG (CREATE_TIME)';
end if;
end;