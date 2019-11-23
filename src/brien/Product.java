package brien;

/**
 * Product.java - This abstract class is used to define methods that will be used by classes that
 * inherit from it. It is designed to have all of the basic information and functionality that all
 * products will need.
 *
 * @author Cameron Brien
 */
public abstract class Product implements Item {

  /** This field holds a unique identifier for each product. */
  private int id;

  /** This field holds the name of the product. */
  private String name;

  /** This field holds the manufacturer of the product. */
  private String manufacturer;

  /** This field holds the item type of the product. */
  private ItemType type;

  /**
   * This is a constructor for the Product class. This constructor takes in arguments for the name,
   * manufacturer, and type fields.
   *
   * @param name The name of the Product
   * @param manufacturer The manufacturer of the Product
   * @param type The type of the Product
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * This is a constructor for the Product class. This constructor takes in arguments for the id,
   * name, manufacturer, and type fields.
   *
   * @param id The id code for the Product
   * @param name The name of the Product
   * @param manufacturer The manufacturer of the Product
   * @param type The type of the Product
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * This method is a getter for the id field.
   *
   * @return The id field
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * This method is a setter for the name field.
   *
   * @param name The value to set the name field to
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method is a getter for the name field.
   *
   * @return The name field
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * This method is a setter for the manufacturer field.
   *
   * @param manufacturer The value to set the manufacturer field to
   */
  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * This method is a getter for the manufacturer field.
   *
   * @return The name field
   */
  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * This method is a setter for the type field.
   *
   * @param type The value to set the type field to
   */
  public void setType(ItemType type) {
    this.type = type;
  }

  /**
   * This method is a getter for the type field.
   *
   * @return The type field
   */
  public ItemType getType() {
    return type;
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
