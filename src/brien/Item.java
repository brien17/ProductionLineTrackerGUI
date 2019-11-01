package brien;

/**
 * Item.java - This interface is used to ensure that all of the classes that implement it have the
 * required methods.
 *
 * @author Cameron Brien
 */
interface Item {
  int getId();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();
}
