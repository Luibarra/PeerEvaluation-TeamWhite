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
    System.out.println( "" );
  }

  // Parse CSV Function     
  public String[][] parseCSV(String input) throws Exception 
  {
    String[][] table = new String[100][100]; 

    //parsing a CSV file into the constructor of Scanner class 
    Scanner sc = new Scanner(new File(input));
    
    //setting comma as delimiter pattern
    sc.useDelimiter(",");

    for(int i = 0; i < 100; i++){
      for(int j = 0; j < 100; j++){
        if(sc.hasNext()){
          String st = sc.next(); 
          table[i][j] = st; 
          System.out.print(" " + st); 
        }
      }
    }
    System.out.println();

    sc.close();   //closes the scanner 
       
    return table; 
  }
}


