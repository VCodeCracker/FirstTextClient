module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

//    opens com.example.demo to javafx.fxml;
//    exports com.example.demo;

    // Specify the main class
    // For example, if your main class is in com.example.demo package and named Main
    // use the following line:
     opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}
