package com.backend.sportC.communication.Request;

import com.backend.sportC.enums.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    private Integer userId;
    private String prefix;
    private String firstName;
    private String lastName;
    private Skill skillLevel;
}
