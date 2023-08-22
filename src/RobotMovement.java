import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RobotMovement extends Thread{

    CounterStrike counterStrike;
    Random rnd = new Random();

    RobotMovement(CounterStrike counterStrike){
        this.counterStrike = counterStrike;
    }

    @Override
    public void run() {
        //move robots if game is not finished
        while (!CounterStrike.end()) {
            for (int i = 0; i < 3; i++) {
                //robot can shoot or move if its alive
                if (!CounterStrike.robots[i].isDead()) {
                    //robot can move if it hast shot yet
                    if (!robotShoot(CounterStrike.robots[i])) {
                        //robot move
                        robotMovement(CounterStrike.robots[i], rnd.nextInt(4) + 1, 1);
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        //finish dialog frame
        JFrame finishDialog = new JFrame();
        finishDialog.setLayout(null);
        finishDialog.setSize(300, 150);
        JLabel dialog = new JLabel();
        dialog.setBounds(80,40, 150, 25);
        dialog.setFont(new Font("Calibri", Font.BOLD, 14));
        finishDialog.add(dialog);
        finishDialog.setLocationRelativeTo(null);
        if (CounterStrike.player.isDead()) {
            dialog.setText("You lost!     " + CounterStrike.timeCounter.getText());
        }else {
            dialog.setText("You won!\n" + CounterStrike.timeCounter.getText());
        }
        finishDialog.setVisible(true);
        //5 seconds delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        finishDialog.dispose();
        counterStrike.dispose();
    }

    //robot shooting
    boolean robotShoot(Robot robot) {
        //shoot
        if (robot.getDirection() == Direction.Up) {
            for (int i = robot.getX(); i >= 1; i--) {
                //shoot up
                if (CounterStrike.buttons[i][robot.getY()].getPlayer() != null || CounterStrike.buttons[i][robot.getY()].isBlocked()) {
                    if (CounterStrike.buttons[i][robot.getY()].getPlayer() != null) {
                        Player player = CounterStrike.buttons[i][robot.getY()].getPlayer();
                        player.minusLives();
                        CounterStrike.buttons[player.getX()][player.getY()].setLives(player.getLives());
                        if (player.getLives() == 0) {
                            player.setDead(true);
                        }
                        return true;
                    }
                    break;
                }
            }

        } else if (robot.getDirection() == Direction.Right) {
            //shoot right
            for (int i = robot.getY(); i <= CounterStrike.n; i++) {
                if (CounterStrike.buttons[robot.getX()][i].getPlayer() != null || CounterStrike.buttons[robot.getX()][i].isBlocked()) {
                    if (CounterStrike.buttons[robot.getX()][i].getPlayer() != null) {
                        Player player = CounterStrike.buttons[robot.getX()][i].getPlayer();
                        player.minusLives();
                        CounterStrike.buttons[player.getX()][player.getY()].setLives(player.getLives());
                        if (player.getLives() == 0) {
                            player.setDead(true);
                        }
                        return true;
                    }
                    break;
                }
            }
        } else if (robot.getDirection() == Direction.Down) {
            //shoot down
            for (int i = robot.getX(); i <= CounterStrike.m; i++) {
                if (CounterStrike.buttons[i][robot.getY()].getPlayer() != null || CounterStrike.buttons[i][robot.getY()].isBlocked()) {
                    if (CounterStrike.buttons[i][robot.getY()].getPlayer() != null) {
                        Player player = CounterStrike.buttons[i][robot.getY()].getPlayer();
                        player.minusLives();
                        CounterStrike.buttons[player.getX()][player.getY()].setLives(player.getLives());
                        if (player.getLives() == 0) {
                            player.setDead(true);
                        }
                        return true;
                    }
                    break;
                }
            }
        } else if (robot.getDirection() == Direction.Left) {
            //shoot left
            for (int i = robot.getY(); i >= 1; i--) {
                if (CounterStrike.buttons[robot.getX()][i].getPlayer() != null || CounterStrike.buttons[robot.getX()][i].isBlocked()) {
                    if (CounterStrike.buttons[robot.getX()][i].getPlayer() != null) {
                        Player player = CounterStrike.buttons[robot.getX()][i].getPlayer();
                        player.minusLives();
                        CounterStrike.buttons[player.getX()][player.getY()].setLives(player.getLives());
                        if (player.getLives() == 0) {
                            player.setDead(true);
                        }
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    //robot movement
    boolean robotMovement(Robot robot, int random, int counter) {
        //robot previous place
        int x, y;
        x = robot.getX();
        y = robot.getY();
        //move to a random direction
        if (random == 1 && !CounterStrike.buttons[x - 1][y].isBlocked() && CounterStrike.buttons[x - 1][y].getPlayer() == null && CounterStrike.buttons[x - 1][y].getRobot() == null) {
            CounterStrike.buttons[x][y].setIcon(null);
            CounterStrike.buttons[x][y].setRobot(null);
            CounterStrike.buttons[x][y].setLives(-1);
            CounterStrike.buttons[x - 1][y].setIcon(robot.getUpIcon());
            CounterStrike.buttons[x - 1][y].setRobot(robot);
            //increase lives if possible
            if (CounterStrike.buttons[x - 1][y].hasHealLive()) {
                if (robot.getLives() < 4) {
                    robot.plusLives();
                }
                CounterStrike.buttons[x - 1][y].setHealLive(false);
            }
            CounterStrike.buttons[x - 1][y].setLives(robot.getLives());
            robot.setX(x - 1);
            robot.setDirection(Direction.Up);
            return true;
        } else if (random == 2 && !CounterStrike.buttons[x][y + 1].isBlocked() && CounterStrike.buttons[x][y + 1].getPlayer() == null && CounterStrike.buttons[x][y + 1].getRobot() == null) {
            CounterStrike.buttons[x][y].setIcon(null);
            CounterStrike.buttons[x][y].setRobot(null);
            CounterStrike.buttons[x][y].setLives(-1);
            CounterStrike.buttons[x][y + 1].setIcon(robot.getRightIcon());
            CounterStrike.buttons[x][y + 1].setRobot(robot);
            //increase lives if possible
            if (CounterStrike.buttons[x][y + 1].hasHealLive() && robot.getLives() < 4) {
                if (robot.getLives() < 4) {
                    robot.plusLives();
                }
                CounterStrike.buttons[x][y + 1].setHealLive(false);
            }
            CounterStrike.buttons[x][y + 1].setLives(robot.getLives());
            robot.setY(y + 1);
            robot.setDirection(Direction.Right);
            return true;
        } else if (random == 3 && !CounterStrike.buttons[x + 1][y].isBlocked() && CounterStrike.buttons[x + 1][y].getPlayer() == null && CounterStrike.buttons[x + 1][y].getRobot() == null) {
            CounterStrike.buttons[x][y].setIcon(null);
            CounterStrike.buttons[x][y].setRobot(null);
            CounterStrike.buttons[x][y].setLives(-1);
            CounterStrike.buttons[x + 1][y].setIcon(robot.getDownIcon());
            CounterStrike.buttons[x + 1][y].setRobot(robot);
            //increase lives if possible
            if (CounterStrike.buttons[x + 1][y].hasHealLive() && robot.getLives() < 4) {
                if (robot.getLives() < 4) {
                    robot.plusLives();
                }
                CounterStrike.buttons[x + 1][y].setHealLive(false);
            }
            CounterStrike.buttons[x + 1][y].setLives(robot.getLives());
            robot.setX(x + 1);
            robot.setDirection(Direction.Down);
            return true;
        } else if (random == 4 && !CounterStrike.buttons[x][y - 1].isBlocked() && CounterStrike.buttons[x][y - 1].getPlayer() == null && CounterStrike.buttons[x][y - 1].getRobot() == null) {
            CounterStrike.buttons[x][y].setIcon(null);
            CounterStrike.buttons[x][y].setRobot(null);
            CounterStrike.buttons[x][y].setLives(-1);
            CounterStrike.buttons[x][y - 1].setIcon(robot.getLeftIcon());
            CounterStrike.buttons[x][y - 1].setRobot(robot);
            //increase lives if possible
            if (CounterStrike.buttons[x][y - 1].hasHealLive() && robot.getLives() < 4) {
                if (robot.getLives() < 4) {
                    robot.plusLives();
                }
                CounterStrike.buttons[x][y - 1].setHealLive(false);
            }
            CounterStrike.buttons[x][y - 1].setLives(robot.getLives());
            robot.setY(y - 1);
            robot.setDirection(Direction.Left);
            return true;
        }
        //check that robot has moved with the random number and make it it move if it hasn't yet
        //counter is for limiting number of recursive calling
        if (random == 1 && counter < 2) {
            if (!robotMovement(robot, 2, counter + 1)) {
                if (!robotMovement(robot, 3, counter + 1)) {
                    return robotMovement(robot, 4, counter + 1);
                }
            }
        } else if (random == 2 && counter < 2) {
            if (!robotMovement(robot, 3, counter + 1)) {
                if (!robotMovement(robot, 4, counter + 1)) {
                    return robotMovement(robot, 1, counter + 1);
                }
            }
        } else if (random == 3 && counter < 2) {
            if (!robotMovement(robot, 4, counter + 1)) {
                if (!robotMovement(robot, 1, counter + 1)) {
                    return robotMovement(robot, 2, counter + 1);
                }
            }
        } else if (random == 4 && counter < 2) {
            if (!robotMovement(robot, 1, counter + 1)) {
                if (!robotMovement(robot, 2, counter + 1)) {
                    return robotMovement(robot, 3, counter + 1);
                }
            }
        }
        return false;
    }
}
