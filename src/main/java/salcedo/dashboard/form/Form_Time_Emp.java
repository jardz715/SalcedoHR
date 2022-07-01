package salcedo.dashboard.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.DBQueries;
import main.ExcelDTR;
import net.proteanit.sql.DbUtils;

public class Form_Time_Emp extends javax.swing.JPanel {

    Connection conn;
    int userid;
    JTable dataTable;
    
    public Form_Time_Emp(Connection temp, int ID) throws SQLException{
        conn = temp;
        userid = ID;
        initComponents();
        jTable.addTableStyle(jScrollPane2);
        getDataFromDB(conn, userid);
        dataTable = new JTable(startTable(getDataFromDB(conn, userid)));
        initTable(getDataFromDB(conn, userid));
        centerTableComponents();
        dataTable.setEnabled(false);
    }
    
     
    protected ResultSet getDataFromDB(Connection conn, int userID){
        DBQueries query = new DBQueries();
        
        ResultSet rs = query.getRow(conn, "userIn, userOut, userAftIn, userAftOut", "UserTable", "userID =" + userID);
        ResultSet rs2 = query.getRow(conn, "timeIn", "TimeTable", "userID =" + userID);
        ResultSet rs3 = query.getRow(conn, "timeHistIn as 'Time In', timeHistOut as 'Time Out', timeHistDiff as 'Total Time In Minutes', timeHistOT as 'Overtime', timeHistUT as 'Undertime', timeHistType as 'AM/PM'", "TimeHistoryTable", "userID =" + userid + " ORDER by timeHistID DESC");
        try{
            if(rs.next()){
                timeInLabel.setText("Time In: " + rs.getString("userIn") + "/" + rs.getString("userAftIn"));
                timeOutLabel.setText("Time Out: " + rs.getString("userOut") + "/" + rs.getString("userAftOut"));
                if(rs2.next() != false){
                    currentLabel.setText("Current: " + rs2.getString("timeIn"));
                }else{
                    currentLabel.setText("Current: Not Timed In.");
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(Form_Profile_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs3;
    }
    
    protected DefaultTableModel startTable(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
    
    private void initTable(ResultSet rs) {
        try {
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            int emptyCellCount = 10 - jTable.getRowCount();
            if(jTable.getRowCount() < 10)
                for(int i=0; i<emptyCellCount; i++)
                    model.addRow(new Object[] {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTable.setEnabled(false);
    }
    
    private void centerTableComponents() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableModel tableModel = jTable.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            jTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
        jTable.setEnabled(false);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeInLabel = new javax.swing.JLabel();
        timeOutLabel = new javax.swing.JLabel();
        currentLabel = new javax.swing.JLabel();
        printButton = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new salcedo.dashboard.swing.Table();
        refreshButton = new javax.swing.JToggleButton();
        dateBox = new javax.swing.JComboBox<>();
        yearChooser = new com.toedter.calendar.JYearChooser();

        setBackground(new java.awt.Color(255, 255, 255));

        timeInLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        timeInLabel.setForeground(new java.awt.Color(102, 102, 102));
        timeInLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeInLabel.setText("Time In:");

        timeOutLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        timeOutLabel.setForeground(new java.awt.Color(102, 102, 102));
        timeOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeOutLabel.setText("Time Out:");

        currentLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        currentLabel.setForeground(new java.awt.Color(102, 102, 102));
        currentLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        currentLabel.setText("Current:");

        printButton.setBackground(new java.awt.Color(153, 153, 153));
        printButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TimeIn", "TimeOut", "TotalTimeInMinutes", "Overtime"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setResizable(false);
            jTable.getColumnModel().getColumn(1).setResizable(false);
            jTable.getColumnModel().getColumn(2).setResizable(false);
            jTable.getColumnModel().getColumn(3).setResizable(false);
        }

        refreshButton.setBackground(new java.awt.Color(153, 153, 153));
        refreshButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        refreshButton.setText("Refresh Table");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        dateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        dateBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeInLabel)
                        .addGap(101, 101, 101)
                        .addComponent(timeOutLabel)
                        .addGap(69, 69, 69)
                        .addComponent(currentLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(dateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(dateBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        ExcelDTR dtr = new ExcelDTR();
        try {
            dtr.ExcelDTR(conn, userid, Integer.toString(dateBox.getSelectedIndex()+1), dateBox.getSelectedItem().toString(), yearChooser.getYear());
        } catch (IOException | SQLException | ParseException ex) {
            Logger.getLogger(Form_Time_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        initTable(getDataFromDB(conn, userid));
        try {
            dataTable = new JTable(startTable(getDataFromDB(conn, userid)));
        } catch (SQLException ex) {
            Logger.getLogger(Form_Time_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
        centerTableComponents();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void dateBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateBoxActionPerformed
    
    public void export(JTable table, File file){
    try
    {
      TableModel m = table.getModel();
        try (FileWriter fw = new FileWriter(file)) {
            for(int i = 0; i < m.getColumnCount(); i++){
                fw.write(m.getColumnName(i) + "\t");
            }
            fw.write("\n");
            for(int i=0; i < m.getRowCount(); i++) {
                for(int j=0; j < m.getColumnCount(); j++) {
                    if(m.getValueAt(i, j) != null){
                        fw.write(m.getValueAt(i,j).toString()+"\t");
                    }else{
                        fw.write(" ");
                    }
                    
                }
                fw.write("\n");
            } }
    }
    catch(IOException e){ System.out.println(e); }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentLabel;
    private javax.swing.JComboBox<String> dateBox;
    private javax.swing.JScrollPane jScrollPane2;
    private salcedo.dashboard.swing.Table jTable;
    private javax.swing.JToggleButton printButton;
    private javax.swing.JToggleButton refreshButton;
    private javax.swing.JLabel timeInLabel;
    private javax.swing.JLabel timeOutLabel;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
