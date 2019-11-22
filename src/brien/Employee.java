package brien;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
  /** This is the employee's full name with a space between their first and last name. */
  private StringBuilder name;
  /** This is the username created from the employee's full name. */
  private String username;

  private String password;
  private String email;

  /**
   * This is the constructor for the Employee class, it takes in the full name of the user and a
   * password that they enter.
   *
   * @param name The full name entered by the user
   * @param password The password entered by the user
   */
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

  /**
   * This method takes in the full name entered by the user and uses it to generate an email for
   * that user.
   *
   * @param name The full name entered by the user
   */
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

  /**
   * This method takes in the StringBuilder that the user entered for their name and creates a
   * username consisting of their first initial followed by their last name, all in lower case.
   *
   * @param name The full name the user entered
   */
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

  /**
   * This method checks that the password the user entered contains an uppercase character, a
   * lowercase character, and a symbol.
   *
   * @param password The password the user entered
   * @return Boolean representing if that password is acceptable or not
   */
  private boolean isValidPassword(String password) {
    final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(password);

    // If the password has a lowercase letter, uppercase letter, and symbol
    return matcher.find();
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
    return matcher.find();
  }

    public StringBuilder getName() {
        return name;
    }

    /**
   * This method is a getter for the password field.
   *
   * @return The password
   */
  public String getPassword() {
    return password;
  }

  /**
   * This is a getter for the username field.
   *
   * @return The username
   */
  String getUsername() {
    return username;
  }

  /**
   * This method generates a string that contains information about the Employee.
   *
   * @return A string containing information about the Employee
   */
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
