package org.learnofJava;

import org.flywaydb.core.Flyway;
import org.learnofJava.domain.DAO.EmployeeDAO;
import org.learnofJava.domain.entity.Employee;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import static org.learnofJava.config.ConnectionUltil.getConnection;

public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();
    public static void main(String[] args) {

        // VERIFICO A CONECTION COM DATA BASE
        try ( var connction = getConnection()){
            System.out.println("Conectado ao MySQL com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // CONFIGURO O FLYWAY PARA CONECTAR E CARREGA AS MIGRATIONS
        var flaway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/db_JDBC","user","root")
                .locations("classpath:db/migration")
                .load();
        // EXECUTO AS MIGRATIONS
        flaway.migrate();

        var employee = new  Employee();
        employee.setName("Maria");
        employee.setSalary(BigDecimal.valueOf(1780));
        employee.setBirthday(OffsetDateTime.now().minusYears(24));

        employeeDAO.insert(employee);



    }




}