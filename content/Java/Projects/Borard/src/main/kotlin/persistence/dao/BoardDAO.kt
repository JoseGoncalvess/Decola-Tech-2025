package org.newTechDeveloper.persistence.dao

import com.mysql.cj.jdbc.StatementImpl
import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.entity.BoardEntity
import java.sql.Connection
import java.sql.SQLException
import java.util.*

@AllArgsConstructor
class BoardDAO( private val connection: Connection) {
    @Throws(SQLException::class)
    fun insert(entity: BoardEntity): BoardEntity {
        val sql = "INSERT INTO BOARDS (name) values (?);"
        connection.prepareStatement(sql).use { statement ->
            statement.setString(1, entity.name)
            statement.executeUpdate()
            if (statement is StatementImpl) {
                entity.id = statement.lastInsertID
            }
        }
        return entity
    }

    @Throws(SQLException::class)
    fun delete(id: Long?) {
        val sql = "DELETE FROM BOARDS WHERE id = ?;"
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, id!!)
            statement.executeUpdate()
        }
    }

    @Throws(SQLException::class)
    fun findById(id: Long?): Optional<BoardEntity> {
        val sql = "SELECT id, name FROM BOARDS WHERE id = ?;"
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, id!!)
            statement.executeQuery()
            val resultSet = statement.resultSet
            if (resultSet.next()) {
                val entity = BoardEntity(null,null);
                entity.id = resultSet.getLong("id")
                entity.name = resultSet.getString("name")
                return Optional.of(entity)
            }
            return Optional.empty()
        }
    }

    @Throws(SQLException::class)
    fun exists(id: Long?): Boolean {
        val sql = "SELECT 1 FROM BOARDS WHERE id = ?;"
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, id!!)
            statement.executeQuery()
            return statement.resultSet.next()
        }
    }
    @Throws(SQLException::class)
    fun allBoard() : MutableList<BoardEntity> {
        val boards : MutableList<BoardEntity> = arrayListOf()
        val sql = "SELECT * FROM BOARDS"
        connection.prepareStatement(sql).use { statement ->
            statement.execute()
            val resultSet = statement.resultSet
            if (resultSet.next()){
                val entity = BoardEntity(null,null);
                entity.id = resultSet.getLong("id")
                entity.name = resultSet.getString("name")
                boards.add(entity)
            }
            return boards
        }
    }

}