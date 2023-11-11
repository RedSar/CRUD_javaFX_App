module ma.rsmi.crud_javafx_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.rsmi.crud_javafx_app to javafx.fxml;
    exports ma.rsmi.crud_javafx_app;
    exports ma.rsmi.crud_javafx_app.controllers;
    exports ma.rsmi.crud_javafx_app.models;
    opens ma.rsmi.crud_javafx_app.controllers to javafx.fxml;
    opens ma.rsmi.crud_javafx_app.models to javafx.fxml;
}