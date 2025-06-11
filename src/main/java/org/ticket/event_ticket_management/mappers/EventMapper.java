package org.ticket.event_ticket_management.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.ticket.event_ticket_management.domain.CreateEventRequest;
import org.ticket.event_ticket_management.domain.CreateTicketTypeRequest;
import org.ticket.event_ticket_management.domain.dtos.CreateEventRequestDto;
import org.ticket.event_ticket_management.domain.dtos.CreateEventResponseDto;
import org.ticket.event_ticket_management.domain.dtos.CreateTicketTypeRequestDto;
import org.ticket.event_ticket_management.domain.dtos.CreateTicketTypeResponseDto;
import org.ticket.event_ticket_management.domain.entities.Event;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

}
