package brien;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 * Controller.java - the controller class for my entire java fx program. This class manages
 * interactions with the user interface, all other classes, and the database.
 *
 * @author Cameron Brien
 */
public class Controller { // inspect code says can be package private, but won't compile if it is

  /** This TextField is used to enter the name of a new product. */
  @FXML private TextField productName;

  /** This TextField is used to enter the manufacturer of a new product. */
  @FXML private TextField productManufacturer;

  /** This ChoiceBox is used to select the item type of a new product. */
  @FXML private ChoiceBox<ItemType> productType;

  /**
   * This ListView is used to hold information about the products that employee's can produce and
   * allow them to select the one they want from the list.
   */
  @FXML private ListView<String> chooseProduct;

  /** This ComboBox is used to allow the employee to specify how many of a product were created. */
  @FXML private ComboBox<String> chooseQuantity;

  /** This TextArea is used to display the production log. */
  @FXML private TextArea productionLogTextArea;

  /** This TableView is used to display the product that are available to produce. */
  @FXML private TableView<Product> existingProducts;

  /** The column for the product ID. */
  @FXML private TableColumn<?, ?> epColId;

  /** The column for the product name. */
  @FXML private TableColumn<?, ?> epColName;

  /** The column for the product manufacturer. */
  @FXML private TableColumn<?, ?> epColMan;

  /** The column for the product item type. */
  @FXML private TableColumn<?, ?> epColType;

  /**
   * This Label informs the user that the information was saved when they record production and
   * notifies them when they enter an invalid value for the number of products produced.
   */
  @FXML private Label chooseQuantityLabel;

  /** This TextField is used to enter the full name of the employee. */
  @FXML private TextField fullNameTextField;

  /** This PasswordField is used to enter the password for the employee account. */
  @FXML private PasswordField passwordField;

  /** This Label is used to display information about the user logging in or creating an account. */
  @FXML private Label employeeLabel;

  /** This Label is used display the employee who is currently logged in on the employee screen. */
  @FXML private Label currentUserLabel;

  /**
   * This Label is used display the employee who is currently logged in on the record production
   * screen.
   */
  @FXML private Label currentUserLabel1;

  /**
   * This Label is used display the employee who is currently logged in on the product line screen.
   */
  @FXML private Label currentUserLabel2;

  /**
   * This Label is used display the employee who is currently logged in on the production log
   * screen.
   */
  @FXML private Label currentUserLabel3;

  /**
   * This is the Connection object used by all of the methods that write to or read from the
   * database.
   */
  private Connection conn;

  /** This ArrayList holds the product that are currently available. */
  private final ArrayList<Product> productLine = new ArrayList<>();

  /** This ArrayList is used to hold the record of production. */
  private final ArrayList<ProductionRecord> productionRecords = new ArrayList<>();

  /** This ArrayList is used to hold the employees. */
  private final ArrayList<Employee> employees = new ArrayList<>();

  /** This ObservableList is used to display the products in the existingProduct TableView. */
  private final ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
  /**
   * This ObservableList is used to display information about the products to the ChooseProduct
   * ListView.
   */
  private final ObservableList<String> observableProductStrings =
      FXCollections.observableArrayList();

  /** This field holds the last product ID used in the program. */
  private int lastId;

  /** This field holds the production number to be used by the next created product. */
  private int currentProductionNumber;

  /** This field holds the employee account for the employee who is currently logged in. */
  private Employee currentEmployee = new Employee("", "");

  /**
   * This method runs when the app is opened and populates the productType and chooseQuantity boxes.
   * It them connects to the database and populates the product line arrayList with the data in the
   * database.
   */
  @FXML
  public void initialize() {
    // Displaying the current user
    displayCurrentUser();

    // Adding values to they type choice box
    for (ItemType it : ItemType.values()) {
      productType.getItems().add(it);
    }

    // Adding numbers to the quantity selector
    for (int i = 1; i < 11; i++) {
      String number = "" + i;
      chooseQuantity.getItems().add(number);
    }

    // Making it editable
    chooseQuantity.setEditable(true);

    // Demonstrating multimedia class functionality
    testMultimedia();

    // Connecting to database
    connectToDatabase();

    // Setting up the product line table
    setupProductLineTable();

    // Load product line from database
    loadProductLine();

    // Load production records from database
    loadProductionLog();

    // Loading the employees from database
    loadEmployees();

    // Selecting the first item
    chooseProduct.getSelectionModel().selectFirst();
    chooseQuantity.getSelectionModel().selectFirst();
  }

