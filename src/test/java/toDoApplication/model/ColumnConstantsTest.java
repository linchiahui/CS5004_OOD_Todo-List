package toDoApplication.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColumnConstantsTest {
  ColumnConstants c1;

  @Before
  public void setUp() throws Exception {
    c1 = new ColumnConstants();
  }

  @Test
  public void testConstants(){
    assertEquals("id", c1.ID);
    assertEquals("text", c1.TEXT);
    assertEquals("completed", c1.STATUS);
    assertEquals("due", c1.DUE);
    assertEquals("priority", c1.PRIORITY);
    assertEquals("category", c1.CATEGORY);
    assertEquals("\""+"id\",\"text\",\"completed\",\"due\",\"priority\",\"category"+"\"", c1.COLUMN_LINE);
  }
}