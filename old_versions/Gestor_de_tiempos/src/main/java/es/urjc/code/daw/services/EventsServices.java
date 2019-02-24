package es.urjc.code.daw.services;

import es.urjc.code.daw.models.Event;
import es.urjc.code.daw.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsServices
{
    @Autowired
    private EventRepository repository;

    public void addEvent(Event event){
        repository.save(event);
    }
    public void deleteEventById(long id) {
        repository.deleteById(id);
    }
}
