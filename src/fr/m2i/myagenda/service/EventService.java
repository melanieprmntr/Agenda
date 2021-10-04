package fr.m2i.myagenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.exception.ContactAbsentException;
import fr.m2i.myagenda.jdbc.MyAgendaConnection;
import fr.m2i.myagenda.singleton.MyAgendaSingleton;

public class EventService {

	public static EventDto getInstance() {
		EventDto event = new EventDto();
		event.setNom("Nouveau événement");
		event.setDate(LocalDateTime.now());
		return event;
	}

	public static void save(EventDto event) throws Exception {

		if (event.getContact() == null) {
			throw new ContactAbsentException();
		}

		if (event.getId() == null) {
			insert(event);
		} else {
			update(event);
		}
	}

	private static void insert(EventDto event) throws ClassNotFoundException, SQLException {

		event.setId(MyAgendaSingleton.nextSequence());

		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();

		ContactDto contact = event.getContact();

		Timestamp timestamp = Timestamp.valueOf(event.getDate());

		String requete = "INSERT INTO EVENT (ID,CONTACT_ID,NOM,DATE,ADRESSE) " + "VALUES (" + event.getId() + ","
				+ contact.getId() + ",'" + event.getNom() + "','" + timestamp + "','" + event.getAdresse() + "')";

		stmt.execute(requete);

		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}

	/***
	 * Fonction permettant de mettre à jour un enregistrement contact
	 * 
	 * @param contact
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void update(EventDto event) throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();

		Timestamp timestamp = Timestamp.valueOf(event.getDate());

//		String requete = "UPDATE EVENT SET NOM='" + event.getNom() + "', CONTACT_ID=" + contact.getId() + ", ADRESSE='"
//				+ event.getAdresse() + "', DATE='" + timestamp + "' WHERE ID=" + event.getId();

		String requete = "UPDATE EVENT SET NOM=?, CONTACT_ID=?, ADRESSE=?, DATE=? WHERE ID=" + event.getId();

		System.out.println("requête : " + requete);

		PreparedStatement pstmt = c.prepareStatement(requete);
		pstmt.setString(1, event.getNom());
		pstmt.setLong(2, event.getContact().getId());
		pstmt.setString(3, event.getAdresse());
		pstmt.setTimestamp(4, timestamp);
		pstmt.executeUpdate();
		pstmt.close();
		MyAgendaConnection.closeConnection(c);
	}

	/***
	 * Permet de retourner l'intégralité des contacts
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<EventDto> findAll() throws ClassNotFoundException, SQLException {

		List<EventDto> result = new ArrayList<EventDto>();

		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT");

		while (rs.next()) {
			EventDto event = getInstance();
			event.setId(rs.getLong("ID"));
			event.setNom(rs.getString("NOM"));
			event.setAdresse(rs.getString("ADRESSE"));

			Timestamp timestamp = rs.getTimestamp("DATE");
			event.setDate(timestamp.toLocalDateTime());

			event.setContact(null);

			Long idContact = rs.getLong("CONTACT_ID");
			ContactDto contact = ContactService.findById(idContact);
			event.setContact(contact);

			result.add(event);
		}

		rs.close();
		stmt.close();
		MyAgendaConnection.closeConnection(c);

		return result;
	}

	public static List<EventDto> findByContact(ContactDto contact) throws ClassNotFoundException, SQLException {
		List<EventDto> result = new ArrayList<EventDto>();

		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE CONTACT_ID=" + contact.getId());

		while (rs.next()) {
			EventDto event = getInstance();
			event.setId(rs.getLong("ID"));
			event.setNom(rs.getString("NOM"));
			event.setAdresse(rs.getString("ADRESSE"));

			Timestamp timestamp = rs.getTimestamp("DATE");
			event.setDate(timestamp.toLocalDateTime());

			event.setContact(contact);
			contact.getListEvents().add(event);

			result.add(event);
		}

		rs.close();
		stmt.close();
		MyAgendaConnection.closeConnection(c);

		return result;
	}
}
