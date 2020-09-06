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
import javax.xml.ws.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;
import javax.xml.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
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
import javax.activation.URLDataSource.*;

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

	// when a drawing item is added a unique Id is added of hashcode(macheId+ddMMyyyyHHmmss);
	public class drawingCanvas extends printableCanvas implements MouseListener,MouseMotionListener,KeyListener,ChangeListener,ActionListener,ItemListener,ListSelectionListener,Printable,buttonCanvasUtils,statusCanvasDialogListener {
		protected	static final int		DCLoadFromDB = 0;
		protected	static final int		DCLoadFromXML = 1;
		private int charWidth = 8;
		private int charHeight = 14;

		private	float	fScalingFactor = 1;
		   private JList 	GCSheets = null;
		   protected int	canvasMaxX,canvasMaxY;
		   private boolean	gridVisible,dcFilled,dcOutline;
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
				return ehsConstants.applicationName;
		   }
			public void drawPictureFrame(Graphics2D g2d) {
				//TRACE("drawPictureFrame:entered",4);
				Color topColor = new Color(181,162,195);
				if (!bDrawPictureFrame) {return;}
				
				//TRACE("drawing picture frame",4);
				g2d.setStroke(new BasicStroke(3));
				g2d.drawRoundRect(charWidth,charHeight*3,ehsConstants.dcMaxX-charWidth,ehsConstants.dcMaxY-charHeight,30,30);
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
				String tmp = supportFunctions.getSystemVar(getEntity() + "_dcbackcolor","white");
				setBackgroundColor(supportFunctions.getColorCode(tmp));
				
				return true;
		   }
		   public void closeDrawingCanvas() {
				supportFunctions.setSystemVar(getEntity() + "_dcbackcolor",supportFunctions.getColorName(getBackgroundColor()));
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
				return String.valueOf(supportFunctions.ehsHashCode(ehsConstants.machineID + tmp));
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
			   createDrawingCanvas("",ehsConstants.dcMaxX,ehsConstants.dcMaxY,ehsConstants.dcGridSpaceX,ehsConstants.dcGridSpaceY,true);
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
			   printing = false;
			   dragDI = null;
			   dragFH = -1;
			   stickyTools = true;
			   dragFHDI = null;
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
			   backgroundColor = Color.white;
			   bUpdateDC = true;
			   clipboardActive = false;
			   
			   createMenu();
			   setSize(maxX,maxY);
			   dcColor = Color.black;
			   
			   addGCSheet(entity);

			   Toolkit toolkit = Toolkit.getDefaultToolkit();
			   MediaTracker picTracker = new MediaTracker(this);
			   Image picImage = toolkit.getImage(ehsConstants.rotateJPG);
			   picTracker.addImage(picImage,0);
			   try {picTracker.waitForID(0);} catch (InterruptedException e) {}
				if (picTracker.isErrorAny()) {
					//TRACE("Error in loading image " + ehsConstants.rotateJPG,4);
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
			   
			   onInitDrawingCanvas();
		   }
		   public void onInitDrawingCanvas() {}
		   public void onDestroyDrawingCanvas() {}
		   public void destroyDrawingCanvas() {
			   onDestroyDrawingCanvas();
			   
				if (GCSheetsDialog != null) {
					GCSheetsDialog.destory();
					GCSheetsDialog.dispose();
				}
			   //TRACE("Drawing Canvas Destroyed",4);
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

			   drawConnectors(g2d);
			   
			   for (int k=0;k<drawingItems.size();k++) {
				   drawingItem d = (drawingItem)drawingItems.elementAt(k);
				   boolean focus = false;
				   if ((k == dcSelIndex || selectedDrawingItems.contains(d)) && !isPrinting()) {focus = true;}
				   focus = focus & getDrawFocusHandles();
				   checkRectBounds(d,d.getBoundingRect());
				   if (readonly) {focus = false;}
				   if (layerMan.layerDisplayed("Layer "+String.valueOf(d.getLayer()))) {if(doPaint(d)) {d.paint(g2d,focus);}}
			   }
			   
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
					//TRACE("dcLayer:" + String.valueOf(dcLayer),4);
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
			   selectedDrawingItems.removeAllElements();
			   clipboardDrawingItems.removeAllElements();
			   dcMode = ehsConstants.dcTypeSelect;
			   bUpdateDC = true;
			   loadMode = DCLoadFromDB;
			   clipboardActive = false;
			   
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
			   Dimension size = new Dimension(ehsConstants.dcMaxX,ehsConstants.dcMaxY);
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
					setCursor(eresizeCursor);
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
						if (d.getType() == ehsConstants.dcTypeSwitchGCSheet) {switchGCSheet("");}
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
			//TRACE("AUI: "+id+"#"+cmd+"#"+params+"#"+supportFunctions.valueOf(bMultiple),4);
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
			  paint();
		  }
		  public void ungroup(String id) {
			  for (int i=0;i<drawingItems.size();i++) {
				  drawingItem d = (drawingItem)drawingItems.elementAt(i);
				  String tmp = d.getGroupID();
				  if (tmp.equals(id)) {d.setGroupID("");}
			  }
			  paint();
		  }
		  public void renameEntityNameInDB(String oldEntityName,String newEntityName) {
			if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcDrawingItems SET sdcDIEntity='" + newEntityName + "' WHERE sdcDIEntity='" + oldEntityName + "'","");}
		  }
		  public void deleteDCFromDB(String entity) {
			if (bUseDatabase) {supportFunctions.getDBConn().executeSQLQuery("DELETE FROM sdcDrawingItems WHERE sdcDIEntity='" + entity + "'","");}
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
				  //displayTransTableEditor();
			  }
			  if (keyCode == KeyEvent.VK_S && ctrlDown) {
				  String s = supportFunctions.currentShortTime().replace(':','_');
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
			   //TRACE("Button right clicked",4);
			   doCommand(cmd,"back");
		  }
		  public void buttonLeftClicked(MouseEvent evt) {
			   JButton b = (JButton)evt.getSource();
			   String cmd = b.getToolTipText();
			   //TRACE("Button left clicked",4);
			   doCommand(cmd,"fore");
		  } 
		  public void buttonDoubleClicked(MouseEvent evt) {
			   JButton b = (JButton)evt.getSource();
			   String cmd = b.getToolTipText();
			   if (cmd.equalsIgnoreCase("customcolor1")) {setCustomColor1();}
			   if (cmd.equalsIgnoreCase("customcolor2")) {setCustomColor2();}
		  }
			public void setCustomColor1() {
				Color c = JColorChooser.showDialog(this,"Choose Custom Color 1",
						ehsConstants.colorCodes[ehsConstants.colorCodes.length-1]);
				if (c != null) {
					ehsConstants.colorCodes[ehsConstants.colorCodes.length-1] = c;			
					supportFunctions.getAppConfigSettings().setConfigurationSetting("customcolor1",String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()));
					refreshColorToolWindow();
				}
			}
			public void setCustomColor2() {
				Color c = JColorChooser.showDialog(this,"Choose Custom Color 2",
						ehsConstants.colorCodes[ehsConstants.colorCodes.length-2]);
				if (c != null) {
					ehsConstants.colorCodes[ehsConstants.colorCodes.length-2] = c;			
					supportFunctions.getAppConfigSettings().setConfigurationSetting("customcolor2",String.valueOf(c.getRed())+","+String.valueOf(c.getGreen())+","+String.valueOf(c.getBlue()));
					refreshColorToolWindow();
				}
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
		  public void deleteDrawingItemsOfType(int type) {
			for (int i=0;i<drawingItems.size();i++) {
				drawingItem d = (drawingItem)drawingItems.elementAt(i);
				if (d.getType() == type) {deleteDrawingItem(d);}
			}
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
			supportFunctions.getMenuItem(menu,"Paste").setEnabled(clipboardDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Cut").setEnabled(selectedDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Copy").setEnabled(selectedDrawingItems.size() != 0);
			supportFunctions.getMenuItem(menu,"Undo").setEnabled(undoIndex > -1);
			supportFunctions.getMenuItem(menu,"Redo").setEnabled(undoIndex > undoDrawingItems.size() - 1);
			cbPictureFrame.setState(bDrawPictureFrame);	 	  
		  }
		  public void itemStateChanged(ItemEvent evt) {
			  if (evt.getSource() == cbPictureFrame) {
				  //TRACE("Changed show picture frame state",4);
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
	}
	