create role myrole;
grant create session to myrole;
grant RESOURCE to myrole;

create user testuser identified by testuser;
grant myrole to testuser;

--FIX PROBLEM WITH:
-- 1) DISK QUOTA
-- 2) ORA-01950: no privileges on tablespace 'USERS'
ALTER USER testuser quota 100M on USERS;

--FIX PROBLEM WITH:
--Liquibase needs to access the DBA_RECYCLEBIN table so we can automatically handle the case where constraints are deleted and restored. Since Oracle doesn't properly restore the original table names referenced in the constraint, we use the information from the DBA_RECYCLEBIN to automatically correct this issue.
--The user you used to connect to the database (TESTUSER) needs to have "SELECT ON SYS.DBA_RECYCLEBIN" permissions set before we can perform
GRANT SELECT ON SYS.DBA_RECYCLEBIN TO TESTUSER;