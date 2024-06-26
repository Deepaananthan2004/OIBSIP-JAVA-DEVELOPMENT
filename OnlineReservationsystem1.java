import java.util.HashMap;arun
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationsystem1 {
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, Reservation> reservations = new HashMap<>();
    private static int nextReservationId = 1;

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        String currentUser = "";

        while (!loggedIn) {
            System.out.println("=== Login Form ===");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (isValidLogin(username, password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                loggedIn = true;
                currentUser = username;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Online Reservation System ===");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    makeReservation(currentUser, scanner);
                    break;
                case 2:
                    cancelReservation(currentUser, scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }

    private static void initializeUsers() {
        // Sample users (in a real system, use a database or more secure storage)
        users.put("admin", "password");
        users.put("user1", "password1");
    }

    private static boolean isValidLogin(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void makeReservation(String username, Scanner scanner) {
        System.out.println("\n=== Reservation Form ===");

        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter journey date (YYYY-MM-DD): ");
        String journeyDate = scanner.nextLine();

        // Simulating reservation creation
        int reservationId = nextReservationId++;
        Reservation reservation = new Reservation(reservationId, username, trainNumber, classType, journeyDate);
        reservations.put(Integer.toString(reservationId), reservation);

        System.out.println("Reservation successfully made!");
        System.out.println("Reservation details: " + reservation);
    }

    private static void cancelReservation(String username, Scanner scanner) {
        System.out.println("\n=== Cancellation Form ===");

        System.out.print("Enter reservation ID to cancel: ");
        String reservationId = scanner.nextLine();

        if (reservations.containsKey(reservationId)) {
            Reservation reservation = reservations.get(reservationId);
            if (reservation.getUsername().equals(username)) {
                reservations.remove(reservationId);
                System.out.println("Reservation with ID " + reservationId + " canceled successfully.");
            } else {
                System.out.println("You do not have permission to cancel this reservation.");
            }
        } else {
            System.out.println("Invalid reservation ID. Please try again.");
        }
    }
}

class Reservation {
    private int id;
    private String username;
    private int trainNumber;
    private String classType;
    private String journeyDate;

    public Reservation(int id, String username, int trainNumber, String classType, String journeyDate) {
        this.id = id;
        this.username = username;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + id +
                ", Train Number: " + trainNumber +
                ", Class Type: " + classType +
                ", Journey Date: " + journeyDate;
    }
}
