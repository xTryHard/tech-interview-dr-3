package com.cardlay.techinterview.cardmanagement.entity

import javax.persistence.*

@Entity
@Table(name = "card_holders")
class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name: String = ""
    var email: String = ""

    @OneToMany(mappedBy = "cardHolder")
    var cards: Set<Card> = setOf()
}