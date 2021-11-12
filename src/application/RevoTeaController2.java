package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RevoTeaController2 {
	
	@FXML
	Label nameLabel;
	
	public void displayName(String username) {
		nameLabel.setText(username);
	}
}