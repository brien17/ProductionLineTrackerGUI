package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  // fields
  String audioSpecification;
  String mediaType;

  // methods

  // constructor
  public AudioPlayer(String name, String manufacturer, String audioSpecification) {
    super(name, manufacturer, "AU");
    this.audioSpecification = audioSpecification;
  }

  public void play() {
    System.out.println("play");
  }

  public void stop() {
    System.out.println("stop");
  }

  public void previous() {
    System.out.println("previous");
  }

  public void next() {
    System.out.println("next");
  }

  public String toString() {
    return super.toString() + "\nAudio Spec: " + audioSpecification + "\nMedia Type: " + mediaType;
  }
}
