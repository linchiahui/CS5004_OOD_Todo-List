package toDoApplication.view;

/**
 * A class that implements the ICommandLineParser interface can parse a String array according to
 * the Options specified and return a CommandLine object.
 */
public interface ICommandLineParser {
  /**
   * Parse the arguments according to the specified Options.
   *
   * @param options the specified Options
   * @param arguments the command line arguments
   * @return the list of atomic Option and value tokens
   * @throws ParserException if there are any problems encountered while parsing the command line tokens.
   */
  CommandLine parse(Options options, String[] arguments) throws ParserException;
}
