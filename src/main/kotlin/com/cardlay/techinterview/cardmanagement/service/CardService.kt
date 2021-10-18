package com.cardlay.techinterview.cardmanagement.service

import com.cardlay.techinterview.cardmanagement.entity.Card
import com.cardlay.techinterview.cardmanagement.exception.NotFoundException
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository
import com.cardlay.techinterview.cardmanagement.repository.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val cardHolderRepository: CardHolderRepository
) {
    @Transactional(readOnly = true)
    fun getAllCardsForCardHolder(cardHolderId: Long): List<Card> {
        if (!cardHolderRepository.existsById(cardHolderId)) {
            throw NotFoundException()
        }

        return cardRepository.findAllByCardHolderId(cardHolderId)
    }

    @Transactional
    fun addToCardBalance(cardId: Long, amount: BigDecimal): Card {
        val cardOptional = cardRepository.findById(cardId)
        if (cardOptional.isEmpty) {
            throw NotFoundException()
        }

        val card = cardOptional.get()
        card.balance = card.balance.add(amount)

        return cardRepository.save(card)
    }

    @Transactional
    fun createCardForCardHolder(cardHolderId: Long, cardNumber: String, balance: BigDecimal): Card {
        val cardHolderOptional = cardHolderRepository.findById(cardHolderId)
        if (cardHolderOptional.isEmpty) {
            throw NotFoundException()
        }

        val cardHolder = cardHolderOptional.get()
        val card = Card().apply {
            this.cardNumber = cardNumber
            this.balance = balance
            this.cardHolder = cardHolder
        }

        return cardRepository.save(card)
    }
}