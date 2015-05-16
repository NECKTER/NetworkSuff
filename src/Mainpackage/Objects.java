package Mainpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Objects {
	public static Panel panel;
	private Rectangle myrect;
	private int x, y, h, w;
	private Image img;
	private Image img2;
	private boolean canAnimate = false;
	private int animation = 0;
	private boolean changeImg = true;
	private boolean destroyed = false;
	private Image shootUp = null;
	private Image shoot = null;
	private Image shootDown = null;

	public Objects(int x, int y, int h, int w, Image img) {
		this.h = h;
		this.img = img;
		this.w = w;
		this.x = x;
		this.y = y;
		this.myrect = new Rectangle(x - w / 3, y, w * 2, h);
	}

	public void flipAll() {
		flipImg(img);
		flipImg(img2);
		flipImg(shoot);
		flipImg(shootUp);
		flipImg(shootDown);
	}

	private void flipImg(Image img) {
		if (img == null) return;
		BufferedImage test = (BufferedImage) img;
		for (int i = 0; i < test.getHeight(); i++) {
			for (int j = 0; j < test.getWidth() / 2; j++) {
				int temp = test.getRGB(j, i);
				int temp2 = test.getRGB(test.getWidth() - j-1, i);
				test.setRGB(j, i, temp2);
				test.setRGB(test.getWidth()-1 - j, i, temp);
			}
		}
		img = (Image) test;
	}

	public void addShootUp(Image img) {
		shootUp = img;
	}

	public void addShoot(Image img) {
		shoot = img;
	}

	public void addShootDonw(Image img) {
		shootDown = img;
	}

	public void addImage(Image img) {
		// TODO Auto-generated method stub
		this.img2 = img;
		canAnimate = true;
	}

	public void draw(boolean changeImg, Graphics g) {
		if (!destroyed) {
			this.changeImg = changeImg;
			g.drawImage(getImage(), x, y, w, h, panel);
			this.changeImg = true;
		}
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (!destroyed) {
			g.drawImage(getImage(), x, y, w, h, panel);
		}
	}

	public void draw(Graphics g, Color c) {
		// TODO Auto-generated method stub
		g.setColor(c);
		BufferedImage img = (BufferedImage) getImage();
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				if (img.getRGB(j, i) == img.getRGB(img.getWidth() / 2, img.getHeight() / 2)) g.drawLine(x + j, y + i, x + j, y + i);
			}
		}
	}

	public void drawShoot(Graphics g, Color c) {
		// TODO Auto-generated method stub
		if (shoot != null) {

			g.setColor(c);
			BufferedImage img = (BufferedImage) shoot;
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth(); j++) {
					if (img.getRGB(j, i) == img.getRGB(img.getWidth() / 2, img.getHeight() / 2)) g.drawLine(x + j, y + i, x + j, y + i);
				}
			}
		}
	}

	public void drawShootUp(Graphics g, Color c) {
		// TODO Auto-generated method stub
		if (shootUp != null) {
			g.setColor(c);
			BufferedImage img = (BufferedImage) shootUp;
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth(); j++) {
					if (img.getRGB(j, i) == img.getRGB(img.getWidth() / 2, img.getHeight() / 2)) g.drawLine(x + j, y + i, x + j, y + i);
				}
			}
		}
	}

	public void drawShootDown(Graphics g, Color c) {
		// TODO Auto-generated method stub
		if (shootDown != null) {
			g.setColor(c);
			BufferedImage img = (BufferedImage) shootDown;
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth(); j++) {
					if (img.getRGB(j, i) == img.getRGB(img.getWidth() / 2, img.getHeight() / 2)) g.drawLine(x + j, y + i, x + j, y + i);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		this.x = (int) x;
		this.y = (int) y;
		myrect.move((int) x - w / 3, (int) y);
	}

	private Image getImage() {
		// TODO Auto-generated method stub
		if (canAnimate) {
			if (animation == 0) {
				if (changeImg) {
					animation++;
				}
				return img;
			}
			if (changeImg) {
				animation--;
			}
			return img2;
		}
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void shoot() {

	}

	public void destroy() {
		destroyed = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public Rectangle getRect() {
		return myrect;
	}
}
