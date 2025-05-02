package User_Online;

import net.miginfocom.swing.MigLayout;
import resources.swing.ButtonMenu;
import resources.swing.ButtonOutLine;
import resources.swing.MyTextField;
import resources.swing.RoundPanel;
import myon.bank.pack.OnlineUsers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Send_appr extends JPanel {
    private ButtonOutLine buttonOutLine1;
    private ButtonMenu buttonMenu1;
    private JLabel jLabel1;
    private MyTextField myTextField1;
    private MyTextField myTextField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pend_comp;
    private RoundPanel roundPanel1;

    static OnlineUsers gou=new OnlineUsers();
    OnlineUsers ou;
    UserAcount uac;
    Approvals_Request appr = new Approvals_Request();
    public Send_appr(UserAcount ua,OnlineUsers ou1) {
        ou=ou1;
        uac=ua;
        initComponents();
        pend_comp.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        roundPanel1.round=35;
        ArrayList<Approvals_Request> appr_array = Approvals_Request.appr;
        for (Approvals_Request ac:appr_array) {
            if(ac.ac_num_from!=null){
                if(ac.ac_num_from.equalsIgnoreCase(User_Background.auth_enter.account_user.getacc())){
                    pend_comp.add(new Requests(ac));
            }}
            System.out.println("Entering.............");
        }

    }
    private void initComponents() {
        roundPanel1 = new RoundPanel();
        jLabel1 = new JLabel();
        myTextField1 = new MyTextField();
        myTextField2 = new MyTextField();
        buttonOutLine1 = new ButtonOutLine();
        buttonMenu1 = new ButtonMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        pend_comp = new javax.swing.JPanel();


        setOpaque(false);
        setLayout(null);

        roundPanel1.setBackground(new Color(255, 255, 255));
        roundPanel1.setLayout(null);

        jLabel1.setFont(new Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Request Money");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(54, 9, 164, 18);

        myTextField1.setBackground(new Color(255, 242, 241));
        myTextField1.setForeground(new Color(255, 123, 102));
        myTextField1.setDisabledTextColor(new Color(255, 123, 102));
        myTextField1.setHint("Account number...");
        myTextField1.setOpaque(true);
        roundPanel1.add(myTextField1);
        myTextField1.setBounds(6, 49, 268, 38);

        myTextField2.setBackground(new Color(255, 242, 241));
        myTextField2.setForeground(new Color(255, 123, 102));
        myTextField2.setHint("Amount...");
        myTextField2.setOpaque(true);
        myTextField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myTextField2ActionPerformed(evt);
            }
        });
        roundPanel1.add(myTextField2);
        myTextField2.setBounds(6, 99, 268, 38);

        buttonOutLine1.setBackground(new Color(255, 123, 102));
        buttonOutLine1.setForeground(new Color(255, 123, 102));
        buttonOutLine1.setText("Request");
        buttonOutLine1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonOutLine1ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonOutLine1);
        buttonOutLine1.setBounds(91, 168, 82, 26);

        buttonMenu1.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-back-15.png"))); // NOI18N
        buttonMenu1.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu1.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu1ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        roundPanel1.add(buttonMenu1);
        buttonMenu1.setBounds(6, 9, 20, 18);
        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pend_comp.setBackground(new java.awt.Color(255, 255, 255));
        pend_comp.setLayout(null);
        jScrollPane1.setViewportView(pend_comp);

        roundPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 210, 260, 340);
        add(roundPanel1);
        roundPanel1.setBounds(0, 0, 280, 543);
    }

    public void show(){
        try {
            gou.add_to_file();
            User_Background.showForm(new UserAcount(ou));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gou.getBalance(ou.account_user));}

    public boolean send_mo() throws InterruptedException, IOException {
        String ac = myTextField1.getText();
        Float cash = Float.parseFloat(myTextField2.getText());
//        System.out.println(ac+cash+ou);
        System.out.println("cash"+gou.getBalance(ou));
        boolean send = gou.send(ou,ac,cash,gou.get_name(myTextField1.getText()));
        if(send){
            show();
            return send;
        }
        return false;

    }

    private void myTextField2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonOutLine1ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new Confirm_appr(myTextField1.getText(),Float.valueOf(myTextField2.getText())));
    }

    private void buttonMenu1ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }

}

