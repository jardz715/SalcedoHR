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
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Menu_Employee extends javax.swing.JPanel implements Runnable{

    private EventMenuSelected event;
    private int hour,second,minute;
    private volatile boolean running = true;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    
    public Menu_Employee() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
        Thread t = new Thread(this);
        t.start();
    }
    
    public void terminate() {
        running = false;
    }
    
    public void run(){
        while (running) {
            try {
                Calendar cal = Calendar.getInstance();
                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);

                SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss aa");
                Date dat = cal.getTime();
                String time24 = sdf12.format(dat);

                time.setText(time24);
            } catch (Exception e) {
                running = false;
            }
        }
    }

    private void init() {
        listMenu1.addItem(new Model_Menu("1", "Profile", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("2", "Time Log", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Documents", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("", "Sample Text", Model_Menu.MenuType.EMPTY));
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
        time = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(280, 590));

        panelMoving.setOpaque(false);

        employeeName.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        employeeName.setForeground(new java.awt.Color(231, 244, 241));
        employeeName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        employeeName.setText("Employee");

        time.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        time.setForeground(new java.awt.Color(231, 244, 241));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("11:34");

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
                .addContainerGap(50, Short.MAX_VALUE))
            .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(employeeName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(time)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
