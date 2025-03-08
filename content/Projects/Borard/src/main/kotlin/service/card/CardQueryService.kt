package org.newTechDeveloper.service.card

import org.newTechDeveloper.dto.CardDetailsDTO
import org.newTechDeveloper.persistence.dao.CardDAO
import java.sql.SQLException
import java.sql.Connection;
import java.util.*


class CardQueryService(private val connection: Connection) {
    @Throws(SQLException::class)
    fun findById(id: Long?): Optional<CardDetailsDTO> {
        val dao = CardDAO(connection)
        return dao.findById(id)
    }
}