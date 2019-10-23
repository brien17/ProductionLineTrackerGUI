package brien;

class Widget extends Product implements Item {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
