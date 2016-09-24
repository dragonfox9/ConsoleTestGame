import java.awt.Color;
import java.io.Serializable;

public class Statistics implements Serializable{    
	private static final long serialVersionUID = 5361855232490338707L;
	private String name;
    private String race;
    private String profession;
    private int exp;
    private int vitality;
    private int inteligence;
	private int strength;
	private int dexterity;
	private int mana;
	private int health;
	private int defence;
	private int speed;
	private int criticalChance;
	private int block;
	private int dodge;
	private int att1;
	private int att2;
	
	
	public static void printAllStats(Statistics stats){
		Console.println("Twoje statystyki:", false);
		Console.print("¯ycie: ", false);
		Console.println(""+stats.getHealth(), false, Color.GREEN);
		Console.print("Mana: ", false);
		Console.println(""+stats.getMana(), false, Color.GREEN);
		Console.print("Atak: ", false);
		Console.print(""+stats.getAtt1(), false, Color.GREEN);
		Console.print(" - ", false);
		Console.println(""+stats.getAtt2(), false,Color.GREEN);
		Console.print("Witalnoœæ: ", false);
		Console.println(""+stats.getVitality(), false, Color.GREEN);
		Console.print("Inteligencja: ", false);
		Console.println(""+stats.getInteligence(), false, Color.GREEN);
		Console.print("Si³a: ", false);
		Console.println(""+stats.getStrength(), false, Color.GREEN);
		Console.print("Zrêcznoœæ: ", false);
		Console.println(""+stats.getDexterity(), false, Color.GREEN);
		Console.print("Obrona: ", false);
		Console.println(""+stats.getDefence(), false, Color.GREEN);
		Console.print("Szybkoœæ ataku: ", false);
		Console.println(""+stats.getSpeed(), false, Color.GREEN);
		Console.print("Szansa na cios krytyczny: ", false);
		Console.println(""+stats.getCriticalChance(), false, Color.GREEN);
		Console.print("Blok: ", false);
		Console.println(""+stats.getBlock(), false, Color.GREEN);
		Console.print("Unik: ", false);
		Console.println(""+stats.getDodge(), false, Color.GREEN);
	}
	

