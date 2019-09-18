/*
Cameron Brien
9/15/2019
This is the controller file for my java fx application. These methods are run when specific actions
are performed in the application.
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;

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

  @FXML private ComboBox<?> chooseQuantity;

  @FXML private TextArea productionLogTextArea;

  // Methods
  /**
   * This method runs when the production line button is clicked.
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  public void productionLineButtonAction(MouseEvent event) {
    System.out.println("production line button clicked");
    Connection conn = connectToDatabase();
  }

  /**
   * This method runs when the record production button is clicked.
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  public void recordProductionButtonAction(MouseEvent event) {
    System.out.println("record production button clicked");
  }

  public Connection connectToDatabase() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL =
            "jdbc:h2:C:/Users/cam12/OneDrive - Florida Gulf Coast University/OOP/ProductionLineTrackerGUI/res";
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      Connection conn = DriverManager.getConnection(DB_URL);
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
