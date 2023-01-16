package com.bmstechpro.javafxexamples.button;
/* javafx-examples
 * @created 01/15/2023
 * @author Konstantin Staykov
 */

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ButtonApp extends Application {
    private Parent createContent() {
        VBox vBox = new VBox();
        vBox.setPrefSize(600, 600);
        TextField textField = new TextField();

        Button button = new Button("Click");
        button.setFont(Font.font(22));
        // textField validation
        button.disableProperty().bind(Bindings.createBooleanBinding(() -> !isValid(textField.getText()), textField.textProperty()));


        // button animation if textField text value is equal to "hello"

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("hello")) animate(button);
        });


        vBox.getChildren().addAll(textField, button);

        return vBox;
    }

    private boolean isValid(String text) {
        return text.length() >= 5;
    }

    private void animate(Node node) {
        var st = new ScaleTransition(Duration.seconds(0.66), node);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(0);
        st.setToY(0);
        st.setAutoReverse(true);
        st.setCycleCount(4);
        st.play();

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
