package it.imperato.test.onetoonelazy.toenhance.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import it.imperato.test.onetoonelazy.jpa.Computer;

/**
 * Class to test lazy load in one-to-one relationship: 
 * this is the class (A), it will be referred by a class (B) Animal, 
 * is mandatory to move this class in the package that 
 * will be enhanced (it.imperato.test.onetoonelazy.toenhance.jpa)
 * 
 * @author Francesco
 *
 */

@Entity
@Table(name = "person", schema = "myproj")
@NamedQueries({
	@NamedQuery(name="allPerson", query="select p from Person p")
})
public class Person {
	
	private Animal animal;
	
	//Not enhanced classes (with one-to-one realtionship):
	private Computer computer;
 
 	private int idPerson;
	
	@Id
	@Column(columnDefinition = "serial", name = "id_person")
	@SequenceGenerator(name = "PersonSequence", sequenceName = "person_id_person_seq", schema = "myproj", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonSequence")
	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = true, mappedBy = "ownerPerson")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public Animal getAnimal() {
	  return animal;
	 }
	 
	 public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	 
	@OneToOne(fetch = FetchType.LAZY, optional = true, mappedBy = "ownerPerson")
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	 
}
