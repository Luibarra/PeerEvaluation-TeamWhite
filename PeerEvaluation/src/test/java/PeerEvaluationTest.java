import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.Result;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class PeerEvaluationTest
{
  public static PeerEvaluation pc;
  public static Connection c;
  public static String[][] totalAverages = new String[10000][7]; 

  @BeforeClass
  public static void setUpDB() throws Exception {
    System.out.println("connecting...");
    pc = new PeerEvaluation();
    c = pc.connect("jdbc:postgresql://localhost:5432/cs375v1", "mrblee", "purplewhite");
  
    String[][] table = pc.parseCSV("evals.csv");
    insertCSV(table);
  }

  @AfterClass
  public static void teardownThisWall() {
   System.out.println(); 
   System.out.println("all done");
  }

  //functions that do simple deletes and inserts, used in tests 
  public void dbtest_delete() {
    pc.nonquery("delete from dbtest"); 
  }

  public void dbtest_inserts() {
    String insert = "insert into dbtest (id,word,charid,val) values (2,'gamers','PM', 420);"; 
    pc.nonquery(insert);
  }
        
  //inserts CSV table into the database, will not reinsert duplicate information
  public static void insertCSV(String[][] table){
    String evalID,student1,student2,cat,val, insert,countString; 

    for(int i = 1; i < table.length;i++){
      evalID = table[i][0];
      student1 = table[i][1];
      student2 = table[i][2];
      cat = table[i][3];
      val = table[i][4];

      countString = "response where (evalID,Student1,Student2,Category,val) = ("+evalID+","+student1+","+student2+",'"+cat+"',"+val+");";
      insert = "insert into response (evalID,Student1,Student2,Category,val) values ("+evalID+","+student1+","+student2+",'"+cat+"',"+val+");"; 
      if(count_rows(countString) == 0){
        pc.nonquery(insert);
      }
    }
  }

  //count rows of a given table 
  public static int count_rows (String table) {
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


  public int countStudents(int evalid){
    ResultSet rs;
    int s = -1;

    try {
        rs = pc.query("select count(distinct student2) as numStudents from response where evalid="+evalid+"");
    } 
    catch (Exception e) {
        System.out.println("ERROR select count(distinct student2) as numStudents: " + e.getMessage());
        assertTrue(false);
        return -1;
    }

    try {
        rs.next();
        s = rs.getInt("numStudents");
    } 
    catch (Exception e) {
        System.out.println("ERROR rs.next() and getInt()");
        assertTrue(false);
        return -1;
    }

    return s;
  }


  public double TotalAverage(int evalid,int s2,String cat){
    ResultSet rs;
    double result = 0; 

    try {
      rs = pc.query("select AVG(val) as AvgTotal from response where (evalid,student2,category) = ("+evalid+","+s2+",'"+cat+"')");
    } 
    catch (Exception e) {
      System.out.println("ERROR select AVG(val) as AvgTotal: " + e.getMessage());
      assertTrue(false);
      return -1;
    }

    try {
      rs.next();
      result = rs.getDouble("AvgTotal");
    } 
    catch (Exception e) {
      System.out.println("ERROR rs.next() and getDouble()");
      assertTrue(false);
      return -1;
    }

    return Math.round(result*100.0)/100.0; //rounds double to two decimal places 
  }


  public double ExclusiveAverage(int evalid,int s2,String cat){
    ResultSet rs;
    double result = 0; 

    try {
      rs = pc.query("select AVG(val) as AvgEx from response where (evalid,student2,category) = ("+evalid+","+s2+",'"+cat+"') and student1!=student2");
    } 
    catch (Exception e) {
      System.out.println("ERROR select AVG(val) as AvgEx: " + e.getMessage());
      assertTrue(false);
      return -1;
    }

    try {
      rs.next();
      result = rs.getDouble("AvgEx");
    } 
    catch (Exception e) {
      System.out.println("ERRORex rs.next() and getDouble()");
      assertTrue(false);
      return -1;
    }

    return Math.round(result*100.0)/100.0; //rounds double to two decimal places 
  }


  public double SelfRate(int evalid,int s2,String cat){
    ResultSet rs;
    double result = 0; 

    try {
      rs = pc.query("select AVG(val) as selfRate from response where (evalid,student2,category) = ("+evalid+","+s2+",'"+cat+"') and student1=student2");
    } 
    catch (Exception e) {
      System.out.println("ERROR select AVG(val) as selfRate: " + e.getMessage());
      assertTrue(false);
      return -1;
    }

    try {
      rs.next();
      result = rs.getDouble("selfRate");
    } 
    catch (Exception e) {
      System.out.println("ERRORself rs.next() and getDouble()");
      assertTrue(false);
      return -1;
    }

    return Math.round(result*100.0)/100.0; //rounds double to two decimal places 
  }


  public String[][] TotalAveragesArray(int evalid){
    //int numStudents = countStudents(evalid);
    int numStudents = count_rows("students");
    String[][] AveragesArray = new String[numStudents+1][7];

      for(int i = 1;i<AveragesArray.length;i++){
        for(int j = 1;j<7;j++){
          switch(j){
            case 2:
              AveragesArray[i][j] = ""+TotalAverage(evalid, i, "C");
              break; 
            case 3:
              AveragesArray[i][j] = ""+TotalAverage(evalid, i, "I");
              break;
            case 4:
              AveragesArray[i][j] = ""+TotalAverage(evalid, i, "K");
              break;
            case 5:
              AveragesArray[i][j] = ""+TotalAverage(evalid, i, "E");
              break;
            case 6:
              AveragesArray[i][j] = ""+TotalAverage(evalid, i, "H");
              break; 
            default:
              AveragesArray[i][j] = ""+i;
          }
        }
      }

    for(int i = 1; i < AveragesArray.length;i++){
        AveragesArray[i][0] = "" + evalid; 
    }
    AveragesArray[0][0] = "evalid";
    AveragesArray[0][1] = "student";
    AveragesArray[0][2] = "C";
    AveragesArray[0][3] = "I";
    AveragesArray[0][4] = "K";
    AveragesArray[0][5] = "E";
    AveragesArray[0][6] = "H";

    return AveragesArray;
  }

 
  public String[][] ExAveragesArray(int evalid){
    //int numStudents = countStudents(evalid);
    int numStudents = count_rows("students");
    String[][] AveragesArray = new String[numStudents+1][7];

      for(int i = 1;i<AveragesArray.length;i++){
        for(int j = 1;j<7;j++){
          switch(j){
            case 2:
              AveragesArray[i][j] = ""+ExclusiveAverage(evalid, i, "C");
              break; 
            case 3:
              AveragesArray[i][j] = ""+ExclusiveAverage(evalid, i, "I");
              break;
            case 4:
              AveragesArray[i][j] = ""+ExclusiveAverage(evalid, i, "K");
              break;
            case 5:
              AveragesArray[i][j] = ""+ExclusiveAverage(evalid, i, "E");
              break;
            case 6:
              AveragesArray[i][j] = ""+ExclusiveAverage(evalid, i, "H");
              break; 
            default:
              AveragesArray[i][j] = ""+i;
          }
        }
      }

    for(int i = 1; i < AveragesArray.length;i++){
        AveragesArray[i][0] = "" + evalid; 
    }
    AveragesArray[0][0] = "evalid";
    AveragesArray[0][1] = "student";
    AveragesArray[0][2] = "C";
    AveragesArray[0][3] = "I";
    AveragesArray[0][4] = "K";
    AveragesArray[0][5] = "E";
    AveragesArray[0][6] = "H";

    return AveragesArray;
  }


  public String[][] SelfRates(int evalid){
    //int numStudents = countStudents(evalid);
    int numStudents = count_rows("students");
    String[][] AveragesArray = new String[numStudents+1][7];

      for(int i = 1;i<AveragesArray.length;i++){
        for(int j = 1;j<7;j++){
          switch(j){
            case 2:
              AveragesArray[i][j] = ""+SelfRate(evalid, i, "C");
              break; 
            case 3:
              AveragesArray[i][j] = ""+SelfRate(evalid, i, "I");
              break;
            case 4:
              AveragesArray[i][j] = ""+SelfRate(evalid, i, "K");
              break;
            case 5:
              AveragesArray[i][j] = ""+SelfRate(evalid, i, "E");
              break;
            case 6:
              AveragesArray[i][j] = ""+SelfRate(evalid, i, "H");
              break; 
            default:
              AveragesArray[i][j] = ""+i;
          }
        }
      }

    for(int i = 1; i < AveragesArray.length;i++){
        AveragesArray[i][0] = "" + evalid; 
    }
    AveragesArray[0][0] = "evalid";
    AveragesArray[0][1] = "student";
    AveragesArray[0][2] = "C";
    AveragesArray[0][3] = "I";
    AveragesArray[0][4] = "K";
    AveragesArray[0][5] = "E";
    AveragesArray[0][6] = "H";

    return AveragesArray;
  }

  public String[][] StrengthsArray(int evalid){
    String[][] EA = ExAveragesArray(evalid);
    String[][] result = new String[EA.length][7];
    for(int i = 1; i<result.length;i++){
      result[i][0] = ""+evalid;
      result[i][1] = ""+i; 
    }

    for(int i = 1;i<result.length;i++){
      for(int j =2;j<7;j++){
        switch(j){
          case 2:
            if(Double.parseDouble(EA[i][j]) >= 4){
              result[i][j] = "Giver";
            }
            else if(Double.parseDouble(EA[i][j]) <= 2.5&& Double.parseDouble(EA[i][j]) >= 1.0){
              result[i][j] = "Withholder";
            }
            else {
              result[i][j] = "Neutral";
            }
            break; 
          case 3:
            if(Double.parseDouble(EA[i][j]) >= 4){
              result[i][j] = "Charismatic";
            }
            else if(Double.parseDouble(EA[i][j]) <= 2.5&& Double.parseDouble(EA[i][j]) >= 1.0){
              result[i][j] = "Reclusive";
            }
            else {
              result[i][j] = "Neutral";
            }
            break;
          case 4:
            if(Double.parseDouble(EA[i][j]) >= 4){
              result[i][j] = "Focused";
            }
            else if(Double.parseDouble(EA[i][j]) <= 2.5&& Double.parseDouble(EA[i][j]) >= 1.0){
              result[i][j] = "Needs Guidance";
            }
            else {
              result[i][j] = "Neutral";
            }
            break;
          case 5:
            if(Double.parseDouble(EA[i][j]) >= 4){
              result[i][j] = "Above and Beyond";
            }
            else if(Double.parseDouble(EA[i][j]) <= 2.5&& Double.parseDouble(EA[i][j]) >= 1.0){
              result[i][j] = "Meets bare minimum";
            }
            else {
              result[i][j] = "Neutral";
            }
            break;
          case 6:
            if(Double.parseDouble(EA[i][j]) >= 4){
              result[i][j] = "Swiss Army Knife";
            }
            else if(Double.parseDouble(EA[i][j]) <= 2.5 && Double.parseDouble(EA[i][j]) >= 1.0){
              result[i][j] = "Unqualified";
            }
            else {
              result[i][j] = "Neutral";
            }
            break; 
        }
      }
    }

    result[0][0] = "evalid";
    result[0][1] = "student";
    result[0][2] = "C";
    result[0][3] = "I";
    result[0][4] = "K";
    result[0][5] = "E";
    result[0][6] = "H";

    return result;
  }

  public String[][] NotesArray(int evalid){
    int numStudents = count_rows("students");
    String[][] result = new String[numStudents+1][4]; 
    String[][] TA = TotalAveragesArray(evalid); 
    String[][] EA = ExAveragesArray(evalid); 
    String[][] SR = SelfRates(evalid); 
    String[] SelfAvg = new String[numStudents+1];
    String[] ExAvg = new String[numStudents+1];

    for(int i =1; i<result.length;i++){
      result[i][0] = ""+evalid; 
    }

    double a;
    double b;
    double c;
    for(int i =1; i<result.length;i++){
      a = 0.0;
      b = 0.0;
      c = 0.0;
      for(int k = 2;k<7;k++){
        a += Double.parseDouble(TA[i][k]); 
        b += Double.parseDouble(SR[i][k]); 
        c += Double.parseDouble(EA[i][k]); 
      }
      a /= 5;
      b /= 5; 
      c /= 5; 
      result[i][1]=""+i;
      result[i][2]=""+Math.round(a*100.0)/100.0;
      SelfAvg[i] = ""+Math.round(b*100.0)/100.0;
      ExAvg[i] = ""+Math.round(c*100.0)/100.0;
    }

    result[0][0] = "evalid"; 
    result[0][1] = "student"; 
    result[0][2] = "OverallAvg"; 
    result[0][3] = "Note"; 

    //for loop checks for 'exceptional conditions'
    for(int i = 1; i<result.length;i++){
      double oavg = Double.parseDouble(result[i][2]); 
      double savg = Double.parseDouble(SelfAvg[i]); 
      double eavg = Double.parseDouble(ExAvg[i]); 
      double minus;
      if(eavg < 2.5 && eavg >= 1.0){
        result[i][3] = "Low Performer"; 
      }
      else if(eavg < 3.0 && savg > eavg){
        minus = savg - eavg; 
        if(minus >= 1){
          result[i][3] = "Overconfident";
        }
        else{
          result[i][3] = "N/A";
        }
      }
      else if(eavg > 3 && savg < eavg){
        minus = eavg - savg; 
        if(minus >= 1){
          result[i][3] = "Underconfident";
        }
        else{
          result[i][3] = "N/A";
        } 
      }
      else if (eavg > 3.5 && eavg > oavg){
        minus = eavg - savg; 
        if(minus >= 0.5){
          result[i][3] = "High Performer";
        }
        else{
          result[i][3] = "N/A";
        }
      }
      else{
        result[i][3] = "N/A";
      }
    }

    return result;
  }


  // *************************************************************************
  //                          Main Test Section 
  // *************************************************************************


  //Stat Displays
  @Test
  public void TotalAverage(){
    System.out.println(); 
    System.out.println("'Total' Averages");
    String[][] TA = TotalAveragesArray(1);
    String[][] TA2 = TotalAveragesArray(2);

    System.out.println(); 
    for(int i = 0;i<TA.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+TA[i][j]+"]"); 
      }
      System.out.println(); 
    }
    for(int i = 0;i<TA2.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+TA2[i][j]+"]"); 
      }
      System.out.println(); 
    }
  }

  @Test
  public void ExclusiveAverage(){
    System.out.println(); 
    System.out.println("'Exclusive' Averages");
    String[][] EA = ExAveragesArray(1);
    String[][] EA2 = ExAveragesArray(2);

    System.out.println(); 
    for(int i = 0;i<EA.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+EA[i][j]+"]"); 
      }
      System.out.println(); 
    }
    for(int i = 0;i<EA2.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+EA2[i][j]+"]"); 
      }
      System.out.println(); 
    }
  }

  @Test
  public void StrengthsWeaknesses(){
    System.out.println(); 
    System.out.println("Strength and Weaknesses");
    String[][] SW = StrengthsArray(1);
    String[][] SW2 = StrengthsArray(2);

    System.out.println(); 
    for(int i = 0;i<SW.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+SW[i][j]+"]"); 
      }
      System.out.println(); 
    }
    for(int i = 0;i<SW2.length;i++){
      for(int j=0;j<7;j++){
        System.out.print("["+SW2[i][j]+"]"); 
      }
      System.out.println(); 
    }
  }

  @Test
  public void Notes(){
    System.out.println(); 
    System.out.println("Exceptional Conditions");
    String[][] Notes = NotesArray(1);
    String[][] Notes2 = NotesArray(2);

    System.out.println(); 
    for(int i = 0;i<Notes.length;i++){
      for(int j=0;j<4;j++){
        System.out.print("["+Notes[i][j]+"]"); 
      }
      System.out.println(); 
    }
    for(int i = 0;i<Notes2.length;i++){
      for(int j=0;j<4;j++){
        System.out.print("["+Notes2[i][j]+"]"); 
      }
      System.out.println(); 
    }
  }

  // @Test
  // public void SelfRates(){
  //   System.out.println(); 
  //   System.out.println("Self Ratings");
  //   String[][] SR = SelfRates(1);
  //   String[][] SR2 = SelfRates(2);

  //   System.out.println(); 
  //   for(int i = 0;i<SR.length;i++){
  //     for(int j=0;j<7;j++){
  //       System.out.print("["+SR[i][j]+"]"); 
  //     }
  //     System.out.println(); 
  //   }
  //   for(int i = 0;i<SR2.length;i++){
  //     for(int j=0;j<7;j++){
  //       System.out.print("["+SR2[i][j]+"]"); 
  //     }
  //     System.out.println(); 
  //   }
  // }

  //database tests
  @Test
  public void check_delete () {
    int n = -1;
    dbtest_delete();
    n = count_rows("dbtest");
    assertEquals("Response table should be empty", 0, n);
    System.out.print("delete from code functioning...");
  }

  @Test
  public void check_inserts () {
    int n = -1;
    dbtest_delete();
    dbtest_inserts();
    n = count_rows("dbtest");
    assertEquals("should now be 1", 1, n);
    System.out.println("insert from code functioning...");
  }   
  
  @Test
  public void check_CSVinsert () {
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int n = -1; 
      n = count_rows("response where evalid=2");
      assertEquals("number of rows should be array length", table.length-1, n);
    }
    catch (Exception e){
      System.out.println("(check_CSVinsert error)"); 
    }
  }

  @Test
  public void CatC(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(table[i][3].equals("C")){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where category='C'and evalid=2");
      assertEquals("count of C responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CatC DB test error)");
    }
  }

  @Test
  public void CatI(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(table[i][3].equals("I")){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where category='I'and evalid=2");
      assertEquals("count of I responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CatI DB test error)");
    }
  }

  @Test
  public void CatK(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(table[i][3].equals("K")){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where category='K'and evalid=2");
      assertEquals("count of K responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CatK DB test error)");
    }
  }

  @Test
  public void CatE(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(table[i][3].equals("E")){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where category='E'and evalid=2");
      assertEquals("count of E responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CatE DB test error)");
    }
  }

  @Test
  public void CatH(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(table[i][3].equals("H")){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where category='H'and evalid=2");
      assertEquals("count of H responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CatH DB test error)");
    }
  }

  @Test
  public void CheckStudent1(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      String student1 = table[1][1];
      for(int i = 1; i < table.length;i++){
        if(table[i][1].equals(student1)){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where student1="+student1+" and evalid=2");
      assertEquals("count of student1 responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CheckStudent1 DB test error)");
    }
  }

  @Test
  public void CheckStudent2(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      String student2 = table[1][2];
      for(int i = 1; i < table.length;i++){
        if(table[i][2].equals(student2)){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where student2="+student2+" and evalid=2");
      assertEquals("count of student2 responses from eval 2 should equal",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(CheckStudent2 DB test error)");
    }
  }

  @Test
  public void ValueInput1(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(Integer.parseInt(table[i][4]) == 1){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where val=1 and evalid=2");
      assertEquals("count of eval 2 responses of 1",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(ValueInput1 DB test error)");
    }
  }

  @Test
  public void ValueInput2(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(Integer.parseInt(table[i][4]) == 2){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where val=2 and evalid=2");
      assertEquals("count of eval 2 responses of 2",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(ValueInput2 DB test error)");
    }
  }

  @Test
  public void ValueInput3(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(Integer.parseInt(table[i][4]) == 3){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where val=3 and evalid=2");
      assertEquals("count of eval 2 responses of 3",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(ValueInput3 DB test error)");
    }
  }

  @Test
  public void ValueInput4(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(Integer.parseInt(table[i][4]) == 4){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where val=4 and evalid=2");
      assertEquals("count of eval 2 responses of 4",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(ValueInput4 DB test error)");
    }
  }

  @Test
  public void ValueInput5(){
    try{
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      //insertCSV(table);

      int catcount = 0; 
      for(int i = 1; i < table.length;i++){
        if(Integer.parseInt(table[i][4]) == 5){
          catcount++; 
        }
      }
      
      int n = -1; 
      n = count_rows("response where val=5 and evalid=2");
      assertEquals("count of eval 2 responses of 5",catcount,n); 
    }
    catch (Exception e){
      System.out.println("(ValueInput5 DB test error)");
    }
  }

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
      System.out.println("(error in columnname1)");
    }
  }

  @Test
  public void testColumnName2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("student1", table[0][1]);
    }

    catch (Exception e) {
      System.out.println("(error in columnname2)");
    }
  }

  @Test
  public void testColumnName3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("student2", table[0][2]);
    }

    catch (Exception e) {
      System.out.println("(error in columnname3)");
    }
  }  

  @Test
  public void testColumnName4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("category", table[0][3]);
    }

    catch (Exception e) {
      System.out.println("(error in column4)");
    }
  }

  @Test
  public void testColumnName5()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertEquals("value", table[0][4]);
    }

    catch (Exception e) {
      System.out.println("(error in column5)");
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
      System.out.println("(TestRatings error)");
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
      System.out.println("(testEvalPair error)");
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
      System.out.println("(vRaterID1 error)");
    }
  }

  @Test
  public void verifyRaterID2()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[2][1]) == 3);
    }

    catch (Exception e) {
      System.out.println("(vRaterID2 error)");
    }
  }

  @Test
  public void verifyRaterID3()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[3][1]) == 3);
    }

    catch (Exception e) {
      System.out.println("(vRaterID3 error)");
    }
  }

  @Test
  public void verifyRaterID4()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[3][1]) == 3);
    }

    catch (Exception e) {
      System.out.println("(vRaterID4 error)");
    }
  }

  //Verify that all Ratee-ID columns in table array match "evals.csv"
  @Test
  public void verifyRateeID1()
  {
  try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][2]) == 2);
    }

    catch (Exception e) {
      System.out.println("(vRatee1 error)");
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
      System.out.println("(vRatee2 error)");
    }
  }

  @Test
  public void verifyRateeID3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[3][2]) == 2);
    }
    catch (Exception e) {
      System.out.println("(vRatee3 error)");
    }
  }

  @Test
  public void verifyRateeID4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(Integer.parseInt(table[4][2]) == 2);
    }
    catch (Exception e) {
      System.out.println("(vRatee4 error)");
    }
  }

  //Verify that all Category columns in table array match "evals.csv"
  @Test
  public void verifyCategory1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(table[1][3].equals("C"));
    }

    catch (Exception e) {
      System.out.println("vCat1 error");
    }
  }

  @Test
  public void verifyCategory2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(table[2][3].equals("I"));
    }

    catch (Exception e) {
      System.out.println("vCat2 error");
    }
  }

  @Test
  public void verifyCategory3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(table[3][3].equals("K"));
    }

    catch (Exception e) {
      System.out.println("vCat3 error");
    }
  }

  @Test
  public void verifyCategory4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");
      assertTrue(table[4][3].equals("E"));
    }

    catch (Exception e) {
      System.out.println("vCat4 error");
    }
  }

  //Verify that all Value columns in table array match "evals.csv"
  @Test
  public void verifyValue1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][4]) == 3);
   }

    catch (Exception e) {
      System.out.println("(vVal1 error)");
    }
  }

  @Test
  public void verifyValue2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[2][4]) == 1);
   }

    catch (Exception e) {
      System.out.println("(vVal2 error)");
    }
  }

  @Test
  public void verifyValue3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[3][4]) == 4);
   }

    catch (Exception e) {
      System.out.println("(vVal3 error)");
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
      System.out.println("(vVal4 error)");
    }
  }

  //Verify that all rows are under evaluation #1
  @Test
  public void verifyRowEvalNum1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[1][0]) == 2);
   }

    catch (Exception e) {
      System.out.println("vRowEval error");
    }
  }

  @Test
  public void verifyRowEvalNum2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[2][0]) == 2);
   }

    catch (Exception e) {
      System.out.println("vRowEval2 error");
    }
  }

  @Test
  public void verifyRowEvalNum3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[3][0]) == 2);
   }

    catch (Exception e) {
      System.out.println("vRowEval3 error");
    }
  }

  @Test
  public void verifyRowEvalNum4()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");


      assertTrue(Integer.parseInt(table[4][0]) == 2);
   }

    catch (Exception e) {
      System.out.println("vRowEval4 error");
    }
  }

  //Verify row values are correct when tested together
  @Test
  public void verifyRowValues1()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[1][0]) == 2);
      assertTrue(Integer.parseInt(table[1][1]) == 3);
      assertTrue(Integer.parseInt(table[1][2]) == 2);
      assertTrue(table[1][3].equals("C"));
      assertTrue(Integer.parseInt(table[1][4]) == 3);
   }

    catch (Exception e) {
      System.out.println("(vRows1 error)");
    }
  }

  @Test
  public void verifyRowValues2()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[2][0]) == 2);
      assertTrue(Integer.parseInt(table[2][1]) == 3);
      assertTrue(Integer.parseInt(table[2][2]) == 2);
      assertTrue(table[2][3].equals("I"));
      assertTrue(Integer.parseInt(table[2][4]) == 1);
   }

    catch (Exception e) {
      System.out.println("(vRows2 error)");
    }
  }

  @Test
  public void verifyRowValues3()
  {
    try {
      PeerEvaluation PeerEval = new PeerEvaluation();
      String[][] table = PeerEval.parseCSV("evals.csv");

      assertTrue(Integer.parseInt(table[3][0]) == 2);
      assertTrue(Integer.parseInt(table[3][1]) == 3);
      assertTrue(Integer.parseInt(table[3][2]) == 2);
      assertTrue(table[3][3].equals("K"));
      assertTrue(Integer.parseInt(table[3][4]) == 4);
   }

    catch (Exception e) {
      System.out.println("(vRows3 error)");
    }
  }
}









