// Ian Orego - 225259
public class Doctor extends HospitalStaff implements Schedulable {
    private String specialization;

    public Doctor(String name, int age, String gender, String staffID, String specialization) {
        super(name, age, gender, staffID);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }

    @Override
    public void performDuties() {
        System.out.println("Doctor duty: Consultation and treatment.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor: " + getName() + " | Staff ID: " + getStaffID() + " | Specialization: " + specialization);
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {
        System.out.println("Appointment scheduled for: " + appointment.getDate());
    }
}
