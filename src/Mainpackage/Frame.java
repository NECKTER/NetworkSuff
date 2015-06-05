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
		JMenuItem newItem = new JMenuItem("Start");
		JMenuItem toggleForeverbullet = new JMenuItem("ForeverBullet");
		newItem.addActionListener(this);// this allows the JMenuItem called
		toggleForeverbullet.addActionListener(this);// newItem to tell the Frame that
		// someone has chosen "new"
		fileMenu.add(newItem);
		fileMenu.add(toggleForeverbullet);
		menuBar.add(fileMenu);
		// menuBar.setUI(new BasicMenuBarUI());
		setJMenuBar(menuBar);
		// add(menuBar, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Start") panel.startGame(1);
		if (e.getActionCommand() == "ForeverBullet") {
			panel.toggleForeverbullet();
			System.out.println("forever");
		}
	}
}
