package com.assessment.ssf.MOCK_SSF.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.ssf.MOCK_SSF.model.Event;
import com.assessment.ssf.MOCK_SSF.repo.RedisRepo;

@Service
public class RedisService {
    
    @Autowired
    private RedisRepo redisRepo;

    public void saveEvent(Event event){
        redisRepo.saveEvent(event);
    }

    public List<Optional<Event>> getListing() {
        List<Optional<Event>> loe = new ArrayList<>();
        Integer num = getNumberOfEvent();
        System.out.println(num);
    
        while (num != -1) {
            Optional<Event> event = getEvent(num);
            if (event == null) {
                break;
            }
            System.out.println(event.toString());
            loe.add(event);
            num--;
        }
    
        return loe;
    }
    

    public Integer getNumberOfEvent(){
        return redisRepo.getNumberOfEvent();
    }

    public Optional<Event> getEvent(Integer index){
        return redisRepo.getEvent(index);
    }

    public void UpdateEvent(Integer index, Event event){
        redisRepo.UpdateEvent(index, event);
    }
}
