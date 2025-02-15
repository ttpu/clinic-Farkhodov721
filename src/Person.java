// Filename: Person.java

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private String ssn;
    private Doctor doctor;

    public Person(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public String getSSN() {
        return ssn;
    }

    public String getFirst() {
        return firstName;
    }

    public String getLast() {
        return lastName;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public int compareTo(Person o) {
        int result = this.lastName.compareTo(o.lastName);
        return (result != 0) ? result : this.firstName.compareTo(o.firstName);
    }
}
