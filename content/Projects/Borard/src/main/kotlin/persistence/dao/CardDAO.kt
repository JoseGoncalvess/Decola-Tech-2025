package org.newTechDeveloper.persistence.dao

import com.mysql.cj.jdbc.StatementImpl
import org.newTechDeveloper.persistence.entity.CardEntity
import java.sql.Connection
import java.sql.SQLException
import java.util.*
import java.util.Objects.nonNull


class CardDAO(private val connection: Connection) {
    @Throws(SQLException::class)
    fun insert(entity: CardEntity): CardEntity {
        val sql = "INSERT INTO CARDS (title, description, board_column_id) values (?, ?, ?);"
        connection.prepareStatement(sql).use { statement ->
            var i = 1
            statement.setString(i++, entity.title)
            statement.setString(i++, entity.description)
            statement.setLong(i, entity.boardColumn!!.id!!)
            statement.executeUpdate()
            if (statement is StatementImpl) {
                entity.id = statement.lastInsertID
            }
        }
        return entity
    }

    @Throws(SQLException::class)
    fun moveToColumn(columnId: Long?, cardId: Long?) {
        val sql = "UPDATE CARDS SET board_column_id = ? WHERE id = ?;"
        connection.prepareStatement(sql).use { statement ->
            var i = 1
            statement.setLong(i++, columnId!!)
            statement.setLong(i, cardId!!)
            statement.executeUpdate()
        }
    }

    @Throws(SQLException::class)
    fun findById(id: Long?)  {

    }
}