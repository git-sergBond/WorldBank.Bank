create role myrole;
grant create session to myrole;
grant RESOURCE to myrole;
create user testuser identified by testuser;
grant myrole to testuser;