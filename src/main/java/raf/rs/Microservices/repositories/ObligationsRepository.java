package raf.rs.Microservices.repositories;

import org.springframework.data.repository.CrudRepository;
import raf.rs.Microservices.model.StructOfObligations;

public interface ObligationsRepository extends CrudRepository<StructOfObligations,Integer> {
}
