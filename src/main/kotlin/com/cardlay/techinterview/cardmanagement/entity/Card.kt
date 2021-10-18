package com.cardlay.techinterview.cardmanagement.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cards")
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var cardNumber: String = ""
    var balance: BigDecimal = BigDecimal.ZERO

    @ManyToOne
    @JoinColumn(name = "card_holder_id", nullable = false)
    var cardHolder: CardHolder = CardHolder()

    @OneToMany(mappedBy = "card")
    private var transactions: MutableSet<Transaction> = mutableSetOf()

    fun getTransactions(): Set<Transaction> {
        return transactions
    }

    fun setTransactions(transactions: MutableSet<Transaction>) {
        this.transactions = transactions
    }

    fun addTransaction(transaction: Transaction) {
        if (transactions.contains(transaction)) {
            return
        }
        transaction.card = this
        transactions.add(transaction)
    }
}