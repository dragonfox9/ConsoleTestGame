import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class testquest {
	public static void main(String args[]){
		
		int Select_Char = 0;
		int Max_Number = 1000000; 
		
		String[] DEBUG  = new String[3];
		
		DEBUG[0] = "test d³ugoœci 1";
		DEBUG[1] = "asd";
		DEBUG[2] = "oauifhqwifuhqfuiwhqufwhiqwuh";
		
		int WEIGHT[] = new int[4];
		int Tablica_Elf_Cie¿ar[] = {4,3,5,7};
		int Tablica_Cz³owiek_Cie¿ar[] = {2,6,3,5};
		
		String Tablica_Elf = "Mieczyk,Amulet,Buzdygan,Chleb";
		String Tablica_Cz³owiek = "Miecz obosieczny,Topór,£uk,Strza³y";
		ArrayList<String> Inventory = null;
		
		Scanner skan = new Scanner(System.in);
		int odczyt = skan.nextInt();
		
		if(odczyt == 1){
			Inventory = new ArrayList<String>(Arrays.asList(Tablica_Elf.split(",")));
			System.out.println(Inventory);
			Select_Char = 1;
			
		}
		else if(odczyt == 2){
			Inventory = new ArrayList<String>(Arrays.asList(Tablica_Cz³owiek.split(",")));
			System.out.println(Inventory);
			Select_Char = 2;
		}
		else
			System.out.println("Nie rozumiem..");
		
		if(Select_Char == 1){
		for(int i = 0;i < WEIGHT.length;i++){
			WEIGHT[i] = Tablica_Elf_Cie¿ar[i];
		}
		}
		else if(Select_Char == 2){
			for(int i = 0;i < WEIGHT.length;i++){
				WEIGHT[i] = Tablica_Cz³owiek_Cie¿ar[i];
			}
		}
		boolean ValidItem = false;
		boolean play = true;
		while(play){
			System.out.println("Wpisz \"find item\"");
			Scanner skan2 = new Scanner(System.in);
			String odczyt2 = skan2.nextLine();
			int maxLength = 0;
			
			if(odczyt2.equals("find item")){
				System.out.println("Podaj nazwê przedmiotu, którego szukasz: ");
				
				for(String str : Inventory){
					if(maxLength < str.length()){
						maxLength = str.length();
					}
				}
				System.out.println("MaxLength: "+maxLength);
					System.out.print("Nazwa przedmiotu:              ");
					int licznik = 0;
					do{
						System.out.print(" ");
						licznik++;
					}while(licznik < maxLength);
					System.out.println("Cie¿ar:");
				for(int i = 0;i < Inventory.size();i++){
					System.out.print((i+1)+". "+Inventory.get(i)+"                           ");
				int Licznik = maxLength-(maxLength - Inventory.get(i).length()+1);
					do{
						System.out.print(" ");
						Licznik++;
					}while(Licznik < maxLength );
					System.out.println("Ciê¿ar: "+WEIGHT[i]);
				}
				
				Scanner skan3 = new Scanner(System.in);
				String odczyt3 = skan3.nextLine();
				
				for(int i = 0;i < Inventory.size();i++){
					if(Inventory.get(i).equals(odczyt3)){
						System.out.println("Przedmiot o nazwie: "+Inventory.get(i)+" znajduje siê na miejscu: "+(i+1));
						ValidItem = true;
					}
				}
				if(ValidItem == false)
					System.out.println("Nie ma takiego przedmiotu");
			}
			else if(odczyt2.equals("debug")){
				for(String str : DEBUG){
					if(maxLength < str.length()){
						maxLength = str.length();
					}
				}
				System.out.println("MaxLength: "+maxLength);
					System.out.print("Nazwa przedmiotu:              ");
					int licznik = 0;
					do{
						System.out.print(" ");
						licznik++;
					}while(licznik < maxLength);
					System.out.println("Cie¿ar:");
				for(int i = 0;i < DEBUG.length;i++){
					System.out.print((i+1)+". "+DEBUG[i]+"                           ");
				int Licznik = maxLength-(maxLength - DEBUG[i].length()+1);
					do{
						System.out.print(" ");
						Licznik++;
					}while(Licznik < maxLength );
					System.out.println("Ciê¿ar: "+i);
				}
			}
			else
				System.out.println("Nie rozumiem...");
			ValidItem = false;
		}
	}
}
