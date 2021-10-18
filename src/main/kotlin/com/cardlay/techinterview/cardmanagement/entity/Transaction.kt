package com.cardlay.techinterview.cardmanagement.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "transactions")
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var amount: BigDecimal = BigDecimal.ZERO

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    var card: Card = Card()
}