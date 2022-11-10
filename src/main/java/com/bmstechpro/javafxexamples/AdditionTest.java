package com.bmstechpro.javafxexamples;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

/***
 * Simple instructional example for the basics of using event-driven programming.
 * In real life, I would improve this code with lots of binding.
 */
public class AdditionTest extends Application {

    private int currentQuestion = 0;

    private int currentCorrect = 0;

    private int firstNumber = 0;
    private int secondNumber = 0;

    private final Random rng = new Random();

    private final static int NUM_QUESTIONS = 5;

    @Override
    public void start(Stage primaryStage) {
        Label questionLabel = new Label();
        Label scoreLabel = new Label();

        TextField userEntry = new TextField();

        Button startButton = new Button("Start");
        Button submitButton = new Button("Submit");
        submitButton.setDisable(true);

        HBox questionBox = new HBox(5, questionLabel, userEntry);
        questionBox.setAlignment(Pos.CENTER);
        HBox buttons = new HBox(5, startButton, submitButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, questionBox, scoreLabel, buttons);
        root.setAlignment(Pos.CENTER);

        startButton.setOnAction(event -> {
            currentQuestion = 0;
            currentCorrect = 0;
            startButton.setDisable(true);
            submitButton.setDisable(false);
            makeQuestion(questionLabel, startButton, submitButton);
            userEntry.requestFocus();
        });

        submitButton.setOnAction(event -> {
            try {
                int answer = Integer.parseInt(userEntry.getText());
                if (answer == firstNumber + secondNumber) {
                    currentCorrect++;
                }
            } catch (NumberFormatException exc) {
                System.out.println("Couldn't convert " + userEntry.getText() + " into a number");
            }
            userEntry.setText("");
            userEntry.requestFocus();
            scoreLabel.setText(currentCorrect + " / " + currentQuestion + " correct");
            makeQuestion(questionLabel, startButton, submitButton);
        });

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.requestFocus();
    }

    private void makeQuestion(Label label, Button startButton, Button submitButton) {
        currentQuestion++;
        if (currentQuestion <= NUM_QUESTIONS) {
            firstNumber = rng.nextInt(10) + 10;
            secondNumber = rng.nextInt(10) + 10;
            label.setText(firstNumber + " + " + secondNumber + " = ");
        } else {
            label.setText("");
            submitButton.setDisable(true);
            startButton.setDisable(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}