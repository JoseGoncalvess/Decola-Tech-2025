package org.newTechDeveloper.dto

import org.newTechDeveloper.persistence.entity.ultils.BoardColumnKindEnum



class BoardColumnInfoDTO(
    val id: Long,
    val order: Int,
    val kind: BoardColumnKindEnum
)
