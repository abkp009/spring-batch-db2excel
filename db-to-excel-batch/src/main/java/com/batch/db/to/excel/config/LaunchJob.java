package com.batch.db.to.excel.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LaunchJob {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    public BatchStatus launch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        params.put("dest", new JobParameter("test.csv"));
        JobExecution run = jobLauncher.run(job, new JobParameters(params));
        while (run.isRunning()) {
            System.out.println("...");
        }
        return run.getStatus();
    }
}
