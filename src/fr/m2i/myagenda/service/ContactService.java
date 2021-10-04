package fr.m2i.myagenda.service;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.singleton.MyAgendaSingleton;

public class ContactService {

	public static ContactDto getInstance() {
		ContactDto contact = new ContactDto();
		contact.setNom("Nouveau contact");
		contact.setPrenom("pr�nom");
		return contact;
	}

	public static void save(ContactDto contact) {
		if (contact.getId() == null) {
			insert(contact);
		} else {
			update(contact);
		}
	}

	private static void insert(ContactDto contact) {

		contact.setId(MyAgendaSingleton.nextSequence());

		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();

			session.save(contact);
			session.flush();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private static void update(ContactDto contact) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();

			session.update(contact);
			session.flush();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void delete(ContactDto contact) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(contact);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static ContactDto findById(Long id) {
		Session session = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			return session.get(ContactDto.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	public static List<ContactDto> findAll() {
		Session session = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();

			// Demande d'un 'robot' permettant de cr�er les CriteriaQuery
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// --------------------------------------------------------------
			// Configure la requ�te
			// Travail sur l'entit� 'ContactDto'
			CriteriaQuery<ContactDto> cq = cb.createQuery(ContactDto.class);

			// L'ensemble de la requ�te porte sur l'entit� 'ContactDto'
			Root<ContactDto> root = cq.from(ContactDto.class);

			// Production de la requ�te
			CriteriaQuery<ContactDto> all = cq.select(root);

			// Soumet la requ�te � la base de donn�es
			TypedQuery<ContactDto> resultQuery = session.createQuery(all);

			return resultQuery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	public static void loadLazy(ContactDto contact) {
		List<GroupeDto> listGroupes = GroupeService.findByContact(contact);
		contact.setListGroupes(listGroupes);
	}
}