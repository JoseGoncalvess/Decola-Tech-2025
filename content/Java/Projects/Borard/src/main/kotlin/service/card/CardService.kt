package org.newTechDeveloper.service.card

import lombok.AllArgsConstructor
import org.newTechDeveloper.dto.BoardColumnInfoDTO
import org.newTechDeveloper.exceptions.CardBlockedException
import org.newTechDeveloper.exceptions.CardFinishedException
import org.newTechDeveloper.exceptions.EntityNotFoundException
import org.newTechDeveloper.persistence.dao.BlockDAO
import org.newTechDeveloper.persistence.dao.CardDAO
import org.newTechDeveloper.persistence.entity.CardEntity
import org.newTechDeveloper.persistence.config.utils.enums.BoardColumnKindEnum.*
import java.sql.Connection
import java.sql.SQLException
import java.util.function.Predicate
import java.util.function.Supplier


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

    @Throws(SQLException::class)
    fun moveToNextColumn(cardId: Long, boardColumnsInfo: MutableList<BoardColumnInfoDTO>) {
        try {
            val dao = CardDAO(connection)
            val optional = dao.findById(cardId)
            val dto = optional.orElseThrow {  EntityNotFoundException(
                "O card de id $cardId não foi encontrado"
            ) }
            if (dto.blocked) {
                throw CardBlockedException("O card $cardId está bloqueado, é necesário desbloquea-lo para mover")
            }
            val currentColumn: BoardColumnInfoDTO = boardColumnsInfo.stream()
                .filter(Predicate<BoardColumnInfoDTO> { bc: BoardColumnInfoDTO ->
                    bc.id == dto.columnId
                })
                .findFirst()
                .orElseThrow<IllegalStateException> {
                    IllegalStateException(
                        "O card informado pertence a outro board"
                    )
                }
            if (currentColumn.kind == FINAL) {
                throw CardFinishedException("O card já foi finalizado")
            }
            val nextColumn: BoardColumnInfoDTO = boardColumnsInfo.stream()
                .filter(Predicate<BoardColumnInfoDTO> { bc: BoardColumnInfoDTO -> bc.order.equals(currentColumn.order + 1) })
                .findFirst().orElseThrow<IllegalStateException> {
                    IllegalStateException(
                        "O card está cancelado"
                    )
                }
            dao.moveToColumn(nextColumn.id, cardId)
            connection.commit()
        } catch (ex: SQLException) {
            connection.rollback()
            throw ex
        }
    }

    @Throws(SQLException::class)
    fun cancel(
        cardId: Long, cancelColumnId: Long,
        boardColumnsInfo: List<BoardColumnInfoDTO>
    ) {
        try {
            val dao = CardDAO(connection)
            val optional = dao.findById(cardId)
            val dto = optional.orElseThrow<RuntimeException>(
                Supplier<RuntimeException> {
                    EntityNotFoundException(
                        "O card de id $cardId não foi encontrado"
                    )
                }
            )
            if (dto.blocked) {
                val message = "O card $cardId está bloqueado, é necesário desbloquea-lo para mover"
                throw CardBlockedException(message)
            }
            val currentColumn: BoardColumnInfoDTO = boardColumnsInfo.stream()
                .filter(Predicate<BoardColumnInfoDTO> { bc: BoardColumnInfoDTO ->
                    bc.id == dto.columnId
                })
                .findFirst()
                .orElseThrow<IllegalStateException> {
                    IllegalStateException(
                        "O card informado pertence a outro board"
                    )
                }
            if (currentColumn.kind == FINAL) {
                throw CardFinishedException("O card já foi finalizado")
            }
            boardColumnsInfo.stream()
                .filter(Predicate<BoardColumnInfoDTO> { bc: BoardColumnInfoDTO -> bc.order == currentColumn.order + 1 })
                .findFirst().orElseThrow<IllegalStateException> {
                    IllegalStateException(
                        "O card está cancelado"
                    )
                }
            dao.moveToColumn(cancelColumnId, cardId)
            connection.commit()
        } catch (ex: SQLException) {
            connection.rollback()
            throw ex
        }
    }

    @Throws(SQLException::class)
    fun block(id: Long, reason: String, boardColumnsInfo: MutableList<BoardColumnInfoDTO>) {
        try {
            val dao = CardDAO(connection)
            val optional = dao.findById(id)
            val dto = optional.orElseThrow<RuntimeException>(
                Supplier<RuntimeException> {
                    EntityNotFoundException("O card de id $id não foi encontrado" )
                }
            )
            if (dto.blocked) {
                val message = "O card $id já está bloqueado"
                throw CardBlockedException(message)
            }
            val currentColumn: BoardColumnInfoDTO = boardColumnsInfo.stream()
                .filter(Predicate<BoardColumnInfoDTO> { bc: BoardColumnInfoDTO ->
                    bc.id == dto.columnId
                })
                .findFirst()
                .orElseThrow()
            if (currentColumn.kind == FINAL || currentColumn.kind == CANCEL) {
                throw IllegalStateException("O card está em uma coluna do tipo ${currentColumn.kind} e não pode ser bloqueado")
            }
            val blockDAO: BlockDAO = BlockDAO(connection)
            blockDAO.block(reason, id)
            connection.commit()
        } catch (ex: SQLException) {
            connection.rollback()
            throw ex
        }
    }

    @Throws(SQLException::class)
    fun unblock(id: Long, reason: String) {
        try {
            val dao = CardDAO(connection)
            val optional = dao.findById(id)
            val dto = optional.orElseThrow<RuntimeException>(
                Supplier<RuntimeException> {
                    EntityNotFoundException(
                        "O card de id %s não foi encontrado".formatted(
                            id
                        )
                    )
                }
            )
            if (!dto.blocked) {
                throw CardBlockedException("O card $id não está bloqueado")
            }
            val blockDAO: BlockDAO = BlockDAO(connection)
            blockDAO.unblock(reason, id)
            connection.commit()
        } catch (ex: SQLException) {
            connection.rollback()
            throw ex
        }
    }

}