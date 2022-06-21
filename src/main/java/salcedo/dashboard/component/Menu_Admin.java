package salcedo.dashboard.component;

import salcedo.dashboard.event.EventMenuSelected;
import salcedo.dashboard.model.Model_Menu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

public class Menu_Admin extends javax.swing.JPanel{

    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    
    public Menu_Admin() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }

    private void init() {
        listMenu1.addItem(new Model_Menu("1", "Profile", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("2", "Employees", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Register New Admin", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("4", "Time Log", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("5", "Documents", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY));

        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("10", "Logout", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        employeeName = new javax.swing.JLabel();
        listMenu1 = new salcedo.dashboard.swing.ListMenu<>();

        setPreferredSize(new java.awt.Dimension(280, 590));

        panelMoving.setOpaque(false);

        employeeName.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        employeeName.setForeground(new java.awt.Color(231, 244, 241));
        employeeName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        employeeName.setText("Admin");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(employeeName))
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(employeeName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#134E5E"), 0, getHeight(), Color.decode("#71B280"));
//        g2.setPaint(gp);
        g2.setColor(new Color(32,157,90));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(g);
    }
    
    private int x,y;
    public void initMoving(JFrame frame) {
        panelMoving.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        panelMoving.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel employeeName;
    private salcedo.dashboard.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
