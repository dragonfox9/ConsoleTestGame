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
import java.util.ArrayList;
import java.util.Scanner;

public class Rooms{
	public static void build(Room[][] room, final int WIDTH, final int HEIGHT, final int MAX_ITEMS){
		
		int roomNumber = 0;
		if(!Console.gameState){
			for (int i = 0; i < WIDTH; i++) {
				roomNumber++;
				for (int j = 0; j < HEIGHT; j++) {
					roomNumber++;
					try {
						room[i][j] = Room.loadRoom(roomNumber);
					} catch (ClassNotFoundException e) {
						room[i][j] = new Room(null,null,null,null,i, "", "", null);
						room[i][j].initalizeItems();
						room[i][j].initializeEnemies();
						room[i][j].initalizeNpc();
					} catch (IOException e) {
						room[i][j] = new Room(null,null,null,null,i, "", "", null);
						room[i][j].initalizeItems();
						room[i][j].initializeEnemies();
						room[i][j].initalizeNpc();
					}
				}
			}
		}
		else{
		
			for (int i = 0; i < WIDTH; i++) {
	            for (int j = 0; j < HEIGHT; j++) {
	                room[i][j] = new Room(null,null,null,null,i, "", "", null);
	               room[i][j].initalizeItems();
	               room[i][j].initializeEnemies();
	               room[i][j].initalizeNpc();
	            }
	        }

		room[0][0].setRoomNumber(1);
		room[0][0].setRoomName("Pokój 1");
		room[0][0].setMainText("Coœ tam blablabla");
		room[0][0].addRoomItem(3, 5);
		room[0][0].addRoomItem(4, 1);
		room[0][0].addRoomEnemy(1, 2);
		room[0][0].addRoomItem(5, 3);
		room[0][0].addRoomNpc(1);
		
		room[0][1].setRoomNumber(2);
		room[0][1].setRoomName("Pokój 2");
		room[0][1].setMainText("Coœ tam blablabla");
		room[0][1].addRoomItem(1, 10);
		
		room[1][0].setRoomNumber(3);
		room[1][0].setRoomName("Pokój 3");
		room[1][0].setMainText("Coœ tam blablabladsajadsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsds");
		room[1][0].addRoomItem(2, 2);
		
		room[1][1].setRoomNumber(4);
		room[1][1].setRoomName("Pokój 4");
		room[1][1].setMainText("Coœ tam blablabla");
		room[1][1].addRoomItem(1, 2);
		}
		
	
		
	}
	
	public static void printNpcAction(int x, int y, Room[][] room, Npc[] npc, String name){
		room[x][y].getNpcAction(npc, name);
	}
	
	public static void printRoom(Item[] items, Enemy[] enemies, Npc[] npc, Room[][] room, int x, int y,int WIDTH,int HEIGHT, final int MAX_ITEMS){
		Room.ways(x,y,WIDTH,HEIGHT);
		Console.println(room[x][y].getRoomName(), false, new Color(45,198,45));
		Console.println(room[x][y].getMainText(), false);
		room[x][y].getItems(items);
		room[x][y].getEnemies(enemies);
		room[x][y].getNpc(npc);
		Console.println("", false);
	}
	
	
	
}

class Room implements Serializable{
	private static final long serialVersionUID = 124640055341L;
	String roomName;
	String mainText;
	int[] itemsId;
	int[] itemsAmount;
	int[] enemiesId;
	int[] enemiesAmount;
	int[] npcId;
	boolean[] npcInRoom;
	String[] npcName;
	int roomNumber;
	int QuestNumber;
	private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "rooms" + File.separatorChar + Console.name;
	
	static Color c = new Color(45,198,45);
	
	public Room(int[] itemsId, int[] itemsAmount, int[] enemiesId, int[] enemiesAmount, int roomNumber,String roomName,String mainText,
			ArrayList<String> roomItems){	
	}
	
	//initalize room parts
	
	public void initalizeItems(){
		this.itemsId = new int[Console.MAX_ITEMS];
		this.itemsAmount = new int[Console.MAX_ITEMS];
		for(int i = 0;i < Console.MAX_ITEMS;i++){
			this.itemsId[i] = i+1;
			this.itemsAmount[i] = 0;
		}
	}
	
	public void initializeEnemies(){
		this.enemiesId = new int[Console.MAX_ENEMIES];
		this.enemiesAmount = new int[Console.MAX_ENEMIES];
		for(int i = 0; i < Console.MAX_ENEMIES;i++){
			this.enemiesId[i] = i+1;
			this.enemiesAmount[i] = 0;
		}
	}
	
	public void initalizeNpc(){
		this.npcId = new int[Console.NPC_MAX_NUMBER];
		this.npcInRoom = new boolean[Console.NPC_MAX_NUMBER];
		for(int i = 0;i < Console.NPC_MAX_NUMBER;i++){
			this.npcId[i] = i+1;
			this.npcInRoom[i] = false; 
		}
	}
	
	//npc settings
	
	public Integer getNpcId(int npcId){
		return this.enemiesId[npcId];
	}
	
	public boolean getNpcInRoom(int npcId){
		return npcInRoom[npcId];
	}
	
	public void addRoomNpc(int npcId){
			this.npcInRoom[npcId-1] = true;
	}
	
