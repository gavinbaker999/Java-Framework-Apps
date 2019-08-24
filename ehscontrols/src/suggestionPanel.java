import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

public class suggestionPanel extends JPanel implements ItemListener {
		private syntaxEditorPane sep = null;
		
		public suggestionPanel(syntaxEditorPane sep) {
			this.sep = sep;
		}
		public syntaxEditorPane getSyntaxEditorPane() {return sep;}
		public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == evt.SELECTED) {
				List source = (List)evt.getSource();
				String item = source.getSelectedItem();
				itemSelected(item);
			}
		}
		public void itemSelected(String item) {
			//TRACE("suggestionPanel:Item Selected:" + item,4);
			getSyntaxEditorPane().suggestionSelected(item);
		}
	}
