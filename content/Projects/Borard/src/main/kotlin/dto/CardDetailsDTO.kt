package org.newTechDeveloper.dto

import java.time.OffsetDateTime



class CardDetailsDTO(
    val id: Long,
    val title: String,
    val description: String,
    val blocked: Boolean
    ,val blockedAt: OffsetDateTime,
    val blockReason: String,
    val blocksAmount: Int,
    val columnId: Long,
    val columnName: String)
