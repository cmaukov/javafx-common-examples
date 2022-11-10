module com.bmstechpro.javafxexamples {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.bmstechpro.javafxexamples to javafx.fxml;
    exports com.bmstechpro.javafxexamples;
}