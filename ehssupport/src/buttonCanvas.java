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

public class buttonCanvas  extends printableCanvas implements ActionListener,MouseListener {
		private JPanel buttonPanel;
		private	int buttonCols;
		private Vector buttons = new Vector();
		private String buttonCanvasName;
		private	buttonCanvasUtils target;
		private panelDialog buttonCanvasPanel;
		
		public buttonCanvas(String name) {
			this(name,2);
		}
		public buttonCanvas(String name,int cols) {
			target = null;
			buttonCanvasPanel = null;
			buttonCanvasName = name;
			buttonCols = cols;
		}
		public void buttonCanvasClear() {
			buttons.removeAllElements();
		}
		public panelDialog buttonCanvasPanel() {
			return buttonCanvasPanel;
		}
		public void buttonCanvasAdd(JCustomButton b) {
			buttons.add(b);
		}
		public void buttonCanvasShow() {
			if (buttonCanvasPanel == null) {buttonCanvasPanel = supportFunctions.displayPanelDialog(null,buttonPanel,buttonCanvasName);}
		}
		public void buttonCanvasHide() {
			if (buttonCanvasPanel != null) {
				buttonCanvasPanel.destory();
				buttonCanvasPanel.dispose();
				buttonCanvasPanel = null;
			}
		}
		public void buttonCanvasCreate() {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(0,buttonCols));
			for(int i=0;i<buttons.size();i++) {
				JCustomButton b = (JCustomButton)buttons.elementAt(i);
				b.addActionListener(this);
				b.addMouseListener(this);
				buttonPanel.add(b);
			}
		}
		public int buttonCanvasNumButtons() {return buttons.size();}
		public JCustomButton buttonCanvasButton(int index) {return (JCustomButton)buttons.elementAt(index);}
		public void actionPerformed(ActionEvent evt) {
			if (target != null) {target.buttonPressed(evt);}
		}
		public void addButtonCanvasListener(buttonCanvasUtils bcu) {target = bcu;}
		public void removeButtonCanvasListener() {target = null;}
		public String buttonCanvasName() {return buttonCanvasName;}
		public void buttonCanvasSize(int width,int hieght) {
			buttonPanel.setSize(width,hieght);
		}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if (target != null) {target.buttonDoubleClicked(evt);}
				}
		}
		public void mousePressed(MouseEvent evt) {
			  int button = evt.getModifiers();
			  if (evt.getClickCount() == 2) {return;}
			  if ((button & MouseEvent.BUTTON3_MASK) != 0) {
				  if (target != null) {target.buttonRightClicked(evt);}
			  } else {
				  if (target != null) {target.buttonLeftClicked(evt);}
			  }
		}
		public void mouseReleased(MouseEvent evt) {} 
	}
