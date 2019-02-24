package es.urjc.code.daw.user;

import javax.persistence.*;

/**
 * The persistent class for the category database table.
 */
@Entity
@Table(name="Category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCategory;

	@Column(unique=true)
	private String name;
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}

	public Long getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
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
	@Override
	public String toString() {
		return "Category{" + "idCategory=" + idCategory + ", name='" + name + '\''+'}';
	}
}