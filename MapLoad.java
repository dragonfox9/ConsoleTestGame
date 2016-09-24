
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MapLoad extends JFrame implements Serializable{
	private static final long serialVersionUID = 124912784L;
	public MapLoad(){
		super("Mapa");
		JPanel map = new Map();
		add(map);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				//Console.checkActiveMap = false;
			}
		});
		
	}
	}
