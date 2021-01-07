package com.cardlay.techinterview.cardmanagement.repository;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
