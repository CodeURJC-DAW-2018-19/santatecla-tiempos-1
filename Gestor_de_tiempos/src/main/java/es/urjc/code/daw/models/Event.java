package es.urjc.code.daw.models;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Event
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEvent;

    @Column
    private String Image;


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
