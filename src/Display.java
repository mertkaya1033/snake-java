import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JPanel implements ActionListener, KeyListener {

    public static final int DISPLAY_SIZE = 800;
    public static final int TILE_SIZE = 20;
    public static final int NUM_TILES = DISPLAY_SIZE / TILE_SIZE;

    private static Tile[][] field;

    private boolean gameOver = false;
    private Timer tm = new Timer(100, this);
    private Head snake;
    
    static {
        field = new Tile[NUM_TILES][NUM_TILES];
        resetField();
    }

    public Display(){
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.addKeyListener(this);
        this.snake = new Head(field, NUM_TILES/2, NUM_TILES/2, 0, 0);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Display.DISPLAY_SIZE, Display.DISPLAY_SIZE);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                g.setColor(Color.gray);
                g.fillRect(i*Display.TILE_SIZE, j*Display.TILE_SIZE,
                        Display.TILE_SIZE, Display.TILE_SIZE);
                field[j][i].display(g);
                g.fillRect((i * Display.TILE_SIZE) + 1, (j * Display.TILE_SIZE) + 1,
                        (Display.TILE_SIZE-2), (Display.TILE_SIZE-2));
            }
        }
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(snake.getLength()),10, 10);
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver) {
            gameOver = !snake.move(field);
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if(keycode == KeyEvent.VK_UP){
            snake.changeDirection(0, -1);
        }
        if(keycode == KeyEvent.VK_DOWN){
            snake.changeDirection(0, 1);
        }
        if(keycode == KeyEvent.VK_RIGHT){
            snake.changeDirection(1, 0);
        }
        if(keycode == KeyEvent.VK_LEFT){
            snake.changeDirection(-1, 0);
        }
        if (keycode == KeyEvent.VK_R && gameOver){
            gameOver = false;
            resetField();
            this.snake = new Head(field, NUM_TILES/2, NUM_TILES/2, 0, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private static void resetField(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 || i == field.length - 1 || j == 0 || j == field.length - 1) {
                    field[i][j] = Tile.WALL;
                } else {
                    field[i][j] = Tile.EMPTY;
                }
            }
        }
    }
}
