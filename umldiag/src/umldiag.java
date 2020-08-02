import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.*;
import java.awt.color.*;				// ColorSpace
import java.awt.geom.*;					// AffineTransform
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.List;
import java.awt.print.*;
import java.util.Date;
import java.util.Map;
import java.text.*;
import java.lang.reflect.*;
import java.beans.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.Timer;
import javax.sound.sampled.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.OutputKeys.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.script.*;
import javax.swing.filechooser.*;
//import javax.xml.ws.*;
//import javax.xml.ws.handler.*;
//import javax.xml.ws.handler.soap.*;
//import javax.xml.soap.*;
import javax.xml.namespace.QName;
//import javax.xml.ws.handler.Handler;
//import javax.xml.messaging.*;
import javax.xml.xpath.XPath; 
import javax.xml.xpath.XPathConstants; 
import javax.xml.xpath.XPathExpressionException; 
import javax.xml.xpath.XPathFactory;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.transform.Source;

import javax.sound.sampled.*;
//import sun.audio.*;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;
import javax.imageio.event.*;
import javax.imageio.metadata.*;
import javax.imageio.plugins.jpeg.*;
import javax.imageio.plugins.bmp.*;
import javax.imageio.spi.*;
import javax.imageio.stream.*;
import javax.imageio.*;
//import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.html.ParagraphView;
import java.awt.datatransfer.*;

import javax.swing.text.Element;
import javax.swing.text.Document;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

//import sun.misc.Unsafe; 
//import sun.reflect.ReflectionFactory;  

public class umldiag extends JApplet implements ChangeListener,Runnable
{
	public		String		dataRelativePath = ".."; 

	protected	Thread		systemThread1;
	protected	mainCard	mainTab;

	protected 	CardLayout 	layout;
	protected 	Panel		cards;

	protected 	int			charWidth;
	protected	int			charHeight;

	protected	registrationinfo systemUserReg;
	protected	JToolBar	roomToolbar;
	
	protected	AppletContext	ac;
	protected	languageStrings	lStrings;
	protected	Container		contentPane;
	protected	boolean 		runThreads = true;	
	protected	Frame			parentFrame = null;
	
	protected	JTabbedPane	tabPane;

	protected 	helpDialog helpDlg = null;

	protected	javax.swing.Action help,about;
			
	protected	customIcon			ciHelp;

	protected	 int		TRACELEVEL = 4;
		
	protected	static final int		sysThreadSleep = 1000;
	protected	static final int		systemMsgThreadPrioity = 7;
	
	protected   static final int 		defaultDialogX = 150;
	protected	static final int 		defaultDialogY = 90;	
	protected	static final int		defaultMenuX = 100;
	protected	static final int		defaultMenuY = 5;
	protected	static final int		iconWidth = 16;
	protected	static final int		iconHeight = 16;

	protected	static final int		dcTypeUML = 10;
	protected	static final int		dcTypeUMLSequence = 11;
	protected	static final int		dcTypeUMLUseCase = 12;
	protected	static final int		dcTypeUMLState = 13;

	protected	static final int 		visibleDCWidthChars = 140;
	protected	static final int 		visibleDCHeightChars = 30;

	protected	String		exHelpFile=dataRelativePath+"/"+appDirectory+"/"+"readme.txt";
	protected	static String			exFAQFile="";
	protected	static final String		appDirectory = "umldiag"; 
	protected	static final String		appClassName = "umldiag";
	protected	static final String		appBaseURL = "http://endhousesoftware.byethost11.com";
	protected	static final String		transtableMappingFilename = "transtablemap.dat";
	protected	static final String		containerClassesFilename = "containerclasses.dat";
	protected	static final String		globalDataClass = "GlobalData";
		
	public		static final String		buildDate = "@@@Build Date: 22-July-2020 03:32 PM Build Number: 35@@@";
	public		static final String		frameworkBuildDate="###JAVA Framework (Version 1.41-RC3)###";
	public 		static final String		gitVersionInfo = "!!!Git Version : 32.9525510.refactor-dirty.2019-08-09.22:37:17!!!";

	public		String		baseUMLTransTableName = dataRelativePath + "/" + appDirectory + "/transtables/";
	public					 String		UMLTypes = "boolean,byte,char,double,float,int,long,short,String,Integer,DateFormat";
	
	public		String		splashJPG = dataRelativePath+"/"+appDirectory+"/umlan_logo.jpg";
	public		String		productKBFile = "uml_kb.txt";
	
	public		static final int 		iNumberCharacetrsInClassNotePopupWindowRow = 25;
	public		static final int		UMLCONN_TOP = 0;
	public		static final int		UMLCONN_BOTTOM = 1;
	public		static final int		UMLCONN_RIGHT = 2;
	public		static final int		UMLCONN_LEFT = 3;
	
	protected 	MediaTracker			bilmt = new MediaTracker(this);
	public		Component				lastPositionWindow = null;
	
	public		umlDiagramStruct		umlDiagram = null;
	public 		scrollableDrawingCanvas sDC=null;
	public		drawingItem				globalDrawingItem = null;
	public		panelDialog				umlInfoHoverWindow = null;
	public		String					notConnectedString = "Not Connected";
	public		int						umlUseCaseSizeX = 150;
	public		int						umlUseCaseSizeY = 75;
	public		int						umlSequenceSizeX = 150;
	public		int						umlSequenceSizeY = 75;
	public		int						umlStateSizeX = 150;
	public		int						umlStateSizeY = 75;

	public		Map<String,Vector>		transTableMap = new HashMap<String,Vector>();
	
	public drawingItem getGlobalDrawingItem() {return globalDrawingItem;}
	public void setGlobalDrawingItem(drawingItem d) {globalDrawingItem = d;}
	public panelDialog getUMLInfoWindow() {return umlInfoHoverWindow;}
	public void setUMLInfoWindow(panelDialog m3d) {umlInfoHoverWindow = m3d;}
	public drawingCanvas getDrawingCanvas() {
		if (sDC != null) {
			return sDC.getDC();
		}
		
		return (drawingCanvas)null;
	}

	public int getMaxStringWidthInPixels(String string) {
		return getGraphics().getFontMetrics().stringWidth(string);
	}
	public int getMaxStringWidthInPixels(String[] strings) {
		int maxWidth = 0;
		for (int i=0;i<strings.length;i++) {
			int width = getMaxStringWidthInPixels(strings[i]);
			if (width > maxWidth) {maxWidth = width;}
		}
		return maxWidth;
	}
	public int getMaxStringWidthInPixels(Vector strings) {
		int maxWidth = 0;
		for (int i=0;i<strings.size();i++) {
			String s = (String)strings.elementAt(i);
			int width = getMaxStringWidthInPixels(s);
			if (width > maxWidth) {maxWidth = width;}
		}
		return maxWidth;
	}

	static appletframe theApp = null;
	Properties commandLineArgs = new Properties();
	public void extraStatusInfo() {
		
	}
	public void applicationCode(String[] args) {	
		int argID = 0;
		TRACELEVEL = 999;
		boolean bDumpSymbols = false;
		boolean bStatus = false;
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("symboldump")) {bDumpSymbols = true;continue;}
			if (args[i].equals("trace")) {
				TRACELEVEL = 4;
				continue;
			}
			if (args[i].equals("status")) {
				bStatus = true;
			}
			if (args[i].equals("about")) {
				BufferedReader br = null;
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(dataRelativePath+"/"+appDirectory+"/readme.txt"));
					while ((sCurrentLine = br.readLine()) != null) {
						System.out.println(sCurrentLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				continue;
			}
			StringTokenizer st = new StringTokenizer(args[i],"=");
			Vector v = new Vector();
			while(st.hasMoreTokens()) {
				v.addElement(st.nextToken());
			} 
			if (v.size() == 1) {v.insertElementAt("arg"+String.valueOf(argID++),0);}
			String opt = (String)v.elementAt(0);
			//if (opt.charAt(0) == '-') {opt = opt.substring(1);} // remove - character
			commandLineArgs.put(opt,(String)v.elementAt(1));
		}
		
		systemUserReg = new registrationinfo(appDirectory,"UML Work Bench","UML Work Bench (Application)","UD1000","03.00.0000.00","09/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "UML Work Bench";
		System.out.println(systemUserReg.getApplicationInfoText() + "\n");
		supportFunctions.connectDatabase(); 
		supportFunctions.getDBConn().connect();
		systemUserReg.registerUser(true);

		if (bStatus) {
			if (supportFunctions.getDBConn().getConnection() == null) {
				System.out.println("Database Status: FAILED");
			} else {
				System.out.println("Database Status: OK");
				System.out.println("Database Product: " + supportFunctions.getDBConn().getDatabaseProduct());
				System.out.println("Database Version:" + supportFunctions.getDBConn().getDatabaseVersion());				
			}
			System.out.println("Registered To: " + systemUserReg.getUserName());

			int p[]= {1,2,5,4,3,5,2,3,1,7,2,5,5,7,7,4,5,5,6,1};
			int result[] = supportFunctions.boundingBoxOfPoints(p);
			System.out.println("Bounding Cords:" + String.valueOf(result[0]) + "," + String.valueOf(result[1]) + "," + String.valueOf(result[2]) + "," + String.valueOf(result[3]));
			int result1[] = supportFunctions.lineIntersectionPoint2D(1,1,5,5,2,4,7,4);
			System.out.println("Intersection Cords:" + String.valueOf(result1[0]) + "," + String.valueOf(result1[1]));
			int result2[] = supportFunctions.lineIntersectsShapeAtCords((Shape)new Rectangle(100,100,30,50),80,50,115,125);
			System.out.println("IntersectionShape Cords:" + String.valueOf(result2[0]) + "," + String.valueOf(result2[1]) + "," + String.valueOf(result2[2]) + "," + String.valueOf(result2[3]));

			extraStatusInfo();
			
			supportFunctions.getDBConn().disconnect();
			System.exit(0);			
		}

		String file = (String)commandLineArgs.get("arg0");
		if (file != null) {
			umlDiagram = new umlDiagramStruct(); // must be first thing created BEFORE scrollabecanvas
			mainTab = new mainCard();
	
			System.out.println("Compiling " + file + "\n");
	
			modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog(systemUserReg.getAppName());
			msgD.setText("Compiling UML Diagram");
			mainTab.setCompiler(mainTab.getLanguageTransTable(supportFunctions.getFilenameExt(file)));
			if (mainTab.getCompiler().compile(file)) 
			{		
				umlDiagram.setNewCompile(true);
				// account for the extra _umldiag
				//String tmp = supportFunctions.getFilenameNoExt(supportFunctions.getFilename(file));
				//file = tmp + "_umldiag.xml";
				//mainTab.generateUMLDiagram(file);
				System.out.println("Compile successful.\n");
			}
			else {
				System.out.println(mainTab.getCompiler().getErrorString() + "\n");
			}
			if (bDumpSymbols) {
				//System.out.println("\n" + mainTab.getCompiler().getAllSymbolTable().dumpSymbols());
			}
		} else {
			System.out.println("Usage: java -jar umldiag.jar [status] [gui] [trace] [symboldump] [about] filename\n");
		}
		supportFunctions.getDBConn().disconnect();
		System.exit(0);
	}
	
//public static Unsafe getUnsafe() throws Exception{
//            Field f = Unsafe.class.getDeclaredField("theUnsafe");
//            f.setAccessible(true);
//           return (Unsafe)f.get(null);
// }
	
