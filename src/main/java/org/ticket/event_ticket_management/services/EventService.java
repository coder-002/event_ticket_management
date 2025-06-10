package org.ticket.event_ticket_management.services;

import org.ticket.event_ticket_management.domain.CreateEventRequest;
import org.ticket.event_ticket_management.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);

}
