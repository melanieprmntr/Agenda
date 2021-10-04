package fr.m2i.myagenda.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "event")
@Table(name = "event")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EventDto extends MyAgendaObject {

	@Column
	private String nom;

	@ManyToOne
	private ContactDto contact;

	@Column
	private LocalDateTime date;

	@Column
	private String adresse;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ContactDto getContact() {
		return contact;
	}

	public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {

		String infoContact = "pas de contact";
		if (this.contact != null) {
			infoContact = contact.getPrenom() + " " + contact.getNom();
		}

		return "Event [hashcode=" + this.hashCode() + ", id=" + this.getId() + ", nom=" + nom + ", contact="
				+ infoContact + ", date=" + date + ", adresse=" + adresse + "]";
	}

	@Override
	public String getInformation() {
		return "Je suis un événement";
	}
}
