package org.newTechDeveloper.service.board

import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.dao.BoardColumnDAO
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import java.sql.Connection
import java.sql.SQLException
import java.util.*

@AllArgsConstructor
class BoardColumnQueryService(private val connection: Connection) {
    @Throws(SQLException::class)
    fun findById(id: Long?): Optional<BoardColumnEntity> {
        val dao = BoardColumnDAO(connection)
        return dao.findById(id)
    }

    @Throws(SQLException::class)
    fun deletById(id: Long): Boolean {
        val dao = BoardColumnDAO(connection)
        return dao.delet(id)
    }
}
