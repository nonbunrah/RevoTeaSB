package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			VBox root = (VBox)FXMLLoader.load(getClass().getResource("RevoTea.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			
			Scene scene = new Scene(root,500,800);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
