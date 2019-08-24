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

public class xmlNodeDialog extends JDialog implements ActionListener,TextListener {
		private JButton	butOK;
		private JButton butCancel;
		private	String nodeName,nodeValue,nodeAttrib;
	    private TextField nTFName,nTFValue,nTFAttrib; 
	    private boolean bOK = false;
	    private int charWidth = 10;
	    private int charHeight = 10;
	   
		public xmlNodeDialog(Frame parent,String name,String value,String attrib) {
		   super(parent,"New XML Node",true);
		   
		   nodeName = name;
		   nodeValue = value;
		   nodeAttrib = attrib;
		   
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
			propSheetPanel.setLayout(new BoxLayout(propSheetPanel,BoxLayout.Y_AXIS));
			propSheetPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			propSheetPanel.add(new JLabel("Node Name",JLabel.LEFT));
			nTFName = new TextField("",30);
			nTFName.addTextListener(this);
			nTFName.setText(nodeName);
			propSheetPanel.add(nTFName);			
			propSheetPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			propSheetPanel.add(new JLabel("Node Value (for a text node)",JLabel.LEFT));
			nTFValue = new TextField("",30);
			nTFValue.addTextListener(this);
			nTFValue.setText(nodeValue);
			propSheetPanel.add(nTFValue);			
			propSheetPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			propSheetPanel.add(new JLabel("Node Attributes (key1=value1,key2=value2,...)",JLabel.LEFT));
			nTFAttrib = new TextField("",30);
			nTFAttrib.addTextListener(this);
			nTFAttrib.setText(nodeAttrib);
			propSheetPanel.add(nTFAttrib);			
			add(propSheetPanel,"Center");
			add(butPanel,"South");
		   
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
	   public String getNodeName() {return nodeName;}
	   public String getNodeValue() {return nodeValue;}
	   public String getNodeAttrib() {return nodeAttrib;}
	   public void textValueChanged(TextEvent evt) {
		   if (evt.getSource() == nTFName) {nodeName = nTFName.getText();}
		   if (evt.getSource() == nTFValue) {nodeValue = nTFValue.getText();}
		   if (evt.getSource() == nTFAttrib) {nodeAttrib = nTFAttrib.getText();}
	   }
	   public boolean isOK() {return bOK;}
	   public void actionPerformed(ActionEvent evt) {
		   if (evt.getSource() == butOK) {
			   bOK = true;
			   dispose();
		   }
		   if (evt.getSource() == butCancel) {
			   bOK = false;
			   dispose();
		   }
	   }
	}
