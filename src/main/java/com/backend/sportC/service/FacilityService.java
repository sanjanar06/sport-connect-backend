package com.backend.sportC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.sportC.communication.Request.FacilityRequest;
import com.backend.sportC.entity.Facility;
import com.backend.sportC.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public void createFacility(FacilityRequest request) {
        new Facility();
        Facility facility = Facility.builder()
                .name(request.getName())
                .description((request.getDescription()))
                .location(request.getLocation())
                .build();
        facilityRepository.save(facility);
    }

    public List<Facility> getFacilities() {
        return facilityRepository.findAll();
    }

    public void updateFacility(Integer id, String name, String desc, String location) {
        Facility facility = facilityRepository.findById(id).orElseThrow();

        if (name != null) {
            facility.setName(name);
        }

        if (desc != null) {
            facility.setDescription(desc);
        }

        if (location != null) {
            facility.setLocation(location);
        }

        facilityRepository.save(facility);
    }

    public void deleteFacility(Integer id) {
        Facility facility = facilityRepository.findById(id).orElseThrow();
        facilityRepository.delete(facility);
    }

}
