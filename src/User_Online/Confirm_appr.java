package User_Online;

import resources.swing.ButtonMenu;
import resources.swing.Gradient;
import resources.swing.RoundPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class Confirm_appr extends JPanel {
    private ButtonMenu buttonMenu1;
    private ButtonMenu buttonMenu2;
    private Gradient gradient1;
    private Gradient gradient2;
    private Gradient gradient3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private RoundPanel roundPanel1;
    public Approvals_Request appr;
    public static Approvals_Request global_appr = new Approvals_Request();
    String ac_nom = "";
    Float cash;
    public Confirm_appr(String ac,Float ca) {
        ac_nom =ac;
        cash =ca;
        initComponents();
        roundPanel1.round = 35;
        gradient1.col1 = new Color(247,248,248);
        gradient1.col2 = new Color(252,252,252);
        gradient2.col2 = new Color(248,248,248);
        gradient2.col1 = new Color(252,252,252);
        gradient3.col1 = new Color(255,129,114);
        gradient3.col2 = new Color(249,123,100);
    }

    private void initComponents() {

        roundPanel1 = new RoundPanel();
        jLabel1 = new JLabel();
        buttonMenu1 = new ButtonMenu();
        gradient1 = new Gradient();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        gradient2 = new Gradient();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        gradient3 = new Gradient();
        buttonMenu2 = new ButtonMenu();

        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(255, 255, 255));
        roundPanel1.setLayout(null);

        jLabel1.setFont(new Font("Arial", 1, 15));
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Request");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(54, 9, 164, 18);

        buttonMenu1.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-back-15.png")));
        buttonMenu1.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu1.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu1ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roundPanel1.add(buttonMenu1);
        buttonMenu1.setBounds(6, 9, 20, 18);

        gradient1.setLayout(null);

        jLabel2.setFont(new Font("Copperplate Gothic Bold", 0, 14));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText(User_Background.global_online.get_name(ac_nom));
        gradient1.add(jLabel2);
        jLabel2.setBounds(6, 6, 256, 25);

        jLabel3.setFont(new Font("Microsoft YaHei UI", 0, 12));
        jLabel3.setForeground(new Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText(ac_nom);
        gradient1.add(jLabel3);
        jLabel3.setBounds(6, 31, 256, 25);

        roundPanel1.add(gradient1);
        gradient1.setBounds(6, 33, 268, 63);

        gradient2.setLayout(null);

        jLabel4.setFont(new Font("Candara Light", 1, 14));
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("Amount");
        gradient2.add(jLabel4);
        jLabel4.setBounds(6, 6, 256, 25);

        jLabel5.setFont(new Font("Bauhaus 93", 1, 36));
        jLabel5.setForeground(new Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText(cash+".RS");
        gradient2.add(jLabel5);
        jLabel5.setBounds(12, 37, 250, 54);

        roundPanel1.add(gradient2);
        gradient2.setBounds(6, 102, 268, 91);

        gradient3.setLayout(null);

        buttonMenu2.setText("Request");
        buttonMenu2.setFont(new Font("Segoe UI Black", 0, 15));
        buttonMenu2.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu2ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gradient3.add(buttonMenu2);
        buttonMenu2.setBounds(6, 6, 196, 37);

        roundPanel1.add(gradient3);
        gradient3.setBounds(34, 211, 208, 49);

        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 540);
    }

    private void buttonMenu1ActionPerformed(ActionEvent evt) throws IOException {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }

    private void buttonMenu2ActionPerformed(ActionEvent evt) throws IOException {
        Approvals_Request ac = new Approvals_Request(User_Background.auth_enter.account_user.getacc(),ac_nom,User_Background.auth_enter.get_name(ac_nom),User_Background.auth_enter.account_user.name_acc(),cash,0);
        appr=ac;
        global_appr.add_appr(ac);
        global_appr.add_to_file();
//        System.out.println(ac.ac_num_to);
        System.out.println(User_Background.auth_enter.account_user.getacc());
        System.out.println(ac_nom);
        System.out.println(User_Background.auth_enter.get_name(ac_nom));
        System.out.println(User_Background.auth_enter.account_user.name_acc());
//        Send_mone.gou.send(User_Background.auth_enter,ac_nom, cash,"Ibft");//PK-9140-1822-1341-347
//        Send_mone.gou.send_opt(User_Background.auth_enter.account_user);
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }

}

