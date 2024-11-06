# NUMBER-GAME (Java)
Description
This is a simple number guessing game implemented in Java. The game randomly selects a number within a specified range (1 to 100), and the player has to guess the correct number. Feedback is given on whether the guess is too high, too low, or correct. The game continues until the player guesses the correct number, and there is a limit to the number of attempts allowed per round. Players can play multiple rounds, and their score is based on the number of attempts taken or rounds won.

Features
Random number generation within a specified range (1 to 100).
User inputs their guess.
Feedback provided on each guess (too high, too low, or correct).
Limited number of attempts per round.
Option to play multiple rounds.
Display of score based on attempts or rounds won.
How to Play
The game generates a random number between 1 and 100.
The player is prompted to guess the number.
After each guess, the game will tell the player if the guess was too high, too low, or correct.
The player has a limited number of attempts to guess the number.
After each round, the player can choose to play again or exit.
The score is displayed at the end of each round, based on the number of attempts or rounds won.
Requirements
Java 8 or higher.
Running the Game
Clone this repository to your local machine.

bash
Copy code
git clone https://github.com/your-username/number-guessing-game-java.git
Navigate to the project directory.

bash
Copy code
cd number-guessing-game-java
Compile the Java file.

bash
Copy code
javac NumberGuessingGame.java
Run the game.

bash
Copy code
java NumberGuessingGame
Example Game Flow
vbnet
Copy code
Welcome to the Number Guessing Game!
I'm thinking of a number between 1 and 100.
You have 10 attempts to guess it.

Attempt 1: Enter your guess: 50
Too high! Try again.

Attempt 2: Enter your guess: 25
Too low! Try again.

...

Attempt 10: Enter your guess: 37
Correct! You guessed the number in 10 attempts.

Would you like to play again? (yes/no)
Contributions
If you'd like to contribute to this project, feel free to fork the repository, make changes, and create a pull request.

License
This project is open-source and available under the MIT License.

