
import java.awt.Color;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Character implements Serializable {
	private static final long serialVersionUID = 124240055341L;
	
	final int ROOM_WIDTH = 2;
	final int ROOM_HEIGHT = 2;
	private int currentMaxItemsInInventory = 10000; 
	private int currentItemsInInventory = 5;
	private String name;
	private String race;
	private String profession;
	private int health;
	private int exp;
	private int mapX = 100;
	private int mapY = 100;
	private int lastPosX = 0;
	private int lastPosY = 0;
	private int mapLocX;
	private int mapLocY;
	
	//Data ostatniego zapisu
	private String LastPlayDate;
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public void addCurrentMaxItemsInInventory(int amount){
		currentMaxItemsInInventory += amount;
	}
	
	public void addCurrentItemsInInventory(int amount){
		currentItemsInInventory += amount;
	}
	
	public int getCurrentItemsInInventory(){
		return currentItemsInInventory;
	}
	
	public int getCurrentMaxItemsInInventory(){
		return currentMaxItemsInInventory;
	}
	
	public int _getMapX(){
		return mapX;
	}
	
	public int _getMapY(){
		return mapY;
	}
	
	public void _setMapX(int x){
		mapX = x;
	}
	
	public void _setMapY(int y){
		mapY = y;
	}
	
	public void _setMapXY(int x, int y){
		mapX = x;
		mapY = y;
	}
	
	public void setMapLocation(int x, int y){
		mapLocX = x;
		mapLocY = y;
	}
	
	public int getMapLocationX(){
		return mapLocX;
	}
	
	public int getMapLocationY(){
		return mapLocY;
	}
	
	public void setLastPosXY(int lastPosX, int lastPosY){
		this.lastPosX = lastPosX;
		this.lastPosY = lastPosY;
	}
	
	public void setLastPosX(int lastPosX){
		this.lastPosX = lastPosX;
	}
	
	public void setLastPosY(int lastPosY){
		this.lastPosY = lastPosY;
	}
	
	public int getLastPosX(){
		return lastPosX;
	}
	
	public int getLastPosY(){
		return lastPosY;
	}

	public int getHealth() {
		return health;
	}

	public String getCharName() {
		return name;
	}
	
	public void setExp(int Exp){
		this.exp = this.exp+Exp;
	}

	public int getExp() {
		return exp;
	}
	
	public void setRace(String race){
		this.race = race;
	}
	
	public String getRace(){
		return race;
	}
	
	public void setProfession(String profession){
		this.profession = profession;
	}
	
	public String getProfession(){
		return profession;
	}
	
	public void setCharName(String name){
		this.name = name;	
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setLastPlayDate(Date data){
		LastPlayDate = df.format(data);
	}
	
	public String getLastPlayDate(){
		return LastPlayDate;
	}
	
	public static void œpij(int a) {
		try {
			Thread.sleep(a);
		} catch (Exception e) {
			System.out.println("coœ posz³o nie tak!");
		}
	}
    }
