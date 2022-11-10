module com.bmstechpro.javafxexamples {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bmstechpro.javafxexamples to javafx.fxml;
    exports com.bmstechpro.javafxexamples;
}