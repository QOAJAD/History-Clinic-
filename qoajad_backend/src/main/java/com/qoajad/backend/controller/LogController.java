package com.qoajad.backend.controller;

import com.qoajad.backend.model.log.Log;
import com.qoajad.backend.service.log.ReadableLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class LogController {

    private final ReadableLogService readableLogService;

    @Autowired
    public LogController(@Qualifier("synchronousReadableLogService") final ReadableLogService readableLogService) {
        this.readableLogService = readableLogService;
    }

    @RequestMapping(value = "/log/list_all", method = RequestMethod.GET)
    public ResponseEntity<List<Log>> retrieveAllLogs() {
        ResponseEntity<List<Log>> response;
        try {
            response = new ResponseEntity<>(readableLogService.retrieveAllLogs(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }
}
