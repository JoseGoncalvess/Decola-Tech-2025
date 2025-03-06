package org.newTechDeveloper.service

import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.dao.BoardColumnDAO
import org.newTechDeveloper.persistence.dao.BoardDAO
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.BoardEntity
import java.sql.Connection
import java.sql.SQLException


@AllArgsConstructor
class BoardService(private val connection: Connection) {
    @Throws(SQLException::class)
    fun insert(entity: BoardEntity): BoardEntity {
        val dao = BoardDAO(connection)
        val boardColumnDAO: BoardColumnDAO = BoardColumnDAO(connection)
        try {
            dao.insert(entity)
            val columns = entity.boardColumns.stream().map<BoardColumnEntity> { c: BoardColumnEntity ->
                c.board = entity
                c
            }.toList()
            for (column in columns) {
                boardColumnDAO.insert(column)
            }
            connection.commit()
        } catch (e: SQLException) {
            connection.rollback()
            throw e
        }
        return entity
    }

    @Throws(SQLException::class)
    fun delete(id: Long?): Boolean {
        val dao = BoardDAO(connection)
        try {
            if (!dao.exists(id)) {
                return false
            }
            dao.delete(id)
            connection.commit()
            return true
        } catch (e: SQLException) {
            connection.rollback()
            throw e
        }
    }
}