package com.backend.sportC.communication.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private Integer eventId;
    private String title;
    private String location;
    private LocalDateTime dateTime;
    private String facilityName;

}
