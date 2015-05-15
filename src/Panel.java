import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer gameTimer;
	private ClipPlayer sound = new ClipPlayer();
	private SpriteSheet sheet = new SpriteSheet();
	private ArrayList<Objects> bullets = new ArrayList<Objects>();
	private ArrayList<Objects> enemybullets = new ArrayList<Objects>();

	public Panel() {
		this.setPreferredSize(new Dimension(1600, 900));
		this.setBackground(Color.WHITE);
		Objects.panel = this;
		gameTimer = new Timer(1, this);
		sound.mapFile("shoot", "SHOOT.wav");
		sound.mapFile("break", "OUTLAW.wav");
		sound.mapFile("death", "DEATH.wav");
		setUpBindings();
	}

	private void setUpBindings() {
		//examples of keybindings
		System.out.println("binding keys");
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");
		this.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "shootOff");
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "up off");
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
		this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "down off");
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "up right");
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "left off");
		this.getActionMap().put("up", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("up off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("up");
			}
		});
		this.getActionMap().put("down", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("down off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("right off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("left off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("shoot", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.getActionMap().put("shootOff", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public void startGame() {
		// setup game then start timer
		gameTimer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//paint what is to be seen then the game has not yet started or has ended
		if (!gameTimer.isRunning()) {
			g.drawString("OUTLAW", 800, 450);
			return;
		}
		//paint game stuff

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// do stuff and then repaint
		repaint();
	}
}
