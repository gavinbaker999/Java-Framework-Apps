// to make a memory XML data file persist call setFilename("...")
	public class memoryXmlDataFile extends xmlDataFile {
		private	String filename;
		
		memoryXmlDataFile() {
			filename = supportFunctions.getTmpFilename();
			openXMLDataFile(filename,"tmp");
		}
		protected void finalize() throws Throwable {
			closeXMLDataFile();
			if (getFilename().equals(filename)) {
				//deleteFilename(""+dataRelativePath+"/"+appDirectory+"/"+filename+".xml");
			}
		}
	}
