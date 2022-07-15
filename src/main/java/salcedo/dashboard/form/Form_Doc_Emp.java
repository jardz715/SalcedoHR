package salcedo.dashboard.form;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import main.DBQueries;
import main.ToBase64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Form_Doc_Emp extends javax.swing.JPanel {

    DBQueries query = new DBQueries();
    Connection conn;
    int userid;
   
    
    public Form_Doc_Emp(Connection temp, int ID) throws SQLException{
        conn = temp;
        userid = ID;
        initComponents();
        
        getDataFromDB(conn);        
    }
    
    protected void getDataFromDB(Connection conn){
        ResultSet rs = query.selectFromTable(conn, "dTemplateTitle as Document_Title", "DocTemplateTable");
        ResultSet rs2 = query.getRow(conn, "docTitle as Document_Title, docSubmitted as Submitted, docValidated as HR_Signed", "DocumentTable", "userID = " + userid );
        try{
            DefaultTableModel model = startTable(rs);
            jTable = new JTable(model);
            jTable.setDefaultEditor(Object.class, null);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jScrollPane2.setViewportView(jTable);
            DefaultTableModel model1 = startTable(rs2);
            jTable1 = new JTable(model1);
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jScrollPane3.setViewportView(jTable1);
            for(int i = 1; i <= model1.getRowCount(); i++){
                for(int j = 1; j <= model1.getColumnCount(); j++){
                    if(j == 1){
                    }else{
                        if(model1.getValueAt(i-1,j-1).equals(false)){
                            model1.setValueAt("No", i-1, j-1);
                        }else{
                            model1.setValueAt("Yes", i-1, j-1);
                        }
                    }
                }
            }
        }catch (SQLException e) {  
            e.printStackTrace();
        }
        
        jTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {     // to detect doble click events
                JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                int column = 0;
                String[] options = new String[] {"Open", "Duplicate Copy", "Close"};
                int response = JOptionPane.showOptionDialog(null, "File: " + jTable.getValueAt(row, column), "Document", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
                ResultSet rs2 = query.getRow(conn, "dTemplatePath, dTemplatebase", "DocTemplateTable", "dTemplateTitle = '" + jTable.getValueAt(row, column) + "'");
                
                //base64 need
                ToBase64 base = new ToBase64();
                
                if(response == 0){
                    try {
                       if(rs2.next() != false){
                           base.decodeFile(rs2.getBytes("dTemplatebase"), rs2.getString("dTemplatePath"));
                           File file = new File(rs2.getString("dTemplatePath"));
                           file.setWritable(false);
                           Desktop.getDesktop().open(file);
                       }
                    } catch (SQLException | IOException ex) {
                       Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(response == 1){
                    try {
                        boolean isNotExisting = true;
                        ResultSet check = query.getRow(conn, "*", "DocumentTable", "userID = " + userid);
                        String compare = jTable.getValueAt(row, column)+ "_" + userid;
                        while(check.next() != false){
                            if(compare.equals(check.getString("docTitle")))
                                isNotExisting = false;
                        }
                        
                        if(isNotExisting){
                            // base 64
                            if(rs2.next() != false){
                                base.decodeFile(rs2.getBytes("dTemplatebase"), rs2.getString("dTemplatePath"));
                            }
                            
                            // we add a document for ourselves
                            ResultSet rs = query.getRow(conn, "dTemplateID, dTemplatePath, dTemplateTitle", "DocTemplateTable", "dTemplateTitle = '" + jTable.getValueAt(row, column) + "'");
                            File pathFile = null;
                            if(rs.next() != false){
                                pathFile = new File(rs.getString("dTemplatePath"));
                            }
                            String extension = FilenameUtils.getExtension(rs.getString("dTemplatePath"));
                            String copyFilePath = "resources/documents/" + rs.getString("dTemplateTitle") + "_" + userid + "." + extension;
                            File copyFile = new File(copyFilePath);
                            List<String> list = new ArrayList<String>();
                            list.add(Integer.toString(userid));
                            list.add(rs.getString("dTemplateID"));
                            list.add(copyFile.getPath());
                            list.add(FilenameUtils.removeExtension(copyFile.getName()));
                            list.add(Integer.toString(0));
                            list.add(Integer.toString(0));
                            FileUtils.copyFile(pathFile, copyFile);
                            String encoded = base.encodeFile(copyFilePath);
                            list.add(encoded);
                            query.insertDocument(conn, list);
                            
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "You already have a duplicate of this file.", "Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            getDataFromDB(conn);
            }
         }
        });
        
        jTable1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {     // to detect doble click events
                JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                int column = 0;
                String[] options = new String[] {"Open", "Reset", "Submit", "Close"};
                int response = JOptionPane.showOptionDialog(null, "File: " + jTable1.getValueAt(row, column), "Document", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
                ResultSet rs = query.getRow(conn, "docID, docPath, docSubmitted, docBase", "DocumentTable", "docTitle = '" + jTable1.getValueAt(row, column) + "'");
                
                //base64 need
                ToBase64 base = new ToBase64();
                
                if(response == 0){
                   try {
                       if(rs.next() != false){
                           File file = new File(rs.getString("docPath"));
                           if(!file.exists()){
                               base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                           }  
                            if(rs.getBoolean("docSubmitted")){
                                file.setWritable(false);
                            }else{
                                file.setWritable(true);
                            }
                            Desktop.getDesktop().open(file);
                           
                       }
                   } catch (SQLException | IOException ex) {
                       Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if(response == 1){
                    try {
                        rs.next();
                        if(rs.getBoolean("docSubmitted")){
                            int response3 = JOptionPane.showConfirmDialog(null, "Resetting file will remove file's submissions. Do you wish to continue?." , "RESET", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(response3 == 0){
                                query.updateRow(conn, "DocumentTable", "docSubmitted = 0", "docID = " + rs.getString("docID"));
                                query.updateRow(conn, "DocumentTable", "docValidated = 0", "docID = " + rs.getString("docID"));
                                int response2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset " + jTable1.getValueAt(row, column) + " as default?." , "RESET", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if(response2 == 0 ){
                                    base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                                    launchReset(rs);
                                }
                            }
                        }else{
                            int response2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset " + jTable1.getValueAt(row, column) + " as default?." , "RESET", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if(response2 == 0 )
                                    base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                                    launchReset(rs);
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else if(response == 2){
                    try {
                        rs.next();
                        File file = new File(rs.getString("docPath"));
                        if(file.exists()){
                            if(!rs.getBoolean("docSubmitted")){
                                String newBase = base.encodeFile(rs.getString("docPath"));
                                query.updateRow(conn, "DocumentTable", "docSubmitted = 1, docBase = '" + newBase + "'", "docID = " + rs.getString("docID"));
                            }else
                                JOptionPane.showMessageDialog(null, "You already have submitted this document.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "File is not currently on your local storage. Please open the file first to make your own copy.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            getDataFromDB(conn);
            }
         }
        });
    }
    
    protected void launchReset(ResultSet rs){
        
        try {
        ResultSet rs2 = query.getRow(conn, "DocTemplateTable.dTemplatePath, DocumentTable.docPath, DocTemplateTable.dTemplateBase",
                "DocTemplateTable INNER JOIN DocumentTable ON DocTemplateTable.dTemplateID = DocumentTable.dtemplateid",
                "DocumentTable.docID = " + rs.getString("docID"));
        rs2.next();
        String newBase64 = rs2.getString("dTemplateBase");
        query.updateRow(conn, "DocumentTable", "docBase = '" + newBase64 +"'", "docID = " + rs.getString("docID"));
        File file = new File(rs2.getString("docPath"));
        file.setWritable(true);
        FileUtils.copyFile(new File(rs2.getString("dTemplatePath")), file);
        JOptionPane.showMessageDialog(null, "Document Successfully Reset.", "Confirm", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        attendanceLabel = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
        ));
        jScrollPane2.setViewportView(jTable);

        attendanceLabel.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        attendanceLabel.setForeground(new java.awt.Color(102, 102, 102));
        attendanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attendanceLabel.setText("Document Templates");

        searchLabel.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(102, 102, 102));
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchLabel.setText("Files");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        ));
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addGap(0, 2, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attendanceLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel searchLabel;
    // End of variables declaration//GEN-END:variables
}