	public void removeRoomNpc(int npcId){
		if(this.npcInRoom[npcId] == true){
			this.npcInRoom[npcId] = false;
		}
	}
	
	public void getNpc(Npc[] npc){
		for(int i = 0; i < Console.NPC_MAX_NUMBER;i++){
			if(npcInRoom[i] == true){
				Console.print(""+npc[i].getName(), false, Color.GREEN);
				Console.println(" jest tutaj.", false);
			}
		}
	}
	
	public void getNpcAction(Npc[] npc, String name){
		for(int i = 0;i < Console.NPC_MAX_NUMBER;i++){
			if(npc[i].getName().contains(name) && npcInRoom[i] == true){
				npc[i].getAction(i+1);
			}
		}
	}
	
	//item settings
	
	public int getItemAmount(int itemsId){
		return itemsAmount[itemsId];
	}
	
	public void addRoomItem(int itemId, int amount){
			this.itemsAmount[itemId-1] += amount;
	}
	
	public void removeRoomItem(int itemId, int amount){
		if(this.itemsAmount[itemId] >= 1 && (this.itemsAmount[itemId] - amount) >= 0){
			this.itemsAmount[itemId] -= amount;
		}
	}
	
	public void getItems(Item[] items){
		for(int i = 0; i < Console.MAX_ITEMS;i++){
			if(itemsAmount[i] != 0){
				Console.println(""+items[i].getName()+"    iloœæ: "+itemsAmount[i], false);
			}
		}
	}
	
	public Integer getItemId(int itemId){
		return this.itemsId[itemId];
	}
	
	//enemy settings
	
	public Integer getEnemyId(int enemyId){
		return this.enemiesId[enemyId];
	}
	
	public int getEnemyAmount(int enemyId){
		return enemiesAmount[enemyId];
	}
	
	public void addRoomEnemy(int enemyId, int enemyAmount){
			this.enemiesAmount[enemyId-1] += enemyAmount;
	}
	
	public void addExistRoomEnemy(int enemyId, int enemyAmount){
		this.enemiesAmount[enemyId] += enemyAmount;
}
	
	public void removeRoomEnemy(int enemyId, int enemyAmount){
		if(this.enemiesAmount[enemyId] >= 1 && (this.enemiesAmount[enemyId] - enemyAmount) >= 0){
			this.enemiesAmount[enemyId] -= enemyAmount;
		}
	}
	
	public void getEnemies(Enemy[] enemies){
		for(int i = 0; i < Console.MAX_ENEMIES;i++){
			if(enemiesAmount[i] != 0){
				Console.println(""+enemies[i].getName()+"    iloœæ: "+enemiesAmount[i], false);
			}
		}
	}
	
	//room settings
	
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}
	
	public String getRoomName(){
		return this.roomName;
	}
	
	public void setMainText(String mainText){
		this.mainText = mainText;
	}
	
	public String getMainText(){
		return this.mainText;
	}
	
	public void setRoomNumber(int roomNumber){
		this.roomNumber = roomNumber;
	}
	
	public int getRoomNumber(){
		return this.roomNumber;
	}
	
	public static void ways(int x, int y, final int WIDTH, final int HEIGHT){
		if(x < WIDTH-1 && x > 0 && y < HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
		}
		else if(x < WIDTH-1 && x == 0 && y < HEIGHT-1 && y == 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
		}
		else if(x == WIDTH-1 && x > 0 && y == HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.println("", false);
		}
		else if(x == WIDTH-1 && x > 0 && y < HEIGHT-1 && y == 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
		}
		else if(x < WIDTH-1 && x == 0 && y == HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.println("", false);
		}
		else if(x < WIDTH-1 && x == 0 && y < HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
		}
		else if(x == WIDTH-1 && x > 0 && y < HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
		}
		else if(x < WIDTH-1 && x > 0 && y == HEIGHT-1 && y > 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("north", false,c);
			Console.println("", false);
		}
		else if(x < WIDTH-1 && x > 0 && y < HEIGHT-1 && y == 0){
			Console.print("Dostêpne ruchy: ", false);
			Console.print("west", false,c);
			Console.print(", ", false);
			Console.print("east", false,c);
			Console.print(", ", false);
			Console.print("south", false,c);
			Console.println("", false);
			
		}
	}
	
	//zapisywanie postaci do pliku
		public static void saveRoom(Room[][] room, final int WIDTH, final int HEIGHT) throws IOException {
			int roomNumber = 0;
			for(int i = 0;i < WIDTH;i++){
				roomNumber++;
				for(int j = 0;j < HEIGHT;j++){
					roomNumber++;
					  File folder = new File(FILE_CHAR_FOLDER);
				        folder.mkdirs();
				        File file = new File(FILE_CHAR_FOLDER+File.separatorChar +"room"+roomNumber+".dat");
				        try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
				            output.writeObject(room[i][j]);
				        }
				}
			}
	    }
		//wczytywanie postaci z pliku
		public static Room loadRoom(int roomNumber) throws IOException, ClassNotFoundException {
					try (InputStream file = new FileInputStream(FILE_CHAR_FOLDER+File.separatorChar + "room"+roomNumber+".dat"); ObjectInput input = new ObjectInputStream(file);) {
			            return (Room) input.readObject();
			        }
	    }
}