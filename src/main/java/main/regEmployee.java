package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class regEmployee extends javax.swing.JFrame {

    
    public regEmployee() throws SQLException {
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
    
    // Method to check if any field on the register form is empty
    protected boolean isFieldEmpty(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j){
        boolean emp = false;
        if(a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("") || f.equals("") || g.equals("") || h.equals("") || i           .equals("") || j.equals("")){
            emp = true;
        }
        else{
            emp = false;
        }
        return emp;
    }
    
    // Method to check if Date chooser is empty
    protected boolean isDateEmpty(){
        boolean emp = false;
        if(bday.getDate() == null){
            emp = true;
        } else emp = false;
        return emp;
    }
    
    // Method to check if both Gender checkbox is empty
    protected boolean isGenderEmpty(){
        boolean emp = false;
        if(!maleBox.isSelected() && !femaleBox.isSelected()){
            emp = true;
        } else emp = false;
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
    
    // Method to get age from date chooser and current system date
    protected int calcAge(String bDate) throws ParseException{
        String currDate;
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        bDate = dateFormat.format(bday.getDate());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime now = LocalDateTime.now();  
        currDate = dtf.format(now);
        
        Date birthDate = dateFormat.parse(bDate);
        Instant instant = birthDate.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        
        Date current = dateFormat.parse(currDate);
        Instant instant2 = current.toInstant();
        ZonedDateTime zone2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate givenDate2 = zone2.toLocalDate();
        
        Period period = Period.between(givenDate, givenDate2);
        return period.getYears();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        passField2 = new javax.swing.JPasswordField();
        fNameField = new javax.swing.JTextField();
        mNameField = new javax.swing.JTextField();
        lNameField = new javax.swing.JTextField();
        bday = new com.toedter.calendar.JDateChooser();
        emailField = new javax.swing.JTextField();
        emailField2 = new javax.swing.JTextField();
        numField = new javax.swing.JTextField();
        maleBox = new javax.swing.JRadioButton();
        femaleBox = new javax.swing.JRadioButton();
        registLabel = new javax.swing.JLabel();
        backLabel = new javax.swing.JLabel();
        togglePass1 = new javax.swing.JToggleButton();
        togglePass2 = new javax.swing.JToggleButton();

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
        admSubLabel.setText("Registration");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(7, 164, 121));
        jLabel3.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(7, 164, 121));
        jLabel4.setText("Password:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(7, 164, 121));
        jLabel5.setText("Re-type Password:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 164, 121));
        jLabel6.setText("First Name:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(7, 164, 121));
        jLabel7.setText("Middle Name:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(7, 164, 121));
        jLabel8.setText("Last Name:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(7, 164, 121));
        jLabel9.setText("Birth Date:");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(7, 164, 121));
        jLabel10.setText("E-mail:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(7, 164, 121));
        jLabel11.setText("Re-type Email:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(7, 164, 121));
        jLabel12.setText("Contact Number:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(7, 164, 121));
        jLabel13.setText("Gender:");

        userField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        userField.setForeground(new java.awt.Color(75, 175, 152));

        passField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        passField.setForeground(new java.awt.Color(75, 175, 152));

        passField2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        passField2.setForeground(new java.awt.Color(75, 175, 152));

        fNameField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        fNameField.setForeground(new java.awt.Color(75, 175, 152));

        mNameField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        mNameField.setForeground(new java.awt.Color(75, 175, 152));

        lNameField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lNameField.setForeground(new java.awt.Color(75, 175, 152));

        bday.setBackground(new java.awt.Color(204, 255, 204));
        bday.setDateFormatString("MMMM dd, yyyy");
        bday.setFocusable(false);
        bday.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        emailField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        emailField.setForeground(new java.awt.Color(75, 175, 152));

        emailField2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        emailField2.setForeground(new java.awt.Color(75, 175, 152));

        numField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        numField.setForeground(new java.awt.Color(75, 175, 152));

        maleBox.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        maleBox.setText("Male");
        maleBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleBoxActionPerformed(evt);
            }
        });

        femaleBox.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        femaleBox.setText("Female");
        femaleBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleBoxActionPerformed(evt);
            }
        });

        registLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        registLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registLabel.setIcon(new javax.swing.ImageIcon("resources/login.png"));
        registLabel.setText("Register");
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

        togglePass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        togglePass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePass1ActionPerformed(evt);
            }
        });

        togglePass2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        togglePass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePass2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(74, 74, 74))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(togglePass1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(passField2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(togglePass2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(119, 119, 119))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(maleBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(femaleBox))
                                    .addComponent(numField)
                                    .addComponent(emailField2)
                                    .addComponent(emailField)
                                    .addComponent(bday, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
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
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(togglePass1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(passField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(togglePass2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(mNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(bday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(maleBox)
                    .addComponent(femaleBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
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
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                Logger.getLogger(regEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void registLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registLabelMouseClicked
        // check database for existing records, if not, register then add to records
        String uname, pword, pword2, fname, mname, lname, email, email2, pnum;
        String birth = null;
        int age;
//        final int adm = 0;                           //admin status set to 0 because only employees would be registering for a new account
        int userID = 0;
        DBQueries query = new DBQueries();
        
        if(!isDateEmpty()){
            try {
                uname = userField.getText().toLowerCase();
                pword = String.valueOf(passField.getPassword());
                pword2 = String.valueOf(passField2.getPassword());
                fname = fNameField.getText();
                mname = mNameField.getText();
                lname = lNameField.getText();
                age = calcAge(birth);
                email = emailField.getText();
                email2 = emailField2.getText();
                pnum = numField.getText();
                
                // If all of the fields are entered and gender is selected, proceed.
                if(!isFieldEmpty(uname, pword, pword2, fname, mname, lname, email, email2, pnum, gender) && !isGenderEmpty()){
                    if(isUnameValid(uname)){
                        // If both password fields and both email fields are identical, proceed, if not, show error message. 
                        if(notIdentical(pword, pword2) || notIdentical(email, email2)){
                            JOptionPane.showMessageDialog(null, "For security purposes, Both Password fields and both E-Mail fields must have the same input data.", "Error", JOptionPane.INFORMATION_MESSAGE);   
                        }
                        else{
                            if(!query.isStrUnique(conn, uname, "username", TABLE_NAME)){
                                JOptionPane.showMessageDialog(null, "Username is already used. Please enter a different username.", "Error", JOptionPane.INFORMATION_MESSAGE);
                            // added email as unique. cannot have the same email. 
                            }else if(!query.isStrUnique(conn, email, "userEmail", TABLE_NAME)){
                                JOptionPane.showMessageDialog(null, "Email is already used. Please enter a different email.", "Error", JOptionPane.INFORMATION_MESSAGE);
                            }else if(!pnum.matches("[0-9]+") || pnum.length() != 11){
                                JOptionPane.showMessageDialog(null, "Only numeric input is allowed for the contact number, and make sure it has 11 digits. EX. 09XXXXXXXXX", "Error", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                //START INSERTING INTO DB
                                List<String> regList = new ArrayList<String>();

                                // sqlite auto-increments int primary keys. 
                                // regList.add(Integer.toString(userID += 1)); //Registers the ID of latest entry but increments it for next entry new entry has unique ID 
                                regList.add(uname);
                                regList.add(pword);
                                regList.add(fname);
                                regList.add(mname);
                                regList.add(lname);
                                regList.add(Integer.toString(age));
                                regList.add(email);
                                regList.add(pnum);
                                regList.add(gender);
                                query.registerUser(conn, regList);

                                // After successfully registering, reset all inputs of register form and return to main menu
                                JOptionPane.showMessageDialog(null, "Registered Successfully.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                                userField.setText("");
                                passField.setText("");
                                passField2.setText("");
                                fNameField.setText("");
                                mNameField.setText("");
                                lNameField.setText("");
                                bday.setCalendar(null);
                                emailField.setText("");
                                emailField2.setText("");
                                numField.setText("");
                                maleBox.setSelected(false);
                                femaleBox.setSelected(false);

                                Main m;
                                try {
                                    this.dispose();
                                    m = new Main();
                                    m.setVisible(true);
                                    m.setLocationRelativeTo(null);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username is not valid. Please enter a username with numbers AND/OR special characters.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more fields are have no input.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ParseException ex) {
                Logger.getLogger(regEmployee.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        else{
            JOptionPane.showMessageDialog(null, "Birth date field cannot be empty.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_registLabelMouseClicked

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        try {
            // TODO add your handling code here:
            this.dispose();
            Main m = new Main();
            m.setVisible(true);
            m.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(regEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backLabelMouseClicked

    private void femaleBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleBoxActionPerformed
        // TODO add your handling code here:
        maleBox.setSelected(false);
        gender = "Female";
    }//GEN-LAST:event_femaleBoxActionPerformed

    private void maleBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleBoxActionPerformed
        // TODO add your handling code here:
        femaleBox.setSelected(false);
        gender = "Male";
    }//GEN-LAST:event_maleBoxActionPerformed

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

    private void togglePass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePass1ActionPerformed
        if(togglePass1.isSelected()){
            // When toggle button is selected
            passField.setEchoChar((char) 0);
        }
        else{
            // When toggle button is deselected
            passField.setEchoChar(passChar);
        }
    }//GEN-LAST:event_togglePass1ActionPerformed

    private void togglePass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePass2ActionPerformed
        // TODO add your handling code here:
        if(togglePass2.isSelected()){
            // When toggle button is selected
            passField2.setEchoChar((char) 0);
        }
        else{
            // When toggle button is deselected
            passField2.setEchoChar(passChar);
        }
    }//GEN-LAST:event_togglePass2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel admSubLabel;
    private javax.swing.JLabel admTitleLabel;
    private javax.swing.JLabel backLabel;
    private com.toedter.calendar.JDateChooser bday;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField emailField2;
    private javax.swing.JTextField fNameField;
    private javax.swing.JRadioButton femaleBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMini;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lNameField;
    private javax.swing.JTextField mNameField;
    private javax.swing.JRadioButton maleBox;
    private javax.swing.JTextField numField;
    private javax.swing.JPasswordField passField;
    private javax.swing.JPasswordField passField2;
    private javax.swing.JLabel registLabel;
    private javax.swing.JToggleButton togglePass1;
    private javax.swing.JToggleButton togglePass2;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
