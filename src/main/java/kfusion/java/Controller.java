package kfusion.java;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea log;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log("Controller initialized");
        log("Hello, JavaFX!");
    }

    protected void log(Object line) {
        log.appendText(line.toString() + System.lineSeparator());
    }
}
