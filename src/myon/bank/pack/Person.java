package myon.bank.pack;


import User_Online.Const;

import java.io.*;
import java.util.ArrayList;

public class Person implements Serializable {
    private static final Customer c = new Customer();
    private static ArrayList<Person> persons = new ArrayList<>();
    private static Integer person_id = 141100001 + persons.size();
    private String NAME = "";
    private String FATHER = "";
    private String CNIC = "";
    //    ivateed String DATE_O_B = "";
    private String EMAIL = "";
    private String AGE = "";
    private String ID = "";

    Person() {

    }

    Person(String name, String father, String cnic, String age, String email) {
        this.NAME = name;
        this.FATHER = father;
        this.CNIC = cnic;
        this.AGE = age;
        this.EMAIL = email;
        this.ID = String.valueOf(person_id);
    }

    public void add_person(Person p) {
        persons.add(p);

        System.out.println("Your Person ID is: " + person_id);
        person_id++;
    }

    public String remove_person(String id) {
        String tmp = "\">>>Person Not Found---Please check Person ID again.\"";
        for (Person p : persons) {
            if (p.ID.equals(id)) {
                c.validate_delete(p.CNIC);
                persons.remove(p);
                tmp = ">>>Person Removed Successfully...";
                return tmp;
            }
        }
        return tmp;
    }

    public String search_person(String cnic) {
        for (Person p : persons) {
            if (p.CNIC.equals(cnic)) {
                return "Person Found:\n" + p;
            }
        }
        return ">>>Person Not Found---Please check Person CNIC again.";
    }

    public String show_all_persons() {
        String temp = "";
        String t="";
        temp = ("""
                \033[1m\033[42m\033[30m |           NAME            |              CNIC              |    AGE    |                EMAIL               |        PERSON-ID      |\033[0m
                \033[32m----------------------------------------------------------------------------------------------------------------------------------------\n""");
        for (Person p : persons) {
//                temp+=p.toString()+"\n";+"|"
            String p_n = per_print(p.NAME, 25);
            String p_c = per_print(p.CNIC, 30);
            String p_a = per_print(p.AGE, 9);
            String p_e = per_print(p.EMAIL, 34);
            String p_i = per_print(p.ID, 21);
            temp += String.format(" | %-25s | %-30s | %-9s | %-34s | %-21s |\n----------------------------------------------------------------------------------------------------------------------------------------\n", p_n, p_c, p_a, p_e, p_i);
t+=p.NAME+"1@101"+p.FATHER+"1@101"+p.CNIC+"1@101"+p.AGE+"1@101"+p.EMAIL+"1@101"+p.ID;
t+="2@2@0196";
        }
//        return temp + "\033[0m";
        return t;
    }

    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\Person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(persons);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\Person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        persons = (ArrayList<Person>) ois.readObject();
        ois.close();
        fis.close();
        person_id = Integer.valueOf(persons.get(persons.size() - 1).ID) + 1;
    }

    public Person return_person(String id) {
        for (Person p : persons) {
            if (p.ID.equals(id)) {
                return p;
            }
        }
        return null;
    }

    public String break_cnic(String cn) {
        if (!(cn.charAt(5) == '-' && cn.charAt(13) == '-')) {
            System.out.println(cn.charAt(5));
            System.out.println(cn.charAt(12));
            System.out.println(cn.substring(0, 5));
            System.out.println(cn.substring(5, 12));
            try {
                // just to check integers error detection
                System.out.println("OK");
                Long cnic = Long.parseLong(cn);
                System.out.println("ok2");
                System.out.println(cnic);
                cn = cn.substring(0, 5) + "-" + cn.substring(5, 12) + "-" + cn.charAt(12);
            } catch (Exception e) {
                return "not_int";
            }
        }
        return cn;
    }

    public boolean validate_CNIC(String cnic) {
        cnic = cnic.replace("-", "");
        if (cnic.length() == 13) {
            return true;

        }
        return false;
    }

    public String getCNIC(Person p) {
        return p.CNIC;
    }

    public String getEMAIL(Person p) {
        return p.EMAIL;
    }

    public String getNAME(Person p) {
        return p.NAME;
    }

    public String getAGE(Person p) {
        return p.AGE;
    }

    public String getID(Person p) {
        return p.ID;
    }

    private String per_print(String text, int total) {
        int spaces = (total - text.length()) / 2;
        return String.format("%" + (spaces + text.length()) + "s", text);
    }

    @Override
    public String toString() {
        return "NAME='" + NAME + '\'' +
                ", FATHER='" + FATHER + '\'' +
                ", CNIC='" + CNIC + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", AGE='" + AGE + '\'' +
                ", ID='" + ID + '\'';
    }
}
