package main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDTR {
    
    ResultSet userData, timeData;
    boolean isUsed = true;
    int total = 0;
    int counter = 1;
    ToBase64 base = new ToBase64();
    
    public void ExcelDTR(Connection conn, int ID, String Date, String Month, int Year) throws FileNotFoundException, IOException, SQLException, ParseException{
        //take user data and time data
        DBQueries query = new DBQueries();
        if(Date.length() == 1){
            Date = "0" + Date;
        }
        userData = query.getRow(conn, "*", "UserTable", "userid = " + ID );
        timeData = query.getRow(conn, "timeHistType, timeHistUT, DATE_FORMAT(timeHistin, '%d') as timeDay, DATE_FORMAT(timeHistIn, '%H:%i:%S') as timeHistIn, DATE_FORMAT(timeHistOut, '%H:%i:%S') as timeHistOut", "TimeHistoryTable", "DATE_FORMAT(timeHistIn, '%m-%Y') = '" + Date +  "-" + Year + "' AND userID = " + ID);
        
        String decode = "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";
        byte[] decoded = Base64.getDecoder().decode(decode.getBytes());
        
        //Resets file back to start everytime
        File file = new File("resources\\documents\\DTR_" + ID + ".xlsx");
        file.setWritable(true);
        try (OutputStream stream = new FileOutputStream(file)) {
            stream.write(decoded);
        }
        
        FileInputStream inputStream = new FileInputStream("resources\\documents\\DTR_" + ID + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("DTR");
        
        //Full Name
        XSSFRow row = sheet.getRow(3);
        XSSFCell cell = row.getCell(0);
        if(userData.next() != false){
            cell.setCellValue(userData.getString("userlastn") + ", " + userData.getString("userfirstn") + " " + userData.getString("usermiddlen"));
        }
        
        //Year and Month
        row = sheet.getRow(6);
        cell = row.getCell(0);
        cell.setCellValue("For the Month of: " + Month + " "+ Year);
        
        //Arrival and Departure
        row = sheet.getRow(7);
        cell = row.getCell(0);
        cell.setCellValue("Office Hours    Arrival A.M. " + userData.getString("userin") + "          P.M. " + userData.getString("userAftIn"));
        row = sheet.getRow(8);
        cell = row.getCell(0);
        cell.setCellValue("               Departure A.M." + userData.getString("userout") + "          P.M." + userData.getString("userAftOut"));
        
        //Time record
        
        for(int r = 11; r <= 41; r++){
            int undertime = 0;
            row = sheet.getRow(r);
            if(isUsed){
                if(timeData.next() != false){
                    r = dateRun(row, cell, sheet, r, undertime);
                }
            }else{
                r = dateRun(row, cell, sheet, r, undertime);
            }
            
        }
        
        //Total undertime
        row = sheet.getRow(42);
        cell = row.getCell(5);
        cell.setCellValue(total + " Minute/s");
        
        
        //Write and Close
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        inputStream.close();
        outputStream.close();
        Desktop.getDesktop().open(file);
    }
    
    protected int dateRun(XSSFRow row, XSSFCell cell, XSSFSheet sheet, int r, int undertime) throws SQLException, ParseException{
        while(timeData.getInt("timeDay") != counter){
            counter++;
            r++;
            row = sheet.getRow(r);
        }
        if(timeData.getInt("timeDay") == counter){
            if(timeData.getString("timeHistType").equals("Morning")){
                cell = row.getCell(1);
                cell.setCellValue(convertTime(timeData.getString("timeHistIn")));
                cell = row.getCell(2);
                cell.setCellValue(convertTime(timeData.getString("timeHistOut")));
                undertime += timeData.getInt("timeHistUT");
                total += timeData.getInt("timeHistUT");
                if(timeData.next() != false){
                    if(timeData.getInt("timeDay") == counter && timeData.getString("timeHistType").equals("Afternoon")){
                        cell = row.getCell(3);
                        cell.setCellValue(convertTime(timeData.getString("timeHistIn")));
                        cell = row.getCell(4);
                        cell.setCellValue(convertTime(timeData.getString("timeHistOut"))); 
                        undertime += timeData.getInt("timeHistUT");
                        total += timeData.getInt("timeHistUT");
                        cell = row.getCell(5);
                        cell.setCellValue(undertime + " mins");
                        counter++;
                        isUsed = true;
                    }else{
                        cell = row.getCell(5);
                        cell.setCellValue(undertime + " mins");
                        counter++;
                        isUsed = false;
                    }
                }else{
                    cell = row.getCell(5);
                    cell.setCellValue(undertime/60 + " mins" );
                    counter++;
                    isUsed = true;
                }
            }else{
                cell = row.getCell(3);
                cell.setCellValue(convertTime(timeData.getString("timeHistIn")));
                cell = row.getCell(4);
                cell.setCellValue(convertTime(timeData.getString("timeHistOut")));
                cell = row.getCell(5);
                cell.setCellValue(timeData.getInt("timeHistUT") + " mins" );
                total += timeData.getInt("timeHistUT");
                counter++;
                isUsed = true;
            }
        }
        return r;
    }
    
    private String convertTime(String time) throws ParseException{
        SimpleDateFormat militaryTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat standardTime = new SimpleDateFormat("hh:mm a");
        Date DT = militaryTime.parse(time);
        return standardTime.format(DT);
    }
    
}
