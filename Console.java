
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
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

public class Console extends JFrame implements ActionListener, Serializable {
	private static JPanel Panel;
	static JTextField textInput;
	private static JTextPane print;
	private static JScrollPane Scrollpane;
	private static JMenuBar MENU_BAR;
	private static JMenu MENU;
	private static JMenuItem btnSave;
	private static JMenuItem btnLoad;
	private static JMenuItem btnExit;
	private static JMenuItem btnMap;
	private static String command = null;
	public static StyledDocument doc;
	private static final long serialVersionUID = 2L;
	//wczytuje datê z komputera
	static Date today = Calendar.getInstance().getTime();  
	
	//tworzenie referencji do informacji o Postaci
	static Character myCharacter = new Character();
	static String name;
	
	//buduje œwiat
	static final int NPC_MAX_NUMBER = 1;
	static final int MAX_ITEMS = 5;
	static final int EQ_SLOTS = 11;
	static final int MAX_ENEMIES = 6;
	static final int WIDTH = 2;
	static final int HEIGHT = 2;
	static Eq[] eq = new Eq[EQ_SLOTS];
    static Room[][] room = new Room[WIDTH][HEIGHT];
    static Slot[] inv = new Slot[myCharacter.getCurrentMaxItemsInInventory()];
    static Item[] itemy = new Item[MAX_ITEMS];
    static Enemy[] enemy = new Enemy[MAX_ENEMIES];
    static Npc[] npc = new Npc[NPC_MAX_NUMBER];
    static NpcActions npcActions = new NpcActions();
    static Statistics stats = new Statistics();
    static boolean gameState = false;
	
	//sprawdza, czy mapa jest w³¹czona.
	//static boolean checkActiveMap = true;
	
	static //blokuje przycisk Save, gdy¿ mo¿e nadpisaæ stan gry pustymi informacjami.
	boolean saveOption = false;
	
	private static final Color ³ososiowy = new Color(201,139,152);
	//tworzenie mapy
	//static Map mapa = new Map();
	
	//informacje na temat miejsca zapisu pliku postaci
	private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "save";
	private static final String FILE_NPC_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "NPC" + File.separatorChar + "Npc Actions";
	private static final String FILE_STATS_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "stats";
	
