# Bank_Management_System

# 💳 Java Bank Application (GUI Version)

A simple desktop banking application built in **Java Swing**, supporting:

- Create Account (Savings/Current with PIN)
- Deposit / Withdraw
- View Balance
- View & Save Account Statements
- Light / Dark Theme Toggle
- `.exe` conversion for Windows app use

---

## 🖥️ Features

- 🪪 **Account creation** with name, number, balance, and PIN
- 💰 **Deposit & Withdraw** money (PIN protected)
- 📄 **Transaction history** (view and optionally save as `.txt`)
- 🎨 **Theme toggle** between Light and Dark mode
- 📋 **Dropdown menu** for selecting account type
- ✅ **Data saved locally** (`accounts.dat`) via serialization
- 💻 Converted to `.exe` using Launch4j for native Windows use

---

## 📁 Project Structure
─ Bank.java # Interface
─ Bank_Implement.java # Backend logic and storage
─ Bank_Main.java # GUI (Java Swing)
