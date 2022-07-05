package salcedo.dashboard.form;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import main.DBQueries;
import main.ToBase64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Form_Doc_Adm extends javax.swing.JPanel {

    DBQueries query = new DBQueries();
    Connection conn;
    int userid;
   
    
    public Form_Doc_Adm(Connection temp, int ID) throws SQLException{
        conn = temp;
        userid = ID;
        initComponents();
        
        getDataFromDB(conn);
    }
    
    protected void getDataFromDB(Connection conn){
        ResultSet rs = query.selectFromTable(conn, "dTemplateTitle as Document_Title", "DocTemplateTable");
        try{
            jTable = new JTable(startTable(rs));
            jTable.setDefaultEditor(Object.class, null);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jScrollPane2.setViewportView(jTable);
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
                String[] options = new String[] {"Open", "Delete", "Close"};
                int response = JOptionPane.showOptionDialog(null, "File: " + jTable.getValueAt(row, column), "Document", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
                ResultSet rs2 = query.getRow(conn, "dTemplatePath, dTemplateBase", "DocTemplateTable", "dTemplateTitle = '" + jTable.getValueAt(row, column) + "'");
                //base64 need
                ToBase64 base = new ToBase64();
                
                if(response == 0){
                   try {
                       if(rs2.next() != false){
                           base.decodeFile(rs.getBytes("dTemplateBase"), rs.getString("dTemplatePath"));
                           File file = new File(rs2.getString("dTemplatePath"));
                           file.setWritable(true);
                           Desktop.getDesktop().open(file);
                       }
                   } catch (SQLException | IOException ex) {
                       Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if (response == 1){
                    int response2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to DELETE this document? Note: This will delete all employee " + jTable.getValueAt(row, column) + " files." , "DELETE", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response2 == 0){
                        ResultSet rs3 = query.getRow(conn, "docPath", "DocumentTable", "docTitle LIKE '" + jTable.getValueAt(row, column) + "%'");
                        try {
                            while(rs3.next() != false){
                                File file1 = new File(rs3.getString("docPath"));
                                if(file1.exists()){
                                    file1.delete();
                                }
                            }
                            rs2.next();
                            File file = new File(rs2.getString("dTemplatePath"));
                            if(file.exists()){
                                file.delete(); 
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        query.deleteRows(conn, "DocumentTable", "docTitle LIKE '" + jTable.getValueAt(row, column) + "%'");
                        query.deleteRows(conn, "DocTemplateTable", "dTemplateTitle = '" + jTable.getValueAt(row, column) + "'");
                        JOptionPane.showMessageDialog(null, "File deleted.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
            getDataFromDB(conn);
            }
         }
        });
        
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
        searchField = new javax.swing.JTextField();
        addButton = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchButton = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
        ));
        jScrollPane2.setViewportView(jTable);

        attendanceLabel.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        attendanceLabel.setForeground(new java.awt.Color(102, 102, 102));
        attendanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attendanceLabel.setText("Documents");

        searchLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(102, 102, 102));
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchLabel.setText("Search:");

        searchField.setBackground(new java.awt.Color(204, 204, 204));
        searchField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        searchField.setToolTipText("Enter Username");
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(153, 153, 153));
        addButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        ));
        jScrollPane3.setViewportView(jTable1);

        searchButton.setBackground(new java.awt.Color(153, 153, 153));
        searchButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // note, if they delete the file in the directory, DB still consider it as existing.
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        List<String> list = new ArrayList<String>();
        JFileChooser addFile = new JFileChooser();
        int res = addFile.showDialog(null, "Add");
        if(res == JFileChooser.APPROVE_OPTION){
            File pathFile = new File(addFile.getSelectedFile().getAbsolutePath());
            String filename = pathFile.getName();
            File copyFile = new File("resources/documents/" + filename);
            try {
                list.add(copyFile.getPath());
                list.add(FilenameUtils.removeExtension(filename));
                
                //base64 here need
                ToBase64 base = new ToBase64();
                String encoded = base.encodeFile(addFile.getSelectedFile().getAbsolutePath());
                if(query.isStrUnique(conn, list.get(1), "dTemplateTitle", "DocTemplateTable")){
                    FileUtils.copyFile(pathFile, copyFile);
                    query.insertDocTemplate(conn, list, encoded);
                }else{
                    FileUtils.copyFile(pathFile, copyFile);
                    query.updateRow(conn, "DocTemplateTable", "dTemplateBase = '" + encoded + "'", "dTemplateTitle = '" + list.get(1) + "'");
                    JOptionPane.showMessageDialog(null, "File already added to the system, File Updated.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
            }catch (IOException e) {
                e.printStackTrace();
            }
            getDataFromDB(conn);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String username = searchField.getText().toLowerCase();
        ResultSet rs = query.getRow(conn, "userID", "UserTable", "username = '" + username + "'");
        try{
            if(rs.next() != false){
                String userDocID = rs.getString("userID");
                ResultSet rs2 = query.getRow(conn, "docTitle as Document_Title, docSubmitted as Submitted, docValidated as HR_Signed", "DocumentTable", "userID = " + userDocID );
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
            }else{
                JOptionPane.showMessageDialog(null, "Username cannot be found in the Database.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Form_Profile_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jTable1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {     // to detect doble click events
                JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                int column = 0;
                String[] options = new String[] {"Open", "Reset", "Delete", "Validate", "Close"};
                ResultSet rs = query.getRow(conn, "docID, docPath, docSubmitted, docValidated, docBase", "DocumentTable", "docTitle = '" + jTable1.getValueAt(row, column) + "'");
                int response = JOptionPane.showOptionDialog(null, "File: " + jTable.getValueAt(row, column), "Document", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[4]);
                
                //base64 here need
                ToBase64 base = new ToBase64();
                if(response == 0){
                   try {
                       if(rs.next() != false){
                           base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                           File file = new File(rs.getString("docPath"));
                           file.setWritable(true);
                           Desktop.getDesktop().open(file);
                       }
                   } catch (SQLException | IOException ex) {
                       Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if (response == 1){
                    int response2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset " + jTable1.getValueAt(row, column) + "?." , "RESET", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response2 == 0 ){
                        try {
                            rs.next();
                            base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                            ResultSet rs2 = query.getRow(conn, "DocTemplateTable.dTemplatePath, DocumentTable.docPath",
                                    "DocTemplateTable INNER JOIN DocumentTable ON DocTemplateTable.dTemplateID = DocumentTable.dtemplateid",
                                    "DocumentTable.docID = " + rs.getString("docID"));
                            rs2.next();
                            FileUtils.copyFile(new File(rs2.getString("dTemplatePath")), new File(rs2.getString("docPath")));
                            query.updateRow(conn, "DocumentTable", "docSubmitted = 0", "docID = " + rs.getString("docID"));
                            query.updateRow(conn, "DocumentTable", "docValidated = 0", "docID = " + rs.getString("docID"));
                            JOptionPane.showMessageDialog(null, "Document Successfully Reset.", "Confirm", JOptionPane.INFORMATION_MESSAGE);
                            jTable1.setValueAt("No", row, 1);
                            jTable1.setValueAt("No", row, 2);
                            } catch (SQLException | IOException ex) {
                                Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else if (response == 2){
                    int response2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to DELETE this document? File: " + jTable1.getValueAt(row, column) , "DELETE", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response2 == 0){
                        ResultSet rs3 = query.getRow(conn, "docPath", "DocumentTable", "docTitle = '" + jTable1.getValueAt(row, column) + "'");
                        try {
                            rs3.next();
                            File file = new File(rs3.getString("docPath"));
                            if(file.exists())
                                file.delete(); 
                        } catch (SQLException ex) {
                            Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        query.deleteRows(conn, "DocumentTable", "docTitle = '" + jTable1.getValueAt(row, column) + "'");
                        ((DefaultTableModel)jTable1.getModel()).removeRow(row);
                        JOptionPane.showMessageDialog(null, "File deleted.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if (response == 3){
                    try {
                        rs.next();
                        base.decodeFile(rs.getBytes("docBase"), rs.getString("docPath"));
                        if(rs.getBoolean("docSubmitted") && !rs.getBoolean("docValidated")){
                            query.updateRow(conn, "DocumentTable", "docValidated = 1", "docID = " + rs.getString("docID"));
                            jTable1.setValueAt("Yes", row, 2);
                            JOptionPane.showMessageDialog(null, "Document Successfully Validated.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        }else
                            JOptionPane.showMessageDialog(null, "Document is either already validated, or not submitted.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_Doc_Emp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Form_Doc_Adm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            getDataFromDB(conn);
            }
         }
        });
        
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            ActionEvent e = null;
            searchButtonActionPerformed(e);
        }
    }//GEN-LAST:event_searchFieldKeyPressed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addButton;
    private javax.swing.JLabel attendanceLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    // End of variables declaration//GEN-END:variables
}
