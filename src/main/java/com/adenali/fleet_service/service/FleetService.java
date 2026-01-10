package com.adenali.fleet_service.service;

import com.adenali.fleet_service.domain.Fleet;
import com.adenali.fleet_service.model.FleetConnection;
import com.adenali.fleet_service.model.FleetDto;
import com.adenali.fleet_service.model.FleetEdge;
import com.adenali.fleet_service.model.PageInfo;
import com.adenali.fleet_service.repository.FleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FleetService {
    private final FleetRepository fleetRepository;

    public List<Fleet> findAll(){
        return fleetRepository.findAll();
    }

    public FleetConnection fleetsWithPagination(String after, Integer size) {
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
}
