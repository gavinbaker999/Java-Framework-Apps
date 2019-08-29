import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class colorAction extends AbstractAction {
		   private	Color	 		color;
		   private	colorActionUtils	target;
		   
		   public colorAction(Color color) { 
			   target = null;
		   	   this.color = color;
			   putValue(javax.swing.Action.SMALL_ICON,new colorIcon(color));
		   }
		   public void actionPerformed(ActionEvent evt) {
			   if (target != null) {target.colorSelected(color);}
		   }
		   public Color getColor() {return color;}
		   public void removeColorActionListener() {target = null;}
		   public void addColorActionListener(colorActionUtils cau) {target = cau;}
	}
