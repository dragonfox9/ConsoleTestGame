
import java.awt.Color;

public class Race {
	static String raceList[] = {"Elf", "Krasnolud", "Goblin", "Cz³owiek"};
	static String race;
	static boolean play = true;
	
	public static void setRace(){
		Console.print("Dostêpne rasy to: ",false);
		for(int i = 0;i < raceList.length;i++)
			Console.print(raceList[i]+", ", false,Color.GREEN);
		Console.println("", false);
		while(play){
			String rasa = Console.playerInput(false);
			for(int i=0; i<raceList.length;i++){
				if(raceList[i].equals(rasa)){
					race = rasa;
					play = false;
				}
			}
			if(play == true)
				Console.println("Nie ma takiej rasy", false, Color.RED);
		}
	}
	
	public static String getRace(){
		return race;
	}
	
	public static void setRace(String oldRace){
		race = oldRace;
	}
}
