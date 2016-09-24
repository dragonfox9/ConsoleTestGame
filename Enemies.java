import java.io.Serializable;

public class Enemies{
	public static void buildEnemies(Enemy[] enemy, final int MAX_ENEMIES){
		for(int id = 0;id < MAX_ENEMIES;id++){
			enemy[id] = new Enemy(id, "",id,id,id,id,id,id,id,id,id,id,id,id);
		}
		
//							id:   name: level:  type:   amount:   att1:   att2:   att3:   att4:   health:   defence:   vit:   int:   str:   dex:   
		enemy[0].addNewEnemy(1, "Dziki pies", 1, 2, 0, 20, 35, 100, 15, 1, 1, 1, 1,1);
		enemy[1].addNewEnemy(2, "Wilk", 1, 2, 0, 15, 18, 100, 15, 20, 1, 23, 14, 12);
		enemy[2].addNewEnemy(3, "Nied¿wied¿", 1, 2, 0, 15, 18, 100, 15, 20, 1, 23, 14, 1);
		enemy[3].addNewEnemy(4, "Pantera", 1, 2, 0, 20, 35, 100, 15, 1, 1, 1, 1, 1);
		enemy[4].addNewEnemy(5, "Kotek", 1, 2, 0, 15, 18, 100, 15, 20, 1, 23, 14, 1);
		enemy[5].addNewEnemy(6, "Dzik", 1, 2, 0, 15, 18, 100, 15, 20, 1, 23, 14, 1);
		
	}
	
}

class Enemy implements Serializable{
	private static final long serialVersionUID = 2821109654L;
	private int id;
	private String name;
	private int exp;
	private int level;
	private int type;
	private int amount;
	private int att1;
	private int att2;
	private int health;
	private int defence;
	private int vitality;
	private int inteligence;
	private int strength;
	private int dexterity;
	
	public Enemy(int id, String name, int exp, int level, int type, int amount, int att1, int att2, int health, int defence, int vitality, int inteligence, int strength, int dexterity){}
	
	public void addNewEnemy(int id, String name, int exp, int level, int type, int amount, int att1, int att2, int health, int defence, int vitality, int inteligence, int strength, int dexterity){
		this.id = id;
		this.name = name;
		this.exp = exp;
		this.type = type;
		this.level = level;
		this.amount = amount;
		this.att1 = att1;
		this.att2 = att2;
		this.health = health;
		this.defence = defence;
		this.vitality = vitality;
		this.inteligence = inteligence;
		this.strength = strength;
		this.dexterity = dexterity;
	}
	
	//enemy basic settings
	public void removeEnemmy(int amount){
		if(amount >= 1)
			this.amount -= amount;
	}
	public int getAmount(){
    	return this.amount;
    }
	public String getName(){
		return this.name;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	public void addAmount(int amount){
		this.amount += amount;
	}
	public void substractAmount(int amount){
		this.amount -= amount;
	}
	
	//enemy stats
	public int getExp(){
		return this.exp;
	}
	public int getLevel(){
		return this.level;
	}
	public int getHealth(){
		return this.health;
	}
	public int getDefence(){
		return this.defence;
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
	public void setExp(int exp){
		this.exp = exp;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setDefence(int defence){
		this.defence = defence;
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
	public void addExp(int exp){
		this.exp += exp;
	}
	
	public void addLevel(int level){
		this.level += level;
	}
	public void addHealth(int health){
		this.health += health;
	}
	public void addDefence(int defence){
		this.defence += defence;
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
	public void substractExp(int exp){
		this.exp -= exp;
	}
	
	public void substractLevel(int level){
		this.level -= level;
	}
	public void substractHealth(int health){
		this.health -= health;
	}
	public void substractDefence(int defence){
		this.defence -= defence;
	}
	public void substractVitality(int vitality){
		this.vitality -= vitality;
	}
	public void substractInteligence(int inteligence){
		this.inteligence -= inteligence;
	}
	public void substractStrength(int strength){
		this.strength -= strength;
	}
	public void substractDexterity(int dexterity){
		this.dexterity -= dexterity;
	}
		
}
