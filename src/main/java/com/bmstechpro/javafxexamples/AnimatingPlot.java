package com.bmstechpro.javafxexamples;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.function.DoubleUnaryOperator;

/**
 * Example of function plot with animation along plotted function in JavaFX. Uses Canvas to render the plot.
 */
public class AnimatingPlot extends Application {

    private enum NamedFunction {
        SIN(x -> -Math.sin(2 * Math.PI * x / 100) * 200 + 200, "Sine"),
        COS(x -> -Math.cos(2 * Math.PI * x / 100) * 200 + 200, "Cosine"),
        LOG(x -> -Math.log(x) * 20 + 320, "Log");

        private final DoubleUnaryOperator function;
        private final String displayName;

        NamedFunction(DoubleUnaryOperator function, String displayName) {
            this.function = function;
            this.displayName = displayName;
        }

        public DoubleUnaryOperator getFunction() {
            return function;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    private Transition animation;

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        pane.setMinSize(400, 400);
        pane.setPadding(new Insets(10));

        ComboBox<NamedFunction> functionChoice = new ComboBox<>();
        functionChoice.getItems().addAll(NamedFunction.values());

        functionChoice.valueProperty().addListener((obs, oldFunction, newFunction) -> {
            pane.getChildren().clear();
            if (newFunction != null) {
                Plot plot = new Plot(newFunction.getFunction(), 400, 400, 0, 400);
                pane.getChildren().add(plot.getPlot());

                if (animation != null) {
                    animation.stop();
                }

                Circle circle = new Circle(10);
                pane.getChildren().add(circle);

                animation = plot.createTransition(
                        Duration.seconds(5),
                        circle.centerXProperty(),
                        circle.centerYProperty());

                animation.setCycleCount(Animation.INDEFINITE);
                animation.setAutoReverse(true);
                animation.play();
            }
        });

        BorderPane.setMargin(pane, new Insets(20));

        BorderPane root = new BorderPane(pane, functionChoice, null, null, null);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static class Plot {
        private final DoubleUnaryOperator function;
        private final double startX;
        private final double endX;
        private final Canvas plot;

        public Plot(DoubleUnaryOperator function, double width, double height,
                    double startX, double endX) {
            this.function = function;
            this.startX = startX;
            this.endX = endX;

            this.plot = new Canvas(width, height);
            GraphicsContext gc = plot.getGraphicsContext2D();
            gc.moveTo(startX, function.applyAsDouble(startX));
            for (double x = startX; x < endX; x++) {
                gc.lineTo(x, function.applyAsDouble(x));
            }
            gc.stroke();
        }

        public Canvas getPlot() {
            return plot;
        }

        public Transition createTransition(Duration cycleDuration, DoubleProperty x, DoubleProperty y) {
            return new Transition() {

                {
                    setCycleDuration(cycleDuration);
                }

                @Override
                protected void interpolate(double frac) {
                    double xValue = (1 - frac) * startX + frac * endX;
                    double yValue = function.applyAsDouble(xValue);
                    x.set(xValue);
                    y.set(yValue);
                }
            };
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
