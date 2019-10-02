/*
Cameron Brien
9/15/2019
This is the controller file for my java fx application. These methods are run when specific actions
are performed in the application.
 */

package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This is the controller for my application.
 *
 * @author Cameron Brien
 */
public class Controller { // inspect code says can be package private, but won't compile if it is
  // Fields
  @FXML private TextField productName;

  @FXML private TextField manufacturer;

  @FXML private ChoiceBox<ItemType> type;

  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private ListView<?> chooseProduct;

  @FXML private ComboBox<String> chooseQuantity;

  @FXML private TextArea productionLogTextArea;

  // Methods
  /** This method runs when the app is opened and populates the choose quantity combo box. */
  @FXML
  public void initialize() {
    // Adding values to they type choice box
    for (ItemType it : ItemType.values()) {
      type.getItems().add(it);
    }

    // Adding numbers to the quantity selector
    for (int i = 1; i < 11; i++) {
      String number = "" + i;
      chooseQuantity.getItems().add(number);
    }
    chooseQuantity.getSelectionModel().selectFirst();
    chooseQuantity.setEditable(true);
  }

  /**
   * This method runs when the production line button is clicked and adds the information specified
   * to the database.
   */
  @FXML
  public void productionLineButtonAction() {
    System.out.println("production line button clicked");

    // Declaring the connection object
    Connection conn = null;
    try {
      // Getting a connection to the database
      conn = connectToDatabase();

      // Making a statement and running it
      // Inspect code says possible null pointer here but is already in a try block
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO PRODUCT (TYPE, MANUFACTURER, NAME) VALUES (?,?,?)");

      pstmt.setString(1, "AUDIO");
      pstmt.setString(2, "Apple");
      pstmt.setString(3, "ipod");
      pstmt.execute();
      pstmt.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();

      // This looks hideous but findBugs is mad otherwise maybe some way to make look better
      // in the future
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception r) {
          System.out.println("closed");
        }
      }
    }
  }

  /**
   * This method runs when the record production button is clicked and prints the number of items
   * that were produced to the console.
   */
  @FXML
  public void recordProductionButtonAction() {
    String quantity = chooseQuantity.getValue();
    System.out.println("record production button clicked");
    try {
      int numProduced = Integer.parseInt(quantity);
      System.out.println("Produced: " + numProduced);
    } catch (NumberFormatException ex) {
      System.out.println("Please enter only numbers");
    }
  }

  /**
   * This method connects to the database and returns a connection object.
   *
   * @return A database connection object
   */
  private Connection connectToDatabase() {
    String dataBaseUrl =
        "jdbc:h2:C:/Users/cam12/OneDrive - Florida Gulf Coast University/OOP/"
            + "ProductionLineTrackerGUI/res";
    String userName = "";
    String pass = "";
    try {
      // Registering the driver
      Class.forName("org.h2.Driver");

      // Creating and returning connection object
      return DriverManager.getConnection(dataBaseUrl, userName, pass);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
