package org.newTechDeveloper.ui

import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.config.DbConectionConfig.Companion.getConnection
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.BoardEntity
import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum
import org.newTechDeveloper.service.board.BoardQueryService
import org.newTechDeveloper.service.board.BoardService
import java.sql.SQLException
import java.util.*
import javax.print.attribute.standard.JobState.PENDING


@AllArgsConstructor
class MainMenu {
    private val scanner: Scanner = Scanner(System.`in`).useDelimiter("\n")

    @Throws(SQLException::class)
    fun execute() {
        println("====== Bem vindo ao gerenciador de boards ======")
        println("O que deseja fazer ?")
        var option = -1
        while (true) {
            println("1 - Criar um novo board")
            println("2 - Selecionar um board existente")
            println("3 - Excluir um board")
            println("4 - Sair")
            option = scanner.nextInt()
            when (option) {
                1 -> createBoard()
                2 -> selectBoard()
                3 -> deleteBoard()
                4 -> System.exit(0)
                else -> println("Opção inválida, informe uma opção do menu!")
            }
        }
    }
    @Throws(SQLException::class)
    private fun createBoard() {
        val entity = BoardEntity(null, null,)
        println("Informe o nome do seu board")
        entity.name = scanner.next()

        println("Seu Board terá Colunas além das 3 Padrões?\n Se Sim Informe quantas, senão digite '0'")
        val additionalColumns = scanner.nextInt()

        val columns: MutableList<BoardColumnEntity> = ArrayList()

        println("Informe o nome da Coluna INICIAL do Board")
        val initialColumnName = scanner.next()
        val initialColumn = createColumn(initialColumnName, BoardColumnKindEnum.INITIAL, 0)
        columns.add(initialColumn)

        // TODO VERIFICAR POSSIVEL ERRO DE EXCULÇÃO
        for (i in 0 until additionalColumns) {
            println("Informe o nome da coluna de tarefa PENDENTE do ")
            val pendingColumnName = scanner.next()
            val pendingColumn = createColumn(pendingColumnName, BoardColumnKindEnum.PENDING, i + 1)
            columns.add(pendingColumn)
        }

        println("Informe o nome da coluna FINAL do Board")
        val finalColumnName = scanner.next()
        val finalColumn = createColumn(finalColumnName, BoardColumnKindEnum.FINAL, additionalColumns + 1)
        columns.add(finalColumn)

        println("Informe o nome da coluna de CANCELAMNETO do Board")
        val cancelColumnName = scanner.next()
        val cancelColumn = createColumn(cancelColumnName, BoardColumnKindEnum.CANCEL, additionalColumns + 2)
        columns.add(cancelColumn)

        entity.boardColumns = columns
        getConnection().use { connection -> BoardService(connection).insert(entity =  entity) }
    }

    @Throws(SQLException::class)
    private fun selectBoard() {
        println("Informe o id do board que deseja selecionar")
        val id = scanner.nextLong()
        getConnection().use { connection ->
            val queryService = BoardQueryService(connection)
            val optional = queryService.findById(id)
            optional.ifPresentOrElse(
                { b: BoardEntity -> BoardMenu(b).execute() },
                { System.out.printf("Não foi encontrado um board com id %s\n", id) }
            )
        }
    }

    @Throws(SQLException::class)
    private fun deleteBoard() {
        println("Informe o id do board que Deseja  Excluir")
        val id = scanner.nextLong()
        getConnection().use { connection ->
            val service = BoardService(connection)
            if (service.delete(id)) {
                System.out.printf("O Board %s Foi Excluido\n", id)
            } else {
                System.out.printf("Não Foi Encontrado um Board com id %s\n", id)
            }
        }
    }

    private fun createColumn(name: String, kind: BoardColumnKindEnum, order: Int): BoardColumnEntity {
        return  BoardColumnEntity(null, name = name,order = order, kind = kind,null)
    }



}