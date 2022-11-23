package raf.rs.Microservices.services;

import org.springframework.stereotype.Service;
import raf.rs.Microservices.model.PreExamObligations;
import raf.rs.Microservices.model.StructOfObligations;
import raf.rs.Microservices.model.Student;
import raf.rs.Microservices.model.Subject;
import raf.rs.Microservices.repositories.ObligationsRepository;
import raf.rs.Microservices.repositories.PreExamRepository;
import raf.rs.Microservices.repositories.StudentRepository;
import raf.rs.Microservices.repositories.SubjectRepository;
import raf.rs.Microservices.wrapper.SortedStudents;

import javax.transaction.Transactional;
import java.sql.Struct;
import java.util.*;

@Service
public class AllService {

    private final StudentRepository studentRepository;
    private final PreExamRepository preExamRepository;
    private final SubjectRepository subjectRepository;
    private final ObligationsRepository obligationsRepository;


    public AllService(StudentRepository studentRepository, PreExamRepository preExamRepository, SubjectRepository subjectRepository, ObligationsRepository obligationsRepository) {
        this.studentRepository = studentRepository;
        this.preExamRepository = preExamRepository;
        this.subjectRepository = subjectRepository;
        this.obligationsRepository = obligationsRepository;
    }

    // save object
    public<S extends Student> S saveStudent(S student){
        return studentRepository.save(student);
    }
    public<S extends Subject> S saveSubject(S subject){
        return subjectRepository.save(subject);
    }
    public<S extends  StructOfObligations> S saveStructOfObligation(S struct){return obligationsRepository.save(struct);}
    public<S extends PreExamObligations> S saveObligation(S obligation){return  preExamRepository.save(obligation);}

    //find metods
    public List<Student> findAllStudents(){
        return (List<Student>) studentRepository.findAll();
    }
    public List<Subject> findAllSubject(){
        return (List<Subject>) subjectRepository.findAll();
    }
    public List<StructOfObligations> findAllStruct(){return (List<StructOfObligations>) obligationsRepository.findAll();}
    public List<PreExamObligations> findAllPreExample(){return (List<PreExamObligations>) preExamRepository.findAll();}

    @Transactional
    public <S extends  Student> S addObligation(S student,PreExamObligations o){
        Optional<Student> lst = studentRepository.findById(student.getId_student());
        Student s = lst.get();


        List<PreExamObligations> obligations = s.getObligations();
        preExamRepository.save(o);
        obligations.add(o);
        student.setObligations(obligations);
        return studentRepository.save(student);
    }
    public <S extends Subject> S addObligationToSubject(S s,StructOfObligations ob ){
        List<StructOfObligations> list = s.getObligations();
        list.add(ob);
        s.setObligations(list);
        return  subjectRepository.save(s);
    }
    @Transactional
    public Optional<Student> findStudentWithString(String student){
        List<Student> all = findAllStudents();

        for(Student s : all){
            if(s.toString().equals(student))
                return Optional.of(s);
        }
        return Optional.empty();
    }

    public Optional<Subject> findSubjectWithString(String subject){
        List<Subject> all = findAllSubject();

        for(Subject s : all){

            if(s.getName().equals(subject))
                return Optional.of(s);
        }
        return Optional.empty();
    }
    public void deleteStudentObligation(Student s, PreExamObligations o){
        List<PreExamObligations> obligations = s.getObligations();
        obligations.remove(o);
        s.setObligations(obligations);
        studentRepository.save(s);
    }

    public void insertSubject(String ime,int espb){
        Subject s = new Subject();
        s.setName(ime);
        s.setEspb(espb);
        saveSubject(s);
    }

    @Transactional
    // 1. unos predmeta sa stukturom obaveza
    public Subject  insertSubject_withObligations(Subject s)    {
        List<StructOfObligations> obligations = findAllStruct();
        List<StructOfObligations> this_subject_obligations = s.getObligations();

        return saveSubject(s);
    }

