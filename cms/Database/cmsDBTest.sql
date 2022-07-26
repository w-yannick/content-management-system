drop database if exists cmsDBTest;
create database cmsDBTest;
use cmsDBTest;

-- Blog
create table Blog(
	Id int primary key auto_increment,
    Title varchar(50),
    Description VARCHAR(50),
    Content mediumtext
);

INSERT INTO Blog(Title, description, Content) 
VALUES ("title of the post","description","content");
