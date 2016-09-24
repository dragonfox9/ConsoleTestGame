
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

public class Items{
	public static void buildItems(Item[] items,final int MAX_ITEMS){
			for(int id = 0;id < MAX_ITEMS;id++){
				items[id] = new Item(id,"","",id,id,id,id,id);
			}
			//int id, String name, String type, int typeId, int level, double weight, int amount, int att1, int att2, int def, int vit, int inte, int str, int dex, int health, int mana, int speed, int crit, int block, int dodge
			items[0].addItem(1, "Miecz", "Broñ",1, 1, 4.37, 0,20,37,0,0,0,10,0,0,0,0,0,0,0);
			items[1].addItem(2, "Miecz dwórêczny", "Broñ",1, 1, 5.25, 0,34,51,0,5,0,6,0,0,0,0,0,0,0);
			items[2].addItem(3, "£uk", "Broñ",1, 1, 4.15, 0,75,91,0,2,0,0,5,0,0,0,0,0,0);
			items[3].addItem(4, "Kamieñ", "Inne",12, 1, 2, 0,0,0,0,0,0,0,0,0,0,0,0,0,0);
			items[4].addItem(5, "Skórzana zbroja", "Pancerz",3, 1, 3.15, 0,0,0,25,10,0,0,3,150,0,-5,0,10,10);
	}
	
	public static void addItem(Item[] items, int id, int amount){
		items[id].addAmount(amount);
	}
	
	public static void printInventory(Item[] items){
		int itemSlotNumber = 1;
		boolean itemsExsist = false;
		int maxWeight = 60;
		double invWeight = 0;
		java.text.DecimalFormat df=new java.text.DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		for(int id = 0;id < items.length;id++){
			if(items[id].getAmount() >= 1){
				invWeight += (items[id].getAmount()*items[id].getWeight());
			}
		}
		Console.print("Twój ekwipunek!        Ciê¿ar ekwipunku: ", false);
		Console.print("[", false);
		if(invWeight <= 50){
			Console.print(""+df.format(invWeight), false,Color.GREEN);
			Console.print("/", false);
			Console.print(""+maxWeight, false);
			Console.println("]", false);
		}
		else if(invWeight >= 50 && invWeight < 60){
			Console.print(""+df.format(invWeight), false,Color.ORANGE);
			Console.print("/", false);
			Console.print(""+maxWeight, false);
			Console.println("]", false);
		}
		else if(invWeight >= 60){
			Console.print(""+df.format(invWeight), false,Color.RED);
			Console.print("/", false);
			Console.print(""+maxWeight, false);
			Console.println("]", false);
		}
		for(int id = 0; id < items.length;id++){
			if(items[id].getAmount() >= 1){
				Console.print(itemSlotNumber+". "+items[id].getName()+" [", false);
				Console.print(""+items[id].getAmount(), false,Color.GREEN);
				Console.print("] ", false);
				Console.print("[", false);
				Console.print(""+items[id].getWeight(), false,Color.GREEN);
				Console.println("]", false);
				itemSlotNumber++;
				itemsExsist = true;
			}
		}
		if(!itemsExsist){
			Console.println("Twój ekwipunek jest pusty!", false,Color.RED);
			Console.println("", false);
		}
		Console.println("", false);
		
	}
	
	public static void CheckItem(int x, int y, String item, Room[][] room, Item[] items, final int MAX_ITEMS){
		String validItemName = null;
		int itemId  = 0;
		boolean validItem = false;
		for(int i = 0;i < MAX_ITEMS;i++) {
			if (items[i].getName().contains(item) && room[x][y].getItemAmount(i) >= 1) {
				Console.println("Znalaz³em: "+items[i].getName(), false);
				validItem = true;
				validItemName = items[i].getName();
				itemId = i;
				break;
			}
		}
		
		if(validItem && room[x][y].getItemAmount(itemId) == 1){
			Console.println("Podnosisz "+validItemName,false);
			room[x][y].removeRoomItem(itemId, 1);
			items[itemId].addAmount(1);
		}
		
		else if(validItem && room[x][y].getItemAmount(itemId) > 1){
			String input;
			int validAmount = 0;
			Console.println("Ile przedmiotów chcesz podnieœæ: ", false);
			try{
			input = Console.playerInput(true);
			validAmount = Integer.parseInt(input);
			}catch(Exception e){
			}
			if(validAmount <= room[x][y].getItemAmount(itemId) && validAmount > 0){
				items[itemId].addAmount(validAmount);
				Console.println("Podnosisz "+validItemName+" w iloœci: "+ validAmount,false);
				room[x][y].removeRoomItem(itemId, validAmount);
			}
			else
				Console.println("Nie masz takiej iloœci przedmiotów!", false);
		}
		
		else{
			Console.println("Nie widzê nic takiego jak \""+item+"\"",false);
		}
}
	public static void removeItem(int x, int y, String item, Room[][] room, Item[] items, final int MAX_ITEMS){
		String validItemName = null;
		int itemId  = 0;
		boolean validItem = false;
		for(int i = 0;i < MAX_ITEMS;i++) {
			if (items[i].getName().contains(item) && items[i].getAmount() >= 1) {
				Console.println("Znalaz³em: "+items[i].getName(), false);
				validItem = true;
				validItemName = items[i].getName();
				itemId = i;
				break;
			}
		}
		
		if(validItem && items[itemId].getAmount() == 1){
			Console.println("Wyrzucasz "+validItemName,false);
			room[x][y].addRoomItem(itemId, 1);
			items[itemId].removeItem(1);
		}
		
		else if(validItem && items[itemId].getAmount() > 1){
			String input;
			int validAmount = 0;
			Console.println("Ile przedmiotów chcesz wyrzuciæ: ", false);
			try{
			input = Console.playerInput(true);
			validAmount = Integer.parseInt(input);
			}catch(Exception e){}
			if(validAmount <= items[itemId].getAmount() && validAmount > 0){
				items[itemId].subtractAmount(validAmount);
				Console.println("Wyrzucasz "+validItemName+" w iloœci: "+ validAmount,false);
				room[x][y].addRoomItem(itemId, validAmount);
			}
			else
				Console.println("B³êdna iloœæ przedmiotów!", false);
		}
		
		else{
			Console.println("Nie widzê nic takiego jak \""+item+"\"",false);
		}
}
}

