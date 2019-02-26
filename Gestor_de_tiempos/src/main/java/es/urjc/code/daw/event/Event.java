package es.urjc.code.daw.event;

import javax.persistence.*;

@Entity
@Table(name="Event")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idEvent;

    /*	@ElementCollection(fetch = FetchType.EAGER)
        private List<String> roles;

        @Column(unique=true)
    */
    @Column(unique=true)

    private String name;

    public Event() {
    }
    public Event(String name) {
        this.name = name;
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
        this.name = name;
    }

    /*	@OneToMany
        @JoinTable(name="", joinColumns = @JoinColumn(name=""), inverseJoinColumns = @JoinColumn(name=""))

        */



}
