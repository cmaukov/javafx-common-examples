package com.bmstechpro.javafxexamples.equipment;
/* javafx-examples
 * @created 01/14/2023
 * @author Konstantin Staykov
 */

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ModelBox extends StackPane {

    public ModelBox(int x, int y, String text) {
        Rectangle rectangle = new Rectangle(EquipmentApp.TILE_SIZE, EquipmentApp.TILE_SIZE);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        relocate(x * EquipmentApp.TILE_SIZE, y * EquipmentApp.TILE_SIZE);
        Label label = new Label(text);

        getChildren().addAll(rectangle,label);

    }
}
