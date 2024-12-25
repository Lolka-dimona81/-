package ru.p11505.practic2024.course3.workingtimecontrol;

import java.time.LocalTime;

public class WorkEvent {

    public static final String EVENT_TYPE_IN = "IN";
    public static final String EVENT_TYPE_OUT = "OUT";
    public static final String EVENT_TYPE_LUNCH = "LUNCH";
    public static final String EVENT_TYPE_PAUSE = "PAUSE";

    private LocalTime time;
    private String eventType;

    public WorkEvent(LocalTime time, String eventType) {
        this.time = time;
        this.eventType = eventType;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getEventType() {
        return eventType;
    }
}
