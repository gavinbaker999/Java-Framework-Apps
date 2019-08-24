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

public class expressionBuilder extends JDialog implements ActionListener {
	private int charWidth,charHeight;
	private FontMetrics fm;
	private Color lightyellow = new Color(255,255,170);
	private Frame parentFrame;
	
	private JButton	butOK,butCancel,butUndo,butClear;
	private List validTokenList;
	private JTextField exprText;
    private boolean bOK = false;
    private String expression = "";
    private Map<String,String> map = new HashMap<String,String>();
    private ArrayList<String> tokens = new ArrayList<String>();
    
    public expressionBuilder(Frame parent,String title,String filename,Map<String,String> extraTokens) {
    	super(parent,title,true);

    	parentFrame = parent;
    	init(filename,extraTokens);
    }
    public expressionBuilder(Frame parent,String title,String filename) {
    	super(parent,title,true);			   

    	parentFrame = parent;
    	init(filename,null);
    }

    public void init(String filename,Map<String,String> extraTokens) {
		fm = getFontMetrics(getFont());
		charHeight = fm.getHeight(); // + fm.getAscent(); remove GDB 09/07/2014
		charWidth = fm.stringWidth("O");

		JPanel dataPanel1 = new JPanel();
    	dataPanel1.setLayout(new BoxLayout(dataPanel1,BoxLayout.X_AXIS));
    	dataPanel1.add(new JLabel("Expression:"));
    	exprText = new JTextField(30);
    	exprText.setEditable(false);
    	exprText.setBackground(lightyellow);
    	dataPanel1.add(exprText);

    	JPanel dataPanel2 = new JPanel();
    	dataPanel2.setLayout(new BoxLayout(dataPanel2,BoxLayout.X_AXIS));
    	validTokenList = new List(5,false);
    	validTokenList.addActionListener(this);
    	dataPanel2.add(validTokenList);		   
    	dataPanel2.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
    	dataPanel2.add(new JLabel("Double click on a token to add it to the expression."));	
    	dataPanel2.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));

    	JPanel dataPanel = new JPanel();
    	dataPanel.setLayout(new BoxLayout(dataPanel,BoxLayout.Y_AXIS));
    	dataPanel.add(dataPanel1);
    	dataPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
    	dataPanel.add(dataPanel2);

    	JPanel butPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	butOK = new JButton("Ok");
    	butCancel = new JButton("Cancel");
    	butUndo = new JButton("Undo");
    	butClear = new JButton("Clear");
    	butOK.addActionListener(this);
    	butCancel.addActionListener(this);
    	butUndo.addActionListener(this);
    	butClear.addActionListener(this);
    	butOK.setMinimumSize(new Dimension(10*charWidth,charHeight));
    	butCancel.setMinimumSize(new Dimension(10*charWidth,charHeight));
    	butUndo.setMinimumSize(new Dimension(10*charWidth,charHeight));
    	butClear.setMinimumSize(new Dimension(10*charWidth,charHeight));
    	butPanel.add(butUndo);
    	butPanel.add(butClear);
    	butPanel.add(butCancel);
    	butPanel.add(butOK);

    	add(dataPanel,"Center");
    	add(butPanel,"South");

    	if (filename != "") {
    		try {
    			String line;
   				FileInputStream fis = new FileInputStream(filename);
   				BufferedReader bin = new BufferedReader(new InputStreamReader(fis));
   		    	while ((line = bin.readLine()) != null) {
   	    			Vector v = supportFunctions.splitIntoTokens(line,"##",false);
   	    			map.put((String)v.elementAt(0),(String)v.elementAt(1));
   				}
    			bin.close();
    			fis.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    			supportFunctions.displayMessageDialog((Frame)null,"File Not Found:"+filename,"Error Occured");
    		}
    	}
    	
    	if (extraTokens != null) {
    		for (Map.Entry<String,String> entry : extraTokens.entrySet()) {
    			map.put(entry.getKey(),entry.getValue());
    		}
    	}
    	
    	updateValidTokenList();
    	updateExprText();

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
    public String getExpressionAsString() {return expression;}
    public String[] getExpressionAsArray() {return (String[])tokens.toArray();}
    public boolean isOK() {return bOK;}
    public void actionPerformed(ActionEvent evt) {
    	if (evt.getSource() == validTokenList) {
    		String s = validTokenList.getSelectedItem();
    		
    		if (s.equals("{string}")) {
    			String data = supportFunctions.getDataAsDialog(parentFrame,"Data Entry","Data Value","");
    			if (data != null) {
    				s = s + "@@" + data;
    			} else {
    				return;
    			}
    		}
    		if (s.startsWith("{choice:")) {
    			String[] titles = new String[]{"Data"};			
    			String[] defaults = new String[1];
    			defaults[0] = s.substring(8,s.length() - 1);
    			String data = supportFunctions.getDataAsDialog(parentFrame,"Data Choice Entry","Data Value",s.substring(8,s.length() - 1));
    			if (data != null) {
    				s = s + "@@" + data;
    			} else {
    				return;
    			}	    			
    		}
    		
    		tokens.add(s);
    		updateValidTokenList();
    		updateExprText();
    	}
    	if (evt.getSource() == butUndo) {
    		// remove last token
    		if (tokens.size() != 0) {
    			tokens.remove(tokens.size() - 1);
    			updateValidTokenList();
    			updateExprText();
    		}
    	}
    	if (evt.getSource() == butClear) {
    		tokens.clear();
    		updateValidTokenList();
    		updateExprText();
    	}
    	if (evt.getSource() == butOK) {
    		bOK = true;
    		dispose();
    	}
    	if (evt.getSource() == butCancel) {
    		bOK = false;
    		tokens.clear();
    		expression = "";
    		dispose();
    	}
    }
    public void updateExprText() {
    	expression = "";
    	for (int i=0;i<tokens.size();i++) {
    		if (i != 0) {expression = expression + " ";}
    		String s = (String)tokens.get(i);
    		Vector v = supportFunctions.splitIntoTokens(s,"@@",false);
    		expression = expression + (String)v.elementAt(v.size() - 1);
    	}
    	exprText.setText(expression);

    	if (tokens.size() == 0) {butUndo.setEnabled(false);} else {butUndo.setEnabled(true);}
    	if (tokens.size() == 0) {butClear.setEnabled(false);} else {butClear.setEnabled(true);}
    	if (validTokenList.getItemCount() == 0) {butOK.setEnabled(true);} else {butOK.setEnabled(false);}
    }
    public void updateValidTokenList() {
    	validTokenList.removeAll();

    	String lastToken = "****"; // default start token
    	if (tokens.size() != 0) {lastToken = tokens.get(tokens.size() - 1);}
		Vector v = supportFunctions.splitIntoTokens(lastToken,"@@",false);
    	String s = map.get((String)v.elementAt(0));
    	//TRACE("eb2:"+s+":lasttoken:"+lastToken,4);
    	if (s != null) {
    		if (s.equals("****")) {
    			// last token so no entries for valid token list
    		} else {
    			Vector v1 = supportFunctions.splitIntoTokens(s,"::",false);
    			for (int i=0;i<v1.size();i++) {
    				validTokenList.add((String)v1.elementAt(i));
    			}					   
    		}
    	} else {
    		//TRACE("Token " + lastToken + "not found");
    	}
    }
}

