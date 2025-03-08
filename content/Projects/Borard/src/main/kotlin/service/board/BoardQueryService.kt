package org.newTechDeveloper.service.board

import org.newTechDeveloper.dto.BoardDetailsDTO
import org.newTechDeveloper.persistence.dao.BoardColumnDAO
import org.newTechDeveloper.persistence.dao.BoardDAO
import org.newTechDeveloper.persistence.entity.BoardEntity
import java.sql.Connection
import java.sql.SQLException
import java.util.*


class BoardQueryService(private val connection: Connection) {

    @Throws(SQLException::class)
    fun findById(id: Long?): Optional<BoardEntity> {
        val dao = BoardDAO(connection)
        val boardColumnDAO = BoardColumnDAO(connection)
        val optional = dao.findById(id)
        if (optional.isPresent) {
            val entity = optional.get()
            entity.boardColumns = boardColumnDAO.findByBoardId(entity.id)
            return Optional.of(entity)
        }
        return Optional.empty()
    }

    @Throws(SQLException::class)
    fun showBoardDetails(id: Long?): Optional<BoardDetailsDTO> {
        val dao = BoardDAO(connection)
        val boardColumnDAO = BoardColumnDAO(connection)
        val optional = dao.findById(id)
        if (optional.isPresent) {
            val entity = optional.get()
            val columns = boardColumnDAO.findByBoardIdWithDetails(entity.id)
            val dto: BoardDetailsDTO = BoardDetailsDTO(entity.id!!, entity.name!!, columns)
            return Optional.of<BoardDetailsDTO>(dto)
        }
        return Optional.empty<BoardDetailsDTO>()
    }
}