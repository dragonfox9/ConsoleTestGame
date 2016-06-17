import java.io.Serializable;

public class Postaæ implements Serializable {
	private static final long serialVersionUID = 124240055341L;
	
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
	
	public void _setHealth(int Health){
		this.Health = Health;
		
	}
	
	
	public static void œpij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("coœ posz³o nie tak!");
		}
	}
    	
    }
