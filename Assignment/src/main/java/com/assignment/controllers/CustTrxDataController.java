package com.assignment.controllers;

/**
 * @author magantilavakumar
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.hibernate.engine.spi.ExecuteUpdateResultCheckStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.services.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class CustTrxDataController {
	
	private static final Logger log = LoggerFactory.getLogger(CustTrxDataController.class);
	@Autowired
	JobLauncher jobLaunch;
	
	@Autowired
	GraphQLService graphQLService;
	
	@Autowired
	Job jobToDo;
	
	@RequestMapping(value = "/loadCustData")
	public BatchStatus loadCustomerInfo() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> mapParam = new HashMap();
		mapParam.put("Time",new JobParameter(System.currentTimeMillis()));
		JobParameters jobParams = new JobParameters(mapParam);
		JobExecution jobExecute = jobLaunch.run(jobToDo, jobParams);
		log.info(jobExecute.getStatus().toString());
		return jobExecute.getStatus();
	}
	
	@RequestMapping(value = "/getCustomerId",method = RequestMethod.POST)
	public ResponseEntity<Object> getCustomerDetails(@RequestBody String query) {
		log.info("Query:---"+query);
		ExecutionResult executeResult =  graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(executeResult,HttpStatus.OK);
	}

}
