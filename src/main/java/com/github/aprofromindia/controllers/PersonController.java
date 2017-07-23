package com.github.aprofromindia.controllers;

import com.github.aprofromindia.config.AppConstants;
import com.github.aprofromindia.services.EventReadService;
import com.github.aprofromindia.viewModels.AverageAgeViewModel;
import com.github.aprofromindia.viewModels.GenderDistViewModel;
import com.github.aprofromindia.viewModels.PersonCountViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apro on 18-07-2017.
 */

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final EventReadService service;

    @GetMapping("/")
    HttpEntity<String> getIndex(){
        return new ResponseEntity<>("Welcome to the Person API", HttpStatus.OK);
    }

    @GetMapping("/viewer-count")
    PersonCountViewModel getViewersCount(@RequestParam("start")
                                         @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime start,
                                         @RequestParam("end")
                                         @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime end,
                                         @RequestParam("device") long deviceId,
                                         @RequestParam("content") long contentId) {

        return service.getCountBy(start, end, deviceId, contentId);
    }

    @GetMapping("/avg-age")
    AverageAgeViewModel getAvgAge(@RequestParam("start")
                                  @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime start,
                                  @RequestParam("end")
                                  @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime end,
                                  @RequestParam("device") long deviceId,
                                  @RequestParam("content") long contentId) {

        return service.getAvgAge(start, end, deviceId, contentId);
    }

    @GetMapping("/gender-dist")
    GenderDistViewModel getGenderDist(@RequestParam("start")
                                      @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime start,
                                      @RequestParam("end")
                                      @DateTimeFormat(pattern = AppConstants.DATE_TIME_PATTERN) LocalDateTime end,
                                      @RequestParam("device") long deviceId,
                                      @RequestParam("content") long contentId) {

        return service.getGenderDist(start, end, deviceId, contentId);
    }
}
