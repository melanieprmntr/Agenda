package fr.m2i.myagenda.process;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.service.EventService;

public class AnalyseEvent {

	public AnalyseEvent(LocalDateTime now) throws ClassNotFoundException, SQLException {

		List<EventDto> listEvents = EventService.findAll();
		for (EventDto event : listEvents) {

			LocalDateTime eventDateHeure = event.getDate();

			if (!isSameYear(eventDateHeure, now)) {
				System.out.println("Les années ne sont pas identiques " + event);
				continue;
			}

			if (!isSameMonth(eventDateHeure, now)) {
				System.out.println("Les mois ne sont pas identiques " + event);
				continue;
			}

			if (!isSameDay(eventDateHeure, now)) {
				System.out.println("Les jours ne sont pas identiques " + event);
				continue;
			}

			if (!isSameHour(eventDateHeure, now)) {
				System.out.println("Les heures ne sont pas identiques " + event);
				continue;
			}

			if (!isSameMinute(eventDateHeure, now)) {
				System.out.println("Les minutes ne sont pas identiques " + event);
				continue;
			}

			System.out.println("Il est temps de faire " + event);

		}
	}

	public static boolean isSameYear(LocalDateTime timestamp, LocalDateTime timestampToCompare) {
		int year = timestamp.getYear();
		if (timestampToCompare.getYear() != year) {
			return false;
		}
		return true;
	}

	public static boolean isSameMonth(LocalDateTime timestamp, LocalDateTime timestampToCompare) {
		Month month = timestamp.getMonth();
		if (timestampToCompare.getMonth().compareTo(month) != 0) {
			return false;
		}
		return true;
	}

	public static boolean isSameDay(LocalDateTime timestamp, LocalDateTime timestampToCompare) {
		return timestamp.truncatedTo(ChronoUnit.DAYS).isEqual(timestampToCompare.truncatedTo(ChronoUnit.DAYS));
	}

	public static boolean isSameHour(LocalDateTime timestamp, LocalDateTime timestampToCompare) {
		return timestamp.truncatedTo(ChronoUnit.HOURS).isEqual(timestampToCompare.truncatedTo(ChronoUnit.HOURS));
	}

	public static boolean isSameMinute(LocalDateTime timestamp, LocalDateTime timestampToCompare) {
		return timestamp.truncatedTo(ChronoUnit.MINUTES).isEqual(timestampToCompare.truncatedTo(ChronoUnit.MINUTES));
	}
}
