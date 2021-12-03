package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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
	private static int aCount = 0;
	private static int topCount = 0;
	
	@FXML
    private Label ordersInCart;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	
	public void clearOnLogin(ActionEvent event) throws IOException {
		switchToLogin(event);
		wipeFile(event);
	}
	
	public void switchToLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToLogout(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToSignUp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToToppings(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Toppings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToBobaOrder(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("BobaOrder.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};
	
	public void switchToOrderConfirmation(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("OrderConfirmation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		readFile(event);
	};
	
	public void switchToPayment(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CreditCard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	};

	
	//Pic for ty screen
    @FXML
    private ImageView TYimage;

    //Trigger pic for thank you screen
    @FXML
    void submitButton(ActionEvent event) throws IOException {
        Image TYImg = new Image(getClass().getResourceAsStream("TYOrder.png"));
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
    
    
   //Decrement count if remove button is clicked and store into file
    @FXML
    void removeBtn1(ActionEvent event) throws IOException {
    	aCount--;
    	if (aCount < 0)
    	{
    		aCount = 0;
    	}
    	 ordersInCart.setText("Orders in Cart: " + aCount);
    	 PrintWriter writer = new PrintWriter("./src/application/resource/BobaOrder.txt");
	     writer.println(aCount);
	     writer.close();
    }
    
    
  //Increment count if remove button is clicked and store into file
    @FXML
    public void addBtn1(ActionEvent event) throws IOException {
    	aCount +=1;	 
		 ordersInCart.setText("Orders in Cart: " + aCount);
		 //insertBobaCount(BobaCount);
		 PrintWriter writer = new PrintWriter("./src/application/resource/BobaOrder.txt");
	    	writer.println(aCount);
	    	writer.close();
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
      private final String FileName = "./src/application/resource/customerInfo.txt";
      
      private String name, email,user,password;
      private final File file = new File(FileName);
      
      
      //Sign up page to check if valid
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
       
       public void updateLbl(ActionEvent event) throws Exception {
      		lblStatus.setText("Login Successful");
      		lblStatus.setTextFill(Color.LIGHTGREEN);
       }
       
       
       public void Login(ActionEvent event) throws Exception {
       	
           BufferedReader br = new BufferedReader(new FileReader(FileName));
           String line = br.readLine();
           System.out.println(line);
           while (line != null) {
           	String[] columnDetail = line.split("\t", -1);
           	if (columnDetail[2].equals(logUsername.getText())) {
           			if (columnDetail[3].equals(logPassword.getText()))  {
//           		lblStatus.setText("Login Successful");
//           		lblStatus.setTextFill(Color.LIGHTGREEN);
                wipeFile(event);
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
           		lblStatus.setTextFill(Color.RED);
           	}
           	line = br.readLine();
           ///**** End Login
           }
          br.close();
       }
       
       //Keeps count of bobaCart
       public void readFile(ActionEvent event) throws IOException {
   	   
    	   File file = new File("./src/application/resource/BobaOrder.txt");
    	   Scanner scan = new Scanner(file);
    	   
    	   while (scan.hasNextLine()) {
    		   scan.nextLine();
    		   bobaCountCart++;
    	   }
    	   
    	 
       }
       
       //Sets the label and stores amount of drinks into file; add drinks cost
       @FXML
       public void addCartTotal(ActionEvent event) throws IOException {
    	
    	  cartItems.setText("Items in Cart: " + aCount );
    	 
    	//  totalOrder.setText("Total: $" + df.format(total));
    	  
    	Scanner scanner = new Scanner (new File("./src/application/resource/BobaOrder.txt"));
    	  
    	  int [] list = new int [1]; 	 
    	  int i = 0;
    	  while(scanner.hasNextInt())
    	  {
    	       list[i++] = scanner.nextInt();
    	  }
    	  
    	  double totalDrink = 3.99 * list[0];
    	  double totalSum = totalDrink;
    	  totalOrder.setText("Total: $" + df.format(totalSum));
    	
    	  
       }
      
     
     //************** Credit Card******************// 
       @FXML
       private TextField cardField;
       @FXML
       private TextField monthField;
       @FXML
       private TextField yearField;
       @FXML
       private TextField codeField;
       @FXML
       private Label status1, status2, status3;
       // Check: the length of the card number must be 16
       // Check: month is valid 
       // Check: year
       public void CreditCard(ActionEvent event) throws Exception {
    	  if (cardField.getText().length() != 19 && cardField.getText().length() != 16) {
    		  System.out.println(cardField.getText().length() );
    		  status1.setText("Invalid Card Number");
    		  return;
    	  }
    	 
    	  int month = Integer.valueOf(monthField.getText());
    	  int year = Integer.valueOf(yearField.getText());
    	  
    	  if (month >12 && month > 1) {
    		  status2.setText("Invalid Month");
    		  return;
    	  }
    	  int year_now = Calendar.getInstance().get(Calendar.YEAR);
    	  if (year >year_now + 10){
    		  status2.setText("Invalid Year");
    		  return;
    	  }
    	  if (year <year_now){
    		  System.out.println(year_now );
    		  status2.setText("Expired Card");	  
    		  return;
    	  }
    	  if (codeField.getText().length() != 3 ) {
    		  status3.setText("Invalid Security Code");
    		  return;
    	  }
    	  else {
    		  status1.setText("");
    		  status2.setText("");
    		  status3.setText("");
    	  }
    	  
    	  Image TYImg = new Image(getClass().getResourceAsStream("resource/image/topping/TYOrder.png"));
          TYimage.setImage(TYImg);
   	      wipeFile(event);
    	  
       }
       //******** End Credit Card
       
       public void wipeFile(ActionEvent event) throws IOException {
    	   File file = new File("./src/application/resource/BobaOrder.txt");
    	   PrintWriter writer = new PrintWriter(file);
    	   writer.print("");
    	   writer.close();
       }
}
