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
