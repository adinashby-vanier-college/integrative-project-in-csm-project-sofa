module com.example.gravityandorbits {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    //requires junit;
    //requires org.junit.jupiter.api;
    //requires org.junit.platform.commons;


    opens com.example.gravityandorbits to javafx.fxml, org.junit.platform.commons;
    exports com.example.gravityandorbits;
}