module com.example.gravityandorbits {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gravityandorbits to javafx.fxml;
    exports com.example.gravityandorbits;
}