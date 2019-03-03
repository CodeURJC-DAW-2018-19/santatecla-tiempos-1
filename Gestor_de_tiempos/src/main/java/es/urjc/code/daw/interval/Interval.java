package es.urjc.code.daw.interval;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the category database table.
 *
 */
@Entity
@Table(name="intervals")
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
	
	private String name;
	private String startdate;
	private String enddate;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="parent_id")
	private Interval parent;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
	private Set<Interval> childrens = new HashSet<Interval>();
	
	
	public Interval() {
	}
	public Interval(String name,String startdate,String enddate) {
	
		this.name = name;
		this.startdate=startdate;
		this.enddate=enddate;
	}
	
	
	public Set<Interval> getChildrens() {
		return childrens;
	}
	
	public void setChildrens(Set<Interval> childrens) {
		this.childrens = childrens;
	}
	
	public void addChild(Interval child) {
		  this.childrens.add(child);
		 }
	
	public Interval getParent() {
		return parent;
	}
	
	public void setParent(Interval parent) {
		this.parent = parent;
	}
	

	public Long getIdInterval() {
		return this.idInterval;
	}

	public void setIdInterval(Long idInterval) {
		this.idInterval = idInterval;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart() {
		return startdate;
	}
	public void setStart(String startdate) {
		this.startdate = startdate;
	}
	public String getEnd() {
		return enddate;
	}
	public void setEnd(String enddate) {
		this.enddate = enddate;
	}
	/*	@OneToMany
	@JoinTable(name="", joinColumns = @JoinColumn(name=""), inverseJoinColumns = @JoinColumn(name=""))
	
	*/
	/*@Override
	public boolean equals(Object emp) {
		if(emp instanceof Interval) {
			Interval interval = (Interval)emp;
			
			if(this.name.equals(interval.getName()))
				return true;
		}

		return false;
	}
	@Override
	public int hashCode() {
	
		return this.name.hashCode();
	}*/
	
	@Override
	public String toString() {
		return "Interval{" + "idInterval=" + idInterval + ", name='" + name + '\''+startdate.toString()+"::"+enddate.toString()+'}';
	}
}
