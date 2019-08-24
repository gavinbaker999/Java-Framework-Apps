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

public class searchreplaceDialog extends JDialog implements ActionListener {
		private JButton	butFind,butReplace,butReplaceAll,butCancel;
		private JRadioButton forwardRB,backwardRB,allRB,selectedRB;
		private ButtonGroup directionBG,selectionBG;
		private boolean OKPressed = false;
		private JCheckBox caseSensitiveCB,regExpressionCB;
		private JTextField searchTF,replaceTF;
		
		public searchreplaceDialog(Frame parent) {
			super(parent,"Search and Replace",false);
			
			JPanel searchPanel = new JPanel();
			searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
			searchPanel.add(new JLabel("Search Text:"));
			searchTF = new JTextField("",10);
			searchPanel.add(searchTF);
			
			JPanel replacePanel = new JPanel();
			replacePanel.setLayout(new BoxLayout(replacePanel,BoxLayout.X_AXIS));
			replacePanel.add(new JLabel("Replace Text:"));
			replaceTF = new JTextField("",10);
			replacePanel.add(replaceTF);
			
			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.X_AXIS));			
			forwardRB = new JRadioButton("Forward");
			forwardRB.setActionCommand("Forward");
			forwardRB.addActionListener(this);
			forwardRB.setSelected(true);
			backwardRB = new JRadioButton("Backward");
			backwardRB.setActionCommand("Backward");
			backwardRB.addActionListener(this);
			directionBG = new ButtonGroup();
			directionBG.add(forwardRB);
			directionBG.add(backwardRB);

			JPanel controlPanel1 = new JPanel();
			controlPanel1.setLayout(new BoxLayout(controlPanel1,BoxLayout.X_AXIS));			
			allRB = new JRadioButton("All");
			allRB.setActionCommand("All");
			allRB.addActionListener(this);
			allRB.setSelected(true);
			selectedRB = new JRadioButton("Selected");
			selectedRB.setActionCommand("Selected");
			selectedRB.addActionListener(this);
			selectionBG = new ButtonGroup();
			selectionBG.add(allRB);
			selectionBG.add(selectedRB);
			controlPanel1.add(forwardRB);
			controlPanel1.add(backwardRB);
			controlPanel1.add(allRB);
			controlPanel1.add(selectedRB);
			
			JPanel controlPanel2 = new JPanel();
			controlPanel2.setLayout(new BoxLayout(controlPanel2,BoxLayout.X_AXIS));			
			regExpressionCB = new JCheckBox("Regular Expression");
			controlPanel2.add(regExpressionCB);
			caseSensitiveCB = new JCheckBox("Case Sensitive");
			controlPanel2.add(caseSensitiveCB);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
			butFind = new JButton("Find");
			butReplace = new JButton("Replace");
			butReplaceAll = new JButton("Replace All");
			butCancel = new JButton("Cancel");
			butFind.addActionListener(this);
			butReplace.addActionListener(this);
			butReplaceAll.addActionListener(this);
			butCancel.addActionListener(this);
			buttonPanel.add(butFind);
			buttonPanel.add(butReplace);
			buttonPanel.add(butReplaceAll);
			buttonPanel.add(butCancel);
			
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			p.add(searchPanel);
			p.add(replacePanel);
			p.add(controlPanel);
			p.add(controlPanel1);
			p.add(controlPanel2);
			p.add(buttonPanel);

			setLayout(new BorderLayout());
			add(p,BorderLayout.CENTER);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
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
		   public void actionPerformed(ActionEvent evt) {
			   //evt.getActionCommand() 
			   if (evt.getSource() == butFind) {
				   SRFind(searchTF.getText());
			   }
			   if (evt.getSource() == butReplace) {
				   SRReplace(searchTF.getText(),replaceTF.getText());
			   }
			   if (evt.getSource() == butReplaceAll) {
				   SRReplaceAll(searchTF.getText(),replaceTF.getText());
			   }
			   if (evt.getSource() == butCancel) {
				   OKPressed = false;
				   dispose();
			   }
		   }
		   public void SRFind(String find) {;}
		   public void SRReplace(String find,String replace) {;}
		   public void SRReplaceAll(String find,String replace) {;}
		   public boolean isRegExpression() {return regExpressionCB.isSelected();}
		   public boolean isCaseSensitive() {return caseSensitiveCB.isSelected();}
		   public boolean isOK() {return OKPressed;}
		   public String getSearchText() {return searchTF.getText();}
		   public String getReplaceText() {return replaceTF.getText();}
		   public void setSearchString(String s) {searchTF.setText(s);}
		   public void setReplaceString(String s) {replaceTF.setText(s);}
		   public boolean isAll() {return allRB.isSelected();}
		   public boolean isSelected() {return selectedRB.isSelected();}
		   public boolean isForward() {return forwardRB.isSelected();}
		   public boolean isBackward() {return backwardRB.isSelected();}
	}
