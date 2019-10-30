package brien;

/**
 * Widget.java - This is a temporary class used to make objects of the abstract class Product. This
 * class is a Product and implements the Item interface. This class will be removed in future
 * versions.
 *
 * @author Cameron Brien
 */
class Widget extends Product implements Item {

//  Widget(String name, String manufacturer, ItemType type) {
//    super(name, manufacturer, type);
//  }

  Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
