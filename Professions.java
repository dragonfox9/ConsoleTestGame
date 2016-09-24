
import java.awt.Color;

public class Professions {
	static String profList[] = {"Mag", "Wojownik", "Skrytobójca", "£ucznik"};
	static String profession;
	static boolean play = true;
	
	public static void setProfession(){
		Console.print("Dostêpne klasy to: ",false);
		for(int i = 0;i < profList.length;i++)
			Console.print(profList[i]+", ", false,Color.GREEN);
			Console.println("", false);
		while(play){
			String prof = Console.playerInput(false);
			for(int i=0; i<profList.length;i++){
				if(profList[i].equals(prof)){
					profession = prof;
					play = false;
				}
			}
			if(play == true)
				Console.println("Nie ma takiej klasy", false, Color.RED);
		}
	}
	
	public static String getProfession(){
		return profession;
	}
	
	public static void setProfession(String prof){
		profession = prof;
	}
}
