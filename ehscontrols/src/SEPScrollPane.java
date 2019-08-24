import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class SEPScrollPane extends JScrollPane implements AdjustmentListener {
		public SEPScrollPane(JEditorPane jep) {
			super(jep);
			getVerticalScrollBar().addAdjustmentListener((AdjustmentListener) this);
		}
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int value = e.getValue();
		}
	}
