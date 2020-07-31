package toDoApplication.view;

import static org.junit.Assert.*;

import java.util.List;
import javax.swing.text.html.parser.Parser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import toDoApplication.view.Option.Builder;

public class CommandLineParserTest {

  CommandLineParser parser1;
  CommandLine cmd1;
  String[] args;
  Options ops;
  OptionsMaker maker;
  Option csv;
  Option display;

  @Before
  public void setUp() throws Exception {
    args = new String[]{"--csv-file", "todos.csv", "--display"};
    ops = new Options();
    maker = new OptionsMaker();
    OptionsMaker.makeOptions(ops);

    csv = new Builder().argName("path").desc("accept the name of the csv file to process").longOpt("csv-file").required().hasArg().build();
    display = new Builder().argName(null).desc("Display all todo.").longOpt("display").hasArg(false).build();

    parser1 = new CommandLineParser();
    cmd1 = new CommandLine();
    cmd1.addOption(csv);
    cmd1.addOption(display);
  }

  @Test
  public void parse() throws ParserException {
    assertEquals(cmd1, parser1.parse(ops, args));
  }

  @Test(expected = ParserException.class)
  public void parse1() throws ParserException{
    String[] random = new String[]{"blue", "--csv-file"};
    parser1.parse(ops, random);
  }

  @Test(expected = ParserException.class)
  public void parse2() throws ParserException{
    parser1.parse(ops, new String[]{"--display"});
  }

  @Test(expected = ParserException.class)
  public void parse3() throws ParserException{
    parser1.parse(ops, null);
  }

}