import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Transaction class
class Transaction {
    private double amount;
    private String type;
    private LocalDateTime timestamp;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

// TransactionHistory class
class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getType() + ": $" + transaction.getAmount() + " at " + transaction.getTimestamp());
        }
        System.out.println();
    }
}

// User class
class User {
    private String userId;
    private String pin;
    private double balance;
    private TransactionHistory transactionHistory;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new TransactionHistory();
    }

    public String getUserId() {
        return userId;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction(new Transaction(amount, "Deposit"));
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.addTransaction(new Transaction(amount, "Withdrawal"));
        return true;
    }

    public void transfer(double amount, String recipientId) {
        // Simulate transfer to recipient (in a real scenario, more checks and validations needed)
        withdraw(amount);
        // Assuming recipient is same as user for simplicity
        transactionHistory.addTransaction(new Transaction(amount, "Transfer to " + recipientId));
    }

    public void printTransactionHistory() {
        System.out.println("=== Transaction History for " + userId + " ===");
        transactionHistory.printTransactions();
    }
}

// ATM interface
interface ATM {
    boolean authenticateUser(String userId, String pin);
    void displayMenu();
    void showTransactionHistory();
    void withdraw(double amount);
    void deposit(double amount);
    void transfer(String recipientId, double amount);
    void quit();
}

// ATM implementation class
public class ATMImplementation implements ATM {
    private User currentUser;
    private Scanner scanner;

    public ATMImplementation(User currentUser) {
        this.currentUser = currentUser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean authenticateUser(String userId, String pin) {
        return currentUser.getUserId().equals(userId) && currentUser.verifyPin(pin);
    }

    @Override
    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.nextLine();
                    transfer(recipientId, transferAmount);
                    break;
                case 5:
                    quit();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    @Override
    public void showTransactionHistory() {
        currentUser.printTransactionHistory();
    }

    @Override
    public void withdraw(double amount) {
        if (currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public void deposit(double amount) {
        currentUser.deposit(amount);
        System.out.println("Deposit successful.");
    }

    @Override
    public void transfer(String recipientId, double amount) {
        currentUser.transfer(amount, recipientId);
        System.out.println("Transfer successful.");
    }

    @Override
    public void quit() {
        System.out.println("Thank you for using the ATM.");
        scanner.close();
    }

    public static void main(String[] args) {
        // Example usage
        User user = new User("user1", "1234");
        ATMImplementation atm = new ATMImplementation(user);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (atm.authenticateUser(userId, pin)) {
            atm.displayMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }

        scanner.close();
    }
}
