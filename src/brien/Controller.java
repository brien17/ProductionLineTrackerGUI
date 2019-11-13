package brien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller.java - the controller class for my entire java fx program. This class manages
 * interacions with the user interface, all other classes, and the database.
 *
 * @author Cameron Brien
 */
public class Controller { // inspect code says can be package private, but won't compile if it is
  // Fields
  @FXML private TextField productName;

  @FXML private TextField productManufacturer;

  @FXML private ChoiceBox<ItemType> productType;

  @FXML private Button productionLineButton;

  @FXML private Button recordProductionButton;

  @FXML private ListView<String> chooseProduct;

  @FXML private ComboBox<String> chooseQuantity;

  @FXML private TextArea productionLogTextArea;

  @FXML private TableView<Product> existingProducts;

  @FXML private TableColumn<?, ?> epColId;

  @FXML private TableColumn<?, ?> epColName;

  @FXML private TableColumn<?, ?> epColMan;

  @FXML private TableColumn<?, ?> epColType;

  private Connection conn;

  // Creating an ArrayList to hold products
  final ArrayList<Product> productLine = new ArrayList<>();

  // Creating an ArrayList to hold production records
  final ArrayList<ProductionRecord> productionRecords = new ArrayList<>();

  // Creating ObservableArrayLists to hold the values for the table and list view
  final ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
  final ObservableList<String> observableProductStrings = FXCollections.observableArrayList();

  private int lastId;
  private int currentProductionNumber;

  // Methods
  /**
   * This method runs when the app is opened and populates the productType and chooseQuantity boxes.
   * It them connects to the database and populates the product line arrayList with the data in the
   * database.
   */
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

    // Selecting the first item
    chooseQuantity.getSelectionModel().selectFirst();

    // Making it editable
    chooseQuantity.setEditable(true);

    // Demonstrating multimedia class functionality
    testMultimedia();

    // Connecting to database
    connectToDatabase();

    // Setting up the product line table
    setupProductLineTable();

    // Load product line
    loadProductLine();

    // Load production records
    loadProductionLog();
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
      int productIndex = chooseProduct.getSelectionModel().getSelectedIndex();
      Widget productToProduce = (Widget) productLine.get(productIndex);
      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      for (int i = 0; i < numProduced; i++) {
        productionRun.add(new ProductionRecord(productToProduce, currentProductionNumber++));
      }
      // Displaying the results
      showProduction(productionRun);

      // Adding to database
      addToProductionDB(productionRun);

    } catch (NumberFormatException ex) {
      System.out.println("Please enter only numbers");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method connects to the database and saves that connection object as a field that can be
   * used later.
   */
  private void connectToDatabase() {
    String dataBaseUrl = "jdbc:h2:C:/Users/Cam/Java/ProductionLineTrackerGUI/res/res";
    String userName = "";
    String pass = "";
    try {
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

      // Reseting the currentProductionNumber
      currentProductionNumber = 0;
      // Looping through results
      while (rs.next()) {
        // Adding 1 to current production number
        currentProductionNumber++;
        // Storing data into variables
        int productNum = rs.getInt(1);
        int productId = rs.getInt(2);
        String serialNum = rs.getString(3);
        Timestamp dateProduced = rs.getTimestamp(4);

        // Creating a product from the database values
        ProductionRecord productionRecord =
            new ProductionRecord(productNum, productId, serialNum, dateProduced);

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

  private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    try {

      for (ProductionRecord record : productionRun) {
        // Making a statement and running it
        PreparedStatement pstmt =
            conn.prepareStatement(
                "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES (?,?,?)");

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

  /** This method displays the production log to the production log text area. */
  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    for (ProductionRecord record : productionRun) {
      productionLogTextArea.appendText(record.toString() + "\n");
    }
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
