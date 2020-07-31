package toDoApplication.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import toDoApplication.controller.Filter.FilterCategory;
import toDoApplication.controller.Filter.FilterIncomplete;
import toDoApplication.model.ToDo;
import toDoApplication.model.ToDo.ToDoBuilder;
import toDoApplication.view.CommandLine;

/**
 * This class refers to the command line arguments and performs actions accordingly (e.g. displaying
 * all to-dos, add a new to-do)
 */
public class ActionMaker {

  private static String desc;
  private static String completed = "false";
  private static String date = "?";
  private static String priority = "3";
  private static String category = "?";

  /**
   * Performs actions based on command line inputs.
   *
   * @param cmd the command line inputs
   * @param toDoList the to-do list
   * @param fileName Name of the destination file
   * @throws ParseException if to-do cannot be parsed to the correct format
   */
  public static void makeActions(CommandLine cmd, List<ToDo> toDoList, String fileName)
      throws ParseException {
    // Add new to-do
    if (cmd.hasOption("add-todo")) {
      desc = cmd.getOptionValue("todo-text");
      if (cmd.hasOption("completed")) {
        completed = "true";
      }
      if (cmd.hasOption("due")) {
        date = cmd.getOptionValue("due");
      }
      if (cmd.hasOption("priority")) {
        priority = cmd.getOptionValue("priority");
      }
      if (cmd.hasOption("category")) {
        category = cmd.getOptionValue("category");
      }
      ToDo newToDo = new ToDoBuilder(desc).addStatus(completed).
          addDueDate(date).changePriority(priority).addCategory(category).build();

      if(!toDoList.contains(newToDo)){
        toDoList.add(newToDo);
      }

      new UpdateFile(fileName, toDoList);
    }
    // Complete an existing to-do
    if (cmd.hasOption("complete-todo")) {
      for (String singleID : cmd.getOptionValues("complete-todo")) {
        int id = Integer.parseInt(singleID);
        new UpdateStatus(toDoList, id);
      }
    }
    // Display all to-do
    if (cmd.hasOption("display")) {
      if (cmd.hasOption("show-incomplete")) {
        toDoList = new FilterIncomplete(toDoList).getResult();
      }
      if (cmd.hasOption("show-category")) {
        toDoList = new FilterCategory(toDoList, cmd.getOptionValue("show-category")).getResult();
      }
      if (cmd.hasOption("sort-by-date")) {
        Collections.sort(toDoList, ToDo.DateComparator);
      } else if (cmd.hasOption("sort-by-priority")) {
        Collections.sort(toDoList, ToDo.PriorityComparator);
      }
      for (int i = 0; i < toDoList.size(); i++) {
        String id = String.valueOf(i + 1);
        System.out.println(id + ": " + toDoList.get(i));
      }
    }
  }
}