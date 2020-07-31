
package toDoApplication.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import toDoApplication.model.ColumnConstants;
import toDoApplication.model.ToDo;

/**
 * This class is responsible for file updating.
 */
public class UpdateFile {

  private String fileName;
  private List<ToDo> toDoList;

  /**
   * This method updates the source file according to the list of to-dos.
   *
   * @param fileName the file to be updated
   * @param toDoList the to-do list
   */
  public UpdateFile(String fileName, List<ToDo> toDoList) {
    updateSourceFile(fileName, toDoList);
  }

  /**
   * Helper method to update the file when a new to-do is added
   *
   * @param fileName the file to be updated
   * @param toDoList the to-do list
   */
  private void updateSourceFile(String fileName, List<ToDo> toDoList) {
    try (BufferedWriter update = new BufferedWriter(
        new FileWriter(fileName))) {
      update.write(ColumnConstants.COLUMN_LINE+System.lineSeparator());
      for (int i = 0; i < toDoList.size(); i++) {
        String id= "\""+ String.valueOf(i+1)+"\"";
        String todo = toDoList.get(i).toString();
        update.write(id+","+todo);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}