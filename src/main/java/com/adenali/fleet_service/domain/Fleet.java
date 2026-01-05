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
@Document(collection = "fleets")
public class Fleet {


    @Id
    private String id;
    private String ownerId;
    private String name;
    private Map<String, Object> attributes;
    private Instant createdAt = Instant.now();
}