package org.newTechDeveloper.persistence.config.utils

import lombok.NoArgsConstructor
import java.sql.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset.UTC
import java.util.Objects.nonNull
import lombok.AccessLevel.PRIVATE
import java.time.ZoneOffset


@NoArgsConstructor(access = PRIVATE)
class OffsetDateTimeConverter {
    companion object{
        fun toOffsetDateTime(value: Timestamp?): OffsetDateTime? {
             return value?.toInstant()?.atOffset(ZoneOffset.UTC)
        }

        fun toTimestamp(value: OffsetDateTime): Timestamp? {
            return if (nonNull(value)) Timestamp.valueOf(value.atZoneSameInstant(UTC).toLocalDateTime()) else null
        }
    }
}