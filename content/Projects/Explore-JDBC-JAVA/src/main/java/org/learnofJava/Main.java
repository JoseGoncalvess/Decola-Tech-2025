package org.learnofJava;

import org.flywaydb.core.Flyway;
import org.learnofJava.domain.DAO.EmployeeAuditDAO;
import org.learnofJava.domain.DAO.EmployeeDAO;
import org.learnofJava.domain.entity.Employee;
import org.learnofJava.domain.entity.EmployeeAuditEntity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

import static java.lang.System.in;
import static org.learnofJava.config.ConnectionUltil.getConnection;

public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();
    private final static EmployeeAuditDAO employeeAudit = new EmployeeAuditDAO();
    public static void main(String[] args) {

        //TODO VERIFICO A CONECTION COM DATA BASE
        try ( var connction = getConnection()){
            System.out.println("Conectado ao MySQL com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO CONFIGURO O FLYWAY PARA CONECTAR E CARREGA AS MIGRATIONS
        var flaway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/db_JDBC","user","root")
                .locations("classpath:db/migration")
                .load();
        // TODO EXECUTO AS MIGRATIONS
//        flaway.repair();
        flaway.migrate();


        //TODO BUSADNO POR TODAS AS ENTITIDADES
//        List<Employee> employeeList =  employeeDAO.findAll();
//employeeList.forEach(employee -> System.out.println(employee.getName()));


        // CREATED NEW REGISTER
//        Employee employee = new Employee();
//        employee.setName("Julia");
//        employee.setSalary(BigDecimal.valueOf(2000));
//        employee.setBirthday(OffsetDateTime.now().minusYears(15));
//
//        employeeDAO.insert(employee);





        // TODO CONSULTANDO ENTITY POR ID
//        Employee employee =  employeeDAO.finsById(Long.valueOf(1));
//        System.out.println(employee.toString());


//
//        // TODO UPDATE EMPLOYER
//        Employee employee =  employeeDAO.finsById(Long.valueOf(1));
//        System.out.println(employee.toString());
//
//        employee.setName("Kelly Diniz");
//        employee.setSalary(BigDecimal.valueOf(1800));
//        employee.setBirthday(OffsetDateTime.now().minusYears(24));
//
//        employeeDAO.update(employee);
//         employee =  employeeDAO.finsById(Long.valueOf(1));
//        System.out.println(employee.toString());

//        // TODO DELETENDAOD REGISTRO
//        employeeDAO.delete(1);
//        employeeDAO.findAll().forEach(System.out::println);

       //  CREATED NEW REGISTER
        Employee employee = new Employee();
        employee.setName("Anny");
        employee.setSalary(BigDecimal.valueOf(15000));
        employee.setBirthday(OffsetDateTime.now().minusYears(20));

        employeeDAO.insert(employee);

        employeeAudit.getEntities().forEach(System.out::println);



    }




}