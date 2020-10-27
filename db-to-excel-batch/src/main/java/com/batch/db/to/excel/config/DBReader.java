package com.batch.db.to.excel.config;

import com.batch.db.to.excel.Employee;
import com.batch.db.to.excel.EmployeeRepository;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public class DBReader{}
////@Component
//public class DBReader implements ItemReader<Employee> {
////    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Override
//    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        System.out.println("reader......");
//        List<Employee> employees = employeeRepository.findAll();
//        if (null != employees && !employees.isEmpty()) {
//            return employees.get(0);
//        } else {
//            return new Employee(-1, "test", new Date());
//        }
//    }
//}
