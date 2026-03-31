import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalMain {

    private static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    private static int readIntRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                if (value < min || value > max) {
                    System.out.println("Enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid whole number.");
                sc.nextLine();
            }
        }
    }

    private static double readDoubleMin(Scanner sc, String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = sc.nextDouble();
                sc.nextLine();
                if (value < min) {
                    System.out.println("Enter a value of " + min + " or more.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number (e.g., 1500 or 1500.50).");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HospitalStore store = new HospitalStore();

        try {
            store.loadPatients("patients.csv");
            store.loadDoctors("doctors.csv");
            store.loadAppointments("appointments.csv");
        } catch (Exception e) {
            System.out.println("Could not load data: " + e.getMessage());
        }

        try {
            System.out.println("=== Hospital Management System ===");

            String pName = readNonEmpty(sc, "Patient name: ");
            int pAge = readIntRange(sc, "Patient age (0-120): ", 0, 120);
            String pGender = readNonEmpty(sc, "Patient gender: ");
            String pId = readNonEmpty(sc, "Patient ID: ");
            String illness = readNonEmpty(sc, "Illness: ");
            Patient patient = new Patient(pName, pAge, pGender, pId, illness);
            store.addPatient(patient);

            String dName = readNonEmpty(sc, "\nDoctor name: ");
            int dAge = readIntRange(sc, "Doctor age (18-100): ", 18, 100);
            String dGender = readNonEmpty(sc, "Doctor gender: ");
            String dStaffId = readNonEmpty(sc, "Doctor staff ID: ");
            String spec = readNonEmpty(sc, "Specialization: ");
            Doctor doctor = new Doctor(dName, dAge, dGender, dStaffId, spec);
            store.addDoctor(doctor);

            String nName = readNonEmpty(sc, "\nNurse name: ");
            int nAge = readIntRange(sc, "Nurse age (18-100): ", 18, 100);
            String nGender = readNonEmpty(sc, "Nurse gender: ");
            String nStaffId = readNonEmpty(sc, "Nurse staff ID: ");
            String ward = readNonEmpty(sc, "Ward: ");
            Nurse nurse = new Nurse(nName, nAge, nGender, nStaffId, ward);

            System.out.println("\n--- Details ---");
            patient.displayInfo();
            doctor.displayInfo();
            nurse.displayInfo();
            doctor.performDuties();
            nurse.performDuties();

            String apptId = readNonEmpty(sc, "\nAppointment ID: ");
            String date = readNonEmpty(sc, "Appointment date (e.g., 26 Feb 2026): ");
            Appointment appt = new Appointment(apptId, date, patient, doctor);
            store.addAppointment(appt);
            doctor.scheduleAppointment(appt);
            appt.displayDetails();

            String billId = readNonEmpty(sc, "\nBill ID: ");
            double amountDue = readDoubleMin(sc, "Amount due: ", 0.0);
            Billing bill = new Billing(billId, patient, amountDue);

            System.out.println("\n--- Billing ---");
            bill.generateBill();

            double paidAmount = readDoubleMin(sc, "Payment amount: ", 0.0);
            patient.makePayment(paidAmount);

            if (paidAmount >= amountDue) {
                bill.markAsPaid();
            } else {
                System.out.println("Partial payment. Remaining: KES " + (amountDue - paidAmount));
            }

            bill.generateBill();

            store.savePatients("patients.csv");
            store.saveDoctors("doctors.csv");
            store.saveAppointments("appointments.csv");
            System.out.println("Data saved.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Program ended.");
        }
    }
}
