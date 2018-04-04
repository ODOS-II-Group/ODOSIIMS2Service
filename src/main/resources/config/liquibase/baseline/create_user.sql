create role :databaseAppRole with 
 NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
 
create role :databaseAppUser with 
 LOGIN PASSWORD :databaseAppUserPassword
 NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE NOREPLICATION in role :databaseAppRole;

grant all on database :databaseName to :databaseAdminUser;
grant connect, temporary on database :databaseName to :databaseAppRole;
revoke all on database :databaseName from public;
 
