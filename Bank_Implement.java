package Bank;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Bank_Implement implements Bank, Serializable {

    static class Account implements Serializable {
        String name;
        int accNumber;
        int pin;
        int balance;
        ArrayList<String> transactions = new ArrayList<>();

        String accType;

        public Account(String name, int accNumber, int pin, int balance, String accType) {
            this.name = name;
            this.accNumber = accNumber;
            this.pin = pin;
            this.balance = balance;
            this.accType = accType;
            transactions.add(getTimestamp() + " - ✅ " + accType + " account opened with ₹" + balance);
        }


        private String getTimestamp() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dtf.format(LocalDateTime.now());
        }
    }

    private final String FILE_NAME = "Bank/accounts.dat";
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public Bank_Implement() {
        loadAccounts();
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException ignored) {}
    }

    private void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (HashMap<Integer, Account>) in.readObject();
        } catch (Exception ignored) {}
    }

    public boolean openAccount(String name, int accNumber, int pin, int balance, String accType) {
        if (accounts.containsKey(accNumber)) return false;
        accounts.put(accNumber, new Account(name, accNumber, pin, balance, accType));
        saveAccounts();
        return true;
    }


    public boolean deposit(int accNumber, int pin, int amount) {
        Account acc = accounts.get(accNumber);
        if (acc != null && acc.pin == pin) {
            acc.balance += amount;
            acc.transactions.add(acc.getTimestamp() + " - 💰 Deposited ₹" + amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    public boolean withdraw(int accNumber, int pin, int amount) {
        Account acc = accounts.get(accNumber);
        if (acc != null && acc.pin == pin && amount <= acc.balance) {
            acc.balance -= amount;
            acc.transactions.add(acc.getTimestamp() + " - 💸 Withdrew ₹" + amount);
            saveAccounts();
            return true;
        }
        return false;
    }

    public String viewBalance(int accNumber, int pin) {
        Account acc = accounts.get(accNumber);
        if (acc != null && acc.pin == pin) {
            return "💼 Current Balance: ₹" + acc.balance;
        }
        return "❌ Invalid account number or PIN.";
    }

    public String viewStatement(int accNumber, int pin, boolean saveToFile) {
        Account acc = accounts.get(accNumber);
        if (acc != null && acc.pin == pin) {
            StringBuilder sb = new StringBuilder();
            sb.append("📄 Account Statement for ").append(acc.name).append(" (A/C: ").append(acc.accNumber).append(")\n");
            for (String tx : acc.transactions) {
                sb.append(tx).append("\n");
            }
            if (saveToFile) {
                try (FileWriter writer = new FileWriter("Bank/statement_" + acc.accNumber + ".txt")) {
                    writer.write(sb.toString());
                } catch (IOException e) {
                    return "❌ Error saving statement.";
                }
            }
            return sb.toString();
        }
        return "❌ Invalid account number or PIN.";
    }
}










// package Bank;

// import java.io.*;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.HashMap;

// public class Bank_Implement implements Bank {

//     static class Account implements Serializable {
//         String name;
//         int accNumber;
//         int balance;
//         int pin;

//         ArrayList<String> transactions = new ArrayList<>(); 

//         Account(String name, int accNumber, int balance, int pin) {
//             this.name = name;
//             this.accNumber = accNumber;
//             this.balance = balance;
//             this.pin = pin;
//             transactions.add("✅ Account opened with ₹" + balance);
//             transactions.add(getTimestamp() + " - ✅ Account opened with ₹" + balance);

//         }
//     }

//     private static String getTimestamp() {
//         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//         return dtf.format(LocalDateTime.now());
//     }

//     private HashMap<Integer, Account> accounts = new HashMap<>();
//     private final String FILE_NAME = "Bank/accounts.dat";

//     public Bank_Implement() {
//         loadAccounts();
//     }

//     @Override
//     public void openAccount(String name, int accNumber, int balance, int pin) {
//         if (accounts.containsKey(accNumber)) {
//             System.out.println("❌ Account number already exists!");
//         } else {
//             accounts.put(accNumber, new Account(name, accNumber, balance, pin));
//             System.out.println("✅ Account created successfully for " + name);
//             saveAccounts();
//         }
//     }

//     @Override
//     public void deposit(int accNumber, int amount, int pin) {
//         Account acc = accounts.get(accNumber);
//         if (acc != null && acc.pin == pin) {
//             acc.balance += amount;
//             acc.transactions.add("💰 Deposited ₹" + amount);
//             acc.transactions.add(getTimestamp() + " - 💰 Deposited ₹" + amount);
//             System.out.println("💰 Deposited ₹" + amount + ". New Balance: ₹" + acc.balance);
//             saveAccounts();
//         } else {
//             System.out.println("❌ Invalid account number or PIN.");
//         }
//     }

//     @Override
//     public void withdraw(int accNumber, int amount, int pin) {
//         Account acc = accounts.get(accNumber);
//         if (acc != null && acc.pin == pin) {
//             if (amount <= acc.balance) {
//                 acc.balance -= amount;
//                 acc.transactions.add("💸 Withdrew ₹" + amount);
//                 acc.transactions.add(getTimestamp() + " - 💸 Withdrew ₹" + amount);
//                 System.out.println("💸 Withdrawn ₹" + amount + ". Remaining Balance: ₹" + acc.balance);
//                 saveAccounts();
//             } else {
//                 System.out.println("❌ Insufficient balance.");
//             }
//         } else {
//             System.out.println("❌ Invalid account number or PIN.");
//         }
//     }

//     @Override
//     public void viewBalance(int accNumber, int pin) {
//         Account acc = accounts.get(accNumber);
//         if (acc != null && acc.pin == pin) {
//             System.out.println("👤 Account Holder: " + acc.name);
//             System.out.println("🏦 Account No: " + acc.accNumber);
//             System.out.println("💼 Current Balance: ₹" + acc.balance);
//         } else {
//             System.out.println("❌ Invalid account number or PIN.");
//         }
//     }

//     @Override
//     public void printStatement(int accNumber, int pin, boolean saveToFile) {
//         Account acc = accounts.get(accNumber);
//         if (acc != null && acc.pin == pin) {
//             System.out.println("\n📄 Account Statement for " + acc.name + " (A/C: " + acc.accNumber + ")");
//             StringBuilder statement = new StringBuilder();

//             for (String entry : acc.transactions) {
//                 System.out.println("- " + entry);
//                 statement.append(entry).append("\n");
//             }

//             if (saveToFile) {
//                 String fileName = "Bank/statement_" + acc.accNumber + ".txt";
//                 try (FileWriter writer = new FileWriter(fileName)) {
//                     writer.write("Account Statement for " + acc.name + " (A/C: " + acc.accNumber + ")\n");
//                     writer.write(statement.toString());
//                     System.out.println("📁 Statement saved to file: " + fileName);
//                 } catch (IOException e) {
//                     System.out.println("❌ Error saving statement: " + e.getMessage());
//                 }
//             }

//         } else {
//             System.out.println("❌ Invalid account number or PIN.");
//         }
//     }



//     private void saveAccounts() {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
//             oos.writeObject(accounts);
//         } catch (IOException e) {
//             System.out.println("❌ Error saving accounts: " + e.getMessage());
//         }
//     }

//     private void loadAccounts() {
//         File file = new File(FILE_NAME);
//         if (file.exists()) {
//             try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
//                 accounts = (HashMap<Integer, Account>) ois.readObject();
//             } catch (IOException | ClassNotFoundException e) {
//                 System.out.println("❌ Error loading accounts: " + e.getMessage());
//             }
//         }
//     }

// }
