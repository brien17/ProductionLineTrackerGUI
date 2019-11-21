package brien;

/**
 * Widget.java - This is a temporary class used to make objects of the abstract class Product. This
 * class is a subclass of Product and implements the Item interface. This class will be removed in
 * future versions.
 *
 * @author Cameron Brien
 */
class Widget extends Product implements Item {
  /**
   * This is a constructor for the Widget class. This constructor takes in arguments for the id,
   * name, manufacturer, and type fields and passes them to the super class constructor.
   *
   * @param id The id code for the Product
   * @param name The name of the Product
   * @param manufacturer The manufacturer of the Product
   * @param type The type of the Product
   */
  Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
