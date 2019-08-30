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

public class fontDialog extends JDialog implements ActionListener,ItemListener {
		private JButton	butOK,butCancel;
		private JCheckBox boldCB,underlineCB,italicCB;
		private Choice fontList,sizeList;
		private boolean OKPressed = false;
		private Font font = null;
		private JLabel labExample;
		private int charWidth = 8;
		private int charHeight = 14;
		   
	   
		public fontDialog(Frame parent) {
		   super(parent,"Font Selection",true);

		   JPanel p = new JPanel();
		   p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
		
		   fontList = new Choice();
		   GraphicsEnvironment graphicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		   String[] fontNames = graphicsEnv.getAvailableFontFamilyNames();
		   for (int i=0;i<fontNames.length;i++) {
			   fontList.addItem(fontNames[i]);
		   }
		   fontList.select(0);
		   fontList.addItemListener(this);
		   int[] fontSizes = {10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,46,52,58,64,72};
		   sizeList = new Choice();
		   for (int i=0;i<fontSizes.length;i++) {
			   sizeList.addItem(String.valueOf(fontSizes[i]));
		   }
		   sizeList.select(0);
		   sizeList.addItemListener(this);
		   boldCB = new JCheckBox("Bold");
		   boldCB.addItemListener(this);
		   underlineCB = new JCheckBox("Underline");
		   underlineCB.addItemListener(this);
		   italicCB = new JCheckBox("Italic");
		   italicCB.addItemListener(this);
		   
		   butOK = new JButton("OK");
		   butCancel = new JButton("Cancel");
		   butOK.addActionListener(this);
		   butCancel.addActionListener(this);
		   p.add(fontList);
		   p.add(sizeList);
		   p.add(boldCB);
		   p.add(underlineCB);
		   p.add(italicCB);
		   p.add(butOK);
		   p.add(butCancel);

		   setLayout(new BorderLayout());
		   add(p,BorderLayout.CENTER);
		   
		   labExample = new JLabel("ABCDEFG abcdefg");
		   labExample.setHorizontalAlignment(JLabel.CENTER);
		   labExample.setPreferredSize(new Dimension(10*charWidth,8*charHeight));
		   add(labExample,BorderLayout.SOUTH);
		   
		   calcFont();
		   
		   addWindowListener(new WindowAdapter() {
   		     public void windowClosing(WindowEvent evt) {
			   font = null;
			   OKPressed = false;
			   dispose();
		     }
		   });
		   pack();
		   setVisible(true);
	   }
	   
	   public void destroy() {
	   		  dispose();
	   }
	   
	   public int getFontSize() {return Integer.parseInt(sizeList.getSelectedItem());}
	   public String getFontName() {return fontList.getSelectedItem();}
	   public boolean isBold() {return boldCB.isSelected();}
	   public boolean isUnderline() {return underlineCB.isSelected();}
	   public boolean isItalic() {return italicCB.isSelected();}
	   public boolean isOK() {return OKPressed;}
	   public Font getFont() {return font;}	   

	   public void setFontSizeList(int[] sizes) {
		   sizeList.removeAll();
		   for (int i=0;i<sizes.length;i++) {
			   sizeList.addItem(String.valueOf(sizes[i]));
		   }
		   sizeList.select(0);
		   calcFont();
	   }
	   public void calcFont() {
		   //TRACE("fontDialog:name:"+getFontName(),4);
		   int fontStyle = Font.PLAIN;
		   if (isBold()) {fontStyle = fontStyle | Font.BOLD;}
		   if (isItalic()) {fontStyle = fontStyle | Font.ITALIC;}
		   font = new Font(getFontName(),fontStyle,getFontSize());
		   if (isUnderline()) {
				font = supportFunctions.getUnderlinedFont(font);
		   }
		   
		   labExample.setFont(font);
		   //labExample.getParent().invalidate();
		   //labExample.getParent().validate();
	   }
	   public void actionPerformed(ActionEvent evt) {
		   if (evt.getSource() == butOK) {
				calcFont();
				OKPressed = true;
				dispose();
		   }
		   if (evt.getSource() == butCancel) {
			   font = null;
			   OKPressed = false;
			   dispose();
		   }
	   }
	   public void itemStateChanged(ItemEvent evt) {
			calcFont();
	   }
	}
