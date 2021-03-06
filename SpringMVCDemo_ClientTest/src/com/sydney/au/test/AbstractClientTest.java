package com.sydney.au.test;

import org.junit.Before;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractClientTest {

    static RestTemplate restTemplate;
    ObjectMapper objectMapper;//JSON
    Jaxb2Marshaller marshaller;//XML
    String baseUri = "http://localhost:8080/users";

    @Before
    public void setUp() throws Exception {
    	
        objectMapper = new ObjectMapper(); //需要添加jackson jar包

        marshaller = new Jaxb2Marshaller(); //需要添加jaxb2实现（如xstream）
        marshaller.setPackagesToScan(new String[]{"com.sishuok"});
        marshaller.afterPropertiesSet();

        restTemplate = new RestTemplate();
    }
}
