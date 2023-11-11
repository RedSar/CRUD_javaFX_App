package ma.rsmi.crud_javafx_app.models;

public class Student {
    private int id;
    private String firstname;
    private String lastname;
    private String course;

    public int getId() {
        return id;
    }

    public Student(String firstname, String lastname, String course) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
    }

    public Student(int id, String firstname, String lastname, String course) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
