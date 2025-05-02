package myon.bank.pack;

import User_Online.Const;

import java.io.*;
import java.util.ArrayList;

public class OnlineUsers extends Accounts implements Serializable {
    private static ArrayList<OnlineUsers> online_accounts = new ArrayList<>();
    public Accounts account_user;
//    public Accounts global_account = new Accounts();
    public String passcode;
    private String temp_pass;
    public boolean add_online_user(OnlineUsers ou){
        online_accounts.add(ou);
        return true;
    }

    public boolean has_online(String acc){
        for (OnlineUsers ou : online_accounts) {
            if (getacc(ou.account_user).equalsIgnoreCase(acc)) {
                return true;
            }
        }return false;
    }
    public boolean authenticate(String ac_nm,String pass){
        for (OnlineUsers ou:online_accounts) {
            if (getacc(ou.account_user).equalsIgnoreCase(ac_nm) && ou.passcode.equals(pass)) {
                return true;
            }
        }return false;
    }
    public OnlineUsers auth_enter(String ac_nm,String pass){
        for (OnlineUsers ou:online_accounts) {
            if (getacc(ou.account_user).equalsIgnoreCase(ac_nm) && ou.passcode.equals(pass)) {
                return ou;
            }
        }return null;
    }
    public OnlineUsers auth_enter(String ac_nm){
        for (OnlineUsers ou:online_accounts) {
            if (getacc(ou.account_user).equalsIgnoreCase(ac_nm)) {
                return ou;
            }
        }return null;
    }

    public boolean change_pass(OnlineUsers uo,String new_pass){
        uo.passcode = new_pass;
        return true;
    }
    public boolean send(OnlineUsers u,String acc_nm,Float cash,String reason) throws IOException {
        if (search_acc(acc_nm)) {
            System.out.println("........................>"+getBalance(u.account_user));
            System.out.println("cash"+cash+"   "+getBalance(u.account_user));
            if(getBalance(u.account_user)>=cash){


            Accounts a= withdraw(getacc(u.account_user),cash,get_name(acc_nm));
            u.account_user = a;
            System.out.println(a);
            if (a!=null) {
                OnlineUsers b = auth_enter(acc_nm);
                Accounts bac = deposit(acc_nm,cash,u.account_user.name_acc());
                b.account_user = bac;
                add_to_file();
                return true;
            }

        }}return false;

    }
    public void save_change(String acc_nm){


    }
    public void add_to_file() throws IOException {
        super.add_to_file();
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\online_account.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(online_accounts);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        super.file_to_arraylist();
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\online_account.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        online_accounts = (ArrayList<OnlineUsers>) ois.readObject();
        ois.close();
        fis.close();
    }

    public boolean send_opt(Accounts ac){
        this.temp_pass = passWord_forgot(ac);
        System.out.println(temp_pass);
        return true;
    }
    public boolean enter_otp(String otp){
        if (this.temp_pass.equals(otp)) {
            return true;
        }
    return false;
    }
    public boolean change_without_online(Accounts ac,String pass){
        for (OnlineUsers ou:online_accounts) {
            if (ou.account_user.toString().equalsIgnoreCase(ac.toString())) {
                ou.passcode = pass;
                return true;
            }
        }
        return false;
    }
    public OnlineUsers(){

    }
    public OnlineUsers(Accounts a){
        this.account_user = a;
        this.passcode = passWord(a);
    }

    @Override
    public String toString() {
        return "OnlineUsers" +
                "account_user = " + account_user.name_acc();
    }
}
