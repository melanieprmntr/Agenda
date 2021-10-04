package fr.m2i.myagenda.test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.process.AnalyseEvent;
import fr.m2i.myagenda.service.EventService;

public class TestAnalyseEventAutomatique extends Thread {

	public static void main(String[] args) {

		EventDto event = EventService.getInstance();
		event.setNom("Doit s'executer dans 1 minute");
		event.setDate(LocalDateTime.now().plusMinutes(1));

		event = EventService.getInstance();
		event.setNom("Doit s'exécuter dans 2 minutes");
		event.setDate(LocalDateTime.now().plusMinutes(2));

		event = EventService.getInstance();
		event.setNom("Doit s'exécuter");
		event.setDate(LocalDateTime.now());

		TestAnalyseEventAutomatique testAnalyseEventAutomatique = new TestAnalyseEventAutomatique();
		testAnalyseEventAutomatique.start();
	}

	public TestAnalyseEventAutomatique() {
		System.out.println("Lancement du processus de gestion des événements");
	}

	@Override
	public void run() {
		boolean loop = true;
		while (loop) {

			try {
				new AnalyseEvent(LocalDateTime.now());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
