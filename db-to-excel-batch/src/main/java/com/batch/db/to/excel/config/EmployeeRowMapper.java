package com.batch.db.to.excel.config;

import com.batch.db.to.excel.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements org.springframework.jdbc.core.RowMapper<com.batch.db.to.excel.Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Employee(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDate("dob"));
    }
}
