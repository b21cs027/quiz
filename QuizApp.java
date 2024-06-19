import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Question class representing a quiz question
class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}

// Quiz class managing a collection of questions
class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int userAnswer = getUserAnswer(scanner, options.size());
            if (question.isCorrectAnswer(userAnswer - 1)) {
                score++;
            }
        }
        scanner.close();
        displayScore();
    }

    private int getUserAnswer(Scanner scanner, int numberOfOptions) {
        int userAnswer = -1;
        while (userAnswer < 1 || userAnswer > numberOfOptions) {
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer < 1 || userAnswer > numberOfOptions) {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
        }
        return userAnswer;
    }

    private void displayScore() {
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

// Main class to run the quiz
public class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        List<String> options1 = new ArrayList<>();
        options1.add("10");
        options1.add("20");
        options1.add("30");
        options1.add("40");
        quiz.addQuestion(new Question("What is 10 + 10?", options1, 1));

        List<String> options2 = new ArrayList<>();
        options2.add("5");
        options2.add("6");
        options2.add("7");
        options2.add("8");
        quiz.addQuestion(new Question("What is 3 + 3?", options2, 3));

        List<String> options3 = new ArrayList<>();
        options3.add("Java");
        options3.add("Python");
        options3.add("C++");
        options3.add("JavaScript");
        quiz.addQuestion(new Question("Which programming language is this quiz written in?", options3, 0));

        quiz.start();
    }
}
