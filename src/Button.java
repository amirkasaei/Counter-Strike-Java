import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    private Player player = null;
    private Robot robot = null;
    private boolean blocked = false;
    private JLabel lives = new JLabel();
    private JPanel board;
    private boolean hasHealLive = false;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setLives(int lives) {
        //set live label text
        if (lives == -1) {
            this.lives.setText(null);
        }else {
            this.lives.setText(String.valueOf(lives));
        }
    }

    public boolean hasHealLive() {
        return hasHealLive;
    }

    public void setHealLive(boolean hasHealLive) {
        this.hasHealLive = hasHealLive;
    }

    Button(JPanel board) {
        this.board = board;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(lives);
        //lives label
        lives.setFont(new Font("Arial", Font.BOLD, 16));
        lives.setForeground(Color.BLACK);
    }

}
