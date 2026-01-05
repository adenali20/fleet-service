package com.adenali.fleet_service.controller;

import com.adenali.fleet_service.domain.Owner;
import com.adenali.fleet_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {


    private final OwnerRepository ownerRepository;


    @PostMapping
    public Owner create(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }


    @GetMapping
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }


    @GetMapping("/{id}")
    public Owner getById(@PathVariable String id) {
        return ownerRepository.findById(id).orElseThrow();
    }
}