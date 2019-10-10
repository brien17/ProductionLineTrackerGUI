/**
 * This is the controller file for my java fx application. This class handles all of the logic in my
 * JavaFX application
 *
 * @author Cameron Brien
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller { // inspect code says can be package private, but won't compile if it is
  // Fields
  @FXML private TextField productName;

  @FXML private TextField productManufacturer;

  @FXML private ChoiceBox<ItemType> productType;

  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private ListView<?> chooseProduct;

  @FXML private ComboBox<String> chooseQuantity;

  @FXML private TextArea productionLogTextArea;

  @FXML private TableView<Product> existingProducts;

  @FXML private TableColumn<?, ?> epColId;

  @FXML private TableColumn<?, ?> epColName;

  @FXML private TableColumn<?, ?> epColMan;

  @FXML private TableColumn<?, ?> epColType;

  private Connection conn;
  private ArrayList<Product> products = new ArrayList<>();

  // Methods
  /** This method runs when the app is opened and populates the choose quantity combo box. */
  @FXML
  public void initialize() {
    // Adding values to they type choice box
    for (ItemType it : ItemType.values()) {
      productType.getItems().add(it);
    }

    // Adding numbers to the quantity selector
    for (int i = 1; i < 11; i++) {
      String number = "" + i;
      chooseQuantity.getItems().add(number);
    }
    chooseQuantity.getSelectionModel().selectFirst();
    chooseQuantity.setEditable(true);
    testMultimedia();
    connectToDatabase();
    populateProductLine();
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
      // Creating product specified
      Widget product = new Widget(name, manufacturer, item.code);

      // Adding to products array list
      products.add(product);

      // Displaying in table view
      existingProducts.getItems().add(product);

      // Making a statement and running it
      // Inspect code says possible null pointer here but is already in a try block
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO PRODUCT (TYPE, MANUFACTURER, NAME) VALUES (?,?,?)");

      pstmt.setString(1, product.getType());
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
  private void connectToDatabase() {
    String dataBaseUrl =
        "jdbc:h2:C:/Users/cam12/OneDrive - Florida Gulf Coast University/OOP/"
            + "ProductionLineTrackerGUI/res";
    String userName = "";
    String pass = "";
    try {
      // Registering the driver
      Class.forName("org.h2.Driver");

      // Creating and returning connection object
      conn = DriverManager.getConnection(dataBaseUrl, userName, pass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void populateProductLine() {
    epColId.setCellValueFactory(new PropertyValueFactory<>("id"));
    epColName.setCellValueFactory(new PropertyValueFactory<>("name"));
    epColMan.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    epColType.setCellValueFactory(new PropertyValueFactory<>("type"));

    try {
      // Making a statement and running it
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
      // Creating product objects from the database information
      while (rs.next()) {
        int id = Integer.parseInt(rs.getString(1));
        String name = rs.getString(2);
        String manufacturer = rs.getString(3);
        String type = rs.getString(4);
        // Adding those objects to the array list
        Widget product = new Widget(id, name, manufacturer, type);
        products.add(product);
      }
      ObservableList<Product> productObservableList = FXCollections.observableArrayList(products);
      existingProducts.setItems(productObservableList);
    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }
}
