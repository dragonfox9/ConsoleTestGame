import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class NpcActions implements Serializable{
	private static final long serialVersionUID = 1912756214L;
	
	public static void getAction(int id){
		if(id == 1){
			action1();
		}
	}
	
	
	public static void action1(){
		boolean defaultState = true;
		if(defaultState){
			Console.print("<1.> ", false);
			Console.println("Jak tam id¹ prace przy mieczach?", false, new Color(246,224,78));
			
			int input = Console.playerInputInt(true);
			if(input == 1){
				Console.roboText("Wszystko ok, ale mi teraz nie przeszkadzaj.",40, new Color(224,224,224)); 
			}
			else
				Console.roboText("Nie wiem o co ci chodzi...", 40);
		}
	}
}

