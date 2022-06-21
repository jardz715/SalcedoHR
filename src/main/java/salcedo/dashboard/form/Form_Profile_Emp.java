package salcedo.dashboard.form;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.DBQueries;

public class Form_Profile_Emp extends javax.swing.JPanel {

    Connection conn;
    int userid;
    int ctr;
    final String TABLE_NAME = "UserTable";
    
    String un, pw, eml, add, num, stat, nat;
    
    public Form_Profile_Emp(Connection temp, int ID) throws SQLException{
        conn = temp;
        userid = ID;
        initComponents();
        
        //Initialize data from database to fill out the fields
        getDataFromDB(conn, userid);
    }
    
    // Method to set field color depending on the field's editable status
    protected void setCorrectFieldColor(JTextField a, JPasswordField b, JTextField c, JTextField d, JTextField e, JTextField f, JTextField g)    {
        if(a.isEditable() && b.isEditable() && c.isEditable() && d.isEditable() && e.isEditable() && f.isEditable() && g.isEditable()){
            a.setBackground(Color.WHITE);
            b.setBackground(Color.WHITE);
            c.setBackground(Color.WHITE);
            d.setBackground(Color.WHITE);
            e.setBackground(Color.WHITE);
            f.setBackground(Color.WHITE);
            g.setBackground(Color.WHITE);
        }
        else{
            a.setBackground(new Color(204,204,204));
            b.setBackground(new Color(204,204,204));
            c.setBackground(new Color(204,204,204));
            d.setBackground(new Color(204,204,204));
            e.setBackground(new Color(204,204,204));
            f.setBackground(new Color(204,204,204));
            g.setBackground(new Color(204,204,204));
        }
    }
    
