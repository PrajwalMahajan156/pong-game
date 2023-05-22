import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

    int id;
    int yvelocity;
    int speed= 10;

    Paddle(int x, int y, int paddle_width,int paddle_height,int id){
        super(x,y,paddle_width,paddle_height);
        this.id=id;

    }
    private void setYDirection(int Direction){

        yvelocity=Direction;
    }

    public void KeyPressed(KeyEvent e){
        switch (id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S)
                {
                   setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                }
                break;

        }

    }
    public void KeyReleased(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                }
                break;
        }

    }
    public void move(){
        y=y+yvelocity;

    }

    public void draw(Graphics g){
    if(id==1){
        g.setColor(Color.red);
    }
    else{
        g.setColor(Color.blue);
    }
    g.fillRect(x,y,width,height);
    }
}
