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

public abstract class drawingItem extends Component {
	  protected	int	diOriginX,diOriginY;
	  protected	String	diParam1,diParam2,diParam3,diParam4,diUnique;
	  protected	Color	diColor;
	  protected	boolean	diFilled,diCanTransform;
	  protected	String	diID;
	  protected	Vector  diFocusHandles = new Vector();
	  protected	int	diOffsetX,diOffsetY;
	  protected String  diGroupID;
	  protected	int	diStrokeWidth;
	  protected int diType;
	  protected	int	diLayer;
	  protected	int	diRotAngle;
	  protected	Rectangle rcTransformBoundingBox;
	  protected	Rectangle rcLastPos;
	  protected	String userDefinedName = "";
	  protected 	int			iSheet;

	  public boolean canDelete() {return true;}
	  public void delete() {;}
	  public void editor() {;}
	  public int getSheet() {return iSheet;}
	  public void setSheet(int i) {iSheet = i;}
	  public void setUserDefinedName(String s) {userDefinedName = s;}
	  public String getUserDefinedName() {return userDefinedName;}
	  public drawingItem() {}
	  public drawingItem(int type,String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
		  diOriginX = orgX;
		  diOriginY = orgY;
		  diParam1 = p1;
		  diParam2 = p2;
		  diParam3 = p3;
		  diParam4 = p4;
		  diFilled = fill;
		  diColor = c;
		  diID = id;
		  diOffsetX = 0;
		  diOffsetY = 0;
		  diUnique = "";
		  diGroupID = "";
		  diStrokeWidth = 1;
		  diType = type;
		  diLayer = 1;
		  diRotAngle = 0;
		  diCanTransform = true;
		  rcTransformBoundingBox = new Rectangle(0,0,0,0);
		  rcLastPos = new Rectangle(0,0,0,0);
		  iSheet = 1;
	  }
	  
