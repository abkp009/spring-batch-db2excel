package com.batch.db.to.excel;

import com.batch.db.to.excel.config.LaunchJob;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbToExcelBatchApplication {
    //	@Autowired
//	private static LaunchJob launchJob;
    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        SpringApplication.run(DbToExcelBatchApplication.class, args);
//		launchJob.launch();
    }

}
