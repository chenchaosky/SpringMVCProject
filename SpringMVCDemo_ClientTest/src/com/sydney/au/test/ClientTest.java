package com.sydney.au.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.sydney.au.model.User;

//�ͻ��˲�����Ҫ������֤restTemplate�ĸ���get put exchange�����Ƿ��ܹ���������
public class ClientTest extends AbstractClientTest {

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        //ģ��һ��������
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testFindById() throws JsonProcessingException {
       
    	String uri = baseUri + "/{id}";
        Long id = 1L;
        User user = new User();
        user.setId(1L);
        user.setName("zhang");
        String userJson = objectMapper.writeValueAsString(user);
        String requestUri = UriComponentsBuilder.fromUriString(uri).buildAndExpand(id).toUriString();

        //��ӷ������˶���
        mockServer.expect(MockRestRequestMatchers.requestTo(requestUri))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(userJson, MediaType.APPLICATION_JSON));

        //����URI����API������
        ResponseEntity<User> entity = restTemplate.getForEntity(uri, User.class, id);

        //�ͻ�����֤
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertThat(entity.getHeaders().getContentType().toString(), containsString(MediaType.APPLICATION_JSON_VALUE));
        Assert.assertThat(entity.getBody(), hasProperty("name", is("zhang")));

        //����������֤����֤֮ǰ��ӵķ������˶��ԣ�
        mockServer.verify();
    }

    @Test
    public void testSaveWithJson() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("zhang");
        String userJson = objectMapper.writeValueAsString(user);

        String uri = baseUri;
        String createdLocation = baseUri + "/" + 1;
        
        mockServer.expect(MockRestRequestMatchers.requestTo(uri))  //��֤����URI
                .andExpect(MockRestRequestMatchers.jsonPath("$.name").value(user.getName())) //��֤�����JSON����
                .andRespond(MockRestResponseCreators.withCreatedEntity(URI.create(createdLocation)).body(userJson).contentType(MediaType.APPLICATION_JSON)); //�����Ӧ��Ϣ

        restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(new MappingJackson2HttpMessageConverter()));
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(uri, user, User.class);

        Assert.assertEquals(createdLocation, responseEntity.getHeaders().get("Location").get(0));
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals(user, responseEntity.getBody());

        mockServer.verify();
    }

    @Test
    public void testSaveWithXML() throws Exception {
       
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        
    	User user = new User();
        user.setId(1L);
        user.setName("zhang");   
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);  
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        jaxbMarshaller.marshal(user, new StreamResult(bos)); 
        
        String userXml = bos.toString();
        String uri = baseUri;
        String createdLocation = baseUri + "/" + 1;

        mockServer.expect(MockRestRequestMatchers.requestTo(uri))  //��֤����URI
                .andExpect(MockRestRequestMatchers.xpath("/user/name/text()").string(user.getName())) //��֤�����JSON����
                .andRespond(MockRestResponseCreators.withCreatedEntity(URI.create(createdLocation)).body(userXml).contentType(MediaType.APPLICATION_XML)); //�����Ӧ��Ϣ

        restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(new Jaxb2RootElementHttpMessageConverter()));
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(uri, user, User.class);

        Assert.assertEquals(createdLocation, responseEntity.getHeaders().get("Location").get(0));
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals(user, responseEntity.getBody());

        mockServer.verify();
    }

    
    //����restTemplate.exchange����ʱ��mockServer.expect(requestTo(uri))��uri����ʽ���������÷�������
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
    public void testUpdate() throws Exception {
        
    	User user = new User();
        user.setId(1L);
        user.setName("zhang");

        String uri = baseUri + "/{id}";
        Long id = 1L;
        String requestUri = UriComponentsBuilder.fromUriString(uri).buildAndExpand(id).toUriString();

        mockServer.expect(MockRestRequestMatchers.requestTo(requestUri))  //��֤����URI
//                .andExpect(jsonPath("$.name").value(user.getName())) //��֤�����JSON����
                .andRespond(MockRestResponseCreators.withNoContent()); //�����Ӧ��Ϣ

        restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(new MappingJackson2HttpMessageConverter()));
        ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(user), (Class) null, user.getId());

        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        mockServer.verify();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
	@Test
    public void testDelete() throws Exception {
        String uri = baseUri + "/{id}";
        Long id = 1L;

        mockServer.expect(MockRestRequestMatchers.requestTo(baseUri + "/" + id))  //��֤����URI
                .andRespond(MockRestResponseCreators.withSuccess()); //�����Ӧ��Ϣ

        ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, HttpEntity.EMPTY, (Class) null, id);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        mockServer.verify();
    }
}
