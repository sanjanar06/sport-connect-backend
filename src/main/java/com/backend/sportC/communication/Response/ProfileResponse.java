package com.backend.sportC.communication.Response;

import com.backend.sportC.enums.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private Skill skillLevel;
}
