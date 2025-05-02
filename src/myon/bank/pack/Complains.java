package myon.bank.pack;

import User_Online.Const;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Complains implements Serializable{
    public static ArrayList<Complains> complains = new ArrayList<>();
    public static ArrayList <Complains> solvedcomplains = new ArrayList<>();
    private String complain;
    private static OnlineUsers global_ou= new OnlineUsers();
    private OnlineUsers onlineUsers;
    private Date date = new Date();
    private String date_today;
    private String time_today;
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    private SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss a");
    public boolean complain_from_costomer(Complains c){
        complains.add(c);
        return true;
    }
    public boolean solve_complain(int i,String answer){
        i--;
        if ((complains.size()-1)>=(i)) {
            complains.get(i).complain+="\nAnswer to Query:"+answer;
            solvedcomplains.add(complains.get(i));
            complains.remove(complains.get(i));
            return true;
        }return false;
    }
    public boolean read_complain(){
        int k= 1;
        for (Complains c:complains) {
            System.out.println("\033[42m\033[30mFrom: "+c.onlineUsers.account_user.name_acc()+" Account:"+c.onlineUsers.account_user.getacc()+"\033[0m");
            System.out.println(k+"-->\033[32m"+c.complain+" Date: "+c.date_today+" Time: "+c.time_today+"\033[0m");
//            System.out.println(c.onlineUsers.account_user);
//            System.out.println("Complain:");
//            System.out.println(c.complain);
            k++;
        } return true;
    }
    public ArrayList<Complains> ret_solved_complain(OnlineUsers ou){
        ArrayList<Complains> sol= new ArrayList<>();
        for (Complains sc: solvedcomplains) {
            if (sc.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                sol.add(sc);
            }
        }
        return sol;
    }
    public String complain_read(Complains comp){
        return comp.complain;
    }
    public String complain_time(Complains comp){
        return comp.time_today;
    }
    public String complain_date(Complains comp){
        return comp.date_today;
    }
    public ArrayList<Complains> ret_unresol_complain(OnlineUsers ou){
        ArrayList<Complains> sol= new ArrayList<>();
        for (Complains sc: complains) {
            if (sc.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                sol.add(sc);
            }
        }
        return sol;
    }
    public void check_status(OnlineUsers ou){
        int k = 1;
        String temp = "You don't have complaints in que";
        System.out.println("\033[42m\033[30m-->Unsolved Complaints\033[0m");
        for (Complains comp: complains) {
            if (comp.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                System.out.println(k+"-->\033[32mComplain: "+comp.complain+" Date: "+comp.date_today+"Time: "+comp.time_today+"\033[0m");
                temp="";
                k++;
            }
        }System.out.println(temp);
        k=1;
        String solve="";
        System.out.println("\033[42m\033[30m-->Solved Complaints\033[0m");
        for (Complains sc: solvedcomplains) {
            if (sc.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                System.out.println(k+"-->\033[32mComplain: "+sc.complain+" Date: "+sc.date_today+"Time: "+sc.time_today+"\033[0m");
                k++;
            }
        }

    }
    public String check_status_ret(OnlineUsers ou){
        int k = 1;
        String temp = "--nothing";
        String complains_text = "";
        for (Complains comp: complains) {
            if (comp.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                System.out.println(comp.complain+" Date: "+comp.date_today+"Time: "+comp.time_today+"\033[0m");
                temp="";
                k++;
            }
        }System.out.println(temp);
        k=1;
        String solve="";
        System.out.println("\033[42m\033[30m-->Solved Complaints\033[0m");
        for (Complains sc: solvedcomplains) {
            if (sc.onlineUsers.toString().equalsIgnoreCase(ou.toString())) {
                System.out.println(k+"-->\033[32mComplain: "+sc.complain+" Date: "+sc.date_today+"Time: "+sc.time_today+"\033[0m");
                k++;
            }
        }
        return complains_text;

    }
    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\complaints.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(complains);
        oos.close();
        fos.close();
        FileOutputStream fos1 = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\solved_complaints.myon");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(solvedcomplains);
        oos1.close();
        fos1.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\complaints.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        complains = (ArrayList<Complains>) ois.readObject();
        ois.close();
        fis.close();
        FileInputStream fis1 = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\solved_complaints.myon");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        solvedcomplains = (ArrayList<Complains>) ois1.readObject();
        ois1.close();
        fis1.close();
    }

    public Complains(){

    }
    public Complains(OnlineUsers ou, String complain){
        Date d = new Date();
        String date_d = sdf.format(d);
        String time = stf.format(d);
        this.onlineUsers = ou;
        this.complain = complain;
        this.date_today = date_d;
        this.time_today = time;
    }

    @Override
    public String toString() {
        return "\033[32mcomplain='" + complain + '\''+
                ", date='" + date_today + '\'' +
                ", time='" + time_today + '\''+"\033[0m";
    }
}
