package org.learnofJava.domain.DAO;

import com.mysql.cj.jdbc.StatementImpl;
import org.learnofJava.config.ConnectionUltil;
import org.learnofJava.domain.entity.Employee;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.TimeZone.LONG;

public class EmployeeParamDAO {

    // TODO DESSE MODOCONSEGUIMOS TARATR AS QUERYS EVITADNO OSSIVEIS ATACK E POR SQL INJECTION
    public void  insert(final Employee employee){

        try(var connection = ConnectionUltil.getConnection();
            // TODO DIFERENTE EM RELÇÂO AO EMPLOYEEDAOU
            var statment = connection.prepareStatement("INSERT INTO employees (name, salary, birthday) values(?,?,?)");
        ) {
            // TODO PREPARAMOS A QUERY E PASSAMOS O PARAMETOR DEPOIS
            statment.setString(1,employee.getName());
            statment.setBigDecimal(2,employee.getSalary());
            statment.setTimestamp(3,
                    Timestamp.valueOf(employee.getBirthday().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()));
            statment.executeUpdate(); // APOS ISSO BASTA EFETUAR UPDATE
            if (statment instanceof  StatementImpl impl) {
                employee.setId(impl.getLastInsertID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void  insertBatch(final List<Employee> employees){

        try(var connection = ConnectionUltil.getConnection();) {
            try(var statment = connection.prepareStatement("INSERT INTO employees (name, salary, birthday) values(?,?,?)");) {

                connection.setAutoCommit(false); // NÂO COMMITAR NO BANCO DE IMEDIATO, PRECISO USAR QUANDO QUERI CONTROLLAR AS ALTERAÇÃO NO DB
                for (int i =0; i < employees.size(); i ++){
                    statment.setString(1, employees.get(i).getName());
                    statment.setBigDecimal(2,employees.get(i).getSalary());
                    statment.setTimestamp(3,
                    Timestamp.valueOf(employees.get(i).getBirthday().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()));
                statment.addBatch(); // ADICONO A FINA PAR ASER INSERIDO
                if (i % 100 == 0 ||i == employees.size() -1) statment.executeBatch();// EXECULTO A FILA QU CIRIE
                }

                connection.commit(); // APOS TUDO ELE COMITA, ASIM A TARNSAÇAO É CONCLUIDA

            }catch (SQLException e) {
                connection.rollback(); // SE DER MERCA VOLTO A ANTES DE TUDO, (OU TUDO OU NADA)
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void  insertWhitProcedure(final Employee employee){

        try(var connection = ConnectionUltil.getConnection();
            var statment = connection.prepareCall(" call prc_insert_employee(?,?,?,?)");
        ) {
            statment.registerOutParameter(1,LONG);
            statment.setString(1,employee.getName());
            statment.setBigDecimal(2,employee.getSalary());
            statment.setTimestamp(3,
                    Timestamp.valueOf(employee.getBirthday().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()));
            statment.execute(); // APOS ISSO BASTA EFETUAR UPDATE
            employee.setId(statment.getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void  update(final Employee employee){
        try( var connection = ConnectionUltil.getConnection();
             var statment = connection.prepareStatement("UPDATE employees set name = ?, salary = ?,birthday = ? WHERE id = ? ");
        ) {
            statment.setString(1,employee.getName());
            statment.setBigDecimal(2,employee.getSalary());
            statment.setTimestamp(3,
                    Timestamp.valueOf(employee.getBirthday().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()));
            statment.setLong(4,employee.getId());

            statment.executeUpdate();
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
             var statment = connection.prepareStatement("DELETE FROM employees WHERE id = ? ");
        ) {
            statment.setLong(1,id);
            statment.executeUpdate();
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
             var statment = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
        ) {
            statment.setLong(1,id);
            statment.executeQuery();
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