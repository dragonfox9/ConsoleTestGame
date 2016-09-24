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

public class NpcList {
	public static void buildNpc(Npc[] npc, Item[] item, final int NPC_MAX_NUMBER){
		if(!Console.gameState){
			for(int id = 0;id < NPC_MAX_NUMBER;id++){
				try{
					npc[id] = Npc.loadNpc(id);
				}catch (ClassNotFoundException e) {
					npc[id] = new Npc(0,"null","null");
					npc[id].initalizeItems(item);
				} catch (FileNotFoundException e) {
					npc[id] = new Npc(0,"null","null");
					npc[id].initalizeItems(item);
				} catch (IOException e){
					npc[id] = new Npc(0,"null","null");
					npc[id].initalizeItems(item);
				}
			}
		}
		else{
			for(int id = 0; id < NPC_MAX_NUMBER;id++){
				npc[id] = new Npc(0,"null","null");
				npc[id].initalizeItems(item);
			}
			
			npc[0].addNpc(1, "Kowal", "Kowal");
			
			
			
			
			
			npc[0].addAction(1, 1);
		}
	}
}

class Npc implements Serializable{
	private static final long serialVersionUID = 9827354236L;
	int id;
	int level;
	int[] itemsId;
	int[] itemsAmount;
	int[] actions = new int[1];
	int[] questsId;
	String type;
	String name;
	
	private static final String FILE_CHAR_FOLDER = System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "NPC" + File.separatorChar + Console.name + File.separatorChar + "NpcList";
	
	public Npc(int id, String type, String name){}
	
	public void initalizeItems(Item[] item){
		this.itemsId = new int[Console.MAX_ITEMS];
		this.itemsAmount = new int[Console.MAX_ITEMS];
		for(int i = 0;i < Console.MAX_ITEMS;i++){
			this.itemsId[i] = i+1;
			this.itemsAmount[i] = 0;
			this.name = item[i].getName();
		}
	}
	public void addNpc(int id, String type, String name){
		this.id = id;
		this.type = type;
		this.name = name;
	}
	public void addShopItem(int itemId, int amount){
		this.itemsAmount[itemId-1] += amount;
	}
	public void removeShopItem(int itemId, int amount){
		if(this.itemsAmount[itemId] >= 1 && (this.itemsAmount[itemId] - amount) >= 0){
			this.itemsAmount[itemId] -= amount;
		}
	}
	public String getName(){
		return this.name;
	}
	public int getNpcId(){
		return this.id;
	}
	public void addAction(int number, int id){
		this.actions[number-1] = id;
	}
	@SuppressWarnings("static-access")
	public void getAction(int id){
		for(int i = 0; i < actions.length;i++){
			if(actions[i] == id){
				Console.npcActions.getAction(id);
			}
		}
	}
	
	//zapisywanie itemów do pliku
  	public static void saveNpc(Npc[] npc, final int NPC_MAX_NUMBER) throws IOException {
  		for(int id = 0;id < NPC_MAX_NUMBER;id++){
  			File folder = new File(FILE_CHAR_FOLDER);
  			folder.mkdirs();
  			File file = new File(FILE_CHAR_FOLDER + File.separatorChar + "Npc"+(id+1)+".npc");
  				try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
  					output.writeObject(npc[id]);
  				}
  		}
  	}
  		//wczytywanie itemów z pliku
  	public static Npc loadNpc(int slotId) throws IOException, ClassNotFoundException {
  		try (InputStream file = new FileInputStream(FILE_CHAR_FOLDER + File.separatorChar + "npc"+(slotId+1)+".npc"); ObjectInput input = new ObjectInputStream(file);) {
  			return (Npc) input.readObject();
  		}
  	}
}