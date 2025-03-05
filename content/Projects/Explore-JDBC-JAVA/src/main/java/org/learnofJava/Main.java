package org.learnofJava;

import net.datafaker.Faker;
import org.flywaydb.core.Flyway;
import org.learnofJava.domain.DAO.EmployeeAuditDAO;
import org.learnofJava.domain.DAO.EmployeeDAO;
import org.learnofJava.domain.DAO.EmployeeParamDAO;
import org.learnofJava.domain.entity.Employee;
import org.learnofJava.domain.entity.EmployeeAuditEntity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.lang.System.in;
import static org.learnofJava.config.ConnectionUltil.getConnection;

public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAudit = new EmployeeAuditDAO();
    private final static Faker fake = new Faker(Locale.of("pt","BR"));
    public static void main(String[] args) {

        //TODO VERIFICO A CONECTION COM DATA BASE
        try ( var connction = getConnection()){
            System.out.println("Conectado ao MySQL com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO CONFIGURO O FLYWAY PARA CONECTAR E CARREGA AS MIGRATIONS
        var flaway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/employees_db","user","root")
                .locations("classpath:db/migration")
                .load();
        // TODO EXECUTO AS MIGRATIONS
        flaway.repair();
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

//       //  CREATED NEW REGISTER
//        Employee employee = new Employee();
//        employee.setName("Anny");
//        employee.setSalary(BigDecimal.valueOf(15000));
//        employee.setBirthday(OffsetDateTime.now().minusYears(20));
//
//        employeeDAO.insertWhitProcedure(employee);
//
//        employeeAudit.getEntities().forEach(System.out::println);


        List<Employee> entites = Stream.generate(()->{
            var employe = new Employee();
            employe.setName(fake.name().fullName());
            employe.setSalary(new  BigDecimal(fake.number().digits(4)));
            employe.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(fake.number().numberBetween(40,20)), LocalTime.MIN, ZoneOffset.UTC));

            return  employe;
        }).limit(4000).toList();

        employeeDAO.insertBatch(entites);
    }




}