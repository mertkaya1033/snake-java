
public class Snake{

    protected Snake next;
    protected Snake prev;
    protected int x, y;
    protected int prevX, prevY;

    public Snake(int x, int y, Snake next, Snake prev){
        this.next = next;
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
        this.prev = prev;
    }

    public Snake(int x, int y){
        this(x, y, null, null);
    }

    public boolean move(Tile[][] field){
        int[]nextPos = this.prev.getPrevPosition();
        this.setPosition(field, nextPos[0], nextPos[1]);
        if(this.next != null){
            this.next.move(field);
        }
        return true;
    }
    protected void addPosition(Tile[][] field, int xP, int yP){
        field[this.y][this.x] = Tile.EMPTY;
        this.prevX = this.x;
        this.prevY = this.y;
        this.x += xP;
        this.y += yP;
    }

    protected void setPosition(Tile[][] field, int x, int y){
        field[this.y][this.x] = Tile.EMPTY;
        this.prevX = this.x;
        this.prevY = this.y;
        this.x = x;
        this.y = y;
        field[this.y][this.x] = Tile.SNAKE;
    }

    public int[] getPrevPosition(){
        return new int[]{this.prevX, this.prevY};
    }

    public void eat(Tile[][] field){
        if(this.next == null){
            field[prevY][prevX] = Tile.SNAKE;
            this.next = new Snake(this.prevX, this.prevY, null, this);
        }else{
            this.next.eat(field);
        }
    }
}
