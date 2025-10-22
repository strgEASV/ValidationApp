module dk.easv.validationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.validationapp to javafx.fxml;
    exports dk.easv.validationapp;
}