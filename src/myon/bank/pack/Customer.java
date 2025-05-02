package myon.bank.pack;

import User_Online.Const;

import java.io.*;
import java.util.ArrayList;

public class Customer implements Serializable {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Person global_per = new Person();
    private static Accounts global_acc = new Accounts();
    public String customer_id;
    public Person person;
    private Integer accoun_s = 1;
    private Accounts acc;

    Customer() {

    }

    Customer(Person p) {
        this.person = p;
        this.customer_id = "Myon102" + global_per.getCNIC(p) + "40";
        this.customer_id = this.customer_id.replace("-", "");
    }

    public void add_customer(Customer cus) {
        customers.add(cus);
        System.out.println("Your Customer ID is: " + cus.customer_id);
    }

    public void remove_customer(String id) {
        for (Customer c : customers) {
            if (c.customer_id.equalsIgnoreCase(id)) {
                customers.remove(c);
                String[] check = global_acc.hasAccount(c);
                if (check[0].equals("t")) {
                    System.out.println("Your Account has been removed. You have " + check[1] + " balance. You can get by showing us CNIC and Email sent to you.");
                }
                System.out.println(">>>Customer removed Successfully.");
                return;
            }
        }
        System.out.println(">>>No Customer found with the id: " + id);
    }

    public void search_Customers(String cnic) {
        for (Customer c : customers) {
            if (global_per.getCNIC(c.person).equals(cnic)) {
                System.out.println("Customer found with the same CNIC:");
                System.out.println(c);
                return;
            }
            System.out.println("No customer found with this CNIC ");
        }
    }

    public Customer getCustom(String id) {
        for (Customer c : customers) {
            if (c.customer_id.equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public void show_all_customer() {
//        int i=1;
//        for (Customer c: customers) {
//            System.out.println(i+". "+c);
//        }
        String temp = "";
        temp = ("""
                \033[1m\033[42m\033[30m |           NAME            |              CNIC              |    AGE    |                EMAIL               |           CUSTOMER-ID           |        PERSON-ID      |   ACCOUNTS   |   BALANCE   |\033[0m
                \033[32m------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n""");
        for (Customer c : customers) {
//                temp+=p.toString()+"\n";+"|"
            String cs_n = per_print(global_per.getNAME(c.person), 25);
            String cs_c = per_print(global_per.getCNIC(c.person), 30);
            String cs_a = per_print(global_per.getAGE(c.person), 9);
            String cs_e = per_print(global_per.getEMAIL(c.person), 34);
            String cs_cus_ID = per_print(c.customer_id, 31);
            String cs_i = per_print(global_per.getID(c.person), 21);
            String cs_acc = per_print(global_acc.count(c).split(":")[0], 12);
            String cs_cash = per_print(global_acc.count(c).split(":")[1], 11);
            temp += String.format(" | %-25s | %-30s | %-9s | %-34s | %-31s | %-21s | %-12s |%-11s |\n----------------" + "------------------------------------------------------------------------------------------------" + "--------------------------------------------------------------------------------------\n", cs_n, cs_c, cs_a, cs_e, cs_cus_ID, cs_i, cs_acc, cs_cash);

        }
        System.out.println(temp + "\033[0m");
    }

    public void show_accounts() {
//        int i=1;
//        for (Customer c: customers) {
//            System.out.println(i+". "+c);
//        }
        String temp = "";
        temp = ("""
                \033[1m\033[42m\033[30m |           NAME            |              CNIC              |                EMAIL               |           CUSTOMER-ID           |     ACCOUNT-NUMBERS    |   ACCOUNTS   |   BALANCE   |\033[0m
                \033[32m-----------------------------------------------------------------------------------------------------------------------------------------------------------------\n""");
        for (Customer c : customers) {
//                temp+=p.toString()+"\n";+"|"
            if (global_acc.hasacc(c)) {
                String cs_n = per_print(global_per.getNAME(c.person), 25);
                String cs_c = per_print(global_per.getCNIC(c.person), 30);
                String cs_e = per_print(global_per.getEMAIL(c.person), 34);
                String cs_cus_ID = per_print(c.customer_id, 31);
                String cs_acc_id = per_print(global_acc.getACCNumber(c), 21);
//                System.out.println(global_acc.count(c).split(":")[0]);
                String cs_acc = per_print(global_acc.count(c).split(":")[1], 12);
                String cs_cash = per_print(global_acc.count(c).split(":")[0], 11);
                temp += String.format(" | %-25s | %-30s | %-34s | %-31s | %-21s | %-12s |%-11s |\n-------------------------------" + "-----------------------------------------------------------------------------------------------" + "----------------------------------------------------\n", cs_n, cs_c, cs_e, cs_cus_ID, cs_acc_id, cs_acc, cs_cash);

            }
            System.out.println(temp + "\033[0m");
        }
    }

    private String per_print(String text, int total) {
        int spaces = (total - text.length()) / 2;
        return String.format("%" + (spaces + text.length()) + "s", text);
    }

    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\Customers.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(customers);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\Customers.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        customers = (ArrayList<Customer>) ois.readObject();
        ois.close();
        fis.close();
    }

    public void validate_delete(String id) {
        for (Customer c : customers) {
            if (id.equals(global_per.getCNIC(c.person))) {
                System.out.println(c);
                System.out.println("customer removing...");
                String[] check = global_acc.hasAccount(c);
                if (check[0].equals("t")) {
                    System.out.println("Your Account has been removed. You have " + check[1] + " balance. You can get by showing us CNIC and Email sent to you.");
                }
                customers.remove(c);
                return;
            }
        }

    }

    public String lastCNIC(Customer c) {
        return global_per.getCNIC(c.person).substring(6, 10);
    }

    public String email_request(Customer c) {
        return global_per.getEMAIL(c.person);
    }

    public String user(Customer c) {
        return global_per.getNAME(c.person);
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id='" + customer_id + '\'' + ", Person=" + person + '}';
    }
}
