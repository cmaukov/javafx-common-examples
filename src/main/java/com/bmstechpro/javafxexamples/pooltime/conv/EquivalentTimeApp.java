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
                , PoolCourse.LONG_COURSE_METERS, SwimStyle.BUTTERFLY, Distance._100, swimTime);
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
        pane.setPrefSize(600, 600);
        ChoiceBox<SwimStyle> swimStyleChoiceBox = new ChoiceBox<>();
        swimStyleChoiceBox.getItems().addAll(SwimStyle.values());
        swimStyleChoiceBox.getSelectionModel().selectFirst();
        swimStyleChoiceBox.setConverter(getConverter());
        swimStyleChoiceBox.setOnAction(event -> {
            SwimStyle selectedItem = swimStyleChoiceBox.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
        });
        swimStyleChoiceBox.setTranslateX(30);
        swimStyleChoiceBox.setTranslateY(30);

        ChoiceBox<PoolCourse> poolCourseChoiceBox = new ChoiceBox<>();
        poolCourseChoiceBox.getItems().addAll(PoolCourse.values());
        poolCourseChoiceBox.getSelectionModel().selectFirst();
        poolCourseChoiceBox.setConverter(getConverter());
        poolCourseChoiceBox.setOnAction(event -> {
            PoolCourse selectedItem = poolCourseChoiceBox.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
        });
        poolCourseChoiceBox.setTranslateX(160);
        poolCourseChoiceBox.setTranslateY(30);

        pane.getChildren().addAll(swimStyleChoiceBox, poolCourseChoiceBox);
        return pane;
    }

    private static <T extends HasDisplayName> StringConverter<T> getConverter() {
        return new StringConverter<T>() {
            @Override
            public String toString(T object) {
                return object.getDisplayName();
            }

            @Override
            public T fromString(String string) {
                return null;
            }
        };
    }


}
