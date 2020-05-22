package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.eps.specialty.Specialties;
import com.qoajad.backend.service.external.specialty.SpecialtyFactory;
import com.qoajad.backend.service.external.specialty.SpecialtyService;
import com.qoajad.backend.service.internal.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class SpecialtyController {

    private final SpecialtyFactory specialtyFactory;
    private final UserService userService;

    @Autowired
    public SpecialtyController(@Qualifier("defaultSpecialtyFactory") final SpecialtyFactory specialtyFactory,
                               @Qualifier("defaultUserService") final UserService userService) {
        this.specialtyFactory = specialtyFactory;
        this.userService = userService;
    }

    @RequestMapping(value = "/specialty/list_all/{hpiName}", method = RequestMethod.GET)
    public ResponseEntity<List<Specialties>> retrieveAllSpecialties(@PathVariable("hpiName") String hpiName) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final SpecialtyService specialtyService = specialtyFactory.create(userService.retrieveHPE(currentUser.getUsername()));
        return new ResponseEntity<>(specialtyService.retrieveAllSpecialties(hpiName), HttpStatus.OK);
    }
}
