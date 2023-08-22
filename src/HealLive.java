import javax.swing.*;
import java.util.Random;

//for adding extra heal lives in board
public class HealLive extends Thread{
    private Random rnd = new Random();
    private Icon heal = new ImageIcon("img/heal.png");

    @Override
    public void run() {
        while (!CounterStrike.end()){
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
            //check if game is not finished in thread sleep time
            if (!CounterStrike.end()) {
                int i,j;
                do {
                    i = rnd.nextInt(CounterStrike.m) + 1;
                    j = rnd.nextInt(CounterStrike.n) + 1;
                } while (CounterStrike.buttons[i][j].isBlocked() || CounterStrike.buttons[i][j].getRobot() != null ||  CounterStrike.buttons[i][j].getPlayer() != null || CounterStrike.buttons[i][j].hasHealLive());
                CounterStrike.buttons[i][j].setHealLive(true);
                CounterStrike.buttons[i][j].setIcon(heal);
            }
        }
    }
}
