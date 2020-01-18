import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
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
import java.text.*;
import java.beans.*;
import java.lang.reflect.*;
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
import javax.xml.ws.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;
import javax.xml.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

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

import javax.script.*;
import javax.swing.filechooser.*;

import javax.sound.sampled.*;
import sun.audio.*;

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
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.html.ParagraphView;

import javax.swing.text.Element;
import javax.swing.text.Document;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//impordt javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression; 
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class template extends JApplet implements ChangeListener,Runnable
{
	protected static String	dataRelativePath = "..";
	protected static final String	appDirectory = "template";
	protected static final String	appClassName = "template";
	protected static final String	appBaseURL = "http://endhousesoftware.byethost11.com";
	protected static helpDialog helpDlg = null;
	protected static String	exHelpFile=dataRelativePath+"/"+appDirectory+"/"+"readme.txt";
	protected static String	splashJPG = dataRelativePath+"/"+appDirectory+"/template_logo.jpg";
	public		String		productKBFile = "tem_kb.txt";
	
	public	static final String		buildDate = "@@@Build Date: 8-March-2019 05:16 PM Build Number: 21@@@";
	public	static final String		frameworkBuildDate="###JAVA Framework (Version 1.41-RC3)###";
	public 	static final String		gitVersionInfo = "!!!Git Version : 22.1e71052.master-dirty.2019-03-08.17:16:29!!!";

	protected	Thread		systemThread1;
	protected	Card1		Tab1;
	protected 	Card2		Tab2;
	protected	Card3		Tab3;
	protected	Card4		Tab4;
	protected	helpCard	helpTab;

	protected 	CardLayout 	layout;
	protected 	Panel		cards;

	protected 	int		charWidth;
	protected	int		charHeight;

	protected	registrationinfo systemUserReg = null;
	
	protected	AppletContext	ac;
	protected	languageStrings	lStrings;
	protected	Container	contentPane;
	protected	boolean 	runThreads = true;	
	protected	Frame			parentFrame = null;
	
	protected	JTabbedPane	tabPane;

	protected	javax.swing.Action help,about;
				
	protected	customIcon			ciHelp;

	protected	 int		TRACELEVEL = 4;
	
	protected   static final int 		defaultDialogX = 150;
	protected	static final int 		defaultDialogY = 90;
	protected	static final int		defaultMenuX = 100;
	protected	static final int		defaultMenuY = 5;

	protected	static final int		sysThreadSleep = 1000;
	protected	static final int		systemMsgThreadPrioity = 7;

	protected	static final int		dcTypeTemplate = 10;
	protected	static final int 		visibleDCWidthChars = 140;
	protected	static final int 		visibleDCHeightChars = 30;

	protected	static String			exFAQFile="";
	
	protected	static final int		iconWidth = 16;
	protected	static final int		iconHeight = 16;
			
	protected 	MediaTracker			bilmt = new MediaTracker(this);
	public		Component				lastPositionWindow = null;
			
	public 		scrollableDrawingCanvas sDC=null;

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
		boolean bStatus = false;
		int argID = 0;
		TRACELEVEL = 999;
		for (int i=0;i<args.length;i++) {
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
		
		systemUserReg = new registrationinfo(appDirectory,"JAVA Template","Template Application","TA1000","02.00.0000.00","16/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "JAVA Template";
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

			extraStatusInfo();
			
			supportFunctions.getDBConn().disconnect();
			System.exit(0);			
		}

		supportFunctions.getDBConn().disconnect();
		System.exit(0);
	}
	public static void main(String[] args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("gui")) {
				ehsConstants.bRunAppWithGUI = true;
			}
		}
		if (ehsConstants.bRunAppWithGUI) {
			new appletframe(new template(),ehsConstants.windowXMax,ehsConstants.windowYMax);
		} else {
			theApp = new appletframe(new template());
			template tmp = (template)theApp.getApplet();
			tmp.applicationCode(args);
		}
	}

	public void start() {
		TRACE("start() called",3);
		runThreads = true;
		systemThread1 = new Thread(this);
		systemThread1.setPriority(systemMsgThreadPrioity);
		systemThread1.setName(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + " - System Message Thread 1");
		systemThread1.start();
	}

	public void stop() {
		TRACE("stop() called",3);
		runThreads = false;
	}

	public void destroySystem() {
		Tab1.destroyCard1();
		Tab2.destroyCard2();
		Tab3.destroyCard3();
		Tab4.destroyCard4();
		helpTab.destroyHelpCard();
		
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
	  
	public void doAppUpdate() {
		   try {
		   	   URL u = new URL(getCodeBase(),"../appupdater.php?appproduct="+systemUserReg.getAppName()+"&curbuildnum="+systemUserReg.getBuildNumber()+"&serialbase="+systemUserReg.getAppSerialBase()+"&directory="+dataRelativePath+"/template/templatebuild.number");
		   	   ac.showDocument(u,"_blank");
		   } catch (Exception e) {e.printStackTrace();}
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

	public helpDialog displayHelpDialog() {
		   helpDialog d = new helpDialog(supportFunctions.getTopLevelParent(this));

		   d.setLocation(defaultDialogX,defaultDialogY);
		   return d;
	}
	
	public void TRACE(String msg) {
		  TRACE(msg,3);
	  }
	  public void TRACE(String msg,int level) {
		if(level < TRACELEVEL) {return;}			
		int threadP = Thread.currentThread().getPriority();
		System.err.println("("+supportFunctions.currentDate()+" "+supportFunctions.currentTime()+") ["+Thread.currentThread().getName()+" ("+String.valueOf(threadP)+")] "+msg);
//		System.err.println("\n");
	  }	  

		public class Card1 extends Panel implements ActionListener {
		
		Card1() {
			sDC = new scrollableDrawingCanvas("virtualtest",ehsConstants.dcMaxX,ehsConstants.dcMaxY,20,20,true);
			getDrawingCanvas().setSubEntity("one");			
			setLayout(new BorderLayout());
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			textPanel.setBorder(BorderFactory.createTitledBorder("Card1"));
			ScrollPane sp = sDC.getComponent();
			sp.setSize(charWidth*visibleDCWidthChars,charHeight*visibleDCHeightChars);
			textPanel.add(sp);
			add(textPanel,"Center");
			
			getDrawingCanvas().showToolWindows();
		}
		public void updateCard1() {}
      	public void actionPerformed(ActionEvent evt) {}
		public void destroyCard1() {}	
	}

 	public class Card2 extends Panel implements ActionListener {
		
		Card2() {
			
			setLayout(new BorderLayout());
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			textPanel.setBorder(BorderFactory.createTitledBorder("Card2"));
			textPanel.add(new JLabel("This Is Card 2"));
			add(textPanel,"Center");
		}
		public void updateCard2() {}
      	public void actionPerformed(ActionEvent evt) {}
		public void destroyCard2() {}	
	}

 	public class Card3 extends Panel implements ActionListener {
		
		Card3() {
			
			setLayout(new BorderLayout());
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			textPanel.setBorder(BorderFactory.createTitledBorder("Card3"));
			textPanel.add(new JLabel("This Is Card 3"));
			add(textPanel,"Center");
		}
		public void updateCard3() {}
      	public void actionPerformed(ActionEvent evt) {}
		public void destroyCard3() {}	
	}

 	public class Card4 extends Panel implements ActionListener {
		
		Card4() {
			
			setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setBorder(BorderFactory.createTitledBorder(""));
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			p.add(new JLabel(systemUserReg.getHTMLInfoText()));
			add(p,"Center");
		}
		public void updateCard4() {}
      	public void actionPerformed(ActionEvent evt) {}
		public void destroyCard4() {}	
	}

	public class helpCard extends Panel {
		private JTextArea	messageWidget;

		helpCard() {
			setLayout(new BorderLayout());
			Panel textPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
			messageWidget = new JTextArea("",26,50);
			messageWidget.setBackground(ehsConstants.lightyellow);
			messageWidget.setWrapStyleWord(true);
			TRACE("Loading help file: "+exHelpFile,4);
			supportFunctions.displayTextFile(exHelpFile,messageWidget);
			messageWidget.append("\n");
			messageWidget.append(systemUserReg.getInfoText());
			messageWidget.setEditable(false);
			JScrollPane pane = new JScrollPane(messageWidget);
			pane.setPreferredSize(new Dimension(50*charWidth,15*charHeight));
			textPanel.add(pane);
			add(textPanel,"Center");
		}
		public void updateHelpCard() {}
		public void destroyHelpCard() {}	
	}

	public void stateChanged(ChangeEvent e) {
		boolean showCard = true;	
				
		if(e.getSource() == tabPane) {			
			if(tabPane.getSelectedComponent() == Tab1) {
			   Tab1.updateCard1();
			}
			if(tabPane.getSelectedComponent() == Tab2) {
				Tab2.updateCard2();
			}
			if(tabPane.getSelectedComponent() == Tab3) {
				Tab3.updateCard3();
			}
			if(tabPane.getSelectedComponent() == Tab4) {
				Tab4.updateCard4();
			}
			if(tabPane.getSelectedComponent() == helpTab) {
				helpTab.updateHelpCard();
			}
			
			if(showCard == false) {
//				tabPane.setSelectedComponent(Tab1);
			}
		}
	}
	
		String appProcessName = "";
		public void startPerApplicationProcess() {
			appProcessName = "EHS-"+"TM-"+supportFunctions.currentShortDate().replace('/', '-')+supportFunctions.currentShortDate().replace(':', '-');
			TRACE("Starting Per Application Processes - " + appProcessName,4);
		}
		public void finishPerApplicationProcess(){
			TRACE("Ending Per Application Processes",4);
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

	public void init() {	
		ehsConstants.bRunAppWithGUI = true;
		setLocation(0,0);
		setSize(ehsConstants.windowXMax,ehsConstants.windowYMax);
		invalidate();
		validate();

		supportFunctions.setNativeLookAndFeel();
		contentPane = getContentPane();
		tabPane = new JTabbedPane();

		systemUserReg = new registrationinfo(appDirectory,"JAVA Template","Template Application","TA1000","02.00.0000.00","16/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "JAVA Template";
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
					   
		modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("Template Loading...");
		
		msgD.setText("Loading Internationalization Database");
		lStrings = new languageStrings();
		
		msgD.setText("Creating User Panels");

		FontMetrics fm = tabPane.getFontMetrics(tabPane.getFont());
		charHeight = fm.getHeight(); // + fm.getAscent(); remove GDB 09/07/2014
		charWidth = fm.stringWidth("O");
		TRACE("init: charWidth=" + Integer.toString(charWidth) + ",charHeight=" + Integer.toString(charHeight),4);

		Tab1 = new Card1();
		Tab2 = new Card2();
		Tab3 = new Card3();
		Tab4 = new Card4();
		helpTab = new helpCard();
		
		contentPane.setLayout(new BorderLayout());
		tabPane.add("Tab 1",Tab1);
		tabPane.add("Tab 2",Tab2);
		tabPane.add("Tab 3",Tab3);
		tabPane.add("Tab 4",Tab4);
		tabPane.add("Help Center",helpTab);
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
		
		//File a = new File("g.txt");
		//FileDialog d = new FileDialog(parentFrame,"title",FileDialog.LOAD); 
		//d.setFile("*.vhdl"); OR
		//d.setFilenameFilter(new FilenameFilter(){
		//    public boolean accept(File dir, String name){
		//      return (name.endsWith(".jpg") || name.endsWith(".gif"));
		//    }
		// });
		//d.setDirectory(defDir);
		//d.show();
		//filename = d.getFile(); OR
		//d.getDirectory() + System.getProperty("file.separator") + fd.getFile();
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
		
	public void symbolNodeClicked(String symbolScoe,String symbolSubScope,String symbolName,String symbolValue) {
	}
	
  	public class templateCompilerTokens extends compilerTokens {
		public String preProcessLine(String line,boolean bKeywords) {
			TRACE("preProcessLine:templateCompilerTokens",4);
			line = basePreProcessLine(line,bKeywords);
			return line;
		}
	}
	public class templateCompiler extends compiler {
		private		pseduoFile	compilerFile;
		protected 	templateCompilerTokens tokenizer;
		protected 	String	compiledLine = "";

		public templateCompiler(String transTableName,String templateReservedWords) {
			super(transTableName,systemUserReg.getAppName());
			tokenizer = new templateCompilerTokens();
			tokenizer.setReservedWords(templateReservedWords);
			setVarPreDefined(true);
			setCommentString("////");
		}
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
		public boolean isValidIdentifier(String ident) {
			if (tokenizer.isReserved(ident)) {return false;}
			return isValidID(ident);
		}
		public boolean syntaxCheck(String line) {
			// strip comments from line
			int index = line.indexOf(getCommentString());
			if (index != -1) {
				line = line.substring(0,index);
			}
			Vector v = tokenizer.tokenizeLine(line);
			syntaxError = "";
			return syntaxCheckInternal(line,v);
		}
		public String getKeywordFromLine(Vector tokens) {
			if (tokens.size() == 0) {return "";}
			// default case first token on line unless first token is a line label
			String tmp = (String)tokens.elementAt(0);
			if (tmp.endsWith(":")) {
				if (tokens.size() > 1) {
					tmp =  (String)tokens.elementAt(1);
				} else {
					return "linelabel";
				}
			}
			return tmp;
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
			if (tokens.size() == 0) {TRACE("syntaxCheckInternal:Empty Line",4);return true;}
		
			String keyword = getKeywordFromLine(tokens);
			
			// process the line using the translation table entry defined by the above keyword
			int status = processLine(line,keyword,tokens);
			if (status != -1) {
				setErrorString("Line:" + String.valueOf(getLineNumber()+1)+" " + line,(String)tokens.elementAt(status));
				return false;
			}
			return true;
		}
		public String getName() {return "Template";}
		public String getFileExt() {return "tem";}
		
		public boolean preCompile(String filename,boolean bHeaders) {
			indentionCount = 0;
						
			if (bHeaders) {		
				symbolTable.removeAllSymbols();
				transTable = new translationTable();
				transTable.loadTranslationTable(transTableName);
			
				compilerFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory,supportFunctions.getFilenameNoExt(filename) + ".out");
				compilerFile.saveFile("<html>\n<head>"); // saveFile used to remove any existing file
				compilerFile.appendFile("\n</head>\n<body>");
			}
			
			// clear compile place holder list
			placeHolders.remove("*e");
			placeHolders.remove("*f");
			placeHolders.remove("*g");
			placeHolders.remove("*h");

			return true;
		}
		public boolean postCompile(String filename,boolean bHeaders) {
			if (bHeaders) {
				compilerFile.flush();
			}
			return true;
		}
		public boolean betweenPassProcesses() {
			if (bProcessHeaders) {
				compilerFile.appendFile("");
			}
			return true;
		}
		public String currentScope() {return "";}
		public boolean completeLine(String line) {return true;}
		public int processTokens(String line,String keyword,Vector lineTokens,translationTableEntry entry) {
			Vector compilerTokens = entry.getTokens();
			//placeHolders = new Properties();
			// clear line only place holder list
			placeHolders.remove("*1");
			placeHolders.remove("*2");
			placeHolders.remove("*3");
			placeHolders.remove("*4");

			Vector expComplierTokens = transTable.expandTokens(compilerTokens);			
			int compilerTokenIndex,lineTokenIndex;
			boolean bNoTransText = false;
			
			if (supportFunctions.strCount(line,'(') != supportFunctions.strCount(line,')')) {
				setExErrorMsg("Missing ( or ) characters");
				return 0;
			}
			
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
				for (int i=0;i<indentionCount;i++) {
					compilerFile.appendFile("\t");
				}
				String s = entry.getpreEntryString();
				//s = s.replaceAll("\\*a",currentScope());
				compilerFile.appendFile(s);
				do {
					lineToken = (String)lineTokens.elementAt(lineTokenIndex);
					if (lineTokenIndex>0) {prevLineToken = (String)lineTokens.elementAt(lineTokenIndex-1);} else {prevLineToken = lineToken;}
					if (lineTokenIndex<lineTokens.size()-1) {nextLineToken = (String)lineTokens.elementAt(lineTokenIndex+1);} else {nextLineToken = lineToken;}
					if (lineToken.length() == 0) {lineTokenIndex++;continue;}
					// check for line labels
					if (lineTokenIndex == 0 && lineToken.endsWith(":")) {
						symbolTable.addSymbol(moduleName,currentScope(),lineToken,String.valueOf(getLineNumber()),symType.LABEL,symClass.NONE);
						compilerFile.appendFile("");
						lineTokenIndex++;
						if (lineTokenIndex < lineTokens.size()) {break;} else {continue;}
					}
					 boolean bTokensMatch = false;
					int multiTransTextIndex = 0;
					
					if (expComplierTokens.size() < compilerTokenIndex) {
						setExErrorMsg("Incomplete translation table entry");
						return lineTokenIndex;
					}
					translationTableToken token = null;
					try {
						token = (translationTableToken)expComplierTokens.elementAt(compilerTokenIndex);
					} catch (Exception e) {supportFunctions.displayMessageDialog(null,"Caught A");}
					//supportFunctions.displayMessageDialog(null,"Compiler Token:"+token.getText());
					Vector v = supportFunctions.splitIntoTokens(token.getText()," ");
					for (int j=0;j<v.size();j++) {
						Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(j),"##",false);
						for (int k=0;k<v1.size();k++) {
							multiTransTextIndex = k;
							complierToken = (String)v1.elementAt(k);
							//supportFunctions.displayMessageDialog(null,"Complier Token:" + complierToken);
							
							// if matching a complier token in a multi-group
							// only use 'break' if we have a match for the token
							// otherwise we will not go on and match the other complier
							// tokens in the multi-group
							if (complierToken.equals("integer")) {
								if (tokenizer.isNumeric(lineToken)) {
									bTokensMatch = true;
									break;
								}
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
//						if (bNoTransText) {transText = "";}
						if (transText.length() != 0) {
							//supportFunctions.displayMessageDialog(null,"tt: " + transText);
							Vector v2 = supportFunctions.splitIntoTokens(transText," ");
							Vector v3 = supportFunctions.splitIntoTokens((String)v2.elementAt(j),"##",false);
							transText = (String)v3.elementAt(multiTransTextIndex);
							
							// don't care about trans text marker
							if (transText.equals("x") || transText.equals("*1") || transText.equals("*2") || transText.equals("*3") || transText.equals("*4") || transText.equals("*e") || transText.equals("*f") || transText.equals("*g") || transText.equals("*h")) {bNoTransText=true;}
							
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
							
							if (bNoTransText) {transText = "";}
							
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
							//if (!bTokensMatch) {lineTokenIndex--;} // ignore optional token only on a non token match
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
				//s = s.replaceAll("\\*a",currentScope());
				compilerFile.appendFile(s);
			}
			return -1; // for success
		}
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
	 		
	public class templateDrawingItem extends drawingItem {
		public templateDrawingItem() {}
		public templateDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeTemplate,id,orgX,orgY,p1,p2,p3,p4,fill,c);
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			  setupPaint(g2d,focus);
			  if (diFilled) {
			  } else {
			  }
			  teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return new Rectangle(0,0,0,0);
		}
	}
	public class templateDrawingCanvas extends drawingCanvas {
		   public templateDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   super(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {
		   } 
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
				if (type == dcTypeTemplate) {
					//d.setType(ehsConstants.dcTypeTemplate);
				}
				
				return (drawingItem)null;
		   }
	}	
	public class scrollableDrawingCanvas {
		  private templateDrawingCanvas	dC;
		  private ScrollPane		  	sPane;
		  private Adjustable			bottomSB,rightSB;
		  
		  public scrollableDrawingCanvas() {
			  this("",ehsConstants.dcMaxX,ehsConstants.dcMaxY,ehsConstants.dcGridSpaceX,ehsConstants.dcGridSpaceY,true);
		  }
		  public scrollableDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean visible) {
			  dC = new templateDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,visible);
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
}
