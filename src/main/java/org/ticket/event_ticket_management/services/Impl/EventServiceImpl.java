package org.ticket.event_ticket_management.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticket.event_ticket_management.domain.CreateEventRequest;
import org.ticket.event_ticket_management.domain.CreateTicketTypeRequest;
import org.ticket.event_ticket_management.domain.entities.Event;
import org.ticket.event_ticket_management.domain.entities.TicketType;
import org.ticket.event_ticket_management.domain.entities.User;
import org.ticket.event_ticket_management.exceptions.UserNotFoundException;
import org.ticket.event_ticket_management.repositories.EventRepository;
import org.ticket.event_ticket_management.repositories.UserRepository;
import org.ticket.event_ticket_management.services.EventService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    Event eventToCreate = new Event();

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer= userRepository.findById(organizerId).orElseThrow(()-> new UserNotFoundException(String.format("User with id %s not found", organizerId)));

       List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(ticketType ->{
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setEvent(eventToCreate);
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
            return ticketTypeToCreate;
        }).toList();


        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());

        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);

    }
}
