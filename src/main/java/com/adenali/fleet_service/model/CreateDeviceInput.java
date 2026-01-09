package com.adenali.fleet_service.model;


import lombok.Data;

import java.util.Map;

@Data
public class CreateDeviceInput {

    private String fleetId;

    private String name;
    private String description;

    private String carrier;
    private String network;

    private String number;
    private Boolean activated;

    private Map<String, Object> metadata;
}

