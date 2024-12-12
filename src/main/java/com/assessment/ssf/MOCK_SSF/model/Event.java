package com.assessment.ssf.MOCK_SSF.model;

public class Event {
    
    //Unique Identifier for an Event
    private Integer eventId;

    //Name of Event
    private String eventName;

    //Maximum size the event can hold
    private Integer eventSize;

    //Event date in milliseconds
    private Long eventDate;

    //Count of the number of people who registered to attend the event
    private Integer participants;

    public Event(){
        
    }

    public Event(Integer eventId, String eventName, Integer eventSize, Long eventDate, Integer participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return eventId + "," + eventName + "," + eventSize + ","
                + eventDate + "," + participants;
    }
    
    public String toPrintString() {
        return "Events [eventId=" + eventId + ", eventName=" + eventName + ", eventSize=" + eventSize + ", eventDate="
                + eventDate + ", participants=" + participants + "]";
    }

}
