package fr.m2i.myagenda.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ModulePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1966901451170348846L;

	JButton addBtn = new JButton("Ajouter");
	JButton gestionBtn = new JButton("Gestion de events");
	JButton sqlBtn = new JButton("afficher SQL");
	
	public ModulePanel(String title, String gestion, boolean isShowSql) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setPreferredSize(new Dimension(200, 160));

		Border blackLine = BorderFactory.createLineBorder(Color.gray);

		this.setBorder(blackLine);

		JLabel titre = new JLabel(title);
		titre.setPreferredSize(new Dimension(200, 20));
		titre.setHorizontalAlignment(JLabel.CENTER);
		this.add(titre);

		Dimension dimensionButton = new Dimension(160, 30);
		addBtn.setPreferredSize(dimensionButton);
		gestionBtn.setPreferredSize(dimensionButton);
		sqlBtn.setPreferredSize(dimensionButton);

		this.add(addBtn);

		gestionBtn.setText(gestion);
		this.add(gestionBtn);

		if (isShowSql) {
			this.add(sqlBtn);
		}
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public JButton getGestionBtn() {
		return gestionBtn;
	}

	public void setGestionBtn(JButton gestionBtn) {
		this.gestionBtn = gestionBtn;
	}

	public JButton getSqlBtn() {
		return sqlBtn;
	}

	public void setSqlBtn(JButton sqlBtn) {
		this.sqlBtn = sqlBtn;
	}

}
