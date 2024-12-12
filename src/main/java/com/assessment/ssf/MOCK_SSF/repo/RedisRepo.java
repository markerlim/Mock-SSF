package com.assessment.ssf.MOCK_SSF.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.assessment.ssf.MOCK_SSF.constant.Constant;
import com.assessment.ssf.MOCK_SSF.model.Event;

@Repository
public class RedisRepo {

    @Autowired
    @Qualifier(Constant.redisString)
    private RedisTemplate<String, String> redisTemplate;

    // Save
    public void saveEvent(Event Event) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(Constant.redisKey, Event.getEventId().toString(), Event.toString());

    }

    // Get
    public Integer getNumberOfEvent() {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        Long size = hashOps.size(Constant.redisKey);
        if (size > Integer.MAX_VALUE) {
            throw new ArithmeticException("Redis hash size exceeds Integer.MAX_VALUE");
        }

        return size.intValue();

    }

    // Get
    public Optional<Event> getEvent(Integer index) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String content = hashOps.get(Constant.redisKey, String.valueOf(index));
        if (content == null) {
            return null;
        }
        String[] data = content.split(Constant.regex);
        Event event = new Event();
        event.setEventId(Integer.parseInt(data[0]));
        event.setEventName(data[1]);
        event.setEventSize(Integer.parseInt(data[2]));
        event.setEventDate(Long.parseLong(data[3]));
        event.setParticipants(Integer.parseInt(data[4]));
        return Optional.ofNullable(event);
    }

    //delete
    public boolean delEvent(Integer index) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        if (getEvent(index) != null) {
            hashOps.delete(Constant.redisKey, index);
            return true;
        }
        return false;
    }

    //update
    public void UpdateEvent(Integer index, Event event){
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(Constant.redisKey,index.toString(),event.toString());
    }
}
