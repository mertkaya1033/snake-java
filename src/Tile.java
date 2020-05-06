import java.awt.*;

public  enum Tile implements Displayable{
    EMPTY, WALL, SNAKE, FOOD, HEAD;

    public void display(Graphics g) {
        if (this == WALL) {
            g.setColor(Color.black);
        }else if(this == SNAKE){
            g.setColor(Color.orange);
        }else if(this == FOOD){
            g.setColor(Color.red);
        }else if(this == HEAD){
            g.setColor(Color.ORANGE);
        } else{
            g.setColor(Color.white);
        }
    }
}
