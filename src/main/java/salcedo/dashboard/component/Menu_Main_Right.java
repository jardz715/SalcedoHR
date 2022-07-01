package salcedo.dashboard.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.DBQueries;
import main.Main;
import main.forgotPass;
import main.regEmployee;
import salcedo.dashboard.main.Main_Admin;
import salcedo.dashboard.main.Main_Employee;

public class Menu_Main_Right extends javax.swing.JPanel {

    Connection conn;
    private Component rootPane;
    final String TABLE_NAME = "UserTable";
    JFrame mainFrame;
    
    public Menu_Main_Right(Connection temp, JFrame frame) throws SQLException {
        initComponents();
        setOpaque(false);
        conn = temp;
        mainFrame = frame;
    }

    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintChildren(g);
    }
    
    //Method to check if username or password field is empty (after getting input from the fields
    protected boolean isEmpty(String x, String y){
       boolean emp = false;
       if(x.equals("") || y.equals("")){
           emp = true;
       }
       else{
           emp = false;
       }    
       return emp;
    }
    
    

    
    
    //Method to check user inputs for validity
    protected boolean checker(Connection conn, String user, String pass){
        boolean validUName = false, passCheck = false;
        boolean check = false;
        DBQueries query = new DBQueries();
        
        // Regex check on username
        final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pat = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pat.matcher(user);
        if(matcher.matches()){
            validUName = true;
        }
        else{
            validUName = false;
            System.out.println("uname checker fail");
        }
        
        // Checks DB to see if password matches username
        ResultSet rs = query.getRow(conn, "*", TABLE_NAME, "username = '" + user + "'");
        try {
            if(rs.next() != false) {
                if(rs.getString("username").equals(user) && rs.getString("userPass").equals(pass)){
                    passCheck = true;
                }
                else{
                    passCheck = false;
                    System.out.println("Password does not match username!");
                }
            }else{
                passCheck = false;
                System.out.println("Either username does not exist or is incorrect!");
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
        
        // Checks to see if all above checks are true before proceeding
        if(validUName == true && passCheck == true){
            check = true;
        }
        else{
            check = false;
        }
        
        return check;
    }
    
    // Method to check whether user is tagged as ADMIN or EMPLOYEE in database
    protected boolean isAdmin(Connection conn, String user, String pass){
        DBQueries query = new DBQueries();
        int admStatus = 0;
        boolean isAdmin = false;
        String queryStmt = String.format("username = '%s' AND userPass = '%s'", user, pass);
        ResultSet rs = query.getRow(conn, "userIsAdmin", TABLE_NAME, queryStmt);
        try {
            while(rs.next()){
                admStatus = rs.getInt("userIsAdmin");
            }
            
            if(admStatus == 1){
                isAdmin = true;
            } else isAdmin = false;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdmin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        signinLabel = new javax.swing.JLabel();
        unameField = new swing.MyTextField();
        passField = new swing.MyPasswordField();
        regLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        jLabelMini = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        fPassLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(516, 516));

        panelMoving.setOpaque(false);

        signinLabel.setBackground(new java.awt.Color(231, 244, 241));
        signinLabel.setFont(new java.awt.Font("Nirmala UI", 1, 48)); // NOI18N
        signinLabel.setForeground(new java.awt.Color(7, 164, 121));
        signinLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        signinLabel.setText("Sign In");
        signinLabel.setAlignmentX(0.5F);
        signinLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signinLabel.setFocusTraversalPolicyProvider(true);
        signinLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        unameField.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        unameField.setHint("Username");
        unameField.setPrefixIcon(new javax.swing.ImageIcon("resources/username.png"));
        unameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unameFieldKeyPressed(evt);
            }
        });

        passField.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        passField.setHint("Password");
        passField.setPrefixIcon(new javax.swing.ImageIcon("resources/pass.png"));
        passField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFieldKeyPressed(evt);
            }
        });

        regLabel.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        regLabel.setForeground(new java.awt.Color(7, 164, 121));
        regLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regLabel.setText("No Account? Click here to Sign Up");
        regLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regLabel.setFocusTraversalPolicyProvider(true);
        regLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regLabelMouseClicked(evt);
            }
        });

        loginLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        loginLabel.setIcon(new javax.swing.ImageIcon("resources/login.png"));
        loginLabel.setText("Login");
        loginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLabelMouseClicked(evt);
            }
        });

        jLabelMini.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMini.setText("-");
        jLabelMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMini.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMiniMouseClicked(evt);
            }
        });

        jLabelClose.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        fPassLabel.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        fPassLabel.setForeground(new java.awt.Color(7, 164, 121));
        fPassLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fPassLabel.setText("Forgot Password?");
        fPassLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fPassLabel.setFocusTraversalPolicyProvider(true);
        fPassLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fPassLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelClose)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fPassLabel))
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMovingLayout.createSequentialGroup()
                                .addComponent(regLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40))
                            .addComponent(unameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(passField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelMovingLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(signinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(79, 79, 79)))))
                .addGap(102, 102, 102))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMini, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(signinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(unameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void regLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regLabelMouseClicked
        try {
            // TODO add your handling code here:
            mainFrame.dispose();
            regEmployee re = new regEmployee();
            re.setVisible(true);
            re.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_regLabelMouseClicked

    private void loginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseClicked
        String un, pw;
        un = unameField.getText().toLowerCase();
        pw = String.valueOf(passField.getPassword());

        if(isEmpty(un , pw)){
            JOptionPane.showMessageDialog(null, "Username and password fields cannot be empty.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(checker(conn, un, pw)){
                System.out.println("Login Success!");
                DBQueries query = new DBQueries();
                ResultSet rs = query.getRow(conn, "userID", TABLE_NAME, "username = '" + un + "'");
                int ID;
                //If statement to redirect to admin dashboard when adm in DB is set to 1
                // and redirect to employee dashboard when adm in DB is set to 0

                if(isAdmin(conn, un, pw)){
                    // proceed to admin dashboard
                    mainFrame.dispose();
                    try {
                        if(rs.next()){
                            ID = rs.getInt("userID");
                            Main_Admin ma = new Main_Admin(conn, ID);
                            ma.setVisible(true);
                            ma.setLocationRelativeTo(null);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    // proceed to employee dashboard
                    mainFrame.dispose();
                    try{
                        if(rs.next()){
                            ID = rs.getInt("userID");
                            Main_Employee me = new Main_Employee(conn, ID);
                            me.setVisible(true);
                            me.setLocationRelativeTo(null);
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password. Please try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
                unameField.setText("");
                passField.setText("");
            }
        }
    }//GEN-LAST:event_loginLabelMouseClicked

    private void jLabelMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMiniMouseClicked
        mainFrame.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMiniMouseClicked

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to exit?",
            "EXIT",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void unameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unameFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            MouseEvent e = null;
            loginLabelMouseClicked(e);
        }
    }//GEN-LAST:event_unameFieldKeyPressed

    private void passFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            MouseEvent e = null;
            loginLabelMouseClicked(e);
        }
    }//GEN-LAST:event_passFieldKeyPressed

    private void fPassLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fPassLabelMouseClicked
        try {
            mainFrame.dispose();
            forgotPass fp = new forgotPass();
            fp.setVisible(true);
            fp.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fPassLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fPassLabel;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMini;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel panelMoving;
    private swing.MyPasswordField passField;
    private javax.swing.JLabel regLabel;
    private javax.swing.JLabel signinLabel;
    private swing.MyTextField unameField;
    // End of variables declaration//GEN-END:variables
}
