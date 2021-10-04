package fr.m2i.myagenda.hibernate;

import java.io.File;
import java.io.FileNotFoundException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.dto.GroupeDto;

public class HibernateConfiguration {

	private static final String FILE_NAME_HIBERNATE_CONFIGURATION = "myagenda.cfg.xml";

	private SessionFactory factory;

	// Cr�ation d'une instance 'file' permettant d'atteindre le fichier de
	// configuration sur le disque dur.
	// Ce fichier XML comporte la configuration de connexion � la base de donn�es,
	// le driver d�finissant la nature de la base de donn�es employ� et son dialect.
	// Peut �galement comporter le flag d'affichage et de production dans la console
	// des requ�tes SQL
	// Peut permettre la configuration du PoolConnect de gestion des connexions � la
	// base de donn�es ainsi que la gestion de la m�moire allou�e � Hibernate
	File file = new File(FILE_NAME_HIBERNATE_CONFIGURATION);

	// Obtention de l'instance de configuration d'hibernate, en lui fournissant le
	// fichier XML contenant la configuration
	Configuration configuration;

	public HibernateConfiguration() throws FileNotFoundException {

		if (!file.exists()) {
			throw new FileNotFoundException("Le fichier de configuration n�cessaire � hibernate, '"
					+ FILE_NAME_HIBERNATE_CONFIGURATION + "' n'existe pas !");
		}

		buildConfiguration(file);

		// Instancation d'un builder : on va notamment produire le gestionnaire d'entity
		// n�cessaire pour g�rer l'ensemble des DTO devant �tre persist� en database
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();

		// Instanciation du service registry : c'est la classe qui administre l'ensemble
		// des �v�nements n�cessaires aux sessions transactionnelles avec la base de
		// donn�es
		ServiceRegistry serviceRegistry = builder.applySettings(configuration.getProperties()).build();

		this.factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public void buildConfiguration(File fileHibernateConfiguration) {
		this.configuration = new Configuration().configure(fileHibernateConfiguration);

		this.configuration.addAnnotatedClass(GroupeDto.class);
		this.configuration.addAnnotatedClass(ContactDto.class);
		this.configuration.addAnnotatedClass(EventDto.class);

	}

	public SessionFactory getFactory() {
		return factory;
	}
}
