package com.ssafy.findyourhome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.findyourhome.dao.PlaceDao;
import com.ssafy.findyourhome.dto.place.HouseInfoRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PlaceDao placeDao;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//        houseDealDao.deleteAll();
    }

    @DisplayName("getHouses")
    @Test
    public void getHouses() throws Exception {
        // given
        final String url = "/api/deal/house";
        final Double minLat = 37.49;
        final Double maxLat = 37.50;
        final Double minLng = 127.03;
        final Double maxLng = 127.04;

        // when
        ResultActions result = mockMvc.perform(get(url)
                .param("minLat", minLat.toString())
                .param("maxLat", maxLat.toString())
                .param("minLng", minLng.toString())
                .param("maxLng", maxLng.toString())
        );

        // then
        result.andExpect(status().isOk());
        List<HouseInfoRes> houseList = placeDao.findAllHouseByCoordinate(minLat, maxLat, minLng, maxLng);
        assertThat(houseList.size()).isGreaterThan(0);
    }
}