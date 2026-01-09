package com.adenali.fleet_service.model;

import lombok.Builder;

import java.util.List;
@Builder
public class FleetConnection {
    List<FleetEdge> edges;
    PageInfo pageInfo;
}
