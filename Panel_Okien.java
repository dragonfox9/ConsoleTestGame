import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * 
 * Aktualne funkcje konsoli:
 * 
 * WYŒWIETL_TEKST("tekst") - wyœwietla tekst w konsoli
 * WCZYTAJ_KOMENDE() - wczytuje tekst wpisany przez gracza i wyœwietla go na konsoli
 * WYCZYŒÆ_KONSOLE() - czyœci aktualny tekst wyœwietlony przez konsolê
 * 
 * 
 * 
 */

public class Panel_Okien extends JFrame implements ActionListener, Serializable {
	private JFrame okno_FRAME;
	private JPanel okno_PANEL;
	private JButton BUTTON_WYŒLIJ;
	JTextField POLE_TEKSTOWE;
	private JTextPane print;
	private JScrollPane SCROLLOWANIE;
	private final static String nowaLinia = "\n";
	private JMenuBar MENU_BAR;
	private JMenu MENU;
	private JMenuItem ZAPISZ;
	private JMenuItem WCZYTAJ;
	private JMenuItem WYJD;
	private String command = null;
	public StyledDocument Dokument;
	private static final long serialVersionUID = 2L;

	public static void main(String args[]) {
		Panel_Okien nowe_okno = new Panel_Okien();
		nowe_okno.setTitle("gra");
		nowe_okno.setVisible(true);
		nowe_okno.setLocationRelativeTo(null);

	}

	public Panel_Okien() {
		// wygl¹da jak windows, nie java.

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		// okno

		setBounds(200, 200, 700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno_PANEL = new JPanel();
		okno_PANEL.setBorder(null);
		okno_PANEL.setLayout(new BorderLayout(0, 0));
		setContentPane(okno_PANEL);

		// Komponenty

		Font font = new Font("Tahoma", Font.BOLD, 12);

		POLE_TEKSTOWE = new JTextField();
		okno_PANEL.add(POLE_TEKSTOWE, BorderLayout.SOUTH);
		POLE_TEKSTOWE.setColumns(10);
		POLE_TEKSTOWE.addActionListener(this);
		POLE_TEKSTOWE.setEnabled(false);
		POLE_TEKSTOWE.setBorder(null);
		POLE_TEKSTOWE.setForeground(new Color(255, 255, 255));
		POLE_TEKSTOWE.setBackground(new Color(0, 0, 0));
		POLE_TEKSTOWE.setFont(font);
		POLE_TEKSTOWE.setCaretColor(Color.RED);
		POLE_TEKSTOWE.setText("");

		print = new JTextPane();
		print.setEditable(false);
		okno_PANEL.add(print, BorderLayout.CENTER);
		print.setAutoscrolls(true);
		print.setFont(font);
		print.setBackground(new Color(0, 0, 0));
		print.setForeground(new Color(255, 255, 255));
		print.setBorder(null);

		Dokument = print.getStyledDocument();

		SCROLLOWANIE = new JScrollPane(print);
		okno_PANEL.add(SCROLLOWANIE);
		SCROLLOWANIE.setBorder(null);
		SCROLLOWANIE.setBackground(new Color(0, 0, 0));

		przyciski();
	}

	public void przyciski() {
		MENU_BAR = new JMenuBar();
		setJMenuBar(MENU_BAR);
		MENU_BAR.setBorder(null);

		MENU = new JMenu("Plik");
		MENU_BAR.add(MENU);
		MENU.setBorder(null);

		ZAPISZ = new JMenuItem("Zapis gry");
		MENU.add(ZAPISZ);
		OPCJA_ZAPISZ zapis = new OPCJA_ZAPISZ();
		ZAPISZ.addActionListener(zapis);

		WCZYTAJ = new JMenuItem("Wczytaj grê");
		MENU.add(WCZYTAJ);
		OPCJA_WCZYTAJ wczytaj = new OPCJA_WCZYTAJ();
		WCZYTAJ.addActionListener(wczytaj);

		WYJD = new JMenuItem("WyjdŸ");
		MENU.add(WYJD);
		OPCJA_WYJDZ wyjdz = new OPCJA_WYJDZ();
		WYJD.addActionListener(wyjdz);

	}

	public void print(String s, boolean trace) {
		print(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}

	public void print(String s, boolean trace, Color c) {

		javax.swing.text.Style style = print.addStyle("Style", null);
		StyleConstants.setForeground(style, c);

		if (trace) {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			String caller = elements[0].getClassName();

			s = caller + " -> " + s;

		}

		try {
			Dokument.insertString(Dokument.getLength(), s, style);
		} catch (Exception e) {
		}

		scrollBottom();

	}

	public void println(String s, boolean trace) {
		println(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}

	public void println(String s, boolean trace, Color c) {
		print(s + "\n", trace, c);
		scrollBottom();
	}

	public void scrollTop() {
		print.setCaretPosition(0);
	}

	public void scrollBottom() {
		print.setCaretPosition(print.getDocument().getLength());
	}

	public void WYCZYŒÆ_KONSOLE() {
		print.setText("");
	}

	public String WCZYTAJ_KOMENDE() {
		command = null;
		POLE_TEKSTOWE.setEnabled(true);
		POLE_TEKSTOWE.requestFocusInWindow();

		do {
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
			}
		} while (command == null);

		POLE_TEKSTOWE.setEnabled(false);
		return command;
	}

	public void _charInfo() {
		Postaæ okno = new Postaæ();
		print("<" + okno._getHealth() + "hp " + okno._getExp() + "xp" + ">", false, Color.CYAN);
	}

	public String WCZYTAJ_KOMENDE(boolean wypiszNaEkran) {
		command = null;

		POLE_TEKSTOWE.setEnabled(true);
		POLE_TEKSTOWE.requestFocusInWindow();
		do {
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
			}
		} while (command == null);

		POLE_TEKSTOWE.setEnabled(false);
		if (wypiszNaEkran) {
			_charInfo();
			println(command, false);
		}
		return command;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		command = POLE_TEKSTOWE.getText();
		POLE_TEKSTOWE.setText("");

	}

	public class OPCJA_WYJDZ implements ActionListener {
		public void actionPerformed(ActionEvent Exit) {
			System.exit(0);

		}
	}

	public class OPCJA_WCZYTAJ implements ActionListener {

		public void actionPerformed(ActionEvent Load) {
			Postaæ load = new Postaæ();
			load._loadGame();
		}
	}

	public class OPCJA_ZAPISZ implements ActionListener {
		public void actionPerformed(ActionEvent Save) {
			Postaæ save = new Postaæ();
			save._saveGame();
		}
	}
}