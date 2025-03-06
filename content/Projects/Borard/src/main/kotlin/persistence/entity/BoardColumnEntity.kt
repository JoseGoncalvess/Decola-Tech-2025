package org.newTechDeveloper.persistence.entity

import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum

@Data
class BoardColumnEntity(
    var id: Long?,
    var name: String?,
    var order : Int?,
    var kind: BoardColumnKindEnum?,
    var board: BoardEntity?,
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    var cards: ArrayList<CardEntity> = ArrayList()

) {




}