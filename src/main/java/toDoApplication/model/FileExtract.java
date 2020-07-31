package toDoApplication.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class processes the data from data file and save each Todos Objects into List. After data
 * processing, they can be used by other classes.
 */
public class FileExtract {

  private List<ToDo> toDoList = new ArrayList<ToDo>();
  private static final int KEY_LINE_INDEX = 0;
  private List<String> inputs;

  /**
   * Constructor for the FileExtract class. Take in the filename and process all the data in the
   * file. Extract all to-do inputs, create To-Do objects accordingly and save in a list.
   *
   * @param fileName The file needed to be open.
   * @throws IOException    if the file can't be open
   * @throws ParseException if value in the original file can't be parsed into needed format
   */
  public FileExtract(String fileName) throws IOException, ParseException {
    this.getSourceFile(fileName);
    Map<String, List<String>> organizedData = this.dataRetrieve(inputs);
    this.toDoList = dataProcessing(organizedData);
  }

  /**
   * Gets the list with all to-do objects.
   *
   * @return a list of all to-dos
   */
  public List<ToDo> getToDoList() {
    return toDoList;
  }

  /**
   * Pass in the organized data hash map and generate the needed todo objects list.
   * @param organizedData the data extracted from source file and processed into a hashmap
   * @return a list contains to do objects.
   * @throws ParseException if the value passed in can't be parsed into required format.
   */
  private List<ToDo> dataProcessing(Map<String, List<String>> organizedData)
      throws ParseException{
    int size = organizedData.get(ColumnConstants.ID).size();
    for (int i = 0; i < size; i++) {
      toDoList.add(generateNewToDo(organizedData, i));
    }
    return toDoList;
  }

  /**
   * Generate a To-Do object with the value in hashmap
   *
   * @param organizedData the hashmap contains input category as keys and a String list of data as value
   * @param id which index of the data in the Value list needs to be processed.
   * @return a To-Do object
   * @throws ParseException if the value passed in can't be parsed into required format.
   */

  private ToDo generateNewToDo(Map<String, List<String>> organizedData, int id)
      throws ParseException {
    String text = organizedData.get(ColumnConstants.TEXT).get(id);
    String status = organizedData.get(ColumnConstants.STATUS).get(id);
    String due = organizedData.get(ColumnConstants.DUE).get(id);
    String priority = organizedData.get(ColumnConstants.PRIORITY).get(id);
    String category = organizedData.get(ColumnConstants.CATEGORY).get(id);
    return new ToDo.ToDoBuilder(text).addStatus(status).
        addDueDate(due).changePriority(priority).addCategory(category).build();
  }


  /**
   * take the String List which contains lines of data from the source file. Split each line into
   * String list with tokens. Instantiate a hashmap which
   * use source file's column names, the first line in the String list as keys.
   * Iterate over the remaining lines in the String List, and put data into related key set.
   *
   * @param inputs List of String contains lines of data from the source file.
   * @return a hashmap with column name as Keys and list of each row's related data as value.
   */
  private Map<String, List<String>> dataRetrieve(List<String> inputs) {
    Map<String, List<String>> inputCategory = new HashMap<>();
    List<String> keys = splitIntoTokens(inputs.get(KEY_LINE_INDEX));
    for (String key : keys) {
      inputCategory.put(key, new ArrayList<String>());
    }
    for (int i = 1; i < inputs.size(); i++) {
      List<String> newInput = splitIntoTokens(inputs.get(i));
      for (int j = 0; j < inputCategory.size(); j++) {
        String item = newInput.get(j);
        inputCategory.get(keys.get(j)).add(item);
      }
    }
    return inputCategory;
  }

  /**
   * Split into tokens at every "," . Also remove the quotes at beginning and end of the list.
   *
   * @param input a String of raw data from original file
   * @return a String list of the separated components in the String.
   */
  private List<String> splitIntoTokens(String input) {
    input = input.replaceAll("^\"|\"$", "");
    String[] result = input.split("\",\"");
    return Arrays.asList(result);
  }

  /**
   * Helper function to read the template file and convert it to a list of string, update field with
   * the string. Will catch error if the file not found or file can't open.
   *
   * @param fileName file needs to be analyzed
   */
  protected void getSourceFile(String fileName) {
    StringBuilder inputs = new StringBuilder();
    try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        inputs.append(line).append(System.lineSeparator());
      }
      this.inputs = Arrays.asList(inputs.toString().split(System.lineSeparator()));
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}