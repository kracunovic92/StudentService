package raf.rs.Microservices.repositories;

import org.springframework.data.repository.CrudRepository;
import raf.rs.Microservices.model.Student;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
