module sys.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens sys.main to javafx.fxml;
    exports sys.main;
}