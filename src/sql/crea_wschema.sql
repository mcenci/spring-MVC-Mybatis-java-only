whenever sqlerror exit

set echo on
spool create_schema.log

CREATE SEQUENCE SQ_USERS INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CYCLE 
/

@c_users
@i_users

commit

select 'SCHEMA SUCCESFULLY CREATED !!!' from dual;

spool off
