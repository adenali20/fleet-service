package com.adenali.fleet_service.model;

import com.adenali.fleet_service.domain.Fleet;
import lombok.Builder;

@Builder
public class FleetEdge {
    FleetDto node;
    public String cursor;
}
