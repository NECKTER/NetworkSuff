package Mainpackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class LoadMap {
//map is 160 pixles by 90 pixles
	private JFileChooser jfc = new JFileChooser();
	private BufferedImage mapImage;
	private int[][] map = new int[90][160];

	public LoadMap() {
		// TODO Auto-generated constructor stub
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
		JOptionPane.showMessageDialog(null, "Please choose a map to play on");
		load();
	}

	public LoadMap(String s) {
		// TODO Auto-generated constructor stub
		load(s);
	}

	private void load() {
		// TODO Auto-generated method stub
		jfc.showOpenDialog(null);
		File doc = jfc.getSelectedFile();
		try {
			mapImage = ImageIO.read(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mapImage.getWidth() == 160 && mapImage.getHeight() == 90) {
			fillArray();
		} else {
			JOptionPane.showMessageDialog(null, "This file is the wrong size for a map!!!\n Please choose a different image.");
			load();
		}
	}

	private void load(String s) {
		// TODO Auto-generated method stub
		try {
			mapImage = ImageIO.read(LoadMap.class.getResourceAsStream("images/" + s));
			System.out.println(s + " map was loaded");
			fillArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fillArray() {
		// TODO Auto-generated method stub
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
				if (!new Color(mapImage.getRGB(j, i)).equals(new Color(255, 255, 255))) {
					map[i][j] = 1;
				}
			}
		}
	}

	public void changeMap() {
		LoadMap temp = new LoadMap();
		map = temp.getMap();
	}

	public int[][] getMap() {
		return map;
	}
}
