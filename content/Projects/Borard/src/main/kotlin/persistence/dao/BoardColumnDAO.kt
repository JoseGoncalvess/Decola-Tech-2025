package org.newTechDeveloper.persistence.dao

import com.mysql.cj.jdbc.StatementImpl
import lombok.RequiredArgsConstructor
import org.newTechDeveloper.dto.BoardColumnDTO
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.CardEntity
import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum
import java.sql.Connection
import java.sql.SQLException
import java.util.*


@RequiredArgsConstructor
class BoardColumnDAO( private val connection: Connection) {
    @Throws(SQLException::class)
    fun insert(entity: BoardColumnEntity): BoardColumnEntity {
        val sql = "INSERT INTO BOARDS_COLUMNS (name, `order`, kind, board_id) VALUES (?, ?, ?, ?);"
        connection.prepareStatement(sql).use { statement ->
            var i = 1
            statement.setString(1, entity.name)
            statement.setInt(2, entity.order!!)
            statement.setString(3, entity.kind!!.name)
            statement.setLong(4, entity.board!!.id!!)
            statement.executeUpdate()
            if (statement is StatementImpl) {
                entity.id = statement.lastInsertID
            }
            return entity
        }
    }

    @Throws(SQLException::class)
    fun findByBoardId(boardId: Long?): List<BoardColumnEntity> {
        val entities: MutableList<BoardColumnEntity> = ArrayList()
        val sql = "SELECT id, name, `order`, kind FROM BOARDS_COLUMNS WHERE board_id = ? ORDER BY `order`"
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, boardId!!)
            statement.executeQuery()
            val resultSet = statement.resultSet
            while (resultSet.next()) {
                val entity = BoardColumnEntity(null,null,null,null,null)
                entity.id = resultSet.getLong("id");
                entity.name = resultSet.getString("name");
                entity.order = resultSet.getInt("order");
                entity.kind = BoardColumnKindEnum.findByName(resultSet.getString("kind"))
                entities.add(entity)
            }
            return entities
        }
    }

    @Throws(SQLException::class)
    fun findByBoardIdWithDetails(boardId: Long?): List<BoardColumnDTO> {
        val dtos: MutableList<BoardColumnDTO> = ArrayList<BoardColumnDTO>()
        val sql =
            """
                SELECT bc.id,
                       bc.name,
                       bc.kind,
                       (SELECT COUNT(c.id)
                               FROM CARDS c
                              WHERE c.board_column_id = bc.id) cards_amount
                  FROM BOARDS_COLUMNS bc
                 WHERE board_id = ?
                 ORDER BY `order`;
                
                """.trimIndent()
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, boardId!!)
            statement.executeQuery()
            val resultSet = statement.resultSet
            while (resultSet.next()) {
                val dto: BoardColumnDTO = BoardColumnDTO(
                    resultSet.getLong("bc.id"),
                    resultSet.getString("bc.name"),
                    BoardColumnKindEnum.findByName(resultSet.getString("bc.kind")),
                    resultSet.getInt("cards_amount")
                )
                dtos.add(dto)
            }
            return dtos
        }
    }

    @Throws(SQLException::class)
    fun findById(boardId: Long?): Optional<BoardColumnEntity> {
        val sql =
            """
        SELECT bc.name,
               bc.kind,
               c.id,
               c.title,
               c.description
          FROM BOARDS_COLUMNS bc
          LEFT JOIN CARDS c
            ON c.board_column_id = bc.id
         WHERE bc.id = ?;
        
        """.trimIndent()
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, boardId!!)
            statement.executeQuery()
            val resultSet = statement.resultSet
            if (resultSet.next()) {
                val entity = BoardColumnEntity(null,null,null,null,null);
                entity.name = resultSet.getString("bc.name");
                entity.kind = BoardColumnKindEnum.findByName(resultSet.getString("bc.kind"));
                do {
                    val card :CardEntity  = CardEntity(null,null,null,null);
                    if (resultSet.getString("c.title") == null) {
                        break
                    }
                    card.id = resultSet.getLong("c.id");
                    card.title = resultSet.getString("c.title");
                    card.description =resultSet.getString("c.description");
                    entity.cards.add(card)
                } while (resultSet.next())
                return Optional.of(entity)
            }
            return Optional.empty()
        }
    }

    @Throws(SQLException::class)
    fun delet(id : Long): Boolean {
        val dao = BoardDAO(connection)
        try {
            if (dao.exists(id)){
                return  true;
            }
        }catch (e:SQLException){
            connection.rollback()
            throw  e
        }

       return false
    }
}