package fr.m2i.myagenda.hibernate;

import java.io.FileNotFoundException;

import fr.m2i.myagenda.singleton.MyAgendaSingleton;

public class HibernateInit {

	
	public static void init() throws FileNotFoundException {
		HibernateConfiguration hibernateConfiguration = new HibernateConfiguration();
		MyAgendaSingleton.getInstance().setHibernateConfiguration(hibernateConfiguration);
	}
}
