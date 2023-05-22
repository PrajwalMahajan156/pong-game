import javax.swing.*;
import java.awt.*;



public class GameFrame extends JFrame {
    GameFrame(){
        setTitle("Pong Game");
//        setLayout(null);
//        setSize(1000,555);
        setBackground(Color.black);
        GamePanel panel=new GamePanel();
        add(panel);
        pack();
//        setFocusable(true);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameFrame n=new GameFrame();
    }

}
