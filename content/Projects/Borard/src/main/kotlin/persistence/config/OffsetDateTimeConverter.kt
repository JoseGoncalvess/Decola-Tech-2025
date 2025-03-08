package org.newTechDeveloper.persistence.config

import lombok.NoArgsConstructor
import java.sql.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset.UTC
import java.util.Objects.nonNull
import lombok.AccessLevel.PRIVATE


@NoArgsConstructor(access = PRIVATE)
class OffsetDateTimeConverter {
    fun toOffsetDateTime(value: Timestamp): OffsetDateTime? {
        return if (nonNull(value)) OffsetDateTime.ofInstant(value.toInstant(), UTC) else null
    }

    fun toTimestamp(value: OffsetDateTime): Timestamp? {
        return if (nonNull(value)) Timestamp.valueOf(value.atZoneSameInstant(UTC).toLocalDateTime()) else null
    }
}