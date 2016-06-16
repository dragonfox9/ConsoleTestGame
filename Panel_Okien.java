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
 * btnLoad_KOMENDE() - wczytuje tekst wpisany przez gracza i wyœwietla go na konsoli
 * WYCZYŒÆ_KONSOLE() - czyœci aktualny tekst wyœwietlony przez konsolê
 * 
 * 
 * 
 */

public class Panel_Okien extends JFrame implements ActionListener, Serializable {
	private JFrame Frame;
	private JPanel Panel;
	private JButton BUTTON_WYŒLIJ;
	JTextField textInput;
	private JTextPane print;
	private JScrollPane Scrollpane;
	private final static String nowaLinia = "\n";
	private JMenuBar MENU_BAR;
	private JMenu MENU;
	private JMenuItem btnSave;
	private JMenuItem btnLoad;
	private JMenuItem btnExit;
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
		Panel = new JPanel();
		Panel.setBorder(null);
		Panel.setLayout(new BorderLayout(0, 0));
		setContentPane(Panel);

		// Komponenty

		Font font = new Font("Tahoma", Font.BOLD, 12);

		textInput = new JTextField();
		Panel.add(textInput, BorderLayout.SOUTH);
		textInput.setColumns(10);
		textInput.addActionListener(this);
		textInput.setEnabled(false);
		textInput.setBorder(null);
		textInput.setForeground(new Color(255, 255, 255));
		textInput.setBackground(new Color(0, 0, 0));
		textInput.setFont(font);
		textInput.setCaretColor(Color.RED);
		textInput.setText("");

		print = new JTextPane();
		print.setEditable(false);
		Panel.add(print, BorderLayout.CENTER);
		print.setAutoscrolls(true);
		print.setFont(font);
		print.setBackground(new Color(0, 0, 0));
		print.setForeground(new Color(255, 255, 255));
		print.setBorder(null);

		Dokument = print.getStyledDocument();

		Scrollpane = new JScrollPane(print);
		Panel.add(Scrollpane);
		Scrollpane.setBorder(null);
		Scrollpane.setBackground(new Color(0, 0, 0));

		buttons();
	}

	public void buttons() {
		MENU_BAR = new JMenuBar();
		setJMenuBar(MENU_BAR);
		MENU_BAR.setBorder(null);

		MENU = new JMenu("File");
		MENU_BAR.add(MENU);
		MENU.setBorder(null);

		btnSave = new JMenuItem("Save game");
		MENU.add(btnSave);
		btnSaveoption save = new btnSaveoption();
		btnSave.addActionListener(save);

		btnLoad = new JMenuItem("Load game");
		MENU.add(btnLoad);
		Loadoption load = new Loadoption();
		btnLoad.addActionListener(load);

		btnExit = new JMenuItem("Exit");
		MENU.add(btnExit);
		OPCJA_WYJDZ exit = new OPCJA_WYJDZ();
		btnExit.addActionListener(exit);

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

	public String btnLoad_KOMENDE() {
		command = null;
		textInput.setEnabled(true);
		textInput.requestFocusInWindow();

		do {
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
			}
		} while (command == null);

		textInput.setEnabled(false);
		return command;
	}

	public void _charInfo() {
		Postaæ okno = new Postaæ();
		print("<" + okno._getHealth() + "hp " + okno._getExp() + "xp" + ">", false, Color.CYAN);
	}

	public String playerInput(boolean wypiszNaEkran) {
		command = null;

		textInput.setEnabled(true);
		textInput.requestFocusInWindow();
		do {
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
			}
		} while (command == null);

		textInput.setEnabled(false);
		if (wypiszNaEkran) {
			_charInfo();
			println(command, false);
		}
		return command;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		command = textInput.getText();
		textInput.setText("");

	}

	public class OPCJA_WYJDZ implements ActionListener {
		public void actionPerformed(ActionEvent Exit) {
			System.exit(0);

		}
	}

	public class Loadoption implements ActionListener {

		public void actionPerformed(ActionEvent Load) {
			Postaæ load = null;
  	      try
  	      {
  	    	  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d://btnSaveGame.dat"));
  	            load = (Postaæ)ois.readObject();
  	            ois.close();
  	      }catch(IOException i)
  	      {
  	         i.printStackTrace();
  	         return;
  	      }catch(ClassNotFoundException c)
  	      {
  	         System.out.println("Error: "+c);
  	         c.printStackTrace();
  	         return;
  	      }
  	      System.out.println(load.Name);
  	}
}

	public class btnSaveoption implements ActionListener {
		public void actionPerformed(ActionEvent btnSave) {
			Postaæ saveChar = new Postaæ();
    	    try {
    	    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d://btnSaveGame.dat"));
    	        oos.writeObject(saveChar);
    	        oos.flush();
    	        oos.close();
    	        
    	    } catch (IOException ex) {
    	        ex.printStackTrace();
    	    }
    	    System.out.println("File Saved!");
    	}
		}
	}
