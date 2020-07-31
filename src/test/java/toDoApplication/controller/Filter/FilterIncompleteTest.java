package toDoApplication.controller.Filter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.model.ToDo;
import toDoApplication.model.ToDo.ToDoBuilder;

public class FilterIncompleteTest {

  ToDo hw9, hw8, hw7;
  FilterIncomplete newFilter;
  List<ToDo> unfinished, allHW;

  @Before
  public void setUp() throws Exception {
    hw9 = new ToDoBuilder("Finish HW9").addDueDate("03/22/2020").changePriority("1").addCategory("school").build();
    hw8= new ToDoBuilder("Finish HW8").addDueDate("03/15/2020").changePriority("2").addCategory("school").addStatus("true").build();
    hw7 = new ToDoBuilder("Finish HW7").addDueDate("03/08/2020").addStatus("true").build();

    allHW = new ArrayList<>(Arrays.asList(hw7, hw8, hw9));
    unfinished = new ArrayList<>(Arrays.asList(hw9));
    newFilter = new FilterIncomplete(allHW);
  }

  @Test
  public void getResult() {
    assertEquals(unfinished, newFilter.getResult());
  }
}