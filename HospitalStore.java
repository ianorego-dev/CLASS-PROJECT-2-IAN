import java.io.*;
import java.util.*;

public class HospitalStore {

    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    private Map<String, Patient> patientById = new HashMap<>();
    private Map<String, Doctor> doctorById = new HashMap<>();

    private Set<String> patientIds = new HashSet<>();
    private Set<String> doctorIds = new HashSet<>();

    public void addPatient(Patient p) {
        String id = p.getPatientID();
        if (patientIds.contains(id)) return;
        patients.add(p);
        patientById.put(id, p);
        patientIds.add(id);
    }

    public void addDoctor(Doctor d) {
        String id = d.getStaffID();
        if (doctorIds.contains(id)) return;
        doctors.add(d);
        doctorById.put(id, d);
        doctorIds.add(id);
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public Patient findPatient(String patientId) {
        return patientById.get(patientId);
    }

    public Doctor findDoctor(String staffId) {
        return doctorById.get(staffId);
    }

    public ArrayList<Patient> getPatients() { return patients; }
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Appointment> getAppointments() { return appointments; }

    public void savePatients(String file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Patient p : patients) {
                bw.write(p.getPatientID() + "," + p.getName() + "," + p.getAge() + "," + p.getGender() + "," + p.getIllness());
                bw.newLine();
            }
        }
    }

    public void saveDoctors(String file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Doctor d : doctors) {
                bw.write(d.getStaffID() + "," + d.getName() + "," + d.getAge() + "," + d.getGender() + "," + d.getSpecialization());
                bw.newLine();
            }
        }
    }

    public void saveAppointments(String file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Appointment a : appointments) {
                bw.write(a.getAppointmentID() + "," + a.getDate() + "," +
                        a.getPatient().getPatientID() + "," + a.getDoctor().getStaffID());
                bw.newLine();
            }
        }
    }

    public void loadPatients(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",", -1);
                if (x.length < 5) continue;

                String id = x[0];
                String name = x[1];
                int age = Integer.parseInt(x[2]);
                String gender = x[3];
                String illness = x[4];

                Patient p = new Patient(name, age, gender, id, illness);
                addPatient(p);
            }
        }
    }

    public void loadDoctors(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",", -1);
                if (x.length < 5) continue;

                String staffId = x[0];
                String name = x[1];
                int age = Integer.parseInt(x[2]);
                String gender = x[3];
                String spec = x[4];

                Doctor d = new Doctor(name, age, gender, staffId, spec);
                addDoctor(d);
            }
        }
    }

    public void loadAppointments(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",", -1);
                if (x.length < 4) continue;

                String apptId = x[0];
                String date = x[1];
                String patientId = x[2];
                String doctorId = x[3];

                Patient p = findPatient(patientId);
                Doctor d = findDoctor(doctorId);

                if (p != null && d != null) {
                    Appointment a = new Appointment(apptId, date, p, d);
                    addAppointment(a);
                }
            }
        }
    }
}
