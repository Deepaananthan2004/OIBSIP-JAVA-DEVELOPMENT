import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setProfile(String newProfile) {
        this.profile = newProfile;
    }
}

// Class to manage the online exam system
public class Onlineexamination {
    private static User currentUser; // To store the current user
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to Online Examination System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        }
        System.out.println("Exiting Online Examination System. Goodbye!");
    }

    // Method to handle user login
    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Dummy authentication (replace with database validation)
        if (authenticateUser(username, password)) {
            System.out.println("Login successful.");
            currentUser = new User(username, password, "Student"); // Example user profile
            showMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    // Dummy authentication method (replace with actual authentication logic)
    private static boolean authenticateUser(String username, String password) {
        // Simulate valid credentials
        return username.equals("admin") && password.equals("admin123");
    }

    // Method to display menu options after login
    private static void showMenu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\nLogged in as " + currentUser.getUsername());
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Select Answers for MCQs");
            System.out.println("4. Start Exam (with Timer)");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    updatePassword();
                    break;
                case 3:
                    selectAnswers();
                    break;
                case 4:
                    startExamWithTimer();
                    break;
                case 5:
                    logout = true;
                    currentUser = null; // Logout current user
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        }
    }

    // Method to update user profile
    private static void updateProfile() {
        System.out.print("Enter new profile information: ");
        String newProfile = scanner.nextLine();
        currentUser.setProfile(newProfile);
        System.out.println("Profile updated successfully.");
    }

    // Method to update user password
    private static void updatePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        if (currentPassword.equals(currentUser.getPassword())) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            currentUser.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect current password. Password update failed.");
        }
    }

    // Method to simulate selecting answers for MCQs
    private static void selectAnswers() {
        System.out.println("Select answers for MCQs.");
        // Add your logic to handle MCQs selection
    }

    // Method to simulate starting exam with timer
    private static void startExamWithTimer() {
        System.out.println("Exam started with timer.");
        // Simulate exam duration and auto-submit logic
        try {
            Thread.sleep(5000); // Simulate 5 seconds exam duration
            System.out.println("Auto-submitting exam.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
