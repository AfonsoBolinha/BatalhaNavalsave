module com.example.batalhanavalsave {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.batalhanavalsave to javafx.fxml;
    exports com.example.batalhanavalsave;
}