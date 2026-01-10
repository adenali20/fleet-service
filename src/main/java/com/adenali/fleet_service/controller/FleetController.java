package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Device;
import com.adenali.fleet_service.domain.Fleet;
import com.adenali.fleet_service.domain.Owner;
import com.adenali.fleet_service.model.*;
import com.adenali.fleet_service.repository.DeviceRepository;
import com.adenali.fleet_service.repository.FleetRepository;
import com.adenali.fleet_service.service.FleetService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fleetservice")
public class FleetController {

    private final FleetService fleetService;


    @GetMapping("/fleet")
    public List<Fleet> fleets() {
        return fleetService.findAll();
    }

    @GetMapping("/fleet/after/{after}/size/{size}")
    public FleetConnection fleetsWithPagination(@PathVariable String after, @PathVariable @NotEmpty @Pattern(regexp = "^[0-9]+$") String size) {
        int pageSize = Integer.parseInt(size);
        return fleetService.fleetsWithPagination(after, pageSize + 1);
    }

}
