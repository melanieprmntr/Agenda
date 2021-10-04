package fr.m2i.myagenda.composantui.combobox.contact;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import fr.m2i.myagenda.dto.ContactDto;

class ContactComboboxRendered extends DefaultListCellRenderer {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		ContactDto contact = (ContactDto) value;
		String infoContact = "";
		if (contact != null) {
			infoContact = contact.getGenre() + " " + contact.getPrenom() + " " + contact.getNom();
		}

		Component component = super.getListCellRendererComponent(list, infoContact, index, isSelected, cellHasFocus);
		component.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		return component;
	}
}