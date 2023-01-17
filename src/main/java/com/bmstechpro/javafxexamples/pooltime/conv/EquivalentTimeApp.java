package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EquivalentTimeApp extends Application {
    public static void main(String[] args) {
        SwimTime swimTime = new SwimTime(59, 52);
        EquivalentTime equivalentTime = new EquivalentTime(PoolCourse.SHORT_COURSE_YARDS
        ,PoolCourse.LONG_COURSE_METERS,SwimStyle.BUTTERFLY,Distance._100,swimTime);
        System.out.println(equivalentTime.convertTime());
        System.out.println(equivalentTime);
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent() {
        Pane pane = new Pane();
        pane.setPrefSize(600,600);
        ChoiceBox<SwimStyle> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(SwimStyle.values());
        choiceBox.getSelectionModel().selectFirst();
//        choiceBox.setConverter(new StringConverter<SwimStyle>() {
//            @Override
//            public String toString(SwimStyle object) {
//                return object.getDisplayName();
//            }
//
//            @Override
//            public SwimStyle fromString(String string) {
//                return null;
//            }
//        });
        choiceBox.setOnAction(event -> {
            SwimStyle selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
        });

        pane.getChildren().add(choiceBox);
        return pane;
    }





}
