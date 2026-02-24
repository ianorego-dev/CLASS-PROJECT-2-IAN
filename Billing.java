// Ian Orego - 225259
public class Billing {
    private String billID;
    private Patient patient;
    private double amount;
    private boolean isPaid;

    public Billing(String billID, Patient patient, double amount) {
        this.billID = billID;
        this.patient = patient;
        this.amount = amount;
        this.isPaid = false;
    }

    public String getBillID() { return billID; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return isPaid; }

    public void generateBill() {
        System.out.println("Bill ID: " + billID);
        System.out.println("Patient: " + patient.getName() + " (" + patient.getPatientID() + ")");
        System.out.println("Amount Due: KES " + amount);
        System.out.println("Status: " + (isPaid ? "PAID" : "UNPAID"));
    }

    public void markAsPaid() {
        this.isPaid = true;
        System.out.println("Bill " + billID + " marked as PAID.");
    }
}
