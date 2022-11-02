package com.example.springbatchdemo.config;

import com.example.springbatchdemo.batch.processors.CustomerProcessor;
import com.example.springbatchdemo.entity.Customer;
import com.example.springbatchdemo.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * created by Satheesh Raju on 08-10-2022
 */

@Configuration
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final CustomerRepository customerRepository;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setName("Customer");
        reader.setStrict(false);
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("customers.csv"));
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setNames("first_name", "last_name", "company_name", "address", "city", "county", "state", "zip", "phone1", "phone2", "email", "web");

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(tokenizer);

        return lineMapper;
    }

    @Bean
    public CustomerProcessor customerProcessor() {
        return new CustomerProcessor();
    }

    @Bean
    public RepositoryItemWriter<Customer> itemWriter() {
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(customerRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-to-customers-step")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(customerProcessor())
                .writer(itemWriter())
                .taskExecutor(asyncExecutor())
                .build();
    }

    @Bean
    public Job customerJob() {
        return jobBuilderFactory.get("customers-job")
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public TaskExecutor asyncExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }

}
