import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.ArrayList;

public class Draw extends JPanel
{
    private final int GAME_TIME=45;
    private final int DRAW_SPEED=300;
    private Timer timer,timer1;
    private Random rand=new Random();
    private Color color;
    private Canvas canvas;
    private ArrayList<Circle> circles;
    private int score;
    private JLabel timeLabel,scoreLabel;
    private int time;
    private JPanel panel;
    private JSplitPane sp;
    private JButton start;

    public Draw()
    {
        canvas=new Canvas();
        circles=new ArrayList<Circle>();
        TimeListener lis=new TimeListener();
        timer1=new Timer(1000,lis);
        timeLabel=new JLabel(Integer.toString(time));
        scoreLabel=new JLabel("Score: " + score);
        start=new JButton("START");
        
        ButtonListener lis4=new ButtonListener();
        start.addActionListener(lis4);
        
        panel=new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(timeLabel);
        panel.add(start);
        panel.add(scoreLabel);
        
        sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel,canvas);
        sp.setDividerLocation(50);//set initial size of control panel
       
        setLayout(new BorderLayout());
        add(sp);
        
        AddCirclesListener lis2=new AddCirclesListener();
        timer=new Timer(DRAW_SPEED,lis2);
        MouseClicked lis3=new MouseClicked();
        canvas.addMouseListener(lis3);
    }
    
    private class ButtonListener implements ActionListener
    {
       public void actionPerformed(ActionEvent event)
         {
            if(event.getSource()==start)
            {
                score=0;
                scoreLabel.setText("Score: " + score);
                time=GAME_TIME;
                timeLabel.setText(Integer.toString(time));
                timer1.start();
                timer.start();
                start.setVisible(false);
                repaint();
            }
         }
         
    }
    
    private class Canvas extends JPanel
    {
        public void paintComponent(Graphics page)
        {
            super.paintComponent(page);
            setBackground(Color.white);
            
            for(int i=0;i<circles.size();i++)//print old circles
            {
                Circle circle=circles.get(i);
                page.setColor(circle.getColor());
                page.fillOval(circle.getX(),circle.getY(),circle.getSize(),circle.getSize());
            }
        }
    }
    
    private void newCircle()
    {
        int size=rand.nextInt(45)+3;
        int x = rand.nextInt(400);
        int y = rand.nextInt(400);
        
        if (x + size > 400)
        {
            x = x - (400 - (x + size));
        }
        if (y + size > 400)
        {
            y = y - (400 - (y + size));
        }
        
        Circle circle=new Circle(rand.nextInt(400),rand.nextInt(300),size,color,(int)Math.ceil(10/(Math.sqrt(size))));
        circles.add(circle);
    }
    
    
    public class TimeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            time--;
            timeLabel.setText(Integer.toString(time));
            if(time==0)
            {
                timer.stop();
                timer1.stop();
                circles = new ArrayList<Circle>();
                repaint();
                start.setText("RETRY");
                start.setVisible(true);
                timeLabel.setText("Game Over");
            }
        }
    }
    
    public class AddCirclesListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int red=rand.nextInt(255);
            int green=rand.nextInt(255);
            int blue=rand.nextInt(255);
            color=new Color(red,green,blue);
            newCircle();
            repaint();
        }
    }
    
    public class MouseClicked implements MouseListener
    {
        public void mousePressed(MouseEvent event){}
        public void mouseClicked(MouseEvent event){}
        public void mouseReleased(MouseEvent event)
        {
            Point point=event.getPoint();
            //System.out.println("X=" + point.x + " Y=" + point.y);
            
            for(int i=0;i<circles.size();i++)
            {
                Circle circle=circles.get(i);
                double halfSize=(double)(circle.getSize()/2.0);
                double x=(double)circle.getX()+halfSize;
                double y=(double)circle.getY()+halfSize;
                double distance = Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2));
                if(distance < halfSize)
                {
                    circles.remove(circles.get(i));
                    score+=circle.getPoints();
                    scoreLabel.setText("Score: " + score);
                    repaint();
                }
            }
            
        }
        public void mouseEntered(MouseEvent event){}
        public void mouseExited(MouseEvent event){}
        public void mouseDragged(MouseEvent event){}
    }
           
}