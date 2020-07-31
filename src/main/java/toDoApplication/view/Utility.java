package toDoApplication.view;

/**
 * This Utility class processes the long-string name of Option objects.
 */
public class Utility {
  /**
   * Remove the hyphens from the beginning of str and return the new String.
   *
   * @param str The string from which the hyphens should be removed.
   * @return the new String.
   */
  private static final int ONE_HYPHEN = 1;
  private static final int TWO_HYPHENS = 2;

  /**
   * Empty constructor for testing purposes.
   */
  public Utility(){

  }

  public static String stripLeadingHyphens(String str) {
    if (str == null) {
      return null;
    }
    if (str.startsWith("--")) {
      return str.substring(TWO_HYPHENS, str.length());
    }
    else if (str.startsWith("-"))
    {
      return str.substring(ONE_HYPHEN, str.length());
    }
    return str;
  }
}

