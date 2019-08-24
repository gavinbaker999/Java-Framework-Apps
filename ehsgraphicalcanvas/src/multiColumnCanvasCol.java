import java.util.Vector;

public class multiColumnCanvasCol {
		private String 		title;
		private boolean 	bEnabled;
		private String[] 	data;
		private boolean 	bComboCol;
		private boolean 	bUpdateDB;
		private String[] 	options;
		private String 		tooltipText;
		private	String 		tableName;
		private	String		colName;
		private String		unqColName;
		private Vector		unqColData = new Vector();
		
		multiColumnCanvasCol() {
			title = "Untitled";
			bEnabled = true;
			data = null;
			options = null;
			tooltipText = null;
			bComboCol = false;
			bUpdateDB = false;
			tableName = null;
			colName = null;
			unqColName = null;
		}
		multiColumnCanvasCol(String title,boolean bEnabled,String[] data) {
			this.title = title;
			this.bEnabled = bEnabled;
			this.data = data;
			this.bComboCol = false;
			this.tooltipText = null;
			this.options = null;
			this.bUpdateDB = false;
			this.tableName = null;
			this.colName = null;
			this.unqColName = null;
		}
		multiColumnCanvasCol(String title,boolean bEnabled,String[] data,String[] options) {
			this.title = title;
			this.bEnabled = bEnabled;
			this.data = data;
			this.bComboCol = true;
			this.tooltipText = null;
			this.options = options;
			this.bUpdateDB = false;
			this.tableName = null;
			this.colName = null;
			this.unqColName = null;
		}
		multiColumnCanvasCol(String title,boolean bEnabled,String tableName,String colName,String unqColName) {
			this.title = title;
			this.bEnabled = bEnabled;
			this.data = getDBFieldData();
			this.bComboCol = false;
			this.tooltipText = null;
			this.options = null;
			this.bUpdateDB = true;
			this.tableName = tableName;
			this.colName = colName;
			this.unqColName = unqColName;
		}
		multiColumnCanvasCol(String title,boolean bEnabled,String tableName,String colName,String unqColName,String[] options) {
			this.title = title;
			this.bEnabled = bEnabled;
			this.data = getDBFieldData();
			this.bComboCol = true;
			this.tooltipText = null;
			this.options = options;
			this.bUpdateDB = true;
			this.tableName = tableName;
			this.colName = colName;
			this.unqColName = unqColName;
	}
		public boolean isUpdateDB() {return bUpdateDB;}
		protected void finalize() throws Throwable {
			if (bUpdateDB) {setDBFieldData();}
			super.finalize();
		}
		void setDBFieldData() {
			String query = "";
			
			for (int i=0;i<data.length;i++) {
				query = "UPDATE " + tableName + " SET " + colName + "='" + 
						data[i] + "' WHERE " + unqColName + "=" + 
						(String)unqColData.elementAt(i);
				supportFunctions.getDBConn().executeSQLQuery(query,"");
			}
		}
		String[] getDBFieldData() {
			String query = "SELECT " + unqColName + " FROM " + tableName;
			String data = supportFunctions.getDBConn().executeSQLQuery(query,"");
			unqColData = supportFunctions.splitIntoTokens(data,",");

			query = "SELECT " + colName + " FROM " + tableName;
			data = supportFunctions.getDBConn().executeSQLQuery(query,"");
			Vector v = supportFunctions.splitIntoTokens(data,",");
			String[] tmp = new String[v.size()];
			v.copyInto(tmp);
			return tmp;
		}
		public String getTitle() {return title;}
		public void setTitle(String s) {title = s;}
		public boolean getEnabled() {return bEnabled;}
		public void setEnabled(boolean b) {bEnabled = b;}
		public String[] getData() {return data;}
		public void setData(String[] data) {this.data = data;}
		public void setData(int index,String value) {
			if ((data.length-1) < index) {return;}
			data[index] = value;
		}
		public String getData(int index) {
			if ((data.length-1) < index) {return null;}
			return data[index];
		}
		public int getNumDataItems() {return data.length;}
		public boolean isComboCol() {return bComboCol;}
		public String[] getComboOpts() {return options;}
		public void addDataItem(String s,int index) {
			if (isUpdateDB()) {supportFunctions.displayMessageDialog(null,"Operation not allowed for a DB column");return;}
			String[] newData = new String[data.length + 1];
			for (int i=0;i<data.length;i++) {newData[i] = data[i];}
			newData[data.length] = s;
			data = newData;
		}
		public void deleteDataItem(int index) {
			if (isUpdateDB()) {supportFunctions.displayMessageDialog(null,"Operation not allowed for a DB column");return;}
			if ((data.length-1) < index) {return;}
			String[] newData = new String[data.length - 1];
			int j = 0;
			for (int i=0;i<data.length;i++) {if (i == index) {} else {newData[j++] = data[i];}}
			data = newData;
		}
	}
