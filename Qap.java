import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctAnswer; // Index of the correct answer (0-based)

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> answerStatus; // True for correct, false for incorrect
    private Scanner scanner;
    private static final int TIME_LIMIT_SECONDS = 10;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.answerStatus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        // Adding sample questions
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Paris", "3. Rome", "4. Madrid"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Jupiter", "3. Mars", "4. Venus"}, 2));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"}, 3));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. J.K. Rowling", "3. Mark Twain", "4. William Shakespeare"}, 3));
        questions.add(new Question("What is the boiling point of water?", new String[]{"1. 100°C", "2. 90°C", "3. 80°C", "4. 110°C"}, 0));
    }

    public void start() {
        System.out.println("Welcome to the Quiz! You have " + TIME_LIMIT_SECONDS + " seconds to answer each question.");
        
        for (Question question : questions) {
            boolean answeredInTime = askQuestionWithTimer(question);
            if (!answeredInTime) {
                System.out.println("Time's up! Moving to the next question.");
                answerStatus.add(false); // Mark as incorrect due to time-out
            }
        }

        displayResult();
        scanner.close();
    }

    private boolean askQuestionWithTimer(Question question) {
        System.out.println("\n" + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        boolean[] answered = {false};
        
        // Schedule a timer to cancel the input if time limit is exceeded
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered[0]) {
                    System.out.println("\nTime's up!");
                    answered[0] = true;
                    scanner.nextLine(); // Clear input if user was typing
                }
            }
        }, TIME_LIMIT_SECONDS * 1000);

        int userAnswer = -1;
        try {
            userAnswer = scanner.nextInt() - 1; // Adjust for 0-based index
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
        }

        timer.cancel(); // Stop the timer if the user answers in time
        answered[0] = true;

        if (userAnswer >= 0 && userAnswer < question.options.length) {
            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
                answerStatus.add(true);
            } else {
                System.out.println("Incorrect.");
                answerStatus.add(false);
            }
            return true;
        } else {
            System.out.println("Invalid input.");
            answerStatus.add(false);
            return false;
        }
    }

    private void displayResult() {
        System.out.println("\n=== Quiz Results ===");
        System.out.println("Total Score: " + score + "/" + questions.size());
        
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            System.out.println("Your answer: " + (answerStatus.get(i) ? "Correct" : "Incorrect"));
            System.out.println("Correct Answer: " + question.options[question.correctAnswer]);
        }
    }
}

public class Qap {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}
