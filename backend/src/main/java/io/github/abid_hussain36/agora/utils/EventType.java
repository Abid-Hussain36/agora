package io.github.abid_hussain36.agora.utils;

import io.github.abid_hussain36.agora.event.Event;

public enum EventType {
    SOCIAL("Social"), ACADEMIC("Academic"), CAREER("Career");

    private final String label;

    EventType(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