    @Transactional
    public void addSUbjectToStudentObject(Student s, Subject o){
        List<Subject> list_object = s.getSubjects();
        if(list_object == null){
            List<Subject> nova_list = new ArrayList<>();
            nova_list.add(o);
            s.setSubjects(nova_list);
        }else {
            list_object.add(o);
            s.setSubjects(list_object);
        }
        List<Student> list_students = o.getStudents();
        list_students.add(s);
        o.setStudents(list_students);
        studentRepository.save(s);
        subjectRepository.save(o);
    }
    @Transactional
    public Student addSubjectToStudent(String student,String subject){
        Optional<Student> this_student =findStudentWithString(student);
        Optional<Subject> this_subject = findSubjectWithString(subject);
        if(!this_subject.isPresent())
            return null;
        if(!this_student.isPresent())
            return null;
        Student s = this_student.get();
        Subject o = this_subject.get();

        List<Subject> list_object = s.getSubjects();
        if(list_object == null){
            List<Subject> nova_list = new ArrayList<>();
            nova_list.add(o);
            s.setSubjects(nova_list);
        }else {
            list_object.add(o);
            s.setSubjects(list_object);
        }
        List<Student> list_students = o.getStudents();
        list_students.add(s);
        o.setStudents(list_students);
        o.setStudents(list_students);
        subjectRepository.save(o);
        return studentRepository.save(s);

    }
    @Transactional
    Subject findSubjectByName(String subject,List<Subject> list){
        for(Subject i : list) {
            System.out.println( i.getName());
            if ((i.getName().toLowerCase().trim()).equals(subject.toLowerCase().trim())) {
                return i;
            }
        }
        return  null;
    }
    @Transactional
    StructOfObligations findStructbyName(String obligation,List<StructOfObligations> list){
        for(StructOfObligations i : list) {
            if ((i.getName()).equals(obligation))
                return i;
        }
        return  null;
    }

    // 2, dodavanje nove obaveze za predmet
    @Transactional
    public Subject insertObligationsinSubject(String subject,String obligation){
        List<Subject> listOfSubjects = findAllSubject();
        List<StructOfObligations> listOfStruct = findAllStruct();
        Subject this_subjet = findSubjectByName(subject,listOfSubjects);
        StructOfObligations this_obligation = findStructbyName(obligation,listOfStruct);

        if(this_subjet == null) {
            System.out.println("ne postoji predmet");
            return null;
        }
        if(this_obligation == null){
            StructOfObligations  ob = new StructOfObligations();
            ob.setName(obligation);
            List<Subject> new_one = new ArrayList<>();
            new_one.add(this_subjet);
            ob.setSubjects(new_one);
            saveStructOfObligation(ob);
            List<StructOfObligations> new_st = new ArrayList<>();
            new_st = this_subjet.getObligations();
            new_st.add(ob);
            this_subjet.setObligations(new_st);
        }else{
            List<StructOfObligations> listO = this_subjet.getObligations();
            listO.add(this_obligation);
            this_subjet.setObligations(listO);
            List<Subject> sta = new ArrayList<>();
            List<Subject> old = this_obligation.getSubjects();
            if( old == null) {
                sta.add(this_subjet);
                this_obligation.setSubjects(sta);
            }
            else {
                old.add(this_subjet);
                this_obligation.setSubjects(old);
            }
            saveStructOfObligation(this_obligation);
            saveSubject(this_subjet);
        }
        return this_subjet;
    }

    //3. unos novog studenta
    @Transactional
    public Student insertStudent(String ime,String prezime,String smer,String godina){
        Student s = new Student();
        s.setFirst_name(ime);
        s.setLast_name(prezime);
        s.setMajor(smer.toLowerCase());
        s.setYear(godina);
        List<Subject> list_subject = findAllSubject();
        for(Subject i : list_subject){
            if((i.getStProgram().toLowerCase()).equals(smer.toLowerCase())){
                addSUbjectToStudentObject(s,i);
            }
        }
        return saveStudent(s);
    }

    //4. unos  ostvarenih poena studenta na predispitnim obavezama
    @Transactional
    public PreExamObligations  insertObaveza(String student,String predmet,String obaveza,int poeni)
    {

        Optional<Student> this_student = findStudentWithString(student);
        Optional<Subject> this_subject = findSubjectWithString(predmet);
        Student obj_student = this_student.get();
        Subject obj_subject = this_subject.get();

        List<StructOfObligations> listStruct =findAllStruct();
        StructOfObligations obl = findStructbyName(obaveza,listStruct);
        if(obl== null){
            StructOfObligations new_struct = new StructOfObligations();
            new_struct.setName(obaveza);
            saveStructOfObligation(new_struct);
        }
        insertObligationsinSubject(obj_subject.getName(),obaveza);
    List<StructOfObligations> struct_obj = obj_subject.getObligations();
        StructOfObligations struct = findStructbyName(obaveza,struct_obj);
        PreExamObligations obligation = new PreExamObligations();
        obligation.setPoints(poeni);
        obligation.setSubject(obj_subject);
        obligation.setStudent(obj_student);
        obligation.setObligations(struct);
        List<PreExamObligations>listObligation = obj_student.getObligations();
        listObligation.add(obligation);
        saveObligation(obligation);
        saveStudent(obj_student);
        return  obligation;

    }

