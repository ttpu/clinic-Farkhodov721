// Filename: PatientCountSpecialization.java

public class PatientCountSpecialization implements Comparable<PatientCountSpecialization> {
    private int patientCount;
    private String spName;

    public PatientCountSpecialization(int patientCount, String spName) {
        this.patientCount = patientCount;
        this.spName = spName;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public String getSpName() {
        return spName;
    }

    @Override
    public int compareTo(PatientCountSpecialization o) {
        int result = Integer.compare(o.patientCount, this.patientCount); // Fixed incorrect return
        return (result != 0) ? result : this.spName.compareTo(o.spName);
    }
}
