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
@Document(collection = "owners")
public class Owner {


    @Id
    private String id;
    private String name;
    private String email;
    private Map<String, Object> metadata;
    private Instant createdAt = Instant.now();
}