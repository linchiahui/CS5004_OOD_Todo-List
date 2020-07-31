package toDoApplication.view;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a CommandLineParser.
 */

public class CommandLineParser implements ICommandLineParser{
  /** The CommandLine instance. */
  protected CommandLine cmd;

  /** The current Options. */
  protected Options options;

  /** The token currently being processed. */
  protected String currentToken;

  /** The last option parsed. */
  protected Option currentOption;

  /** The required options and groups expected to be found when parsing the command line. */
  protected List<String> expectedOpts;

  /**
   * Empty constructor.
   */
  public CommandLineParser(){

  }

  /**
   * Parse the arguments according to the specified Options.
   *
   * @param options   the specified Options
   * @param arguments the command line arguments
   * @return A CommandLine object, i.e. the list of atomic option and value tokens
   * @throws ParserException if there are any problems encountered while parsing the command line
   *                         tokens.
   */
  @Override
  public CommandLine parse(Options options, String[] arguments) throws ParserException {
    this.options = options;
    this.currentOption = null;
    this.expectedOpts = new ArrayList(options.getRequiredOptions());

    this.cmd = new CommandLine();

    if (arguments == null) throw new ParserException("ERROR: No arguments were entered.");
    for (String argument : arguments) {
        this.handleToken(argument);
    }
    // check the arguments of the last option
    this.checkRequiredArgs();
    // check if all required Options are fulfilled
    this.checkRequiredOptions();
    return this.cmd;

  }

  /**
   * This method handles and processes all command line arguments as tokens.
   *
   * @param token the command line token to handle
   * @throws ParserException if the cmd line token is judged to be invalid
   */
  private void handleToken(String token) throws ParserException {
    this.currentToken = token;
    if (this.currentOption != null && this.currentOption.acceptsArg() && this.isArgument(token)) {
      this.currentOption.addValueForProcessing(token.trim());
    } else if (this.currentToken.startsWith("--")) {
      this.handleLongOption(this.currentToken);
    } else {
      throw new ParserException("ERROR: Unrecognized option: " + this.currentToken);
    }
    if (!token.startsWith("--")) {
      this.currentOption = null;
    }
    if (this.currentOption != null && !this.currentOption.acceptsArg())
    {
      this.currentOption = null;
    }
  }

  /**
   * This methods processes command line arguments starting with "--"
   * i.e. all possible arguments, excluding the paths following them.
   * e.g. --csv-file, --display
   *
   * @param token the command line token to process
   */
  private void handleLongOption(String token) throws ParserException {
    List<String> matchingOpts = this.options.getMatchingOptions(token);
    if (matchingOpts.isEmpty()) {
      throw new ParserException("ERROR: Unrecognized option: " + token);
    } else {
      this.handleOption(this.options.getOption(matchingOpts.get(0)));
    }
  }

  /**
   * This method handles and processes an Option object.
   *
   * @param option the Option
   * @throws ParserException if the Option is judged to be invalid.
   */
  private void handleOption(Option option) throws ParserException {
    this.checkRequiredArgs();
    this.updateRequiredOptions(option);
    this.cmd.addOption(option);
    if (option.hasArg()) {
      this.currentOption = option;
    } else {
      this.currentOption = null;
    }
  }

  /**
   * Removes the Option from the list of expected Options.
   *
   * @param option
   */
  private void updateRequiredOptions(Option option) {
    if (option.isRequired()) {
      this.expectedOpts.remove(option.getLongOpt());
    }
  }

  /**
   * Helper method to check whether all the required Options are present.
   *
   * @throws ParserException if any of the required Options are missing.
   */
  private void checkRequiredOptions() throws ParserException {
    // If there are required options that have not been processed
    if (!this.expectedOpts.isEmpty()) {
      throw new ParserException(this.expectedOpts + " Missing Option Exception");
    }
  }

  /**
   * Checks whether the current Option has received its required arguments.
   *
   * @throws ParserException if the current Option has not received all of its required arguments.
   */
  private void checkRequiredArgs() throws ParserException {
    if (this.currentOption != null && this.currentOption.requiresArg()){
      throw new ParserException(this.currentOption.getLongOpt() + " Missing Argument Exception");
    }
  }

  /**
   * Returns true is the token is a valid argument.
   * In this case, an argument is judged to be valid as long as it is not one of the
   * long-names of Options.
   *
   * @param token the argument to be evaluated
   * @return true if we have a valid argument, false otherwise.
   */
  private boolean isArgument(String token)
  {
    return !isLongOption(token);
  }

  /**
   * Checks if if the token looks like the long-name of an Option.
   *
   * @param token the token to be evaluated
   */
  private boolean isLongOption(String token) {
    if (!token.startsWith("-") || token.length() == 1) {
      return false;
    }

    if (!this.options.getMatchingOptions(token).isEmpty())
    {
      // long or partial long options (--L, -L, --L=V, -L=V, --l, --l=V)
      return true;
    }
    return false;
  }
}