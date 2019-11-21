package brien;

/**
 * MoviePlayer.java - This class is used to represent movie players that can be produced by the
 * factory. This class is a Product and implements the MultimediaControl interface.
 *
 * @author Cameron Brien
 */
public class MoviePlayer extends Product implements MultimediaControl {
  // fields
  private MonitorType monitorType;
  private Screen screen;

  // methods

  /**
   * This is a constructor for the MoviePlayer class. This constructor accepts arguments for the
   * name, manufacturer, screen, and monitorType fields.
   *
   * @param name The name of the MoviePlayer
   * @param manufacturer The manufacturer of the MoviePlayer
   * @param screen A screen class with details on the screen the MoviePlayer has
   * @param monitorType The type of screen that the MoviePlayer has
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.valueOf("Visual"));
    this.monitorType = monitorType;
    this.screen = screen;
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * This method generates a string that contains information about the MoviePlayer.
   *
   * @return a string containing information about the MoviePlayer
   */
  public String toString() {
    return super.toString() + "\nScreen: " + screen.toString() + "\nMonitor Type: " + monitorType;
  }
}
