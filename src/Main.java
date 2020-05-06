import javax.swing.*;

public class Main {


    public static void main(String[] args){
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Display.DISPLAY_SIZE, Display.DISPLAY_SIZE+30);
        frame.add(new Display());
        frame.setVisible(true);

    }
}
