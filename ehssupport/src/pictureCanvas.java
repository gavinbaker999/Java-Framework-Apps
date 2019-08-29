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

public class pictureCanvas extends printableCanvas implements Printable { 
		private	Image		picImage = null;
		private	int		picWidth,picHeight;
		private	pictureUtils	target;
		
		pictureCanvas(String name,int mx,int my) {
			setuppictureCanvas(name,mx,my);
		}
		pictureCanvas(int mx,int my) {
			setuppictureCanvas("",mx,my);
		}
		pictureCanvas(String name) {
			setuppictureCanvas(name,ehsConstants.noScaleX,ehsConstants.noScaleY);
		}
		public void setuppictureCanvas(String name,int mx,int my) {
			  picImage = null;
			  target = null;
		   	  picWidth = mx;
			  picHeight = my;
			  setSize(mx,my);
			  if (name.length() != 0) {pictureLoad(name);}
		}
		public void removePictureListener() {target = null;}
		public void addPictureListener(pictureUtils pu) {target = pu;}
		public void pictureLoad(String name) {
			System.err.println("Loading image " + name);
			picStartLoading();

			MediaTracker picTracker = new MediaTracker(this);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			picImage = toolkit.getImage(name);
			picTracker.addImage(picImage,0);
			try {picTracker.waitForID(0);} catch (InterruptedException e) {}
			if (picTracker.isErrorAny()) {
				System.err.println("Error in loading image " + name);
			}

			picFinishLoading();
			if (picWidth == ehsConstants.noScaleX) {
				picWidth = picImage.getWidth(this);
				picHeight = picImage.getHeight(this);
			} 
			setSize(picWidth,picHeight);
			repaint();
		}
		public void picStartLoading() {
			System.err.println("picStartLoading called");
			if (target != null) {target.pictureStart();}
		}
		public void picFinishLoading() {
			System.err.println("picFinishLoading called");
			if (target != null) {target.pictureFinish();}
		}	
		public int print(Graphics g,PageFormat format,int pagenum) {
			if (pagenum>0) {return Printable.NO_SUCH_PAGE;}
			Dimension size = new Dimension(picWidth,picHeight);
			Graphics2D g2d = printPageSetup(g,format,size);
			paint(g2d);
			return Printable.PAGE_EXISTS;
		}
		public void update(Graphics g) {
			paint(g);
		}
		public void paint(Graphics g) {
			paint(g,0,0);
		}
		public void paint(Graphics g,int x,int y) {
			if (picImage != null) {
				g.drawImage(picImage,x,y,picWidth,picHeight,this);
			}
			if (target != null) {target.picturePaint(g);}
		}
		public void setImage(Image i) {
			System.err.println("setImage in pictureCanvas called");
			picImage = i;
			if (picWidth == ehsConstants.noScaleX) {
				picWidth = picImage.getWidth(this);
				picHeight = picImage.getHeight(this);
			} 
			setSize(picWidth,picHeight);
			repaint();
		}
		public int height() {return picHeight;}
		public int width() {return picWidth;}
		public Image image() {return picImage;}		
	}