class Item implements Serializable{
	private static final long serialVersionUID = 1246125341L;
	int id;
	int amount;
	int level;
    String type;
    int typeId;
    public String name;
    double weight;
    int att1;
    int att2;
    int def;
    int vit;
    int inte;
    int str;
    int dex;
    int health;
    int mana;
    int speed;
    int crit;
    int block;
    int dodge;
    
    
    private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "items" + File.separatorChar + Console.name;
    
    
    public Item(int id, String name, String type, int level, double weight, int amount, int att1, int att2){}
    
    public void addItem(int id, String name, String type, int typeId, int level, double weight, int amount, int att1, int att2, int def, int vit, int inte, int str, int dex, int health, int mana, int speed, int crit, int block, int dodge){
    	this.id = id;
    	this.name = name;
    	this.type = type;
    	this.typeId = typeId;
    	this.level = level;
    	this.weight = weight;
    	this.amount = amount;
    	this.att1 = att1;
    	this.att2 = att2;
    	this.def = def;
    	this.vit = vit;
    	this.inte = inte;
    	this.str = str;
    	this.dex = dex;
    	this.health = health;
    	this.mana = mana;
    	this.speed = speed;
    	this.crit = crit;
    	this.block = block;
    	this.dodge = dodge;
    }
    
    public void removeItem(int amount){
    	if(amount >= 1)
        	this.amount -= amount; 
    }
    public int getDef(){
    	return this.def;
    }
    public int getVit(){
    	return this.vit;
    }
    public int getInt(){
    	return this.inte;
    }
    public int getStr(){
    	return this.str;
    }
    public int getDex(){
    	return this.dex;
    }
    public int getHealth(){
    	return this.health;
    }
    public int getMana(){
    	return this.mana;
    }
    public int getSpeed(){
    	return this.speed;
    }
    public int getCrit(){
    	return this.crit;
    }
    public int getBlock(){
    	return this.block;
    }
    public int getDodge(){
    	return this.dodge;
    }
    public int getId(){
    	return this.id;
    }
    public String getType(){
    	return this.type;
    }
    public int getTypeId(){
    	return this.typeId;
    }
    public double getWeight(){
    	return this.weight;
    }
    public int getAtt1(){
    	return this.att1;
    }
    public int getAtt2(){
    	return this.att2;
    }
    public String getName(){
    	return this.name;
    }
     public int getAmount(){
    	return this.amount;
    }
    public void setAtt1(int att1){
    	this.att1 = att1;
    }
    public void setAtt2(int att2){
    	this.att2 = att2;
    }
    
    public void setAmount(int amount){
    	this.amount = amount;
    }
    
    public void addAtt1(int att1){
    	this.att1 += att1;
    }
    public void addAtt2(int att2){
    	this.att2 += att2;
    }
    
    public void addAmount(int amount){
    	this.amount += amount;
    }
    
    public void substractAtt1(int att1){
    	this.att1 -= att1;
    }
    public void substractAtt2(int att2){
    	this.att2 -= att2;
    }
    public void subtractAmount(int amount){
    	this.amount -= amount;
    }
    
    //zapisywanie itemów do pliku
  	public static void saveItem(Item[] items, final int MAX_ITEMS) throws IOException {
  		for(int i = 0;i < MAX_ITEMS;i++){
  					 File folder = new File(FILE_CHAR_FOLDER);
  				       folder.mkdirs();
  				       File file = new File(FILE_CHAR_FOLDER + File.separatorChar + "item"+(i+1)+".dat");
  				       try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
  				            output.writeObject(items[i]);
  				       }
  			}
  	    }
  		//wczytywanie itemów z pliku
  		public static Item loadItem(int itemId) throws IOException, ClassNotFoundException {
  					try (InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + "item"+(itemId)+".dat"); ObjectInput input = new ObjectInputStream(file);) {
  			            return (Item) input.readObject();
  			        }
  	    }
}