  /**
   * This method runs when the production line button is clicked and adds the information specified
   * to the database.
   */
  @FXML
  public void productionLineButtonAction() {
    System.out.println("production line button clicked");

    // Getting user input values
    String name = productName.getText();
    String manufacturer = productManufacturer.getText();
    ItemType item = productType.getValue();
    try {
      // Incrementing the last ID value
      lastId++;

      // Creating product specified
      Widget product = new Widget(lastId, name, manufacturer, item);

      // Adding to products array list
      productLine.add(product);

      // Displaying in table view
      observableProductLine.add(product);

      // Display in list view
      observableProductStrings.add(product.toString());

      // Making a statement and running it
      // Inspect code says possible null pointer here but is already in a try block
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO PRODUCT (TYPE, MANUFACTURER, NAME) VALUES (?,?,?)");

      pstmt.setString(1, product.getType().code);
      pstmt.setString(2, product.getManufacturer());
      pstmt.setString(3, product.getName());
      pstmt.execute();
      pstmt.close();

    } catch (NullPointerException npe) {
      System.out.println("Please complete all fields");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR");
    }
  }

  /**
   * This method runs when the record production button is clicked. It adds the results of the
   * production to the database and displays the results on the production log screen.
   */
  @FXML
  public void recordProductionButtonAction() {
    String quantity = chooseQuantity.getValue();
    System.out.println("record production button clicked");
    try {
      int numProduced = Integer.parseInt(quantity);
      System.out.println("Produced: " + numProduced);
      int productIndex = chooseProduct.getSelectionModel().getSelectedIndex();
      Widget productToProduce = (Widget) productLine.get(productIndex);
      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      for (int i = 0; i < numProduced; i++) {
        productionRun.add(
            new ProductionRecord(
                productToProduce, currentProductionNumber++, currentEmployee.getUsername()));
      }
      // Displaying the results
      showProduction(productionRun);

      // Adding to database
      addToProductionDB(productionRun);

      // Display message to user
      chooseQuantityLabel.setText("Production Recorded");
      chooseQuantityLabel.setStyle("-fx-text-fill: black");
      chooseQuantityLabel.setVisible(true);

      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> chooseQuantityLabel.setVisible(false));
      visiblePause.play();

    } catch (NumberFormatException ex) {
      System.out.println("Please enter only numbers");

      // Displaying error label
      chooseQuantityLabel.setStyle("-fx-text-fill: firebrick");
      chooseQuantityLabel.setText("Please enter only whole numbers");
      chooseQuantityLabel.setVisible(true);

      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> chooseQuantityLabel.setVisible(false));
      visiblePause.play();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method runs when the login button is clicked and, if the account details entered by the
   * user are valid, it sets the currentEmployee to be the Employee who is logging in. Otherwise it
   * notifies the user that the entered credentials are not valid.
   */
  public void loginButtonAction() {
    // Boolean to keep track of if the login was successful
    boolean successfulLogin = false;

    // Getting the full name and password entered by the user
    String fullName = fullNameTextField.getText();
    String password = passwordField.getText();

    // Saving the results of the login attempt
    Employee employeeToLogin = new Employee(fullName, password);
    // Setting currentEmployee as a new Employee created if it matches an existing employee in the
    // database
    for (Employee employee : employees) {
      System.out.println(employee.getUsername());
      if (employee.getUsername().equals(employeeToLogin.getUsername())
          && employee.getPassword().equals(employeeToLogin.getPassword())) {
        // Setting the employee entered as the current employee
        currentEmployee = employeeToLogin;
        successfulLogin = true;
        // Display message to user
        employeeLabel.setText("Signed in as " + currentEmployee.getName());
        employeeLabel.setStyle("-fx-text-fill: black");
        employeeLabel.setVisible(true);
        // Hiding label
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
        visiblePause.play();
        // Updating the labels
        displayCurrentUser();
        break;
      }
    }
    if (!successfulLogin) {
      // Display message to user
      employeeLabel.setText("Incorrect name or password");
      employeeLabel.setStyle("-fx-text-fill: firebrick");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();
    }
  }

