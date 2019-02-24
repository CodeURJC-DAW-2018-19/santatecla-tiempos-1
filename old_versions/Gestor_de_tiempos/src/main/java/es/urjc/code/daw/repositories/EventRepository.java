package es.urjc.code.daw.repositories;

import es.urjc.code.daw.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByIdEvent(long id);
}