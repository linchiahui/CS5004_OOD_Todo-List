package toDoApplication.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents an Option object.
 */
public class Option {

  /**
   * Constant indicating that the given Option does not require arguments following it
   */
  public static final int UNINITIALIZED = -1;

  /**
   * Constant indicating that the given Option can have arguments following it
   */
  public static final int UNLIMITED_VALUES = Integer.MAX_VALUE;

  /** The long String representation of the Option */
  private String longOpt;

  /** The name of the argument for this Option */
  private String argName;

  /** Description of the Option */
  private String description;

  /** Specifies whether this option is required to run the program*/
  private boolean required;

  /** The number of arguments this Option can have */
  private int numberOfArgs;

  /** The list of argument values **/
  private List<String> values = new ArrayList<>();


  /**
   * Constructs a new Option object. Each of its instance variables is built by a Builder.
   *
   * @param builder The Builder object to built the Option's instance variables
   */
  public Option(Builder builder) {
    this.argName = builder.argName;
    this.description = builder.description;
    this.longOpt = builder.longOpt;
    this.numberOfArgs = builder.numberOfArgs;
    this.required = builder.required;
  }

  /**
   * Gets long opt of the Option.
   *
   * @return the long opt of the Option.
   */
  public String getLongOpt() {
    return this.longOpt;
  }

  /**
   * Gets arg name of the Option.
   *
   * @return the arg name of the Option.
   */
  public String getArgName() {
    return this.argName;
  }

  /**
   * Whether this Option requires at least one argument
   *
   * @return true if this Option requires at least one argument, false if it doesn't require any
   * arguments.
   */
  public boolean hasArg()
  {
    return this.numberOfArgs > 0;
  }

  /**
   * Whether this Option can take more than one arguments.
   *
   * @return true if this Option can take more than one argument, false otherwise.
   */
  public boolean hasArgs()
  {
    return this.numberOfArgs == UNLIMITED_VALUES;
  }

  /**
   * Gets description of the Option.
   *
   * @return the description of the Option.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Whether this Option is required to run the program.
   *
   * @return true if the Option is required to run the program, false otherwise.
   */
  public boolean isRequired() {
    return this.required;
  }

  /**
   * Gets the number of args following this Option.
   *
   * @return the number of args following this Option.
   */
  public int getNumberOfArgs() {
    return this.numberOfArgs;
  }

  /**
   * Adds a specified argument value to this Option.
   *
   * @param value is a/the argument value for this Option
   * @throws ParserException if arguments were given to Options that do not require arguments
   */
  public void addValueForProcessing(String value) throws ParserException {
    if (this.numberOfArgs == UNINITIALIZED) {
      throw new ParserException("ERROR: arguments were provided for incorrect command(s)");
    }
    this.processValue(value);
  }

  /**
   * Processes the argument value.  If this Option has a argument value separator, the argument
   * value will have to be parsed into individual tokens.  When n-1 tokens have been processed and
   * there are more value separators in the argument value, parsing is ceased and the remaining
   * characters are added as a single token.
   *
   * @param value The argument value to be processed.
   * @throws ParserException if the Option cannot accept more arguments.
   */
  public void processValue(String value) throws ParserException {
    if (!this.acceptsArg()) {
      throw new ParserException("Incorrect number of argument value(s) provided to Option(s)");
    }
    // Stores argument value into the Option's args list.
    this.values.add(value);
  }

  /**
   * Whether the Option can accept more arguments.
   *
   * @return true if the Option can accept more arguments, false if the maximum number of arguments
   * is reached.
   */
  protected boolean acceptsArg() {
    return (this.hasArg()) && (this.numberOfArgs <= 0 || this.values.size() < this.numberOfArgs);
  }

  /**
   * Whether the Option requires more arguments to be valid.
   *
   * @return true if the Option needs more arguments to become valid, false otherwise.
   */
  protected boolean requiresArg()
  {
    return this.acceptsArg();
  }

  /**
   * Gets the arguments values of a given Option.
   *
   * @return the arguments of this Option as a List, or null if this Option requires no arguments.
   */
  public List<String> getValuesList(){
    if(this.values.isEmpty()) return null;
    return this.values;
  }

  /**
   * Returns whether this Option has any arguments at all.
   *
   * @return true if this Option has argument(s), false otherwise.
   */
  private boolean hasNoValues()
  {
    return this.values.isEmpty();
  }

