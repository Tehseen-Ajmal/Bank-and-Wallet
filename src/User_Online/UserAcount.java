package User_Online;

import resources.swing.ButtonMenu;
import resources.swing.Gradient;
import resources.swing.RoundPanel;
import myon.bank.pack.OnlineUsers;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class UserAcount extends JPanel {
    static OnlineUsers g_o = new OnlineUsers();
    OnlineUsers ou;
    ArrayList<Transactions> tr = new ArrayList<>();

    String balance;
    private JLabel balance_user;
    private JLabel balance_user1;
    private ButtonMenu buttonMenu1;
    private ButtonMenu buttonMenu2;
    private ButtonMenu buttonMenu3;
    private ButtonMenu buttonMenu4;
    private ButtonMenu buttonMenu5;
    private ButtonMenu buttonMenu6;
    private Gradient gradient1;
    private Gradient gradient2;
    private Gradient gradient3;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JScrollPane jScrollPane2;
    private RoundPanel roundPanel1;
    private RoundPanel roundPanel2;
    private JPanel trans;
    public void create(OnlineUsers ou1){
        ou=ou1;
        balance = String.valueOf(ou.account_user.getBalance(ou1.account_user).intValue());
        System.out.println("user balance:"+balance);
        initComponents();
    }
    public UserAcount(OnlineUsers ou1) {
        create(ou1);

        trans.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        roundPanel1.round = 35;
        gradient1.col1 = new Color(1,227,189);
        gradient1.col2 = new Color(0,212,178);

        gradient2.col1 = new Color(31,172,252);
        gradient2.col2 = new Color(30,156,232);

        gradient3.col1 = new Color(255,129,114);
        gradient3.col2 = new Color(249,123,100);

        for (String[] a : ou.account_user.trans) {
            if (a[4].charAt(0) =='p') {
                tr.add(new Transactions(a[1],a[0],String.valueOf(a[4].charAt(0)),String.valueOf((Float.parseFloat(a[3])))));
            }else {
                tr.add(new Transactions(a[1],a[0],String.valueOf(a[4].charAt(0)),String.valueOf(Float.parseFloat(a[5]))));
            }

        }
        for (int i = tr.size()-1; i>=0 ; i--) {
            trans.add(tr.get(i));
        }

    }

    private void initComponents() {

        roundPanel1 = new RoundPanel();
        gradient1 = new Gradient();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel6 = new JLabel();
        balance_user = new JLabel();
        balance_user1 = new JLabel();
        buttonMenu3 = new ButtonMenu();
        gradient2 = new Gradient();
        jLabel2 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        buttonMenu1 = new ButtonMenu();
        gradient3 = new Gradient();
        jLabel5 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        buttonMenu2 = new ButtonMenu();
        roundPanel2 = new RoundPanel();
        buttonMenu4 = new ButtonMenu();
        jLabel1 = new JLabel();
        jScrollPane2 = new JScrollPane();
        trans = new JPanel();
        jLabel11 = new JLabel();
        buttonMenu5 = new ButtonMenu();
        jLabel12 = new JLabel();
        buttonMenu6 = new ButtonMenu();

        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(242, 246, 247));
        roundPanel1.setLayout(null);

        gradient1.setLayout(null);

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-right-30.png"))); // NOI18N
        jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gradient1.add(jLabel3);
        jLabel3.setBounds(100, 166, 30, 30);

        jLabel4.setIcon(new ImageIcon(getClass().getResource("/resources/icon/Mastercard1.png"))); // NOI18N
        jLabel4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        gradient1.add(jLabel4);
        jLabel4.setBounds(14, 166, 45, 32);

        jLabel6.setFont(new Font("Yu Gothic Medium", 0, 12)); // NOI18N
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel6.setText("Current Balance");
        jLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gradient1.add(jLabel6);
        jLabel6.setBounds(14, 14, 116, 20);

        balance_user.setFont(new Font("Arial Unicode MS", 1, 20)); // NOI18N
        balance_user.setForeground(new Color(255, 255, 255));
        balance_user.setText(balance);
        gradient1.add(balance_user);
        balance_user.setBounds(52, 34, 90, 35);

        balance_user1.setFont(new Font("Arial Unicode MS", 1, 20)); // NOI18N
        balance_user1.setForeground(new Color(255, 255, 255));
        balance_user1.setText("Rs.");
        gradient1.add(balance_user1);
        balance_user1.setBounds(14, 34, 32, 35);

        buttonMenu3.setEffectColor(new Color(0, 200, 160));
        buttonMenu3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu3ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gradient1.add(buttonMenu3);
        buttonMenu3.setBounds(6, 6, 136, 192);

        roundPanel1.add(gradient1);
        gradient1.setBounds(12, 15, 148, 204);

        gradient2.setLayout(null);

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-down-30.png"))); // NOI18N
        jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gradient2.add(jLabel2);
        jLabel2.setBounds(6, 6, 30, 30);

        jLabel7.setFont(new Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setText("Load");
        gradient2.add(jLabel7);
        jLabel7.setBounds(12, 42, 49, 19);

        jLabel8.setFont(new Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setText("Money");
        gradient2.add(jLabel8);
        jLabel8.setBounds(13, 61, 69, 19);

        buttonMenu1.setEffectColor(new Color(25, 150, 222));
        buttonMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu1ActionPerformed(evt);
            }
        });
        gradient2.add(buttonMenu1);
        buttonMenu1.setBounds(6, 6, 84, 84);

        roundPanel1.add(gradient2);
        gradient2.setBounds(172, 15, 96, 96);

        gradient3.setLayout(null);

        jLabel5.setIcon(new ImageIcon(getClass().getResource("/resources/icon/side_arr.png"))); // NOI18N
        jLabel5.setText("        ");
        jLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gradient3.add(jLabel5);
        jLabel5.setBounds(58, 6, 32, 30);

        jLabel9.setFont(new Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new Color(255, 255, 255));
        jLabel9.setText("Send &");
        gradient3.add(jLabel9);
        jLabel9.setBounds(6, 48, 70, 19);

        jLabel10.setFont(new Font("Arial", 1, 16)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("Request");
        gradient3.add(jLabel10);
        jLabel10.setBounds(7, 67, 69, 19);

        buttonMenu2.setEffectColor(new Color(239, 113, 90));
        buttonMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu2ActionPerformed(evt);
            }
        });
        gradient3.add(buttonMenu2);
        buttonMenu2.setBounds(6, 6, 84, 84);

        roundPanel1.add(gradient3);
        gradient3.setBounds(172, 123, 96, 96);

        roundPanel2.setBackground(new Color(255, 255, 255));
        roundPanel2.setLayout(null);

        buttonMenu4.setForeground(new Color(226, 135, 117));
        buttonMenu4.setText("See all");
        buttonMenu4.setFont(new Font("Calibri", 1, 12)); // NOI18N
        buttonMenu4.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu4.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonMenu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu4ActionPerformed(evt);
            }
        });
        roundPanel2.add(buttonMenu4);
        buttonMenu4.setBounds(198, 7, 53, 18);

        jLabel1.setFont(new Font("Yu Gothic Medium", 0, 13)); // NOI18N
        jLabel1.setForeground(new Color(130, 138, 141));
        jLabel1.setText("   Transactions");
        roundPanel2.add(jLabel1);
        jLabel1.setBounds(0, 6, 100, 22);

        jScrollPane2.setBackground(new Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        trans.setBackground(new Color(255, 255, 255));
        trans.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(trans);
        jScrollPane2.setViewportView(trans);

        roundPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(6, 34, 244, 219);

        roundPanel1.add(roundPanel2);
        roundPanel2.setBounds(12, 241, 256, 259);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/resources/icon/red_home.png"))); // NOI18N
        roundPanel1.add(jLabel11);
        jLabel11.setBounds(60, 510, 30, 30);
        roundPanel1.add(buttonMenu5);
        buttonMenu5.setBounds(60, 510, 30, 30);

        jLabel12.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-complaint-30.png"))); // NOI18N
        roundPanel1.add(jLabel12);
        jLabel12.setBounds(180, 510, 30, 30);

        buttonMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu6ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu6);
        buttonMenu6.setBounds(180, 510, 30, 30);

        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 543);    }

    private void buttonMenu3ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        User_Background.showForm(new Bill_Payment());
//        JPanel p = new JPanel();
//        p.setBackground(Color.GREEN);
//        p.setSize(543,280);
//        User_Background.showForm(p);
    }

    private void buttonMenu1ActionPerformed(ActionEvent evt) {

        User_Background.showForm(new Send_appr(this,User_Background.auth_enter));
    }

    private void buttonMenu2ActionPerformed(ActionEvent evt) {
//        JPanel p = new JPanel();
//        p.setBackground(Color.red);
//        p.setSize(543,280);
        User_Background.showForm(new Send_mone(this,User_Background.auth_enter));
    }

    private void buttonMenu4ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new All_transactions(ou));
    }

    private void buttonMenu6ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Complaint());
    }
}

