package Mainpackage;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage main;
	private Image player;
	private Image bullet;
	private Image block;
	private Image playerShoot;
	private Image playerShootUp;
	private Image playerShootDown;
	private Image playerStep;

	public SpriteSheet() {
		try {
			main = ImageIO.read(SpriteSheet.class.getResourceAsStream("images/outlaw_sprites.png"));
		} catch (IOException e) {
		}

	}

}
