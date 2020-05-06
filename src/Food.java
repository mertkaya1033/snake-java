import java.awt.*;
import java.util.Random;

public class Food {
    private int x, y;
    private Tile[][] field;
    private Random random = new Random();

    public Food(Tile[][] field){
        this.field = field;
        this.eaten();
    }

    public void eaten(){
        int posX = random.nextInt(field.length), posY = random.nextInt(field.length);

        while(field[posY][posX] != Tile.EMPTY){
            posX = random.nextInt(field.length);
            posY = random.nextInt(field.length);
        }

        this.x = posX;
        this.y = posY;
        field[y][x] = Tile.FOOD;
    }

    public boolean isEaten() {
        return this.field[y][x] != Tile.FOOD;
    }

}
