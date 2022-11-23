package raf.rs.Microservices.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import raf.rs.Microservices.model.PreExamObligations;
import raf.rs.Microservices.model.StructOfObligations;
import raf.rs.Microservices.model.Student;
import raf.rs.Microservices.model.Subject;
import raf.rs.Microservices.services.AllService;
import raf.rs.Microservices.wrapper.SortedStudents;
import raf.rs.Microservices.wrapper.WrapperInsertObaveza;
import raf.rs.Microservices.wrapper.WrapperSort;
import raf.rs.Microservices.wrapper.WrapperStudent;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProjectRestController {

    private final AllService allService;

    public ProjectRestController(AllService allService) {
        this.allService = allService;
    }
    @GetMapping(value = "/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents(){
        return allService.findAllStudents();
    }

    @PostMapping(value= "/create_student",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student){return allService.saveStudent(student);}
    @PostMapping(value= "/create_subject",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject createStudent(@RequestBody Subject subject){return allService.saveSubject(subject);}
    @PostMapping(value = "/create_structObligation",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public StructOfObligations createStructObligation(@RequestBody StructOfObligations obligations){return allService.saveStructOfObligation(obligations);}
    @PostMapping(value = "/student_subject",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Student createStudentSubject(@RequestBody WrapperInsertObaveza i){
        String student = i.getStudent();
        String predmet = i.getPredmet();
        return allService.addSubjectToStudent(student,predmet);
    }

    //1
    @PostMapping( value="/1",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject  insertSubject_withObligations(@RequestBody Subject s){
        return allService.insertSubject_withObligations(s);}

    //2
    @PostMapping(value = "/2",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Subject insertObligationinSubject(@RequestBody WrapperInsertObaveza clas){
        String s = clas.getPredmet();
        String o = clas.getObaveza();
        return allService.insertObligationsinSubject(s,o);}

    //3
    @PostMapping(value="/3",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Student insertStudent(@RequestBody WrapperStudent clas){
        String ime =clas.getIme();
        String prezime = clas.getPrezime();
        String smer = clas.getSmer();
        String godina = clas.getGodina();

        return allService.insertStudent(ime,prezime,smer,godina);
    }
    //4a
    @PostMapping(value = "/4a",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PreExamObligations insertObaveza(@RequestBody WrapperInsertObaveza o){

        String student = o.getStudent();
        String predment = o.getPredmet();
        String obaveza = o.getObaveza();
        int poeni = o.getPoeni();
        return allService.insertObaveza(student,predment,obaveza,poeni);
    }

    //4b
    @PostMapping(value = "/4b",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PreExamObligations changeObaveza(@RequestBody WrapperInsertObaveza o){

        String student = o.getStudent();
        String predment = o.getPredmet();
        String obaveza = o.getObaveza();
        int poeni = o.getPoeni();
        return allService.changeObaveza(student,predment,obaveza,poeni);
    }

    //4c
    @DeleteMapping(value = "/4c",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteObaveza(@RequestBody WrapperInsertObaveza o){

        String student = o.getStudent();
        String predment = o.getPredmet();
        String obaveza = o.getObaveza();
        int poeni = o.getPoeni();
        return allService.deleteObaveza(student,predment,obaveza);
    }


    //5
    @PostMapping(value = "/5",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public int preuzmiPoene(@RequestBody WrapperInsertObaveza o){

        String student = o.getStudent();
        String predment = o.getPredmet();
        String obaveza = o.getObaveza();
        int poeni = o.getPoeni();
        return allService.findPreExamStudentSubject(student,predment,obaveza);
    }

    //6
    @PostMapping(value = "/6",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PreExamObligations> ukupnoPoena(@RequestBody WrapperInsertObaveza o){

        String student = o.getStudent();
        String predment = o.getPredmet();
        String obaveza = o.getObaveza();
        int poeni = o.getPoeni();
        return allService.findAllObligationStudent(student);
    }


    //7
    @PostMapping(value = "/7",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SortedStudents> ukupnoPoena(@RequestBody WrapperSort o){

        String name = o.getName();
        Integer type = o.getType();
        return allService.findAndSort(name,type);
    }


}
