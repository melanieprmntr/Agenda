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

public class GroupeService {

	public static GroupeDto getInstance() {
		return new GroupeDto();
	}

	public static void save(GroupeDto groupe) {
		if (groupe.getId() == null) {
			insert(groupe);
		} else {
			update(groupe);
		}
	}

	private static void insert(GroupeDto groupe) {

		groupe.setId(MyAgendaSingleton.nextSequence());

		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();

			session.save(groupe);
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

	private static void update(GroupeDto groupe) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();

			session.update(groupe);
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

	public static void delete(GroupeDto groupe) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(groupe);
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

	public static GroupeDto findById(Long id) {
		Session session = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();
			return session.get(GroupeDto.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	public static List<GroupeDto> findAll() {
		Session session = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();

			// Demande d'un 'robot' permettant de créer les CriteriaQuery
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// --------------------------------------------------------------
			// Configure la requête
			// Travail sur l'entité 'GroupeDto'
			CriteriaQuery<GroupeDto> cq = cb.createQuery(GroupeDto.class);

			// L'ensemble de la requête porte sur l'entité 'GroupeDto'
			Root<GroupeDto> root = cq.from(GroupeDto.class);

			// Production de la requête
			CriteriaQuery<GroupeDto> all = cq.select(root);

			// Soumet la requête à la base de données
			TypedQuery<GroupeDto> resultQuery = session.createQuery(all);

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

	// SELECT * FROM GROUPE WHERE ID IN (SELECT GROUPE_ID FROM GROUPE_CONTACT WHERE
	// CONTACT_ID=?)

//	public static List<GroupeDto> findByContact(ContactDto contact) {
//		List<GroupeDto> result = new ArrayList<GroupeDto>();
//		try {
//			Connection c = MyAgendaConnection.getConnection();
//			Statement stmt = c.createStatement();
//			ResultSet rs = stmt.executeQuery(
//					"SELECT ID FROM GROUPE WHERE ID IN (SELECT GROUPE_ID FROM GROUPE_CONTACT WHERE LISTCONTACTS_ID="
//							+ contact.getId() + ")");
//			while (rs.next()) {
//				result.add(findById(rs.getLong("ID")));
//			}
//			rs.close();
//			stmt.close();
//			MyAgendaConnection.closeConnection(c);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	public static List<GroupeDto> findByContact(ContactDto contact) {

		Session session = null;
		try {
			session = MyAgendaSingleton.getInstance().getHibernateConfiguration().getFactory().openSession();

			final TypedQuery<GroupeDto> query = session.createNativeQuery(
					"SELECT * FROM GROUPE WHERE ID IN ( SELECT GROUPE_ID FROM GROUPE_CONTACT WHERE LISTCONTACTS_ID=:contactId)",
					GroupeDto.class);
			query.setParameter("contactId", contact.getId());
			final List<GroupeDto> listGroupes = query.getResultList();
			return listGroupes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}
}
