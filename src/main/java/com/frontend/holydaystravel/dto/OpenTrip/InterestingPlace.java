package com.frontend.holydaystravel.dto.OpenTrip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestingPlace {
    private String name;
    private String text;
    private String image;
}
