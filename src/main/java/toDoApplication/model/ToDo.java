package toDoApplication.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * This class represents a to-do instance. Including task, status, due date, priority and category
 * options.
 */

public class ToDo {

  private static final int DEFAULT_PRIORITY = 3;
  protected static final boolean DEFAULT_COMPLETED = false;
  protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
  private String text;
  private boolean completed;
  private Date due;
  private int priority;
  private String category;

  /**
   * Use builder to create a To-Do object.
   *
   * @param builder builder object
   */

  private ToDo(ToDoBuilder builder) {
    this.text = builder.text;
    this.completed = builder.completed;
    this.due = builder.due;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  /**
   * Sets the status of to-do to "completed".
   */
  public void setCompleted() {
    this.completed = true;
  }

  /**
   * Gets the complete status, true if completed, otherwise false
   *
   * @return true if the to-do is completed, false if it is not.
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Gets the to-do's category
   *
   * @return a String specifying the to-do's category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Gets the to-do's due date
   *
   * @return a Date object.
   */
  public Date getDue() {
    return this.due;
  }

  /**
   * get the priority of a todo
   *
   * @return the int that represent the todo's priority, from 1 to 3
   */

  public int getPriority() {
    return priority;
  }

  /**
   * Nested builder class to build To-Do object.
   */

  public static class ToDoBuilder {

    private String text;
    private Boolean completed;
    private Date due;
    private int priority;
    private String category;

    /**
     * Instantiate a object with task content, set default status to false and default priority to
     * 3.
     *
     * @param text the to-do task content
     */
    public ToDoBuilder(String text) {
      this.text = text;
      this.completed = DEFAULT_COMPLETED;
      this.priority = DEFAULT_PRIORITY;
    }

    /**
     * Update the status of a to-do
     *
     * @param status a String value of status, can be true or false.
     * @return the updated builder object
     */
    public ToDoBuilder addStatus(String status) {
      this.completed = Boolean.parseBoolean(status);
      return this;
    }

    /**
     * Update the due date of the to-do
     *
     * @param due a String value of the Date need to be updated.
     * @return the updated builder object
     * @throws ParseException if the due date value passed in has wrong format
     */
    public ToDoBuilder addDueDate(String due) throws ParseException {
      if(!due.equals("null") && !due.equals("?")){
        this.due = DATE_FORMAT.parse(due);
      }
      return this;
    }

    /**
     * Update the priority of the to-do
     *
     * @param priority the new priority, has to be 1 to 3
     * @return updated builder object
     * @throws IllegalArgumentException if new priority is not 1 to 3.
     */

    public ToDoBuilder changePriority(String priority) throws IllegalArgumentException{
      if (!priority.equals("?") && !priority.equals("null")) {
        this.priority = Integer.parseInt(priority);
      }
      return this;
    }

    /**
     * Update the category of the to-do
     *
     * @param category the new category
     * @return updated builder object
     */
    public ToDoBuilder addCategory(String category) {
      if (!category.equals("?") && !category.equals("null")) {
        this.category = category;
      }
      return this;
    }

    /**
     * Builds a new To-Do object
     * @return new TO-Do object
     */
    public ToDo build() {
      return new ToDo(this);
    }

  }

  /**
   * Set the comparator for date by ascending order. If the To-Do's due date
   * is null, it will be set to maximum value, which means this object will be
   * at the bottom of the list.
   */
  public static Comparator<ToDo> DateComparator = new Comparator<ToDo>() {
    @Override
    public int compare(ToDo o1, ToDo o2) {
      if (o1.getDue() == null) {
        return 1;
      } else if (o2.getDue() == null) {
        return -1;
      } else {
        return o1.getDue().compareTo(o2.getDue());
      }
    }
  };

  /**
   * Sets the comparator for priority by ascending order.
   */
  public static Comparator<ToDo> PriorityComparator = new Comparator<ToDo>() {
    @Override
    public int compare(ToDo o1, ToDo o2) {
      return o1.getPriority() - o2.getPriority();
    }
  };


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDo toDo = (ToDo) o;
    return completed == toDo.completed &&
        priority == toDo.priority &&
        Objects.equals(text, toDo.text) &&
        Objects.equals(due, toDo.due) &&
        Objects.equals(category, toDo.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, completed, due, priority, category);
  }

  /**
   * convert due date to the MM/dd/YYYY format and list all fields in string format.
   * @return a string contains all fields
   */

  @Override
  public String toString() {
    String strDate = "null";
    if (due != null) {
      strDate = DATE_FORMAT.format(due);
    }
    return
        "\"" + text + '\"' +
            "," + "\"" +completed +"\""+
            "," +"\""+ strDate + "\""+
            "," + "\""+ priority+ "\"" +
            "," + "\""+category + "\"" +
            System.lineSeparator();
  }

}