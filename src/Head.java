public class Head extends Snake{
    private Food food;
    private int xDir, yDir;
    private boolean turning = false;
    private int length = 0;

    public Head(Tile[][] field, int x, int y, int xDir, int yDir){
        super(x, y);
        this.xDir = xDir;
        this.yDir = yDir;
        this.food = new Food(field);
    }

    @Override
    public boolean move(Tile[][] field){
        turning = false;
        this.addPosition(field, this.xDir, this.yDir);

        if(this.next != null){
            this.next.move(field);
        }

        if (field[y][x] == Tile.WALL || field[y][x] == Tile.SNAKE ){
            field[this.y][this.x] = Tile.HEAD;
            return false;
        }else if(food.isEaten()){
            this.eat(field);
            this.food.eaten();
            this.length++;
        }

        field[this.y][this.x] = Tile.HEAD;
        return true;
    }

    public void changeDirection(int xDir, int yDir) {
        if(((this.xDir == this.yDir)|| (xDir != this.xDir) == (yDir != this.yDir)) && !turning) {
            this.xDir = xDir;
            this.yDir = yDir;
            turning = true;
        }
    }

    public int getLength(){
        return this.length;
    }
}
