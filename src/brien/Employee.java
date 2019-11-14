package brien;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
  private StringBuilder name;
  private String username;
  private String password;
  private String email;

  Employee(String name, String password) {
    // Changing the String to a StringBuilder
    StringBuilder builderName = new StringBuilder(name);

    // Setting name
    this.name = builderName;

    // Checking for valid name
    if (checkName(builderName)) {
      // If the name is valid setting username and email
      setUsername(builderName);
      setEmail(builderName);
    } else {
      // If the name is not valid setting to default values
      username = "default";
      email = "user@oracleacademy.Test";
    }

    // Checking for valid password
    if (isValidPassword(password)) {
      // If password is valid setting it to password
      this.password = password;
    } else {
      // setting to default
      this.password = "pw";
    }
  }

  private void setEmail(StringBuilder name) {
    // Setting up regex to get first and last names
    final String regex = "[a-zA-Z]+";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);
    // Saving first and last name
    matcher.find();
    String firstName = matcher.group(0);
    matcher.find();
    String lastName = matcher.group(0);
    // Creating email
    email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
  }

  private void setUsername(StringBuilder name) {
    // Setting up regex to get first and last names
    final String regex = "[a-zA-Z]+";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);
    matcher.find();
    String firstName = matcher.group(0);
    matcher.find();
    String lastName = matcher.group(0);
    username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
  }

  private boolean isValidPassword(String password) {
    final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(password);

    // If the password has a lowercase letter, uppercase letter, and symbol return true else return
    // false
    if (matcher.find()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method is used inside the Employee constructor and checks to see if the name contains a
   * space. It will return true if there is a space and false if there is no space.
   *
   * @param name The name entered by the user
   * @return Whether the name has a space or not
   */
  private boolean checkName(StringBuilder name) {
    final String regex = "\\s";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);

    // If there is a space return true else return false
    if (matcher.find()) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    return "Employee Details\nName : "
        + name
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "\nInitial Password : "
        + password;
  }
}
