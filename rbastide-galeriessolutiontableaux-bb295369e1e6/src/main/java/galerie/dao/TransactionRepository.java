package galerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Transaction;

// This will be AUTO IMPLEMENTED by Spring 

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
