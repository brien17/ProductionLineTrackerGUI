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
import java.sql.PreparedStatement;

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
    /**
     * This method runs when the app is opened and populates the choose quantity combo box.
     */
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
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  public void productionLineButtonAction(MouseEvent event) {
    System.out.println("production line button clicked");
    Connection conn = connectToDatabase();
    try {
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO PRODUCT (TYPE, MANUFACTURER, NAME) VALUES (?,?,?)");
      pstmt.setString(1, "AUDIO");
      pstmt.setString(2, "Apple");
      pstmt.setString(3, "ipod");
      pstmt.execute();
      pstmt.close();
      conn.close();
    } catch (Exception e) {

    }
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

    /**
     * This method connects to the database and returns a connection object.
     * @return
     */
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
