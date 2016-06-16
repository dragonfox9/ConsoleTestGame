import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Postaæ implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String imiê;
	int ¯ycie;
	int Exp;
	
	Panel_Okien okno = new Panel_Okien();
	
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
	
	

	public void _saveGame(){
		Postaæ postaæ_save = new Postaæ();
		postaæ_save.imiê = "£ukasz";
	    try {
	    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d://saveGame.dat"));
	        oos.writeObject(postaæ_save);
	        
	        oos.flush();
	        oos.close();
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    System.out.println("Zapisano!");
	}
	
	public void _loadGame(){
		Postaæ load = null;
	      try
	      {
	    	  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d://saveGame.dat"));
	            load = (Postaæ)ois.readObject();
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
	      System.out.println(load.imiê);
	}
	
    

    	public void WPROWADZENIE() {
    		okno.setTitle("Gra");
    		okno.setVisible(true);
    		okno.println("Twoje imiê to: "+imiê, false,Color.GRAY);
    		okno.println("Coœ tam", false, Color.GREEN);
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
    		okno.println("Podaj swoje imiê: ", false);
    		imiê = okno.WCZYTAJ_KOMENDE(true);
    		okno.print("Twoje imiê to: ", false);
    		okno.println(imiê, false,Color.RED);
    		WPROWADZENIE();
    	}
    	
    	
    	
    }
