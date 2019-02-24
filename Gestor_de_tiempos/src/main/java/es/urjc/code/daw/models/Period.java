package es.urjc.code.daw.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Period
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPeriod;

}
