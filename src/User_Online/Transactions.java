package User_Online;

import java.awt.Color;
import javax.swing.*;

public class Transactions extends JPanel {

    private JLabel cash;
    private JLabel in_out;
    private JTextArea name;
    private JLabel time;
    String name_us;
    String cash_us;
    String time_us;
    Color col;
    String icon;

    public Transactions(String name, String time, String in_o, String cash) {
        name_us = name;
        time_us = time;
        if (in_o.equals("p")) {
            cash_us = "+Rs. " + cash;
            col = new Color(7, 208, 174);
            icon = "/resources/icon/green.png";
        } else {
            cash_us = "-Rs. " + cash;
            col = new Color(239, 129, 112);
            icon = "/resources/icon/red.png";
        }
        initComponents();
        in_out.setIcon(new ImageIcon(getClass().getResource(icon)));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        in_out = new JLabel();
        name = new JTextArea();
        time = new JLabel();
        cash = new JLabel();

        setBackground(new Color(255, 255, 255));

        name.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        name.setForeground(new java.awt.Color(8, 34, 49));
        name.setText(name_us);
        name.setWrapStyleWord(true);
        name.setLineWrap(true);
        name.setOpaque(false); // To make it look like a JLabel
        name.setEditable(false); // To make it non-editable
        name.setFocusable(false); // To make it non-focusable
        name.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        name.setBackground(new Color(0, 0, 0, 0)); // Set background to transparent

        time.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        time.setForeground(new java.awt.Color(135, 145, 147));
        time.setText(time_us);

        cash.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cash.setForeground(col);
        cash.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        cash.setText(cash_us);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(in_out)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 77, Short.MAX_VALUE))
                                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(time))
                                        .addComponent(in_out))
                                .addGap(6, 6, 6))
        );
    }// </editor-fold>

}