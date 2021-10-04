package fr.m2i.myagenda.generateData;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.ContactService;
import fr.m2i.myagenda.service.GroupeService;

public class GenerateData {

	public static void generate() {
		// Peuple ma base de données
		ContactDto contact = ContactService.getInstance();
		contact.setGenre("Monsieur");
		contact.setNom("REYNIER");
		contact.setPrenom("Eric");
		ContactService.save(contact);

		contact = ContactService.getInstance();
		contact.setGenre("Monsieur");
		contact.setNom("REYNIER");
		contact.setPrenom("Martin");
		ContactService.save(contact);

		contact = ContactService.getInstance();
		contact.setGenre("Monsieur");
		contact.setNom("JMILI");
		contact.setPrenom("Jamil");
		ContactService.save(contact);

		contact = ContactService.getInstance();
		contact.setGenre("Madame");
		contact.setNom("ZENASNI");
		contact.setPrenom("Chayma");
		ContactService.save(contact);

		contact = ContactService.getInstance();
		contact.setGenre("Monsieur");
		contact.setNom("DA CUNHA");
		contact.setPrenom("Tom");
		ContactService.save(contact);

		GroupeDto groupe1 = GroupeService.getInstance();
		groupe1.setNom("Equipe de foot");

		ContactDto reynierEric = ContactService.findById(0L);
		groupe1.getListContacts().add(reynierEric);

		System.out.println("before insert groupe1");
		GroupeService.save(groupe1);

		System.out.println("before instanciation groupe2");

		GroupeDto groupe2 = GroupeService.getInstance();
		groupe2.setNom("Cousinade 2022");

		ContactDto jamilJMILI = ContactService.findById(2L);
		groupe2.getListContacts().add(jamilJMILI);
		groupe2.getListContacts().add(reynierEric);

		GroupeService.save(groupe2);

		System.out.println("Liste des groupes " + GroupeService.findAll());
	}
}
