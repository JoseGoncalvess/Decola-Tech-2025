package org.newTechDeveloper.persistence.dao

import com.mysql.cj.jdbc.StatementImpl
import org.newTechDeveloper.dto.CardDetailsDTO
import org.newTechDeveloper.persistence.config.utils.OffsetDateTimeConverter.Companion.toOffsetDateTime
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
            statement.setString(1, entity.title)
            statement.setString(2, entity.description)
            statement.setLong(3, entity.boardColumn!!.id!!)
            statement.executeUpdate()
            if (statement is StatementImpl) {
                entity.id = statement.lastInsertID
            }
        }
        return entity
    }

    @Throws(SQLException::class)
    fun moveToColumn(columnId: Long, cardId: Long) {
        val sql = "UPDATE CARDS SET board_column_id = ? WHERE id = ?;"
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, columnId)
            statement.setLong(2, cardId)
            statement.executeUpdate()
        }
    }

    @Throws(SQLException::class)
    fun findById(id: Long): Optional<CardDetailsDTO> {

        // TODO MELHORAR A QUERRY TRAZENDO SOMENTE O CARD QUE PERTENCE A O BORD SELECIOBADO
        var sql =
            """
                SELECT c.id,
                       c.title,
                       c.description,
                       b.blocked_at,
                       b.block_reason,
                       c.board_column_id,
                       bc.name,
                       (SELECT COUNT(sub_b.id)
                               FROM BLOCKS sub_b
                              WHERE sub_b.card_id = c.id) blocks_amount
                  FROM CARDS c
                  LEFT JOIN BLOCKS b
                    ON c.id = b.card_id
                   AND b.unblocked_at IS NULL
                 INNER JOIN BOARDS_COLUMNS bc
                    ON bc.id = c.board_column_id
                  WHERE c.id = ?;
                """;
        connection.prepareStatement(sql).use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
            val resultSet = statement.resultSet
            if (resultSet.next()) {
                val dto: CardDetailsDTO = CardDetailsDTO(
                    resultSet.getLong("c.id"),
                    resultSet.getString("c.title"),
                    resultSet.getString("c.description"),
                    nonNull(resultSet.getString("b.block_reason")),
                    blockedAt = toOffsetDateTime(resultSet.getTimestamp("b.blocked_at")),
                    resultSet.getString("b.block_reason") ?: "Motivo não informado",
                    resultSet.getInt("blocks_amount"),
                    resultSet.getLong("c.board_column_id"),
                    resultSet.getString("bc.name")
                )
                return Optional.of<CardDetailsDTO>(dto)
            }
        }
        return Optional.empty<CardDetailsDTO>()
    }
}