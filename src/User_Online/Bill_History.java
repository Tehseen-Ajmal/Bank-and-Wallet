package User_Online;

import myon.bank.pack.Accounts;

import java.io.*;
import java.util.ArrayList;

public class Bill_History implements Serializable {
    private static ArrayList<Bill_History> billHistoryList = new ArrayList<>();
    private String billMonth;
    private String referenceNo;
    private Double amount;
    public boolean isPaid;

    public Bill_History(String referenceNo, String billMonth, Double amount, boolean isPaid) {
        this.referenceNo = referenceNo;
        this.billMonth = billMonth;
        this.amount = amount;
        this.isPaid = isPaid;
    }
    public Bill_History(){

    }

    // Add a new bill to the list
    public void addBill(Bill_History bill) {
        billHistoryList.add(bill);
        System.out.println("Your Reference Number is: " + bill.referenceNo);
    }

    // Check if a bill is paid by reference number
    public boolean isBillPaid(String referenceNo) {
        for (Bill_History bill : billHistoryList) {
            if (bill.referenceNo.equals(referenceNo)) {
                return bill.isPaid;
            }
        }
        return false;
    }

    // Check if the bill is the same (same reference number, month, and amount)
    public boolean checkBill(String referenceNo, String billMonth, Double amount) {
        for (Bill_History bill : billHistoryList) {

            if (bill.referenceNo.equals(referenceNo) && bill.billMonth.equals(billMonth) && bill.amount.equals(amount)) {
                return true;
            }
        }
        return false;
    }

    // Show all bills
    public String showAllBills() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReferenceNo | BillMonth | Amount | PaidStatus\n");
        for (Bill_History bill : billHistoryList) {
            sb.append(String.format("%-12s | %-9s | %-6.2f | %-10s\n",
                    bill.referenceNo, bill.billMonth, bill.amount, bill.isPaid ? "Paid" : "Not Paid"));
        }
        return sb.toString();
    }

    // Save the bill history to a file
    public void addToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\Sada_Pay\\src\\User_Online\\bill.myon");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(billHistoryList);
        oos.close();
        fos.close();
    }

    // Load bill history from file
    public void fileToArrayList() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\Sada_Pay\\src\\User_Online\\bill.myon");
        ObjectInputStream ois = new ObjectInputStream(fis);
        billHistoryList = (ArrayList<Bill_History>) ois.readObject();
        ois.close();
        fis.close();
    }

    // Return the bill by reference number
    public Bill_History returnBill(String referenceNo) {
        for (Bill_History bill : billHistoryList) {
            if (bill.referenceNo.equals(referenceNo)) {
                return bill;
            }
        }
        return null;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return "ReferenceNo='" + referenceNo + '\'' +
                ", BillMonth='" + billMonth + '\'' +
                ", Amount=" + amount +
                ", PaidStatus=" + (isPaid ? "Paid" : "Not Paid");
    }
}
