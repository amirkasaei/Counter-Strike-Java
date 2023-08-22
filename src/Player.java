import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private int x;
    private int y;
    int iconSize;
    private boolean dead = false;
    private int shotTime = 0;
    private int lives = 3;
    private Icon right;
    private Icon left;
    private Icon up;
    private Icon down;
    private Direction direction;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getLives() {
        return lives;
    }

    public void plusLives() {
        //plus lives if its less than 4
        if (lives < 4) {
            lives++;
        }
    }

    public void minusLives() {
        if (lives > 0) {
            lives--;
        }
    }

    public Icon getRightIcon() {
        return right;
    }

    public Icon getLeftIcon() {
        return left;
    }

    public Icon getUpIcon() {
        return up;
    }

    public Icon getDownIcon() {
        return down;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getShotTime() {
        return shotTime;
    }

    public void setShotTime(int shotTime) {
        this.shotTime = shotTime;
    }

//setting icon size according to board size
    void setRightIcon() {
        File image = new File("img/userR.png");
        BufferedImage right = null;
        try {
            right = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.right = new ImageIcon(right.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setUpIcon() {
        File image = new File("img/userU.png");
        BufferedImage up = null;
        try {
            up = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.up = new ImageIcon(up.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setDownIcon() {
        File image = new File("img/userD.png");
        BufferedImage down = null;
        try {
            down = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.down = new ImageIcon(down.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setLeftIcon() {
        File image = new File("img/userL.png");
        BufferedImage left = null;
        try {
            left = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.left = new ImageIcon(left.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    Player(int m, int n) {
        iconSize = 1200/(3*m);
        setUpIcon();
        setRightIcon();
        setDownIcon();
        setLeftIcon();
    }
}




