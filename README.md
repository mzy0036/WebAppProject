# WebAppProject

Title: K-12 Math Education Online Website.  
Problem Statement: Design website using JSP, Servlet, XML and MySQL.                                                                                          
Purpose and Scope: Design an online math education website to help K-12 student better receive math education.

# K-12 Math Education Online Website
**Authors:**
- Mengjin Ye (mzy0036)
- Taylor Newton (tjn0012)
- Edward Riley (emr0085)
- Austin Rowell (arr0099)

## Synopsis
- **Problem Statement:** Design website using JSP, Servlet, XML, and MySQL.                                                                                          
- **Purpose and Scope:** Design an online math education website to help K-12 student better receive math education for K-12 users.  


## 1. Introduction
Today, children learn in various ways. In addition to in-person school, they utilize online learning to master knowledge easily and efficiently. The main purpose of this project is to design an online math education website that will help K-12 students receive an improved math education.

## 2. Description
### K-12 Math Education (KME) is a proven online learning platform that helps students master course topics through a continuous cycle of mastery, knowledge retention, and positive feedback.

Additionally, KME helps educators and parents understand each student's knowledge and learning progress in depth and provides the unique support required for every student to achieve mastery.

### Target Audience:
- **Primary Education:** Students and faculties in kindergarten through 6th grade.
- **Secondary Education:** Students and faculties in 7th grade through 12th grade.

### User Role Type Actions:
- **Teacher (Educator):** sets up courses, grades students, and provides online tutoring.
- **General user (Student):** enrolls and manages courses, take quizzes, and asks for assistance through the chatting room or forum.

### Two Categories Supported Courses:
- Algebra
- Geometry

## 3. Core Functionalities
### General Functionalities:
- User Login: provides different login pages and functions based on user type.

### Faculty Functionalities:
- Teacher Course Management: Creates new online lessons and quizzes and uploads syllabus.
- Teacher Student Management: Edit registered students and grade student quizzes.
- Teacher-Student Support: Supports student to connect with their teachers and reach out for extra help through chatting room. 
- Teacher-Student Support: Supports sending out annoucements to enrolled students

### Student Functionalities:
- General User Enrollment: Students are permitted to enroll in courses.
- General User Post Enrollment: Students can take online courses and corresponding quizzes.
- Independent Learning Courses: Students can choose personal learning courses without teachers.
- Student Interactivity: 
  - Students can post questions on the web page, and other students or teachers can reply and answer them.
  - Supports students to join interactive discussions about their courses through the chatting room.

## 4. Technical Architecture

### Stack Architecture:
Developed using Java Servlets, JSP templates, JDBC driver, and MySQL database.

### Java Server Pages
Applications can use Java Server Pages (JSP) to implement web pages. JSP is a servlet that mixes static content (such as HTML) and Java code definitions.

### URL+XML Pathing
Web.xml defines the mapping between URL paths and servlets that handle requests through those paths. The web server uses this configuration to determine the servlet to handle a given request and to invoke the class method that corresponds to the request method (such as the doGet() method of an HTTP GET request).  To map a URL to a servlet, you declare the servlet with the `<servlet>` element and then define the mapping from the URL path to the servlet declaration via the `<servlet-mapping>` element. JSP is compiled into a servlet class automatically, and mapped to the URL path equivalent to the path to the JSP file.

### Integrated Development Environment (IDE)
#### Altova XMLSpy
Altova XML Spy is an IDE specialized for XML-related data that also allows you to query and manipulate data directly from SQL databases, making it well-suited for working with XML, JSON, and relational database data together.

#### Intellij IDEA
IntelliJ IDEA is a strong IDE robust that provides support for servlet and Java Server Pages (JSP) programming through its intelligent coding assistance. This includes context-aware code completion, navigation, and refactoring tools, helping developers write more reliable and maintainable code efficiently. It has a seamless integration with server environments and frameworks, along with built-in tools for debugging and testing web applications, makes it a comprehensive and convenient choice for web developers.


#### Visual Studio Code (VSC)
Visual Studio Code is well-known for its lightweight application including extensive plugin ecosystem and robust support for a wide range of programming languages and tools. This enhancement makes Visual Studio Code highly versatile and efficient for developers across various disciplines.


### Git Version Control (GitHub)
Git version control helps programmers keep track of changes they make to their code, so if something goes wrong, they can easily go back to an earlier version that worked well. It also makes it easier for multiple people to work on the same project at the same time, without messing up each other's code. The git repository code is stored on the GitHub cloud services.


### MySQL Database:
MySQL is a widely used, open-source relational database management system. We can use it to store users’ registered information. This makes MySQL A robust, open-source relational database management system for storing and managing data, such as user registration details. Web applications with MySQL can efficiently handle large volumes of data with high reliability and security, ensuring user information is both accessible and protected.
<img width="822" alt="截屏2024-04-20 下午6 41 10" src="https://github.com/mzy0036/WebAppProject/assets/38323190/26b84362-43fb-417f-89ff-11455a4cfc30">
![image](https://github.com/mzy0036/WebAppProject/assets/38323190/0984f617-85e8-4a05-abbb-f2b26fc457b7)


### High Level Architecture
![High Level Architecture](docs/img/High%20Level%20Architecture%20-%20MVC.png)

## 5. Timelines
4/10/2024 ~ 4.2 Project Proposal<br />
4/18/2024 ~ Technical Proposal <br />
4/22/2024 ~ Complete Login page and Functionality and set up Database<br />
4/26/2024 ~ Complete Teacher Functionality <br />
5/02/2024 ~ Complete Student Functionality<br />
5/03/2024 ~ Review and Final Submission<br />

## 6. Potential Challenges 
- Because different types of users have different purposes, different functions need to be designed for the same web page.
- The server side receiving user requests, calling database data, jumping to different pages, and managing functions may be our biggest problem.
- There could be unforeseen authentication issues.
- When the JSP File is converted to Servlet and if the JSP service method size exceeds a limit of 64K bytes, the JSP file will not compile, throwing the below error.


## 7. Deliverers
GitHub: [https://github.com/mzy0036/WebAppProject.git](https://github.com/mzy0036/WebAppProject.git)
