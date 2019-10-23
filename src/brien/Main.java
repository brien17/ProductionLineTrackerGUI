/*
Cameron Brien
9/15/2019
This is the main file for my java fx application. This file contains the methods to launch the
application.
 */

package brien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class of my program.
 *
 * @author Cameron Brien
 */
public class Main extends Application {
  /**
   * This method is the starting point for my Java FX program.
   *
   * @param primaryStage the first thing the user sees
   * @throws Exception potential exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductionTabs.fxml"));
    primaryStage.setTitle("Production Records");
    primaryStage.setScene(new Scene(root, 680, 700));
    primaryStage.show();
  }

  /**
   * This is the main method of my program that launches the application.
   *
   * @param args The application to be launched
   */
  public static void main(String[] args) {
    launch(args);
  }
}
