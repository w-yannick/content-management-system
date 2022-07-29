drop database if exists cmsDB;
create database cmsDB;
use cmsDB;

-- Blog
create table Blog(
	Id int primary key auto_increment,
    Title varchar(50),
    Description VARCHAR(50),
    ExpiryDate Date,
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

INSERT INTO Blog(Title, Description, ExpiryDate,Approved) 
VALUES 
	("title 1 of the post","description 1","2022-07-26",true),
    ("title 2 of the post","description 2","2022-07-29",true),
    ("title 3 of the post","description 3","2022-08-10",true),
    ("title 4 of the post","description 4","2022-08-10",true),
    ("title 5 of the post","description 5","2022-08-10",false);
    
INSERT INTO Blog(Title, Description) 
VALUES 
	("title 6 of the post","description 6"),
    ("title 7 of the post","description 7");
    
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
    (3,3);
    
    
    
    





