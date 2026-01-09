package com.adenali.fleet_service.model;


import lombok.Data;

import java.util.Map;

@Data
public class CreateFleetInput {

    private String type;
    private String name;
    private String model;
    private Integer year;
    private String brand;
    private String plate;

    private OwnerInput owner;

    private Map<String, Object> metadata;
}
