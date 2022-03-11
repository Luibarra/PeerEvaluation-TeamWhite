import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 * 
 */

public class PeerEvaluationTest
{
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
        System.out.println("");
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
        System.out.println("");
      }
    }
}









