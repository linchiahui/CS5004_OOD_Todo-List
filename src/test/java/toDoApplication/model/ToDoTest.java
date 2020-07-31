package toDoApplication.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.model.ToDo.ToDoBuilder;

public class ToDoTest {

  ToDo hw9;
  String hw9Desc;

  ToDo clean;
  ToDo hw9Same;


  @Before
  public void setUp() throws Exception {
    hw9 = new ToDoBuilder("Finish HW9").addDueDate("03/22/2020").changePriority("1").addCategory("school").build();
    hw9Desc = "\"Finish HW9\",\"false\",\"03/22/2020\",\"1\",\"school\"" + System.lineSeparator();
    clean = new ToDoBuilder("Clean the house").addDueDate("03/22/2020").addCategory("home").build();
    hw9Same = new ToDoBuilder("Finish HW9").addDueDate("03/22/2020").changePriority("1").addCategory("school").build();
  }

  @Test
  public void setCompleted() {
    hw9.setCompleted();
    assertTrue(hw9.isCompleted());
  }

  @Test
  public void isCompleted() {
    assertFalse(hw9.isCompleted());
  }

  @Test
  public void getCategory() {
    assertEquals("school", hw9.getCategory());
  }

  @Test
  public void getPriority() {
    assertEquals(1, hw9.getPriority());
  }

  @Test
  public void testEquals() {
    assertTrue(hw9.equals(hw9));
    assertFalse(hw9.equals(null));
    assertFalse(hw9.equals(hw9Desc));
    assertTrue(hw9.equals(hw9Same));
    assertFalse(hw9.equals(clean));
  }

  @Test
  public void testHashCode() {
    assertTrue(hw9.hashCode() == hw9Same.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(hw9Desc, hw9.toString());
  }

  @Test
  public void compareToDos() {
    assertEquals(0, ToDo.DateComparator.compare(hw9, clean));
    assertTrue(ToDo.PriorityComparator.compare(hw9, clean) < 0);
  }
}

