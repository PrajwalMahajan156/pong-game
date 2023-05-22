import java.awt.*;
import java.util.Random;

public class Ball extends  Rectangle{

    int xvelocity;
    int yvelocity;
    int initialspeed=5;
    Random random;
    Ball(int x,int y, int width,int height){
        super(x, y, width, height);
        random =new Random();
        int RandomXDirection=random.nextInt(2);
        if(RandomXDirection==0){
            RandomXDirection--;
        }
        else{

        }
        setXDirection(RandomXDirection);

        int RandomYDirection=random.nextInt(2);
        if(RandomYDirection==0){
            RandomYDirection--;
        }
        setYDiraction(RandomYDirection);

    }

    public void setYDiraction(int randomYDirection) {
        yvelocity=randomYDirection;
    }

    public void setXDirection(int randomXDirection) {
        xvelocity=randomXDirection;
    }

    public void move(){
        x+=xvelocity;
        y+=yvelocity;

    }

    public void draw(Graphics g){

        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
        g.setColor(Color.white);
        g.drawLine(1000/2,0,1000/2,555);
    }
}
