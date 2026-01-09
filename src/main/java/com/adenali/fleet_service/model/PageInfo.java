package com.adenali.fleet_service.model;

import lombok.Builder;

@Builder
public class PageInfo {
    Boolean hasNextPage;
    String endCursor;
}
