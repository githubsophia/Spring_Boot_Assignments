package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:flyway/migrations/V1.0_Jeep_Schema.sql",
		"classpath:flyway/migrations/V1.1_Jeep_Data.sql"},
config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied(){
		//Given a valid model, trim, and URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
		//When a connection is made to the URI
		ResponseEntity<Jeep> response = 
		getRestTemplate().getForEntity(uri, Jeep.class);
		
		//Then a success (OK - 200) status code is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		
		
		
	}

	private TestRestTemplate getRestTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getBaseUri() {
		// TODO Auto-generated method stub
		return null;
	}
	
	}