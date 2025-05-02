package myon.bank.pack;

import User_Online.Const;

import java.io.*;
import java.util.ArrayList;

public class Admins implements Serializable {
    private static ArrayList<Admins> admins = new ArrayList<>();
    private String name = "";
    private String password = "";

    Admins(String name, String pass) {
        this.name = name;
        this.password = pass;
    }

    Admins() {
    }

    public boolean validate_admin(String name, String pass) {
        for (Admins i : admins) {
            if (i.name.equalsIgnoreCase(name) && i.password.equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public void add(Admins a) {
        admins.add(a);
    }

    public void remove(String id) {
        for (Admins a : admins) {
            if (a.name.equalsIgnoreCase(id) && !a.name.equalsIgnoreCase("tehs")) {
                admins.remove(a);
                System.out.println(a.name + " removed successfully.");
            } else if (a.name.equalsIgnoreCase("tehs")) {
                System.out.println(">>>You cannot remove the OWNER.");
            }
        }
    }

    public void add_to_file() throws IOException {
        FileOutputStream fos = new FileOutputStream(Const.basePath + "src\\myon\\bank\\pack\\Admin.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(admins);
        oos.close();
        fos.close();
    }

    public void file_to_arraylist() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Const.basePath + "src\\myon\\bank\\pack\\Admin.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        admins = (ArrayList<Admins>) ois.readObject();
        ois.close();
        fis.close();
    }

    @Override
    public String toString() {
        return "Admins{" +
                "name='" + name + '\'' +
                ", password='" + "*".repeat(password.length()) + '\'' +
                '}';
    }
}
