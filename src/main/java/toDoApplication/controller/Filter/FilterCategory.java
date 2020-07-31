package toDoApplication.controller.Filter;

import toDoApplication.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class selects all to-dos that are of a specified category.
 */
public class FilterCategory implements IFilter{

  /**
   * The list of all to-dos of the specified category.
   */
  private List<ToDo> result = new ArrayList<>();

  /**
   * Constructs a new FilterCategory object.
   *
   * @param toDoList the to-do list to filter
   * @param condition the Category
   */
  public FilterCategory(List<ToDo> toDoList, String condition) {
    this.filter(toDoList, condition);
  }

  /**
   * Gets the list of to-dos with the specified Category
   *
   * @return the list of to-dos with the specified Category
   */
  public List<ToDo> getResult() {
    return this.result;
  }

  @Override
  public void filter(List<ToDo> toDoList, String condition) {
    for (ToDo item : toDoList) {
      if (item.getCategory() != null && item.getCategory().equals(condition)) {
        result.add(item);
      }
    }
  }

}