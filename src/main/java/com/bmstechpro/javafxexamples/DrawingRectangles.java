package com.bmstechpro.javafxexamples;
/* javafx-examples
 * @created 01/06/2023
 * @author Konstantin Staykov
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DrawingRectangles extends Application {
    private int startPositionX = 100;
    private int startPositionY = 100;
    private final Pane pane = new Pane();
    private final DraggableMaker draggableMaker = new DraggableMaker();

    @Override
    public void start(Stage stage) throws Exception {

        Button button = new Button("Add UC600");
        button.setMinWidth(100);
        button.setOnAction(e -> addRectangle(85));
        Button button2 = new Button("Add UC400");
        button2.setMinWidth(100);
        button2.setOnAction(e -> addRectangle(56.5));
        Button button3 = new Button("Add XM32");
        button3.setMinWidth(100);
        button3.setOnAction(e -> addRectangle(28.2));
        Button button4 = new Button("Add XM30");
        button4.setMinWidth(100);
        button4.setOnAction(e -> addRectangle(21.1));


        HBox hBox = new HBox(button, button2, button3, button4);
        pane.getChildren().add(hBox);


        stage.setScene(new Scene(pane));
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
    }

    private void addRectangle(double recWidth) {

        Rectangle rectangle = new Rectangle(recWidth, 40);
        double width = pane.getWidth();

        if (startPositionX > width - recWidth) {
            startPositionX = 100;
            startPositionY += 80;
        }
        rectangle.setTranslateX(startPositionX);
        startPositionX += recWidth;
        rectangle.setTranslateY(startPositionY);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.BLACK);
        draggableMaker.makeDraggable(rectangle);


        pane.getChildren().add(rectangle);

    }

    public static void main(String[] args) {
        launch(args);
    }
    // One DIN unit = 18mm
    // 1" = 25.4mm
    // XM30 = 3 DIN 2.11"4"
    // XM32 = 4 DIN 2.82"x4"
    // XM70 = 12 DIN 8.5"x4"
    // UC600 = 12 DIN 8.5"x4"
    // UC400 = 8 DIN 5.65"x4"


}
