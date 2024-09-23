use ssafydb;

create table test_tbl1
(
	a int primary key,
    b int,
    c int
);

show index from test_tbl1;


create table test_tbl2
(
	a int primary key,
    b int unique,
    c int unique
);

show index from test_tbl2;

create table test_tbl3
(
	a int unique,
    b int unique,
    c int unique
);

show index from test_tbl3;

create table test_tbl4
(
	a int unique not null,
    b int unique,
    c int unique,
    d int 
);

show index from test_tbl4;

create table test_tbl5
(
	a int unique not null,
    b int unique,
    c int unique,
    d int primary key
);

show index from test_tbl5;