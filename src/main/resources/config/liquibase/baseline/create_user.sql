create role :databaseAppRole with 
 LOGIN PASSWORD :databaseAppPassword
 NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

grant all on database :databaseName to :databaseAdminUser;
grant connect, temporary on database :databaseName to :databaseAppRole;
revoke all on database :databaseName from public;
 
