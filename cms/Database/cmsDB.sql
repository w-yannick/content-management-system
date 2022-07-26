drop database if exists cmsDB;
create database cmsDB;
use cmsDB;

-- Blog
create table Blog(
	Id int primary key auto_increment,
    Title varchar(50),
    Description VARCHAR(50),
    Content mediumtext
);

INSERT INTO Blog(Title, description, Content) 
VALUES ("title of the post","description","content");
