
create schema electronic_statement;
use electronic_statement;
CREATE TABLE sheets (
    id int AUTO_INCREMENT PRIMARY KEY ,
    name varchar(100) NOT NULL
);

CREATE TABLE statement_columns (
    id int AUTO_INCREMENT PRIMARY KEY ,
    sheet_id int NOT NULL,
    name varchar(100) NOT NULL,
    FOREIGN KEY (sheet_id) REFERENCES sheets(id)
);

CREATE TABLE statement_rows (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    sheet_id int NOT NULL,
    FOREIGN KEY (sheet_id) REFERENCES sheets(id)
);

CREATE TABLE cell_values (
	id int PRIMARY KEY AUTO_INCREMENT,
    row_id int NOT NULL,
    column_id int NOT NULL,
    value int,
    FOREIGN KEY (row_id) REFERENCES statement_rows(id),
    FOREIGN KEY (column_id) REFERENCES statement_columns(id)
);



CREATE table users(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    password varchar(100) NOT NULL, 
    role varchar(100) NOT NULL
);

select * from users;
select * from statement_columns;
select * from statement_rows;
select * from cell_values;
select * from sheets;

