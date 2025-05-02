package myon.bank.pack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {
    private ArrayList<Transactions> transaction = new ArrayList<>();
    private String date;
    private String time;
    private String pos_neg;
    private Float withdraw;
    private Float deposit;
    private Float remain;
    private String reason;
    private String[] whole_tran;
    private Accounts global_acc = new Accounts();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    private SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss a");

    Transactions(Accounts a, Float cash, String p, String reason) {

    }

    //{{"10-12-2022","Opening balance","positive","0.0","positive","5000","negative","1000"},{"10-12-2022","atm aco","positive","0.0","positive","5000","positive","2000"},{"10-12-2022","Closing","positive","0.0","positive","5000","negative","1000"}};
    Transactions(Accounts acc, String change, String pos, String reason) {
        Date d = new Date();
        String date_d = sdf.format(d);
        String time = stf.format(d);
        if (pos.equals("p")) {
            this.whole_tran = new String[]{date_d + " " + time, reason, "negative", String.valueOf(Float.valueOf(change)), "positive", String.valueOf((float) 0), "negative", String.valueOf(global_acc.getBalance(acc))};
        } else {
            this.whole_tran = new String[]{date_d + " " + time, reason, "negative", String.valueOf((float) 0), "positive", String.valueOf(Float.valueOf(change)), "negative", String.valueOf(global_acc.getBalance(acc))};
        }

    }

    public void add_tran(Transactions tr) {
        this.transaction.add(tr);
    }
}
