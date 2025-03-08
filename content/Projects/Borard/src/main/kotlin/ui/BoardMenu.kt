package org.newTechDeveloper.ui

import lombok.AllArgsConstructor
import org.newTechDeveloper.dto.BoardColumnInfoDTO
import org.newTechDeveloper.dto.BoardDetailsDTO
import org.newTechDeveloper.persistence.config.DbConectionConfig.Companion.getConnection
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.BoardEntity
import org.newTechDeveloper.persistence.entity.CardEntity
import org.newTechDeveloper.service.board.BoardQueryService
import org.newTechDeveloper.service.card.CardQueryService
import org.newTechDeveloper.service.card.CardService
import java.sql.SQLException
import java.util.*
import java.util.function.Consumer


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

    @Throws(SQLException::class)
    private fun moveCardToNextColumn() {
        println("Informe o id do card que deseja mover para a próxima coluna")
        val cardId = scanner.nextLong()
        val boardColumnsInfo = entity.boardColumns.stream()
            .map<Any> { bc: BoardColumnEntity ->
                BoardColumnInfoDTO(
                    bc.id!!,
                    bc.order!!,
                    bc.kind!!
                )
            }
            .toList()
        try {
            getConnection().use { connection ->
                CardService(connection).moveToNextColumn(cardId, boardColumnsInfo)
            }
        } catch (ex: RuntimeException) {
            println(ex.message)
        }
    }

    @Throws(SQLException::class)
    private fun blockCard() {
        println("Informe o id do card que será bloqueado")
        val cardId = scanner.nextLong()
        println("Informe o motivo do bloqueio do card")
        val reason = scanner.next()
        val boardColumnsInfo = entity.boardColumns.stream()
            .map<Any> { bc: BoardColumnEntity ->
                BoardColumnInfoDTO(
                    bc.id!!,
                    bc.order!!,
                    bc.kind!!
                )
            }
            .toList()
        try {
            getConnection().use { connection ->
                CardService(connection).block(cardId, reason, boardColumnsInfo)
            }
        } catch (ex: RuntimeException) {
            println(ex.message)
        }
    }

    @Throws(SQLException::class)
    private fun unblockCard() {
        println("Informe o id do card que será desbloqueado")
        val cardId = scanner.nextLong()
        println("Informe o motivo do desbloqueio do card")
        val reason = scanner.next()
        try {
            getConnection().use { connection ->
                CardService(connection).unblock(cardId, reason)
            }
        } catch (ex: RuntimeException) {
            println(ex.message)
        }
    }

    @Throws(SQLException::class)
    private fun cancelCard() {
        println("Informe o id do card que deseja mover para a coluna de cancelamento")
        val cardId = scanner.nextLong()
        val cancelColumn = entity.getCancelColumn()
        val boardColumnsInfo = entity.boardColumns.stream()
            .map<BoardColumnInfoDTO> { bc: BoardColumnEntity ->
                BoardColumnInfoDTO(
                    bc.id!!,
                    bc.order!!,
                    bc.kind!!
                )
            }
            .toList()
        try {
            getConnection().use { connection ->
                CardService(connection).cancel(cardId, cancelColumn.id, boardColumnsInfo)
            }
        } catch (ex: RuntimeException) {
            println(ex.message)
        }
    }

    @Throws(SQLException::class)
    private fun showBoard() {
        getConnection().use { connection ->
            val optional = BoardQueryService(connection).showBoardDetails(entity.id)
            optional.ifPresent { b: BoardDetailsDTO ->
                System.out.printf("Board [${b.id},${b.name}]\n")
                b.columns.forEach { c ->
                    System.out.printf(
                        "Coluna [${c.name}] tipo: [${c.kind}] tem ${c.cardsAmount} cards\n",
                        ,

                    )
                }
            }
        }
    }

    @Throws(SQLException::class)
    private fun showColumn() {
        val columnsIds = entity.boardColumns.stream().map(BoardColumnEntity::id).toList()
        var selectedColumnId = -1L
        while (!columnsIds.contains(selectedColumnId)) {
            System.out.printf("Escolha uma coluna do board %s pelo id\n", entity.name)
            entity.boardColumns.forEach(Consumer { c: BoardColumnEntity ->
                System.out.printf(
                    "%s - %s [%s]\n",
                    c.id,
                    c.name,
                    c.kind
                )
            })
            selectedColumnId = scanner.nextLong()
        }
        getConnection().use { connection ->
            val column: Unit = BoardColumnQueryService(connection).findById(selectedColumnId)
            column.ifPresent { co ->
                System.out.printf("Coluna %s tipo %s\n", co.getName, co.getKind)
                co.getCards().forEach { ca ->
                    System.out.printf(
                        "Card %s - %s\nDescrição: %s",
                        ca.getId(), ca.getTitle, ca.getDescription
                    )
                }
            }
        }
    }

    @Throws(SQLException::class)
    private fun showCard() {
        println("Informe o id do card que deseja visualizar")
        val selectedCardId = scanner.nextLong()
        getConnection().use { connection ->
            CardQueryService(connection).findById(selectedCardId)
                .ifPresentOrElse(
                    { cd ->
                        System.out.printf("Card %s - %s.\n", cd.id, cd.title)
                        System.out.printf("Descrição: %s\n", cd.description)
                        System.out.println(if (cd.blocked) "Está bloqueado. Motivo: " + cd.blockReason else "Não está bloqueado")
                        System.out.printf("Já foi bloqueado %s vezes\n", cd.blocksAmount)
                        System.out.printf("Está no momento na coluna %s - %s\n", cd.columnId, cd.columnName)
                    },
                    { System.out.printf("Não existe um card com o id %s\n", selectedCardId) })
        }
    }

}