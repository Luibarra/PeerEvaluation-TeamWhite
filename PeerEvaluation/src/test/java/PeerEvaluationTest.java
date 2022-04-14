import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 */

public class PeerEvaluationTest
{
  public static PeerEvaluation pc;
  public static Connection c;

  @BeforeClass
  public static void setUpDB() throws Exception {
    System.out.println("connecting...");
    pc = new PeerEvaluation();
    c = pc.connect("jdbc:postgresql://localhost:5432/cs375v1", "mrblee", "purplewhite");
  }

  @AfterClass
  public static void teardownThisWall() {
   System.out.println(); 
   System.out.println("all done");
  }

  //need to create dummy table for interaction tests
  public void response_delete() {
    pc.nonquery("delete from response"); 
  }

  public void response_inserts() {
    String insert = "INSERT INTO Response (evalID,Student1,Student2,Category,Val) VALUES (1906, 45, 46, 'T', 9);"; 
    pc.nonquery(insert);
  }
        
  public int count_rows (String table) {
    ResultSet rs;
    int n = -1;

    try {
        rs = pc.query("select count(*) as n from " + table);
    } 
    catch (Exception e) {
        System.out.println("ERROR select count(*) as n: " + e.getMessage());
        assertTrue(false);
        return -1;
    }

    try {
        rs.next();
        n = rs.getInt("n");
    } 
    catch (Exception e) {
        System.out.println("ERROR rs.next() and getInt()");
        assertTrue(false);
        return -1;
    }

    return n;
  } 

  @Test
  public void check_delete () {
    int n = -1;
    response_delete();
    n = count_rows("Response");
    assertEquals("Response table should be empty", 0, n);
    System.out.print("delete from code functioning...");
  }

  @Test
  public void check_inserts () {
    int n = -1;
    response_delete();
    response_inserts();
    n = count_rows("Response");
    assertEquals("should now be 1", 1, n);
    System.out.println("insert from code functioning...");
  }    

  // *************************************************************************
  //                          Main Test Section 
  // *************************************************************************

  //Test that column names are what they should be
  @Test
  public void testColumnName1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("evaluation-id", table[0][0]);
    }

    catch (Exception e) {
      System.out.print("error in columnname1");
    }
  }

  @Test
  public void testColumnName2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("rator-id", table[0][1].replaceAll("\\s+",""));
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void testColumnName3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("ratee-id", table[0][2].replaceAll("\\s+",""));
    }

    catch (Exception e) {
      System.out.print("");
    }
  }  

  @Test
  public void testColumnName4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("question-id", table[0][3].replaceAll("\\s+",""));
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify Ratings
  @Test
  public void testRatings()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      //ensure that each rating is within a range of 1 to 5
      assertTrue(Integer.parseInt(table[1][4]) < 6 && Integer.parseInt(table[1][4]) > 0);
      assertTrue(Integer.parseInt(table[2][4]) < 6 && Integer.parseInt(table[2][4]) > 0);
      assertTrue(Integer.parseInt(table[3][4]) < 6 && Integer.parseInt(table[3][4]) > 0);
      assertTrue(Integer.parseInt(table[4][4]) < 6 && Integer.parseInt(table[4][4]) > 0);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void testEvalPair()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      //save eval id 1 and its pair of rator and ratee
      int eval = Integer.parseInt(table[1][0]);
      int rator = Integer.parseInt(table[1][1]);
      int ratee = Integer.parseInt(table[1][2]);

      //ensure if the eval has the same id, same pairing
      if(Integer.parseInt(table[2][0]) == eval)
          assertTrue(Integer.parseInt(table[1][1]) == rator && Integer.parseInt(table[1][2]) == ratee);

      if(Integer.parseInt(table[3][0]) == eval)
          assertTrue(Integer.parseInt(table[3][1]) == rator && Integer.parseInt(table[3][2]) == ratee);

      if(Integer.parseInt(table[4][0]) == eval)
          assertTrue(Integer.parseInt(table[4][1]) == rator && Integer.parseInt(table[4][2]) == ratee);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

//Verify that all Rater-ID columns in table array match "evals.csv"
  @Test
  public void verifyRaterID1()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][1]) == 3);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRaterID2()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][2]) == 3);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRaterID3()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][3]) == 3);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRaterID4()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][4]) == 3);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify that all Ratee-ID columns in table array match "evals.csv"
  @Test
  public void verifyRateeID1()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[2][1]) == 2);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRateeID2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[2][2]) == 2);
  }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRateeID3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[2][3]) == 2);
    }
    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRateeID4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[2][4]) == 2);
    }
    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify that all Question columns in table array match "evals.csv"
  @Test
  public void verifyQuestion1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[3][1]) == 1);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyQuestion2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[3][2]) == 2);
    }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyQuestion3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[3][3]) == 3);
    }
    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyQuestion4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[3][4]) == 4);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify that all Value columns in table array match "evals.csv"
  @Test
  public void verifyValue1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[4][1]) == 3);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyValue2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[4][2]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyValue3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[4][3]) == 4);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyValue4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[4][4]) == 5);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify that all rows are under evaluation #1
  @Test
  public void verifyRowEvalNum1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[0][1]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRowEvalNum2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[0][2]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRowEvalNum3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[0][3]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRowEvalNum4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[0][4]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  //Verify row values are correct when tested together
  @Test
  public void verifyRowValues1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[0][1]) == 1);
      assertTrue(Integer.parseInt(table[1][1]) == 3);
      assertTrue(Integer.parseInt(table[2][1]) == 2);
      assertTrue(Integer.parseInt(table[3][1]) == 1);
      assertTrue(Integer.parseInt(table[4][1]) == 3);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRowValues2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[0][2]) == 1);
      assertTrue(Integer.parseInt(table[1][2]) == 3);
      assertTrue(Integer.parseInt(table[2][2]) == 2);
      assertTrue(Integer.parseInt(table[3][2]) == 2);
      assertTrue(Integer.parseInt(table[4][2]) == 1);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }

  @Test
  public void verifyRowValues3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[0][3]) == 1);
      assertTrue(Integer.parseInt(table[1][3]) == 3);
      assertTrue(Integer.parseInt(table[2][3]) == 2);
      assertTrue(Integer.parseInt(table[3][3]) == 3);
      assertTrue(Integer.parseInt(table[4][3]) == 4);
   }

    catch (Exception e) {
      System.out.print("");
    }
  }
}









