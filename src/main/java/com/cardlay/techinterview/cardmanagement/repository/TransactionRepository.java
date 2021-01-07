package com.cardlay.techinterview.cardmanagement.repository;

import com.cardlay.techinterview.cardmanagement.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
