package org.newTechDeveloper.persistence.entity

import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import org.newTechDeveloper.persistence.entity.BoardColumnEntity
import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum
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
        return getFilteredColumn(Predicate<BoardColumnEntity> { bc -> bc.kind!!.equals(BoardColumnKindEnum.INITIAL) })
    }

    fun getCancelColumn(): BoardColumnEntity {
        return getFilteredColumn(Predicate<BoardColumnEntity> { bc -> bc.kind!!.equals(BoardColumnKindEnum.CANCEL) })
    }

    private fun getFilteredColumn(filter: Predicate<BoardColumnEntity>): BoardColumnEntity {
        return boardColumns.stream()
            .filter(filter)
            .findFirst().orElseThrow()
    }

}