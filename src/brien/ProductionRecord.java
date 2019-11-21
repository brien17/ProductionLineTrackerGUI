package brien;

import java.util.Date;

/**
 * ProductionRecord.java - This class is used to represent an entry in the production record that
 * tracks all of the products produced at the factory
 *
 * @author Cameron Brien
 */
class ProductionRecord {
  private int productionNumber;
  private int productId;
  private String serialNumber;
  private Date dateProduced;
  private String creator;

  /**
   * This is a constructor for the ProductionRecord class. This constructor accepts an argument for
   * the productId.
   *
   * @param productId The unique identification code for the product
   */
  ProductionRecord(int productId) {
    this.productId = productId;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * This is a constructor for the ProductionRecord class. This constructor accepts arguments for
   * the productionNumber, productId, serialNumber, and dateProduced.
   *
   * @param productionNumber The number of total products that have been produced
   * @param productId The unique identification code for the product
   * @param serialNumber The serial number of the product
   * @param dateProduced The date the product was produced
   * @param creator The employee who produced the product
   */
  ProductionRecord(
      int productionNumber, int productId, String serialNumber, Date dateProduced, String creator) {
    this.productionNumber = productionNumber;
    this.productId = productId;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());
    this.creator = creator;
  }

  /**
   * This is a constructor for the ProductionRecord class. This constructor takes in a product and
   * the itemCount for that product.
   *
   * @param productProduced An object representing the product that was produced
   * @param itemCount The number of that product that have been produced
   * @param creator The username of the employee who created the product that this record is for
   */
  ProductionRecord(Product productProduced, int itemCount, String creator) {
    productId = productProduced.getId();
    serialNumber = generateSerialNum(productProduced, itemCount);
    dateProduced = new Date();
    productionNumber = itemCount;
    this.creator = creator;
  }

  /**
   * This method is a getter for the productionNumber field.
   *
   * @return The productionNumber field
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * This method is a setter for the productionNumber field.
   *
   * @param productionNumber The new value for the productionNumber field
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * This method is a getter for the productId field.
   *
   * @return The productId field
   */
  int getProductId() {
    return productId;
  }

  /**
   * This method is a setter for the productId field.
   *
   * @param productId The new value for the productId field
   */
  public void setProductId(int productId) {
    this.productId = productId;
  }

  /**
   * This method is a getter for the serialNumber field.
   *
   * @return The serialNumber field
   */
  String getSerialNumber() {
    return serialNumber;
  }

  /**
   * This method is a setter for the serialNumber field.
   *
   * @param serialNumber The new value for the serialNumber field
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * This method is a getter for the dateProduced field.
   *
   * @return The dateProduced field
   */
  Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  /**
   * This method is a setter for the dateProduced field.
   *
   * @param dateProduced The new value for the dateProduced field
   */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * This methods returns a string object containing information about the productionNumber,
   * productId, serialNumber, and dateProduced of the ProductionRecord, in a cleanly displayable
   * format.
   *
   * @return String containing information about the productionNumber, productId, serialNumber, and
   *     dateProduced
   */
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productId
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced
        + " Username: "
        + creator;
  }

  /**
   * This method uses a Product and the item count for that Product to generate a serial number.
   *
   * @param product The product to generate the serial number for
   * @param itemCount The item count for that product
   * @return The serial number
   */
  private String generateSerialNum(Product product, int itemCount) {
    // Creating placeholder variables
    String manufacturer = product.getManufacturer();
    String itemTypeCode = product.getType().code;
    String itemCountString = "" + itemCount;

    // Add the proper number of leading zeros
    switch (itemCountString.length()) {
      case 1:
        itemCountString = "0000" + itemCountString;
        break;
      case 2:
        itemCountString = "000" + itemCountString;
        break;
      case 3:
        itemCountString = "00" + itemCountString;
        break;
      case 4:
        itemCountString = "0" + itemCountString;
        break;
      default:
    }
    // Returning the serial number
    return manufacturer.substring(0, 3) + itemTypeCode + itemCountString;
  }
}
