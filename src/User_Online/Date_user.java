package User_Online;

import javax.swing.*;
import java.awt.*;
public class Date_user extends JPanel {
    String date_us;
    private JLabel jLabel1;
    public Date_user(String date) {
        date_us = date;
        initComponents();
    }
    private void initComponents() {

        jLabel1 = new JLabel();

        setBackground(new Color(255, 255, 255));

        jLabel1.setFont(new Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new Color(135, 145, 147));
        jLabel1.setText(date_us);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

}

