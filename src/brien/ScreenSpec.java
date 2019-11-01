package brien;

/**
 * ScreenSpec.java - This interface is used to ensure that all of the classes that implement it have
 * the required methods.
 *
 * @author Cameron Brien
 */
interface ScreenSpec {
  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}
