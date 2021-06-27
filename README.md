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

Functionalities 
* Check Balances
  The user will be able to view their balance in real time directly on the home page. Links to transaction history
* View Transaction History
  Directly from the homepage the user will be able to see a short snippet of their transactions. To view more they can click a link to go to their full transaction history, stored for 7 years.
* Password Recovery
  The user will be able to reset/recover their password anytime on their own without contacting customer service. The Forum will ask confidential information to verify they are the owner, and if they fail 3x their account will be locked. They will then contact customer service (See admin page).
*Account Creation
  The user will be able to register anytime online without staff interaction. They will be able to enter information on a forum that will be validated and processed.
*Transfer funds to Users
  The user will be able to transfer funds to and request funds from other users. Information about fund availability will be supplied to users before the transaction is confirmed.
*Transfer funds To/From bank
  The user will be able to use a form to send money to and from their personal bank accounts with a notification on expected transfer times.
* Add friends and view contacts
  Users will be able to add and view their contacts, directly interact to send and request money and view transactions with the contact.
* Admin Page
  Admins will be able to log into their own page for additional functionalities 
* Unlock/Lock Account
  The admin will be able to unlock a users account and lock an account
* View Transactions
  The admin will be able to view all transaction from a user, and all global transactions in real time
* Cancel Transactions
  The admin will have the option to interact with live transactions to cancel them, transaction cancellation will reflect on sending user history

