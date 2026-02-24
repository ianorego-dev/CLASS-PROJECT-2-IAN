// Ian Orego - 225259
public class Nurse extends HospitalStaff {
    private String ward;

    public Nurse(String name, int age, String gender, String staffID, String ward) {
        super(name, age, gender, staffID);
        this.ward = ward;
    }

    public String getWard() { return ward; }

    @Override
    public void performDuties() {
        System.out.println("Nurse duty: Patient care in " + ward + " ward.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Nurse: " + getName() + " | Staff ID: " + getStaffID() + " | Ward: " + ward);
    }
}
