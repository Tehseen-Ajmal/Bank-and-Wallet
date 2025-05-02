package User_Online;

import myon.bank.pack.Complains;
import resources.swing.Button;
import resources.swing.ButtonMenu;
import resources.swing.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Write_compl extends JPanel {

    Complains global_complain = new Complains();
    private Button button1;
    private ButtonMenu buttonMenu5;
    private ButtonMenu buttonMenu6;
    private ButtonMenu buttonMenu1;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private RoundPanel roundPanel1;
    public Write_compl() {
        initComponents();
        roundPanel1.round=35;
    }

    private void initComponents() {

        roundPanel1 = new RoundPanel();
        buttonMenu5 = new ButtonMenu();
        buttonMenu6 = new ButtonMenu();
        buttonMenu1 = new ButtonMenu();
        jLabel12 = new JLabel();
        jLabel11 = new JLabel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        button1 = new Button();

        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(255, 255, 255));
        roundPanel1.setForeground(new Color(255, 255, 255));
        roundPanel1.setLayout(null);

        buttonMenu5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu5ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu5);
        buttonMenu5.setBounds(66, 513, 30, 30);

        buttonMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu6ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu6);
        buttonMenu6.setBounds(188, 513, 30, 30);

        jLabel12.setIcon(new ImageIcon(getClass().getResource("/resources/icon/red_complain (1).png"))); // NOI18N
        roundPanel1.add(jLabel12);
        jLabel12.setBounds(188, 513, 30, 30);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-home-30.png"))); // NOI18N
        roundPanel1.add(jLabel11);
        jLabel11.setBounds(66, 513, 30, 30);

        jLabel1.setFont(new Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Complaint");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(74, 6, 127, 18);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        roundPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(6, 42, 268, 153);
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
        button1.setBackground(new Color(255, 123, 102));
        button1.setForeground(new Color(255, 255, 255));
        button1.setText("Submit");
        button1.setFont(new Font("Arial", 1, 14)); // NOI18N
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    button1ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roundPanel1.add(button1);
        button1.setBounds(84, 207, 111, 41);

        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 543);
    }
    private void buttonMenu5ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }
    private void buttonMenu6ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }

    private void button1ActionPerformed(ActionEvent evt) throws IOException {
        Complains c = new Complains(User_Background.auth_enter,jTextArea1.getText());
        global_complain.complain_from_costomer(c);
        global_complain.add_to_file();
        User_Background.showForm(new Write_compl());
    }
    private void buttonMenu1ActionPerformed(java.awt.event.ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }

}