  /**
   * This method runs when the createAccountButton is clicked. If the information entered by the
   * user is valid, it creates a new Employee object using the data that the user entered, sets the
   * currentUser as the new Employee, and calls the addEmployeeToDatabase method with the new
   * Employee. Otherwise it notifies the user of an error.
   */
  public void createAccountButtonAction() {
    // Getting full name and password entered by the user
    String fullName = fullNameTextField.getText();
    String password = passwordField.getText();

    // Creating a new Employee object
    Employee newEmployee = new Employee(fullName, password);

    // Checking if their username or password is rejected
    if (newEmployee.getUsername().equals("default")) {
      // Display message to user
      employeeLabel.setText("Please enter your first and last name separated by a space");
      employeeLabel.setStyle("-fx-text-fill: firebrick");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();

    } else if (newEmployee.getPassword().equals("pw")) {
      // Display message to user
      employeeLabel.setText(
          "Please use an uppercase letter, lowercase letter, and special character in your "
              + "password");
      employeeLabel.setStyle("-fx-text-fill: firebrick");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();

    } else {
      // Adding to the employees ArrayList
      employees.add(newEmployee);
      // Setting the newly created account as the current account
      currentEmployee = newEmployee;
      // Adding the account to the database
      addEmployeeToDatabase(newEmployee);

      // Display message to user
      employeeLabel.setText("Account Created. Signed in as " + currentEmployee.getName());
      employeeLabel.setStyle("-fx-text-fill: black");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();
      // Updating the labels
      displayCurrentUser();
    }
  }

