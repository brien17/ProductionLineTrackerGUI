package brien17;

public class Widget extends Product implements Item {

  public Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  public Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
