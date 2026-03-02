module org.example.monika {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;
    requires mysql.connector.j;

    opens org.example.monika.ui to javafx.fxml;
    exports org.example.monika;
    opens org.example.monika.Navigation to javafx.fxml;
}