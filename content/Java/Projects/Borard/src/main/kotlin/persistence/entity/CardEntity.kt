package org.newTechDeveloper.persistence.entity

import lombok.Data


@Data
class CardEntity(
     var id: Long?,
     var title: String?,
     var description: String?,
     var boardColumn: BoardColumnEntity?,
) {}