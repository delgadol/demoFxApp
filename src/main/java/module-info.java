module com.io.demofxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.io.demofxapp to javafx.fxml;
    exports com.io.demofxapp;
}