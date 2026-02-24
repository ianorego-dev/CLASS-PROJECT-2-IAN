// Ian Orego - 225259
public class Appointment {
    private String appointmentID;
    private String date;
    private Patient patient;
    private Doctor doctor;

    public Appointment(String appointmentID, String date, Patient patient, Doctor doctor) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getAppointmentID() { return appointmentID; }
    public String getDate() { return date; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }

    public void displayDetails() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Date: " + date);
        System.out.println("Patient: " + patient.getName() + " (" + patient.getPatientID() + ")");
        System.out.println("Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
    }
}
