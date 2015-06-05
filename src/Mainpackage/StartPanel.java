package Mainpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener {
	private SpriteSheet sheet = new SpriteSheet();
	private LoadMap loadmap;
	private int[][] map = new int[90][160];
	int l=0;
	StartPanel(){
		loadmap = new LoadMap("backround.png");
		map = loadmap.getMap();
		this.setPreferredSize(new Dimension(1600, 900));
		this.setBackground(Color.GRAY);
		JButton p1= new JButton("Start with Player 1");
		p1.setBounds(200, 300, 90, 40);
		p1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		startactual(1);
	}
	private int startactual(int onle){
		return onle;
		
	}
	private void drawMap(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) g.fillRect(j * 10, i * 10, 10, 10);
			}
		}
	}

}
