package com.ssafy.springtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.mysql.cj.Session;
import com.ssafy.springtest.model.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
	
	@Autowired
	MockMvc mock;	//was를 흉내내는 객체 (request, response 생성)
	
	@Autowired
	MemberService service;
	
	/*
	 * @Test public void loginTest() throws Exception { //given String userId =
	 * "ssafy"; String userPassword = "ssafy";
	 * 
	 * //when: post방식으로 해서 post(URI)임 request를 생성 MultiValueMap<String, String> map
	 * = new LinkedMultiValueMap<>(); map.add("userid", userId); map.add("pw",
	 * userPassword); MockHttpServletRequestBuilder builder =
	 * post("/user/login").params(map); ResultActions action =
	 * mock.perform(builder);
	 * 
	 * //then action.andExpect(status().is(302))
	 * .andExpect(handler().methodName("doLogin"))
	 * .andExpect(redirectedUrl("/regist"))
	 * .andExpect(request().sessionAttribute("loginUser", service.login(userId,
	 * userPassword))) .andDo(print()); }
	 * 
	 * @Test public void loginTest2() throws Exception { //given String userId =
	 * "ssafy"; String userPassword = "1234";
	 * 
	 * //when: post방식으로 해서 post(URI)임 request를 생성 MultiValueMap<String, String> map
	 * = new LinkedMultiValueMap<>(); map.add("userid", userId); map.add("pw",
	 * userPassword); MockHttpServletRequestBuilder builder =
	 * post("/user/login").params(map); ResultActions action =
	 * mock.perform(builder);
	 * 
	 * //then action.andExpect(status().is(200))
	 * .andExpect(handler().methodName("doLogin")) .andExpect(view().name("index"));
	 * }
	 */
	
}
