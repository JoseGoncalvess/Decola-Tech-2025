package org.newTechDeveloper.persistence.dao

import java.sql.Connection
import java.sql.SQLException
import java.time.OffsetDateTime
import org.newTechDeveloper.persistence.config.utils.OffsetDateTimeConverter.*
import org.newTechDeveloper.persistence.config.utils.OffsetDateTimeConverter.Companion.toTimestamp

class BlockDAO( private val connection : Connection) {
    @Throws(SQLException::class)
    fun block(reason: String, cardId: Long) {
        val sql = "INSERT INTO BLOCKS (blocked_at, block_reason, card_id) VALUES (?, ?, ?);"
        connection.prepareStatement(sql).use { statement ->
            statement.setTimestamp(1, toTimestamp(OffsetDateTime.now()))
            statement.setString(2, reason)
            statement.setLong(3, cardId)
            statement.executeUpdate()
        }
    }

    @Throws(SQLException::class)
    fun unblock(reason: String?, cardId: Long?) {
        val sql = "UPDATE BLOCKS SET unblocked_at = ?, unblock_reason = ? WHERE card_id = ? AND unblock_reason IS NULL;"
        connection.prepareStatement(sql).use { statement ->
            statement.setTimestamp(1, toTimestamp(OffsetDateTime.now()))
            statement.setString(2, reason)
            statement.setLong(3, cardId!!)
            statement.executeUpdate()
        }
    }

}