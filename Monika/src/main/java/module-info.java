module org.example.monika {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.monika to javafx.fxml;
    exports org.example.monika;
}