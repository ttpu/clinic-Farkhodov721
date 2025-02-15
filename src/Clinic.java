// Filename: Clinic.java

import java.io.IOException;
import java.util.*;

public class Clinic {
    private Map<String, Person> patientMap = new TreeMap<>();
    private Map<Integer, Doctor> doctorMap = new TreeMap<>();

    public void addPatient(String first, String last, String ssn) {
        Person person = new Person(first, last, ssn);
        patientMap.put(ssn, person);
    }

    public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
        Doctor doctor = new Doctor(first, last, ssn, docID, specialization);
        this.doctorMap.put(docID, doctor);
        this.patientMap.put(ssn, doctor);
    }

    public Person getPatient(String ssn) throws NoSuchPatient {
        Person person = this.patientMap.get(ssn);
        if (person == null) { // Fixed incorrect null check
            throw new NoSuchPatient();
        }
        return person;
    }

    public Doctor getDoctor(int docID) throws NoSuchDoctor {
        Doctor doctor = doctorMap.get(docID);
        if (doctor == null) {
            throw new NoSuchDoctor();
        }
        return doctor;
    }

    public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
        Person patient = this.getPatient(ssn);
        Doctor doctor = this.getDoctor(docID);
        patient.setDoctor(doctor);
        doctor.addPatient(patient);
    }

    Collection<Doctor> idleDoctors() {
        List<Doctor> doctorList = new LinkedList<>();
        for (Doctor doctor : this.doctorMap.values()) {
            if (doctor.patientCount() == 0) {
                doctorList.add(doctor);
            }
        }
        Collections.sort(doctorList);
        return doctorList; // Fixed return value (was null)
    }

    Collection<Doctor> busyDoctors() {
        if (this.patientMap.isEmpty()) return Collections.emptyList(); // Prevent division by zero

        int patientCount = 0;
        for (Doctor doctor : this.doctorMap.values()) {
            patientCount += doctor.patientCount();
        }

        int avr = patientCount / this.doctorMap.size(); // Fixed division by number of doctors
        List<Doctor> doctorList = new LinkedList<>();

        for (Doctor doctor : this.doctorMap.values()) {
            if (doctor.patientCount() > avr) {
                doctorList.add(doctor);
            }
        }

        Collections.sort(doctorList);
        return doctorList;
    }

    Collection<String> doctorsByNumPatients() {
        List<Doctor> doctorList = new ArrayList<>(this.doctorMap.values());

        doctorList.sort((d1, d2) -> {
            int result = d2.patientCount() - d1.patientCount(); // Fixed incorrect sorting logic
            if (result == 0) {
                return d1.compareTo(d2);
            }
            return result;
        });

        List<String> result = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            result.add(String.format("%03d: %d %s %s", doctor.patientCount(), doctor.getId(), doctor.getLast(), doctor.getFirst()));
        }
        return result;
    }

    public Collection<String> countPatientsPerSpecialization() {
        List<PatientCountSpecialization> pcSp = new LinkedList<>();
        for (Doctor doctor : this.doctorMap.values()) {
            pcSp.add(new PatientCountSpecialization(doctor.patientCount(), doctor.getSpecialization()));
        }

        Collections.sort(pcSp);

        List<String> stringList = new ArrayList<>();
        for (PatientCountSpecialization p : pcSp) {
            stringList.add(String.format("%03d - %s", p.getPatientCount(), p.getSpName()));
        }
        return stringList;
    }

    public void loadData(String path) throws IOException {
        // Implement file reading logic here if needed
    }
}
