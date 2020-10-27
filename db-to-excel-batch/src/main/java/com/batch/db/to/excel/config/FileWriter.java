package com.batch.db.to.excel.config;

import com.batch.db.to.excel.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.List;
public class FileWriter{}
//@Component
//public class FileWriter implements ItemWriter<Employee> {
//
//    @Override
//    public void write(List<? extends Employee> list) throws Exception {
//        System.out.println("writer....");
//        final StringBuilder sb = new StringBuilder();
//        FileOutputStream outputStream = new FileOutputStream("flat-file1.csv");
//        list.forEach(employee -> {
//            sb.append(employee.toString()).append("\n");
//        });
//        outputStream.write(sb.toString().getBytes());
//        outputStream.close();
//    }
//}
