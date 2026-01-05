package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Device;
import com.adenali.fleet_service.domain.DeviceType;
import com.adenali.fleet_service.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deviceservice")
public class DeviceController {



    private final DeviceRepository deviceRepository;


    @PostMapping("/fleets/{fleetId}/devices")
    public Device register(@PathVariable String fleetId, @RequestBody Device device) {
        device.setFleetId(fleetId);
        return deviceRepository.save(device);
    }


    @GetMapping("/fleets/{fleetId}/devices")
    public List<Device> getByFleet(@PathVariable String fleetId) {
        return deviceRepository.findByFleetId(fleetId);
    }


    @GetMapping("/devices")
    public List<Device> getByType(@RequestParam DeviceType type) {
        return deviceRepository.findByType(type);
    }


    @PatchMapping("/devices/{deviceId}")
    public Device updateAttributes(@PathVariable String deviceId,
                                   @RequestBody Map<String, Object> updates) {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        device.getAttributes().putAll(updates);
        return deviceRepository.save(device);
    }


}
