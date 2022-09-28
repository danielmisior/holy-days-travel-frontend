package com.frontend.holydaystravel.dto.OpenTrip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacePropertiesDto {
    private String xid;
    private String name;
    private String otm;
    private String wikipedia;
    private String image;
    private Preview preview;
    @JsonProperty("wikipedia_extracts")
    private WikiExtracts wiki;
}
