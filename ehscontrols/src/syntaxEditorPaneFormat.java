	public class syntaxEditorPaneFormat {
		private	String formatName;
		private	String formatRegEx;
		private	String formatStartHTML;
		private	String formatEndHTML;
		private boolean formatEnabled;
		
		public syntaxEditorPaneFormat(String name,String regex,String start,String end) {
			formatName = name;
			formatRegEx = regex;
			formatStartHTML = start.toLowerCase();
			formatEndHTML = end.toLowerCase();
			formatEnabled = true;
		}
		public void saveAsXML(org.w3c.dom.Document doc,org.w3c.dom.Element root) {
			org.w3c.dom.Element setting = doc.createElement("syntaxeditorpaneformat");
			setting.setAttribute("name",formatName);
			setting.setAttribute("regex",formatRegEx);
			setting.setAttribute("starthtml",formatStartHTML);
			setting.setAttribute("endhtml",formatEndHTML);
			root.appendChild(setting);
		}
		public void setEnabled(boolean bEnable) {formatEnabled = bEnable;}
		public boolean getEnabled() {return formatEnabled;}
		public String getFormatName() {return formatName;}
		public String getFormatRegEx() {return formatRegEx;}
		public String getFormatStartHTML() {return formatStartHTML;}
		public String getFormatEndHTML() {return formatEndHTML;}
		public void setFormatName(String s) {formatName = s;}
		public void setFormatRegEx(String s) {formatRegEx = s;}
		public void setFormatStartHTML(String s) {formatStartHTML = s;}
		public void setFormatEndHTML(String s) {formatEndHTML = s;}
	}
