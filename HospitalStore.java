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
        if (patientIds.contains(p.getPatientID())) return;
        patients.add(p);
        patientById.put(p.getPatientID(), p);
        patientIds.add(p.getPatientID());
    }

    public void addDoctor(Doctor d) {
        if (doctorIds.contains(d.getStaffID())) return;
        doctors.add(d);
        doctorById.put(d.getStaffID(), d);
        doctorIds.add(d.getStaffID());
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public Patient findPatient(String id) {
        return patientById.get(id);
    }

    public Doctor findDoctor(String id) {
        return doctorById.get(id);
    }

    public ArrayList<Patient> getPatients() { return patients; }
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Appointment> getAppointments() { return appointments; }

    public void savePatients(String file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Patient p : patients) {
            bw.write(p.getPatientID() + "," + p.getName() + "," + p.getAge() + "," + p.getGender() + "," + p.getIllness());
            bw.newLine();
        }
        bw.close();
    }

    public void saveDoctors(String file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Doctor d : doctors) {
            bw.write(d.getStaffID() + "," + d.getName() + "," + d.getAge() + "," + d.getGender() + "," + d.getSpecialization());
            bw.newLine();
        }
        bw.close();
    }

    public void saveAppointments(String file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Appointment a : appointments) {
            bw.write(a.getAppointmentID() + "," + a.getDate() + "," + a.getPatient().getPatientID() + "," + a.getDoctor().getStaffID());
            bw.newLine();
        }
        bw.close();
    }

    public void loadPatients(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] x = line.split(",");
            if (x.length < 5) continue;
            Patient p = new Patient(x[1], Integer.parseInt(x[2]), x[3], x[0], x[4]);
            addPatient(p);
        }
        br.close();
    }

    public void loadDoctors(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] x = line.split(",");
            if (x.length < 5) continue;
            Doctor d = new Doctor(x[1], Integer.parseInt(x[2]), x[3], x[0], x[4]);
            addDoctor(d);
        }
        br.close();
    }

    public void loadAppointments(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] x = line.split(",");
            if (x.length < 4) continue;
            Patient p = findPatient(x[2]);
            Doctor d = findDoctor(x[3]);
            if (p != null && d != null) {
                addAppointment(new Appointment(x[0], x[1], p, d));
            }
        }
        br.close();
    }
}
