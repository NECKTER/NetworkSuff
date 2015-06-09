package Mainpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import Networkstuuff.GameClient;
import Networkstuuff.GameServer;

public class Panel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer gameTimer;
	private boolean first;
	private ClipPlayer sound = new ClipPlayer();
	private SpriteSheet sheet = new SpriteSheet();
	private final int pnum;
	private final int maxBullets = 1;
	private LoadMap loadmap;
	private ArrayList<Objects> bullets = new ArrayList<Objects>();
	private ArrayList<Objects> enemybullets = new ArrayList<Objects>();
	private ArrayList<Objects> garbage = new ArrayList<Objects>();
	private int[][] map = new int[90][160];
	private Objects player = new Objects(200, 400, sheet.getPlayer().getHeight(null), sheet.getPlayer().getHeight(null), sheet.getPlayer());
	private int p1Health = 10;
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
	private Objects player2 = new Objects(1300, 400, sheet.getPlayer().getHeight(null), sheet.getPlayer().getHeight(null), sheet.getPlayer());
	private int p2Health = 10;
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
	private GameClient socketClient;
	private GameServer socketServer;
	private boolean foreverbullet = false;
	private int scrollSpeed = Integer.MAX_VALUE;
	private long lastScroll = System.currentTimeMillis();

	public Panel(int i) {
		this.setPreferredSize(new Dimension(1600, 900));
		this.setBackground(Color.WHITE);
		Objects.panel = this;
		gameTimer = new Timer(1, this);
		sound.mapFile("shoot", "SHOOT.wav");
		//block break and step sound
		sound.mapFile("break", "OUTLAW.wav");
		sound.mapFile("death", "DEATH.wav");
		pnum = i;
		loadmap = new LoadMap("wall.png");
		map = loadmap.getMap();
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
				if (pnum == 1) {
					setP1Up(true);
//					if (socketServer.getmini().getvalue(2) == "n") {
//						socketServer.getmini().togglevalue(2);
//					}
				} else {
					setP2Up(true);
//					if (socketClient.getvalue(7) == "n") {
//						socketClient.togglevalue(7);
//					}

				}
			}
		});
		this.getActionMap().put("up off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Up(false);
//					if (socketServer.getmini().getvalue(2) == "y") {
//						socketServer.getmini().togglevalue(2);
//					}
				} else
					setP2Up(false);
//				if (socketClient.getvalue(7) == "y") {
//					socketClient.togglevalue(7);
//				}
			}
		});
		this.getActionMap().put("down", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Down(true);
//					if (socketServer.getmini().getvalue(3) == "n") {
//						socketServer.getmini().togglevalue(3);
//					}
				} else
					setP2Down(true);
//				if (socketClient.getvalue(8) == "n") {
//					socketClient.togglevalue(8);
//				}

			}
		});
		this.getActionMap().put("down off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Down(false);
//					if (socketServer.getmini().getvalue(3) == "y") {
//						socketServer.getmini().togglevalue(3);
//					}
				} else
					setP2Down(false);
//				if (socketClient.getvalue(8) == "y") {
//					socketClient.togglevalue(8);
//				}
			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Right(true);
//					if (socketServer.getmini().getvalue(1) == "n") {
//						socketServer.getmini().togglevalue(1);
//					}
				} else
					setP2Right(true);
//				if (socketClient.getvalue(6) == "n") {
//					socketClient.togglevalue(6);
//				}
			}
		});
		this.getActionMap().put("right off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Right(false);
//					if (socketServer.getmini().getvalue(1) == "y") {
//						socketServer.getmini().togglevalue(1);
//					}
				} else
					setP2Right(false);
//				if (socketClient.getvalue(6) == "y") {
//					socketClient.togglevalue(6);
//				}
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Left(true);
//					if (socketServer.getmini().getvalue(0) == "n") {
//						socketServer.getmini().togglevalue(0);
//					}
				} else
					setP2Left(true);
//				if (socketClient.getvalue(5) == "n") {
//					socketClient.togglevalue(5);
//				}
			}
		});
		this.getActionMap().put("left off", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Left(false);
//					if (socketServer.getmini().getvalue(0) == "n") {
//						socketServer.getmini().togglevalue(0);
//					}
				} else
					setP2Left(false);
//				if (socketClient.getvalue(5) == "y") {
//					socketClient.togglevalue(5);
//				}
			}
		});
		this.getActionMap().put("shoot", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Shooting(1);
//					if (socketServer.getmini().getvalue(4) == "n") {
//						socketServer.getmini().togglevalue(4);
//					}
				} else
					setP2Shooting(1);
//				if (socketClient.getvalue(9) == "n") {
//					socketClient.togglevalue(9);
//				}
			}
		});
		this.getActionMap().put("shootOff", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pnum == 1) {
					setP1Shooting(-1);
//					if (socketServer.getmini().getvalue(4) == "n") {
//						socketServer.getmini().togglevalue(4);
//					}
				} else
					setP2Shooting(-1);
