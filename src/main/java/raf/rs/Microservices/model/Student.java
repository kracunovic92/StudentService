package raf.rs.Microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_student;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;


    @Column(name = "MAJOR")
    private String major;

    @Column(name = "YEAR_ENTRY")
    private String year;
    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    @OneToMany
    private List<PreExamObligations> obligations;

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getId_student() {
        return id_student;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getMajor() {
        return major;
    }

    public List<PreExamObligations> getObligations() {
        return obligations;
    }

    public void setObligations(List<PreExamObligations> obligations) {
        this.obligations = obligations;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return first_name.toLowerCase().subSequence(0,1) +
                last_name.toLowerCase() +
                year.substring(year.length()-2)+
                id_student +
                major.toLowerCase();
    }
}
