package com.example.springbatchdemo.controller;

import com.example.springbatchdemo.entity.Customer;
import com.example.springbatchdemo.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by Satheesh Raju on 08-10-2022
 */

@RestController
@RequestMapping(value = "/customers")
@AllArgsConstructor
public class CustomersController {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final CustomerRepository customerRepository;

    @GetMapping(value = "/jobs/import")
    public String importCustomers() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
            return "Job run successful";
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            return "Job run failed :" + e.getMessage();
        }
    }

    @GetMapping(value = "/all")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/delete")
    public void deleteCustomers() {
        customerRepository.deleteAll();
    }


}
