import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Robot {
    private int x;
    private int y;
    private boolean dead = false;
    private int lives = 3;
    private Icon rightIcon;
    private Icon leftIcon;
    private Icon upIcon;
    private Icon downIcon;
    int iconSize;
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

    public Icon getRightIcon() {
        return rightIcon;
    }

    public Icon getLeftIcon() {
        return leftIcon;
    }

    public Icon getUpIcon() {
        return upIcon;
    }

    public Icon getDownIcon() {
        return downIcon;
    }

    public int getLives() {
        return lives;
    }

    public void plusLives() {
        if (lives < 4) {
            lives++;
        }
    }

    public void minusLives() {
        if (lives > 0) {
            lives--;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //setting icon size according to board size
    void setRightIcon() {
        File image = new File("img/robotR.png");
        BufferedImage right = null;
        try {
            right = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.rightIcon = new ImageIcon(right.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setUpIcon() {
        File image = new File("img/robotU.png");
        BufferedImage up = null;
        try {
            up = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.upIcon = new ImageIcon(up.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setDownIcon() {
        File image = new File("img/robotD.png");
        BufferedImage down = null;
        try {
            down = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.downIcon = new ImageIcon(down.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    void setLeftIcon() {
        File image = new File("img/robotL.png");
        BufferedImage left = null;
        try {
            left = ImageIO.read(image);
        } catch (IOException e) {
            e.getStackTrace();
        }
        //add piece
        this.leftIcon = new ImageIcon(left.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH));
    }

    Robot() {
        iconSize = 1200/(3* CounterStrike.m);
        setUpIcon();
        setRightIcon();
        setDownIcon();
        setLeftIcon();
    }
}
