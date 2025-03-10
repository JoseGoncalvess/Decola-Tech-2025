package org.newTechDeveloper.persistence.entity

import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import org.newTechDeveloper.persistence.config.utils.enums.BoardColumnKindEnum
import java.util.function.Predicate

@Data
class BoardEntity(
     var id: Long?,
     var name: String?,
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
     var boardColumns: List<BoardColumnEntity> = ArrayList()

) {


    fun getInitialColumn(): BoardColumnEntity {
        return getFilteredColumn(Predicate<BoardColumnEntity> { bc -> bc.kind!! == BoardColumnKindEnum.INITIAL })
    }

    fun getCancelColumn(): BoardColumnEntity {
        return getFilteredColumn(Predicate<BoardColumnEntity> { bc -> bc.kind!!.equals(BoardColumnKindEnum.CANCEL) })
    }

    private fun getFilteredColumn(filter: Predicate<BoardColumnEntity>): BoardColumnEntity {
        return boardColumns.stream()
            .filter(filter)
            .findFirst().orElseThrow()
    }

    override fun toString(): String {
        return "BoardEntity(id=$id, name=$name, boardColumns=$boardColumns)"
    }

}