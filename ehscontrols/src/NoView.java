import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.View;

// An example "no view" View class to hide any content found inside
	// Element elem. Basically, we return a 0x0 bounding box
	// and do nothing in the paint() method.
	public class NoView extends View {
		public NoView(javax.swing.text.Element elem) {
			super(elem);
			setSize(0.0f, 0.0f);
		}
		public int viewToModel(float fx, float fy, Shape a,Position.Bias[] bias) {
			return 0;
		}
		public Shape modelToView(int pos, Shape a, Position.Bias b)
			throws BadLocationException {
				return new Rectangle(0, 0);
			}
		public float getPreferredSpan(int axis) {
			return 0.0f;
		} 
		public void paint(Graphics g, Shape allocation) {
		}
	}
