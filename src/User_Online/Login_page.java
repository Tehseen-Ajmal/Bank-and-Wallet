package User_Online;

import myon.bank.pack.Accounts;
import myon.bank.pack.Driver;
import myon.bank.pack.OnlineUsers;
import resources.swing.*;
import resources.swing.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login_page extends JPanel {
    private Button button1;
    private ButtonMenu buttonMenu2;
    private ButtonMenu buttonMenu3;
    private ButtonMenu buttonMenu4;
    private Gradient gradient1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private MyPasswordField myPasswordField1;
    private MyTextField myTextField1;
//    private static OnlineUsers global_online = new OnlineUsers();
//    public static Accounts global_account = new Accounts();

    Image img21;
    int showing_pass = 0;
    public Login_page() throws IOException {
        BufferedImage img2 = ImageIO.read(new File(Const.basePath + "src\\resources\\icon\\bank.png"));
        img21 = img2.getScaledInstance(200,160,BufferedImage.SCALE_SMOOTH);
        initComponents();
        gradient1.col1 = new Color(255,255,255);
        gradient1.col2 = new Color(249,245,249);
        gradient1.round = 40;
        myTextField1.col1 = new Color(230, 245, 241);

    }

    private void initComponents() {

        gradient1 = new Gradient();
        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        myTextField1 = new MyTextField();
        button1 = new Button();
        buttonMenu2 = new ButtonMenu();
        myPasswordField1 = new MyPasswordField();
        buttonMenu3 = new ButtonMenu();
        buttonMenu4 = new ButtonMenu();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();

        setOpaque(false);
        setLayout(null);

        gradient1.setLayout(null);

        jLabel2.setIcon(new ImageIcon(img21));
        gradient1.add(jLabel2);
        jLabel2.setBounds(40, 40, 200, 160);

        jLabel1.setFont(new Font("Arial", 1, 15));
        jLabel1.setForeground(new Color(255, 123, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Login");
        gradient1.add(jLabel1);
        jLabel1.setBounds(52, 0, 164, 18);

        myTextField1.setBackground(new Color(230, 245, 241));
        myTextField1.setForeground(new Color(51, 51, 51));
        myTextField1.setHint("Account Number");
        gradient1.add(myTextField1);
        myTextField1.setBounds(10, 230, 260, 38);

        button1.setBackground(new Color(255, 129, 114));
        button1.setForeground(new Color(255, 255, 255));
        button1.setText("LOGIN");
        button1.setFont(new Font("Segoe UI", 1, 14));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    button1ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gradient1.add(button1);
        button1.setBounds(80, 340, 120, 30);

        buttonMenu2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-closed-eye-15.png")));
        buttonMenu2.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu2.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonMenu2ActionPerformed(evt);
            }
        });
        gradient1.add(buttonMenu2);
        buttonMenu2.setBounds(240, 290, 20, 18);

        myPasswordField1.setBackground(new Color(230, 245, 241));
        myPasswordField1.setForeground(new Color(51, 51, 51));
        myPasswordField1.setHint("Password");
        gradient1.add(myPasswordField1);
        myPasswordField1.setBounds(10, 280, 260, 38);

        buttonMenu3.setForeground(new Color(255, 153, 153));
        buttonMenu3.setText("I forgot my Password");
        buttonMenu3.setHorizontalAlignment(SwingConstants.CENTER);
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
        buttonMenu3.setBounds(60, 380, 150, 20);

        buttonMenu4.setForeground(new Color(255, 153, 153));
        buttonMenu4.setText("Register");
        buttonMenu4.setHorizontalAlignment(SwingConstants.CENTER);
        buttonMenu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buttonMenu4ActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gradient1.add(buttonMenu4);
        buttonMenu4.setBounds(60, 400, 150, 20);

        jLabel3.setForeground(new Color(1, 227, 189));
        jLabel3.setText("this service.");
        gradient1.add(jLabel3);
        jLabel3.setBounds(10, 510, 260, 30);

        jLabel4.setForeground(new Color(1, 227, 189));
        jLabel4.setText("You must have an account in the bank to avail");
        gradient1.add(jLabel4);
        jLabel4.setBounds(10, 500, 260, 16);

        add(gradient1);
        gradient1.setBounds(0, 0, 280, 543);
    }

    private void buttonMenu2ActionPerformed(ActionEvent evt) {
        if(showing_pass==0){
            myPasswordField1.setEchoChar((char)0);
            showing_pass = 1;
            buttonMenu2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-eye-15.png")));
        }
        else{
            myPasswordField1.setEchoChar('*');
            showing_pass = 0;
            buttonMenu2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/icons8-closed-eye-15.png")));
        }
    }

    private void button1ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        System.out.println(myPasswordField1.getPassword());
        char[] password = myPasswordField1.getPassword();
        User_Background.create(myTextField1.getText(), new String(password));
    }

    private void buttonMenu3ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        User_Background.global_online.file_to_arraylist();
        if(User_Background.global_online.has_online(myTextField1.getText())){
            User_Background.showForm(new Change_passWord(myTextField1.getText()));
        }else{
            JOptionPane.showMessageDialog(this,"Please Enter Valid Account Number...","Warning",JOptionPane.WARNING_MESSAGE);
        }
        //forgot
    }

    private void buttonMenu4ActionPerformed(ActionEvent evt) throws IOException, ClassNotFoundException {
        String ac = myTextField1.getText();
        if (!(User_Background.global_online.has_online(ac))) {
            Accounts acc = User_Background.global_account.get_acc(ac);
            System.out.println(User_Background.global_account.get_acc(ac));
            System.out.println(User_Background.global_account.get_name(ac));
            OnlineUsers online = new OnlineUsers(acc);
            User_Background.global_online.add_online_user(online);
            JOptionPane.showMessageDialog(this,"Secret Password has been sent to your email "+User_Background.global_account.get_acc(ac).email_acc(),"Message",JOptionPane.INFORMATION_MESSAGE);
            User_Background.global_online.add_to_file();
//            User_Background.global_online.file_to_arraylist();
//            User_Background.showForm(new Change_passWord(ac));
        }else {
            JOptionPane.showMessageDialog(this,"Account Already exist If you forget password click on forget password...","Warning",JOptionPane.WARNING_MESSAGE);

        }

    }


}

