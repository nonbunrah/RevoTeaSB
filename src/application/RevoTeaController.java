package application;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	
	@FXML
	TextField nameTextField;
	
	@FXML
	Label cartItems;
	
	@FXML
	Label totalOrder;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
    private static int bobaCountCart = 0;
	
	Double bobaPrice = 3.00;
	int cartCount = 0;
	int aCount = 0;
	@FXML
    private Label ordersInCart;
	
//	public void sendData(ActionEvent event) throws IOException {
//		String username = "hello";
//		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/OrderConfirmation.fxml"));
//		root = loader.load();
//		
//		RevoTeaController2 scene2Controller = loader.getController();
//		scene2Controller.displayName(username);
//		
//		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public void switchToLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToLogout(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToSignUp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/SignUp.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToToppings(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Toppings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
		readFile(event);
	};
	
	public void switchToPayment(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/CreditCard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};

    @FXML
    private ImageView TYimage;

    @FXML
    void submitButton(ActionEvent event) throws IOException {
        Image TYImg = new Image(getClass().getResourceAsStream("image/topping/TYOrder.png"));
        TYimage.setImage(TYImg);
 	    wipeFile(event);
    }
    
    @FXML
    public void newButtonClick(KeyEvent event) {
         labelId.setText("not label");
    }
    
    
    //Buttons for Adding/Removing
    @FXML
    private Button remove1;
    @FXML
    
    private Button add1;
    
    @FXML
    void removeBtn1(ActionEvent event) {
    	aCount--;
    	if (aCount < 0)
    	{
    		aCount = 0;
    	}
    	 ordersInCart.setText("Orders in Cart: " + aCount);
    }
    
    @FXML
    void addBtn1(ActionEvent event) throws IOException {
    	aCount++;	 
		 ordersInCart.setText("Orders in Cart: " + aCount);
		 insertBobaCount(BobaCount);
    }    
    
    
  //*********** SignUp******************************
  	  @FXML
      private TextField nameField;
  	  
      @FXML
      private TextField emailField;
      
      @FXML
      private TextField usernameField;
      
      @FXML
      private PasswordField passwordField;
      
      @FXML
      private Button submitButton;
      
      @FXML
      private Label labelId;
      private final String FileName = "bin/application/customerInfo.txt";
      private String name, email,user,password;
      private final File file = new File(FileName);
      
      @FXML
      public void SignUp(ActionEvent event) throws Exception {
          Window owner = submitButton.getScene().getWindow();
          name = nameField.getText();
          email = emailField.getText();
          user = usernameField.getText();
          password = passwordField.getText();
          if(name.isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Please enter your name");
              return;
          }
          if(email.isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                      "Please enter your email id");
              return;
          }
          if(user.isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                      "Please enter your username");
              return;
          }
          if(password.isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                      "Please enter a password");
              return;
          }
          if (isValid(email)== false) {
          	 showAlert(Alert.AlertType.ERROR, owner, "Invalid email"," Invalid email -\nPlease choose another one");
          	 return;
          	}
          
          //check if email or username exist
          boolean check = true;
          BufferedReader br = new BufferedReader(new FileReader(FileName));
          String line = br.readLine();
          while (line != null) {
          	String[] columnDetail = line.split("\t", -1);
          	if (columnDetail[1].equals(email)) {
          		System.out.println(columnDetail[1]);
          		check = false;
          		showAlert(Alert.AlertType.ERROR, owner, "Warning","Existing email");
          		break;
          	}
          	if (columnDetail[2].equals(user)) {
          		check = false;
          		showAlert(Alert.AlertType.ERROR, owner, "Warning","Existed username");
          		break;
          	}
          	line = br.readLine();
          }
          System.out.println(check);
          if (check) {
          inserNewCustomer(file,name, email, user, password);
          showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",  "Welcome " + nameField.getText());
          switchToLogin(event);
          br.close();
      }}
      public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
          Alert alert = new Alert(alertType);
          alert.setTitle(title);
          alert.setHeaderText(null);
          alert.setContentText(message);
          alert.initOwner(owner);
          alert.show();
      }
      public static boolean isValid(String email)
      {
          String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                              "[a-zA-Z0-9_+&*-]+)*@" +
                              "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                              "A-Z]{2,7}$";
                                
          Pattern pat = Pattern.compile(emailRegex);
          if (email == null)
              return false;
          return pat.matcher(email).matches();
      }
   // insert a new customer
      public static void inserNewCustomer(File file,String name, String email, String user, String password) throws IOException 
      {
      	FileOutputStream fos = null;
          Writer writer = null;
          System.out.println(name + "\t" + email + "\t" + user + "\t" + password + "\n");
          try {
              fos = new FileOutputStream(file.getAbsolutePath(), true);
              writer = new OutputStreamWriter(fos, "UTF-8");

              writer.write(name + "\t" + email + "\t" + user + "\t" + password + "\n");
              writer.flush();
              writer.close();

          } catch (IOException br) {
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Error Encountered");
              alert.setContentText("Error: " + br.getMessage());

          }
      }
      //*************End SignUp***************************** 
      
      //*************Login ***************************** 
       @FXML
       private TextField logUsername;
       @FXML
       private TextField logPassword;
       @FXML
       private Label lblStatus;
       
       public void Login(ActionEvent event) throws Exception {
       	
           BufferedReader br = new BufferedReader(new FileReader(FileName));
           String line = br.readLine();
           System.out.println(line);
           while (line != null) {
           	String[] columnDetail = line.split("\t", -1);
           	if (columnDetail[2].equals(logUsername.getText())) {
           			if (columnDetail[3].equals(logPassword.getText()))  {
           		//System.out.println(columnDetail[1]);
           		lblStatus.setText("Login Successful");
           		switchToBobaOrder(event);
           		break;
           	}
           			else {
           		lblStatus.setText("Wrong Password");
           		break;
           			}
           	}
           	else {
           		lblStatus.setText("Wrong username or username not exist");
           	}
           	line = br.readLine();
           	br.close();
           ///**** End Login
           }
       }
       
       private final static String FileNamePrice = "bin/application/BobaOrder.txt";
       private final static File filePrice = new File(FileNamePrice);
       private final int BobaCount = 1;
       
       public static void insertBobaCount(int BobaCount) throws IOException 
       {
       	FileOutputStream fos = null;
           Writer writer = null;
           try {
               fos = new FileOutputStream(filePrice.getAbsolutePath(), true);
               writer = new OutputStreamWriter(fos, "UTF-8");

               writer.write(BobaCount + "\n");
               writer.flush();
               writer.close();

           } catch (IOException br) {
               Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Error Encountered");
               alert.setContentText("Error: " + br.getMessage());

           }
       }
       
       public void readFile(ActionEvent event) throws IOException {
    	   File file = new File("bin/application/BobaOrder.txt");
    	   Scanner scan = new Scanner(file);
    	   while (scan.hasNextLine()) {
    		   scan.nextLine();
    		   bobaCountCart++;
    	   }
    	   System.out.println("final:" + bobaCountCart);
    	   scan.close();
       }
       
       @FXML
       public void addCartTotal(ActionEvent event) throws IOException {
    	  cartItems.setText("Items in Cart: " + bobaCountCart);
    	  double total = bobaCountCart * 3.99;
    	  totalOrder.setText("Total: $" + df.format(total));
       }
       
       // not used yet
       public void wipeFile(ActionEvent event) throws IOException {
    	   File file = new File("bin/application/BobaOrder.txt");
    	   PrintWriter writer = new PrintWriter(file);
    	   writer.print("");
    	   writer.close();
       }
}
