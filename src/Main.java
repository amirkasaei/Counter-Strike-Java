import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    //  exit
    private void exit(){
        System.exit(0);
    }

    //  main menu frame
    public Main() {

        super();
        setSize(450, 400);
        getContentPane().setBackground(new Color(120, 153, 134, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //frame logo
        ImageIcon logo = new ImageIcon("img/frameLogo.png");
        setIconImage(logo.getImage());

        //a reference for inside of action listener block
        Main main = this;

        //logo
        JLabel gameLogo = new JLabel(new ImageIcon("img/logo.png"));
        gameLogo.setBounds(140,10,150,150);
        add(gameLogo);

        //creating menu buttons and their action listener and adding them to the frame
        JButton[] menuButton = new JButton[4];
        for (byte i = 0; i < 4; i++) {
            menuButton[i] = new JButton();
            if (i == 0) {
                //offline game button
                menuButton[i].setText("Offline");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        main.setVisible(false);
                        new CounterStrike(main);
                    }
                });
            }else if (i == 1) {
                //client online button
                menuButton[i].setText("Client online");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
            }else if (i == 2) {
                //server online button
                menuButton[i].setText("Server online");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
            }else {
                //exit button
                menuButton[i].setText("Exit");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int ans = JOptionPane.showConfirmDialog(null, "Are uou sure you want to exit?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == 0)
                            exit();
                    }
                });
            }
            menuButton[i].setFocusPainted(false);
            menuButton[i].setBounds(170,180+i*40, 100, 30);
            add(menuButton[i]);
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        //changing look and feel into windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.getStackTrace();
        }
        //main menu frame
        new Main();
    }
}
