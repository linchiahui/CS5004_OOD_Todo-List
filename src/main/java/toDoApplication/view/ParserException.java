package toDoApplication.view;

/**
 * Customized Exception class. Thrown the user enters one or more invalid command line argument(s).
 * Always thrown with a specific message about the error, followed by an instruction on how to input
 * the arguments properly.
 */
public class ParserException extends Exception {

  private static final String GEN_INSTRUCTION = ""
      + " Usage:\n"
      + "--csv-file <path/to/file> The CSV file containing the todos. This option is required.\n"
      + "--add-todo Add a new todo. If this option is provided, then --todo-text must also be provided.\n"
      + "--todo-text <description of todo> A description of the todo."
      + "--completed (Optional) Sets the completed status of a new todo to true.\n"
      + "--due <due date> (Optional> Sets the due date of a new todo.\n"
      + "--priority <1, 2, or 3> (Optional) Sets the priority of a new todo. The value can be 1, 2, or 3.\n"
      + "--category <a category name> (Optional) Sets the category of a new todo."
      + "--complete-todo <id> Mark the Todo with the provided ID as complete.\n"
      + "--display Display todos. If none of the following optional arguments are provided, display all todos\n"
      + "--show-incomplete (Optional) If --display is provided, only incomplete todos should be displayed.\n"
      + "--show-category <category> (Optional) If --display is provided, only todos with the given category is displayed.\n"
      + "--sort-by-date (Optional) If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by-priority.\n"
      + "--sort-by-priority (Optional) If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.\n"
      + System.lineSeparator()
      + "Examples:"
      + System.lineSeparator();

  private static final String DISPLAY_EG = ""
      + "If entered: --csv-file todos.csv --display --sort-by-priority\n"
      + "1: \"Finish HW9\",\"false\",\"03/22/2020\",\"1\",\"school\"\n"
      + "2: \"Study for finals\",\"false\",\"null\",\"2\",\"school\"\n"
      + "3: \"Mail passport\",\"true\",\"02/28/2020\",\"3\",\"null\"\n"
      + "4: \"Clean the house\",\"false\",\"03/22/2020\",\"3\",\"home\"\n"
      + "5: \"Buy yarn for blanket, stuffed toy\",\"true\",\"null\",\"3\",\"home\"\n";

  private static final String UPDATED_DISPLAY = ""
      + "If entered: --csv-file todos.csv --add-todo --todo-text drinking --priority 1\n"
      + "Run the program, and rerun with: --csv-file todos.csv --add-todo partying --priority 1 --due 03/01/2020 --complete-todo 6 --display --sort-by-date\n"
      + "1: \"Mail passport\",\"true\",\"02/28/2020\",\"3\",\"null\"\n"
      + "2: \"partying\",\"false\",\"03/01/2020\",\"1\",\"null\"\n"
      + "3: \"Finish HW9\",\"false\",\"03/22/2020\",\"1\",\"school\"\n"
      + "4: \"Clean the house\",\"false\",\"03/22/2020\",\"3\",\"home\"\n"
      + "5: \"Study for finals\",\"false\",\"null\",\"2\",\"school\"\n"
      + "6: \"Buy yarn for blanket, stuffed toy\",\"true\",\"null\",\"3\",\"home\"\n"
      + "7: \"drinking\",\"true\",\"null\",\"1\",\"null\"\n";

  private static final String INSTRUCTIONS = GEN_INSTRUCTION + DISPLAY_EG + System.lineSeparator() + UPDATED_DISPLAY;

  public ParserException(String message) {
    super(message + System.lineSeparator() +  System.lineSeparator() + INSTRUCTIONS);
  }
}
