package com.cardlay.techinterview.cardmanagement.repository

import com.cardlay.techinterview.cardmanagement.entity.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository : CrudRepository<Card, Long> {
    fun findAllByCardHolderId(cardHolderId: Long): List<Card>
}