import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    int width=1000;
    int height=(int)(width*0.555);
    Dimension screen=new Dimension(width,height);

    int paddle_width=25;
    int paddle_height=100;
    int ball_diameter =20;
    Image image;

    Graphics graphics;
    Paddle p1,p2;

    Ball ball;

    Score score = new Score(width,height);

    Thread GameThread;
    GamePanel(){

        setPreferredSize(screen);
        GameThread = new Thread(this);
        GameThread.start();
        addKeyListener(new AL());
        setFocusable(true);
        newpaddle();
        newball();
    }

    private void newball() {
        Random random=new Random();
        ball=new Ball(width/2,random.nextInt(height-ball_diameter), ball_diameter, ball_diameter);
    }

    private void newpaddle() {
        p1=new Paddle(0,(height-paddle_height)/2,paddle_width,paddle_height,1);
        p2=new Paddle(width-paddle_width,(height-paddle_height)/2,paddle_width,paddle_height,2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) {
        p1.draw(g);
        p2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void run(){
        Long lastTime=System.nanoTime();
        double amountTime=60;
        double ns= 1000000000/amountTime;
        double delta=0;
        while (true){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1)
            {
                repaint();
                move();
                checkcollision();
                delta--;
            }
        }

    }

    private void checkcollision() {

        if(ball.y<=0){
            ball.setYDiraction(-ball.yvelocity);
        }
        if(ball.y>=height-ball_diameter){
            ball.setYDiraction(-ball.yvelocity);
        }
        if(ball.intersects(p1)){
            ball.xvelocity=-ball.xvelocity;
            ball.xvelocity++;
            if(ball.yvelocity>0){
                ball.yvelocity++;
            }
            else{
                ball.yvelocity--;
            }
            ball.setYDiraction(ball.yvelocity);
            ball.setXDirection(ball.xvelocity);
        }
        if(ball.intersects(p2)){
            ball.xvelocity=-ball.xvelocity;
            ball.xvelocity--;
            if(ball.yvelocity>0){
                ball.yvelocity++;
            }
            else{
                ball.yvelocity--;
            }
            ball.setYDiraction(ball.yvelocity);
            ball.setXDirection(ball.xvelocity);
        }
        if(p1.y<=0){
            p1.y=0;
        }
        if(p1.y>=height-paddle_height){
            p1.y=height-paddle_height;
        }
        if(p2.y<=0){
            p2.y=0;
        }
        if(p2.y>=height-paddle_height){
            p2.y=height-paddle_height;
        }
        if(ball.x>=width-ball_diameter){
            newball();
            newpaddle();
            score.player1++;
        }
        if(ball.x<=0){
            newpaddle();
            newball();
            score.player2++;
        }
    }

    private void move() {
        p1.move();
        p2.move();
        ball.move();
    }

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            p1.KeyPressed(e);
            p2.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            p1.KeyReleased(e);
            p2.KeyReleased(e);
        }
    }
}
