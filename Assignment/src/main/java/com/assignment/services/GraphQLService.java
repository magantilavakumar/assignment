/**
 * 
 */
package com.assignment.services;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.assignment.services.fetchers.AllCustomerTransactionFetcher;
import com.assignment.services.fetchers.CustomerAccountNoDataFetcher;
import com.assignment.services.fetchers.CustomerIDDataFetcher;
import com.assignment.services.fetchers.CustomerTrxDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * @author magantilavakumar
 *
 */

@Service
public class GraphQLService {

	@Value("classpath:customerTrx.graphql")
	Resource resource;
	
	private GraphQL graphQL;

	@Autowired
	private CustomerIDDataFetcher customerIDDataFectcher;

	@Autowired
	private CustomerAccountNoDataFetcher custAccountNoDataFectcher;

	@Autowired
	private CustomerTrxDataFetcher custTrxdescDataFectcher;
	
	@Autowired
	private AllCustomerTransactionFetcher allCustomerTrxFetcher;
	
	
	@PostConstruct
	private void loadSchema() throws IOException {
		//Get the Graph QL Schema
		File schemaFile = resource.getFile();
		
		//Parse the Schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring rTimeWiring = buildRuntimeWiring();
		GraphQLSchema gQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, rTimeWiring);
		graphQL = GraphQL.newGraphQL(gQLSchema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",typeWiring -> typeWiring
													.dataFetcher("customerID", customerIDDataFectcher)
													.dataFetcher("accountNo", custAccountNoDataFectcher)
													.dataFetcher("transactionDesc",custTrxdescDataFectcher)
													.dataFetcher("allCustomerTrx", allCustomerTrxFetcher)
													)
				.build();
													
	}

	/**
	 * @return the graphQL
	 */
	public GraphQL getGraphQL() {
		return graphQL;
	}

	/**
	 * @param graphQL the graphQL to set
	 */
	public void setGraphQL(GraphQL graphQL) {
		this.graphQL = graphQL;
	}

	
	
}
