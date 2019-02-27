package es.urjc.code.daw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Event findOneByName(String name) {
        return repository.findByName(name);
    }
    public Event findOne(Long id) {
        return repository.getOne(id);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public void save(Event event) {
        repository.save(event);
    }

    public void delete(long idEvent) {
        repository.delete(idEvent);
    }
}