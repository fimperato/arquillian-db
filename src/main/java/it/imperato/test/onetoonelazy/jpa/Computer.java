package it.imperato.test.onetoonelazy.jpa;

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

import it.imperato.test.onetoonelazy.toenhance.jpa.Person;

/**
 * No-To-Enhance (with bytecode instrumentation) class: 
 * created to test lazy one-to-one
 * 
 * @author Francesco
 *
 */

@Entity
@Table(name = "computer", schema = "myproj")
public class Computer {
	
	private Person ownerPerson;
	
	private int idComputer;
	
	@Id
	@Column(columnDefinition = "serial", name = "id_computer")
	@SequenceGenerator(name = "ComputerSequence", sequenceName = "computer_id_computer_seq", schema = "myproj", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ComputerSequence")
	public int getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
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
