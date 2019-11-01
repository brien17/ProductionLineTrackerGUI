/*
Cameron Brien
9/25/2019
This is an enumerator to hold my data types and the type code associated with them.
 */

package brien;

/**
 * ItemType.java - This enumerator is used to store the possible values for the item type and their
 * associated code.
 *
 * @author Cameron Brien
 */
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
