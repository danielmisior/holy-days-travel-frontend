package com.frontend.holydaystravel.dto.OpenTrip;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceListDto {
    private String xid;
    private String name;
    private double dist;
    private Point point;
}
