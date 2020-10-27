package com.batch.db.to.excel.config;

import com.batch.db.to.excel.Employee;
import com.batch.db.to.excel.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Bean
    public Employee save() {
        Employee e = new Employee();
        e.setDob(new Date());
        e.setName("Abhishek");
        Employee e2 = new Employee();
        e2.setDob(new Date());
        e2.setName("Pandey");
        employeeRepository.save(e);
        return employeeRepository.save(e2);
    }

    @Bean
    public JdbcCursorItemReader<Employee> reader() {
        JdbcCursorItemReader<Employee> read = new JdbcCursorItemReader<>();
        read.setDataSource(dataSource);
        read.setSql("SELECT id,name,dob from EMPLOYEE");
        read.setRowMapper(new EmployeeRowMapper());
        return read;
    }

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        FlatFileItemWriter<Employee> w = new FlatFileItemWriter<>();
        w.setResource(new ClassPathResource("all.csv"));
        w.setHeaderCallback(writer -> writer.write("ID, NAME, DOB"));

        DelimitedLineAggregator<Employee> d = new DelimitedLineAggregator<>();
        d.setDelimiter(",");

        BeanWrapperFieldExtractor<Employee> be = new BeanWrapperFieldExtractor<>();
        be.setNames(new String[]{"id", "name", "dob"});
        d.setFieldExtractor(be);

        w.setLineAggregator(d);

        return w;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("STEP")
                .<Employee, Employee>chunk(100)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("JOB")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

}
