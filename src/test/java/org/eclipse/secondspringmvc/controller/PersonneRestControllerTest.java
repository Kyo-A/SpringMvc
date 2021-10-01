package org.eclipse.secondspringmvc.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.eclipse.secondspringmvc.config.ApplicationConfig;
import org.eclipse.secondspringmvc.model.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class PersonneRestControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	
	@Test
	public void givenPersons_whenMockMVC_thenVerifyResponse() throws Exception {
		this.mockMvc
	      .perform(get("/personnes"))
	      .andDo(print()).andExpect(status().isOk());    
	}
	
	@Test
	public void givenPersonWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
		Integer id = 2;
	    this.mockMvc
	      .perform(get("/personnes/{id}", id).accept(MediaType.APPLICATION_JSON))
	      .andDo(print()).andExpect(status().isOk())
	      .andExpect(content().contentType("application/json"));
	}
	
	@Test
	public void savePerson_whenMockMVC_thenResponseOK() throws Exception {
		
	    this.mockMvc.perform(post("/personnes")
	      .contentType("application/json")
	      .content(asJsonString(new Personne("firstName4", "lastName4")))
	      .accept("application/json"))
	      .andExpect(status().isCreated());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void editPersonWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
		
		Integer id = 12;
		Personne p = new Personne("Test1", "test1");
		
		Gson gson = new Gson();
	    String json = gson.toJson(p);
		
	    this.mockMvc
	    		.perform(put("/personnes/{id}", id)
	    		.accept("application/json")
	            .contentType("application/json").content(json))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void deletePersonWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
		
		Integer id = 11;
		
	    this.mockMvc
	    		.perform(delete("/personnes/{id}", id)
	    		.accept(MediaType.APPLICATION_JSON)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	

}
