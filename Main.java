import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Main {

    private static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        returnToStart();

    }

    public static void addGame(int x,int y){
        for(int i = 0; i < frame.getComponentCount(); i++){
            System.out.println(frame.getComponent(i).getBackground());
        }
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(1,1));
        frame.add(new game(x, y));
        System.out.println(frame.getComponentCount());
        frame.setVisible(true);
        frame.repaint();

    }


    public static void returnToStart(){
        JButton b4 = new JButton("2048 4x4");
        JButton b5 = new JButton("2048 5x5");
        JButton b6 = new JButton("2048 6x6");
        JButton b8 = new JButton("2048 8x8");
        TextField welcome = new TextField("Welcome to 2048");
        JPanel gamePanel = new JPanel();


        welcome.setFont(new java.awt.Font("Comic Sans MS",Font.BOLD, 70));
        welcome.setEditable(false);
        welcome.setForeground(Color.black);
        welcome.setBackground(Color.orange);


        b4.setBackground(Color.yellow);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // frame.removeAll();
                addGame(4, 4);
            }
        
        });

        b5.setBackground(Color.red);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // frame.removeAll();
                addGame(5, 5);
            }
        
        });

        b6.setBackground(Color.green);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // frame.removeAll();
                addGame(6, 6);
            }
        
        });

        b8.setBackground(Color.blue);
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // frame.removeAll();
                addGame(8, 8);
            }
        
        });

        gamePanel.add(b4);
        gamePanel.add(b5);
        gamePanel.add(b6);
        gamePanel.add(b8);
        gamePanel.setLayout(new GridLayout(2,2));

        
        frame.setLayout(new BorderLayout());
        frame.add(welcome, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}






