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

public class textBlock extends Component {
		private	AttributedString	text;
		private int					startIndex;

		textBlock() {startIndex=0;text = new AttributedString("");}
		textBlock(String s) {startIndex=0;text = new AttributedString(s);}
		textBlock(String s,int i) {startIndex=i;text = new AttributedString(s);}
		void setStartIndex(int i) {startIndex = i;}
		int getStartIndex() {return startIndex;}
		void setText(String s) {text = new AttributedString(s);}
		AttributedString getText() {return text;}
		void drawText(Graphics2D g2d,int x,int y) {
			AttributedCharacterIterator iter = text.getIterator();
			LineMetrics metrics = g2d.getFont().getLineMetrics(iter,0,iter.getEndIndex(),g2d.getFontRenderContext());
			g2d.drawString(iter,x,y);
		}
		void drawJustifiedText(Graphics2D g2d,int x,int y,int w) {
			int MARGIN = 3;
			AttributedCharacterIterator iter = text.getIterator();
			FontRenderContext frc = new FontRenderContext(null,		// use an AffineTransform
														  true,		// use antialiasing
														  true);	// use fractional metrics
			LineBreakMeasurer measurer = new LineBreakMeasurer(iter,frc);
			float wrappingWidth = w - 2*MARGIN; // leave some margin on both sides
			float curY = MARGIN;
			Vector vLayouts = new Vector();
			Vector vPenPositions = new Vector();
			TextLayout layout = null;
			Point2D.Float penPosition = null;
			while (measurer.getPosition() < iter.getEndIndex()) {
				layout = measurer.nextLayout(wrappingWidth);
				curY += (layout.getAscent());
				if (vLayouts.size() > 0) {
					TextLayout previousLine = (TextLayout)vLayouts.elementAt(vLayouts.size() - 1);
					previousLine = previousLine.getJustifiedLayout(wrappingWidth * 2);
					vLayouts.setElementAt(previousLine,vLayouts.size() - 1);
				}
				penPosition = new Point2D.Float(MARGIN,curY);
				vPenPositions.addElement(penPosition);
				vLayouts.addElement(layout);
				curY += layout.getDescent() + layout.getLeading();
			}
			
			for (int i=0;i<vLayouts.size();i++) {
				TextLayout t = (TextLayout)vLayouts.elementAt(i);
				Point2D.Float p = (Point2D.Float)vPenPositions.elementAt(i);
				t.draw(g2d,p.x,p.y);
			}
		}
		public String[] splitIntoLines(String textOrg,FontMetrics fm,int width) {
			String text = textOrg;
			Vector v = new Vector();
			int charsPerLine = width / fm.charWidth('O');
			if (text.length() < charsPerLine) {
				v.addElement(text);
			} else {
				while (text.length() > charsPerLine) {
					int index = text.lastIndexOf(' ',charsPerLine);
					if (index == -1) {index = charsPerLine;}
					v.addElement(text.substring(0,index));
					text = text.substring(index);
				}
				if (text.length() != 0) {v.addElement(text);}
			}
		
			String[] tmp = new String[v.size()];
			v.copyInto(tmp);
			return tmp;
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
		public void boxCenterText(Graphics2D g2d,Color back,String s1,String s2,int x,int y,int w,int h) {
			Font orgFont = g2d.getFont();
			int height,ascent;
			int width1=0,width2=0,x0=0,x1=0,y0=0,y1=0;
			float size = g2d.getFont().getSize();
			size = size + 1;
			do { // reduce the font size until the text fits in the bounding box
				Font newFont = g2d.getFont().deriveFont(size - 1);
				g2d.setFont(newFont);
				FontMetrics fm1 = getFontMetrics(newFont);
				width1 = fm1.stringWidth(s1);
				if (s2.length() != 0) width2 = fm1.stringWidth(s2);
				height = fm1.getHeight();
				ascent = fm1.getAscent();
				size = size - 1;
			}
			while(((width1 > w) || (width2  > w)) && size > 0);
			x0 = x + (w - width1) / 2;
			x1 = x + (w - width2) / 2;
			if (s2.length() == 0) {
				y0 = y + (h - height) / 2 + ascent;
			} else {
				y0 = y + (h - (int)(height * 2.2)) / 2 + ascent;
				y1 = y0 + (int)(height * 1.2);
			}
			g2d.setColor(back);
			g2d.fillRoundRect(x,y,w,h,20,20);
			g2d.setColor(Color.black);
			g2d.drawRoundRect(x,y,w,h,20,20);
			g2d.drawString(s1,x0,y0);
			if (s2.length() != 0) {
				g2d.drawString(s2,x1,y1);
			}
			g2d.setFont(orgFont);
		}
	}