  /**
   * Returns the argument following this Option
   *
   * @return the argument following the Option, or null if there is no value.
   */
  public String getValue()
  {
    return this.hasNoValues() ? null : this.values.get(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Option)) {
      return false;
    }
    Option option = (Option) o;
    return isRequired() == option.isRequired() &&
        getNumberOfArgs() == option.getNumberOfArgs() &&
        Objects.equals(getLongOpt(), option.getLongOpt()) &&
        Objects.equals(getArgName(), option.getArgName()) &&
        Objects.equals(getDescription(), option.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLongOpt(), getArgName(), getDescription(), isRequired(),
        getNumberOfArgs());
  }
  @Override
  public String toString() {
    return "Option{" +
        "longOpt='" + longOpt + '\'' +
        ", argName='" + argName + '\'' +
        ", description='" + description + '\'' +
        ", required=" + required +
        ", numberOfArgs=" + numberOfArgs +
        '}';
  }

  /**
   * A nested builder class that creates Option instances using builder pattern.
   */
  public static class Builder {
    /** Description of the Option */
    private String description;

    /** The long name representation of the Option */
    private String longOpt;

    /** The name of the argument for this Option */
    private String argName;

    /** Specifies whether this option is required to be present */
    private boolean required;

    /** The number of argument values this option can have; assuming it can have none at the
     * beginning.*/
    private int numberOfArgs = UNINITIALIZED;

    /**
     * Constructs a new Builder object with the minimum required parameters for an Option instance.
     * Overloaded constructor: can instantiate a Builder either by no parameters, or the long name
     * of the Option.
     *
     * @throws IllegalArgumentException if there are any non valid Option characters
     */
    public Builder() {
      this(null);
    }

    /**
     * Instantiates a new Builder object.
     * Overloaded constructor: can instantiate a Builder either by no parameters, or the long name
     * of the Option.
     *
     * @param longOpt the long name of the Option
     * @throws IllegalArgumentException if the long name is judged to be invalid
     */
    public Builder(String longOpt) throws IllegalArgumentException {
      OptionValidator.validateOption(longOpt);
      this.longOpt = longOpt;
    }

    /**
     * Sets the display name for the argument value.
     *
     * @param argName the display name for the argument value.
     * @return this builder, to allow method chaining
     */
    public Builder argName(String argName) {
      this.argName = argName;
      return this;
    }

    /**
     * Sets the description for this Option.
     *
     * @param description the description of the Option.
     * @return this builder, to allow method chaining
     */
    public Builder desc(String description) {
      this.description = description;
      return this;
    }

    /**
     * Sets the long name of the Option.
     *
     * @param longOpt the long name of the Option
     * @return this builder, to allow method chaining
     */
    public Builder longOpt(final String longOpt) {
      this.longOpt = longOpt;
      return this;
    }

    /**
     * Overloaded method; if the user calls required() with no parameters, the corresponding
     * required instance is set to true.
     *
     * @return this builder, to allow method chaining
     */
    public Builder required()
    {
      return this.required(true);
    }

    /**
     * Overloaded method; if the user calls required() with true or false specified, the the
     * corresponding required instance variable is updated.
     *
     * @param required specifies whether the Option is mandatory
     * @return this builder, to allow method chaining
     */
    public Builder required(boolean required) {
      this.required = required;
      return this;
    }

    /**
     * Indicates that the Option will require an argument.
     * Overloaded method; if the user calls hasArgs() with no parameters, the corresponding
     * "hasArg" instance is set to true.
     *
     * @return this builder, to allow method chaining
     */
    public Builder hasArg()
    {
      return this.hasArg(true);
    }

    /**
     * Indicates if the Option has an argument or not.
     * Overloaded method; if the user calls hasArgs() with true or false specified, the
     * corresponding "hasArg" instance is set to accordingly.
     *
     * @param hasArg specifies whether the Option takes an argument or not
     * @return this builder, to allow method chaining
     */
    public Builder hasArg(boolean hasArg)
    {
      // Set to UNINITIALIZED when no arg is specified to be compatible with OptionBuilder
      this.numberOfArgs = hasArg ? 1 : Option.UNINITIALIZED;
      return this;
    }

    /**
     * Indicates that the Option can have unlimited argument values.
     *
     * @return this builder, to allow method chaining
     */
    public Builder hasArgs() {
      this.numberOfArgs = Option.UNLIMITED_VALUES;
      return this;
    }

    /**
     * Constructs an Option with the values declared by this Builder.
     *
     * @return the new Option object.
     * @throws IllegalArgumentException if neither opt or longOpt has been set
     */
    public Option build() {
      if (this.longOpt == null) {
        throw new IllegalArgumentException("Either opt or longOpt must be specified");
      }
      return new Option(this);
    }
  }
}

