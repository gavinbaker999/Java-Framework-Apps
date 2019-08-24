import javax.swing.JEditorPane;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

public class syntaxPaneEditorKit extends HTMLEditorKit {
		public void install(JEditorPane paneEditor) {
			super.install(paneEditor);
		}
		public ViewFactory getViewFactory() {
			return new syntaxPaneViewFactory();
		}
		public class syntaxPaneViewFactory extends HTMLEditorKit.HTMLFactory {
			public View create(javax.swing.text.Element elem) {
				return super.create(elem);
			}
		}
	}
