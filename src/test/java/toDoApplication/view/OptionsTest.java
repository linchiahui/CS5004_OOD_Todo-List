package toDoApplication.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.view.Option.Builder;

public class OptionsTest {

  Option csv, display, com1, com2, com3;
  Options set1, set2, set3, set4;
  String set2Desc;

  @Before
  public void setUp() throws Exception {
    set1 = new Options();
    csv = new Builder().argName("csv").desc("accepts the name of the csv file to process").longOpt("--csv-file").required().hasArg().build();
    display = new Builder().argName("display").desc("Display all todo.").longOpt("--display").hasArg(false).build();
    com1 = new Builder("--complete-todo-one").argName("completeone").desc("Complete to-do number one").hasArg().build();
    com2 = new Builder("--complete-todo-two").argName("completetwo").desc("Complete to-do number two").hasArg().build();
    com3 = new Builder("--complete-todo-three").argName("completethree").desc("Complete to-do number three").hasArg().build();

    set2 = new Options().addOption(com1);
    set3 = new Options().addOption(com2);
    set4 = new Options().addOption(com1);
    set2Desc = "Options{longOpts={complete-todo-one=Option{longOpt='--complete-todo-one',"
        + " argName='completeone', description='Complete to-do number one', required=false,"
        + " numberOfArgs=1}}, requiredOpts=[]}";
  }

  @Test
  public void addOption() {
    set1 = set1.addOption(csv);
    Map<String, Option> inSet1 = new LinkedHashMap<>();
    inSet1.put("csv-file", csv);
    List<String> set1List = new ArrayList<>(Arrays.asList("--csv-file"));
    assertTrue(set1.getLongOpts().equals(inSet1));
    assertTrue(set1.getRequiredOptions().equals(set1List));
  }

  @Test
  public void getRequiredOptions() {
    set1 = set1.addOption(display);
    Map<String, Option> inSet1 = new LinkedHashMap<>();
    inSet1.put("display", display);
    List<String> set1List = new ArrayList<>();
    assertTrue(set1.getLongOpts().equals(inSet1));
    assertTrue(set1.getRequiredOptions().equals(set1List));
  }

  @Test
  public void getOption() {
    set1 = set1.addOption(csv).addOption(display);
    assertEquals(display, set1.getOption("--display"));
    assertNull(set1.getOption("--complete-todo"));
  }

  @Test
  public void getOption2() {
    set1 = set1.addOption(com1);
    assertTrue(set1.getOption("-complete-todo-one").equals(com1));
  }

  @Test
  public void getOption3(){
    set1 = set1.addOption(com1);
    assertNull(set1.getOption("csv-file"));
  }

  @Test
  public void getMatchingOptions() {
    set1 = set1.addOption(display).addOption(csv);
    List<String> onlyDisplay = new ArrayList<>(Arrays.asList("display"));
    assertEquals(onlyDisplay, set1.getMatchingOptions("-display"));
  }

  @Test
  public void getMatchingOptionsMultiple(){
    Options o5 = set1.addOption(display).addOption(csv).addOption(com1).addOption(com2).addOption(com3);
    List<String> coms = new ArrayList<>(Arrays.asList("complete-todo-one", "complete-todo-two","complete-todo-three" ));
    assertTrue(o5.getMatchingOptions("--complete").equals(coms));
  }

  @Test
  public void hasOption(){
    set1 = set1.addOption(display);
    assertTrue(set1.hasOption("-display"));
    assertFalse(set1.hasOption("completed"));
  }


  @Test
  public void testEquals() {
    assertTrue(set2.equals(set2));
    assertFalse(set2.equals(null));
    assertTrue(set2.equals(set4));
    assertFalse(set2.equals(set3));
    assertFalse(set2.equals(set2Desc));
  }

  @Test
  public void testHashCode() {
    assertTrue(set2.hashCode() == set4.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(set2Desc, set2.toString());
  }
}