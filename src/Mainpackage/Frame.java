package Mainpackage;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Networkstuuff.GameClientRe;

public class Frame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel panel;
	private int server;

	public Frame() {
		super("OUTLAW");
		setLayout(new BorderLayout());
		createMenus();
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel();
		this.add(panel);
		pack();
		this.setVisible(true);
	}

	private void createMenus() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);
		JMenu fileMenu = new JMenu("Game");
		JMenuItem newItem = new JMenuItem("Start with server");
		//JMenuItem otherItem = new JMenuItem("Start without server");
		newItem.addActionListener(this);// this allows the JMenuItem called newItem to tell the Frame that someone has chosen "new"
		//otherItem.addActionListener(this);
		fileMenu.add(newItem);
		//fileMenu.add(otherItem);
		menuBar.add(fileMenu);
		//menuBar.setUI(new BasicMenuBarUI());
		setJMenuBar(menuBar);
		//add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		panel.startGame(1);
		
	}
}
