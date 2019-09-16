/*
Cameron Brien
9/15/2019
This is the controller file for my java fx application. These methods are run when specific actions
are performed in the application.
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This is the controller for my application.
 *
 * @author Cameron Brien
 */
public class Controller {
  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private Button productionLogButton;

  /**
   * This method runs when the production line button is clicked.
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  void productionLineButtonAction(MouseEvent event) {
    System.out.println("production line button clicked");
  }

  /**
   * This method runs when the production log button is clicked.
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  void productionLogButtonAction(MouseEvent event) {
    System.out.println("production log button clicked");
  }

  /**
   * This method runs when the record production button is clicked.
   *
   * @param event The event is the mouse button being clicked
   */
  @FXML
  void recordProductionButtonAction(MouseEvent event) {
    System.out.println("record production button clicked");
  }
}
