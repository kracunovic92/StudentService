package raf.rs.Microservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import raf.rs.Microservices.model.PreExamObligations;
import raf.rs.Microservices.model.StructOfObligations;
import raf.rs.Microservices.model.Student;
import raf.rs.Microservices.model.Subject;
import raf.rs.Microservices.repositories.ObligationsRepository;
import raf.rs.Microservices.repositories.PreExamRepository;
import raf.rs.Microservices.repositories.StudentRepository;
import raf.rs.Microservices.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllRepoTest {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PreExamRepository preExamRepository;

    @Autowired
    ObligationsRepository obligationsRepository;

    @Test
    public void saveStudentTest()throws Exception{

        Student student1 = new Student();
        student1.setFirst_name("lazar");
        student1.setLast_name("Kracunovic");
        student1.setSubjects(new ArrayList<Subject>());
        Student student2 = new Student();
        student2.setFirst_name("Danilo");
        student2.setLast_name("Markovic");
        student2.setSubjects(new ArrayList<Subject>());

        Subject subject1 = new Subject();
        subject1.setName("Mikroservisne");
        Subject subject2 = new Subject();
        subject2.setName("Programiranje");

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        studentRepository.save(student2);
        studentRepository.save(student1);

        //students and subjects
        List<Student> studentiPredmet1 = new ArrayList<>();
        studentiPredmet1.add(student1);
        studentiPredmet1.add(student2);

        List<Student> studentiZaPredmet2 = new ArrayList<>();
        studentiZaPredmet2.add(student1);

        List<Subject> predmetiZaStudenta1 = new ArrayList<>();
        predmetiZaStudenta1.add(subject1);
        predmetiZaStudenta1.add(subject2);

        List<Subject> predmetiZaStudenta2 = new ArrayList<>();
        predmetiZaStudenta2.add(subject1);

        student1.setSubjects(predmetiZaStudenta1);
        subject1.setStudents(studentiPredmet1);
        student2.setSubjects(predmetiZaStudenta2);
        subject2.setStudents(studentiZaPredmet2);
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        StructOfObligations obligations1 = new StructOfObligations();
        obligations1.setName("Kolokvijum");
        StructOfObligations test = new StructOfObligations();
        test.setName("Test");
        obligationsRepository.save(test);
        obligationsRepository.save(obligations1);

        List<Subject> szo = new ArrayList<>();
        szo.add(subject1);
        test.setSubjects(szo);
        List<StructOfObligations> ozs = new ArrayList<>();
        ozs.add(test);
        subject1.setObligations(ozs);

        subjectRepository.save(subject1);
        obligationsRepository.save(test);

        PreExamObligations poeni1 = new PreExamObligations();
        poeni1.setStudent(student1);
        poeni1.setSubject(subject1);
        poeni1.setId_obligation(obligations1.getId_struct());

        preExamRepository.save(poeni1);






    }


}
