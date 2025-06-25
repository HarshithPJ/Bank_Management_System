package Bank;

import javax.swing.*;

public class BankApp {
    private static BankSystem bank = new BankSystem();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Bank GUI");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel label = new JLabel("Choose an option:");
        label.setBounds(30, 30, 200, 25);
        frame.add(label);

        JButton openBtn = new JButton("Open Account");
        JButton depBtn = new JButton("Deposit");
        JButton wdBtn = new JButton("Withdraw");
        JButton balBtn = new JButton("View Balance");
        JButton stmtBtn = new JButton("View Statement");

        openBtn.setBounds(100, 70, 200, 30);
        depBtn.setBounds(100, 110, 200, 30);
        wdBtn.setBounds(100, 150, 200, 30);
        balBtn.setBounds(100, 190, 200, 30);
        stmtBtn.setBounds(100, 230, 200, 30);

        frame.add(openBtn);
        frame.add(depBtn);
        frame.add(wdBtn);
        frame.add(balBtn);
        frame.add(stmtBtn);

        // Button actions
        openBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Name:");
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("4-digit PIN:"));
            int bal = Integer.parseInt(JOptionPane.showInputDialog("Initial Balance:"));
            boolean success = bank.openAccount(name, acc, pin, bal);
            JOptionPane.showMessageDialog(null, success ? "Account created." : "Account already exists.");
        });

        depBtn.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int amt = Integer.parseInt(JOptionPane.showInputDialog("Amount to deposit:"));
            boolean success = bank.deposit(acc, pin, amt);
            JOptionPane.showMessageDialog(null, success ? "Deposit successful." : "Invalid credentials.");
        });

        wdBtn.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int amt = Integer.parseInt(JOptionPane.showInputDialog("Amount to withdraw:"));
            boolean success = bank.withdraw(acc, pin, amt);
            JOptionPane.showMessageDialog(null, success ? "Withdrawal successful." : "Invalid credentials or low balance.");
        });

        balBtn.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            String result = bank.getBalance(acc, pin);
            JOptionPane.showMessageDialog(null, result);
        });

        stmtBtn.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int save = JOptionPane.showConfirmDialog(null, "Save statement to file?", "Save", JOptionPane.YES_NO_OPTION);
            String result = bank.getStatement(acc, pin, save == 0);
            JOptionPane.showMessageDialog(null, result);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
