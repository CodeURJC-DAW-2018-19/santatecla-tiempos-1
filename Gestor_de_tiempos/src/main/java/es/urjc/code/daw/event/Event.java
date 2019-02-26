package es.urjc.code.daw.event;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Event")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idEvent;

    public Event() {
    }

    @Column(unique=true)

    private String name;

    @Column(unique = true)
    private String eventPhoto;
    @Column(columnDefinition = "")
    private String eventWiki;
    @Column
    private String eventDate;


    public Event(String name, String eventPhoto, String eventWiki, String eventDate) {
        setName(name);
        setEventPhoto(eventPhoto);
        setEventWiki(eventWiki);
        setEventDate(eventDate);
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        if (eventDate == null) eventDate = "";
        this.eventDate = eventDate;
    }

    public String getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        if (eventPhoto == null) eventPhoto = "";
        this.eventPhoto = eventPhoto;
    }

    public String getEventWiki() {
        return eventWiki;
    }

    public void setEventWiki(String eventWiki) {
        if (eventWiki == null) eventWiki = "";
        this.eventWiki = eventWiki;
    }

    public Long getIdEvent() {
        return this.idEvent;
    }

    public void setIdEvent(Long idCategory) {
        this.idEvent = idCategory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) name = "";
        this.name = name;
    }

}
