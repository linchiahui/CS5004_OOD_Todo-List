package toDoApplication.controller.Filter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.model.ToDo;
import toDoApplication.model.ToDo.ToDoBuilder;

public class FilterCategoryTest {

  ToDo hw7, hw8, hw9, clean;
  FilterCategory newFilter, withMoreHW;
  List<ToDo> todo, school, moreHW;

  @Before
  public void setUp() throws Exception {
    hw9 = new ToDoBuilder("Finish HW9").addDueDate("03/22/2020").changePriority("1").addCategory("school").build();
    clean = new ToDoBuilder("Clean the house").addDueDate("03/22/2020").addCategory("home").build();
    hw8 = new ToDoBuilder("Finish HW8").addDueDate("03/15/2020").changePriority("2").addCategory("school").build();
    hw7 = new ToDoBuilder("Finish HW7").addDueDate("03/08/2020").build();

    todo = new ArrayList<>(Arrays.asList(hw8, hw9, clean));
    school = new ArrayList<>(Arrays.asList(hw8, hw9));
    moreHW = new ArrayList<>(Arrays.asList(hw7, hw8, hw9));

    newFilter = new FilterCategory(todo, "school");
    withMoreHW = new FilterCategory(moreHW, "school");
  }

  @Test
  public void getResult() {
    assertEquals(school, newFilter.getResult());
    assertEquals(school, withMoreHW.getResult());
  }
}