package raf.rs.Microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STRUCT_OF_OBLIGATIONS")
public class StructOfObligations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_struct;
    private String name;


    @ManyToMany(mappedBy = "obligations",     cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Subject> subjects;


    public int getId_struct() {
        return id_struct;
    }

    public void setId_struct(int id_struct) {
        this.id_struct = id_struct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
