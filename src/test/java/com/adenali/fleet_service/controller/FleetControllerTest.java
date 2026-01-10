package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Fleet;
import com.adenali.fleet_service.model.FleetConnection;
import com.adenali.fleet_service.service.FleetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(FleetController.class)
public class FleetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FleetService fleetService;

    @Test
    void shouldReturnFleets() throws Exception {

        when(fleetService.findAll()).thenReturn(List.of(Fleet.builder().name("FL1").build()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/fleetservice/fleet");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("FL1"));
    }

    @Test
    void shouldFetchFleetsWithPagination() throws Exception {
        when(fleetService.fleetsWithPagination("after", 1)).thenReturn(FleetConnection.builder().build());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/fleetservice/fleet/after/1234/size/5");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldThrow400onFetchFleetsWithPagination() throws Exception {
        when(fleetService.fleetsWithPagination("after", 1)).thenReturn(FleetConnection.builder().build());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/fleetservice/fleet/after/1234/size/1e");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
