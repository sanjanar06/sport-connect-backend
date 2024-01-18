package com.backend.sportC.communication.Request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private Integer facilityId;
    private String title;
    private String location;
    private LocalDateTime dateTime;
}
