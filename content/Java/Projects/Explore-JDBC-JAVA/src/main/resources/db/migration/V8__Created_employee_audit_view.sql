-- TRATA_SE DE UAM QUERY QUE DEIXO ARMAZENADA PARA USAR SMEPRE QUE CHAMR A VIEW
CREATE VIEW view_employee_audit AS
       SELECT employee_id,
              name,
              old_name,
              salary,
              old_salary,
              birthday,
              old_birthday,
              operation
        FROM employees_audit
      ORDER BY create_at;