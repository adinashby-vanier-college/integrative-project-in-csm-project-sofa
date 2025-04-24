module com.example.gravityandorbits {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires junit;
    requires org.junit.jupiter.api;


    opens com.example.gravityandorbits to javafx.fxml;
    exports com.example.gravityandorbits;
}