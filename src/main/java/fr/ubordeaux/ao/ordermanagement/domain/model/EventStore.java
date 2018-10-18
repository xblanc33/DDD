package fr.ubordeaux.ao.ordermanagement.domain.model;

import java.util.List;

import fr.ubordeaux.ao.ordermanagement.domain.model.Event;

/**
 * Repository 
 * 
 */
public abstract class EventStore {
    public abstract void add(Event event);
    public abstract List<Event> getEventList();
    public abstract List<Event> getEventListByReference(Reference reference);
}