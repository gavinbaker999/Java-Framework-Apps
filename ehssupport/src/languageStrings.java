import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class languageStrings {
		private Locale locale = null;
		private ResourceBundle rb = null;
		
		public Locale getLocale() {return locale;}
		public ResourceBundle getBundle() {return rb;}
		public languageStrings () {
			setNewLang(Locale.getDefault());
		}
		public languageStrings (Locale locale) {
			setNewLang(locale);
		}
		public void setNewLang(Locale locale) {
			this.locale = locale;
			loadRB(locale);
		}
		public void loadRB(Locale locale){
			try {
				rb = ResourceBundle.getBundle("intlresources",locale);
			} catch (Exception e) {e.printStackTrace();}
		}
		public Object getObject(String key) {
			if (rb == null) {return "";}
			return rb.getObject(key);
		}
		public String getString(String key) {
			if (rb == null) {return "";}
			return rb.getString(key);
		}
		public Enumeration getKeys() {
			if (rb == null) {return null;}
				return rb.getKeys();
		}
	}	
