	public class statusCanvasProp {
		private String name;
		private String value;
		private	String intValue;
		private boolean enabled;
		private String[] options;
		
		statusCanvasProp(String n,String v,String[] opts) {
			name = n;
			value = v;
			intValue = v;
			enabled = true;
			options = opts;
		}
		statusCanvasProp(String n,String v) {
			name = n;
			value = v;
			intValue = v;
			enabled = true;
			options = null;
		}
		statusCanvasProp() {
			name = "";
			value = "";
			enabled = true;
			options = null;
		}
		public Class getPropClass() {
			String tmp = "";
			return tmp.getClass();
		}
		public boolean isCombo() {if (options==null) {return false;} else {return true;}}
		public String[] getComboOpts() {return options;}
		public void setComboOpts(String[] opts) {options = opts;}
		public boolean getEnabled() {return enabled;}
		public void setEnabled(boolean b) {enabled = b;}
		public String getName() {return name;}
		public String getValue() {return value;}
		public String getIntValue() {return intValue;}
		public int getValueAsInt() {
			return Integer.parseInt(value);
		}
		public void setValueAsInt(int i) {
			value = String.valueOf(i);
		}
		public void setValue(String s) {value = s;}
	}
