package main;
// 4 SQL method imports + io.File for checking if DB Exists
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    
	public Connection dbCheck() throws SQLException{
            
		// Initialize DB path
		File file = new File ("resources/test.db");
                String userN = "root";
                String userP = "";
		
		// No DB? Create
		if(!file.exists()) {
			
			try {
                            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/salcedo2", userN, userP);

                            //Add methods for creating all tables here. Preferably a different class for recycling purposes
                            DBQueries query = new DBQueries();
                            query.createTables(conn);
		        
		        return conn;
		    } catch ( Exception e ) {
		        e.printStackTrace();
		        System.exit(0);
                        //System.out.println("Exception");
		    }   
			
			// return statement will never reach here unless errors.
		    return null;
		    
		// Yes DB? Connect    
		} else {	
			try {
				return DriverManager.getConnection("jdbc:mysql://localhost/salcedo2", userN, userP);
				
		    } catch ( Exception e ) {
		        e.printStackTrace();
		        System.exit(0);
		    }
			
			// return statement will never reach here unless errors.
			return null;
		}
	}
	
	
}