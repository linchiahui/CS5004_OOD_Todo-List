package toDoApplication.view;

import static org.junit.Assert.*;

import java.util.List;
import jdk.jfr.StackTrace;
import org.junit.Before;
import org.junit.Test;
import toDoApplication.view.Option.Builder;

public class CommandLineTest {

  Option csv, display;
  CommandLine cmd, cmd2, cmd3;
  String cmdS;


  @Before
  public void setUp() throws Exception {
    cmd = new CommandLine();
    cmd2 = new CommandLine();
    cmd3 = new CommandLine();
    csv = new Builder().argName("csv").desc("accepts the name of the csv file to process").longOpt("--csv-file").required().hasArg().build();
    display = new Builder().argName("display").desc("Display all todo.").longOpt("--display").hasArg(false).build();
    cmd.addOption(csv);
    cmdS = "[ CommandLine: [ options: [Option{longOpt='--csv-file', argName='csv', description='accepts the name of the csv file to process', required=true, numberOfArgs=1}] ] ]";
    cmd2.addOption(csv);
    cmd3.addOption(csv);
    cmd3.addOption(display);
  }

  @Test
  public void hasOption() {
    assertTrue(cmd.hasOption("--csv-file"));
  }

  @Test
  public void addOption() {
    cmd.addOption(display);
    System.out.println(cmd);
    assertTrue(cmd.hasOption("display"));
  }

  @Test
  public void testEquals(){
    assertTrue(cmd.equals(cmd));
    assertFalse(cmd.equals(null));
    assertFalse(cmd.equals(cmdS));
    assertTrue(cmd.equals(cmd2));
    assertFalse(cmd.equals(cmd3));
  }

  @Test
  public void testHashcode(){
    assertTrue(cmd.hashCode() == cmd2.hashCode());
  }
  @Test
  public void testToString() {
    assertEquals(cmdS, cmd.toString());
  }
}