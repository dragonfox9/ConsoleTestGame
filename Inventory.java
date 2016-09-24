
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

public class Inventory {
	public static void buildInventory(Slot[] slot, int currentMaxItemsInInventory){
		if(!Console.gameState){
			for (int id = 0; id < currentMaxItemsInInventory; id++) {
					try {
						slot[id] = Slot.loadItem(id+1);
					} catch (ClassNotFoundException e) {
						slot[id] = new Slot(-1,-1,null,null,-1,-1);
					} catch (FileNotFoundException e) {
						slot[id] = new Slot(-1,-1,null,null,-1,-1);
					} catch (IOException e) {
					}
			}
		}
		else{
			for(int id = 0;id < currentMaxItemsInInventory;id++){
				slot[id] = new Slot(-1,-1,null,null,-1,-1);
			}
	}
	}
	
	public static void printInventory(Slot[] slot, int currentMaxItemsInInventory){
		int itemSlotNumber = 1;
		boolean itemsExsist = false;
		int maxWeight = 60;
		double invWeight = 0;
		java.text.DecimalFormat df=new java.text.DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		for(int id = 0;id < slot.length;id++){
			if(slot[id].getAmount() >= 1){
				invWeight += (slot[id].getAmount()*slot[id].getWeight());
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
		for(int id = 0; id < slot.length;id++){
			if(slot[id].getAmount() >= 1){
				Console.print(itemSlotNumber+". "+slot[id].getName()+" [", false);
				Console.print(""+slot[id].getAmount(), false,Color.GREEN);
				Console.print("] ", false);
				Console.print("[", false);
				Console.print(""+slot[id].getWeight(), false,Color.GREEN);
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
	
	public static void checkItem(int x, int y, String itemName, Room[][] room, Item[] items, Slot[] slot, int MAX_ITEMS, int currentMaxItemsInInventory){
		
		String validItemName = null;
		int itemId  = 0;
		int slotItemId = 0;
		int emptySlot = 0;
		boolean validItem = false;
		boolean newItem = true;
		
		for(int i = 0; i < MAX_ITEMS;i++){
			if(items[i].getName().contains(itemName) && room[x][y].getItemAmount(i) >= 1){
				validItem = true;
				validItemName = items[i].getName();
				itemId = i;
				break;
			}
		}
		
		for(int i = 0; i < currentMaxItemsInInventory;i++){
			if(slot[i].getAmount() < 1){
				emptySlot = i;
				break;
			}
		}
		
		for(int i = 0; i < room[x][y].itemsId.length;i++){
			if(room[x][y].getItemId(itemId) == slot[i].getId()){
				slotItemId = i;
				break;
			}
		}
			
		if(validItem && room[x][y].getItemId(itemId) == slot[slotItemId].getId() && room[x][y].getItemAmount(itemId) == 1 && slot[slotItemId].getAmount() >= 1){
			slot[slotItemId].addItem(1);
			room[x][y].removeRoomItem(itemId, 1);
			Console.println("Podnosisz "+validItemName,false);
			newItem = false;
			
		}
		else if(validItem && room[x][y].getItemId(itemId) == slot[slotItemId].getId() && room[x][y].getItemAmount(itemId) > 1 && slot[slotItemId].getAmount() >= 1){
			String input;
			int validAmount = 0;
			Console.println("Ile przedmiotów chcesz podnieœæ: ", false);
			
			try{
				input = Console.playerInput(true);
				validAmount = Integer.parseInt(input);
			}catch(Exception e){}
			
			if(validAmount <= room[x][y].getItemAmount(itemId) && validAmount > 0){
				slot[slotItemId].addItem(validAmount);
				Console.println("Podnosisz "+validItemName+" w iloœci: "+ validAmount,false);
				room[x][y].removeRoomItem(itemId, validAmount);
				newItem = false;
				}
				else
					Console.println("B³êdna iloœæ przedmiotów", false);
		}
		
		else if(validItem && room[x][y].getItemAmount(itemId) == 1 && newItem){
			slot[emptySlot].addNewItem(emptySlot, items[itemId].getId(), items[itemId].getName(), items[itemId].getType(), items[itemId].getWeight(), 1);
			room[x][y].removeRoomItem(itemId, 1);
			Console.myCharacter.addCurrentItemsInInventory(1);
			Console.println("Podnosisz "+validItemName,false);
		}
		
		else if(validItem && room[x][y].getItemAmount(itemId) > 1 && newItem){
			String input;
			int validAmount = 0;
			Console.println("Ile przedmiotów chcesz podnieœæ: ", false);
			
			try{
				input = Console.playerInput(true);
				validAmount = Integer.parseInt(input);
			}catch(Exception e){}
			
			if(validAmount <= room[x][y].getItemAmount(itemId) && validAmount > 0 && newItem){
				slot[emptySlot].addNewItem(emptySlot, items[itemId].getId(), items[itemId].getName(), items[itemId].getType(), items[itemId].getWeight(), validAmount);
				room[x][y].removeRoomItem(itemId, validAmount);
				Console.myCharacter.addCurrentItemsInInventory(1);
				Console.println("Podnosisz "+validItemName+" w iloœci: "+ validAmount,false);
			}
			
			else
				Console.println("B³êdna iloœæ przedmiotów", false);
		}
		else{
			Console.print("Nie widzê nic takiego jak ", false);
			Console.println(itemName, false, Color.RED);
		}
	}
	
	public static void removeItem(int x, int y, String itemName, Room[][] room, Item[] items, Slot[] slot, int MAX_ITEMS, int currentMaxItemsInInventory){
		
		String validItemName = null;
		int itemId  = 0;
		int slotItemId = 0;
		int emptySlot = 0;
		boolean validItem = false;
		boolean newItem = true;
		
		for(int i = 0; i < MAX_ITEMS;i++){
			if(slot[i].getName().contains(itemName) &&  slot[i].getAmount() >= 1){
				validItem = true;
				validItemName = slot[i].getName();
				slotItemId = i;
				itemId = slot[i].getId();
				break;
			}
		}
			
		if(validItem && slot[slotItemId].getAmount() == 1){
			slot[slotItemId].removeItem(itemId, 1);
			room[x][y].addRoomItem(itemId, 1);
			Console.println("Wyrzucasz "+validItemName,false);
			newItem = false;
			
		}
		else if(validItem && slot[slotItemId].getAmount() > 1){
			String input;
			int validAmount = 0;
			Console.println("Ile przedmiotów chcesz wyrzuciæ: ", false);
			
			try{
				input = Console.playerInput(true);
				validAmount = Integer.parseInt(input);
			}catch(Exception e){}
			
			if(validAmount <= slot[slotItemId].getAmount() && validAmount > 0){
				slot[slotItemId].removeItem(itemId, validAmount);
				Console.println("Wyrzucasz "+validItemName+" w iloœci: "+ validAmount,false);
				room[x][y].addRoomItem(itemId, validAmount);
				newItem = false;
				}
				else
					Console.println("B³êdna iloœæ przedmiotów", false);
		}
	}
}

class Slot implements Serializable{
	private static final long serialVersionUID = 1246125341L;
	int slot;
	int id;
	String name;
    String type;
    double weight;
    int amount;
    private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "Inventory" + File.separatorChar + Console.name;
	
	public Slot(int slot, int id, String name, String type, int weight, int amount){}
	
	public void addNewItem(int slot, int id, String name, String type, double weight, int amount){
		this.slot = slot;
		this.id = id;
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.amount = amount;
	}
	
	public void addItem( int amount){
		if(this.amount >= 1){
			this.amount += amount;
		}
	}
	
	public void removeItem(int id, int amount){
		if(this.amount >=1 && (this.amount - amount) == 0){
			this.slot = 0;
			this.id = 0;
			this.type = null;
			this.weight = 0;
			this.amount = 0;
			this.name = null;
		}
		else if(this.amount >= 1 && (this.amount - amount) >= 1){
			this.amount -= amount;
		}
	}
	
	public int getSlot(){
		return this.slot;
	}
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getType(){
		return this.type;
	}
	public double getWeight(){
		return this.weight;
	}
	public int getAmount(){
		return this.amount;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
  	public static void saveInventory(Slot[] slot, int currentMaxItemsInInventory) throws IOException {
  		for(int id = 0;id < currentMaxItemsInInventory;id++){
  			File folder = new File(FILE_CHAR_FOLDER);
  			folder.mkdirs();
  			File file = new File(FILE_CHAR_FOLDER + File.separatorChar + "slot"+(id+1)+".dat");
  				try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
  					output.writeObject(slot[id]);
  				}
  		}
  	}
  	public static Slot loadItem(int slotId) throws IOException, ClassNotFoundException {
  		try (InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + "slot"+(slotId)+".dat"); ObjectInput input = new ObjectInputStream(file);) {
  			return (Slot) input.readObject();
  		}
  	}
}