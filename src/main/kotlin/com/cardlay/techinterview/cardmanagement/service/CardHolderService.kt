package com.cardlay.techinterview.cardmanagement.service

import com.cardlay.techinterview.cardmanagement.entity.CardHolder
import com.cardlay.techinterview.cardmanagement.exception.NotFoundException
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.StreamSupport

@Service
class CardHolderService(
    private val cardHolderRepository: CardHolderRepository
) {
    fun getAllCardHolders(): List<CardHolder> {
        val cardHolderIterable = cardHolderRepository.findAll()

        return StreamSupport.stream(cardHolderIterable.spliterator(), false)
            .toList()
    }

    fun createCardHolder(name: String, email: String): CardHolder {
        val cardHolder = CardHolder().apply {
            this.name = name
            this.email = email
        }

        return cardHolderRepository.save(cardHolder)
    }

    @Transactional
    fun updateCardHolder(cardHolderId: Long, name: String?): CardHolder {
        val cardHolderOptional = cardHolderRepository.findById(cardHolderId)
        if (cardHolderOptional.isEmpty) {
            throw NotFoundException()
        }

        val cardHolder = cardHolderOptional.get()
        cardHolder.name = name ?: cardHolder.name

        return cardHolderRepository.save(cardHolder)
    }
}