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

public class diControlCanvas {
		private buttonCanvas canvas;
		private Vector names = new Vector();
		private Vector icons = new Vector();
		
		public diControlCanvas() {
			createCustomIcons();
			canvas = new buttonCanvas("Controls",2);
			Insets m = new Insets(0,0,0,0);
			for(int i=0;i<names.size();i++) {
				String name = (String)names.elementAt(i);
				customIcon icon = (customIcon)icons.elementAt(i);
				JCustomButton b = new JCustomButton("",name,icon);
				b.setMargin(m);
				canvas.buttonCanvasAdd(b);
			}
			canvas.buttonCanvasCreate();
		}
		public void createCustomIcons() {
			customIcon icon1 = new customIcon();
			int[] x1 = {5,8,1,3,10,13,15,5};
			int[] y1 = {3,6,13,15,8,11,1,3};
			customIconData ciD = new customIconData(x1,y1,Color.black);
			icon1.addData(ciD);
			names.addElement("Select");
			icons.addElement(icon1);
			customIcon icon2 = new customIcon();
			int[] x2 = {1,12,15,5,1};
			int[] y2 = {12,1,4,15,12};
			ciD = new customIconData(x2,y2,Color.black);
			icon2.addData(ciD);
			names.addElement("Line");
			icons.addElement(icon2);
			customIcon icon3 = new customIcon();
			int[] x3 = {2,14,14,2,2};
			int[] y3 = {2,2,14,14,2};
			ciD = new customIconData(x3,y3,Color.black);
			icon3.addData(ciD);
			int[] x13 = {4,12,12,4,4};
			int[] y13 = {4,4,12,12,4};
			ciD = new customIconData(x13,y13,Color.white);
			icon3.addData(ciD);
			names.addElement("Rectangle");
			icons.addElement(icon3);
			customIcon icon4 = new customIcon();
			int[] x4 = {5,10,10,12,12,13,13,14,14,13,13,12,12,10,10,5,5,3,3,2,2,1,1,2,2,3,3,5};
			int[] y4 = {2,2,3,3,4,4,5,5,10,10,11,11,12,12,13,13,12,12,11,11,10,10,5,5,4,4,3,3};
			ciD = new customIconData(x4,y4,Color.black);
			icon4.addData(ciD);
			int[] x20 = {6,9,9,11,11,13,13,11,11,9,9,6,6,4,4,2,2,4,4,6};
			int[] y20 = {3,3,4,4,5,5,10,10,11,11,12,12,11,11,10,10,5,5,4,4,3};
			ciD = new customIconData(x20,y20,Color.white);
			icon4.addData(ciD);
			names.addElement("Elipse");
			icons.addElement(icon4);
			customIcon icon5 = new customIcon();
			int[] x5 = {2,14,14,2,2};
			int[] y5 = {2,2,14,14,2};
			ciD = new customIconData(x5,y5,Color.black);
			icon5.addData(ciD);
			int[] x10 = {4,7,7,4,4};
			int[] y10 = {3,3,6,6,3};
			ciD = new customIconData(x10,y10,Color.red);
			icon5.addData(ciD);
			int[] x11 = {10,13,13,10};
			int[] y11 = {4,7,7,4};
			ciD = new customIconData(x11,y11,Color.green);
			icon5.addData(ciD);
			int[] x12 = {7,12,12,7,5};
			int[] y12 = {9,9,11,11,9};
			ciD = new customIconData(x12,y12,Color.blue);
			icon5.addData(ciD);
			names.addElement("Image");
			icons.addElement(icon5);
			customIcon icon6 = new customIcon();
			int[] x6 = {1,15,15,10,10,6,6,1,1};
			int[] y6 = {2,2,5,5,14,14,5,5,2};
			ciD = new customIconData(x6,y6,Color.black);
			icon6.addData(ciD);
			names.addElement("Text");
			icons.addElement(icon6);
			customIcon icon7 = new customIcon();
			int[] x7 = {1,15,15,1};
			int[] y7 = {1,1,15,15};
			ciD = new customIconData(x7,y7,Color.black);
			icon7.addData(ciD);
			int[] x14 = {2,14,14,2};
			int[] y14 = {2,2,14,14};
			ciD = new customIconData(x14,y14,Color.white);
			icon7.addData(ciD);
			int[] x15 = {10,13,13,10};
			int[] y15 = {3,3,6,6};
			ciD = new customIconData(x15,y15,Color.black);
			icon7.addData(ciD);
			int[] x16 = {3,9,9,3};
			int[] y16 = {6,6,7,7};
			ciD = new customIconData(x16,y16,Color.black);
			icon7.addData(ciD);
			int[] x17 = {3,11,11,3};
			int[] y17 = {8,8,9,9};
			ciD = new customIconData(x17,y17,Color.black);
			int[] x18 = {3,9,9,3};
			int[] y18 = {10,10,11,11};
			ciD = new customIconData(x18,y18,Color.black);
			icon7.addData(ciD);
			int[] x19 = {3,8,8,3};
			int[] y19 = {12,12,13,13};
			ciD = new customIconData(x19,y19,Color.black);
			icon7.addData(ciD);
			icon7.addData(ciD);
			names.addElement("VirtualMsg");
			icons.addElement(icon7);
			customIcon icon8 = new customIcon();
			int[] x8 = {1,4,4,1,1};
			int[] y8 = {1,1,4,4,1};
			ciD = new customIconData(x8,y8,Color.black);
			icon8.addData(ciD);
			int[] x21 = {7,10,10,7,7};
			int[] y21 = {1,1,4,4,1};
			ciD = new customIconData(x21,y21,Color.black);
			icon8.addData(ciD);
			int[] x22 = {13,16,16,13,13};
			int[] y22 = {1,1,4,4,1};
			ciD = new customIconData(x22,y22,Color.black);
			icon8.addData(ciD);
			int[] x23 = {1,4,4,1,1};
			int[] y23 = {7,7,10,10,7};
			ciD = new customIconData(x23,y23,Color.black);
			icon8.addData(ciD);
			int[] x24 = {7,10,10,7,7};
			int[] y24 = {7,7,10,10,7};
			ciD = new customIconData(x24,y24,Color.black);
			icon8.addData(ciD);
			int[] x25 = {13,16,16,13,13};
			int[] y25 = {7,7,10,10,7};
			ciD = new customIconData(x25,y25,Color.black);
			icon8.addData(ciD);
			int[] x26 = {1,4,4,1,1};
			int[] y26 = {13,13,16,16,13};
			ciD = new customIconData(x26,y26,Color.black);
			icon8.addData(ciD);
			int[] x27 = {7,10,10,7,7};
			int[] y27 = {13,13,16,16,13};
			ciD = new customIconData(x27,y27,Color.black);
			icon8.addData(ciD);
			int[] x28 = {13,16,16,13,13};
			int[] y28 = {13,13,16,16,13};
			ciD = new customIconData(x28,y28,Color.black);
			icon8.addData(ciD);
			names.addElement("Grid");
			icons.addElement(icon8);
			customIcon icon9 = new customIcon();
			int[] x9 = {2,14,14,2,2};
			int[] y9 = {2,2,14,14,2};
			ciD = new customIconData(x9,y9,Color.black);
			icon9.addData(ciD);
			names.addElement("Fill");
			icons.addElement(icon9);

			customIcon icon10 = new customIcon();
			int[] x29 = {1,14,14,1,1};
			int[] y29 = {1,1,3,3,1};
			ciD = new customIconData(x29,y29,Color.black);
			icon10.addData(ciD);
			int[] x30 = {1,14,14,1,1};
			int[] y30 = {4,4,7,7,4};
			ciD = new customIconData(x30,y30,Color.black);
			icon10.addData(ciD);
			int[] x31 = {1,14,14,1,1};
			int[] y31 = {8,8,12,12,8};
			ciD = new customIconData(x31,y31,Color.black);
			icon10.addData(ciD);
			names.addElement("Width");
			icons.addElement(icon10);
		}
		public void dialogClose() {diControlCanvasHide();}
		public void dialogMoved() {diControlCanvasSave();} 
		public buttonCanvas diControlCanvas() {return canvas;}
		public void diControlCanvasShow() {
			canvas.buttonCanvasShow();
			//canvas.buttonCanvasPanel().loadPosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "dic");
			//canvas.buttonCanvasPanel().addMsgDialogListener(this);
		}
		public void diControlCanvasSave() {
			if (canvas.buttonCanvasPanel() != null) {
				//canvas.buttonCanvasPanel().savePosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "dic");
			}
		}
		public void diControlCanvasHide() {
			if (canvas.buttonCanvasPanel() != null) {
				canvas.buttonCanvasHide();
			}
		}
		protected void finalize() throws Throwable {
			diControlCanvasHide();
			super.finalize();
		}
	}
