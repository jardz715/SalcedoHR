package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class forgotPass extends javax.swing.JFrame {

    
    public forgotPass() throws SQLException {
        initComponents();
        // get original password field character
        passChar = passField.getEchoChar();
    }

    int xMouse = 0, yMouse = 0;
    DBConnect dc = new DBConnect();
    Connection conn = dc.dbCheck();
    final String TABLE_NAME = "UserTable";
    String gender = "";
    char passChar;
    
    protected boolean isFieldEmpty(String a, String b, String c, String d){
        boolean emp = a.equals("") || b.equals("") || c.equals("") || d.equals("");
        return emp;
    }
    
    // Method to perform regex check on username
    protected boolean isUnameValid(String user){
        boolean valid = false;
        final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pat = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pat.matcher(user);
        if(matcher.matches()){
            valid = true;
        }
        else{
            valid = false;
            System.out.println("uname checker fail");
        }
        return valid;
    }
    
    // Method to check if two fields are not identical
    protected boolean notIdentical(String a, String b){
        boolean notSame = false;
        if(!a.equals(b)){
            notSame = true;
        } else notSame = false;
        return notSame;
    }
    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        admTitleLabel = new javax.swing.JLabel();
        admSubLabel = new javax.swing.JLabel();
        jLabelMini = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        retypeField = new javax.swing.JPasswordField();
        registLabel = new javax.swing.JLabel();
        backLabel = new javax.swing.JLabel();
        newPassField3 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(32, 157, 90));
        jPanel1.setPreferredSize(new java.awt.Dimension(426, 367));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        admTitleLabel.setFont(new java.awt.Font("Nirmala UI", 1, 28)); // NOI18N
        admTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        admTitleLabel.setText("Employee");

        admSubLabel.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        admSubLabel.setForeground(new java.awt.Color(255, 255, 255));
        admSubLabel.setText("Forgot Password");

        jLabelMini.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelMini.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMini.setText("-");
        jLabelMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMiniMouseClicked(evt);
            }
        });

        jLabelClose.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(admTitleLabel)
                    .addComponent(admSubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelClose)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMini, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(admTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(admSubLabel)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(7, 164, 121));
        jLabel3.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(7, 164, 121));
        jLabel4.setText("Current Password:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(7, 164, 121));
        jLabel5.setText("New Password:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 164, 121));
        jLabel6.setText("Re-type Password");

        userField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        userField.setForeground(new java.awt.Color(75, 175, 152));

        passField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        passField.setForeground(new java.awt.Color(75, 175, 152));

        retypeField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        retypeField.setForeground(new java.awt.Color(75, 175, 152));

        registLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        registLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registLabel.setIcon(new javax.swing.ImageIcon("resources/login.png"));
        registLabel.setText("Reset Password");
        registLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registLabelMouseClicked(evt);
            }
        });

        backLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        backLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backLabel.setIcon(new javax.swing.ImageIcon("resources/back.png"));
        backLabel.setText("Go Back");
        backLabel.setPreferredSize(new java.awt.Dimension(91, 40));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });

        newPassField3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        newPassField3.setForeground(new java.awt.Color(75, 175, 152));
        newPassField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPassField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPassField3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(retypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(newPassField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(retypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMiniMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMiniMouseClicked

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to exit?",
            "EXIT",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0)
        {
            try {
                this.dispose();
                Main m = new Main();
                m.setVisible(true);
                m.setLocationRelativeTo(null);
            } catch (SQLException ex) {
                Logger.getLogger(forgotPass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        try {
            // TODO add your handling code here:
            this.dispose();
            Main m = new Main();
            m.setVisible(true);
            m.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(forgotPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backLabelMouseClicked

    private void registLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registLabelMouseClicked
        DBQueries query = new DBQueries();
        String uname = userField.getText();
        String curPass = String.valueOf(passField.getPassword());
        String newPass = String.valueOf(newPassField3.getPassword());
        String retypePass = String.valueOf(retypeField.getPassword());
        if(!isFieldEmpty(uname, curPass, newPass, retypePass)){
            ResultSet userSet = query.getRow(conn, "*", "UserTable", "username = '" + uname.toLowerCase() + "'");
            try {
                if(userSet.next() != false){
                    if(curPass.equals(userSet.getString("userPass"))){
                        if(!notIdentical(newPass, retypePass)){
                            String stmt = String.format("userPass = '%s'", newPass);
                            String stmt2 = "username = '" + uname.toLowerCase() + "'";
                            query.updateRow(conn, "UserTable", stmt, stmt2);
                            JOptionPane.showMessageDialog(null, "Password Updated.", "Notice", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Retype Password is not the same as New Password.", "Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Current password Invalid.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Username cannot be found.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(forgotPass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_registLabelMouseClicked

    private void newPassField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPassField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPassField3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel admSubLabel;
    private javax.swing.JLabel admTitleLabel;
    private javax.swing.JLabel backLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMini;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField newPassField3;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel registLabel;
    private javax.swing.JPasswordField retypeField;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