	//czcionka
    private static String FontName;
    private static int FontSize;
    
	
	//Tworzenie okna
	static Console nowe_okno = new Console();
	
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		nowe_okno.setTitle("gra");
		nowe_okno.setVisible(true);
		nowe_okno.setLocationRelativeTo(null);
		Start();
		
	}
	
	//zapisywanie npc
	public static void saveNpcAction(NpcActions npcActions) throws IOException {
			File folder = new File(FILE_NPC_FOLDER + File.separatorChar + name);
			folder.mkdirs();
			File file = new File(FILE_NPC_FOLDER + File.separatorChar + name + File.separatorChar + "Npc.actions");
				try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
					output.writeObject(npcActions);
				}
}
		//wczytywanie npc z pliku
	public static NpcActions loadNpcAction() throws IOException, ClassNotFoundException {
		try (InputStream file = new FileInputStream(FILE_NPC_FOLDER + File.separatorChar + name + File.separatorChar + "Npc.actions"); ObjectInput input = new ObjectInputStream(file);) {
			return (NpcActions) input.readObject();
		}
	}
	
	//zapisywanie statystyk
		public static void saveStats(Statistics stats) throws IOException {
				File folder = new File(FILE_STATS_FOLDER);
				folder.mkdirs();
				File file = new File(FILE_STATS_FOLDER + File.separatorChar + name + "_stats.stats");
					try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
						output.writeObject(stats);
					}
	}
			//wczytywanie statystyk z pliku
		public static Statistics loadStats() throws IOException, ClassNotFoundException {
			try (InputStream file = new FileInputStream(FILE_STATS_FOLDER + File.separatorChar + name + "_stats.stats"); ObjectInput input = new ObjectInputStream(file);) {
				return (Statistics) input.readObject();
			}
		}
	
	//zapisywanie postaci do pliku
	private static void saveCharacter(Character myCharacter) throws IOException {
        File folder = new File(FILE_CHAR_FOLDER);
        folder.mkdirs();
        File file = new File(FILE_CHAR_FOLDER + File.separatorChar + myCharacter.getCharName());
        try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
            output.writeObject(myCharacter);
        }
    }
	//wczytywanie postaci z pliku
	private static void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        try{
        println("Dostêpne postacie: ",false);
        for(File f : filesList){
            if(f.isDirectory())
                getAllFiles(f);
            if(f.isFile()){
                println(f.getName(),false,new Color(45,195,45));
            }
        }
        }catch(Exception e){
        	println("Nie ma ¿adnych dostêpnych postaci!", false,Color.RED);
        	Start();
        }
        println("Podaj nazwê postaci, któr¹ chcesz wczytaæ: ",false);
    }
	
	private static String getPlayerSaveName(File curDir, String getChar){
		File[] filesList = curDir.listFiles();
		for(File f : filesList){
			if(f.equals(FILE_CHAR_FOLDER + File.separatorChar + getChar));
			else
				break;
		}
		return getChar;
	}
	
	private static Character loadCharacter() throws IOException, ClassNotFoundException {
		File curDir = new File(System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "save");
		getAllFiles(curDir);
		
		ObjectInput input = null;
		boolean play = true;
		while(play){
			String getChar = playerInput(false);
			try{
				InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + getPlayerSaveName(curDir,getChar)); 
				input = new ObjectInputStream(file);
				play = false;
				return (Character) input.readObject();
			}catch(Exception e){
				println("Ta postaæ nie istnieje",false,Color.RED);
				play = true;
			}
		}
        return (Character) input.readObject();
    }
	
	private static void checkCharExsist(){
		File curDir = new File(System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "save");
		File[] filesList = curDir.listFiles();
		ObjectInput input = null;
		boolean play = true;
		while(play){
			String getChar = playerInput(false);
			try{
				InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + getPlayerSaveName(curDir,getChar)); 
				input = new ObjectInputStream(file);
				play = true;
				println("Ta nazwa postaci jest ju¿ zajêta!",false,Color.RED);
			}catch(Exception e){
				play = false;
				myCharacter.setCharName(getChar);
			}
		}
	}


	public Console() {
		
		// przyjmuje podstawowy wygl¹d okienek przypisanych przez system operacyjny.
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

		
		//czcionka wczytywana z pliku txt
		File file = new File("config/font.txt");
	    Scanner in;
		try {
			in = new Scanner(file);
			FontName = in.nextLine();
			FontSize = in.nextInt();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Brak pliku lub uszkodzony plik font.txt!", "B³¹d", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		Font font = new Font(FontName, Font.BOLD, FontSize);
				
		// Komponenty
		

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
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				myCharacter.setLastPlayDate(today);
				myCharacter.setProfession(Professions.getProfession());
				myCharacter.setRace(Race.getRace());
				try {
					saveCharacter(myCharacter);
					saveStats(stats);
					Npc.saveNpc(npc, NPC_MAX_NUMBER);
					Room.saveRoom(room, WIDTH, HEIGHT);
					Slot.saveInventory(inv, myCharacter.getCurrentItemsInInventory());
					Eq.saveEquipment(eq, EQ_SLOTS);
					saveNpcAction(npcActions);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
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
		Saveoption save = new Saveoption();
		btnSave.addActionListener(save);

		btnLoad = new JMenuItem("Load game");
		MENU.add(btnLoad);
		Loadoption load = new Loadoption();
		btnLoad.addActionListener(load);
		
		btnMap = new JMenuItem("Map");
		//MENU.add(btnMap); //b³¹d mapy (bug uniemo¿liwia pisanie po ponownym jej w³¹czeniu)
		Mapoption map = new Mapoption();
		btnMap.addActionListener(map);

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

	public static void clearConsole() {
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
		print("<" + myCharacter.getHealth() + "hp " + myCharacter.getExp() + "xp" + ">", false, Color.CYAN);
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
	
	public static int playerInputInt(boolean wypiszNaEkran){
		int number = 0;
		try{
		String input = playerInput(wypiszNaEkran);
		int text = Integer.parseInt(input);
		number = text;
		return text;
		}catch(Exception e){}
		return number;
	}
	
	public static void roboText(String text, int timer, Color c){
        String[] characterTable = text.split("");
		for (String character : characterTable) {
			Character.œpij(timer);
            print(character,false,c);
        }
		Console.println("", false);
	}
	
	public static void roboText(String text, int timer){
        String[] characterTable = text.split("");
		for (String character : characterTable) {
			Character.œpij(timer);
            print(character,false);
        }
		Console.println("", false);
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
	
	public static void newMap(){
		MapLoad mapLoad = new MapLoad();
	}
	


	public class Loadoption implements ActionListener {
		public void actionPerformed(ActionEvent Load) {
			}
		
			
		
	}
	
	public class Mapoption implements ActionListener {
		public void actionPerformed(ActionEvent Map){
			//if(!checkActiveMap){
				//MapLoad mapLoad = new MapLoad();
				//checkActiveMap = true;
				//Load_cmd();
			//}
		}
	}

	public class Saveoption implements ActionListener {
		public void actionPerformed(ActionEvent Save) {
			myCharacter.setLastPlayDate(today);
			myCharacter.setProfession(Professions.getProfession());
			myCharacter.setRace(Race.getRace());
			if(saveOption){
			try {
				saveCharacter(myCharacter);
				saveStats(stats);
				Room.saveRoom(room, WIDTH, HEIGHT);
				Npc.saveNpc(npc, NPC_MAX_NUMBER);
				Slot.saveInventory(inv, myCharacter.getCurrentItemsInInventory());
				Eq.saveEquipment(eq, EQ_SLOTS);
				saveNpcAction(npcActions);
			} catch (IOException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			}
			}else
				println("Nie mo¿esz teraz tego wykonaæ...",false,Color.RED);
		}
	}
	


	public static void Start(){
		println("testowanie kolorów",false,³ososiowy);
		//Map.checkMap();
		menuStart();
	}
	public static void menuStart(){
		
		boolean bool = true;
		println("Co chcesz zrobiæ?\n1.Wczytaj Postaæ\n2.Stwórz now¹ Postaæ",false);
		while(bool){
		String text = playerInput(false).toLowerCase();
		if(text.equals("1")){
			try {
				myCharacter = loadCharacter();
				name = myCharacter.getCharName();
				npcActions = loadNpcAction();
				stats = loadStats();
			} catch (ClassNotFoundException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			} catch (IOException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			}
			println("Ostatnio grano: "+myCharacter.getLastPlayDate(),false);
			//Map.setXY(myCharacter._getMapX(), myCharacter._getMapY());
			//Map.setLoc(myCharacter.getMapLocationX(),myCharacter.getMapLocationY());
			//Map.repainter();
			Race.setRace(myCharacter.getRace());
			Professions.setProfession(myCharacter.getProfession());
			print("Pomyœlnie wczytano Postaæ: ",false);
			println(myCharacter.getCharName()+"\n\n\n",false,Color.GREEN);
			print("Rasa: ",false);
			println(myCharacter.getRace(),false,Color.GREEN);
			print("Klasa: ",false);
			println(myCharacter.getProfession(),false,Color.GREEN);
			print("Wczytujê œwiat",false,Color.RED);
			Character.œpij(400);
			print(".",false,Color.RED);
			Character.œpij(400);
			print(".",false,Color.RED);
			Character.œpij(400);
			print(".",false,Color.RED);
			Character.œpij(400);
			println(".",false,Color.RED);
			Character.œpij(400);
			println("Wczytano œwiat!",false,Color.RED);
			println("Koordynaty mapy to: x: "+Map.mapGetX()+" y: "+Map.mapGetY(),false);
			println("Lokacja mapy to: "+Map.getMapLoc(),false);
			Character.œpij(2000);
			clearConsole();
			bool = false;
			saveOption = true;
			Main_Game();
		}
		else if(text.equals("2")){
			gameState = true;
			println("Wprowadz imiê: ",false);
			checkCharExsist();
			myCharacter.setHealth(100);
			myCharacter.setLastPlayDate(today);
			Race.setRace();
			Professions.setProfession();
			name = myCharacter.getCharName();
			println("Pomyœlnie stworzono: "+myCharacter.getCharName()+", ¯ycie:"+myCharacter.getHealth(),false);
			try {
				saveCharacter(myCharacter);
				saveStats(stats);
				Npc.saveNpc(npc, NPC_MAX_NUMBER);
				Room.saveRoom(room, WIDTH, HEIGHT);
				Slot.saveInventory(inv, myCharacter.getCurrentItemsInInventory());
				Eq.saveEquipment(eq, EQ_SLOTS);
				saveNpcAction(npcActions);
			} catch (IOException e) {
				println("Error: "+e,false,Color.RED);
				e.printStackTrace();
			}
			bool = false;
		}
		else println("Nie rozumiem co mówiê..",false);
		}
		saveOption = true;
		Main_Game();
		
		
		
	}
	
	public static void Main_Game(){
		Inventory.buildInventory(inv, myCharacter.getCurrentMaxItemsInInventory());
		Items.buildItems(itemy, MAX_ITEMS);
		Enemies.buildEnemies(enemy, MAX_ENEMIES);
		Rooms.build(room, WIDTH, HEIGHT,MAX_ITEMS);
		NpcList.buildNpc(npc, itemy, NPC_MAX_NUMBER);
		Equipment.buildEquipment(eq, EQ_SLOTS);
        int x = myCharacter.getLastPosX();
        int y = myCharacter.getLastPosY();
        Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
        //Map.setLoc(x,y);
        //Map.repainter();
        
		
		boolean playing = true;
        while (playing) {
        	myCharacter.setLastPosXY(x, y);
            String input = playerInput(true);

            if (input.equals("n")) {
                if (y > 0) {
                    y--;
                    Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
                    //Map.setLoc(x, y);
                } else {
                    println("Nie mo¿esz iœæ w tê stronê.",false);
                }
            } else if (input.equals("s")) {
                if (y < HEIGHT - 1) {
                    y++;
                    Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
                    //Map.setLoc(x, y);
                } else {
                    println("Nie mo¿esz iœæ w tê stronê.",false);
                }
            } else if (input.equals("e")) {
                if (x > 0) {
                    x--;
                    Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
                    //Map.setLoc(x, y);
                } else {
                    println("Nie mo¿esz iœæ w tê stronê.",false);
                }
            } else if (input.equals("w")) {
                if (x < WIDTH - 1) {
                    x++;
                    Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
                    //Map.setLoc(x, y);
                } else {
                    println("Nie mo¿esz iœæ w tê stronê.",false);
                }
            }
            
            else if (input.length() > 8  && input.substring(0, 8).equals("podnieœ ")) {
	        	if (input.substring(0, input.indexOf(' ')).equals("podnieœ")) {
	        		if (input.substring(input.indexOf(' ')).length() > 1) {
	        			String item = input.substring(input.indexOf(' ') + 1);
	                	Inventory.checkItem(x, y, item, room, itemy, inv, MAX_ITEMS, myCharacter.getCurrentItemsInInventory());
	        		}	
	        	}
	        }
			
			else if (input.length() > 7  && input.substring(0, 7).equals("wyrzuæ ")) {
	        	if (input.substring(0, input.indexOf(' ')).equals("wyrzuæ")) {
	        		if (input.substring(input.indexOf(' ')).length() > 1) {
	        			String item = input.substring(input.indexOf(' ') + 1);
	                	Inventory.removeItem(x, y, item, room, itemy, inv, MAX_ITEMS, myCharacter.getCurrentItemsInInventory());
	        		}	
	        	}
	        }
            
			else if (input.length() > 6  && input.substring(0, 6).equals("za³ó¿ ")) {
	        	if (input.substring(0, input.indexOf(' ')).equals("za³ó¿")) {
	        		if (input.substring(input.indexOf(' ')).length() > 1) {
	        			String item = input.substring(input.indexOf(' ') + 1);
	                	Equipment.setEq(item, eq, inv, itemy, stats, MAX_ITEMS, EQ_SLOTS, myCharacter.getCurrentItemsInInventory());
	        		}	
	        	}
	        }
            
			else if (input.length() > 8  && input.substring(0, 8).equals("zdejmij ")) {
	        	if (input.substring(0, input.indexOf(' ')).equals("zdejmij")) {
	        		if (input.substring(input.indexOf(' ')).length() > 1) {
	        			String item = input.substring(input.indexOf(' ') + 1);
	                	Equipment.removeEq(item, eq, inv, itemy, stats, MAX_ITEMS, EQ_SLOTS, myCharacter.getCurrentItemsInInventory());
	        		}	
	        	}
	        }
            
			else if (input.length() > 8  && input.substring(0, 8).equals("zagadaj ")) {
	        	if (input.substring(0, input.indexOf(' ')).equals("zagadaj")) {
	        		if (input.substring(input.indexOf(' ')).length() > 1) {
	        			String name = input.substring(input.indexOf(' ') + 1);
	                	Rooms.printNpcAction(x, y, room, npc, name);
	        		}	
	        	}
	        }
            
			else if(input.equals("wyposa¿enie")){
				Equipment.printEq(eq, itemy, EQ_SLOTS);
	        }
            
			else if(input.equals("pomoc")){
				println("Dostêpne komendy: podnieœ, wyrzuæ, za³ó¿, zdejmij, zagadaj, statystyki, w, e, s, n, inv", false);
			}
            
			else if(input.equals("popatrz")){
            	Rooms.printRoom(itemy, enemy, npc, room, x, y,WIDTH,HEIGHT,MAX_ITEMS);
            }
            
            else if(input.equals("inv") || input.equals("inventory")){
            	Inventory.printInventory(inv, myCharacter.getCurrentItemsInInventory());
            }
            
            else if(input.contains("statystyki") || input.contains("staty")){
            	Statistics.printAllStats(stats);
            }       
        }	
	}		
}