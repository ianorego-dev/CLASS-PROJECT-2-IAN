// Ian Orego - 225259
public class Patient extends Person implements Payable {
    private String patientID;
    private String illness;

    public Patient(String name, int age, String gender, String patientID, String illness) {
        super(name, age, gender);
        this.patientID = patientID;
        this.illness = illness;
    }

    public String getPatientID() { return patientID; }
    public String getIllness() { return illness; }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient: " + getName() + " | ID: " + patientID + " | Illness: " + illness);
    }

    @Override
    public void makePayment(double amount) {
        System.out.println(getName() + " paid KES " + amount);
    }
}
