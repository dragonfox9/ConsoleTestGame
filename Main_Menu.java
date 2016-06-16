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

//public class Main_Menu extends Postaæ {
	
	public static void main(String[] args) {
		Panel_Okien frame = new Panel_Okien();
		frame.setVisible(true);
		
		Postaæ myCharacter = new Postaæ();
		
		boolean bool = true;
		while(bool){
		String text = frame.playerInput(true);
		}
		
		
	}
	private static void saveCharacter(Character myCharacter) throws IOException {
        File folder = new File("d://");
        folder.mkdirs();
        File file = new File("save.dat");
        try (OutputStream outputStream = new FileOutputStream(file); ObjectOutput output = new ObjectOutputStream(outputStream)) {
            output.writeObject(myCharacter);
        }
    }
 
    private static Character loadCharacter() throws IOException, ClassNotFoundException {
        try (InputStream file = new FileInputStream("d://save.dat"); ObjectInput input = new ObjectInputStream(file);) {
            return (Character) input.readObject();
        }
    }

}
