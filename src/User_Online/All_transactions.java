package User_Online;

import resources.swing.ButtonMenu;
import resources.swing.RoundPanel;
import myon.bank.pack.OnlineUsers;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class All_transactions extends JPanel {
    private ArrayList<Transactions> tr = new ArrayList<>();
    private RoundPanel out_pane;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private ButtonMenu buttonMenu1;
    private JScrollPane jScrollPane3;
    private JPanel transactions;

    public All_transactions(OnlineUsers ou) {
        initComponents();
        setupTransactions(ou);
    }

    private void setupTransactions(OnlineUsers ou) {
        out_pane.round = 35;
        transactions.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        for (String[] a : ou.account_user.trans) {
            if (a[4].charAt(0) == 'p') {
                tr.add(new Transactions(a[1], a[0], String.valueOf(a[4].charAt(0)), String.valueOf((Float.parseFloat(a[3])))));
            } else {
                tr.add(new Transactions(a[1], a[0], String.valueOf(a[4].charAt(0)), String.valueOf(Float.parseFloat(a[5]))));
            }
        }
        Transactions temp=null;
        for (int i = tr.size() - 1; i >= 0; i--) {

            if(temp!=null){
                if(!temp.time_us.toString().substring(0,12).equals(tr.get(i).time_us.toString().substring(0,12))){
                    transactions.add(new Date_user(tr.get(i).time_us.toString().substring(0,12)));
                }
            }else if(temp==null) {
                transactions.add(new Date_user(tr.get(i).time_us.toString().substring(0,12)));

            }
            transactions.add(tr.get(i));
            temp = tr.get(i);
        }
    }

    private void initComponents() {
        out_pane = new RoundPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        buttonMenu1 = new ButtonMenu();
        jScrollPane3 = new JScrollPane();
        transactions = new JPanel();

        setBackground(new Color(255, 255, 255));
        setOpaque(false);
        setLayout(null);

        out_pane.setBackground(new Color(255, 255, 255));
        out_pane.setLayout(null);

        jLabel1.setFont(new Font("Arial", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Transaction History");
        out_pane.add(jLabel1);
        jLabel1.setBounds(79, 6, 127, 16);

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-back-15.png"))); // NOI18N
        out_pane.add(jLabel2);
        jLabel2.setBounds(6, 6, 21, 16);

        buttonMenu1.setText("buttonMenu1");
        buttonMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu1ActionPerformed(evt);
            }
        });
        out_pane.add(buttonMenu1);
        buttonMenu1.setBounds(3, 2, 20, 30);

        jScrollPane3.setBackground(new Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setOpaque(false);

        transactions.setBackground(new Color(255, 255, 255));
        transactions.setLayout(new BorderLayout());
        jScrollPane3.setViewportView(transactions);
        jScrollPane3.setViewportView(transactions);

        out_pane.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 260, 480);

        add(out_pane);
        out_pane.setBounds(0, 0, 280, 540);
    }
    private void buttonMenu1ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }
}

