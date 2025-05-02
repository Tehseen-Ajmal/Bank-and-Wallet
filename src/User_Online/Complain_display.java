//package User_Online;
//
//import java.awt.Color;
//import javax.swing.*;
//
//public class Complain_display extends javax.swing.JPanel {
//    String text_cmp;
//    String date_comp;
//    int color;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JTextArea jTextArea1;
//    private resources.swing.RoundPanel roundPanel1;
//
//    public Complain_display(String text,String date,int col) {
//        color = col;
//        text_cmp = text;
//        date_comp = date;
//        initComponents();
//    }
//
//    @SuppressWarnings("unchecked")
//    private void initComponents() {
//
//        roundPanel1 = new resources.swing.RoundPanel();
//        jLabel1 = new javax.swing.JLabel();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        jTextArea1 = new javax.swing.JTextArea();
//
//        setOpaque(false);
//
//        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
//        jLabel1.setText(date_comp);
//
//        jTextArea1.setEditable(false);
//        jTextArea1.setColumns(20);
//        jTextArea1.setLineWrap(true);
//        jTextArea1.setRows(1);
//        jTextArea1.setBorder(null);
//        if(color==1){
//        jTextArea1.setDisabledTextColor(new java.awt.Color(1,227,189));
//        jTextArea1.setForeground(new java.awt.Color(1,227,189));}
//        else{
//            jTextArea1.setDisabledTextColor(new java.awt.Color(249,123,100));
//            jTextArea1.setForeground(new java.awt.Color(249,123,100));
//        }
//        jTextArea1.setBackground(new Color(255,255,255)); // Set background color
//        jTextArea1.setText(text_cmp);
//        jScrollPane1.setViewportView(jTextArea1);
//
//        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
//        roundPanel1.setLayout(roundPanel1Layout);
//        roundPanel1Layout.setHorizontalGroup(
//                roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
//                        .addGroup(roundPanel1Layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addContainerGap())
//        );
//        roundPanel1Layout.setVerticalGroup(
//                roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(roundPanel1Layout.createSequentialGroup()
//                                .addComponent(jLabel1)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
//        );
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                .addGap(0, 0, Short.MAX_VALUE)
//                                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(0, 0, 0))
//        );
//    }
//}
package User_Online;

import java.awt.*;
import javax.swing.*;

public class Complain_display extends JPanel {
    String text_cmp;
    String date_comp;
    int color;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private resources.swing.RoundPanel roundPanel1;

    public Complain_display(String text, String date, int col) {
        color = col;
        text_cmp = text;
        date_comp = date;
        initComponents();
    }
    private String formatDate(String date) {
        // Split the date string by one or more spaces
        String[] dateTimeParts = date.trim().split("\\s+");

        // Check if the string was split correctly into date and time parts
        if (dateTimeParts.length < 2) {
            return date;  // If the date format is unexpected, return it as-is
        }

        // Extract the date part and time part
        String datePart = dateTimeParts[0];  // e.g., "December" or "Dec"
        String timePart = dateTimeParts[1];  // e.g., "10:38:51 pm"

        // Shorten the month part (e.g., "December" to "Dec")
        String formattedDate = datePart.split(" ")[0].substring(0, 3) + " " + datePart.split(" ")[1] + ", " + datePart.split(" ")[2];

        // Format the time part (e.g., "10:38:51 pm" to "10:38 PM")
        String formattedTime = timePart.substring(0, 5) + " " + timePart.substring(6).toUpperCase(); // "PM" instead of "pm"

        return formattedDate + " " + formattedTime;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        roundPanel1 = new resources.swing.RoundPanel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();

        setOpaque(false);

        jLabel1.setForeground(new Color(102, 102, 102));
        jLabel1.setText(date_comp);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setRows(1);
        jTextArea1.setBorder(null);
        jTextArea1.setBackground(new Color(255, 255, 255)); // Set background color
        jTextArea1.setText(text_cmp);
        jTextArea1.setOpaque(false);

        if (color == 1) {
            jTextArea1.setDisabledTextColor(new Color(1, 227, 189));
            jTextArea1.setForeground(new Color(1, 227, 189));
            roundPanel1.setBackground(new Color(1, 227, 189));
        } else {
            jTextArea1.setDisabledTextColor(new Color(249, 123, 100));
            jTextArea1.setForeground(new Color(249, 123, 100));
        }

        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setBorder(null);

        // Adjust the height of the JTextArea based on its content
        jTextArea1.setSize(jTextArea1.getPreferredSize());
        Dimension d = jTextArea1.getPreferredSize();
        jScrollPane1.setPreferredSize(new Dimension(jScrollPane1.getWidth(), d.height));

        GroupLayout roundPanel1Layout = new GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
                roundPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
                roundPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(roundPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 10, Short.MAX_VALUE)
                                .addComponent(roundPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
    }
}