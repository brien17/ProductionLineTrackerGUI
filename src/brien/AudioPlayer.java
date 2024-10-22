package brien;

/**
 * AudioPlayer.java - This class is used to represent audio players that can be produced by the
 * factory. This class is a Product and implements the MultimediaControl interface.
 *
 * @author Cameron Brien
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /** This field is used to hold the types of audio file the audio player can play. */
  private String supportedAudioFormats;

  /** This field holds the playlist format that is used by the audio player. */
  private String supportedPlaylistFormats;

  /**
   * This is a constructor for the AudioPlayer class. This constructor accepts arguments for the
   * name, manufacturer, SupportedAudioFormats and supportedPlaylistFormats fields.
   *
   * @param name The name of the AudioPlayer
   * @param manufacturer The manufacturer of the AudioPlayer
   * @param supportedAudioFormats The audio specification for the AudioPlayer
   * @param supportedPlaylistFormats The media type it is able to play
   */
  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.valueOf("Audio"));
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void play() {
    System.out.println("Playing");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * This a stub method that displays a message to the console, it will have additional
   * functionality in later versions.
   */
  public void next() {
    System.out.println("Next");
  }

  /**
   * This method generates a string that contains information about the AudioPlayer.
   *
   * @return A string containing information about the AudioPlayer
   */
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }
}
