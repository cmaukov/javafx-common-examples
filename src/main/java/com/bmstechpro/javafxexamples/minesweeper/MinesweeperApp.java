package com.bmstechpro.javafxexamples.minesweeper;
/* javafx-examples
 * @created 01/14/2023
 * @author Konstantin Staykov
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperApp extends Application {
    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    private final Tile[][] grid = new Tile[X_TILES][Y_TILES];
    List<Tile> tiles = new ArrayList<>();
    private Scene scene;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(W, H);
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);
                grid[x][y] = tile;
                tiles.add(tile);
                root.getChildren().add(tile);
            }
        }

//        tiles.forEach(t->{
//            if(!t.hasBomb){
//                long bombs = getNeighbors(t).stream().filter(t1 -> t1.hasBomb).count();
//                if (bombs > 0)
//                    t.text.setText(String.valueOf(bombs));
//            }
//        });
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = grid[x][y];
                if (tile.hasBomb) continue;
                // set bombs
                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();
                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));

            }
        }


        return root;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt
        int[] points = new int[]{
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };
        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES
                    && newY >= 0 && newY < Y_TILES) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());

        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private  class Tile extends StackPane {
        private final int x, y;
        private final boolean hasBomb;
        private final Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        private final Text text = new Text();
        private boolean isOpen = false;


        private Tile(int x, int y, boolean hasBomb) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

            border.setStroke(Color.LIGHTGRAY);
//            text.setFill(Color.WHITE);
            text.setFont(Font.font(18));
            text.setText(hasBomb ? "X" : "");
            getChildren().addAll(border, text);
            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);
            setOnMouseClicked(e -> open());
        }

        private void open() {
            if(isOpen)return;
            if(hasBomb){
                System.out.println("Game Over");
                tiles.forEach(t->t.border.setFill(null));


//                scene.setRoot(createContent());
                return;
            }
            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }
        }

        @Override
        public String toString() {
            return "Tile{" +
                    "x=" + x +
                    ", y=" + y +
                    ", hasBomb=" + hasBomb +
                    '}';
        }
    }
}
