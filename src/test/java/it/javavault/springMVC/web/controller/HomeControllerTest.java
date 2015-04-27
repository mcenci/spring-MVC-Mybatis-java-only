package it.javavault.springMVC.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import it.javavault.springMVC.config.DataConfig;
import it.javavault.springMVC.config.WebConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader=AnnotationConfigWebContextLoader.class, classes={DataConfig.class, WebConfig.class})
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetUsers() throws Exception{
		this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(print());
	}
	
	@Test
	public void testGetUserById() throws Exception{
		this.mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetUserDetailById() throws Exception{
		this.mockMvc.perform(get("/users/detail/1?username=user1&nickname=UserNickname1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void testUpdateUserById() throws Exception{
		this.mockMvc.perform(put("/users/update/2")
					.contentType(MediaType.TEXT_PLAIN)
					.contentType(MediaType.TEXT_HTML)
					.param("email", "newMail@user1.com"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$" , hasToString("UPDATE SUCCESS")));
	}
	
	@Test
	public void testUpdateUserDetailByIdNotNullEmail() throws Exception{
		this.mockMvc.perform(put("/users/update/detail/3")
					.contentType(MediaType.TEXT_PLAIN)
					.contentType(MediaType.TEXT_HTML)
					.param("nickname", "NEWUser3")
					.param("email", "newMail@user3.com"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$" , hasToString("UPDATE SUCCESS")));
	}

	@Test
	public void testUpdateUserDetailByIdNullEmail() throws Exception{
		this.mockMvc.perform(put("/users/update/detail/4")
					.contentType(MediaType.TEXT_PLAIN)
					.contentType(MediaType.TEXT_HTML)
					.param("nickname", "NewUser4"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$" , hasToString("UPDATE SUCCESS")));
	}


	@Test
	public void testDeleteUserById() throws Exception{
		this.mockMvc.perform(delete("/users/delete/7")
				.contentType(MediaType.TEXT_PLAIN)
				.contentType(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$" , hasToString("DELETE SUCCESS")));
	}	
}