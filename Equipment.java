
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Equipment{
	static Color itemColor = new Color(71,41,193);
	
	public static void buildEquipment(Eq[] eq, final int EQ_SLOTS){
		if(!Console.gameState){
			for(int i = 0;i < EQ_SLOTS;i++){
				try {
					eq[i] = Eq.loadEquipment(i);
				} catch (ClassNotFoundException e) {
					eq[i] = new Eq("", "null",0,10,0);
				} catch (FileNotFoundException e) {
					eq[i] = new Eq("", "null",0,10,0);
				} catch (IOException e){
				}
			}
		}
		else if(Console.gameState){
		for(int i = 0; i < EQ_SLOTS;i++){
			eq[i] = new Eq("", "null",0,10,0);
		}
		
		eq[0].setEqSlot("Broñ",1, 0);
		eq[1].setEqSlot("He³m",2, 0);
		eq[2].setEqSlot("Pancerz",3, 0);
		eq[3].setEqSlot("Rêkawica",4, 0);
		eq[4].setEqSlot("Spodnie",5, 0);
		eq[5].setEqSlot("Buty",6, 0);
		eq[6].setEqSlot("Naszyjnik",7, 0);
		eq[7].setEqSlot("Kolczyki",8, 0);
		eq[8].setEqSlot("Pierœcieñ",9, 0);
		eq[9].setEqSlot("Pierœcieñ2",10, 0);
		eq[10].setEqSlot("Bransoleta",11, 0);
		
		
		
		//eq[0].setAliases(9, "Broñ", "Bronie", "Broni", "Broni", "Broni", "Broni", "Broniach", "Broni¹", "Broniami");
		//eq[1].setAliases(12, "He³m", "He³my", "He³ma", "He³mów", "He³mowi", "He³mom", "He³ma", "He³mów", "He³mie", "He³mach", "He³mem", "He³mami" );
		//eq[2].setAliases(12, "Pancerz", "Pancerze", "Pancerza", "Pancerzy", "Pancerzowi", "Pancerzom", "Pancerza", "Pancerzy", "Pancerzu", "Pancerzach", "Pancerzem", "Pancerzami" );
		//eq[3].setAliases(12, "Rêkawica", "Rêkawice", "Rêkawicy", "Rêkawic", "Rêkawicy", "Rêkawicom", "Rêkawicy", "Rêkawic", "Rêkawicy", "Rêkawicach", "Rêkawic¹", "Rêkawicami");
		//eq[4].setAliases(7, "Brak", "Spodnie", "Spodni", "Spodni", "Spodni", "Spodniach", "Spodniami");
		//eq[5].setAliases(12, "But", "Buty", "Buta", "Butów", "Butowi", "Butom", "Buta", "Butów", "Bucie", "Butach", "Butem", "Butami");
		//eq[6].setAliases(aliasNumber, "Naszyjnik", "Naszyjniki", "Naszyjnika", "Naszyjników", "Naszyjnikowi", "Naszyjnikom", "Naszyjnika", "Naszyjników", "");
		
		
		
		
		
		
		eq[0].setAliases(3, "Broñ", "Bronie", "Broni");
		eq[1].setAliases(8, "He³m", "He³my", "He³ma", "He³mów", "He³mowi", "He³mom", "He³mem", "He³mie", "He³mem");
		eq[2].setAliases(5, "Pancerz", "Pancerze", "Pancerzy", "Panerzu", "Pancerzowi");
		eq[3].setAliases(4, "Rêkawice", "Rêkawice", "Rêkawicy", "Rêkawic");
		eq[4].setAliases(4, "Spodnie", "Spodnie", "Spodni", "Spodni");
		eq[5].setAliases(6, "Buy", "Buty","Buta", "Butów", "Bucie", "Butom");
		eq[6].setAliases(9, "Naszyjnik", "Naszyjniki", "Naszyjnika", "Naszyjników", "Naszyjniki", "Naszyjnikiem", "Naszyjniku", "Naszyjnika", "Naszyjnikowi");
		eq[7].setAliases(6, "Kolczyki", "Kolczyki", "Kolczyka", "Kolczyków", "Kolczykach", "Kolczyku");
		eq[8].setAliases(10, "Pierœcieñ[1]", "Pierœcienie[1]", "Pierœcienia[1]", "Pierœcieni[1]", "Pierœcieniu[1]", "Pierœcieniom[1]", "Pierœcieniach[1]", "Pierœcieniom[1]", "Pierœcienie[1]", "Pierœcieni[1]");
		eq[9].setAliases(10, "Pierœcieñ[2]", "Pierœcienie[2]", "Pierœcienia[2]", "Pierœcieni[2]", "Pierœcieniu[2]", "Pierœcieniom[2]", "Pierœcieniach[2]", "Pierœcieniom[2]", "Pierœcienie[2]", "Pierœcieni[2]");
		eq[10].setAliases(6, "Bransoleta", "Bransolety", "Bransolety", "Bransolet", "Bransoletami", "Bransoletach");
	}
}
	
	public static void printEq(Eq[] eq, Item[] item, final int EQ_SLOTS){
		for(int i = 0;i < EQ_SLOTS;i++){
			if(eq[i].getId() != 0){
				Console.print(eq[i].getAlias(0)+": ", false);
				Console.println(""+item[eq[i].getId()-1].getName(), false, itemColor);
			}
		}
	}
	public static void setEq(String item, Eq[] eq, Slot[] slot, Item[] items, Statistics stats, final int MAX_ITEMS, final int EQ_SLOTS, int currentMaxItemsInInventory){
		boolean validItem = false;
		boolean validSlot = false;
		int slotId = 0;
		int itemId = 0;
		int eqSlotId = 0;
		String itemName = null;
		
		
		for(int i = 0; i < MAX_ITEMS;i++){
			if(items[i].getName().contains(item)){
				itemName = items[i].getName();
				itemId = items[i].getId();
				slotId = i;
				validSlot = true;
				break;
			}
		}
		
		for(int i = 0; i < EQ_SLOTS;i++){
			if(items[slotId].getTypeId() == eq[i].getEqSlotNameId() && eq[i].getId() == 0){
				eqSlotId = i;
				validItem = true;
				break;
			}
		}
		
		for(int i = 0; i < slot.length;i++){
			if(slot[i].getId() == itemId && validItem && itemName != null){
				eq[eqSlotId].setItem(itemId);
				eq[eqSlotId].setName(itemName);
				slot[i].removeItem(itemId, 1);
				Console.print("Za³o¿y³eœ ", false);
				Console.println(itemName, false, itemColor);
				stats.changeWeaponStatsAdd(items, itemId);
				break;
			}
		}
		if(!validItem || !validSlot){
			Console.println("Nie mo¿esz za³o¿yæ tego przedmiotu", false);
		}
	}
	
	public static void removeEq(String item, Eq[] eq, Slot[] slot, Item[] items, Statistics stats, final int MAX_ITEMS, final int EQ_SLOTS, int currentMaxItemsInInventory){
		boolean validItem = false;
		boolean itemStatus = false;
		boolean addedItem = false;
		int slotId = 0;
		int itemId = 0;
		String itemName = null;
		int emptySlot = 0;
		
		for(int i = 0; i < EQ_SLOTS; i++){
			if(eq[i].getName().contains(item) && eq[i].getId() != 0){
				itemId = eq[i].getId();
				itemName = eq[i].getName();
				slotId = i;
				eq[i].setName("null");
				eq[i].setItem(0);
				validItem = true;
				break;
			}
		}
		
		for(int i = 0; i < currentMaxItemsInInventory;i++){
			if(slot[i].getId() == itemId && slot[i].getAmount() >= 1 && validItem){
				slot[i].addItem(1);
				itemStatus = true;
				addedItem = true;
				break;
			}
		}
		
		for(int i = 0; i < currentMaxItemsInInventory;i++){
			if(slot[i].getAmount() < 1 && validItem){
				emptySlot = i;
				break;
			}
		}
		
		for(int i = 0; i < MAX_ITEMS;i++){
			if(items[i].getId() == itemId && validItem){
				slotId = i;
			}
		}
		
		if(!itemStatus && validItem){
			slot[emptySlot].addNewItem(emptySlot, itemId, itemName, items[slotId].getName(), items[slotId].getWeight(), 1);
			addedItem = true;
		}
		else if(!addedItem){
			Console.println("Nie masz tego przedmiotu", false);
		}
		if(addedItem){
			Console.print("Zdj¹³eœ ",false);
			stats.changeWeaponStatsSubstract(items, itemId);
			Console.println(itemName, false, itemColor);
		}
		
	}
}

 class Eq implements Serializable{
	private static final long serialVersionUID = 13237891264L;
	int id;
	String itemName;
	int aliasNumber = 10;
	String eqSlotName;
	int eqSlotNameId;
	String[] alias = new String[aliasNumber];
	private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "Equipment" + File.separatorChar + Console.name;
	
	public Eq(String eqSlotName, String itemName, int eqSlotNameId, int aliasNumber, int id){
		this.eqSlotName = eqSlotName;
		this.eqSlotNameId = eqSlotNameId;
		this.itemName = itemName;
		this.id = id;
	}
	public void setEqSlot(String eqSlotName,int eqSlotNameId, int id){
		this.eqSlotName = eqSlotName;
		this.eqSlotNameId = eqSlotNameId;
		this.id = id;
	}
	public String getEqSlotName(){
		return this.eqSlotName;
	}
	public int getEqSlotNameId(){
		return this.eqSlotNameId;
	}
	
	public int getId(){
		return id;
	}
	public void setItem(int id){
		this.id = id;
	}
	public void setAliases(int aliasNumber, String alias1, String...aliasX) {
		this.aliasNumber = aliasNumber;
		this.alias[0] = alias1;
		for(int i = 1;i < aliasX.length;i++){
			this.alias[i] = aliasX[i-1];
		}
	}
	public void setName(String name){
		this.itemName = name;
	}
	public String getName(){
		return this.itemName;
	}
	public String getAlias(int aliasNumber){
		return alias[aliasNumber];
	}
	
	
	//zapisywanie itemów do pliku
  	public static void saveEquipment(Eq[] eq, final int EQ_SLOTS) throws IOException {
  		for(int id = 0;id < EQ_SLOTS;id++){
  			File folder = new File(FILE_CHAR_FOLDER);
  			folder.mkdirs();
  			File file = new File(FILE_CHAR_FOLDER + File.separatorChar + "EquipmentSlot"+(id+1)+".dat");
  				try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
  					output.writeObject(eq[id]);
  				}
  		}
  	}
  		//wczytywanie itemów z pliku
  	public static Eq loadEquipment(int slotId) throws IOException, ClassNotFoundException {
  		try (InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + "EquipmentSlot"+(slotId+1)+".dat"); ObjectInput input = new ObjectInputStream(file);) {
  			return (Eq) input.readObject();
  		}
  	}
	
}