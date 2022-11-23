package raf.rs.Microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_subject;

    private String name;

    private int espb;

    private String stProgram;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "SUBJECT_STUDENT",
                joinColumns = @JoinColumn(name = "subject_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(    cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "SUBJECT_OBLIGATION",
                joinColumns =  @JoinColumn(name = "subject_id"),
                inverseJoinColumns = @JoinColumn(name = "obligation_id")
    )
    private List<StructOfObligations> obligations = new ArrayList<>();

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId_subject() {
        return id_subject;
    }

    public String getName() {
        return name;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<StructOfObligations> getObligations() {
        return obligations;
    }

    public void setObligations(List<StructOfObligations> obligations) {
        this.obligations = obligations;
    }

    public String getStProgram() {
        return stProgram;
    }

    public void setStProgram(String stProgram) {
        this.stProgram = stProgram;
    }
}
