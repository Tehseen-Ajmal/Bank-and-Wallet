package User_Online;

import resources.swing.ButtonMenu;
import resources.swing.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Complaint extends JPanel {

    private ButtonMenu buttonMenu2;
    private ButtonMenu buttonMenu5;
    private ButtonMenu buttonMenu6;
    private ButtonMenu buttonMenu7;
    private ButtonMenu buttonMenu8;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private RoundPanel roundPanel1;
    private RoundPanel roundPanel2;
    private RoundPanel roundPanel3;
    private RoundPanel roundPanel4;
    public Complaint() {
        initComponents();
        roundPanel1.round=35;
    }
    private void initComponents() {
        roundPanel1 = new RoundPanel();
        jLabel1 = new JLabel();
        roundPanel2 = new RoundPanel();
        buttonMenu2 = new ButtonMenu();
        roundPanel3 = new RoundPanel();
        buttonMenu7 = new ButtonMenu();
        roundPanel4 = new RoundPanel();
        buttonMenu8 = new ButtonMenu();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        buttonMenu5 = new ButtonMenu();
        buttonMenu6 = new ButtonMenu();
        jLabel12 = new JLabel();
        jLabel11 = new JLabel();

        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(255, 255, 255));
        roundPanel1.setLayout(null);

        jLabel1.setFont(new Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Help center");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(77, 15, 127, 18);

        roundPanel2.setBackground(new Color(255, 242, 241));
        roundPanel2.setLayout(null);

        buttonMenu2.setForeground(new Color(255, 123, 102));
        buttonMenu2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/complain (1).png"))); // NOI18N
        buttonMenu2.setText("New complaint");
        buttonMenu2.setFont(new Font("Arial", 0, 15)); // NOI18N
        buttonMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu2ActionPerformed(evt);
            }
        });
        roundPanel2.add(buttonMenu2);
        buttonMenu2.setBounds(6, 6, 256, 46);

        roundPanel1.add(roundPanel2);
        roundPanel2.setBounds(6, 45, 268, 58);

        roundPanel3.setBackground(new Color(255, 242, 241));
        roundPanel3.setLayout(null);

        buttonMenu7.setForeground(new Color(255, 123, 102));
        buttonMenu7.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-pending-30.png"))); // NOI18N
        buttonMenu7.setText("Pending");
        buttonMenu7.setFont(new Font("Arial", 0, 15)); // NOI18N
        buttonMenu7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu7ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roundPanel3.add(buttonMenu7);
        buttonMenu7.setBounds(6, 6, 256, 46);

        roundPanel1.add(roundPanel3);
        roundPanel3.setBounds(6, 109, 268, 58);

        roundPanel4.setBackground(new Color(255, 242, 241));
        roundPanel4.setLayout(null);

        buttonMenu8.setForeground(new Color(255, 123, 102));
        buttonMenu8.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-customer-support-30.png"))); // NOI18N
        buttonMenu8.setText("Solved");
        buttonMenu8.setFont(new Font("Arial", 0, 15)); // NOI18N
        buttonMenu8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu8ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roundPanel4.add(buttonMenu8);
        buttonMenu8.setBounds(6, 6, 256, 46);

        roundPanel1.add(roundPanel4);
        roundPanel4.setBounds(6, 173, 268, 58);

        jLabel2.setFont(new Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("You can contact us at our customer support email");
        roundPanel1.add(jLabel2);
        jLabel2.setBounds(6, 313, 268, 14);

        jLabel3.setForeground(new Color(0, 212, 178));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText(Const.email_acc);
        roundPanel1.add(jLabel3);
        jLabel3.setBounds(122, 333, 134, 16);

        jLabel4.setFont(new Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Customer Support:");
        roundPanel1.add(jLabel4);
        jLabel4.setBounds(6, 335, 110, 14);

        buttonMenu5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu5ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu5);
        buttonMenu5.setBounds(60, 513, 30, 30);

        buttonMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu6ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu6);
        buttonMenu6.setBounds(182, 513, 30, 30);

        jLabel12.setIcon(new ImageIcon(getClass().getResource("/resources/icon/red_complain (1).png"))); // NOI18N
        roundPanel1.add(jLabel12);
        jLabel12.setBounds(182, 513, 30, 30);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-home-30.png"))); // NOI18N
        roundPanel1.add(jLabel11);
        jLabel11.setBounds(60, 513, 30, 30);

        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 543);
    }

    private void buttonMenu6ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }

    private void buttonMenu5ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }

    private void buttonMenu2ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Write_compl());
    }

    private void buttonMenu7ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        User_Background.showForm(new Pendin_complaints());
    }

    private void buttonMenu8ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        User_Background.showForm(new Resolved());
    }
}

