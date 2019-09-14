package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {
  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private Button productionLogButton;

  @FXML
  void productionLineButtonAction(MouseEvent event) {
    System.out.println("production line button clicked");
  }

  @FXML
  void productionLogButtonAction(MouseEvent event) {
    System.out.println("production log button clicked");
  }

  @FXML
  void recordProductionButtonAction(MouseEvent event) {
    System.out.println("record production button clicked");
  }
}
