package toDoApplication.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptionValidatorTest {
  OptionValidator validator;
  String nullString;
  String valid;
  String invalid;

  @Before
  public void setUp() throws Exception {
    validator = new OptionValidator();
    nullString = null;
    valid = "--csv.file";
    invalid = "--csv88.file";
  }

  @Test(expected = IllegalArgumentException.class)
  public void validateOption() {
    OptionValidator.validateOption(invalid);
  }
}