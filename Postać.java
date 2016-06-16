import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Postaæ implements Serializable {
	private static final long serialVersionUID = 1L;
	
	static String imiê;
	static int ¯ycie;
	static int Exp;
	
	static Panel_Okien okno = new Panel_Okien();
	
	public static void œpij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("coœ posz³o nie tak!");
		}
	}
	

	
	public void _charInfo(){
		okno.print("<" + _getHealth() + "hp " + _getExp() + "xp" + ">", false, Color.CYAN);
	}

	public int _getHealth() {
		return ¯ycie;
	}

	public String _getCharName() {
		return imiê;
	}

	public int _getExp() {
		return Exp;
	}
	public String _setCharName(String a){
		imiê = a;
		return imiê;
		
	}
	
    

    	public static void WPROWADZENIE() {
    		okno.setTitle("Gra");
    		okno.setVisible(true);
    		okno.println("Twoje imiê to: "+imiê, false,Color.GRAY);
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
    		okno.println("Podaj swoje imiê: ", false);
    		imiê = okno.WCZYTAJ_KOMENDE(true);
    		okno.print("Twoje imiê to: ", false);
    		okno.println(imiê, false,Color.RED);
    		WPROWADZENIE();
    	}
    	
    	
    	
    }
