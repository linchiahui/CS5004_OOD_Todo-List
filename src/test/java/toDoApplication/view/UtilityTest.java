package toDoApplication.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilityTest {
  Utility utility;
  String nullString;
  String noHyphen;
  String oneHyphen;
  String twoHyphens;

  @Before
  public void setUp() throws Exception {
    utility = new Utility();
    nullString = null;
    noHyphen = "complete-todo";
    oneHyphen = "-textfile.txt";
    twoHyphens = "--csv-file.csv";
  }

  @Test
  public void stripLeadingHyphens() {
    assertEquals("complete-todo", Utility.stripLeadingHyphens(noHyphen));
    assertEquals("textfile.txt", Utility.stripLeadingHyphens(oneHyphen));
    assertEquals("csv-file.csv", Utility.stripLeadingHyphens(twoHyphens));
    assertNull(Utility.stripLeadingHyphens(nullString));
  }
}