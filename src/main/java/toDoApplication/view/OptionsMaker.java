package toDoApplication.view;

public class OptionsMaker {
  public static void makeOptions(Options options) {
    options.addOption(new Option.Builder()
        .longOpt("csv-file")
        .hasArg()
        .required(true)
        .argName("path")
        .desc("accept the name of the csv file to process ")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("add-todo")
        .desc("Add a new todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("todo-text")
        .hasArg()
        .argName("description")
        .desc("A description of the todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("completed")
        .desc("Sets the completed status of a new todo to true.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("due")
        .hasArg()
        .argName("date")
        .desc("the due date of a new todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("priority")
        .hasArg()
        .argName("Priority")
        .desc("Sets the priority of a new todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("category")
        .hasArg()
        .argName("categoryName")
        .desc("Sets the category of a new todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("complete-todo")
        .hasArgs()
        .argName("id")
        .desc("Mark the Todo with the provided ID as complete.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("display")
        .desc("Display all todo.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("show-incomplete")
        .desc("only incomplete todo should be displayed.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("show-category")
        .hasArg()
        .argName("category>")
        .desc("only todos with the given category should be displayed.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("sort-by-date")
        .desc("sort the list of todo by date order.")
        .build());

    options.addOption(new Option.Builder()
        .longOpt("sort-by-priority")
        .desc("sort the list of todo by priority.")
        .build());
  }
}