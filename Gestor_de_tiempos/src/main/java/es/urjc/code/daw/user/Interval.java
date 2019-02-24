package es.urjc.code.daw.user;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;


/**
 * The persistent class for the category database table.
 *
 */
@Entity
@Table(name="Interval")
@NamedQuery(name="Interval.findAll", query="SELECT i FROM Interval i")
public class Interval{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idInterval;

/*	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	@Column(unique=true)
*/
	@Column(unique=true)
	
	private String nameI;
	
	public Interval() {
	}
	public Interval(String name) {
	
		this.nameI = name;

	}


	public Long getIdInterval() {
		return this.idInterval;
	}

	public void setIdInterval(Long idInterval) {
		this.idInterval = idInterval;
	}

public String getName() {
		return nameI;
	}
	public void setName(String name) {
		this.nameI = name;
	}
	/*	@OneToMany
	@JoinTable(name="", joinColumns = @JoinColumn(name=""), inverseJoinColumns = @JoinColumn(name=""))
	
	*/
	@Override
	public String toString() {
		return "Interval{" + "idInterval=" + idInterval + ", name='" + nameI + '\''+'}';
	}
}