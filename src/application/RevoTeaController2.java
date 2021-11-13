package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RevoTeaController2 {
	
	@FXML
	Label cartItems;
	
	public void displayName(String username) {
		cartItems.setText(username);
	}
	
	
}