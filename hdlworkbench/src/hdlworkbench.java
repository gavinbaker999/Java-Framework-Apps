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
import java.awt.geom.*;
import java.lang.reflect.*;
import java.util.Date;
import java.util.Map;
import java.text.*;
import java.beans.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
import javax.script.*;
import javax.swing.filechooser.*;
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
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.Utilities;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.ParagraphView;
import javax.swing.text.html.HTMLDocument.*;

import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.DefaultStyledDocument;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class hdlworkbench extends JApplet implements ChangeListener,Runnable
{
	public		String		dataRelativePath = "..";
	
	protected	Thread		systemThread1;
	protected	mainCard	mainTab;
	protected 	designCard	designTab;
	protected	resultsCard	resultsTab;
	protected	helpCard	helpTab;

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
	
	protected	hdlSystem hdlworkbench;
	
	protected	customIcon			ciHelp;
	
	protected	 int		TRACELEVEL = 4;
	protected	boolean					bSymbolDump = false;
	
	protected	static final int		sysThreadSleep = 1000;
	protected	static final int		systemMsgThreadPrioity = 7;

	protected   static final int 		defaultDialogX = 150;
	protected	static final int 		defaultDialogY = 90;
	protected	static final int		defaultMenuX = 100;
	protected	static final int		defaultMenuY = 5;
	protected	static final int		iconWidth = 16;
	protected	static final int		iconHeight = 16;

	protected	static final int		dcTypeHDL = 10;
	protected	static final int		dcTypeHDLConn = 11;
	protected	static final int 		visibleDCWidthChars = 25;
	protected	static final int 		visibleDCHeightChars = 10;

	protected	String		exHelpFile=dataRelativePath+"/"+appDirectory+"/"+"readme.txt";
	protected	static String			exFAQFile="";
	protected	static final String		appDirectory = "hdlworkbench";
	protected	static final String		appClassName = "hdlworkbench";
	protected	static final String		appBaseURL = "http://endhousesoftware.byethost11.com";
	
	public		String		splashJPG = dataRelativePath+"/"+appDirectory+"/hdlwb_logo.jpg";
	public		String		productKBFile = "hdl_kb.txt";
	
	public		static final int 		iNumberCharacetrsInHDLCodePopupWindowRow = 25;
	
	public		static final String		buildDate = "@@@Build Date: 8-March-2019 05:13 PM Build Number: 16@@@";
	public		static final String		frameworkBuildDate="###JAVA Framework (Version 1.41-RC3)###";
	public 		static final String		gitVersionInfo = "!!!Git Version : 22.1e71052.master-dirty.2019-03-08.17:13:41!!!";
	
	public		static final String VHDLTypes = "bit,std_logic,int,string,qsim_state_vector,std_logic_vector";
	public		static final String VerilogTypes = "bit";
	
	//public		static final String standVHDLFunctions = "write";
	//public		static final String standVerilogFunctions = "write";
	// keywords that contain spaces in the source code must appear in pairs
	public		static final String		VHDLReservedWords = "force,end block,endblock,end record,endrecord,end case,endcase,end fuction,endfunction,end procedure,endprocedure,end loop,endloop,end process,endprocess,end component,endcomponent,port map,portmap,use entity,useentity,end if,endif,abs,access,after,alias,all,and,architecture,array,assert,attribute,begin,block,body,buffer,bus,case,component,configuration,constant,disconnect,downto,else,elsif,end,entity,exit,file,for,function,generate,generic,group,guarded,if,impure,in,inertial,inout,is,label,library,linkage,literal,loop,map,mod,nand,new,next,nor,not,null,of,on,open,or,others,out,package,port,postponed,procedure,process,pure,range,record,register,reject,rem,report,return,rol,ror,select,severity,signal,shared,sia,sll,sra,srl,subtype,then,to,transport,type,unaffected,units,util,use,variable,wait,when,while,with,xnor,xor";
	public		static final String[]		VHDLNYSWords = {"shared","postponed","pure","impure","guarded","bus","register","transport","inertial","reject"};
	// appear in pairs, attribute string,transtext (In transtext, *1 = , and *2 = atrribute signal and *3 = Entity Name and *4 = Scope and *5 = Optional Param (int) and *6 = Optional Param)
	// trans text strings need to contain spaces as they would if they had been through preProcessLine(...)
	public		static final String		VHDLAttribs="event,event ( '*2' ),active,active ( '*2' ),last_value,last_value ( '*2' ),last_event,last_event ( '*2' ),last_active,last_active ( '*2' ),driving_value,driving_value ( '*2' ),driving,driving ( '*2' ),transaction,transaction ( '*2' ),delayed,delayed ( '*2' *1 *5 ),stable,stable ( '*2' *1 *5 ),quiet,quiet ( '*2' *1 *5 )";
	// keywords that contain spaces in the source code must appear in pairs
	// trans text strings need to contain spaces as they would if they had been through preProcessLine(...)
	public		static final String		VerilogReservedWords = "force,and,always,assign,begin,buf,bufif0,bufif1,case,casex,casez,cmos,deassign,default,defparam,disable,edge,else,end,endcase,endfunction,endprimitive,endmodule,endspecify,endtable,endtask,event,for,force,forever,fork,function,highz0,highz1,if,ifnone,initial,inout,input,integer,join,large,macromodule,medium,module,nad,negedge,nor,not,notif0,notif1,nmos,or,output,parameter,pmos,posedge,primitive,pulldown,pullup,pull0,pull1,rcmos,real,realtime,reg,release,repeat,rnmos,rpmos,rtran,rtranif0,rtranif1,scalared,small,specify,specparam,strength,string0,strong1,supply0,supply1,table,task,tran,tranif0,tranif1,time,tri,triand,trior,trireg,tri0,tri1,vectored,wait,wand,weak0,weak1,while,wire,wor,xnor,xor";
	public		static final String[]		VerilogNYSWords = {""};
	// appear in pairs, attribute string,transtext (In transtext, *1 = , and *2 = atrribute signal and *3 = Entity Name and *4 = Scope and *5 = Optional Param (int) and *6 = Optional Param)
	public		String		VerilogAttribs="attrib,transtext";
	public		String		VHDLTransTableName = ""+dataRelativePath+"/" + appDirectory + "/transtables/vhdl.ttd";
	public		String		VerilogTransTableName = ""+dataRelativePath+"/" + appDirectory + "/transtables/verilog.ttd";
	
	protected 	MediaTracker			bilmt = new MediaTracker(this);
	public		Component				lastPositionWindow = null;
	
	public		panelDialog				hdlCodeWindow = null;
	public 		panelDialog getHDLCodeWindow() {return hdlCodeWindow;}
	public 		void setHDLCodeWindow(panelDialog m3d) {hdlCodeWindow = m3d;}
	
	// these class member variables have been moved up to this level so that they can be accessed by other classes easily - naughty!!!
	public		String 				currentProcessName = "";
	public 		int 				passNumber;
	public		int					noNameProcessesID = 0;
	public		String				arrayNamesProcessed = "";

	public		Map<String,Vector>		transTableMap = new HashMap<String,Vector>();
			
	public static final int			unconstrainedArrayValue = 9999;
	
	private	scrollableDrawingCanvas sDC = null;
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
		boolean bStatus = false;
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("trace")) {
				TRACELEVEL = 4;
				continue;
			}
			if (args[i].equals("status")) {
				bStatus = true;
			}
			if (args[i].equals("symboldump")) {
				bSymbolDump = true;
				continue;
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
		
		systemUserReg = new registrationinfo(appDirectory,"HDL Work Bench","HDL Work Bench","WB1000","02.00.0000.00","18/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "HDL Work Bench";
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

		lStrings = new languageStrings();

		String file = (String)commandLineArgs.get("arg0");
		if (file != null) {
				hdlworkbench = new hdlSystem();
				//file = "..\\..\\applications\\" + appDirectory + "\\" + file;
				System.out.println("Compiling " + file + " ...\n");
				hdlworkbench.resetSystemState();
				hdlworkbench.setSystemName(file);
				hdlworkbench.setSystemModified(false);
				hdlworkbench.setSystemFile(new basicFile(file));
				if (hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).compile(hdlworkbench.getSystemName())) {
					System.out.println("Compile successful.\n");
				} else {
					System.out.println(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getErrorString() + "\n");
				}
				if (bSymbolDump) {
					HDLCompiler hdlComp = (HDLCompiler)hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex());
					System.out.println("\n" + hdlComp.getAllSymbolTable().dumpSymbols());
				}
		} else {
			System.out.println("Usage: java -jar hdlworkbench.jar [status] [gui] [trace] [about] [symboldump] filename\n");
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
			new appletframe(new hdlworkbench(),ehsConstants.windowXMax,ehsConstants.windowYMax);
		} else {
			theApp = new appletframe(new hdlworkbench());
			hdlworkbench tmp = (hdlworkbench)theApp.getApplet();
			tmp.applicationCode(args);
		}
	}

	public void start() {
		TRACE("start() called",3);
		runThreads = true;
		systemThread1 = new Thread(this);
		systemThread1.setPriority(systemMsgThreadPrioity);
		systemThread1.setName("HDL Work Bench - System Message Thread 1");
		systemThread1.start();
	}

	public void stop() {
		TRACE("stop() called",3);
		runThreads = false;
	}

	public void destroySystem() {
		
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
		
	public class hdlSystem {
		private compiler[] compilers = new compiler[2];
		private int currentCompilerIndex = 0;
		private String hdlSystemName = "Untitled";
		private boolean hdlSystemModified = false;
		private boolean hdlSystemCompiled = false;
		private basicFile hdlSystemFile = null;
		private int iSheet;
		private int iMaxLevel;
		private int iMarginWidth,iMarginHeight;
		
		public int getMarginWidth() {return iMarginWidth;}
		public int getMarginHeight() {return iMarginHeight;}
		public void setSystemFile(basicFile tmp) {hdlSystemFile = tmp;}
		public basicFile getSystemFile() {return hdlSystemFile;}
		public void setSystemName(String tmp) {hdlSystemName = tmp;}
		public String getSystemName() {return hdlSystemName;}
		public boolean getSystemModified() {return hdlSystemModified;}
		public void setSystemModified(boolean tmp) {hdlSystemModified = tmp;}
		public boolean getSystemCompiled() {return hdlSystemCompiled;}
		public void setSystemCompiled(boolean tmp) {hdlSystemCompiled = tmp;}
		
		public hdlSystem() {
			iMaxLevel = -1;
			iSheet = 1;
			iMarginWidth =  10 * charWidth;
			iMarginHeight =  2 * charHeight;
			compilers[1] = new VerilogCompiler();
			compilers[0] = new VHDLCompiler();
		}
		public void resetSystemState() {
			iMaxLevel = -1;
			iSheet = 1;
			hdlSystemModified = false;
			hdlSystemCompiled = false;
			hdlSystemName = "Untitled";
			hdlSystemFile = null;
		}
		public int getMaxLevel() {return iMaxLevel;}
		public void setMaxLevel(int i) {iMaxLevel = i;}
		public int getCurrentSheet() {return iSheet;}
		public void setCurrentSheet(int i) {iSheet = i;}
		public void setCompilerIndex(int i) {currentCompilerIndex = i;}
		public int getCompilerIndex() {return currentCompilerIndex;}
		public compiler getCompiler(int index) {return compilers[index];}
			public int getNumberOfCompilers() {return compilers.length;}
	}

	public class JScrollPaneEHS extends JScrollPane {
		JScrollPaneEHS() {
			super(); 
		}
		JScrollPaneEHS(Component view) {
			super(view); 
		}
		public void scrollToLastLine() {
			JScrollBar tmp = verticalScrollBar;
			tmp.setValue(tmp.getMaximum());
		}
	}
	
	public void TRACE(String msg,Vector v,int level) {
		if (v.size() == 0) {TRACE(msg,level);return;}
		String tmp = (String)v.elementAt(0);
		for (int i=1;i<v.size();i++) {
			tmp = tmp + "," + (String)v.elementAt(i);
		}
		TRACE(msg + ":" + tmp,level);
	  }
	  public void TRACE(String msg) {
		  TRACE(msg,3);
	  }
	  public void TRACE(String msg,int level) {
		if(level < TRACELEVEL) {return;}			
		//if (isApplication()) {return;}
		int threadP = Thread.currentThread().getPriority();
		System.err.println("("+supportFunctions.currentDate()+" "+supportFunctions.currentTime()+") ["+Thread.currentThread().getName()+" ("+String.valueOf(threadP)+")] "+msg);
	  }	  
	
	public class hdlSyntaxEditorPane extends syntaxEditorPane {
		public void SEPUpdateMaxLineNumber(int num) {
			// if different max line number redraw line number header
			if (getMaxLineNumber() != num) {
				mainTab.getSEPComp().getHeader().updateMaxLineNumber(num);
			}		
		}
		public void searchReplace() {
			hdlSearchReplaceDialog dlg = controlsFunctions.searchreplaceCommonDialog();
		}
		public void SEPClickedAt(int row,int col) {
		}
		public void setRawText(String s) {
			bInit = true;
			s = s.replaceAll("<","&lt"); 			
			s = s.replaceAll(">","&gt"); 			
						
			compilerTokens tokenizer = new compilerTokens();
			String name = hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getName(); 
			if (name.equals("VHDL")) { // for VHDL
				tokenizer.setReservedWords(VHDLReservedWords);
				tokenizer.setTypes(VHDLTypes);
			} else { // and for Verilog
				tokenizer.setReservedWords(VerilogReservedWords);
				tokenizer.setTypes(VerilogTypes);
			}

			removeAllFormats();
			addFormat(new syntaxEditorPaneFormat("beginLineKeyword","^("+tokenizer.getKeywordORedString()+")\\W","<b><font color='blue'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("endLineKeyword","\\W("+tokenizer.getKeywordORedString()+")$","<b><font color='blue'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("midLineKeyword","\\W("+tokenizer.getKeywordORedString()+")\\W","<b><font color='blue'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("singleLineKeyword","^("+tokenizer.getKeywordORedString()+")$","<b><font color='blue'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("midLineType","\\W("+tokenizer.getTypeORedString()+")\\W","<b><font color='green'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("midLineNumber","\\W(\\d+)\\W","<b><font color='gray'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("listNumber","[\\(;,](\\d+)[;,\\)]","<b><font color='gray'>","</font></b>"));
			addFormat(new syntaxEditorPaneFormat("time","\\W((?:\\d+)(?:ms|us|ns|ps|secs))\\W","<b><font color='gray'>","</font></b>"));

			String tmp = "";
			Vector lines = supportFunctions.splitIntoTokens(s,"\n");
			setMaxLineNumber(lines.size());
			for (int i=0;i<lines.size();i++) {
				String line = (String)lines.elementAt(i);
				line = doFormats(line);
				tmp = tmp + line;
				tmp = tmp + "\n";
			}
			
			tmp = tmp.replaceAll("\\n","<br>\n");

			setText(tmp);
			bInit = false;
		}
	}
	public class hdlSEPComponent extends SEPComponent {
		private JScrollPaneLineNumberHeader header = null;
		private	int							width = 35;
		private int							height = 100000; // fudge !!!
		
		public hdlSEPComponent(syntaxEditorPane jep,int cols,int rows) {
			super(jep,cols,rows);
			
			header = new JScrollPaneLineNumberHeader();
			header.setPreferredSize(new Dimension(width, height));
		}
		public JScrollPaneLineNumberHeader getHeader() {return header;}
	}
	
	public class mainCard extends Panel implements ActionListener,KeyListener,ListSelectionListener,ChangeListener,ItemListener {
		private JButton newBut,openBut,saveBut,compileBut,simulateBut,helpBut;
		private JTextField systemnameTF;
		private JTextArea statusTA;
		private hdlSyntaxEditorPane HDLTA;
		private JList compilers;
		private DefaultListModel compilersM;
		private	JScrollPaneEHS statusScrollPane;
		private JSpinner startTime,stopTime;
		private String timeUnit = "ms";
		private JRadioButton[] timeButtons = new JRadioButton[4];
		private String timeLabels[] = {"ns","us","ms","secs"};
		private ButtonGroup timeGroup = new ButtonGroup();
		private	hdlSEPComponent SEPComp = null;
		
		mainCard() {
			HDLTA = new hdlSyntaxEditorPane();
			HDLTA.addKeyListener(this);
			HDLTA.setBackground(ehsConstants.lightyellow);
			
			compilersM = new DefaultListModel();
			compilers = new JList(compilersM);
			compilers.addListSelectionListener(this);
			compilers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			compilers.setVisibleRowCount(5);
			for(int i=0;i<hdlworkbench.getNumberOfCompilers();i++) {
				compilersM.addElement(hdlworkbench.getCompiler(i).getName());
			}
			compilers.setSelectedIndex(0);
			JScrollPane pane = new JScrollPane(compilers);
			pane.setPreferredSize(new Dimension(5*charWidth,2*charHeight));
			JPanel topPanel = new JPanel();
			JLabel lab = new JLabel("<html><font size='+2' color='red'>HDL Work Bench</font><p><font size='-2'>"+systemUserReg.getAppCopyright()+"</font></html>");
			Font f = lab.getFont();
			lab.setFont(f.deriveFont((float)f.getSize() + 3));
			topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
			topPanel.add(lab);
//			topPanel.add(Box.createRigidArea(new Dimension(5 * charWidth,charHeight)));
//			topPanel.add(pane);
			
			newBut = new JButton("New");
			newBut.addActionListener(this);
			newBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			newBut.setToolTipText("New HDL File");
			openBut = new JButton("Open");
			openBut.addActionListener(this);
			openBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			openBut.setToolTipText("Open HDL File");
			saveBut = new JButton("Save");
			saveBut.addActionListener(this);
			saveBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			saveBut.setToolTipText("Save HDL File");
			compileBut = new JButton("Compile");
			compileBut.addActionListener(this);
			compileBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			compileBut.setToolTipText("Compile HDL File");
			simulateBut = new JButton("Simulate");
			simulateBut.addActionListener(this);
			simulateBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			simulateBut.setToolTipText("Simulate HDL File");
			helpBut = new JButton("Support");
			helpBut.addActionListener(this);
			helpBut.setPreferredSize(new Dimension(10*charWidth,charHeight));
			helpBut.setToolTipText("Help File");

			JPanel butPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			butPanel.add(newBut);
			butPanel.add(openBut);
			butPanel.add(saveBut);
			butPanel.add(compileBut);
			butPanel.add(helpBut);

			startTime = new JSpinner();
			stopTime = new JSpinner();
			startTime.addChangeListener(this);
			stopTime.addChangeListener(this);
			startTime.setPreferredSize(new Dimension(6*charWidth,charHeight));
			stopTime.setPreferredSize(new Dimension(6*charWidth,charHeight));
			JPanel simPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			simPanel.add(new JLabel("Plot Start Time "));
			simPanel.add(startTime);
			simPanel.add(new JLabel(" Stop Time: "));
			simPanel.add(stopTime);
			stopTime.setValue(new Integer(100));
			simPanel.add(simulateBut);
			for (int i=0;i<timeButtons.length;i++) {
				timeButtons[i] = new JRadioButton(timeLabels[i]);
				timeButtons[i].setContentAreaFilled(false);
				timeButtons[i].addItemListener(this);
				timeGroup.add(timeButtons[i]);
				simPanel.add(timeButtons[i]);
			}
			timeButtons[2].setSelected(true);
			
			systemnameTF = new JTextField("",50);
			statusTA = new JTextArea("",7,50);
			statusTA.setEditable(false);
			statusTA.setText(systemUserReg.getMultiLineInfoText());
			systemnameTF.setEditable(false);
			statusTA.setWrapStyleWord(true);
			statusTA.setBackground(ehsConstants.lightyellow);
			f = statusTA.getFont();
			statusTA.setFont(f.deriveFont((float)f.getSize() - 3));
			
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			p.add(topPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			p.add(systemnameTF);
			SEPComp = new hdlSEPComponent(HDLTA,15,15);
			SEPComp.getScrollPane().setRowHeaderView(SEPComp.getHeader());
			SEPComp.getScrollPane().setCorner(JScrollPane.UPPER_LEFT_CORNER,
					new JScrollPaneSolidColorCorner(Color.red));
			SEPComp.getScrollPane().setCorner(JScrollPane.LOWER_LEFT_CORNER,
					new JScrollPaneSolidColorCorner(Color.red));
			p.add(SEPComp.getScrollPane());
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			p.add(butPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			p.add(simPanel);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight/2)));
			statusScrollPane = new JScrollPaneEHS(statusTA);
			statusScrollPane.setPreferredSize(new Dimension(15*charWidth,5*charHeight));
			p.add(statusScrollPane);
			add(p,"West");

			displaySystemName();
								
			saveBut.setEnabled(false);
			compileBut.setEnabled(false);
			simulateBut.setEnabled(false);
		}
		public hdlSEPComponent getSEPComp() {return SEPComp;}
		public void updateMainCard() {}
		public void stateChanged(ChangeEvent evt) {
			Object start = startTime.getValue();
			Object stop = stopTime.getValue();
			Integer istart = (Integer)start;
			Integer istop = (Integer)stop;

			if (evt.getSource() == startTime) {
				//supportFunctions.displayMessageDialog(null,"Start Time: " + istart.toString());
				if (istart.intValue() >= istop.intValue()) {
					startTime.setValue(new Integer(istop.intValue() - 1));
				}
			}
			if (evt.getSource() == stopTime) {
				//supportFunctions.displayMessageDialog(null,"Stop Time: " + istop.toString());
				if (istop.intValue() <= istart.intValue()) {
					stopTime.setValue(new Integer(istart.intValue() + 1));
				}
			}
		}
		public void valueChanged(ListSelectionEvent evt) {
			if (!evt.getValueIsAdjusting()) {
				hdlworkbench.setCompilerIndex(compilers.getSelectedIndex());
				//HDLTA.refreshText();
			}
		}
      		public void itemStateChanged(ItemEvent evt) {
			JRadioButton but = (JRadioButton)evt.getItem();
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				timeUnit = but.getText();
			}
		}
      		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == newBut) {hdlSystemNew();}
			if (evt.getSource() == openBut) {hdlSystemOpen();}
			if (evt.getSource() == saveBut) {hdlSystemSave(true);}
			if (evt.getSource() == compileBut) {hdlSystemCompile();}
			if (evt.getSource() == simulateBut) {hdlSystemSimulate();}
			if (evt.getSource() == helpBut) {
				try {
					URL u = new URL("https://ehsphpapps.herokuapp.com/applications/devtrack");
					miniWebBrowser tmp = new miniWebBrowser(u);
				} catch(Exception e) {;}
			}
		}
		public void keyTyped(KeyEvent evt) {}
		public void keyReleased(KeyEvent evt) {}
		public void keyPressed(KeyEvent evt) {
			hdlworkbench.setSystemModified(true);
			displaySystemName();
			saveBut.setEnabled(true);
			compileBut.setEnabled(true);
			simulateBut.setEnabled(true);
		}
		public void hdlSystemNew() {
			hdlSystemSave(false);
			hdlworkbench.resetSystemState();
			getSEPComp().getHeader().removeAllMarkers();
			HDLTA.setRawText("");
			getSEPComp().getHeader().updateMaxLineNumber(1);
			displaySystemName();
			saveBut.setEnabled(false);
			compileBut.setEnabled(false);
			simulateBut.setEnabled(false);
		}
		public void hdlSystemOpen() {
			hdlSystemSave(false);
			hdlworkbench.resetSystemState();
			String tmp = controlsFunctions.fileOpenDialog("","*." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt());
			if (tmp == null) {return;}
			addStatusMsg("\nLoaded File:"+tmp);
			hdlworkbench.setSystemName(tmp);
			hdlworkbench.setSystemModified(false);
			hdlworkbench.setSystemFile(new basicFile(tmp));
			getSEPComp().getHeader().removeAllMarkers();
			HDLTA.setRawText(hdlworkbench.getSystemFile().loadFile());
			getSEPComp().getHeader().updateMaxLineNumber(getSEPComp().getEditorPane().getMaxLineNumber());
			displaySystemName();
			saveBut.setEnabled(false);
			compileBut.setEnabled(true);
			simulateBut.setEnabled(true);
		}
		public void addStatusMsg(String msg) {
			statusTA.append(msg);
			statusScrollPane.scrollToLastLine();
		}
		public boolean hdlSystemSave(boolean bForceSave) {
			if (hdlworkbench.getSystemModified()) {
				if (!bForceSave) {
					if (JOptionPane.showConfirmDialog(null,"Save File","Save File",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {return false;}
				}
				if (hdlworkbench.getSystemName().contains("Untitled")) {
					String tmp = controlsFunctions.fileSaveDialog("","*." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt());
					if (tmp == null) {return false;}
					hdlworkbench.setSystemFile(new basicFile(tmp));
					hdlworkbench.setSystemName(tmp);
				}
				
				String fileContents = HDLTA.getRawText();
				Vector fileLines = supportFunctions.splitIntoTokens(fileContents,"\n");
				String line = (String)fileLines.elementAt(0);
				hdlworkbench.getSystemFile().saveFile(line+"\n");
				for (int i=1;i<fileLines.size();i++) {
					line = (String)fileLines.elementAt(i);
					hdlworkbench.getSystemFile().appendFile(line+"\n");
				}
				hdlworkbench.setSystemModified(false);
				addStatusMsg("\nFile Saved - " + hdlworkbench.getSystemName());
				displaySystemName();
				saveBut.setEnabled(false);
			}
			return true;
		}
		public void hdlSystemCompile() {
			hdlSystemSave(true);
			addStatusMsg("\nCompiling System ...");
			if (hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).compile(hdlworkbench.getSystemName())) {
				addStatusMsg("\nCompile Successful");
			} else {
				addStatusMsg("\nCompile Failed");
				addStatusMsg(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getErrorString() + "\n");
			}
		}
		public void hdlSystemSimulate() {
			hdlSystemSave(true);
			addStatusMsg("\nSimulating System ...");
			try {
				Object start = startTime.getValue();
				Object stop = stopTime.getValue();
				Integer s1 = (Integer)start;
				Integer s2 = (Integer)stop;
				String simulatePHPFile = "output/" + supportFunctions.getFilenameNoExt(hdlworkbench.getSystemName()) + ".php?entity="+supportFunctions.getFilenameNoExt(hdlworkbench.getSystemName()+"&start="+s1.toString()+"&end="+s2.toString()+"&timeunit="+timeUnit);
				ac.showDocument(new URL(getCodeBase(),simulatePHPFile),"_blank");
			} catch (Exception e) {;}
		}
		public void displaySystemName() {
			String tmp = hdlworkbench.getSystemName();
			tmp = supportFunctions.getFilename(tmp);
			if (hdlworkbench.getSystemModified()) {tmp = tmp + "*";}
			systemnameTF.setText(tmp);
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

	public class HDLConnector {
		private	String	connName;
		private	String	connEntity;
		private	String	connPort;
		private	String	connList;
		
		HDLConnector(String name,String entity,String port,String list) {
			connName = name;
			connEntity = entity;
			connPort = port;
			connList = list;
		}
		public String getConnName() {return connName;}
		public String getConnEntity() {return connEntity;}
		public String getConnPort() {return connPort;}
		public String getConnList() {return connList;}
	}
 	public class designCard extends Panel implements drawingCanvasUtils,ActionListener {
		private	Vector HDLConnectors = new Vector();

		designCard() {			
			setLayout(new BorderLayout());
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));
			textPanel.setBorder(BorderFactory.createTitledBorder("Design"));
			sDC = new scrollableDrawingCanvas(supportFunctions.getTmpFilename("untitled"),ehsConstants.dcMaxX,ehsConstants.dcMaxY,20,20,true);
			getDrawingCanvas().addDrawingCanvasListener(this);
			getDrawingCanvas().setReadOnly(false);
			getDrawingCanvas().setUseDatabase(false);
			getDrawingCanvas().setDrawFocusHandles(false);
			getDrawingCanvas().setSubEntity("subentity");

			ScrollPane sp = sDC.getComponent();
			sp.setSize(charWidth*visibleDCWidthChars,charHeight*visibleDCHeightChars);
			textPanel.add(sp);
			add(textPanel,"Center");
		}
		public HDLConnector getHDLConnector(String name) {			
				for (int i=0;i<HDLConnectors.size();i++) {
					HDLConnector conn = (HDLConnector)HDLConnectors.elementAt(i);
					if (conn.getConnName().equals(name)) {return conn;}
				}
				
				return (HDLConnector)null;
		}
		public void updateDesignCard(boolean b) {
			if (b) {
				String fname ="";
				getDrawingCanvas().hideToolWindows();
			   	if (hdlworkbench.getSystemFile() == null) {			
					fname = controlsFunctions.fileOpenDialog("","*." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt());
					if (fname == null) {return;}
					hdlworkbench.setSystemName(fname);
					hdlworkbench.setSystemModified(false);
					hdlworkbench.setSystemFile(new basicFile(fname));
					mainTab.getSEPComp().getHeader().removeAllMarkers();
					mainTab.HDLTA.setRawText(hdlworkbench.getSystemFile().loadFile());
					mainTab.getSEPComp().getHeader().updateMaxLineNumber(mainTab.getSEPComp().getEditorPane().getMaxLineNumber());
			  	} else {
					mainTab.hdlSystemSave(true);
				}
				Vector v = getDrawingCanvas().getDrawingItemsOfType(dcTypeHDL);
				if (v.size() == 0){generateHDLBlocks();}
			} else {
				getDrawingCanvas().hideToolWindows();
			}
		} 
		public void generateHDLBlocks() {
			modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("Generating Diagram");
			msgD.setText("Generating HDL Blocks ...");
			getDrawingCanvas().clearDrawingCanvas();
			getDrawingCanvas().setUpdate(false);
			createHDLDrawingItems(supportFunctions.getFilenameNoExt(hdlworkbench.getSystemName()));
			processLevelData(getDrawingCanvas().getDrawingItemsOfType(dcTypeHDL));
			processCordData(getDrawingCanvas().getDrawingItemsOfType(dcTypeHDL));
			processHDLConnectors(getDrawingCanvas().getDrawingItemsOfType(dcTypeHDL));
			getDrawingCanvas().setUpdate(true);
			msgD.destory();
			msgD.dispose();
		}
		public void processHDLConnectors(Vector drawingitems) {
			for (int i=0;i<drawingitems.size();i++) {
				HDLDrawingItem d = (HDLDrawingItem)drawingitems.elementAt(i);
				for (int ii=0;ii<HDLConnectors.size();ii++) {
					HDLConnector conn = (HDLConnector)HDLConnectors.elementAt(ii);
					if (d.getUserDefinedName().equals(conn.getConnEntity())) {
						int xCord = 0;
						int rotAngle = 0;
						Rectangle r = d.getBoundingRect();
						Point pt = d.getHDLPortPoint(conn.getConnPort());
						if (pt.x < r.x + r.width - 3) { // 'in' mode port
							TRACE("processHDLConnectors:port " + conn.getConnPort() + " is in mode",4);
							xCord = pt.x - (4*charWidth);
						} else {
							TRACE("processHDLConnectors:port " + conn.getConnPort() + " is out mode",4);
							xCord = pt.x;
							rotAngle = 180;
						}
						int yCord = pt.y - charWidth;
						HDLConnDrawingItem dd = (HDLConnDrawingItem)getDrawingCanvas().addDrawingItem(getDrawingCanvas().getEntity(),dcTypeHDLConn,xCord,yCord,conn.getConnName(),conn.getConnEntity(),conn.getConnPort(),conn.getConnList(),false,Color.black);
						dd.setCanTransform(false);
						dd.setRotAngle(rotAngle);
					}
				}
			}
		}
		public void processLevelData(Vector drawingItems) {
			// reset default level for each HDL drawing item
			hdlworkbench.setMaxLevel(0);
			if (drawingItems.size() == 0) {return;}
			HDLDrawingItem d = (HDLDrawingItem)drawingItems.elementAt(0);
			Rectangle rc = d.getBoundingRect(); // assume all HDL drawing items same size!
			int iNumberPerRow = ehsConstants.dcMaxX/(rc.width + hdlworkbench.getMarginWidth());
			TRACE("processLevelData:Number per row:"+String.valueOf(iNumberPerRow),4);
			
			for (int i=0;i<drawingItems.size();i++) {
				HDLDrawingItem dd = (HDLDrawingItem)drawingItems.elementAt(i);
				dd.setLevel((int)i/iNumberPerRow);
				TRACE("processLevelData:HDL DI:"+dd.getUserDefinedName()+":Set to level:"+String.valueOf(i/iNumberPerRow),4);
				dd.setSheet(1);
			}

			hdlworkbench.setMaxLevel((int)((drawingItems.size()-1)/iNumberPerRow));
			TRACE("processLevelData:Max Level:"+String.valueOf(hdlworkbench.getMaxLevel()),4);
		}
		public void processCordData(Vector drawingitems) {
			for (int i=0;i<=hdlworkbench.getMaxLevel();i++) {
				int iNumOnLevel = 0;
				for (int ii=0;ii<drawingitems.size();ii++) {
					HDLDrawingItem d = (HDLDrawingItem)drawingitems.elementAt(ii);
					Rectangle rc = d.getBoundingRect();
					if (d.getLevel() == i) {
						int xCord = hdlworkbench.getMarginWidth() + ((hdlworkbench.getMarginWidth() + rc.width) * iNumOnLevel);
						int yCord = hdlworkbench.getMarginHeight() + ((hdlworkbench.getMarginHeight() + rc.height) * i);
						TRACE("processLevelData:HDL DI:"+d.getUserDefinedName()+":Set to ("+String.valueOf(xCord)+","+String.valueOf(yCord)+")",4);
						d.moveTo(new Point(xCord,yCord));
						iNumOnLevel++;
					}
				}
			}
		}
		public void createHDLDrawingItems(String filename) {
			org.w3c.dom.Element 	root;
			org.w3c.dom.Document 	doc;
			
			xmlDataFile hdlItems = new xmlDataFile();
			String filename1 = dataRelativePath+"/"+appDirectory+"/output/" + filename + "_hdlitems";
			TRACE("createHDLDrawingItems:XML Filename:"+filename1,4);
			if (!hdlItems.openXMLDataFile(filename1,"hdldrawingitems",false)) {
				supportFunctions.displayMessageDialog(null,"Failed to open HDL drawing items XML Data");
				root = null;
				doc = null;
				return;
			}
			root = hdlItems.getRootElement();
			doc = hdlItems.getXMLDocument();
			
			HDLConnectors.removeAllElements();
			NodeList n = supportFunctions.executeXPathExpr(doc,"/hdldrawingitems/entity");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				String entityName = e.getAttribute("name");
				String entityPath = e.getAttribute("path");
				TRACE("createHDLDrawingItems:entity name:"+entityName+":entity path:"+entityPath,4);

				NodeList n1 = supportFunctions.executeXPathExpr(doc,"/hdldrawingitems/entity[@name='" + entityName + "']/allports");
				org.w3c.dom.Element e2 = (org.w3c.dom.Element)n1.item(0);
				String allPorts = e2.getAttribute("name");
				TRACE("createHDLDrawingItems:allports:"+allPorts,4);

				n1 = supportFunctions.executeXPathExpr(doc,"/hdldrawingitems/entity[@name='" + entityName + "']/port");
				for (int ii=0;ii<n1.getLength();ii++) {
					org.w3c.dom.Element e1 = (org.w3c.dom.Element)n1.item(ii);
					String portName = e1.getAttribute("name");
					String portMode = e1.getAttribute("mode");
					String portType = e1.getAttribute("type");
					TRACE("createHDLDrawingItems:port name:"+portName+":mode:"+portMode+":type:"+portType,4);
				}
				HDLDrawingItem d = (HDLDrawingItem)getDrawingCanvas().addDrawingItem(getDrawingCanvas().getEntity(),dcTypeHDL,0,0,entityName,allPorts,entityPath,"",false,Color.black);
				d.setCanTransform(false);
				d.setUserDefinedName(entityName);
			}
			n = supportFunctions.executeXPathExpr(doc,"/hdldrawingitems/connlist");
			for (int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				String name = e.getAttribute("name");
				String entity = e.getAttribute("entity");
				String port = e.getAttribute("port");
				String clist = e.getAttribute("clist");
				TRACE("createHDLDrawingItems:conn list name:"+name+":Entity:"+entity+":Port:"+port+":list:"+clist,4);
				HDLConnectors.addElement(new HDLConnector(name.trim(),entity.trim(),port.trim(),clist.trim()));
			}
			
			hdlItems.closeXMLDataFile();
		} 
		public void rightClickAction(Vector v,MouseEvent evt) {;}
		public void leftClickAction(drawingItem d,MouseEvent evt) {;}
		public void leftClickSelectedAction(Vector v,MouseEvent evt) {;}
		public void doubleClickAction(drawingItem d,MouseEvent evt) {
			if (d != null) {
				if (d.getType() != dcTypeHDL) {return;}
				HDLDrawingItem dd = (HDLDrawingItem)d;
				TRACE("doubleClickAction:source filename:"+dataRelativePath+"/" + appDirectory + "/" + dd.getPath() + "/" + dd.getUserDefinedName() + "." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt(),4);
				miniTextEditor ddd = new miniTextEditor(supportFunctions.getTopLevelParent(this),""+dataRelativePath+"/" + appDirectory + "/" + dd.getPath() + "/" + dd.getUserDefinedName() + "." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt());
				if (ddd.isSaved()) {
					supportFunctions.displayMessageDialog(null,"point A");
					String fname = ""+dataRelativePath+"/" + appDirectory + "/" + dd.getPath() + "/" + dd.getUserDefinedName() + "." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt();
					hdlworkbench.setSystemName(fname);
					hdlworkbench.setSystemModified(false);
					hdlworkbench.setSystemFile(new basicFile(fname));
					mainTab.getSEPComp().getHeader().removeAllMarkers();
					mainTab.HDLTA.setRawText(hdlworkbench.getSystemFile().loadFile());
					mainTab.getSEPComp().getHeader().updateMaxLineNumber(mainTab.getSEPComp().getEditorPane().getMaxLineNumber());
					mainTab.hdlSystemCompile();
					generateHDLBlocks();
				}
			}
		}
		public void hoverAction(drawingItem d,MouseEvent evt) {			
			if (d != null) {				
				if (d.getType() != dcTypeHDLConn) {return;}
				TRACE("hoverAction:Entered",4);
				
				panelDialog m3d = getHDLCodeWindow();			
				if (m3d == null) {
					JTextArea tf = null;
					JPanel p = new JPanel();
					HDLConnDrawingItem dd = (HDLConnDrawingItem)d;
					String code = "This port is connected to " + dd.getPortNetList();
					TRACE("hoverAction:PortNetList:"+code,4);
					tf = new JTextArea(code,2,iNumberCharacetrsInHDLCodePopupWindowRow);
					tf.setBackground(new Color(239,237,235));
					tf.setWrapStyleWord(true);
					tf.setEditable(false);
					Font newFont = tf.getFont().deriveFont(tf.getFont().getSize() - 2);
					tf.setFont(newFont);
					p.add(tf);						
					setHDLCodeWindow(supportFunctions.displayPanelDialog(null,p,systemUserReg.getAppName()));
				}
			} else {
				panelDialog m3d = getHDLCodeWindow();
				if (m3d != null) {
					m3d.destory();
					m3d.dispose();
					setHDLCodeWindow((panelDialog)null);
				}
			}
		}
		public void customDoCommand(String cmd,String params,drawingItem d) {;}
		public void focusHandleHit(drawingItem d) {;}
      	public void actionPerformed(ActionEvent evt) {}
		public boolean canvasDoubleClickAction(MouseEvent evt) {return false;}
		public boolean canvasLeftClickAction(MouseEvent evt) {return false;}
	}

	public class resultsCanvas extends printableCanvas implements Printable,MouseListener {
		private	plotData	resultsData = null;
		private	int			hh = 0;
		private	int			ww = 0;

		private	int 		numTimeDivisions = 10;
		private	int 		x1gap = 15 * charWidth;
		private	int 		x2gap = 3 * charWidth;
		private	int 		y1gap = 3 * charHeight;
		private	int 		y2gap = 4 * charHeight;

		public resultsCanvas() {
			   addMouseListener(this);
			   resultsData = null;
			   hh = 0;
			   ww = 0;						   
		}
		public void loadResults() {
			   if (hdlworkbench.getSystemFile() == null) {			
				String fname = controlsFunctions.fileOpenDialog("","*." + hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getFileExt());
				if (fname == null) {resultsData = null;}
				hdlworkbench.setSystemName(fname);
				hdlworkbench.setSystemModified(false);
				hdlworkbench.setSystemFile(new basicFile(fname));
				mainTab.getSEPComp().getHeader().removeAllMarkers();
				mainTab.HDLTA.setRawText(hdlworkbench.getSystemFile().loadFile());
			  }

			  if (resultsData != null) { // results data already loaded
			  } else {		
				resultsData = new plotData();
				if (resultsData.loadResults(supportFunctions.getFilenameNoExt(hdlworkbench.getSystemName())) == false) {resultsData = null;}
			  }
		}
		public int getHH() {return hh;}
		public int getWW() {return ww;}
		public Rectangle getBoundingRect() {return new Rectangle(0,0,ww,hh);}
		public plotData getPlotData() {return resultsData;}
		
		// PrinterJob calls the print method to render the graphics
		// object, starting at pageIndex of 0
		// return PAGE_EXISTS if you have printed that page
		// return NO_SUCH_PAGE if there are no more pages left
		   public int print(Graphics g,PageFormat format,int pagenum) {
			   if (pagenum > 0) {return Printable.NO_SUCH_PAGE;}
			   Dimension size = new Dimension(ehsConstants.dcMaxX,ehsConstants.dcMaxY);
			   Graphics2D g2d = printPageSetup(g,format,size);
			   //printing = true;
			   paint(g2d);
			   printingOnly(g2d);
			   //printing = false;
			   return Printable.PAGE_EXISTS;
		   }
		   public void printingOnly(Graphics2D g2d) {;}
		public void reDraw() {
			paint(getGraphics());
		}
		public void paint(Graphics g) {
			paint((Graphics2D)g);
		}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mousePressed(MouseEvent evt) {
			int xCord = evt.getX();
			int yCord = evt.getY();
			int button=evt.getModifiers();
			
			if ((button & MouseEvent.BUTTON3_MASK) != 0) {return;}

			Vector v = resultsData.getPlottedUniqueSignalNames();
			for (int i=0;i<v.size();i++) {
				Rectangle rcSignal = getSignalRect(i,(String)v.elementAt(i));
				if (rcSignal.contains(xCord,yCord)) {
					supportFunctions.displayMessageDialog(null,"You clicked on signal " + (String)v.elementAt(i));
				}
			}
		}
		public void mouseReleased(MouseEvent evt) {}
		public Rectangle getSignalRect(int index,String signal) {
			return new Rectangle(x1gap,y1gap + ((index*2) * charHeight),ww,2*charHeight);
		}
		public void paint(Graphics2D g2d) {					
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.blue);
			
			if (resultsData == null) {
				supportFunctions.centerTextAtPoint(g2d,"Results file not found - " + supportFunctions.getFilenameNoExt(hdlworkbench.getSystemName()),getWidth() / 2,getHeight() / 2);
				return;
			}
						
			Vector v = resultsData.getPlottedUniqueSignalNames();
			
			hh = ((v.size() * 2) * charHeight) + (2*y1gap) + y2gap;
			ww = getWidth();
			setSize(ww,hh);
			
			g2d.drawLine(x1gap,y1gap,x1gap,hh - y2gap);
			g2d.drawLine(x1gap,hh - y2gap,ww - x2gap,hh - y2gap);
			
			TRACE("width:" + String.valueOf(ww) + " Height:" + String.valueOf(hh),9);
			
			int xAxisLength = ( ww - x2gap ) - x1gap;
			int yAxisLength = ( hh - y2gap ) - y1gap;
			int timeTickLength = xAxisLength / numTimeDivisions;
			int timeTickValue = (resultsData.getStopTime() - resultsData.getStartTime()) / numTimeDivisions;
			
			g2d.drawString(resultsData.getID(),2*charWidth,hh - (2 * charHeight));
			supportFunctions.centerTextAtPoint(g2d,"Time (" + resultsData.getTimeUnits() + ")",x1gap  + (xAxisLength / 2),hh - charHeight - (charHeight / 2));
			for (int i=0;i<=numTimeDivisions;i++) {
				supportFunctions.centerTextAtPoint(g2d,String.valueOf(i * timeTickValue),x1gap + (timeTickLength * i),hh - (3 * charHeight));
				TRACE("plot:"+String.valueOf(i * timeTickValue),9);
				TRACE("x:" + String.valueOf(x1gap + (timeTickLength * i)) + " y:" + String.valueOf(hh - (3 * charHeight)),9);
			}
			
			int betweenSignalGap = 0;
		for (int i=0;i<v.size();i++) {
				boolean bitSignal = false;
				betweenSignalGap = betweenSignalGap + 2;
				String signalName = (String)v.elementAt(i);
				g2d.drawString(signalName,2*charWidth,y1gap + (betweenSignalGap * charHeight));
				int signalXCord = x1gap;
				int signalYCord = y1gap + (betweenSignalGap * charHeight);
				Vector v1 = resultsData.getSignalPointData();
				float signalNewXCord,signalNewYCord;
				for (int j=0;j<v1.size();j++) {
					plotPointData ppd = (plotPointData)v1.elementAt(j);
					if (signalName.equals(ppd.getSignalName())) {
						TRACE("Signal: " + signalName + " Value:" + String.valueOf(ppd.getSignalValue()) + " Time: " + String.valueOf(ppd.getSignalTime()),4);
						if (ppd.getSignalType() == sigType.BIT) {
							bitSignal = true;
							signalNewXCord = x1gap + (( (float)ppd.getSignalTime() / (float)timeTickValue ) * (float)timeTickLength );
							signalNewYCord = signalYCord;
							g2d.drawLine(signalXCord,signalYCord,(int)signalNewXCord,(int)signalNewYCord);
							signalXCord = (int)signalNewXCord;
							signalYCord = (int)signalNewYCord;
							int yAdjust = 1;
							if ( Integer.parseInt(ppd.getSignalValue()) == 1) {yAdjust = -1;}
							signalNewYCord = signalYCord + ( yAdjust * charHeight);
							g2d.drawLine(signalXCord,signalYCord,(int)signalNewXCord,(int)signalNewYCord);
							signalXCord = (int)signalNewXCord;
							signalYCord = (int)signalNewYCord;
						} else {
							float centerXCord = x1gap + (( (float)ppd.getSignalTime() / (float)timeTickValue ) * (float)timeTickLength );
							float crossOverGap = 15;
							float textXCord = centerXCord + crossOverGap;
							float textYCord = signalYCord - ( charHeight / 2);
							g2d.drawString(ppd.getSignalValue(),textXCord,textYCord);
							g2d.drawLine(signalXCord,signalYCord,(int)(centerXCord-crossOverGap),signalYCord);
							g2d.drawLine((int)(centerXCord-crossOverGap),signalYCord,(int)textXCord,signalYCord-charHeight);
							g2d.drawLine(signalXCord,signalYCord-charHeight,(int)(centerXCord-crossOverGap),signalYCord-charHeight);
							g2d.drawLine((int)(centerXCord-crossOverGap),signalYCord-charHeight,(int)textXCord,signalYCord);
							signalXCord = (int)textXCord;
						}
				}
			}
				// complete signal plot to simulation end time
				if (bitSignal) {
					signalNewXCord = x1gap + (( resultsData.getStopTime() / timeTickValue ) * timeTickLength );
					g2d.drawLine(signalXCord,signalYCord,(int)signalNewXCord,signalYCord);
				} else {
					signalNewXCord = x1gap + (( resultsData.getStopTime() / timeTickValue ) * timeTickLength );
					g2d.drawLine(signalXCord,signalYCord,(int)signalNewXCord,signalYCord);
					g2d.drawLine(signalXCord,signalYCord-charHeight,(int)signalNewXCord,signalYCord-charHeight);
				}
			}
		}	
	}
	
	public enum sigType {BIT,BUS};	
	public class plotPointData {
		private	String	signalName;
		private	sigType signalType;
		private	String	signalValue;
		private int	signalTime;
		
		public plotPointData(String signalName,sigType signalType,String signalValue,int signalTime) {
			this.signalName = signalName;
			this.signalType = signalType;
			this.signalValue = signalValue;
			this.signalTime = signalTime;
		}
		
		public String getSignalName() {return signalName;}
		public sigType getSignalType() {return signalType;}
		public String getSignalValue() {return signalValue;}
		public int getSignalTime() {return signalTime;}
	}
	public class plotData {
		private	int	startTime;
		private int	stopTime;
		private Vector	pointData = new Vector();
		private Vector	signalNames = new Vector();
		private Vector	signalPlot = new Vector();
		private String	id = "";
		private String	timeUnits = "";
		
		public plotData() {
			startTime = 0;
			stopTime = 100;
			id = "";
		}
		public Vector getUniqueSignalNames() {return signalNames;} // return all signal names wheter to plot or not
		public Vector getPlottedUniqueSignalNames() {
			Vector v = new Vector();
			
			for (int i=0;i<signalNames.size();i++) {
				if (getSignalPlot((String)signalNames.elementAt(i))) {v.addElement((String)signalNames.elementAt(i));}
			}
			
			return v; // return all signal names to plot
		} 
		public Vector getSignalPointData() {return pointData;}
		public int getStartTime() {return startTime;}
		public void setStartTime(int t) {startTime = t;}
		public int getStopTime() {return stopTime;}
		public void setStopTime(int t) {stopTime = t;}
		public void setTimeUnits(String s) {timeUnits = s;}
		public String getTimeUnits() {return timeUnits;}
		public void setID(String s) {id = s;}
		public String getID() {return id;}
		public boolean getSignalPlot(String s) {
			int index = signalNames.indexOf(s);
			if (index != -1) {
				String tmp = (String)signalPlot.elementAt(index);
				if (tmp.equals("0")) {return false;}
			}
			return true;
		}
		public void setSignalPlot(String s,boolean b) {
			int index = signalNames.indexOf(s);
			if (index == -1) {return;}
			if (b) {signalPlot.setElementAt("1",index);} else {signalPlot.setElementAt("0",index);}
		}
		public boolean loadResults(String baseFilename) {
			pointData.removeAllElements();
			signalNames.removeAllElements();
			signalPlot.removeAllElements();
			
			basicFile resultsFile = new basicFile(""+dataRelativePath+"/"+appDirectory+"/output/results",baseFilename + ".res");
			String tmp = resultsFile.readFileLine(); // read simulation id string
			if (tmp == null) {return false;} // results file not found
			setID(baseFilename + " - " + tmp);
			tmp = resultsFile.readFileLine(); // read simulation time units
			setTimeUnits(tmp);
			tmp = resultsFile.readFileLine(); // read simulation start time
			setStartTime(Integer.parseInt(tmp));
			do {
				tmp = resultsFile.readFileLine();
				if (tmp != null && tmp.length() != 0) {
					if (tmp.contains(",")) {
						Vector v = supportFunctions.splitIntoTokens(tmp,",");
						String signame = (String)v.elementAt(0);
						if (signame.indexOf("intsignal") == 0) {continue;}
						sigType signalType = sigType.BIT;
						if (Integer.parseInt((String)v.elementAt(1)) == 1) {signalType = sigType.BUS;}
						plotPointData ppd = new plotPointData((String)v.elementAt(0),signalType,(String)v.elementAt(2),Integer.parseInt((String)v.elementAt(3)));
						pointData.addElement(ppd);
						
						if (!signalNames.contains((String)v.elementAt(0))) {
								signalNames.addElement((String)v.elementAt(0));
								signalPlot.addElement("1"); // default is to plot signal
						}
					} else {
						setStopTime(Integer.parseInt(tmp));
						return true;
					}
				}
			} while (tmp != null);
			
			return true;
		}
	}
	
	public class plotOptionsDialog extends JDialog implements ActionListener {
		private JButton	butOK;
		private JButton butCancel;
		private ScrollPane sPane;
		private plotData pData;
		private Vector sigNames = new Vector();
		private Vector sigCB = new Vector();
	   
		public plotOptionsDialog(Frame parent,plotData pD) {
		   super(parent,"Plot Options",true);

		   pData = pD;
		   sigNames = pData.getUniqueSignalNames();
		   
		   butOK = new JButton("Ok");
		   butCancel = new JButton("Cancel");
		   butOK.addActionListener(this);
		   butCancel.addActionListener(this);
		   butOK.setPreferredSize(new Dimension(10*charWidth,charHeight));
		   butCancel.setPreferredSize(new Dimension(10*charWidth,charHeight));

		   JPanel p = new JPanel();
		   p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
		   p.add(butOK);
		   p.add(butCancel);
		   
		   JPanel  p1 = new JPanel();
		   sPane = new ScrollPane();
		   p1.setLayout(new GridLayout(2,(sigNames.size() / 2) + 1));
		   for (int i=0;i<sigNames.size();i++) {
				Checkbox cb = new Checkbox((String)sigNames.elementAt(i));
				cb.setState(pData.getSignalPlot((String)sigNames.elementAt(i)));
				sigCB.addElement(cb);
				p1.add(cb);
		   }
		   sPane.add(p1);
		   
		   add(sPane,"Center");
		   add(p,"South");
		   
		   addWindowListener(new WindowAdapter() {
   		     public void windowClosing(WindowEvent evt) {
			     dispose();
		     }
		   });
		   pack();
		   setVisible(true);
		   setSize(800,8*charHeight);
	   }
	  	   
	   public void destroy() {
	   	dispose();
	   }
	   
	   public void actionPerformed(ActionEvent evt) {
		   if (evt.getSource() == butOK) {
				for (int i=0;i<sigNames.size();i++) {
					Checkbox cb = (Checkbox)sigCB.elementAt(i);
					pData.setSignalPlot((String)sigNames.elementAt(i),cb.getState());
				}
			   dispose();
		   }
		   if (evt.getSource() == butCancel) {
			   dispose();
		   }
	   }
	}

 	public class resultsCard extends Panel implements ActionListener {
		private JPanel		resultsPanel,statusPanel;
		private	resultsCanvas	rCanvas;
		private JButton		printButton,optionsButton,exportButton;
		private ScrollPane	sPane;
		
		resultsCard() {
			printButton = new JButton("Print");
			printButton.addActionListener(this);
			printButton.setToolTipText("Print Results");
			optionsButton = new JButton("Plot Options");
			optionsButton.addActionListener(this);
			optionsButton.setToolTipText("Plot Options");
			exportButton = new JButton("Export JPG");
			exportButton.addActionListener(this);
			exportButton.setToolTipText("Export JPG");
			
			setLayout(new BorderLayout());
			resultsPanel = new JPanel();
			resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.Y_AXIS));
			resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
			
			statusPanel = new JPanel();
			statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.X_AXIS));
			statusPanel.add(printButton);
			statusPanel.add(exportButton);
			statusPanel.add(optionsButton);
			
			resultsPanel.add(statusPanel);
			rCanvas = new resultsCanvas();
			sPane = new ScrollPane();
			sPane.setSize(resultsPanel.getWidth(),rCanvas.getHH());
			sPane.add(rCanvas);
			resultsPanel.add(sPane);
			add(resultsPanel);
		}
		public void updateResultsCard() {
			rCanvas.loadResults();
			rCanvas.reDraw();
		}
      		public void actionPerformed(ActionEvent evt) {
    			if (evt.getSource() == printButton) {
    				rCanvas.printCanvas();
    			}
    			if (evt.getSource() == exportButton) {
    				rCanvas.saveAsJPG(rCanvas.getBoundingRect(), "");
    			}
			if (evt.getSource() == optionsButton) {
				plotOptionsDialog dlg = new plotOptionsDialog(supportFunctions.getTopLevelParent(this),rCanvas.getPlotData());
				updateResultsCard();
			}
		}
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

			// perform common actions needed when switching to another tab
			designTab.updateDesignCard(false);			
			
			if(tabPane.getSelectedComponent() == mainTab) {
				mainTab.updateMainCard();
			}
			if(tabPane.getSelectedComponent() == designTab) {
				designTab.updateDesignCard(true);
			}
			if(tabPane.getSelectedComponent() == resultsTab) {
				resultsTab.updateResultsCard();
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
			appProcessName = "EHS-"+"HW-"+supportFunctions.currentShortDate().replace('/', '-')+supportFunctions.currentShortTime().replace(':', '-');
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

		systemUserReg = new registrationinfo(appDirectory,"HDL Work Bench","HDL Work Bench","WB1000","02.00.0000.00","18/10/19","(c) End House Software 2007-2020",splashJPG,exHelpFile,ehsConstants.bRemoteHosted,buildDate,frameworkBuildDate,gitVersionInfo);
		ehsConstants.applicationName = "HDL Work Bench";
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
					   
		modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("HDL Work Bench is Loading");
		
		msgD.setText("Loading Internationalization Database");
		lStrings = new languageStrings();
		
		msgD.setText("Creating User Panels");

		FontMetrics fm = tabPane.getFontMetrics(tabPane.getFont());
		charHeight = fm.getHeight(); // + fm.getAscent(); remove GDB 09/07/2014
		charWidth = fm.stringWidth("O");
		TRACE("init: charWidth=" + Integer.toString(charWidth) + ",charHeight=" + Integer.toString(charHeight),4);

		hdlworkbench = new hdlSystem();
		mainTab = new mainCard();
		designTab = new designCard();
		resultsTab = new resultsCard();
		helpTab = new helpCard();
		
		contentPane.setLayout(new BorderLayout());
		tabPane.add("Main",mainTab);
		tabPane.add("Design",designTab);
		tabPane.add("Results",resultsTab);
		tabPane.add("Help",helpTab);
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
	
	public void executeJavaScript(String filename) {
		try {
			// create a script engine manager
			ScriptEngineManager factory = new ScriptEngineManager();
			// create a JavaScript engine
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			// evaluate JavaScript code from String
			engine.eval("print('Hello, World')");
		} catch (Exception e) {e.printStackTrace();}
	}

	public void symbolNodeClicked(String symbolScoe,String symbolSubScope,String symbolName,String symbolValue) {
	}
	
	public class HDLCompilerTokens extends compilerTokens {
		String processOperatorStrings(String line) {
			String[] ops = {"cat","and","or","xor","nand","nor","xnor","sll","srl","sla","sra","rol","ror"};
			
			for (int i=0;i<ops.length;i++) {
				String[] groups = supportFunctions.regMatchGroups(line,"((\\w+)\\s"+ops[i]+"\\s(\\w+))");
				if (groups.length > 0) {
					line = line.replaceAll(groups[0],"op_"+ops[i]+"("+groups[1]+","+groups[2]+")");
				}
			}
			return line;
		}		
	}
	public class VerilogcompilerTokens extends HDLCompilerTokens {
		public String preProcessLine(String line,boolean bKeywords) {
			TRACE("preProcessLine:VerilogcompilerTokens",4);
			line = processNumberStrings(line);
			line = processOperatorStrings(line);
			line = basePreProcessLine(line,bKeywords);
			line = processNoNameProcesses(line);
			line = processAttributes(line); // has to be called after basePreProcessLine(...)
			
			// remove space between double syntax operators
			line = line.replaceAll("< =","<=");
			line = line.replaceAll("> =",">=");
			line = line.replaceAll(": =",":=");
			line = line.replaceAll("! =","!=");
			line = line.replaceAll("/ =","/=");
			//line = line.replaceAll("* *","**");

			// remove any spaces in time ids
			line = line.replaceAll(" ns ","ns ");
			line = line.replaceAll(" us ","us ");
			line = line.replaceAll(" ms ","ms ");
			line = line.replaceAll(" secs ","secs ");
			
			return line;
		}
		public String processNoNameProcesses(String line) {
			return line;
		}
		public String processAttributes(String line) {
			TRACE("processAttributes:VerilogcompilerTokens:" + line,4);
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(line,"([0-9a-zA-Z_]+?)'([0-9a-zA-Z_]+?)\\s");
			String[] tokens = p.getFoundStringsArray();
			Vector v5 = supportFunctions.splitIntoTokens(VerilogAttribs,",");
			for (int j=0;j<tokens.length;j++) {
				//supportFunctions.displayMessageDialog(null,"PA0: " + tokens[j]); // debug line
				Vector v4 = supportFunctions.splitIntoTokens(tokens[j],"\'");
				String attrib = (String)v4.elementAt(1);
				attrib = attrib.trim();
				boolean found = false;
				for (int i=0;i<v5.size();i=i+2) {
				//supportFunctions.displayMessageDialog(null,"PA1: " + attrib + "," + (String)v5.elementAt(i)); // debug line
					if (attrib.equals((String)v5.elementAt(i))) {
						String transText = (String)v5.elementAt(i+1);
						//supportFunctions.displayMessageDialog(null,"PA2: " + transText);
						transText = transText.replaceAll("\\*1",",");
						transText = transText.replaceAll("\\*2",(String)v4.elementAt(0));
						transText = transText.replaceAll("\\*3",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName());
						transText = transText.replaceAll("\\*4",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope());				
						//supportFunctions.displayMessageDialog(null,"PA3: " + transText);
						line = line.replaceAll(tokens[j]," " + transText + " ");
						//supportFunctions.displayMessageDialog(null,"PA4: " + line);
						found = true;
						break;
					}
				}
				if (!found) {
					supportFunctions.displayMessageDialog(null,"Attribute " + attrib + " not supported.");
				}
			}
			return line;
		}
	}
	public class VHDLcompilerTokens extends HDLCompilerTokens {
		private	String		noNameProcesses = "";
		
		public String preProcessLine(String line,boolean bKeywords) {
			TRACE("preProcessLine:VHDLcompilerTokens",4);
			line = line.replaceAll(" postponed "," ");
			line = processNumberStrings(line);
			line = processOperatorStrings(line);
			line = preProcessAttributes(line);
			line = basePreProcessLine(line,bKeywords);
			line = processNoNameProcesses(line);
			line = processAttributes(line); // has to be called after basePreProcessLine(...)
			line = processExtras(line);
			
			// remove space between double syntax operators
			line = line.replaceAll(": =",":=");
			line = line.replaceAll("< =","<=");
			line = line.replaceAll("> =",">=");
			line = line.replaceAll("! =","!=");
			//line = line.replaceAll("* *","**");
			line = line.replaceAll("/ =","/=");
			
			// remove any spaces in time ids
			line = line.replaceAll(" ns ","ns ");
			line = line.replaceAll(" us ","us ");
			line = line.replaceAll(" ms ","ms ");
			line = line.replaceAll(" secs ","secs ");
			
			return line;
		}
		public String processExtras(String line) {
			if (line.indexOf(" wait ") != -1) { // has to be called after basePreProcessLine(...)
				line = line.replaceAll(" , ",","); // so a list of sensitivity signals appear as one token
			}
			
			line = line.replaceAll("now","getSimTime()");
			line = line.replaceAll("NOW","getSimTime()");
			
			return line;
		}
		public String processNoNameProcesses(String line) {
			TRACE("processNoNameProcesses:entered:line:"+line,4);
			if (line.indexOf("endprocess") != -1) {
				line = line.replaceAll("endprocess ;","endprocess " + currentProcessName + " ;");
				TRACE("processNoNameProcesses:exited(endprocess):line:"+line,4);
				return line;
			}
			if (line.indexOf("process") == -1) {TRACE("processNoNameProcesses:exited(process):line:"+line,4);return line;}
			if (line.indexOf(":") == -1) {
				// no process name specified
				line = "process" + String.valueOf(noNameProcessesID++) + " : " +line;
			}
			//line = line.replaceAll(" postponed "," "); // as done before calling basePreProcessLine(...)
			line = line.replaceAll(" is "," ");
			if (line.endsWith("process")) {
				int index = line.indexOf(":");
				String pname = line.substring(0,index-1); // -1 added GDB 22/06/2014 to remove space between end of process name and the :
				if (passNumber == 2) { // only add to list on pass 2 of the compiler!!!
					if (noNameProcesses.length() != 0) {noNameProcesses = noNameProcesses + ",";}
					noNameProcesses = noNameProcesses + pname;
				}
				line = line + " ( )";
			}
			TRACE("processNoNameProcesses:exited(processed):line:"+line,4);
			return line;
		}
		public compilerSymbol getCompilerSymbol(String typeName) {
			compilerSymbol cs = hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).symbolTable.findSymbol(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName(),hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope(),typeName,symType.SIGNAL);
			if (cs == null) {cs = hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).symbolTable.findSymbol(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName(),hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope(),typeName,symType.VARIABLE);}
			if (cs == null) {cs = hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).symbolTable.findSymbol(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName(),hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope(),typeName,symType.TYPE);}
			
			return cs;
		}
		public String preProcessAttributes(String line) {
			TRACE("preProcessAttributes:VHDLcompilerTokens:start:" + line,4);

			String optParam = "";
			
			line = line.replaceAll("'driving value","'drivingvalue");
			line = line.replaceAll("UNSIGNED'","");
			line = line.replaceAll("STRING'","");
			
			line = line.replaceAll("integer'high",String.valueOf(unconstrainedArrayValue));
			line = line.replaceAll("integer'low",String.valueOf(-unconstrainedArrayValue));
			line = line.replaceAll("real'high",String.valueOf(unconstrainedArrayValue));
			line = line.replaceAll("real'low",String.valueOf(-unconstrainedArrayValue));

			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(line,"(?i)(\\w+)'simple\\sname");
			String[] tokens = p.getFoundGroupsArray();
			String[] tokens1 = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				line = line.replaceAll(tokens1[i] + "'name",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName());
			}
			p.regExpMatch(line,"(?i)(\\w+)'instance\\sname");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				line = line.replaceAll(tokens1[i] + "'instance name",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName());
			}
			p.regExpMatch(line,"(?i)(\\w+)'path\\sname");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				line = line.replaceAll(tokens1[i] + "'path name",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName());
			}

			p.regExpMatch(line,"(?i)(\\w+)'base'\\w+");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				compilerSymbol cs = hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).symbolTable.findSymbol(hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName(),hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope(),tokens[i],symType.TYPE);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised type (base attrib) - " + tokens[i]);}
				else {line = line.replaceAll(tokens[i]+"'base'",cs.getSymbolValue() + "'");}
			}
			p.regExpMatch(line,"(?i)((\\w+)'leftof\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = Integer.parseInt(tokens[2]);
				index--;
				if (index < 0) {index = 0;}
				line = line.replaceAll(tokens[0],cs.getEnumValue(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'rightof\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = Integer.parseInt(tokens[2]);
				index++;
				if (index >= cs.getEnumCount()) {index--;}
				line = line.replaceAll(tokens[0],cs.getEnumValue(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'pred\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = Integer.parseInt(tokens[2]);
				index--;
				if (index < 0) {index = 0;}
				line = line.replaceAll(tokens[0],cs.getEnumValue(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'succ\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = Integer.parseInt(tokens[2]);
				index++;
				if (index >= cs.getEnumCount()) {index--;}
				line = line.replaceAll(tokens[0],cs.getEnumValue(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'val\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = Integer.parseInt(tokens[2]);
				if (index >= cs.getEnumCount()) {index--;}
				if (index < 0) {index = 0;}
				line = line.replaceAll(tokens[0],cs.getEnumValue(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'pos\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				int index = cs.getEnumIndex(tokens[2]);
				line = line.replaceAll(tokens[0],String.valueOf(index));
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'image\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				line = line.replaceAll(tokens[0],"'"+tokens[2]+"'");
			}
			}
			p.regExpMatch(line,"(?i)((\\w+)'value\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			if (tokens.length != 0) {
			compilerSymbol cs = getCompilerSymbol(tokens[1]);
			if (cs == null) {
				supportFunctions.displayMessageDialog(null,"Unrecognised " + tokens[0]);
			} else {
				line = line.replaceAll(tokens[0],tokens[2].substring(1,tokens[2].length()-1));
			}
			}
			
			p.regExpMatch(line,"(?i)((\\w+)'range\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (range attrib) - " + tokens[1]);}
				else {
					if (cs.getStringLowerRange().length() != 0) {supportFunctions.displayMessageDialog(null,"range NYS for string ranges");} else
					{
						int lower = cs.getLowerRange();
						int upper = cs.getUpperRange();
						String newRange = String.valueOf(lower);
						if(lower < upper) {newRange = newRange + " to ";} else {newRange = newRange + " downto ";}
						newRange = newRange + String.valueOf(upper);
						line = line.replaceAll(tokens[0],newRange);
					}
				}
			}
				
			p.regExpMatch(line,"(?i)((\\w+)'reverse_range\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (reverse_range attrib) - " + tokens[1]);}
				else {
					if (cs.getStringLowerRange().length() != 0) {supportFunctions.displayMessageDialog(null,"reverse_range NYS for string ranges");} else
					{
						int lower = cs.getUpperRange(); // remember we are swapping them around !!!
						int upper = cs.getLowerRange();
						String newRange = String.valueOf(lower);
						if(lower < upper) {newRange = newRange + " to ";} else {newRange = newRange + " downto ";}
						newRange = newRange + String.valueOf(upper);
						line = line.replaceAll(tokens[0],newRange);
					}
				}
			}
				
			p.regExpMatch(line,"(?i)((\\w+)'length\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (length attrib) - " + tokens[1]);}
				else {
					if (cs.isArray()) {
						line = line.replaceAll(tokens[0],String.valueOf(cs.size()));
					} else {
						supportFunctions.displayMessageDialog(null,"length attribute only supported for arrays");
					}
				}
			}
			
			p.regExpMatch(line,"(?i)((\\w+)'ascending\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (ascending attrib) - " + tokens[1]);}
				else {
					if (cs.isAscending()) {
						line = line.replaceAll(tokens[0],"true");
					} else {
						line = line.replaceAll(tokens[0],"false");
					}
				}
			}
			
			p.regExpMatch(line,"(?i)((\\w+)'low\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (low attrib) - " + tokens[1]);}
				else {
					if (cs.isArray()) {
						line = line.replaceAll(tokens[0],String.valueOf(Math.min(cs.getLowestBound(),cs.getHighestBound())));
					} else {
						String replace = "";
						if (cs.getEnum()) {
							String[] values = cs.getEnumValues();
							if (values.length != 0) {
								Arrays.sort(values);
								replace = values[0];
							} else {replace = "0";}
						} else {supportFunctions.displayMessageDialog(null,"Unrecognised part (low attrib) - " + tokens[1]);}
						line = line.replaceAll(tokens[0],replace); 
					}
				}
			}
			
			p.regExpMatch(line,"(?i)((\\w+)'high\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (high attrib) - " + tokens[1]);}
				else {
					if (cs.isArray()) {
						line = line.replaceAll(tokens[0],String.valueOf(Math.max(cs.getHighestBound(),cs.getLowestBound())));
					} else {
						String replace = "";
						if (cs.getEnum()) {
							String[] values = cs.getEnumValues();
							if (values.length != 0) {
								Arrays.sort(values);
								replace = values[values.length - 1];
							} else {replace = "0";}
						} else {supportFunctions.displayMessageDialog(null,"Unrecognised part (high attrib) - " + tokens[1]);}
						line = line.replaceAll(tokens[0],replace); 
					}
				}
			}
				
			p.regExpMatch(line,"(?i)((\\w+)'left\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (left attrib) - " + tokens[1]);}
				else {
					if (cs.isArray()) {
						line = line.replaceAll(tokens[0],String.valueOf(cs.getLowerBound()));
					} else {
						String replace = "";
						if (cs.getEnum()) {
							String[] values = cs.getEnumValues();
							if (values.length != 0) {
								replace = values[0];
							} else {replace = "0";}
						} else {supportFunctions.displayMessageDialog(null,"Unrecognised part (left attrib) - " + tokens[1]);}
						line = line.replaceAll(tokens[0],replace); 
					}
				}
			}
			
			p.regExpMatch(line,"(?i)((\\w+)'right\\((.*)\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			if (tokens.length != 0) {
				if (tokens.length > 2) {optParam=tokens[2];} else {optParam="";}
				compilerSymbol cs = getCompilerSymbol(tokens[1]);
				if (cs == null) {supportFunctions.displayMessageDialog(null,"Unrecognised Signal, Variable or Type (right attrib) - " + tokens[1]);}
				else {
					if (cs.isArray()) {
						line = line.replaceAll(tokens[0],String.valueOf(cs.getUpperBound()));
					} else {
						String replace = "";
						if (cs.getEnum()) {
							String[] values = cs.getEnumValues();
							if (values.length != 0) {
								replace = values[values.length - 1];
							} else {replace = "0";}
						} else {supportFunctions.displayMessageDialog(null,"Unrecognised part (right attrib) - " + tokens[1]);}
						line = line.replaceAll(tokens[0],replace); 
					}
				}
			}
			
			// replace any remaining instances of (...) with [...]
			p.regExpMatch(line,"(?i)\\w+'(\\w+\\(.*\\))");
			tokens = p.getFoundGroupsArray();
			tokens1 = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = tokens[i].replaceAll("(","[");
				tmp = tmp.replaceAll(")","]");
				line = line.replaceAll(tokens[i],tmp);
			}
			
			TRACE("preProcessAttributes:VHDLcompilerTokens:end:" + line,4);
			return line;
		}
		public String processAttributes(String line) {
			TRACE("processAttributes:VHDLcompilerTokens:" + line,4);
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(line,"([0-9a-zA-Z_]+?)'([\\[\\]0-9a-zA-Z_]+?)\\s");
			String[] tokens = p.getFoundStringsArray();
			Vector v5 = supportFunctions.splitIntoTokens(VHDLAttribs,",");
			for (int j=0;j<tokens.length;j++) {
				TRACE("PA0: " + tokens[j],4); // debug line
				Vector v4 = supportFunctions.splitIntoTokens(tokens[j],"\'");
				String attrib = (String)v4.elementAt(1);
				attrib = attrib.trim();
				String optParam = "''";
				String optParamI = "0";
				int g = attrib.indexOf("[");
				if (g != -1) {
					optParam = "'" + attrib.substring(g+1,attrib.length()-1) + "'"; // remove trailing ] character
					try {
						Integer.parseInt(attrib.substring(g+1,attrib.length()-1));
						optParamI = attrib.substring(g+1,attrib.length()-1);
					} catch (Exception e) {optParamI = "0";}
					attrib = attrib.substring(0,g-1);
				}
				boolean found = false;
				for (int i=0;i<v5.size();i=i+2) {
				TRACE("PA1: " + attrib + "," + (String)v5.elementAt(i),4); // debug line
					if (attrib.equals((String)v5.elementAt(i))) { // check if we match a VHDLAttribs (1st entry in the pair)
						String transText = (String)v5.elementAt(i+1); // trans text starts as the 2nd entry in the pair
						TRACE("PA2: " + transText,4);
						transText = transText.replaceAll("\\*1",",");
						transText = transText.replaceAll("\\*2",(String)v4.elementAt(0));
						transText = transText.replaceAll("\\*3",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).getEntityName());
						transText = transText.replaceAll("\\*4",hdlworkbench.getCompiler(hdlworkbench.getCompilerIndex()).currentScope());				
						transText = transText.replaceAll("\\*5",optParamI);				
						transText = transText.replaceAll("\\*6",optParam);				
						TRACE("PA3: " + transText,4);
						line = line.replaceAll(tokens[j]," " + transText + " ");
						TRACE("PA4: " + line,4);
						found = true;
						break;
					}
				}
				if (!found) {
					supportFunctions.displayMessageDialog(null,"Attribute " + attrib + " not supported.");
				}
			}
			return line;
		}
	}
	
	public class HDLCompiler extends compiler {
		public compilerSymbolTable allSymbolTable = null;
		public Stack loopLabels;
		public Stack loopVariables;
		
		public HDLCompiler(String s,String s1) {
			super(s,s1);
			allSymbolTable = new compilerSymbolTable(s1);
			loopLabels = new Stack();
			loopVariables = new Stack();
		}
		public compilerSymbolTable getAllSymbolTable() {return allSymbolTable;}
		public boolean isLoop() {if (loopLabels.size() == 0) {return false;} else {return true;}}
		public int getLoopDepth() {return loopLabels.size();}
		public void addLoopLabel(String label) {loopLabels.push(label);}
		public String removeLoopLabel() {return (String)loopLabels.pop();}
		public String getLoopLabel() {return (String)loopLabels.peek();}
		public void addLoopVariable(String label) {loopVariables.push(label);}
		public String removeLoopVariable() {return (String)loopVariables.pop();}
		public String getLoopVariable() {return (String)loopVariables.peek();}
	}

 	public class VHDLCompiler extends HDLCompiler {
		private Properties 	componentPorts = new Properties();
		private String		componentDefName;
		private	pseduoFile	compilerPHPFile;
//		private	basicFile		compilerPHPFile;
		private String		transFunctionPrefix;
		private String 		entityName,archName;
		
		private Vector 		signalFuncLines = new Vector();
		private Vector 		forcesFuncLines = new Vector();
		private Vector 		endOfFuncLines = new Vector();		
		private Vector 		endOfModuleLines = new Vector();
		private	Vector		portDefLines = new Vector();
		private	Vector		portmapDefLines = new Vector();
		
		//private	String 		currentProcessName; // moved up one level so that tokenizer class can access it
		private	String 		currentProcedureName;
		private	String 		currentFunctionName;
		private String		currentSigVarConstantName;
		private symType 	currentSigVarConstantType;
		private	String		currentType;

		private	String		currentParameterName;
		private	String		currentParameterModifier;
		private	String		currentParameterType;
		private	String		currentParameterMode;
		private	String		currentParameterDefault;
		private	int			currentParameterRet;
		private	int			currentParameterPos;
		
		protected String	compiledLine = "";
		protected VHDLcompilerTokens tokenizer;
		
		private	  String	waitStartChain;
				
		public VHDLCompiler() {
			super(VHDLTransTableName,systemUserReg.getAppName());
			tokenizer = new VHDLcompilerTokens();
			tokenizer.setReservedWords(VHDLReservedWords);
			tokenizer.setTypes(VHDLTypes);
			setVarPreDefined(true);
			setCommentString("////");
			setStartMultiLineCommentString("//*");
			setEndMultiLineCommentString("*//");
			
			transFunctionPrefix = "";
			componentDefName = "";
			currentFunctionName = "";
			currentSigVarConstantName = "";
			currentSigVarConstantType = symType.NONE;
			currentType = "";
			currentProcessName = "";
			currentProcedureName = "";
			currentParameterName = "";
			currentParameterModifier = "";
			currentParameterType = "";
			currentParameterMode = "in";
			currentParameterDefault = "";
			currentParameterRet = 0;
			currentParameterPos = 1;
			waitStartChain = "";
			endOfFuncLines.removeAllElements();
			endOfModuleLines.removeAllElements();
		}
		public String getEntityName() {return entityName;}
		public boolean isValidIdentifier(String ident) {
			if (tokenizer.isReserved(ident)) {return false;}
			return isValidID(ident);
		}
		public String compileLine(String line) {
			String PHPCode = "";
			setCompileLine(true);
			Vector lines = supportFunctions.splitIntoTokens(line,";");
			for (int lineNumber=0;lineNumber<lines.size();lineNumber++) {
				compiledLine = "";
				if(!syntaxCheck((String)lines.elementAt(lineNumber))) {return "";}
				PHPCode = PHPCode + compiledLine;
			}
			setCompileLine(false);
			return PHPCode;
		}
		public String clearQuotedStrings(String line) {
			String tmp = line;
			
			String[] groups = supportFunctions.regMatchGroups(tmp,"\"(.*)\"");
			for (int i=0;i<groups.length;i++) {
				tmp = tmp.replaceAll("\""+groups[i]+"\"","\"\"");
			}
			return tmp;
		}
		public boolean syntaxCheck(String line) {
			ehsRegExp p = new ehsRegExp();
			
			p.regExpMatch(line,"(?i)disconnect\\s*(\\w+)\\s*:\\s*.*(after\\s*.*);");
			String[] tokens1 = p.getFoundGroupsArray();
			if(tokens1.length > 0) {
				line = tokens1[0]+"<='Z' " + tokens1[1] + ";";
				//supportFunctions.displayMessageDialog(null,line);
			}
			
			p.regExpMatch(line,"((\\w+)\\s*&\\s*(\\w+))");
			tokens1 = p.getFoundGroupsArray();
			if (tokens1.length > 0) {
				line = line.replaceAll(tokens1[0],tokens1[1] + " cat " + tokens1[2]);
			}
			
			p.regExpMatch(line,"(?i)block\\s*is\\s*$");
			tokens1 = p.getFoundGroupsArray();
			if(tokens1.length > 0) {
				line = line.replaceAll("block","block true");
			}
			
			p.regExpMatch(line,"(?i)block\\s*$");
			tokens1 = p.getFoundGroupsArray();
			if(tokens1.length > 0) {
				line = line.replaceAll("block","block true");
			}

			Vector v = tokenizer.tokenizeLine(line);
			syntaxError = "";

			return syntaxCheckInternal(line,v);
		}
		compilerSymbol isType(String entity,String symbolName) {
			// is symbolName a type or a constant in the symbol table?
			compilerSymbol tmp = null;
			tmp = symbolTable.findSymbol(entity,currentScope(),symbolName,symType.TYPE);
			if (tmp != null) {return tmp;}
			tmp = symbolTable.findSymbol(entity,currentScope(),symbolName,symType.CONSTANT);
			if (tmp != null) {return tmp;}
			symbolName = symbolName.toLowerCase();
			
			if (tokenizer.isType(symbolName)) {
				tmp = new compilerSymbol(entity,"",symbolName,symbolName,symType.INTERNAL,symClass.NONE);
				return tmp;
			}
			
			return (compilerSymbol)null;
		}
		String getRecordType(String entity,String typeName,String typeDef) {
			String retType = "";
			
			//supportFunctions.displayMessageDialog(null,"in:"+typeDef);
			Vector v = supportFunctions.splitIntoTokens(typeDef,";");
			writeDirectToOutput("\nclass " + typeName + " {");
			for (int i=0;i<v.size();i++) {
				Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(i),":");
				if (v1.size()==0) {continue;}
				String tmp = (String)v1.elementAt(1);
				Vector v2 = supportFunctions.splitIntoTokens((String)v1.elementAt(0),",");
				for (int ii=0;ii<v2.size();ii++) {
				if (retType.length() != 0) {retType = retType + "&";}
					retType = retType + (String)v2.elementAt(ii) + "&" + tmp;
					String tmp1 = (String)v2.elementAt(ii);
					writeDirectToOutput("\n\tvar $" +  tmp1.trim() + ";");
				}
			}
			writeDirectToOutput("\n}\n");
			retType = retType.replaceAll(" & ","&");
			//supportFunctions.displayMessageDialog(null,"out:"+retType);
			return retType;
		}
		String getTypeType (String entity,String typeName,String typeDef) {
			// the supplied string for checking is from the start of the type definition to the
			// terminating ; or := or ) characters
			
			String type = "";
			ehsRegExp p = new ehsRegExp();
			typeDef.trim();

			p.regExpMatch(typeDef,"(?i)record(.*)end\\s*record");
			String[] tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				return getRecordType(entity,typeName,tokens[0].trim());
			}
			
			p.regExpMatch(typeDef,"(?i)of\\s(\\w+)");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				TRACE("Point A",5);
				type = tokens[0];
			}

			p.regExpMatch(typeDef,"(?i)(\\w+)\\s\\(\\s(\\w+)\\s(?:to|downto)\\s(\\w+)\\s\\)");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point B",5);
				type = tokens[0];
			}

			p.regExpMatch(typeDef,"(?i)(\\w+)\\srange");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point C",5);
				type = tokens[0];
			}

			p.regExpMatch(typeDef,"(?i)(\\w+)\\s\\(\\s(\\w+)\\s\\)");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point D",5);
				type = tokens[0];
			}

			p.regExpMatch(typeDef,"\\(\\s(.*)\\s\\)"); 
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point E",5);
				type = tokens[0];
				type = type.replaceAll(",","#"); // enum type - can't use comma character to separate elements!
				type = "enum" + type;
			}

			p.regExpMatch(typeDef,"(\\w+)\\s(\\w+)"); 
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point F",5);
				type = tokens[0];
			}

			p.regExpMatch(typeDef,"(\\w+)"); 
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0 && type.length() == 0) {
				TRACE("Point G",5);
				type = tokens[0];
			}

			if (type.length() !=0) {
				// got a type that will either be built-in, in the symbol table or an undefined type
				TRACE("Point H",5);
				compilerSymbol tmp = isType(entity,type);
				if (tmp != null) {
					TRACE("Point I",5);
					String tmp1 = tmp.getSymbolValue();
					if (tmp.getEnum() == true) { tmp1 = "enum" + tmp1;}
					type = tmp1;
					TRACE("Point J",5);
				}
			} else {
				supportFunctions.displayMessageDialog(null,"Invalid type definition: " + typeDef);
			}
			
			TRACE("getTypeType:typedef:"+typeDef+" and type:"+type,5);
			return type;
		}
		public int getNumericTypeStringValue(String entity,String token) {
			TRACE("getNumericTypeStringValue:"+token,4);
			if (tokenizer.isNumeric(token)) {return Integer.parseInt(token);}
			compilerSymbol tmp = isType(entity,token);
			if (tmp != null) {return Integer.parseInt(tmp.getSymbolValue());}
			
			return -unconstrainedArrayValue - 1;
		}
		String lowerStringRange = "";
		String upperStringRange = "";
		String getLowerStringRange() {return lowerStringRange;}
		String getUpperStringRange() {return upperStringRange;}
		int[] getTypeRange(String entity,String typeDef) {
			int[] range = new int[2];
			range[0] = 0;
			range[1] = 0;
			lowerStringRange = "";
			upperStringRange = "";
			
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(typeDef,"(?i)range\\s('*\\w+'*)\\s(?:to|downto)\\s('*\\w+'*)");
			String[] tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				range[0] = getNumericTypeStringValue(entity,tokens[0]);
				range[1] = getNumericTypeStringValue(entity,tokens[1]);	
				if (range[0] == -unconstrainedArrayValue - 1) {lowerStringRange = tokens[0];}
				if (range[1] == -unconstrainedArrayValue - 1) {upperStringRange = tokens[1];}			
				return range;
			}

			p.regExpMatch(typeDef,"(?i)range\\s(\\w+)"); 
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				range[0] = -unconstrainedArrayValue;
				range[1] = unconstrainedArrayValue;				
			}
			
			// this is the last check in the chain, so if it fails just return 0 for the lower and upper range values
			p.regExpMatch(typeDef,"(\\w+)"); // check just for a type identifier
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				compilerSymbol tmp = isType(entity,tokens[0]); // assuming a symType.TYPE or symType.CONSTANT or built in type
				if (tmp == null) {
					range[0] = 0;
					range[1] = 0;
				} else {
					range[0] = tmp.getLowerRange();
					range[1] = tmp.getUpperRange();
				}
				
				return range;
			}

			return range;
		}
		int[] getTypeBounds(String entity,String typeDef) {
			int[] bounds = new int[2];
			bounds[0] = 0;
			bounds[1] = 0;
			
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(typeDef,"(?i)\\((\\w+)\\s(?:to|downto)\\s(\\w+)\\)");
			String[] tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				bounds[0] = getNumericTypeStringValue(entity,tokens[0]);
				bounds[1] = getNumericTypeStringValue(entity,tokens[1]);								
				return bounds;
			}
		
			p.regExpMatch(typeDef,"\\((\\w+)\\)");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				// found term will either be an integer representing upper bound (lower bound=0) or another type id
				if (tokenizer.isNumeric(tokens[0])) {
					bounds[0] = 0;
					bounds[1] = Integer.parseInt(tokens[0]);
				} else {
					compilerSymbol tmp = isType(entity,tokens[0]); // assuming a symType.TYPE or symType.CONSTANT or built in type
					if (tmp != null) {
						bounds[0] = tmp.getLowerBound();
						bounds[1] = tmp.getUpperBound();
					} else {
						supportFunctions.displayMessageDialog(null,"getTypeBounds1: Error " + tokens[0] + " is not a valid type identifier");
						bounds[0] = 0;
						bounds[1] = 0;
					}
				}
				
				return bounds;
			}
			
			// check for a unconstrained array
			p.regExpMatch(typeDef,"(?i)\\((\\w+)\\srange\\s<>\\)");
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
					bounds[0] = -unconstrainedArrayValue;
					bounds[1] = unconstrainedArrayValue;
				
				return bounds;
			}
			
			// this is the last check in the chain, so if it fails just return 0 for the lower and upper bound values
			p.regExpMatch(typeDef,"(\\w+)"); // check just for a type identifier
			tokens = p.getFoundGroupsArray();
			if(tokens.length > 0) {
				compilerSymbol tmp = isType(entity,tokens[0]); // assuming a symType.TYPE or symType.CONSTANT or built in type
				if (tmp == null) {
					bounds[0] = 0;
					bounds[1] = 0;
				} else {
					bounds[0] = tmp.getLowerBound();
					bounds[1] = tmp.getUpperBound();
				}
				return bounds;
			}

			return bounds;
		}
		String processTypeUse(String entity,String typeName,String typeDef,String complierToken,String subscope,symType symtype) {
			compilerSymbol sigvarconSym = null;
			if (typeName.length() != 0) { // for signal, variable and constant keywords
				// see last part of this function for where we update this symbol table entry
				sigvarconSym = symbolTable.findSymbol(entity,currentScope(),typeName,symtype);
				if (sigvarconSym == null) {
					supportFunctions.displayMessageDialog(null,"ProcessTypeUse: Error " + typeName + " not found in symbol table");
					return ""; // error condition?
				}
			}
			
			boolean bArray = false;
			if (typeDef.indexOf("array") != -1) {bArray = true;} else {bArray = false;}
			boolean bDynamic = false;
			if (typeDef.indexOf("access") != -1) {bDynamic = true;} else {bDynamic = false;}
			typeDef = typeDef.replaceAll("access","");
			
			boolean bEnum = false;
			String retstring = "";
			int[] range = new int[2];
			int[] bounds = new int[2];
			String type = getTypeType(entity,typeName,typeDef);
			if (type.startsWith("enum")) {
				bEnum = true;
				type = type.substring(4);
			}

			range = getTypeRange(entity,typeDef);
			bounds = getTypeBounds(entity,typeDef);
			retstring = type + "," + String.valueOf(bounds[0]) + "," + String.valueOf(bounds[1]) + "," + String.valueOf(range[0]) + "," + String.valueOf(range[1]) + "," + subscope + "," + getLowerStringRange() + "," + getUpperStringRange() + "," + supportFunctions.valueOf(bEnum) + "," + supportFunctions.valueOf(bArray) + "," + supportFunctions.valueOf(bDynamic);
			
			// update symbol table entry for 'signal', 'variable' or 'constant' keywords
			if (sigvarconSym != null) {
				sigvarconSym.setRanges(range[0],range[1]);
				sigvarconSym.setBounds(bounds[0],bounds[1]);
				sigvarconSym.setSymbolValue(type);
				sigvarconSym.setSubScope(subscope);
				sigvarconSym.setEnum(bEnum);
				sigvarconSym.setArray(bArray);
				sigvarconSym.setDynamic(bDynamic);
				sigvarconSym.setStringLowerRange(getLowerStringRange());
				sigvarconSym.setStringUpperRange(getUpperStringRange());
			}
			
			return retstring;
		}
		String processTypeDef(String entity,String typeName,String typeDef,String subscope) {
			//supportFunctions.displayMessageDialog(null,"processTypeDef:"+typeName+":"+typeDef);
			compilerSymbol sym = symbolTable.findSymbol(entity,currentScope(),typeName,symType.TYPE);
			if (sym != null) {
				
				if (typeDef.indexOf("access") != -1) {sym.setDynamic(true);} else {sym.setDynamic(false);}
				typeDef = typeDef.replaceAll("access","");
				
				int[] range = getTypeRange(entity,typeDef);
				int[] bounds = getTypeBounds(entity,typeDef);
				sym.setRanges(range[0],range[1]);
				sym.setBounds(bounds[0],bounds[1]);
				sym.setEnum(false);
				String tmp = getTypeType(entity,typeName,typeDef);
				if (tmp.startsWith("enum")) {
					tmp = tmp.substring(4);
					sym.setEnum(true);
				}
				if (typeDef.indexOf("array") != -1) {
					sym.setArray(true);
					if(arrayNamesProcessed.length() != 0) {arrayNamesProcessed = arrayNamesProcessed  + ",";}
					arrayNamesProcessed = arrayNamesProcessed  + entity + "," + tmp;
				} else {sym.setArray(false);}
				sym.setSymbolValue(tmp);
				sym.setSubScope(subscope);
				sym.setStringLowerRange(getLowerStringRange());
				sym.setStringUpperRange(getUpperStringRange());
			}
			return typeDef;
		}
		public String getKeywordFromLine(Vector tokens) {
			if (tokens.size() == 0) {return "";}
			//TRACE("getKeywordFromLine:",tokens,4);
			// if line does a signal or variable assignment return the "airtheng" keyword
			if (tokens.contains("<=") || tokens.contains(":=")) {
				if (tokens.contains("signal") || tokens.contains("variable")) {
				} else {
					return "airtheng";
				}
			}
			// special case for keywords - processs, portmap and useentity
			// no space between for example, portmap, because called after preProcessLine(..)
			if (tokens.contains("process")) {return "process";}
			if (tokens.contains("portmap")) {return "portmap";}
			if (tokens.contains("useentity")) {return "useentity";}
			
			// default case first token on line unless first token is a line label
			String tmp = (String)tokens.elementAt(0);
			
			if (tokens.size() > 2) {
				String tmp1 = (String)tokens.elementAt(1);
				if (tmp1.equals(":") && !tokens.contains("process") && !tokens.contains("end") && !tokens.contains("port")) {
					TRACE("getKeywordFromLine:Got line label:"+tmp,4);
					tokens.setElementAt((String)tokens.elementAt(0)+":",0);
					tokens.setElementAt("",1);
					if (tokens.size() > 2) {
						tmp =  (String)tokens.elementAt(2);
					} else {
						return "linelabel";
					}
				}
			}
			
			// check to see if we have a valid keyword (check reserved words)
			Vector v = supportFunctions.splitIntoTokens(VHDLReservedWords);
			if (v.contains(tmp)) {
				if (tmp.equals("for")) {
					if (!tokens.contains("to") && !tokens.contains("downto")) {tmp = "forenum";}
				}
				return tmp;
			}
				
			return "justfunctions";
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
			if (tokens.size() == 0) {TRACE("syntaxCheckInternal:Empty Line",4);return true;}
		
			TRACE("VHDL Syntax Check Internal Line : " + line,4);
			
			// delete the NYS keywords
			boolean bFound = false;
			for (int i=0;i<VHDLNYSWords.length;i++) {
				tokens.removeElement(VHDLNYSWords[i]);
				bFound = true;
			}
			if (bFound) {line = supportFunctions.reasembleTokens(tokens," ");}
			
			// get the VHDL keyword from the line
			String keyword = getKeywordFromLine(tokens);
			
			// for FILE keyword swap tokens 5 and 6 (zero based)(in##out and filename)
			if (keyword.equals("file")) {
				String tmp = (String)tokens.elementAt(5);
				//supportFunctions.displayMessageDialog(null,"FILE (t5): " + tmp); // debug line
				//supportFunctions.displayMessageDialog(null,"FILE (t6): " + (String)tokens.elementAt(6)); // debug line
				tokens.setElementAt((String)tokens.elementAt(6),5);
				tokens.setElementAt(tmp,6);
				line = supportFunctions.reasembleTokens(tokens," ");
				//supportFunctions.displayMessageDialog(null,"FILE: " + line); // debug line
			}

			if (line.indexOf("wait") != -1) {
				if (getPassNumber() == 2) {processWaits(line,tokens);}
				return true;
			}
		
			// process the line using the translation table entry defined by the above keyword
			int status = processLine(line,keyword,tokens);
			if (status != -1) {
				setErrorString("Line:" + String.valueOf(getLineNumber()+1)+" " + line,(String)tokens.elementAt(status));
				return false;
			}
			return true;
		}
		public void writeWaitBlock(String pname,String sname,String tiggertime,boolean bDeleteSignal) {
			TRACE("writeWaitBlock:pname:"+pname+":sname:"+sname,4);
			int startLine = getCompilerFile().getCurrentLineNum() + 1; 
			int endProcessLine = getCompilerFile().find("end process",startLine);
			int waitLine = getCompilerFile().find("wait",startLine);
			TRACE("writeWaitBlock:startLine="+String.valueOf(startLine)+",endProcessLine="+String.valueOf(endProcessLine)+",waitLine="+String.valueOf(waitLine),4);
			
			
			Vector signals = supportFunctions.splitIntoTokens(sname,",");
			for (int i=0;i<signals.size();i++) {
				writeDirectToOutput("\ncreateSPMap('"+entityName+"','"+pname+"','"+(String)signals.elementAt(i)+"');");
				if (tiggertime.length() != 0) {writeDirectToOutput("\nprocessWaitForSignal('"+tiggertime+"');");}
			}
			addExtraLine("end process " + currentProcessName + ";");
			addExtraLine(pname + ": process("+sname+")");
			addExtraLine("begin");
			for (int i=0;i<signals.size();i++) {
				addExtraLine("&\ndeleteSPMap('"+entityName+"','"+pname+"','"+(String)signals.elementAt(i)+"');"); // note & char for write direct
				if (bDeleteSignal) {addExtraLine("&\ndeleteSignal('"+entityName+"','"+(String)signals.elementAt(i)+"');");}
			}
			
			TRACE("writeWaitBlock:Set file line:" + String.valueOf(endProcessLine) + ":end process " + pname + ";",4);
			getCompilerFile().setFileLine(endProcessLine,"end process " + pname + ";");

			if (waitStartChain.length() == 0) { // start of wait chain
				TRACE("writeWaitBlock:start of wait chain",4);
				waitStartChain = currentProcessName; // GDB 01/03/2015 used to be pname
			}
			if (waitLine == -1 || endProcessLine < waitLine) { // end of wait chain
				TRACE("writeWaitBlock:end of wait chain",4);
				addExtraLine("&\nrerunwaitchain('" + waitStartChain + "');");
				waitStartChain = "";
			}
		}
		public void processWaits(String line,Vector tokens) {
			TRACE("processWaits:Enterted",4);
			
			String sname = "";
			String tiggertime = "";
			String pname = "process" + String.valueOf(noNameProcessesID++);
			
			if (line.indexOf(" on ") != -1) {
				TRACE("processWaits:on",4);
				int index = tokens.indexOf("on");
				sname = (String)tokens.elementAt(index + 1);
				writeWaitBlock(pname,sname,tiggertime,false);
				return;
			}
			if (line.indexOf(" until ") != -1) {
				TRACE("processWaits:until",4);
				int index = tokens.indexOf("until");
				sname = "intsignal" + String.valueOf(noNameProcessesID++);
				int index1 = line.indexOf("until");
				index1 = index1 + 6; // get to rest of line after until keyword
				String waitCondition = line.substring(index1,line.length()-1); // also removes trailing ; character
				waitCondition = processFuncArgs(waitCondition);
				writeDirectToOutput("\ncreateSignal('"+entityName+"','"+entityName+"','"+sname+"','out','bit,0,0,0,0,"+entityName+",,,0,0',0,'0');");
				symbolTable.addSymbol(entityName,currentScope(),sname,String.valueOf(0),symType.SIGNAL,symClass.NONE);
				writeDirectToOutput("\nprocessWaitUntilSignal('"+entityName+"','"+sname+"','"+waitCondition+"');");
				writeWaitBlock(pname,sname,tiggertime,true);
				return;
			}
			if (line.indexOf(" for ") != -1) {
				TRACE("processWaits:for",4);
				int index = tokens.indexOf("for");
				sname = "intsignal" + String.valueOf(noNameProcessesID++);
				String timespec = (String)tokens.elementAt(index + 1);
				tiggertime = entityName +":" + sname + ":" + supportFunctions.numberPart(timespec) + ":" + supportFunctions.stringPart(timespec); // module:signalname:time value:time unit
				writeDirectToOutput("\ncreateSignal('"+entityName+"','"+entityName+"','"+sname+"','out','bit,0,0,0,0,"+entityName+",,,0,0',0,'0');");
				symbolTable.addSymbol(entityName,currentScope(),sname,String.valueOf(0),symType.SIGNAL,symClass.NONE);
				writeWaitBlock(pname,sname,tiggertime,true);
				return;
			}
			// get here and its wait; - default case (ignore?)
			TRACE("processWaits:wait;",4);
			waitStartChain = ""; // cancel any wait chain
		}
		public String getName() {return "VHDL";}
		public String getFileExt() {return "vhdl";}
		public void writeDirectToOutput(String line) {compilerPHPFile.appendFile(line);}
		public boolean preCompile(String filename,boolean bHeaders) {
			indentionCount = 0;
			archName = "";
			entityName = "";
			componentPorts.clear();
			transFunctionPrefix = "";
			waitStartChain = "";
			arrayNamesProcessed = "";
			
			// clear compile place holder list
			placeHolders.remove("*e");
			placeHolders.remove("*f");
			placeHolders.remove("*g");
			placeHolders.remove("*h");
			
			// start with an empty symbol table
			symbolTable.removeAllSymbols();
			
			TRACE("PC1: PreCompile",4); // debug line
			
			if (bHeaders) {
				portDefLines.removeAllElements();
				portmapDefLines.removeAllElements();
				signalFuncLines.removeAllElements();
				signalFuncLines.addElement("function initSignals() {");
				signalFuncLines.addElement("}");
				forcesFuncLines.removeAllElements();
				forcesFuncLines.addElement("function storeProcessNames() {");
				forcesFuncLines.addElement("}");
			
				extraCompileFiles.removeAllElements();
				transTable = new translationTable();
				transTable.loadTranslationTable(transTableName);
//				compilerPHPFile = new basicFile("../hdlworkbench/output",supportFunctions.getFilenameNoExt(filename) + ".php");
				compilerPHPFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + ".php");
				TRACE("PC2: PreCompile",4); // debug line
				compilerPHPFile.saveFile("<html>\n<head>"); // saveFile used to remove any existing file
				if(systemUserReg.getAppRemotedHosted()) {
					compilerPHPFile.appendFile("\n<script src=\""+appBaseURL+"/website/scripts/common.js\" type=\"text/javascript\"></script>");
					compilerPHPFile.appendFile("\n<script src=\""+appBaseURL+"/website/scripts/jquery-1.9.0.js\" type=\"text/javascript\"></script>");
					compilerPHPFile.appendFile("\n<link rel=\"stylesheet\" href=\""+appBaseURL+"/website/css/ehs_default.css\" type=\"text/css\" media=\"screen\">");
				} else {
					compilerPHPFile.appendFile("\n<script src=\"../scripts/common.js\" type=\"text/javascript\"></script>");
					compilerPHPFile.appendFile("\n<script src=\"../scripts/jquery-1.9.0.js\" type=\"text/javascript\"></script>");
				}
				compilerPHPFile.appendFile("\n</head>\n<body>");
				compilerPHPFile.appendFile("\n<?");
				compilerPHPFile.appendFile("\n\n// Generated by: " + systemUserReg.getSingleLineInfoText());
				compilerPHPFile.appendFile("\n// Generation date: " + supportFunctions.currentShortDate() + " " + supportFunctions.currentShortTime());
				compilerPHPFile.appendFile("\n");
				if(!systemUserReg.getAppRemotedHosted()) {
					compilerPHPFile.appendFile("\ninclude(\"../website/scripts/common.php\");");
				} else {
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/website/scripts/common.php\");");
				}
				// has to be before the line that includes simlib.php as this files will set it to a default of "" if not already set
				compilerPHPFile.appendFile("\n$langkeywords = \""+VHDLReservedWords+"\";\n");
				if(!systemUserReg.getAppRemotedHosted()) {
					compilerPHPFile.appendFile("\ninclude(\"../support/simlib.php\");");
					compilerPHPFile.appendFile("\ninclude(\"../support/vhdlstandardfuncs.php\");");
					compilerPHPFile.appendFile("\ninclude(\"../support/textio.php\");");
					compilerPHPFile.appendFile("\ninclude(\"../support/std_logic_1164.php\");");
					compilerPHPFile.appendFile("\ninclude(\"../support/numeric_std.php\");");
					compilerPHPFile.appendFile("\ninclude(\"../support/standard.php\");");
				} else {
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/simlib.php\");");
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/vhdlstandardfuncs.php\");");
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/textio.php\");");
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/std_logic_1164.php\");");
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/numeric_std.php\");");
					compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/applications/hdlworkbench/support/standard.php\");");
				}
			
				compilerPHPFile.appendFile("\n$link = mysqlConnect();");
				compilerPHPFile.appendFile("\nclearDatabase($_GET[\"entity\"]);");
			}
			
			TRACE("PC3: PreCompile",4); // debug line
			return true;
		}
		public boolean postCompile(String filename,boolean bHeaders) {
				TRACE("PC1: PostCompile",4); // debug line
				waitStartChain = "";
				
				if (bHeaders) {	
				TRACE("postCompile:filename:"+filename,4);
				String entitiesProcessed = supportFunctions.getFilenameNoExt(filename);
				String dirsProcessed = supportFunctions.getPath(filename);
				getAllSymbolTable().mergeSymbolTable(symbolTable);

				for (int i=0;i<extraCompileFiles.size();i++) {
						//supportFunctions.displayMessageDialog(null,"Compile Extra File " + (String)extraCompileFiles.elementAt(i));
						TRACE("PC2: PostCompile ExtraFile:" + (String)extraCompileFiles.elementAt(i),4); // debug line
						if (!compile((String)extraCompileFiles.elementAt(i),false)) {
						return false;
					}
					entitiesProcessed = entitiesProcessed + ",";
					entitiesProcessed = entitiesProcessed + supportFunctions.getFilenameNoExt((String)extraCompileFiles.elementAt(i));
					dirsProcessed = dirsProcessed + ",";
					dirsProcessed = dirsProcessed + supportFunctions.getPath((String)extraCompileFiles.elementAt(i));
					getAllSymbolTable().mergeSymbolTable(symbolTable);
				}

				compilerPHPFile.appendFile("\nfunction getEntities() {\n\treturn \"");
				compilerPHPFile.appendFile(entitiesProcessed);
				compilerPHPFile.appendFile("\";\n}\n");

				compilerPHPFile.appendFile("\nfunction getTypes() {\n\treturn \"");
				compilerPHPFile.appendFile(tokenizer.getStringOfTypes());
				compilerPHPFile.appendFile("\";\n}\n");

				compilerPHPFile.appendFile("\nfunction getNoNameProcesses() {\n\treturn \"");
				compilerPHPFile.appendFile(tokenizer.noNameProcesses);
				compilerPHPFile.appendFile("\";\n}\n");

				compilerPHPFile.appendFile("\nfunction getArrayNames() {\n\treturn \"");
				compilerPHPFile.appendFile(arrayNamesProcessed);
				compilerPHPFile.appendFile("\";\n}\n");

				for (int j=0;j<signalFuncLines.size();j++) {
					compilerPHPFile.appendFile("\n" + (String)signalFuncLines.elementAt(j));
				}
				for (int j=0;j<forcesFuncLines.size();j++) {
					compilerPHPFile.appendFile("\n" + (String)forcesFuncLines.elementAt(j));
				}
			
				compilerPHPFile.appendFile("\nsimulate($_GET[\"entity\"]);\nmysql_close($link);\n?>");
				compilerPHPFile.appendFile("\n</body>\n</html>\n");
				compilerPHPFile.flush();
				
				processHDLDrawingItems(filename,dirsProcessed,entitiesProcessed);				
				if (ehsConstants.bRunAppWithGUI) {getAllSymbolTable().createSymbolDialog("Data Dictionary","");}
			}
			return true;
		}
		public void processHDLDrawingItems(String filename,String dirsProcessed,String entitiesProcessed) {
			basicFile f = new basicFile(filename);
			pseduoFile HDLDrawingItemsFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + "_hdlitems.xml");
			HDLDrawingItemsFile.saveFile("<?xml version=\"1.0\"?>"); // saveFile used to remove any existing file
			HDLDrawingItemsFile.appendFile("\n");
			HDLDrawingItemsFile.appendFile("<hdldrawingitems>");
			HDLDrawingItemsFile.appendFile("\n");
			
			String basePath = ""+dataRelativePath+"/"+appDirectory;
			HDLDrawingItemsFile.appendFile("\t<config date=\"" + supportFunctions.currentShortDate() + "\" time =\"" + supportFunctions.currentShortTime() + "\" basepath =\"" + basePath + "\" />\n");

			TRACE("processHDLDrawingItems:entitiesProcessed:"+entitiesProcessed,4);
			TRACE("processHDLDrawingItems:dirsProcessed:"+dirsProcessed,4);
			Vector v = supportFunctions.splitIntoTokens(entitiesProcessed,",");
			Vector v4 = supportFunctions.splitIntoTokens(dirsProcessed,",");
			for (int i=0;i<v.size();i++) {
				String entity = (String)v.elementAt(i);
				String dir = (String)v4.elementAt(i);
				if (dir.startsWith(basePath)) {dir = dir.substring(basePath.length() + 1);}
				HDLDrawingItemsFile.appendFile("\t<entity name=\"" + entity + "\" path =\"" + dir + "\">\n");
				String allPorts = "";
				for (int ii=0;ii<portDefLines.size();ii++) {
					String tmp = (String)portDefLines.elementAt(ii);
					Vector v1 = supportFunctions.splitIntoTokens(tmp,",");
					if (entity.equals((String)v1.elementAt(0))) {
						HDLDrawingItemsFile.appendFile("\t\t<port name=\"" + (String)v1.elementAt(2) + "\" mode=\"" + (String)v1.elementAt(3) + "\" type=\"" + (String)v1.elementAt(4) + "\" />\n");
						if (allPorts.length() != 0) {allPorts = allPorts + ";";}
						allPorts = allPorts + (String)v1.elementAt(2) + "," + (String)v1.elementAt(3) + "," + (String)v1.elementAt(4);
					}
				}
				HDLDrawingItemsFile.appendFile("\t\t<allports name=\"" + allPorts + "\" />\n");
				HDLDrawingItemsFile.appendFile("\t</entity>\n");
			}
			TRACE("processHDLDrawingItems:PC1",4);
			int connID = 0;
			Vector keys = new Vector();
			Vector values = new Vector();
			for (int i=0;i<portmapDefLines.size();i++) {
				String tmp = (String)portmapDefLines.elementAt(i);
				Vector v1 = supportFunctions.splitIntoTokens(tmp,",");
				Vector v2 = supportFunctions.splitIntoTokens((String)v1.elementAt(1),";");
				int startIndex = 3;
				for (int ii=0;ii<v2.size();ii++) {
					Vector v3 = supportFunctions.splitIntoTokens((String)v2.elementAt(ii),":");
					HDLDrawingItemsFile.appendFile("\t<connection name=\"connection" + String.valueOf(connID++) + "\" fromentity=\"" + (String)v1.elementAt(0) + "\" toentity=\"" + (String)v1.elementAt(2) + "\" fromport=\"" + (String)v3.elementAt(0) + "\" toport=\"" + (String)v1.elementAt(startIndex++) + "\" />\n");
					String key = (String)v1.elementAt(0) + ":" + (String)v3.elementAt(0);
					// for the other end of the connection
					String otherEndKey = "";
					String otherEndData = key;
					
					int index = keys.indexOf(key);
					if (index == -1) {
						keys.addElement(key);
						values.addElement((String)v1.elementAt(2) + ":" + (String)v1.elementAt(startIndex-1));
						otherEndKey = (String)v1.elementAt(2) + ":" + (String)v1.elementAt(startIndex-1);
					} else {
						String tmp1 = (String)values.elementAt(index);
						tmp1 = tmp1 + "," + (String)v1.elementAt(2) + ":" + (String)v1.elementAt(startIndex-1);
						values.setElementAt(tmp1,index);
						otherEndKey = (String)v1.elementAt(2) + ":" + (String)v1.elementAt(startIndex-1);
					}
					
					TRACE("processHDLDrawingItems:otherendkey:"+otherEndKey+":otherenddata:,"+otherEndData,4);
					index = keys.indexOf(otherEndKey);
					if (index == -1) {
						keys.addElement(otherEndKey);
						values.addElement(otherEndData);
					} else {
						String tmp1 = (String)values.elementAt(index);
						tmp1 = tmp1 + "," + otherEndData;					
						values.setElementAt(tmp1,index);
					}
				}
			}
			TRACE("processHDLDrawingItems:PC2",4);
			connID = 0;
			for (int i=0;i<keys.size();i++) {
				String tmp = (String)keys.elementAt(i);
				Vector v5 = supportFunctions.splitIntoTokens(tmp,":");
				String entity = (String)v5.elementAt(0);
				String port = (String)v5.elementAt(1);
				String connlist = (String)values.elementAt(i);
				HDLDrawingItemsFile.appendFile("\t<connlist name=\"connlist" + String.valueOf(connID++) + "\" entity=\"" + entity + "\" port=\"" + port + "\" clist=\"" + connlist + "\" />\n");			
			}
			
			HDLDrawingItemsFile.appendFile("</hdldrawingitems>\n");
			HDLDrawingItemsFile.flush();

			TRACE("processHDLDrawingItems:PC3",4);
		}
		public boolean betweenPassProcesses() {
			if (transFunctionPrefix.length() == 0) {
				setExErrorMsg("Not an Entity or a Package");
				return false;
			} else {
				if (bProcessHeaders) {
				}
			}
			return true;
		}
		public boolean completeLine(String line) {
			int bracketMatchCount = supportFunctions.strCount(line,'(') - supportFunctions.strCount(line,')');
		
			// check for complete lines that do not end in a ';' character
			if (line.contains("begin")) {return true;}
			if (line.contains("process") && supportFunctions.strCount(line,'(') == 1 && supportFunctions.strCount(line,')') == 1) {return true;}
			
			// the next four lines must be tested in this order and before the test for line ending in 'is'
			if (line.contains("type") && line.contains("record") && line.contains("end record")) {return true;}
			if (line.contains("type") && line.contains("record")) {return false;}
			if (line.contains("type") && line.endsWith(";")) {return true;}
			if (line.contains("type")) {return  false;}
			
			if (line.startsWith("component")) {return true;}
			if (line.endsWith("is")) {return true;}
			if (line.endsWith("then")) {return true;}
			if (line.endsWith("else")) {return true;}
			if (line.endsWith("process")) {return true;}

			// check for complete lines that do end in a ';' character
			if (line.contains("port") && bracketMatchCount == 0) {return true;}
			if (line.endsWith(";") && !line.contains("port")) {return true;}
			
			return false;
		}
		String processFuncArgs(String line) {
			// This fuction look for signals, variables or constants in the input line and replaces
			// with getSignal ( entityName , signalName [,arrayIndex] ) for example - note the spaces!
			// Called for 'justfunctions', 'airtheng', 'pfstring' and the wait keyword processing
			// For all other keywords the 'getSignal', etc. will occur in the translation table entry.
			
			TRACE("processFuncArgs:Enter:"+line,4);
			
			line = line.replaceAll("else unaffected","");
			String[] tokens = supportFunctions.regMatchGroups(line,"((\\w+)\\.(\\w+))");
			if (tokens.length > 0) {
				compilerSymbol tmp = symbolTable.findSymbol(entityName,currentScope(),tokens[1],symType.NEWDATATYPE);
				if (tmp != null) {
					line = line.replaceAll(tokens[0],"$" + tokens[1] + "->$" + tokens[2]);
				}
			}
			compilerSymbol tmp1 = symbolTable.findSymbol(entityName,currentScope(),line,symType.VARIABLE); // every NEWDATATYPE will have a matching VARIABLE
			if (tmp1 != null) {
				if (tmp1.isDynamic()) {
					line = "$" + line;
				}
			}
		
			String line1 = "";
			String unquotedLine = clearQuotedStrings(line);
			unquotedLine = unquotedLine.replaceAll("\\("," \\( ");
			unquotedLine = unquotedLine.replaceAll("\\)"," \\) ");
			unquotedLine = unquotedLine.replaceAll(";"," ; ");
			unquotedLine = unquotedLine.replaceAll("\\s{2,}"," ");
			unquotedLine = unquotedLine.trim();
			//supportFunctions.displayMessageDialog(null,"PFA:"+unquotedLine);

			Vector v = supportFunctions.splitIntoTokens(unquotedLine," ");
			//supportFunctions.displayMessageDialog(null,"PFA:Num:"+String.valueOf(v.size()));
			
			for (int i=0;i<v.size();i++) {
				String token = (String)v.elementAt(i);	
				token = token.trim();
				String indexString = "-1";
				compilerSymbol tmp = symbolTable.findSymbol(entityName,currentScope(),token); // used to be findsymbol2(...)
				if (tmp != null) {
					int bracketCount = 1; // 1 to count initial opening ( character
					indexString = "";
					String nextToken = (String)v.elementAt(i+1); // just peek at next token
					nextToken = nextToken.trim();
					if (nextToken.equals("(")) { // eat up all index string
						i = i + 2; // skip over opening ( token and point to next token
						do {
							if (i >= v.size()) {supportFunctions.displayMessageDialog(null,"Index String Overflow");break;}
							nextToken = (String)v.elementAt(i++);
							if (nextToken.equals("(")) {bracketCount++;}
							if (nextToken.equals(")")) {bracketCount--;} 
							if (bracketCount == 0) {nextToken = "";} // clear nextToken so we don't add closing ) token
							indexString = indexString + nextToken + " ";
						} while (bracketCount != 0);
						indexString = processFuncArgs(indexString);
						// GDB 13/09/15 indexString = "eval(" + indexString + ")";
					}
					
					// some functions take the signal name, etc. as the parameter instead of its value
					// check for these cases ... note: some attributes work this way too ...
					
					switch (tmp.getSymbolType()) {
						case SIGNAL:
							token = "getSignal ( '" + entityName + "' , '" + token + "' , '" + indexString + "' )";
							break;
						case VARIABLE:
							token = "getVariable ( '" + entityName + "' , '" + token + "' , '" + indexString + "' )";
							break;
						case CONSTANT:
							token = "getConstant ( '" + entityName + "' , '" + token + "' , '" + indexString + "' )";
							break;
					}
				}
				line1 = line1 + token + " ";
			}
			line1 = line1.trim(); 
			TRACE("processFuncArgs:Exit:"+line1,4);
			return line1;
		}
		public int processTokens(String line,String keyword,Vector lineTokens,translationTableEntry entry) {
			Vector compilerTokens = entry.getTokens();
			Vector expComplierTokens = transTable.expandTokens(compilerTokens);
			//placeHolders = new Properties();
			// clear line only place holder list
			placeHolders.remove("*1");
			placeHolders.remove("*2");
			placeHolders.remove("*3");
			placeHolders.remove("*4");
			String debugLine = "";
			currentParameterName = "";
			currentParameterModifier = "";
			currentParameterType = "";
			currentParameterMode = "in";
			currentParameterDefault = "";
			currentParameterRet = 0;
			currentParameterPos = 1;
			int compilerTokenIndex,lineTokenIndex;
			boolean bNoTransText = false;
			loopLabels.clear();
			loopVariables.clear();
			
			TRACE("PT: line:"+line+" Keyword:"+keyword,4);
			
			// we do not want to include bracket characters inside strings!
			//if (supportFunctions.strCount(line,'(') != supportFunctions.strCount(line,')')) {
			//	setExErrorMsg("Missing ( or ) characters");
			//	return 0;
			//}
			
			if (getPassNumber() == 1 && !getCompileLine()) {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;

				placeHolders.setProperty("*0","");
				placeHolders.setProperty("*8"," ");
				placeHolders.setProperty("*5","lineex");
				placeHolders.setProperty("*6",",");
				placeHolders.setProperty("*9",moduleName);
				
				Pattern p = Pattern.compile("(?i)entity (.*?) is");
				Matcher m = p.matcher(line);
				if (m.find()) {
					//moduleName = m.group(1);
					transFunctionPrefix = "entity";
				}
				p = Pattern.compile("(?i)package (.*?) is");
				m = p.matcher(line);
				if (m.find()) {
					//moduleName = m.group(1);
					transFunctionPrefix = "package";
				}
			} else {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;

				placeHolders.setProperty("*0","");
				placeHolders.setProperty("*8"," ");
				placeHolders.setProperty("*5","lineex");
				placeHolders.setProperty("*6",",");
				placeHolders.setProperty("*9",moduleName);

				setExErrorMsg("");
				String lineEX = "";
				String lineEX1 = "";
				debugLine = "";
				String lineToken = "";
				String prevLineToken = "";
				String nextLineToken = "";
				String complierToken = "";
				if (!getCompileLine()) {
					compilerPHPFile.appendFile("\n");
					for (int i=0;i<indentionCount;i++) {
						compilerPHPFile.appendFile("\t");
					}
				}
				do {
					setExErrorMsg(""); // reset the extended error message
					lineToken = (String)lineTokens.elementAt(lineTokenIndex);
					TRACE("PT:Line Token:"+lineToken,4);
					if (lineTokenIndex>0) {prevLineToken = (String)lineTokens.elementAt(lineTokenIndex-1);} else {prevLineToken = lineToken;}
					if (lineTokenIndex<lineTokens.size()-1) {nextLineToken = (String)lineTokens.elementAt(lineTokenIndex+1);} else {nextLineToken = lineToken;}
//xxx					placeHolders.setProperty("*7",lineToken);
					//supportFunctions.displayMessageDialog(null,"Line Token1:" + lineToken);
					if (lineToken.length() == 0) {lineTokenIndex++;continue;}
					// check for line labels
					if (lineTokenIndex == 0 && lineToken.endsWith(":")) {
						symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(getLineNumber()),symType.LABEL,symClass.NONE);
						if (!getCompileLine()) {compilerPHPFile.appendFile("/n" + lineToken + "/n");}
						lineTokenIndex++;
						if (lineTokenIndex < lineTokens.size()) {break;} else {continue;}
					}
					boolean bTokensMatch = false;
					int multiTransTextIndex = 0;
					
					//if (line.contains("if ")) {
					//	supportFunctions.displayMessageDialog(null,"line:"+line);
					//	supportFunctions.displayMessageDialog(null,"Previous file line: "+debugLine);
					//	supportFunctions.displayMessageDialog(null,"Line token: "+lineToken);
					//	translationTableToken t = (translationTableToken)compilerTokens.elementAt(compilerTokenIndex);
					//	supportFunctions.displayMessageDialog(null,"compiler token: "+t.getText());
					//}
					
					if (expComplierTokens.size() < compilerTokenIndex) {
						setExErrorMsg("Incomplete translation table entry");
						return lineTokenIndex;
					}
					translationTableToken token = null;
					try {
						token = (translationTableToken)expComplierTokens.elementAt(compilerTokenIndex);
					} catch (Exception e) {supportFunctions.displayMessageDialog(null,"Caught A");}
					TRACE("PT:Compiler Token:"+token.getText(),4);
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
							
							if (complierToken.equals("*6")) {
								if (lineToken.equals(placeHolders.getProperty("*6"))) {
									bTokensMatch = true;
									break;
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
							if (complierToken.equals("airtheng")) {
								String firstPart = "";
								String secondPart = "";
								String PHPCode = "";
								
								String newLine = "";
								for (int i=0;i<lineTokens.size();i++) {newLine = newLine + (String)lineTokens.elementAt(i) + " ";}
								String trimLine = newLine.trim();
								if (trimLine.contains(":=")) {
									// variable assignment
									
									String[] tokens = supportFunctions.regMatchGroups(line,"((\\w+)\\s*:=\\s*new\\s*(.*));");
									if (tokens.length > 0) {
										symbolTable.addSymbol(entityName,currentScope(),tokens[1],tokens[2],symType.NEWDATATYPE,symClass.NONE);
										compilerSymbol tmp = symbolTable.findSymbol(entityName,currentScope(),tokens[2],symType.TYPE);
										if (tmp == null) { // built in type
											tokens[2] = "builtInType";
										}
										trimLine = trimLine.replaceAll(tokens[0],"$"+tokens[1]+":=new "+tokens[2]+"()");
									}
									
									int index = trimLine.indexOf(":=");
									firstPart = trimLine.substring(0,index-1);
									secondPart = trimLine.substring(index+2);
									if (symbolTable.findSymbol(entityName,currentScope(),firstPart,symType.VARIABLE) == null) {
										setExErrorMsg("Unrecognized variable (" + firstPart + ")");
										break;
									}
									
									secondPart = secondPart.replace("\"","'");
									
									ehsRegExp pp = new ehsRegExp();
									pp.regExpMatch(firstPart,"\\(.*\\)");
									String[] tokens1 = pp.getFoundStringsArray();
									for (int ii=0;ii<tokens1.length;ii++) {
										firstPart = firstPart.replace(tokens1[ii],"("+processFuncArgs(tokens1[ii].substring(1,tokens1[ii].length()-1))+")");
									}
									
									PHPCode = "processVariable(\"" + entityName + "\",\"" + firstPart + "\",\"" + processFuncArgs(secondPart) + "\");\n";
									if (!getCompileLine()) {compilerPHPFile.appendFile(PHPCode);} else {compiledLine=PHPCode;}								
								} else {
									// signal assignment
									int index = trimLine.indexOf("<=");
									firstPart = trimLine.substring(0,index-1);
									secondPart = trimLine.substring(index+2);
									//if (symbolTable.findSymbol(entityName,currentScope(),firstPart,symType.SIGNAL) == null) {
									//	setExErrorMsg("Unrecognized signal (" + firstPart + ")");
									//	break;
									//}
									
									// if line contains AFTER keyword make delayPart="delaysignal:time value:time unit" and remove from line
									String delayPart = "";
									ehsRegExp p = new ehsRegExp();
									p.regExpMatch(secondPart,"(?i)\\safter\\s(\\d+)(\\s*)(\\w+)\\s");
									String[] tokens,tokens1;
									tokens = p.getFoundGroupsArray();
									tokens1 = p.getFoundStringsArray();
									if (tokens.length > 0) {
										delayPart = "delaysignal:"+(String)tokens[0]+":"+(String)tokens[2];
										secondPart = secondPart.replace((String)tokens1[0]," ");
									}
									//if(delayPart.length() !=0) {supportFunctions.displayMessageDialog(null,"Delayed Signal: " + delayPart);}
									
									secondPart = secondPart.replace("\"","'");
									
									ehsRegExp pp = new ehsRegExp();
									pp.regExpMatch(firstPart,"\\(.*\\)");
									tokens1 = pp.getFoundStringsArray();
									for (int ii=0;ii<tokens1.length;ii++) {
										firstPart = firstPart.replace(tokens1[ii],"("+processFuncArgs(tokens1[ii].substring(1,tokens1[ii].length()-1))+")");
									}

									PHPCode = "processSignal(\"" + entityName + "\",\"" + firstPart + "\",\"" + processFuncArgs(secondPart) + "\",\"" + delayPart + "\");\n";
									if (!getCompileLine()) {compilerPHPFile.appendFile(PHPCode);} else {compiledLine=PHPCode;}								
								}
								
								token.setTransText("");
								lineTokenIndex = lineTokens.size(); // because this processes all the line
								bTokensMatch = true;
							}
							if (complierToken.equals("paramspec")) {
								Vector values = supportFunctions.splitIntoTokens("signal,file,variable,constant",",");
								if (values.contains(lineToken)) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Expected signal/file/variable/constant (got " + lineToken + ")");
								}
								break;
							}
							if (complierToken.equals("deallocateptr")) {
								compilerSymbol cs = symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.NEWDATATYPE);
								if (cs != null) {
									symbolTable.deleteSymbol(entityName,currentScope(),lineToken,symType.NEWDATATYPE);
									bTokensMatch = true;
								} else {
									setExErrorMsg("Expected new data pointer (got " + lineToken + ")");
								}
								break;
							}
							if (complierToken.equals("timeunit")) {
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("filename")) {
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("filetype")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.FILE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined file (" + lineToken + ")");								
								}
								break;
							}
							if (complierToken.equals("newfiletype")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid filetype identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.FILE,symClass.NONE) != null) {
									if (currentScope().equals(moduleName)) {
										//supportFunctions.displayMessageDialog(null,"Added fclose A");
										endOfModuleLines.addElement("fclose($" + lineToken + ");");
									} else {
										//supportFunctions.displayMessageDialog(null,"Added fclose b");
										endOfFuncLines.addElement("fclose($" + lineToken + ");");
									}
									bTokensMatch = true;
								} else {
									setExErrorMsg("File (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("newtypename")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid typename identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.TYPE,symClass.NONE) != null) {
									bTokensMatch = true;
									currentType = lineToken;
								} else {
									// will be already defined if an incomplete type
									//setExErrorMsg("Type (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("typename")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.TYPE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined type (" + lineToken + ")");								
								}
								break;
							}
							if (complierToken.equals("funcdef")) {
								// eat tokens until we have the complete function defination
								String funcDef = lineToken;
								int obcount = 0;
								while (lineTokenIndex++ < lineTokens.size()-1) {
									lineToken = (String)lineTokens.elementAt(lineTokenIndex);
									funcDef = " " + funcDef + lineToken;
									//supportFunctions.displayMessageDialog(null,"funcdef:"+funcDef+" ("+lineToken+")");
									if (lineToken.equals("(")) {obcount++;}
									if (lineToken.equals(")")) {
										obcount--;
										if (obcount>0) {continue;}
										// got complete function defination when this token is a ) and obcount=0
										bTokensMatch = true; 
										// need to set linetoken to be complete defination so can be written using *7 in transtext
										lineToken = funcDef;
										break;
									}
								}
								// if we get here we have run out of line tokens - an incomplete function defination
								if (!bTokensMatch) {setExErrorMsg("Incomplete function defination");}
								break;
							}
							if (complierToken.equals("statments")) {
								String stats = lineToken;
								// eat line tokens until we have the complete line
								while (lineTokenIndex++ < lineTokens.size()-1) {
									lineToken = (String)lineTokens.elementAt(lineTokenIndex);
									stats = " " + stats + lineToken;
								}
								//stats = processFuncArgs(stats); // because done in the compileLine(...) below
								supportFunctions.displayMessageDialog(null,"statments in: " + stats); // debug line
								String PHPCode = compileLine(stats);
								supportFunctions.displayMessageDialog(null,"statments out: " + PHPCode); // debug line
								if (PHPCode.length() == 0) {
									setExErrorMsg("Invalid statments - " + stats);
									return 0; // failure
								}
								compilerPHPFile.appendFile(PHPCode + "\n");
								return -1; // because we have finish processing the complete line
							}
							if (complierToken.equals("justfunctions")) {
								line = processFuncArgs(line);
								// replace any " in the line with \"
								line = line.replaceAll("\"","'");
								compilerPHPFile.appendFile("$hdlCode = composePHPCode(\"" + entityName + "\",\"" + line + "\");\neval(\"$hdlCode\");");
								bTokensMatch = true;
								return -1; // because we have finish processing the complete line
							}
							if (complierToken.equals("paramname")) {
								currentParameterName = lineToken;
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("typedef")) {
								String typeDef = lineToken;
								// eat line tokens until we have the complete type or the end of the line
								while (lineTokenIndex++ < lineTokens.size()-1) {
									lineToken = (String)lineTokens.elementAt(lineTokenIndex);
									if (lineToken.equals(";") || lineToken.equals(":=")) { // got complete type definition when next token is ; or :=
										if (typeDef.indexOf("record") != -1) {if (typeDef.indexOf("endrecord") == -1) {typeDef = typeDef + " " + lineToken;continue;}}
										bTokensMatch = true; 
										// need to set linetoken to be complete definition so can be written using *7 in transtext
										lineToken = processTypeDef(entityName,currentType,typeDef,currentScope());
										lineTokenIndex--;
										break;
									}
									typeDef = typeDef + " " + lineToken;
								}
								// if we get here we have run out of line tokens - an incomplete type definition
								if (!bTokensMatch) {setExErrorMsg("Incomplete type definition");}
								break;
							}
							if (complierToken.equals("vartype")) {
								String typeDef = lineToken;
								// eat line tokens until we have the complete type or the end of the line
								while (lineTokenIndex++ < lineTokens.size()-1) {
									lineToken = (String)lineTokens.elementAt(lineTokenIndex);
									if (lineToken.equals(";") || lineToken.equals(":=")) { // got complete type defination when next token is ; or :=
										bTokensMatch = true; 
										// need to set linetoken to be complete defination so can be written using *7 in transtext
										// 'currentSigVarConstantName' is set to the identifier of the signal, variable 
										// or constant we are defining and that identifier has already been added
										// to the symbol table and 'currentSigVarConstantType' has been set to
										// symType.SIGNAL, symType.VARIABLE or symType.CONSTANT. The previous two 
										// variables are set when processing the 'newsignalname', 'newvarname' 
										// or 'newconstname' compiler tokens.
										//supportFunctions.displayMessageDialog(null,"vartype:"+typeDef);
										lineToken = processTypeUse(entityName,currentSigVarConstantName,typeDef,complierToken,currentScope(),currentSigVarConstantType);
										lineTokenIndex--;
										break;
									}
									typeDef = typeDef + " " + lineToken;
								}
								// if we get here we have run out of line tokens - an incomplete type defination
								if (!bTokensMatch) {setExErrorMsg("Incomplete type defination");}
								break;
							}
							if (complierToken.equals("paramtype")) { // need to eat tokens
								String typeDef = lineToken;
								// eat line tokens until we have the complete type or the end of the line
								int obcount = 0;
								while (lineTokenIndex++ < lineTokens.size()-1) {
									lineToken = (String)lineTokens.elementAt(lineTokenIndex);
									if (lineToken.equals("(")) {obcount++;}
									// for a ')' character to be a terminating character it must not be a closing
									// bracket matching any opening bracket in the type defination !!!
									if (lineToken.equals(";") || lineToken.equals(":=") || lineToken.equals(")")) { // got complete type defination when next token is ; or := or )
										if (lineToken.equals(")")) {
											if (obcount != 0) { // closing bracket is part of the type defination
												obcount--; 
												typeDef = typeDef + " " + lineToken;
												continue;
											} 
										}
										// need to set linetoken to be complete defination so can be written using *7 in transtext
										lineToken = processTypeUse(entityName,"",typeDef,complierToken,currentScope(),symType.NONE);
										lineTokenIndex--;
										break;
									}
									typeDef = typeDef + " " + lineToken;
								}
								currentParameterType = lineToken;
								bTokensMatch = true;
								// 
								if (lineToken.equals(":=")) {break;}
								String tmp;
								if (currentProcedureName.length() !=0 ) {tmp=currentProcedureName;} else {tmp=currentFunctionName;}
								signalFuncLines.insertElementAt("	createParameter('"+moduleName+"','"+tmp+"','"+currentParameterName+"','"+currentParameterType+"',"+Integer.toString(currentParameterPos)+",'"+currentParameterMode+"',"+Integer.toString(currentParameterRet)+",0,0,'"+currentParameterDefault+"');",1);
								currentParameterName = "";
								currentParameterType = "";
								currentParameterMode = "in";
								currentParameterDefault = "";
								currentParameterRet = 0;
								currentParameterPos++;
								break;
							}
							if (complierToken.equals("parammode")) {
								if (lineToken.equals("in") || lineToken.equals("out") || lineToken.equals("inout")) {
									currentParameterMode = lineToken;
									bTokensMatch = true;
								} else {
									setExErrorMsg("Invalid parameter mode (" + lineToken + ")");
								}
								break;
							}
							if (complierToken.equals("paramrettype")) {
								currentParameterRet = 1;
								currentParameterName = lineToken; // do not clear parameter data block until you process endfuction							
								signalFuncLines.insertElementAt("	createParameter('"+moduleName+"','"+currentFunctionName+"','"+currentParameterName+"','"+"xxx"+"',"+Integer.toString(0)+",'"+"xxx"+"',"+Integer.toString(currentParameterRet)+",0,0,'"+currentParameterDefault+"');",1);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("paramdefault")) {
								if (currentParameterType.length()==0) {continue;}
								currentParameterDefault = lineToken;							
								String tmp;
								if (currentProcedureName.length() !=0 ) {tmp=currentProcedureName;} else {tmp=currentFunctionName;}
								signalFuncLines.insertElementAt("	createParameter('"+moduleName+"','"+tmp+"','"+currentParameterName+"','"+currentParameterType+"',"+Integer.toString(currentParameterPos)+",'"+currentParameterMode+"',"+Integer.toString(currentParameterRet)+",0,0,'"+currentParameterDefault+"');",1);
								currentParameterName = "";
								currentParameterType = "";
								currentParameterMode = "in";
								currentParameterDefault = "";
								currentParameterRet = 0;
								currentParameterPos++;
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("parammodifier")) {
								currentParameterModifier = lineToken;							
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("pfstring")) {
								lineToken = processFuncArgs(lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("string")) {
								if ((lineToken.charAt(0) == '\"') && lineToken.charAt(lineToken.length()-1) == '\"')  {
									lineToken = lineToken.substring(1,lineToken.length()-1);
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Invalid string (" + lineToken + ")");
								}
							}
							if (complierToken.equals("whilecondstr")) {
								compilerSymbol tmp = symbolTable.findSymbol(entityName,currentScope(),lineToken); // used to be findsymbol2(...)
								if (tmp != null) {
									switch (tmp.getSymbolType()) {
										case SIGNAL:
											lineToken = "getSymbol ( '"+entityName+"' , '"+lineToken+"' )";
										break;
										case VARIABLE:
											lineToken = "getVariable ( '"+entityName+"' , '"+lineToken+"' )";
										break;
										case CONSTANT:
											lineToken = "getConstant ( '"+entityName+"' , '"+lineToken+"' )";
										break;
									}
								}
								lineToken = lineToken.replaceAll("=","==");
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("uqstring")) {
								if (!tokenizer.isReserved(lineToken)) {							
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Invalid uqstring (" + lineToken + ")");
								}
							}
							if (complierToken.equals("character")) {
								if (lineToken.length() == 3 && lineToken.charAt(0) == '\'' && lineToken.charAt(lineToken.length()-1) == '\'')  {
									lineToken = lineToken.substring(1,lineToken.length()-1);
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Invalid character (" + lineToken + ")");
								}
							}
							if (complierToken.equals("dontcare")) {
								bTokensMatch = true;
								break;
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
							if (complierToken.equals("comma")) {
								if (lineToken.equals(",")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected , (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("text")) {
								if (lineToken.equals("text")) {
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected text (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("enumtype")) {
								compilerSymbol cs = symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.TYPE);
								if (cs != null) {
									writeDirectToOutput("");
									bTokensMatch = true;
									break;
								} else {
									setExErrorMsg("Expected enumtype (got " + lineToken + ")");
								}
							}
							if (complierToken.equals("intvarname")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.VARIABLE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined integer variable (" + lineToken + ")");								
								}
//								break;
							}
							if (complierToken.equals("stringvarname")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.VARIABLE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined string variable (" + lineToken + ")");								
								}
//								break;
							}
							if (complierToken.equals("newintvarname")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid intvarname identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.VARIABLE,symClass.NONE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Variable (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("newstringvarname")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid stringvarname identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.VARIABLE,symClass.NONE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Variable (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("varname")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.VARIABLE) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined variable (" + lineToken + ")");								
								}
//								break;
							}
							if (complierToken.equals("newvarname")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid varname identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.VARIABLE,symClass.NONE) != null) {
									bTokensMatch = true;
									currentSigVarConstantName = lineToken;
									currentSigVarConstantType = symType.VARIABLE;
								} else {
									setExErrorMsg("Variable (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("constname")) {
								if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.CONSTANT) != null) {
									bTokensMatch = true;
								} else {
									setExErrorMsg("Undefined constant (" + lineToken + ")");								
								}
//								break;
							}
							if (complierToken.equals("newconstname")) {
								if (!isValidIdentifier(lineToken)) {setExErrorMsg(lineToken + " is not a valid constname identifier");break;}
								if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.CONSTANT,symClass.NONE) != null) {
									bTokensMatch = true;
									currentSigVarConstantName = lineToken;
									currentSigVarConstantType = symType.CONSTANT;
								} else {
									setExErrorMsg("Constant (" + lineToken + ") already defined");								
								}
								break;
							}
							if (complierToken.equals("constval")) {
								compilerSymbol tmp = symbolTable.findSymbol(entityName,currentScope(),currentSigVarConstantName,symType.CONSTANT);
								tmp.setSymbolValue(lineToken);
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("newentityid")) {
								entityName = lineToken;
								if (isValidIdentifier(lineToken)) {
										//supportFunctions.displayMessageDialog(null,"Added newentityid " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.ENTITY,symClass.NONE) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Entity (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid entityid identifier");}
							}
							if (complierToken.equals("newpackageid")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.PACKAGE,symClass.NONE) != null) {
										bTokensMatch = true;
										//supportFunctions.displayMessageDialog(null,"Added newpackageid " + entityName+","+currentScope()+","+lineToken);
									} else {
										setExErrorMsg("Package (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid package identifier");}
							}
							if (complierToken.equals("newarchname")) {
								archName = lineToken;
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.ARCH,symClass.NONE) != null) {
										//supportFunctions.displayMessageDialog(null,"Added newarchname " + entityName+","+currentScope()+","+lineToken);
										endOfModuleLines.removeAllElements();
										bTokensMatch = true;
									} else {
										setExErrorMsg("Architecture (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid archname identifier");}
							}
							if (complierToken.equals("archname")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end archname - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.ARCH) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined architecture (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid archname identifier");}
							}
							if (complierToken.equals("entityid")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end entityid - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.ENTITY) != null) {
										//supportFunctions.displayMessageDialog(null,"Found entityid");
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined entity (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid entityid identifier");}
							}
							if (complierToken.equals("packageid")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end packageid - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.PACKAGE) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined package (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid packageid identifier");}
							}
							if (complierToken.equals("endarchname")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end archname - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.ARCH) != null) {
										bTokensMatch = true;
										for (int jj=0;jj<endOfModuleLines.size();jj++) {
											compilerPHPFile.appendFile((String)endOfModuleLines.elementAt(jj)+"\n");									
										}
										endOfModuleLines.removeAllElements();
									} else {
										setExErrorMsg("Undefined end architecture (" + lineToken + ")");
										continue;
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid archname identifier");}
							}
							if (complierToken.equals("endentityid")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end entityid - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.ENTITY) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined end entity (" + lineToken + ")");								
										continue;
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid entityid identifier");}
							}
							if (complierToken.equals("endpackageid")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"end packageid - " + entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.PACKAGE) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined end package (" + lineToken + ")");	
										continue;
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid packageid identifier");}
							}
							if (complierToken.equals("newprocessname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.PROCESS,symClass.NONE) != null) {
										endOfFuncLines.removeAllElements();
										currentProcessName = lineToken;
										bTokensMatch = true;
									} else {
										setExErrorMsg("Process (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid processname identifier");}
							}
							if (complierToken.equals("processname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.PROCESS) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined process (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid processname identifier");}
							}
							if (complierToken.equals("functionname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.FUNCTION) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined function (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid functionname identifier");}
							}
							if (complierToken.equals("newfunctionname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,"",symType.FUNCTION,symClass.NONE) != null) {
										endOfFuncLines.removeAllElements();
										currentFunctionName = lineToken;
										bTokensMatch = true;
									} else {
										setExErrorMsg("Function (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid functionname identifier");}
							}
							if (complierToken.equals("procedurename")) {
								if (isValidIdentifier(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"Finding procedure procedure:"+entityName+","+currentScope()+","+lineToken);
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.PROCEDURE) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined procedure (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid procedurename identifier");}
							}
							if (complierToken.equals("newprocedurename")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,"",symType.PROCEDURE,symClass.NONE) != null) {
										//supportFunctions.displayMessageDialog(null,"Added procedure:"+entityName+","+currentScope()+","+lineToken);
										endOfFuncLines.removeAllElements();
										currentProcedureName = lineToken;
										bTokensMatch = true;
									} else {
										setExErrorMsg("Procedure (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid procedurename identifier");}
							}
							if (complierToken.equals("newcomponentname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.COMPONENT,symClass.NONE) != null) {
										componentDefName = lineToken;
										bTokensMatch = true;
									} else {
										setExErrorMsg("Component (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid componentname identifier");}
							}
							if (complierToken.equals("componentname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.COMPONENT) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined component (" + lineToken + ")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid componentname identifier");}
							}
							if (complierToken.equals("newcomponentid")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.COMPONENTID,symClass.NONE) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Componentid (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid componentid identifier");}
							}
							if (complierToken.equals("componentid")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.COMPONENTID) != null) {
										bTokensMatch = true;
									} else {
										setExErrorMsg("Undefined componentid ("+lineToken+":"+entityName+":"+currentScope()+")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid componentid identifier");}
							}
							if (complierToken.equals("signalname")) {
								if (isValidIdentifier(lineToken)) {
									if(lineToken.equals("others") || lineToken.equals("all")) {bTokensMatch = true;break;}
									if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.SIGNAL) != null) {
										bTokensMatch = true;
										//supportFunctions.displayMessageDialog(null,"sig matched");
									} else {
										setExErrorMsg("Undefined signal ("+lineToken+":"+entityName+":"+currentScope()+")");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid signal identifier");}
							}
							if (complierToken.equals("gsignalname")) {
								if (isValidIdentifier(lineToken)) {
									if(lineToken.equals("others") || lineToken.equals("all")) {bTokensMatch = true;break;}
									if (symbolTable.findSymbol(entityName,entityName,lineToken,symType.SIGNAL) != null) {
										bTokensMatch = true;
										//supportFunctions.displayMessageDialog(null,"sig matched");
										break;
									} else {
										setExErrorMsg("Undefined signal ("+lineToken+":"+entityName+":"+currentScope()+")");								
									}
									//break;
								} else {setExErrorMsg(lineToken + " is not a valid signal identifier");}
							}
							if (complierToken.equals("signamenocheck")) {
								if (isValidIdentifier(lineToken)) {
									//if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.SIGNAL) != null) {
										bTokensMatch = true;
										//supportFunctions.displayMessageDialog(null,"sig matched");
									//} else {
										//setExErrorMsg("Undefined signal ("+lineToken+":"+entityName+":"+currentScope()+")");								
									//}
									//break;
								} else {setExErrorMsg(lineToken + " is not a valid signal identifier");}
							}
							if (complierToken.equals("signamenocheck1")) {
								if (isValidIdentifier(lineToken)) {
									//if (symbolTable.findSymbol(entityName,currentScope(),lineToken,symType.SIGNAL) != null) {
										bTokensMatch = true;
										//supportFunctions.displayMessageDialog(null,"sig matched");
									//} else {
										//setExErrorMsg("Undefined signal ("+lineToken+":"+entityName+":"+currentScope()+")");								
									//}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid signal identifier");}
							}
							if (complierToken.equals("newsignalname")) {
								if (isValidIdentifier(lineToken)) {
									if (symbolTable.addSymbol(entityName,currentScope(),lineToken,String.valueOf(0),symType.SIGNAL,symClass.NONE) != null) {
										bTokensMatch = true;
										currentSigVarConstantName = lineToken;
										currentSigVarConstantType = symType.SIGNAL;
									} else {
										setExErrorMsg("Signal (" + lineToken + ") already defined");								
									}
									break;
								} else {setExErrorMsg(lineToken + " is not a valid signalname identifier");}
							}
							if (complierToken.equals("useentity") && lineToken.equals("useentity")) {
								//supportFunctions.displayMessageDialog(null,"use entity line - " + line);
								Pattern p = Pattern.compile("(?i)work\\s\\.\\s(.*?)\\s\\("); // GDB 25/10/15 old pattern (?i)work\\.(.*?)\\(
								Matcher m = p.matcher(line);
								if (m.find()) {
									//supportFunctions.displayMessageDialog(null,"Add extra compile file " + m.group(1));
									//extraCompileFiles.addElement(""+dataRelativePath+"/hdlworkbench/vhdlfiles/" + m.group(1) + "." + getFileExt());
									extraCompileFiles.addElement("vhdlfiles/" + m.group(1) + "." + getFileExt()); // GDB 090716
								}
								return -1; // because we have finish processing the complete line
							}
							if (complierToken.equals("port") && lineToken.equals("port")) {
								//supportFunctions.displayMessageDialog(null,"component port def");
								Pattern p = Pattern.compile("(?i)\\((.*?)\\)");
								Matcher m = p.matcher(line);
								if (m.find()) {
									//supportFunctions.displayMessageDialog(null,"store port: " + m.group(1) + " for component: " + componentDefName);
									if (componentDefName.length() != 0) { // port statement for a VHDL 'component'
										componentPorts.setProperty(componentDefName,m.group(1));
									} else { // port statement for an VHDL 'entity'
										Vector v2 = supportFunctions.splitIntoTokens(m.group(1),";");
										for (int i=0;i<v2.size();i++) {
											String signalName = (String)v2.elementAt(i);
											String modetype = signalName.substring(signalName.indexOf(':')+1);
											signalName = signalName.substring(0,signalName.indexOf(':'));
											signalName = signalName.trim(); // GDB 160815
											modetype = modetype.trim();  // GDB 160815
											//supportFunctions.displayMessageDialog(null,"port:"+signalName+":"+modetype+":"+currentScope());
											symbolTable.addSymbol(entityName,currentScope(),signalName,String.valueOf(0),symType.SIGNAL,symClass.NONE);
											// Every thing after 1st space is the type
											String mode = modetype.substring(0,modetype.indexOf(' '));
											String type = modetype.substring(modetype.indexOf(' ')+1);
											type = processTypeUse(entityName,"",type,complierToken,currentScope(),symType.NONE);
											signalFuncLines.insertElementAt("	" + "createSignal(\""+entityName+"\",\""+currentScope()+"\",\""+signalName+"\",\""+mode+"\",\""+type+"\",1,\"0\");",1);
											portDefLines.addElement(entityName+","+currentScope()+","+signalName+","+mode+","+type+",1,0");
										}
									}
								}
								return -1; // because we have finish processing the complete line
							}
							if (complierToken.equals("portmap") && lineToken.equals("portmap")) {
								Pattern p = Pattern.compile("(?i)\\:(.*?)port");
								Matcher m = p.matcher(line);
								if (m.find()) {
									//supportFunctions.displayMessageDialog(null,"got component name: " + m.group(1).trim());
									Pattern p1 = Pattern.compile("(?i)\\((.*?)\\)");
									Matcher m1 = p1.matcher(line);
									if (m1.find()) {
										String tmp = componentPorts.getProperty(m.group(1).trim());
										//supportFunctions.displayMessageDialog(null,"got port: " + tmp);
										signalFuncLines.insertElementAt("	" + "createSSMap(\""+m.group(1).trim()+"\",\""+tmp+"\",\""+entityName+"\",\""+m1.group(1)+"\");",1);
										portmapDefLines.addElement(m.group(1).trim()+","+tmp+","+entityName+","+m1.group(1));
									}
								}
								return -1; // because we have finish processing the complete line
							}
							if (tokenizer.isSyntax(complierToken)) {
								if (complierToken.equals(lineToken)) {
									//supportFunctions.displayMessageDialog(null,"CT: " + complierToken + " LT: " + lineToken);
									bTokensMatch = true;
									break;
								}
							}
							if (complierToken.equals("endif") && lineToken.equals("endif")) {
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endloop") && lineToken.equals("endloop")) {
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endfunction") && lineToken.equals("endfunction")) {
								if (currentParameterName.length() != 0) {
									compilerPHPFile.appendFile("return $" + currentParameterName + ";\n");
									currentParameterName = "";
									currentParameterType = "";
									currentParameterMode = "in";
									currentParameterDefault = "";
									currentParameterRet = 0;
									currentParameterPos = 0;								
								}
								
								for (int jj=0;jj<endOfFuncLines.size();jj++) {
									compilerPHPFile.appendFile((String)endOfFuncLines.elementAt(jj)+"\n");									
								}
								endOfFuncLines.removeAllElements();
								
								currentFunctionName = "";
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endprocedure") && lineToken.equals("endprocedure")) {
								//supportFunctions.displayMessageDialog(null,"endprocedure");
								for (int jj=0;jj<endOfFuncLines.size();jj++) {
									compilerPHPFile.appendFile((String)endOfFuncLines.elementAt(jj)+"\n");
									//supportFunctions.displayMessageDialog(null,"LL: " + (String)endOfFuncLines.elementAt(jj));
								}
								endOfFuncLines.removeAllElements();
								
								currentProcedureName = "";
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endcomponent") && lineToken.equals("endcomponent")) {
								componentDefName = "";
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endprocess") && lineToken.equals("endprocess")) {
								for (int jj=0;jj<endOfFuncLines.size();jj++) {
									compilerPHPFile.appendFile((String)endOfFuncLines.elementAt(jj)+"\n");									
								}
								endOfFuncLines.removeAllElements();
								
								currentProcessName = "";
								bTokensMatch = true;
								break;
							}
							if (complierToken.equals("endcase") && lineToken.equals("endcase")) {
								bTokensMatch = true;
								break;
							}
							// must always be last test
							if (tokenizer.isReserved(complierToken)) {
								if (complierToken.equals(lineToken)) {
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
									setExErrorMsg(""); // clear any multi token error messages on a successful token match
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
								// the *5 place holder (lineex) is only to add to an internal list
								if (tmp.equals("lineex")) {
									if (lineEX1.length() != 0) {lineEX1=lineEX1+",";}
									lineEX1 = lineEX1 + lineToken;
									//tmp = lineToken;
									tmp = "";
								}
								// and then the line token is added back into the trans string
								transText = transText.replace(tokens[phs],tmp);
							}
							
							// update special place holder *0 if needed
							if (transText.equals("<") || transText.equals("<=")) {
								placeHolders.setProperty("*0","++");
								transText = transText.replace("*0","++");
							}
							if (transText.equals(">") || transText.equals(">=")) {
								placeHolders.setProperty("*0","--");
								transText = transText.replace("*0","--");
							}

							if (bNoTransText) {transText = "";}
							
							if (transText.contains("{")) {indentionCount++;}
							if (transText.contains("}")) {indentionCount--;}
							
							// this if statement is on its own because we also have to append to compiler file !!!
							if (keyword.equals("process")) {
								if (lineEX1.length() != 0) {
									lineEX1 = lineEX1.replaceAll(",{2,}",",");
								}
								lineEX = "addProcessName(\""+moduleName+"\",\""+currentProcessName+"\",\""+lineEX1+"\");";
							}
							
							if (keyword.equals("force") || keyword.equals("forceperiod") || keyword.equals("port")) {
								lineEX = lineEX + transText;
							} else {
								if (!getCompileLine()) {
									//supportFunctions.displayMessageDialog(null,"tt:"+transText);
									if (bClearTransText) {compilerPHPFile.removeLastFileLine();} else {compilerPHPFile.appendFile(transText);}
								} else {
									compiledLine=transText;
								}
								
								debugLine = debugLine + transText;

							}
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
				
				if (keyword.equals("force") || keyword.equals("forceperiod") || keyword.equals("port")) {
						signalFuncLines.insertElementAt("	"+lineEX,1);
				} else if (keyword.equals("process")) {
						forcesFuncLines.insertElementAt("	"+lineEX,1);
						Vector vv = supportFunctions.splitIntoTokens(lineEX1,",");
						for (int m=0;m<vv.size();m++) {
							forcesFuncLines.insertElementAt("	"+"createSPMap(\""+moduleName+"\",\""+currentProcessName+"\",\""+(String)vv.elementAt(m)+"\");",1);
						}
				} 
			} // end of passNumber if statement
			compilerPHPFile.appendFile(entry.getpostEntryString());
			return -1; // for success
		}
		public String currentScope() {
			if (currentFunctionName.length()==0) {
				if (currentProcedureName.length()==0) {
					if (currentProcessName.length()==0) {
						return moduleName;
					} else {return currentProcessName;}
				} else {return currentProcedureName;}
			} 
		
			return currentFunctionName;
		}
	}
	
 	public class VerilogCompiler extends HDLCompiler {
		private	pseduoFile	compilerPHPFile;
		private	String		currentParameterName;
		private	String		currentParameterModifier;
		private	String		currentParameterType;
		private	String		currentParameterMode;
		private	String		currentParameterDefault;
		private	int		currentParameterRet;
		private	int		currentParameterPos;
		protected VerilogcompilerTokens tokenizer;
		protected String	compiledLine = "";

		public VerilogCompiler() {
			super(VerilogTransTableName,systemUserReg.getAppName());
			tokenizer = new VerilogcompilerTokens();
			tokenizer.setReservedWords(VerilogReservedWords);
			tokenizer.setTypes(VerilogTypes);
			setVarPreDefined(true);
			setCommentString("////");
			setStartMultiLineCommentString("//*");
			setEndMultiLineCommentString("*//");
			
			currentParameterName = "";
			currentParameterModifier = "";
			currentParameterType = "";
			currentParameterMode = "in";
			currentParameterDefault = "";
			currentParameterRet = 0;
			currentParameterPos = 1;
		}
		public String compileLine(String line) {
			String PHPCode = "";
			setCompileLine(true);
			Vector lines = supportFunctions.splitIntoTokens(line,";");
			for (int lineNumber=0;lineNumber<lines.size();lineNumber++) {
				compiledLine = "";
				if(!syntaxCheck((String)lines.elementAt(lineNumber))) {return "";}
				PHPCode = PHPCode + compiledLine;
			}
			setCompileLine(false);
			return PHPCode;
		}
		//public String getEntityName() {return entityName;}
		public boolean isValidIdentifier(String ident) {
			if (tokenizer.isReserved(ident)) {return false;}
			return isValidID(ident);
		}
		public boolean syntaxCheck(String line) {
			Vector v = tokenizer.tokenizeLine(line);
			syntaxError = "";
			return syntaxCheckInternal(line,v);
		}
		public String getKeywordFromLine(Vector tokens) {
			if (tokens.size() == 0) {return "";}
			// default case first token on line unless first token is a line label
			String tmp = (String)tokens.elementAt(0);
			String tmp1 = (String)tokens.elementAt(1);
			if (tmp1.equals(":")) {
				tokens.setElementAt((String)tokens.elementAt(0)+":",0);
				tokens.setElementAt("",1);
				if (tokens.size() > 2) {
					tmp =  (String)tokens.elementAt(2);
				} else {
					return "linelabel";
				}
			}

			return tmp;
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
//			supportFunctions.displayMessageDialog(null,"Verilog Syntax Check Internal Line : " + line);

			// delete the NYS keywords
			for (int i=0;i<VerilogNYSWords.length;i++) {
				tokens.removeElement(VerilogNYSWords[i]);
			}
	
			// get the Verilog keyword from the line
			String keyword = getKeywordFromLine(tokens);
			
			// process the line using the translation table entry defined by the above keyword
			int status = processLine(line,keyword,tokens);
			if (status != -1) {
				setErrorString("Line:" + String.valueOf(getLineNumber()+1)+" " + line,(String)tokens.elementAt(status));
				return false;
			}
			return true;
		}
		public String getName() {return "Verilog";}
		public String getFileExt() {return "vlog";}
		public String currentScope() {return "";}
		public boolean preCompile(String filename,boolean bHeaders) {
			indentionCount = 0;
			
			if (bHeaders) {
			
			symbolTable.removeAllSymbols();
			transTable = new translationTable();
			transTable.loadTranslationTable(transTableName);
//			compilerPHPFile = new basicFile("../hdlworkbench/output",supportFunctions.getFilenameNoExt(filename) + ".php");
			compilerPHPFile = new pseduoFile(""+dataRelativePath+"/"+appDirectory+"/output",supportFunctions.getFilenameNoExt(filename) + ".php");
			compilerPHPFile.saveFile("<html>\n<head>"); // saveFile used to remove any existing file
			if(systemUserReg.getAppRemotedHosted()) {
				compilerPHPFile.appendFile("\n<script src=\""+appBaseURL+"/website/scripts/common.js\" type=\"text/javascript\"></script>");
				compilerPHPFile.appendFile("\n<script src=\""+appBaseURL+"/website/scripts/jquery-1.9.0.js\" type=\"text/javascript\"></script>");
				compilerPHPFile.appendFile("\n<link rel=\"stylesheet\" href=\""+appBaseURL+"/website/css/ehs_default.css\" type=\"text/css\" media=\"screen\">");
			} else {
				compilerPHPFile.appendFile("\n<script src=\"../../../../../development/website/scripts/common.js\" type=\"text/javascript\"></script>");
				compilerPHPFile.appendFile("\n<script src=\"../../../../../development/website/scripts/jquery-1.9.0.js\" type=\"text/javascript\"></script>");
				compilerPHPFile.appendFile("\n<link rel=\"stylesheet\" href=\"../../../../../development/website/css/ehs_default.css\" type=\"text/css\" media=\"screen\">");
			}
			compilerPHPFile.appendFile("\n</head>\n<body>");
			compilerPHPFile.appendFile("\n<?");
			if(!systemUserReg.getAppRemotedHosted()) {
				compilerPHPFile.appendFile("\ninclude(\"../../../../../development/website/scripts/common.php\");");
			} else {
				compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/website/scripts/common.php\");");
			}
			// has to be before the line that includes simlib.php as this files will set it to a default of "" if not already set
			compilerPHPFile.appendFile("\n$langkeywords = \""+VerilogReservedWords+"\";\n");
			if(!systemUserReg.getAppRemotedHosted()) {
				compilerPHPFile.appendFile("\ninclude(\"../support/simlib.php\");");
				compilerPHPFile.appendFile("\ninclude(\"../support/verilogstandardfuncs.php\");");
			} else {
				compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/website/hdlworkbench/support/simlib.php\");");
				compilerPHPFile.appendFile("\ninclude(\""+appBaseURL+"/website/hdlworkbench/support/verilogstandardfuncs.php\");");
			}
			compilerPHPFile.appendFile("\n$link = mysqlConnect();");
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
			compilerPHPFile.appendFile("\nsimulate($_GET[\"entity\"]);\nmysql_close($link);\n?>");
			compilerPHPFile.appendFile("\n</body>\n</html>\n");
			compilerPHPFile.flush();
			}
			return true;
		}
		public boolean betweenPassProcesses() {
			if (bProcessHeaders) {
				compilerPHPFile.appendFile("\nfunction internalSimulate($module) {" + moduleName + "();}\n");
			}
			return true;
		}
		public boolean completeLine(String line) {return true;}
		public int processTokens(String line,String keyword,Vector lineTokens,translationTableEntry entry) {
			Vector compilerTokens = entry.getTokens();
			boolean bNoTransText = false;
			
//			supportFunctions.displayMessageDialog(null,"Verilog Process tokens for keyword : " + keyword);

			//placeHolders = new Properties();
			// clear line only place holder list
			placeHolders.remove("*1");
			placeHolders.remove("*2");
			placeHolders.remove("*3");
			placeHolders.remove("*4");
			
			Vector expComplierTokens = transTable.expandTokens(compilerTokens);

			currentParameterName = "";
			currentParameterModifier = "";
			currentParameterType = "";
			currentParameterMode = "in";
			currentParameterDefault = "";
			currentParameterRet = 0;
			currentParameterPos = 1;
			
			int compilerTokenIndex,lineTokenIndex;
			
			if (supportFunctions.strCount(line,'(') != supportFunctions.strCount(line,')')) {
				setExErrorMsg("Missing ( or ) characters");
				return 0;
			}
			
			if (getPassNumber() == 1) {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;
				placeHolders.setProperty("*9",moduleName);
			} else {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;
				
				setExErrorMsg("");
				String lineToken = "";
				String prevLineToken = "";
				String nextLineToken = "";
				String complierToken = "";
				compilerPHPFile.appendFile("\n");
				for (int i=0;i<indentionCount;i++) {
					compilerPHPFile.appendFile("\t");
				}
				compilerPHPFile.appendFile(entry.getpreEntryString());
				do {
					lineToken = (String)lineTokens.elementAt(lineTokenIndex);
					if (lineTokenIndex>0) {prevLineToken = (String)lineTokens.elementAt(lineTokenIndex-1);} else {prevLineToken = lineToken;}
					if (lineTokenIndex<lineTokens.size()-1) {nextLineToken = (String)lineTokens.elementAt(lineTokenIndex+1);} else {nextLineToken = lineToken;}
					if (lineToken.length() == 0) {lineTokenIndex++;continue;}
					// check for line labels
					if (lineTokenIndex == 0 && lineToken.endsWith(":")) {
						symbolTable.addSymbol(moduleName,currentScope(),lineToken,String.valueOf(getLineNumber()),symType.LABEL,symClass.NONE);
						compilerPHPFile.appendFile("");
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
						
						String transText = token.getTransText();
						if (bNoTransText) {transText = "";}
						if (transText.length() != 0) {
							//supportFunctions.displayMessageDialog(null,"tt: " + transText);
							Vector v2 = supportFunctions.splitIntoTokens(transText," ");
							Vector v3 = supportFunctions.splitIntoTokens((String)v2.elementAt(j),"##",false);
							transText = (String)v3.elementAt(multiTransTextIndex);
							
							// don't care about trans text marker
							if (transText.equals("x")) {transText="";}
							
							if (transText.contains("{")) {indentionCount++;}
							if (transText.contains("}")) {indentionCount--;}
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
				compilerPHPFile.appendFile(entry.getpostEntryString());
			}
			return -1; // for success
		}
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
			//tokenizer.setTypes(templateTypes);
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
			Vector v = tokenizer.tokenizeLine(line);
			syntaxError = "";
			return syntaxCheckInternal(line,v);
		}
		public String getKeywordFromLine(Vector tokens) {
			if (tokens.size() == 0) {return "";}
			// default case first token on line unless first token is a line label
			String tmp = (String)tokens.elementAt(0);
			String tmp1 = (String)tokens.elementAt(1);
			if (tmp1.equals(":")) {
				tokens.setElementAt((String)tokens.elementAt(0)+":",0);
				tokens.setElementAt("",1);
				if (tokens.size() > 2) {
					tmp =  (String)tokens.elementAt(2);
				} else {
					return "linelabel";
				}
			}
			return tmp;
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
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
		public int processTokens(String line,String keyword,Vector compilerTokens,Vector lineTokens,translationTableEntry entry) {
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
			} else {
				compilerTokenIndex = 0;
				lineTokenIndex = 0;
				
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
				//s = s.replaceAll("\\*a",completeScope());
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
						
						String transText = token.getTransText();
						if (bNoTransText) {transText = "";}
						if (transText.length() != 0) {
							//supportFunctions.displayMessageDialog(null,"tt: " + transText);
							Vector v2 = supportFunctions.splitIntoTokens(transText," ");
							Vector v3 = supportFunctions.splitIntoTokens((String)v2.elementAt(j),"##",false);
							transText = (String)v3.elementAt(multiTransTextIndex);
							
							// don't care about trans text marker
							if (transText.equals("x")) {transText="";}
							
							if (transText.contains("{")) {indentionCount++;}
							if (transText.contains("}")) {indentionCount--;}
							
							compilerFile.appendFile(transText);
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
				//s = s.replaceAll("\\*a",completeScope());
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

	//public String	selFile;
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
	
	public void doAppUpdate() {
		   try {
		   	   URL u = new URL(getCodeBase(),"../appupdater.php?appproduct="+systemUserReg.getAppName()+"&curbuildnum="+systemUserReg.getBuildNumber()+"&serialbase="+systemUserReg.getAppSerialBase()+"&directory="+dataRelativePath+"/hdlwb/hdlwbbuild.number");
		   	   ac.showDocument(u,"_blank");
		   } catch (Exception e) {e.printStackTrace();}
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
				  HDLTA.setRawText("I love <b>Joide</b>");
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
			 
	public class HDLDrawingItemPort {
		public String	name;
		public String   type;
		public String	mode;
		public Point	ptPort;
		
		public HDLDrawingItemPort() {}
		public HDLDrawingItemPort(String name,String mode,String type) {
			this.name = name;
			this.type = type;
			this.mode = mode;
			ptPort = null;
		}
	}
	public class HDLDrawingItem extends drawingItem {
		private	Rectangle		boundingRect;
		private	int				umlDIWidth = 100;
		private	int				umlDIHeight = 100;
		private	Vector 			ports = new Vector();
		private int				iLevel;
		private	String			path;
		private	String 			entity;

		public HDLDrawingItem() {}
		public HDLDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeHDL,id,orgX,orgY,p1,p2,p3,p4,fill,c);

			iLevel = -1;
			iSheet = 1;
			boundingRect = new Rectangle(orgX,orgY,umlDIWidth,umlDIHeight);
			
			setEntity(p1);
			Vector v = supportFunctions.splitIntoTokens(p2,";");
			for (int i=0;i<v.size();i++) {
				String tmp = (String)v.elementAt(i);
				Vector v1 = supportFunctions.splitIntoTokens(tmp,",");
				ports.addElement(new HDLDrawingItemPort((String)v1.elementAt(0),(String)v1.elementAt(1),(String)v1.elementAt(2)));
			}
			setPath(p3);
			
			processHDLPorts(); // calculation positions of HDL ports on HDL drawing item
		}
		public Point getHDLPortPoint(String name) {
			for (int i=0;i<ports.size();i++) {
				HDLDrawingItemPort port = (HDLDrawingItemPort)ports.elementAt(i);
				if (port.name.equals(name)) {return port.ptPort;}
			}
			
			return (Point)null;
		}
		public void processHDLPorts() {
			Rectangle r = getBoundingRect();
			int inYCord = r.y + (3 * charWidth);
			int outYCord = r.y + (4 * charWidth);
			int xCord = 0;
			for (int i=0;i<ports.size();i++) {
				HDLDrawingItemPort port = (HDLDrawingItemPort)ports.elementAt(i);
				if (port.mode.equals("in")) {
					xCord = r.x + 3;
					port.ptPort = new Point(xCord,inYCord);
					inYCord = inYCord + (2 * charWidth);
				} else {
					xCord = r.x + r.width;
					port.ptPort = new Point(xCord,outYCord);
					outYCord = outYCord + (2 * charWidth);
				}
			}
		}
		public Vector getPorts() {return ports;}
		public String getEntity() {return entity;}
		public void setEntity(String s) {entity = s;}
		public String getPath() {return path;}
		public void setPath(String s) {path = s;}
		public int getLevel() {return iLevel;}
		public void setLevel(int i) {iLevel = i;}
		public String getUserDefinedName() {return entity;}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			g2d.setColor(Color.green);
			g2d.fillRect((int)boundingRect.getX(),(int)boundingRect.getY(),(int)boundingRect.getWidth(),(int)boundingRect.getHeight());
			supportFunctions.boxCenterText(g2d,Color.green,getUserDefinedName(),(int)boundingRect.getX(),(int)boundingRect.getY(),(int)boundingRect.getWidth(),1 * charHeight,false);
			g2d.setColor(Color.black);
			for (int i=0;i<ports.size();i++) {
				HDLDrawingItemPort port = (HDLDrawingItemPort)ports.elementAt(i);
				if (port.mode.equals("in")) {
					g2d.drawString(port.name,port.ptPort.x,port.ptPort.y);
				} else {
					g2d.drawString(port.name,port.ptPort.x-(charWidth * port.name.length()),port.ptPort.y);
				}
			}
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
		public void moveTo(Point p) {
			Point ptOldOrgin = getOrigin();
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
			for (int i=0;i<ports.size();i++) {
				HDLDrawingItemPort port = (HDLDrawingItemPort)ports.elementAt(i);
				Point ptOrg = port.ptPort;
				port.ptPort = new Point((p.x - ptOldOrgin.x) + ptOrg.x,(p.y - ptOldOrgin.y) + ptOrg.y);
			}
		}
	}
	public class HDLConnDrawingItem extends drawingItem {
		private	Rectangle		boundingRect;
		private	String			connEntity;
		private String 			connPort;
		private String			connList;
		private String 			connName;
		
		public HDLConnDrawingItem() {}
		public HDLConnDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(dcTypeHDLConn,id,orgX,orgY,p1,p2,p3,p4,fill,c);

			connName = p1;
			connEntity = p2;
			connPort = p3;
			connList = p4;
			
			setUserDefinedName(connName);
			boundingRect = new Rectangle(orgX,orgY,4*charWidth,2*charWidth);
		}
		public String getPortNetList() {return connList;}
		public String getUserDefinedName() {return connName;}
		public String getEntity() {return connEntity; }
		public String getPort() {return connPort;}
		public String getName() {return connName;}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			Rectangle r = getBoundingRect();
			g2d.drawOval(r.x,r.y,2*charWidth,2*charWidth);
			g2d.drawLine(r.x+(2*charWidth),r.y+charWidth,r.x+(4*charWidth),r.y+charWidth);
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here as UML drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			  return boundingRect;
		}
		public void moveTo(Point p) {
			setOrigin(p);
			boundingRect = new Rectangle(diOriginX,diOriginY,boundingRect.width,boundingRect.height);
			setTransformBoundingBox(new Rectangle(0,0,ehsConstants.dcMaxX,ehsConstants.dcMaxY));
		}
	}
	public class HDLDrawingCanvas extends drawingCanvas {
		   public HDLDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   super(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public void beforePainting(Graphics2D g2d) {;}
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {
		   }
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
				if (type == dcTypeHDL) {
					HDLDrawingItem d = new HDLDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					return d;				
				}
				if (type == dcTypeHDLConn) {
					HDLConnDrawingItem d = new HDLConnDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					return d;				
				}
				
				return (drawingItem)null;
		   }
	}
	public class scrollableDrawingCanvas {
		  private HDLDrawingCanvas  	dC;
		  private ScrollPane		  	sPane;
		  private Adjustable			bottomSB,rightSB;
		  
		  public scrollableDrawingCanvas() {
			  this("",ehsConstants.dcMaxX,ehsConstants.dcMaxY,ehsConstants.dcGridSpaceX,ehsConstants.dcGridSpaceY,true);
		  }
		  public scrollableDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean visible) {
			  dC = new HDLDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,visible);
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
