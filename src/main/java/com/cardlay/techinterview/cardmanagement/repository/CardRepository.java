package com.cardlay.techinterview.cardmanagement.repository;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findAllByCardHolderId(Long cardHolderId);

}
