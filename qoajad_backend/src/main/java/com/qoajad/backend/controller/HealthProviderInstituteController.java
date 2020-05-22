package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import com.qoajad.backend.service.external.health.HealthProviderInstituteFactory;
import com.qoajad.backend.service.external.health.HealthProviderInstituteService;
import com.qoajad.backend.service.external.specialty.SpecialtyService;
import com.qoajad.backend.service.internal.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class HealthProviderInstituteController {

    private final HealthProviderInstituteFactory hpiFactory;
    private final UserService userService;

    @Autowired
    public HealthProviderInstituteController(@Qualifier("defaultHealthProviderInstituteFactory") final HealthProviderInstituteFactory hpiFactory,
                                             @Qualifier("defaultUserService") final UserService userService) {
        this.hpiFactory = hpiFactory;
        this.userService = userService;
    }

    @RequestMapping(value = "/hpi/list_all", method = RequestMethod.GET)
    public ResponseEntity<List<HealthProviderInstitute>> retrieveAllHPI() {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final HealthProviderInstituteService hpiService = hpiFactory.create(userService.retrieveHPE(currentUser.getUsername()));
        return new ResponseEntity<>(hpiService.retrieveAllHPI(), HttpStatus.OK);
    }
}
