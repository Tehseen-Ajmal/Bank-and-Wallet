package User_Online;

import myon.bank.pack.Complains;
import resources.swing.ButtonMenu;
import resources.swing.RoundPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Resolved extends JPanel {
    private ButtonMenu buttonMenu5;
    private ButtonMenu buttonMenu6;
    private ButtonMenu buttonMenu1;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JScrollPane jScrollPane1;
    private JPanel pend_comp;
    private RoundPanel roundPanel1;
    Complains global_com = new Complains();
    public Resolved() throws IOException, ClassNotFoundException {
        global_com.file_to_arraylist();
        initComponents();
        roundPanel1.round=35;
        pend_comp.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
//        JTextArea a = new JTextArea("Hello my name is abdullah's ");
//
//        a.setColumns(20);
//        a.setBackground(new Color(255,242,241));
//        a.setEditable(false);
////        a.setBorder(null);
//        a.setWrapStyleWord(true);
//        a.setLineWrap(true);
//        a.setForeground(new Color(255,123,102));
        ArrayList<Complains> comp = global_com.ret_solved_complain(User_Background.auth_enter);
        for(Complains i:comp){
            String[] splitStrings = global_com.complain_read(i).split("Answer to Query:", 2);
            String complain_actual = splitStrings[0].trim();
            String solution = splitStrings[1].trim();
            pend_comp.add(new Complain_display(complain_actual,global_com.complain_date(i)+"                     "+global_com.complain_time(i),0));
            pend_comp.add(new Complain_display(solution,"Answer",1));
        }
//        pend_comp.add(a);
//        pend_comp.add(new JTextArea("Hello my name is abdullah"));
//        pend_comp.add(new JTextArea("Hello my name is abdullah"));
//        pend_comp.add(new JTextArea("Hello my name is abdullah"));
//        pend_comp.add(new Complain_display("Hello my name is abdullah"));
//        pend_comp.add(new Complain_display("Hello my name is abdullah"));



    }

    private void initComponents() {

        roundPanel1 = new RoundPanel();
        buttonMenu5 = new ButtonMenu();
        buttonMenu6 = new ButtonMenu();
        buttonMenu1 = new ButtonMenu();
        jLabel12 = new JLabel();
        jLabel11 = new JLabel();
        jScrollPane1 = new JScrollPane();
        pend_comp = new JPanel();
        jLabel1 = new JLabel();

        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(255, 255, 255));
        roundPanel1.setLayout(null);

        buttonMenu5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu5ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu5);
        buttonMenu5.setBounds(64, 510, 30, 30);

        buttonMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu6ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu6);
        buttonMenu6.setBounds(186, 510, 30, 30);

        jLabel12.setIcon(new ImageIcon(getClass().getResource("/resources/icon/red_complain (1).png"))); // NOI18N
        roundPanel1.add(jLabel12);
        jLabel12.setBounds(186, 510, 30, 30);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-home-30.png"))); // NOI18N
        roundPanel1.add(jLabel11);
        jLabel11.setBounds(64, 510, 30, 30);

        jScrollPane1.setBackground(new Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pend_comp.setBackground(new Color(255, 255, 255));
        pend_comp.setLayout(null);
        jScrollPane1.setViewportView(pend_comp);

        roundPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(6, 30, 268, 460);

        jLabel1.setFont(new Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Resolved Complaints");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(59, 6, 164, 18);

        buttonMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/icons8-back-15.png"))); // NOI18N
        buttonMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu1ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu1);
        buttonMenu1.setBounds(6, 9, 20, 18);
        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 546);
    }

    private void buttonMenu5ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }

    private void buttonMenu6ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }
    private void buttonMenu1ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }
}

