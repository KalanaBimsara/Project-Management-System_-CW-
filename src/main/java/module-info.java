module com.example.cw_code {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cw_code to javafx.fxml;
    exports com.example.cw_code;
}
