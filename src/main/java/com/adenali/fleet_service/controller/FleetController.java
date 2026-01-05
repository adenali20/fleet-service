package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Fleet;
import com.adenali.fleet_service.repository.FleetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequestMapping("/api/fleetservice")
@RestController
@RequiredArgsConstructor

public class FleetController {


    private final FleetRepository fleetRepository;


    @PostMapping("/owners/{ownerId}/fleets")
    public Fleet create(@PathVariable String ownerId, @RequestBody Fleet fleet) {
        fleet.setOwnerId(ownerId);
        return fleetRepository.save(fleet);
    }


    @GetMapping("/owners/{ownerId}/fleets")
    public List<Fleet> getByOwner(@PathVariable String ownerId) {
        return fleetRepository.findByOwnerId(ownerId);
    }


    @GetMapping("/fleets/{fleetId}")
    public Fleet getById(@PathVariable String fleetId) {
        return fleetRepository.findById(fleetId).orElseThrow();
    }


    @GetMapping("/user/get")
    public String get(Authentication authentication){
        String caller = (String) authentication.getPrincipal();
        log.info("caller is :  : "+caller+" for fleet service");
        return "caller is :  : "+caller+" for fleet service";
    }

    @GetMapping("/id")
    public String getId(){
        return "234";
    }
}

