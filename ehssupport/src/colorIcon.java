import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

class colorIcon extends customIcon {
		  
		  public Color color;
		  
		  public colorIcon(Color color) {this.color = color;}
		  public void paintIcon(Component c,Graphics g,int x,int y) {
		  		 g.setColor(color);
				 g.fillRect(x,y,16,16);
		  } 
	}
