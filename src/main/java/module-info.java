module com.example.laba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.laba to javafx.fxml;
    exports com.example.laba;
}