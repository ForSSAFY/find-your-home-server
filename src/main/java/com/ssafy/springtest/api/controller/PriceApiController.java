package com.ssafy.springtest.api.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.springtest.model.dto.HouseDealDto;
import com.ssafy.springtest.model.service.PriceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@Slf4j
public class PriceApiController {
	
	private final PriceService priceService;
	private final ObjectMapper objectMapper;
	
    @GetMapping
    public ResponseEntity<String> getPrice() throws SQLException, JsonProcessingException {
        List<String> sidos = priceService.getSidos();
        List<Integer> years = priceService.getYears();

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sidos", sidos);
        responseData.put("years", years);
        String jsonResponse = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(jsonResponse);
    }
    
    @GetMapping("/sido")
    public String getSido() throws SQLException, JsonProcessingException {
        List<String> result = priceService.getSidos();
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @GetMapping("/gugun")
    public String getGugun(@RequestParam String sido) throws SQLException, JsonProcessingException {
        log.info("[getGugun] sido: {}", sido);
        List<String> result = priceService.getGuguns(sido);
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @GetMapping("/dong")
    public String getDong(@RequestParam String sido, @RequestParam String gugun) throws SQLException, JsonProcessingException {
    	log.info("[getDong] sido: {} gugun : {}", sido, gugun);
        List<String> result = priceService.getDongs(sido, gugun);
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @GetMapping("/deal")
    public String getDeal(@RequestParam String sido, @RequestParam String gugun, @RequestParam String dong, @RequestParam String year, @RequestParam String month) throws SQLException, JsonProcessingException {
        int dealYear;
        int dealMonth;
        try {
            dealYear = Integer.parseInt(year);
            dealMonth = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            System.out.println("year month 잘못됨.");
            return "[]";
        }
        List<HouseDealDto> result = priceService.getHouseDeals(sido, gugun, dong, dealYear, dealMonth);
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
}
