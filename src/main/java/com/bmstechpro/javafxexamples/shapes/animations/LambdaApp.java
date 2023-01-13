package com.bmstechpro.javafxexamples.shapes.animations;
/* javafx-examples
 * @created 01/12/2023
 * @author Konstantin Staykov
 */

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class LambdaApp extends Application {
    Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(600, 600);
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
//            System.out.println("key pressed");
//            Text text = new Text(event.getCode().toString());
//            text.setFont(Font.font(20));
//
//            root.getChildren().add(text);
            Circle c = new Circle(20);
            c.setFill(getRandomColor());
            root.getChildren().add(c);
            Circle circle = new Circle(100);
            circle.setTranslateX(300);
            circle.setTranslateY(300);
//            Shape shape = Shape.subtract(new Rectangle(100,100),circle);
//            shape.setTranslateX(300);
//            shape.setTranslateY(300);
//            shape.setFill(Color.BLUE);
//            root.getChildren().add(shape);
            PathTransition pt = new PathTransition(Duration.seconds(2), circle, c);
            pt.setAutoReverse(true);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.play();
        });

        stage.setScene(scene);
        stage.show();
    }

    public static Color getRandomColor() {
        Random random = new Random();
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
