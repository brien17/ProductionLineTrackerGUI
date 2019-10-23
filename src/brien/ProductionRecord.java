package brien;

import java.util.Date;

class ProductionRecord {
  private int productionNumber;
  private int productId;
  private String serialNumber;
  private Date dateProduced;

  ProductionRecord(int productId) {
    this.productId = productId;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  public ProductionRecord(
      int productionNumber, int productId, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productId = productId;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());
  }

  public ProductionRecord(Product productProduced, int itemCount) {
    productionNumber = 0;
    productId = productProduced.getId();
    serialNumber = generateSerialNum(productProduced, itemCount);
    dateProduced = new Date();
  }

  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productId
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }

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
