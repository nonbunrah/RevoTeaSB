package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RevoTeaController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Double bobaPrice = 3.00;
	int cartCount = 0;
	
	@FXML
    private Label ordersInCart;
	
	public void counterPlus(ActionEvent event) throws IOException {
		cartCount++;
		ordersInCart.setText("Orders in Cart: " + cartCount);
	}
	
	
	public void switchToToppings(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Toppings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		cartCount++;
		ordersInCart.setText("orders:" + cartCount);
	};
	
	public void switchToBobaOrder(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/BobaOrder.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToOrderConfirmation(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/OrderConfirmation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};

    @FXML
    private ImageView TYimage;

    @FXML
    void submitButton(ActionEvent event) {
        Image TYImg = new Image(getClass().getResourceAsStream("image/topping/TYOrder.png"));
        TYimage.setImage(TYImg);
    }
	
	@FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    
    @FXML
    private Label labelId;
    
    @FXML
    public void newButtonClick(KeyEvent event) {
         labelId.setText("not label");
    }

    @FXML
    public void SignUp(ActionEvent event) throws Exception {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/SignUp.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
        Window owner = submitButton.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if(emailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your email id");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter a password");
            return;
        }

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", 
                "Welcome " + nameField.getText());
    }
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
}
