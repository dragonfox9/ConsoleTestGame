
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Map extends JPanel implements Serializable {
	private static final long serialVersionUID = 124912784L;
	
	private static int imageFileLength = 4;
	
	private static BufferedImage HeadImage;
	private static BufferedImage PlayerMarker;
	private static BufferedImage[] image = new BufferedImage[100];
	private static int imageNumber = 0;
	static File[] imageFile = new File[imageFileLength];
	static File PlayMarker = new File("marker.jpg");
	static long MarkerSize = PlayMarker.length();
	
	static long FileSize[] = new long[imageFileLength];
	
	static int x = 0;
	static int y = 0;
	static int mapLocation = 0;
	//static MapLoad obraz = new MapLoad();
	
	public Map() {
		
		super();
		
		//wielkoœæ plików obrazków
		FileSize[0] = 70500;
		FileSize[1] = 73602;
		FileSize[2] = 261047;
		FileSize[3] = 220031;
		
		
		//wczytywanie obrazków
		
		File loadImage = new File("config/mapy.txt");
		try {
			Scanner MapNameScanner = new Scanner(loadImage);
			for(int i = 0;i < imageFileLength;i++){
				imageFile[i] = new File(MapNameScanner.nextLine()+".jpg");
			}
		} catch (FileNotFoundException e) {
			//Console.println("B³¹d: "+e, false, Color.RED);
			e.printStackTrace();
		}
		
		//inicjalizacja obrazków
		try{
			for(int i = 0; i < imageFileLength;i++){
			image[i] = ImageIO.read(imageFile[i]);
			PlayerMarker = ImageIO.read(PlayMarker);
			}
		}catch(IOException e){
			//Console.println("B³¹d: "+e, false, Color.RED);
		}
		
		HeadImage = image[imageNumber];
		setDimension();
	}
	
	public void setDimension(){
		Dimension dimension = new Dimension(HeadImage.getWidth(), HeadImage.getHeight());
		setPreferredSize(dimension);
	}
	
	//sprawdza, czy pliki w folderze mapy nie zosta³y podmienione na inne.
	public static void checkMap(){
		for(int i = 0;i < imageFileLength;i++){
			if(imageFile[i].length() == FileSize[i] && MarkerSize == 983){
			}	
			else{
				JOptionPane.showMessageDialog(null, "B³¹d wczytywania mapy!", "B³¹d", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(HeadImage, 0, 0, this);
		g2d.drawImage(PlayerMarker,x,y,this);
	}
	
	public static void setLoc(int x, int y){
		if(x == 0 && y == 0)
			HeadImage = image[0];
		else if(x == 1 && y == 0)
			HeadImage = image[1];
		else if(x == 0 && y == 1)
			HeadImage = image[2];
		else if(x == 1 && y == 1)
			HeadImage = image[3];
		Map.repainter();
	}
	
	public static int getMapLoc(){
		return mapLocation;
	}
	
	public static int mapGetX(){
		return x;
	}
	
	public static int mapGetY(){
		return y;
	}
	
	public static void reloadMap(){
		Map mapa = new Map();
		mapa.setVisible(true);
	}
	public static void setXY(int a,int b){
		x = a;
		y = b;
	}
	
	public static void repainter(){
		//obraz.repaint();
	}
}
