package com.adenali.fleet_service.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.Instant;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "devices")
public class Device {


    @Id
    private String id;
    private String fleetId;
    private String sku;
    private String name;
    private DeviceType type;
    private Map<String, Object> attributes;
    private Instant createdAt = Instant.now();
}

