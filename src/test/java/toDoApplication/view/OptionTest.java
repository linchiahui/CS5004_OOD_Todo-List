package toDoApplication.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import toDoApplication.view.Option.Builder;

public class OptionTest {

  Builder invalidBuilder;
  Option completeToDo, csv, sameCSV, display, invalid;
  String csvDesc;

  @Before
  public void setUp() throws Exception {
    csv = new Builder().argName("csv").desc("accepts the name of the csv file to process").longOpt("--csv-file").required().hasArg().build();
    csvDesc = "Option{longOpt='--csv-file', argName='csv',"
        + " description='accepts the name of the csv file to process',"
        + " required=true, numberOfArgs=1}";
    sameCSV = new Builder("--csv-file").argName("csv").desc("accepts the name of the csv file to process").required(true).hasArg().build();
    completeToDo = new Builder("--complete-todo").argName("complete").desc("Mark the Todo with the provided ID as complete.").required(false).hasArgs().build();
    display = new Builder().argName("display").desc("Display all todo.").longOpt("--display").hasArg(false).build();
    invalidBuilder = new Builder().argName("display").desc("Display all todo.").hasArg(false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor(){
    invalid = invalidBuilder.build();
  }

  @Test
  public void getLongOpt() {
    assertEquals("--csv-file", csv.getLongOpt());
  }

  @Test
  public void getArgName() {
    assertEquals("csv", csv.getArgName());
  }

  @Test
  public void hasArg() {
    assertTrue(csv.hasArg());
    assertFalse(display.hasArg());
  }

  @Test
  public void hasArgs() {
    assertFalse(csv.hasArgs());
    assertTrue(completeToDo.hasArgs());
  }

  @Test
  public void getDescription() {
    assertEquals("accepts the name of the csv file to process", csv.getDescription());
  }

  @Test
  public void isRequired() {
    assertTrue(csv.isRequired());
    assertFalse(completeToDo.isRequired());
  }

  @Test
  public void getNumberOfArgs() {
    assertEquals(1, csv.getNumberOfArgs());
    assertEquals(Integer.MAX_VALUE, completeToDo.getNumberOfArgs());
  }

  @Test(expected = ParserException.class)
  public void testAddArg() throws ParserException {
    display.addValueForProcessing("check");
  }

  @Test(expected = ParserException.class)
  public void testProcessValue() throws ParserException {
    csv.addValueForProcessing("path1");
    csv.addValueForProcessing("path2");
  }

  @Test
  public void getValuesList() throws ParserException {
    assertNull(display.getValuesList());
    List<String> csvPath = new ArrayList<>();
    csvPath.add("path");
    csv.addValueForProcessing("path");
    assertEquals(csvPath, csv.getValuesList());
  }

  @Test
  public void getValue() throws ParserException {
    csv.addValueForProcessing("path");
    assertEquals("path", csv.getValue());
    assertNull(display.getValue());
  }

  @Test
  public void testEquals() {
    assertTrue(csv.equals(csv));
    assertFalse(csv.equals(completeToDo));
    assertTrue(csv.equals(sameCSV));
    assertFalse(csv.equals(null));
    assertFalse(csv.equals(csvDesc));
  }

  @Test
  public void testHashCode() {
    assertTrue(csv.hashCode() == sameCSV.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(csvDesc, csv.toString());
  }
}