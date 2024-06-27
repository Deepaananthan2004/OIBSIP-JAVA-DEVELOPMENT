import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;     // Lower bound of the number range
        int upperBound = 100;   // Upper bound of the number range
        int maxAttempts = 10;   // Maximum number of attempts allowed
        int score = 0;          // Score of the player
        int rounds = 0;         // Number of rounds played

        boolean playAgain = true;

        while (playAgain) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            System.out.println("I have generated a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (guess < lowerBound || guess > upperBound) {
                    System.out.println("Your guess is out of the valid range.");
                    continue;
                }

                attempts++;

                if (guess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the number " + generatedNumber + " correctly in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1; // Score calculation (more points for fewer attempts)
                    guessedCorrectly = true;
                } else if (guess < generatedNumber) {
                    System.out.println("The number is higher than " + guess + ".");
                } else {
                    System.out.println("The number is lower than " + guess + ".");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you did not guess the number. It was " + generatedNumber + ".");
            }

            rounds++;

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine().toLowerCase();

            if (!playChoice.equals("yes")) {
                playAgain = false;
                System.out.println("Thank you for playing Guess the Number! You played " + rounds + " round(s). Your final score is " + score + ".");
            }
        }

        scanner.close();
    }
}
