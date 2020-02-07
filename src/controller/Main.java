package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Wstep.fxml"));
			AnchorPane pane = loader.load();
			primaryStage.setTitle("Skorowidz Krzyzowkowy");
	        Scene scene = new Scene(pane);
	        EnterWindowController enterWindowController = loader.getController();
	        enterWindowController.setMain(this,primaryStage);
	        primaryStage.setMinWidth(500.00);
	        primaryStage.setMinHeight(600.00);
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}