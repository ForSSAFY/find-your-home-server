package com.ssafy.springtest.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MainApiController {

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ResponseEntity<String> index() {
		// 여기 비즈니스 로직 추가하거나 필요한 데이터 생성

		HttpHeaders headers = new HttpHeaders();
		// 필요하다면 헤더 설정
		log.debug("HttpHeaders: [{}]", headers);
		return new ResponseEntity<>("index", headers, HttpStatus.OK);
	}
	
}
