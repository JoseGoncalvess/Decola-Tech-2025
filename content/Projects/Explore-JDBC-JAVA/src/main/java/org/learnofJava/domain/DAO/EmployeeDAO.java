package org.learnofJava.domain.DAO;

import com.mysql.cj.jdbc.StatementImpl;
import org.learnofJava.config.ConnectionUltil;
import org.learnofJava.domain.entity.Employee;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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

    }
    public void  delet(final Employee employee){

    }

    public List<Employee> findAll(){
        return  null;
    }

    public Employee  finsById(final Long id){
return  null;
    }

private String formaterDateOffSet(final OffsetDateTime dateTime){
        return  dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

}


}
