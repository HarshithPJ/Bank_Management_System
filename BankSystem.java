package Bank;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankSystem {
    public static class Account implements Serializable {
        String name;
        int accNumber;
        int pin;
        int balance;
        ArrayList<String> transactions = new ArrayList<>();

        public Account(String name, int accNumber, int pin, int balance) {
            this.name = name;
            this.accNumber = accNumber;
            this.pin = pin;
            this.balance = balance;
            transactions.add(timestamp() + " - ✅ Account opened with ₹" + balance);
        }

        private String timestamp() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dtf.format(LocalDateTime.now());
        }
    }

    private final String FILE = "accounts.dat";
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public BankSystem() {
        load();
    }

    public boolean openAccount(String name, int accNo, int pin, int balance) {
        if (accounts.containsKey(accNo)) return false;
        accounts.put(accNo, new Account(name, accNo, pin, balance));
        save();
        return true;
    }

    public boolean deposit(int accNo, int pin, int amt) {
        Account acc = accounts.get(accNo);
        if (acc != null && acc.pin == pin) {
            acc.balance += amt;
            acc.transactions.add(acc.timestamp() + " - 💰 Deposited ₹" + amt);
            save();
            return true;
        }
        return false;
    }

    public boolean withdraw(int accNo, int pin, int amt) {
        Account acc = accounts.get(accNo);
        if (acc != null && acc.pin == pin && amt <= acc.balance) {
            acc.balance -= amt;
            acc.transactions.add(acc.timestamp() + " - 💸 Withdrew ₹" + amt);
            save();
            return true;
        }
        return false;
    }

    public String getBalance(int accNo, int pin) {
        Account acc = accounts.get(accNo);
        if (acc != null && acc.pin == pin) {
            return "Balance: ₹" + acc.balance;
        }
        return "Invalid account number or PIN.";
    }

    public String getStatement(int accNo, int pin, boolean saveToFile) {
        Account acc = accounts.get(accNo);
        if (acc != null && acc.pin == pin) {
            StringBuilder sb = new StringBuilder();
            sb.append("Account Statement for ").append(acc.name).append(" (A/C: ").append(acc.accNumber).append(")\n");
            for (String s : acc.transactions) {
                sb.append(s).append("\n");
            }

            if (saveToFile) {
                try (FileWriter fw = new FileWriter("Bank/statement_" + acc.accNumber + ".txt")) {
                    fw.write(sb.toString());
                } catch (IOException e) {
                    return "Failed to save statement.";
                }
            }

            return sb.toString();
        }
        return "Invalid account number or PIN.";
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            accounts = (HashMap<Integer, Account>) in.readObject();
        } catch (Exception ignored) {}
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(accounts);
        } catch (IOException ignored) {}
    }
}