  /**
   * This method connects to the database and saves that connection object as a field that can be
   * used later.
   */
  private void connectToDatabase() {
    String dataBaseUrl = "jdbc:h2:./res/res";
    String userName = "";
    try {
      // Getting the reversed password from file
      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      String reversedPass = prop.getProperty("password");

      // Reversing the password to get the correct password
      String pass = reversePassword(reversedPass);

      // Registering the driver
      Class.forName("org.h2.Driver");

      // Creating and returning connection object
      conn =
          DriverManager.getConnection(
              dataBaseUrl, userName, pass); // findbugs doesn't like the empty password
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method uses a database connection to get the products that are available for production
   * and then adds those products to the observableProductLine ObservableArrayList and the contents
   * of their toString() method to the observableProductStrings ObservableArrayList.
   */
  private void loadProductLine() {
    try {
      // Making a statement
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      // Looping through results
      while (rs.next()) {
        // Storing data into variables
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String type = rs.getString(3);
        String manufacturer = rs.getString(4);
        // Getting the proper item type from the code
        ItemType item;
        switch (type) {
          case "AM":
            item = ItemType.AudioMobile;
            break;
          case "AU":
            item = ItemType.Audio;
            break;
          case "VI":
            item = ItemType.Visual;
            break;
          case "VM":
            item = ItemType.VisualMobile;
            break;
          default:
            item = null;
        }
        // Creating product objects from the database information
        Widget product = new Widget(id, name, manufacturer, item);
        // Adding those objects to the array list and observableLists
        productLine.add(product);
        observableProductLine.add(product);
        observableProductStrings.add(product.toString());
      }

      // Getting the last index to create a new product with later
      lastId = productLine.get(productLine.size() - 1).getId();

      // Closing statement
      stmt.close();
    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method uses a database connection to get information from the production record and
   * creates ProductionRecord objects from that information. It them stores them in the
   * productionRecord ArrayList.
   */
  private void loadProductionLog() {
    try {
      // Making a statement
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");

      // Resetting the currentProductionNumber
      currentProductionNumber = 1;
      // Looping through results
      while (rs.next()) {
        // Adding 1 to current production number
        currentProductionNumber++;
        // Storing data into variables
        int productNum = rs.getInt(1);
        int productId = rs.getInt(2);
        String serialNum = rs.getString(3);
        Timestamp dateProduced = rs.getTimestamp(4);
        String creator = rs.getString(5);

        // Creating a production record from the database values
        ProductionRecord productionRecord =
            new ProductionRecord(productNum, productId, serialNum, dateProduced, creator);

        // Adding to the ArrayList
        productionRecords.add(productionRecord);
      }

      // Displaying to the text area
      showProduction(productionRecords);

      // Closing statement
      stmt.close();

    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to load the employees ArrayList with Employee objects created from the
   * EMPLOYEE table in the database.
   */
  private void loadEmployees() {
    try {
      // Making a statement
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");

      // Looping through results
      while (rs.next()) {

        // Storing data into variables
        String fullName = rs.getString(1);
        String password = rs.getString(2);

        // Creating a product from the database values
        Employee employee = new Employee(fullName, password);

        // Adding to the ArrayList
        employees.add(employee);
      }

      // Displaying to the text area
      showProduction(productionRecords);

      // Closing statement
      stmt.close();

    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to add a list of production records to the production record database
   * table.
   *
   * @param productionRun An ArrayList containing the ProductionRecords to be added to the database
   */
  private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    try {
      for (ProductionRecord record : productionRun) {
        // Making a statement and running it
        PreparedStatement pstmt =
            conn.prepareStatement(
                "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
                    + "VALUES (?,?,?)");

        pstmt.setInt(1, record.getProductId());
        pstmt.setString(2, record.getSerialNumber());
        pstmt.setTimestamp(3, new Timestamp(record.getDateProduced().getTime()));
        pstmt.execute();
        pstmt.close();
      }
    } catch (NullPointerException npe) {
      System.out.println("Please complete all fields");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR");
    }
  }

  /**
   * This method adds the username, password, and email of an Employee object to the EMPLOYEE table
   * in the database.
   *
   * @param employee The employee object who's details are to be added
   */
  private void addEmployeeToDatabase(Employee employee) {
    try {

      // Making a statement and running it
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO EMPLOYEE (FULL_NAME, PASSWORD)" + "VALUES (?,?)");

      pstmt.setString(1, employee.getName().toString());
      pstmt.setString(2, employee.getPassword());
      pstmt.execute();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method sets up the existingProducts table and the chooseProduct list and associates them
   * with observable array lists containing the values that they need.
   */
  private void setupProductLineTable() {
    epColId.setCellValueFactory(new PropertyValueFactory<>("id"));
    epColName.setCellValueFactory(new PropertyValueFactory<>("name"));
    epColMan.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    epColType.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Adding items to the existingProducts table view
    existingProducts.setItems(observableProductLine);

    // Adding that array list to the choose product list view
    chooseProduct.setItems(observableProductStrings);
  }

  /**
   * This method displays the production log to the production log text area.
   *
   * @param productionRun An ArrayList of all of the ProductionRecord that were created.
   */
  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    for (ProductionRecord record : productionRun) {
      productionLogTextArea.appendText(record.toString() + "\n");
    }
  }

  /**
   * This method works recursively to reverse the password that is passed into it and returns the
   * result.
   *
   * @param password The password that you want to reverse
   * @return The reversed password
   */
  private String reversePassword(String password) {
    // Getting last character of the string
    String lastChar = password.substring(password.length() - 1);

    // Checking if there is more than one character in the string
    if (password.length() == 1) {
      // Returning the only remaining character
      return lastChar;
    } else {
      // Getting all but the last character of the string
      String allButLast = password.substring(0, password.length() - 1);
      // Returning the last character plus the result of this method called with allButLast
      return lastChar + reversePassword(allButLast);
    }
  }

  /**
   * This method is used to update the current user labels on all of the different tabs all at once.
   */
  private void displayCurrentUser() {
    currentUserLabel.setText("Current User: " + currentEmployee.getUsername());
    currentUserLabel1.setText("Current User: " + currentEmployee.getUsername());
    currentUserLabel2.setText("Current User: " + currentEmployee.getUsername());
    currentUserLabel3.setText("Current User: " + currentEmployee.getUsername());
  }

  /**
   * This is a test class to demonstrate that the AudioPlayer and MoviePlayer classes are working
   * properly.
   */
  private static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    MoviePlayer newerMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LED);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    productList.add(newerMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }
}
