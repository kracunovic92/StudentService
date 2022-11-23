package raf.rs.Microservices.repositories;

import org.springframework.data.repository.CrudRepository;
import raf.rs.Microservices.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject,Integer> {
}
