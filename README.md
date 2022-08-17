# content-management-system
Content Management System v0.1 hosted at heroku.com  
https://my-cms-project-y.herokuapp.com/  

## Features
This cms allows a user to create, manage and publish blogs.   
Anyone can see the blogs in the home page and can contact the company using the contact tab.  
A manager can create blogs, but need to wait for the approval of the admin in order for the blogs to be published.

An admin can create, edit or delete a blog. The admin also need to approve a blog for it to be published.



## Login

There are currently two users :

###### Admin user:

admin:password

###### Manager user:

manager:password

# Tech Stack

###### Programming language : Java
Java 11

###### Framework: Spring
This project uses the Spring framework in order to manage the dependency and the web application

###### DB: ClearDB MySQL
hosted at heroku.com on a free plan, with a limit of 5mb.

###### JpaRepository 
manage the connection to the database and allows CRUD request

###### FrontEnd: Thymeleaf, Javascript, css, bootstrap,...

###### Hosting service
This project is hosted on heroku and uses an automated deploy system. A new version is released everytime the master branch is updated.