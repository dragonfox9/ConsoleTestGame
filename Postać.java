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
	
	private String Name;
	private int Health;
	private int Exp;

	public int _getHealth() {
		return Health;
	}

	public String _getCharName() {
		return Name;
	}

	public int _getExp() {
		return Exp;
	}
	public void _setCharName(String Name){
		this.Name = Name;
		
	}
	
	
	public static void œpij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("coœ posz³o nie tak!");
		}
	}
    	
    }
