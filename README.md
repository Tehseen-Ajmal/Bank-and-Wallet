# Bank and Wallet Management System

## Overview

The **Bank and Wallet Management System** is a Java-based desktop application designed to manage user bank accounts and wallet transactions. It features an admin panel (console-based) and a user-friendly GUI similar to modern digital wallets. A Python-powered module is also included to fetch LESCO electricity bills using Selenium automation.

## Features

- **User Account Management**: Create, manage, and delete user accounts.
- **Wallet Transactions**: Deposit, withdraw, transfer between bank and wallet.
- **Statement History**: Generate receipts and transaction history for all operations.
- **Admin Panel (Console-Based)**: Admin can manage accounts, complaints, and transactions from the command-line interface.
- **User Interface (GUI)**: Users interact with the system through an intuitive GUI similar to commercial wallet apps.
- **Money Requests**:
  - Users can request money from friends.
  - Incoming requests show in the "Requests" tab for approval.
- **Forget Password with OTP**:
  - Users can reset passwords using email-based OTP verification.
- **LESCO Bill Fetching**:
  - Automated electricity bill retrieval using Python and Selenium.
  - Fetched bill is integrated with the Java user interface.
- **Complain System**:
  - Users can lodge complaints and track their status.
  - Admin can view and reply to individual complaints.
- **Secure Login System**: Role-based login for admin and users.

## Configuration

Before cloning the repository, update your email and key in `Const.java`:

```java
public class Const {
    public static final String email_acc = "youremail@example.com";
    public static final String key_email = "your_key";
}
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Tehseen-Ajmal/Bank-and-Wallet.git
cd Bank-and-Wallet
```

### 2. Admin & User
Admin:
```bash
src/myon/bank/pack/Driver.java
```
User:
```
src/User_Online/User_Background.java
```
### 3. Requirements for Bill Fetching
1- selenium :
```bash
pip install selenium
```
2- Browser Driver :
```bash
msedgedriver.exe
```
Make sure you have the matching ChromeDriver (or geckodriver) for your browser.

### 4. LESCO Bill Fetching Script

```bash
python bill_fetcher.py
```

## Technologies Used

- **Java** (Core + Swing GUI)
- **Python** (Selenium)

## Author

**Tehseen Ajmal**
