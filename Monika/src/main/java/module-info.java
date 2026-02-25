module org.example.streamingplatformprojekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;
    requires mysql.connector.j;

    opens ui to javafx.fxml;

    // Eksporter base package (ofte org.example.streamingplatformprojekt.Main ligger her)
    exports org.example.monika;
}