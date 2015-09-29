package it.imperato.test.onetoonelazy.toenhance.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class to test lazy load in one-to-one relationship: 
 * this is the class (B) with the foreign key, 
 * is mandatory to move this class in the package that 
 * will be enhanced (it.imperato.test.onetoonelazy.toenhance.jpa)
 * 
 * @author Francesco
 *
 */
@Entity
@Table(name = "animal", schema = "myproj")
public class Animal {
	
	private Person ownerPerson;
	
	private int idAnimal;
	
	@Id
	@Column(columnDefinition = "serial", name = "id_animal")
	@SequenceGenerator(name = "AnimalSequence", sequenceName = "animal_id_animal_seq", schema = "myproj", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AnimalSequence")
	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_person")
	public Person getOwnerPerson() {
	  return ownerPerson;
	}
	
	public void setOwnerPerson(Person ownerPerson) {
	  this.ownerPerson = ownerPerson;
	}
}
