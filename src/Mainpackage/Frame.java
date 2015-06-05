package Mainpackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel panel;

	public Frame() {
		super("OUTLAW");
		setLayout(new BorderLayout());
		createMenus();
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel(1);
		this.add(panel);
		pack();
		this.setVisible(true);
	}

	private void createMenus() {
		// TODO Auto-generated method stub
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);
		JMenu fileMenu = new JMenu("Game");

		JMenuItem newItem = new JMenuItem("Start");
		newItem.addActionListener(this);
		fileMenu.add(newItem);

		JMenuItem toggleForeverbullet = new JMenuItem("ForeverBullet");
		toggleForeverbullet.addActionListener(this);
		fileMenu.add(toggleForeverbullet);
		
		JMenuItem map = new JMenuItem("Change Map");
		map.addActionListener(this);
		fileMenu.add(map);

		JMenu speedMenu = new JMenu("Scroll Speed");
		JMenuItem normal = new JMenuItem("Normal");
		normal.addActionListener(this);
		speedMenu.add(normal);

		JMenuItem fast = new JMenuItem("Fast");
		fast.addActionListener(this);
		speedMenu.add(fast);

		JMenuItem none = new JMenuItem("None");
		none.addActionListener(this);
		speedMenu.add(none);

		fileMenu.add(speedMenu);
		// this allows the JMenuItem called
		// newItem to tell the Frame that
		// someone has chosen "new"

		menuBar.add(fileMenu);
		// menuBar.setUI(new BasicMenuBarUI());
		setJMenuBar(menuBar);
		// add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Start") panel.startGame();
		if (e.getActionCommand() == "ForeverBullet") panel.toggleForeverbullet();
		if (e.getActionCommand() == "Normal") panel.setScrollSpeed(333);
		if (e.getActionCommand() == "Fast") panel.setScrollSpeed(10);
		if (e.getActionCommand() == "None") panel.setScrollSpeed(Integer.MAX_VALUE);
		if (e.getActionCommand() == "Change Map") panel.changeMap();
	}
}
