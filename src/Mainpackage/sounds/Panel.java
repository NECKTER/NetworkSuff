package Mainpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
	private ArrayList<Objects> garbage = new ArrayList<Objects>();
	private Objects player = new Objects(200, 300, sheet.getPlayer().getHeight(null), sheet.getPlayer().getHeight(null), sheet.getPlayer());
	private boolean p1CanMove = true;
	private boolean p1Up = false;
	private boolean p1Right = false;
	private boolean p1Left = false;
	private boolean p1Down = false;
	private int p1Shooting = 0;
	private Color p1col = Color.green;
	private int p1moves = 0;
	private int p1Bullet = 0;
	private boolean p1WasHit = false;
	private Objects player2 = new Objects(1300, 300, sheet.getPlayer().getHeight(null), sheet.getPlayer().getHeight(null), sheet.getPlayer());
	private boolean p2CanMove = true;
	private boolean p2Up = false;
	private boolean p2Right = false;
	private boolean p2Left = false;
	private boolean p2Down = false;
	private int p2Shooting = 0;
	private Color p2col = Color.red;
	private int p2moves = 0;
	private int p2Bullet = 0;
	private boolean p2WasHit = false;
	private int ticks = 0;
	private int ticks2 = 0;

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
		player.addDeath(sheet.getPlayerDeath());
		player2.addImage(sheet.getPlayerStep());
		player2.addShoot(sheet.getPlayerShoot());
		player2.addShootUp(sheet.getPlayerShootUp());
		player2.addShootDonw(sheet.getPlayerShootDown());
		player2.addDeath(sheet.getPlayerDeath());
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
				setP1Up(true);
//				setP2Up(true);
			}
		});
		this.getActionMap().put("up off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Up(false);
//				setP2Up(false);
			}
		});
		this.getActionMap().put("down", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Down(true);
//				setP2Down(true);

			}
		});
		this.getActionMap().put("down off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Down(false);
//				setP2Down(false);
			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Right(true);
//				setP2Right(true);
			}
		});
		this.getActionMap().put("right off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Right(false);
//				setP2Right(false);
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Left(true);
//				setP2Left(true);
			}
		});
		this.getActionMap().put("left off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Left(false);
//				setP2Left(false);
			}
		});
		this.getActionMap().put("shoot", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Shooting(1);
//				setP2Shooting(1);
			}
		});
		this.getActionMap().put("shootOff", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setP1Shooting(-1);
//				setP2Shooting(-1);
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
			player.drawDeath(g, p1col);
			return;
		}
		drawBullets(g);
