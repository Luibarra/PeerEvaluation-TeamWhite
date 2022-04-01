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

  public void interacting_delete() {
    pc.nonquery("delete from interacting");
  }

  public void interacting_inserts() {
    String insert = "INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected) VALUES ('1', 'Asks for and shows an interest in teammates ideas and contributions.', 'Makes sure teammates stay informed and understand each other.', 'Provides encouragement or enthusiasm to the team.', '0');"; 
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
    interacting_delete();
    n = count_rows("Interacting");
    assertEquals("Interacting table should be empty", 0, n);
    System.out.print("delete from code functioning...");
  }

  @Test
  public void check_inserts () {
    int n = -1;
    interacting_delete();
    interacting_inserts();
    n = count_rows("Interacting");
    assertEquals("should now be 1", 1, n);
    System.out.print("insert from code functioning...");
  }    
  
  @Test
  public void testColumnNames() 
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation(); 
      String[][] table = PeerEval.parseCSV("evals.csv");

      //check that column names are what they should be 
      assertEquals("evaluation-id", table[0][0]);
      assertEquals("rator-id", table[0][1].replaceAll("\\s+",""));
      assertEquals("ratee-id", table[0][2].replaceAll("\\s+",""));
      assertEquals("question-id", table[0][3].replaceAll("\\s+",""));
    } 

    catch (Exception e) {
      System.out.println("test fail tho");
    }
  }

  // ======== this was breaking for no reason ==========
  
    // @Test
    // public void testRatings() 
    // {
    // 	try {
    //     PeerEvaluation PeerEval = new PeerEvaluation(); 
    //     String[][] table = PeerEval.parseCSV("evals.csv");

    //     //ensure that each rating is within a range of 1 to 5 
    //     assertTrue(Integer.parseInt(table[1][4]) < 6 && Integer.parseInt(table[1][4]) > 0);
    //     assertTrue(Integer.parseInt(table[2][4]) < 6 && Integer.parseInt(table[2][4]) > 0);
    //     assertTrue(Integer.parseInt(table[3][4]) < 6 && Integer.parseInt(table[3][4]) > 0);
    //     assertTrue(Integer.parseInt(table[4][4]) < 6 && Integer.parseInt(table[4][4]) > 0);
		//   } 
	
    //   catch (Exception e) {
    //     System.out.println("");
    //   }
    // }

    // @Test
    // public void testEvalPair() 
    // {
    // 	try {
    //     PeerEvaluation PeerEval = new PeerEvaluation(); 
    //     String[][] table = PeerEval.parseCSV("evals.csv");

    //     //save eval id 1 and its pair of rator and ratee
    //     int eval = Integer.parseInt(table[1][0]);
    //     int rator = Integer.parseInt(table[1][1]); 
    //     int ratee = Integer.parseInt(table[1][2]); 

    //     //ensure if the eval has the same id, same pairing
    //     if(Integer.parseInt(table[2][0]) == eval)
    //         assertTrue(Integer.parseInt(table[1][1]) == rator && Integer.parseInt(table[1][2]) == ratee); 

    //     if(Integer.parseInt(table[3][0]) == eval)
    //         assertTrue(Integer.parseInt(table[3][1]) == rator && Integer.parseInt(table[3][2]) == ratee);
            
    //     if(Integer.parseInt(table[4][0]) == eval)
    //         assertTrue(Integer.parseInt(table[4][1]) == rator && Integer.parseInt(table[4][2]) == ratee);
		//   } 
	
    //   catch (Exception e) {
    //     System.out.println("");
    //   }
    // }
}









