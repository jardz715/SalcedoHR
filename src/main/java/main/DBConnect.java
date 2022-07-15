package main;
// 4 SQL method imports + io.File for checking if DB Exists
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    
	public Connection dbCheck() throws SQLException{
            
		// Initialize DB path
                String server = "jdbc:mysql://db4free.net:3306";
                String userN = "salcedohr";
                String userP = "salcedo004";
                
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    Connection conn = DriverManager.getConnection(server, userN, userP);

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
	
	
	} 
	
}