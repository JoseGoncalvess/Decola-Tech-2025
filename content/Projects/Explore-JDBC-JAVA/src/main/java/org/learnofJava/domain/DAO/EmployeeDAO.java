package org.learnofJava.domain.DAO;

import com.mysql.cj.jdbc.StatementImpl;
import org.learnofJava.config.ConnectionUltil;
import org.learnofJava.domain.entity.Employee;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void  insert(final Employee employee){

        try( var connection = ConnectionUltil.getConnection();
            var statment = connection.createStatement();
        ) {
            statment.executeUpdate("INSERT INTO employees (name, salary, birthday) values('"+employee.getName()+"', "+employee.getSalary().toString()+", '"+employee.getBirthday()+"')");
            System.out.println(statment.getUpdateCount());
            if (statment instanceof  StatementImpl impl) {
                employee.setId(impl.getLastInsertID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void  update(final Employee employee){
        try( var connection = ConnectionUltil.getConnection();
             var statment = connection.createStatement();
        ) {
            statment.executeUpdate("UPDATE employees set" +
                    " name = '"+employee.getName()+"'," +
                    " salary = '"+employee.getSalary().toString()+"'," +
                    " birthday = '"+employee.getBirthday()+"'" +
                    " WHERE id = '"+employee.getId()+"'");
            System.out.println("O total de registros afetos foram: "+ statment.getUpdateCount());
            if (statment instanceof  StatementImpl impl) {
                employee.setId(impl.getLastInsertID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void  delete(final long id){
        try( var connection = ConnectionUltil.getConnection();
             var statment = connection.createStatement();
        ) {
            statment.executeUpdate("DELETE FROM employees WHERE id = "+id);
            System.out.println(statment.getUpdateCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> findAll(){
        List<Employee> employeeList = new ArrayList<>(List.of());
        try( var connection = ConnectionUltil.getConnection();
             var statment = connection.createStatement();
        ) {
      statment.executeQuery("SELECT * FROM employees");
      var results = statment.getResultSet();

          while (results.next()){
              var entity = new Employee();
              entity.setName(results.getString("name"));
              entity.setSalary(results.getBigDecimal("salary"));
              entity.setId(results.getLong("id"));
              var birthdayStatment = results.getTimestamp("birthday").toInstant();
              entity.setBirthday(OffsetDateTime.ofInstant(birthdayStatment, ZoneOffset.UTC));

              employeeList.add(entity);
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  employeeList;
    }

    public Employee  finsById(final Long id){

        Employee employee = new Employee();
        try( var connection = ConnectionUltil.getConnection();
             var statment = connection.createStatement();
        ) {
            statment.executeQuery("SELECT * FROM employees WHERE id = "+id);
            var results = statment.getResultSet();

            if (results.next()){
                employee.setName(results.getString("name"));
                employee.setSalary(results.getBigDecimal("salary"));
                employee.setId(results.getLong("id"));
                var birthdayStatment = results.getTimestamp("birthday").toInstant();
                employee.setBirthday(OffsetDateTime.ofInstant(birthdayStatment, ZoneOffset.UTC));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  employee;
    }

private String formaterDateOffSet(final OffsetDateTime dateTime){
        // TRASNFOMA A DATA  PADRONIZAOD AS ZONA DE HORÁRIO
        var utcDateTime = dateTime.withOffsetSameInstant(ZoneOffset.UTC);
        return  utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

}


}
