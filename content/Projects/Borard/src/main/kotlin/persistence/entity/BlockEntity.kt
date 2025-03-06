package org.newTechDeveloper.persistence.entity

import lombok.Data
import java.time.OffsetDateTime



@Data
class BlockEntity(
     val id: Long,
     val blockedAt: OffsetDateTime,
     val blockReason: String,
     val unblockedAt: OffsetDateTime,
     val unblockReason: String
) {}