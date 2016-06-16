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
	
	static String Name;
	static int Health;
	static int Exp;
	
	static Panel_Okien frame = new Panel_Okien();
	
	public static void œpij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("coœ posz³o nie tak!");
		}
	}
	

	
	public void _charInfo(){
		frame.print("<" + _getHealth() + "hp " + _getExp() + "xp" + ">", false, Color.CYAN);
	}

	public int _getHealth() {
		return Health;
	}

	public String _getCharName() {
		return Name;
	}

	public int _getExp() {
		return Exp;
	}
	public String _setCharName(String a){
		Name = a;
		return Name;
		
	}
	
    

    	public static void Start() {
    		frame.setTitle("TestGame");
    		frame.setVisible(true);
    		frame.println("Your name: "+Name, false,Color.GRAY);
    		frame.println("type \"continue\"", false, Color.GREEN);
    		boolean bool = true;
    		while(bool){
    			String text = frame.playerInput(true).toLowerCase();
    			if(text.equals("continue")){
    				bool = false;
    			}
    		}
    		next();

    	}
    	
    	public static void next(){
    		frame.println("Who are you?: ", false);
    		Name = frame.playerInput(true);
    		frame.print("Your name: ", false);
    		frame.println(Name, false,Color.RED);
    		Start();
    	}
    	
    	
    	
    }
