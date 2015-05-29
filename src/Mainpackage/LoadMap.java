package Mainpackage;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class LoadMap {
//map is 160 pixles by 90 pixles
	private JFileChooser jfc = new JFileChooser();
	private Image mapImage;
	private ArrayList<Point> map = new ArrayList<>();

	public LoadMap() {
		// TODO Auto-generated constructor stub
		JFileChooser jfc = new JFileChooser();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			jfc.updateUI();
			UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			jfc.setCurrentDirectory(new File(LoadMap.class.getResource("LoadMap.class").toURI().toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jfc.showOpenDialog(null);
		File doc = jfc.getSelectedFile();

	}
}
