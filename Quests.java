import java.util.Scanner;

public class Quests {
	
	private final int Quest_state_count = 1;
	
	boolean[] Quest_State = new boolean[Quest_state_count];
	
	public static void Quest1(){
		
		System.out.println("Ent: Znajdz dla mnie kwiat ostrokrzewu na skraju lasu Lorien\n1. Nie mam teraz czasu\n2. Biorê siê do roboty");
		Scanner skan = new Scanner(System.in);
		int odczyt = skan.nextInt();
		
		if(odczyt == 1){
			System.out.println("Nie to nie, co za gbur!");
			System.exit(0);
		}
		
		else if(odczyt == 2){
		System.out.println("W³asnie znajdujesz siê na skraju lasu Lorien.\nW oddali widzisz skupisko przeró¿nych krzewów...\nWœród nich odnajdujesz kwiat zwany ostrokrzewiem, o który prosi³ Ciê Ent.\nDostêpne akcje: \"Podnieœ ostrokrzew\"");
		boolean play = true;
		while(play){
		Scanner skan2 = new Scanner(System.in);
		String odczyt2 = skan2.nextLine();
		if(odczyt2.equals("Podnieœ ostrokrzew")){
			System.out.println("Podnosisz ostrokrzew\nWracasz do Enta...\n1.Oddaj Entowi ostrokrzew\n2.Nie oddawaj ostrokrzewu");
			Scanner skan3 = new Scanner(System.in);
			int odczyt3 = skan3.nextInt();
			
			if(odczyt3 == 1){
				System.out.println("Dziêkujê za przys³ugê");
				play = false;
			}
			else if(odczyt3 == 2){
				System.out.println("Niech ciê licho porwie!");
				play = false;
			}
			
			
		}
		else 
			System.out.println("Nie ma takiej akcji");
		}
		}
		else
			System.out.println("Nie rozumiem o co ci chodzi");
		
		
		
	}
}
