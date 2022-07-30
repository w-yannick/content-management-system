drop database if exists cmsDBTest;
create database cmsDBTest;
use cmsDBTest;

-- Blog
create table Blog(
	Id int primary key auto_increment,
    Title varchar(50),
    Description VARCHAR(50),
    ExpiryDate Date,
    publishedDate datetime,
    Approved Boolean default false
);

Create table BlogBody(
	Id int primary Key,
    Body mediumtext,
    foreign key (Id)
		references Blog(Id) ON DELETE CASCADE
);


-- Hashtag
create table Hashtag(
	Id int primary key auto_increment,
    Name varchar(50)
    
);

-- BlogHashtag bridge table
create table BlogHashtag(
	BlogId int,
    HashtagId int,
    PRIMARY KEY  (BlogId, HashtagId),
    foreign key (BlogId)
		references Blog(Id) ON DELETE CASCADE,
	foreign key (HashtagId)
		references Hashtag(Id) ON DELETE CASCADE
);

-- Hashtag
create table Contact(
	Id int primary key auto_increment,
    FirstName varchar(50),
    LastName varchar(50),
    Phone varchar(50),
    Email varchar(50),
    Message VARCHAR(255)
);


create Table staticPage(
	id int primary key auto_increment,
    Title varchar(50),
    content mediumtext
);

INSERT INTO Blog(Title, Description, ExpiryDate,publishedDate,Approved) 
VALUES 
	("title 1 of the post","description 1","2022-07-26","2022-07-25",true),
    ("title 2 of the post","description 2","2022-07-29","2022-07-26",true),
    ("title 3 of the post","description 3","2022-08-10","2022-07-27",true),
    ("title 4 of the post","description 4","2022-08-10","2022-07-28",true),
    ("title 5 of the post","description 5","2022-08-10","2022-07-29",false),
    ("title 6 of the post","description 6","2022-08-10","2022-07-29",false),
    ("title 7 of the post","description 7","2022-08-10","2022-07-29",false);
    
INSERT INTO BlogBody(Id, Body) 
VALUES 
	(1,"<p>test content 1</p>"),
    (2,"<p>test content 2</p>"),
    (3,"<p>test content 3</p>"),
    (4,"<p>test content 4</p>"),
    (5,"<p><Strong>test content 5</Strong></p>"),
    (6,"<p>test content 6</p>"),
    (7,"<p>test content 7</p>");
    

INSERT INTO Hashtag(Name) 
VALUES 
	("#Food"),
    ("#Travel"),
    ("#Peace");
    
    
    
INSERT INTO BlogHashtag(BlogId, HashtagId) 
VALUES 
	(1,1),
    (1,2),
    (1,3),
    (2,2),
    (3,1),
    (3,3),
    (5,3),
    (5,2);
    
INSERT INTO StaticPage(Title, Content) 
VALUES 
	("Disclaimer","<p>These are my personal thoughts...../p>");