	public String  getName(){
		return this.name;
	}
	public String getRace(){
		return this.race;
	}
	public String getProffesion(){
		return this.profession;
	}
	public int getExp(){
		return this.exp;
	}
	public int getVitality(){
		return this.vitality;
	}
	public int getInteligence(){
		return this.inteligence;
	}
	public int getStrength(){
		return this.strength;
	}
	public int getDexterity(){
		return this.dexterity;
	}
	public int getMana(){
		return this.mana;
	}
	public int getHealth(){
		return this.health;
	}
	public int getDefence(){
		return this.defence;
	}
	public int getSpeed(){
		return this.speed;
	}
	public int getCriticalChance(){
		return this.criticalChance;
	}
	public int getBlock(){
		return this.block;
	}
    public int getDodge(){
    	return this.dodge;
    }
    public int getAtt1(){
    	return this.att1;
    }
    public int getAtt2(){
    	return this.att2;
    }
    public void setExp(int exp){
    	this.exp = exp;
    }
	public void setVitality(int vitality){
		this.vitality = vitality;
	}
	public void setInteligence(int inteligence){
		this.inteligence = inteligence;
	}
	public void setStrength(int strength){
		this.strength = strength;
	}
	public void setDexterity(int dexterity){
		this.dexterity = dexterity;
	}
	public void setMana(int mana){
		this.mana = mana;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setDefence(int defence){
		this.defence = defence;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public 	void setCriticalChance(int criticalChance){
		this.criticalChance = criticalChance;
	}
	public void setBlock(int block){
		this.block = block;
	}
	public void setDodge(int dodge){
		this.dodge = dodge;
	}
	public void setAtt1(int att1){
		this.att1 = att1;
	}
	public void setAtt2(int att2){
		this.att2 = att2;
	}
    public void setAllStats(String name, int exp, int vitality,int inteligence, int strength,int dexterity, int mana, int health, int defence, int speed, int criticalChance, int block, int dodge, int att1, int att2){
		this.exp = exp;
    	this.vitality = vitality;
		this.inteligence = inteligence;
		this.strength = strength;
		this.dexterity = dexterity;
		this.mana = mana;
		this.health = health;
		this.defence = defence;
		this.speed = speed;
		this.criticalChance = criticalChance;
		this.block = block;
		this.dodge = dodge;
		this.att1 = att1;
		this.att2 = att2;
	}
    public void addExp(int exp){
    	this.exp += exp;
    }
	public void addVitality(int vitality){
		this.vitality += vitality;
	}
	public void addInteligence(int inteligence){
		this.inteligence += inteligence;
	}
	public void addStrength(int strength){
		this.strength += strength;
	}
	public void addDexterity(int dexterity){
		this.dexterity += dexterity;
	}
	 public void addMana(int mana){
		this.mana += mana;
	}
    public void addHealth(int health){
		this.health += health;
	}
    public void addDefence(int defence){
		this.defence += defence;
	}
    public void addSpeed(int speed){
		this.speed += speed;
	}
	public void addCriticalChance(int criticalChance){
		this.criticalChance += criticalChance;
	}
	public void addBlock(int block){
		this.block += block;
	}
	public void addDodge(int dodge){
		this.dodge += dodge;
	}
	public void addAtt1(int att1){
		this.att1 += att1;
	}
	public void addAtt2(int att2){
		this.att2 += att2;
	}
	public void subtractExp(int exp){
		this.exp -= exp;
	}
	public void subtractVitality(int vitality){
		this.vitality -= vitality;
	}
	public void subtractInteligence(int inteligence){
		this.inteligence -= inteligence;
	}
	public void subtractStrength(int strength){
		this.strength -= strength;
	}
	public void subtractDexterity(int dexterity){
		this.dexterity -= dexterity;
	}
	public void subtractMana(int mana){
		this.mana -= mana;
	}
	public void subtractHealth(int health){
		this.health -= health;
	}
	public void subtractDefence(int defence){
		this.defence -= defence;
	}
	public void subtractSpeed(int speed){
		this.speed -= speed;
	}
	public void subtractCriticalChance(int criticalChance){
		this.criticalChance -= criticalChance;
	}
	public void subtractBlock(int block){
		this.block -= block;
	}
	public void subtractDodge(int dodge){
		this.dodge -= dodge;
	}
	public void subtractAtt1(int att1){
		this.att1 -= att1;
	}
	public void subtractAtt2(int att2){
		this.att2 -= att2;
	}
	
	public void setClass(String race, String profession){
		if(race.equals("Cz³owiek") && profession.equals("£ucznik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Cz³owiek") && profession.equals("Wojownik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Cz³owiek") && profession.equals("Skrytobójca")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Cz³owiek") && profession.equals("Mag")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Elf") && profession.equals("£ucznik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Elf") && profession.equals("Wojownik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Elf") && profession.equals("Skrytobójca")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Elf") && profession.equals("Mag")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Krasnolud") && profession.equals("£ucznik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Krasnolud") && profession.equals("Wojownik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Krasnolud") && profession.equals("Skrytobójca")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Krasnolud") && profession.equals("Mag")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Goblin") && profession.equals("£ucznik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Goblin") && profession.equals("Wojownik")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Goblin") && profession.equals("Skrytobójca")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
		else if(race.equals("Goblin") && profession.equals("Mag")){
			setAllStats("",1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		}
	}
	
	public void changeWeaponStatsAdd(Item[] item, int id){
		int slotId = 0;
		for(int i = 0; i < item.length; i++){
			if(item[i].getId() == id){
				slotId = i;
			}
		}
			
			addAtt1(item[slotId].getAtt1());
			addAtt2(item[slotId].getAtt2());
			addVitality(item[slotId].getVit());
			addInteligence(item[slotId].getInt());
			addStrength(item[slotId].getStr());
			addDexterity(item[slotId].getDex());
			addMana(item[slotId].getMana());
			addHealth(item[slotId].getHealth());
			addDefence(item[slotId].getDef());
			addSpeed(item[slotId].getSpeed());
			addCriticalChance(item[slotId].getCrit());
			addBlock(item[slotId].getBlock());
			addDodge(item[slotId].getDodge());
	}
	public void changeWeaponStatsSubstract(Item[] item, int id){
		int slotId = 0;
		for(int i = 0; i < item.length; i++){
			if(item[i].getId() == id){
				slotId = i;
			}
		}
			
			subtractAtt1(item[slotId].getAtt1());
			subtractAtt2(item[slotId].getAtt2());
			subtractVitality(item[slotId].getVit());
			subtractInteligence(item[slotId].getInt());
			subtractStrength(item[slotId].getStr());
			subtractDexterity(item[slotId].getDex());
			subtractMana(item[slotId].getMana());
			subtractHealth(item[slotId].getHealth());
			subtractDefence(item[slotId].getDef());
			subtractSpeed(item[slotId].getSpeed());
			subtractCriticalChance(item[slotId].getCrit());
			subtractBlock(item[slotId].getBlock());
			subtractDodge(item[slotId].getDodge());
	}

}