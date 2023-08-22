import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CounterStrike extends JFrame {
    static Player player;
    static Robot[] robots = new Robot[3];
    //board size
    static int m = 6, n = 8;
    static Button[][] buttons = new Button[(m + 2)][(n + 2)];
    Random random = new Random();
    //timer label
    static JLabel timeCounter = new JLabel();
    //game threads
    RobotMovement robotMovement = new RobotMovement(this);
    Stopwatch stopwatch = new Stopwatch(timeCounter);
    HealLive healLive = new HealLive();

    //menu frame
    public CounterStrike(Main main) {
        super("Counter Strike");
        setSize(810, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //handling close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.setVisible(true);
            }
        });

        //container panel
        JPanel container = new JPanel(null);
        add(container);

        //header, board and footer panels
        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 800, 100);
        header.setBackground(Color.BLACK);
        timeCounter.setBounds(370, 60, 200, 15);
        timeCounter.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel headerImageLeft = new JLabel(new ImageIcon("img/userR.png"));
        headerImageLeft.setBounds(10, 40, 50, 50);
        JLabel headerImageRight = new JLabel(new ImageIcon("img/robotL.png"));
        headerImageRight.setBounds(740, 40, 50, 50);
        header.add(timeCounter);
        header.add(headerImageLeft);
        header.add(headerImageRight);
        container.add(header);

        //game board
        JPanel board = new JPanel();
        board.setBounds(0, 100, 795, 562);
        board.setLayout(new GridLayout(m + 2, n + 2));
        int numberOfBlock = (int) Math.floor(0.4 * m * n);
        container.add(board);

        //creating buttons
        for (int i = 0; i < m + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                buttons[i][j] = new Button(board);
                //key listener for player movement
                buttons[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        playerMove(e.getKeyCode());
                    }
                });
                //corner blocks
                if (i == 0 || i == m + 1 || j == n + 1 || j == 0) {
                    buttons[i][j].setBackground(new Color(120, 153, 134, 255));
                    buttons[i][j].setBorderPainted(false);
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setBlocked(true);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }

                buttons[i][j].setFocusPainted(false);
                board.add(buttons[i][j]);
            }
        }

        int x, y;
        int direction;
        //inserting blocks
        for (int i = 0; i < numberOfBlock; i++) {
            if (random.nextInt(2) == 1) {
                x = random.nextInt(m) + 1;
                y = random.nextInt(n) + 1;
                buttons[x][y].setBackground(Color.BLACK);
                buttons[x][y].setBorderPainted(false);
                buttons[x][y].setEnabled(false);
                buttons[x][y].setBlocked(true);
            }
        }

        //inserting robots
        for (int i = 0; i < 3; i++) {
            //random possible place for player
            do {
                x = random.nextInt(m) + 1;
                y = random.nextInt(n) + 1;
            } while (buttons[x][y].isBlocked() || buttons[x][y].getRobot() != null);
            robots[i] = new Robot();
            buttons[x][y].setRobot(robots[i]);
            buttons[x][y].setLives(robots[i].getLives());
            robots[i].setX(x);
            robots[i].setY(y);
            //set random direction
            direction = random.nextInt(4)+1;
            switch (direction) {
                //up
                case 1:
                    buttons[x][y].setIcon(robots[i].getUpIcon());
                    robots[i].setDirection(Direction.Up);
                    break;
                //right
                case 2:
                    buttons[x][y].setIcon(robots[i].getRightIcon());
                    robots[i].setDirection(Direction.Right);
                    break;
                //down
                case 3:
                    buttons[x][y].setIcon(robots[i].getDownIcon());
                    robots[i].setDirection(Direction.Down);
                    break;
                //left
                case 4:
                    buttons[x][y].setIcon(robots[i].getLeftIcon());
                    robots[i].setDirection(Direction.Left);
                    break;
            }
        }

        //inserting player
        //possible random place for player
        do {
            x = random.nextInt(m) + 1;
            y = random.nextInt(n) + 1;
        } while (buttons[x][y].isBlocked() || buttons[x][y].getRobot() != null);
        player = new Player(m, n);
        buttons[x][y].setPlayer(player);
        buttons[x][y].setLives(player.getLives());
        player.setX(x);
        player.setY(y);
        //set random direction
        direction = random.nextInt(4) + 1;
        switch (direction) {
            //up
            case 1:
                buttons[x][y].setIcon(player.getUpIcon());
                player.setDirection(Direction.Up);
                break;
            //right
            case 2:
                buttons[x][y].setIcon(player.getRightIcon());
                player.setDirection(Direction.Right);
                break;
            //down
            case 3:
                buttons[x][y].setIcon(player.getDownIcon());
                player.setDirection(Direction.Down);
                break;
            //left
            case 4:
                buttons[x][y].setIcon(player.getLeftIcon());
                player.setDirection(Direction.Left);
                break;
        }
        setResizable(false);
        setVisible(true);
        robotMovement.start();
        stopwatch.start();
        healLive.start();
    }

    //player movement and shooting
    void playerMove(int keycode) {
        int x = player.getX();
        int y = player.getY();

        switch (keycode) {
            case KeyEvent.VK_UP:
                //move up
                if (!buttons[x - 1][y].isBlocked() && buttons[x - 1][y].getRobot() == null) {
                    //empty old button
                    buttons[x][y].setIcon(null);
                    buttons[x][y].setPlayer(null);
                    buttons[x][y].setLives(-1);
                    //organizing new button
                    buttons[x - 1][y].setIcon(player.getUpIcon());
                    buttons[x - 1][y].setPlayer(player);
                    //increase lives if possible
                    if (buttons[x - 1][y].hasHealLive()) {
                        if (player.getLives() < 4) {
                            player.plusLives();
                        }
                        buttons[x - 1][y].setHealLive(false);
                    }
                    buttons[x - 1][y].setLives(player.getLives());
                    player.setX(x - 1);
                    player.setDirection(Direction.Up);
                } else {
                    JOptionPane.showMessageDialog(null, "Cant move here!");
                }
                break;
            case KeyEvent.VK_RIGHT:
                //move right
                if (!buttons[x][y + 1].isBlocked() && buttons[x][y + 1].getRobot() == null) {
                    //empty old button
                    buttons[x][y].setIcon(null);
                    buttons[x][y].setPlayer(null);
                    buttons[x][y].setLives(-1);
                    //organizing new button
                    buttons[x][y + 1].setIcon(player.getRightIcon());
                    buttons[x][y + 1].setPlayer(player);
                    //increase lives if possible
                    if (buttons[x][y + 1].hasHealLive()) {
                        if (player.getLives() < 4) {
                            player.plusLives();
                        }
                        buttons[x][y + 1].setHealLive(false);
                    }
                    buttons[x][y + 1].setLives(player.getLives());
                    player.setY(y + 1);
                    player.setDirection(Direction.Right);
                } else {
                    JOptionPane.showMessageDialog(null, "Cant move here!");
                }
                break;
            case KeyEvent.VK_DOWN:
                //move down
                if (!buttons[x + 1][y].isBlocked() && buttons[x + 1][y].getRobot() == null) {
                    //empty old button
                    buttons[x][y].setIcon(null);
                    buttons[x][y].setPlayer(null);
                    buttons[x][y].setLives(-1);
                    //organizing new button
                    buttons[x + 1][y].setIcon(player.getDownIcon());
                    buttons[x + 1][y].setPlayer(player);
                    //increase lives if possible
                    if (buttons[x + 1][y].hasHealLive()) {
                        if (player.getLives() < 4) {
                            player.plusLives();
                        }
                        buttons[x + 1][y].setHealLive(false);
                    }
                    buttons[x + 1][y].setLives(player.getLives());
                    player.setX(x + 1);
                    player.setDirection(Direction.Down);
                } else {
                    JOptionPane.showMessageDialog(null, "Cant move here!");
                }
                break;
            case KeyEvent.VK_LEFT:
                //move left
                if (!buttons[x][y - 1].isBlocked() && buttons[x][y - 1].getRobot() == null) {
                    //empty old button
                    buttons[x][y].setIcon(null);
                    buttons[x][y].setPlayer(null);
                    buttons[x][y].setLives(-1);
                    //organizing new button
                    buttons[x][y - 1].setIcon(player.getLeftIcon());
                    buttons[x][y - 1].setPlayer(player);
                    //increase lives if possible
                    if (buttons[x][y - 1].hasHealLive()) {
                        if (player.getLives() < 4) {
                            player.plusLives();
                        }
                        buttons[x][y - 1].setHealLive(false);
                    }
                    buttons[x][y - 1].setLives(player.getLives());
                    player.setY(y - 1);
                    player.setDirection(Direction.Left);
                } else {
                    JOptionPane.showMessageDialog(null, "Cant move here!");
                }
                break;

            case KeyEvent.VK_SPACE:
                //one second delay check
                if (stopwatch.getTime() - player.getShotTime() < 1) {
                    JOptionPane.showMessageDialog(null, "You cant shoot!", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    //shoot
                    if (player.getDirection() == Direction.Up) {
                        //shoot up
                        for (int i = player.getX(); i >= 1; i--) {
                            if (buttons[i][player.getY()].getRobot() != null || buttons[i][player.getY()].isBlocked()) {
                                if (buttons[i][player.getY()].getRobot() != null) {
                                    Robot robot = buttons[i][player.getY()].getRobot();
                                    robot.minusLives();
                                    if (robot.getLives() == 0) {
                                        buttons[robot.getX()][robot.getY()].setLives(0);
                                        robot.setDead(true);
                                    }
                                }
                                break;
                            }
                        }
                    } else if (player.getDirection() == Direction.Right) {
                        //shoot right
                        for (int i = player.getY(); i <= n; i++) {
                            if (buttons[player.getX()][i].getRobot() != null || buttons[player.getX()][i].isBlocked()) {
                                if (buttons[player.getX()][i].getRobot() != null) {
                                    Robot robot = buttons[player.getX()][i].getRobot();
                                    robot.minusLives();
                                    if (robot.getLives() == 0) {
                                        buttons[robot.getX()][robot.getY()].setLives(0);
                                        robot.setDead(true);
                                    }
                                }
                                break;
                            }
                        }
                    } else if (player.getDirection() == Direction.Down) {
                        //shoot down
                        for (int i = player.getX(); i <= m; i++) {
                            if (buttons[i][player.getY()].getRobot() != null || buttons[i][player.getY()].isBlocked()) {
                                if (buttons[i][player.getY()].getRobot() != null) {
                                    Robot robot = buttons[i][player.getY()].getRobot();
                                    robot.minusLives();
                                    if (robot.getLives() == 0) {
                                        buttons[robot.getX()][robot.getY()].setLives(0);
                                        robot.setDead(true);
                                    }
                                }
                                break;
                            }
                        }
                    } else if (player.getDirection() == Direction.Left) {
                        //shoot left
                        for (int i = player.getY(); i >= 1; i--) {
                            if (buttons[player.getX()][i].getRobot() != null || buttons[player.getX()][i].isBlocked()) {
                                if (buttons[player.getX()][i].getRobot() != null) {
                                    Robot robot = buttons[player.getX()][i].getRobot();
                                    robot.minusLives();
                                    if (robot.getLives() == 0) {
                                        buttons[robot.getX()][robot.getY()].setLives(0);
                                        robot.setDead(true);
                                    }
                                }
                                break;
                            }
                        }
                    }
                    //setting player shooting time for checking one second limit
                    player.setShotTime(stopwatch.getTime());
                }
                break;
        }
    }

    //check if game is finished
    static boolean end() {
        if (player.isDead() || (robots[0].isDead() && robots[1].isDead() && robots[2].isDead())) {
            return true;
        }
        return false;
    }
}