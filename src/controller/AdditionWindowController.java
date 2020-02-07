package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Haslo;

public class AdditionWindowController {
    private MainWindowController mainWindowController;
    private Stage secondaryStage;

    public void setMainWindowController(MainWindowController mainWindowController,Stage secondaryStage) {
        this.mainWindowController = mainWindowController;
        this.secondaryStage = secondaryStage;
    }

    @FXML
    private TextField textHaslo;

    @FXML
    private TextArea textOpis;

    @FXML
    void dodaj() {
        Haslo haslo = new Haslo(textHaslo.getText(), textOpis.getText());
        if(haslo.isPasswordGood(haslo.getHaslo())==true) {
            mainWindowController.addToHaslaList(new Haslo(textHaslo.getText(), textOpis.getText()));
            secondaryStage.close();
        }else {
            Alert alert = new Alert(AlertType.WARNING, "Haslo moze zawierac tylko male i duze litery", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