	public static void main(String[] args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("gui")) {
				ehsConstants.bRunAppWithGUI = true;
			}
		}
		if (ehsConstants.bRunAppWithGUI) {
			new appletframe(new umldiag(),ehsConstants.windowXMax,ehsConstants.windowYMax);
		} else {
			theApp = new appletframe(new umldiag());
			umldiag tmp = (umldiag)theApp.getApplet();
			tmp.applicationCode(args);				
		}
	}
 
	public void start() {
		TRACE("start() called",4);
		runThreads = true;
		systemThread1 = new Thread(this);
		systemThread1.setPriority(systemMsgThreadPrioity);
		systemThread1.setName("UML Analyser - System Message Thread 1");
		systemThread1.start();
	}

	public void stop() {
		TRACE("stop() called",4);
		runThreads = false;
	}

	public void destroySystem() {
		mainTab.destroyMainCard();
		
		destroyApplication();
	}
	public void destroyApplication() {	
		runThreads = false;
		finishPerApplicationProcess();
		supportFunctions.getDBConn().disconnect();
	}
	public void destroy() {
		TRACE("destroy() called",4);
		destroySystem();
	}

	public void run() {
		while (runThreads) {
			try {
				systemThread1.sleep(sysThreadSleep);
			} catch (InterruptedException e) {break;}	
		}
	}	
	  
	public helpDialog displayHelpDialog() {
		   helpDialog d = new helpDialog(supportFunctions.getTopLevelParent(this));
		   d.setLocation(defaultDialogX,defaultDialogY);
		   return d;
	}
	
	  public void TRACE(String msg) {
		  TRACE(msg,3);
	  }
	  public void TRACE(String msg,Vector v,int level) {
		if (v.size() == 0) {TRACE(msg,level);return;}
		String tmp = (String)v.elementAt(0);
		for (int i=1;i<v.size();i++) {
			tmp = tmp + "," + (String)v.elementAt(i);
		}
		TRACE(msg + ":" + tmp,level);
	  }
	  public void TRACE(String msg,int level) {
		if(level < TRACELEVEL) {return;}			
		int threadP = Thread.currentThread().getPriority();
		System.err.println("("+supportFunctions.currentDate()+" "+supportFunctions.currentTime()+") ["+Thread.currentThread().getName()+" ("+String.valueOf(threadP)+")] "+msg);
//		System.err.println("\n");
	  }	  
	
	public class UMLClassFunctionData {
		private	String 		functionAccess;
		private	String		functionExAccess;
		private	String		functionReturnType;
		private	String		functionName;
		private	String		functionParameters;
		private	ImageIcon[] icons;
		
		public UMLClassFunctionData() {
			functionAccess = "";
			functionReturnType = "";
			functionName = "";
			functionParameters = "";
			functionExAccess = "";
			icons = null;
		}
		public ImageIcon[] getIcons() {return icons;}
		public void setIcons(ImageIcon[] i) {
			icons = new ImageIcon[i.length];
			for (int j=0;j<i.length;j++) {
				icons[j] = i[j];
			}
		}
		public String getFunctionAccess() {return functionAccess;}
		public void	setFunctionAccess(String s) {functionAccess = s;}
		public String getFunctionExAccess() {return functionExAccess;}
		public void	setFunctionExAccess(String s) {functionExAccess = s;}
		public String getFunctionReturnType() {return functionReturnType;}
		public void	setFunctionReturnType(String s) {functionReturnType = s;}
		public String getFunctionName() {return functionName;}
		public void	setFunctionName(String s) {functionName = s;}
		public String getFunctionParameters() {return functionParameters;}
		public void	setFunctionParameters(String s) {functionParameters = s;}
	}
	public class UMLClassVariableData {
		private	String 		variableAccess;
		private	String		variableExAccess;
		private	String		variableType;
		private	String		variableName;
		private	String		variableDefaultValue;
		private String		variableTemplateType;
		private ImageIcon[]	icons;
		
		public UMLClassVariableData() {
			variableAccess = "";
			variableType = "";
			variableName = "";
			variableDefaultValue = "";
			variableExAccess = "";
			variableTemplateType = "";
			icons = null;
		}
		public ImageIcon[] getIcons() {return icons;}
		public void setIcons(ImageIcon[] i) {
			icons = new ImageIcon[i.length];
			for (int j=0;j<i.length;j++) {
				icons[j] = i[j];
			}
		}
		public String getVariableTemplateType() {return variableTemplateType;}
		public void	setVariableTemplateType(String s) {variableTemplateType = s;}		
		public String getVariableAccess() {return variableAccess;}
		public void	setVariableAccess(String s) {variableAccess = s;}		
		public String getVariableExAccess() {return variableExAccess;}
		public void	setVariableExAccess(String s) {variableExAccess = s;}		
		public String getVariableType() {
			if (!variableTemplateType.equals("-")) {
				return variableTemplateType + "<" + variableType + ">";
			} else {return variableType;}
		}
		public void	setVariableType(String s) {variableType = s;}		
		public String getVariableName() {return variableName;}
		public void	setVariableName(String s) {variableName = s;}
		public String getVariableDefaultValue() {return variableDefaultValue;}
		public void	setVariableDefaultValue(String s) {variableDefaultValue = s;}				
	}
	public class UMLClassData {
		private	String 		className;
		private String		classExAccess;
		private	String 		classExtends;
		private	String 		classImplements;
		private String 		classNotes;
		private	Vector		classVariables = new Vector();
		private	Vector		classFunctions = new Vector();
		private	boolean		bInterface,bEnum;
		private String		classPackage;
		private String 		classScope;
		private String 		templateType;	
		
		public UMLClassData() {
			className = "";
			classExtends = "";
			classImplements = "";
			classNotes = "";
			bInterface = false;
			bEnum = false;
			classExAccess = "";
			classPackage = "";
			classScope = "";
			templateType = "";
		}
		public String getClassExAccess() {return classExAccess;}
		public void	setClassExAccess(String s) {classExAccess = s;}		
		public String getPackageName() {return classPackage;}
		public void	setPackageName(String s) {classPackage = s;}		
		public String getScope() {return classScope;}
		public void	setScope(String s) {classScope = s;}		
		public boolean hasInterfaces() {
			if (classImplements.equals("-")) {return false;}
			
			return true;
		}
		public boolean hasClassNotes() {
			if (classNotes.equals("-")) {return false;}
			
			return true;
		}
		public boolean isTemplate() {if (templateType.length() != 0) {return true;} else {return false;}}
		public String getTemplateType() {return templateType;}
		public void setTemplateType(String s) {templateType = s;}
		public boolean isInterface() {return bInterface;}
		public void setInterface(boolean b) {bInterface = b;}
		public boolean isEnum() {return bEnum;}
		public void setEnum(boolean b) {bEnum = b;}
		public String getClassName() {return className;}
		public void setClassName(String s) {className = s;}
		public String getClassExtends() {return classExtends;}
		public void setClassExtends(String s) {classExtends = s;}
		public String getClassImplements() {return classImplements;}
		public void setClassImplements(String s) {classImplements = s;}
		public String getClassNotes() {return classNotes;}
		public void setClassNotes(String s) {classNotes = s;}
		public boolean isFunctionAConstructor(int index) {
			boolean bRet = false;
			if (index < 0 || index > classFunctions.size()-1) {return bRet;}
			UMLClassFunctionData tmp = (UMLClassFunctionData)classFunctions.elementAt(index);
			if (tmp.getFunctionName().equals(getClassName())) {bRet = true;}
			if (tmp.getFunctionReturnType().equals(getClassName())) {bRet = true;}
			
			return bRet;
		}
		public UMLClassFunctionData[] getClassFunctionStrings() {
			UMLClassFunctionData[] list = new UMLClassFunctionData[classFunctions.size()];
			for (int i=0;i<classFunctions.size();i++) {
				UMLClassFunctionData tmp = (UMLClassFunctionData)classFunctions.elementAt(i);
				list[i] = tmp;
			}
			return list;
		}
		public boolean isMemberVariable(String s) {
			String[] tmp = getRawClassVariableStrings();
			for (int i=0;i<tmp.length;i++) {
				if (s.equals(tmp[i])) {return true;}
			}
			return false;
		}
		public boolean isMemberFunction(String s) {
			String[] tmp = getRawClassFunctionStrings();
			for (int i=0;i<tmp.length;i++) {
				if (s.equals(tmp[i])) {return true;}
			}
			return false;
		}
		public String[] getRawClassFunctionStrings() {
			String[] list = new String[classFunctions.size()];
			for (int i=0;i<classFunctions.size();i++) {
				UMLClassFunctionData tmp = (UMLClassFunctionData)classFunctions.elementAt(i);
				list[i] = tmp.getFunctionName();
			}
			return list;
		}
		public String[] getRawClassVariableStrings() {
			String[] list = new String[classVariables.size()];
			for (int i=0;i<classVariables.size();i++) {
				UMLClassVariableData tmp = (UMLClassVariableData)classVariables.elementAt(i);
				list[i] = tmp.getVariableName();
			}
			return list;
		}
		public String[] getRawLinkVariableStrings(String className) {
			Vector v = new Vector();
			for (int i=0;i<umlDiagram.UMLConnectors.size();i++) {
				UMLConnector conn = (UMLConnector)umlDiagram.UMLConnectors.elementAt(i);
				UMLDrawingItem dStart = (UMLDrawingItem)conn.getStart();
				if (dStart.getData().getClassName().equals(className) && conn.getType() != UMLConnectorType.HEIR) {
					v.addElement("link"+conn.getVariable());
				}
			}
			String[] list = new String[v.size()];
			v.copyInto(list);
			return list;
		}
		public UMLClassVariableData[] getClassVariableStrings() {
			UMLClassVariableData[] list = new UMLClassVariableData[classVariables.size()];
			for (int i=0;i<classVariables.size();i++) {
				UMLClassVariableData tmp = (UMLClassVariableData)classVariables.elementAt(i);
				list[i] = tmp;
			}
			return list;
		}
		public void addClassVariable(UMLClassVariableData data) {
			classVariables.addElement(data);
		}
		public void addClassFunction(UMLClassFunctionData data) {
			classFunctions.addElement(data);
		}
		public void removeAllClassVariables() {classVariables.removeAllElements();}
		public void removeAllClassFunctions() {classFunctions.removeAllElements();}
	}
	public enum UMLConnectorType {HEIR("HEIR",dcTypeUML),AREG("AREG",dcTypeUML),DEPENDS("DEPENDS",dcTypeUML),
			SEQUENCENONE("SEQUENCENONE",dcTypeUMLSequence),SEQUENCESYNC("SEQUENCESYNC",dcTypeUMLSequence),
			SEQUENCEASYNC("SEQUENCEASYNC",dcTypeUMLSequence),SEQUENCESIMPLE("SEQUENCESIMPLE",dcTypeUMLSequence),
			SEQUENCERETURN("SEQUENCERETURN",dcTypeUMLSequence),SEQUENCESYNCRETURN("SEQUENCESYNCRETURN",dcTypeUMLSequence),
			USECASE("USECASE",dcTypeUMLUseCase),STATESTART("STATESTART",dcTypeUMLState),STATEEND("STATEEND",dcTypeUMLState),
			STATEHISTORY("STATEHISTORY",dcTypeUMLState),STATENORMAL("STATENORMAL",dcTypeUMLState),
			STATEBRANCH("STATEBRANCH",dcTypeUMLState),STATESYNC("STATESYNC",dcTypeUMLState);
		private String 	description;
		private int		type;
		
		UMLConnectorType(String description,int type) {
			this.description = description;
			this.type = type;
		}
		public String description() {return description;}
		public int type() {return type;}
	}
	public class UMLBaseConnector {
		public drawingItem	start,end;
		public UMLConnectorType	type;
		public int				id;
		public Point 			ptStart;
		public Point			ptEnd;
		public String			connText;
		
		public UMLBaseConnector(drawingItem start,drawingItem end,UMLConnectorType type,int id) {
			this.start = start;
			this.end = end;
			this.type = type;
			this.id = id;
			this.ptStart = new Point(-1,-1);
			this.ptEnd = new Point(-1,-1);
			this.connText = "";
		}
		public void setConnText(String text) {connText = text;}
		public String getConnText() {return connText;}
		public boolean isCordsValid() {
			if (ptStart.x == -1) {return false;}
			return true;
		}
		public void setStart(Point pt) {ptStart = pt;}
		public void setEnd(Point pt) {ptEnd = pt;}
		public Point getStart(Point pt) {return ptStart;}
		public Point getEnd(Point pt) {return ptEnd;}
		public boolean hitTest(Graphics g,int x,int y) {
			GeneralPath p = new GeneralPath();
			p.moveTo(ptStart.x,ptStart.y);
			p.lineTo(ptEnd.x,ptEnd.y);
			Rectangle r = new Rectangle(x-2,y-2,4,4);
			Graphics2D g2d = (Graphics2D)g;
			return g2d.hit(r,p,true);
		}
		public drawingItem getStart() {return start;}
		public drawingItem getEnd() {return end;}
		public int getID() {return id;}
		public String getIDAsString() {return String.valueOf(id);}
		public String getTypeAsString() {
			return type.description();
		}
		public UMLConnectorType getType() {return type;}
		public void setType(UMLConnectorType t) {type = t;}
		public void draw(Graphics2D g2d) {;}
		public boolean isStartConnectedTo(drawingItem d) {
			if (d == start) {return true;}
			return false;
		}
		public boolean isEndConnectedTo(drawingItem d) {
			if (d == end) {return true;}
			return false;
		}
	}
	
	public class UMLStateConnector extends UMLBaseConnector {
		public UMLStateConnector(UMLStateDrawingItem start,UMLStateDrawingItem end,UMLConnectorType type,int id) {
			super(start,end,type,id);
		}
		public void draw(Graphics2D g2d) {
			if (umlDiagram.getDiagramType() != UMLDiagramType.STATE) {return;}
		}		
	}
	public class UMLSequenceConnector extends UMLBaseConnector {
		public UMLSequenceConnector(UMLSequenceDrawingItem start,UMLSequenceDrawingItem end,UMLConnectorType type,int id) {
			super(start,end,type,id);
		}
		public void draw(Graphics2D g2d) {
			if (umlDiagram.getDiagramType() != UMLDiagramType.SEQUENCE) {return;}
		}		
	}
	public enum actorLocation {NONE,ACTOR1RECT,ACTOR2RECT};
	public class UMLUseCaseConnector extends UMLBaseConnector {
		private actorLocation actorLoc;
		
		public UMLUseCaseConnector(UMLUseCaseDrawingItem start,UMLUseCaseDrawingItem end,UMLConnectorType type,int id) {
			super(start,end,type,id);
		}
		public void draw(Graphics2D g2d) {
			if (umlDiagram.getDiagramType() != UMLDiagramType.USECASE) {return;}
			
			Rectangle rcStart = getStart().getBoundingRect();
			Rectangle rcEnd = getEnd().getBoundingRect();

			actorLoc = actorLocation.NONE;
			if (supportFunctions.rectContainsRect(umlDiagram.getUseCaseActor1Rectangle(),rcStart)) {
				actorLoc = actorLocation.ACTOR1RECT;
			}
			if (supportFunctions.rectContainsRect(umlDiagram.getUseCaseActor2Rectangle(),rcStart)) {
				// swap around rcStaret and rcEnd
				Rectangle rcTmp = new Rectangle(0,0,0,0);
				rcTmp = rcStart;
				rcStart = rcEnd;
				rcEnd = rcTmp;
				actorLoc = actorLocation.ACTOR2RECT;
			}
			
			TRACE("draw:rcStart:"+rcStart.toString()+":rcEnd:"+rcEnd.toString(),4);
			Point ptStart = supportFunctions.centerPoint(rcStart);
			Point ptEnd = supportFunctions.centerPoint(rcEnd);
			TRACE("draw:ptStart:"+ptStart.toString()+":ptEnd:"+ptEnd.toString(),4);
			
			double m = 0.0;
			double x = (double)rcStart.width / 2.0;
			TRACE("draw:x:"+String.valueOf(x),4);
			if (ptStart.y != ptEnd.y) {
				m = (double)(ptEnd.y - ptStart.y) / (double)(ptEnd.x - ptStart.x);
			}
			TRACE("draw:m:"+String.valueOf(m),4);
			// origin of axis on ptStart therefore in y=mx+c c is zero. (m can be negative)
			double y = m * x;
			TRACE("draw:y:"+String.valueOf(y),4);
			
			int x1Cord = rcStart.x + rcStart.width;
			int x2Cord = rcEnd.x;
			int y1Cord = ptStart.y + (int)y;
			int y2Cord = ptEnd.y - (int)y;
			
			if (y1Cord < rcStart.y) {
				// line through top of rectangle
				TRACE("draw:point a",4);
				y1Cord = rcStart.y;
				y = (double)rcStart.height / 2.0;
				x = y / m;
				x1Cord = ptStart.x - (int)x;
			}
			if (y1Cord > rcStart.y + rcStart.height) {
				// line through bottom of rectangle
				TRACE("draw:point b",4);
				y1Cord = rcStart.y + rcStart.height;
				y = (double)rcStart.height / 2.0;
				x = y / m;
				x1Cord = ptStart.x + (int)x;
			}
			if (y2Cord < rcEnd.y) {
				// line through top of rectangle
				TRACE("draw:point c",4);
				y2Cord = rcEnd.y;
				y = (double)rcEnd.height / 2.0;
				x = y / m;
				x2Cord = ptEnd.x - (int)x;
			}
			if (y2Cord > rcEnd.y + rcEnd.height) {
				// line through bottom of rectangle
				TRACE("draw:point d",4);
				y2Cord = rcEnd.y + rcEnd.height;
				y = (double)rcEnd.height / 2.0;
				x = y / m;
				x2Cord = ptEnd.x + (int)x;
			}
			
			Point ptLineStart = new Point(x1Cord,y1Cord);
			Point ptLineEnd = new Point(x2Cord,y2Cord);
			TRACE("draw:ptLineStart:"+ptLineStart.toString()+":ptLineEnd:"+ptLineEnd.toString(),4);

			Point ptText = new Point(ptLineStart.x + 14,ptLineStart.y + 8); // actorLocation.NONE
			Point ptSymbol = new Point(ptLineEnd.x,ptLineEnd.y);
			if (actorLoc == actorLocation.ACTOR1RECT) {
				//ptSymbol = ptLineEnd; // same as above code
				//ptText = ptLineStart; // same as above code
			}
			if (actorLoc == actorLocation.ACTOR2RECT) {
				ptSymbol = ptLineStart;
				ptText = ptLineEnd;
			}
			setStart(ptLineStart);
			setEnd(ptLineEnd);
			//g2d.drawLine(ptStart.x,ptStart.y,ptEnd.x,ptEnd.y);
			g2d.drawLine(ptLineStart.x,ptLineStart.y,ptLineEnd.x,ptLineEnd.y);

			TRACE("draw:conn text:"+getConnText(),4);
			if (!getConnText().equals("blank")) {
				AffineTransform flip = AffineTransform.getScaleInstance(1, -1);
				Font orgFont = g2d.getFont();
				double rotAngle = Math.toDegrees(Math.atan2(ptEnd.y - ptStart.y,ptEnd.x - ptStart.x));
				if (m < 0) {rotAngle = 180 - rotAngle;}
				TRACE("draw:rotAngle:"+String.valueOf(rotAngle),4);
				Font newFont = supportFunctions.getRotatedFont(orgFont,(int)rotAngle);
				if (m < 0) {newFont = newFont.deriveFont(flip);}
				g2d.setFont(newFont);
				FontMetrics fm = getFontMetrics(newFont);
				g2d.drawString("<< " + getConnText() + " >>",ptText.x,ptText.y);
				g2d.setFont(orgFont);	
				
				Polygon symbolArrowRight = new Polygon();
				symbolArrowRight.addPoint(ptSymbol.x,ptSymbol.y);
				symbolArrowRight.addPoint(ptSymbol.x - (charWidth/2),ptSymbol.y - (charWidth/2));
				symbolArrowRight.addPoint(ptSymbol.x - (charWidth/2),ptSymbol.y + (charWidth/2));							
				Polygon symbolArrowLeft = new Polygon();
				symbolArrowLeft.addPoint(ptSymbol.x,ptSymbol.y);
				symbolArrowLeft.addPoint(ptSymbol.x + (charWidth/2),ptSymbol.y - (charWidth/2));
				symbolArrowLeft.addPoint(ptSymbol.x + (charWidth/2),ptSymbol.y + (charWidth/2));							
				Rectangle rcSymbolArrowRight = symbolArrowRight.getBounds();
				Rectangle rcSymbolArrowLeft = symbolArrowLeft.getBounds();
				
				switch (actorLoc) {
					case NONE:
						//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y - (charWidth/2));
						//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y + (charWidth/2));
						TRACE("draw:point e",4);
						AffineTransform symbolRot2 = AffineTransform.getRotateInstance(Math.toRadians(rotAngle),
								rcSymbolArrowRight.x + rcSymbolArrowRight.width/2,
								rcSymbolArrowRight.y + rcSymbolArrowRight.height/2);
						Shape rotShape2 = symbolRot2.createTransformedShape(symbolArrowRight);
						g2d.fill(rotShape2);
						break;
						
					case ACTOR1RECT:
						if (m < 0) {
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x + (charWidth/2),ptSymbol.y + (charWidth/2));
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y + (charWidth/2));
							TRACE("draw:point f",4);
						} else {
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y - (charWidth/2));
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y + (charWidth/2));							
							TRACE("draw:point g",4);
						}

						AffineTransform symbolRot = AffineTransform.getRotateInstance(Math.toRadians(rotAngle),
								rcSymbolArrowRight.x + rcSymbolArrowRight.width/2,
								rcSymbolArrowRight.y + rcSymbolArrowRight.height/2);
						Shape rotShape = symbolRot.createTransformedShape(symbolArrowRight);
						g2d.fill(rotShape);
						break;
						
					case ACTOR2RECT:
						if (m < 0) {
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y - (charWidth/2));
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x + (charWidth/2),ptSymbol.y - (charWidth/2));
							TRACE("draw:point h",4);
						} else {
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x - (charWidth/2),ptSymbol.y + (charWidth/2));
							//g2d.drawLine(ptSymbol.x,ptSymbol.y,ptSymbol.x + (charWidth/2),ptSymbol.y + (charWidth/2));							
							TRACE("draw:point i",4);
						}
						AffineTransform symbolRot1 = AffineTransform.getRotateInstance(Math.toRadians(rotAngle),
								rcSymbolArrowLeft.x + rcSymbolArrowLeft.width/2,
								rcSymbolArrowLeft.y + rcSymbolArrowLeft.height/2);
						Shape rotShape1 = symbolRot1.createTransformedShape(symbolArrowLeft);
						g2d.fill(rotShape1);
						break;						
					}
			}
		}	
	}
	public class UMLConnector extends UMLBaseConnector {
		public boolean			bArray = false;
		public String 			variable = "";
		
		public UMLConnector(UMLDrawingItem start,UMLDrawingItem end,UMLConnectorType type,int id,boolean bArray,String variable) {
			super(start,end,type,id);
			
			this.bArray = bArray;
			this.variable = variable;
		}
		public UMLConnector(UMLDrawingItem start,UMLDrawingItem end,UMLConnectorType type,int id) {
			super(start,end,type,id);
		}
		public void drawUMLInterSheetConnector(Graphics2D g2d,Point ptCenter,String text) {
			g2d.setColor(Color.red);
			g2d.fillOval(ptCenter.x - (charWidth/2),ptCenter.y - (charWidth/2),charWidth,charWidth);
			g2d.setColor(Color.black);
			supportFunctions.centerTextAtPoint(g2d,text,ptCenter.x,ptCenter.y + charWidth);
		}
		public void drawUMLDependsConnector(Graphics2D g2d,Point ptStart,Point ptEnd,boolean bDrawConnLine,UMLDrawingItem umlDI) {
			// HOOK B2 and HOOK E

			g2d.setStroke(new BasicStroke(1.f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,8.f,new float[] {6.f,6.f},0.f));

			if (bDrawConnLine) {g2d.drawLine(ptStart.x-charWidth,ptStart.y,ptEnd.x,ptEnd.y);}

			g2d.setStroke(new BasicStroke(1.f));
		}
		public void drawUMLVertConnector(Graphics2D g2d,Point ptStart,Point ptEnd,boolean bDrawConnLine) {
			TRACE("drawUMLVertConnector:ptStart:"+ptStart.toString()+":ptEnd:"+ptEnd.toString(),4);
			if(bDrawConnLine) {g2d.drawLine(ptStart.x,ptStart.y,ptEnd.x,ptEnd.y);}
			
			if (ptEnd != null) {
				g2d.drawLine(ptEnd.x,ptEnd.y,ptEnd.x-(charWidth),ptEnd.y-charWidth);
				g2d.drawLine(ptEnd.x-(charWidth),ptEnd.y-charWidth,ptEnd.x+(charWidth),ptEnd.y-charWidth);
				g2d.drawLine(ptEnd.x+(charWidth),ptEnd.y-charWidth,ptEnd.x,ptEnd.y);
			}
			if (ptStart != null) {
			}
		}
		public void drawUMLHorzConnector(Graphics2D g2d,Point ptStart,Point ptEnd,boolean bDrawConnLine,boolean bArray,String variable,boolean bDrawRightSymbols,UMLDrawingItem umlDI) {
			TRACE("drawUMLHorzConnector:ptStart:"+ptStart.toString()+":ptEnd:"+ptEnd.toString(),4);
			if (bDrawConnLine) {g2d.drawLine(ptStart.x-charWidth,ptStart.y,ptEnd.x,ptEnd.y);}

			if (ptStart != null) {
				if (bDrawRightSymbols) {
					Polygon p = new Polygon();
					p.addPoint(ptStart.x,ptStart.y);
					p.addPoint(ptStart.x+(charWidth/2),ptStart.y-(charWidth/2));
					p.addPoint(ptStart.x+charWidth,ptStart.y);
					p.addPoint(ptStart.x+(charWidth/2),ptStart.y+(charWidth/2));
					p.addPoint(ptStart.x,ptStart.y);
					if (umlDI.getData().isMemberVariable(variable)) {g2d.fill(p);} else {g2d.draw(p);}
				} else {
					Polygon p = new Polygon();
					p.addPoint(ptStart.x,ptStart.y);
					p.addPoint(ptStart.x+(charWidth/2),ptStart.y-(charWidth/2));
					p.addPoint(ptStart.x+charWidth,ptStart.y);
					p.addPoint(ptStart.x+(charWidth/2),ptStart.y+(charWidth/2));
					p.addPoint(ptStart.x,ptStart.y);
					if (umlDI.getData().isMemberVariable(variable)) {g2d.fill(p);} else {g2d.draw(p);}
				}
			}
			
			if (ptEnd != null) {
				if (bDrawRightSymbols) {
					if (bArray) {
						g2d.fillOval(ptEnd.x - (charWidth/2),ptEnd.y - (charWidth/2),charWidth,charWidth);
						// adjust end point so arrow head draw to correct position
						ptEnd = new Point(ptEnd.x - (charWidth/2),ptEnd.y);
					}
					g2d.drawLine(ptEnd.x,ptEnd.y,ptEnd.x - (charWidth/2),ptEnd.y - (charWidth/2));
					g2d.drawLine(ptEnd.x,ptEnd.y,ptEnd.x - (charWidth/2),ptEnd.y + (charWidth/2));
				} else {
					if (bArray) {
						g2d.fillOval(ptEnd.x + (charWidth/2),ptEnd.y - (charWidth/2),charWidth,charWidth);
						// adjust end point so arrow head draw to correct position
						ptEnd = new Point(ptEnd.x + (charWidth/2),ptEnd.y);
					}
					g2d.drawLine(ptEnd.x,ptEnd.y,ptEnd.x + (charWidth/2),ptEnd.y - (charWidth/2));
					g2d.drawLine(ptEnd.x,ptEnd.y,ptEnd.x + (charWidth/2),ptEnd.y + (charWidth/2));
				}
			}
			
			if (ptStart != null && ptEnd != null) {
				Point ptTextStart = new Point(0,0);
				double rotAngle = 0;
				Font orgFont = g2d.getFont();
				Font newFont;
				if (bDrawRightSymbols) {
					ptTextStart.x = ptStart.x + charWidth;
					ptTextStart.y = ptStart.y;
					rotAngle = Math.toDegrees(Math.atan2(ptEnd.y-ptStart.y,ptEnd.x-ptStart.x));
					newFont = supportFunctions.getRotatedFont(orgFont,(int)rotAngle);
				} else {
					ptTextStart.x = ptStart.x - charWidth; 
					ptTextStart.y = ptStart.y; 
					rotAngle = Math.toDegrees(Math.atan2(ptEnd.y-ptStart.y,ptEnd.x-ptStart.x));
					rotAngle = 180 - rotAngle;
					AffineTransform flip = AffineTransform.getScaleInstance(1,-1);
					newFont = supportFunctions.getRotatedFont(orgFont,(int)rotAngle);
					newFont = newFont.deriveFont(flip);
				}
				TRACE("drawUMLHorzConnector:Text rotate angle:"+String.valueOf(rotAngle),4);
				g2d.setFont(newFont);
				g2d.drawString(umlDiagram.getUMLCustomStereotypeText(umlDI.getData().getClassName() + ":link:"+variable+":stereotype") + " " + variable + " " + umlDiagram.getUMLCustomPropsText(umlDI.getData().getClassName() + ":link:"+variable),ptTextStart.x,ptTextStart.y);
				g2d.setFont(orgFont);
			}
		}
		public void draw(Graphics2D g2d) {
			if (umlDiagram.getDiagramType() != UMLDiagramType.CLASS) {return;}
			boolean bDrawRightSymbols = true;
			Point ptStart= new Point(0,0);
			Point ptEnd= new Point(0,0);
			UMLDrawingItem dStart = (UMLDrawingItem)getStart();
			UMLDrawingItem dEnd = (UMLDrawingItem)getEnd();
			Point ptStartOrigin = dStart.getOrigin();
			Point ptEndOrigin = dEnd.getOrigin();
			if (getType() == UMLConnectorType.HEIR) {
				ptStart = dStart.getConnectorPoint(UMLCONN_BOTTOM);
				ptEnd = dEnd.getConnectorPoint(UMLCONN_TOP);
			}
			if (getType() == UMLConnectorType.AREG || getType() == UMLConnectorType.DEPENDS) {
				if (ptEndOrigin.x > ptStartOrigin.x) {
					ptStart = dStart.getConnectorPoint(UMLCONN_RIGHT);
					ptEnd = dEnd.getConnectorPoint(UMLCONN_LEFT);
					bDrawRightSymbols = true;
				} else {
					ptStart = dStart.getConnectorPoint(UMLCONN_LEFT);
					ptEnd = dEnd.getConnectorPoint(UMLCONN_RIGHT);
					bDrawRightSymbols = false;
				}
			}
			boolean bArray = isArray();
			setStart(ptStart);
			setEnd(ptEnd);
			if( dStart.getSheet() == umlDiagram.getDiagramType().getCurrentSheet() && dEnd.getSheet() == umlDiagram.getDiagramType().getCurrentSheet()) {
				TRACE("drawUMLConnectors:Both UML DI on same sheet",4);
				if (getType() == UMLConnectorType.HEIR) {
					drawUMLVertConnector(g2d,ptStart,ptEnd,true);
				}
				if (getType() == UMLConnectorType.AREG) {
					drawUMLHorzConnector(g2d,ptStart,ptEnd,true,bArray,getVariable(),bDrawRightSymbols,(UMLDrawingItem)getStart());
				}
				if (getType() == UMLConnectorType.DEPENDS) {
					drawUMLDependsConnector(g2d,ptStart,ptEnd,true,(UMLDrawingItem)getStart());
				}
			} else {
				TRACE("drawUMLConnectors:Both UML DI on different sheets",4);
				UMLDrawingItem d = null;
				Point ptA = new Point(0,0);
				if( dStart.getSheet() == umlDiagram.getDiagramType().getCurrentSheet()) {d = dStart;ptEnd = null;ptA = ptStart;}
				if( dEnd.getSheet() == umlDiagram.getDiagramType().getCurrentSheet()) {d = dEnd;ptStart = null;ptA = ptEnd;}
				if (d != null) {
					Point ptCenter = new Point(0,0);
					if (getType() == UMLConnectorType.HEIR) {
						drawUMLVertConnector(g2d,ptStart,ptEnd,false);
						ptCenter = new Point(0,0);
					}
					if (getType() == UMLConnectorType.AREG) {
						drawUMLHorzConnector(g2d,ptStart,ptEnd,false,bArray,getVariable(),bDrawRightSymbols,(UMLDrawingItem)getStart());
						ptCenter = new Point(0,0);
					}
					if (getType() == UMLConnectorType.DEPENDS) {
						drawUMLDependsConnector(g2d,ptStart,ptEnd,false,(UMLDrawingItem)getStart());
						ptCenter = new Point(0,0);
					}

					g2d.drawLine(ptA.x,ptA.y,ptCenter.x,ptCenter.y);
					drawUMLInterSheetConnector(g2d,ptCenter,getIDAsString());
				}
			}
		}
		public boolean isArray() {return bArray;}
		public String getArrayAsString() {if (bArray) {return "yes";} else {return "no";}}
		public String getVariable() {return variable;}
		public void setVariable(String s) {variable = s;}
	}
		
	public class UMLUseCaseDrawingItem extends drawingItem {
		private	Rectangle		boundingRect;
		private String			name;
		private String			description;
		private String			type;
		private String			conn;
		private String 			connText;
		private boolean 		bActor;

		public UMLUseCaseDrawingItem() {}
		public UMLUseCaseDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeUMLUseCase,id,orgX,orgY,p1,p2,p3,p4,fill,c);
			
			name = p1;
			description = p2;
			
			if (p4.indexOf(":") != -1) {
				type = p3;
				Vector v = supportFunctions.splitIntoTokens(p4,":");
				conn = (String)v.elementAt(0);
				connText = (String)v.elementAt(1);	
				bActor = true;
			} else {
				conn = p3;
				connText = p4;	
				bActor = false;
			}
			
			iSheet = 1;
			boundingRect = new Rectangle(orgX,orgY,umlUseCaseSizeX,umlUseCaseSizeY);
		}
		public boolean getActor() {return bActor;}
		public void setActor(boolean b) {bActor = b;}
		public boolean isActor() {return getActor();}
		public String getUseCaseName() {return name;}
		public String getUseCaseDescription() {return description;}
		public String getUseCaseType() {return type;}
		public String getUseCaseConn() {return conn;}
		public String getUseCaseConnText() {return connText;}
		public void setUseCaseConn(String s) {conn = s;}
		public void setUseCaseConnText(String s) {connText = s;}
		public void moveTo(Point p) {
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void delete() {
			NodeList nl = null;
			if (isActor()) {
				nl = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/actor[@name='"+getUserDefinedName()+"']");						
			} else {
				nl = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/usecase[@name='"+getUserDefinedName()+"']");												
			}
			if (nl.getLength() != 0) {
				// if we are deleting a usecase, we must remove any 
				// reference to it from any connected actor or usecase
				if (!isActor()) {
					Vector v = umlDiagram.getConnectorNet(this);
					for (int i=0;i<v.size();i++) {
						UMLBaseConnector conn = (UMLBaseConnector)v.elementAt(i);
						UMLUseCaseDrawingItem dStart = (UMLUseCaseDrawingItem)conn.getStart();
						if (dStart.isActor()) {
							NodeList nl1 = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),
									"/umlusecase/actor[@name='"+dStart.getUserDefinedName()+"']");
								if (nl1.getLength() == 1) {
									org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl1.item(0);
									org.w3c.dom.Element eNew = umlDiagram.getUseCaseDocument().createElement("actor");
									eNew.setAttribute("name",eOld.getAttribute("name"));
									eNew.setAttribute("description",eOld.getAttribute("description"));
									eNew.setAttribute("type",eOld.getAttribute("type"));
									eNew.setAttribute("conn",notConnectedString);
									eNew.setAttribute("conntext","blank");
									umlDiagram.getUseCaseRootElement().replaceChild(eNew,eOld);													
								} else {
									supportFunctions.displayMessageDialog(null,"Actor with name " + dStart.getUserDefinedName() + " not found");
								}							
						} else {							
							NodeList nl1 = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),
									"/umlusecase/usecase[@name='"+dStart.getUserDefinedName()+"']");
							if (nl1.getLength() == 1) {
								org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl1.item(0);
								org.w3c.dom.Element eNew = umlDiagram.getUseCaseDocument().createElement("usecase");
								eNew.setAttribute("name",eOld.getAttribute("name"));
								eNew.setAttribute("description",eOld.getAttribute("description"));
								eNew.setAttribute("conn",notConnectedString);
								eNew.setAttribute("conntext","blank");
								umlDiagram.getUseCaseRootElement().replaceChild(eNew,eOld);													
							} else {
								supportFunctions.displayMessageDialog(null,"Use case with name " + dStart.getUserDefinedName() + " not found");								
							}
						}
						
						umlDiagram.deleteConnector(conn.getID());
					}
				}
				
				org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(0);
				umlDiagram.getUseCaseRootElement().removeChild(e);
				
				umlDiagram.umlXMLFileUseCase().saveXMLDataFile();

				getDrawingCanvas().focusFirst(); // GDB 130817
			}	
		}
		public void editor() {
			NodeList nl = null;
			if (isActor()) {
				nl = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/actor[@name='"+getUserDefinedName()+"']");						
			} else {
				nl = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/usecase[@name='"+getUserDefinedName()+"']");												
			}
			if (nl.getLength() != 0) {
				org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl.item(0);
				org.w3c.dom.Element eNew = null;
				if (isActor()) {
					String[] titles = new String[]{"Name","Description","Type","Connection","Conn Text"};			
					// fill defaults with values from node eOld
					int actorTypeIndex = stringIndex(getEnumValues(actorType.NONE),eOld.getAttribute("type"));
					int useCaseIndex = stringIndex(umlDiagram.getUseCaseNames(),eOld.getAttribute("conn"));
					int useCaseTextIndex = stringIndex("blank,communicates,uses,extends",eOld.getAttribute("conntext"));
					String[] defaults = new String[] {eOld.getAttribute("name"),eOld.getAttribute("description"),getEnumValues(actorType.NONE)+",##"+String.valueOf(actorTypeIndex),umlDiagram.getUseCaseNames()+",##"+String.valueOf(useCaseIndex),"blank,communicates,uses,extends"+",##"+String.valueOf(useCaseTextIndex)};
					String[] data = supportFunctions.getDataAsDialog("Add UML Use Case Actor",titles,defaults);
					if (data != null) {
						eNew = umlDiagram.getUseCaseDocument().createElement("actor");
						eNew.setAttribute("name",data[0]);
						eNew.setAttribute("description",data[1]);
						eNew.setAttribute("type",data[2]);
						eNew.setAttribute("conn",data[3]);
						eNew.setAttribute("conntext",data[4]);
						umlDiagram.getUseCaseRootElement().replaceChild(eNew,eOld);													
						umlDiagram.umlXMLFileUseCase().saveXMLDataFile();
						getDrawingCanvas().focusFirst();
						// update drawing item with new values
						name = data[0];
						setUserDefinedName(name);
						description = data[1];
						type = data[2];
						conn = data[3];
						connText = data[4];	

						umlDiagram.processUseCaseConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));				
					}
				} else {
					String[] titles = new String[]{"Name","Description","Connection","Conn Text"};
					// fill defaults with values from node eOld
					int useCaseIndex = stringIndex(umlDiagram.getUseCaseNames(),eOld.getAttribute("conn"));
					int useCaseTextIndex = stringIndex("blank,communicates,uses,extends",eOld.getAttribute("conntext"));
					String[] defaults = new String[] {"","",umlDiagram.getUseCaseNames()+",##"+String.valueOf(useCaseIndex),"blank,communicates,uses,extends"+",##"+String.valueOf(useCaseTextIndex)};
					String[] data = supportFunctions.getDataAsDialog("Add UML Use Case",titles,defaults);
					if (data != null) {
						eNew = umlDiagram.getUseCaseDocument().createElement("usecase");
						eNew.setAttribute("name",data[0]);
						eNew.setAttribute("description",data[1]);
						eNew.setAttribute("conn",data[2]);
						eNew.setAttribute("conntext",data[3]);
						umlDiagram.getUseCaseRootElement().replaceChild(eNew,eOld);													
						umlDiagram.umlXMLFileUseCase().saveXMLDataFile();
						getDrawingCanvas().focusFirst();
						// update drawing item with new values
						name = data[0];
						setUserDefinedName(name);
						description = data[1];
						conn = data[2];
						connText = data[3];	

						umlDiagram.processUseCaseConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
					}
				}
			}
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			
			if (umlDiagram.getDiagramType().getCurrentSheet() != getSheet()) {
				teardownPaint(g2d,focus);
				return;
			}
			
			if (isActor()) {
				actorType actor = actorType.valueOf(getUseCaseType());
				actor.paint(g2d,getFontMetrics(g2d.getFont()),this);			
			} else {
				Color old = g2d.getColor();
				g2d.setColor(Color.gray);
				g2d.fillOval(boundingRect.x, boundingRect.y, 
						boundingRect.width, boundingRect.height);
				g2d.setColor(old);
				supportFunctions.centerTextAtBox(g2d,getUseCaseName(),boundingRect);
			}
			
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
	}
	public int stringIndex(String array,String s) {
		Vector v = supportFunctions.splitIntoTokens(array,",");
		return v.indexOf(s);
	}
	public class UMLSequenceDrawingItem extends drawingItem {
		private	Rectangle		boundingRect;
		private String			name;
		private String			description;
		private String			type;
		private String			connAndType;

		public UMLSequenceDrawingItem() {}
		public UMLSequenceDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeUMLSequence,id,orgX,orgY,p1,p2,p3,p4,fill,c);
	
			name = p1;
			description = p2;
			type = p3;
			connAndType = p4; // name:type of sequence connector

			iSheet = 1;
			boundingRect = new Rectangle(orgX,orgY,umlSequenceSizeX,umlSequenceSizeY);
		}
		public void delete() {
			NodeList nl = null;
			
			nl = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),"/umlsequence/sequence[@name='"+getUserDefinedName()+"']");												
			if (nl.getLength() != 0) {
				Vector v = umlDiagram.getConnectorNet(this);
				for (int i=0;i<v.size();i++) {
					UMLBaseConnector conn = (UMLBaseConnector)v.elementAt(i);
					
					drawingItem dItem = null;
					drawingItem dStart = conn.getStart();
					drawingItem dEnd = conn.getEnd();
					if (dStart == this) {dItem = dEnd;} else {dItem = dStart;}
					
					NodeList nl1 = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),
							"/umlsequence/sequence[@name='"+dItem.getUserDefinedName()+"']");
					if (nl1.getLength() == 1) {
						org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl1.item(0);
						org.w3c.dom.Element eNew = umlDiagram.getSequenceDocument().createElement("sequence");
						eNew.setAttribute("name",eOld.getAttribute("name"));
						eNew.setAttribute("description",eOld.getAttribute("description"));
						eNew.setAttribute("type",eOld.getAttribute("type"));
						eNew.setAttribute("conn",notConnectedString);
						eNew.setAttribute("conntype","SEQUENCENONE");
						umlDiagram.getSequenceRootElement().replaceChild(eNew,eOld);													
					} else {
						supportFunctions.displayMessageDialog(null,"Sequence with name " + dItem.getUserDefinedName() + " not found");
					}
				}
				
				org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(0);
				umlDiagram.getSequenceRootElement().removeChild(e);
				
				umlDiagram.umlXMLFileSequence().saveXMLDataFile();

				getDrawingCanvas().focusFirst(); // GDB 130817
			}
		}
		public void editor() {
			NodeList nl = null;
			
			nl = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),"/umlsequence/sequence[@name='"+getUserDefinedName()+"']");												
			if (nl.getLength() != 0) {
				org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl.item(0);
				
				Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence);

				String sequenceConnTypes = "";
				int ii = 0;
				for (UMLConnectorType p : UMLConnectorType.values()) {
					if (p.type() == dcTypeUMLSequence) {
						if (ii != 0) {sequenceConnTypes = sequenceConnTypes + ",";ii++;}
						//sequenceConnTypes = sequenceConnTypes + p.description();					
						sequenceConnTypes = sequenceConnTypes + p;	// just p, so we can use valueOf with the text to get an enum instance				
					}
				}

				String sequenceNames = notConnectedString;
				for(int i=0;i<v.size();i++) {
					UMLSequenceDrawingItem d = (UMLSequenceDrawingItem)v.elementAt(i);
					sequenceNames = sequenceNames + "," + d.getUserDefinedName();
				}
				String[] titles = new String[]{"Name","Description","Type","Connection","Connection Type"};			
				// fill defaults with values from node eOld
				int sequenceTypeIndex = stringIndex(getEnumValues(sequenceType.NONE),eOld.getAttribute("type"));
				int sequenceIndex = stringIndex(sequenceNames,eOld.getAttribute("conn"));
				int sequenceConnTypeIndex = stringIndex(sequenceConnTypes,eOld.getAttribute("conntype"));
				String[] defaults = new String[] {eOld.getAttribute("name"),eOld.getAttribute("description"),getEnumValues(sequenceType.NONE)+",##"+String.valueOf(sequenceTypeIndex),sequenceNames+",##"+String.valueOf(sequenceIndex),sequenceConnTypes+",##"+String.valueOf(sequenceConnTypeIndex)};
				String[] data = supportFunctions.getDataAsDialog("Add UML Sequence",titles,defaults);
				if (data != null) {
					org.w3c.dom.Element eNew = umlDiagram.getSequenceDocument().createElement("sequence");
					eNew.setAttribute("name",data[0]);
					eNew.setAttribute("description",data[1]);
					eNew.setAttribute("type",data[2]);
					eNew.setAttribute("conn",data[3]);
					eNew.setAttribute("conntype",data[4]);
					umlDiagram.getSequenceRootElement().replaceChild(eNew,eOld);
					umlDiagram.umlXMLFileSequence().saveXMLDataFile();

					// update drawing item with new values
					name = data[0];
					setUserDefinedName(data[0]);
					description = data[1];
					type = data[2];
					connAndType = data[3]+":"+data[4]; // name:type of sequence connector

					// recalculate cords and connectors
					umlDiagram.processSequenceConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence));
				}
			}
		}
		public String getSequenceName() {return name;}
		public String getSequenceDescription() {return description;}
		public String getSequenceType() {return type;}
		public String getSequenceConnType() {return connAndType;}
		public void moveTo(Point p) {
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			
			if (umlDiagram.getDiagramType().getCurrentSheet() != getSheet()) {
				teardownPaint(g2d,focus);
				return;
			}

			sequenceType seq = sequenceType.valueOf(getSequenceType());
			seq.paint(g2d,getFontMetrics(g2d.getFont()),this);			

			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
	}
	public class UMLStateDrawingItem extends drawingItem {
		private	Rectangle		boundingRect;
		private String			name;
		private String			description;
		private String			type;
		private String			connAndType;

		public UMLStateDrawingItem() {}
		public UMLStateDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeUMLState,id,orgX,orgY,p1,p2,p3,p4,fill,c);
	
			name = p1;
			description = p2;
			type = p3;
			connAndType = p4; // name:type of sequence connector

			iSheet = 1;
			boundingRect = new Rectangle(orgX,orgY,umlStateSizeX,umlStateSizeY);
		}
		public void delete() {
			NodeList nl = null;
			
			nl = supportFunctions.executeXPathExpr(umlDiagram.getStateDocument(),"/umlstate/state[@name='"+getUserDefinedName()+"']");												
			if (nl.getLength() != 0) {
				Vector v = umlDiagram.getConnectorNet(this);
				for (int i=0;i<v.size();i++) {
					UMLBaseConnector conn = (UMLBaseConnector)v.elementAt(i);
					
					drawingItem dItem = null;
					drawingItem dStart = conn.getStart();
					drawingItem dEnd = conn.getEnd();
					if (dStart == this) {dItem = dEnd;} else {dItem = dStart;}
					
					NodeList nl1 = supportFunctions.executeXPathExpr(umlDiagram.getStateDocument(),
							"/umlstate/state[@name='"+dItem.getUserDefinedName()+"']");
					if (nl1.getLength() == 1) {
						org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl1.item(0);
						org.w3c.dom.Element eNew = umlDiagram.getStateDocument().createElement("state");
						eNew.setAttribute("name",eOld.getAttribute("name"));
						eNew.setAttribute("description",eOld.getAttribute("description"));
						eNew.setAttribute("type",eOld.getAttribute("type"));
						eNew.setAttribute("conn",notConnectedString);
						eNew.setAttribute("conntype","STATENONE");
						umlDiagram.getStateRootElement().replaceChild(eNew,eOld);													
					} else {
						supportFunctions.displayMessageDialog(null,"State with name " + dItem.getUserDefinedName() + " not found");
					}
				}
				
				org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(0);
				umlDiagram.getStateRootElement().removeChild(e);
				
				umlDiagram.umlXMLFileState().saveXMLDataFile();

				getDrawingCanvas().focusFirst(); // GDB 130817
			}
		}
		public void editor() {
			NodeList nl = null;
			
			nl = supportFunctions.executeXPathExpr(umlDiagram.getStateDocument(),"/umlstate/state[@name='"+getUserDefinedName()+"']");												
			if (nl.getLength() != 0) {
				org.w3c.dom.Element eOld = (org.w3c.dom.Element)nl.item(0);
				
				Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState);

				String stateConnTypes = "";
				int ii = 0;
				for (UMLConnectorType p : UMLConnectorType.values()) {
					if (p.type() == dcTypeUMLState) {
						if (ii != 0) {stateConnTypes = stateConnTypes + ",";ii++;}
						//stateConnTypes = stateConnTypes + p.description();					
						stateConnTypes = stateConnTypes + p;	// just p, so we can use valueOf with the text to get an enum instance				
					}
				}

				String stateNames = notConnectedString;
				for(int i=0;i<v.size();i++) {
					UMLStateDrawingItem d = (UMLStateDrawingItem)v.elementAt(i);
					stateNames = stateNames + "," + d.getUserDefinedName();
				}
				String[] titles = new String[]{"Name","Description","Type","Connection","Connection Type"};			
				// fill defaults with values from node eOld
				int stateTypeIndex = stringIndex(getEnumValues(stateType.NONE),eOld.getAttribute("type"));
				int stateIndex = stringIndex(stateNames,eOld.getAttribute("conn"));
				int stateConnTypeIndex = stringIndex(stateConnTypes,eOld.getAttribute("conntype"));
				String[] defaults = new String[] {eOld.getAttribute("name"),eOld.getAttribute("description"),getEnumValues(stateType.NONE)+",##"+String.valueOf(stateTypeIndex),stateNames+",##"+String.valueOf(stateIndex),stateConnTypes+",##"+String.valueOf(stateConnTypeIndex)};
				String[] data = supportFunctions.getDataAsDialog("Add UML State",titles,defaults);
				if (data != null) { 
					org.w3c.dom.Element eNew = umlDiagram.getStateDocument().createElement("state");
					eNew.setAttribute("name",data[0]);
					eNew.setAttribute("description",data[1]);
					eNew.setAttribute("type",data[2]);
					eNew.setAttribute("conn",data[3]);
					eNew.setAttribute("conntype",data[4]);
					umlDiagram.getStateRootElement().replaceChild(eNew,eOld);
					umlDiagram.umlXMLFileState().saveXMLDataFile();

					// update drawing item with new values
					name = data[0];
					setUserDefinedName(data[0]);
					description = data[1];
					type = data[2];
					connAndType = data[3]+":"+data[4]; // name:type of state connector

					// recalculate cords and connectors
					umlDiagram.processStateConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState));
				}
			}
		}
		public String getStateName() {return name;}
		public String getStateDescription() {return description;}
		public String getStateType() {return type;}
		public String getStateConnType() {return connAndType;}
		public void moveTo(Point p) {
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			
			if (umlDiagram.getDiagramType().getCurrentSheet() != getSheet()) {
				teardownPaint(g2d,focus);
				return;
			}

			stateType state = stateType.valueOf(getStateType());
			state.paint(g2d,getFontMetrics(g2d.getFont()),this);			

			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
	}
	public class UMLDrawingItem extends drawingItem {
		private	UMLClassData	data;
		private	textBlock		tB;
		private	Rectangle		boundingRect;
		private	int				iLevel;
		private	int				iOrignalLevel;
		private int[]			numSideConnectors = new int[4];
		private	int				numRowsClassCompartment = 3;
		private String[] 		svariableList = null;
		private String[] 		sfunctionList = null;

		public void editor() {
			supportFunctions.displayMessageDialog(null,"UMLDIDoubleClicked:NYI");

			if (getData().getClassName().equals(globalDataClass)) {
				supportFunctions.displayMessageDialog(null,"Can not edit pseduo 'GlobalData' class");
				return;
			}
			String expr = "/umldata/class[@name='" + getData().getClassName()+"']";
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getDiagDocument(),expr);
			if (n.getLength() == 0) {
				supportFunctions.displayMessageDialog(null,"UMLDI XML Error");
				return;
			}
			org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
			
		}
		public boolean canDelete() {return false;}
		public ImageIcon getDIIcon() {
			if (getData().isEnum()) {return umlDiagram.getUMLIcon("enum");}
			if (getData().isInterface()) {return umlDiagram.getUMLIcon("interface");}
			
			return umlDiagram.getUMLIcon("class");
		}
		public String getSterotype() {
			if (getData().isEnum()) {return "<<enum>>";}			
			if (getData().isInterface()) {return "<<interface>>";}			

			return umlDiagram.getUMLCustomStereotypeText(data.getClassName()+":"+data.getClassName()+":stereotype");
		}
		public void setTemplateType(String s) {
			data.setTemplateType(s);
			String tmp = data.getClassName() + "<" + s + ">";
			int boundingWidth = getMaxStringWidthInPixels(tmp);
			boundingWidth = boundingWidth + (2 * umlDiagram.getMarginWidth());
			Rectangle rc = getBoundingRect();
			boundingRect = new Rectangle(rc.x,rc.y,boundingWidth,rc.height);
		}
		public boolean isClassName(String s) {
			if (!s.equals(data.getClassName())) {return false;}
			return true;
		}
		public boolean isFunctionName(String s) {
			String[] arr = data.getRawClassFunctionStrings();
			for (int i=0;i<arr.length;i++) {
				if (s.equals(arr[i])) {return true;}
			}
			return false;
		}
		public boolean isVariableName(String s) {
			String[] arr = data.getRawClassVariableStrings();
			for (int i=0;i<arr.length;i++) {
				if (s.equals(arr[i])) {return true;}
			}
			return false;
		}
		public int getNumConnnectors(int iSide) {
			if (iSide < 0 || iSide > 3) {return -1;}
			
			return numSideConnectors[iSide];
		}
		public void resetConnectors() {
			for (int i=0;i<numSideConnectors.length;i++) {numSideConnectors[i] = 0;}
			numSideConnectors[UMLCONN_LEFT]++; // takes into account the optional interface(s) connector
		}
		public Point getConnectorPoint(int iSide) {
			int xCord = 0;
			int yCord = 0;
			if (iSide < 0 || iSide > 3) {return new Point(xCord,yCord);}
			
			String side = "";
			numSideConnectors[iSide]++;
			if (iSide == UMLCONN_TOP) {
				xCord = (charWidth + (charWidth/2)) + (numSideConnectors[UMLCONN_TOP] * charWidth);
				yCord = boundingRect.y;
				side = "TOP";
			}
			if (iSide == UMLCONN_BOTTOM) {
				xCord = (charWidth + (charWidth/2)) + (numSideConnectors[UMLCONN_BOTTOM] * charWidth);
				yCord = boundingRect.y + boundingRect.height;
				side = "BOTTOM";
			}
			if (iSide == UMLCONN_RIGHT) {
				xCord = boundingRect.x + boundingRect.width;
				yCord = (charHeight + (charHeight/2)) + (numSideConnectors[UMLCONN_RIGHT] * charHeight);
				side = "RIGHT";
			}
			if (iSide == UMLCONN_LEFT) {
				xCord = boundingRect.x;
				yCord = (charHeight + (charHeight/2)) + (numSideConnectors[UMLCONN_LEFT] * charHeight);
				side = "LEFT";
			}			
			
			Point ptRet = new Point(xCord,yCord);
			TRACE("getConnectorPoint:Name:" + getUserDefinedName() + ":Side:" + side + ":xCord:" + String.valueOf(xCord) + ":yCord:" + String.valueOf(yCord) + ":ptRet:"+ptRet.toString(),4);
			return ptRet;
		}
		public int getLevel() {return iLevel;}
		public void setOrignalLevel(int i) {iOrignalLevel = i;}
		public int getOrignalLevel() {return iOrignalLevel;}
		public void setLevel(int i) {iLevel = i;}
		public String getUserDefinedName() {return data.getClassName();}
		public UMLClassData getData() {return data;}
		public UMLDrawingItem() {data = null;}
		public UMLDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeUML,id,orgX,orgY,p1,p2,p3,p4,fill,c);
		
			iOrignalLevel = -1;
			iLevel = -1;
			iSheet = 1;
			
			data = new UMLClassData();
			resetConnectors();
			
			processClassHeader(p1,p4);
			processClassVariables(p2);
			processClassFunctions(p3);
			
			// calculate UML drawing item bounding box
			UMLClassVariableData[] variableList = data.getClassVariableStrings();
			TRACE("UMLDrawingItem:var list length:"+String.valueOf(variableList.length),4);
			UMLClassFunctionData[] functionList = data.getClassFunctionStrings();
			TRACE("UMLDrawingItem:func list length:"+String.valueOf(functionList.length),4);
			TRACE("UMLDrawingItem:POINT A",4);
			svariableList = new String[variableList.length];
			sfunctionList = new String[functionList.length];
			for(int i=0;i<variableList.length;i++) {
				String ss = variableList[i].getVariableDefaultValue();
				if (ss.equals("-")) {ss = "";} else {ss = " = " + ss;} 
				svariableList[i] = umlDiagram.getUMLCustomStereotypeText(getData().getClassName()+":"+variableList[i].getVariableName()+":stereotype") + " " +
						variableList[i].getVariableExAccess() + " " + variableList[i].getVariableAccess() + " " + variableList[i].getVariableName() + 
					umlDiagram.getUMLCustomMultiplicityText(getData().getClassName()+":"+variableList[i].getVariableName()+":multiplicity") + 
					" : " + variableList[i].getVariableType() + ss + " " + umlDiagram.getUMLCustomPropsText(getData().getClassName()+":"+variableList[i].getVariableName());
			}
			for(int i=0;i<functionList.length;i++) {
				sfunctionList[i] = functionList[i].getFunctionExAccess() + " " + functionList[i].getFunctionAccess() + " " + functionList[i].getFunctionName() + "(" + functionList[i].getFunctionParameters() + ") : " + functionList[i].getFunctionReturnType();
			}
			
			String tmp1 = data.getClassName();
			if (data.getTemplateType().length() != 0) {
				tmp1 = tmp1 + "<" + data.getTemplateType() + ">";
			}
			int boundingWidth = getMaxStringWidthInPixels(tmp1);
			if (getMaxStringWidthInPixels(getSterotype()) > boundingWidth)
				{boundingWidth = getMaxStringWidthInPixels(getSterotype());}
			
			TRACE("UMLDrawingItem:POINT B",4);
			if (getMaxStringWidthInPixels(svariableList) > boundingWidth)
				{boundingWidth = getMaxStringWidthInPixels(svariableList);}
			TRACE("UMLDrawingItem:POINT C",4);
			if (getMaxStringWidthInPixels(sfunctionList) > boundingWidth)
				{boundingWidth = getMaxStringWidthInPixels(sfunctionList);}
			TRACE("UMLDrawingItem:POINT D",4);
			boundingWidth = boundingWidth + (2 * umlDiagram.getMarginWidth());
			
			int tmp = numRowsClassCompartment;
			tmp = tmp + variableList.length + 2;
			tmp = tmp + functionList.length + 2;
			
			int boundingHeight = (tmp * charHeight);
			boundingRect = new Rectangle(orgX,orgY,boundingWidth,boundingHeight);
			TRACE("UMLDrawingItem:"+data.getClassName()+":BR:" + boundingRect.toString(),4);
		}
		public void moveTo(Point p) {
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
		}
		public void processClassHeader(String p1,String p4) {
			String[] info = p1.split("::");
			data.setClassName(info[0]);
			setUserDefinedName(info[0]);
			data.setClassExtends(info[1]);
			data.setClassImplements(info[2]);
			data.setClassExAccess(info[3]);
			data.setClassNotes(p4);
		}
		public void processClassVariables(String p2) {
			String[] info = p2.split("::");
			for(int i=0;i<info.length;i++) {
				String[] info1 = info[i].split(":");
				if (info1.length != 6) {return;}
				UMLClassVariableData tmp = new UMLClassVariableData();
				tmp.setVariableAccess(info1[0]);
				tmp.setVariableType(info1[1]);
				tmp.setVariableName(info1[2]);
				tmp.setVariableDefaultValue(info1[3]);
				tmp.setVariableExAccess(info1[4]);
				tmp.setVariableTemplateType(info1[5]);
				ImageIcon[] icons = umlDiagram.getUMLIcons(info1[0]+"field", info1[4]);
				tmp.setIcons(icons);
				data.addClassVariable(tmp);
			}
		}
		public void processClassFunctions(String p3) {
			String[] info = p3.split("::");
			for(int i=0;i<info.length;i++) {
				//TRACE("processClassFunctions:"+info[i],4);
				String[] info1 = info[i].split(":");
				if (info1.length != 5) {return;}
				UMLClassFunctionData tmp = new UMLClassFunctionData();
				tmp.setFunctionAccess(info1[0]);
				tmp.setFunctionReturnType(info1[1]);
				tmp.setFunctionName(info1[2]);
				tmp.setFunctionParameters(info1[3]);
				tmp.setFunctionExAccess(info1[4]);
				ImageIcon[] icons = umlDiagram.getUMLIcons(info1[0]+"method", info1[4]);
				tmp.setIcons(icons);
				data.addClassFunction(tmp);
			}
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			Color topColor = new Color(181,162,195);
			Color bottomColor = new Color(239,237,235);
			
			setupPaint(g2d,focus);
			
			if (umlDiagram.getDiagramType().getCurrentSheet() != getSheet()) {
				teardownPaint(g2d,focus);
				return;
			}
			
			UMLClassVariableData[] variableList = data.getClassVariableStrings();
			UMLClassFunctionData[] functionList = data.getClassFunctionStrings();

			Color orgColor = g2d.getColor();
			g2d.setColor(bottomColor);
			g2d.fillRect((int)boundingRect.getX(),(int)boundingRect.getY(),(int)boundingRect.getWidth(),(int)boundingRect.getHeight());				  
			g2d.setColor(orgColor);

			int x1 = boundingRect.x;
			int x2 = boundingRect.x + boundingRect.width;
			int y1 = (charHeight * numRowsClassCompartment) + boundingRect.y; 
			int tmp = variableList.length+2;
			int y2 = y1 + (tmp * charHeight);
			g2d.drawLine(x1,y1,x2,y1);
			g2d.drawLine(x1,y2,x2,y2);

			orgColor = g2d.getColor();
			g2d.setColor(topColor);
			g2d.fillRect((int)boundingRect.getX(),(int)boundingRect.getY(),(int)boundingRect.getWidth(),charHeight*numRowsClassCompartment);				  
			g2d.setColor(orgColor);
			String tmp1 = data.getClassName();
			if (data.getTemplateType().length() != 0) {
				tmp1 = tmp1 + "<" + data.getTemplateType() + ">";
			}

			ImageIcon icon = getDIIcon();
			
			icon.paintIcon(null, g2d, boundingRect.x, boundingRect.y);
			supportFunctions.centerTextAtBox(g2d,tmp1,new Rectangle(boundingRect.x,boundingRect.y,boundingRect.width,charHeight+(charHeight/2)));
			supportFunctions.centerTextAtBox(g2d,getSterotype(),new Rectangle(boundingRect.x,boundingRect.y+charHeight,boundingRect.width,charHeight+(charHeight/2)));
			supportFunctions.centerTextAtBox(g2d,data.getPackageName(),new Rectangle(boundingRect.x,boundingRect.y+charHeight+charHeight,boundingRect.width,charHeight+(charHeight/2)));
			int yCord = y1 + charHeight + charHeight;
			for(int i=0;i<variableList.length;i++) {
				int offset = umlDiagram.drawUMLIcons(variableList[i].getIcons(), g2d, boundingRect.x + umlDiagram.getMarginWidth(), yCord - (charHeight / 2));
				g2d.drawString(svariableList[i],boundingRect.x + umlDiagram.getMarginWidth() + offset,yCord);
				yCord = yCord + charHeight;
			}
			yCord = y2 + charHeight + charHeight;
			for(int i=0;i<functionList.length;i++) {
				Font orgFont = g2d.getFont();
				if (data.isFunctionAConstructor(i)) {
					g2d.setFont(supportFunctions.getUnderlinedFont(orgFont));
				}
				int offset = umlDiagram.drawUMLIcons(functionList[i].getIcons(), g2d, boundingRect.x + umlDiagram.getMarginWidth(), yCord - (charHeight / 2));
				g2d.drawString(sfunctionList[i],boundingRect.x + umlDiagram.getMarginWidth() + offset,yCord);
				g2d.setFont(orgFont);
				yCord = yCord + charHeight;
			}

			if (data.hasClassNotes()) {
				umlDiagram.getClassNoteIcon().paintIcon(null,g2d,boundingRect.x+boundingRect.width-iconWidth-1,boundingRect.y+1);
			}
			if (data.hasInterfaces()) {
				TRACE("class interfaces:"+data.getClassImplements(),4);
				g2d.drawLine(boundingRect.x,boundingRect.y+(charHeight/2),boundingRect.x-(charWidth),boundingRect.y+(charHeight/2)); 
				g2d.drawLine(boundingRect.x-(charWidth),boundingRect.y+(charHeight/2),boundingRect.x-(charWidth),boundingRect.y-(charHeight)); 
				g2d.drawOval(boundingRect.x-(charWidth)-(charWidth/2),boundingRect.y-(charHeight)-(charWidth),charWidth,charWidth); 
				orgColor = g2d.getColor();
				g2d.setColor(topColor);
				g2d.fillRect(boundingRect.x+(charWidth/2),boundingRect.y-(charHeight)-(charHeight/2),(data.getClassImplements().length()-4) * charWidth,charHeight);				  
				g2d.setColor(orgColor);
				g2d.drawString(data.getClassImplements(),boundingRect.x+(charWidth/2)+1,boundingRect.y-(charHeight)+1); 
			}
			
			// move to last so drawn on top
			g2d.drawRect((int)boundingRect.getX(),(int)boundingRect.getY(),(int)boundingRect.getWidth(),(int)boundingRect.getHeight());				  

			Rectangle rc = getBoundingRect();
			String id = data.getClassName() + ":" + data.getClassName();
			Font orgFont = g2d.getFont();
			int yCord1 = rc.y + charHeight;
			g2d.setFont(orgFont.deriveFont(orgFont.getSize() - 2));
			String bottomText = umlDiagram.getUMLCustomOCLText(id+":ocl");
			if (bottomText.length() !=0 ) {
				g2d.drawString(bottomText,rc.x,yCord1);
				yCord1 = yCord1 + charHeight;
			}
			bottomText = umlDiagram.getUMLCustomPropsText(id);
			if (bottomText.length() !=0 ) {
				g2d.drawString(bottomText,rc.x,yCord1);
			}
			g2d.setFont(orgFont);
			
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
	}
	public enum UMLDiagramType {CLASS("Class Diagram"),SEQUENCE("Sequence Diagram"),USECASE("Use Case Diagram"),STATE("State Diagram");
		private final String description;
		private int iCurrentSheet;
		private int iMaxSheet;
		
		UMLDiagramType(String description) {
			this.description = description;
			iCurrentSheet = 1;
			iMaxSheet = 1;
		}
		public void reset() {
			iCurrentSheet = 1;
			iMaxSheet = 1;
		}
		public String getDescription() {return description;}
		public int getCurrentSheet() {return iCurrentSheet;}
		public void setCurrentSheet(int i) {iCurrentSheet = i;}
		public int getMaxSheet() {return iMaxSheet;}
		public void setMaxSheet(int i) {iMaxSheet = i;}
	}
	public class UMLDrawingCanvas extends drawingCanvas {
		   public UMLDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   super(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public void onInitDrawingCanvas() {
			   add(umlDiagram.getMenuUMLDI());
		   }
		   public boolean doPaint(drawingItem d) {
			   if (d.getType() <= ehsConstants.dcTypeBuiltInMaxId) {return true;} //built in types always drawn
			   if (d.getType() == dcTypeUMLUseCase && umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {return true;}
			   if (d.getType() == dcTypeUML && umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {return true;}
			   if (d.getType() == dcTypeUMLSequence && umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {return true;}
			   if (d.getType() == dcTypeUMLState && umlDiagram.getDiagramType() == UMLDiagramType.STATE) {return true;}

			   return false;
		   } 
		   public boolean getDrawingItemAtPointFilter(drawingItem d) {
				if (umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {	
					if (d instanceof UMLDrawingItem) {return true;}
				}
				if (umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {	
					if (d instanceof UMLUseCaseDrawingItem) {return true;}
				}
				if (umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {	
					if (d instanceof UMLSequenceDrawingItem) {return true;}
				}
				if (umlDiagram.getDiagramType() == UMLDiagramType.STATE) {	
					if (d instanceof UMLStateDrawingItem) {return true;}
				}
				
			    return false;
		   }
		   public boolean doPaste(drawingItem d) {
			   if (d.getType() <= ehsConstants.dcTypeBuiltInMaxId) {return true;} //built in types always drawn
			   if (d.getType() == dcTypeUMLUseCase && umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {return true;}
			   if (d.getType() == dcTypeUML && umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {return true;}
			   if (d.getType() == dcTypeUMLSequence && umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {return true;}
			   if (d.getType() == dcTypeUMLState && umlDiagram.getDiagramType() == UMLDiagramType.STATE) {return true;}

			   return false;
		   } 
			public boolean printPageSetupOverride(Graphics2D g2d,PageFormat format,Dimension size) {
				String tmp = supportFunctions.getPropValue("Scaling Factor","Scaling Factor","1");
				float fScalingFactor = new java.lang.Double(tmp.trim()).floatValue();
				g2d.scale(fScalingFactor,fScalingFactor);
				return true;
			}
		   public boolean printOverride(Graphics2D g2d) {
			   g2d.drawImage(getCurrentCanvasImage(),0,0,canvasMaxX,canvasMaxY,null);
			   return true;
		   }
		   public void beforePainting(Graphics2D g2d) {
			   Vector drawingitems = getDrawingItemsOfType(dcTypeUML);
			   for (int i=0;i<drawingitems.size();i++) {
					UMLDrawingItem d = (UMLDrawingItem)drawingitems.elementAt(i);
					d.resetConnectors();
				}
				
				if(umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {
					int margin = 30;
					
					if (umlDiagram.getUseCaseDiagramTitle().length() != 0) {
						Font f = g2d.getFont();
						float size = g2d.getFont().getSize();
						g2d.setFont(f.deriveFont(size * 2));
						supportFunctions.centerTextAtBox(g2d,umlDiagram.getUseCaseDiagramTitle(),new Rectangle(
							umlDiagram.getUseCaseRectangle().x - margin,
							umlDiagram.getUseCaseRectangle().y - margin - (2 * charHeight),
							umlDiagram.getUseCaseRectangle().width + (2 * margin),
							2 * charHeight
						));	
						g2d.setFont(f);
					}
					g2d.setColor(Color.lightGray);
					//g2d.fillRect(umlDiagram.getUseCaseActor1Rectangle().x,
					//		umlDiagram.getUseCaseActor1Rectangle().y,
					//		umlDiagram.getUseCaseActor1Rectangle().width,
					//		umlDiagram.getUseCaseActor1Rectangle().height
					//		);
					//g2d.fillRect(umlDiagram.getUseCaseActor2Rectangle().x,
					//		umlDiagram.getUseCaseActor2Rectangle().y,
					//		umlDiagram.getUseCaseActor2Rectangle().width,
					//		umlDiagram.getUseCaseActor2Rectangle().height
					//		);
					g2d.fillRect(umlDiagram.getUseCaseRectangle().x - margin,
							umlDiagram.getUseCaseRectangle().y - margin,
							umlDiagram.getUseCaseRectangle().width + (2 * margin),
							umlDiagram.getUseCaseRectangle().height + (2 * margin)
							);
					g2d.setColor(Color.black);
					g2d.drawRect(umlDiagram.getUseCaseRectangle().x - margin,
							umlDiagram.getUseCaseRectangle().y - margin,
							umlDiagram.getUseCaseRectangle().width + (2 * margin),
							umlDiagram.getUseCaseRectangle().height + (2 * margin)
							);
					}

				umlDiagram.drawUMLConnectors(g2d);
		   }
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {;}
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
				if (type == dcTypeUML) {
					UMLDrawingItem d = new UMLDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					d.setCanTransform(false);
					return d;
				}
				if (type == dcTypeUMLSequence) {
					UMLSequenceDrawingItem d = new UMLSequenceDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					d.setCanTransform(false);
					d.setUserDefinedName(p1);
					return d;
				}
				if (type == dcTypeUMLUseCase) {
					UMLUseCaseDrawingItem d = new UMLUseCaseDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					d.setCanTransform(false);
					d.setUserDefinedName(p1);
					return d;
				}
				
				return (drawingItem)null;
		   }
		   public PopupMenu customCreateMenu(PopupMenu menu) {
			  menu.removeAll();
			  
			  MenuItem item = new MenuItem("Cut");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Copy");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Paste");
			  item.addActionListener(this);
			  menu.add(item);
			  menu.addSeparator();
			  item = new MenuItem("Undo");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Redo");
			  item.addActionListener(this);
			  menu.add(item);
			  menu.addSeparator();
			  item = new MenuItem("Add UML Class");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Add UML Connector");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Add Actor");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("Add Use Case");
			  item.addActionListener(this);
			  menu.add(item);
			  menu.addSeparator();
			  item = new MenuItem("Diagram Properties...");
			  item.addActionListener(this);
			  menu.add(item);
			  menu.addSeparator();
			  item = new MenuItem("Generate Code");
			  item.addActionListener(this);
			  menu.add(item);
			  item = new MenuItem("OCL Builder");
			  item.addActionListener(this);
			  menu.add(item);
			  
			  return menu;
		  }
		  public void customUpdateMenu(PopupMenu menu) {
			supportFunctions.getMenuItem(menu,"Paste").setEnabled(clipboardDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Cut").setEnabled(selectedDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Copy").setEnabled(selectedDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Undo").setEnabled(undoIndex > -1);
			supportFunctions.getMenuItem(menu,"Redo").setEnabled(undoIndex > undoDrawingItems.size() - 1);

			supportFunctions.getMenuItem(menu,"Diagram Properties...").setEnabled(umlDiagram.isDiagramLoaded());

			if (umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {			
				supportFunctions.getMenuItem(menu,"Add UML Class").setEnabled(true);
				supportFunctions.getMenuItem(menu,"Add UML Connector").setEnabled(true);				
				supportFunctions.getMenuItem(menu,"Add Actor").setEnabled(false);
				supportFunctions.getMenuItem(menu,"Add Use Case").setEnabled(false);				
			} 
			if (umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {
				supportFunctions.getMenuItem(menu,"Add UML Class").setEnabled(false);
				supportFunctions.getMenuItem(menu,"Add UML Connector").setEnabled(false);				
				supportFunctions.getMenuItem(menu,"Add Actor").setEnabled(true);
				supportFunctions.getMenuItem(menu,"Add Use Case").setEnabled(true);				
			} 
			if (umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {
				supportFunctions.getMenuItem(menu,"Add UML Class").setEnabled(false);
				supportFunctions.getMenuItem(menu,"Add UML Connector").setEnabled(false);				
				supportFunctions.getMenuItem(menu,"Add Actor").setEnabled(false);
				supportFunctions.getMenuItem(menu,"Add Use Case").setEnabled(false);
			}
		  }
	}
	public class scrollableDrawingCanvas {
		  private UMLDrawingCanvas  	dC;
		  private ScrollPane		  	sPane;
		  private Adjustable			bottomSB,rightSB;
		  
		  public scrollableDrawingCanvas() {
			  this("",ehsConstants.dcMaxX,ehsConstants.dcMaxY,ehsConstants.dcGridSpaceX,ehsConstants.dcGridSpaceY,true);
		  }
		  public scrollableDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean visible) {
			  dC = new UMLDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,visible);
			  sPane = new ScrollPane();
			  sPane.add(dC);
			  bottomSB = sPane.getHAdjustable();
			  rightSB = sPane.getVAdjustable();
		  }
		  public Point getGCTopLeftCords() {return sPane.getScrollPosition();}
		  public void setGCTopLeftCords(Point pt) {sPane.setScrollPosition(pt);}
		  public Adjustable getBottomSB() {return bottomSB;}
		  public Adjustable getRightSB() {return rightSB;}
		  public void paint(Graphics g) {dC.paint(g);}
		  public drawingCanvas getDC() {return dC;}
		  public ScrollPane getComponent() {return sPane;}
		  public void update(Graphics g) {paint(g);}
		  public void paint() {paint(getGraphics());}
		  public void setSize(Dimension d) {ehsConstants.dcMaxX=(int)d.getWidth();ehsConstants.dcMaxY=(int)d.getHeight();dC.setSize(d);}
		  public Dimension getSize() {return dC.getSize();}
	}
	
/*
		public String executeXQuery(String xqueryfilename) {
			String retData = "";
			
			try {
			      InputStream inputStream = new FileInputStream(new File(xqueryfilename));
			      XQDataSource ds = new SaxonXQDataSource();
			      XQConnection conn = ds.getConnection();
			      XQPreparedExpression exp = conn.prepareExpression(inputStream);
			      XQResultSequence result = exp.executeQuery();
			      
			      while (result.next()) {
			         //System.out.println(result.getItemAsString(null));
			    	  retData = retData + result.getItemAsString(null) + "/n";
			      }
			   } catch (Exception e) {
				   return "";
			   }
			
			return retData;
		}
*/	
		
	public class deleteClassNotesAction extends AbstractAction {
			public deleteClassNotesAction() {super("deleteClassNotesAction");putValue(SHORT_DESCRIPTION,"deleteClassNotesAction description");}
			public void actionPerformed(ActionEvent evt) {
				UMLDrawingItem d = (UMLDrawingItem)getGlobalDrawingItem();
				if (d != null) {
					TRACE("deleteClassNotesAction entered",4);
					d.getData().setClassNotes("-");
					NodeList nl = supportFunctions.executeXPathExpr(umlDiagram.getNotesDocument(),"/umlnotes/class[@name='"+d.getData().getClassName()+"']");
					if (nl.getLength() != 0) {
						TRACE("Class note for " + d.getData().getClassName() + " deleted from nodelist",4);
						org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(0);
						umlDiagram.getNotesRootElement().removeChild(e);
						umlDiagram.umlXMLFileNotes().saveXMLDataFile();
					}
				}
			}
		}
	public class deleteAction extends AbstractAction {
		public deleteAction() {super("deleteAction");putValue(SHORT_DESCRIPTION,"deleteAction description");}
		public void actionPerformed(ActionEvent evt) {
			drawingItem d = getGlobalDrawingItem();
			if (d != null) {
				getDrawingCanvas().deleteDrawingItem(d);
			}
		}
	}
				
	public class addClassNotesAction extends AbstractAction {
		public addClassNotesAction() {super("addClassNotesAction");putValue(SHORT_DESCRIPTION,"addClassNotesAction description");}
		public void actionPerformed(ActionEvent evt) {
			UMLDrawingItem d = (UMLDrawingItem)getGlobalDrawingItem();
			if (d != null) {
				TRACE("addClassNotesAction entered",4);
				String classNotes = JOptionPane.showInputDialog(null,"Enter Class Notes",systemUserReg.getAppName(),JOptionPane.QUESTION_MESSAGE);
				if (classNotes != null) {d.getData().setClassNotes(classNotes);}
				org.w3c.dom.Element e = umlDiagram.getNotesDocument().createElement("class");
				e.setAttribute("name",d.getData().getClassName());
				e.setAttribute("note",classNotes);

				NodeList nl = supportFunctions.executeXPathExpr(umlDiagram.getNotesDocument(),"/umlnotes/class[@name='"+d.getData().getClassName()+"']");
				if (nl.getLength() != 0) {
					umlDiagram.getNotesRootElement().replaceChild(e,(org.w3c.dom.Element)nl.item(0));
					TRACE("Class note replaced for " + d.getData().getClassName(),4);
				} else {
					umlDiagram.getNotesRootElement().appendChild(e);
					TRACE("Class note added for " + d.getData().getClassName(),4);
				}
				
				umlDiagram.umlXMLFileNotes().saveXMLDataFile();
			}
		}
	}

	public class UMLClassLocationInfo {
		private	int 			sheet;
		private int				level;
		private int				compartment;
		private UMLDrawingItem	UMLDI;
		private Rectangle		rcComp;
		
		UMLClassLocationInfo(int sheet,int level,int compartment,UMLDrawingItem d,Rectangle rc) {
			this.sheet = sheet;
			this.level = level;
			this.compartment = compartment;
			this.UMLDI = d;
			this.rcComp = rc;
		}
		public String toString() {
			String name = "";
			if (UMLDI != null) {name = UMLDI.getUserDefinedName();}
			return "Sheet:" + String.valueOf(sheet) + ",Level:" + String.valueOf(level) + ",Compartment:" + String.valueOf(compartment) + ",UML DI:" + name + ",Rect:" + rcComp.toString();
		}
		public void setSheet(int i) {sheet = i;}
		public int getSheet() {return sheet;}
		public void setLevel(int i) {level = i;}
		public int getLevel() {return level;}
		public void setCompartment(int i) {compartment = i;}
		public int getCompartment() {return compartment;}
		public void setRectangle(Rectangle r) {rcComp = r;}
		public Rectangle getRectangle() {return rcComp;}
		public UMLDrawingItem getUMLDI() {return UMLDI;}
		public void setUMLDI(UMLDrawingItem d) {UMLDI = d;}
	}
	public String getEnumValues(Enum e) {
		String ret = "";
		Class c = e.getDeclaringClass();
		Object[] values = c.getEnumConstants();
		if (values != null) {
			for (int i=0;i<values.length;i++) {
				TRACE("getEnumValues:"+String.valueOf(i)+":" + values[i],4);
				if (i != 0) {ret = ret + ",";}
				ret = ret + values[i];
			}
		}
		return ret;
	}

	public enum stateType {NONE("None")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLStateDrawingItem d) {;}
		},
		STATE("State")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLStateDrawingItem d) {;}			
		};
		
		private final String description;
		stateType(String description) {
			this.description = description;
		}
		public String getDescription() {return description;}
		public abstract void paint(Graphics2D g2d,FontMetrics fm,UMLStateDrawingItem d);
	}
	public enum sequenceType {NONE("None")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d) {;}
		},
		PROCESS("Process")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d) {;}			
		},
		DESTROY("Destroy")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d) {;}			
		},
		HEADER("Header")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d) {;}			
		},
		LIFELINE("Life Line")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d) {;}			
		};
		
		private final String description;
		sequenceType(String description) {
			this.description = description;
		}
		public String getDescription() {return description;}
		public abstract void paint(Graphics2D g2d,FontMetrics fm,UMLSequenceDrawingItem d);
	}
	public enum actorType {NONE("None")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {;}
		},
		USER("User")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				centerText(g2d,fm,"<<actor>>",new Rectangle(
						d.boundingRect.x, d.boundingRect.y, 
						d.boundingRect.width, d.boundingRect.height / 2
				));	
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
						d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
						d.boundingRect.width, d.boundingRect.height / 2
					));	
			}
		},
		FILE("File")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
					centerText(g2d,fm,"<<actor>>",new Rectangle(
						d.boundingRect.x, d.boundingRect.y, 
						d.boundingRect.width, d.boundingRect.height / 2
				));	
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
						d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
						d.boundingRect.width, d.boundingRect.height / 2
				));	
			}
		},
		XML("XML")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				centerText(g2d,fm,"<<actor>>",new Rectangle(
					d.boundingRect.x, d.boundingRect.y, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
					d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
			}
		},
		URL("Uniform Resource Locator")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				centerText(g2d,fm,"<<actor>>",new Rectangle(
					d.boundingRect.x, d.boundingRect.y, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
					d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
			}
		},
		DB("Database")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				centerText(g2d,fm,"<<actor>>",new Rectangle(
					d.boundingRect.x, d.boundingRect.y, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	 
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
					d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
			}
		},
		COMPUTEENG("Compute Engine")
		{
			public void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d) {
				g2d.setColor(Color.white);
				g2d.fillRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				g2d.setColor(Color.black);
				g2d.drawRect(d.boundingRect.x, d.boundingRect.y,d.boundingRect.width, d.boundingRect.height);
				centerText(g2d,fm,"<<actor>>",new Rectangle(
					d.boundingRect.x, d.boundingRect.y, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
				centerText(g2d,fm,d.getUserDefinedName(),new Rectangle(
					d.boundingRect.x, d.boundingRect.y + d.boundingRect.height / 2, 
					d.boundingRect.width, d.boundingRect.height / 2
				));	
			}
		};
		
		private final String description;
		actorType(String description) {
			this.description = description;
		}
		public String getDescription() {return description;}
		public void centerText(Graphics2D g2d,FontMetrics fm,String text,Rectangle rect) {
			int width = fm.stringWidth(text);
			g2d.drawString(text,rect.x + ((rect.width - width) / 2),rect.y + (rect.height / 2));			
		}
		public abstract void paint(Graphics2D g2d,FontMetrics fm,UMLUseCaseDrawingItem d);
	}
	public class umlDiagramStruct implements propBoxDialogListener {
		private boolean					bModified;
		private String					filename;
		private xmlDataFile				umlDiag = null;
		private	xmlDataFile				umlDiagTypes = null;
		private	xmlDataFile				umlDiagNotes = null;
		private	xmlDataFile				umlDiagCustom = null;
		private	xmlDataFile				umlDiagConnectors = null;
		private	xmlDataFile				umlDiagUseCase = null;
		private	xmlDataFile				umlDiagSequence = null;
		private	xmlDataFile				umlDiagState = null;
		private org.w3c.dom.Element 	root,rootUseCase,rootSequence,rootState,rootTypes,rootNotes,rootCustom,rootConnectors;
		private org.w3c.dom.Document 	doc,docUseCase,docSequence,docState,docTypes,docNotes,docCustom,docConnectors;
		private int 					iMaxLevel = -1;
		private int[] 					maxLevelWidth = null;
		private int[] 					maxLevelHeight = null;
		private int[] 					numberDIOnLevel = null;
		private	int						iMarginWidth;
		private	int						iMarginHeight;
		private	PopupMenu				menuUMLDI = null;
		private	customIcon				classNoteIcon = null;
		private Vector 					UMLConnectors = new Vector();
		private	int						UMLConnID = 0;
		private	boolean					bNewCompile;
		private	configurationSettings 	UMLCodeMetrics;
		private String 					fileExt = "";
		private	Vector					extraFiles = new Vector();
		private	String[]				accessKeywords = {"private","protected","public","internal"};
		private String[]				exaccessKeywords = {"abstract","final","static","override","sealed","readonly","fixed","unsafe","virtual"};
		private String[]				funcModifiers = {"ref","out","params"};
		private Hashtable				umlIcons = new Hashtable();
		private	UMLDiagramType			umlDiagType = UMLDiagramType.CLASS;
		private Rectangle				rcUMLDiagram = null;
		private	Vector					UMLClassLocInfoBlocks = new Vector();
		private	Rectangle				rcUseCaseRectangle = null;
		private	Rectangle				rcUseCaseActor1Rectangle = null;
		private	Rectangle				rcUseCaseActor2Rectangle = null;
		private	callingTree				UMLCallingTree = null;
		private String					usecaseDiagramTitle = "";
		private String					sequenceDiagramTitle = "";
		private	String					stateDiagramTitle = "";
		
		umlDiagramStruct() {
			reset();
		}
		public void deleteConnector(int id) {
			for (int i=0;i<UMLConnectors.size();i++) {
				UMLBaseConnector conn = (UMLBaseConnector)UMLConnectors.elementAt(i);
				if (conn.getID() == id) {
					UMLConnectors.remove(i);
					return;
				}
			}
		}
		public String getUseCaseDiagramTitle() {return usecaseDiagramTitle;}
		public String getSequenceDiagramTitle() {return sequenceDiagramTitle;}
		public String getStateDiagramTitle() {return stateDiagramTitle;}
		public void setUseCaseDiagramTitle(String s) {usecaseDiagramTitle = s;}
		public void setSequenceDiagramTitle(String s) {sequenceDiagramTitle = s;}
		public void setStateDiagramTitle(String s) {stateDiagramTitle = s;}
		public Vector getConnectorNet(drawingItem d) {
			Vector v = new Vector();
			for (int i=0;i<UMLConnectors.size();i++) {
				UMLBaseConnector conn = (UMLBaseConnector)UMLConnectors.elementAt(i);
				if (conn.isStartConnectedTo(d) || conn.isEndConnectedTo(d)) {
					v.addElement(conn);
				}
			}			
			
			return v;
		}
		public void deleteConnectorsOfType(UMLConnectorType type) {
			for (int i=0;i<UMLConnectors.size();i++) {
				UMLBaseConnector conn = (UMLBaseConnector)UMLConnectors.elementAt(i);
					if (conn.getType() == type) {UMLConnectors.remove(conn);}
			}
		}
		public callingTree getUMLCallingTree() {return UMLCallingTree;}
		public void setUMLCallingTree(callingTree tree) {UMLCallingTree = tree;}
		public Rectangle getUseCaseRectangle() {return rcUseCaseRectangle;}
		public Rectangle getUseCaseActor1Rectangle() {return rcUseCaseActor1Rectangle;}
		public Rectangle getUseCaseActor2Rectangle() {return rcUseCaseActor2Rectangle;}
		public String[] getFuncParamModKeywords() {return funcModifiers;}
		public Rectangle getUMLDiagramRect() {return rcUMLDiagram;}
		public UMLDiagramType getDiagramType() {return umlDiagType;}
		public void setDiagramType(UMLDiagramType dt) {umlDiagType = dt;}
		public void processPackages(Vector di) {
			NodeList nl = getPackageList();
			
			for(int i=0;i<di.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)di.elementAt(i);
				TRACE("processPackages:Checking:"+d.getData().getScope()+":"+d.getUserDefinedName(),4);
				String scope = d.getData().getScope();
				for (int ii=0;ii<nl.getLength();ii++) {
					org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(ii);
					TRACE("processPackages:Checking1:"+scope+":"+e.getAttribute("scope"),4);
					if (scope.equals(e.getAttribute("scope"))) {
						d.getData().setPackageName(e.getAttribute("name"));
					}
				}
			}
		}
		public void createUMLIcons() {
			umlIcons.clear();
			umlIcons.put("class", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlclasst.gif"));
			umlIcons.put("interface", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlinterfacet.gif"));
			umlIcons.put("enum", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlenumt.gif"));
			umlIcons.put("privatemethod", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlprivatemethodt.gif"));
			umlIcons.put("protectedmethod", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlprotectedmethodt.gif"));
			umlIcons.put("publicmethod", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlpublicmethodt.gif"));
			umlIcons.put("privatefield", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlprivatefieldt.gif"));
			umlIcons.put("protectedfield", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlprotectedfieldt.gif"));
			umlIcons.put("publicfield", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlpublicfieldt.gif"));
			umlIcons.put("constructor", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlconstructort.gif"));
			umlIcons.put("abstract", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlabstractt.gif"));
			umlIcons.put("static", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlstatict.gif"));
			umlIcons.put("final", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlfinalt.gif"));
			umlIcons.put("overrides", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umloverridest.gif"));
			umlIcons.put("implements", new ImageIcon(dataRelativePath+"/"+appDirectory+"/umlicons/umlimplementst.gif"));
		}
		public ImageIcon getUMLIcon(String key) {
			return (ImageIcon) umlIcons.get(key);
		}
		public int drawUMLIcons(ImageIcon[] icons,Graphics2D g2d,int x,int y) {
			int pixelsBetweenIcons = 3;
			int ret = 0;
			for (int i=0;i<icons.length;i++) {
				ImageIcon icon = icons[i];
				if (icon != null) {
					icon.paintIcon(null,g2d,x,y);
					TRACE("drawUMLIcons:icon width:"+String.valueOf(icon.getIconWidth()),4);
					x = x + icon.getIconWidth() + pixelsBetweenIcons; 
					ret = ret + icon.getIconWidth() + pixelsBetweenIcons;
				}
			}
			
			return ret;
		}
		public ImageIcon[] getUMLIcons(String access,String exaccess)
		{
			Vector keywords = supportFunctions.splitIntoTokens(exaccess," ");
			keywords.insertElementAt(access, 0);
			int numKeywords = keywords.size();
			ImageIcon[] icons = new ImageIcon[numKeywords];
			for (int i=0;i<keywords.size();i++) {
				String keyword = (String)keywords.elementAt(i);
				TRACE("getUMLIcon for " + keyword,4);
				icons[i] = getUMLIcon(keyword);					
			}
			
			return icons;
		}
		public String getAccessRegExp(){
			//return "(public|protected|private)\\s+(abstract|static|final)*\\s*(abstract|static|final)*\\s*";
			String tmp = "(";
			for (int i=0;i<accessKeywords.length;i++) {
					if (i != 0) {tmp = tmp + "|";}
					tmp = tmp + accessKeywords[i];
			}
			tmp = tmp + ")\\s+(";
			for (int i=0;i<exaccessKeywords.length;i++) {
				if (i != 0) {tmp = tmp + "|";}
				tmp = tmp + exaccessKeywords[i];
			}
			tmp = tmp + ")*\\s*(";
			for (int i=0;i<exaccessKeywords.length;i++) {
				if (i != 0) {tmp = tmp + "|";}
				tmp = tmp + exaccessKeywords[i];
			}
			tmp = tmp + ")*\\s*";
			TRACE("getAccessRegExp:"+tmp,4);
			
			return tmp;
		}
		public Vector getAccessKeywords() {
			Vector v = new Vector();
			for (int i=0;i<accessKeywords.length;i++) {v.addElement(accessKeywords[i]);}
			return v;
		}
		public Vector getExAccessKeywords() {
			Vector v = new Vector();
			for (int i=0;i<exaccessKeywords.length;i++) {v.addElement(exaccessKeywords[i]);}
			return v;
		}
		public String getNextExtraFile() {
			if (extraFiles.size() == 0) {return "";}
			String s = (String)extraFiles.elementAt(0);
			extraFiles.removeElementAt(0);
			return s;
		}
		public void clearExtraFiles() {extraFiles.removeAllElements();}
		public Vector getExtraFiles() {return extraFiles;}
		public void addExtraFile(String s) {extraFiles.addElement(s);}
		public String getFileExt() {return fileExt;}
		public void setFileExt(String s) {fileExt = s;}
		public configurationSettings getCodeMetrics() {return UMLCodeMetrics;}
		public void setCodeMetrics(configurationSettings settings) {UMLCodeMetrics = settings;}
		public void setCodeMetric(String name,String value) {
			if (getCodeMetrics() != null) {
				TRACE("setCodeMetric:"+name+"="+value,4);
				getCodeMetrics().setConfigurationSetting(name,value);
			} else {
				TRACE("setCodeMetric:not inited",4);
			}
		}
		public String getCodeMetric(String name,String defaultvalue) {
			if (getCodeMetrics() != null) {
				return getCodeMetrics().getConfigurationSetting(name,defaultvalue);
			}
			return (String)null;
		}
		public void generateCode(Vector drawingItems){
			// called from the GUI, so can assume UML DIs have been created
			// generateCode(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML))
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
			}
			supportFunctions.displayMessageDialog(null,"generateCode:NYI");
		}
		public void propChanged(String title,statusCanvasProp tmp,String newValue) {
			
		}
		public String getUseCaseNames() {			
			Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase);
			String useCaseNames = notConnectedString;
			for(int i=0;i<v.size();i++) {
				UMLUseCaseDrawingItem d = (UMLUseCaseDrawingItem)v.elementAt(i);
				if (!d.isActor()) {
					useCaseNames = useCaseNames + "," + d.getUserDefinedName();
				}
			}
			
			return useCaseNames;
		}
		public void addActor() {
			String[] titles = new String[]{"Name","Description","Type","Connection","Conn Text"};			
			String[] defaults = new String[] {"","",getEnumValues(actorType.NONE),getUseCaseNames(),"blank,communicates,uses,extends"};
			String[] data = supportFunctions.getDataAsDialog("Add UML Use Case Actor",titles,defaults);
			if (data != null) {
				org.w3c.dom.Element e = umlDiagram.getUseCaseDocument().createElement("actor");
				e.setAttribute("name",data[0]);
				e.setAttribute("description",data[1]);
				e.setAttribute("type",data[2]);
				e.setAttribute("conn",data[3]);
				e.setAttribute("conntext",data[4]);
				umlDiagram.getUseCaseRootElement().appendChild(e);
				umlDiagram.umlXMLFileUseCase().saveXMLDataFile();	
				getDrawingCanvas().addDrawingItem(data[0],dcTypeUMLUseCase,0,0,
						data[0],data[1],data[2],data[3]+":"+data[4],false,Color.black);
				
				// recalculate cords and connectors
				processUseCaseConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
				processUseCaseCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
			}
		}
		public void diagramProperties() {
			if (umlDiagram.getDiagramType() == UMLDiagramType.STATE) {	
				String[] titles = new String[]{"Title"};
				String[] defaults = new String[]{umlDiagram.getStateDiagramTitle()};
				String[] data = supportFunctions.getDataAsDialog("State Diagram Title",titles,defaults);
				if (data != null) {
					umlDiagram.setStateDiagramTitle(data[0]);
				}

				NodeList nList = supportFunctions.executeXPathExpr(umlDiagram.getStateDocument(),"/umlstate/setup");
				org.w3c.dom.Element e = (org.w3c.dom.Element)nList.item(0);
				org.w3c.dom.Element eNew = umlDiagram.getStateDocument().createElement("setup");
				eNew.setAttribute("title",umlDiagram.getStateDiagramTitle());
				if (e == null) {
					umlDiagram.getStateRootElement().appendChild(eNew);
				} else {
					umlDiagram.getStateRootElement().replaceChild(eNew,e);
				}
				umlDiagram.umlXMLFileState().saveXMLDataFile();
			}			
			if (umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {	
			}
			if (umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {
				String[] titles = new String[]{"Title"};
				String[] defaults = new String[]{umlDiagram.getUseCaseDiagramTitle()};
				String[] data = supportFunctions.getDataAsDialog("Use Case Diagram Title",titles,defaults);
				if (data != null) {
					umlDiagram.setUseCaseDiagramTitle(data[0]);
				}

				NodeList nList = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/setup");
				org.w3c.dom.Element e = (org.w3c.dom.Element)nList.item(0);
				org.w3c.dom.Element eNew = umlDiagram.getUseCaseDocument().createElement("setup");
				eNew.setAttribute("title",umlDiagram.getUseCaseDiagramTitle());
				if (e == null) {
					umlDiagram.getUseCaseRootElement().appendChild(eNew);
				} else {
					umlDiagram.getUseCaseRootElement().replaceChild(eNew,e);
				}
				umlDiagram.umlXMLFileUseCase().saveXMLDataFile();
			}
			if (umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {	
				String[] titles = new String[]{"Title"};
				String[] defaults = new String[]{umlDiagram.getSequenceDiagramTitle()};
				String[] data = supportFunctions.getDataAsDialog("Sequence Diagram Title",titles,defaults);
				if (data != null) {
					umlDiagram.setSequenceDiagramTitle(data[0]);
				}

				NodeList nList = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),"/umlsequence/setup");
				org.w3c.dom.Element e = (org.w3c.dom.Element)nList.item(0);
				org.w3c.dom.Element eNew = umlDiagram.getSequenceDocument().createElement("setup");
				eNew.setAttribute("title",umlDiagram.getSequenceDiagramTitle());
				if (e == null) {
					umlDiagram.getSequenceRootElement().appendChild(eNew);
				} else {
					umlDiagram.getSequenceRootElement().replaceChild(eNew,e);
				}
				umlDiagram.umlXMLFileSequence().saveXMLDataFile();
			}			
		}
		public void addUseCase() {
			String[] titles = new String[]{"Name","Description","Connection","Conn Text"};
			String[] defaults = new String[] {"","",getUseCaseNames(),"blank,communicates,uses,extends"};
			String[] data = supportFunctions.getDataAsDialog("Add UML Use Case",titles,defaults);
			if (data != null) {
				org.w3c.dom.Element e = umlDiagram.getUseCaseDocument().createElement("usecase");
				e.setAttribute("name",data[0]);
				e.setAttribute("description",data[1]);
				e.setAttribute("conn",data[2]);
				e.setAttribute("conntext",data[3]);
				umlDiagram.getUseCaseRootElement().appendChild(e);
				umlDiagram.umlXMLFileUseCase().saveXMLDataFile();
				getDrawingCanvas().addDrawingItem(data[0],dcTypeUMLUseCase,0,0,
						data[0],data[1],data[2],data[3],false,Color.black);

				// recalculate cords and connectors
				processUseCaseConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
				processUseCaseCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
			}
		}
		public void addState() {
			Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState);

			String stateConnTypes = "";
			int ii = 0;
			for (UMLConnectorType p : UMLConnectorType.values()) {
				if (p.type() == dcTypeUMLState) {
					if (ii != 0) {stateConnTypes = stateConnTypes + ",";ii++;}
					//stateConnTypes = ateConnTypes + p.description();					
					stateConnTypes = stateConnTypes + p;	// just p, so we can use valueOf with the text to get an enum instance				
				}
			}

			String stateNames = notConnectedString;
			for(int i=0;i<v.size();i++) {
				UMLStateDrawingItem d = (UMLStateDrawingItem)v.elementAt(i);
				stateNames = stateNames + "," + d.getUserDefinedName();
			}
			String[] titles = new String[]{"Name","Description","Type","Connection","Connection Type"};			
			String[] defaults = new String[] {"","",getEnumValues(stateType.NONE),stateNames,stateConnTypes};
			String[] data = supportFunctions.getDataAsDialog("Add UML Sequence",titles,defaults);
			if (data != null) {
				addState1(data[0],data[1],data[2],data[3],data[4]);
				// recalculate cords and connectors
				processStateConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState));
				processStateCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState));
			}
		}
		public void addState1(String name,String desc,String type,String conn,String conntype) {
			org.w3c.dom.Element e = getStateDocument().createElement("state");
			e.setAttribute("name",name);
			e.setAttribute("description",desc);
			e.setAttribute("type",type);
			e.setAttribute("conn",conn);
			e.setAttribute("conntype",conntype);
			umlDiagram.getStateRootElement().appendChild(e);
			umlDiagram.umlXMLFileState().saveXMLDataFile();
			getDrawingCanvas().addDrawingItem(name,dcTypeUMLState,0,0,
					name,desc,type,conn+":"+conntype,false,Color.black);
			
		}
		public void addSequence() {
			Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence);

			String sequenceConnTypes = "";
			int ii = 0;
			for (UMLConnectorType p : UMLConnectorType.values()) {
				if (p.type() == dcTypeUMLSequence) {
					if (ii != 0) {sequenceConnTypes = sequenceConnTypes + ",";ii++;}
					//sequenceConnTypes = sequenceConnTypes + p.description();					
					sequenceConnTypes = sequenceConnTypes + p;	// just p, so we can use valueOf with the text to get an enum instance				
				}
			}

			String sequenceNames = notConnectedString;
			for(int i=0;i<v.size();i++) {
				UMLSequenceDrawingItem d = (UMLSequenceDrawingItem)v.elementAt(i);
				sequenceNames = sequenceNames + "," + d.getUserDefinedName();
			}
			String[] titles = new String[]{"Name","Description","Type","Connection","Connection Type"};			
			String[] defaults = new String[] {"","",getEnumValues(sequenceType.NONE),sequenceNames,sequenceConnTypes};
			String[] data = supportFunctions.getDataAsDialog("Add UML Sequence",titles,defaults);
			if (data != null) {
				addSequence1(data[0],data[1],data[2],data[3],data[4]);
				// recalculate cords and connectors
				processSequenceConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence));
				processSequenceCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence));
			}
		}
		public void addSequence1(String name,String desc,String type,String conn,String conntype) {
			org.w3c.dom.Element e = getSequenceDocument().createElement("sequence");
			e.setAttribute("name",name);
			e.setAttribute("description",desc);
			e.setAttribute("type",type);
			e.setAttribute("conn",conn);
			e.setAttribute("conntype",conntype);
			umlDiagram.getSequenceRootElement().appendChild(e);
			umlDiagram.umlXMLFileSequence().saveXMLDataFile();
			getDrawingCanvas().addDrawingItem(name,dcTypeUMLSequence,0,0,
					name,desc,type,conn+":"+conntype,false,Color.black);			
		}
		public void UMLConnectorDoubleClicked(UMLBaseConnector conn) {
			String expr = "/umlconnectors/connector[@id='"+conn.getIDAsString()+"']";
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getConnectorsDocument(),expr);
			if (n.getLength() == 0) {supportFunctions.displayMessageDialog(null,"Connector XML Error");return;}
			org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);

			JPanel p = new JPanel();
			p.add(new JLabel(e.getAttribute("id")));
			supportFunctions.displayPanelDialog(null,p,"Connector ID",true);
		}
		public int getMarginWidth() {return iMarginWidth;}
		public int getMarginHeight() {return iMarginHeight;}
		public org.w3c.dom.Document getDiagDocument() {return doc;}
		public org.w3c.dom.Document getTypesDocument() {return docTypes;}
		public org.w3c.dom.Document getNotesDocument() {return docNotes;}
		public org.w3c.dom.Document getCustomDocument() {return docCustom;}
		public org.w3c.dom.Document getConnectorsDocument() {return docConnectors;}
		public org.w3c.dom.Document getUseCaseDocument() {return docUseCase;}
		public org.w3c.dom.Document getSequenceDocument() {return docSequence;}
		public org.w3c.dom.Document getStateDocument() {return docState;}
		public org.w3c.dom.Element getDiagRootElement() {return root;}
		public org.w3c.dom.Element getTypesRootElement() {return rootTypes;}
		public org.w3c.dom.Element getNotesRootElement() {return rootNotes;}
		public org.w3c.dom.Element getCustomRootElement() {return rootCustom;}
		public org.w3c.dom.Element getConnectorsRootElement() {return rootConnectors;}
		public org.w3c.dom.Element getUseCaseRootElement() {return rootUseCase;}
		public org.w3c.dom.Element getSequenceRootElement() {return rootSequence;}
		public org.w3c.dom.Element getStateRootElement() {return rootState;}
		public xmlDataFile umlXMLFile() {return umlDiag;}
		public xmlDataFile umlXMLFileTypes() {return umlDiagTypes;}
		public xmlDataFile umlXMLFileNotes() {return umlDiagNotes;}
		public xmlDataFile umlXMLFileCustom() {return umlDiagCustom;}
		public xmlDataFile umlXMLFileConnectors() {return umlDiagConnectors;}
		public xmlDataFile umlXMLFileUseCase() {return umlDiagUseCase;}
		public xmlDataFile umlXMLFileSequence() {return umlDiagSequence;}
		public xmlDataFile umlXMLFileState() {return umlDiagState;}
		public void reset() {
			if (umlDiag != null) {umlDiag.closeXMLDataFile();}
			if (umlDiagTypes != null) {umlDiagTypes.closeXMLDataFile();}
			if (umlDiagNotes != null) {umlDiagNotes.closeXMLDataFile();}
			if (umlDiagCustom != null) {umlDiagCustom.closeXMLDataFile();}
			if (umlDiagConnectors != null) {umlDiagConnectors.closeXMLDataFile();}
			if (umlDiagUseCase != null) {umlDiagUseCase.closeXMLDataFile();}
			if (umlDiagSequence != null) {umlDiagSequence.closeXMLDataFile();}
			if (umlDiagState != null) {umlDiagState.closeXMLDataFile();}
			if (UMLCodeMetrics != null) {UMLCodeMetrics.closeConfigurationSettings();}

			filename = "untitled";
			bModified = false;
			bNewCompile = false;
			umlDiag = null;
			umlDiagTypes = null;
			umlDiagNotes = null;
			umlDiagCustom = null;
			umlDiagConnectors = null;
			umlDiagUseCase = null;
			umlDiagSequence = null;
			umlDiagState = null;
			root = null;
			doc = null;
			rootTypes = null;
			docTypes = null;
			rootNotes = null;
			docNotes = null;
			rootCustom = null;
			docCustom = null;
			docConnectors = null;
			rootConnectors = null;
			docUseCase = null;
			rootUseCase = null;
			docSequence = null;
			rootSequence = null;
			iMaxLevel = -1;
			maxLevelWidth = null;
			maxLevelHeight = null;	
			numberDIOnLevel = null;
			iMarginWidth =  2 * charWidth;
			iMarginHeight =  2 * charHeight;
			UMLConnID = 0;
			UMLCodeMetrics = null;
			umlDiagType = UMLDiagramType.CLASS;
			rcUMLDiagram = null;
						
			menuUMLDI = new PopupMenu("UML DI");
			MenuItem mi = new MenuItem("Delete");
			mi.addActionListener(new deleteAction());
			menuUMLDI.add(mi);
			mi = new MenuItem("Add Class Note");
			mi.addActionListener(new addClassNotesAction());
			menuUMLDI.add(mi);
			mi = new MenuItem("Delete Class Note");
			mi.addActionListener(new deleteClassNotesAction());
			menuUMLDI.add(mi);
			
			classNoteIcon = new customIcon();
			int[] y1 = {0,0,15,15,0};
			int[] x1 = {0,15,15,0,0};
			customIconData ciD = new customIconData(x1,y1,Color.black);
			classNoteIcon.addData(ciD);
			int[] y2 = {1,1,14,14,1};
			int[] x2 = {1,14,14,1,1};
			ciD = new customIconData(x2,y2,Color.white);
			classNoteIcon.addData(ciD);
			int[] y3 = {3,3,4,4,3};
			int[] x3 = {2,13,13,2,2};
			ciD = new customIconData(x3,y3,Color.black);
			classNoteIcon.addData(ciD);
			int[] y4 = {6,6,7,7,6};
			int[] x4 = {2,13,13,2,2};
			ciD = new customIconData(x4,y4,Color.black);
			classNoteIcon.addData(ciD);
			int[] y5 = {9,9,10,10,9};
			int[] x5 = {2,13,13,2,2};
			ciD = new customIconData(x5,y5,Color.black);
			classNoteIcon.addData(ciD);
			int[] y6 = {12,12,13,13,12};
			int[] x6 = {2,13,13,2,2};
			ciD = new customIconData(x6,y6,Color.black);
			classNoteIcon.addData(ciD);
			
			UMLConnectors.removeAllElements();
			UMLClassLocInfoBlocks.removeAllElements();
			clearExtraFiles();
			
			createUMLIcons();
		}
		public void setupUseCaseData() {
			// can't call in reset() as following line returns null pointer on startup
			Rectangle rc = getDrawingCanvas().getDCBoundingRect();
			rcUseCaseRectangle = new Rectangle(rc.width/4,rc.height/4,
					rc.width/2,rc.height/2);
			rcUseCaseActor1Rectangle = new Rectangle(rcUseCaseRectangle.x/4,
					(int)(rc.height*0.1),rcUseCaseRectangle.x/2,(int)(rc.height*0.8));
			rcUseCaseActor2Rectangle = new Rectangle(rcUseCaseRectangle.x + rcUseCaseRectangle.width + rcUseCaseRectangle.x/4,
					(int)(rc.height*0.1),rcUseCaseRectangle.x/2,(int)(rc.height*0.8));
		}
		public Vector getUMLClassLocInfoBlocks() {return UMLClassLocInfoBlocks;}
		public boolean getNewCompile() {return bNewCompile;}
		public void setNewCompile(boolean b) {bNewCompile = b;}
		public customIcon getClassNoteIcon() {return classNoteIcon;}
		public PopupMenu getMenuUMLDI() {return menuUMLDI;}
		public String[] getClassExtends(Vector drawingItems,UMLDrawingItem d) {
			Vector v = supportFunctions.splitIntoTokens(d.getData().getClassExtends(),",");
			String[] opts = new String[v.size()];
			v.copyInto(opts);
			return opts;
		}
		public boolean isExtends(Vector drawingItems,UMLDrawingItem d) {
			// does the class extend an application (not library,framework,etc.) class?
			String extendsclassname = d.getData().getClassExtends();
			TRACE("isExtends:"+extendsclassname,4);
			Vector v = supportFunctions.splitIntoTokens(extendsclassname,",");
			
			boolean bFound = false;
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem dd = (UMLDrawingItem)drawingItems.elementAt(i);
				String tmp = dd.getData().getClassName();
				if (v.contains(tmp)) {TRACE("isExtends:"+tmp+" extends "+d.getData().getClassName(),4);bFound = true;}
			}
		
			TRACE("isExtends:"+d.getData().getClassName()+" does not extend an application class",4);
			return bFound;
		}
		public boolean isExtended(Vector drawingItems,UMLDrawingItem d) {
			// is the class extended by an application (not library,framework,etc.) class?
			String classname = d.getData().getClassName();
			TRACE("isExtended:"+classname,4);
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem dd = (UMLDrawingItem)drawingItems.elementAt(i);
				Vector v = supportFunctions.splitIntoTokens(dd.getData().getClassExtends(),",");
				if (v.contains(classname)) {TRACE("isExtended:"+classname+" is extended by "+dd.getData().getClassName(),4);return true;}
			}
			
			TRACE("isExtended:"+classname+" is not extended by an application class",4);
			return false;
		}
		public int getUMLDrawingItemMaxLevel(Vector drawingItems) {
			int maxLevel = 0;
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				if (d.getLevel() > maxLevel) {maxLevel = d.getLevel();}
			}
			return maxLevel + 1;
		}
		public Vector getUMLDrawingItemsOnSheet(Vector drawingItems,int sheetNumber) {
			Vector v = new Vector();
			
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				if (d.getSheet() == sheetNumber) {v.addElement(d);}
			}
			return v;
		}
		public Vector getUMLLevelSet(Vector drawingItems,int level) {
			Vector v = new Vector();
			
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				if (d.getLevel() == level) {v.addElement(d);}
			}
			return v;
		}
		public Vector calcNewLevelSet(Vector drawingItems) {
			Vector v = new Vector();
			
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				if (d.getLevel() == -1) {v.addElement(d);}
			}
			return v;
		}
		public void setUMLCustomText(String id,String text) {
			if (umlDiagram.getCustomDocument() != null) {
				org.w3c.dom.Element setting = getCustomDocument().createElement("customitem");
				setting.setAttribute("id",id);
				setting.setAttribute("text",text);

				NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getCustomDocument(),"/umlcustom/customitem[@id='"+id+"']");
				if (n.getLength() == 0) {
					umlDiagram.getCustomRootElement().appendChild(setting);
				} else {
					org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
					umlDiagram.getCustomRootElement().replaceChild(setting,e);
				}
			
				umlDiagram.umlXMLFileCustom().saveXMLDataFile(); 
			}
		}
		public String getUMLCustomText(String id) {
			//TRACE("getUMLCustomText:id:"+id,4);
			if (umlDiagram.getCustomDocument() != null) {
				NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getCustomDocument(),"/umlcustom/customitem[@id='"+id+"']");
				for (int i=0;i<n.getLength();i++) {
					org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
					return e.getAttribute("name");
				}
			}

			return "";
		}
		public String getUMLCustomOCLText(String id) {
			String ret = "";
			String s = getUMLCustomText(id);
			if (s.length() != 0) {
				ret = "{" + ret + "}";
			}
			
			return ret;
		}
		public String getUMLCustomStereotypeText(String id) {
			String ret = "";
			String s = getUMLCustomText(id);
			if (s.length() != 0) {
				ret = "<<" + ret + ">>";
			}
			
			return ret;
		}
		
		public String getUMLCustomMultiplicityText(String id) {
			String ret = "";
			String s = getUMLCustomText(id);
			if (s.length() != 0) {
				ret = "[" + ret + "]";
			}
			
			return ret;
		}
		public String getUMLCustomPropsText(String id) {
			String ret = "";
			String constraint = getUMLCustomText(id + ":constraint");
			String nvp = getUMLCustomText(id + ":namevaluepair");
			TRACE("getUMLCustomProps:constraint:"+constraint+":nvp:"+nvp,4);
			if (constraint.length() == 0 && nvp.length() == 0) {return ret;}
			ret = "{" + constraint;
			if (ret.length() != 1) {ret = ret + ",";}
			ret = ret + nvp + "}";
			
			return ret;
		}
		public void processSequenceConnectors(Vector drawingItems) {			
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.SEQUENCESYNC);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.SEQUENCESYNCRETURN);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.SEQUENCEASYNC);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.SEQUENCESIMPLE);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.SEQUENCERETURN);

			for (int i=0;i<drawingItems.size();i++) {
				UMLSequenceDrawingItem dStart = (UMLSequenceDrawingItem)drawingItems.elementAt(i);
				String s = dStart.getSequenceConnType(); // in the form name:type
				Vector v = supportFunctions.splitIntoTokens(s,":");
				if (v.size() == 2) {
					String connname = (String)v.elementAt(0);
					String conntype = (String)v.elementAt(1);
					if (!connname.equals(notConnectedString)) {
						UMLConnectorType connType = UMLConnectorType.valueOf(conntype);
						UMLSequenceDrawingItem dEnd = (UMLSequenceDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(connname);
						if (dEnd != null) {
							TRACE("processSequenceConnectors:"+dStart.getUserDefinedName()+":"+dEnd.getUserDefinedName()+":"+connType.description(),4);
							UMLConnectors.addElement(new UMLSequenceConnector(dStart,dEnd,connType,UMLConnID++));
						}
					} else {
						// no connector defined for this UML sequence item
					}
				} else {
					supportFunctions.displayMessageDialog(null,"Sequence connector error");
				}
			}
		}
		public void processUseCaseConnectors(Vector drawingItems) {	
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.USECASE);
			for (int i=0;i<drawingItems.size();i++) {
				UMLUseCaseDrawingItem dStart = (UMLUseCaseDrawingItem)drawingItems.elementAt(i);
//				if (dStart.isActor()) {
					UMLUseCaseDrawingItem dEnd = (UMLUseCaseDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(dStart.getUseCaseConn());
					if (dEnd != null) {
						TRACE("processUseCaseConnectors:"+dStart.getUserDefinedName()+":"+dEnd.getUserDefinedName(),4);
						UMLUseCaseConnector c = new UMLUseCaseConnector(dStart,dEnd,UMLConnectorType.USECASE,UMLConnID++);
						c.setConnText(dStart.getUseCaseConnText());
						UMLConnectors.addElement(c);
					} else {
						if (!dStart.getUseCaseConn().equals(notConnectedString)) {
							supportFunctions.displayMessageDialog(null,"processUseCaseConnectors:Actor " + dStart.getUseCaseConn() + " not found, reset to notConnectedString.");
							dStart.setUseCaseConn(notConnectedString);
						}
					}
//				}
			}
		}
		public void processUMLConnectors(Vector drawingItems) {
			TRACE("processUMLConnectors:Number of UML DIs:"+String.valueOf(drawingItems.size()),4);
			
			// check if this is a 'Open Diagram' if so use existing connector datafile (user may have changed some connector types)
			if (umlDiagram.getNewCompile() == false) { // HOOK G
				int resp = JOptionPane.showConfirmDialog(null,"Recalculate Connectors",systemUserReg.getAppName(),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (resp == JOptionPane.NO_OPTION) {return;}
			}
			
			UMLConnectors.removeAllElements();
			UMLConnID = 0;
			
			// inheritance connectors
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem dStart = (UMLDrawingItem)drawingItems.elementAt(i);
				TRACE("processUMLConnectors(heir):processing class:"+dStart.getData().getClassName(),4);
				if (isExtends(drawingItems,dStart)) {
					String[] extendclasses = umlDiagram.getClassExtends(drawingItems,dStart);
					for (int ii=0;ii<extendclasses.length;ii++) {
						TRACE("processUMLConnectors:Extend Class:"+extendclasses[ii],4);
						UMLDrawingItem dEnd = (UMLDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(extendclasses[ii]);
						if (dEnd != null) {
							TRACE("processUMLConnectors:create UML connector",4);
							UMLConnectors.addElement(new UMLConnector(dStart,dEnd,UMLConnectorType.HEIR,UMLConnID++));
						}
					}
				}
			}
			
			// aggregation connectors
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem dStart = (UMLDrawingItem)drawingItems.elementAt(i);
				TRACE("processUMLConnectors(areg):processing class:"+dStart.getData().getClassName(),4);
				if (umlDiagram.getTypesDocument() != null) {
					NodeList nl = supportFunctions.executeXPathExpr(umlDiagram.getTypesDocument(),"/umltypes/type[@subscope='C"+dStart.getData().getClassName()+"' and (@class='Application' or @class='TemplateType')]");
					TRACE("processUMLConnectors:Number of selected types(Application):"+String.valueOf(nl.getLength()),4);
					for (int ii=0;ii<nl.getLength();ii++) {
						org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(ii);
						String classname = e.getAttribute("name");
						String variable = e.getAttribute("variable");
						boolean bArray = false;
						int index = classname.indexOf("[");
						if (index != -1) {classname = classname.substring(0,index);bArray = true;}
						UMLDrawingItem dEnd = (UMLDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(classname);
						if (dEnd != null) {
							TRACE("processUMLConnectors:create UML connector(application)",4);
							UMLConnectors.addElement(new UMLConnector(dStart,dEnd,UMLConnectorType.AREG,UMLConnID++,bArray,variable));
						}
					}
					nl = supportFunctions.executeXPathExpr(umlDiagram.getTypesDocument(),"/umltypes/type[@subscope='C"+dStart.getData().getClassName()+"' and @class='Container']");
					TRACE("processUMLConnectors:Number of selected types(Container):"+String.valueOf(nl.getLength()),4);
					for (int ii=0;ii<nl.getLength();ii++) {
						org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(ii);
						String contains = e.getAttribute("contains");
						String variable = e.getAttribute("variable");
						UMLDrawingItem dEnd = (UMLDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(contains);
						if (dEnd != null) {
							TRACE("processUMLConnectors:create UML connector(container)",4);
							UMLConnectors.addElement(new UMLConnector(dStart,dEnd,UMLConnectorType.AREG,UMLConnID++,true,variable));
						}
					}
				}
			}
			
			// depends connectors - Go through every function parameter, if the name of an enum, class, etc. 
			// then we connect to that enum/class with a depends connector.
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem dStart = (UMLDrawingItem)drawingItems.elementAt(i);
				UMLClassFunctionData[] functionList = dStart.getData().getClassFunctionStrings();
				for (int ii=0;ii<functionList.length;ii++) {
					UMLClassFunctionData tmp = functionList[ii];
					
					String funcparams = tmp.getFunctionParameters();
					TRACE("Depends:" + funcparams,4);
					Vector v = supportFunctions.splitIntoTokens(funcparams,",");
					for (int iii=0;iii<v.size();iii++) {
						Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(iii)," ");
						
						String classname = (String)v1.elementAt(0);
						int index = classname.indexOf("[");
						if (index != -1) {classname = classname.substring(0,index);}
						UMLDrawingItem dEnd = (UMLDrawingItem)sDC.getDC().getDIWithUserDefinedName(classname);
						if (dEnd != null) {
							UMLConnectors.addElement(new UMLConnector(dStart,dEnd,UMLConnectorType.DEPENDS,UMLConnID++,false,""));
						}
					}
				}
			}
			
			TRACE("ProcessUMLConnectors:Number of connectors:"+String.valueOf(UMLConnectors.size()),4);

			String filename = umlDiagram.getFilename();
			filename = filename.substring(0,filename.length()-8); // remove the end _umldiag
			pseduoFile connectorsFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + "_connectors.xml");
			connectorsFile.saveFile("<?xml version=\"1.0\"?>"); // saveFile used to remove any existing file
			connectorsFile.appendFile("\n");
			connectorsFile.appendFile("<umlconnectors>");
			for (int i=0;i<UMLConnectors.size();i++) {
				UMLConnector conn = (UMLConnector)UMLConnectors.elementAt(i);
				connectorsFile.appendFile("\n\t<connector id=\"" + conn.getIDAsString() + "\" start=\"" + conn.getStart().getUserDefinedName() + "\" end=\"" + conn.getEnd().getUserDefinedName() +"\" array=\"" + conn.getArrayAsString() + "\" type=\"" + conn.getTypeAsString() + "\" variable=\"" + conn.getVariable() + "\"/>\n");
				mainTab.compilier.getSymbolTable().addSymbol(filename,filename,conn.getIDAsString()+"-"+conn.getStart().getUserDefinedName()+"-"+conn.getEnd().getUserDefinedName(),conn.getVariable()+"-"+conn.getTypeAsString()+"-"+conn.getArrayAsString(),symType.CONNECTOR,symClass.NONE);
			}
			connectorsFile.appendFile("\n");
			connectorsFile.appendFile("</umlconnectors>");
			connectorsFile.flush();
		
			String f = dataRelativePath+"/"+appDirectory+"/output"+"/"+supportFunctions.getFilenameNoExt(filename) + "_connectors.xml";
			if (umlDiagConnectors.openXMLDataFile(f, "umlconnectors", false)) {
				rootConnectors = umlDiagConnectors.getRootElement();
				docConnectors = umlDiagConnectors.getXMLDocument();
			} else {
				rootConnectors = null;
				docConnectors = null;
			}
		}
		
		public void drawUMLConnectors(Graphics2D g2d) {
			TRACE("drawUMLConnectors:Num conns:"+String.valueOf(UMLConnectors.size()),4);
						
		   g2d.setColor(Color.black); 

			for (int i=0;i<UMLConnectors.size();i++) {
				UMLBaseConnector conn = (UMLBaseConnector)UMLConnectors.elementAt(i);
				conn.draw(g2d);				
			}
		}

		public void processClassNotes(Vector drawingItems) {
			TRACE("processClassNotes entered",4);
			NodeList nl = umlDiagram.getNotesRootElement().getElementsByTagName("class");
			for (int i=0;i<nl.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)nl.item(i);
				String name = e.getAttribute("name");
				String note = e.getAttribute("note");
				TRACE("processClassNotes:name is " + name + " and note is " + note,4);

				drawingItem d = getDrawingCanvas().getDIWithUserDefinedName(name);
				if (d != null) {
					UMLDrawingItem dd = (UMLDrawingItem)d;
					TRACE("processClassNotes:setting class note for " + name,4);
					dd.getData().setClassNotes(note);
				} else {
					umlDiagram.getNotesRootElement().removeChild(e);
					umlDiagram.umlXMLFileNotes().saveXMLDataFile();
				}
			}
		}
		public int getMaxLevel() {return iMaxLevel;}
		public int[] subArray(int[] srcArray,int fromIndex,int toIndex) {
			if (fromIndex < 0 || toIndex > srcArray.length-1) {return null;}
			if (toIndex < fromIndex) {return null;}
			int [] tmp = new int[(toIndex - fromIndex) + 1];
			int index = 0;
			for (int i=fromIndex;i<=toIndex;i++) {
				tmp[index++]  = srcArray[i];
			}
			
			return tmp;
		}
		public int[] insertIntoArray(int[] srcArray,int index,int value) {
			int tmp[] = new int[srcArray.length + 1];
			System.arraycopy(srcArray,0,tmp,0,srcArray.length);
			for (int i=tmp.length - 1;i>index;i--) {tmp[i] = tmp[i-1];}
			tmp[index] = value;
			
			return tmp;
		}
		public Vector getUMLDrawingItemsInLevelOrder(Vector drawingItems) {
			int level = 0;
			Vector orgDrawingItems = drawingItems;
			Vector v = new Vector();
			while (orgDrawingItems.size() != 0) {
				int i = 0;
				while (i < orgDrawingItems.size()) {
					UMLDrawingItem d = (UMLDrawingItem)orgDrawingItems.elementAt(i);
					if (d.getLevel() == level) {
						v.addElement(d);
						orgDrawingItems.removeElementAt(i);
					} else {i++;}
				}
				level++;
			}
			
			return v;
		}
		public void splitIntoSheets(Vector drawingItems) {
			Rectangle rcSheet = new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY);
			int numXSheets = (rcUMLDiagram.width / ehsConstants.dcMaxX) + 1;
			int numYSheets = (rcUMLDiagram.height / ehsConstants.dcMaxY) + 1;

			if (numYSheets != 1) {
				// should always be 1 because processCordData(...) adjusts canvas's
				// height to be the max height of the bounding rect of the UML diagram
				supportFunctions.displayMessageDialog(null,"splitIntoSheets:Num Y Sheets Error");
			}
			
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				Rectangle rc = d.getBoundingRect();

				int sx = rc.x / ehsConstants.dcMaxX;
				int sy = rc.y / ehsConstants.dcMaxY;
				int newSheet = (sy * numXSheets) + sx + 1;
				d.setSheet(newSheet);
				
				int xNewCord = rc.x - (sx * ehsConstants.dcMaxX);
				int yNewCord = rc.y - (sy * ehsConstants.dcMaxY);
				d.moveTo(new Point(xNewCord,yNewCord));
			}

			umlDiagram.getDiagramType().setMaxSheet(numXSheets * numYSheets);
		}
		public void processMaxWidthHeightData(Vector drawingItems) {
			maxLevelWidth = new int[iMaxLevel];
			maxLevelHeight = new int[iMaxLevel];
			for (int i=0;i<iMaxLevel;i++) {
				maxLevelWidth[i] = 0;
				maxLevelHeight[i] = 0;	
			}			
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				Rectangle rc = d.getBoundingRect();
				int iLevel = d.getLevel();
				if (rc.width > maxLevelWidth[iLevel]) {maxLevelWidth[iLevel] = rc.width;}
				if (rc.height > maxLevelHeight[iLevel]) {maxLevelHeight[iLevel] = rc.height;}
			}
		}
		public void processLevelData(Vector drawingItems) {
			
			// reset default level for each UML drawing item
			iMaxLevel = 0;
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				d.setLevel(-1);
				d.setOrignalLevel(-1);
				d.setSheet(1);
			}
			
			// calculate all UML drawing items to appear on level 0
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				String tmp = d.getUserDefinedName();
				if (tmp.equals(globalDataClass) || d.getData().isInterface() || d.getData().isEnum() || (!isExtends(drawingItems,d) && !isExtended(drawingItems,d))) {
					d.setLevel(0);
					d.setOrignalLevel(0);
					TRACE("Setting level to 0 for class " + d.getData().getClassName(),4);
				}
			}
			
			// calculate levels for all other UML drawing items
			iMaxLevel++; // this line needs to execute even if no drawing items still with iLevel of -1
			Vector v = calcNewLevelSet(drawingItems);
			TRACE("Size of new level set:"+String.valueOf(v.size()),4);
			while (v.size() != 0) {
				for (int i=0;i<v.size();i++) {
					UMLDrawingItem dd = (UMLDrawingItem)v.elementAt(i);
					if (!isExtended(v,dd)) {
						dd.setLevel(iMaxLevel);
						dd.setOrignalLevel(iMaxLevel);
						TRACE("Setting level to " + String.valueOf(iMaxLevel) + " for class " + dd.getData().getClassName(),4);
					}
				}
				v = calcNewLevelSet(drawingItems);
				TRACE("Size of new level set (loop):"+String.valueOf(v.size()),4);
				iMaxLevel++;
				TRACE("New level (loop):"+String.valueOf(iMaxLevel),4);
			}

		}
		public void compactUMLClassDiagram(Vector drawingItems) {
			calculateCompartments(drawingItems);
			
			for (int sheet=1;sheet<=umlDiagram.getDiagramType().getMaxSheet();sheet++) {
				for(int i=0;i<umlDiagram.UMLClassLocInfoBlocks.size();i++) {
					UMLClassLocationInfo block = (UMLClassLocationInfo)umlDiagram.UMLClassLocInfoBlocks.elementAt(i);
					if (block.getSheet() != sheet) {continue;}
				}	
			}
		}
		public void calculateCompartments(Vector drawingItems) {
			umlDiagram.UMLClassLocInfoBlocks.removeAllElements();
			for (int sheet=1;sheet<=umlDiagram.getDiagramType().getMaxSheet();sheet++) {
				Vector dis = getUMLDrawingItemsOnSheet(drawingItems,sheet);
				int sheetMaxLevel = getUMLDrawingItemMaxLevel(dis);
				for (int level=0;level<sheetMaxLevel;level++) {
					int rowCompartments = numberDIOnLevel[level];
					for (int compartment=0;compartment<rowCompartments;compartment++) {
						int xCord = getMarginWidth();
						for(int ii=0;ii<compartment;ii++) {
							xCord = xCord + maxLevelWidth[level];
						}
						int yCord = getMarginHeight();
						for(int ii=0;ii<level;ii++) {
							yCord = yCord + getMarginHeight() + maxLevelHeight[ii];
						}
						
						Rectangle rcCompartment = new Rectangle(xCord,yCord,
								maxLevelWidth[level],maxLevelHeight[level]);
						Vector v = getDrawingCanvas().drawingItemsInRect(rcCompartment);
						if (v.size() != 0) {
							for(int i=0;i<v.size();i++) {
								UMLDrawingItem d = (UMLDrawingItem)v.elementAt(i);
								UMLClassLocationInfo block = new UMLClassLocationInfo(sheet,level,compartment,d,rcCompartment);
								umlDiagram.UMLClassLocInfoBlocks.addElement(block);
								TRACE("calculateCompartments:" + block.toString(),4);								
							}
						} else {
							UMLClassLocationInfo block = new UMLClassLocationInfo(sheet,level,compartment,null,rcCompartment);
							umlDiagram.UMLClassLocInfoBlocks.addElement(block);
							TRACE("calculateCompartments:" + block.toString(),4);															
						}
					}
				}
			}
		}
		public void generateSequenceXMLData() {
			
		}
		public void generateStateXMLData() {
			
		}
		public void processStateConnectors(Vector v) {
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATESTART);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATEEND);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATEHISTORY);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATENORMAL);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATEBRANCH);
			umlDiagram.deleteConnectorsOfType(UMLConnectorType.STATESYNC);

			for (int i=0;i<v.size();i++) {
				UMLStateDrawingItem d = (UMLStateDrawingItem)v.elementAt(i);
			}
		}
		public void processStateCordData(Vector v) {
			for (int i=0;i<v.size();i++) {
				UMLStateDrawingItem d = (UMLStateDrawingItem)v.elementAt(i);
			}			
		}
		public void processStateDrawingItems() {
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),"/umlstate/setup");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				umlDiagram.setSequenceDiagramTitle(e.getAttribute("title"));
			}
		}
		public void processSequenceCordData(Vector drawingItems) {			
			for (int i=0;i<drawingItems.size();i++) {
				UMLSequenceDrawingItem d = (UMLSequenceDrawingItem)drawingItems.elementAt(i);
			}
		}
		public void processSequenceDrawingItems() {
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getSequenceDocument(),"/umlsequence/setup");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				umlDiagram.setSequenceDiagramTitle(e.getAttribute("title"));
			}
		}
		public void processUseCaseCordData(Vector drawingItems) {
			int margin = 30;
			int numUseCaseItems = 0;
			int numActorItems = 0;
			int numUseCaseColumn = 0;
			Rectangle rcActor = getUseCaseActor1Rectangle();
			
			for (int i=0;i<drawingItems.size();i++) {
				UMLUseCaseDrawingItem d = (UMLUseCaseDrawingItem)drawingItems.elementAt(i);
				if (d.isActor()) {
					d.moveTo(new Point(rcActor.x,(rcActor.y) +
						(numActorItems *  (umlUseCaseSizeY + margin))));
					numActorItems++;
					if (rcActor.y + (numActorItems *  (umlUseCaseSizeY + margin)) > 
					  rcActor.y + rcActor.height) {
						rcActor = getUseCaseActor2Rectangle();
						numActorItems = 0;
					}
				} else {
					d.moveTo(new Point(getUseCaseRectangle().x +
						(numUseCaseColumn * umlUseCaseSizeX),
						(getUseCaseRectangle().y) +
						(numUseCaseItems *  (umlUseCaseSizeY + margin))));
					numUseCaseItems++;
					if (getUseCaseRectangle().y + (numUseCaseItems *  (umlUseCaseSizeY + margin)) >
					  getUseCaseRectangle().y + getUseCaseRectangle().height) {
						numUseCaseColumn++;
						numUseCaseItems = 0;
					}
				}
			}
		}
		public void processUseCaseDrawingItems() {
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/setup");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				umlDiagram.setUseCaseDiagramTitle(e.getAttribute("title"));
			}
			n = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/actor");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				getDrawingCanvas().addDrawingItem(e.getAttribute("name"),dcTypeUMLUseCase,0,0,e.getAttribute("name"),e.getAttribute("description"),
					e.getAttribute("type"),e.getAttribute("conn")+":"+e.getAttribute("conntext"),false,Color.black);
			}
			n = supportFunctions.executeXPathExpr(umlDiagram.getUseCaseDocument(),"/umlusecase/usecase");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				getDrawingCanvas().addDrawingItem(e.getAttribute("name"),dcTypeUMLUseCase,0,0,e.getAttribute("name"),e.getAttribute("description"),
						e.getAttribute("conn"),e.getAttribute("conntext"),false,Color.black);
			}
		}
		public boolean processCordData(Vector drawingItems) {
			Rectangle rcSheet = new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY);
			int xMaxCord = 0;
			int yMaxCord = 0;
		
			int sheetMaxLevel = getUMLDrawingItemMaxLevel(drawingItems);
			TRACE("processCordData:Number Levels:"+String.valueOf(sheetMaxLevel),4);
		  
			numberDIOnLevel = new int[sheetMaxLevel];
			for (int i=0;i<sheetMaxLevel;i++) {
				numberDIOnLevel[i] = 0;
			}			
			
			// calculate x,y coordinates for each UML drawing item
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				int iLevel = d.getLevel();
				Rectangle rc = d.getBoundingRect();
				float bitLeft = (maxLevelWidth[iLevel] - rc.width) / 2;
				int xCord = getMarginWidth();
				//xCord = xCord + (getMarginWidth() + maxLevelWidth[iLevel]) * numberDIOnLevel[iLevel]++;
				xCord = xCord + (maxLevelWidth[iLevel]) * numberDIOnLevel[iLevel]++;
				xCord = xCord + (int)bitLeft;
				int yCord = getMarginHeight();
				for (int ii=0;ii<iLevel;ii++) {yCord = yCord + getMarginHeight() + maxLevelHeight[ii];}
				TRACE("processCordData:Classname:" + d.getData().getClassName() + ":XCord:" + String.valueOf(xCord) + " YCord:" + String.valueOf(yCord),4);
				// check if xCord of DI rhs is bigger then xCord of nearest sheet boundary
				if (xCord + rc.width >= (((rc.x / ehsConstants.dcMaxX) + 1) * ehsConstants.dcMaxX)) {
					numberDIOnLevel[iLevel]++;
					xCord = xCord + maxLevelWidth[iLevel];
				}
				d.moveTo(new Point(xCord,yCord));
				rc = d.getBoundingRect();
				if (xMaxCord < rc.x + rc.width) {xMaxCord = rc.x + rc.width;}
				if (yMaxCord < rc.y + rc.height) {yMaxCord = rc.y + rc.height;}
			}
		  
		  rcUMLDiagram = new Rectangle(0, 0, xMaxCord, yMaxCord);
		  TRACE("processCordData:rcUMLDiagram:" + rcUMLDiagram.toString() + " rcSheet:" + rcSheet.toString(),4);
		  if (rcUMLDiagram.getHeight() > rcSheet.getHeight()) {
			 sDC.setSize(new Dimension((int)rcSheet.getWidth(),(int)rcUMLDiagram.getHeight())); 
			 TRACE("processCordData:drawing canvas size changed",4);
		  }
		  return !supportFunctions.rectContainsRect(rcSheet,rcUMLDiagram);
		}
		public void setModified(boolean b) {bModified = b;}
		public boolean isModified() {return bModified;}
		public boolean isDiagramLoaded() {
			if (umlDiagram.getFilename().equals("untitled")) {return false;}
			
			return true;
		}
		public void createDefaultSourceFile() {		
			pseduoFile sourceFile = new pseduoFile(dataRelativePath+"/"+appDirectory,"testdefault.java");
			sourceFile.saveFile("import java.awt.*;"); // saveFile used to remove any existing file
			sourceFile.appendFile("import java.util.*;");
			sourceFile.appendFile("public static void main(String[] args) {");
			sourceFile.appendFile("}");
			sourceFile.flush();
			TRACE(dataRelativePath+"/"+appDirectory+"/testdefault.java",4);
		}
		public void setFilename(String s) {filename = s;}
		public String getFilename() {return filename;}
		public NodeList getPackageList() {
			NodeList n = null;
			if (doc != null) {n = doc.getElementsByTagName("package");}
			return n;
		}
		public NodeList getEnumList() {
			NodeList n = null;
			if (doc != null) {n = doc.getElementsByTagName("enum");}
			return n;
		}
		public NodeList getClassList() {
			NodeList n = null;
			if (doc != null) {n = doc.getElementsByTagName("class");}
			return n;
		}
		public NodeList getInterfaceList() {
			NodeList n = null;
			if (doc != null) {n = doc.getElementsByTagName("interface");}
			return n;
		}
		public boolean loadAsXML() {
			umlDiag = new xmlDataFile();
			umlDiagTypes = new xmlDataFile();
			umlDiagNotes = new xmlDataFile();
			umlDiagCustom = new xmlDataFile();
			umlDiagConnectors = new xmlDataFile();
			umlDiagUseCase = new xmlDataFile();
			umlDiagSequence = new xmlDataFile();
			umlDiagState = new xmlDataFile();
			
			TRACE("LAX:Filename:"+filename,4);
			
			if (!filename.endsWith("_umldiag")) {
				supportFunctions.displayMessageDialog(null,"XML file not produced with " + systemUserReg.getAppName());
				root = null;
				doc = null;
				return false;
			}
			
			TRACE("UML XML Filename:"+filename,4);
			if (!umlDiag.openXMLDataFile(filename,"umldata",false)) {
				supportFunctions.displayMessageDialog(null,"Failed to open XML Data");
				root = null;
				doc = null;
				return false;
			}
			root = umlDiag.getRootElement();
			doc = umlDiag.getXMLDocument();
			String tmp = filename.substring(0,filename.length()-7) + "types";
			TRACE("UML Types XML Filename:"+tmp,4);
			if (!umlDiagTypes.openXMLDataFile(tmp,"umltypes",false)) {
				supportFunctions.displayMessageDialog(null,"Failed to open XML Types");
				rootTypes = null;
				docTypes = null;
				return false;
			}
			rootTypes = umlDiagTypes.getRootElement();
			docTypes = umlDiagTypes.getXMLDocument();
			tmp = filename.substring(0,filename.length()-7) + "notes";
			TRACE("UML Notes XML Filename:"+tmp,4);
			if (!umlDiagNotes.openXMLDataFile(tmp,"umlnotes",true)) { // force create if data file does not exist
				supportFunctions.displayMessageDialog(null,"Failed to open XML Notes");
				rootNotes = null;
				docNotes = null;
				return false;
			}
			rootNotes = umlDiagNotes.getRootElement();
			docNotes = umlDiagNotes.getXMLDocument();
			tmp = filename.substring(0,filename.length()-7) + "custom";
			TRACE("UML Custom XML Filename:"+tmp,4);
			if (!umlDiagCustom.openXMLDataFile(tmp,"umlcustom",true)) { // force create if data file does not exist
				supportFunctions.displayMessageDialog(null,"Failed to open XML Custom");
				rootCustom = null;
				docCustom = null;
				return false;
			}
			rootCustom = umlDiagCustom.getRootElement();
			docCustom = umlDiagCustom.getXMLDocument();
			tmp = filename.substring(0,filename.length()-7) + "usecase";
			TRACE("Use Case XML Filename:"+tmp,4);
			if (!umlDiagUseCase.openXMLDataFile(tmp,"umlusecase",true)) { // force create if data file does not exist
				supportFunctions.displayMessageDialog(null,"Failed to open XML Use Case");
				rootUseCase = null;
				docUseCase = null;
				return false;
			}
			rootUseCase = umlDiagUseCase.getRootElement();
			docUseCase = umlDiagUseCase.getXMLDocument();
			tmp = filename.substring(0,filename.length()-7) + "sequence";
			TRACE("Sequence XML Filename:"+tmp,4);
			if (!umlDiagSequence.openXMLDataFile(tmp,"umlsequence",true)) { // force create if data file does not exist
				supportFunctions.displayMessageDialog(null,"Failed to open XML Sequence");
				rootSequence = null;
				docSequence = null;
				return false;
			}
			rootSequence = umlDiagSequence.getRootElement();
			docSequence = umlDiagSequence.getXMLDocument();
			tmp = filename.substring(0,filename.length()-7) + "state";
			TRACE("State XML Filename:"+tmp,4);
			if (!umlDiagState.openXMLDataFile(tmp,"umlstate",true)) { // force create if data file does not exist
				supportFunctions.displayMessageDialog(null,"Failed to open XML State");
				rootState = null;
				docState = null;
				return false;
			}
			rootState = umlDiagState.getRootElement();
			docState = umlDiagState.getXMLDocument();

			return true;
		}
		protected void finalize() throws Throwable {
			if (umlDiag != null) {umlDiag.closeXMLDataFile();}
			if (umlDiagTypes != null) {umlDiagTypes.closeXMLDataFile();}
			if (umlDiagNotes != null) {umlDiagNotes.closeXMLDataFile();}
			if (umlDiagCustom != null) {umlDiagCustom.closeXMLDataFile();}
			if (umlDiagConnectors != null) {umlDiagConnectors.closeXMLDataFile();}
			if (umlDiagUseCase != null) {umlDiagUseCase.closeXMLDataFile();}
			if (umlDiagSequence != null) {umlDiagSequence.closeXMLDataFile();}
			if (umlDiagState != null) {umlDiagState.closeXMLDataFile();}
			if (UMLCodeMetrics != null) {UMLCodeMetrics.closeConfigurationSettings();}
			super.finalize();
		}
		public Vector getClassNames() {
			Vector v = new Vector();
			Vector drawingItems = getDrawingCanvas().getDrawingItemsOfType(dcTypeUML);
			for (int i=0;i<drawingItems.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)drawingItems.elementAt(i);
				v.addElement(d.getData().getClassName());
			}
			
			return v;
		}
	}

	public boolean cmdLineParamPresent(String name) {
		if (commandLineArgs.get(name) != null) {
			return true;
		}
		return false;

	}
	public String cmdLineParamValue(String name,String defaultValue) {
		String value = (String)commandLineArgs.get(name);
		if (value != null) {
			return value;
		}

		return defaultValue;
	} 

	public class UMLCustomIDEditor extends JDialog implements ActionListener,ItemListener {
		private JButton			butSave,butCancel;
		private List			listScope,listSubscope,listType;
		
		public UMLCustomIDEditor(Frame parent) {
			super(parent,"UML Custom ID Editor",true);
			init(parent);
		}
		public void init(Frame parent) {
			butSave = new JButton("Set ID");
			butCancel = new JButton("Cancel");
			butSave.addActionListener(this);
			butCancel.addActionListener(this);
			setLayout(new BorderLayout());
			JPanel butPanel = new JPanel();
			JPanel editPanel = new JPanel();
			butPanel.add(butSave);
			butPanel.add(butCancel);
			listScope = new List(5);
			listSubscope = new List(5);
			listType = new List(5);
			editPanel.add(listScope);
			editPanel.add(listSubscope);
			editPanel.add(listType);
			
			Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeUML);
			for (int i=0;i<v.size();i++) {
				UMLDrawingItem d = (UMLDrawingItem)v.elementAt(i);
				listScope.add(d.getData().getClassName());
			}
			for (symCustom st : symCustom.values()) {
				if (st == symCustom.NONE) {continue;}
				listType.add(st.getDescription());
			}
			listScope.select(0);
			listType.select(0);			
			fillInSubscopeList(listScope.getSelectedItem());			
			listScope.addItemListener(this);
			
			add(editPanel,BorderLayout.CENTER);
			add(butPanel,BorderLayout.SOUTH);
			
			pack();
			setVisible(true);
		}
		public void fillInSubscopeList(String className) {
			listSubscope.removeAll();
			
			UMLDrawingItem d = (UMLDrawingItem)getDrawingCanvas().getDIWithUserDefinedName(className);
			if (d == null) {return;}
			
			listSubscope.add(className);
			String[] tmp = d.getData().getRawClassVariableStrings();
			for (int i=0;i<tmp.length;i++) {
				listSubscope.add(tmp[i]);
			}
			tmp = d.getData().getRawLinkVariableStrings(className);
			for (int i=0;i<tmp.length;i++) {
				listSubscope.add(tmp[i]);
			}
			
			listSubscope.select(0);
		}
		public void destroy() {
			dispose();
	    }
		public boolean isValidUMLCustomTextID(String id) {
			boolean bFunction = false;
			boolean bMulti = false;
			
			TRACE("isValidUMLCustomTextID:id:"+id,4);
			Vector v = supportFunctions.splitIntoTokens(id,":");
			String tmp = (String)v.elementAt(2);
			if (tmp.equals("multiplicity")) {bMulti = true;}
			tmp = (String)v.elementAt(1);
			if (tmp.startsWith("link")) {bFunction = true;}
			
			return !(bFunction ^ bMulti);
		}
	
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == butSave) {
				String umlID = listScope.getSelectedItem() + ":" + listSubscope.getSelectedItem() + ":" + listType.getSelectedItem().toLowerCase();
				if (listType.getSelectedItem().toLowerCase().equals("ocl")) {
					expressionBuilder dlg = new expressionBuilder(parentFrame,"OCL Expression",
							dataRelativePath+"/"+appDirectory+"/"+"oclsyntax.dat");
					if (dlg.isOK()) {
						umlDiagram.setUMLCustomText(umlID,dlg.getExpressionAsString());
					}
				} else {					
					if (isValidUMLCustomTextID(umlID)) {
						UMLCustomEditor d = new UMLCustomEditor(supportFunctions.getTopLevelParent(this),symCustomFromString(listType.getSelectedItem()),umlID);
					} else {
						supportFunctions.displayMessageDialog(null,"UML Custom Text ID Not Supported");
					}
				}
				
				return;
			}
			
			dispose();
		}
		public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == evt.SELECTED) {
				List source = (List)evt.getSource();
				String item = source.getSelectedItem();
				fillInSubscopeList(item);
			}
		}
	}
	public class UMLCustomEditor extends JDialog implements ActionListener,TextListener {
		private JButton			butSave,butCancel;
		private	TextArea		text;
		private int				numCols = 20;
		private	int				numRows = 45;
		private	symCustom		type;
		private	boolean			bIsOK = false;
		private	String 			id = "";
		
		public UMLCustomEditor(Frame parent,symCustom umlType,String umlId) {
			super(parent,"UML Custom Editor",true);
			type = umlType;
			id = umlId;
			init(parent,numCols,numRows);
		}
		public void init(Frame parent,int cols,int rows) {
			butSave = new JButton("OK");
			butCancel = new JButton("Cancel");
			butSave.addActionListener(this);
			butCancel.addActionListener(this);
			butSave.setEnabled(false);
			setLayout(new BorderLayout());
			JPanel butPanel = new JPanel();
			JPanel editPanel = new JPanel();
			text = new TextArea(umlDiagram.getUMLCustomText(id),cols,rows);
			text.addTextListener(this);
			editPanel.add(text);
			butPanel.add(butSave);
			butPanel.add(butCancel);

			add(editPanel,BorderLayout.CENTER);
			add(butPanel,BorderLayout.SOUTH);
			
			setTitle("UML Custom Editor - " + type.getDescription());
			
			pack();
			setVisible(true);
		}
		public String getCustomText() {return text.getText();}
		public boolean isOK() {return bIsOK;}
		public void destroy() {
			dispose();
	    }
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == butSave) {
				bIsOK = true;
				umlDiagram.setUMLCustomText(id,text.getText());
			}
			
			dispose();
		}
		public void textValueChanged(TextEvent e) {
			butSave.setEnabled(type.validate(text.getText()));
		}
	}
		
	public class mainCard extends Panel  implements drawingCanvasUtils,ActionListener,statusCanvasDialogListener,ItemListener {
		private JButton 					openSourceBut,openDiagramBut,printDiagramBut,umlCustomTextBut,cycleBut,dataDictBut,saveAsJPGBut,supportBut;
		private JTextField 					diagramFilenameTF,diagramSheetTF;
		private UMLCompiler 				compilier;
		private modelessStatusDialog 		msgD;
		private Choice						umlDiagTypes;

  	    public void itemStateChanged(ItemEvent evt) {
  	    	String sdt = umlDiagTypes.getSelectedItem();
  	    	for (UMLDiagramType dt : UMLDiagramType.values()) {
  	    		if (sdt.equals(dt.getDescription())) {
  	    			umlDiagram.setDiagramType(dt);
  	    		}
  	    	}
  	    	if (!umlDiagram.isDiagramLoaded()) {
				int resp = JOptionPane.showConfirmDialog(null,"No Diagram Loaded, create Default Diagram",systemUserReg.getAppName(),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (resp == JOptionPane.YES_OPTION) {
					umlDiagram.createDefaultSourceFile();
					mainTab.openSource1(dataRelativePath+"/"+appDirectory+"/testdefault.java");
				}
  	    	}
  	    	updateDiagramSheetText();
  	    	getDrawingCanvas().repaint();
		}
		public boolean canvasDoubleClickAction(MouseEvent evt) {
			for (int i=0;i<umlDiagram.UMLConnectors.size();i++) {
				UMLBaseConnector conn = (UMLBaseConnector)umlDiagram.UMLConnectors.elementAt(i);
				if (conn.hitTest(getDrawingCanvas().getGraphics(),evt.getX(),evt.getY()) == true) {
					umlDiagram.UMLConnectorDoubleClicked(conn);
					return true;
				}
			}
			
			return false;
		}
		public boolean canvasLeftClickAction(MouseEvent evt) {return false;}
		public void setCompiler(UMLCompiler c) {compilier = c;}
		public UMLCompiler getCompiler() {return compilier;}
		mainCard() {
			// for an application (no GUI) we do not want to init the GUI interface but still want access to the mainCard class functions
			if (!ehsConstants.bRunAppWithGUI) {return;}
		
			umlDiagram = new umlDiagramStruct(); // must be first thing created BEFORE scrollabecanvas
			TRACE("init umlDiagram!!!",4);
			msgD = null;
			
			umlDiagTypes = new Choice();
			for (UMLDiagramType dt : UMLDiagramType.values()) {
				umlDiagTypes.addItem(dt.getDescription());
			}
			umlDiagTypes.select(0);
			umlDiagTypes.addItemListener(this);
			
			diagramFilenameTF = new JTextField("",50);
			diagramFilenameTF.setEditable(false);
			diagramSheetTF = new JTextField("Sheet 1 of 1",9);
			diagramSheetTF.setEditable(false);

			openSourceBut = new JButton("Open Source");
			openSourceBut.addActionListener(this);
			openSourceBut.setPreferredSize(new Dimension(12*charWidth,charHeight));
			openSourceBut.setToolTipText("Open Source");
			openDiagramBut = new JButton("Open Diagram");
			openDiagramBut.addActionListener(this);
			openDiagramBut.setPreferredSize(new Dimension(12*charWidth,charHeight));
			openDiagramBut.setToolTipText("Open Diagram");
			printDiagramBut = new JButton("Print");
			printDiagramBut.addActionListener(this);
			printDiagramBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			printDiagramBut.setToolTipText("Print Diagram");
			cycleBut = new JButton("Cycle");
			cycleBut.addActionListener(this);
			cycleBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			cycleBut.setToolTipText("Cycle Sheets");
			umlCustomTextBut = new JButton("Custom");
			umlCustomTextBut.addActionListener(this);
			umlCustomTextBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			umlCustomTextBut.setToolTipText("Custom UML Text");
			dataDictBut = new JButton("Symbols");
			dataDictBut.addActionListener(this);
			dataDictBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			dataDictBut.setToolTipText("Data Dictionary");
			saveAsJPGBut = new JButton("JPG Export");
			saveAsJPGBut.addActionListener(this);
			saveAsJPGBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			saveAsJPGBut.setToolTipText("JPG Export");
			supportBut = new JButton("Support");
			supportBut.addActionListener(this);
			supportBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			supportBut.setToolTipText("Support");
			
			JPanel butPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			butPanel.add(openSourceBut);
			butPanel.add(openDiagramBut);
			butPanel.add(umlCustomTextBut);
			butPanel.add(cycleBut);
			butPanel.add(printDiagramBut);
			butPanel.add(saveAsJPGBut);
			butPanel.add(dataDictBut);
			butPanel.add(supportBut);
			butPanel.add(new JLabel("Hover over class for any notes"));

			JPanel topPanel = new JPanel();
			JLabel lab = new JLabel("<html><font size='+2' color='red'>"+systemUserReg.getAppName()+"</font><p><font size='-2'>"+systemUserReg.getAppCopyright()+"</font></html>");
			Font f = lab.getFont();
			lab.setFont(f.deriveFont((float)f.getSize() + 3));
			topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
			topPanel.add(lab);

			JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			infoPanel.add(diagramFilenameTF);
			infoPanel.add(diagramSheetTF);
			infoPanel.add(umlDiagTypes);

			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			p.add(topPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			p.add(butPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			p.add(infoPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			
			sDC = new scrollableDrawingCanvas(supportFunctions.getTmpFilename("untitled"),ehsConstants.dcMaxX,ehsConstants.dcMaxY,20,20,false);
			getDrawingCanvas().hideToolWindows();
			getDrawingCanvas().setUseDatabase(false);
			getDrawingCanvas().setReadOnly(false);
			getDrawingCanvas().addDrawingCanvasListener(this);
			getDrawingCanvas().setDrawFocusHandles(false);
			getDrawingCanvas().setSubEntity("subentity");
			ScrollPane t = sDC.getComponent();
			t.setSize(charWidth*visibleDCWidthChars,charHeight*visibleDCHeightChars);
			p.add(t);
			
			add(p,"West");

			cycleBut.setEnabled(false);
			printDiagramBut.setEnabled(false);
			umlCustomTextBut.setEnabled(false);
			dataDictBut.setEnabled(false);
			saveAsJPGBut.setEnabled(false);
			
			createStatusPanels();
		}
		public void updateMainCard() {
		}
		String[] statusPanelNames = {"a","b"};
        public void createStatusPanels() {
		   //statusCanvas statusPanel = new statusCanvas("Drawing Properties");
		   Vector props = new Vector();
		   
		   //props.addElement(new statusCanvasProp("Name",dcEntity));
		   //props.addElement(new statusCanvasProp("SubName",dcSubEntity));
		   //props.addElement(new statusCanvasProp("BackColor",supportFunctions.getColorName(backgroundColor)));
		   //props.addElement(new statusCanvasProp("Width",String.valueOf(dcStrokeWidth)));
		   //props.addElement(new statusCanvasProp("XCord",String.valueOf(xCord)));
		   //props.addElement(new statusCanvasProp("YCord",String.valueOf(yCord)));
		   //props.addElement(new statusCanvasProp("Layer","Layer " + String.valueOf(dcLayer),layerMan.getLayerNames()));
		   //props.addElement(new statusCanvasProp("ForeColor",supportFunctions.getColorName(dcColor)));
		   //props.addElement(new statusCanvasProp("Mode",optLabs[dcMode]));
		   //String tmp = "False";
		   //if (dcFilled) {tmp = "True";}
		   //props.addElement(new statusCanvasProp("Filled",tmp));
		   //statusPanel.setProps(props);
		   //statusPanel.statusCanvasShow();
		   //statusPanel.getDialog().addStatusCanvasDialogListener(this);
		   //statusPanel.setEnabledPropByName("Name",false);
		   //statusPanel.statusCanvasHide();
			
		}
		public void propTableUpdated(String title,String propName,String propNewValue) {
//				dcStrokeWidth = Integer.parseInt(statusPanel.getPropByName("Width"));
		}
		public void customDoCommand(String cmd,String params,drawingItem d) {
		  if (cmd.equalsIgnoreCase("Add UML Class")) {
				supportFunctions.displayMessageDialog(null,"Add UML Class:NYI");
		  }
		  if (cmd.equalsIgnoreCase("Add UML Connector")) {
				supportFunctions.displayMessageDialog(null,"Add UML Connector:NYI");
		  }
		  if (cmd.equalsIgnoreCase("Generate Code")) {
			  umlDiagram.generateCode(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
		  }
		  if (cmd.equalsIgnoreCase("Add Actor")) {
			  umlDiagram.addActor();
		  }
		  if (cmd.equalsIgnoreCase("Add Use Case")) {
			  umlDiagram.addUseCase();
		  }
		  if (cmd.equalsIgnoreCase("Diagram Properties...")) {
			  umlDiagram.diagramProperties();
		  	}
		}
		public void rightClickAction(Vector v,MouseEvent evt) {
			// only work if we have one UML DI selected
			if (v.size() > 1) {return;}
			// remove any UML class note window
			panelDialog m3d = getUMLInfoWindow();
			if (m3d != null) {
				m3d.destory();
				m3d.dispose();
				setUMLInfoWindow((panelDialog)null);
			}
			drawingItem d = (drawingItem)v.elementAt(0);
			Point pt = supportFunctions.centerPoint(d.getBoundingRect()); 

			if (umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {	
				if (!(d instanceof UMLDrawingItem)) {return;}
				setGlobalDrawingItem((drawingItem)d);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete").setEnabled(false);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete Class Note").setEnabled(((UMLDrawingItem)getGlobalDrawingItem()).getData().hasClassNotes());
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Add Class Note").setEnabled(true);				
			}
			if (umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {				
				if (!(d instanceof UMLUseCaseDrawingItem)) {return;}
				setGlobalDrawingItem((drawingItem)d);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete").setEnabled(true);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete Class Note").setEnabled(false);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Add Class Note").setEnabled(false);				
			}
			if (umlDiagram.getDiagramType() == UMLDiagramType.SEQUENCE) {				
				if (!(d instanceof UMLSequenceDrawingItem)) {return;}
				setGlobalDrawingItem((drawingItem)d);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete").setEnabled(true);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete Class Note").setEnabled(false);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Add Class Note").setEnabled(false);				
			}
			if (umlDiagram.getDiagramType() == UMLDiagramType.STATE) {				
				if (!(d instanceof UMLStateDrawingItem)) {return;}
				setGlobalDrawingItem((drawingItem)d);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete").setEnabled(true);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Delete Class Note").setEnabled(false);
				supportFunctions.getMenuItem(umlDiagram.getMenuUMLDI(),"Add Class Note").setEnabled(false);				
			}
			umlDiagram.getMenuUMLDI().show(sDC.getDC(),pt.x,pt.y);
		}
		public void leftClickAction(drawingItem d,MouseEvent evt) {;}
		public void leftClickSelectedAction(Vector v,MouseEvent evt) {;}
		public void hoverAction(drawingItem d,MouseEvent evt) {
			if (umlDiagram.getDiagramType() == UMLDiagramType.CLASS || umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {
				if (d != null) {
					panelDialog m3d = getUMLInfoWindow();
					if (d instanceof UMLDrawingItem && umlDiagram.getDiagramType() == UMLDiagramType.CLASS) {
						UMLDrawingItem dd = (UMLDrawingItem)d;
						if (m3d == null) {
							if (dd.getData().hasClassNotes()) {
								TRACE("hover class notes:"+dd.getData().getClassNotes(),4);
								setGlobalDrawingItem((drawingItem)dd);
								JPanel p = new JPanel();
								JTextArea tf = new JTextArea(dd.getData().getClassNotes(),(dd.getData().getClassNotes().length() / iNumberCharacetrsInClassNotePopupWindowRow) + 1,iNumberCharacetrsInClassNotePopupWindowRow);
								tf.setBackground(new Color(239,237,235));
								tf.setWrapStyleWord(true);
								tf.setEditable(false);
								p.add(tf);						
								setUMLInfoWindow(supportFunctions.displayPanelDialog(null,p,systemUserReg.getAppName()));
								return;
							} else {
								TRACE("No class notes",4);
							}
						}
					}
					if (d instanceof UMLUseCaseDrawingItem && umlDiagram.getDiagramType() == UMLDiagramType.USECASE) {
						UMLUseCaseDrawingItem dd = (UMLUseCaseDrawingItem)d;
						if (m3d == null) {
							setGlobalDrawingItem((drawingItem)dd);
							JPanel p = new JPanel();
							JTextArea tf = new JTextArea(dd.getUseCaseDescription(),(dd.getUseCaseDescription().length() / iNumberCharacetrsInClassNotePopupWindowRow) + 1,iNumberCharacetrsInClassNotePopupWindowRow);
							tf.setBackground(new Color(239,237,235));
							tf.setWrapStyleWord(true);
							tf.setEditable(false);
							p.add(tf);						
							setUMLInfoWindow(supportFunctions.displayPanelDialog(null,p,systemUserReg.getAppName()));
							return;	
						}
					}
				}
				
				panelDialog m3d = getUMLInfoWindow();
				if (m3d != null) {
					m3d.destory();
					m3d.dispose();
					setUMLInfoWindow((panelDialog)null);
				}
			}
		}
		public void doubleClickAction(drawingItem d,MouseEvent evt) {
			d.editor();
		}
		public void focusHandleHit(drawingItem d) {;}
		public void destroyMainCard() {
			getDrawingCanvas().hideToolWindows(); // this also saves the current positions of the tool windows
		}
      	public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == openSourceBut) {openSource();}
			if (evt.getSource() == openDiagramBut) {openDiagram();}
			if (evt.getSource() == printDiagramBut) {printDiagram();}
			if (evt.getSource() == cycleBut) {cycleDiagramSheets();}
			if (evt.getSource() == supportBut) {
				try {
					URL u = new URL("https://ehsphpapps.herokuapp.com/applications/devtrack");
					miniWebBrowser tmp = new miniWebBrowser(u);
				} catch(Exception e) {;}
			}
			if (evt.getSource() == dataDictBut) {compilier.getSymbolTable().createSymbolDialog("Data Dictionary","Data Dictionary");}
			if (evt.getSource() == saveAsJPGBut) {getDrawingCanvas().saveAsJPG(getDrawingCanvas().getDCBoundingRect(), "");}
			if (evt.getSource() == umlCustomTextBut) {umlCustomText();}
		}
		public void umlCustomText() {
			UMLCustomIDEditor d = new UMLCustomIDEditor(supportFunctions.getTopLevelParent(this));
		}
		public void cycleDiagramSheets() {
			int sheet = umlDiagram.getDiagramType().getCurrentSheet();
			if (++sheet > umlDiagram.getDiagramType().getMaxSheet()) {sheet = 1;}
			umlDiagram.getDiagramType().setCurrentSheet(sheet);
			updateDiagramSheetText();
		}
		public void updateDiagramSheetText() {			
			diagramSheetTF.setText("Sheet " + String.valueOf(umlDiagram.getDiagramType().getCurrentSheet()) + " of " + String.valueOf(umlDiagram.getDiagramType().getMaxSheet()));
			if (umlDiagram.getDiagramType().getMaxSheet() == 1) {cycleBut.setEnabled(false);} else {cycleBut.setEnabled(true);}
		}
		public void openDiagram() {			
			String filename = controlsFunctions.fileOpenDialog("","*.xml");
			if (filename == null) {return;}

			umlDiagram.setNewCompile(false);
			diagramFilenameTF.setText(filename);

			msgD = supportFunctions.displayModelessStatusDialog(systemUserReg.getAppName());
			generateUMLDiagram(filename);
		}
		public void generateUMLDiagram(String filename) {			
			msgD.setText("Generating UML Diagram");

			filename = dataRelativePath+"/"+appDirectory+"/output"+"/" + supportFunctions.getFilenameNoExt(supportFunctions.getFilename(filename));
			umlDiagram.setFilename(filename);
			if (!umlDiagram.loadAsXML()) {return;}
			getDrawingCanvas().clearDrawingCanvas(); // GDB 130814
			getDrawingCanvas().setEntity(filename);
			//getDrawingCanvas().deleteDCFromDB(filename);
			cycleBut.setEnabled(true);
			printDiagramBut.setEnabled(true);
			umlCustomTextBut.setEnabled(true);
			dataDictBut.setEnabled(true);
			saveAsJPGBut.setEnabled(true);
			umlDiagram.setModified(false);
			drawUMLDiagram();
		}
		public void drawUMLDiagram() {
			msgD.setText("Drawing UML Diagram");
			getDrawingCanvas().setUpdate(false);
			processGlobalData();

			NodeList n = umlDiagram.getClassList();
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				UMLDrawingItem d = createUMLDrawingItem(e);
				if (e.getAttribute("enum").length() != 0) { // check for JAVA's extended enum type
					d.getData().setEnum(true);
				}
				d.getData().setScope(e.getAttribute("scope"));
			}
			n = umlDiagram.getInterfaceList();
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				UMLDrawingItem d = createUMLDrawingItem(e);
				d.getData().setInterface(true);
			}
			n = umlDiagram.getEnumList();
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				UMLDrawingItem d = createUMLDrawingItem(e);
				d.getData().setEnum(true);
			}

			umlDiagram.getDiagramType().reset();

			umlDiagram.setDiagramType(UMLDiagramType.CLASS);
			msgD.setText("Processing Level Data");
			umlDiagram.processLevelData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			msgD.setText("Processing Coordinate Data A");
			umlDiagram.processMaxWidthHeightData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			if (umlDiagram.processCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML)) == true) {
				msgD.setText("Spliting Into Sheets");
				umlDiagram.splitIntoSheets(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			}
			msgD.setText("Compacting UML Class Diagram");
			umlDiagram.compactUMLClassDiagram(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			msgD.setText("Processing UML Class Notes");
			umlDiagram.processClassNotes(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			msgD.setText("Processing UML Connectors");
			umlDiagram.processUMLConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));
			msgD.setText("Processing Packages");
			umlDiagram.processPackages(getDrawingCanvas().getDrawingItemsOfType(dcTypeUML));

			umlDiagram.setDiagramType(UMLDiagramType.SEQUENCE);
			msgD.setText("Processing Sequence DIs");
			umlDiagram.generateSequenceXMLData();
			umlDiagram.processSequenceDrawingItems();
			msgD.setText("Processing Sequence Coordinate Data");
			umlDiagram.processSequenceCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence));
			msgD.setText("Processing UML Sequence Connectors");
			umlDiagram.processSequenceConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLSequence));
			
			umlDiagram.setDiagramType(UMLDiagramType.USECASE);
			msgD.setText("Processing Use Case DIs");
			umlDiagram.processUseCaseDrawingItems();
			msgD.setText("Processing Use Case Coordinate Data");
			umlDiagram.processUseCaseCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));
			msgD.setText("Processing UML Use Case Connectors");
			umlDiagram.processUseCaseConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLUseCase));

			umlDiagram.setDiagramType(UMLDiagramType.STATE);
			msgD.setText("Processing State DIs");
			umlDiagram.generateStateXMLData();
			umlDiagram.processStateDrawingItems();
			msgD.setText("Processing State Coordinate Data");
			umlDiagram.processStateCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState));
			msgD.setText("Processing UML State Connectors");
			umlDiagram.processStateConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeUMLState));

			umlDiagram.setDiagramType(UMLDiagramType.CLASS); // set default diagram type
			updateDiagramSheetText();
			
			//supportFunctions.displayMessageDialog(null,"Generation Complete");
			msgD.setText("Creating Data Dictionary");
			if (ehsConstants.bRunAppWithGUI) {compilier.getSymbolTable().createSymbolDialog("Data Dictionary","Data Dictionary");}
			msgD.setText("Creating Calling Tree");
			umlDiagram.getUMLCallingTree().displayCallingTree();

			getDrawingCanvas().setUpdate(true);
			
			msgD.destory();
			msgD.dispose();
		}
		public String getAccessSymbol(String mode) {
			mode = mode.toLowerCase();
			//if(mode.equals("private")) {return "-";}
			//if(mode.equals("public")) {return "+";}
			//if(mode.equals("protected")) {return "#";}
			
			return mode;
		}
		public String stringOrDash(String s) {
			if (s.length() == 0) {return "-";}
			return s;
		}
		public UMLDrawingItem createUMLDrawingItem(org.w3c.dom.Element e) {
			TRACE("createUMLDrawingItem:entered",4);
			NodeList functionsNL = e.getElementsByTagName("function");
			NodeList variablesNL = e.getElementsByTagName("variable");
			
			String templatetype = e.getAttribute("type");

			int x = 0;
			int y = 0;
			String exaccess = e.getAttribute("exaccess");
			TRACE("createUMLDrawingItem:exaccess:"+exaccess,4);
			String p1 = e.getAttribute("name") + "::" + stringOrDash(e.getAttribute("extends")) + "::" + stringOrDash(e.getAttribute("implements")) + "::" + stringOrDash(exaccess);				
			String p2 = "";
			String p3 = "";			
			String p4 = "-"; // optional class notes
			
			for (int i=0;i<functionsNL.getLength();i++) {
				org.w3c.dom.Element e1 = (org.w3c.dom.Element)functionsNL.item(i);
				//TRACE("\nFGEBTN:"+e1.getAttribute("name"),4);
				if (p3.length() !=0 ) {p3 = p3 + "::";}
				exaccess = e1.getAttribute("exaccess");
				p3 = p3 + getAccessSymbol(e1.getAttribute("access")) + ":" + e1.getAttribute("returntype") + ":" + e1.getAttribute("name") + ":" + stringOrDash(e1.getAttribute("parameters")) + ":" + stringOrDash(exaccess);
			}
			for (int i=0;i<variablesNL.getLength();i++) {
				org.w3c.dom.Element e2 = (org.w3c.dom.Element)variablesNL.item(i);
				//TRACE("\nVGEBTN:"+e2.getAttribute("name"),4);
				if (p2.length() !=0 ) {p2 = p2 + "::";}
				exaccess = e2.getAttribute("exaccess");
				String classtype = e2.getAttribute("class");
				p2 = p2 + getAccessSymbol(e2.getAttribute("access")) + ":" + e2.getAttribute("type") + ":" + e2.getAttribute("name") + ":" + stringOrDash(e2.getAttribute("defaultvalue")) + ":" + stringOrDash(exaccess) + ":" + stringOrDash(classtype);
			}			
			
			p2 = stringOrDash(p2); 
			p3 = stringOrDash(p3); 
			
			TRACE("createUMLDrawingItem:P1="+p1+"\ncreateUMLDrawingItem:P2="+p2+"\ncreateUMLDrawingItem:P3="+p3+"\ncreateUMLDrawingItem:P4="+p4,4);
			UMLDrawingItem d = (UMLDrawingItem)getDrawingCanvas().addDrawingItem(getDrawingCanvas().getEntity(),dcTypeUML,x,y,p1,p2,p3,p4,false,Color.black);

			if (templatetype.length() != 0) {
				d.setTemplateType(templatetype);
			}			

			return (UMLDrawingItem)d;
		}
		public void processGlobalData() {
			boolean bGotGlobalData = false;
			int x = 0;
			int y = 0;
			String p1 = globalDataClass + "::-::-::-";				
			String p2 = "";
			String p3 = "";			
			String p4 = "-"; // optional class notes
			String exaccess = "";
			NodeList n = supportFunctions.executeXPathExpr(umlDiagram.getDiagDocument(),"/umldata/variable");
			TRACE("Global variable list length="+String.valueOf(n.getLength()),4);
			for (int i=0;i<n.getLength();i++) {
				bGotGlobalData = true;
				org.w3c.dom.Element e2 = (org.w3c.dom.Element)n.item(i);
				exaccess = e2.getAttribute("exaccess");
				if (p2.length() !=0 ) {p2 = p2 + "::";}
				p2 = p2 + getAccessSymbol(e2.getAttribute("access")) + ":" + e2.getAttribute("type") + ":" + e2.getAttribute("name") + ":" + stringOrDash(e2.getAttribute("defaultvalue")) + ":" + stringOrDash(exaccess);
			}

			NodeList n1 = supportFunctions.executeXPathExpr(umlDiagram.getDiagDocument(),"/umldata/function");
			TRACE("Global function list length="+String.valueOf(n1.getLength()),4);
			for (int i=0;i<n1.getLength();i++) {
				bGotGlobalData = true;
				org.w3c.dom.Element e1 = (org.w3c.dom.Element)n1.item(i);
				exaccess = e1.getAttribute("exaccess");
				if (p3.length() !=0 ) {p3 = p3 + "::";}
				p3 = p3 + getAccessSymbol(e1.getAttribute("access")) + ":" + e1.getAttribute("returntype") + ":" + e1.getAttribute("name") + ":" + stringOrDash(e1.getAttribute("parameters")) + ":" + stringOrDash(exaccess);
			}

			p2 = stringOrDash(p2); 
			p3 = stringOrDash(p3); 

			if (bGotGlobalData) {
				TRACE("\nP1="+p1+"\nP2="+p2+"\nP3="+p3+"\nP4="+p4,4);
				drawingItem d = getDrawingCanvas().addDrawingItem(getDrawingCanvas().getEntity(),dcTypeUML,x,y,p1,p2,p3,p4,false,Color.black);
			}
		}
		public void printDiagram() {
			 getDrawingCanvas().printCanvas();
		}
		public UMLCompiler getLanguageTransTable(String fileExt) {
			TRACE("Source file extension:" + fileExt,4);
			compilier = null;
			umlDiagram.setFileExt(fileExt);
			String s = "";
			try {
				basicFile f = new basicFile(""+dataRelativePath+"/" + appDirectory + "/" + transtableMappingFilename);
				s = f.loadFile();
			} catch(Exception e) {e.printStackTrace();}	
			Vector v = supportFunctions.splitIntoTokens(s,",");
			// tokens in groups of four : file ext,trans table filename,reserved words (separated by a :),built in types (separated by a :)
			for (int i=0;i<v.size();i=i+4) {
				//TRACE("File ext:"+(String)v.elementAt(i)+" Trans table name:"+(String)v.elementAt(i+1)+" Reserved words:"+(String)v.elementAt(i+2)+" Built in types:"+(String)v.elementAt(i+3),4);
				if (fileExt.equals((String)v.elementAt(i))) {
					//System.out.println("File ext:"+(String)v.elementAt(i)+" Trans table name:"+(String)v.elementAt(i+1)+" Reserved words:"+(String)v.elementAt(i+2)+" Built in types:"+(String)v.elementAt(i+3)+"\n");
					UMLTypes = (String)v.elementAt(i+3);
					UMLTypes = UMLTypes.replaceAll(":",","); // UMLTypes is init in 'compiler' derived class constructor
					String resWords = (String)v.elementAt(i+2);
					resWords = resWords.replaceAll(":",",");					
					//compilier = new UMLCompiler(baseUMLTransTableName + (String)v.elementAt(i+1),resWords);
					return new UMLCompiler(baseUMLTransTableName + (String)v.elementAt(i+1),resWords);
				}
			}
			return (UMLCompiler)null;
		}
		public void openSource() {
			String sourceFilename = controlsFunctions.fileOpenDialog("","*.*");
			if (sourceFilename == null) {return;}
			openSource1(sourceFilename);
		}
		public void openSource1(String sourceFilename) {
			if (supportFunctions.getFilenameExt(sourceFilename).equals("xml")) {
				String[] files = getFileSet(sourceFilename);
				if (files.length < 1) {return;}
				sourceFilename = files[0];
				for (int i=0;i<files.length;i++) {
					umlDiagram.addExtraFile(files[i]);
				}
			}
			
			compilier = getLanguageTransTable(supportFunctions.getFilenameExt(sourceFilename));
			if (compilier == null) {
				supportFunctions.displayMessageDialog(null,"No compiler defined for extension " + supportFunctions.getFilenameExt(sourceFilename));
				umlDiagram.setFilename("untitled");
				return;
			}
			
			msgD = supportFunctions.displayModelessStatusDialog(systemUserReg.getAppName());
			msgD.setText("Compiling UML Diagram");
			
			diagramFilenameTF.setText(sourceFilename);
			
			if (compilier.compile(sourceFilename)) 
			{		
				//supportFunctions.displayMessageDialog(null,"Compile Complete");
				umlDiagram.setNewCompile(true);
				// account for the extra _umldiag
				String tmp = supportFunctions.getFilenameNoExt(supportFunctions.getFilename(sourceFilename));
				sourceFilename = tmp + "_umldiag.xml";
				generateUMLDiagram(sourceFilename);
			}
			else {
				// display compile error messages
				umlDiagram.setFilename("untitled");
				String s = compilier.getErrorString();
				supportFunctions.displayMessageDialog(null,s);
			}
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		boolean showCard = true;	
				
		if(e.getSource() == tabPane) {			
			if(tabPane.getSelectedComponent() == mainTab) {
			   mainTab.updateMainCard();
			}			
			if(showCard == false) {
//				tabPane.setSelectedComponent(Tab1);
			}
		}
	}
			
		String appProcessName = "";
		public void startPerApplicationProcess() {
			appProcessName = "EHS-"+"UD-"+supportFunctions.currentShortDate().replace('/', '-')+supportFunctions.currentShortTime().replace(':', '-');
			TRACE("Starting Per Application Processes - " + appProcessName,4);
		}
		public void finishPerApplicationProcess(){
			TRACE("Ending Per Application Processes",4);
		}
				
		public void init() {	
		ehsConstants.bRunAppWithGUI = true;
		TRACE("init() called",4);
		
		setLocation(0,0);
		setSize(ehsConstants.windowXMax,ehsConstants.windowYMax);
		invalidate();
		validate();
		
		supportFunctions.setNativeLookAndFeel();
		contentPane = getContentPane();
		tabPane = new JTabbedPane();

		systemUserReg = new registrationinfo(appDirectory,"UML Work Bench","UML Work Bench (Application)","UD1000","03.00.0000.00","09/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "UML Work Bench";
		supportFunctions.connectDatabase(); 
		supportFunctions.getDBConn().connect();
		//systemUserReg.setPreRelease(true);
		systemUserReg.registerUser(true);
		
		help = new helpAction();
		about = new aboutAction();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(help);
		helpMenu.add(about);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		//if(supportFunctions.getSystemVar(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "menubar",0) == 1) {setJMenuBar(menuBar);}
		
		ac = getAppletContext();
					   
		modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog(systemUserReg.getAppName() + " is Loading...");
		
		msgD.setText("Loading Internationalization Database");
		lStrings = new languageStrings();
		
		msgD.setText("Creating User Panels");

		FontMetrics fm = tabPane.getFontMetrics(tabPane.getFont());
		charHeight = fm.getHeight(); // + fm.getAscent(); remove GDB 09/07/2014
		charWidth = fm.stringWidth("O");
		TRACE("init: charWidth=" + Integer.toString(charWidth) + ",charHeight=" + Integer.toString(charHeight),4);
		
		mainTab = new mainCard();
		TRACE("new mainCard()!!!",4);
		
		contentPane.setLayout(new BorderLayout());
		tabPane.add(systemUserReg.getAppName(),mainTab);
		tabPane.addChangeListener(this);
		contentPane.add(tabPane,"Center");
		
		ciHelp = new customIcon();
		int[] x5 = {6,10,10,6};
		int[] y5 = {15,15,13,13};
		customIconData ciD = new customIconData(x5,y5,Color.black);
		ciHelp.addData(ciD);
		int[] x6 = {7,9,9,10,10,11,10,11,11,10,10,9,9,5,5,4,4,3,3,5,5,6,6,9,9,10,10,9,9,8,8,7,7};
		int[] y6 = {12,12,9,9,7,7,6,6,4,4,3,3,1,1,3,3,4,4,8,8,4,4,3,3,4,4,6,6,7,7,8,8,12};
		ciD = new customIconData(x6,y6,Color.black);
		ciHelp.addData(ciD);
		
		msgD.destory();
		msgD.dispose();

		startPerApplicationProcess();
		
		parentFrame = supportFunctions.getTopLevelParent(this);
		ehsConstants.machineID = supportFunctions.getMachineUniqueIDInternal("../" + appDirectory);
		
		exFAQFile = dataRelativePath + "/knowledgebases/" + productKBFile;
		TRACE("init:Knowledgebase file:"+exFAQFile,4);
		systemUserReg.setFAQFile(exFAQFile);
		
		if (umlDiagram == null) {TRACE("umlDiagram is NULL!!!",4);}
		umlDiagram.setupUseCaseData();	
	}

	public void toggleHelp() {
		   if (helpDlg == null) {
		   	  helpDlg = displayHelpDialog();
		   } else {
		   	 helpDlg.destroy();
			 helpDlg.dispose();
			 helpDlg = null;
		  }
	}
	public class helpDialog extends JDialog {
		   private JTextArea ta;
		   
		   public helpDialog(Frame parent) {
		   		  super(parent,"Help",false);
				  setLayout(new FlowLayout(FlowLayout.CENTER));
				  ta = new JTextArea("",10,30);
				  supportFunctions.displayTextFile(exHelpFile,ta);
				  ta.setEditable(false);
				  add(ta);
	   			  addWindowListener(new WindowAdapter() {
	   			     public void windowClosing(WindowEvent evt) {
					 	helpDlg = null;
	   		            dispose();
	                 }
			      });
				  pack();
				  setVisible(true);
		   }
		   
		   public void destroy() {
		   		  dispose();
		   }
	}
		
	public enum symCustom {
		NONE("None")
		{
			public boolean validate(String s)
			{
				return true;
			}	
		},
		OCL("OCL")
		{
			public boolean validate(String s)
			{
				return true;
			}	
		},
		CONSTRAINT("Constraint")
		{
			public boolean validate(String s)
			{
				return isOneWord(s);
			}	
		},
		STEREOTYPE("Stereotype")
		{
			public boolean validate(String s)
			{
				return isOneWord(s);
			}	
		},
		NAMEVALUEPAIR("NameValuePair")
		{
			public boolean validate(String s)
			{
				Vector v = new Vector();
				StringTokenizer st = new StringTokenizer(s,"=");
				while (st.hasMoreTokens()) {
					v.addElement(st.nextToken());
				}
				if (v.size() != 2) {return false;}
				if (!isOneWord((String)v.elementAt(1))) {return false;}
				return true;				
			}	
		},
		MULTIPLICITY("Multiplicity")
		{
			public boolean validate(String s)
			{
				return true;
			}	
		};
		
		private final String description;
		symCustom(String description) {
			this.description = description;
		}
		public abstract boolean validate(String s);
		public boolean isOneWord(String s) {
			for (int i=0;i<s.length();i++) {
				char ch = s.charAt(i);
				if (!Character.isLetter(ch) && !Character.isDigit(ch)) {return false;}
			}
			
			return true;
		}
		public String getDescription() {return description;}
	}
	public symCustom symCustomFromString(String s) {
		for (symCustom st : symCustom.values()) {
			if (s.equals(st.getDescription())) {return st;}
		}
		return symCustom.NONE;
	}
	public void symbolNodeClicked(String symbolScoe,String symbolSubScope,String symbolName,String symbolValue) {
	}
			 	
 	public class UMLCompilerTokens extends compilerTokens {
 		private	int staticBlockNumber = 0;

		public String preProcessLine(String line,boolean bKeywords) {
			line = basePreProcessLine(line,bKeywords);
			line = processTemplateTypes(line);
			line = processJavaStaticBlocks(line);
			return line;
		}
		public String processJavaStaticBlocks(String line) {			
			if (line.indexOf("static {") != -1) {
				String tmp = mainTab.getCompiler().currentScope();
				line = line.replace("static {",
						"class staticBlock_" + tmp + "_" + String.valueOf(staticBlockNumber++) + " {");
			}
			
			return line;
		}
		public String processTemplateTypes(String line) {
			if (line.indexOf(" class ") != -1) {return line;}
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(line,"<.*?>");
			String[] tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = (String)tokens[i];
				tmp = tmp.replaceAll(" ",""); // remove all spaces from line
				//line = line.replace((String)tokens[i],tmp);
			}

			return line;
		}
	}
	public String processFunctionDefinations(String line) {
		// make sure every function definition (including the constructor)
		// has an access specifier and a return type (default: public void)
		ehsRegExp r = new ehsRegExp();
		if (!r.regExpMatch(line, "\\((.*)\\)\\s*\\{")) {return line;}
		TRACE("processFunctionDefinations (before):"+line,4);
		if (!r.regExpMatch(line, "(public|protected|private|internal)")) {line = "public " + line;}
		r.regExpMatch(line, "(public|protected|private|internal)\\s+(\\w+)\\s*(.*)\\s*\\(");
		String[] tmp = r.getFoundGroupsArray();
		for (int i=0;i<tmp.length;i++) {
			TRACE("processFunctionDefinations:FG:"+tmp[i],4);
		}
		// index 2 because the groups are filled in order with no gaps!!!
		if (tmp[2].length() == 0) {line = line.replaceAll("public","public void ");}
		
		TRACE("processFunctionDefinations (after):"+line,4);
		return line;
	}
	public class UMLCompiler extends compiler {
		private		pseduoFile	compilerFile;
		private		int classDepth =  0;
		private		int compilerTokenIndex = 0,lineTokenIndex = 0;
		private		Stack scopes = new Stack();
		private 	Stack callTreeIDs = new Stack();
		private		String entityName;
		private		boolean	bInternalBlock,bClassBlock,bInterfaceBlock,bFuncBlock; 
		protected 	UMLCompilerTokens tokenizer;
		protected 	String	compiledLine = "";
		protected	int	id = 0;
		public 		String lasttemplatetype = "";
		public		String lasttemplatename = "";
		public		int numClasses,numInterfaces,numEnums;
		public		int uniqueID = 0;

		public UMLCompiler(String transTableName,String reservedWords) {
			super(transTableName,systemUserReg.getAppName());
			tokenizer = new UMLCompilerTokens();
			tokenizer.setReservedWords(reservedWords);
			tokenizer.setTypes(UMLTypes);
			setVarPreDefined(true);
			setCommentString("////");
			setStartMultiLineCommentString("//*");
			setEndMultiLineCommentString("*//");
			scopes.clear();
			callTreeIDs.clear();
			bInternalBlock = false;
			bClassBlock = false;
			bInterfaceBlock = false;
			bFuncBlock = false;
			classDepth = 0;
		}
		public int getClassDepth() {return classDepth;}
		public String compileLine(String line) {
			String code = "";
			setCompileLine(true);
			Vector lines = supportFunctions.splitIntoTokens(line,";");
			for (int lineNumber=0;lineNumber<lines.size();lineNumber++) {
				compiledLine = "";
				if(!syntaxCheck((String)lines.elementAt(lineNumber))) {return "";}
				code = code + compiledLine;
			}
			setCompileLine(false);
			return code;
		}
		public String getEntityName() {return entityName;}
		public boolean isValidIdentifier(String ident) {
			if (tokenizer.isReserved(ident)) {return false;}
			return isValidID(ident);
		}
		public boolean syntaxCheck(String line) {
			Vector v = tokenizer.tokenizeLine(line);
			syntaxError = "";
			return syntaxCheckInternal(line,v);
		}
		public String getKeywordFromLine(Vector tokens,String line) {
			ehsRegExp r = new ehsRegExp();
			
			TRACE("GKFL: line:"+line,4);
						
			if (tokens.size() == 0) {return "none";}

			if (getFileExt().equals("cs")) { // only for C# source code
				
			}
			if (getFileExt().equals("cpp")) { // only for C++ source code
					
			}
			if (getFileExt().equals("java")) { // only for JAVA source code
				if(getPassNumber() == 2) { // only do for second pass of complier
					String tmp = line;
					int obindex = tmp.indexOf("{");
					if (obindex == -1) {obindex = 0;}
					String[] funcCalls = umlDiagram.getUMLCallingTree().getFunctionCalls(tmp.substring(obindex));
					if (funcCalls != null) {
						for (int i=0;i<funcCalls.length;i=i+2) {
							if (tmp.indexOf("new ") == -1) {
								umlDiagram.getUMLCallingTree().addCallingTreeNode("funccall",funcCalls[i],funcCalls[i+1]);						
							} else {
								umlDiagram.getUMLCallingTree().addCallingTreeNode("newvar",funcCalls[i],funcCalls[i+1]);
							}
						}
					}
				}

				TRACE("GKFL: Point A",4);
				if (r.regExpMatch(line,umlDiagram.getAccessRegExp()+"class\\s*(\\w+)\\s*<\\s*(.*)\\s*>")) {bClassBlock=true;classDepth++;return "newclasstemplate";}
				TRACE("GKFL: Point B",4);
				if (r.regExpMatch(line,umlDiagram.getAccessRegExp()+"(\\w+)\\s+<\\s*(.*)\\s*>\\s*(\\w+)\\s*[=;]")) {return "newtemplatevar";}
			}
			
			if (tokens.contains("enum")) {if (tokens.contains("(")) {bClassBlock=true;classDepth++;return "extenum";} else {return "newenum";}}
			if (tokens.contains("class")) {bClassBlock=true;classDepth++;return "newclass";}
			if (tokens.contains("interface")) {bInterfaceBlock=true;return "newinterface";}			
			if (r.regExpMatch(line,umlDiagram.getAccessRegExp()+"(\\w+)\\s+(\\w+)\\s*\\(")) {
				if (tokens.contains("}")) {
					return "newclassfuncps";
				} else {
					bFuncBlock=true;
					return "newclassfunc";
				}
			}
			if (r.regExpMatch(line,umlDiagram.getAccessRegExp()+"([a-zA-Z0-9_\\[\\]]+)\\s+(\\w+)\\s*[=;,]")) {
				//supportFunctions.displayMessageDialog(null,"sv:"+line);
				if (splitVariables(line)) {
					return "none"; //ignore this source code line
				} 
				return "newclassvar";
			}
			
			if (tokens.contains("{")) {
				bInternalBlock = true;
			}
			
			if (tokens.contains("}")) {
				if (!bInternalBlock) {
					// the following checks must be in the order of inner most to outer most
					if (bFuncBlock) {bFuncBlock = false;return "popscopefunc";} 
					if (bInterfaceBlock) {bInterfaceBlock = false;return "popscopeinterface";}
					if (bClassBlock) {
						classDepth--;
						if (classDepth == 0) {bClassBlock = false;}
						return "popscopeclass";
					}
				}
				bInternalBlock = false; // otherwise ignore internal block
			}
						
			return "none";
		}
		public boolean splitVariables(String line) {			
			String tmp = line;
			String initalValue = ";";
			
			tmp = tmp.replaceAll(" =","=");
			tmp = tmp.replaceAll("= ","=");
			tmp = tmp.substring(0,tmp.length() - 1);

			int index = tmp.indexOf("=");
			if (index != -1 ) {
				initalValue = tmp.substring(index+1);
				tmp = tmp.substring(0,index);
			}
			
			tmp = tmp.replaceAll("\\t{2,}"," ");
			tmp = tmp.replaceAll("\\s{2,}"," ");
			tmp = tmp.replaceAll(" ,",",");
			tmp = tmp.replaceAll(", ",",");
			tmp = tmp.trim();

			Vector v = supportFunctions.splitIntoTokens(tmp," ");
			Vector vars = supportFunctions.splitIntoTokens((String)v.elementAt(v.size() - 1),",");
			if (vars.size() == 1) {return false;}
			String start = "";
			for (int i=0;i<v.size() - 1;i++) {
				start = start + " " + (String)v.elementAt(i);
			}

			for (int i=0;i<vars.size();i++) {
				addExtraLine(start + " " + (String)vars.elementAt(i) + " " + initalValue);
			}
			
			return true;
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
			if (tokens.size() == 0) {TRACE("syntaxCheckInternal:Empty Line",4);return true;}
		
			String keyword = getKeywordFromLine(tokens,line);
			TRACE("CL:"+line+":Keyword:"+keyword,4);
						
			// process the line using the translation table entry defined by the above keyword
			int status = processLine(line,keyword,tokens);
			if (status != -1) {
				setErrorString("Line:" + String.valueOf(getLineNumber()+1)+" " + line,(String)tokens.elementAt(status));
				return false;
			}
			
			return true;
		}
		public String getName() {return "UML";}
		public String getFileExt() {return umlDiagram.getFileExt();}
		
		public boolean preCompile(String filename,boolean bHeaders) {
			indentionCount = 0;
			bInternalBlock = false;
			bClassBlock = false;
			bInterfaceBlock = false;
			bFuncBlock = false;
			classDepth = 0;
				
			numClasses = 0;
			numInterfaces = 0;
			numEnums = 0;
				
			if (bHeaders) {		
				symbolTable.removeAllSymbols();
				transTable = new translationTable();
				transTable.loadTranslationTable(transTableName);
			
				compilerFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + "_umldiag.xml");
				compilerFile.saveFile("<?xml version=\"1.0\"?>"); // saveFile used to remove any existing file
				compilerFile.appendFile("\n");
				compilerFile.appendFile("<umldata>");
				
				umlDiagram.setUMLCallingTree(new callingTree(supportFunctions.getFilenameNoExt(filename)));
				
				umlDiagram.setCodeMetrics(new configurationSettings());
				umlDiagram.getCodeMetrics().newConfigurationSettings(""+dataRelativePath+"/"+appDirectory+"/output"+"/"+supportFunctions.getFilenameNoExt(filename) + "_codemetrics");
			}
			
			scopes.clear();
			callTreeIDs.clear();
										
			entityName = moduleName;
			addScope("M"+moduleName);
			
			// clear compile place holder list
			placeHolders.remove("*e");
			placeHolders.remove("*f");
			placeHolders.remove("*g");
			placeHolders.remove("*h");

			return true;
		}
		public boolean postCompile(String filename,boolean bHeaders) {
			// Initial file is compiled with 'bHeaders' set to true, hence any
			// extra files must be processed before the if(bHeaders...) {...} line
			
			processTypes(filename);
			
			updateCodeMetrics();

			if (umlDiagram.getExtraFiles().size() != 0) {
				String file = umlDiagram.getNextExtraFile();
				UMLCompiler c = mainTab.getLanguageTransTable(supportFunctions.getFilenameExt(file));
				c.compile(file,false);				
			}
			
			if (bHeaders) {
				compilerFile.appendFile("\n");
				compilerFile.appendFile("</umldata>");
				compilerFile.flush();
				umlDiagram.getCodeMetrics().closeConfigurationSettings();
			}
			
			return true;
		}
		public void updateCodeMetrics() {
			int tlines = Integer.parseInt(umlDiagram.getCodeMetric("totallines","0"));
			umlDiagram.setCodeMetric("totallines",String.valueOf(tlines + getLineNumber()));
			tlines = tlines + getLineNumber();
			int blines = Integer.parseInt(umlDiagram.getCodeMetric("totalblanklines","0"));
			umlDiagram.setCodeMetric("totalblanklines",String.valueOf(blines + getNumBlankLines()));
			blines = blines + getNumBlankLines();
			int clines = Integer.parseInt(umlDiagram.getCodeMetric("totalcommentlines","0"));
			umlDiagram.setCodeMetric("totalcommentlines",String.valueOf(clines + getNumCommentLines()));
			clines = clines + getNumCommentLines();
			umlDiagram.setCodeMetric("totalcodelines",String.valueOf(tlines - blines - clines));

			Vector v = symbolTable.getSymbols(moduleName,symType.TEMPLATE);
			int tmp = Integer.parseInt(umlDiagram.getCodeMetric("totaltemplates","0"));
			umlDiagram.setCodeMetric("totaltemplates",String.valueOf(tmp + v.size()));
			tmp = Integer.parseInt(umlDiagram.getCodeMetric("totalclasses","0"));
			umlDiagram.setCodeMetric("totalclasses",String.valueOf(tmp + numClasses));
			tmp = Integer.parseInt(umlDiagram.getCodeMetric("totalinterfaces","0"));
			umlDiagram.setCodeMetric("totalinterfaces",String.valueOf(tmp + numInterfaces));
			tmp = Integer.parseInt(umlDiagram.getCodeMetric("totalenums","0"));
			umlDiagram.setCodeMetric("totalenums",String.valueOf(tmp + numEnums));
		}
		public boolean isTemplate(String typeName) {
			Vector v = symbolTable.getSymbols(moduleName,symType.TEMPLATE);
			TRACE("isTemplate:Getting all template symbols for " + moduleName + " Number="+String.valueOf(v.size()),4);
			for (int i=0;i<v.size();i++) {
				compilerSymbol cs = (compilerSymbol)v.elementAt(i);
				TRACE("isTemplate:"+typeName+":"+cs.getSymbolName(),4);
				if (typeName.equals(cs.getSymbolName())) {return true;}
			}
			return false;
		}
		public void processTypes(String filename) {
			basicFile f = new basicFile(filename);
			pseduoFile typesFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + "_types.xml");
			typesFile.saveFile("<?xml version=\"1.0\"?>"); // saveFile used to remove any existing file
			typesFile.appendFile("\n");
			typesFile.appendFile("<umltypes>");
			
			Vector v = symbolTable.getSymbols(moduleName,symType.TEMPLATE);
			TRACE("Getting all template symbols for " + moduleName + " Number="+String.valueOf(v.size()),4);
			for (int i=0;i<v.size();i++) {
				compilerSymbol cs = (compilerSymbol)v.elementAt(i);
				typesFile.appendFile("\n");
				typesFile.appendFile("<template name=\"" + cs.getSymbolName() + "\" scope=\"" + cs.getScope() + "\" subscope=\"" + cs.getSubScope() + "\" type=\"" + cs.getSymbolValue() + "\" />");				
			}
			v = symbolTable.getSymbols(moduleName,symType.TYPE);
			TRACE("Getting all type symbols for " + moduleName + " Number="+String.valueOf(v.size()),4);
			for (int i=0;i<v.size();i++) {
				compilerSymbol cs = (compilerSymbol)v.elementAt(i);
				Vector v2 = supportFunctions.splitIntoTokens(cs.getSymbolName(),"-"); // symbol name is in the form id-type
				String typeName = (String)v2.elementAt(1);	
				
				// get base type name
				int index = typeName.indexOf("[");
				if (index != -1) {
					typeName = typeName.substring(0,index);
				}
				index = typeName.indexOf("<");
				if (index != -1) {
					typeName = typeName.substring(0,index);
				}
				//supportFunctions.displayMessageDialog(null,"processTypes:base type name:"+typeName);

				if (isTemplate(typeName)) {cs.setSymbolClass(symClass.TEMPLATETYPE);continue;}

				if (tokenizer.isType(typeName)) {cs.setSymbolClass(symClass.BULITIN);continue;}
				if (tokenizer.isClassAContainer(typeName)) {cs.setSymbolClass(symClass.CONTAINER);continue;}
				Vector v1 = symbolTable.getSymbols(moduleName,symType.CLASSINTERFACEENUM);
				TRACE("Getting all clsss-interface-enum symbols for " + moduleName + " Number="+String.valueOf(v1.size()),4);
				TRACE("Checking for type:"+typeName,4);
				for (int ii=0;ii<v1.size();ii++) {
					compilerSymbol cs1 = (compilerSymbol)v1.elementAt(ii);
					String typeName1 = cs1.getSymbolName();
					TRACE("class-interface-enum name:"+typeName1,4);
					//supportFunctions.displayMessageDialog(null,"processTypes:class-interface-enum name:"+typeName1);
					if (typeName1.equals(typeName)) {cs.setSymbolClass(symClass.APPLICATION);continue;}
				}
				
				if (cs.getSymbolClass() == symClass.NONE) {cs.setSymbolClass(symClass.LIBRARY);}
			}
			for (int i=0;i<v.size();i++) {
				compilerSymbol cs = (compilerSymbol)v.elementAt(i);
				String contains = "";
				String array = "no";
				
				// if type is an array set array to "yes"
				ehsRegExp p1 = new ehsRegExp();
				if (p1.regExpMatch(cs.getSymbolName(),"\\[.*?\\]")) {array = "yes";}
				
				if (cs.getSymbolClass() == symClass.CONTAINER) {
					array = "yes";
					int lineNumber = cs.getExtra();
					f.resetReadFlag();
					int iScopeLevel = 1;
					String tmp = f.readFileLine(lineNumber-1); // as lines are zero-based;
					// read file lines until end of scope or end of file
					do {
						if (tmp != null) {
							TRACE("Line Read:"+tmp,4);
							iScopeLevel = iScopeLevel + supportFunctions.strCount(tmp,'{') - supportFunctions.strCount(tmp,'}');
							TRACE("Current scope level:"+String.valueOf(iScopeLevel),4);
							ehsRegExp p = new ehsRegExp();
							String expression = "\\((.*?)\\)"+cs.getSymbolValue();
							TRACE("container expression:"+expression,4);							
							p.regExpMatch(tmp,expression);
							String[] ftokens = p.getFoundStringsArray();			
							for (int ii=0;ii<ftokens.length;ii++) {
								TRACE("container strings found:"+ftokens[ii],4);
							}
							String[] gtokens = p.getFoundGroupsArray();
							for (int ii=0;ii<gtokens.length;ii++) {
								contains = gtokens[0];
								TRACE("container groups found:"+gtokens[ii],4);
							}
						}
						tmp = f.readFileLine();
					} while (tmp != null && iScopeLevel != 0);
					TRACE("processtypes:exited file read loop",4);
				}
				typesFile.appendFile("\n");
				Vector v3 = supportFunctions.splitIntoTokens(cs.getSymbolName(),"-"); // symbol name is in the form id-type
				typesFile.appendFile("<type name=\"" + (String)v3.elementAt(1) + "\" scope=\"" + cs.getScope() + "\" subscope=\"" + cs.getSubScope() + "\" variable=\"" + cs.getSymbolValue() + "\" class=\"" + cs.getSymbolClass().getDescription() + "\" contains=\"" + contains + "\" array=\"" + array + "\" />");				
			}
			
			typesFile.appendFile("\n");
			typesFile.appendFile("</umltypes>");
			typesFile.flush();
		}
		public boolean betweenPassProcesses() {
			if (bProcessHeaders) {
				compilerFile.appendFile("");
			}
			bInternalBlock = false;
			bClassBlock = false;
			bInterfaceBlock = false;
			bFuncBlock = false;
			return true;
		}
		public String completeRawScope() {
			String cscope = "";
			Object[] tmp = scopes.toArray();
			for (int i=0;i<tmp.length;i++) {
				if (i>0) {cscope = cscope + "::";}
				cscope = cscope + removeQualifier((String)tmp[i]);
			}
			return cscope;			
		}
		public String completeScope() {
			String cscope = "";
			Object[] tmp = scopes.toArray();
			for (int i=0;i<tmp.length;i++) {
				if (i>0) {cscope = cscope + "::";}
				cscope = cscope + (String)tmp[i];
			}
			return cscope;
		}
		public String currentScope() {return (String)scopes.peek();}
		public String removeScope() {return (String)scopes.pop();}
		public void addScope(String s) {scopes.push(s);}
		public String removeQualifier(String s) {return s.substring(1);}
		public void preCompleteLine(String line) {
			line = line.trim();
			int index = line.indexOf("{");
			if (index != -1 && index != line.length() - 1) {
				if (line.indexOf("}") == -1) {
					addExtraLine(line.substring(index + 1));
					line = line.substring(0,index + 1);
					updateSourceFileLine(line);
					supportFunctions.displayMessageDialog(null,"preCompleteLine {");
				}
			}
			index = line.indexOf("else");
			if (index != -1) {
				addExtraLine(line.substring(index));
				line = line.substring(0,index);
				updateSourceFileLine(line);
				supportFunctions.displayMessageDialog(null,"preCompleteLine else");
			}
			index = line.indexOf("while");
			if (index != -1) {
				addExtraLine(line.substring(index));
				line = line.substring(0,index);
				updateSourceFileLine(line);
				supportFunctions.displayMessageDialog(null,"preCompleteLine while");
			}
		}
		
		protected	static final String branchStart = "branchstart";
		protected	static final String branchEnd = "branchend";
		protected	static final String branchElse = "branchelse";
		protected	static final String loopStart = "loopstart";
		protected	static final String loopEnd = "loopend";
		public void postCompleteLine(String line) {
			line = processFunctionDefinations(line);
			updateSourceFileLine(line);
			
			ehsRegExp r = new ehsRegExp();
			if(getPassNumber() == 2) { // only do for second pass of complier
				String initalValue = "-";
				
				int index = line.indexOf("=");
				if (index != - 1) {
					initalValue = line.substring(index+1);
					initalValue = initalValue.trim();
				}
				
				if (r.regExpMatch(line,umlDiagram.getUMLCallingTree().getVarDefRegExp())) {
					String[] tmp = r.getFoundGroupsArray();
					tmp[1] = tmp[1].trim();
					if (tmp[1].indexOf(",") == -1) {
						if (bFuncBlock) {
							// local variable (inside a function block)
							compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),tmp[1],tmp[0],symType.LOCALVARIABLE,symClass.NONE);
							cs.setExtra(getLineNumber()); // GDB 060218
							umlDiagram.getUMLCallingTree().addCallingTreeNode("localvar",tmp[0] + "." + tmp[1],initalValue);
						} else {
							if (!r.regExpMatch(line, "(public|protected|private|internal)")) {
								// class variable defination with no access type - so assume the default public
								line = "public " + line;
								updateSourceFileLine(line);
							}
							umlDiagram.getUMLCallingTree().addCallingTreeNode("classvar",tmp[0] + "." + tmp[1],initalValue);
						}
					}
				}
				
				if (r.regExpMatch(line,umlDiagram.getUMLCallingTree().getVarAssignRegExp())) {
					String[] tmp = r.getFoundGroupsArray();	
					umlDiagram.getUMLCallingTree().addCallingTreeNode("varassign",tmp[0],initalValue);
				}
				
				NodeList nl = umlDiagram.getUMLCallingTree().getLangElementNodeList();
				for (int i=0;i<nl.getLength();i++) {
					org.w3c.dom.Element e =
						(org.w3c.dom.Element)nl.item(i);
					//if (r.regExpMatch(line,(String)v.elementAt(i))) {
					//	String[] tmp1 = r.getFoundGroupsArray();
					//}
				}
				
				String tmp = umlDiagram.getUMLCallingTree().getLoopingRegExp();
				Vector v = supportFunctions.splitIntoTokens(tmp,"::",false);
				for (int i=0;i<v.size();i++) {
					if (r.regExpMatch(line,(String)v.elementAt(i))) {
						String[] tmp1 = r.getFoundGroupsArray();
						String id = "loopstart," + String.valueOf(uniqueID++);
						callTreeIDs.push(id);
						umlDiagram.getUMLCallingTree().addCallingTreeNode(loopStart,
								id,tmp1[0] + "::" + String.valueOf(mainTab.compilier.getLineNumber()));
					}
				}
				
				tmp = umlDiagram.getUMLCallingTree().getBranchingRegExp();
				v = supportFunctions.splitIntoTokens(tmp,"::",false);
				for (int i=0;i<v.size();i++) {
					if (r.regExpMatch(line,(String)v.elementAt(i))) {
						String key = branchStart;
						if (line.indexOf("else") != -1) {key = branchElse;}
						String[] tmp1 = r.getFoundGroupsArray();
						String id = key + "," + String.valueOf(uniqueID++);
						callTreeIDs.push(id);
						umlDiagram.getUMLCallingTree().addCallingTreeNode(key,
								id,tmp1[0] + "::" + String.valueOf(mainTab.compilier.getLineNumber()));
					}
				}
				
				// next two checks must be done following the checks for
				// start of looping and branching source code lines
				if (line.indexOf("}") != -1 && callTreeIDs.size() != 0) {
					Vector v1 = supportFunctions.splitIntoTokens((String)callTreeIDs.pop(),",");
					String key = loopEnd;
					String text = (String)v1.elementAt(0);
					if(text.startsWith("branch")) {key = branchEnd;}
					umlDiagram.getUMLCallingTree().addCallingTreeNode(
							key,(String)v1.elementAt(1),"");					
				}
			}			
		}
		public boolean completeLine(String line) {
			line = line.trim();
			
			// an empty line is a complete line
			if (line.length()==0) {return true;}
			TRACE("completeLine:line:"+line,4);
			
			// if line contains 'class' or 'interface' must also contain a '{' character
			if (line.indexOf("class")!=-1 || line.indexOf("interface")!=-1) {
				if (line.indexOf("{")!=-1) {return true;} else {return false;}
			}
			
			
			ehsRegExp r = new ehsRegExp();
			// if line is a function def must also contain a '{' character
			if (r.regExpMatch(line,umlDiagram.getAccessRegExp()+"(\\w+)\\s+(\\w+)\\s*\\(")) {
				if (line.indexOf("{")!=-1) {return true;} else {return false;}
			}
			
			// if the last character on the line is a ';' or a '}' or a '{' then a complete line
			if (line.charAt(line.length()-1)==';' || line.charAt(line.length()-1)=='}' || line.charAt(line.length()-1)=='{') {return true;}
			//if (line.charAt(line.length()-1)==')') {return true;}
			
			
			TRACE("completeLine:return false",4);
			return false;
		}
		public String tasteNextToken(Vector lineTokens) {
			String tmp = "";
			if (lineTokenIndex+1 < lineTokens.size()-1) {
				tmp = (String)lineTokens.elementAt(lineTokenIndex+1);
			}
			return tmp;
		}
		public String eatNextToken(Vector lineTokens) {
			String tmp = "";
			if (lineTokenIndex+1 < lineTokens.size()-1) {
				tmp = (String)lineTokens.elementAt(lineTokenIndex++);
			}
			return tmp;
		}
		public String eatLineTokensUntilString(Vector lineTokens,String[] stopTokens) {
			String ret = (String)lineTokens.elementAt(lineTokenIndex);
			TRACE("ELTUS:Point A",4);
			while (lineTokenIndex++ < lineTokens.size()-1) {
				String lineToken = (String)lineTokens.elementAt(lineTokenIndex);
				TRACE("ELTUS:linetoken="+lineToken+" Return="+ret,4);
				for (int i=0;i<stopTokens.length;i++) {
					if (lineToken.equals(stopTokens[i])) {
						lineTokenIndex--;
						TRACE("ELTUS:Stop Token:"+stopTokens[i],4);
						return ret;
					}
				}
				if (lineToken.equals("[") || lineToken.equals("]")) {ret = ret + lineToken;} else {ret = ret + " " + lineToken;}
			}
			TRACE("ELTUS:Point B",4);
			return ret;
		}
		public int processTokens(String line,String keyword,Vector lineTokens,translationTableEntry entry) {
			Vector compilerTokens = entry.getTokens();
			//placeHolders = new Properties();
			// clear line only place holder list
			placeHolders.remove("*1");
			placeHolders.remove("*2");
			placeHolders.remove("*3");
			placeHolders.remove("*4");

			Vector expComplierTokens = transTable.expandTokens(compilerTokens);			
			boolean bNoTransText = false;
			String lastvartype = "";
						
			if (getPassNumber() == 1) {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;
				placeHolders.setProperty("*0","");
				placeHolders.setProperty("*8"," ");
				placeHolders.setProperty("*6",",");
				placeHolders.setProperty("*9",moduleName);
			} else {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;
				placeHolders.setProperty("*0","");
				placeHolders.setProperty("*8"," ");
				placeHolders.setProperty("*6",",");
				placeHolders.setProperty("*9",moduleName);
				
				setExErrorMsg("");
				String lineToken = "";
				String prevLineToken = "";
				String nextLineToken = "";
				String complierToken = "";
				
				compilerFile.appendFile("\n");
				String key = entry.getKeyword();
				
				String baseIndent = "";
				for(int i=0;i<classDepth;i++) {baseIndent = baseIndent + "\t";}
				compilerFile.appendFile(baseIndent);
				
				// commented out below 2 lines as we have the appendFile in the line above
				//if (key.equals("newclass")) {compilerFile.appendFile("\t");}
				//if (key.equals("newinterface")) {compilerFile.appendFile("\t");}
				if (key.equals("popscopeclass")) {compilerFile.appendFile("\t");lasttemplatetype="";lasttemplatename="";}
				if (key.equals("popscopeinterface")) {compilerFile.appendFile("\t");lasttemplatetype="";lasttemplatename="";}
				
				// these of course need an extra tab of indentation
				if (key.equals("newclassvar")) {compilerFile.appendFile("\t");}
				if (key.equals("newclassfunc")) {compilerFile.appendFile("\t");}
				if (key.equals("newclassfuncps")) {compilerFile.appendFile("\t");}

				String s = entry.getpreEntryString();
				s = s.replaceAll("\\*a",completeScope());
				compilerFile.appendFile(s);
				do {
					lineToken = (String)lineTokens.elementAt(lineTokenIndex);
					//supportFunctions.displayMessageDialog(null,"G1:" + lineToken);
					if (lineTokenIndex>0) {prevLineToken = (String)lineTokens.elementAt(lineTokenIndex-1);} else {prevLineToken = lineToken;}
					if (lineTokenIndex<lineTokens.size()-1) {nextLineToken = (String)lineTokens.elementAt(lineTokenIndex+1);} else {nextLineToken = lineToken;}
					if (lineToken.length() == 0) {lineTokenIndex++;continue;}
					// check for line labels
					if (lineTokenIndex == 0 && lineToken.endsWith(":")) {
						//symbolTable.addSymbol(moduleName,currentScope(),lineToken,String.valueOf(getLineNumber()),symType.LABEL,symClass.NONE);
						compilerFile.appendFile("");
						continue;
					}
					boolean bTokensMatch = false;
					int multiTransTextIndex = 0;
					
					translationTableToken token = null;
					//for(int ii=0;ii<compilerTokens.size();ii++) {
					//	token = (translationTableToken)compilerTokens.elementAt(ii);
					//	supportFunctions.displayMessageDialog(null,"CT:" + token.getText());
					//}
					//for(int iii=0;iii<expComplierTokens.size();iii++) {
					//	token = (translationTableToken)expComplierTokens.elementAt(iii);
					//	supportFunctions.displayMessageDialog(null,"ECT:" + token.getText());
					//}
					
					if (expComplierTokens.size() < compilerTokenIndex) { // changed from <= 080712
						setExErrorMsg("Incomplete translation table entry");
						return lineTokenIndex;
					}
					try {
						token = (translationTableToken)expComplierTokens.elementAt(compilerTokenIndex);
					} catch (Exception e) {supportFunctions.displayMessageDialog(null,"No more translation table entry tokens");}
					//supportFunctions.displayMessageDialog(null,"Compiler Token:"+token.getText());
					Vector v = supportFunctions.splitIntoTokens(token.getText()," ");
					for (int j=0;j<v.size();j++) {
						Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(j),"##",false);
						for (int k=0;k<v1.size();k++) {
							multiTransTextIndex = k;
							complierToken = (String)v1.elementAt(k);
														
							TRACE("Line Token:" + lineToken + " and Complier Token:" + complierToken,4);
							
							// if matching a complier token in a multi-group
							// only use 'break' if we have a match for the token
							// otherwise we will not go on and match the other complier
							// tokens in the multi-group
							if (complierToken.equals("none")) {
									bTokensMatch = true;
									break;
							}
							if (complierToken.equals("templatetype")) { // HOOK D
								TRACE("templatetype:finding symbol:"+moduleName+":"+currentScope()+":"+lasttemplatename,4);
								String[] sa = {">"};
								String ss = eatLineTokensUntilString(lineTokens,sa);
								compilerSymbol cs = symbolTable.findSymbol(moduleName,currentScope(),lasttemplatename,symType.TEMPLATE);
								if (cs != null) {
									TRACE("templatetype:setting symbol:"+ss,4);
									cs.setSymbolValue(ss);
								}
								lasttemplatetype = ss;
								umlDiagram.getUMLCallingTree().startDefine("pushscopeclasstemplate",lasttemplatename+"<"+lasttemplatetype+">");
								lineToken = ss;
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("deftemplatename")) { // HOOK E
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid template name (deftemplatename)");
									continue;
								}
								TRACE("deftemplatename:adding symbol:"+moduleName+":"+currentScope()+":"+lineToken,4);
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.TEMPLATE,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								lasttemplatename = lineToken;
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("comma")) {
								if (lineToken.equals(",")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected , (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("<")) {
								if (lineToken.equals("<")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected < (got " + lineToken + ")");
								}
							}
							if (complierToken.equals(">")) {
								if (lineToken.equals(">")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected > (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("integer")) {
								if (tokenizer.isNumeric(lineToken)) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected integer (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("class")) {
								if (lineToken.equals("class")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected class (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("interface")) {
								if (lineToken.equals("interface")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected interface (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("extends")) {
								if (lineToken.equals("extends")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected extends (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("implements")) {
								if (lineToken.equals("implements")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected implements (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("obrace")) {
								if (lineToken.equals("{")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected { (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("cbrace")) {
								if (lineToken.equals("}")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected } (got " + lineToken + ")");
								}
							}
							if (complierToken.equals(";")) {
								if (lineToken.equals(";")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected ; (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("(")) {
								if (lineToken.equals("(")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected ( (got " + lineToken + ")");
								}
							}
							if (complierToken.equals(")")) {
								if (lineToken.equals(")")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected ) (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("[")) {
								if (lineToken.equals("[")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected [ (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("]")) {
								if (lineToken.equals("]")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected ] (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("packagename")) {
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("access")) {
								Vector values = umlDiagram.getAccessKeywords();
								if (values.contains(lineToken)) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Expected access keyword (got " + lineToken + ")");
								}
								break;
							}
							if (complierToken.equals("exaccess")) {
								Vector values = umlDiagram.getExAccessKeywords();
								if (values.contains(lineToken)) {
									String nlToken = tasteNextToken(lineTokens);
									TRACE("exaccess:TNT:"+nlToken,4);
									if (values.contains(nlToken)) {
										lineToken = lineToken + " " + nlToken;
										eatNextToken(lineTokens);
										TRACE("exaccess:ENT",4);
									}
									bTokensMatch = true;
								} else {
									setExErrorMsg("Expected exaccess keyword (got " + lineToken + ")");
								}
								break;
							}
							if (complierToken.equals("popscopefunc")) {
								String t=removeScope();
								TRACE("RSPSF:"+t,4);
								umlDiagram.getUMLCallingTree().endDefine(complierToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("popscopeclass")) {
								String t=removeScope();
								TRACE("RSPSC:"+t,4);
								umlDiagram.getUMLCallingTree().endDefine(complierToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("popscopeinterface")) {
								removeScope();
								umlDiagram.getUMLCallingTree().endDefine(complierToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("classname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid class name");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("defclassname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid class name");
									continue;
								}
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.CLASSINTERFACEENUM,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.CLASS,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								numClasses++;
								addScope("C"+lineToken);
								umlDiagram.getUMLCallingTree().startDefine("pushscopeclass",lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("classinterfacename")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid interface name");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("interfacename")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid interface name");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("definterfacename")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid interface name");
									continue;
								}
								numInterfaces++;
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.CLASSINTERFACEENUM,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.INTERFACE,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								umlDiagram.getUMLCallingTree().startDefine("pushscopeinterface",lineToken);
								addScope("I"+lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("paramater")) {
								String[] sa = {",",")"};
								String ss = eatLineTokensUntilString(lineTokens,sa);
								String[] tmp = umlDiagram.getFuncParamModKeywords();
								for (int i=0;i<tmp.length;i++) {
									ss = ss.replaceAll(tmp[i]+"\\s+", "");
								}
								compilerFile.appendFile(ss);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("defaultvalue")) {
								String[] sa = {";"};
								String ss = eatLineTokensUntilString(lineTokens,sa);
								compilerFile.appendFile(ss);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("funcname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid function name");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("deffuncname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid function name");
									continue;
								}
								addScope("F"+lineToken);
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.FUNCTION,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								umlDiagram.getUMLCallingTree().startDefine("pushscopefunc",lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("deffuncname1")) { // used as keyword when } appears on same line
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid function name");
									continue;
								}
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.FUNCTION,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								umlDiagram.getUMLCallingTree().startDefine("pushscopefuncps",lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("varname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid variable name (varname)");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("defvarname")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid variable name (defvarname)");
									continue;
								}
								TRACE("defvarname:finding symbol:"+moduleName+":"+currentScope()+":"+lastvartype,4);
								compilerSymbol cs = symbolTable.findSymbol1(id++,moduleName,currentScope(),lastvartype,symType.TYPE);
								if (cs != null) {
									TRACE("defvarname:setting symbol:"+lineToken,4);
									cs.setSymbolValue(lineToken);
									cs.setExtra(getLineNumber());
								}

								cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.VARIABLE,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("funcrettype")) {
								if (!isValidIdentifier(lineToken)) {
									setExErrorMsg(lineToken + " is not a valid function return type");
									continue;
								}
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("vartype")) {
								//if (!isValidIdentifier(lineToken)) {
								//	setExErrorMsg(lineToken + " is not a valid variable type");
								//	continue;
								//}
								compilerSymbol cs = symbolTable.findSymbol(moduleName,currentScope(),lasttemplatename,symType.TEMPLATE);
								if (cs != null) {
									if (lineToken.equals(cs.getSymbolValue())) { // do not add to symbol table
										lastvartype = lineToken;
										bTokensMatch = true;
										break;
									}
								}
								TRACE("vartype:adding symbol:"+moduleName+":"+currentScope()+":"+lineToken,4);
								cs = symbolTable.addSymbol(id,moduleName,currentScope(),String.valueOf(id)+"-"+lineToken,"value",symType.TYPE,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								lastvartype = String.valueOf(id)+"-"+lineToken;
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("enumname")) {
								numEnums++;
								compilerSymbol cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.CLASSINTERFACEENUM,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								cs = symbolTable.addSymbol(moduleName,currentScope(),lineToken,"value",symType.ENUM,symClass.NONE);
								cs.setExtra(getLineNumber()); // GDB 060218
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("enumitem")) {
								bTokensMatch = true;
								break;
							}
						} // end of split '##' loop
					
						if (!bTokensMatch) {
							// first check to see if this is an optional token, yes ignore it
							bNoTransText = false;
							String flags = token.getFlags();
							if (flags.length() != 0) {
								Vector v5 = supportFunctions.splitIntoTokens(flags,"##",false);
								String tmp = (String)v5.elementAt(multiTransTextIndex);
								if (tmp.startsWith("z")) {
									setExErrorMsg(""); // clear any multi token error messages on a sucessful token match
									lineTokenIndex--;
									bTokensMatch = true;
									bNoTransText = true;
									TRACE("Above complier token optional - not included in input line",4);
								}
							}
							
							if (!bTokensMatch) { // if token is not optional
								if(getExErrorMsg().length() == 0) {setExErrorMsg("Unrecognized Token (line:"+lineToken+" compiler:"+complierToken+")");}
								return lineTokenIndex;
							}
						} else {
							bNoTransText = false;
							setExErrorMsg(""); // clear any multi token error messages on a sucessful token match
						}
						
						placeHolders.setProperty("*7",lineToken);
						String transText = token.getTransText();
						if (bNoTransText) {transText = "";}
						if (transText.length() != 0) {
							//supportFunctions.displayMessageDialog(null,"tt: " + transText);
							Vector v2 = supportFunctions.splitIntoTokens(transText," ");
							Vector v3 = supportFunctions.splitIntoTokens((String)v2.elementAt(j),"##",false);
							transText = (String)v3.elementAt(multiTransTextIndex);
							
							// don't care about trans text marker
							if (transText.equals("x")) {transText="";}

							// place holder logic, remember place holder *9 is the moduleName
							boolean bClearTransText = false;
							ehsRegExp p = new ehsRegExp();
							p.regExpMatch(transText,"\\*[\\dabcdefghi]");
							String[] tokens;
							tokens = p.getFoundStringsArray();
							for (int phs=0;phs<tokens.length;phs++) {
								
								// if you add more place holders update the regexp above
								String tmp = "";
								if (tokens[phs].equals("*a")) {
									tmp = "";
								} else if (tokens[phs].equals("*b")) {
									tmp = currentScope();
								} else if (tokens[phs].equals("*c")) {
									tmp = "";
								} else if (tokens[phs].equals("*d")) {
									tmp = "::";
								} else if (tokens[phs].equals("*i")) {
									bClearTransText = true;
								} else {
									tmp = placeHolders.getProperty(tokens[phs]);
								}
								
								//supportFunctions.displayMessageDialog(null,"t:"+tokens[phs]+" tmp:"+tmp);
								// tmp wil be null if say there is currently no property set for
								// say *1, so set it to the current linetoken
								if (tmp == null) {
									placeHolders.setProperty(tokens[phs],lineToken);
									tmp = lineToken;
								}
								// and then the line token is added back into the trans string
								transText = transText.replace(tokens[phs],tmp);
							}
							
							if (transText.contains("{")) {indentionCount++;}
							if (transText.contains("}")) {indentionCount--;}
							
							if (bClearTransText) {compilerFile.removeLastFileLine();} else {compilerFile.appendFile(transText);}
						} else {
							//supportFunctions.displayMessageDialog(null,"No Trans Text");
						}

						if (j != v.size()-1) {
							try {
								lineToken = (String)lineTokens.elementAt(++lineTokenIndex);
							} catch (Exception e) {supportFunctions.displayMessageDialog(null,"Caught B");}
							//supportFunctions.displayMessageDialog(null,"Line Token2:" + lineToken);
						}
					} // end of split ' ' loop
					
					String flags = token.getFlags();
					if (flags.length() != 0) {
						//supportFunctions.displayMessageDialog(null,"Flags:"+flags);
						Vector v4 = supportFunctions.splitIntoTokens(flags,"##",false);
						String tmp = "";
						try {
							tmp = (String)v4.elementAt(multiTransTextIndex);
						} catch (Exception e) {	supportFunctions.displayMessageDialog(null,"Token flag mismatch");}
						if (tmp.startsWith("y")) {
							break; // finish processing line
						}
						if (tmp.startsWith("z")) {
							//if (!bTokensMatch) {lineTokenIndex--;} 
						}
						if (tmp.startsWith("r")) {
							try {
								int adjust = Integer.parseInt(tmp.substring(1));
								compilerTokenIndex = compilerTokenIndex + adjust;
							} catch (Exception e) {}
						} else {
							compilerTokenIndex++;
						}	
					} else {
						compilerTokenIndex++;
					}
					
					lineTokenIndex++;

				} while( (lineTokenIndex < lineTokens.size()) && (compilerTokenIndex < expComplierTokens.size()) ); // end of lineTokens for loop
				s = entry.getpostEntryString();
				s = s.replaceAll("\\*a",completeScope());
				compilerFile.appendFile(s);
				TRACE("CF:" + s,4);
			}
			return -1; // for success
		}
	}
	
	public void doAppUpdate() {
		   try {
		   	   URL u = new URL(getCodeBase(),"../appupdater.php?appproduct="+systemUserReg.getAppName()+"&curbuildnum="+systemUserReg.getBuildNumber()+"&serialbase="+systemUserReg.getAppSerialBase()+"&directory="+dataRelativePath+"/umlwb/umlwbbuild.number");
		   	   ac.showDocument(u,"_blank");
		   } catch (Exception e) {e.printStackTrace();}
	}

	public class aboutAction extends AbstractAction {
		   public aboutAction() {super("About");putValue(SHORT_DESCRIPTION,"Display About Box");}
		   public void actionPerformed(ActionEvent evt) {
		   		  TRACE("Pressed the display about box toolbar button",3);
				  systemUserReg.displayAboutBox();
		   }
	}
	public class helpAction extends AbstractAction {
		   public helpAction() {super("Toggle Help");putValue(SHORT_DESCRIPTION,"Toggle Help");}
		   public void actionPerformed(ActionEvent evt) {
				  toggleHelp(); 
		   }
	}
	
	public String doStringTransform(String xmlText,String xsltText) {
		return doTransform(new StreamSource(new StringReader(xmlText)),
				new StreamSource(new StringReader(xsltText)));
	}
	public String doFileTransform(String xmlFilename,String xsltFilename) {
		try {
			 return doTransform(
					 new StreamSource(new File(xmlFilename)),
					 new StreamSource(new File(xsltFilename)));
		} catch (Exception e) {e.printStackTrace();return "";}	
}
	public String doTransform(Source xml,Source xslt) {
		StringWriter writer = new StringWriter();
		Result out = new StreamResult(writer);
		try {
			XslTransformer trans = new XslTransformer();
			trans.process(xml,xslt,out);
		} catch(Exception e) {supportFunctions.displayMessageDialog(null,e.toString());}
		return writer.toString();
	}
 	 		
	public String[] getFileSet(String name) {
		Vector v = new Vector();
		xmlDataFile f = new xmlDataFile();
		f.openXMLDataFile(supportFunctions.getPathFilenameNoExt(name),"filesets",false);
		if (f != null) {
			org.w3c.dom.Document doc = f.getXMLDocument();
			NodeList n = supportFunctions.executeXPathExpr(doc,"/filesets/fileset/file/");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				v.addElement(e.getAttribute("name"));
			}
		}
		f.closeXMLDataFile();
		String[] tmp = new String[v.size()];
		v.copyInto(tmp);
		return tmp;
	}
	public String[] getFileList(String startDirectory,String filePattern) {
		basicFile xyz = new basicFile(startDirectory,"");		
		if (startDirectory.length() == 0) {
			startDirectory = ""+dataRelativePath+"/" + appDirectory;
			xyz.setdirectory(startDirectory);
		} 
		xyz.listFiles();
		Vector files = xyz.getFiles();
		Vector filetypes = xyz.getFileTypes();
		Vector fileIncludes = controlsFunctions.fileIncludes(files,filePattern);
		Vector v =new Vector();
		for (int i=0;i<files.size();i++){
			String f=(String)files.elementAt(i);
			String ii=(String)fileIncludes.elementAt(i);
			String t=(String)filetypes.elementAt(i);
			if ( ii.length()!=0 || t.equals("dir")) {v.addElement(f);}
		}
		String[] tmp = new String[v.size()];
		v.copyInto(tmp);
		return tmp;
	}
	public class splashScreenDialog extends JDialog implements pictureUtils {
	    private	Timer 			tick;
		private	int				timeoutSecs,currentSecs;
		private	pictureCanvas 	splashCanvas;
		public String 			title;
		public String 			extraText;
		public String 			bottomText1 = "";
		public String			bottomText2 = "";
		
		public void pictureStart() {}
		public void pictureFinish() {}
		public void picturePaint(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			Font orgFont = g2d.getFont();
			float size = g2d.getFont().getSize();
			Font newFont = g2d.getFont().deriveFont(Font.BOLD,size + 20);
			FontMetrics fm = getFontMetrics(newFont);
			int width = fm.stringWidth(title);
			int height = fm.getHeight();
			int ascent = fm.getAscent();
			int x = (splashCanvas.width() - width) / 2;
			Font newFont1 = g2d.getFont().deriveFont(Font.BOLD,size + 10);
			FontMetrics fm1 = getFontMetrics(newFont1);
			int width1 = fm1.stringWidth(extraText);
			int height1 = fm1.getHeight();
			int ascent1 = fm1.getAscent();
			int x1 = (splashCanvas.width() - width1) / 2;
			int yGap = (splashCanvas.height() - (height + height1)) / 3;
			int y = yGap + height;
			int y1 = yGap + height + 10 + height1;
			
			Font newFont2 = g2d.getFont().deriveFont(Font.BOLD,size);
			FontMetrics fm2 = getFontMetrics(newFont2);
			int height2 = fm2.getHeight();
			int ascent2 = fm2.getAscent();
			String s2 = bottomText1;
			int y2 = splashCanvas.height() - (2 * height2);
			String s3 = bottomText2;
			int y3 = splashCanvas.height() - height2;
			
			int botWidth = fm2.stringWidth(s2);
			if (botWidth < fm2.stringWidth(s3)) {botWidth = fm.stringWidth(s3);}
			int topWidth = fm.stringWidth(title);
			if (topWidth < fm1.stringWidth(extraText)) {topWidth = fm1.stringWidth(extraText);}
	
			g2d.setColor(Color.white);
			g2d.fill3DRect(x - 5,y - height,topWidth + 5 + 5,height + 10 + height1 + 5,true);
			g2d.setColor(Color.black);
			g2d.setFont(newFont);
			g2d.drawString(title,x,y);
			g2d.setFont(newFont1);
			g2d.drawString(extraText,x1,y1);
			
			g2d.setColor(Color.white);
			g2d.fill3DRect(5,y2 - height2 - 5,botWidth + 5,(2 * height2) + 5 + 5,true);
			g2d.setColor(Color.black);
			g2d.setFont(newFont2);
			g2d.drawString(s2,10,y2);
			g2d.drawString(s3,10,y3);
			
			g2d.setFont(orgFont);
		}
		public splashScreenDialog(Frame parent,String title,String image,String extraText,String bt1,String bt2,int timeToDisplay) {
			super(parent,title,true);

			this.title = title;
			this.extraText = extraText;
			this.bottomText1 = bt1;
			this.bottomText2 = bt2;
			
			timeoutSecs = 5;
			currentSecs = 0;
	
			splashCanvas = new pictureCanvas(image);			
			splashCanvas.addPictureListener(this);
			
			setTimeoutTick(timeToDisplay);
			Panel splashPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
			splashPanel.add(splashCanvas);
			setLayout(new BorderLayout());
			add(splashPanel,"North");
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();
			setLocation((d.width - splashCanvas.width()) / 2,(d.height - splashCanvas.height())/2);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
				tick.stop();
				dispose();
				}
			});
			
		   ActionListener task = new ActionListener() {
  				 public void actionPerformed(ActionEvent evt) {
						timedDialogTick(currentSecs++);
						if (currentSecs > timeoutSecs) {
							timedDialogTmeout();
							destroy();
						}
					 }
			};
		    tick = new Timer(1000,task);
		    tick.start();

		    pack();
		    setVisible(true);
		}	
		public void setTimeoutTick(int i) {timeoutSecs = i;}
		public int getTimeoutTick() {return timeoutSecs;}
		public int getCurrentTick() {return currentSecs;}
		public void timedDialogTmeout() {}
		public void timedDialogTick(int tick) {}
		public void destroy() {
			tick.stop();
			dispose();
	   }
	}
	
	public void testSyntaxEditorPane() {
		syntaxEditorPaneDialog d = new syntaxEditorPaneDialog(supportFunctions.getTopLevelParent(this));
		supportFunctions.displayMessageDialog(null,d.HDLTA.getRawText());
	}
	
	public class SEPComponent {
		private SEPScrollPane 		sp = null;
		private syntaxEditorPane 	jep = null;
		
		public SEPComponent(syntaxEditorPane jep,int cols,int rows) {
			this.jep = jep;
			sp = new SEPScrollPane(jep);
			sp.setPreferredSize(new Dimension(cols*charWidth,rows*charHeight));
			jep.setScrollPane(sp);
		}
		public SEPScrollPane getScrollPane() {return sp;}
		public syntaxEditorPane getEditorPane() {return jep;} 
	}
	
	public class syntaxEditorPaneDialog extends JDialog implements ActionListener {
		   private syntaxEditorPane	HDLTA;
		   private Button			ok;
		   
		   public syntaxEditorPaneDialog(Frame parent) {
		   		  super(parent,"Syntax Editor Pane Test",true);
				  setLayout(new FlowLayout(FlowLayout.CENTER));
	   			  addWindowListener(new WindowAdapter() {
	   			     public void windowClosing(WindowEvent evt) {
	   		            dispose();
	                 }
			      });
				  HDLTA = new syntaxEditorPane();
				  HDLTA.setBackground(ehsConstants.lightyellow);
				  HDLTA.setText("I love <b>Joide</b>");
				  JScrollPane sp = new JScrollPane(HDLTA);
				  sp.setPreferredSize(new Dimension(15*charWidth,5*charHeight));
 				  ok = new Button("Ok");
				  ok.addActionListener(this);
				  add(sp);
				  add(ok);
				  pack();
				  setVisible(true);
		   }
		   
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == ok) {
					dispose();
				}
			}
		   
		   public void destroy() {
		   		  dispose();
		   }	
	}

	public void multiColumnCanvasTest() {
		multiColumnCanvasComponent c = new multiColumnCanvasComponent();
		String[] colData1 = {"aaa","bbb"};
		String[] colData2 = {"ccc","ddd"};  
		String[] colData3 = {"Yes","No"};
		String[] options = {"Yes","No"};
		c.addColumn("Title 1",true,colData1);
		c.addColumn("Title 2",true,colData2);
		c.addComboColumn("Title 3",true,colData3,options);
		multiColumnCanvasDialog d = new multiColumnCanvasDialog(null,"Test MCD",c);
	}
}

