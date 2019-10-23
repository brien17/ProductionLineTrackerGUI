package brien;

public class Screen implements ScreenSpec {
  // fields
  private String resolution;
  private int refreshRate;
  private int responseTime;

  // methods
  // constructor
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public int getRefreshRate() {
    return refreshRate;
  }

  public void setRefreshRate(int refreshRate) {
    this.refreshRate = refreshRate;
  }

  public int getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(int responseTime) {
    this.responseTime = responseTime;
  }

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
