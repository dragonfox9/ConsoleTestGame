import java.util.Scanner;

public class timerTest {
	static boolean op�znienie1 = true;
	static int cooldown = 10000;
	public static void main(String args[]){
		boolean play = true;
		while(play){
			Scanner skan = new Scanner(System.in);
			int odczyt = skan.nextInt();
			
			
			switch(odczyt){
			case 1:
				if(op�znienie1){
				System.out.println("W��czono 10 sekund op�znienia");
				op�znienie1 = false;
				skillCooldown();
				}
				else if(!op�znienie1){
					System.out.println("Tw�j skill si� jeszcze nie za�adowa�!");
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
			            System.out.println("Skill za�adowany!");
			            op�znienie1 = true;
			        }
			    }, 
			    cooldown
			);
	}
}
