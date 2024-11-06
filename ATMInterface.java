import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
            return false;
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive amount.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        
        System.out.println("Welcome to the ATM!");

        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // Initialize account with an initial balance
        BankAccount account = new BankAccount(1000.00);
        
        // Create ATM and start the user interface
        ATM atm = new ATM(account);
        atm.start();
    }
}