//				if (socketClient.getvalue(9) == "y") {
//					socketClient.togglevalue(9);
//				}
			}
		});
	}

	public void startGame() {
		// setup game then start timer
		if (first) {
			if (pnum == 1) {
				socketServer = new GameServer();
			} else {
				socketClient = new GameClient();
			}
			first = false;
		}
		socketClient = new GameClient();
		p1Health = 10;
		p2Health = 10;
		player.move(200, 400);
		player2.move(1300, 400);
		gameTimer.start();
		map = loadmap.getMap();

		//makes the game client

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//paint what is to be seen then the game has not yet started or has ended
		g.drawImage(sheet.getBackround(), 0, 0, 1600, 900, null);
		if (!gameTimer.isRunning()) {
			g.setFont(new Font("Times new roman", Font.PLAIN, 100));
			if (p1Health == 0) {
				player2.draw(g, p2col, false);
				g.setColor(p2col);
				g.drawString("Winner", 650, 200);
			}
			if (p2Health == 0) {
				g.setColor(p1col);
				player.draw(g, p1col, false);
				g.drawString("Winner", 650, 200);
			}
			return;
		}
		drawMap(g);
		drawBullets(g);
//		p1col = colorize();
//		p2col = colorize();
		playerAnimate(g);
		//paint game stuff

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// do stuff and then repaint
		checkGameOver();
//		updateservervalues();
		movestuff();
		repaint();
	}

	private void checkGameOver() {
		// TODO Auto-generated method stub
		if (p1Health < 1) {
			gameTimer.stop();
		}
		if (p2Health < 1) {
			gameTimer.stop();

		}
	}

	private void updateservervalues() {
		// TODO Auto-generated method stub
		if (pnum == 1) {
			if (socketServer.getmini().getvalue(5) == "n") {
				p2Left = false;
			} else {
				p2Left = true;
			}
			if (socketServer.getmini().getvalue(6) == "n") {
				p2Right = false;
			} else {
				p2Right = true;
			}
			if (socketServer.getmini().getvalue(7) == "n") {
				p2Up = false;
			} else {
				p2Up = true;
			}
			if (socketServer.getmini().getvalue(8) == "n") {
				p2Down = false;
			} else {
				p2Down = true;
			}
			if (socketServer.getmini().getvalue(9) == "y") {
				p2Shooting = 1;
			} else { //I DONT KNOW HOW YOUR BULLETS WORK, YOU DO THIS SHIT
				//p2Shooting=;
			}
		} else
			if (pnum == 2) {
				if (socketClient.getvalue(0) == "n") {
					p1Left = false;
				} else {
					p1Left = true;
				}
				if (socketClient.getvalue(1) == "n") {
					p1Right = false;
				} else {
					p1Right = true;
				}
				if (socketClient.getvalue(2) == "n") {
					p1Up = false;
				} else {
					p1Up = true;
				}
				if (socketClient.getvalue(3) == "n") {
					p1Down = false;
				} else {
					p1Down = true;
				}
				if (socketClient.getvalue(4) == "y") {
					p1Shooting = 1;
				} else { //I DONT KNOW HOW YOUR BULLETS WORK, YOU DO THIS SHIT
					//p2Shooting=;
				}
			}

	}

	private void movestuff() {
		// TODO Auto-generated method stub\
		checkForColision();
		checkShooting();
		if (System.currentTimeMillis() - lastScroll > scrollSpeed) {
			scrollMap();
			lastScroll = System.currentTimeMillis();
		}
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

	private void drawMap(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) g.fillRect(j * 10, i * 10, 10, 10);
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
		//keeps players on screen
		int heigh = sheet.getPlayer().getHeight(null);
		int width = sheet.getPlayer().getWidth(null);
		if (player.getX() + width > this.getWidth()) {
			p1Right = false;
		}
		if (player.getX() < 0) {
			p1Left = false;
		}
		if (player.getY() + heigh > this.getHeight()) {
			p1Down = false;
		}
		if (player.getY() < 0) {
			p1Up = false;
		}
		if (player2.getX() + width > this.getWidth()) {
			p2Right = false;
		}
		if (player2.getX() < 0) {
			p2Left = false;
		}
		if (player2.getY() + heigh > this.getHeight()) {
			p2Down = false;
		}
		if (player2.getY() < 0) {
			p2Up = false;
		}
		//if players hit 
		for (Objects objects : bullets) {
			objects.move();
			if (objects.getX() > this.getWidth() || objects.getX() < 0 || objects.getY() > this.getHeight() || objects.getY() < 0) {
				if (objects.Hasbounced() == false || foreverbullet) {
					if (objects.getY() > this.getHeight() || objects.getY() < 0) objects.bounce();
					else {
						if ((objects.getX() > this.getWidth() || objects.getX() < 0) && foreverbullet) objects.changeDirection();
						else
							garbage.add(objects);
					}
				}
//				if (objects.Hasbounced() && !foreverbullet) garbage.add(objects);
			}
			if (player2.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p2WasHit = true;
				p2Health--;
			}
		}
		bullets.removeAll(garbage);
		garbage.clear();

		for (Objects objects : enemybullets) {
			objects.move();
			if (objects.getX() > this.getWidth() || objects.getX() < 0 || objects.getY() > this.getHeight() || objects.getY() < 0) {
				if (objects.Hasbounced() == false || foreverbullet) {
					if (objects.getY() > this.getHeight() || objects.getY() < 0) objects.bounce();
					else {
						if ((objects.getX() > this.getWidth() || objects.getX() < 0) && foreverbullet) objects.changeDirection();
						else
							garbage.add(objects);
					}
				}
//				if (objects.Hasbounced() && !foreverbullet) garbage.add(objects);
			}
			if (player.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p1WasHit = true;
				p1Health--;
			}
		}
		enemybullets.removeAll(garbage);
		garbage.clear();

		for (Objects objects : bullets) {
			if (player.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p1WasHit = true;
				p1Health--;
			}
		}
		bullets.removeAll(garbage);
		garbage.clear();

		for (Objects objects : enemybullets) {
			if (player2.getPixels().contains(new Point(objects.getX(), objects.getY()))) {
				garbage.add(objects);
				sound.play("death");
				p2WasHit = true;
				p2Health--;
			}
		}
		enemybullets.removeAll(garbage);
		garbage.clear();

		//bullet collision
		if (!bullets.isEmpty() && !enemybullets.isEmpty() && enemybullets.get(0).getRect().contains(new Point(bullets.get(0).getX(), bullets.get(0).getY())) == true) {
			enemybullets.remove(0);
			bullets.remove(0);
		}
		//if a bullet hit a wall
		for (Objects objects : bullets) {
			if (objects.getY() / 10 > 0 && objects.getY() / 10 < 90 && objects.getX() / 10 > 0 && objects.getX() / 10 < 160) {
				if (map[objects.getY() / 10][objects.getX() / 10] == 1) {
					map[objects.getY() / 10][objects.getX() / 10] = 0;
					sound.play("break");
					if (!foreverbullet) garbage.add(objects);
				}
			}
		}
		bullets.removeAll(garbage);
		garbage.clear();
		for (Objects objects : enemybullets) {
			if (objects.getY() / 10 > 0 && objects.getY() / 10 < 90 && objects.getX() / 10 > 0 && objects.getX() / 10 < 160) {
				if (map[objects.getY() / 10][objects.getX() / 10] == 1) {
					map[objects.getY() / 10][objects.getX() / 10] = 0;
					sound.play("break");
					if (!foreverbullet) garbage.add(objects);
				}
			}
		}
		enemybullets.removeAll(garbage);
		garbage.clear();
		//player walking into wall
		for (Point p : player.getPixels()) {
			if ((int) ((p.getX() + 1) / 10) < 160 && (int) ((p.getY()) / 10) < 90 && map[(int) ((p.getY()) / 10)][(int) ((p.getX() + 1) / 10)] == 1) p1Right = false;
			if ((int) ((p.getX() - 1) / 10) > 0 && (int) ((p.getY()) / 10) < 90 && map[(int) ((p.getY()) / 10)][(int) ((p.getX() - 1) / 10)] == 1) p1Left = false;
			if ((int) ((p.getY() - 1) / 10) > 0 && map[(int) ((p.getY() - 1) / 10)][(int) ((p.getX()) / 10)] == 1) p1Up = false;
			if ((int) ((p.getY() + 1) / 10) < 90 && map[(int) ((p.getY() + 1) / 10)][(int) ((p.getX()) / 10)] == 1) p1Down = false;
		}
	}

	private void checkShooting() {
		if (p1Shooting == 1) p1CanMove = false;
		if (p2Shooting == 1) p2CanMove = false;
		if (p2Shooting == -1) {
			p2CanMove = true;
			if (enemybullets.size() < maxBullets && !p2WasHit) {
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

			if (bullets.size() < maxBullets && !p1WasHit) {
				switch (p1Bullet) {
				case 1:
					bullets.add(new Objects(player.getX() + 55, player.getY() + 10, 5, 5, sheet.getBullet(), p1Bullet, 1));
					break;
				case 0:
					bullets.add(new Objects(player.getX() + 59, player.getY() + 27, 5, 5, sheet.getBullet(), p1Bullet, 1));
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

	public void toggleForeverbullet() {
		if (!gameTimer.isRunning()) {
			if (foreverbullet) {
				foreverbullet = false;
			} else {
				foreverbullet = true;
			}
		}
	}

	public void setScrollSpeed(int scrollSpeed) {
		if (!gameTimer.isRunning()) {
			this.scrollSpeed = scrollSpeed;
		}
	}

	public void changeMap() {
		if (!gameTimer.isRunning()) {
			loadmap.changeMap();
			map = loadmap.getMap();
		}
	}

	private void scrollMap() {
		int temp = 0;
		for (int i = 0; i < map[0].length; i++) {
			for (int j = map.length - 1; j >= 0; j--) {
				if (j == map.length - 1) {
					temp = map[j][i];
				}
				if (j > 0) {
					map[j][i] = map[j - 1][i];
				} else {
					map[j][i] = temp;
				}

			}
		}
	}
}