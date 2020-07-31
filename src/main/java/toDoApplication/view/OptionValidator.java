package toDoApplication.view;

/**
 * Validates an Option string.
 */
public class OptionValidator {

  /**
   * Empty constructor for testing purposes.
   */
  public OptionValidator(){

  }

  /**
   * Validates whether opt is a permissible Option
   *
   * @param opt The option string to validate, might be null
   * @throws IllegalArgumentException if the option string is invalid.
   */
  public static void validateOption(String opt) throws IllegalArgumentException {
    // If opt is NULL, do not check further
    if (opt == null) {
      return;
    }

    // Handle the multi-character opt
    for (char ch : opt.toCharArray()) {
      if (!isValidChar(ch)) {
        throw new IllegalArgumentException("The option '" + opt + "' contains an illegal "
            + "character : '" + ch + "'");
      }
    }
  }

  /**
   * Returns whether the specified character is valid.
   * Here, we are assuming that the name of Options only contains letters and dashes; therefore,
   * a string is judged to be invalid if it contains any characters other than letters or dashes.
   *
   * @param c the option name to validate
   * @return true if char c is a letter, false otherwise.
   */
  private static boolean isValidChar(char c) {
    return Character.isJavaIdentifierPart(c) || c == '-';
  }
}
