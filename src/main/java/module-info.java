module com.bmstechpro.javafxexamples {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.swing;


    exports com.bmstechpro.javafxexamples;
    exports com.bmstechpro.javafxexamples.tablevalidation;
    exports com.bmstechpro.javafxexamples.checkers;
    exports com.bmstechpro.javafxexamples.shapes.animations;
    exports com.bmstechpro.javafxexamples.parametrics;
    exports com.bmstechpro.javafxexamples.reflect;
    exports com.bmstechpro.javafxexamples.tictactoe;
    exports com.bmstechpro.javafxexamples.equipment;
    exports com.bmstechpro.javafxexamples.minesweeper;
    exports com.bmstechpro.javafxexamples.connect4;
    exports com.bmstechpro.javafxexamples.encryption;

    opens com.bmstechpro.javafxexamples.tablevalidation to javafx.fxml;
    opens com.bmstechpro.javafxexamples to javafx.fxml;
}