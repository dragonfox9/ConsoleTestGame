import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Posta� implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String imi�;
	int �ycie;
	int Exp;
	
	Panel_Okien okno = new Panel_Okien();
	
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
	
	

	public void _saveGame(){
		Posta� posta�_save = new Posta�();
		posta�_save.imi� = "�ukasz";
	    try {
	    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d://saveGame.dat"));
	        oos.writeObject(posta�_save);
	        
	        oos.flush();
	        oos.close();
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    System.out.println("Zapisano!");
	}
	
	public void _loadGame(){
		Posta� load = null;
	      try
	      {
	    	  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d://saveGame.dat"));
	            load = (Posta�)ois.readObject();
	            ois.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Nie odnaleziono klasy");
	         c.printStackTrace();
	         return;
	      }
	      System.out.println(load.imi�);
	}
	
    

    	public void WPROWADZENIE() {
    		okno.setTitle("Gra");
    		okno.setVisible(true);
    		okno.println("Twoje imi� to: "+imi�, false,Color.GRAY);
    		okno.println("Co� tam", false, Color.GREEN);
    		boolean prawda = true;
    		while(prawda){
    			String text = okno.WCZYTAJ_KOMENDE(true);
    			if(text.equals("dalej")){
    				prawda = false;
    			}
    		}
    		dalej();

    	}
    	
    	public void dalej(){
    		okno.println("Podaj swoje imi�: ", false);
    		imi� = okno.WCZYTAJ_KOMENDE(true);
    		okno.print("Twoje imi� to: ", false);
    		okno.println(imi�, false,Color.RED);
    		WPROWADZENIE();
    	}
    	
    	
    	
    }
