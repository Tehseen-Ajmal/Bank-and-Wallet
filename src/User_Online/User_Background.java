package User_Online;

import myon.bank.pack.Accounts;
import myon.bank.pack.Complains;
import myon.bank.pack.OnlineUsers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class User_Background extends JFrame {

    private JLabel jLabel1;
    private JPanel jPanel1;
    private static JPanel user_backpanel;
    public static OnlineUsers global_online = new OnlineUsers();
    public static Accounts global_account = new Accounts();
    //PK-9140-1156-1234-346
    static Approvals_Request ac = new Approvals_Request();
    public static Complains glob_comp = new Complains();
//    static String ac_n ="PK-9140-7474-1341-1346  , PK-9140-6638-4513-1346 46-11311-1984313-U
//    static String password = "i2/woayjIr";  PK-9140-4643-1232-1347
    public static OnlineUsers auth_enter;
    BufferedImage img2 = ImageIO.read(new File(Const.basePath + "src\\resources\\icon\\back_blur.jpg"));
    Image img21 = img2.getScaledInstance(900,600,BufferedImage.SCALE_DEFAULT);

    public static void showForm(Component com) {
        user_backpanel.removeAll();
        user_backpanel.add(com);
        user_backpanel.revalidate();
        user_backpanel.repaint();
    }
    public static void create(String ac_n, String password) throws IOException, ClassNotFoundException {
        global_account.file_to_arraylist();
        global_online.file_to_arraylist();
        glob_comp.file_to_arraylist();

        ac.file_to_arraylist();
        System.out.println("ENTER "+ac_n+" "+password);
        auth_enter = global_online.auth_enter(ac_n, password);
        System.out.println(auth_enter);
        showForm(new UserAcount(auth_enter));
    }
    public User_Background() throws IOException, ClassNotFoundException {
        global_account.file_to_arraylist();
        global_online.file_to_arraylist();
        initComponents();
        jLabel1.setIcon(new ImageIcon(img21));
        showForm(new Login_page());

    }

    private void initComponents() throws IOException, ClassNotFoundException {
        jPanel1 = new JPanel();
        user_backpanel = new JPanel();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(296, 580));
        setResizable(true);
        setSize(new Dimension(900, 600));

        jPanel1.setLayout(null);

        user_backpanel.setOpaque(false);
        user_backpanel.setLayout(new BorderLayout());
        jPanel1.add(user_backpanel);
        user_backpanel.setBounds(0, 0, 280, 543);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 900, 600);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);

    }
    private void store_files() throws IOException {
        global_online.add_to_file();
        glob_comp.add_to_file();

    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            User_Background userBackground = new User_Background();
            userBackground.setVisible(true);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

