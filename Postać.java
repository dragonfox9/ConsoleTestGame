import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Posta� implements Serializable {
	private static final long serialVersionUID = 1L;
	
	static String imi�;
	static int �ycie;
	static int Exp;
	
	static Panel_Okien okno = new Panel_Okien();
	
	public static void �pij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("co� posz�o nie tak!");
		}
	}
	

	
	public void _charInfo(){
		okno.print("<" + _getHealth() + "hp " + _getExp() + "xp" + ">", false, Color.CYAN);
	}

	public int _getHealth() {
		return �ycie;
	}

	public String _getCharName() {
		return imi�;
	}

	public int _getExp() {
		return Exp;
	}
	public String _setCharName(String a){
		imi� = a;
		return imi�;
		
	}
	
    

    	public static void WPROWADZENIE() {
    		okno.setTitle("Gra");
    		okno.setVisible(true);
    		okno.println("Twoje imi� to: "+imi�, false,Color.GRAY);
    		okno.println("wpisz \"dalej\"", false, Color.GREEN);
    		boolean prawda = true;
    		while(prawda){
    			String text = okno.WCZYTAJ_KOMENDE(true).toLowerCase();
    			if(text.equals("dalej")){
    				prawda = false;
    			}
    		}
    		dalej();

    	}
    	
    	public static void dalej(){
    		okno.println("Podaj swoje imi�: ", false);
    		imi� = okno.WCZYTAJ_KOMENDE(true);
    		okno.print("Twoje imi� to: ", false);
    		okno.println(imi�, false,Color.RED);
    		WPROWADZENIE();
    	}
    	
    	
    	
    }
