import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class customAction extends AbstractAction {
		   public customAction (String desc,customIcon ci) {
		   		  super(desc);
		   		  putValue(javax.swing.Action.SMALL_ICON,ci);
		   		  putValue(javax.swing.Action.SHORT_DESCRIPTION,desc);
		   }
		   public void actionPerformed(ActionEvent evt) {
		   }
	}
