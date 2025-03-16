package org.learnofJava.domain.entity;

import org.learnofJava.utils.OperationsEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record EmployeeAuditEntity(
        long employeeId ,
        String name,
        String oldName,
        BigDecimal salary,
        BigDecimal oldSalary,
        OffsetDateTime birthday,
        OffsetDateTime oldBirthday,
        OperationsEnum operation
        ) {
}
