package Bank;

public interface Bank {
    boolean openAccount(String name, int accNumber, int pin, int balance, String accType);
    boolean deposit(int accNumber, int pin, int amount);
    boolean withdraw(int accNumber, int pin, int amount);
    String viewBalance(int accNumber, int pin);
    String viewStatement(int accNumber, int pin, boolean saveToFile);
}








// package Bank;

// public interface Bank {
//     void openAccount(String name, int accNumber, int balance, int pin);
//     void deposit(int accNumber, int amount, int pin);
//     void withdraw(int accNumber, int amount, int pin);
//     void viewBalance(int accNumber, int pin);
//     void printStatement(int accNumber, int pin, boolean saveToFile);
// }