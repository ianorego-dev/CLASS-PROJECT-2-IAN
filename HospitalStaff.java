// Ian Orego - 225259
// OOP Activity 2: Hospital Management System
public abstract class HospitalStaff extends Person {
    private String staffID;

    public HospitalStaff(String name, int age, String gender, String staffID) {
        super(name, age, gender);
        this.staffID = staffID;
    }

    public String getStaffID() { return staffID; }

    public abstract void performDuties();
}
