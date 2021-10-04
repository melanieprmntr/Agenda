package fr.m2i.myagenda;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import fr.m2i.myagenda.jdbc.DatabaseStructure;
import fr.m2i.myagenda.view.ViewTestLayout;

public class ApplicationLauncher {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedLookAndFeelException {

		// Cr�ation de la structure de ma base de donn�es
		DatabaseStructure.createTableContact();
		DatabaseStructure.createTableEvent();
		DatabaseStructure.createTableGroupe();
		DatabaseStructure.createTableRelationContactGroupe();
		DatabaseStructure.createTableRelationContactEvent();

		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		new ViewTestLayout();
	}

}
