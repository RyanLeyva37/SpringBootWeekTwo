package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.promineotech.jeep.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
		"classpath:flyway/migrations/V1.0__Jeep_Schema.sql"},
		config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest extends FetchJeepTestSupport{
	

  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    
//    JeepModel model = JeepModel.WRANGLER;
//    String trim = "Sport";
//    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//    
//    ResponseEntity<Jeep> response = super.getRestTemplate().getForEntity(uri, Jeep.class);
//    
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	  
	  
    // Given: a valid model, trim and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
    
    // When: a connection is made to the URI
    @SuppressWarnings("unchecked")
	ResponseEntity<List<Jeep>> response = super.getRestTemplate().exchange(uri, HttpMethod.GET, null, 
    			new ParameterizedTypeReference<>() {});
    // Then: a successs (200) status code is returned
    
    //System.out.println(getBaseUri());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    // And: the actual list returned is the same as the expected list
    List<Jeep> expected = buildExpected();
    assertThat(response.getBody()).isEqualTo(expected);
    System.out.println("OUTPUT: " + response.getBody());
  }



}
