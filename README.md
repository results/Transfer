# Transfer
Spring Boot based mock money transfer site. Case Study for Per Scholas.

Objective: 
Create an online webpage that allows users to register, login, add friends, create transfer between users, view transaction receipts, and withdraw funds.

Technologies Used
* HTML
* CSS
* JSP
* JavaScript
* Java 8 EE
* Spring Boot / Spring MVC / Spring JPA
* JUNIT / Mockito


Challenges
* Creating different tables for User Information to try to avoid exposing them to the front end. (e.g. UserLogin information will never be sent to front, just UserInformation)
* Transactions table refused to add both sender and receiver to the table, needed to create a different table for each.


Still To Do
* Encrypted Login details with Spring Security
* Ability to Delete your account
* Ability to Search transactions
* Ability to request money from other users
* Rework front end to be more appealing (things not centered etc)
* Admin page to manage accounts and transactions
* A form to add money to account from bank account/debit card, not just a button
* Refactor project to be better organized

DataBase Schema
![alt text](https://github.com/results/Transfer/blob/main/Diagrams/schema.PNG?raw=true)
