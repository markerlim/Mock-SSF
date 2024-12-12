package com.assessment.ssf.MOCK_SSF.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.ssf.MOCK_SSF.model.Event;
import com.assessment.ssf.MOCK_SSF.model.User;
import com.assessment.ssf.MOCK_SSF.service.RedisService;

@Controller
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private RedisService redisService;

    @GetMapping("/listing")
    public ModelAndView displayEvents(){
        ModelAndView mav = new ModelAndView();

        //unwrapping of Optional to allow for thymeleaf to loop
        List<Optional<Event>> loe = redisService.getListing();
        List<Event> events = loe.stream()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();

        mav.addObject("listofevents",events);
        mav.setViewName("event-listing");
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

}
