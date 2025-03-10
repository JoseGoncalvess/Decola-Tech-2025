package org.newTechDeveloper.dto

import org.newTechDeveloper.persistence.config.utils.enums.BoardColumnKindEnum



class BoardColumnInfoDTO(
    val id: Long,
    val order: Int,
    val kind: BoardColumnKindEnum
)
