package Mainpackage;

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
	private Objects player = new Objects(200, 300, sheet.getPlayer().getHeight(null), sheet.getPlayer().getHeight(null), sheet.getPlayer());
	private boolean p1CanMove = true;
	private boolean p1Up = false;
	private boolean p1Right = false;
	private boolean p1Left = false;
	private boolean p1Down = false;
	private Color p1col = Color.green;
	private int p1moves = 0;

	public Panel() {
		this.setPreferredSize(new Dimension(1600, 900));
		this.setBackground(Color.WHITE);
		Objects.panel = this;
		gameTimer = new Timer(1, this);
		sound.mapFile("shoot", "SHOOT.wav");
		//block break and step sound
		sound.mapFile("break", "OUTLAW.wav");
		sound.mapFile("death", "DEATH.wav");
		setUpBindings();
		player.addImage(sheet.getPlayerStep());
		player.addShoot(sheet.getPlayerShoot());
		player.addShootUp(sheet.getPlayerShootUp());
		player.addShootDonw(sheet.getPlayerShootDown());
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
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "right off");
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "left off");
		this.getActionMap().put("up", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Up = true;
			}
		});
		this.getActionMap().put("up off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Up = false;
			}
		});
		this.getActionMap().put("down", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Down = true;
			}
		});
		this.getActionMap().put("down off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Down = false;
			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Right = true;
			}
		});
		this.getActionMap().put("right off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Right = false;
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Left = true;
			}
		});
		this.getActionMap().put("left off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Left = false;
			}
		});
		this.getActionMap().put("shoot", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1CanMove = false;
			}
		});
		this.getActionMap().put("shootOff", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				p1CanMove = true;
				sound.play("shoot");
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
			g.drawImage(sheet.getMain(), 0, 0, null);
			return;
		}
		playerAnimate(g);
		//paint game stuff

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// do stuff and then repaint
		movestuff();
		repaint();
	}

	private void movestuff() {
		// TODO Auto-generated method stub
		if (p1CanMove) {
			if (p1Down) {
				player.move(player.getX(), player.getY() + 1);
			}
			if (p1Up) {
				player.move(player.getX(), player.getY() - 1);
			}
			if (p1Left) {
				player.move(player.getX() - 1, player.getY());
			}
			if (p1Right) {
				player.move(player.getX() + 1, player.getY());
			}
		}
	}

	private void playerAnimate(Graphics g) {
		boolean moving = p1Up || p1Down || p1Left || p1Right;
		if (p1CanMove) {
			if (p1moves % 40 != 0 && moving) moving = false;
			if (p1moves == 81) p1moves = 1;
			player.draw(g, p1col, moving);
			if (moving && p1moves % 80 == 0) sound.play("break");
			p1moves++;
		} else {
			if (p1Up && !p1Down) player.drawShootUp(g, p1col);
			else
				if (p1Down && !p1Up) player.drawShootDown(g, p1col);
				else
					player.drawShoot(g, p1col);
		}

	}
}