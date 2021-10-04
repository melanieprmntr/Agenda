package fr.m2i.myagenda.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {

	public static void createTableContact() throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		String requeteCreationTable = "CREATE TABLE CONTACT(ID BIGINT,GENRE VARCHAR(100),NOM VARCHAR(200),PRENOM VARCHAR(100), ADRESSE VARCHAR(1000), TELEPHONE VARCHAR(30), EMAIL VARCHAR(100))";
		stmt.executeUpdate(requeteCreationTable);
		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}
	
	public static void createTableEvent() throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		String requeteCreationTable = "CREATE TABLE EVENT(ID BIGINT,CONTACT_ID BIGINT,NOM VARCHAR(200), ADRESSE VARCHAR(1000), DATE TIMESTAMP)";
		stmt.executeUpdate(requeteCreationTable);
		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}

	public static void createTableGroupe() throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		String requeteCreationTable = "CREATE TABLE GROUPE(ID BIGINT,NOM VARCHAR(200))";
		stmt.executeUpdate(requeteCreationTable);
		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}

	public static void createTableRelationContactGroupe() throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		String requeteCreationTable = "CREATE TABLE GROUPE_CONTACT(LISTCONTACTS_ID BIGINT,GROUPE_ID BIGINT)";
		stmt.executeUpdate(requeteCreationTable);
		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}

	public static void createTableRelationContactEvent() throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		Statement stmt = c.createStatement();
		String requeteCreationTable = "CREATE TABLE CONTACT_EVENT(LISTEVENTS_ID BIGINT,CONTACT_ID BIGINT)";
		stmt.executeUpdate(requeteCreationTable);
		stmt.close();
		MyAgendaConnection.closeConnection(c);
	}
	
	
//	 Modifier le code de création de la table 'CONTACT' pour disposer de
//	 l'ensemble des attributs de l'object 'Contact'
//	 Créer le code pour créer la table 'EVENT' avec les attributs de l'object
//	 'Event'
//	 Modifier le test TestJdbcCreateTable pour créer la table 'EVENT' et des
//	 enregistrements dans la table 'EVENT', puis une recherche pour afficher les
//	 enregistrements.
}
