package fr.m2i.myagenda.test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.process.AnalyseEvent;
import fr.m2i.myagenda.service.EventService;

public class TestAnalyseEvent {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		EventDto event = EventService.getInstance();
		event.setNom("Changemente d'année");
		event.setDate(LocalDateTime.now().plusYears(1));

		event = EventService.getInstance();
		event.setNom("Changement de mois");
		event.setDate(LocalDateTime.now().plusMonths(1));

		event = EventService.getInstance();
		event.setNom("Changement de jour");
		event.setDate(LocalDateTime.now().plusDays(1));

		event = EventService.getInstance();
		event.setNom("changement d'heure");
		event.setDate(LocalDateTime.now().plusHours(1));

		event = EventService.getInstance();
		event.setNom("Changement de minute");
		event.setDate(LocalDateTime.now().plusMinutes(1));

		event = EventService.getInstance();
		event.setNom("Doit s'exécuter");
		event.setDate(LocalDateTime.now());

		System.out.println(event.getDate());

		// Analyser les event pour savoir si ils doivent être effectués ou pas
		new AnalyseEvent(LocalDateTime.now());

		System.out.println("--------------- Doit renvoyer l'évent avec changement de minutes ------------------");
		new AnalyseEvent(LocalDateTime.now().plusMinutes(1));

		System.out.println("--------------- Dois renvoyer l'event avec changement d'heure ------------------");
		new AnalyseEvent(LocalDateTime.now().plusHours(1));

		System.out.println("---------------- Doit renvoyer l'event avec changement de jour -----------------");
		new AnalyseEvent(LocalDateTime.now().plusDays(1));

		System.out.println("---------------- Doit renvoyer l'event avec changement de mois ----------------");
		new AnalyseEvent(LocalDateTime.now().plusMonths(1));

		System.out.println("---------------- Doit renvoyer l'event avec changement d'année -----------------");
		new AnalyseEvent(LocalDateTime.now().plusYears(1));

	}

}