    // Method to retrieve information from table based on user ID
    protected void getDataFromDB(Connection conn, int uID){
        DBQueries query = new DBQueries();
        String queryStmt = String.format("userID = '%s'", uID);
        ResultSet rs = query.getRow(conn, "*", TABLE_NAME, queryStmt);
        try {
            while(rs.next()){
                IDField.setText(String.valueOf(rs.getInt("userID")));
                fNameField.setText(rs.getString("userFirstN"));
                mNameField.setText(rs.getString("userMiddleN"));
                lNameField.setText(rs.getString("userLastN"));
                unameField.setText(rs.getString("username"));
                passField.setText(String.valueOf(rs.getObject("userPass")));
                ageField.setText(String.valueOf(rs.getInt("userAge")));
                genderField.setText(rs.getString("userGender"));
                emailField.setText(rs.getString("userEmail"));
                addField.setText(rs.getString("userAdd"));
                numField.setText("0" + rs.getString("userContact"));
                appDateField.setText(rs.getString("userAppDate"));
                statusField.setText(rs.getString("userStatus"));
                natField.setText(rs.getString("userNat"));
                posField.setText(rs.getString("userPos"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Profile_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        titleLabel = new javax.swing.JLabel();
        IDLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel14 = new javax.swing.JLabel();
        editButton = new javax.swing.JToggleButton();
        IDField = new javax.swing.JTextField();
        fNameField = new javax.swing.JTextField();
        mNameField = new javax.swing.JTextField();
        lNameField = new javax.swing.JTextField();
        unameField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        ageField = new javax.swing.JTextField();
        genderField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        addField = new javax.swing.JTextField();
        numField = new javax.swing.JTextField();
        appDateField = new javax.swing.JTextField();
        statusField = new javax.swing.JTextField();
        natField = new javax.swing.JTextField();
        posField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        titleLabel.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(102, 102, 102));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Employee Details");

        IDLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        IDLabel.setForeground(new java.awt.Color(102, 102, 102));
        IDLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        IDLabel.setText("User ID:");

        jLabel1.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("First Name:");

        jLabel2.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Middle Initial:");

        jLabel3.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Last Name:");

        jLabel4.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Username:");

        jLabel5.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Password:");

        jLabel6.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Age:");

        jLabel7.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Gender:");

        jLabel8.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("E-Mail:");

        jLabel9.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Address:");

        jLabel10.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Contact Number:");

        jLabel11.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Date of Appointment:");

        jLabel12.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Civil Status:");

        jLabel13.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Nationality:");

        jLabel14.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Position:");

        editButton.setBackground(new java.awt.Color(153, 153, 153));
        editButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        IDField.setEditable(false);
        IDField.setBackground(new java.awt.Color(204, 204, 204));
        IDField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        fNameField.setEditable(false);
        fNameField.setBackground(new java.awt.Color(204, 204, 204));
        fNameField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        mNameField.setEditable(false);
        mNameField.setBackground(new java.awt.Color(204, 204, 204));
        mNameField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        lNameField.setEditable(false);
        lNameField.setBackground(new java.awt.Color(204, 204, 204));
        lNameField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        unameField.setEditable(false);
        unameField.setBackground(new java.awt.Color(204, 204, 204));
        unameField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        passField.setEditable(false);
        passField.setBackground(new java.awt.Color(204, 204, 204));
        passField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        ageField.setEditable(false);
        ageField.setBackground(new java.awt.Color(204, 204, 204));
        ageField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        genderField.setEditable(false);
        genderField.setBackground(new java.awt.Color(204, 204, 204));
        genderField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        emailField.setEditable(false);
        emailField.setBackground(new java.awt.Color(204, 204, 204));
        emailField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        addField.setEditable(false);
        addField.setBackground(new java.awt.Color(204, 204, 204));
        addField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        numField.setEditable(false);
        numField.setBackground(new java.awt.Color(204, 204, 204));
        numField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        appDateField.setEditable(false);
        appDateField.setBackground(new java.awt.Color(204, 204, 204));
        appDateField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        statusField.setEditable(false);
        statusField.setBackground(new java.awt.Color(204, 204, 204));
        statusField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        natField.setEditable(false);
        natField.setBackground(new java.awt.Color(204, 204, 204));
        natField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        posField.setEditable(false);
        posField.setBackground(new java.awt.Color(204, 204, 204));
        posField.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unameField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(natField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(posField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(102, 102, 102))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IDField)
                            .addComponent(IDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(appDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(unameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(natField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(editButton.isSelected()){
            // When toggle button is selected
            ctr++;
            unameField.setEditable(true);
            passField.setEditable(true);
            emailField.setEditable(true);
            addField.setEditable(true);
            numField.setEditable(true);
            statusField.setEditable(true);
            natField.setEditable(true);
            editButton.setText("Update");
            
            setCorrectFieldColor(unameField, passField, emailField, addField, numField, statusField, natField);
        }
        else{
            // When toggle button is deselected again
            ctr++;
            unameField.setEditable(false);
            passField.setEditable(false);
            emailField.setEditable(false);
            addField.setEditable(false);
            numField.setEditable(false);
            statusField.setEditable(false);
            natField.setEditable(false);
            editButton.setText("Edit");
            
            setCorrectFieldColor(unameField, passField, emailField, addField, numField, statusField, natField);
            
            //Get input from field then update database
            un = unameField.getText().toLowerCase();
            pw = String.valueOf(passField.getPassword());
            eml = emailField.getText();
            add = addField.getText();
            num = numField.getText();
            stat = statusField.getText();
            nat = natField.getText();
            
            DBQueries query = new DBQueries();
            if(isUnameValid(un)){
                if(!query.isStrUnique(conn, userid, un, "username", TABLE_NAME) && ctr % 2 == 0){
                    JOptionPane.showMessageDialog(null, "Username is already used. Please enter a different username.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    getDataFromDB(conn, userid);
                }                        
                else if(!query.isStrUnique(conn, userid, eml, "userEmail", TABLE_NAME) && ctr % 2 == 0){
                    JOptionPane.showMessageDialog(null, "Email is already used. Please enter a different email.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    getDataFromDB(conn, userid);
                }
                else if(!num.matches("[0-9]+") || num.length() != 11){
                    JOptionPane.showMessageDialog(null, "Only numeric input is allowed, and make sure it has 11 digits. EX. 09XXXXXXXXX", "Error", JOptionPane.INFORMATION_MESSAGE);
                    getDataFromDB(conn, userid);
                }
                else{
                    String stmt = String.format("username = '%s', userPass = '%s', userEmail = '%s', userAdd = '%s', userContact = '%s', userStatus = '%s', userNat = '%s'", un, pw, eml, add, num, stat, nat);
                    String stmt2 = "userID = '" + userid + "'";
                    query.updateRow(conn, TABLE_NAME, stmt, stmt2);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Username is not valid. Please enter a username with numbers AND/OR special characters.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JTextField addField;
    private javax.swing.JTextField ageField;
    private javax.swing.JTextField appDateField;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fNameField;
    private javax.swing.JTextField genderField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lNameField;
    private javax.swing.JTextField mNameField;
    private javax.swing.JTextField natField;
    private javax.swing.JTextField numField;
    private javax.swing.JPasswordField passField;
    private javax.swing.JTextField posField;
    private javax.swing.JTextField statusField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField unameField;
    // End of variables declaration//GEN-END:variables
}
