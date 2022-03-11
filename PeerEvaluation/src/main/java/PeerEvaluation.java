/**
 *
 *
 */
import java.io.* ;
import java.util.Scanner;


public class PeerEvaluation 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Calculator! AMA." );
    }

    // Parse CSV Function     
    public void parseCSV(String input) throws Exception {

      //parsing a CSV file into the constructor of Scanner class 
      Scanner sc = new Scanner(new File(input));
      
      //setting comma as delimiter pattern
      sc.useDelimiter(",");
      
      while (sc.hasNext()) {
        System.out.print(sc.next());
      }
      System.out.println();
      sc.close();   //closes the scanner 
       
  }
}


