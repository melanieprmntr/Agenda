package fr.m2i.myagenda.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.m2i.myagenda.generateData.GenerateData;
import fr.m2i.myagenda.hibernate.HibernateInit;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2549393140623727385L;

	public MainView() throws ClassNotFoundException, SQLException {
		try {
		
			HibernateInit.init();

			GenerateData.generate();

			JPanel content = (JPanel) this.getContentPane();
			content.setLayout(new BorderLayout());
			content.add(new MainMenu(), BorderLayout.CENTER);

			JLabel titre = new JLabel("My Agenda !");
			titre.setFont(new Font("Lucida Grande", Font.BOLD, 18));
			content.add(titre, BorderLayout.NORTH);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(800, 500);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Défaut de configuration", JOptionPane.WARNING_MESSAGE);
			System.exit(ERROR);
		}
	}

}
