import java.io.* ;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PeerEvaluation 
{
  public static void main( String[] args )
  {
    System.out.println( "" );
  }

  public Connection c;
    
  public Connection connect(String url, String user, String password) {
    try {
      //Class.forName("org.postgresql.Driver");
      c = DriverManager.getConnection(url, user, password);
    } 
    catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
      System.exit(0);
    }
      System.out.println("Opened database: " + url + " successfully.");
      return c;
  }

  //query function
  public ResultSet query(String inQuery) throws Exception {
    // System.out.println("query: [" + inQuery + "]");
    ResultSet rs = null;
    PreparedStatement pstmt = c.prepareStatement(inQuery);
    rs = pstmt.executeQuery();
    return rs;
  }

  //nonquery function 
  public void nonquery(String inQuery)  {
    // System.out.println("nonquery: [" + inQuery + "]");
    ResultSet rs;
    try {
        PreparedStatement pstmt = c.prepareStatement(inQuery);
        rs = pstmt.executeQuery();
        System.out.println(rs); 
    }  
    catch(Exception e) {
      if (! ("No results were returned by the query.".equals(e.getMessage()))) 
      {
        System.out.println("ERROR response delete");
        System.out.println(e.getMessage());
      }
    }
  }
        
  public void v_response_print(ResultSet rs) {
    System.out.println("evalid\tstudent1\tstudent2\tcategory\tvalue");
    try {
        while(rs.next()) {
            System.out.print(rs.getInt(1));
            System.out.print("\t");
            System.out.print(rs.getInt(2));
            System.out.print("\t\t");
            System.out.print(rs.getInt(3));
            System.out.print("\t\t"+ rs.getString(4));
            System.out.print("\t\t");
            System.out.println(rs.getInt(5));
        } 
    }
    catch(Exception exec) {
        System.out.println("v_response_print not happy");
        exec.printStackTrace();
    }
  }

  // Parse CSV Function     
  public String[][] parseCSV(String input) throws Exception 
  {
    String[][] table = new String[10000][5]; //may still need to be made dynamic size
    String[] splitLine = new String[5];      //length of row is always the same

    //parsing a CSV file into the constructor of Scanner class 
    Scanner sc = new Scanner(new File(input)); 
    
    //setting comma as delimiter pattern
    sc.useDelimiter(",");

    int i = 0; 
    while(sc.hasNextLine()){
      String st = sc.nextLine(); //take line and split into tokens in another array for each row 
      splitLine = st.split(","); 
      //System.out.print("["+st+"]");   //debug: shows each line in brackets

       //System.out.print("{");   //debug: shows each row array, braces outling the array
      for(int j = 0; j < 5;j++){
          table[i][j] = splitLine[j]; 
           //System.out.print("["+table[i][j]+"]"); //debug: brackets outline variables
      }  
      //System.out.print("}");
      //System.out.println(); 

      i++;
    }

    //array being returned is resized 
    String[][] newSize = new String[i][5]; 
    for(int j = 0; j < newSize.length;j++){
      for(int k = 0; k < 5; k++){
        newSize[j][k] = table[j][k]; 
      }
    }

    sc.close();   //closes the scanner 
       
    return newSize; 
  }

  //
  // this may never be used but idk if i should delete it
  //
  // public Connection getConnection() throws SQLException {

  //   Connection conn = null;
  //   Properties connectionProps = new Properties();
  //   connectionProps.put("user", "postgres");
  //   connectionProps.put("password", "Spartan7624$$");

  //   String url = "jdbc:postgresql://localhost:5432/CS735v1"; 
  //   conn = DriverManager.getConnection(url, connectionProps);
    
  //   System.out.println("Connected to database");
  //   return conn;
  // }
}


