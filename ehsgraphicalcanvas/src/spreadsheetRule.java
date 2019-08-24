	public class spreadsheetRule {
		private String 	rule = "";
		private String	cellRef = "";
		
		spreadsheetRule(String cellRef,String rule) {
			this.rule = rule;
			this.cellRef = cellRef;
		}
		public String rule() {return rule;}
		public String cellRef() {return cellRef;}
	}
