# Little CRM

Spring MVC web application for messaging and tasks exchange between registered users.

## Description

It is my first project. In this application a registered user can write messages, create and complete tasks. User with admin role is authorised to add new users and update the data of existing ones. The application is written using Spring MVC framework and Maven for external dependency managment.

## Link to the app on Heroku

[Little CRM](https://little-crm.herokuapp.com/auth/login)

**login:** admin 

**password:** admin

## Database & configuration

- PostgreSQL
- Maven
- Git

## Backend technologies
- Java
- Spring MVC, Spring Boot, Spring Security
- Hibernate ORM, Hibernate Validator

## Frontend technologies
- HTML, Thymeleaf

## Database Diagram

![image](https://github.com/zoya0107/zoya0107/blob/main/LittleCRM_db_structure.png)

## Functionality

*Message* is created by one of the users who specifies the receiver's login, topic and content. The date field is automatically filled with today’s date . The sender can view created message in the “sent messages and tasks” tab, the receiver - in the “received messages and tasks” tab. When viewing a received message, the sender's first and last name are indicated in the header, when viewing sent messages - “message from you”.

*Task*, in addition to the message functionality, provides the ability to specify the due date and urgency (check box). The due date cannot be past. When the performer (receiver) views the task, the “solution” window is available for input. By clicking the “send” button the receiver changes the task status from “registered” to “done”. After that, the author of the task reviews the solution and changes the status to “approved” by clicking “approve”.

*Person* has two types of roles:
- User - can view the list of users, write messages, give tasks and complete them.
- Admin - person, who can do the same as user, and also add, edit and delete users.

All data except login can be changed. To each user an “active” or “banned” status is assigned. When a person is deleted, status changes to “banned”, and “existence” is set to false.
