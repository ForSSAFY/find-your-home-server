package com.ssafy.findyourhome.controller.deal;

import com.ssafy.findyourhome.dto.deal.DealReq;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.deal.HouseInfoRes;
import com.ssafy.findyourhome.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/deal")
@RequiredArgsConstructor
@Slf4j
public class DealController {

    private final DealService dealService;
    private final String NAME = "[DealController]";

    @GetMapping("/sido")
    public ResponseEntity<?> getSido() throws SQLException {
        List<String> result = dealService.getSidos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/gugun")
    public ResponseEntity<?> getGugun(@RequestParam String sido) throws SQLException {
        log.info("{} getGugun by sido({})", NAME, sido);
        List<String> result = dealService.getGuguns(sido);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/dong")
    public ResponseEntity<?> getDong(@RequestParam String sido, @RequestParam String gugun) throws SQLException {
        log.info("{} getDong by sido({}), gugun({})", NAME, sido, gugun);
        List<String> result = dealService.getDongs(sido, gugun);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /*@GetMapping
    public ResponseEntity<?> getDeal() throws SQLException {
        List<String> sidos = dealService.getSidos();
        List<Integer> years = dealService.getYears();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sidos", sidos);
        responseData.put("years", years);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseData);
    }*/

    @GetMapping
    public ResponseEntity<?> getDeals(@RequestBody DealReq req) throws SQLException {
        log.info("getDeal");
        List<HouseDealInfoDto> result = dealService.getHouseDeals(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/house")
    public ResponseEntity<?> getHouses(@RequestParam Double minLat, @RequestParam Double maxLat, @RequestParam Double minLng, @RequestParam Double maxLng) throws SQLException {
        log.info("getHouse");
        List<HouseInfoRes> result = dealService.getHouses(minLat, maxLat, minLng, maxLng);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
