package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EnterWindowController {

	private Main main;
	private Stage primaryStage;

	public void setMain(Main main, Stage primaryStage) {
		this.main = main;
		this.primaryStage = primaryStage;
	}
	

    @FXML
    private Button buttonDalej;

    @FXML
    void dalej() {
    	try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindowView.fxml"));
			AnchorPane pane = loader.load();
			primaryStage.setTitle("Okno edycji");
	        Scene scene = new Scene(pane);
	        MainWindowController mainWindowController = loader.getController();
	        mainWindowController.setWstepController(this,primaryStage);
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
