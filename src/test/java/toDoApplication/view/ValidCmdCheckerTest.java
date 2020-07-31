package toDoApplication.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import toDoApplication.view.Option.Builder;

public class ValidCmdCheckerTest {

  Options options;
  CommandLine cmd1;
  CommandLine cmd2;
  CommandLine cmd3;
  CommandLineParser parser;
  String[] twoSorts;
  String[] noAddToDo;
  String[] incorrectPriority;
  ValidCmdChecker checker;

  @Before
  public void setUp() throws Exception {
    checker = new ValidCmdChecker();
    options = new Options();
    OptionsMaker.makeOptions(options);
    parser = new CommandLineParser();
    twoSorts = new String[]{"--csv-file", "todos.csv", "--display", "--sort-by-date", "--sort-by-priority"};
    noAddToDo = new String[]{"--csv-file", "todos.csv", "--add-todo"};
    incorrectPriority = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "meetwithfriends", "--priority", "8" };

  }

  @Test(expected = ParserException.class)
  public void checkCmd() throws ParserException {
    cmd1 = parser.parse(options, twoSorts);
    ValidCmdChecker.checkCmd(cmd1);
  }

  @Test(expected = ParserException.class)
  public void checkCmd2() throws ParserException{
    cmd2 = parser.parse(options, noAddToDo);
    ValidCmdChecker.checkCmd(cmd2);
  }

  @Test(expected = ParserException.class)
  public void checkCmd3() throws ParserException{
    cmd3 = parser.parse(options, incorrectPriority);
    ValidCmdChecker.checkCmd(cmd3);
  }
}