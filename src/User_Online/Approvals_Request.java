package User_Online;

import myon.bank.pack.Accounts;

import java.io.*;
import java.util.ArrayList;

public class Approvals_Request implements Serializable{
    public static ArrayList<Approvals_Request> appr = new ArrayList<>();
    String ac_num_from;
    String ac_num_to;
    public static int appr_number = 1;
    int count;

    String name_to;
    String name_from;

    float cash;
    int req_app;

    public boolean add_appr(Approvals_Request acc) {
        appr.add(acc);
        return true;
    }
    public boolean add_req(Approvals_Request ar){
        appr.add(ar);
        return true;
    }
    public boolean remove(Approvals_Request ar){
        for (Approvals_Request apr:appr) {
            if (apr.equals(ar)) {
                appr.remove(ar);
                return true;
            }
        }return false;

    }
    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\Bank_Management\\src\\User_Online\\approvals.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appr);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\Bank_Management\\src\\User_Online\\approvals.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        appr = (ArrayList<Approvals_Request>) ois.readObject();
        try{
        appr_number=appr.get(appr.size()).count;}
        catch (Exception e){
            System.out.println(e);
        }
        ois.close();
        fis.close();
    }
    Approvals_Request(){

    }
    Approvals_Request(String ac,String ac_to,String name_to,String name_from,float cash_in,int req){
        this.ac_num_from = ac;
        this.ac_num_to = ac_to;
        this.name_to = name_to;
        this.name_from = name_from;
        this.req_app = req;
        this.cash = cash_in;
        this.count=appr_number++;
    }

}
