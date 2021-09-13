package app;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	double x,y = 0;
	@Override
	public void start(Stage primaryStage) {
//		primaryStage.initStyle(StageStyle.UNDECORATED);
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ui/homepage/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("ui/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Manage Supermarket");
		    primaryStage.show();
		    
	        root.setOnMousePressed(event -> {
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });

	        root.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - x);
	            primaryStage.setY(event.getScreenY() - y);
	        });
		    
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
