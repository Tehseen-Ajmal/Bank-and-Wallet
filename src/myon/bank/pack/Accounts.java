package myon.bank.pack;

import User_Online.Const;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Accounts implements Serializable {
    private static ArrayList<Accounts> accounts = new ArrayList<>();
    private static Person global_per = new Person();
    private static Integer count = 1346;
    private static Customer global_cus = new Customer();
    private String Account_number;
    private Float Acc_balance = 0.0F;
    private Customer[] customer = new Customer[2];
    private Email_Send email = new Email_Send();
    //    private Transactions transactions;
    private String[] whole_tran;
    private String[][] whole;
    public ArrayList<String[]> trans = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    private SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss a");
    private Random r = new Random();

    public Accounts() {

    }
    Accounts(Customer customer) {

        Date d = new Date();
        String date_d = sdf.format(d);
        String time = stf.format(d);
//        this.customer = new Customer[]{customer};
        this.customer[0] = customer;
        //                     PK-3456-8901-3456-8901

        this.Account_number = "PK-9140-" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + "-" + global_cus.lastCNIC(customer) + "-" + count;
        this.trans.add(new String[]{date_d + " " + time, "Opening Balance", "positive", String.valueOf(0.0F), "positive", String.valueOf(0.0F), "positive", String.valueOf(0.0F)});
        count++;
    }

    public boolean add_account(Accounts acc) {
        accounts.add(acc);
        System.out.println(">>>Account Added Successfully " + acc.Account_number);
        return true;
    }

    public void show_accounts() {
//        int i=1;
//        for (Customer c: customers) {
//            System.out.println(i+". "+c);
//        }
        String temp = "";
        temp = ("""
                \033[1m\033[42m\033[97m |           NAME            |              CNIC              |                EMAIL               |           CUSTOMER-ID           |     ACCOUNT-NUMBERS    |   ACCOUNTS   |   BALANCE   |\033[0m
                \033[32m-----------------------------------------------------------------------------------------------------------------------------------------------------------------\n""");
        for (Accounts a : accounts) {
            Customer c = a.customer[0];
//                temp+=p.toString()+"\n";+"|"
            if (hasacc(a.customer[0])) {
                String cs_n = per_print(global_per.getNAME(c.person), 25);
                String cs_c = per_print(global_per.getCNIC(c.person), 30);
                String cs_e = per_print(global_per.getEMAIL(c.person), 34);
                String cs_cus_ID = per_print(a.customer[0].customer_id, 31);
                String cs_acc_id = per_print(getACCNumber(c), 21);
//                System.out.println(global_acc.count(c).split(":")[0]);
                String cs_acc = per_print(count(c).split(":")[1], 12);
                String cs_cash = per_print(String.valueOf(a.Acc_balance), 11);
                temp += String.format(" | %-25s | %-30s | %-34s | %-31s | %-21s | %-12s |%-11s |\n-------------------------------" +
                        "-----------------------------------------------------------------------------------------------" +
                        "----------------------------------------------------\n", cs_n, cs_c, cs_e, cs_cus_ID, cs_acc_id, cs_acc, cs_cash);

            }

        }
        System.out.println(temp + "\033[0m");
    }

    public boolean remove_single_account(String acc) {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(acc)) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }

    public boolean hasacc(Customer c) {
        for (Accounts a : accounts) {
            if (a.customer[0].equals(c)) {// && a.customer.length==1) {
                return true;
                //            } else if (a.customer[1].equals(c)) {
//                a.customer = new Customer[]{a.customer[0]};
//                return true;
//            }
            }
        }
        return false;
    }

    public String[] hasAccount(Customer c) {
        for (Accounts a : accounts) {
//            System.out.println(a.customer[0]);
            if (a.customer[0].toString().equals(c.toString())) {// && a.customer.length==1) {
                float cash = a.Acc_balance;
                accounts.remove(a);
                return new String[]{"t", String.valueOf(cash)};
//            } else if (a.customer[1].equals(c)) {
//                a.customer = new Customer[]{a.customer[0]};
//                return true;
//            }
            }
//            System.out.println("Account not removed...");
        }
        return new String[]{"f"};
    }

    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\account.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(accounts);
        oos.close();
        fos.close();
    }
    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\account.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        accounts = (ArrayList<Accounts>) ois.readObject();
        count = Integer.valueOf(accounts.get(accounts.size() - 1).Account_number.substring(18));
        count++;
        ois.close();
        fis.close();
    }

    public Float getBalance(Accounts a) {
        return a.Acc_balance;
    }

    public Accounts deposit(String acc_number, Float Cash, String reason) throws IOException {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(acc_number)) {
                System.out.println("Account found: "+a.name_acc());
                a.Acc_balance += Cash;
                a.trans.add(transactions(a, String.valueOf(Cash), "p", reason));
                email.deposit_receipt(global_cus.email_request(a.customer[0]), global_cus.user(a.customer[0]), acc_number, a.Acc_balance, Cash);
                OnlineUsers o = new OnlineUsers();
                if (o.has_online(acc_number)){
                    OnlineUsers ou = o.auth_enter(acc_number);
                    ou.account_user = a;
                }
                add_to_file();
                return a;

            }
        }
        return null;
    }

    public Accounts withdraw(String acc_number, Float Cash, String reason) throws IOException {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(acc_number)) {
                if (a.Acc_balance >= Cash) {
                    a.Acc_balance -= Cash;
                    a.trans.add(transactions(a, String.valueOf(Cash), "n", reason));
                    System.out.println();
                    email.withdraw_receipt(global_cus.email_request(a.customer[0]), global_cus.user(a.customer[0]), acc_number, a.Acc_balance, Cash);
                    System.out.println(">>> Remaining Balance: " + a.Acc_balance);
                    OnlineUsers o = new OnlineUsers();
                    if (o.has_online(acc_number)){
                        OnlineUsers ou = o.auth_enter(acc_number);
                        ou.account_user = a;
                    }
                    add_to_file();
                    return a;
                } else {
                    System.out.println(">>>No Balance");
                }
            }
        }
        return null;
    }

    //    private String[] Transaction(Accounts a,Float cash ,String check,String reason){
