package com.ssafy.findyourhome.controller.deal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.service.deal.DealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/deal")
@RequiredArgsConstructor
@Slf4j
public class DealController {

    private final DealService dealService;
    private final ObjectMapper objectMapper;
    private final String NAME = "[DealController]";

    @GetMapping
    public ResponseEntity<String> getDeal() throws SQLException, JsonProcessingException {
        List<String> sidos = dealService.getSidos();
        List<Integer> years = dealService.getYears();

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sidos", sidos);
        responseData.put("years", years);
        String jsonResponse = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/sido")
    public String getSido() throws SQLException, JsonProcessingException {
        List<String> result = dealService.getSidos();
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }

    @GetMapping("/gugun")
    public String getGugun(@RequestParam String sido) throws JsonProcessingException, SQLException {
        log.info("{} getGugun by sido({})", NAME, sido);
        List<String> result = dealService.getGuguns(sido);
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }

    @GetMapping("/dong")
    public String getDong(@RequestParam String sido, @RequestParam String gugun) throws JsonProcessingException, SQLException {
        log.info("{} getDong by sido({}), gugun({})", NAME, sido, gugun);
        List<String> result = dealService.getDongs(sido, gugun);
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
            e.printStackTrace();
            return "[]";
        }
        List<HouseDealInfoDto> result = dealService.getHouseDeals(sido, gugun, dong, dealYear, dealMonth);
        String jsonResult = objectMapper.writeValueAsString(result);
        log.info("jsonResult: {}", jsonResult);
        return jsonResult;
    }
}
