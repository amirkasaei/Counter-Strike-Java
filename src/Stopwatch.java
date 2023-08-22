import javax.swing.*;
import java.awt.*;

//for timer counting
public class Stopwatch extends Thread {
    JLabel timeCounter;
    int s = 0;

    Stopwatch(JLabel timeCounter) {
        this.timeCounter = timeCounter;
        timeCounter.setForeground(Color.WHITE);
    }

    @Override
    public void run() {
        // 24 hours in a day
        for (int hours = 0; hours < 24; hours++) {
            // 60 minutes in an hours
            for (int minutes = 0; minutes < 60; minutes++) {
                // 60 secs in a min
                for (int seconds = 0; seconds < 60; seconds++, s++) {
                    if (!CounterStrike.end()) {
                        timeCounter.setText((hours + " : " + minutes + " : " + seconds));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.getStackTrace();
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    int getTime () {
        return s;
    }
}