//        Date d = new Date();
//        String date_d = sdf.format(d);
//        String time = stf.format(d);
//        String[] string;
//        if (check.equals("n")) {
//            string = new String[]{date_d + " " + time, reason, "negative", String.valueOf(cash), "positive", String.valueOf(0.0F), "positive", String.valueOf(a.Acc_balance)};
//        }else {
//            string = new String[]{date_d + " " + time, reason, "negative", String.valueOf(0.0F), "positive", String.valueOf(cash), "positive", String.valueOf(a.Acc_balance)};
//        }
//
//        return string;
//    }
    public String count(Customer c) {
        Float cash;
        cash = 0.0F;
        int cou = 0;
        for (Accounts a : accounts) {
            if (a.customer[0].toString().equals(c.toString())) {
                cou++;
                cash += a.Acc_balance;
            }
        }
//            if (a.customer[0].equals(c)){//&& a.customer.length==1) {
//                cash+=a.Acc_balance;
//                cou++;
//                return cou +":"+cash;
//            }else if (a.customer[0].equals(c) && a.customer.length==2) {
//                cash+=a.Acc_balance;
//                cou++;
//                return cou +":"+cash;
//            } else if (a.customer[1].equals(c)) {
//                cash+=a.Acc_balance;
//                cou++;
//                return cou +":"+cash;
//            }else {
//                System.out.println(">>>No such account exist");
//            }
//            System.out.println(cou+":"+cash);
//        }return cou +":"+cash;
        return cash + ":" + cou;
    }

    private String[] transactions(Accounts acc, String change, String pos, String reason) {
        Date d = new Date();
        String date_d = sdf.format(d);
        String time = stf.format(d);
        if (!pos.equals("n")) {
            this.whole_tran = new String[]{date_d + " " + time, reason, "positive", String.valueOf(Float.valueOf(change)), "positive", String.valueOf((float) 0), "positive", String.valueOf(getBalance(acc))};
        } else {
            this.whole_tran = new String[]{date_d + " " + time, reason, "positive", String.valueOf((float) 0), "negative", String.valueOf(Float.valueOf(change)), "negative", String.valueOf(getBalance(acc))};
        }
        return this.whole_tran;
    }

    public String getACCNumber(Customer c) {
        String acc = "null";
        for (Accounts a : accounts) {
//            System.out.println(a.Account_number);
            if (a.customer[0].equals(c)) {// && a.customer.length==1) {
//                System.out.println(a.Account_number);
                acc = a.Account_number;
            }
//            }else if (a.customer[0].equals(c) && a.customer.length==2) {
//                return a.Account_number;
//            }if (a.customer[1].equals(c) && a.customer.length==2) {
//                return a.Account_number;
//            }return a.Account_number;
//        }return null;
        }
        return acc;
    }

    public String getacc() {
        return this.Account_number;
    }
    public String getacc(Accounts a) {
        return a.Account_number;
    }

    public void search(String ac) {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(ac)) {
                System.out.println(">>>Account Found.");
                System.out.println(a);

            }
        }
    }
    public boolean search_acc(String ac) {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(ac)) {
                return true;
            }
        }return false;
    }
    public Accounts get_acc(String ac) {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(ac)) {
                return a;
            }
        }return null;
    }
    public String get_name(String ac) {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(ac)) {
                return global_cus.user(a.customer[0]);
            }
        }return null;
    }

    public void statement(String id) throws IOException {
        for (Accounts a : accounts) {
            if (a.Account_number.equalsIgnoreCase(id)) {
                String temp = "";
                temp = ("""
                        \033[1m\033[42m\033[97m |      DATE       |      TIME      |           DESCRIPTION            |             DEPOSIT             |             WITHDRAW             |          TOTAL BALANCE          |\033[0m
                        \033[32m------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n""");
                for (String[] st : a.trans) {
//                    System.out.println(Arrays.toString(st));
                    for (String s : st) {
//                        System.out.print(s+" ");
                        String cs_n = per_print(s, 30);
//                System.out.println(global_acc.count(c).split(":")[0]);
                        if (!(s.equalsIgnoreCase("positive") || s.equalsIgnoreCase("negative"))) {
                            temp += String.format(" | %-30s |", cs_n);
                        }
                    }
                    temp += "\n";
                }

                System.out.println(temp + "\033[0m");
                email.html_creator(a.trans, global_cus.email_request(a.customer[0]), global_cus.user(a.customer[0]), a.Account_number);
            }
        }
    }
    private String pass_gen(Accounts a){
        String pass ="1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM@#;,?/|!$%&";
        String n_pass = "";
        for (int i = 0; i < 10; i++) {
            n_pass+=pass.charAt(r.nextInt(pass.length()));
        }
        return n_pass;
    }
    private String pass_gen_otp(Accounts a){
        String pass ="1234567890BANKMYON";
        String n_pass = "";
        for (int i = 0; i < 6; i++) {
            n_pass+=pass.charAt(r.nextInt(pass.length()));
        }
        return n_pass;
    }
    public String email_acc(){
        return global_cus.email_request(this.customer[0]);
    }public String name_acc(){
        return global_cus.user(this.customer[0]);
    }
    public String passWord(Accounts a){
        String pass =pass_gen(a);
        email.send_password(global_cus.email_request(a.customer[0]),global_cus.user(a.customer[0]),pass,a.Account_number);
        return pass;
    }
    public String passWord_forgot(Accounts a){
        String pass =pass_gen_otp(a);
        System.out.println(pass);
        email.reset_pass(global_cus.email_request(a.customer[0]),global_cus.user(a.customer[0]),pass,a.Account_number);
        return pass;
    }

    private String per_print(String text, int total) {
        int spaces = (total - text.length()) / 2;
        return String.format("%" + (spaces + text.length()) + "s", text);
    }

    @Override
    public String toString() {
        return "\033[1m\033[42m\033[30mName :" + global_cus.user(customer[0]) + "\nAccount_number='" + Account_number + '\'' +
                ", Acc_balance=" + Acc_balance +
                ", customer=" + Arrays.toString(customer) + "\033[0m";
    }
}