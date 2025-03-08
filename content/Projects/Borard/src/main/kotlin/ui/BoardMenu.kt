package org.newTechDeveloper.ui

import lombok.AllArgsConstructor
import org.newTechDeveloper.dto.BoardDetailsDTO
import org.newTechDeveloper.persistence.config.DbConectionConfig.Companion.getConnection
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.BoardEntity
import org.newTechDeveloper.persistence.entity.CardEntity
import org.newTechDeveloper.service.board.BoardQueryService
import org.newTechDeveloper.service.card.CardService
import java.sql.SQLException
import java.util.*


@AllArgsConstructor
class BoardMenu(private  val entity : BoardEntity) {

    private val scanner: Scanner = Scanner(System.`in`).useDelimiter("\n")
    fun execute() {
        try {
            System.out.printf("Bem vindo ao board %s, selecione a operação desejada\n", entity.id)
            var option = -1
            while (option != 9) {
                println("1 - Criar um card")
                println("2 - Mover um card")
                println("3 - Bloquear um card")
                println("4 - Desbloquear um card")
                println("5 - Cancelar um card")
                println("6 - Ver board")
                println("7 - Ver coluna com cards")
                println("8 - Ver card")
                println("9 - Voltar para o menu anterior um card")
                println("10 - Sair")
                option = scanner.nextInt()
                when (option) {
                    1 -> createCard()
                    2 -> moveCardToNextColumn()
                    3 -> blockCard()
                    4 -> unblockCard()
                    5 -> cancelCard()
                    6 -> showBoard()
                    7 -> showColumn()
                    8 -> showCard()
                    9 -> println("Voltando para o menu anterior")
                    10 -> System.exit(0)
                    else -> println("Opção inválida, informe uma opção do menu")
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            System.exit(0)
        }
    }

    private fun showBoard() {
        TODO("Not yet implemented")
    }

    private fun cancelCard() {
        TODO("Not yet implemented")
    }

    private fun unblockCard() {
        TODO("Not yet implemented")
    }

    private fun blockCard() {
        TODO("Not yet implemented")
    }

    private fun moveCardToNextColumn() {
        TODO("Not yet implemented")
    }

    private fun showColumn() {
        TODO("Not yet implemented")
    }

    private fun showCard() {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    private fun createCard() {
        val card = CardEntity(null,null,null,null)
        println("Informe o título do card")
        card.title = scanner.next()
        println("Informe a descrição do card")
        card.description = scanner.next()
        card.boardColumn = entity.getInitialColumn()
        getConnection().use { connection ->
            CardService(connection).create(card)
        }
    }

}