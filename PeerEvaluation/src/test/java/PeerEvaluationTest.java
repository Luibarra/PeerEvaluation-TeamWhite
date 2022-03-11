import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 * 
 */

public class PeerEvaluationTest
{
    @Test
    public void testBlank() 
    {
    	try {
        PeerEvaluation PeerEval = new PeerEvaluation(); 
        PeerEval.parseCSV("evals.csv");
		  } 
	
      catch (Exception e) {
        System.out.println("blab");
      }
    }
}









