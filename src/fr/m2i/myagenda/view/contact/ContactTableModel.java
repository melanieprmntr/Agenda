package fr.m2i.myagenda.view.contact;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.MyAgendaObject;

public class ContactTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7716157448045176990L;

	private List<MyAgendaObject> listContacts = new ArrayList<MyAgendaObject>();

	private String[] colonnes = { "nom", "prénom", "téléphone" };

	public List<MyAgendaObject> getListContacts() {
		return listContacts;
	}

	public void setListContacts(List<MyAgendaObject> listContacts) {
		this.listContacts = listContacts;
	}

	public String[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(String[] colonnes) {
		this.colonnes = colonnes;
	}

	public String getColumnName(int column) {
		return this.colonnes[column];
	}

	@Override
	public int getRowCount() {
		return this.listContacts.size();
	}

	@Override
	public int getColumnCount() {
		return this.colonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ContactDto contact = (ContactDto) this.listContacts.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return contact.getNom();
		case 1:
			return contact.getPrenom();
		case 2:
			return contact.getTelephone();
		}
		throw new NullPointerException("impossible de déterminer la colonne à afficher pour " + columnIndex);
	}

}
