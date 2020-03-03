import java.applet.*;
import java.awt.*;
import javax.swing.JFrame;  
public class appletcheck extends Canvas
{
    public void paint(Graphics g)
    {
    	Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("CardPics/Slide1.jpg");
        g.drawImage(i,200,300,700,350,this);  
        g.drawRect(200,300,700,350);
        g.setFont(new Font("TimesRoman", Font.PLAIN,60));
        g.drawString("a",500,500);
        i=t.getImage("CardPics/Slide2.jpg");
        g.drawImage(i,1000,300,700,350,this);  
        g.drawRect(1000,300,700,350);
    }
    public static void main(String[] args) {
    	appletcheck m=new appletcheck();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(1920,1080);
        f.setVisible(true);
    }
}