//		p1col = colorize();
//		p2col = colorize();
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
		// TODO Auto-generated method stub\
		checkForColision();
		checkShooting();
		if (bullets.isEmpty()) {
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
		if (enemybullets.isEmpty()) {
			if (p2CanMove) {
				if (p2Down) {
					player2.move(player2.getX(), player2.getY() + 1);
				}
				if (p2Up) {
					player2.move(player2.getX(), player2.getY() - 1);
				}
				if (p2Left) {
					player2.move(player2.getX() - 1, player2.getY());
				}
				if (p2Right) {
					player2.move(player2.getX() + 1, player2.getY());
				}
			}
		}
	}

	private void playerAnimate(Graphics g) {
		boolean moving = p1Up || p1Down || p1Left || p1Right;
		if (p1WasHit) {
			p1CanMove = false;
			if (ticks == 33) {
				p1WasHit = false;
				ticks = 0;
				p1CanMove = true;
			}
			ticks++;
			player.drawDeath(g, p1col);
		} else

			if (p1CanMove && bullets.isEmpty()) {
				if (p1moves % 40 != 0 && moving) moving = false;
				if (p1moves == 81) p1moves = 1;
				player.draw(g, p1col, moving);
				if (moving && p1moves % 80 == 0) sound.play("break");
				p1moves++;
			} else {
				if (p1Up && !p1Down) {
					player.drawShootUp(g, p1col);
					p1Bullet = 1;
				} else {
					if (p1Down && !p1Up) {
						player.drawShootDown(g, p1col);
						p1Bullet = -1;
					} else {
						player.drawShoot(g, p1col);
						p1Bullet = 0;
					}
				}

			}
		boolean moving2 = p2Up || p2Down || p2Left || p2Right;
		player2.flipAll();
		if (p2WasHit) {
			p2CanMove = false;
			if (ticks2 == 33) {
				p2WasHit = false;
				ticks2 = 0;
				p2CanMove = true;
			}
			player2.drawDeath(g, p2col);
			ticks2++;
		} else {
			if (p2CanMove && enemybullets.isEmpty()) {
				if (p2moves % 40 != 0 && moving2) moving2 = false;
				if (p2moves == 81) p2moves = 1;
				player2.draw(g, p2col, moving2);
				if (moving2 && p2moves % 80 == 0) sound.play("break");
				p2moves++;
			} else {
				if (p2Up && !p2Down) {
					player2.drawShootUp(g, p2col);
					p2Bullet = 1;
				} else {
					if (p2Down && !p2Up) {
						player2.drawShootDown(g, p2col);
						p2Bullet = -1;
					} else {
						player2.drawShoot(g, p2col);
						p2Bullet = 0;
					}
				}

			}
		}
		player2.flipAll();

	}

	private void drawBullets(Graphics g) {
		for (Objects objects : bullets) {
			objects.draw(g);
		}
		for (Objects objects : enemybullets) {
			objects.draw(g);
		}
	}

	private Color colorize() {
		int num = (int) Math.round((Math.random() * 9));
		switch (num) {
		case 0:
			return Color.blue;
		case 1:
			return Color.cyan;
		case 2:
			return Color.green;
		case 3:
			return Color.magenta;
		case 4:
			return Color.orange;
		case 5:
			return Color.pink;
		case 6:
			return Color.red;
		case 7:
			return Color.yellow;
		case 8:
			return Color.black;
		default:
			return Color.gray;
		}
	}

	private void checkForColision() {
		for (Objects objects : bullets) {
			objects.move();
			if (objects.getX() > this.getWidth() || objects.getX() < 0 || objects.getY() > this.getHeight() || objects.getY() < 0) {
				if (objects.Hasbounced() == false) {
					objects.bounce();
				} else {
					garbage.add(objects);
				}
			}
			if (player2.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p2WasHit = true;
			}
		}
		bullets.removeAll(garbage);
		garbage.clear();
		for (Objects objects : enemybullets) {
			objects.move();
			if (objects.getX() > this.getWidth() || objects.getX() < 0 || objects.getY() > this.getHeight() || objects.getY() < 0) {
				if (objects.Hasbounced() == false) {
					objects.bounce();
				} else {
					garbage.add(objects);
				}
			}
			if (player.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p1WasHit = true;
			}
		}
		enemybullets.removeAll(garbage);
		garbage.clear();
		if (!bullets.isEmpty() && !enemybullets.isEmpty() && enemybullets.get(0).getRect().contains(new Point(bullets.get(0).getX(), bullets.get(0).getY())) == true) {
			enemybullets.remove(0);
			bullets.remove(0);
		}
	}

	private void checkShooting() {
		if (p1Shooting == 1) p1CanMove = false;
		if (p2Shooting == 1) p2CanMove = false;
		if (p2Shooting == -1) {
			p2CanMove = true;
			if (enemybullets.isEmpty() && !p2WasHit) {
				switch (p2Bullet) {
				case 1:
					enemybullets.add(new Objects(player2.getX() - 5, player2.getY() + 10, 5, 5, sheet.getBullet(), p2Bullet, -1));
					break;
				case 0:
					enemybullets.add(new Objects(player2.getX(), player2.getY() + 27, 5, 5, sheet.getBullet(), p2Bullet, -1));
					break;
				case -1:
					enemybullets.add(new Objects(player2.getX(), player2.getY() + 55, 5, 5, sheet.getBullet(), p2Bullet, -1));
					break;

				default:
					break;
				}

				sound.play("shoot");
			}
			p2Shooting = 0;
		}
		if (p1Shooting == -1) {
			p1CanMove = true;

			if (bullets.isEmpty() && !p1WasHit) {
				switch (p1Bullet) {
				case 1:
					bullets.add(new Objects(player.getX() + 55, player.getY() + 10, 5, 5, sheet.getBullet(), p1Bullet, 1));
					break;
				case 0:
					bullets.add(new Objects(player.getX() + 55, player.getY() + 27, 5, 5, sheet.getBullet(), p1Bullet, 1));
					break;
				case -1:
					bullets.add(new Objects(player.getX() + 55, player.getY() + 55, 5, 5, sheet.getBullet(), p1Bullet, 1));
					break;

				default:
					break;
				}

				sound.play("shoot");
			}
		}
		p1Shooting = 0;
	}

	public void setP1Down(boolean p1Down) {
		this.p1Down = p1Down;
	}

	public void setP1Left(boolean p1Left) {
		this.p1Left = p1Left;
	}

	public void setP1Right(boolean p1Right) {
		this.p1Right = p1Right;
	}

	public void setP1Up(boolean p1Up) {
		this.p1Up = p1Up;
	}

	public void setP1Shooting(int p1Shooting) {
		this.p1Shooting = p1Shooting;
	}

	public void setP2Down(boolean p1Down) {
		this.p2Down = p1Down;
	}

	public void setP2Left(boolean p1Left) {
		this.p2Left = p1Left;
	}

	public void setP2Right(boolean p1Right) {
		this.p2Right = p1Right;
	}

	public void setP2Up(boolean p1Up) {
		this.p2Up = p1Up;
	}

	public void setP2Shooting(int p1Shooting) {
		this.p2Shooting = p1Shooting;
	}
}