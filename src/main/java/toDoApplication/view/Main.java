package toDoApplication.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import toDoApplication.controller.ActionMaker;
import toDoApplication.model.FileExtract;
import toDoApplication.model.ToDo;

/**
 * Main class. Entry point of the program.
 */
public class Main {

  public static void main(String[] args) {
    try {
      // Create a new Options object for this program
      Options options = new Options();
      // Populate the Options object for this program
      OptionsMaker.makeOptions(options);
      ICommandLineParser parser = new CommandLineParser();
      CommandLine commandLine = parser.parse(options, args);
      // Check that all the command line args are valid
      ValidCmdChecker.checkCmd(commandLine);

      // Extract the file name following command "--csv-file"
      String fileName= commandLine.getOptionValue("csv-file");
      List<ToDo> test = new FileExtract(fileName).getToDoList();
      ActionMaker.makeActions(commandLine,test,fileName);

    } catch (ParserException e) {
      System.out.println("Parser Exception " + e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}