package toDoApplication.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a CommandLine object.
 */
public class CommandLine {

  /** The processed Options */
  protected final List<Option> options = new ArrayList<>();

  /**
   * Empty constructor.
   */
  protected CommandLine() {
  };

  /**
   * Checks whether an Option has been set
   *
   * @param opt Short name of the option
   * @return true if set, false if not
   */
  public boolean hasOption(String opt)
  {
    return this.options.contains(this.resolveOption(opt));
  }

  /**
   * Retrieve the first argument, if any, of this Option.
   *
   * @param opt the name of the Option
   * @return Value of the argument if option is set, and has an argument, otherwise null.
   */
  public String getOptionValue(String opt) {
    String[] values = this.getOptionValues(opt);
    return (values == null) ? null : values[0];
  }

  /**
   * Adds an Option to the CommandLine object. The values of the Options are stored.
   *
   * @param opt the processed Option
   */
  protected void addOption(Option opt) {
    options.add(opt);
  }

  /**
   * Retrieves the array of values, if any, of an option.
   *
   * @param opt string name of the option
   * @return Values of the argument if option is set, and has an argument,
   * otherwise null.
   */
  public String[] getOptionValues(String opt) {
    List<String> values = new ArrayList<>();
    for (Option option : options) {
      if (opt.equals(option.getLongOpt())) {
        values.addAll(option.getValuesList());
        break;
      }
    }
    return values.isEmpty() ? null : values.toArray(new String[values.size()]);
  }


  /**
   * Retrieves the Option object given the long or short Option name  as a String
   *
   * @param opt short or long name of the option
   * @return Canonicalized option
   */
  private Option resolveOption(String opt) {
    opt = Utility.stripLeadingHyphens(opt);
    for (Option option : this.options) {
      if (opt.equals(Utility.stripLeadingHyphens(option.getLongOpt()))) {
        return option;
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommandLine)) {
      return false;
    }
    CommandLine that = (CommandLine) o;
    return options.equals(that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(options);
  }

  public String toString() {
    StringBuilder buf = new StringBuilder();

    buf.append("[ CommandLine: [ options: ");
    buf.append(options.toString());
    buf.append(" ] ]");

    return buf.toString();
  }
}
