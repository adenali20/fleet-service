package com.adenali.fleet_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deviceservice")
public class DeviceController {

    @GetMapping("/devices")
    public  Map<String, Object> get(Authentication authentication){
        String caller = (String) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("owner", caller);
        map.put("deviceList",List.of("front camera","rear camera","door sensor"));

        return map;
    }


}
