
package toDoApplication.controller;
import toDoApplication.model.*;
import java.util.List;

/**
 * Takes in a To-Do ID and updates the corresponding To-Do's status to completed.
 * Save the change in original file.
 */
public class UpdateStatus {

  private final static int ID_OFFSET = 1;

  public UpdateStatus(List<ToDo> toDoList, int ID) {
    toDoList.get(ID - ID_OFFSET).setCompleted();
  }
}