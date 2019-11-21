package brien;

/**
 * Screen.java - This class is used to represent the screen that a MoviePlayer will have. This class
 * implements the ScreenSpec interface.
 *
 * @author Cameron Brien
 */
public class Screen implements ScreenSpec {
  // fields
  private String resolution;
  private int refreshRate;
  private int responseTime;

  // methods
  // constructor

  /**
   * This is a constructor for the screen class and accepts arguments for the resolution,
   * refreshRate, and responseTime fields.
   *
   * @param resolution the value for the resolution field
   * @param refreshRate the value for the refreshRate field
   * @param responseTime the value for the responseTime field
   */
  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * This is a getter method for the resolution field.
   *
   * @return the string in the resolution field
   */
  public String getResolution() {
    return resolution;
  }

  /**
   * This is a getter method for the refreshRate field.
   *
   * @return the int in the refresh rate field
   */
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * This is a getter method for the responseTime field.
   *
   * @return the int in the responseTime field
   */
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * This method generates a string that contains information about the movie player.
   *
   * @return a string containing information about the movie player
   */
  public String toString() {
    return "Screen:"
        + "\nResolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshRate
        + "\nResponse time: "
        + responseTime;
  }
}
