package Bank;

import javax.swing.*;

public class Bank_Main {
    public static void main(String[] args) {
        Bank_Implement bank = new Bank_Implement();

        JFrame frame = new JFrame("Bank System GUI");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel label = new JLabel("Choose an option:");
        label.setBounds(30, 30, 200, 25);
        frame.add(label);

        JButton open = new JButton("Open Account");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton balance = new JButton("View Balance");
        JButton statement = new JButton("View Statement");
        JButton themeBtn = new JButton("Toggle Theme");
        

        open.setBounds(100, 70, 200, 30);
        deposit.setBounds(100, 110, 200, 30);
        withdraw.setBounds(100, 150, 200, 30);
        balance.setBounds(100, 190, 200, 30);
        statement.setBounds(100, 230, 200, 30);
        themeBtn.setBounds(100, 270, 200, 30);

        frame.add(open);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(balance);
        frame.add(statement);
        frame.add(themeBtn);

        open.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Name:");
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("4-digit PIN:"));
            int bal = Integer.parseInt(JOptionPane.showInputDialog("Initial Balance:"));

            String[] types = {"Savings", "Current"};
            String accType = (String) JOptionPane.showInputDialog(null, "Choose Account Type:",
                    "Account Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);

            boolean created = bank.openAccount(name, acc, pin, bal, accType);
            JOptionPane.showMessageDialog(null, created ? "✅ Account created." : "❌ Account already exists.");
        });     


        deposit.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int amt = Integer.parseInt(JOptionPane.showInputDialog("Amount to deposit:"));
            boolean success = bank.deposit(acc, pin, amt);
            JOptionPane.showMessageDialog(null, success ? "✅ Deposit successful." : "❌ Invalid details.");
        });

        withdraw.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int amt = Integer.parseInt(JOptionPane.showInputDialog("Amount to withdraw:"));
            boolean success = bank.withdraw(acc, pin, amt);
            JOptionPane.showMessageDialog(null, success ? "✅ Withdraw successful." : "❌ Invalid details or low balance.");
        });

        balance.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            String result = bank.viewBalance(acc, pin);
            JOptionPane.showMessageDialog(null, result);
        });

        statement.addActionListener(e -> {
            int acc = Integer.parseInt(JOptionPane.showInputDialog("Account Number:"));
            int pin = Integer.parseInt(JOptionPane.showInputDialog("PIN:"));
            int save = JOptionPane.showConfirmDialog(null, "Save to file?", "Save", JOptionPane.YES_NO_OPTION);
            String result = bank.viewStatement(acc, pin, save == JOptionPane.YES_OPTION);
            JOptionPane.showMessageDialog(null, result);
        });

        final boolean[] isDark = {false};  // store theme state

        themeBtn.addActionListener(e -> {
            if (!isDark[0]) {
                frame.getContentPane().setBackground(java.awt.Color.DARK_GRAY);
                label.setForeground(java.awt.Color.WHITE);
                isDark[0] = true;
            } else {
                frame.getContentPane().setBackground(null); // reset to default
                label.setForeground(java.awt.Color.BLACK);
                isDark[0] = false;
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}












// package Bank;

// import java.util.Scanner;

// public class Bank_Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         Bank_Implement bank = new Bank_Implement();

//         while (true) {
//             System.out.println("\n--- Welcome to the Bank ---");
//             System.out.println("1. Open Account");
//             System.out.println("2. Deposit");
//             System.out.println("3. Withdraw");
//             System.out.println("4. View Balance");
//             System.out.println("5. Exit");
//             System.out.println("6. View Account Statement");
//             System.out.print("Enter your choice: ");
//             int choice = sc.nextInt();

//             int accNumber, amount, pin;
//             String name;

//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter Name: ");
//                     name = sc.next();
//                     System.out.print("Enter Account Number: ");
//                     accNumber = sc.nextInt();
//                     System.out.print("Enter Initial Balance: ");
//                     amount = sc.nextInt();
//                     System.out.print("Set 4-digit PIN: ");
//                     pin = sc.nextInt();
//                     bank.openAccount(name, accNumber, amount, pin);
//                     break;

//                 case 2:
//                     System.out.print("Enter Account Number: ");
//                     accNumber = sc.nextInt();
//                     System.out.print("Enter PIN: ");
//                     pin = sc.nextInt();
//                     System.out.print("Enter Deposit Amount: ");
//                     amount = sc.nextInt();
//                     bank.deposit(accNumber, amount, pin);
//                     break;

//                 case 3:
//                     System.out.print("Enter Account Number: ");
//                     accNumber = sc.nextInt();
//                     System.out.print("Enter PIN: ");
//                     pin = sc.nextInt();
//                     System.out.print("Enter Withdrawal Amount: ");
//                     amount = sc.nextInt();
//                     bank.withdraw(accNumber, amount, pin);
//                     break;

//                 case 4:
//                     System.out.print("Enter Account Number: ");
//                     accNumber = sc.nextInt();
//                     System.out.print("Enter PIN: ");
//                     pin = sc.nextInt();
//                     bank.viewBalance(accNumber, pin);
//                     break;

//                 case 5:
//                     System.out.println("✅ Exiting. Thank you!");
//                     return;

//                 case 6:
//                     System.out.print("Enter Account Number: ");
//                     accNumber = sc.nextInt();
//                     System.out.print("Enter PIN: ");
//                     pin = sc.nextInt();
//                     System.out.print("Do you want to save the statement to a file? (yes/no): ");
//                     sc.nextLine(); // consume newline
//                     String answer = sc.nextLine().trim().toLowerCase();
//                     boolean save = answer.equals("yes") || answer.equals("y");
//                     bank.printStatement(accNumber, pin, save);
//                     break;


//                 default:
//                     System.out.println("❌ Invalid choice. Try again.");
//             }
//         }
//     }
// }
