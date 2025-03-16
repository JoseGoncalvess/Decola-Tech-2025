package org.learnofJava.domain.DAO;

import org.learnofJava.config.ConnectionUltil;
import org.learnofJava.domain.entity.Employee;
import org.learnofJava.domain.entity.EmployeeAuditEntity;
import org.learnofJava.utils.OperationsEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class EmployeeAuditDAO {


    public List<EmployeeAuditEntity> getEntities() {
        List<EmployeeAuditEntity> entities = new ArrayList<>();

        try(var connection = ConnectionUltil.getConnection();
            var statment = connection.createStatement();
        ) {
            // AQUI O SELECT E FEITO USADNO A VIEW
            statment.executeQuery("SELECT * FROM view_employee_audit");
            var results = statment.getResultSet();

            while (results.next()){

               entities.add(new EmployeeAuditEntity(
                       results.getLong("employee_id"),
                       results.getString("name"),
                       results.getString("old_name"),
                       results.getBigDecimal("salary"),
                       results.getBigDecimal("old_salary"),
                       getDateTimeOrNull(results,"birthday"),
                       getDateTimeOrNull(results,"old_birthday"),
                       OperationsEnum.getByDbOperation(results.getString("operation"))
               ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  entities;

    }

    public  OffsetDateTime getDateTimeOrNull(final ResultSet resultSet, String fieldKey) throws  SQLException{
        return  isNull(resultSet.getTimestamp(fieldKey))? null: OffsetDateTime.ofInstant(
                resultSet.getTimestamp(fieldKey)
                        .toInstant(),
                ZoneOffset.UTC);
    }



}
