package fr.m2i.myagenda.composantui.combobox.genre;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class GenreCombobox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String NO_GENRE = "-";

	JComboBox<String> comboBox;

	public GenreCombobox() {
		String[] genres = new String[] { NO_GENRE, "Madame", "Monsieur" };
		comboBox = new JComboBox<String>(genres);
		this.add(comboBox);
	}

	public void setSelectedItem(String genre) {
		System.out.println("genre " + genre);
		if (genre == null) {
			this.comboBox.setSelectedItem(NO_GENRE);
		} else {
			this.comboBox.setSelectedItem(genre);
		}
	}

	public String getSelectedItem() {
		return (String) comboBox.getSelectedItem();
	}
}
