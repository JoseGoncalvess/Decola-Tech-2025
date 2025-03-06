package org.newTechDeveloper.dto

import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum

data class BoardColumnDTO(
    var id: Long,
    var name: String,
    var kind : BoardColumnKindEnum,
    var cardsAmount : Int
) {

}