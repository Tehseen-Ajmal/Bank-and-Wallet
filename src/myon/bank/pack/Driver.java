package myon.bank.pack;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.io.Console;

public class Driver {
    public static Admins global_admin = new Admins();
    public static Person global_person = new Person();
    public static Customer global_customer = new Customer();
    public static Accounts global_account = new Accounts();
    public static OnlineUsers global_online = new OnlineUsers();
    public static Complains global_complain = new Complains();
    public static String capital(String s){
        String temp="";
        if (!s.isEmpty()) {
            String[] s1 = s.split( " ");
            for (String s2:s1) {
                temp+=(s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase());
                temp+=" ";
            }return temp;
        }return s;
    }

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
//        global_person.file_to_arraylist();
//        global_admin.file_to_arraylist();
//        global_customer.file_to_arraylist();
//        global_account.file_to_arraylist();
//        global_online.file_to_arraylist();
//        global_complain.file_to_arraylist();
        global_admin.add(new Admins("t","a"));
        global_admin.add_to_file();

        boolean main_check = true;
        int user_choice = 0;
        boolean admin_enter;
        int admin_outer = 0;
        int admin_inner = 0;
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
//        System.out.println("══════════════════════════════════════════════".repeat(7));
        System.out.println("     ".repeat(11)+"Welcome to Our Bank Management System");
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
//        System.out.println("══════════════════════════════════════════════".repeat(7));
        System.out.println("->We are committed to providing you with secure and efficient banking services.");
        System.out.println("->Please choose an option from the menu below:\n");
        Scanner sc = new Scanner(System.in);
        while (main_check) {

            System.out.println("―".repeat(41)+"\033[34m USER OPTIONS \033[0m"+"―".repeat(41));

            System.out.print("""
                    1.Admin login
                    2.User login
                    3.exit
                    Enter 1,2,3 : """);
            user_choice = sc.nextInt();
            if (user_choice == 1) {
                System.out.println("―".repeat(42)+"\033[34m ADMIN LOGIN \033[0m"+"―".repeat(42));
                sc.nextLine();
                System.out.print("\nEnter Your Admin Login ID:");
                String login = sc.nextLine();
                System.out.print("Enter Your Password:");
                String pass = sc.nextLine();
//                System.out.print("Enter Your Password: ");
//                Console console = System.console();
//                char[] passwordArray = console.readPassword(); // Hides the input while typing

//                String pass = new String(passwordArray);
                if (global_admin.validate_admin(login, pass)) {
                    System.out.println("\nWelcome Mr. " + capital(login));
                    admin_enter = true;
                    while (admin_enter) {
                        System.out.println("―".repeat(38)+"\033[34m ADMIN MANAGING OPTIONS \033[0m"+"―".repeat(35));

                        System.out.print("""
                                1.Manage Persons
                                2.Manage Customers
                                3.Manage Accounts
                                4.Add Admin
                                5.Remove Admin
                                6.Customer Complaints
                                7.User Options (Back)
                                Enter (1,2,3,4,5): """);
                        admin_outer = sc.nextInt();
                        if (admin_outer == 1) {


                            while (true) {
                                System.out.println("―".repeat(43)+"\033[34m PERSON \033[0m"+"―".repeat(43));
                                System.out.print("""
                                        \n1.Add Person
                                        2.Search Persons
                                        3.Remove Person
                                        4.Show all Persons
                                        5.Edit Details
                                        6.exit
                                        Enter (1,2,3,4,5,6): """);
                                admin_inner = sc.nextInt();
                                if (admin_inner == 1) {
                                    sc.nextLine();
                                    System.out.print("\nEnter name of Person to Add: ");
                                    String name = sc.nextLine();
                                    System.out.print("Enter Father's name: ");
                                    String father = sc.nextLine();
                                    int check_cnic = 1;
                                    String cnic = null;
                                    while (check_cnic == 1) {
                                        System.out.print("Enter CNIC of person: ");
                                        cnic = sc.next();
                                        if (global_person.validate_CNIC(cnic)) {
                                            cnic = global_person.break_cnic(cnic);
                                            if (cnic.equals("not_int")) {
                                                System.out.println(">>>CNIC is not in correct form (xxxxx-xxxxxxx-x) or 13 digits");
                                            } else {
                                                check_cnic = 0;
                                            }
                                        }
                                    }
                                    System.out.print("Enter Age of Person: ");
                                    String age = sc.next();
                                    System.out.print("Enter Email of Person: ");
                                    String email = sc.next();

                                    Person p = new Person(name, father, cnic, age, email);
                                    global_person.add_person(p);
//                                global_person.add_to_file();
                                } else if (admin_inner == 2) {
                                    System.out.print("\nEnter CNIC of Person to search: ");
                                    String cnic = sc.next();
                                    System.out.println(global_person.search_person(cnic));
                                } else if (admin_inner == 3) {
                                    System.out.print("\nEnter Person ID of Person which you want to remove: ");
                                    String id = sc.next();
                                    System.out.println(global_person.remove_person(id));
                                } else if (admin_inner == 4) {
                                    System.out.println(global_person.show_all_persons());
                                } else if (admin_inner == 5) {
                                    break;
                                }
                            }
                        } else if (admin_outer == 2) {
                            System.out.println("―".repeat(42)+"\033[34m CUSTOMERS \033[0m"+"―".repeat(42));

                            while (true) {
                                System.out.print("""
                                        \n1.Add Customer
                                        2.Search Customer
                                        3.Remove Customer
                                        4.Show all Customer
                                        5.exit
                                        Enter 1,2,3,4: """);
                                admin_inner = sc.nextInt();
                                if (admin_inner == 1) {
                                    sc.nextLine();
                                    System.out.println("\nEnter the Person ID of the Person: ");
                                    String id = sc.nextLine();
                                    Person p = global_person.return_person(id);
                                    if (p != null) {
                                        Customer cus = new Customer(p);
                                        Email_Send send = new Email_Send();
                                        int i = 1;
                                        while (i != 0) {
                                            boolean stat = send.otp_send(global_person.getEMAIL(p), global_person.getNAME(p));
                                            if (stat) {
                                                System.out.println("Enter 6 digits code sent to your Email: " + send.acc_hash(global_person.getEMAIL(p)));
                                                String code = sc.next();
                                                if (code.equalsIgnoreCase("again")) {
                                                    i++;
                                                    continue;
                                                }

                                                boolean ver = send.verify(code);
                                                if (ver) {
                                                    global_customer.add_customer(cus);
//                                                global_customer.add_to_file();
                                                    break;
                                                } else if (!ver) {
                                                    System.out.println(">>>Email is not verified please try again later.");
                                                    break;
                                                }
                                            }
                                            i--;
                                        }

                                    } else {
                                        System.out.println("\n-->Person doesn't exist with the ID: " + id);
                                    }
                                } else if (admin_inner == 2) {
                                    System.out.print("\nEnter CNIC of Customer to search: ");
                                    String cnic = sc.next();
                                    global_customer.search_Customers(cnic);
                                } else if (admin_inner == 3) {
                                    System.out.print("\nEnter Customer ID of customer which you want to remove: ");
                                    String id = sc.next();
                                    global_customer.remove_customer(id);
                                } else if (admin_inner == 4) {
                                    global_customer.show_all_customer();
                                } else if (admin_inner == 5) {
                                    break;
                                }
                            }
                        } else if (admin_outer == 3) {
                            System.out.println("―".repeat(39)+"\033[34m ACCOUNTS MANAGEMENT \033[0m"+"―".repeat(39));

                            while (true) {
                                System.out.print("""
                                        \n1.Add Account
                                        2.Remove Account
                                        3.Deposit Money
                                        4.Withdraw Money
                                        5.Show all Accounts
                                        6.Search Account
                                        7.Account Statement
                                        8.exit
                                        Enter 1,2,3,4,5: """);
                                admin_inner = sc.nextInt();
                                if (admin_inner == 1) {
                                    sc.nextLine();
                                    System.out.print("\nEnter the Customer ID to open an Account: ");
                                    String id = sc.nextLine();
                                    Customer c = global_customer.getCustom(id);
                                    if (c != null) {
                                        Accounts acc = new Accounts(c);
                                        global_account.add_account(acc);
//                                        global_account.add_to_file();
                                        System.out.print("Please Enter Initial amount you want to deposit: ");
                                        Float cash = sc.nextFloat();
                                        global_account.deposit(acc.getacc(), cash, "Account Opening");
                                    } else {
                                        System.out.print("Customer doesn't exist with the ID: " + id);
                                    }

                                } else if (admin_inner == 2) {
                                    sc.nextLine();
                                    System.out.println("\nEnter Account Of Customer to remove an Account:");
                                    String ac = sc.nextLine();
                                    if (global_account.remove_single_account(ac)) {
                                        System.out.println(">>>Account Successfully Removed");
                                    } else if (!global_account.remove_single_account(ac)) {
                                        System.out.println(">>>There is a problem in removing this Account");
                                        ;
                                    }
                                } else if (admin_inner == 3) {
                                    System.out.print("\nEnter Account Number You Want to deposit: ");
                                    String acc = sc.next();
                                    System.out.println("Enter Amount to Deposit: ");
                                    global_account.deposit(acc, sc.nextFloat(), "Casual Deposit");

                                } else if (admin_inner == 4) {
                                    System.out.print("\nEnter Account Number You Want to Withdraw: ");
                                    String acc = sc.next();
                                    System.out.println("Enter Amount to Withdraw: ");
                                    global_account.withdraw(acc, sc.nextFloat(), "Casual Withdraw");
                                } else if (admin_inner == 5) {
                                    global_account.file_to_arraylist();
                                    global_account.show_accounts();
//                                    global_customer.show_accounts();
                                } else if (admin_inner == 6) {
                                    System.out.print("\nPlease Enter Account Number you want to search:");
                                    String ac = sc.next();
                                    global_account.search(ac);
                                } else if (admin_inner == 7) {
                                    global_account.file_to_arraylist();
                                    System.out.print("\nEnter Account Number for Statement: ");
                                    String id = sc.next();
                                    global_account.statement(id);
                                } else if (admin_inner == 8) {
                                    break;
                                }
                            }

                        } else if (admin_outer == 4) {
                            System.out.println("―".repeat(42)+"\033[34m ADMIN CONTROL \033[0m"+"―".repeat(42));

                            sc.nextLine();
                            System.out.print("\nEnter ADMIN/login ID name you want to add: ");
                            String ID = sc.nextLine();
                            System.out.print("Choose a Strong PassWord: ");
                            String passw = sc.nextLine();
                            Admins a = new Admins(ID, passw);
                            global_admin.add(a);
                        } else if (admin_outer == 5) {
                            System.out.println("―".repeat(42)+"\033[34m ADMIN REMOVE \033[0m"+"―".repeat(42));
                            sc.nextLine();
                            System.out.print("\nEnter ADMIN/login ID name you want to remove: ");
                            String ID = sc.nextLine();
                            System.out.print("Do you really want to remove (y/n): " + ID);
                            String ch = sc.nextLine();
                            if (ch.equalsIgnoreCase("y")) {
                                global_admin.remove(ID);
                            }
                        } else if (admin_outer == 6) {
                            sc.nextLine();

                            while (true){
                                System.out.println("―".repeat(41)+"\033[34m COMPLAINTS \033[0m"+"―".repeat(41));
                                System.out.println("""
                                    1.Read Complaints
                                    2.Answer Complaint
                                    3.Exit
                                    Enter (1,2,3): """);
                                int com = sc.nextInt();
                                if (com==1) {
                                    global_complain.file_to_arraylist();
                                    global_complain.read_complain();
                                } else if (com==2) {
                                    global_complain.file_to_arraylist();
                                    System.out.println("Enter Complaints number to resolve: ");
                                    int num = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Answer to Complaints\n-->");
                                    String answer = sc.nextLine();
                                    boolean solve = global_complain.solve_complain(num,answer);
                                    if (solve){
                                        System.out.println("-->Your Answer has been sent");
                                        global_complain.add_to_file();
                                    }
                                    else {
                                        System.out.println("-->Something Went wrong");
                                    }
                                }else if (com==3) {
                                    break;
                                }
                            }
                        }else if (admin_outer == 7) {
                            break;
                        }
                    }
                }
            } else if (user_choice == 2) {
                int outer_online = 1;
                int inner_choice = 0;
                System.out.println("―".repeat(40)+"\033[34m INTERNET BANKING \033[0m"+"―".repeat(40));

                while (outer_online==1)
                {
                System.out.print("""
                        \n1.Login
                        2.Register
                        3.Forget PassWord
                        Enter (1,2,3):""");
                int outer_choice = sc.nextInt();
                    if (outer_choice == 1) {
                        System.out.println("―".repeat(42)+"\033[34m USER LOGIN \033[0m"+"―".repeat(42));

                    int online_check =0;
                    sc.nextLine();
                    System.out.print("\nEnter your Account Number:");
                    String ac_n = sc.nextLine();
                    System.out.print("Enter Your PassWord:");
                    String password = sc.nextLine();
                    boolean auth = global_online.authenticate(ac_n,password);

                    if (auth) {
                        OnlineUsers auth_enter = global_online.auth_enter(ac_n,password);
                        online_check=1;
                        System.out.println("―".repeat(39)+"\033[34m USER AUTHENTICATED \033[0m"+"―".repeat(39));

                        while (online_check==1){
                            System.out.print("""
                            \n1.Send Money
                            2.Account Statement
                            3.Change passWord
                            4.Customer Support
                            5.Exit...
                            Enter (1,2,3,4,5):""");
                            inner_choice = sc.nextInt();
                            if (inner_choice == 1) {
                                sc.nextLine();
                                System.out.print("\nEnter Account number you want to send money: ");
                                String rec_acc = sc.nextLine();
                                System.out.print("Enter Cash you want to send: ");
                                String cash = sc.nextLine();
                                System.out.print("Enter Description of sending Cash (reason): ");
                                String reason = sc.nextLine();

                                boolean stat = global_online.send(auth_enter,rec_acc, Float.valueOf(cash),reason);
                                if (stat) {
                                    System.out.println("Money sent Successfully to :"+rec_acc);
                                    System.out.println("Amount :"+cash);
                                }
                            } else if (inner_choice==2) {
                                global_account.statement(ac_n);
                            }else if (inner_choice==3) {
                                sc.nextLine();
                                System.out.print("Enter New PassWord to change");
                                System.out.print("---> ");
                                String pass_new = sc.nextLine();
                                boolean stat = global_online.change_pass(auth_enter,pass_new);
                                if (stat) {
                                    System.out.println("PassWord changed Successfully...");
                                }
                            }else if (inner_choice==4) {
                                while (true){
                                    System.out.println("―".repeat(39)+"\033[34m CUSTOMER SUPPORT \033[0m"+"―".repeat(39));
                                    System.out.print("""
                                            1.Complain
                                            2.Check Complaint Status
                                            Enter (1,2): """);
                                    int comp = sc.nextInt();
                                    if (comp ==1) {
                                        sc.nextLine();
                                        System.out.print("Enter your Query\n-->");
                                        String que = sc.nextLine();
                                        Complains c = new Complains(auth_enter,que);
                                        global_complain.complain_from_costomer(c);
                                    } else if (comp==2) {
                                        global_complain.check_status(auth_enter);
                                    }else if (comp==3) {
                                        break;
                                    }
                                }
                            }else if (inner_choice==5) {
                                break;
                            }

                        }
                    }else {
                        System.out.println("-->Wrong Credentials or not registered....");
                    }

                } else if (outer_choice == 2) {
                        System.out.println("―".repeat(38)+"\033[34m USER REGISTRATION \033[0m"+"―".repeat(38));

                        System.out.println("\nEnter account number to register: ");
                        String ac = sc.next();
                        if (!(global_online.has_online(ac))) {
                            Accounts acc = global_account.get_acc(ac);
                            OnlineUsers online = new OnlineUsers(acc);
                            global_online.add_online_user(online);
                        }else {
                            System.out.println("-->You already have an online account, if you forgot password go to 3.");
                        }

//                        outer_choice=1;
                    }else if (outer_choice == 3) {
                        System.out.println("―".repeat(39)+"\033[34m PASSWORD RESET \033[0m"+"―".repeat(39));
                        sc.nextLine();
                        System.out.print("Enter Your Account number:\n-->");
                        String accc= sc.nextLine();
                        if (global_online.has_online(accc)) {
                            OnlineUsers ou = new OnlineUsers();
                            Email_Send nes = new Email_Send();
                            Accounts ac =global_account.get_acc(accc);
                            int i = 1;
                            while (i != 0) {
                                boolean stat = ou.send_opt(ac);
                                if (stat) {
                                    System.out.println("Enter One-Time-PassWord sent to your Email: " + nes.acc_hash(ac.email_acc()));
                                    String code = sc.nextLine();
                                    if (code.equalsIgnoreCase("again")) {
                                        i++;
                                    }else if (ou.enter_otp(code)) {
                                        System.out.print("Enter your New PassWord:");
                                        ou.change_without_online(ac,sc.nextLine());
                                        System.out.println("PassWord has been changed...");
                                        break;
                                    }

                                }

                                }

                            }

                        }
                    else if (outer_choice == 4) {
                        break;
                    }
                }

            }else if (user_choice == 3) {
                break;
            }
        }
        global_person.add_to_file();
        global_account.add_to_file();
        global_customer.add_to_file();
        global_admin.add_to_file();
        global_online.add_to_file();
        global_complain.add_to_file();
        System.out.println("-Safely Exit-");
    }


}
