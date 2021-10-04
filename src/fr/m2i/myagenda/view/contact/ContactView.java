package fr.m2i.myagenda.view.contact;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.service.ContactService;

public class ContactView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContactDto contactCourant;

	private ContactTableModel tableModel = new ContactTableModel();

	private JTable table = new JTable(this.tableModel);

	private JScrollPane scroll = new JScrollPane(this.table);

	private ContactForm form = new ContactForm(this);

	public ContactView() throws ClassNotFoundException, SQLException {

		this.tableModel.getListContacts().addAll(ContactService.findAll());

		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				ContactDto contact = (ContactDto) tableModel.getListContacts().get(row);
				form.setContactCourant(contact);
			}

		});

		this.setLayout(new BorderLayout());

		JButton addContact = new JButton("Ajouter un contact");
		this.add(addContact, BorderLayout.PAGE_START);
		addContact.addActionListener(e -> {
			ContactDto contact = ContactService.getInstance();
			this.setContactCourant(contact);
		});
		
		JPanel content = new JPanel(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
		
		content.add(this.scroll, BorderLayout.LINE_START);

		content.add(this.form, BorderLayout.CENTER);

		this.setSize(1800, 1000);
		this.setVisible(false);
	}

	public ContactDto getContactCourant() {
		return this.contactCourant;
	}

	public void setContactCourant(ContactDto contact) {
		this.contactCourant = contact;
		if (this.contactCourant != null) {
			this.form.setContactCourant(contact);
			this.setVisible(true);
		} else {
			this.form.setContactCourant(null);
			this.setVisible(false);
		}
	}

	public ContactTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ContactTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void updateTable(ContactDto contact) {
		if (!this.getTableModel().getListContacts().contains(contact)) {
			this.getTableModel().getListContacts().add(contact);
		}
		this.getTableModel().fireTableDataChanged();
	}
}