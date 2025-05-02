
package User_Online;


import net.miginfocom.swing.MigLayout;
import resources.swing.ButtonMenu;
import resources.swing.ButtonOutLine;
import resources.swing.MyTextField;
import resources.swing.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Bill_Payment extends javax.swing.JPanel {
    private ButtonOutLine buttonOutLine1;
    private ButtonMenu buttonMenu1;
    private JLabel jLabel1;
    private MyTextField myTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pend_comp;
    private RoundPanel roundPanel1;
    public static Bill_History billHistory = new Bill_History();

    public Bill_Payment() throws IOException, ClassNotFoundException {
        try {
            billHistory.fileToArrayList(); // Load history from file
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        initComponents();
        roundPanel1.round = 35;
        pend_comp.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));

        // Initialize BillHistory instance
//        billHistory = new Bill_History("", "", 0.0, false);
//        billHistory.fileToArrayList();

    }
        private void initComponents() {
        roundPanel1 = new RoundPanel();
        jLabel1 = new JLabel();
        myTextField1 = new MyTextField();
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
        jLabel1.setText("Lesco Bill Payment");
        roundPanel1.add(jLabel1);
        jLabel1.setBounds(54, 9, 164, 18);

        myTextField1.setBackground(new Color(255, 242, 241));
        myTextField1.setForeground(new Color(255, 123, 102));
        myTextField1.setDisabledTextColor(new Color(255, 123, 102));
        myTextField1.setHint("Reference No.");
        myTextField1.setOpaque(true);
        roundPanel1.add(myTextField1);
        myTextField1.setBounds(6, 49, 268, 38);



        buttonOutLine1.setBackground(new Color(255, 123, 102));
        buttonOutLine1.setForeground(new Color(255, 123, 102));
        buttonOutLine1.setText("Load Bill");
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
                buttonMenu1ActionPerformed(evt);
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
        buttonOutLine1.setEnabled(true);
    }

    private void buttonOutLine1ActionPerformed(ActionEvent evt) {
        String refNumber = myTextField1.getText();

        // Validate that the reference number is not empty
        if (refNumber.isEmpty() || refNumber.length()!=18) {
            JOptionPane.showMessageDialog(this, "Please enter a reference number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the bill already exists in the history
        if (billHistory.isBillPaid(refNumber)) {
            // Bill is already paid, show message
            JOptionPane.showMessageDialog(this, "This bill has already been paid.", "Bill Paid", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Bill doesn't exist or isn't paid yet, so we need to load and show the bill details
            try {
                String pythonScriptPath = Const.basePath+"resources/bill_fetcher.py";
                BillLoader billLoader = new BillLoader(pythonScriptPath);

                // Append '-r' to the reference number as per the original code
//                String refWithSuffix = refNumber + "-r";
                String refWithSuffix = refNumber;

                // Fetch the bill details
                String[] billDetails = billLoader.fetchBillDetails(refWithSuffix);

                // Show the confirmation payment form with the fetched details
                User_Background.showForm(new Confirm_Pay(billDetails[0], refWithSuffix, billDetails[1], billDetails[3], billDetails[2]));

                // After the payment is confirmed, add the bill to history and mark it as paid
                Bill_History newBill = new Bill_History(refWithSuffix, billDetails[3], Double.parseDouble(billDetails[1]), false);
                billHistory.addBill(newBill);
                billHistory.addToFile(); // Save the updated history to file

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching bill details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buttonMenu1ActionPerformed(ActionEvent evt) {
        User_Background.showForm(new UserAcount(User_Background.auth_enter));
    }
}
