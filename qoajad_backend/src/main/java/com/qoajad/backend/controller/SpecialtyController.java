package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.specialty.Specialties;
import com.qoajad.backend.service.external.specialty.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(@Qualifier("defaultSpecialtyService") final SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @RequestMapping(value = "/specialty/list_all/{hpiName}", method = RequestMethod.GET)
    public ResponseEntity<List<Specialties>> retrieveAllSpecialties(@PathVariable("hpiName") String hpiName) {
        //TODO: Must process the response given by the API to check whether it was successful or not.
        return new ResponseEntity<>(specialtyService.retrieveAllSpecialties(hpiName), HttpStatus.OK);
    }
}
