import java.util.Scanner;

public class timerTest {
	static boolean opóznienie1 = true;
	static int cooldown = 10000;
	public static void main(String args[]){
		boolean play = true;
		while(play){
			Scanner skan = new Scanner(System.in);
			int odczyt = skan.nextInt();
			
			
			switch(odczyt){
			case 1:
				if(opóznienie1){
				System.out.println("W³¹czono 10 sekund opóznienia");
				opóznienie1 = false;
				skillCooldown();
				}
				else if(!opóznienie1){
					System.out.println("Twój skill siê jeszcze nie za³adowa³!");
				}
				break;
			default:
				System.out.println("Nie rozumiem...");
				break;
			}
		}
	}
	
	
	public static void skillCooldown(){
		new java.util.Timer().schedule(

			    new java.util.TimerTask() {
			        @Override
			        public void run() {
			            System.out.println("Skill za³adowany!");
			            opóznienie1 = true;
			        }
			    }, 
			    cooldown
			);
	}
}