    // 4. izmena ostvarenih poena na predispitnim obavezama
@Transactional
    public  PreExamObligations changeObaveza(String student,String predmet,String obaveza,int poeni){

        Optional<Student> this_student = findStudentWithString(student);
        if(!this_student.isPresent()){
            return  null;
        }
        Optional<Subject> this_subject = findSubjectWithString(predmet);
        if(!this_subject.isPresent()){
            return null;
        }

        List<PreExamObligations> obligations = this_student.get().getObligations();

        for (PreExamObligations o : obligations){
            if(o.getSubject() == this_subject.get() && o.getObligations().getName().equals(obaveza)){
                o.setPoints(poeni);
                return  preExamRepository.save(o);
            }
        }
        return null;
    }
    // 4. obrisi ostvarene poene na predispitnim obavezama
    @Transactional
    public  boolean deleteObaveza(String student,String predmet,String obaveza){

        Optional<Student> this_student = findStudentWithString(student);
        if(!this_student.isPresent()){
            return  false;
        }
        Optional<Subject> this_subject = findSubjectWithString(predmet);
        if(!this_subject.isPresent()){
            return false;
        }

        List<PreExamObligations> obligations = this_student.get().getObligations();

        for (PreExamObligations o : obligations){
            if(o.getObligations().getName().equals(obaveza))
            if(o.getSubject() == this_subject.get()){
                preExamRepository.delete(o);
                deleteStudentObligation(this_student.get(),o);
                return  true;
            }
        }
        return false;
    }

    //5. preuzimanje ostvarenih poena na predispitnim obavezama za nalog studenta i predmet
@Transactional
    public int findPreExamStudentSubject(String student,String predmet,String obligation){
        Optional<Student> this_student = findStudentWithString(student);
        if(!this_student.isPresent()){
            return  -1;
        }
        Optional<Subject> this_subject = findSubjectWithString(predmet);
        if(!this_subject.isPresent()){
            return -2;
        }
        List<PreExamObligations> obligations = this_student.get().getObligations();
        for (PreExamObligations o : obligations){
            System.out.println("ovo su obaveze " + o.getObligations().getName());
            if(o.getSubject() == this_subject.get() && o.getObligations().getName().equals(obligation)){
                return o.getPoints();
            }
        }
        return -3;
    }

    //6. preuzimanje svih poena za jednog studenta
  @Transactional
    public List<PreExamObligations>   findAllObligationStudent(String student){
        Optional<Student> this_student = findStudentWithString(student);
        Student s = this_student.get();
        if(!this_student.isPresent())
            return null;
        int id = s.getId_student();
        List<PreExamObligations> list = findAllPreExample();
        List<PreExamObligations> res = new ArrayList<>();
        for(PreExamObligations i : list) {
            if (i.getStudent().getId_student() == id)
                res.add(i);
        }

        int sum = 0;
      return  res;
    }
    //7. preuzimanje ukupnog broja poena za sve studente na jednom predmetu
    // sortirano po  broju poena
    // sortirano po prezimenu
    // sortirano po indeksu
    @Transactional
    public List<SortedStudents>  findAndSort(String subject, Integer type){
        if(type == 1) {
            List<Object[]> list = preExamRepository.findAndSort1(subject);
            List<SortedStudents> res = new ArrayList<>();
            for(Object[] i : list){
                SortedStudents s = new SortedStudents((String) i[1], (String) i[2], Math.toIntExact((Long) i[3]));
                res.add(s);
            }

            return res;
        }
        if(type == 2) {
            List<Object[]> list = preExamRepository.findAndSort2(subject);
            List<SortedStudents> res = new ArrayList<>();
            for(Object[] i : list){
                SortedStudents s = new SortedStudents((String) i[1], (String) i[2], Math.toIntExact((Long) i[3]));
                res.add(s);
            }

            return res;
        }else{
            List<Object[]> list = preExamRepository.findAndSort3(subject);
            List<SortedStudents> res = new ArrayList<>();
            for(Object[] i : list){
                SortedStudents s = new SortedStudents((String) i[1], (String) i[2], Math.toIntExact((Long) i[3]));
                res.add(s);
            }

            return res;
        }
    }


}
