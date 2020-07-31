package toDoApplication.controller.Filter;

import toDoApplication.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class selects all To-dos that are incomplete.
 */
public class FilterIncomplete implements IFilter {

  /**
   * All the to-dos that are incomplete.
   */
  private List<ToDo> result = new ArrayList<ToDo>();
  private final static String DEFAULT="true";

  /**
   * Constructs a new FilterIncomplete object.
   *
   * @param toDoList the list of to-dos
   */
  public FilterIncomplete(List<ToDo> toDoList) {
    this.filter(toDoList, DEFAULT);
  }

  /**
   * Gets the list of incomplete to-dos.
   *
   * @return the list of incomplete to-dos.
   */
  public List<ToDo> getResult() {
    return this.result;
  }

  @Override
  public void filter(List<ToDo> toDoList, String condition) {
    for (ToDo item : toDoList) {
      if (!item.isCompleted()) {
        this.result.add(item);
      }
    }
  }
}