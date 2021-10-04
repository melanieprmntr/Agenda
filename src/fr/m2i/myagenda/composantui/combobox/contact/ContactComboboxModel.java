package fr.m2i.myagenda.composantui.combobox.contact;

import javax.swing.DefaultComboBoxModel;

import fr.m2i.myagenda.dto.ContactDto;

public class ContactComboboxModel extends DefaultComboBoxModel<ContactDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactComboboxModel() {
	}

	@Override
	public ContactDto getSelectedItem() {
		ContactDto selectedContact = (ContactDto) super.getSelectedItem();
		return selectedContact;
	}
}