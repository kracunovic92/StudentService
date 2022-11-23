package raf.rs.Microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "PRE_EXAM_OBLIGATIONS")
public class PreExamObligations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_obligation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_obligations")
    private StructOfObligations obligations;

    private int points;

    public void setId_obligation(int id_obligation) {
        this.id_obligation = id_obligation;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId_obligation() {
        return id_obligation;
    }

    public Subject getSubject() {
        return subject;
    }

    public Student getStudent() {
        return student;
    }

    public StructOfObligations getObligations() {
        return obligations;
    }

    public void setObligations(StructOfObligations obligations) {
        this.obligations = obligations;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PreExamObligations{" +
                "id_obligation=" + id_obligation +
                ", subject=" + subject +
                ", student=" + student +
                ", obligations=" + obligations +
                ", points=" + points +
                '}';
    }
}

