package salcedo.dashboard.main;

import salcedo.dashboard.event.EventMenuSelected;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import main.DBConnect;
import main.DBQueries;
import main.Main;
import salcedo.dashboard.form.Form_Doc_Adm;
import salcedo.dashboard.form.Form_Employees_Admin;
import salcedo.dashboard.form.Form_Profile_Adm;
import salcedo.dashboard.form.Form_Reg_Adm;
import salcedo.dashboard.form.Form_Time_Adm;

public class Main_Admin extends javax.swing.JFrame {
    
    int userid;
    Connection conn;
    final String TABLE_NAME = "UserTable";
    
    public Main_Admin(Connection temp, int ID) {
        userid = ID;
        conn = temp;
        initComponents();
        setBackground(new Color(0,0,0,0));
        jFrame1.setLocationRelativeTo(null);
        menu_Admin.initMoving(Main_Admin.this);
        menu_Admin.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    try {
                        setForm(new Form_Profile_Adm(conn, userid));
                        delVisible(userid);
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 1) {
                    try {
                        setForm(new Form_Employees_Admin(conn));
                        delButton.setVisible(false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if (index == 2){
                    try {
                        setForm(new Form_Reg_Adm(conn));
                        delButton.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 3){
                    try {
                        setForm(new Form_Time_Adm(conn, userid));
                        delButton.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 4){
                    try {
                        setForm(new Form_Doc_Adm(conn, userid));
                        delButton.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 11){
                    int response = JOptionPane.showConfirmDialog(rootPane,
                    "Reset Database Connection?",
                    "RESET",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(response == 0)
                    {
                        DBConnect dbc = new DBConnect();
                        try {
                            conn = dbc.dbCheck();
                            JOptionPane.showMessageDialog(null, "Connection Refreshed.", "REFRESH", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else if (index == 12){
                    int response = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to log out?",
                    "EXIT",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(response == 0)
                    {
                        dispose();
                        try {
                            conn.close(); //closes connection to avoid DB lock 
                            Main m = new Main();
                            m.setVisible(true);
                            m.setLocationRelativeTo(null);
                        } catch (SQLException ex) {
                            Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        //Default screen set to admin profile
        try {
            setForm(new Form_Profile_Adm(conn, userid));
        } catch (SQLException ex) {
            Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Acc delete button only visible when admin is not main admin
        delVisible(userid);
    }
    
    private void delVisible(int uID){
        if(uID != 1){
            delButton.setVisible(true);
        }
        else{
            delButton.setVisible(false);
        }
    }
    
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panelBorder1 = new salcedo.dashboard.swing.panelBorder();
        jLabel1 = new javax.swing.JLabel();
        delButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        menu_Admin = new salcedo.dashboard.component.Menu_Admin();

        jFrame1.setLocation(new java.awt.Point(10, -5));
        jFrame1.setResizable(false);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("MS PGothic", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Dashboard");

        delButton.setBackground(new java.awt.Color(153, 153, 153));
        delButton.setText("Deactivate Account");
        delButton.setVisible(false);
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delButton)
                        .addGap(68, 68, 68))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        // TODO add your handling code here
        int response = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to permanently delete this admin account?",
                    "EXIT",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(response == 0)
                    {
                        try {
                            String stmt = "DELETE FROM " + TABLE_NAME + " WHERE UserID = '" + userid + "'";
                            PreparedStatement pstmt = conn.prepareStatement(stmt);
                            pstmt.execute();
                            dispose();
                            conn.close(); //closes connection to avoid DB lock 
                            Main m = new Main();
                            m.setVisible(true);
                            m.setLocationRelativeTo(null);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
    }//GEN-LAST:event_delButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delButton;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private salcedo.dashboard.component.Menu_Admin menu_Admin;
    private salcedo.dashboard.swing.panelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
