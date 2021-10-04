package fr.m2i.myagenda.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import fr.m2i.myagenda.generateData.GenerateData;
import fr.m2i.myagenda.hibernate.HibernateInit;
import fr.m2i.myagenda.view.contact.ContactView;

public class ViewTestLayout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320016103777080811L;

	public ViewTestLayout() {

		try {
			HibernateInit.init();
			GenerateData.generate();

			this.setLayout(new BorderLayout());

			this.add(getHeader(), BorderLayout.PAGE_START);
			this.add(getCenter(), BorderLayout.CENTER);
			this.add(getFooter(), BorderLayout.PAGE_END);

			this.add(getEast(), BorderLayout.EAST);
			this.add(getWest(), BorderLayout.WEST);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);

			this.setSize(1000, 1000);

			this.setLocationRelativeTo(null);

			this.setJMenuBar(createMenuBar());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e.getMessage(), "défaut de configuration", JOptionPane.WARNING_MESSAGE);
			System.exit(ERROR);

		}

	}

	public JPanel getHeader() {
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));

//		Border blackLine = BorderFactory.createLineBorder(Color.RED);
//
//		header.setBorder(blackLine);
//
//		Label l1 = new Label("Ligne header 1");
//		Label l2 = new Label("Ligne header 2");
//		Label l3 = new Label("Ligne header 3");
//
//		header.add(l1);
//		header.add(l2);
//		header.add(l3);
		

		this.add(getEast(), BorderLayout.EAST);
		this.add(getWest(), BorderLayout.WEST);
		/*Border blackLine = BorderFactory.createLineBorder(Color.orange);
		Label l1 = new Label("Ligne header 1");
		Label l2 = new Label("Ligne header 2");
		Label l3 = new Label("Ligne header 3");
		header.add(l1);
		header.add(l2);
		header.add(l3);
		header.setBorder(blackLine);*/

		JToolBar toolBar= new JToolBar();
		header.add(toolBar);
		JScrollPane scroll = new JScrollPane();
		JButton ajouterPizza = new JButton();
		toolBar.setPreferredSize(new Dimension(80,80));
		toolBar.add(ajouterPizza);


		return header;
	}

	// Panel CENTER - GridBagLayout
	public JTabbedPane getCenter() {
//		JPanel center = new JPanel(new GridBagLayout());
//
//		Label l1 = new Label("Ligne centre 1");
//		Label l2 = new Label("Ligne centre 2");
//		Label l3 = new Label("Ligne centre 3");
//
//		center.add(l1);
//		center.add(l2);
//		center.add(l3);

		JTabbedPane tabs = new JTabbedPane();
		try {
			tabs.addTab("Contact", new ContactView());
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Défaut de configuration", JOptionPane.WARNING_MESSAGE);
			System.exit(ERROR);
		}

		tabs.addTab("table1", new JPanel());
		tabs.addTab("table1", new JPanel());

		return tabs;

	}

	// Panel FOOTER - FlowLayout
	public JPanel getFooter() {
		JPanel footer = new JPanel(new FlowLayout());

		Label l1 = new Label("Ligne footer 1");
		Label l2 = new Label("Ligne footer 2");
		Label l3 = new Label("Ligne footer 3");

		footer.add(l1);
		footer.add(l2);
		footer.add(l3);

		return footer;
	}

	public JPanel getEast() {
		JPanel east = new JPanel(new BorderLayout());

		Border blackLine = BorderFactory.createLineBorder(Color.pink);

		east.setBorder(blackLine);
		Label l1 = new Label("est");
		l1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		east.add(l1, BorderLayout.NORTH);

		JTextArea text = new JTextArea("ceci est un essai");

		JScrollPane scroll = new JScrollPane(text);

		east.add(scroll, BorderLayout.CENTER);

		return east;
	}

	public JPanel getWest() {
		JPanel west = new JPanel(new BorderLayout());

		Border blackLine = BorderFactory.createLineBorder(Color.cyan);

		west.setBorder(blackLine);
		Label l1 = new Label("ouest");

		west.add(l1, BorderLayout.NORTH);

		JTree tree = new JTree();
		JScrollPane scroll = new JScrollPane(tree);

		west.add(scroll, BorderLayout.CENTER);

		return west;
	}

	/* Methode de construction de la barre de menu */
	private JMenuBar createMenuBar() {
		// La barre de menu à proprement parler
		JMenuBar menuBar = new JMenuBar();
		// Définition du menu déroulant "File" et de son contenu
		JMenu mnuFile = new JMenu("File");
		mnuFile.setMnemonic('F');
		JMenuItem mnuNewFile = new JMenuItem("New File");
		mnuNewFile.setIcon(new ImageIcon("icons/new.png"));
		mnuNewFile.setMnemonic('N');
		mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		mnuNewFile.addActionListener(this::menuNewListener);
		mnuFile.add(mnuNewFile);
		mnuFile.addSeparator();
		JMenuItem mnuOpenFile = new JMenuItem("Open File ...");
		mnuOpenFile.setIcon(new ImageIcon("icons/open.png"));
		mnuOpenFile.setMnemonic('O');
		mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		mnuFile.add(mnuOpenFile);
		JMenuItem mnuSaveFile = new JMenuItem("Save File ...");
		mnuSaveFile.setIcon(new ImageIcon("icons/save.png"));
		mnuSaveFile.setMnemonic('S');
		mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		mnuFile.add(mnuSaveFile);
		JMenuItem mnuSaveFileAs = new JMenuItem("Save File As ...");
		mnuSaveFileAs.setIcon(new ImageIcon("icons/save_as.png"));
		mnuSaveFileAs.setMnemonic('A');
		mnuFile.add(mnuSaveFileAs);
		mnuFile.addSeparator();
		JMenuItem mnuExit = new JMenuItem("Exit");
		mnuExit.setIcon(new ImageIcon("icons/exit.png"));
		mnuExit.setMnemonic('x');
		mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		mnuFile.add(mnuExit);

		menuBar.add(mnuFile);

		// Définition du menu déroulant "Edit" et de son contenu
		JMenu mnuEdit = new JMenu("Edit");
		mnuEdit.setMnemonic('E');

		JMenuItem mnuUndo = new JMenuItem("Undo");
		mnuUndo.setIcon(new ImageIcon("icons/undo.png"));
		mnuUndo.setMnemonic('U');
		mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuUndo);

		JMenuItem mnuRedo = new JMenuItem("Redo");
		mnuRedo.setIcon(new ImageIcon("icons/redo.png"));
		mnuRedo.setMnemonic('R');
		mnuRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuRedo);

		mnuEdit.addSeparator();

		JMenuItem mnuCopy = new JMenuItem("Copy");
		mnuCopy.setIcon(new ImageIcon("icons/copy.png"));
		mnuCopy.setMnemonic('C');
		mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuCopy);

		JMenuItem mnuCut = new JMenuItem("Cut");
		mnuCut.setIcon(new ImageIcon("icons/cut.png"));
		mnuCut.setMnemonic('t');
		mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuCut);

		JMenuItem mnuPaste = new JMenuItem("Paste");
		mnuPaste.setIcon(new ImageIcon("icons/paste.png"));
		mnuPaste.setMnemonic('P');
		mnuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		mnuEdit.add(mnuPaste);
		menuBar.add(mnuEdit);
		// Définition du menu déroulant "Help" et de son contenu
		JMenu mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic('H');

		menuBar.add(mnuHelp);

		return menuBar;
	}

	public void menuNewListener(ActionEvent event) {
		System.out.println("click ");
	}

}
