import javax.swing.*;

public class Project extends JApplet
 {
  private final int WIDTH = 450;
  private final int HEIGHT = 450;

  public void init()
   {
       Draw draw=new Draw();
       getContentPane().add(draw);
       setSize(WIDTH,HEIGHT);
   }
}