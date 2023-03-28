# Completed tasks

**Master** branch contains realisation of 1-3 + additional task, while **with_database** branch contains realisation of
1-7 + additional task.

### Equation viability checks

The checks for equations are being made on construction of Equation object, if given equation is
not viable, the isViable field sets to false with output of the reason in console.

### Validity of root for equation

... is being checked by library **matheclipse-parser** from **org.matheclipse**

### Number counting

in equation is realised by regular expression with pattern object, count of number is returned from **getNumbers()** in
Equation class

# DataBase realisation

**PostgreSQL** was used as database, **JDBC** as api layer. The database name is "testtask".

### table for equations:

Name of the table is **equations**. Fields: **id** (primary key), **body** (varchar (256), not null).

### table for roots:

Name of the table is **equation_to_roots**. Fields: **eq_id**(integer, references equations(id)), **root** (varchar(
256), not null).

#### other:

.sql file for db and tables also was created in **src/main/resources/sql/init_database.sql**. **SQL prompts** where
raised to
separate class of **utils** directory, named **SQLConstants**