	  public String getParams(String entity) {
		return entity + "," + String.valueOf(getType()) + "," + String.valueOf(getOriginX()) + "," + String.valueOf(getOriginY()) + "," + getParam1() + "," + getParam2() + "," + getParam3() + "," + getParam4() + "," + supportFunctions.valueOf(getFilled()) + "," + supportFunctions.getColorName(getColor());
	  } 
	  public Rectangle getTransformBoundingBox() {return rcTransformBoundingBox;}
	  public void setTransformBoundingBox(Rectangle bounding) {
		Rectangle rc = getBoundingRect(); // bounding rectangle at an angle of 0 degrees rotation
		Point pt = centerPoint(rc);
		// rotate rc by diRotAngle degrees around the center point
		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(diRotAngle),pt.x,pt.y);
		Shape s = rotation.createTransformedShape((Shape)rc);
		// check that the rotated drawing item fits in the supplied bounding rect, if not leave 
		// the transform rectangle unchanged
		if (supportFunctions.rectContainsRect(bounding,(Rectangle)s.getBounds())) {
			rcTransformBoundingBox = s.getBounds();
		} else {
			rcTransformBoundingBox = getBoundingRect();
		}
	  }
	  public boolean getCanTransform() {return diCanTransform;}
	  public void setCanTransform(boolean b) {diCanTransform = b;}
	  public int getLayer() {return diLayer;}
	  public void setLayer(int i) {diLayer = i;}
	  public int getRotAngle() {return diRotAngle;}
	  public void setRotAngle(int i) {diRotAngle = i;}
	  public int getType() {return diType;}
	  public void setType(int i) {diType = i;}
	  public int getStrokeWidth() {return diStrokeWidth;}
	  public void setStrokeWidth(int i) {diStrokeWidth = i;}
	  public String getGroupID() {return diGroupID;}
	  public void setGroupID(String s) {diGroupID = s;}
	  public void setOffsetX(int i) {diOffsetX = i;}
	  public int getOffsetX() {return diOffsetX;}
	  public void setOffsetY(int i) {diOffsetY = i;}
	  public int getOffsetY() {return diOffsetY;}
	  public void setColor(Color c) {diColor = c;}
	  public Color getColor() {return diColor;}
	  public void setOrigin(Point p) {diOriginX = p.x;diOriginY = p.y;}
	  public Point getOrigin() {return new Point(diOriginX,diOriginY);}
	  public void setFilled(boolean b) {diFilled = b;}
	  public boolean getFilled() {return diFilled;}
	  public void setParam1(String s) {diParam1 = s;}
	  public String getParam1() {return diParam1;}
	  public void setParam2(String s) {diParam2 = s;}
	  public String getParam2() {return diParam2;}
	  public void setParam3(String s) {diParam3 = s;}
	  public String getParam3() {return diParam3;}
	  public void setParam4(String s) {diParam4 = s;}
	  public String getParam4() {return diParam4;}
	  public String getUnique() {return diUnique;}
	  public void setUnique(String s) {diUnique = s;}
	  public void setID(String s) {diID = s;}
	  public String getID() {return diID;}
	  public void paint() {paint((Graphics2D)getGraphics(),false);}
	  public void paint(Graphics2D g2d) {paint(g2d,false);}
	  public void paint(Graphics2D g2d,boolean focus) {}
	  public void teardownPaint(Graphics2D g2d,boolean focus) {
		  if (focus) {drawFocusHandles(g2d);}
	  }
	  public Point centerPoint(Rectangle r) {
		float cx = (float)r.getWidth() / (float)2.00;
		float cy = (float)r.getHeight() / (float)2.00;
		cx = cx + (float)r.getX();
		cy = cy + (float)r.getY();
		return new Point((int)cx,(int)cy);
	  }
	  public void setupPaint(Graphics2D g2d,boolean focus) {
		  g2d.setColor(diColor);
		  g2d.setStroke(new BasicStroke(diStrokeWidth));
		  Point pt = centerPoint(getTransformBoundingBox());
		  AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(diRotAngle),pt.x,pt.y);
		  g2d.setTransform(rotation);
	  }
	public boolean hitTest(int x,int y) {
		  Rectangle r = getTransformBoundingBox();
		  return r.contains(x,y);
	}
	  public Rectangle getBoundingRect() {return new Rectangle(0,0,0,0);}
	  public String getCompositeID() {return "";}
	  public int hitTestFocusHandles(int x,int y) {
		  for (int i=0;i<diFocusHandles.size();i++) {
			  Rectangle r = (Rectangle)diFocusHandles.elementAt(i);
			  if (r.contains(x,y)) {return i;}
		  }
		  return -1;
	  }
	  public void drawFocusHandles(Graphics2D g2d) {
		  calcFocusHandles();
		  g2d.setColor(Color.black);
		  for (int i=0;i<diFocusHandles.size();i++) {
			  Rectangle r = (Rectangle)diFocusHandles.elementAt(i);
			  if (!r.isEmpty()) {g2d.fillRect((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());}
		  }
	  }
	  public void calcFocusHandles() {
		  int focusHandleSize = 10;
		  
		  diFocusHandles.removeAllElements();
		  Rectangle r = getTransformBoundingBox();
		  diFocusHandles.addElement(new Rectangle((int)r.getX(),(int)r.getY(),focusHandleSize,focusHandleSize));
		  diFocusHandles.addElement(new Rectangle((int)r.getX()+(int)r.getWidth()-focusHandleSize,(int)r.getY(),focusHandleSize,focusHandleSize));
		  diFocusHandles.addElement(new Rectangle((int)r.getX(),(int)r.getY()+(int)r.getHeight()-focusHandleSize,focusHandleSize,focusHandleSize));
		  diFocusHandles.addElement(new Rectangle((int)r.getX()+(int)r.getWidth()-focusHandleSize,(int)r.getY()+(int)r.getHeight()-focusHandleSize,focusHandleSize,focusHandleSize));
		  
		  int halfWidth = (int)r.getWidth() / 2;
		  int halfHeight = (int)r.getHeight() / 2;
		  
		  if (r.getWidth() > 10*focusHandleSize) {
			  diFocusHandles.addElement(new Rectangle((int)r.getX()+halfWidth,(int)r.getY(),focusHandleSize,focusHandleSize));
			  diFocusHandles.addElement(new Rectangle((int)r.getX()+halfWidth,(int)r.getY()+(int)r.getHeight()-focusHandleSize,focusHandleSize,focusHandleSize));
		  } else {
			  diFocusHandles.addElement(new Rectangle(0,0,0,0));
			  diFocusHandles.addElement(new Rectangle(0,0,0,0));
		  }
		  if (r.getHeight() > 10*focusHandleSize) {
			  diFocusHandles.addElement(new Rectangle((int)r.getX(),(int)r.getY()+halfHeight,focusHandleSize,focusHandleSize));
			  diFocusHandles.addElement(new Rectangle((int)r.getX()+(int)r.getWidth()-focusHandleSize,(int)r.getY()+halfHeight,focusHandleSize,focusHandleSize));
		  } else {
			  diFocusHandles.addElement(new Rectangle(0,0,0,0));
			  diFocusHandles.addElement(new Rectangle(0,0,0,0));
		  }
		  // calculate centre rotation focus handle
		  if (getCanTransform()) {
			Point pt = centerPoint(getTransformBoundingBox());
			diFocusHandles.addElement(new Rectangle(pt.x,pt.y,focusHandleSize,focusHandleSize));
		  }
	  }
	  public void fitToRectangle(Rectangle r) {;}
	  public void showPropsDialog(String entity) {
	  	drawingItemPropsDialog d = new drawingItemPropsDialog(supportFunctions.getTopLevelParent(this),this);
		if (d.isOK()) {
			setColor(d.getColor());
			supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIColor='"+supportFunctions.getColorName(d.getColor())+"' WHERE sdcDIEntity='"+entity+"' AND sdcDIName='"+getID()+"'","");
			setStrokeWidth(d.getStrokeWidth());
			supportFunctions.getDBConn().executeSQLQuery("UPDATE sdcdrawingitems SET sdcDIStrokeWidth="+String.valueOf(d.getStrokeWidth())+" WHERE sdcDIEntity='"+entity+"' AND sdcDIName='"+getID()+"'","");
		} 
		d.dispose();
	  }
	  public int getOriginX() {return diOriginX;}
	  public int getOriginY() {return diOriginY;}
	  public void getDrawingItemAsXML(org.w3c.dom.Document doc,org.w3c.dom.Element root) {
		org.w3c.dom.Element setting = doc.createElement("drawingitem");
		setting.setAttribute("layer",String.valueOf(getLayer()));
		setting.setAttribute("rotangle",String.valueOf(getRotAngle()));
		setting.setAttribute("type",String.valueOf(getType()));
		setting.setAttribute("name",getID());
		setting.setAttribute("originx",String.valueOf(getOriginX()));
		setting.setAttribute("originy",String.valueOf(getOriginY()));
		setting.setAttribute("color",supportFunctions.getColorName(getColor()));
		setting.setAttribute("fill",supportFunctions.valueOf(getFilled()));
		setting.setAttribute("param1",getParam1());
		setting.setAttribute("param2",getParam2());
		setting.setAttribute("param3",getParam3());
		setting.setAttribute("param4",getParam4());
		setting.setAttribute("unique",getUnique());
		setting.setAttribute("width",String.valueOf(getStrokeWidth()));
		root.appendChild(setting);
	  }
}
