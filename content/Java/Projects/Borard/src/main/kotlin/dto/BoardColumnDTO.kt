package org.newTechDeveloper.dto

import org.newTechDeveloper.persistence.config.utils.enums.BoardColumnKindEnum

data class BoardColumnDTO(
    var id: Long,
    var name: String,
    var kind : BoardColumnKindEnum,
    var cardsAmount : Int
) {

}