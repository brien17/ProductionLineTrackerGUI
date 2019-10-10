package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  // fields
  private String audioSpecification;
  private String mediaType;

  // methods

  // constructor
  public AudioPlayer(
      String name, String manufacturer, String audioSpecification, String mediaType) {
    super(name, manufacturer, "AUDIO");
    this.audioSpecification = audioSpecification;
    this.mediaType = mediaType;
  }

  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }

  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + audioSpecification
        + "\nSupported Playlist Formats: "
        + mediaType;
  }
}
