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

public class transTableDrawingItem extends drawingItem {
		private String 				keyword;
		private String				text;
		private	String 				transtext;
		private String 				flags;
		private String				prestring;
		private String				poststring;
		private	Rectangle			boundingRect;
		private	transTableTokenType	type = transTableTokenType.NONE;
		private int					groupTTID = 0;
		private	int					count = 0;
		
		public void setCount(int i) {count = i;}
		public int getCount() {return count;}
		public void setTTGroupID(int i) {groupTTID = i;}
		public int getTTGroupID() {return groupTTID;}
		public transTableTokenType getTokenType() {return type;}
		public void setTokenType(transTableTokenType t) {type = t;}
		public String getPreString() {return prestring;}
		public void setPreString(String s) {prestring = s;} 
		public String getPostString() {return poststring;}
		public void setPostString(String s) {poststring = s;} 
		public String getFlags() {return flags;}
		public String getTransText() {return transtext;}
		public String getText() {return text;}
		public transTableDrawingItem() {}
		public transTableDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(ehsConstants.dcTypeTransTable,id,orgX,orgY,p1,p2,p3,p4,fill,c);
			
			Vector v = supportFunctions.splitIntoTokens(p1,":");
			keyword = (String)v.elementAt(0);
			text = (String)v.elementAt(1);
			this.transtext = p2;
			this.flags = p3;
			this.groupTTID = Integer.parseInt(p4);
			boundingRect = new Rectangle(orgX,orgY,ehsConstants.ttDIWidth,ehsConstants.ttDIHeight);
			prestring = "";
			poststring = "";
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void editor() {
			String[] titles = new String[]{"Text","Trans Text","Flags"};			
			String[] defaults = new String[] {getText(),getTransText(),getFlags()};
			//String[] data = getDataAsDialog("Edit Trans Table Element",titles,defaults);
			String[] data = null;
			if (data != null) {
				text = data[0];
				transtext = data[1];
				flags = data[2];
			}
			
			repaint();
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			
			g2d.setColor(type.getTokenColor());

			AffineTransform at = new AffineTransform();
			Shape s = new RoundRectangle2D.Double(boundingRect.x, boundingRect.y, 
					boundingRect.width, boundingRect.height,
					25,25);
			g2d.fill(s);

			g2d.setColor(Color.black);
			g2d.drawRoundRect(boundingRect.x, boundingRect.y, 
					boundingRect.width, boundingRect.height,
					25,25);
			supportFunctions.centerTextAtBox(g2d,text,boundingRect);
			
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			return boundingRect;
		}
		public void setBoundingRect(Rectangle r) {
			boundingRect = r;
		}
		//public boolean canDelete() {return false;}
	}
