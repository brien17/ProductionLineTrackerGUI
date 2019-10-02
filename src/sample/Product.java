package sample;

public abstract class Product implements Item {
  // Fields
  int id;
  String name;
  String manufacturer;
  String type;


  // Methods
  public Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * This method is a getter for the id field.
   *
   * @return The id field
   */
  public int getId() {
    return id;
  }

  /**
   * This method is a setter for the name field.
   *
   * @param name The value to set the name field to
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method is a getter for the name field.
   *
   * @return The name field
   */
  public String getName() {
    return name;
  }

  /**
   * This method is a setter for the manufacturer field.
   *
   * @param manufacturer The value to set the manufacturer field to
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * This method is a getter for the manufacturer field.
   *
   * @return The name field
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * This methods returns a string object containing information about the name, type, and
   * manufacturer of the product, in a cleanly displayable format.
   *
   * @return String containing information about the name, type, and manufacturer of the product.
   */
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}
