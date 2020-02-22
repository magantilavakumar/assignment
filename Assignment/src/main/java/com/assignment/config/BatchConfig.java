package com.assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assignment.batch.components.CustomerTrxFieldMapper;
import com.assignment.batch.entity.CustomerTransaction;
import com.assignment.common.Constants;

@Configuration
@EnableBatchProcessing

public class BatchConfig {
 
	private static final Logger lob = LoggerFactory.getLogger(BatchConfig.class);
	@Bean
	public Job  getJob(JobBuilderFactory jBF,StepBuilderFactory sBF,ItemReader<CustomerTransaction> itemReader,
			ItemProcessor<CustomerTransaction,CustomerTransaction> itemProcessor,ItemWriter<CustomerTransaction> itemWriter) {
		Step step = sBF.get("Step_UploadCustomerTranInfo")
				.<CustomerTransaction,CustomerTransaction>chunk(20)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		Job job = jBF.get("Job_CustomerTran")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
		return job;
	}
	
	@Bean
	public FlatFileItemReader<CustomerTransaction> itemReader(@Value("${input.custDataPath}") String filePath){
		lob.info(""+filePath);
		FlatFileItemReader<CustomerTransaction> fFItemReader = new FlatFileItemReader<CustomerTransaction>();
		fFItemReader.setResource(new FileSystemResource(filePath));	
		fFItemReader.setName("Customer Tranx Info Reader");
		fFItemReader.setLinesToSkip(1);
		fFItemReader.setLineMapper(getLineMapper());
		return fFItemReader;
	}
	
	@Bean
	public LineMapper<CustomerTransaction> getLineMapper(){
		DefaultLineMapper<CustomerTransaction> dLineMapper = new DefaultLineMapper<CustomerTransaction>();
		DelimitedLineTokenizer dLinetokenizer = new DelimitedLineTokenizer();
		dLinetokenizer.setDelimiter(Constants.SYMBOLS.PIPE);
		dLinetokenizer.setStrict(false);
		dLinetokenizer.setNames(new String[] {"accountNo","transactionAmount","transactionDesc","transactionDate","transactionTime","customerID"});	
		dLineMapper.setLineTokenizer(dLinetokenizer);
			
		dLineMapper.setFieldSetMapper(new CustomerTrxFieldMapper());
		return dLineMapper;
	}
}
