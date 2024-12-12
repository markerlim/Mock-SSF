package com.assessment.ssf.MOCK_SSF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.ssf.MOCK_SSF.model.Event;
import com.assessment.ssf.MOCK_SSF.model.User;
import com.assessment.ssf.MOCK_SSF.service.RedisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/events")
public class RegisterController {
    
    @Autowired
    private RedisService redisService;

    @GetMapping("/register/{id}")
    public ModelAndView register(@PathVariable String id){
        ModelAndView mav = new ModelAndView();

        Event event = redisService.getEvent(Integer.parseInt(id)).orElse(new Event());
        User user = new User();
        mav.addObject("event",event);
        mav.addObject("user", user);
        mav.setViewName("event-register");
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }

    //BindingResult must precede the object that is being binded
    @PostMapping("/register/{id}")
    public ModelAndView processRegistration(@PathVariable String id, @Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        Event event = redisService.getEvent(Integer.parseInt(id)).orElse(new Event());


        if (result.hasErrors() || event.getEventSize() < event.getParticipants() + user.getTickets()) {
            if (event.getEventSize() < event.getParticipants() + user.getTickets()) {
                result.rejectValue("tickets", "error.user", "Event is full or insufficient capacity.");
            }

            mav.addObject("event", event);
            mav.addObject("user", user);
            mav.setViewName("event-register");
            mav.setStatus(HttpStatusCode.valueOf(400));
            return mav;
        }

        Integer currentP = event.getParticipants();
        event.setParticipants(currentP + user.getTickets());

        redisService.UpdateEvent(event.getEventId(), event);
        mav.setViewName("success");
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }

}
