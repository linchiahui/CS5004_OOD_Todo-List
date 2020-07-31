package toDoApplication.view;

/**
 * This class does basic checks on command line arguments, such as whether the minimum number of args
 * is met, two non-coexisting args were not entered at the same time, etc.
 */
public class ValidCmdChecker {
  /**
   * The maximum priority a to-do can have.
   */
  public static final int MAX_PRIORITY = 3;
  /**
   * The minimum priority a to-do can have.
   */
  public static final int MIN_PRIORITY = 1;

  /**
   * Empty constructor for testing purposes.
   */
  public ValidCmdChecker(){};

  public static void checkCmd(CommandLine cmd) throws ParserException {
    checkCompleted(cmd);
    checkConflict(cmd);
    checkPriority(cmd);
  }

  private static void checkConflict(CommandLine cmd) throws ParserException {
    if (cmd.hasOption("sort-by-date") && cmd.hasOption("sort-by-priority")) {
      throw new ParserException("You can't sort by date and priority");
    }
  }

  private static void checkCompleted(CommandLine cmd) throws ParserException {
    if (cmd.hasOption("add-todo")) {
      if (!cmd.hasOption("todo-text")) {
        throw new ParserException("If --add-todo, then --todo-text must also be provided.");
      }
    }
  }

  private static void checkPriority(CommandLine cmd) throws ParserException {
    if (cmd.hasOption("priority")) {
      Integer priority = Integer.parseInt(cmd.getOptionValue("priority"));
      if (priority < MIN_PRIORITY || priority > MAX_PRIORITY) {
        throw new ParserException("priority should be either 1, 2 or 3");
      }
    }
  }


}
