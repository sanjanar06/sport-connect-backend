package com.backend.sportC.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sportC.communication.Request.FacilityRequest;
import com.backend.sportC.entity.Facility;
import com.backend.sportC.service.FacilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/facility")
@RequiredArgsConstructor
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createFacility(@RequestBody FacilityRequest request) {
        facilityService.createFacility(request);
        return ResponseEntity.ok("Facility enlisted successfully");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Facility>> getFacilities() {
        return ResponseEntity.ok(facilityService.getFacilities());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('FACILITY_OWNER')")
    public ResponseEntity<String> updateFacility(
            @PathVariable Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String location) {
        facilityService.updateFacility(id, name, description, location);
        return ResponseEntity.ok("Facility updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteFacility(@PathVariable Integer id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok("Facility deleted successfully");
    }

}
