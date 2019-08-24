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
	public		boolean		bRemoteHosted = false;

	protected static String	dataRelativePath = "..";
	protected static final String	appDirectory = "template";
	protected static final String	appClassName = "template";
	protected static final String	appBaseURL = "http://endhousesoftware.byethost11.com";
	protected static helpDialog helpDlg = null;
	protected static String	exHelpFile=dataRelativePath+"/"+appDirectory+"/"+"documents/help.xml";
	protected static String	splashJPG = dataRelativePath+"/"+appDirectory+"/template_logo.jpg";
	protected static String	rotateJPG = dataRelativePath+"/"+appDirectory+"/rotate.png";

	public	static final String		buildDate = "@@@Build Date: 8-March-2019 05:16 PM Build Number: 21@@@";
	public	static final String		frameworkBuildDate="###JAVA Framework (Version 1.41-RC3)###";
	public 	static final String		gitVersionInfo = "!!!Git Version : 22.1e71052.master-dirty.2019-03-08.17:16:29!!!";
	protected static final String	registrationEmail="endhousesoftware999@gmail.com";
	protected static final String	adminEmail="endhousesoftware999@gmail.com";

	public		static boolean	bRunAppWithGUI = false; 

	protected	Thread		systemThread1;
	protected	Card1		Tab1;
	protected 	Card2		Tab2;
	protected	Card3		Tab3;
	protected	Card4		Tab4;
	protected	helpCard	helpTab;

	protected 	CardLayout 	layout;
	protected 	Panel		cards;

	protected	FontMetrics	fm;
	protected 	int		charWidth;
	protected	int		charHeight;

	protected	registrationinfo systemUserReg = null;
	protected	sounds		systemAudioClips;
	
	protected	AppletContext	ac;
	protected	languageStrings	lStrings;
	protected	Container	contentPane;
	protected	boolean 	runThreads = true;	
	protected	Frame			parentFrame = null;
	
	protected	JTabbedPane	tabPane;

	protected	int maxSoundID;
	protected	javax.swing.Action help,about;
			
	protected String machineID = ""; // test
	
	protected	customIcon			ciHelp;

	protected	 int		TRACELEVEL = 4;
	
	protected	static final int		windowXMax = 1600;
	protected	static final int		windowYMax = 1200;
	protected   static final int 		defaultDialogX = 150;
	protected	static final int 		defaultDialogY = 90;
	protected	static int				dcMaxX = windowXMax;
	protected	static int				dcMaxY = windowYMax;
	protected	static final int		dcGridSpaceX = 20;
	protected	static final int		dcGridSpaceY = 20;

	protected	static final int		sysThreadSleep = 1000;
	protected	static final int		systemSoundThreadPrioity = 8;
	protected	static final int		systemMsgThreadPrioity = 7;

	protected	static final int		defaultMenuX = 100;
	protected	static final int		defaultMenuY = 5;
	protected	static final int		extraSounds = 10;	 

	protected	static final int		dcTypeTemplate = 10;
	protected	static final int 		visibleDCWidthChars = 140;
	protected	static final int 		visibleDCHeightChars = 30;

	protected	static final int		DCMsg = 0;	
	protected	static final int		DCRefresh = 1;	
	protected	static final int		DCLoadFromDB = 0;
	protected	static final int		DCLoadFromXML = 1;

	protected	static final String		IDENTITY_XSLT = "<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'" + " version='1.0'>" + "<xsl:template match='/'><xsl:copy-of select='.'/>" + "</xsl:template></xsl:stylesheet>";

	protected	static String			exFAQFile="";
	
	protected	static final int		iconWidth = 16;
	protected	static final int		iconHeight = 16;
		
	public		static final int		transTableNoJump = 99;
	
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

	public String getTTName() { 
		return "";
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
		
		systemUserReg = new registrationinfo("JAVA Template","Template Application","TA1000","01.41.0000.00","01/02/18","(c) End House Software 2007-2019",bRemoteHosted);
		System.out.println(systemUserReg.getApplicationInfoText() + "\n");
		supportFunctions.connectDatabase(); 
		supportFunctions.getDBConn().connect();
		systemUserReg.registerUser();

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

		writeHitRecord("tem");
		supportFunctions.getDBConn().disconnect();
		System.exit(0);
	}
	public static void main(String[] args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("gui")) {
				bRunAppWithGUI = true;
			}
		}
		if (bRunAppWithGUI) {
			new appletframe(new template(),windowXMax,windowYMax);
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
		systemAudioClips.unloadAllSounds();
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
	  
	public int getSystemVar(String name, int defValue) {
		String data = "";
		data=supportFunctions.getDBConn().executeSQLQuery("SELECT chatVarValue FROM chatvariables WHERE chatVarName='" + name + "'",String.valueOf(defValue));
		
		TRACE("data (getSystemVar) is " + data + " length="+String.valueOf(data.length()),3);
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

		int val = 0;
		try {
		   val = Integer.parseInt(data);
		} catch(Exception e) {e.printStackTrace();}
		return val;
	}

	public boolean getSystemVar(String name, boolean defValue) {
		String data="";
		data = supportFunctions.getDBConn().executeSQLQuery("SELECT chatVarValue FROM chatvariables WHERE chatVarName='" + name + "'",supportFunctions.valueOf(defValue));
		
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

 	    boolean ret = false;
		try {
		   if(Integer.parseInt(data) == 1) {ret = true;}
		} catch (Exception e) {e.printStackTrace();}
		
		return ret;
	}

	public String getSystemVar(String name, String defValue) {
		String data = "";
		data=supportFunctions.getDBConn().executeSQLQuery("SELECT chatVarValue FROM chatvariables WHERE chatVarName='" + name + "'",defValue);
		
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

		return data.trim();
	}

	public String setSystemVar(String name, String val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO chatvariables (chatVarName,chatVarValue) VALUES ('"+name+"','"+val+"')","");
		return val;
	}

	public int setSystemVar(String name, int val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO chatvariables (chatVarName,chatVarValue) VALUES ('"+name+"','"+String.valueOf(val)+"')","");
		return val;
	}
	
	public boolean setSystemVar(String name, boolean val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO chatvariables (chatVarName,chatVarValue) VALUES ('"+name+"','"+supportFunctions.valueOf(val)+"')","");
		return val;
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
	
	public String getAppletInfo() {
		return systemUserReg.getAppCopyright();
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
			sDC = new scrollableDrawingCanvas("virtualtest",dcMaxX,dcMaxY,20,20,true);
			getDrawingCanvas().setSubEntity("one");			
			setLayout(new BorderLayout());
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			textPanel.setBorder(BorderFactory.createTitledBorder("Card1"));
			ScrollPane sp = sDC.getComponent();
			sp.setSize(charWidth*visibleDCWidthChars,charHeight*visibleDCHeightChars);
			textPanel.add(sp);
			add(textPanel,"Center");
			
			getDrawingCanvas().showToolWindows();

			//setCustomColor1();
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

	public class helpCard extends Panel implements ActionListener {
		private JTextArea	messageWidget;
		private	JButton		reportBut;

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
			reportBut = new JButton("End House Software Help Center");
			textPanel.add(reportBut);
			reportBut.addActionListener(this);
			add(textPanel,"Center");
		}
		public void updateHelpCard() {}
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == reportBut) {systemUserReg.sendReport("");}
		}
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
	
public void deleteFilename(String filename) {
	basicFile tmp = new basicFile(filename);
	tmp.deleteFile();
}

		public String getRemoteHostName(String ipaddr) {
			String hostname = "";
			try {
				hostname = InetAddress.getByName(ipaddr).getHostName();
			}
			catch (Exception e) {return "";}
			return hostname;
		}
		public void writeHitRecord(String product) {
			String ipAddr = "0.0.0.0";
			String reverse = getRemoteHostName(ipAddr);
			String tmp = supportFunctions.currentShortDate();
			tmp = tmp.replace('/','-');
			supportFunctions.getDBConn().executeSQLQuery("INSERT INTO sysehswebstats (sysEHSWSID,sysEHSWSProduct,sysEHSWSIP,sysEHSWSDate,sysEHSWSTime,sysEHSWSRef,sysEHSWSReverse) VALUES (null,'"+product+"','"+ipAddr+"','"+tmp+"','"+supportFunctions.currentShortDate()+":00','','"+reverse+"')","");
		}
		
		public Vector removeNumberTokens(Vector v) {
			Vector n = new Vector();
			for (int i=0;i<v.size();i++) {
				try {
					int num = Integer.parseInt((String)v.elementAt(i));
				} catch (Exception e) {
					n.addElement((String)v.elementAt(i));
				}
			}
			
			return n;
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
		bRunAppWithGUI = true;
		setLocation(0,0);
		setSize(windowXMax,windowYMax);
		invalidate();
		validate();

		supportFunctions.setNativeLookAndFeel();
		contentPane = getContentPane();
		tabPane = new JTabbedPane();

		systemUserReg = new registrationinfo("JAVA Template","Template Application","TA1000","01.41.0000.00","01/02/18","(c) End House Software 2007-2019",bRemoteHosted);
		supportFunctions.connectDatabase(); 
		supportFunctions.getDBConn().connect();
		String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSDeptName FROM sysehsdepartments","Admin,Enquiry,Techinical,Sales");
		Vector v = supportFunctions.splitIntoTokens(data);
		systemUserReg.setDepts(v);
		systemUserReg.setPreRelease(true);
		systemUserReg.registerUser();
		systemAudioClips = new sounds();
		
		help = new helpAction();
		about = new aboutAction();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(help);
		helpMenu.add(about);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		if(getSystemVar(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "menubar",0) == 1) {setJMenuBar(menuBar);}
		
		ac = getAppletContext();
					   
		modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("Template Loading...");
		
		msgD.setText("Loading Internationalization Database");
		lStrings = new languageStrings();
		
		msgD.setText("Creating User Panels");

		fm = getFontMetrics(getFont());
		charHeight = fm.getHeight(); // + fm.getAscent(); remove GDB 09/07/2014
		charWidth = fm.stringWidth("O");


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

		maxSoundID = 0;
		systemAudioClips.createNumberSounds(maxSoundID + extraSounds);

		ciHelp = new customIcon();
		int[] x5 = {6,10,10,6};
		int[] y5 = {15,15,13,13};
		customIconData ciD = new customIconData(x5,y5,Color.black);
		ciHelp.addData(ciD);
		int[] x6 = {7,9,9,10,10,11,10,11,11,10,10,9,9,5,5,4,4,3,3,5,5,6,6,9,9,10,10,9,9,8,8,7,7};
		int[] y6 = {12,12,9,9,7,7,6,6,4,4,3,3,1,1,3,3,4,4,8,8,4,4,3,3,4,4,6,6,7,7,8,8,12};
		ciD = new customIconData(x6,y6,Color.black);
		ciHelp.addData(ciD);
		
		writeHitRecord("tem");
		
		msgD.destory();
		msgD.dispose();

		startPerApplicationProcess();
		
		parentFrame = supportFunctions.getTopLevelParent(this);
		machineID = getMachineUniqueIDInternal();

		String data1 = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSProdKBFilename FROM sysehsproducts WHERE sysEHSProdName='" + systemUserReg.getAppName() + "'","");
		exFAQFile = dataRelativePath + "/knowledgebases/" + data1;
		TRACE("init:Knowledgebase file:"+exFAQFile,4);
		
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

	public String getUserUniqueID() {
		return systemUserReg.getAppName() + "#" + systemUserReg.getUserName();
	}
	public String getMachineUniqueIDInternal() {
		if (systemUserReg.getAppRemotedHosted()) {return "1";}
		
		pseduoFile f = new pseduoFile(""+dataRelativePath+"/" + appDirectory + "/.licence");
		String  s = f.loadFile();
		if (s.length() != 0) {return s.trim();}
		
		int tmp = getSystemVar("muniquecount",0);
		setSystemVar("muniquecount",tmp+1);

		pseduoFile f1 = new pseduoFile(""+dataRelativePath+"/" + appDirectory + "/.licence");
		f1.saveFile(String.valueOf(tmp));
		f1.flush();
		
		return String.valueOf(tmp);
	}
	public String getMachineUniqueString() {
		return getMachineUniqueIDInternal();
	}
	public int getMachineUniqueID() {
		try {
			return Integer.parseInt(getMachineUniqueIDInternal());
		} catch (Exception e) {return 0;}
	}

	public int ehsHashCode(String s) {
		int hc = 0;
		for (int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			hc = hc + ch;
		}
		return hc;
	}
	
	public String stringPart(String token) {
		if (token.length() == 0) {return (String)null;}
		
		int index = 0;
		for (int i=0;i<token.length();i++) {
			if (!Character.isDigit(token.charAt(i))) {break;}
			index++;
		}
		if (index == token.length()-1) {return null;}
		return token.substring(index);
	}

	public int numberPart(String token) {
		if (token.length() == 0) {return 0;}

		int index = 0;
		for (int i=0;i<token.length();i++) {
			if (!Character.isDigit(token.charAt(i))) {break;}
			index++;
		}
		if (index == token.length()-1) {return 0;}
		return Integer.parseInt(token.substring(0,index));
	}
	
	public String selectCurrentWord(JTextField tf) {
		   Vector v = getWordStartEndPos(tf.getText(),tf.getCaretPosition());
		   tf.select(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1))+1);
		   return tf.getSelectedText();
	}
	
	public void replaceCurrentWord(JTextField tf,String word) {
		   String oldText = tf.getText();
		   Vector v = getWordStartEndPos(tf.getText(),tf.getCaretPosition());
		   String newText = oldText.substring(0,Integer.parseInt((String)v.elementAt(0))) + word + oldText.substring(Integer.parseInt((String)v.elementAt(1)),oldText.length());
		   tf.setText(newText);		   
	}	
	
	public Vector getWordStartEndPos(String s,int spos) {
	   Vector v = new Vector();
	   if (spos<0 || spos>s.length()) {return v;}
	   StringBuffer b = new StringBuffer(s);
	   int index = 0;
	   for (int i=spos;i>-1;i--) {
	      if (b.charAt(i) == ' ') {index=i+1;break;}
	   }
	   v.addElement(String.valueOf(index));
	   index = s.length()-1;
	   for(int i=spos+1;i<s.length();i++) {
	      if (b.charAt(i) == ' ') {index=i-1;break;}
	   }	   
	   v.addElement(String.valueOf(index));
	   return v;
	}

	public String posSearchStr(String keywords) {
		Vector v = supportFunctions.splitIntoTokens(keywords);
		if (v.size()==0) {return "";}
		String tmp = "POSITION('"+(String)v.elementAt(0)+"' IN sysEHSHelpText)>0";
		if (v.size()==1) {return tmp;}
		for (int i=1;i<v.size();i++) {
			tmp = tmp + " AND POSITION('"+(String)v.elementAt(i)+"' IN sysEHSHelpText)>0";
		}
		return tmp;
	}
	
	public void populateChoice(Choice c,Vector v) {
		c.removeAll();
		for (int i=0;i<v.size();i++) {
			c.addItem((String)v.elementAt(i));
		}
	}
	
	public void tileImage(Graphics g,Component c,Image i) {
		   Rectangle r = c.getBounds();
		   int iw = i.getWidth(c);
		   int ih = i.getHeight(c);
		   if ((iw<=0) || (ih<=0)) {return;}
		   for (int x=0;x<r.width;x+=iw) {
		   	   for (int y=0;y<r.height;y+=ih) {
			   	   g.drawImage(i,x,y,c);
			   }
		   }
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

	private	int 						xTTMargin = 10;
	private	int 						yTTMargin = 10;
	public class transTableCanvasDialog extends JDialog implements drawingCanvasUtils {
		private int 						numRows = 20;
		private int 						numCols = 60;
		private scrollableTransTableCanvas 	sDC1 = null;
		private	drawingCanvas				dc = null;
		private	ScrollPane 					sp = null;
		private	translationTable			table = null;
		private	boolean						bOK = false;
		private JButton						OKBut,CANCELBut;
		private final int					DELETE = -1;
		private final int					INSERT = 1;
		
		public boolean isOK() {return bOK;}
		public void rightClickAction(Vector v,MouseEvent evt) {;}
		public void leftClickAction(drawingItem d,MouseEvent evt) {;}
		public void leftClickSelectedAction(Vector v,MouseEvent evt) {;}
		public void hoverAction(drawingItem d,MouseEvent evt) {;}
		public void customDoCommand(String cmd,String params,drawingItem d) {;}
		public boolean canvasDoubleClickAction(MouseEvent evt) {return false;}
		public boolean canvasLeftClickAction(MouseEvent evt) {return false;}
		public void doubleClickAction(drawingItem d,MouseEvent evt) {
			doubleClickAction((transTableDrawingItem)d);
		}
		public void makeGroup(int groupID,Vector v,transTableTokenType type) {
			for(int i=0;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				if (d.getTTGroupID() == groupID) {
					d.setTokenType(type);
				}
			}
		}
		public boolean isGroup(int groupID,Vector v) {
				int count = 0;
			
			for(int i=0;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				if (d.getTTGroupID() == groupID) {
					if (++count > 1) {return true;}
				}
			}
			
			return false;
		}
		public void adjustCordData(int startIndex,int groupID,Vector v,int signAdjust,boolean bGroup) {
			int changeX = (xTTMargin + ehsConstants.ttDIWidth) * signAdjust;
			int changeY = (yTTMargin + ehsConstants.ttDIHeight) * signAdjust;
			
			//supportFunctions.displayMessageDialog(null,"adjustCordData:si:"+String.valueOf(startIndex)+":gi:"+String.valueOf(groupID)
			//		+":sa:"+String.valueOf(signAdjust)+":bGroup:"+String.valueOf(bGroup));
			
			for(int i=startIndex;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				Rectangle rc = d.getBoundingRect();
				
				if (bGroup) {
					if (d.getTTGroupID() == groupID) {
						rc.y = rc.y + changeY;
						d.setBoundingRect(rc);
					}
				} else {
					rc.x = rc.x + changeX;
					d.setBoundingRect(rc);
					d.setTTGroupID(d.getTTGroupID() + signAdjust);
				}
			}
		}
		public Window getActiveWindow() {
	         final KeyboardFocusManager currentKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	         final Window activeWindow = currentKeyboardFocusManager.getActiveWindow();
	         
	         return activeWindow;
		}
		public void doubleClickAction(transTableDrawingItem d) {
			Vector tmp = supportFunctions.splitIntoTokens(d.getUserDefinedName(),":");
			Vector v = transTableMap.get((String)tmp.elementAt(0));
			//supportFunctions.displayMessageDialog(null,"Num Tokens (get)"+String.valueOf(v.size()));
			if(v != null) {
				int index = v.indexOf(d);
				if (index == 0) {
					supportFunctions.displayMessageDialog(null,"Can not edit keyword token.");
					return;
				}
				if(index != -1) {					
					int groupID = d.getTTGroupID();
					Rectangle rc = d.getBoundingRect();
					int xCord = rc.x;
					int yCord = rc.y;
					
					JPanel p = new JPanel();
					p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
					JButton closeBut = new JButton("Close");
					closeBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getActiveWindow().dispose();							
						}
					});
					JButton editorBut = new JButton("Editor");
					editorBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							d.editor();
							getActiveWindow().dispose();							
						}
					});
					JButton deleteBut = new JButton("Delete");
					deleteBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean bGroup = isGroup(groupID,v);
							getDC().deleteDrawingItem((drawingItem)d);
							v.removeElementAt(index);
							adjustCordData(index,groupID,v,DELETE,bGroup);
							if(isGroup(groupID,v)) {
								//supportFunctions.displayMessageDialog(null,"Group ID "+String.valueOf(groupID)+" is a group");
							} else {
								//supportFunctions.displayMessageDialog(null,"Group ID "+String.valueOf(groupID)+" is NOT a group");
								makeGroup(groupID,v,transTableTokenType.NORMAL);
							}
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					JButton insertBeforeBut = new JButton("Insert");
					insertBeforeBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// groupID is one more then element clicked on 
							// xCord is xCord + xMargin + ehsConstants.ttDIWidth
							// yCord is the same as element double clicked on
							transTableDrawingItem dNew = (transTableDrawingItem)getDC().addDrawingItem(
								getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord + xTTMargin + ehsConstants.ttDIWidth,yCord,
								(String)tmp.elementAt(0) + ":" + "newtext",
								"*c","x",
								String.valueOf(groupID+1),false,Color.black);
							dNew.setTokenType(transTableTokenType.NORMAL);
							dNew.setCanTransform(false);
							dNew.setPreString("");
							dNew.setPostString("");
							dNew.setUserDefinedName((String)tmp.elementAt(0) + ":" + "newtext");
							v.insertElementAt(dNew,index+1);
							adjustCordData(index + 2,groupID,v,INSERT,false); //+2 to start from element after inserted one
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					JButton insertGroupBut = new JButton("Insert Group");
					insertGroupBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// groupID is the same as the element double clicked on 
							// xCord is the same as element click on
							// yCord is yCord + yMargin + ehsConstants.ttDIHeight
							transTableDrawingItem dNew = (transTableDrawingItem)getDC().addDrawingItem(
								getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord,yCord + yTTMargin + ehsConstants.ttDIHeight,
								(String)tmp.elementAt(0) + ":" + "newtext",
								"*c","x",
								String.valueOf(groupID),false,Color.black);
							dNew.setTokenType(transTableTokenType.MULTIPLE);
							d.setTokenType(transTableTokenType.MULTIPLE);
							dNew.setCanTransform(false);
							dNew.setPreString("");
							dNew.setPostString("");
							dNew.setUserDefinedName((String)tmp.elementAt(0) + ":" + "newtext");
							v.insertElementAt(dNew,index+1);
							adjustCordData(index + 2,groupID,v,INSERT,true); //+2 to start from element after inserted one
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(new JLabel("<html><b>Keyword:"+(String)tmp.elementAt(0)+"</html></b>"));
					p.add(new JLabel("<html><b>Text:"+d.getText()+"</html></b>"));
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(deleteBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(insertBeforeBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(insertGroupBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(editorBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(closeBut);
					supportFunctions.displayPanelDialog(null,p,"Edit TT Element",true);
					
					//supportFunctions.displayMessageDialog(null,"Num Tokens (put)"+String.valueOf(v.size()));
					transTableMap.put((String)tmp.elementAt(0),v);								
				} else {
					supportFunctions.displayMessageDialog(null,"Token DI for keyword ("+(String)tmp.elementAt(0)+") NOT found");
				}
			} else {
				supportFunctions.displayMessageDialog(null,"Keyword ("+(String)tmp.elementAt(0)+") NOT Found");
			}
		}
		public void processTransTableDrawingItems() {	
			int xCord = 0;
			int yCord = 0;
			int yCordMax = 0;
			
			transTableMap.clear();
			modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog(systemUserReg.getAppName());
			int numTokens = 0;
			Vector keywords = table.getKeywords();
			for (int i=0;i<keywords.size();i++) {
				int groupID = 0;
				Vector v = new Vector();
				xCord = xTTMargin;
				yCord = yCordMax + yTTMargin + ehsConstants.ttDIHeight;
				int yCordStart = yCord;
				yCordMax = yCord;
				TRACE("processTransTableDrawingItems:keyword:"+(String)keywords.elementAt(i),4);
				TRACE("processTransTableDrawingItems:entity:"+getDC().getEntity(),4);
				msgD.setText("Processing translation table keyword:" + (String)keywords.elementAt(i));
				//supportFunctions.displayMessageDialog(null,"processTransTableDrawingItems:key:"+(String)keywords.elementAt(i));
				transTableDrawingItem d = (transTableDrawingItem)getDC().addDrawingItem(getDC().getEntity(),ehsConstants.dcTypeTransTable,
						xCord,yCord,(String)keywords.elementAt(i) + ":" + (String)keywords.elementAt(i),"","",
						String.valueOf(groupID++),false,Color.black);
				d.setTokenType(transTableTokenType.KEYWORD);
				d.setCanTransform(false);
				d.setPreString(table.getTranslationTableEntry((String)keywords.elementAt(i)).getpreEntryString());
				d.setPostString(table.getTranslationTableEntry((String)keywords.elementAt(i)).getpostEntryString());
				d.setUserDefinedName((String)keywords.elementAt(i) + ":" + (String)keywords.elementAt(i));
				v.addElement(d);

				Vector tokens = table.getTokens((String)keywords.elementAt(i));				
				numTokens = numTokens + tokens.size();
				for (int j=0;j<tokens.size();j++) {
					translationTableToken token = (translationTableToken)tokens.elementAt(j);
					xCord = xCord + xTTMargin + ehsConstants.ttDIWidth;
					Vector syntaxStrings = supportFunctions.splitIntoTokens(token.getText(),"##",false);
					TRACE("processTransTableDrawingItems:"+token.getText()+":Tokens:"+String.valueOf(syntaxStrings.size()),4);
					
					String transTextStrings1 = token.getTransText();
					if (transTextStrings1.length() == 0) {
						transTextStrings1 = "*c" + supportFunctions.stringRepeat("##*c",syntaxStrings.size()-1);
					}
					String flagStrings1 = token.getFlags();
					if (flagStrings1.length() == 0) {
						if (j == tokens.size()-1) { // last token should have a 'y' finish processing flag
							flagStrings1 = "y" + supportFunctions.stringRepeat("##y",syntaxStrings.size()-1);																						
						} else {
							flagStrings1 = "x" + supportFunctions.stringRepeat("##x",syntaxStrings.size()-1);															
						}
					}
					
					Vector transTextStrings = supportFunctions.splitIntoTokens(transTextStrings1,"##",false);
					Vector flagsStrings = supportFunctions.splitIntoTokens(flagStrings1,"##",false);
					
					for (int part=0;part<syntaxStrings.size();part++) {
						if (part > 0) {
							yCord = yCord + yTTMargin + ehsConstants.ttDIHeight;
							yCordMax = yCord;
						}
						String syntax = (String)syntaxStrings.elementAt(part);
						String transText = (String)transTextStrings.elementAt(part);
						String flags = (String)flagsStrings.elementAt(part);
						String id = (String)keywords.elementAt(i) + ":" + syntax;

						transTableTokenType type = transTableTokenType.NORMAL;
						if (syntaxStrings.size() > 1) {
							type = transTableTokenType.MULTIPLE;
						}
						if (flags.indexOf("z") != -1) {type = transTableTokenType.OPTIONAL;} // check for optional token
						
						d = (transTableDrawingItem)getDC().addDrawingItem(getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord,yCord,id,transText,flags,String.valueOf(groupID),false,Color.black);

						d.setTokenType(type);
						d.setCanTransform(false);
						d.setPreString("");
						d.setPostString("");
						d.setUserDefinedName(id);
						v.addElement(d);
					} // for all parts
					groupID++;
					yCord = yCordStart;
				} // for all tokens
				transTableMap.put((String)keywords.elementAt(i),v);
			} // for all keywords
			
			msgD.destory();
			msgD.dispose();
			//supportFunctions.displayMessageDialog(null,"Keywords: " + String.valueOf(keywords.size()) + " Tokens: " + String.valueOf(numTokens));
		}
		public drawingCanvas getDC() {return dc;}
		public ScrollPane getScrollPane() {return sp;}
		public transTableCanvasDialog(Frame parent,String title,String entity,int xMax,int yMax,translationTable table) {
			super(parent,title,true);
			
			this.table = table;
						
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			
			sDC1 = new scrollableTransTableCanvas(entity,xMax,yMax,20,20,false);

			dc = sDC1.getDC();
			sp = sDC1.getComponent();

			processTransTableDrawingItems();
			
			getDC().hideToolWindows();
			getDC().setMultiUser(false);
			getDC().setUseDatabase(false);
			getDC().setReadOnly(false);
			getDC().addDrawingCanvasListener(this);
			getDC().setDrawFocusHandles(false);
			getDC().setSubEntity("subentity");
			
			getScrollPane().setSize(charWidth*numCols,charHeight*numRows);
			p.add(getScrollPane());
			p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
			JPanel p1 = new JPanel();
			p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
			OKBut = new JButton("Ok");
			p1.add(OKBut);
			CANCELBut = new JButton("Cancel");
			p1.add(CANCELBut);
			p.add(p1);
			add(p);

			ActionListener OKTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bOK = true;
					dispose();
			   }
			};
			OKBut.addActionListener(OKTask);
			ActionListener CANCELTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					dispose();
			   }
			};
			CANCELBut.addActionListener(CANCELTask);

 			addWindowListener(new WindowAdapter() {
 				public void windowClosing(WindowEvent evt) {
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

	public class transTableDrawingCanvas extends drawingCanvas {
		private ArrayList<Integer> l = new ArrayList<Integer>();
		
		   public transTableDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   super(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public boolean doPaint(drawingItem d) {
			   if (d.getType() <= ehsConstants.dcTypeBuiltInMaxId) {return true;} //built in types always drawn

			   return true;
		   } 
		   public boolean printOverride(Graphics2D g2d) {
			   g2d.drawImage(getCurrentCanvasImage(),0,0,canvasMaxX,canvasMaxY,null);
			   return true;
		   }
			public int calcJump(transTableDrawingItem d) {
				int jump = 1; // default jump value
				String flags = d.getFlags();
				
				int index = flags.indexOf("r");
				if (index != -1) {
					jump = Integer.parseInt(flags.substring(index+1,index+3));
				}
				if (flags.indexOf("y") != -1) {return transTableNoJump;}
				
				if (jump != 0) {
					if (jump > 0) { // forward jump
						jump = l.get(d.getTTGroupID()) - d.getCount();
					} else { // backward jump
						jump = jump - d.getCount();
					}
				}
				
				return jump;
			}
			public Vector getTransTableGroupItems(Vector values,int group) {
				Vector v = new Vector();
				
				for(int i=0;i<values.size();i++) {
					transTableDrawingItem di = (transTableDrawingItem)values.elementAt(i);
					if (group == di.getTTGroupID()) {
						v.addElement(di);
					}
				}
				
				return v;
			}
			public void drawTransTableConnectors(Graphics2D g2d) {
				for (Map.Entry<String, Vector> entry : transTableMap.entrySet()) {
					String key = entry.getKey();
					Vector value = entry.getValue();
					l.clear();
					int currentGroup = 0;
					int count = 0;
					for(int i=0;i<value.size();i++) {
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(i);
						if (currentGroup == di.getTTGroupID()) {
							di.setCount(count++);
						} else {
							l.add(count);
							TRACE("drawTransTableConnectors:count:"+String.valueOf(count),4);
							currentGroup = di.getTTGroupID();
							count = 0;
							di.setCount(count++);
						}
						if (i == value.size()-1) {// add the count for the LAST TT DI Element
							l.add(count);
							TRACE("drawTransTableConnectors:count:"+String.valueOf(count),4);
						}
					}
					for(int i=0;i<value.size();i++) {
						
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(i);
						int jump = calcJump(di);
						
						TRACE("drawTransTableConnectors:i:"+String.valueOf(i)+":keyword:"+key+":value:"
								+di.getText()+":flags:"+di.getFlags()+":group:"+di.getTTGroupID()
								+":count:"+di.getCount()+":jump:"+String.valueOf(jump),4);

						if (jump != transTableNoJump) {
							transTableDrawingItem diTo = (transTableDrawingItem)value.elementAt(i + jump);
														
							if (jump == 0) {
								Vector v = getTransTableGroupItems(value,di.getTTGroupID());
								if (v.size() > 2) {
									transTableDrawingItem diFirst = (transTableDrawingItem)v.elementAt(0);
									transTableDrawingItem diLast = (transTableDrawingItem)v.elementAt(v.size() - 1);
									Rectangle rcFirst = diFirst.getBoundingRect();
									Rectangle rcLast = diLast.getBoundingRect();
									Point ptFirst = centerPoint(rcFirst);
									Point ptLast = centerPoint(rcLast);
									int m = (ehsConstants.ttDIHeight / 2) + (yTTMargin / 2);
									int m1 = (int)(diFirst.getBoundingRect().width / 2.0) + (int)(xTTMargin / 2.0);
									
									g2d.drawLine(ptFirst.x,ptFirst.y,ptFirst.x,ptFirst.y-m);
									g2d.drawLine(ptFirst.x,ptFirst.y-m,ptFirst.x+m1,ptFirst.y-m);
									
									g2d.drawLine(ptFirst.x+m1,ptFirst.y-m,ptLast.x+m1,ptLast.y+m);
									
									g2d.drawLine(ptLast.x+m1,ptLast.y+m,ptLast.x,ptLast.y+m);
									g2d.drawLine(ptLast.x,ptLast.y+m,ptLast.x,ptLast.y);

								}
							}
							if (jump == -1 || jump == 1) {
								Point ptStart = centerPoint(di.getBoundingRect());
								Point ptEnd = centerPoint(diTo.getBoundingRect());
								g2d.drawLine(ptStart.x,ptStart.y,ptEnd.x,ptEnd.y);
							}
							if (jump < -1  || jump > 1) {
								Point ptStart1 = centerPoint(di.getBoundingRect());
								Point ptEnd1 = centerPoint(diTo.getBoundingRect());
								// use 1.5 instead of 2.0 because otherwise the connector would merge with other connectors
								double length = (di.getBoundingRect().height / 2.0) + (yTTMargin / 1.5);
								g2d.drawLine(ptStart1.x,ptStart1.y,ptStart1.x,(int)(ptStart1.y - length));
								g2d.drawLine(ptStart1.x,(int)(ptStart1.y - length),ptEnd1.x,(int)(ptEnd1.y - length));
								g2d.drawLine(ptEnd1.x,(int)(ptEnd1.y - length),ptEnd1.x,ptEnd1.y);
							}
						}
						//supportFunctions.displayMessageDialog(null,"pause");
					}
				}		
			}
		   public void beforePainting(Graphics2D g2d) {
			   drawTransTableConnectors(g2d);
		   }
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {;}
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
				if (type == ehsConstants.dcTypeTransTable) {
					transTableDrawingItem d = new transTableDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					return d;
				}
				
				return (drawingItem)null;
		   }
		   public PopupMenu customCreateMenu(PopupMenu menu) {
			   menu.removeAll();
			   
			   return menu;
		   }
		   public void customUpdateMenu(PopupMenu menu) {
			   
		   }
	}
	public class scrollableTransTableCanvas {
		  private transTableDrawingCanvas  	dC;
		  private ScrollPane		  		sPane;
		  private Adjustable				bottomSB,rightSB;
		  
		  public scrollableTransTableCanvas() {
			  this("",dcMaxX,dcMaxY,dcGridSpaceX,dcGridSpaceY,true);
		  }
		  public scrollableTransTableCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean visible) {
			  dC = new transTableDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,visible);
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
		  public void setSize(Dimension d) {dC.setSize(d);}
		  public Dimension getSize() {return dC.getSize();}
	}
	public		Map<String,Vector>		transTableMap = new HashMap<String,Vector>();
	public void displayTransTableEditor() {
		int xMax = 5000;
		int yMax = 5000;
		translationTable transTable = new translationTable();
		transTable.loadTranslationTable(getTTName());
		
		if (transTable != null) {
			JPanel keyPanel = new JPanel();
			basicFile f = new basicFile(dataRelativePath + "/" + appDirectory + "/documents/ttkey.txt");
			JTextArea text = new JTextArea(f.loadFile(),10,30);
			text.setEditable(false);
			text.setBackground(ehsConstants.lightyellow);
			keyPanel.add(text);
			floatingWindow keyWindow = new floatingWindow(keyPanel,"Trans Table Key");
			keyWindow.setLocation(0,0);
			transTableCanvasDialog d = new transTableCanvasDialog(null,"Trans Table Editor","entity",xMax,yMax,transTable);			
			keyWindow.destory();
			if (d.isOK()) {
				xmlDataFile ttItems = new xmlDataFile();
				if (ttItems.openXMLDataFile(supportFunctions.getPathFilenameNoExt(getTTName()),"transtable",true)) {
					org.w3c.dom.Element root = ttItems.getRootElement();
					org.w3c.dom.Document doc = ttItems.getXMLDocument();
					for (Map.Entry<String, Vector> entry : transTableMap.entrySet()) {
						String key = entry.getKey();
						Vector value = entry.getValue();
						// assume first DI item is the keyword
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(0);
						org.w3c.dom.Element keywordnode = doc.createElement("ttkeyword");
						keywordnode.setAttribute("keyword",key);
						root.appendChild(keywordnode);
						
						if (value.size() > 1) {
							for (int i=1;i<value.size();i++) {
								di = (transTableDrawingItem)value.elementAt(i);
								org.w3c.dom.Element node = doc.createElement("ttentry");
								node.setAttribute("syntax",di.getText());
								node.setAttribute("transtext",di.getTransText());
								node.setAttribute("flags",di.getFlags());
								node.setAttribute("prestring",di.getPreString());
								node.setAttribute("poststring",di.getPostString());
								node.setAttribute("groupid",String.valueOf(di.getTTGroupID()));
								keywordnode.appendChild(node);
							}
						}
					}
					
					ttItems.saveXMLDataFile();
					ttItems.closeXMLDataFile();
					supportFunctions.displayMessageDialog(null,"XML Translation Table File Saved, Tokens:" 
							+ String.valueOf(transTableMap.size()));
				}
			}
		} else {
			supportFunctions.displayMessageDialog(null,"Can not load translation table");
		}
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
	
/**
 * Represents a Tree of Objects of generic type T. The Tree is represented as
 * a single rootElement which points to a List<Node<T>> of children. There is
 * no restriction on the number of children that a particular node may have.
 * This Tree provides a method to serialize the Tree into a List by doing a
 * pre-order traversal. It has several methods to allow easy updation of Nodes
 * in the Tree.
 */
public class Tree<T> {
 
    private Node<T> rootElement;
     
    /**
     * Default ctor.
     */
    public Tree() {
        super();
    }
 
    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public Node<T> getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     */
    public void setRootElement(Node<T> rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Returns the Tree<T> as a List of Node<T> objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List<Node<T>>.
     */
    public java.util.List<Node<T>> toList() {
        java.util.List<Node<T>> list = new ArrayList<Node<T>>();
        walk(rootElement, list);
        return list;
    }
     
    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public String toString() {
        return toList().toString();
    }
     
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(Node<T> element, java.util.List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            walk(data, list);
        }
    }
}

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
public class Node<T> {
 
    public T data;
    public java.util.List<Node<T>> children;
 
    /**
     * Default ctor.
     */
    public Node() {
        super();
    }
 
    /**
     * Convenience ctor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public Node(T data) {
        this();
        setData(data);
    }
     
    /**
     * Return a list of the children of Node<T>. The Tree<T> is represented by a single
     * root Node<T> whose children are represented by a List<Node<T>>. Each of
     * these Node<T> elements in the List can have children. The getChildren()
     * method will return a list of the children of a Node<T>.
     * @return a list of the children of Node<T>
     */
    public java.util.List<Node<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    public void setChildren(java.util.List<Node<T>> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
     
    /**
     * Adds a child to the list of children for this Node<T>. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a Node<T> object to set.
     */
    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        children.add(child);
    }
     
    /**
     * Inserts a Node<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the Node<T> element at index index of the List<Node<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
     
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append(getData().toString()).append(",[");
		int i = 0;
		for (Node<T> e : getChildren()) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(e.toString());
			i++;
		}
		sb.append("]").append("}");
		return sb.toString();
	}
}

	public class registrationinfo {
		private boolean appUseDatabase;
		private	boolean	appRemoteHosted;
		private	String 	userID;
		private	String	userName;
		private	String	userSerialNumber;
		private	String	userRawSerialNumber;
		private	String	appName;
		private String  appDescription;
		private String  appSerialBase;
		private	String	appVersion;
		private	String	appDate;
		private	String	appCopyright;
		private String 	statusMsg;
		private String  lastLogonDetails;
		private	boolean	appPreRelease;
		private Vector 	depts = new Vector();
		private Vector 	rptTypes = new Vector();
		private Vector	rptStatus = new Vector();
		private int	numAttemptsLeft,userCredit,features; 
		private	userManager userMan;
		
	public class userManager {
		public String[] getUserListByTag(String tag) {
			String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID,sysEHSRegName,sysEHSRegEmail,sysEHSRegProduct FROM sysehsregistrations WHERE sysEHSRegTag='"+tag+"' GROUP BY sysEHSRegEmail","");
			
			Vector v = supportFunctions.splitIntoTokens(data,",");
		    String[] tokens = new String[v.size() / 4];
			
			int j = 0;
			for (int i=0;i<v.size();i=i+4) {
				tokens[j++] = (String)v.elementAt(i) + "," + (String)v.elementAt(i+1) + "," + (String)v.elementAt(i+2) + "," + (String)v.elementAt(i+3) + "," + (String)v.elementAt(i+4);
			}
			
			return tokens;
		}
		public String[] getUserListByProduct(String product) {
			String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID,sysEHSRegName,sysEHSRegEmail,sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' GROUP BY sysEHSRegEmail","");
			
			Vector v = supportFunctions.splitIntoTokens(data,",");
		    String[] tokens = new String[v.size() / 4];
			
			int j = 0;
			for (int i=0;i<v.size();i=i+4) {
				tokens[j++] = (String)v.elementAt(i) + "," + (String)v.elementAt(i+1) + "," + (String)v.elementAt(i+2) + "," + (String)v.elementAt(i+3) + "," + (String)v.elementAt(i+4);
			}
			
			return tokens;
		}
	}
		registrationinfo(String name,String description,String serialbase,String version,String date,String cr,boolean internet) {
			numAttemptsLeft = -1;
			appUseDatabase = true;
			appRemoteHosted = internet;
			appName = name;
			appDescription = description;
			appSerialBase = serialbase;
			appVersion = version;
			appDate = date;
			appCopyright = cr;
			appPreRelease = false;
			features = 0;
			userCredit = 0;
			statusMsg = "";
			rptTypes = supportFunctions.splitIntoTokens("Action Item,Defect,Contact Us,Documentation,Enhancement,Feature,Comment,Help File,Knowledge Base,Manual,Order,Quote,Sales,Purchase Order,Equipment Order,Supplies Order,Tech Support,Trainning,Website Design");
			rptStatus = supportFunctions.splitIntoTokens("Unassigned,Open,On Order,In Process,Coding,Documentation,Testing,QA,Closed");
			patchBuildNumber();
			clearRegistrationData();
			supportFunctions.createAppConfigSettings(""+dataRelativePath+"/"+appDirectory+"/"+name+"_settings");
			userMan = new userManager();
		}
		public void setUserTag(String s) {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				Vector v = supportFunctions.splitIntoTokens(data,",");
				String[] tokens = new String[v.size()];
				v.copyInto(tokens);
				
				String newTag = "";
				boolean bTagFound = false;
				for (int i=0;i<tokens.length;i=i+2) {
					if (i != 0) {newTag = newTag + ",";}
					newTag = newTag + tokens[i] + ",";
					if (tokens[i].equals(getAppName())) {newTag = newTag + s;bTagFound = true;} else {newTag = newTag + tokens[i+1];}
				}
				
				if (!bTagFound) {
					if (newTag.length() != 0) {newTag = newTag + ",";}
					newTag = newTag + getAppName() + "," + s;
				}
				
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegTag='" + newTag + "' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
			}	
		}
		public String getUserTag() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				Vector v = supportFunctions.splitIntoTokens(data,",");
				String[] tokens = new String[v.size()];
				v.copyInto(tokens);
				
				for (int i=0;i<tokens.length;i=i+2) {
					if (tokens[i].equals(getAppName())) {return tokens[i+1];}
				}
			}
			
			return "";
		}
		public void setAllowEmails(boolean b) {
			if (getUserRegistered()) {
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegReceiveEmailUpdates=" + String.valueOf(b) + " WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
			}
		}
		public boolean getAllowEmails() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegReceiveEmailUpdates FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				return supportFunctions.valueOf(data);
			}
			
			return false;
		}
		protected void finalize() throws Throwable {
			super.finalize();
		}
		public userManager getUserManager() {return userMan;}
		public configurationSettings getConfigurationSettings() {return supportFunctions.getAppConfigSettings();}
		public String getLastLogonDetails() {return lastLogonDetails;}
		public String getAppSerialBase() {return appSerialBase;}
		public String getSerialNumber() {return userSerialNumber;}
		public int isTrial() {return numAttemptsLeft;}
		public String getInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease)";}
			if(getUserRegistered()) {
				return appName + "\n" + appVersion + " (" + appDate + ") " + extra + "\n" + appCopyright + "\n" + userName + "\n" + userSerialNumber + "\n" + getBuildString() + "\n" + getframeworkBuildString();
			} else {
				return appName + "\n" + appVersion + " (" + appDate + ") " + extra + "\n" + appCopyright + "\n" + "Unregistered Version" + "\n" + getBuildString() + "\n" + getframeworkBuildString();
			}
		}
		public String getHTMLInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease)";}
			if(getUserRegistered()) {
				return "<html><center><font color='red'><font size='+3'><i>" + appName + "</i></font></font><br><font color='blue'><font size='+2'>" + appVersion + " (" + appDate + ")" + " " + extra + "</font></font><br><br><b>" + appCopyright + "<br>" + userName + " " + userSerialNumber + "</b><br>" + getBuildString() + "<br><font size='-1'>" + getframeworkBuildString() + "</font></center></html>";
			} else {
				return "<html><center><font color='red'><font size='+3'><i>" + appName + "</i></font></font><br><font color='blue'><font size='+2'>" + appVersion + " (" + appDate + ")" + " " + extra + "</font></font><br><br><b>" + appCopyright + "<br>" + "Unregistered Version</b><br>" + getBuildString() + "<br><font size='-1'>" + getframeworkBuildString() + "</font></center></html>";
			}
		}
		public String getApplicationInfoText() {
			return appName + " " + appVersion + " " + appCopyright;
		}
		public String getSingleLineInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease) ";}
			if(getUserRegistered()) {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + " " + userName + " " + userSerialNumber + " " + getBuildString() + " " + getframeworkBuildString();
			} else {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + " " + "Unregistered Version" + " " + getBuildString() + " " + getframeworkBuildString();
			}
		}
		public String getMultiLineInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease) ";}
			if(getUserRegistered()) {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + "\n" + userName + " " + userSerialNumber + "\n" + getBuildString() + " " + getframeworkBuildString();
			} else {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + "\n" + "Unregistered Version" + "\n" + getBuildString() + " " + getframeworkBuildString();
			}
		}
		public String getStatusMsg() {return statusMsg;}
		public void setStatusMsg(String msg) {statusMsg = msg;}
		public int getFeatures() {return features;}
		public void setFeatures(int f) {features = f;}
		public Vector getDepts() {return depts;}
		public Vector getRptTypes() {return rptTypes;}
		public void setDepts(Vector v) {depts = v;}
		public void setRptTypes(Vector v) {rptTypes = v;}
		public boolean getPreRelease() {return appPreRelease;}
		public void setPreRelease(boolean b) {appPreRelease=b;}
		public boolean getUseDatabase() {return appUseDatabase;}
		public void setUseDatabase(boolean b) {appUseDatabase=b;}
		public boolean isOffline() {return !appRemoteHosted;}
		public String getUserName() {return userName;}
		public String getUserID() {return userID;}
		public void setUserID(String s) {userID = s;}
		public void clearRegistrationData() {
			   userName = "unregistered";
			   userID = "";
			   userRawSerialNumber = "";
			   userSerialNumber = "";
			   lastLogonDetails = "";
			   userCredit = 0;
		}
		public void initUserCredit() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegCredit FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+ userName+"'","0");
				try {
					userCredit = Integer.parseInt(data);
				} catch (Exception e) {userCredit = 0;}
			} else {userCredit = 0;}
		}
		public int getUserCredit() {return userCredit;}
		public void setUserCredit(int val) {
			if (getUserRegistered()) {
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegCredit="+String.valueOf(val)+" WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				userCredit = val;
			}
			else {
				supportFunctions.displayMessageDialog(null,"User is not registered");
			}
		}
		public void deleteProductEntry(String productCode) {
			supportFunctions.getDBConn().executeSQLQuery("DELETE FROM sysehsproducts WHERE sysEHSProdCode='"+productCode+"'","");
		}
		public void createProductEntry(String productName,String productDescription,String productSerialBase,int productPrice,int productBeta,int productTries,String productVersion,String productUpdateURL,String productPaymentURL,int productClient,String productClientEmail,String productClientName,String productCode,String productAdminUser,String productAdminPassword,int features) {
			supportFunctions.getDBConn().executeSQLQuery("INSERT INTO sysehsproducts (sysEHSProdID,sysEHSProdName,sysEHSProdDescription,sysEHSProdSerialBase,sysEHSProdPrice,sysEHSProdBeta,sysEHSProdTries,sysEHSProdVersion,sysEHSProdUpdateURL,sysEHSProdPaymentURL,sysEHSProdClient,sysEHSProdClientEmail,sysEHSProdClientName,sysEHSProdCode,sysEHSProdAdminUser,sysEHSProdAdminPassword,sysEHSProdFeatures) VALUES (NULL,'"+productName+"','"+productDescription+"','"+productSerialBase+"',"+String.valueOf(productPrice)+","+String.valueOf(productBeta)+","+String.valueOf(productTries)+",'"+productVersion+"','"+productUpdateURL+"','"+productPaymentURL+"',"+String.valueOf(productClient)+",'"+productClientEmail+"','"+productClientName+"','"+productCode+"','"+productAdminUser+"','"+productAdminPassword+"',"+String.valueOf(features)+")","");
		}
		public boolean productEntryExists(String productCode) {
			String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSProdDescription FROM sysehsproducts WHERE sysEHSProdCode='"+productCode+"'","");
			if(data.length()==0) return false; else return true;
		}
		public String getAppCopyright() {
			return appCopyright;
		}
		public boolean getAppRemotedHosted() {
			return appRemoteHosted;
		}
		public String getAppName() {
			   return appName;
		}
		public String getAppNameVersion() {
			   return appName + " " + appVersion;
		}
		public String getAppVersion() {
				return appVersion;
		}
		public int getMinorVersionNumber() {
			return getMinorVersionNumber(appVersion);
		}
		public int getMajorVersionNumber() {
			return getMajorVersionNumber(appVersion);
		}
		public int getMinorVersionNumber(String version) {
			int val = 0;
			try {
				val = Integer.parseInt(version.substring(3,5));
			} catch(Exception e) {e.printStackTrace();}
			return val;
		}
		public int getMajorVersionNumber(String version) {
			int val = 0;
			try {
				val = Integer.parseInt(version.substring(0,2));
			} catch(Exception e) {e.printStackTrace();}
			return val;
		}
		public String isUserRegistered(String user,String product) {
			   return supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegEmail FROM sysehsregistrations WHERE sysEHSRegName='"+user+"' AND sysEHSRegProduct='"+product+"'","");
		}
		public void saveRegistrationData() {
		   if (getUseDatabase()) {
		   } else {
//			   	 char[] buf = new char[4096];
//			   	 try {
//				 	 File f = new File(appName+userName+".lic");
//				 	 FileWriter out = new FileWriter(f);
//					 if (out != null) {
//					 	String s = userRawSerialNumber + "," + userName;
//					 	StringBuffer sb = new StringBuffer(s);
//						int len = s.length();
//						for(int i=0;i<len;i++) {buf[i]=sb.charAt(i);}
//						out.write(buf,0,len);
//					 	out.close();
//					}
//				 } catch (Exception e) {e.printStackTrace();}
		   }
		}
		public String getRawSerialNumber() {return userRawSerialNumber;}
		public boolean loadRegistrationData(String username,String password) {
			String data = "";
//			if (getUseDatabase()) {
				data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegSerial,sysEHSRegName,sysEHSRegPassword,sysEHSRegLogonDate,sysEHSRegLogonTime,sysEHSRegUserName FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"' AND sysEHSRegActive=1","");
				// assume at the moment passwords match, but need to check that !!!
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonDate='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"'","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonTime='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"'","");
				TRACE("LRD:"+data,4);
				if (data.length() == 0) { // user not yet registered
					TRACE("LRD:User not yet registered",4);
					return false;
				} 
//			}
				
			return loadRegistrationDataInternal(data);
		}		
		public boolean loadRegistrationData(String uniqueID) {
			if (uniqueID.equals("1")) {return loadRegistrationDataInternal("EHS-ES1000-P00-00ZZ,End House Software,endhousesoftware,05/06/16,21:26,endhousesoftware");} // running on the real internet
			
			String data = "";
//			if (getUseDatabase()) {
				data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegSerial,sysEHSRegName,sysEHSRegPassword,sysEHSRegLogonDate,sysEHSRegLogonTime,sysEHSRegUserName FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"' AND sysEHSRegActive=1","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonDate='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"'","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonTime='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"'","");
				TRACE("LRD:"+data,4);
				if (data.length() == 0) { // user not yet registered
					TRACE("LRD:User not yet registered",4);
					return false;
				} 
//			}
				
			return loadRegistrationDataInternal(data);
		}
		public boolean loadRegistrationDataInternal(String data) {
//				 if (getUseDatabase()) {
				  Vector v = supportFunctions.splitIntoTokens(data);
				  if (v.size() > 0) {
					  lastLogonDetails = (String)v.elementAt(3) + " - " + (String)v.elementAt(4);
				  } else {
					  lastLogonDetails = supportFunctions.currentDate() + " - " + supportFunctions.currentTime();
				  }
				  setUserID((String)v.elementAt(5));
				  if (data.startsWith("EHS")) {
				  	setRegistrationData((String)v.elementAt(1),(String)v.elementAt(0));
					userLogOn(getUserID());
					return true;
				  }
//			   } else {
//			   	 char[] buf = new char[4096];
//			   	 try {
//				 	 String name = ""; // set to username at some point !!!
//					 int num = 0;
//					 File f = new File(appName+name+".lic");
//				 	 if (!f.exists()) {return false;}
//				 	 FileReader in = new FileReader(f);
//					 if (in != null) {
//					 	num = in.read(buf);
//					 	in.close();
//					}
//					String s = new String(buf,0,num);
//					Vector v = supportFunctions.splitIntoTokens(s);
//					if (v.size() != 2) {return false;}
//					setRegistrationData((String)v.elementAt(1),(String)v.elementAt(0));
//					return true;
//				 } catch (Exception e) {e.printStackTrace();}
//			   }
			   
			   clearRegistrationData();
			   return false;
		}
		public void logOff() {
			userLogOff(getUserID());
			clearRegistrationData();
		}
		public void userLogOn(String userid) {;}
		public void userLogOff(String userid) {;}
		panelDialog helpDlg = null;
		public void sendReport(String email) {
			final		Choice		deptCh,typeCh;
			JButton		FAQBut,submitBut,OKBut;
			final 		JTextArea	ta;
			JLabel		statusLab;
			final 		JTextField	titleTF,emailTF;
			JPanel		repPanel;
			
			repPanel = new JPanel();
			repPanel.setLayout(new BoxLayout(repPanel,BoxLayout.Y_AXIS));			
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
			p.add(new JLabel("Title:"));
			titleTF = new JTextField("",20);
			titleTF.setBackground(ehsConstants.lightyellow);
			p.add(titleTF);
			repPanel.add(p);
			repPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
			deptCh = new Choice();
			typeCh = new Choice();
			p.add(new JLabel("Dept:"));
			p.add(deptCh);
			p.add(new JLabel("Type:"));
			p.add(typeCh);
			deptCh.setBackground(ehsConstants.lightyellow);
			typeCh.setBackground(ehsConstants.lightyellow);
			repPanel.add(p);
			repPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
			emailTF = new JTextField("",20);
			emailTF.setBackground(ehsConstants.lightyellow);
			p.add(new JLabel("Email:"));
			p.add(emailTF);
			repPanel.add(p);
			repPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel descLab = new JLabel("Description:");
			p.add(descLab);
			repPanel.add(p);
			ta = new JTextArea("",5,20);
			ta.setBackground(ehsConstants.lightyellow);
			ta.setWrapStyleWord(true);
			repPanel.add(ta);
			repPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
			statusLab = new JLabel(getHTMLInfoText());
			p.add(statusLab);
			repPanel.add(p);
			repPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
			FAQBut = new JButton("FAQ");
			submitBut = new JButton("Submit");
			OKBut = new JButton("Ok");
			p.add(FAQBut);
			p.add(submitBut);
			p.add(OKBut);
			repPanel.add(p);
			emailTF.setText(email);
			populateChoice(deptCh,getDepts());
			populateChoice(typeCh,getRptTypes());
			deptCh.select(0);
			typeCh.select(0);
			ActionListener OKTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					helpDlg.dispose();
			   }
			};
			OKBut.addActionListener(OKTask);
			ActionListener FAQTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					displayFAQ(getAppName());
			   }
			};
			FAQBut.addActionListener(FAQTask);
			ActionListener submitTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String tmp = "EHS-" + String.valueOf(supportFunctions.rand(100,999)) + "-" + supportFunctions.currentShortDate();
					String ticket = tmp.replace('/','-');
					modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("Submit Report");
					msgD.setText("Submitting Report ...");
					String tmp1 = "INSERT INTO sysehsreports (sysEHSRepID,sysEHSRepOwnerID,sysEHSRepProduct,sysEHSRepVersion,sysEHSRepDesc,sysEHSRepName,sysEHSRepEmail,sysEHSRepType,sysEHSRepDept,sysEHSRepDate,sysEHSRepTicket,sysEHSRepUser) VALUES (null,LAST_INSERT_ID()+1,'"+appName+"','"+appVersion+"','"+ta.getText()+"','"+titleTF.getText()+"','"+emailTF.getText()+"','"+typeCh.getSelectedItem()+"','"+deptCh.getSelectedItem()+"','"+supportFunctions.currentDate()+"','"+ticket+"','"+userName+"')";
					//supportFunctions.displayMessageDialog(null,tmp1);
					supportFunctions.getDBConn().executeSQLQuery(tmp1,"");
					supportFunctions.mail(emailTF.getText(),"EHS Report Submitted","Thank you for submitting a report. It has been given ticket ID " + ticket + ". You do not need to reply to this email. Report data sent is " + ta.getText());
					supportFunctions.mail(registrationEmail,"New EHS Report Created","Ticket: " + ticket + " Product:" + appName + " Description:" + ta.getText());
					msgD.destory();
					msgD.dispose();
					supportFunctions.displayMessageDialog(null,"Thank you for the report, it has been assigined ticket ID " + ticket + ". Please use this reference in any further communication.");
					titleTF.setText("");
					emailTF.setText("");
					ta.setText("");
				}
			};
			submitBut.addActionListener(submitTask);
			boolean bEnable = false;
			TRACE("sendReport:userid:"+getUserID(),4);
			if (getUserID().equals("gavin") || getUserID().equals("admin")) {bEnable = true;}
			helpDlg = supportFunctions.displayPanelDialog(null,repPanel,"Help Center");
			helpDlg.setLocationRelativeTo(null);
		}
		public void registerUser() {
			if (getUserRegistered()) {return;}
			
			int id = getMachineUniqueID();
			boolean bRegistered = loadRegistrationData(String.valueOf(id));
			if (!bRegistered) {
				Vector v = supportFunctions.splitIntoTokens(displayLogonDialog(),",");
				if (v.size() == 2) {
					String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+(String)v.elementAt(0)+"' AND sysEHSRegPassword='"+(String)v.elementAt(1)+"' AND sysEHSRegActive=1","");
					if (data.length() != 0) {
						supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegUnique="+id+" WHERE sysEHSRegID="+data,"");
						registerUser();
						return;
					} else {
						supportFunctions.displayMessageDialog(null,"Logon failure, will continue as unregistered.");
					}
				}
			}
			
			if (!bRunAppWithGUI) {return;}
			
			if (splashJPG.equals("") && !bRegistered) {
				JOptionPane.showMessageDialog(null,"This copy of "+ getAppName() + " is unregistered. Please see website to obtain a serial number.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (splashJPG.equals("")) {
					JOptionPane.showMessageDialog(null,getSingleLineInfoText(),getAppName(),JOptionPane.INFORMATION_MESSAGE);
				} else {
					int major = getMajorVersionNumber();
					int minor = getMinorVersionNumber();
					displaySplashScreen(getAppName(),splashJPG,"Version "+String.valueOf(major)+"."+String.valueOf(minor),15);
				}
			}
		}
		public void setRegistrationData(String name,String serial) {
			userName = name;
		  	userRawSerialNumber = serial;
			userSerialNumber = serial.substring(0,12) + "xx-xxxx";
			if (serial.charAt(11) == 'T') {
				numAttemptsLeft = Integer.parseInt(serial.substring(12,14));
				if (numAttemptsLeft == 0) {
					supportFunctions.displayMessageDialog(null,"Sorry your trail licence has expired.");
					supportFunctions.mail(registrationEmail,"Licence Expiry","A trail licence for "+name+" has expired.");
					clearRegistrationData();
					return;
				} else {
					numAttemptsLeft--;
					String tmp = String.valueOf(numAttemptsLeft);
					if (tmp.length() == 1) {tmp = "0" + tmp;}
					String newValCode = serial.substring(0,12) + tmp + serial.substring(14);
					supportFunctions.displayMessageDialog(null,"You have " + String.valueOf(numAttemptsLeft) + " runs left on your trail licence.");
					//if (getUseDatabase()) 
						{supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegSerial='" + newValCode +"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+name+"'","");}
				}
			}
			saveRegistrationData();
			initUserCredit();
		}
		public boolean getUserRegistered() {
			if(userName.equals("unregistered")) {
				return false; 
			} else {
				return true;
			}
		}
		public void doAppUpdate() {
			   try {
			   	   URL u = new URL(getCodeBase(),"../appupdater.php?appproduct="+appName+"&curbuildnum="+getBuildNumber()+"&serialbase="+appSerialBase+"&directory="+dataRelativePath+"/umldiag/umldiagbuild.number");
			   	   ac.showDocument(u,"_blank");
			   } catch (Exception e) {e.printStackTrace();}
		}
		public String getGitVersionInfoString() {return gitVersionInfo.substring(3,gitVersionInfo.length()-3);}
		public String getBuildString() {return buildDate.substring(3,buildDate.length()-3);}
		public String getframeworkBuildString() {return frameworkBuildDate.substring(3,frameworkBuildDate.length()-3);}
		public String getBuildNumber() {return getBuildString().substring(getBuildString().lastIndexOf(" ")+1);}
		public void patchBuildNumber() {
			appVersion = appVersion.substring(0,6) + String.format("%04d",Integer.parseInt(getBuildNumber())) + appVersion.substring(10);
		}
    		public void displayInfoText() {
			supportFunctions.displayMessageDialog(null,getSingleLineInfoText());
		}
		public void displayAboutBox() {
			ActionListener regTask = new ActionListener() {
      		   public void actionPerformed(ActionEvent evt) {
			   		  registerUser();
			   }
			};
  			JButton regBut = new JButton("Register");
			regBut.addActionListener(regTask);
			ActionListener updateTask = new ActionListener() {
      		   public void actionPerformed(ActionEvent evt) {
			   		  doAppUpdate();
			   }
			};
			JButton updateBut = new JButton("Updates");
  			updateBut.addActionListener(updateTask);
			   Object[] sels = {"Ok",regBut,updateBut};
			   int ret = JOptionPane.showOptionDialog(null,getSingleLineInfoText(),getAppName(),JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,sels,sels[0]);
		}
	}

	public class positionDialog extends JDialog {
		public positionDialog(Frame parent,String s,boolean b) {
			super(parent,s,b);
			setLocationRelativeTo(lastPositionWindow);
			lastPositionWindow = this;
		}
		public positionDialog(Frame parent,String s,boolean b,String id) {
			super(parent,s,b);
			id = id.replaceAll(" ","");
			String tmp = getSystemVar(id,"0,0");
			Vector v = supportFunctions.splitIntoTokens(tmp);
			setLocation(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1)));
			lastPositionWindow = this;
		}
		public positionDialog(Frame parent) {
			super(parent);
			setLocationRelativeTo(lastPositionWindow);
			lastPositionWindow = this;
		}
		public positionDialog(Frame parent,String id) {
			super(parent);
			id = id.replaceAll(" ","");
			String tmp = getSystemVar(id,"0,0");
			Vector v = supportFunctions.splitIntoTokens(tmp);
			setLocation(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1)));
			lastPositionWindow = this;
		}
		public void savePosition(String id) {
			Point p = getPosition();
			id = id.replaceAll(" ","");
			String tmp = String.valueOf(p.x) + "," + String.valueOf(p.y);
			setSystemVar(id,tmp);
		}
		
		public void loadPosition(String id) {
			id = id.replaceAll(" ","");
			String tmp = getSystemVar(id,"0,0");
			Vector v = supportFunctions.splitIntoTokens(tmp);
			setLocation(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1)));
		}

		public void loadPosition(String id,String defaultPos) {
			id = id.replaceAll(" ","");
			String tmp = getSystemVar(id,defaultPos);
			Vector v = supportFunctions.splitIntoTokens(tmp);
			setLocation(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1)));
		}
		
		public Point getPosition() {return getLocationOnScreen();}
	}
		
	public class CustomTableModel extends DefaultTableModel {
		   CustomTableModel(String[][] data,String[] colNames) {
		      super(data,colNames);
		   }
		   public boolean isCellEditable(int row,int column) {return false;}
	}
	
	public class sounds {
	
		public class soundDesc {
			protected	String		soundURL;
			protected	AudioClip	soundAudioClip;
			protected	boolean		soundPlaying;
		
			soundDesc(AudioClip a,String b,boolean c) {
				soundURL = b;
				soundAudioClip = a;
				soundPlaying = c;
			}
			public void setAudioClip(AudioClip a) {soundAudioClip = a;}
			public void setPlaying(boolean b) {soundPlaying = b;}
			public void setSoundURL(String s) {soundURL = s;}
		}	
		private	Vector	acSounds = new Vector();
		private soundDesc sd; 
	

		public void createNumberSounds(int num) {
			// fudge the vector to contain num elements
			for(int i=0;i<num;i++) {
				sd = new soundDesc((AudioClip)null,"",false);
				acSounds.add(sd);
			}
		}

		public boolean loadAndPlaySound(final String soundURL, final int index) {
		   return loadSound(soundURL,index,false,true);
		}
		public boolean loadAndPlaySoundDirect(final String soundURL) {
		   return loadSound(soundURL,-1,false,true);
		}
		public boolean loadAndPlaySoundDirect(final String soundURL,boolean block) {
		   return loadSound(soundURL,-1,block,true);
		}
		public boolean loadAndPlaySound(final String soundURL, final int index,boolean block) {
		   return loadSound(soundURL,index,block,true);
		}
		
		public boolean loadSound(String soundURL,int index) {
			return loadSound(soundURL,index,false,false);
		}
		public boolean loadSound(String soundURL,int index,boolean block) {
			return loadSound(soundURL,index,block,false);
		}
		public boolean loadSound(final String soundURL, final int index, boolean block, final boolean play) {
			if(!block) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						TRACE("Starting loading sound " + soundURL,3);
						AudioClip ac = getAudioClip(getCodeBase(),soundURL);
						if (index != -1) {
						   sd = new soundDesc(ac,soundURL,play);
						   acSounds.setElementAt(sd,index);
						}
						TRACE("Finished loading sound " + soundURL,3);
						if (play) {
						   TRACE("Playing loaded sound " + soundURL,3);
						   ac.play();
						}
					}
				});
				t.setPriority(systemSoundThreadPrioity);
				t.setName("System load sound thread");
				t.start();
			} else {
				AudioClip ac = getAudioClip(getCodeBase(),soundURL);
				soundDesc sd = new soundDesc(ac,soundURL,false);
				acSounds.setElementAt(sd,index);
			}
			return true;
		}
		public void unloadSound(int index) {
			if (index < 0 || index > acSounds.size()-1) {return;}
			acSounds.setElementAt((soundDesc)null,index);
		}
		public AudioClip getSoundClip(int index) {
			if (index < 0 || index > acSounds.size()-1) {return (AudioClip)null;}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			return sd.soundAudioClip;
		}
		public boolean getSoundPlaying(int index) {
			if (index < 0 || index > acSounds.size()-1) {return false;}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			return sd.soundPlaying;
		}
		public String getSoundURL(int index) {
			if (index < 0 || index > acSounds.size()-1) {return "";}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			return sd.soundURL;
		}
		public int getSoundIndex(String soundURL) {
			for(int i=0;i<acSounds.size();i++) {
				soundDesc sd = (soundDesc)acSounds.elementAt(i);
				if (soundURL.equals(sd.soundURL)) {return i;}
			}
			return -1;
		}
		public void unloadAllSounds() {
			for(int i=0;i<acSounds.size();i++) {
				stopSound(i);
			}
			acSounds.removeAllElements();
		}
		public void playSound(int index) {
			if (index < 0 || index > acSounds.size()-1) {return;}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			if(!sd.soundPlaying) {
				sd.soundPlaying = true;
				sd.soundAudioClip.play();
			}
		}
		public void stopSound(int index) {
			if (index < 0 || index > acSounds.size()-1) {return;}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			if(sd.soundPlaying) {
				sd.soundPlaying = false;
				sd.soundAudioClip.stop();
			}
		}
		public void loopSound(int index) {
			if (index < 0 || index > acSounds.size()-1) {return;}
			soundDesc sd = (soundDesc)acSounds.elementAt(index);
			if(!sd.soundPlaying) {
				sd.soundPlaying = true;
				sd.soundAudioClip.loop();
			}
		}

	}
		
	public JToolBar setupToolBar(boolean floatable) {
		   JToolBar tBar = new JToolBar("");
		   tBar.setFloatable(floatable);
		   tBar.add(help);
		   return tBar;
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
	
	public class customAction extends AbstractAction {
		   public customAction (String desc,customIcon ci) {
		   		  super(desc);
		   		  putValue(javax.swing.Action.SMALL_ICON,ci);
		   		  putValue(javax.swing.Action.SHORT_DESCRIPTION,desc);
		   }
		   public void actionPerformed(ActionEvent evt) {
		   }
	}
		
	public String listSupportedTargetTypes()
	{
		String	strMessage = "";
		AudioFileFormat.Type[]	aTypes = AudioSystem.getAudioFileTypes();
		for (int i = 0; i < aTypes.length; i++)
		{
			if (i>0) {strMessage += ",";}
			strMessage += aTypes[i].getExtension();
		}
		return strMessage;
	}

	public AudioFileFormat.Type findTargetType(String strExtension)
	{
		AudioFileFormat.Type[]	aTypes = AudioSystem.getAudioFileTypes();
		for (int i = 0; i < aTypes.length; i++)
		{
			if (aTypes[i].getExtension().equals(strExtension))
			{
				return aTypes[i];
			}
		}
		return null;
	}

	public boolean isPcm(AudioFormat.Encoding encoding)
	{
		return encoding.equals(AudioFormat.Encoding.PCM_SIGNED)
			|| encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED);
	}

	public Mixer.Info getMixerInfo(String strMixerName)
	{
		Mixer.Info[]	aInfos = AudioSystem.getMixerInfo();
		for (int i = 0; i < aInfos.length; i++)
		{
			if (aInfos[i].getName().equals(strMixerName))
			{
				return aInfos[i];
			}
		}
		return null;
	}

	public SourceDataLine getSourceDataLine(String strMixerName,AudioFormat audioFormat,int nBufferSize) {
		   SourceDataLine line = null;
		   DataLine.Info  info = new DataLine.Info(SourceDataLine.class,audioFormat,nBufferSize);
		   try {
		   	   if (strMixerName != null) {
			   	  Mixer.Info mixerInfo = getMixerInfo(strMixerName);
				  if (mixerInfo == null) {
				  	 return null;
				  }
				  Mixer mixer = AudioSystem.getMixer(mixerInfo);
				  line = (SourceDataLine)mixer.getLine(info);
			   } else {
			   	 line = (SourceDataLine)AudioSystem.getLine(info);
			   }
			   line.open(audioFormat,nBufferSize);
		   }
		   catch (LineUnavailableException e) {e.printStackTrace();}
		   catch (Exception e) {e.printStackTrace();}
		   return line;
	}
	
	public TargetDataLine getTargetDataLine(String strMixerName,
							AudioFormat audioFormat,
							int nBufferSize)
	{
		TargetDataLine	targetDataLine = null;
		DataLine.Info	info = new DataLine.Info(TargetDataLine.class,
							 audioFormat, nBufferSize);
		try
		{
			if (strMixerName != null)
			{
				Mixer.Info	mixerInfo = getMixerInfo(strMixerName);
				if (mixerInfo == null)
				{
					return null;
				}
				Mixer	mixer = AudioSystem.getMixer(mixerInfo);
				targetDataLine = (TargetDataLine) mixer.getLine(info);
			}
			else
			{
				targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
			}

			targetDataLine.open(audioFormat, nBufferSize);
		}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (Exception e) { e.printStackTrace(); }
		return targetDataLine;
	}
	
	public void playSoundToEnd(String soundFile) {
		   int nInternalBufferSize = AudioSystem.NOT_SPECIFIED;
		   try {
		   	   URL u = new URL(soundFile);
		   	   AudioInputStream ais = AudioSystem.getAudioInputStream(u);
		   	   AudioFormat af = ais.getFormat();
		   	   DataLine.Info info = new DataLine.Info(SourceDataLine.class,af,nInternalBufferSize);
		  	   boolean isSupported = AudioSystem.isLineSupported(info);
		   	   if (!isSupported) {
		   	  	   int nSampleSizeInBits = 16;
			  	   boolean bBigEndian = false;
		   	  	   AudioFormat source = af;
			  	   AudioFormat	target = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,source.getSampleRate(),
					nSampleSizeInBits,source.getChannels(),source.getChannels() * (nSampleSizeInBits / 8),source.getSampleRate(),bBigEndian);
			  	   ais = AudioSystem.getAudioInputStream(target, ais);
			       af = ais.getFormat();
		   	   }	  
		  	   SourceDataLine line = getSourceDataLine(null,af,AudioSystem.NOT_SPECIFIED);
		   	   if (line == null) {return;}
		   	   line.start();
		   	   int nBytesRead = 0;
		   	   byte[] abData = new byte[128000];
		   	   while (nBytesRead != -1) {
				 nBytesRead = ais.read(abData,0,abData.length);
		   		 if (nBytesRead >=0) {
				 	int nBytesWritten = line.write(abData,0,nBytesRead);
				 }
			   }
		   	   line.drain();
		   	   line.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	public interface colorActionUtils {
		public void colorSelected(Color c);
	}
	public class colorAction extends AbstractAction {
		   private	Color	 		color;
		   private	colorActionUtils	target;
		   
		   public colorAction(Color color) {
			   target = null;
		   	   this.color = color;
			   putValue(javax.swing.Action.SMALL_ICON,new colorIcon(color));
		   }
		   public void actionPerformed(ActionEvent evt) {
			   if (target != null) {target.colorSelected(color);}
		   }
		   public Color getColor() {return color;}
		   public void removeColorActionListener() {target = null;}
		   public void addColorActionListener(colorActionUtils cau) {target = cau;}
	}
	
interface xslTransformerUtils {
	// e.g. void transformer.setParameter("name","value");
	// void transformer.clearParameters();
	//
	// String transformer.getOutputProperty(String name);
	// void transformer.setOutputProperty(String name,String value);
	// to clear a single property set value=null
	//
	public void preTransform(Transformer transformer);
}

/** Creates an XSLT transformer for processing an XML document.
 *  A new transformer, along with an style template are created 
 *  for each document transformation. The XSLT, DOM, and 
 *  SAX processors are based on system default parameters.
 */ 

public class XslTransformer {
  private TransformerFactory factory;
  private xslTransformerUtils target;

  public void addxslUtilsListener(xslTransformerUtils xtu) {target = xtu;}
  public void removexslUtilsListener() {target = null;}
  public XslTransformer() {
    factory =  TransformerFactory.newInstance();
    target = null;
  }

  /** Transform an XML and XSL document as <code>Reader</code>s,
   *  placing the resulting transformed document in a 
   *  <code>Writer</code>. Convenient for handling an XML 
   *  documents as a String (<code>StringReader</code>) residing
   *  in memory, not on disk. The output document could easily be
   *  handled as a String (<code>StringWriter</code>) or as a
   *  <code>JSPWriter</code> in a JavaServer page.
   */

  public void process(Reader xmlFile, Reader xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML and XSL document as <code>File</code>s,
   *  placing the resulting transformed document in a 
   *  <code>Writer</code>. The output document could easily 
   *  be handled as a String (<code>StringWriter</code)> or as 
   *  a <code>JSPWriter</code> in a JavaServer page.
   */

  public void process(File xmlFile, File xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML <code>File</code> based on an XSL 
   *  <code>File</code>, placing the resulting transformed 
   *  document in an <code>OutputStream</code>. Convenient for 
   *  handling the result as a <code>FileOutputStream</code> or 
   *  <code>ByteArrayOutputStream</code>.
   */

  public void process(File xmlFile, File xslFile,
                      OutputStream out)
                 throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(out));
  }

  /** Transform an XML source using XSLT based on a new template
   *  for the source XSL document. The resulting transformed 
   *  document is placed in the passed in <code>Result</code> 
   *  object.
   */

  public void process(Source xml, Source xsl, Result result)
                throws TransformerException {
    try {
      Templates template = factory.newTemplates(xsl);
      Transformer transformer = template.newTransformer();
      if (target != null) {
	      target.preTransform(transformer);
      }
      transformer.transform(xml, result);
    } catch(TransformerConfigurationException tce) {
        throw new TransformerException(
                    tce.getMessageAndLocation());
    } catch (TransformerException te) {
      throw new TransformerException(
                  te.getMessageAndLocation());
    }
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
				deleteFilename(""+dataRelativePath+"/"+appDirectory+"/"+filename+".xml");
			}
		}
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
	
	public void displaySplashScreen(String title,String image,String extraText,int timeToDisplay) {
		splashScreenDialog d = new splashScreenDialog(supportFunctions.getTopLevelParent(this),title,image,extraText,timeToDisplay);
	}
	public class splashScreenDialog extends JDialog implements pictureUtils {
	    private	Timer 			tick;
		private	int				timeoutSecs,currentSecs;
		private	pictureCanvas 	splashCanvas;
		public String 			title;
		public String 			extraText;
		
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
			String s2 = "Unregistered";
			if (systemUserReg.getUserRegistered()) {s2 = "Registered To: " + systemUserReg.getUserName();}
			int y2 = splashCanvas.height() - (2 * height2);
			String s3 = "Serial Number: ";
			if (systemUserReg.getUserRegistered()) {s3 = s3 + systemUserReg.getSerialNumber();}
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
		public splashScreenDialog(Frame parent,String title,String image,String extraText,int timeToDisplay) {
			super(parent,title,true);

			this.title = title;
			this.extraText = extraText;
			
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
	 	
	public String displayLogonDialog() {
		logonDialog d = new logonDialog(null);
		return d.getUserName() + "," + d.getPassword();
	}
	public class logonDialog extends positionDialog  implements ActionListener {
		private JButton	butOK,butCancel;
		private String username,password;
		private	TextField usernameTF,passwordTF;

		public logonDialog (Frame f) {
			super(f,"User Logon",true);
				  
			username = "";
			password = "";			
				  
		    JPanel butPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    butOK = new JButton("Ok");
		    butCancel = new JButton("Cancel");
		    butOK.addActionListener(this);
		    butCancel.addActionListener(this);
		    butOK.setMinimumSize(new Dimension(10*charWidth,charHeight));
		    butCancel.setMinimumSize(new Dimension(10*charWidth,charHeight));
		    butPanel.add(butOK);
		    butPanel.add(butCancel);
		   
			JPanel propSheetPanel = new JPanel();
			propSheetPanel.setLayout(new GridLayout(0,2));
			propSheetPanel.add(new JLabel("Username:"));
			usernameTF = new TextField("",10);
			propSheetPanel.add(usernameTF);			
			propSheetPanel.add(new JLabel("Password:"));
			passwordTF = new TextField("",10);
			propSheetPanel.add(passwordTF);			
			add(propSheetPanel,"Center");
			add(butPanel,"South");
		    
			addWindowListener(new WindowAdapter() {
				public void WindowClosing(WindowEvent evt) {
					dispose();
				}
		    });
		
		    pack();
		    setVisible(true);
		}		
	    public String getUserName() {return username;}
		public String getPassword() {return password;}
	    public void destroy() {
	   		  dispose();
	    }
		public void actionPerformed(ActionEvent evt) {
		   if (evt.getSource() == butOK) {
				username = usernameTF.getText();
				password = passwordTF.getText();
				dispose();
		   }
		   if (evt.getSource() == butCancel) {
				username = "";
				password = "";			
			    dispose();
		   }
	    }
	}

	public MenuItem getMenuItem(Menu m,String label) {
		for (int i=0;i<m.getItemCount();i++) {
			if (label.equalsIgnoreCase(m.getItem(i).getLabel())) {return m.getItem(i);}
		}
		return (MenuItem)null;
	}
	
	public String readFromJARFile(String filename)
			throws IOException
			{
			  InputStream is = getClass().getResourceAsStream(filename);
			  InputStreamReader isr = new InputStreamReader(is);
			  BufferedReader br = new BufferedReader(isr);
			  StringBuffer sb = new StringBuffer();
			  String line;
			  while ((line = br.readLine()) != null) 
			  {
			    sb.append(line);
			  }
			  br.close();
			  isr.close();
			  is.close();
			  return sb.toString();
			}
	
	public Color colorCommonDialog(Color c) {
		return JColorChooser.showDialog(this,"Choose Color",c);
	}
	public void setCustomColor1() {
		Color c = colorCommonDialog(ehsConstants.colorCodes[ehsConstants.colorCodes.length-1]);
		if (c != null) {
			ehsConstants.colorCodes[ehsConstants.colorCodes.length-1] = c;			
			TRACE("setCustomColor1:"+String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()),4);
			systemUserReg.getConfigurationSettings().setConfigurationSetting("customcolor1",String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()));
			if (getDrawingCanvas() != null) {getDrawingCanvas().refreshColorToolWindow();}
		}
	}
	public void setCustomColor2() {
		Color c = colorCommonDialog(ehsConstants.colorCodes[ehsConstants.colorCodes.length-2]);
		if (c != null) {
			ehsConstants.colorCodes[ehsConstants.colorCodes.length-2] = c;			
			TRACE("setCustomColor2:"+String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()),4);
			systemUserReg.getConfigurationSettings().setConfigurationSetting("customcolor2",String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()));
			if (getDrawingCanvas() != null) {getDrawingCanvas().refreshColorToolWindow();}
		}
	}
	
	// when a drawing item is added a unique Id is added of hashcode(macheId+ddMMyyyyHHmmss);
	// call setMultiUser(true) to write out a virtual refresh message every time a drawing item is added
	public class drawingCanvas extends printableCanvas implements MouseListener,MouseMotionListener,KeyListener,ChangeListener,ActionListener,ItemListener,ListSelectionListener,Printable,buttonCanvasUtils,statusCanvasDialogListener {
			private	float	fScalingFactor = 1;
		   private JList 	GCSheets = null;
		   protected int	canvasMaxX,canvasMaxY;
		   private boolean	gridVisible,dcFilled,dcOutline,dcMultiUser;
		   private int 		gridSpaceX,gridSpaceY;
		   private int		dcSelIndex;
		   private Cursor	normalCursor,handCursor,moveCursor,crosshairCursor,waitCursor;
		   private Cursor	eresizeCursor,wresizeCursor,nresizeCursor,sresizeCursor,rotateCursor;
		   private Cursor	neresizeCursor,nwresizeCursor,seresizeCursor,swresizeCursor;
		   private int		xCord,yCord;
		   protected Vector	drawingItems,selectedDrawingItems,clipboardDrawingItems,connectors,undoDrawingItems;
		   private String	dcEntity,dcSubEntity;
		   private int		dcMode,dcNum;
		   private int		dcLastX,dcLastY;
		   private int		dcStrokeWidth;
		   private int		dcLayer;
		   private Color	dcColor;
		   protected int		undoIndex;
		   private PopupMenu	dcMenu,dcConnectorMenu;
		   private CheckboxMenuItem	cbItem,cbGrid,cbPictureFrame;
		   private String[] optLabs = {"Line","Rectangle","Elipse","Image","Text","Select","Clear","Print","Properties"};
		   //private String[] colorLabs = {"White","Black","Green","Blue","Yellow","Red"};
		   private statusCanvas			statusPanel;
		   private colorButtonCanvas	colorPanel;
		   private diControlCanvas    	controlPanel;
		   private boolean	loading = false;
		   private drawingCanvasUtils	target = null;
		   private Thread	dcThread = null;
		   private boolean	dcThreadRunning = false;
		   private int		dcLastMsgID = 0;
		   private boolean	printing = false;
		   private drawingItem	dragDI = null;
		   private int		dragFH = -1;
		   private boolean	stickyTools = true;
		   private drawingItem	dragFHDI = null;
		   private boolean readonly = false;
		   private drawingItemConnector	menuSelectedConnector = null;
		   private drawingItem	menuSelectedDI = null;
		   private boolean displayStatus = true;
		   private Color	backgroundColor = Color.white;
		   private	boolean	dragBoundingBox = false;
		   private	Rectangle rcBoundingBox = null;
		   private	Point ptBoundingBox = null;
		   private	boolean bUseDatabase = true;
		   private 	boolean bVirtualMsgProcessing = false;
		   private	boolean bUpdateDC = true;
		   private	layerManager layerMan = new layerManager();
		   private	int loadMode = DCLoadFromDB;
		   private	boolean clipboardActive = false;
		   
		   private	int	defaultImageWidth = 50;
		   private	int	defaultImageHeight = 50;

		   private	boolean bDrawPictureFrame = false;
		   private	panelDialog GCSheetsDialog = null;
		   private	ArrayList arrGCSheets = new ArrayList();
		   
		   private	Image currentCanvasImage = null;
		   public 	Image getCurrentCanvasImage() {return currentCanvasImage;}
		  
		   public boolean bDrawFocusHandles = true;
		   public boolean getDrawFocusHandles() {return bDrawFocusHandles;}
		   public void setDrawFocusHandles(boolean b) {bDrawFocusHandles = b;}
		   
		   private float defaultZoomStep = 0.1f;
		   public void setDefaultZoomStep(float f) {defaultZoomStep = f;}
		   public float getDefaultZoomStep() {return defaultZoomStep;}
		   public void zoomNormalise() {fScalingFactor = 1;}
		   public void zoomIn(float step){fScalingFactor = fScalingFactor + step;}
		   public void zoomOut(float step){fScalingFactor = fScalingFactor - step;}
		   public float zoomFactor() {return fScalingFactor;}

			  public String[] getUserDefinedIDs(Vector drawingItems) {
				  String[] data = new String[drawingItems.size()];
				  for (int i=0;i<drawingItems.size();i++) {
					  drawingItem tmp = (drawingItem)drawingItems.elementAt(i);
					  data[i] = tmp.getUserDefinedName();
				  }
				  return data;
			  }
		   //public Rectangle getBoundingRect() {return new Rectangle(0,0,canvasMaxX,canvasMaxY);}
		   public void drawnOutOfBounds(drawingItem d) {;}
		   public boolean checkRectBounds(drawingItem d,Rectangle rc) {
			   try {
				   if (supportFunctions.rectContainsRect(getDCBoundingRect(),d.getBoundingRect())) {throw new DCOutOfBoundsException();}
		   		} catch (DCOutOfBoundsException e) {drawnOutOfBounds(d);return false;}
			   return true;
		   }
		   
		   public String getPictureFrameText() {
				return systemUserReg.getAppName();
		   }
			public void drawPictureFrame(Graphics2D g2d) {
				TRACE("drawPictureFrame:entered",4);
				Color topColor = new Color(181,162,195);
				if (!bDrawPictureFrame) {return;}
				
				TRACE("drawing picture frame",4);
				g2d.setStroke(new BasicStroke(3));
				g2d.drawRoundRect(charWidth,charHeight*3,dcMaxX-charWidth,dcMaxY-charHeight,30,30);
				g2d.setStroke(new BasicStroke(1));
				String text = getPictureFrameText();
				if (text.length()  != 0) {
					FontMetrics fm = getFontMetrics(g2d.getFont());
					int width = fm.stringWidth(text + "OO");
					int width1 = fm.stringWidth("O");
					int height = fm.getHeight();
					int ascent = fm.getAscent();
					g2d.fillRect(50,charHeight,width,height+(height/2));
					g2d.drawString(text,50+width1,charHeight*2);
				}
			}
			public int getNumberOfGCSheets() {
				return arrGCSheets.size();
			}
			public void addGCSheet(String name) {
				if (!arrGCSheets.contains(name)) {
					arrGCSheets.add(name);
				}
				if (GCSheetsDialog != null) {
					GCSheetsDialog.destory();
					GCSheetsDialog.dispose();
					GCSheetsDialog = supportFunctions.displayPanelDialog(null,createGCSheetPanel(),"Switch GC Sheet");
				}
			}
			public void replaceGCSheet(String newname,String oldname) {
				deleteGCSheet(oldname);	
				addGCSheet(newname);	
			}
			public void deleteGCSheet(String name) {
				arrGCSheets.remove(name);
			}
			public void deleteAllGCSheets() {
				arrGCSheets.clear();
			}
		   public boolean switchGCSheet(String newentity) {
				closeDrawingCanvas();
				return openDrawingCanvas(newentity,loadMode);
		   }
		   public String[] getGCSheets() {
				String []tmp = new String[arrGCSheets.size()];
				arrGCSheets.toArray(tmp);
				return tmp;
		   }
		   public Point centerPoint(Rectangle r) {
			float cx = (float)r.getWidth() / (float)2.00;
			float cy = (float)r.getHeight() / (float)2.00;
			cx = cx + (float)r.getX();
			cy = cy + (float)r.getY();
			return new Point((int)cx,(int)cy);
		  }
		   public drawingItem getDIWithUserDefinedName(String name) {
			for (int i=0;i<drawingItems.size();i++) {
				drawingItem d = (drawingItem)drawingItems.elementAt(i);
				if (name.equals(d.getUserDefinedName())) {return d;}
			}
			
			return (drawingItem)null;
		   }
		   public boolean isClipboardActive() {return clipboardActive;}
		   public boolean openDrawingCanvas(String name,int lm) {
				//clearDrawingCanvas(); // not needed as called from loadDrawingItems() and loadAsXML(...)
				dcEntity = name; // do not use setEntity(...) as we do not want to call replaceGCSheet(...)
				dcSubEntity = "subentity"; // default value
				addGCSheet(dcEntity);
				if (lm > DCLoadFromXML) {lm = DCLoadFromDB;}
				loadMode = lm;
				switch (loadMode) {
					case DCLoadFromDB:
						loadDrawingItems();
						break;
					
					case DCLoadFromXML:
						loadAsXML(name);
						break;
				}
				String tmp = getSystemVar(getEntity() + "_dcbackcolor","white");
				setBackgroundColor(supportFunctions.getColorCode(tmp));
				
				return true;
		   }
		   public void closeDrawingCanvas() {
				setSystemVar(getEntity() + "_dcbackcolor",supportFunctions.getColorName(getBackgroundColor()));
				if (loadMode == DCLoadFromXML) {saveAsXML(getEntity());} else {saveDrawingItems();}
				clearDrawingCanvas();
				dcEntity = "entity"; // default value
				dcSubEntity = "subentity"; // default value
		   }
		   
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			return (drawingItem)null;
		   }
		   public String getUniqueID() {
				Date d = new java.util.Date();
				SimpleDateFormat f = new SimpleDateFormat("ddMMyyyyHHmmss");
				String tmp = f.format(d);
				return String.valueOf(ehsHashCode(machineID + tmp));
		   }
		   public void createConnector(drawingItem start,drawingItem end,String textStart,String textEnd,String id,int symStart,int symEnd,int layer,boolean writeDB) {
				if (textStart == null) {textStart = "";}
				if (textEnd == null) {textEnd = "";}
				if (writeDB) {
					supportFunctions.getDBConn().executeSQLQuery("INSERT INTO sdcdrawingitems (sdcDIID,sdcDIEntity,sdcDIType,sdcDIName,sdcDIOriginX,sdcDIOriginY,sdcDIParam1,sdcDIParam2,sdcDIFilled,sdcDIColor,sdcDIParam3,sdcDIUnique,sdcDILayer,sdcDIParam4) VALUES (null,'"+dcEntity+"',"+String.valueOf(ehsConstants.dcTypeConnector)+",'"+id+"',0,0,'"+start.getUnique()+"','"+end.getUnique()+"',0,'black','"+textStart+","+textEnd+"','"+getUniqueID()+"',"+String.valueOf(layer)+",'"+String.valueOf(symStart)+","+String.valueOf(symEnd)+"'","");
				}
			drawingItemConnector conn = new drawingItemConnector(start,end,textStart,textEnd,symStart,symEnd,id,dcLayer);
			connectors.addElement(conn);
		   }
		   public boolean getUpdate() {return bUpdateDC;}
		   public void setUpdate(boolean b) {bUpdateDC = b;if(b) {paint();}}
		   public boolean getUseDatabase() {return bUseDatabase;}
		   public void setUseDatabase(boolean b) {bUseDatabase = b;}
		   public void setStickyTools(boolean b) {stickyTools = b;}
		   public boolean getStickyTools() {return stickyTools;}
		   public void setReadOnly(boolean b) {readonly = b;}
		   public boolean getReadOnly() {return readonly;}
		   public void addDrawingCanvasListener(drawingCanvasUtils dcu) {target = dcu;}
		   public void removeDrawingCanvasListener() {target = null;}
		   public drawingCanvas() {
			   createDrawingCanvas("",dcMaxX,dcMaxY,dcGridSpaceX,dcGridSpaceY,true);
		   }
		   public drawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   createDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public Rectangle getDCBoundingRect() {return new Rectangle(0,0,canvasMaxX,canvasMaxY);}
		   public void createDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   drawingItems = new Vector();
			   selectedDrawingItems = new Vector();
			   clipboardDrawingItems = new Vector();
			   undoDrawingItems = new Vector();
			   connectors = new Vector();
			   
			   layerMan.removeAllLayers();
			   layerMan.addNewLayer(); // add default layer
			   
			   undoIndex = -1;
			   dcLastMsgID = 0;
			   dcSelIndex = -1;
			   canvasMaxX = maxX;
			   canvasMaxY = maxY;
			   gridSpaceX = spaceX;
			   gridSpaceY = spaceY;
			   gridVisible = gVisible;
			   dcFilled = false;
			   dcNum = 0;
			   dcEntity = entity;
			   dcSubEntity = "subentity";
			   dcLastX = 0;
			   dcLastY = 0;
			   xCord = 0;
			   yCord = 0;
			   dcMode = ehsConstants.dcTypeSelect;
			   dcOutline = false;
			   loading = false;
			   target = null;
			   dcThread = null;
			   dcThreadRunning = false;
			   printing = false;
			   dragDI = null;
			   dragFH = -1;
			   stickyTools = true;
			   dragFHDI = null;
			   dcMultiUser = false;
			   readonly = false;
			   menuSelectedConnector = null;
			   menuSelectedDI = null;
			   dcStrokeWidth = 1;
			   dcLayer = 1;
			   displayStatus = true;
			   dragBoundingBox = false;
			   rcBoundingBox = null;
			   ptBoundingBox = null;
			   bUseDatabase = true;
			   bVirtualMsgProcessing = false;
			   backgroundColor = Color.white;
			   bUpdateDC = true;
			   clipboardActive = false;
			   
			   createMenu();
			   setSize(maxX,maxY);
			   dcColor = Color.black;
			   
			   addGCSheet(entity);

			   MediaTracker picTracker = new MediaTracker(this);
			   Toolkit toolkit = Toolkit.getDefaultToolkit();
			   Image picImage = toolkit.getImage(rotateJPG);
			   picTracker.addImage(picImage,0);
			   try {picTracker.waitForID(0);} catch (InterruptedException e) {}
				if (picTracker.isErrorAny()) {
					TRACE("Error in loading image " + rotateJPG,4);
				}
			   
			   loadMode = DCLoadFromDB;
			   
			   normalCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			   handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			   crosshairCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
			   moveCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
			   waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
			   nresizeCursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
			   sresizeCursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
			   eresizeCursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
			   wresizeCursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			   neresizeCursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
			   nwresizeCursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
			   seresizeCursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
			   swresizeCursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
			   Toolkit tk = Toolkit.getDefaultToolkit();
			   rotateCursor = tk.createCustomCursor(picImage,new Point(0,0),"rotate");
			   
			   addMouseListener(this);
			   addMouseMotionListener(this);
			   addKeyListener(this);
			   add(dcMenu);
			   add(dcConnectorMenu);
			   //add(umlDiagram.getMenuUMLDI());
			   
			   colorPanel = new colorButtonCanvas();
			   colorPanel.colorButtonCanvas().addButtonCanvasListener(this);
			   controlPanel = new diControlCanvas();
			   controlPanel.diControlCanvas().addButtonCanvasListener(this);

			   statusPanel = new statusCanvas("Drawing Properties");
			   Vector props = new Vector();
			   props.addElement(new statusCanvasProp("Name",dcEntity));
			   props.addElement(new statusCanvasProp("SubName",dcSubEntity));
			   props.addElement(new statusCanvasProp("BackColor",supportFunctions.getColorName(backgroundColor)));
			   props.addElement(new statusCanvasProp("Width",String.valueOf(dcStrokeWidth)));
			   props.addElement(new statusCanvasProp("XCord",String.valueOf(xCord)));
			   props.addElement(new statusCanvasProp("YCord",String.valueOf(yCord)));
			   props.addElement(new statusCanvasProp("Layer","Layer " + String.valueOf(dcLayer),layerMan.getLayerNames()));
			   props.addElement(new statusCanvasProp("ForeColor",supportFunctions.getColorName(dcColor)));
			   props.addElement(new statusCanvasProp("Mode",optLabs[dcMode]));
			   String tmp = "False";
			   if (dcFilled) {tmp = "True";}
			   props.addElement(new statusCanvasProp("Filled",tmp));
			   statusPanel.setProps(props);
			   
			   dcThread = new Thread(new Runnable() {
				public void run() {
					while(dcThreadRunning) {
					   if (dcMultiUser) {
						try {
							readVirtualMsg();
							Thread.sleep(3000);
						} catch (Exception e) {;}
					   }
					}
				}
			   });
			   dcThreadRunning = true;
			   dcThread.setPriority(5);
			   dcThread.setName("Drawing canvas thread");
			   dcThread.start();
		   }
		   public void destroyDrawingCanvas() {
			   if (dcThreadRunning) {
				   dcThreadRunning = false;
			   }
				if (GCSheetsDialog != null) {
					GCSheetsDialog.destory();
					GCSheetsDialog.dispose();
				}
			   TRACE("Drawing Canvas Destroyed",4);
		   }
		   protected void finalize() throws Throwable {
			   destroyDrawingCanvas();
			   super.finalize();
		   }
		   public void saveAsXML(String filename) {
				xmlDataFile f = new xmlDataFile();
				f.openXMLDataFile(filename,"gcanvas");
				org.w3c.dom.Element root = f.getRootElement();
				org.w3c.dom.Document doc = f.getXMLDocument();
				for (int k=0;k<drawingItems.size();k++) {
				   drawingItem d = (drawingItem)drawingItems.elementAt(k);
				   d.getDrawingItemAsXML(doc,root);
				}
				// save connectors
				for (int i=0;i<connectors.size();i++) {
					drawingItemConnector c = (drawingItemConnector)connectors.elementAt(i);
					org.w3c.dom.Element setting = doc.createElement("connector");
					setting.setAttribute("start",c.getStart().getUnique());
					setting.setAttribute("end",c.getEnd().getUnique());
					setting.setAttribute("textstart",c.getTextStart());
					setting.setAttribute("textend",c.getTextEnd());
					setting.setAttribute("id",c.getID());
					setting.setAttribute("symstart",String.valueOf(c.getSymStart()));
					setting.setAttribute("symend",String.valueOf(c.getSymEnd()));
					setting.setAttribute("layer",String.valueOf(c.getLayer()));
					root.appendChild(setting);
				}
				f.closeXMLDataFile();
		   }
		   public void loadAsXML(String filename) {
				clearDrawingCanvas();
				xmlDataFile f = new xmlDataFile();
				f.openXMLDataFile(filename,"gcanvas");
				loading = true;
				NodeList nl = f.buildNodeList("drawingitem");
				for (int i=0;i<nl.getLength();i++) {
					org.w3c.dom.Node n = nl.item(i);
					String name = n.getNodeName();
					org.w3c.dom.Element e = (org.w3c.dom.Element)n;
					// got to get attributes in right order for code below to work !!!
					String nodeAttribs = "";
					nodeAttribs = e.getAttribute("type");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("name");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("originx");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("originy");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("color");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("fill");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("param1");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("param2");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("param3");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("unique");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("width");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("layer");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("param4");
					nodeAttribs = nodeAttribs + "," + e.getAttribute("rotangle");
						
				  Vector v1 = supportFunctions.splitIntoTokens(nodeAttribs);
  				  String[] tokens = new String[v1.size()];
				  v1.copyInto(tokens);
				  boolean fill = false;
				  if (tokens[5].equals("1")) {fill = true;}
				  drawingItem d = addDrawingItem(getEntity(),Integer.parseInt(tokens[0]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),tokens[6],tokens[7],fill,supportFunctions.getColorCode(tokens[4]));
				  d.setID(tokens[1]);
				  d.setUnique(tokens[9]);
				  d.setStrokeWidth(Integer.parseInt(tokens[10]));
				  d.setRotAngle(Integer.parseInt(tokens[13]));
				  d.setTransformBoundingBox(getDCBoundingRect());
				  int layer = Integer.parseInt(tokens[11]);
				  d.setLayer(layer);
				  if (layerMan.getLayer("Layer " + String.valueOf(layer)) == null) {
					layerMan.addNewLayer("Layer " + String.valueOf(layer));
				  }
				}
				// load connectors		
				NodeList n2 = f.buildNodeList("connector");
				for (int i=0;i<n2.getLength();i++) {
					org.w3c.dom.Node n = n2.item(i);
					String name = n.getNodeName();
					org.w3c.dom.Element e = (org.w3c.dom.Element)n;
					String startU = e.getAttribute("start");
					String endU = e.getAttribute("end");
					String textStart = e.getAttribute("textstart");
					String textEnd = e.getAttribute("textend");
					String id = e.getAttribute("id");
					String symStart = e.getAttribute("symstart");
					String symEnd = e.getAttribute("symend");
					int layer = Integer.parseInt(e.getAttribute("layer"));
					drawingItem start = getDIFromUnique(startU);
					drawingItem end = getDIFromUnique(endU);
					if (start != null && end != null) {
						createConnector(start,end,textStart,textEnd,id,Integer.parseInt(symStart),Integer.parseInt(symEnd),layer,false);
					} else {
						supportFunctions.displayMessageDialog(null,"Invalid Connector");
					}
				}
				loading = false;
				dcNum = nl.getLength();
				f.closeXMLDataFile();
				paint();
		   }
		   
		  public void keyTyped(KeyEvent evt) {}	
		  public void keyReleased(KeyEvent evt) {}	
		  public void keyPressed(KeyEvent evt) {
			  doKeyPressed(evt);
		  }
		   public void drawConnectors(Graphics2D g2d) {
				g2d.setColor(Color.black); // connectors will always be drawn in black
				for (int i=0;i<connectors.size();i++) {
					drawingItemConnector c = (drawingItemConnector)connectors.elementAt(i);
					if (layerMan.layerDisplayed("Layer "+String.valueOf(c.getLayer()))) {c.drawConnector(g2d);}
				}
		   }
		   
		   public void setBackgroundColor(Color c) {backgroundColor = c;}
		   public Color getBackgroundColor() {return backgroundColor;}
		   public void setDisplayStatus(boolean b) {displayStatus = b;}
		   public boolean getDisplayStatus() {return displayStatus;}
		   public void setMultiUser(boolean b) {dcMultiUser = b;}
		   public boolean getMultiUser() {return dcMultiUser;}
		   public void setSubEntity(String s) {dcSubEntity = s;}
		   public String getSubEntity() {return dcSubEntity;}
		   public String getEntity() {return dcEntity;}
		   public void changeEntity(String s) {
				if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcDrawingItems SET sdcDIEntity='"+s+"' WHERE sdcDIEntity='"+dcEntity+"'","");}
				replaceGCSheet(s,dcEntity);
				dcEntity = s;
			}
		   public void setEntity(String s) {
				replaceGCSheet(s,dcEntity);
				dcEntity = s;
		   }
		   public boolean isMultipleSelected(drawingItem d) {
			   for (int i=0;i<selectedDrawingItems.size();i++) {
				   if (d == (drawingItem)selectedDrawingItems.elementAt(i)) {return true;}
			   }
			   return false;
		   }
		   public String getSelectedID() {
			   if (dcSelIndex == -1) {
				   return "None";
			   }
			   drawingItem d = (drawingItem)drawingItems.elementAt(dcSelIndex);
			   return d.getID();
		   }
		   
		   public Rectangle getTotalBoundingRect() {
			   Rectangle rc = new Rectangle(0,0,0,0);
			   for (int i=0;i<drawingItems.size();i++) {
				   drawingItem d = (drawingItem)drawingItems.elementAt(i);
				   Rectangle r = d.getBoundingRect();
				   rc = rc.union(r);
			   }
			   
			   return rc;
		   }
		   public void paint() {paint(getGraphics());}
		   public boolean doPaint(drawingItem d) {return true;}
		   public void paint(Graphics g) {
			   if (g == null) {return;} // getGraphics() called on a component not yet rendered will return null
			   Image offScreen = createImage(canvasMaxX,canvasMaxY);
			   Graphics offG = offScreen.getGraphics();
			  
			   Graphics2D g2d = (Graphics2D)offG;
			   
			   g2d.setColor(backgroundColor); // paint the backgound of the canvas
			   g2d.fillRect(0,0,canvasMaxX,canvasMaxY);
			   g2d.setColor(dcColor); // set back default DC color
	   
			   beforePainting(g2d);
			   
			   if (gridVisible) {
				   for (int i=0;i<canvasMaxX;i=i+gridSpaceX) {
					   for (int j=0;j<canvasMaxY;j=j+gridSpaceY) {
						   g2d.drawLine(i,j,i+1,j+1);
					   }
				   }
			   }
			   for (int k=0;k<drawingItems.size();k++) {
				   drawingItem d = (drawingItem)drawingItems.elementAt(k);
				   boolean focus = false;
				   if ((k == dcSelIndex || selectedDrawingItems.contains(d)) && !isPrinting()) {focus = true;}
				   focus = focus & getDrawFocusHandles();
				   checkRectBounds(d,d.getBoundingRect());
				   if (readonly) {focus = false;}
				   if (layerMan.layerDisplayed("Layer "+String.valueOf(d.getLayer()))) {if(doPaint(d)) {d.paint(g2d,focus);}}
			   }
			   
			   drawConnectors(g2d);
			   
			   if (dragBoundingBox) {
			    int xCordAdj = xCord;
				int yCordAdj = yCord;
			    if (xCordAdj < ptBoundingBox.x) {xCordAdj = ptBoundingBox.x;}
			    if (yCordAdj < ptBoundingBox.y) {yCordAdj = ptBoundingBox.y;}
			    int width = xCordAdj - ptBoundingBox.x;
				int height = yCordAdj - ptBoundingBox.y;
				g2d.drawRect(ptBoundingBox.x,ptBoundingBox.y,width,height);
				rcBoundingBox = new Rectangle(ptBoundingBox.x,ptBoundingBox.y,width,height);
			   }
			   
			   if (dcOutline) {
				  g2d.setStroke(new BasicStroke(1));
				  g2d.setColor(dcColor);
				  switch (dcMode) {
					  case ehsConstants.dcTypeElispe:
					   	elispeDrawingItem di = new elispeDrawingItem();
					   	di.outlinePaint(g2d,dcLastX,dcLastY,xCord,yCord,dcFilled);
						break;
					  case ehsConstants.dcTypeRect:
					   	rectDrawingItem di1 = new rectDrawingItem();
					   	di1.outlinePaint(g2d,dcLastX,dcLastY,xCord,yCord,dcFilled);
						break;
					   case ehsConstants.dcTypeLine:
					   	lineDrawingItem di2 = new lineDrawingItem();
					   	di2.outlinePaint(g2d,dcLastX,dcLastY,xCord,yCord,dcFilled);
						break;
					   case ehsConstants.dcTypeTextBox:
					    textboxDrawingItem di3 = new textboxDrawingItem();
						di3.outlinePaint(g2d,dcLastX,dcLastY,xCord,yCord,dcFilled);
					   break;
					   default:
					    customOutlineDrawingItem(g2d,dcMode);
				  }
			   }
			   
			   displayStatusText();
			   drawPictureFrame(g2d);
			   afterPainting(g2d);
			   if (bUpdateDC == true) {
				   Graphics2D g2dImage = (Graphics2D)g;
				   g2dImage.scale(fScalingFactor,fScalingFactor);
				   g2dImage.drawImage(offScreen,0,0,canvasMaxX,canvasMaxY,this);
				   currentCanvasImage=offScreen;
			   }
			   offG.dispose();
		   }
		   public void beforePainting(Graphics2D g2d) {;}
		   public void afterPainting(Graphics2D g2d) {;}

		   public void displayStatusText() {
			   updatePropTable();
		   }
		   public void propTableUpdated(String title,String propName,String propNewValue) {
				dcStrokeWidth = Integer.parseInt(statusPanel.getPropByName("Width"));
				if (propName.equals("Layer")) {
					dcLayer = Integer.parseInt(propNewValue.substring(6));
					TRACE("dcLayer:" + String.valueOf(dcLayer),4);
				}
		   }
		   public void updatePropTable() {
			   statusPanel.setPropByName("Name",dcEntity);
			   statusPanel.setPropByName("SubName",dcSubEntity);
			   statusPanel.setPropByName("Width",dcStrokeWidth);
			   statusPanel.setPropByName("XCord",xCord);
			   statusPanel.setPropByName("YCord",yCord);
			   statusPanel.setPropByName("Layer","Layer " + String.valueOf(dcLayer));
			   statusPanel.setPropByName("ForeColor",supportFunctions.getColorName(dcColor));
			   statusPanel.setPropByName("BackColor",supportFunctions.getColorName(backgroundColor));
			   statusPanel.setPropByName("Mode",optLabs[dcMode]);
			   String tmp = "False";
			   if (dcFilled) {tmp = "True";}
			   statusPanel.setPropByName("Filled",tmp);
		   }
		   
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {
		   }
		   
		   public void showToolWindows() {
			   colorPanel.colorButtonCanvasShow();
			   controlPanel.diControlCanvasShow();
			   statusPanel.statusCanvasShow();
			   statusPanel.getDialog().addStatusCanvasDialogListener(this);
			   statusPanel.setEnabledPropByName("Name",false);
			   statusPanel.setEnabledPropByName("SubName",false);
			   //statusPanel.setEnabledPropByName("Width",false);
			   statusPanel.setEnabledPropByName("XCord",false);
			   statusPanel.setEnabledPropByName("YCord",false);
			   //statusPanel.setEnabledPropByName("Layer",false);
			   statusPanel.setEnabledPropByName("Color",false);
			   statusPanel.setEnabledPropByName("Mode",false);
			   statusPanel.setEnabledPropByName("Filled",false);
		   }
		   public void hideToolWindows() {
			   colorPanel.colorButtonCanvasHide();
			   controlPanel.diControlCanvasHide();
			   statusPanel.statusCanvasHide();
		   }
		   public void refreshColorToolWindow() {
			   colorPanel.colorButtonCanvasHide();
			   colorPanel = new colorButtonCanvas();
			   colorPanel.colorButtonCanvas().addButtonCanvasListener(this);
			   colorPanel.colorButtonCanvasShow();
		   }
		   
		   public void focusDrawingItem(drawingItem d) {
			   dcSelIndex = drawingItems.indexOf(d);
			   selectedDrawingItems.addElement((drawingItem)drawingItems.elementAt(dcSelIndex));
		   }
		   
		   public void focusFirst() {
			   if (drawingItems.size() == 0) {
				   dcSelIndex = -1;
			   } else {
				   dcSelIndex = 0;
				   selectedDrawingItems.addElement((drawingItem)drawingItems.elementAt(dcSelIndex));
			   }
		   }
		   
		   public void focusNext() {
			   if (dcSelIndex == -1) {return;}
			   dcSelIndex++;
			   if (dcSelIndex > drawingItems.size()-1) {dcSelIndex = 0;}
			   selectedDrawingItems.addElement((drawingItem)drawingItems.elementAt(dcSelIndex));
		   }
		   
		   public void focusPrevious() {
			   if (dcSelIndex == -1) {return;}
			   dcSelIndex--;
			   if (dcSelIndex < 0) {dcSelIndex = drawingItems.size()-1;}
			   selectedDrawingItems.addElement((drawingItem)drawingItems.elementAt(dcSelIndex));
		   }
		   
		   public void clearDrawingCanvas() {			   
			   drawingItems.removeAllElements();
			   connectors.removeAllElements();
			   layerMan.removeAllLayers();
			   layerMan.addNewLayer(); // add default layer
			   dcSelIndex = -1;
			   dcLayer = 1;
			   dcStrokeWidth = 1;
			   dcColor = Color.black;
			   backgroundColor = Color.white;
			   connectors.removeAllElements();
			   selectedDrawingItems.removeAllElements();
			   clipboardDrawingItems.removeAllElements();
			   dcMode = ehsConstants.dcTypeSelect;
			   bUpdateDC = true;
			   bVirtualMsgProcessing = false;
			   loadMode = DCLoadFromDB;
			   clipboardActive = false;
			   
			   if (dcMultiUser) {writeVirtualMsg(dcEntity,String.valueOf(DCRefresh),dcSubEntity,"");}
			   
			   paint(getGraphics());
		   }
		   
		   public void setGridVisible(boolean b) {gridVisible = b;}
		   public boolean getGridVisible() {return gridVisible;}
		   public int getGridSpaceX() {return gridSpaceX;}
		   public void setGridSpaceX(int i) {gridSpaceX = i;}
		   public int getGridSpaceY() {return gridSpaceY;}
		   public void setGridSpaceY(int i) {gridSpaceY = i;}
		   public drawingItemConnector getConnectorAtPoint(int x,int y) {
			   menuSelectedConnector = null;
			   for (int i=0;i<connectors.size();i++) {
				   drawingItemConnector c = (drawingItemConnector)connectors.elementAt(i);
				   if (c.hitTest(x,y)) {menuSelectedConnector = c;}
			   }
			   return menuSelectedConnector;
		   }
		   public boolean getDrawingItemAtPointFilter(drawingItem d) {
			   return true;
		   }
		   public Vector getDrawingItemAtPointInternal(int x,int y) {
			   Vector v = new Vector();
			   
			   for (int i=0;i<drawingItems.size();i++) {
				   drawingItem d = (drawingItem)drawingItems.elementAt(i);
				   if (d.hitTest(x,y)) {v.add(d);}
			   }
			   
			   return v;
		   }
		   public drawingItem getDrawingItemAtPoint(int x,int y) {
			   Vector v = getDrawingItemAtPointInternal(x,y);
			   for (int i=0;i<v.size();i++) {
				   drawingItem d = (drawingItem)v.elementAt(i);
				   if (getDrawingItemAtPointFilter(d)) {return d;}
			   }
			   return null;
		   }
		   public int getX() {return xCord;}
		   public int getY() {return yCord;}
		   public void record(int x,int y) {
			   dcLastX = x;
			   dcLastY = y;
		   }
		   public boolean printOverride(Graphics2D g2d) {return false;}
		// PrinterJob calls the print method to render the graphics
		// object, starting at pageIndex of 0
		// return PAGE_EXISTS if you have printed that page
		// return NO_SUCH_PAGE if there are no more pages left
		   public int print(Graphics g,PageFormat format,int pagenum) {
			   if (pagenum > 0) {return Printable.NO_SUCH_PAGE;}
			   Dimension size = new Dimension(dcMaxX,dcMaxY);
			   Graphics2D g2d = printPageSetup(g,format,size);
			   printing = true;
			   if (!printOverride(g2d)) {paint(g2d);}
			   printingOnly(g2d);
			   printing = false;
			   return Printable.PAGE_EXISTS;
		   }
		   public void printingOnly(Graphics2D g2d) {;}
		   public void setCanvasNumber(int i) {dcNum = i;}
		   public int getCanvasNumber() {return dcNum;}
		   public void update(Graphics g) {
			   paint(g);
		   }
		   public void dragHandleProcess(int x,int y,boolean bFinish) {
			int offsetX = 0;
			int offsetY = 0;
			Rectangle r_new;
			Rectangle r = dragFHDI.getTransformBoundingBox();
			
			switch (dragFH) {
				case 0:
					setCursor(nwresizeCursor);
					offsetX = (int)r.getX() - x;
					offsetY = (int)r.getY() - y;
					r_new = new Rectangle(x,y,(int)r.getWidth()+offsetX,(int)r.getHeight()+offsetY);
					break;
				case 1:
					setCursor(neresizeCursor);
					offsetX = x - ((int)r.getX()+(int)r.getWidth());
					offsetY = (int)r.getY() - y;
					r_new = new Rectangle((int)r.getX(),y,(int)r.getWidth()+offsetX,(int)r.getHeight()+offsetY);
					break;
				case 2:
					setCursor(swresizeCursor);
					offsetX = (int)r.getX() - x;
					offsetY = y - ((int)r.getY()+(int)r.getHeight());
					r_new = new Rectangle(x,(int)r.getY(),(int)r.getWidth()+offsetX,(int)r.getHeight()+offsetY);
					break;
				case 3:
					setCursor(seresizeCursor);
					offsetX = x - ((int)r.getX()+(int)r.getWidth());
					offsetY = y - ((int)r.getY()+(int)r.getHeight());
					r_new = new Rectangle((int)r.getX(),(int)r.getY(),(int)r.getWidth()+offsetX,(int)r.getHeight()+offsetY);
					break;
				case 4:
					setCursor(nresizeCursor);
					offsetY = (int)r.getY() - y;
					r_new = new Rectangle((int)r.getX(),y,(int)r.getWidth(),(int)r.getHeight()+offsetY);
					break;
				case 5:
					setCursor(sresizeCursor);
					offsetY = y - ((int)r.getY()+(int)r.getHeight());
					r_new = new Rectangle((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight()+offsetY);
					break;
				case 6:
					setCursor(wresizeCursor);
					offsetX = (int)r.getX() - x;
					r_new = new Rectangle(x,(int)r.getY(),(int)r.getWidth()+offsetX,(int)r.getHeight());
					break;
				case 7:
					setCursor(wresizeCursor);
					offsetX = x - ((int)r.getX()+(int)r.getWidth());
					r_new = new Rectangle((int)r.getX(),(int)r.getY(),(int)r.getWidth()+offsetX,(int)r.getHeight());
					break;
				case 8:
					setCursor(rotateCursor);
					dragFHDI.setRotAngle(dragFHDI.getRotAngle() + (x - (int)r.getX()));
					dragFHDI.setTransformBoundingBox(getDCBoundingRect());
					//r_new = dragFHDI.getTransformBoundingBox();
					r_new = r; // drawing item is stored in the database as a un-rotated rectangle of 0 degrees
					break;
				default:
					r_new = r;
					break;
			}
			if (dragFH != 8) {dragFHDI.fitToRectangle(r_new);}
			if (bFinish) {
				dragFHDI.setTransformBoundingBox(getDCBoundingRect());
				if(dragFHDI instanceof imageDrawingItem || dragFHDI instanceof textDrawingItem) {
					if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIOriginX="+String.valueOf((int)r_new.getX())+",sdcDIOriginY="+String.valueOf((int)r_new.getY())+",sdcDIParam2='"+String.valueOf((int)r_new.getWidth())+","+String.valueOf((int)r_new.getHeight())+"',sdcDIRotAngle="+String.valueOf(dragFHDI.getRotAngle())+" WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+dragFHDI.getID()+"'","");}
				} else {
					if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIOriginX="+String.valueOf((int)r_new.getX())+",sdcDIOriginY="+String.valueOf((int)r_new.getY())+",sdcDIParam1='"+String.valueOf((int)r_new.getWidth())+"',sdcDIParam2='"+String.valueOf((int)r_new.getHeight())+"',sdcDIRotAngle="+String.valueOf(dragFHDI.getRotAngle())+" WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+dragFHDI.getID()+"'","");}
				}
				addUndoItem(dragFHDI.getUnique(),"Move",String.valueOf(dragFHDI.rcLastPos.x)+","+String.valueOf(dragFHDI.rcLastPos.y)+","+String.valueOf(dragFHDI.rcLastPos.width)+","+String.valueOf(dragFHDI.rcLastPos.height));
				dragFH = -1;
				dragFHDI = null;
				setCursor(normalCursor);
			}
			paint();
		   }
		   public void dragProcess(int x,int y,boolean bFinish) {
			  if (dragDI != null) {
				  setCursor(handCursor);
				  Vector items = new Vector();
				  if (isMultipleSelected(dragDI)) {
					  items = selectedDrawingItems;
				  } else {
					  items.addElement(dragDI);
				  }
				  
				  boolean bMultiple = true;
				  for (int i=0;i<items.size();i++) {
					if (i == items.size()-1) {bMultiple = false;}
					drawingItem d = (drawingItem)items.elementAt(i);
					Rectangle r = d.getTransformBoundingBox();
				  	d.fitToRectangle(new Rectangle(x-d.getOffsetX(),y-d.getOffsetY(),(int)r.getWidth(),(int)r.getHeight()));
				  	if (bFinish) {
						d.setTransformBoundingBox(getDCBoundingRect());
						addUndoItem(dragDI.getUnique(),"Move",String.valueOf(dragDI.rcLastPos.x)+","+String.valueOf(dragDI.rcLastPos.y)+","+String.valueOf(dragDI.rcLastPos.width)+","+String.valueOf(dragDI.rcLastPos.height),bMultiple);
					  	if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIOriginX="+String.valueOf(x-d.getOffsetX())+",sdcDIOriginY="+String.valueOf(y-d.getOffsetY())+" WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d.getID()+"'","");}
				  	}
				  }
				  if (bFinish) {
					setCursor(normalCursor);
					dragDI = null;
				  }
				  paint();
			  }
		   }
		   public void mouseMoved(MouseEvent evt) {
			   xCord = evt.getX();
			   yCord = evt.getY();
			   drawingItem d = getDrawingItemAtPoint(xCord,yCord);
			   
			   if (d != null) {
				   int hoverFH = d.hitTestFocusHandles(xCord,yCord);
				   if (hoverFH != -1) {
						switch (hoverFH) {
						case 0:
							setCursor(nwresizeCursor);
							break;
						case 1:
							setCursor(neresizeCursor);
							break;
						case 2:
							setCursor(swresizeCursor);
							break;
						case 3:
							setCursor(seresizeCursor);
							break;
						case 4:
							setCursor(nresizeCursor);
							break;
						case 5:
							setCursor(sresizeCursor);
							break;
						case 6:
							setCursor(wresizeCursor);
							break;
						case 7:
							setCursor(wresizeCursor);
							break;
						case 8:
							setCursor(rotateCursor);
							break;
						}
						return;
				   }
			   }
			   setCursor(normalCursor);
			   if (target != null) {target.hoverAction(d,evt);}
			   paint();
		   }
		   public void mouseDragged(MouseEvent evt) {
			   if (readonly) {return;}
			   xCord = evt.getX();
			   yCord = evt.getY();
			   if (dragBoundingBox) {paint();return;}
			   if (dragFHDI != null) {dragHandleProcess(evt.getX(),evt.getY(),false);return;}
			   dragProcess(evt.getX(),evt.getY(),false);
			   paint();
		   }
		  public void stateChanged(ChangeEvent evt) {
		  }
		  public void mouseEntered(MouseEvent evt) {
			   xCord = evt.getX();
			   yCord = evt.getY();
			   paint();
		  }
		  public void mouseExited(MouseEvent evt) {
		  }	
		  public void mouseClicked(MouseEvent evt) {
				if (readonly) {return;}
				if (evt.getClickCount() == 2) {
					if (target != null) {if (target.canvasDoubleClickAction(evt)) {return;}}
				  	drawingItem d = getDrawingItemAtPoint(evt.getX(),evt.getY());
					if (d != null) {
						if (d.getType() == ehsConstants.dcTypeSwitchGCSheet) {}
						if (target != null) {target.doubleClickAction(d,evt);}
					}
				}
		  }	
		  public void mousePressed(MouseEvent evt) {
			  int button=evt.getModifiers();
			  if ((button & MouseEvent.BUTTON3_MASK) != 0) {return;} // ignore right-button click
			  if (readonly) {return;}
    	      if (target != null) {if (target.canvasLeftClickAction(evt)) {return;}}

			  xCord = evt.getX();
			  yCord = evt.getY();
			  record(evt.getX(),evt.getY());
			  
			  for (int i=0;i<drawingItems.size();i++) {
				  drawingItem d = (drawingItem)drawingItems.elementAt(i);
				  Point org = d.getOrigin();
				  d.setOffsetX(evt.getX()-org.x); // used in dragProcess(...)
				  d.setOffsetY(evt.getY()-org.y);
			  }
			  
			  dcOutline = false;
			  drawingItem clickedOnDI = getDrawingItemAtPoint(evt.getX(),evt.getY());
			  if (clickedOnDI != null) {
				  dragFH = clickedOnDI.hitTestFocusHandles(evt.getX(),evt.getY());
				  if( dragFH != -1) {
					dragFHDI = clickedOnDI;
					dcMode = ehsConstants.dcTypeSelect;
					return;
				  }
			  }

				switch(dcMode) {
				  case ehsConstants.dcTypeSelect:  	
					if (clickedOnDI != null) {
						if (evt.isShiftDown()) {
							if (isMultipleSelected(clickedOnDI)) {
								selectedDrawingItems.remove(clickedOnDI);
							} else {
							    if (selectedDrawingItems.size() == 0) { // first multiple select item - do we need this now ??? (see e.g. FocusNext())
									drawingItem d1 = (drawingItem)drawingItems.elementAt(dcSelIndex);
									selectedDrawingItems.addElement(d1);
								}
								selectedDrawingItems.addElement(clickedOnDI);
								focusDrawingItem(clickedOnDI);
							}
							if (target != null) {target.leftClickSelectedAction(selectedDrawingItems,evt);}
							return;
						}
						
						clickedOnDI.rcLastPos = clickedOnDI.getTransformBoundingBox();
						dragDI = clickedOnDI;
						
						selectedDrawingItems.removeAllElements();
						focusDrawingItem(clickedOnDI);
					  
					} else {
						// clicked on empty part of canvas
						dragBoundingBox = true;
						ptBoundingBox = new Point(xCord,yCord);
					}
						if (target != null) {target.leftClickAction(clickedOnDI,evt);}						
					paint();
				   break;
				   case ehsConstants.dcTypeText:
				   	String data = JOptionPane.showInputDialog(null,"Enter Text","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
					if (data != null) {
						addDrawingItem(dcEntity,ehsConstants.dcTypeText,evt.getX(),evt.getY(),data,String.valueOf(charWidth*data.length())+","+String.valueOf(charHeight),false,dcColor);
					}
					break;
				   case ehsConstants.dcTypeSwitchGCSheet:
					   	String data1 = JOptionPane.showInputDialog(null,"Enter GC Sheet Name","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
						if (data1 != null) {
							addDrawingItem(dcEntity,ehsConstants.dcTypeSwitchGCSheet,evt.getX(),evt.getY(),data1,"",false,dcColor);
						}					   
					   break;
				   case ehsConstants.dcTypeImage:
				   	data = controlsFunctions.fileOpenDialog("","");
					if (data != null) {
						addDrawingItem(dcEntity,ehsConstants.dcTypeImage,evt.getX(),evt.getY(),data,String.valueOf(defaultImageWidth) + "," + String.valueOf(defaultImageHeight),false,dcColor);
					}
					break;
				    default:
				    	dcOutline = true;
					break;
			  }
		  }	
		  public Vector drawingItemsInRect(Rectangle r) {
			Vector v = new Vector();
			
			for (int i=0;i<drawingItems.size();i++) {
				drawingItem d = (drawingItem)drawingItems.elementAt(i);
				if(r.intersects(d.getTransformBoundingBox())) {
					v.addElement(d);
				}
			}
			
			return v;
		  }
		  public void mouseReleased(MouseEvent evt) {
			  menuSelectedDI = getDrawingItemAtPoint(evt.getX(),evt.getY());
			  menuSelectedConnector = getConnectorAtPoint(evt.getX(),evt.getY());
			  
			  if (evt.isPopupTrigger()) {
				  if (menuSelectedDI != null) { // Have we right-clicked on a drawing item?
					  if (target != null) {
						  if (isMultipleSelected(menuSelectedDI)) {
							  target.rightClickAction(selectedDrawingItems,evt);
						  } else {
							  Vector v = new Vector();
							  v.addElement(menuSelectedDI);
							  target.rightClickAction(v,evt);
						  }
					  }
					  return;
				  }
			  if (readonly) {return;}
				  if (menuSelectedConnector != null) {
				  	dcConnectorMenu.show(this,evt.getX(),evt.getY());
				  } else {
					customUpdateMenu(dcMenu);
					dcMenu.show(this,evt.getX(),evt.getY());
				  }

				  return;
			  }
     		  
			  if (dragBoundingBox) {
				Vector v = drawingItemsInRect(rcBoundingBox);
				if (v.size() == 0) {
					selectedDrawingItems.removeAllElements();
					focusFirst();					
				} else {
					focusDrawingItemsInBoundingBox(rcBoundingBox);
				}

				dragBoundingBox = false;
				rcBoundingBox = null;
				ptBoundingBox = null;
				return;
			  }
			  
			  if (dragFHDI != null) {dragHandleProcess(evt.getX(),evt.getY(),true);return;}
			  if (dragDI != null) {dragProcess(evt.getX(),evt.getY(),true);return;}
			  
			  if(dcMode == ehsConstants.dcTypeTextBox) {
				   	String data = JOptionPane.showInputDialog(null,"Enter Text","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
					if (data != null) {
						addDrawingItem(getEntity(),ehsConstants.dcTypeTextBox,evt.getX(),evt.getY(),data,"",false,dcColor);
					}
			  }
			  if (dcOutline == true && dcMode != ehsConstants.dcTypeTextBox) {
				  addDrawingItem(getEntity(),dcMode,dcLastX,dcLastY,String.valueOf(evt.getX()),String.valueOf(evt.getY()),dcFilled,dcColor);
			  }
			  dcOutline = false;
		  }

		  public void focusDrawingItemsInBoundingBox(Rectangle box) {
			  selectedDrawingItems.removeAllElements();
			  for (int i=0;i<drawingItems.size();i++) {
				  drawingItem tmp = (drawingItem)drawingItems.elementAt(i);
				  if (box.intersects(tmp.getTransformBoundingBox())) {
					  selectedDrawingItems.addElement(tmp);
				  }
			  }
			  paint();
		  }
		  public void addUndoItem(String id,String cmd,String params) {
			addUndoItem(id,cmd,params,false);
		  }
		  public void addUndoItem(String id,String cmd,String params,boolean bMultiple) {
			TRACE("AUI: "+id+"#"+cmd+"#"+params+"#"+supportFunctions.valueOf(bMultiple),4);
			undoDrawingItems.insertElementAt(id+"#"+cmd+"#"+params+"#"+supportFunctions.valueOf(bMultiple),++undoIndex);
		  }
		  public void undo() {
			if (undoIndex > -1) {
				String tmp = (String)undoDrawingItems.elementAt(undoIndex);
				Vector v = supportFunctions.splitIntoTokens(tmp,"#");
				if (v.size() != 4) {return;}
				String id = (String)v.elementAt(0);
				String cmd = (String)v.elementAt(1);
				String params = (String)v.elementAt(2);
				boolean bMultiple = supportFunctions.valueOf((String)v.elementAt(3));
				undoIndex--;
				drawingItem d = getDIFromUnique(id);
				doCommand(cmd,params,d);
				if (bMultiple) {
					undo();
				}
			} else {
				supportFunctions.displayMessageDialog(null,"Nothing to Undo");
			}
  		  }
		  public void redo() {
			if (undoIndex < undoDrawingItems.size()-1) {
				String tmp = (String)undoDrawingItems.elementAt(undoIndex);
				Vector v = supportFunctions.splitIntoTokens(tmp,"#");
				if (v.size() != 4) {return;}
				String id = (String)v.elementAt(0);
				String cmd = (String)v.elementAt(1);
				String params = (String)v.elementAt(2);
				boolean bMultiple = supportFunctions.valueOf((String)v.elementAt(3));
				undoIndex++;
				drawingItem d = getDIFromUnique(id);
				doCommand(cmd,params,d);
				if (bMultiple) {
					redo();
				}
			} else {
				supportFunctions.displayMessageDialog(null,"Nothing to Redo");
			}
		  }
		  public void selectAll() {
			  selectedDrawingItems = drawingItems;
			  paint();
		  }
		  public void paste() {
			  clipboardActive = true;
			  for (int i=0;i<clipboardDrawingItems.size();i++) {
				  if (i == clipboardDrawingItems.size() - 1) {clipboardActive = false;}
				  drawingItem d = (drawingItem)clipboardDrawingItems.elementAt(i);
				  if (doPaste(d)) {pasteItem(d);}		
			  }
			  selectedDrawingItems = clipboardDrawingItems;
			  clipboardActive = false;
			  paint();
		  }
		  public boolean doPaste(drawingItem d) {return true;}
		  public void pasteItem(drawingItem d) {
			  Point pt = d.getOrigin();
			  pt.x = pt.x + 10;
			  pt.y = pt.y + 10;
			  addDrawingItem(dcEntity,d.getType(),pt.x,pt.y,d.getParam1(),d.getParam2(),d.getParam3(),d.getParam4(),d.getFilled(),d.getColor());
		  }
		  public void copy() {
			  clipboardDrawingItems = selectedDrawingItems;
		  }
		  public void cut() {
			  clipboardActive = true;
			  copy();
			  for (int i=0;i<clipboardDrawingItems.size();i++) {
				if (i == clipboardDrawingItems.size() - 1) {clipboardActive = false;}
				deleteDrawingItem((drawingItem)clipboardDrawingItems.elementAt(i));
			  }
			  clipboardActive = false;
			  selectedDrawingItems.removeAllElements();
			  focusFirst();
		  }
		  public void group(String id) {
			  for (int i=0;i<selectedDrawingItems.size();i++) {
				  drawingItem d = (drawingItem)selectedDrawingItems.elementAt(i);
				  d.setGroupID(id);
			  }
			  if (dcMultiUser) {writeVirtualMsg(dcEntity,String.valueOf(DCRefresh),dcSubEntity,"");}
			  paint();
		  }
		  public void ungroup(String id) {
			  for (int i=0;i<drawingItems.size();i++) {
				  drawingItem d = (drawingItem)drawingItems.elementAt(i);
				  String tmp = d.getGroupID();
				  if (tmp.equals(id)) {d.setGroupID("");}
			  }
			  if (dcMultiUser) {writeVirtualMsg(dcEntity,String.valueOf(DCRefresh),dcSubEntity,"");}
			  paint();
		  }
		  public void renameEntityNameInDB(String oldEntityName,String newEntityName) {
			if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcDrawingItems SET sdcDIEntity='" + newEntityName + "' WHERE sdcDIEntity='" + oldEntityName + "'","");}
		  }
		  public void deleteDCFromDB(String entity) {
			if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("DELETE FROM sdcDrawingItems WHERE sdcDIEntity='" + entity + "'","");}
		  }
		  public void doKeyPressed(KeyEvent evt) {
			  if (readonly) {return;}
			  int mods = evt.getModifiers();
			  int keyCode = evt.getKeyCode();
			  boolean shiftDown = false;
			  boolean ctrlDown = false;
			  if ((mods & InputEvent.SHIFT_MASK) != 0) {shiftDown = true;}
			  if ((mods & InputEvent.CTRL_MASK) != 0) {ctrlDown = true;}
			  if (keyCode == KeyEvent.VK_DELETE) {
				  if (dcSelIndex == -1) {return;}
				  drawingItem d = (drawingItem)drawingItems.elementAt(dcSelIndex);
				  if (isMultipleSelected(d)) {
					  for (int i=0;i<selectedDrawingItems.size();i++) {
						  deleteDrawingItem((drawingItem)selectedDrawingItems.elementAt(i));
					  }
				  } else {
					  deleteDrawingItem(d);
				  }
				  
				  selectedDrawingItems.removeAllElements();
				  focusFirst();
			  }
			  if (keyCode == KeyEvent.VK_T && ctrlDown) {
				  displayTransTableEditor();
			  }
			  if (keyCode == KeyEvent.VK_S && ctrlDown) {
				  String s = supportFunctions.currentShortDate().replace(':','_');
				  saveAsJPG(getDCBoundingRect(),"clip" + s + ".jpg");
			  }
			  if (keyCode == KeyEvent.VK_N && shiftDown) {
				  selectedDrawingItems.removeAllElements();
				  focusNext();
			  }
			  if (keyCode == KeyEvent.VK_P && shiftDown) {
				  selectedDrawingItems.removeAllElements();
				  focusPrevious();
			  }
			  if (keyCode == KeyEvent.VK_A && ctrlDown) {
				  selectAll();
			  }
			  if (keyCode == KeyEvent.VK_C && ctrlDown) {
				  copy();
			  }
			  if (keyCode == KeyEvent.VK_V && ctrlDown) {
				  paste();
			  }
			  if (keyCode == KeyEvent.VK_X && ctrlDown) {
				  cut();
			  }
			  if (keyCode == KeyEvent.VK_Z && ctrlDown) {
				  undo();
			  }
			  if (keyCode == KeyEvent.VK_Y && ctrlDown) {
				  redo();
			  }
			  if (keyCode == KeyEvent.VK_J && ctrlDown) {
				  zoomIn(defaultZoomStep);
			  }
			  if (keyCode == KeyEvent.VK_K && ctrlDown) {
				  zoomOut(defaultZoomStep);
			  }
			  if (keyCode == KeyEvent.VK_L && ctrlDown) {
				  zoomNormalise();
			  }
			  if (keyCode == KeyEvent.VK_R && ctrlDown) {
				  // CTRL-R is manual refresh key
				  // note: any key will refresh as paint() is always called at end of this function!!!
			  }
			  if (keyCode == KeyEvent.VK_G && ctrlDown) {
				  String data = JOptionPane.showInputDialog(null,"Enter Group Name","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
				  if (data != null) {group(data);}
			  }
			  if (keyCode == KeyEvent.VK_U && ctrlDown) {
				  if (dcSelIndex == -1) {supportFunctions.displayMessageDialog(null,"Nothing To Ungroup");return;}
				  drawingItem d = (drawingItem)drawingItems.elementAt(dcSelIndex);
				  ungroup(d.getGroupID());
			  }
			  paint();
		  }
		  
		  // called when button is pressed on a control panel 
		  public void buttonPressed(ActionEvent evt) {
			   //JButton b = (JButton)evt.getSource();
			   //String cmd = b.getToolTipText();
			   //doCommand(cmd);
		  }
		  public void buttonRightClicked(MouseEvent evt) {
			   JButton b = (JButton)evt.getSource();
			   String cmd = b.getToolTipText();
			   TRACE("Button right clicked",4);
			   doCommand(cmd,"back");
		  }
		  public void buttonLeftClicked(MouseEvent evt) {
			   JButton b = (JButton)evt.getSource();
			   String cmd = b.getToolTipText();
			   TRACE("Button left clicked",4);
			   doCommand(cmd,"fore");
		  } 
		  public void buttonDoubleClicked(MouseEvent evt) {
			   JButton b = (JButton)evt.getSource();
			   String cmd = b.getToolTipText();
			   if (cmd.equalsIgnoreCase("customcolor1")) {setCustomColor1();}
			   if (cmd.equalsIgnoreCase("customcolor2")) {setCustomColor2();}
		  }
	  
		  public void actionPerformed(ActionEvent evt) {
			  String cmd = evt.getActionCommand();
			  doCommand(cmd);
		  }
			public void deleteConnector(drawingItemConnector conn) {
				supportFunctions.getDBConn().executeSQLQuery("DELETE FROM sdcdrawingitems WHERE sdcDIName='"+conn.getID()+"'","");
				connectors.remove(conn);
			}
			public void doCommand(String cmd) {
			  drawingItem d = null;
			  if (dcMode == ehsConstants.dcTypeSelect && dcSelIndex != -1) {
				d = (drawingItem)drawingItems.elementAt(dcSelIndex);
			  }
			  doCommand(cmd,"",d);
			}
			public void doCommand(String cmd,String params) {
			  drawingItem d = null;
			  if (dcMode == ehsConstants.dcTypeSelect && dcSelIndex != -1) {
				d = (drawingItem)drawingItems.elementAt(dcSelIndex);
			  }
			  doCommand(cmd,params,d);
			}
		  public void doCommand(String cmd,String params,drawingItem d) {			  
			  Color c = supportFunctions.getColorCode(cmd);
			  if (c != null) {
				
				if (params.equalsIgnoreCase("back")) {
					addUndoItem("abc",supportFunctions.getColorName(backgroundColor),"back");
					backgroundColor = c;
				} else {				
				  if (d != null) {
					if (selectedDrawingItems.size() != 0) { // multiple selection
					  boolean bMultiple = true;
					  for (int i=0;i<selectedDrawingItems.size();i++) {
						  if (i == selectedDrawingItems.size()-1) {bMultiple = false;}
						  drawingItem d1 =(drawingItem)selectedDrawingItems.elementAt(i);
					      addUndoItem(d.getUnique(),supportFunctions.getColorName(d.getColor()),"fore",bMultiple);
						  d1.setColor(c);
						  if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIColor='"+supportFunctions.getColorName(c)+"' WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d1.getID()+"'","");}
					  }
					} else { // single selection
					  addUndoItem(d.getUnique(),supportFunctions.getColorName(d.getColor()),"fore");
					  d.setColor(c);
					  if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIColor='"+supportFunctions.getColorName(c)+"' WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d.getID()+"'","");}
					}
				  } else {
					dcColor = c;
				  }
				 }
			  }
			  if (cmd.equalsIgnoreCase("Grid")) {
				  gridVisible = !gridVisible;
			  }
			  if (cmd.equalsIgnoreCase("Fill")) {
				  dcFilled = !dcFilled;
				  if (d != null) {
					if (selectedDrawingItems.size() != 0) { // multiple selection
					  for (int i=0;i<selectedDrawingItems.size();i++) {
						  drawingItem d1 =(drawingItem)selectedDrawingItems.elementAt(i);
						  d1.setFilled(dcFilled);
						  if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIFilled="+supportFunctions.valueOf(dcFilled)+" WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d1.getID()+"'","");}
					  }
					 } else { // single selection
						d.setFilled(dcFilled);
						if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIFilled="+supportFunctions.valueOf(dcFilled)+" WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d.getID()+"'","");}
					 }
				  } 
			  }
			  if (cmd.equalsIgnoreCase("Select")) {
				  dcMode = ehsConstants.dcTypeSelect;
			  }
			  if (cmd.equalsIgnoreCase("Line")) {
				  dcMode = ehsConstants.dcTypeLine;
			  }
			  if (cmd.equalsIgnoreCase("Rectangle")) {
				  dcMode = ehsConstants.dcTypeRect;
			  }
			  if (cmd.equalsIgnoreCase("TextBox")) {
					dcMode = ehsConstants.dcTypeTextBox;
			  }
			  if (cmd.equalsIgnoreCase("Elipse")) {
				  dcMode = ehsConstants.dcTypeElispe;
			  }
			  if (cmd.equalsIgnoreCase("Image")) {
				  dcMode = ehsConstants.dcTypeImage;
			  }
			  if (cmd.equalsIgnoreCase("Text")) {
				  dcMode = ehsConstants.dcTypeText;
			  }
			  if (cmd.equalsIgnoreCase("Delete Connector")) {
				if (menuSelectedConnector != null) {
					deleteConnector(menuSelectedConnector);
					selectedDrawingItems.removeAllElements();
					focusFirst();
				}
			  }
			  if (cmd.equalsIgnoreCase("Connector Properties")) {
				if (menuSelectedConnector != null) {
					connectorPropsDialog d1 = new connectorPropsDialog(supportFunctions.getTopLevelParent(this),menuSelectedConnector);
					if (d1.isOK()) {
						menuSelectedConnector.setTextStart(d1.getTextStart());
						menuSelectedConnector.setTextEnd(d1.getTextEnd());
						supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIParam3='" + d1.getTextStart()+","+d1.getTextEnd() + "' WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+menuSelectedConnector.getID()+"'","");
					} 
					d1.dispose();
				}
			  }
			  if (cmd.equalsIgnoreCase("Properties")) {
				if (selectedDrawingItems.size() == 0 && dcSelIndex != -1) {
					drawingItem d1 = (drawingItem)drawingItems.elementAt(dcSelIndex);
					d1.showPropsDialog(getEntity());
				} else {
					supportFunctions.displayMessageDialog(null,"Select only one drawing item");
				}
			  }
			  if (cmd.equalsIgnoreCase("Print")) {
				printCanvas();
			  }
			  if (cmd.equalsIgnoreCase("Clear")) {
					if (JOptionPane.showConfirmDialog(null,"Clear drawing canvas","Canvas - " + getEntity(),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						clearDrawingCanvas();
						deleteDCFromDB(dcEntity);
					}
			  }
			  if (cmd.equalsIgnoreCase("Add Connector")) {
					if (selectedDrawingItems.size() == 2) {
						String textStart = JOptionPane.showInputDialog(null,"Enter Connector Start Label","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
						String textEnd = JOptionPane.showInputDialog(null,"Enter Connector End Label","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
						if (textStart != null && textEnd != null) 	{
							String 	id = getEntity()+ "-" + getSubEntity() + "-" + String.valueOf(dcNum++);
createConnector((drawingItem)selectedDrawingItems.elementAt(0),(drawingItem)selectedDrawingItems.elementAt(1),textStart,textEnd,id,0,0,dcLayer,true);
							//supportFunctions.displayMessageDialog(null,"Add Connector:Added");
						}
					} else {
						supportFunctions.displayMessageDialog(null,"Add Connector: Select 2 Items To Connect");
					}
			  }
			  if (cmd.equalsIgnoreCase("Virtual Message")) {
				  String text = JOptionPane.showInputDialog(null,"Enter Message","Canvas - " + getEntity(),JOptionPane.QUESTION_MESSAGE);
				  if (text != null) {
					  if (dcMultiUser) {writeVirtualMsg(dcEntity,String.valueOf(DCMsg),dcSubEntity,text);}
				  }
			  }
			  if (cmd.equalsIgnoreCase("Delete")) {
				deleteDrawingItem(d);
			  }
			  if (cmd.equalsIgnoreCase("Move")) {
				moveDrawingItem(d,params);
			  }
			  if (cmd.equalsIgnoreCase("Add")) {
				Vector v = supportFunctions.splitIntoTokens(params,",");
				d = addDrawingItem((String)v.elementAt(0),
						Integer.parseInt((String)v.elementAt(1)),
						Integer.parseInt((String)v.elementAt(2)),
						Integer.parseInt((String)v.elementAt(3)),
						(String)v.elementAt(4),
						(String)v.elementAt(5),
						(String)v.elementAt(6),
						(String)v.elementAt(7),
						supportFunctions.valueOf((String)v.elementAt(8)),
						supportFunctions.getColorCode((String)v.elementAt(9)));
			  }
			  if (cmd.equalsIgnoreCase("Width")) {
				dcStrokeWidth = supportFunctions.getPropValue("Get Stroke Width","Stroke Width",dcStrokeWidth);
				displayStatusText();
			  }
			  if (cmd.equalsIgnoreCase("Copy")) { 
				copy();
			  }
			  if (cmd.equalsIgnoreCase("Cut")) {
				cut();
			  }
			  if (cmd.equalsIgnoreCase("Paste")) {
				paste();
			  }
			  if (cmd.equalsIgnoreCase("Undo")) {
				undo();
			  }
			  if (cmd.equalsIgnoreCase("Redo")) {
				redo();
			  }
			  if (cmd.equalsIgnoreCase("Switch GC Sheet")) {
				GCSheetsDialog = supportFunctions.displayPanelDialog(null,createGCSheetPanel(),"Switch GC Sheet");
			  }
			  if (cmd.equalsIgnoreCase("Layers")) {
				layerDialog layerDialog1 = new layerDialog(null,layerMan);
				String[] newOpts = layerMan.getLayerNames();
				statusPanel.setComboOptsByName("Layer",newOpts);
			  }
			  
			  if (target != null) {target.customDoCommand(cmd,params,d);}

			  paint();
		  }
		  
		  public JPanel createGCSheetPanel() {
				JPanel p = new JPanel();
				p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));			
				p.add(new JLabel("Defined GC Sheets",JLabel.CENTER));
				String[] sheets = getGCSheets();
				GCSheets = new JList(sheets);
				GCSheets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				GCSheets.setVisibleRowCount(5);
				for (int i=0;i<sheets.length;i++) {
					if (sheets[i].equals(getEntity())) {GCSheets.setSelectedIndex(i);break;}
				}
				GCSheets.addListSelectionListener(this);
				JScrollPane pane = new JScrollPane(GCSheets);
				p.add(pane);
				
				return p;
		  }
		  public void valueChanged(ListSelectionEvent evt) {
			if (!evt.getValueIsAdjusting()) {
				String GCSheet = (String)GCSheets.getSelectedValue();
				switchGCSheet(GCSheet);
			}
		}
		  public void moveDrawingItem(drawingItem d,String params) {
			Vector v = supportFunctions.splitIntoTokens(params,",");
			Rectangle r_new = new Rectangle(Integer.parseInt((String)v.elementAt(0)),
				Integer.parseInt((String)v.elementAt(1)),
				Integer.parseInt((String)v.elementAt(2)),
				Integer.parseInt((String)v.elementAt(3)));
			d.fitToRectangle(r_new);
			if(d instanceof imageDrawingItem) {
				if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIOriginX="+String.valueOf((int)r_new.getX())+",sdcDIOriginY="+String.valueOf((int)r_new.getY())+",sdcDIParam2='"+String.valueOf((int)r_new.getWidth())+","+String.valueOf((int)r_new.getHeight())+"' WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d.getID()+"'","");}
			} else {
				if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIOriginX="+String.valueOf((int)r_new.getX())+",sdcDIOriginY="+String.valueOf((int)r_new.getY())+",sdcDIParam1='"+String.valueOf((int)r_new.getWidth())+"',sdcDIParam2='"+String.valueOf((int)r_new.getHeight())+"' WHERE sdcDIEntity='"+getEntity()+"' AND sdcDIName='"+d.getID()+"'","");}
			}
		  }
		  public boolean isLoading() {return loading;}
		  public boolean isPrinting() {return printing;}
		  public drawingItem addDrawingItem(String entity,int type,int x,int y,String p1,String p2,boolean fill,Color c) {
			return addDrawingItem(entity,type,x,y,p1,p2,"","",fill,c);
		  }
		  public int countDrawingItemsOfType(int type) {
			return getDrawingItemsOfType(type).size();
		  }
		  public Vector getDrawingItemsOfType(int type) {
			Vector v = new Vector();
			
			for (int i=0;i<drawingItems.size();i++) {
				drawingItem d = (drawingItem)drawingItems.elementAt(i);
				if (d.getType() == type) {v.addElement(d);}
			}
			return v;
		  }
		  public drawingItem addDrawingItem(String entity,int type,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			  String id;
			  if(!isLoading()) {
				id = getEntity()+ "-" + getSubEntity() + "-" + String.valueOf(dcNum++);
			  } else {
				id = ""; // we overwrite this value in loadDrawingItems(...)
			  }
			  drawingItem d = null;
			  
			  switch (type) {
				  case ehsConstants.dcTypeTextBox:
					d = new textboxDrawingItem(id,x,y,p1,p2,fill,c);
				    break;
				  case ehsConstants.dcTypeRect:
				  	d = new rectDrawingItem(id,x,y,p1,p2,fill,c);
					break;
				  case ehsConstants.dcTypeLine:
				  	d = new lineDrawingItem(id,x,y,p1,p2,fill,c);
					break;
				  case ehsConstants.dcTypeImage:
				  	d = new imageDrawingItem(id,x,y,p1,p2,fill,c);
					break;
				  case ehsConstants.dcTypeText:
				  	d = new textDrawingItem(id,x,y,p1,p2,fill,c);
					break;
				  case ehsConstants.dcTypeElispe:
				  	d = new elispeDrawingItem(id,x,y,p1,p2,fill,c);
					break;
					
				  default:
					d = createCustomDrawingItem(entity,type,id,x,y,p1,p2,p3,p4,fill,c);
					break;
			  }
			  
			  if (d == null) {return (drawingItem)null;}
			  
			  d.setStrokeWidth(dcStrokeWidth);
			  d.setLayer(dcLayer);
			  drawingItems.addElement(d);
			  dcSelIndex = drawingItems.size() - 1;
			  d.setRotAngle(0);
			  d.setTransformBoundingBox(getDCBoundingRect());
			  if(!isLoading()) {
				  d.setUnique(getUniqueID());
				  if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("INSERT INTO sdcdrawingitems (sdcDIID,sdcDIEntity,sdcDIType,sdcDIName,sdcDIOriginX,sdcDIOriginY,sdcDIParam1,sdcDIParam2,sdcDIParam3,sdcDIParam4,sdcDIFilled,sdcDIColor,sdcDIUnique,sdcDIStrokeWidth,sdcDILayer,sdcDIRotAngle) VALUES (null,'"+entity+"',"+String.valueOf(type)+",'"+id+"',"+String.valueOf(x)+","+String.valueOf(y)+",'"+p1+"','"+p2+"','"+p3+"','"+p4+"',"+supportFunctions.valueOf(fill)+",'"+supportFunctions.getColorName(c)+"','"+d.getUnique()+"',"+String.valueOf(dcStrokeWidth)+","+String.valueOf(dcLayer)+",0)","");}
				  // notify users of this drawing canvas that it has changed
				  if (dcMultiUser) {writeVirtualMsg(dcEntity,String.valueOf(DCRefresh),dcSubEntity,"");}
				  if (!stickyTools) {dcMode = ehsConstants.dcTypeSelect;}
				  addUndoItem(d.getUnique(),"Delete","",isClipboardActive());
				  paint();
			  }
			  
			  return d;
		  }	  
		  public void setMode(int m) {dcMode = m;}
		  public int getMode() {return dcMode;}
		  public void setColor(Color c) {dcColor = c;}
		  public Color getColor() {return dcColor;}
		  public void deleteDrawingItem(drawingItem d) {
			  if (d.canDelete()) {
			  d.delete();
			  // remove any connectors attached to drawingItem 'd'
			  for (int i=0;i<connectors.size();i++) {
				drawingItemConnector c = (drawingItemConnector)connectors.elementAt(i);
				if (c.getStart() == d || c.getEnd() == d) {
					deleteConnector(c);
				}
			  }
			  if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("DELETE FROM sdcdrawingitems WHERE sdcDIName='"+d.getID()+"'","");}
			  addUndoItem("abc","Add",d.getParams(getEntity()),isClipboardActive());
			  drawingItems.remove(d);
			  } else {
				  supportFunctions.displayMessageDialog(null,"Application denied delete request.");
			  }
		  }
		  public drawingItem getDIFromUnique(String u) {
			for (int i=0;i<drawingItems.size();i++) {
				drawingItem d = (drawingItem)drawingItems.elementAt(i);
				String tmp = d.getUnique();
				if (tmp.equals(u)) {return d;}
			}
		  
			return (drawingItem)null;
		  }
		  public void loadDrawingItems() {
			  loading = true;
			  clearDrawingCanvas();
			  
			  String ids = supportFunctions.getDBConn().executeSQLQuery("SELECT sdcDIID FROM sdcdrawingitems WHERE sdcDIEntity='"+getEntity()+"'","");
			  if (ids.length() == 0) {return;}
			  Vector v = supportFunctions.splitIntoTokens(ids);
			  for(int i=0;i<v.size();i++) {
				  String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sdcDIType,sdcDIName,sdcDIOriginX,sdcDIOriginY,sdcDIColor,sdcDIFilled,sdcDIParam1,sdcDIParam2,sdcDIParam3,sdcDIUnique,sdcDIStrokeWidth,sdcDILayer,sdcDIParam4,sdcDIRotAngle FROM sdcdrawingitems WHERE sdcDIID="+(String)v.elementAt(i),"");
				  Vector v1 = supportFunctions.splitIntoTokens(data);
  				  String[] tokens = new String[v1.size()];
				  v1.copyInto(tokens);
				  boolean fill = false;
				  if (tokens[5].equals("1")) {fill = true;}
				  if (Integer.parseInt(tokens[0]) == ehsConstants.dcTypeConnector) {
					drawingItem start = getDIFromUnique(tokens[6]);
					drawingItem end = getDIFromUnique(tokens[7]);
					if (start != null && end != null) {
						Vector labels = supportFunctions.splitIntoTokens(tokens[8]);
						Vector symbols = supportFunctions.splitIntoTokens(tokens[12]);
						createConnector(start,end,(String)labels.elementAt(0),(String)labels.elementAt(1),tokens[1],Integer.parseInt((String)symbols.elementAt(0)),Integer.parseInt((String)symbols.elementAt(1)),Integer.parseInt(tokens[11]),false);
					} else {
						supportFunctions.displayMessageDialog(null,"Invalid Connector");
					}
				  } else {
					drawingItem d = addDrawingItem(getEntity(),Integer.parseInt(tokens[0]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),tokens[6],tokens[7],fill,supportFunctions.getColorCode(tokens[4]));
					d.setID(tokens[1]);
					d.setUnique(tokens[9]);
					d.setStrokeWidth(Integer.parseInt(tokens[10]));
					d.setRotAngle(Integer.parseInt(tokens[13]));
					d.setTransformBoundingBox(getDCBoundingRect());
					int layer = Integer.parseInt(tokens[11]);
					d.setLayer(layer);
					if (layerMan.getLayer("Layer " + String.valueOf(layer)) == null) {
					layerMan.addNewLayer("Layer " + String.valueOf(layer));
					}
				}
			  }
			  dcNum = v.size();
			  loading = false;
			  paint();
		  }
		  public void saveDrawingItems() {}
		  public void createMenu() {
			  dcConnectorMenu = new PopupMenu("Connector_Menu");
			  MenuItem item = new MenuItem("Delete Connector");
			  item.addActionListener(this);
			  dcConnectorMenu.add(item);
			  item = new MenuItem("Connector Properties");
			  item.addActionListener(this);
			  dcConnectorMenu.add(item);


			  dcMenu = new PopupMenu("Drawing_Canvas");
			  item = new MenuItem("Cut");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  item = new MenuItem("Copy");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  item = new MenuItem("Paste");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  dcMenu.addSeparator();
			  item = new MenuItem("Undo");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  item = new MenuItem("Redo");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  dcMenu.addSeparator();
				
				cbPictureFrame = new CheckboxMenuItem("Show Picture Frame",false);
				cbPictureFrame.addItemListener(this);
				dcMenu.add(cbPictureFrame);
				dcMenu.addSeparator();
				//item = new MenuItem("Add GC Sheet");
				//item.addActionListener(this);
				//dcMenu.add(item);
				//item = new MenuItem("Delete GC Sheet");
				//item.addActionListener(this);
				//dcMenu.add(item);
				item = new MenuItem("Switch GC Sheet");
				item.addActionListener(this);
				dcMenu.add(item);
				dcMenu.addSeparator();
			  
			  item = new MenuItem("Layers");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  item = new MenuItem("Add Connector");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  item = new MenuItem("Virtual Message");
			  item.addActionListener(this);
			  dcMenu.add(item);
			  dcMenu = customCreateMenu(dcMenu);
		  }
		  public PopupMenu customCreateMenu(PopupMenu menu) {
				int count = menu.getItemCount();
				for (int i=0;i<count;i++) {
					MenuItem mi = menu.getItem(i);
					//String label = mi.getLabel();
				}
				return menu;
		  }
		  public void customUpdateMenu(PopupMenu menu) {
			getMenuItem(menu,"Paste").setEnabled(clipboardDrawingItems.size() != 0);
			getMenuItem(menu,"Cut").setEnabled(selectedDrawingItems.size() != 0);
			getMenuItem(menu,"Copy").setEnabled(selectedDrawingItems.size() != 0);
			getMenuItem(menu,"Undo").setEnabled(undoIndex > -1);
			getMenuItem(menu,"Redo").setEnabled(undoIndex > undoDrawingItems.size() - 1);
			cbPictureFrame.setState(bDrawPictureFrame);		  
		  }
		  public void itemStateChanged(ItemEvent evt) {
			  if (evt.getSource() == cbPictureFrame) {
				  TRACE("Changed show picture frame state",4);
				  bDrawPictureFrame = cbPictureFrame.getState();
			  }
			  if (evt.getSource() == cbItem) {
				  dcFilled = cbItem.getState();
			  }
			  if (evt.getSource() == cbGrid) {
				  gridVisible = cbGrid.getState();
			  }
			  paint();
		  }
		  public void writeVirtualMsg(String entity,String type,String p1,String p2) {
			  //if (systemUserReg.getUseDatabase()) {
				  supportFunctions.getDBConn().executeSQLQuery("INSERT INTO sysehsmsg (sysEHSMsgID,sysEHSMsgEntity,sysEHSMsgType,sysEHSMsgParam1,sysEHSMsgParam2) VALUES (null,'"+entity+"','"+type+"','"+p1+"','"+p2+"')","");
			  //}
		  }
		  public String readVirtualMsg() { // called by the DC thread every few seconds for a multi user canvas
			  String msg = "";
			  if (bVirtualMsgProcessing) {return msg;}
			  //if (systemUserReg.getUseDatabase()) {
				  msg = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSMsgID,sysEHSMsgEntity,sysEHSMsgType,sysEHSMsgParam1,sysEHSMsgParam2 FROM sysehsmsg WHERE sysEHSMsgEntity='"+getEntity()+"' AND sysEHSMsgID>"+String.valueOf(dcLastMsgID)+" LIMIT 1","");
			  //} 
			  if (msg.length() == 0) {return msg;}
			  
			  TRACE("Read Virtual Msg: "+msg,4);
			  Vector v = supportFunctions.splitIntoTokens(msg);
			  String[] tokens = new String[v.size()];
			  v.copyInto(tokens);
			  dcLastMsgID = Integer.parseInt(tokens[0]);
			  processVirtualMsg(tokens[1],tokens[2],tokens[3],tokens[4]);
			  return msg;
		  }
		 public void processCustomVirtualMsg(String entity,String msgType,String param1,String param2) {
			int msgID = Integer.parseInt(msgType);
			switch (msgID) {
				case DCRefresh:
					//loadDrawingItems();
					break;
				}
		 }
		  public void processVirtualMsg(String entity,String msgType,String param1,String param2) {
			TRACE("Process Virtual Msg: E="+entity+", Type="+msgType+", P1="+param1+", P2="+param2,4);
			if (param1.equals(dcSubEntity)) { // ignore our own virtual messages
 				TRACE("Ignored own virtual msg",4);
				return;
			} 
			boolean bProcessed = false;
			bVirtualMsgProcessing = true;
			int msgID = Integer.parseInt(msgType);
				switch (msgID) {
					case DCMsg:
						supportFunctions.displayMessageDialog(null,param2);
						bProcessed = true;
						break;
				}
			
			if (!bProcessed) {
				processCustomVirtualMsg(entity,msgType,param1,param2);
			}			
			bVirtualMsgProcessing = false;
		}
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
			  this("",dcMaxX,dcMaxY,dcGridSpaceX,dcGridSpaceY,true);
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
		  public void setSize(Dimension d) {dcMaxX=(int)d.getWidth();dcMaxY=(int)d.getHeight();dC.setSize(d);}
		  public Dimension getSize() {return dC.getSize();}
	}
	
	public void displayFAQ(String product) {
		JTextArea ta = new JTextArea("",8,40);
		ta.setBackground(new Color(255,255,170));
		ta.setWrapStyleWord(true);
		JPanel p = new JPanel();
		JScrollPane sp = new JScrollPane(ta);
		p.add(sp);
		supportFunctions.displayTextFile(exFAQFile,ta);
		supportFunctions.displayPanelDialog(null,p,product + " - FAQ");
	}
}

