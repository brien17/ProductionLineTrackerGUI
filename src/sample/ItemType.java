package sample;

public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  VisualMobile("VM"),
  AudioMobile("AM");

  final String code;

  ItemType(String code) {
    this.code = code;
  }
}
