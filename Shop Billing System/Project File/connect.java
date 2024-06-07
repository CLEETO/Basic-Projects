import java.io.*;
import java.util.*;
import java.sql.*;  
public class connect
{
    Connection con;  
    Statement stmt;
    connect()
    {
     try
     {
     Class.forName("com.mysql.jdbc.Driver");
     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cp","root","MyNewPass4#"); 
     System.out.println(con);
     if(con==null)
       System.out.println("no");
     else
       System.out.println("yes");
     stmt=con.createStatement();
     }
     catch(Exception e){ System.out.println(e);}  
    }
    public void execute(String s)
    {
      try
      {      
         stmt.executeUpdate(s);
       }
       catch(Exception e){ System.out.println(e);}  
    }
    public ResultSet execute2(String s)
    {
        ResultSet rs=null;
        try
        {
          rs=stmt.executeQuery(s);  
        }
        catch(Exception e){ System.out.println(e);}  
        return rs;
    }
}