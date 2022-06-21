package main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDTR {
    
    ResultSet userData, timeData;
    boolean isUsed = true;
    int total = 0;
    int counter = 1;
    
    public void ExcelDTR(Connection conn, int ID, String Date, String Month, int Year) throws FileNotFoundException, IOException, SQLException, ParseException{
        //take user data and time data
        DBQueries query = new DBQueries();
        if(Date.length() == 1){
            Date = "0" + Date;
        }
        userData = query.getRow(conn, "*", "UserTable", "userID = " + ID );
        timeData = query.getRow(conn, "timeHistType, timeHistUT, strftime('%d', timeHistin) as timeDay, strftime('%H:%M:%S', timeHistIn) as timeHistIn, strftime('%H:%M:%S', timeHistOut) as timeHistOut", "TimeHistoryTable", "strftime('%m-%Y', timeHistIn) = '" + Date +  "-" + Year + "' AND userID = " + ID);
        
        //Resets file back to start everytime
        File file = new File("resources\\documents\\DTR_" + ID + ".xlsx");
        String excelFilePath = "resources\\documents\\DTR.xlsx";
        FileUtils.copyFile(new File(excelFilePath), file);
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("DTR");
        
        //Full Name
        XSSFRow row = sheet.getRow(3);
        XSSFCell cell = row.getCell(0);
        cell.setCellValue(userData.getString("userLastN") + ", " + userData.getString("userFirstN") + " " + userData.getString("userMiddleN"));
        
        //Year and Month
        row = sheet.getRow(6);
        cell = row.getCell(0);
        cell.setCellValue("For the Month of: " + Month + " "+ Year);
        
        //Arrival and Departure
        row = sheet.getRow(7);
        cell = row.getCell(0);
        cell.setCellValue("Office Hours    Arrival A.M. " + userData.getString("userIn") + "          P.M. " + userData.getString("userAftIn"));
        row = sheet.getRow(8);
        cell = row.getCell(0);
        cell.setCellValue("               Departure A.M." + userData.getString("userOut") + "          P.M." + userData.getString("userAftOut"));
        
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
