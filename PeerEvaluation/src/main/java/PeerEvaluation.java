/**
 *
 *
 */
import java.io.* ;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


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

  public Connection getConnection() throws SQLException {

    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "postgres");
    connectionProps.put("password", "Spartan7624$$");

    String url = "jdbc:postgresql://localhost:5432/CS735v1"; 
    conn = DriverManager.getConnection(url, connectionProps);
    
    System.out.println("Connected to database");
    return conn;
}
}


