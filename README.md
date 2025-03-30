# 🏦 Banking Management System

## 📚 Project Overview
This Banking Management System is developed using **Java** and **JDBC**. The system performs operations such as:
- Creating a new account
- User login
- Performing secure transactions
- Checking account balance

---

## 📝 Class Descriptions

### 1. `User.java` 📧
- **Purpose:** Handles user registration and login.
- **Key Methods:**
    - `register()` → Registers a new user.
    - `login()` → Authenticates the user.
    - `user_exist(String email)` → Checks if the user already exists.

---

### 2. `Account.java` 🏦
- **Purpose:** Manages account creation and account information retrieval.
- **Key Methods:**
    - `create_accn(String email)` → Creates a new bank account for the user.
    - `get_accountno(String email)` → Retrieves the account number using the email.
    - `Account_exist(String email)` → Checks if the account exists.

---

### 3. `AccountManager.java` 💸
- **Purpose:** Handles all transaction-related functionalities.
- **Key Methods:**
    - `debit_money(Long accountno)` → Deducts money from an account.
    - `credit_money(Long accountno)` → Adds money to an account.
    - `getbalance(long accn_no)` → Checks account balance.
    - `transfer_money(Long senders_accountno)` → Transfers money between accounts.

---

### 4. `bankingApp.java` 📚
- **Purpose:** Main application that initializes the system and handles user choices.
- **Features:**
    - Login/Register
    - Open Bank Account
    - Perform Banking Operations (Debit, Credit, Transfer, Check Balance)

---

## ⚙️ How It Works
### 1. **Register a New User**
- Input Full Name, Email, and Password.
- Validates and inserts data into the `user` table.

✅ **Database Entry after Registration**
```
INSERT INTO user (user_name, email, password) 
VALUES ("John Doe", "john@example.com", "password123");
```
![User Registration Success](https://github.com/shruti-ugale-code/Banking_management_system/blob/main/registration.img.png?raw=true)
![User Registration Success](https://github.com/shruti-ugale-code/Banking_management_system/blob/main/registration%20table.png?raw=true)

---

### 2. **Login with Credentials**
- Verifies credentials and returns the email if valid.
- Proceeds to account management if the user exists.

---

### 3. **Create New Account (if not exists)**
- Generates a unique account number.
- Inserts initial balance and security pin.

✅ **Database Entry after Account Creation**
```
INSERT INTO accounts (account_no, user_name, email, balance, security_pin)
VALUES (10000100, "John Doe", "john@example.com", 5000, "1234");
```
![Account Created Successfully](https://github.com/shruti-ugale-code/Banking_management_system/blob/main/Screenshot%202025-03-30%20202717(1).png?raw=true)
![Account Created Successfully](https://github.com/shruti-ugale-code/Banking_management_system/blob/main/Account%20create.png?raw=true)

---

### 4. **Perform Transactions**
- Debit, Credit, or Transfer money securely with pin authentication.
- Ensures transaction rollback on failure for data consistency.

✅ **Money Transfer Transaction Example**
- Sender: John (Account No: 10000100)
- Receiver: Alice (Account No: 10000101)
- Amount: 1000
```
UPDATE accounts SET balance = balance - 1000 WHERE account_no = 10000100;
UPDATE accounts SET balance = balance + 1000 WHERE account_no = 10000101;
```


---

## 🛠️ Setup Instructions
### 1. **Clone the Repository**
```bash
git clone https://github.com/your-username/banking-app.git
cd banking-app
```

### 2. **Configure Database**
- Import the SQL file provided in the repository to create necessary tables.
- Update `bankingApp.java` with your database credentials.


---



