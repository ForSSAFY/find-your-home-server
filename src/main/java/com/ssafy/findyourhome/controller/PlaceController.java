package com.ssafy.findyourhome.controller;

import com.ssafy.findyourhome.dto.place.*;
import com.ssafy.findyourhome.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;
    private final String NAME = "[DealController]";

    @GetMapping("/sido")
    public ResponseEntity<?> getSido() throws SQLException {
        List<String> result = placeService.getSidos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/gugun")
    public ResponseEntity<?> getGugun(@RequestParam String sido) throws SQLException {
        log.info("{} getGugun by sido({})", NAME, sido);
        List<String> result = placeService.getGuguns(sido);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/dong")
    public ResponseEntity<?> getDong(@RequestParam String sido, @RequestParam String gugun) throws SQLException {
        log.info("{} getDong by sido({}), gugun({})", NAME, sido, gugun);
        List<String> result = placeService.getDongs(sido, gugun);
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
    public ResponseEntity<?> getDeals(@RequestBody PlaceReq req) throws SQLException {
        log.info("getDeal");
        List<HouseDealInfoDto> result = placeService.getHouseDeals(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/house")
    public ResponseEntity<?> getHouses(@RequestParam Double minLat, @RequestParam Double maxLat, @RequestParam Double minLng, @RequestParam Double maxLng) throws SQLException {
        log.info("getHouse");
        List<HouseInfoRes> result = placeService.getHouses(minLat, maxLat, minLng, maxLng);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/sidogun")
    public ResponseEntity<?> getSidoguns(@RequestParam Double minLat, @RequestParam Double maxLat, @RequestParam Double minLng, @RequestParam Double maxLng, @RequestParam Integer level) throws SQLException {
        log.info("getSidoguns");
        List<SidogunInfoRes> result = placeService.getSidogunInfos(minLat, maxLat, minLng, maxLng, level);

        Map<String, Object> response = new HashMap<>();
        response.put("level", nextLevel(level));
        response.put("result", result);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    private int nextLevel(int level) {
        if (level <= 6) {
            return 4;
        } else if (level <= 10) {
            return 6;
        } else {
            return 10;
        }
    }
}
