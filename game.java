import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class game extends JPanel{
    
    private String[][] grid;
    private int width;
    private int height;
    private logic gameLogic;
    private JButton[][] board;
    private JPanel buttonPanel = new JPanel();
    private TextField score = new TextField();

    public game(int x,int y){
        width = x;
        height = y;
        board = new JButton[width][height];
        gameLogic = new logic(x,y);
        grid = gameLogic.getBoard();
        this.addKeyListener(new MyKeyListener(this));
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(237, 194, 46));
        if(width > 0 && height > 0)makeBoard();
        this.setSize(600,800);
    }

    public void makeBoard(){
        score = new TextField(getText("      Score: " + gameLogic.getScore()));
        score.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
        score.setEditable(false);
        score.setForeground(Color.black);
        score.setBackground(new Color( 237, 194, 46 ));
        this.add(score, BorderLayout.NORTH);

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].equals("0"))board[x][y] = new JButton(" ");
                else board[x][y] = new JButton(grid[x][y]);
                board[x][y].setFocusable(false);
                board[x][y].setFont(new Font("Arial", Font.BOLD, getFSize()));
                if(grid[x][y].equals("2"))board[x][y].setForeground(Color.black);
                else board[x][y].setForeground(Color.white);
                board[x][y].setBackground(getColor(grid[x][y]));
                board[x][y].setBorder(BorderFactory.createLineBorder(Color.black, 4));
                buttonPanel.add(board[x][y]);
                buttonPanel.setBackground(new Color(237, 194, 46));
                buttonPanel.setLayout(new GridLayout(width,height));
            }
        }
        this.add(buttonPanel);
    }
    public Color getColor(String value){
        if (value.equals("2"))
        {
            return new Color( 238, 228, 218 );
        }
        else if (value.equals("4"))
        {
            return new Color( 27, 224, 200 );
        }
        else if (value.equals("8"))
        {
            return new Color( 242, 177, 121 );
        }
        else if (value.equals("16"))
        {
            return new Color( 245, 149, 99 );
        }
        else if (value.equals("32"))
        {
            return new Color( 246, 124, 95 );
        }
        else if (value.equals("64"))
        {
            return new Color( 246, 94, 59 );
        }
        else if (value.equals("128"))
        {
            return new Color( 237, 207, 114 );
        }
        else if (value.equals("256"))
        {
            return new Color( 237, 204, 97 );
        }
        else if (value.equals("512"))
        {
            return new Color( 237, 200, 80 );
        }
        else if (value.equals("1024"))
        {
            return new Color( 237, 197, 63 );
        }
        else
        {
            return new Color( 237, 194, 46 );
        }
    }
      
    
    public int getFSize(){
        if(width == 4)return 50;
        else if(width == 5)return 40;
        else if(width == 6)return 30;
        else return 20;
    }   
    

    public void updateBoard(){
        grid = gameLogic.getBoard();
        score.setText(getText("      Score: " + gameLogic.getScore()));
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(grid[x][y].equals("0"))board[x][y].setText(" ");
                else board[x][y].setText(grid[x][y]);
                if(grid[x][y].equals("2"))board[x][y].setForeground(Color.black);
                else board[x][y].setForeground(Color.white);
                board[x][y].setBackground(getColor(grid[x][y]));
            }
        }
    }

    public void makeMove(String direction){
        gameLogic.makeMoves(direction);
        updateBoard();
    }

    public boolean isWin(){
        return gameLogic.isWin();
    }

    public boolean isValid(String direction){
        return gameLogic.isValidMove(direction);
    }

    public void resetGame(){
        gameLogic = new logic(width,height);
        grid = gameLogic.getBoard();
        updateBoard();
    }

    public String getText(String word){
      if(word.length() > 0){
        if(word.length() > 14){
          word = word.substring(1,word.length());
          word = getText(word);
        }
      }
      return word;
    }

    public TextField getScore(){
      return score;
    }


}

class MyKeyListener implements KeyListener{
    private game Game;

    public MyKeyListener(game game){
        Game = game;
    }
 
    @Override
    public void keyPressed(KeyEvent e){

        if(Game.isWin()){
          Game.getScore().setText(Game.getText("      You Win!! :)"));
            return;
        } else if(!Game.isValid("up") && !Game.isValid("down") && !Game.isValid("left") && !Game.isValid("right")){
            Game.getScore().setText(Game.getText("      You Lose!! :("));
            return;
        
        }else {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                Game.resetGame();
            }

            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W && Game.isValid("up")){
                Game.makeMove("up");
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S  && Game.isValid("down")){
                Game.makeMove("down");
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A  && Game.isValid("left")){
                Game.makeMove("left");
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D  && Game.isValid("right")){
                Game.makeMove("right");
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
