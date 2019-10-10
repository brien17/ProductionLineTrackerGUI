/*
Cameron Brien
9/25/2019
This is an enumerator to hold my data types and the type code associated with them.
 */

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
