package org.ticket.event_ticket_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticket.event_ticket_management.domain.entities.Event;

import java.util.UUID;

@Repository
public interface EventRepository  extends JpaRepository<Event, UUID> {
}
