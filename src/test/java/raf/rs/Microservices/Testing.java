package raf.rs.Microservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import raf.rs.Microservices.model.PreExamObligations;
import raf.rs.Microservices.model.StructOfObligations;
import raf.rs.Microservices.model.Subject;
import raf.rs.Microservices.services.AllService;
import raf.rs.Microservices.wrapper.SortedStudents;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Testing {

    @Autowired
    AllService allService;

    @Test
    public void  first() throws Exception{

        Subject s  = new Subject();
        StructOfObligations o1 = new StructOfObligations();
        StructOfObligations o2 = new StructOfObligations();
        o1.setName("Kolokvijum");
        o2.setName("Test");
        s.setName("Programiranje");
        List<StructOfObligations> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        s.setObligations(list);
//        allService.saveStructOfObligation(o1);
//        allService.saveStructOfObligation(o2);
        allService.insertSubject_withObligations(s);

    }
//    @Test
//    public Subject  second() throws Exception{
//
//        Subject s  = new Subject();
//        StructOfObligations o1 = new StructOfObligations();
//        StructOfObligations o2 = new StructOfObligations();
//        o1.setName("Kolokvijum");
//        o2.setName("Test");
//        s.setName("Programiranje");
//        List<StructOfObligations> list = new ArrayList<>();
//        list.add(o1);
//        list.add(o2);
//        allService.saveSubject(s);
//        allService.saveStructOfObligation(o1);
//        allService.saveStructOfObligation(o2);
//       return allService.insertObligationsinSubject("Programiranje","Kolokvijum");
//
//    }
    @Test
    public void  third() throws Exception{

        allService.insertStudent("Nikola","Mitrovic","RI","2019");


    }
    @Test
    public void  fourth() throws Exception{

        allService.insertStudent("Marko","Mitrovic","RI","2019");
        allService.insertSubject("Programiranje",6);
        allService.addSubjectToStudent("mmitrovic191ri","Programiranje");
        System.out.println(allService.findAllStudents().toString());
        allService.insertObaveza("mmitrovic191ri","Programiranje","Kolokvijum",10);
        allService.insertObaveza("mmitrovic191ri","Programiranje","Test",10);
        allService.changeObaveza("mmitrovic191ri","Programiranje","Test",16);
        allService.deleteObaveza("mmitrovic191ri","Programiranje","Kolokvijum");
    }
    @Test
    public void  fifth() throws Exception{

        allService.insertStudent("Marko","Mitrovic","RI","2019");
        allService.insertSubject("Programiranje",6);
        allService.addSubjectToStudent("mmitrovic191ri","Programiranje");
        System.out.println(allService.findAllStudents().toString());
        allService.insertObaveza("mmitrovic191ri","Programiranje","Kolokvijum",10);
        allService.insertObaveza("mmitrovic191ri","Programiranje","Test",10);
        allService.changeObaveza("mmitrovic191ri","Programiranje","Test",16);
        System.out.println(allService.findPreExamStudentSubject("mmitrovic191ri","Programiranje","Kolokvijum"));

    }
    @Test
    public void  sixth() throws Exception{

        allService.insertStudent("Marko","Mitrovic","RI","2019");
        allService.insertSubject("Programiranje",6);
        allService.addSubjectToStudent("mmitrovic191ri","Programiranje");
        System.out.println(allService.findAllStudents().toString());
        allService.insertObaveza("mmitrovic191ri","Programiranje","Kolokvijum",10);
        allService.insertObaveza("mmitrovic191ri","Programiranje","Test",10);
        allService.changeObaveza("mmitrovic191ri","Programiranje","Test",16);
        List<PreExamObligations> res = allService.findAllObligationStudent("mmitrovic191ri");
        for(PreExamObligations i : res)
            System.out.println(i.toString());

    }
    @Test
    public void  seventh() throws Exception{

        allService.insertStudent("Nikola","Mitrovic","RI","2019");
        allService.insertStudent("Marko","Nikolic","RI","2019");
        allService.insertStudent("Marinko","Dimic","RI","2019");
        allService.insertStudent("Coda","Sikic","RI","2019");
        allService.insertStudent("Sinan","Avamovic","RI","2019");
        allService.insertSubject("Programiranje",6);
        allService.addSubjectToStudent("nmitrovic191ri","Programiranje");
        allService.addSubjectToStudent("mnikolic192ri","Programiranje");
        allService.addSubjectToStudent("mdimic193ri","Programiranje");
        allService.addSubjectToStudent("csikic194ri","Programiranje");
        allService.addSubjectToStudent("savamovic195ri","Programiranje");
        allService.insertObaveza("nmitrovic191ri","Programiranje","Kolokvijum",10);
        allService.insertObaveza("mnikolic192ri","Programiranje","Kolokvijum",12);
        allService.insertObaveza("mdimic193ri","Programiranje","Kolokvijum",13);
        allService.insertObaveza("csikic194ri","Programiranje","Kolokvijum",15);
        allService.insertObaveza("savamovic195ri","Programiranje","Kolokvijum",9);
        List<SortedStudents> list = (allService.findAndSort("Programiranje",2));
        System.out.println(list.size());
        for( SortedStudents i : list){
            System.out.println(i.getIme() + " "+i.getPrezime() + " "+i.getPoeni() + " ");
        }
    }
}
