import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class JScrollPaneSolidColorCorner extends JComponent {
		private	Color	c = null;
		
		public JScrollPaneSolidColorCorner(Color c) {
			this.c = c;
		}
	    protected void paintComponent(Graphics g) {
	        g.setColor(c);
	        g.fillRect(0, 0, getWidth(), getHeight());
	    }
	}
