import java.util.Random;
import java.util.Scanner;

public class Numg {
    private static final int MAX_ATTEMPTS = 5;
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int roundScore = playRound(scanner);
            totalScore += roundScore;
            System.out.println("Your score for this round: " + roundScore);
            System.out.println("Total score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Your final score: " + totalScore);
        scanner.close();
    }

    private static int playRound(Scanner scanner) {
        Random random = new Random();
        int randomNumber = random.nextInt(RANGE_MAX - RANGE_MIN + 1) + RANGE_MIN;
        int attempts = 0;
        int roundScore = 0;

        System.out.println("\nGuess the number between " + RANGE_MIN + " and " + RANGE_MAX + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                roundScore = MAX_ATTEMPTS - attempts + 1;  // Higher score for fewer attempts
                break;
            } else if (guess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (attempts == MAX_ATTEMPTS && roundScore == 0) {
            System.out.println("You've used all attempts. The correct number was " + randomNumber);
        }

        return roundScore;
    }
}
