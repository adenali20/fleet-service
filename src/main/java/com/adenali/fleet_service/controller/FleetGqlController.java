package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Device;
import com.adenali.fleet_service.domain.Fleet;
import com.adenali.fleet_service.domain.Owner;
import com.adenali.fleet_service.model.*;
import com.adenali.fleet_service.repository.DeviceRepository;
import com.adenali.fleet_service.repository.FleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class FleetGqlController {

    private final FleetRepository fleetRepository;
    private final DeviceRepository deviceRepository;

    @QueryMapping
    public Fleet fleet(@Argument String id) {
        return fleetRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Fleet> fleets() {
        return fleetRepository.findAll();
    }

    @QueryMapping
    public FleetConnection fleetsWithPagination(@Argument String after, @Argument Integer size) {
        // Fetch one extra to detect next page
        PageRequest pageable = PageRequest.of(0, size + 1);


        List<Fleet> fleets = (after == null)
                ? fleetRepository.findAll(pageable).getContent()
                : fleetRepository.findFleetByIdAfter(after, pageable);


        boolean hasNextPage = fleets.size() > size;


        if (hasNextPage) {
            fleets = fleets.subList(0, size);
        }


        List<FleetEdge> edges = fleets.stream()
                .map(fleet->FleetEdge.builder()
                        .node(FleetDto.builder().name(fleet.getName()).id(fleet.getId()).build())
                        .cursor(fleet.getId())
                        .build())
                .collect(Collectors.toList());


        String endCursor = edges.isEmpty()
                ? null
                : edges.get(edges.size() - 1).cursor;


        return FleetConnection.builder().edges(edges)
                .pageInfo(PageInfo.builder().hasNextPage(hasNextPage).endCursor(endCursor).build()).build();
    }

    @MutationMapping
    public Fleet createFleet(@Argument CreateFleetInput input) {
        Owner owner=null;
        if(input.getOwner()!=null){
            owner = Owner.builder()
                    .ownerId(input.getOwner().getOwnerId())
                    .name(input.getOwner().getName())
                    .build();
        }



        Fleet fleet = Fleet.builder()
                .type(input.getType())
                .name(input.getName())
                .model(input.getModel())
                .year(input.getYear())
                .brand(input.getBrand())
                .plate(input.getPlate())
                .owner(owner)
                .metadata(input.getMetadata())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return fleetRepository.save(fleet);
    }

    @MutationMapping
    public Device attachDevice(@Argument CreateDeviceInput input) {

        Fleet fleet = fleetRepository.findById(input.getFleetId())
                .orElseThrow(() -> new RuntimeException("Fleet not found"));

        Device device = Device.builder()
                .fleetId(fleet.getId())
                .name(input.getName())
                .description(input.getDescription())
                .carrier(input.getCarrier())
                .network(input.getNetwork())
                .number(input.getNumber())
                .activated(input.getActivated())
                .metadata(input.getMetadata())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return deviceRepository.save(device);
    }


}
