import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
	private static JFrame Frame;
	private static JPanel Panel;
	private static JButton btnButton;
	static JTextField textInput;
	private static JTextPane print;
	private static JScrollPane Scrollpane;
	private final static String nowaLinia = "\n";
	private static JMenuBar MENU_BAR;
	private static JMenu MENU;
	private static JMenuItem btnSave;
	private static JMenuItem btnLoad;
	private static JMenuItem btnExit;
	private static String command = null;
	public static StyledDocument doc;
	private static final long serialVersionUID = 2L;
	
	private static final String FILE_FOLDER = "D:" + File.separatorChar + "test";
    private static final String FILE_NAME = "character.data";
    private static final String FILE_PATH = FILE_FOLDER + File.separatorChar + FILE_NAME;
	
	static Postaæ myCharacter = new Postaæ();
	
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		Panel_Okien nowe_okno = new Panel_Okien();
		nowe_okno.setTitle("gra");
		nowe_okno.setVisible(true);
		nowe_okno.setLocationRelativeTo(null);
		Beggining();
		
		
	}
	private static void saveCharacter(Postaæ myCharacter) throws IOException {
        File folder = new File(FILE_FOLDER);
        folder.mkdirs();
        File file = new File(FILE_PATH);
        try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
            output.writeObject(myCharacter);
        }
    }
 
    private static Postaæ loadCharacter() throws IOException, ClassNotFoundException {
        try (InputStream file = new FileInputStream(FILE_PATH); ObjectInput input = new ObjectInputStream(file);) {
            return (Postaæ) input.readObject();
        }
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
		Panel.setVisible(true);
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

		doc = print.getStyledDocument();

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
		Exitoption exit = new Exitoption();
		btnExit.addActionListener(exit);

	}

	public static void print(String s, boolean trace) {
		print(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}

	public static void print(String s, boolean trace, Color c) {

		javax.swing.text.Style style = print.addStyle("Style", null);
		StyleConstants.setForeground(style, c);

		if (trace) {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			String caller = elements[0].getClassName();

			s = caller + " -> " + s;

		}

		try {
			doc.insertString(doc.getLength(), s, style);
		} catch (Exception e) {
		}

		scrollBottom();

	}

	public static void println(String s, boolean trace) {
		println(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}

	public static void println(String s, boolean trace, Color c) {
		print(s + "\n", trace, c);
		scrollBottom();
	}

	public void scrollTop() {
		print.setCaretPosition(0);
	}

	public static void scrollBottom() {
		print.setCaretPosition(print.getDocument().getLength());
	}

	public void clearConsole() {
		print.setText("");
	}

	public String Load_cmd() {
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

	public static void _charInfo() {
		print("<" + myCharacter._getHealth() + "hp " + myCharacter._getExp() + "xp" + ">", false, Color.CYAN);
	}

	public static String playerInput(boolean wypiszNaEkran) {
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

	public class Exitoption implements ActionListener {
		public void actionPerformed(ActionEvent Exit) {
			System.exit(0);

		}
	}

	public class Loadoption implements ActionListener {
		public void actionPerformed(ActionEvent Load) {
			try {
				loadCharacter();
			} catch (ClassNotFoundException | IOException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			}
		}
	}

	public class btnSaveoption implements ActionListener {
		public void actionPerformed(ActionEvent btnSave) {
			try {
				saveCharacter(myCharacter);
			} catch (IOException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			}
		}
	}




	public static void Beggining() throws IOException, ClassNotFoundException{
		println(myCharacter._getCharName(),false,Color.RED);
		println("Type \"next\"", false, Color.GREEN);
		boolean prawda = true;
		while(prawda){
			String text = playerInput(true);
			if(text.equals("next")){
				prawda = false;
			}
			else if(text.equals("zapisz")){
				saveCharacter(myCharacter);
				
				Postaæ loadedCharacter = loadCharacter();
				println(loadedCharacter._getCharName(),false);
			}
			else if(text.equals("Wczytaj")){
				Postaæ loadedCharacter = loadCharacter();
				println(loadedCharacter._getCharName(),false);
			}
				
		}
		nextroom();
	}
	public static void nextroom() throws ClassNotFoundException, IOException{
		println("Podaj swoje imiê: ", false);
		String text = playerInput(true);
		myCharacter._setCharName(text);
		print("Twoje imiê to: ", false);
		println(myCharacter._getCharName(), false,Color.RED);
		Beggining();
	}
}


