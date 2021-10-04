package fr.m2i.myagenda.test;

import java.util.List;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.jdbc.DatabaseStructure;
import fr.m2i.myagenda.service.ContactService;
import fr.m2i.myagenda.service.EventService;
import fr.m2i.myagenda.singleton.MyAgendaSingleton;

public class TestJdbcCreateTable {

	public static void main(String[] args) throws Exception {

		// Création de la structure de ma base de données
		DatabaseStructure.createTableContact();
		DatabaseStructure.createTableEvent();

		// Peuple ma base de données
		ContactDto contact = ContactService.getInstance();
		contact.setId(MyAgendaSingleton.nextSequence());
		contact.setNom("REYNIER");
		contact.setPrenom("Eric");
		ContactService.save(contact);

		contact = ContactService.getInstance();
		contact.setId(MyAgendaSingleton.nextSequence());
		contact.setNom("REYNIER");
		contact.setPrenom("Martin");
		ContactService.save(contact);

		EventDto event = EventService.getInstance();
		event.setId(MyAgendaSingleton.nextSequence());
		event.setNom("repas chez la maman de Remi");
		event.setContact(contact);
		event.setAdresse("A la part dieu à Lyon");
		EventService.save(event);

		event = EventService.getInstance();
		event.setId(MyAgendaSingleton.nextSequence());
		event.setNom("repas chez la maman de Jamil");
		event.setContact(contact);
		event.setAdresse("A Lyon");
		EventService.save(event);

		// J'affiche pour mon utilisateur l'ensemble des contacts
		List<ContactDto> listContact = ContactService.findAll();
		System.out.println(listContact);

		// J'affiche l'ensemble des événements
		List<EventDto> listEvent = EventService.findAll();
		System.out.println(listEvent);

		// J'affiche le dernier contact créé
		System.out.println("Contact avant findByContact " + contact);
		EventService.findByContact(contact);
		System.out.println("Contact Reynier Martin " + contact);
	}
}
