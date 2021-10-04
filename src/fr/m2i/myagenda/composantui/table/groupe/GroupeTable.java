package fr.m2i.myagenda.composantui.table.groupe;

import java.awt.BorderLayout;
import java.awt.Label;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.GroupeDto;

public class GroupeTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupeTableModel tableModel = new GroupeTableModel();

	private JTable table = new JTable(this.tableModel);

	private JScrollPane scroll = new JScrollPane(this.table);

	private ContactDto contactCourant;

	public GroupeTable() {
		this.setLayout(new BorderLayout());

		Label label = new Label("Groupe(s) associé(s)");

		this.add(label, BorderLayout.PAGE_START);
		this.add(scroll, BorderLayout.CENTER);
	}

	public GroupeTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(GroupeTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setListGroupes(List<GroupeDto> listGroupes) {
		this.tableModel.getListGroupes().addAll(listGroupes);
	}

	public void addGroupe(GroupeDto groupe) {
		this.tableModel.getListGroupes().add(groupe);
	}

	public ContactDto getContactCourant() {
		return contactCourant;
	}

	public void setContactCourant(ContactDto contactCourant) {
		this.contactCourant = contactCourant;
		this.tableModel.getListGroupes().clear();
		this.tableModel.getListGroupes().addAll(this.contactCourant.getListGroupes());
		this.tableModel.fireTableDataChanged();
	}
}
