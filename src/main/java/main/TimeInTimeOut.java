package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// DEFAULT SQLITE DATETIME FORMAT YYYY-MM-DD HH:MM:SS
// DEFAULT SQLITE TIME FORMAT HH:MM:SS

public class TimeInTimeOut {

    //Modified
    public void timeIn(Connection conn, int ID, String time) {
        DBQueries query = new DBQueries();
        ResultSet rs = query.getRow(conn, "*", "UserTable", "userID = '" + ID + "'");
        List<String> timeList = new ArrayList<String>();
        try {
            rs.next();
            timeList.add(rs.getString("userID"));
            timeList.add(rs.getString("userIn"));
            timeList.add(rs.getString("userOut"));
            timeList.add(rs.getString("userAftIn"));
            timeList.add(rs.getString("userAftOut"));
            query.insertTimeIn(conn, timeList, time);
            
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

    }
	
    public void timeOut(Connection conn, int ID) {
        DBQueries query = new DBQueries();
        query.insertTimeOut(conn, ID);
        query.transferToTimeHistory(conn, ID);
    }
    
    public void forceTimeOut(Connection conn, int ID) {
        DBQueries query = new DBQueries();
        query.forceTimeOut(conn, ID);
        query.transferToTimeHistory(conn, ID);
    }
}
