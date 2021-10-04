package fr.m2i.myagenda.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "groupe")
@Table(name = "groupe")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GroupeDto extends MyAgendaObject {

	@Column
	private String nom;

	@ManyToMany
	private List<ContactDto> listContacts = new ArrayList<ContactDto>();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ContactDto> getListContacts() {
		return listContacts;
	}

	public void setListContacts(List<ContactDto> listContacts) {
		this.listContacts = listContacts;
	}

	@Override
	public String toString() {
		return "GroupeDto [id=" + this.getId() + ", nom=" + nom + "]";
	}

	@Override
	public String getInformation() {
		return "Je suis un groupe";
	}
}
