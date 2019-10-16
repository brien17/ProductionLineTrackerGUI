package brien17;

public class MoviePlayer extends Product implements MultimediaControl {
  // fields
  private MonitorType monitorType;
  private Screen screen;

  // methods

  // constructor
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.valueOf("Visual"));

    this.monitorType = monitorType;
    this.screen = screen;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  public String toString() {
    return super.toString() + "\nScreen: " + screen.toString() + "\nMonitor Type: " + monitorType;
  }
}
