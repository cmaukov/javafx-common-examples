package com.bmstechpro.javafxexamples.equipment;
/* javafx-examples
 * @created 01/13/2023
 * @author Konstantin Staykov
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EquipmentApp extends Application {
    private Pane pane;
    private Stage stage;
    private final ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(new Scene(createContent()));
        stage.setWidth(800);
        stage.setHeight(800);
        stage.show();
    }

    private Parent createContent() {
        pane = new Pane();
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        TextArea textArea = new TextArea();
        textArea.setPrefSize(600, 400);

        Button loadFileBtn = new Button("Load File");
        loadFileBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                loadFile(file);
            }

        });

        Button parseText = new Button("Parse Text");
        parseText.setOnAction(event -> {
            Stream<String> lines = textArea.getText().lines();
            List<Equipment> collect = lines.map(EquipmentParser::parse)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            equipmentList.addAll(collect);
            equipmentList.forEach(System.out::println);
            textArea.clear();
        });

        hBox.getChildren().addAll(loadFileBtn, parseText);

        vBox.getChildren().addAll(hBox, textArea);
        pane.getChildren().add(vBox);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);

    }

    private void loadFile(File file) {
//        Path path = Path.of("data.txt");
        Path path = file.toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            List<Equipment> collect = lines.map(EquipmentParser::parse)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            equipmentList.addAll(collect);
            equipmentList.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
