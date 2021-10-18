package com.cardlay.techinterview.cardmanagement.repository

import com.cardlay.techinterview.cardmanagement.entity.CardHolder
import org.springframework.data.repository.CrudRepository

interface CardHolderRepository : CrudRepository<CardHolder, Long>