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

public class controlsFunctions {
	   public static Vector fileIncludes(Vector files,String filePattern) {
		   Vector v = new Vector();

		   String filePatternRegExp = "";
		   for (int i=0;i<filePattern.length();i++) {
			   char ch = filePattern.charAt(i);
			   switch (ch) {
			   	case '.':
				   filePatternRegExp = filePatternRegExp + "\\.";
				   break;
			   	case '*':
				   filePatternRegExp = filePatternRegExp + ".*";
				   break;
			   	case '?':
				   filePatternRegExp = filePatternRegExp + ".";
				   break;
			   	default:
				   char data[] = {ch};
				   filePatternRegExp = filePatternRegExp + new String(data);
				   break;
			   }
		   }
		   filePatternRegExp = "^" + filePatternRegExp + "$";
//		   supportFunctions.displayMessageDialog(null,"File pattern regfexp : "+ filePatternRegExp);
		   
		   v.addElement(new String("")); // ignore . directory
		   for (int i=1;i<files.size();i++) {
			   String tmp = (String)files.elementAt(i);
			   if (tmp.matches(filePatternRegExp)) {
				   v.addElement(new String("*"));
			   } else {
				   v.addElement(new String(""));
			   }
		   }
		   return v;
	   }
	public static String fileOpenDialog(String startDirectory,String filePattern) {
		FileDialog d = new FileDialog((Frame)null,"File Open",FileDialog.LOAD);
		d.setFile(filePattern);
		d.setDirectory(startDirectory);
		d.show();
		String filename = d.getFile();
		if (filename == null) {filename = "";return null;}
		return d.getDirectory() + d.getFile();
	}
	public  static String fileSaveDialog(String startDirectory,String filePattern) {
		FileDialog d = new FileDialog((Frame)null,"File Save",FileDialog.SAVE);
		d.setFile(filePattern);
		d.setDirectory(startDirectory);
		d.show();
		String filename = d.getFile();
		if (filename == null) {filename = "";}
		return d.getDirectory() + d.getFile();
	}	
	public static fontDialog fontCommonDialog() {
		fontDialog dlg = new fontDialog(null);
		if (dlg.isOK()) {
			return dlg;
		} 
		return (fontDialog)null;
	}
	public static hdlSearchReplaceDialog searchreplaceCommonDialog() {
		hdlSearchReplaceDialog dlg = new hdlSearchReplaceDialog(null);
		if (dlg.isOK()) {
			return dlg;
		} 
		return (hdlSearchReplaceDialog)null;
	}  
}
