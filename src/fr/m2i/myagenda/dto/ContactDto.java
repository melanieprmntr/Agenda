package fr.m2i.myagenda.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "contact")
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ContactDto extends MyAgendaObject {

	@Column
	private String genre;

	@Column
	private String nom;

	@Column
	private String prenom;

	@Column
	private String adresse;

	@Column
	private String telephone;

	@Column
	private String email;

	@OneToMany
	private List<EventDto> listEvents = new ArrayList<EventDto>();

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "GROUPE_CONTACT", joinColumns = { @JoinColumn(name = "LISTCONTACTS_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "GROUPE_ID") })
	private List<GroupeDto> listGroupes = new ArrayList<GroupeDto>();

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EventDto> getListEvents() {
		return listEvents;
	}

	public void setListEvents(List<EventDto> listEvents) {
		this.listEvents = listEvents;
	}

	public List<GroupeDto> getListGroupes() {
		return listGroupes;
	}

	public void setListGroupes(List<GroupeDto> listGroupes) {
		this.listGroupes = listGroupes;
	}

	@Override
	public String toString() {
		return "Contact [hascode=" + this.hashCode() + ", id=" + this.getId() + ", genre=" + genre + ", nom=" + nom
				+ ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email
				+ ", listEvents=" + listEvents + "]";
	}

	@Override
	public String getInformation() {
		return "Je suis un contact";
	}

}
