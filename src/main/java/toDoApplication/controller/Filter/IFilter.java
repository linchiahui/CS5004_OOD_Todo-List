package toDoApplication.controller.Filter;

import toDoApplication.model.*;
import java.util.List;

/**
 * All classes implementing this interface will have a method that selects to-dos according to a
 * specific criteria.
 */
public interface IFilter {

  /**
   * This method selects to-dos based on a specified criteria.
   *
   * @param toDoList the complete to-do list
   * @param condition the condition
   */
  void filter(List<ToDo> toDoList, String condition);
}
