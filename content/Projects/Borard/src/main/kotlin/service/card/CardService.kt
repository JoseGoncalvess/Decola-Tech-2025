package org.newTechDeveloper.service.card

import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.dao.CardDAO
import org.newTechDeveloper.persistence.entity.CardEntity
import java.sql.Connection
import java.sql.SQLException


@AllArgsConstructor
class CardService( private val connection: Connection) {

    @Throws(SQLException::class)
    fun create(entity: CardEntity): CardEntity {
        try {
            val dao: CardDAO = CardDAO(connection)
            dao.insert(entity)
            connection.commit()
            return entity
        } catch (ex: SQLException) {
            connection.rollback()
            throw ex
        }
    }
}