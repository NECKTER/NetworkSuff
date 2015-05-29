package Mainpackage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage main;
	private Image backround;
	private Image player;//138 125 201 224
	private Image playerDeath;//1 177 65 241
	private Image bullet;//0 494 7 501
	private Image block;//136 352 151 367
	private Image playerShoot;//227 17 291 99
	private Image playerShootUp;//166 17 223 99 
	private Image playerShootDown;//144 244 201 326
	private Image playerStep;//69 346 133 445
	private Image cactus;//0 0 86 139

	public SpriteSheet() {
		try {
			main = ImageIO.read(SpriteSheet.class.getResourceAsStream("images/outlaw_sprites.png"));
			backround = ImageIO.read(SpriteSheet.class.getResourceAsStream("images/backround.png"));
		} catch (IOException e) {
		}
		try {
			player = main.getSubimage(138, 125, 201 - 138, 224 - 125);
			playerDeath = main.getSubimage(1, 177, 65 - 1, 241 - 177);
			bullet = main.getSubimage(0, 494, 7, 501 - 494);
			block = main.getSubimage(136, 352, 151 - 136, 367 - 352);
			playerShoot = main.getSubimage(227, 17, 291 - 227, 99 - 17);
			playerShootUp = main.getSubimage(166, 17, 223 - 166, 99 - 17);
			playerShootDown = main.getSubimage(144, 244, 201 - 144, 326 - 244);
			playerStep = main.getSubimage(69, 346, 133 - 69, 445 - 346);
			cactus = main.getSubimage(0, 0, 86, 139);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image getBackround() {
		return backround;
	}

	public BufferedImage getMain() {
		return main;
	}

	public Image getPlayerDeath() {
		return playerDeath;
	}

	public Image getBlock() {
		return block;
	}

	public Image getBullet() {
		return bullet;
	}

	public Image getCactus() {
		return cactus;
	}

	public Image getPlayer() {
		return player;
	}

	public Image getPlayerShoot() {
		return playerShoot;
	}

	public Image getPlayerShootDown() {
		return playerShootDown;
	}

	public Image getPlayerShootUp() {
		return playerShootUp;
	}

	public Image getPlayerStep() {
		return playerStep;
	}

}
