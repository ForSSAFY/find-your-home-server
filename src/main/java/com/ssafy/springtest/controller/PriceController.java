package com.ssafy.springtest.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.springtest.model.dto.HouseDealDto;
import com.ssafy.springtest.model.service.PriceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/price")
@RequiredArgsConstructor
@Slf4j
public class PriceController {
	
	private final PriceService priceService;
	private final ObjectMapper objectMapper;
	
    @GetMapping
    public String getPrice(HttpSession session) throws ServletException, IOException, SQLException {
        session.setAttribute("sidoList", priceService.getSidos());
        session.setAttribute("years", priceService.getYears());
        return "price/apt";
    }
    
    @ResponseBody
    @GetMapping("/sido")
    public String getSido() throws SQLException, JsonProcessingException {
        List<String> result = priceService.getSidos();
        // TODO parse to json and return
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @ResponseBody
    @GetMapping("/gugun")
    public String getGugun(@RequestParam String sido) throws SQLException, JsonProcessingException {
        log.info("[getGugun] sido: {}", sido);
        List<String> result = priceService.getGuguns(sido);
        // TODO parse to json and return
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @ResponseBody
    @GetMapping("/dong")
    public String getDong(@RequestParam String sido, @RequestParam String gugun) throws SQLException, JsonProcessingException {
    	log.info("[getDong] sido: {} gugun : {}", sido, gugun);
        List<String> result = priceService.getDongs(sido, gugun);
        // TODO parse to json and return
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
    
    @ResponseBody
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
        // TODO parse to json and return
        
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
}
