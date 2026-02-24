// Ian Orego - 225259
public class HospitalMain {
    public static void main(String[] args) {
        Patient patient = new Patient("Ian Patient", 28, "Male", "P101", "Malaria");
        Doctor doctor = new Doctor("Dr. Kariuki", 42, "Male", "D201", "General Medicine");
        Nurse nurse = new Nurse("Nurse Mercy", 33, "Female", "N301", "Outpatient");

        patient.displayInfo();
        doctor.displayInfo();
        nurse.displayInfo();

        doctor.performDuties();
        nurse.performDuties();

        Appointment appointment = new Appointment("A001", "26 Feb 2026", patient, doctor);
        doctor.scheduleAppointment(appointment);
        appointment.displayDetails();

        Billing bill = new Billing("B001", patient, 1500.00);
        bill.generateBill();
        patient.makePayment(1500.00);
        bill.markAsPaid();
        bill.generateBill();
    }
}
