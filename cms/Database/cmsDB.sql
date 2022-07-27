drop database if exists cmsDB;
create database cmsDB;
use cmsDB;

-- Blog
create table Blog(
	Id int primary key auto_increment,
    Title varchar(50),
    Description VARCHAR(50),
    ExpiryDate Date
);

Create table BlogBody(
	Id int primary Key,
    Body mediumtext,
    foreign key (Id)
		references Blog(Id)
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
		references Blog(Id),
	foreign key (HashtagId)
		references Hashtag(Id)
);

INSERT INTO Blog(Title, Description, ExpiryDate) 
VALUES 
	("title 1 of the post","description 1","2022-07-26"),
    ("title 2 of the post","description 2","2022-07-29"),
    ("title 3 of the post","description 3","2022-08-01"),
    ("title 4 of the post","description 4","2022-08-02"),
    ("title 5 of the post","description 5","2022-08-02");
    
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
    
    
    
    





