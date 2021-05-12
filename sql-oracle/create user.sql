create role myrole;
grant create session to myrole;
grant RESOURCE to myrole;

create user testuser2 identified by testuser2;
grant myrole to testuser2;

--FIX PROBLEM WITH DISK QUOTA
ALTER USER testuser2 quota 100M on USERS;