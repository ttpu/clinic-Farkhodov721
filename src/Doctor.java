// Filename: Doctor.java

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Doctor extends Person {
    private int docID;
    private String specialization;
    private List<Person> patientList = new LinkedList<>();

    public Doctor(String first, String last, String ssn, int docID, String specialization) {
        super(first, last, ssn);
        this.docID = docID;
        this.specialization = specialization;
    }

    public int getId() {
        return docID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void addPatient(Person person) {
        this.patientList.add(person);
    }

    public Collection<Person> getPatients() {
        return patientList;
    }

    public int patientCount() {
        return this.patientList.size();
    }
}
