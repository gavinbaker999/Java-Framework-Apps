import javax.swing.text.AttributeSet;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;

// Our own version of an Iterator that allows us to look at non-leaf tags as well.
	public class TagIterator {
		private HTML.Tag tag;
		private ElementIterator pos;
		TagIterator(HTML.Tag t, javax.swing.text.Document doc) {
			tag = t;
			pos = new ElementIterator(doc);
			next();
		}
		public AttributeSet getAttributes() {
			javax.swing.text.Element elem = pos.current();
			if (elem != null) {
				AttributeSet a =
					(AttributeSet)elem.getAttributes().getAttribute(tag);
					if (a == null) {
						return elem.getAttributes();
					}
				return a;
			}
			return null;
		}
		public void next() {
			for (pos.next(); isValid(); pos.next()) {
				javax.swing.text.Element elem = pos.current();
				if (elem.getName().equals(tag.toString())) {
					break;
				}
				AttributeSet a = pos.current().getAttributes();
				if (a.isDefined(tag)) {
					// we found the next one
					break;
				}
			}
		}
		public HTML.Tag getTag() { return tag; }
		public boolean isValid() {
			return (pos.current() != null);
		}
	}
