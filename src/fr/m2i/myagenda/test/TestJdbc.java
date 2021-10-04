package fr.m2i.myagenda.test;

import java.sql.Connection;
import java.sql.SQLException;

import fr.m2i.myagenda.jdbc.MyAgendaConnection;

public class TestJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection c = MyAgendaConnection.getConnection();
		System.out.println("Connection : " + c);
		MyAgendaConnection.closeConnection(c);
	}
}
