package com.bmstechpro.javafxexamples.equipment;
/* javafx-examples
 * @created 01/13/2023
 * @author Konstantin Staykov
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EquipmentApp extends Application {
    public static final int TILE_SIZE = 20;
    public static final int HEIGHT = 2;

    private Stage stage;
    private final Pane modelPane = new Pane();
    private final ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(new Scene(createContent()));
        stage.setWidth(1600);
        stage.setHeight(1600);
        stage.show();
    }

    private Parent createContent() {
        Pane pane = new Pane();
        VBox vBox = new VBox();
        HBox hBox = new HBox();


        TextArea textArea = new TextArea();
        textArea.setPrefSize(800, 200);

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
                    .sorted(Comparator.comparing(Equipment::modelNumber))
                    .toList();
            equipmentList.addAll(collect);
            equipmentList.forEach(System.out::println);
            textArea.clear();
        });

        hBox.getChildren().addAll(loadFileBtn, parseText);

        TableView<Equipment> tableView = new TableView<>();
        tableView.setItems(equipmentList);

        TableColumn<Equipment, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setPrefWidth(200);
        tagColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().tag()));
        TableColumn<Equipment, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setPrefWidth(600);
        modelColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().modelNumber()));
        tableView.getColumns().add(tagColumn);
        tableView.getColumns().add(modelColumn);

        TextArea textArea1 = new TextArea();
        textArea1.setPrefSize(800, 30);
        textArea1.setEditable(false);
        textArea1.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textArea1.clear();
                textArea1.appendText(String.format("Tag: %s\tModel Number: %s", newValue.tag(), newValue.modelNumber()));
                setModel(newValue.modelNumber());
            }
        });

        modelPane.setPrefSize(800, 300);

        vBox.getChildren().addAll(hBox, textArea, tableView, textArea1, modelPane);
        pane.getChildren().add(vBox);
        return pane;
    }

    private void setModel(String modelNumber) {
        modelPane.getChildren().clear();
        char[] chars = toCharArray(modelNumber);
        int length = chars.length;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < length; x++) {
                ModelBox tile = new ModelBox(x, y, y == 0 ? "" + (x + 1) : String.valueOf(chars[x]));

                modelPane.getChildren().add(tile);

            }
        }
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

    private char[] toCharArray(String s) {
        return s.toCharArray();
    }


}
