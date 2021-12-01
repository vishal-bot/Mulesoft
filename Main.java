package Main;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
 
public class Main {
	
	public static Connection connect() {  
	       Connection conn = null;  
	       try {  
	           // db parameters  
	           String url = "jdbc:sqlite:C:/sqlite/MovieDB.db";  
	           // create a connection to the database  
	           conn = DriverManager.getConnection(url);  
	             
//	           System.out.println("Connection to SQLite has been established.");  
	             
	       } catch (SQLException e) {  
	           System.out.println(e.getMessage());  
	       }    

	       return conn;
	}
	
	public static void createNewDatabase(String fileName) {  
		   
        String url = "jdbc:sqlite:C:/sqlite/" + fileName;  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();   
//                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static void createNewTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://sqlite/MovieDB.db";  
          
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " Movie_name text NOT NULL,\n"  
                + " Lead_actor text NOT NULL,\n"
                + " Lead_actress text NOT NULL,\n"
                + " Director text NOT NULL,\n"
                + " Year_of_Release integer NOT NULL \n"
                + " );";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	public static void insert(String Movie_name, String Lead_actor, String Lead_actress, String Director, int Year_of_Release) {  
        String sql = "INSERT INTO movies(Movie_name, Lead_actor, Lead_actress, Director, Year_of_Release) VALUES(?,?,?,?,?)";  
   
        try{  
            Connection conn = connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, Movie_name);  
            pstmt.setString(2, Lead_actor); 
            pstmt.setString(3, Lead_actress); 
            pstmt.setString(4, Director); 
            pstmt.setDouble(5, Year_of_Release);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static void selectAll(){  
        String sql = "SELECT * FROM movies";  
          
        try {  
            Connection conn = connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("id") +  "\t" +   
                                   rs.getString("Movie_name") + "\t" +  
                                   rs.getString("Lead_actor") + "\t" + 
                                   rs.getString("Lead_actress") + "\t" + 
                                   rs.getString("Director") + "\t" + 
                                   rs.getInt("Year_of_Release"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connect(); 
		createNewDatabase("MovieDB.db"); 
		createNewTable();
		insert("3 Idiots","Aamir Khan","Kareena Kapoor","Rajkumar Hirani", 2009);
		insert("96","Vijay Sethupathi","Trisha Krishnan","C. Prem Kumar", 2018);
		selectAll(); // Select query 
	}
}
