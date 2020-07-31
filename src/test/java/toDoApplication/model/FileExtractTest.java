package toDoApplication.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.model.ToDo.ToDoBuilder;

public class FileExtractTest {

  FileExtract file;
  ToDo hw9;

  @Before
  public void setUp() throws Exception {
    file = new FileExtract("short.csv");
    hw9 = new ToDoBuilder("Finish HW9").addDueDate("03/22/2020").changePriority("1").addCategory("school").build();
  }

  @Test
  public void getToDoList() {
  }

}