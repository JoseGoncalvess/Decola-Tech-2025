CREATE TABLE employees_audit(
    id BIGINT not null auto_increment,
    name VARCHAR(100) not null,
    old_name VARCHAR(100) default null,
    salary DECIMAL(10,2) not null,
    old_salary DECIMAL(10,2) default null,
    birthday TIMESTAMP not null,
    old_birthday TIMESTAMP default null,
    operation CHAR(1) ,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;