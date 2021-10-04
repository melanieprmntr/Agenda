package fr.m2i.myagenda.composantui.combobox.contact;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.service.ContactService;

public class ContactCombobox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ContactComboboxModel comboBoxModel;

	JComboBox<ContactDto> comboBox;

	public ContactCombobox() {
		comboBoxModel = new ContactComboboxModel();
		comboBox = new JComboBox<ContactDto>(comboBoxModel);
		comboBox.setRenderer(new ContactComboboxRendered());

		this.add(comboBox);
	}

	public void updateComboBox() {
		comboBoxModel.removeAllElements();
		comboBoxModel.addAll(ContactService.findAll());
	}

	public void setSelectedItem(ContactDto contact) {
		this.comboBoxModel.setSelectedItem(contact);
	}

	public ContactDto getSelectedItem() {
		return comboBoxModel.getSelectedItem();
	}
}
