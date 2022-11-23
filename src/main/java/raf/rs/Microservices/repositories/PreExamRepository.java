package raf.rs.Microservices.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import raf.rs.Microservices.model.PreExamObligations;
import raf.rs.Microservices.wrapper.SortedStudents;

import java.util.List;

public interface PreExamRepository extends CrudRepository<PreExamObligations,Integer> {
    @Query(value = "select s.id_student,s.first_name,s.last_name, sum(ob.points) from " +
            "Student s join s.subjects p  join s.obligations  ob where p.name like :subject "+
            "group by s.id_student order by s.last_name "
    )
    List<Object[]> findAndSort1(String subject);
    @Query("select s.id_student,s.first_name,s.last_name, sum(ob.points) as suma from " +
            "Student s join s.subjects p  join s.obligations  ob where p.name like :subject "+
            "group by s.id_student order by suma desc "
    )
    List<Object[]> findAndSort2(String subject);
    @Query("select s.id_student,s.first_name,s.last_name, sum(ob.points) from " +
            "Student s join s.subjects p  join s.obligations  ob where p.name like :subject "+
            "group by s.id_student order by s.id_student "
    )
    List<Object[]> findAndSort3(String subject);
}
