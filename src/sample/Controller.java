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
public class Controller {
  // Fields
  @FXML private TextField productName;

  @FXML private TextField manufacturer;

  @FXML private ChoiceBox<?> type;

  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private ListView<?> chooseProduct;

  @FXML private ComboBox<Integer> chooseQuantity;

  @FXML private TextArea productionLogTextArea;

  // Methods
  /** This method runs when the app is opened and populates the choose quantity combo box. */
  @FXML
  public void initialize() {
    for (int i = 1; i < 11; i++) {
      chooseQuantity.getItems().add(i);
      chooseQuantity.getSelectionModel().selectFirst();
      chooseQuantity.setEditable(true);
    }
  }

  /**
   * This method runs when the production line button is clicked.
   */
  @FXML
  public void productionLineButtonAction() {
    System.out.println("production line button clicked");
    try {
      Connection conn = connectToDatabase();

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
    }
  }

  /**
   * This method runs when the record production button is clicked.
   */
  @FXML
  public void recordProductionButtonAction() {
    System.out.println("record production button clicked");
  }

  /**
   * This method connects to the database and returns a connection object.
   *
   * @return A database connection object
   */
  private Connection connectToDatabase() {
    try {
      // STEP 1: Register JDBC driver
      Class.forName("org.h2.Driver");

      // STEP 2: Open a connection
      return DriverManager.getConnection(
          "jdbc:h2:C:/Users/cam12/OneDrive - Florida Gulf Coast University/OOP/"
              + "ProductionLineTrackerGUI/res");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
