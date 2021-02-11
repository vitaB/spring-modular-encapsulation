## prepare database 
* create DB `docker run --name postgres-test-db -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres`
* create schema for moduleA
```sql
CREATE TABLE modulea.entitya (
    ID SERIAL PRIMARY KEY,
    FirstName varchar(255)
); 
```

* create schema for moduleB
```sql
CREATE TABLE moduleb.entityb (
    ID SERIAL PRIMARY KEY,
    LastName varchar(255)
);
```

## publish modules to maven local
* execute 'gradle publishToMavenLocal' in moduleA directory
* execute 'gradle publishToMavenLocal' in moduleB directory
