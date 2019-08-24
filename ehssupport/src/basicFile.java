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

public class basicFile {
	protected String ehsDir,basicFile1;
	int nextFileLine = 0;
	boolean fileRead = false;
	Vector fileLines = new Vector();
	Vector files = new Vector();
	Vector filetypes = new Vector();

	public basicFile() {
		this("","");
	}
	public void setPathFilename (String pathfilename) {
		ehsDir = supportFunctions.getPath(pathfilename);
		basicFile1 = supportFunctions.getFilename(pathfilename);
	}
	public basicFile(String pathfilename) {
		this(supportFunctions.getPath(pathfilename),supportFunctions.getFilename(pathfilename));
	}
	public basicFile(String dir,String file) {
		basicFile1 = file;
		ehsDir = dir;
		resetReadFlag();
	}
	public int maxLineLengthInChars() {
		int maxlen = 0;
		for (int i=0;i<fileLines.size();i++) {
			String tmp = (String)fileLines.elementAt(i);
			if (tmp.length() > maxlen) {maxlen = tmp.length();}
		}
		return maxlen;
	}
	public int find(String findString,int startLine) {
		if (startLine >= 0 && startLine < fileLines.size()) {
			for (int i=startLine;i<fileLines.size();i++) {
				String tmp = (String)fileLines.elementAt(i);
				if (tmp.indexOf(findString) != -1) {return i;}
			}
		}

		return -1;
	}
	public int replace(String findString,String replaceString,int startLine) {
		if (startLine > 0 && startLine < fileLines.size()) {
			int foundLine = find(findString,startLine);
			if (foundLine == -1) {return -1;}
			String tmp = (String)fileLines.elementAt(foundLine);
			tmp = tmp.replaceAll(findString,replaceString);
			fileLines.setElementAt(tmp,foundLine);
			return foundLine;
		}

		return -1;
	}
	public void resetReadFlag() {
		fileRead = false;
		nextFileLine = 0;
		fileLines.removeAllElements();
	}
	public String readFileLine(int line) {
		if (!fileRead) {
			resetReadFlag();
			String tmp = loadFile();
			fileLines = supportFunctions.splitIntoTokens(tmp,"\n");
			fileRead =  true;
		}
		if (line < fileLines.size()) {
			nextFileLine = line + 1;	// so readFileLine() will read from the next file line
			return (String)fileLines.elementAt(line);
		} 

		return (String)null;
	}
	public String readFileLine() {
		String tmp = readFileLine(nextFileLine);
		//nextFileLine++; // done in  readFileLine(...)
		return tmp;
	}
	public void setCurrentLineNum(int lineNum) {
		if (!fileRead) {return;}
		if (lineNum < 0 || lineNum >= fileLines.size()) {return;}
		nextFileLine = lineNum;
	}
	public int getCurrentLineNum() {
		return nextFileLine - 1; // as points to next file line to be read
	}
	public String getFileLine(int lineNum) {
		if (!fileRead) {return (String)null;}
		if (lineNum < 0 || lineNum >= fileLines.size()) {return (String)null;}

		return (String)fileLines.elementAt(lineNum);
	}
	public void setFileLine(int lineNum,String line) {
		if (!fileRead) {return;}
		if (lineNum < 0 || lineNum >= fileLines.size()) {return;}

		fileLines.setElementAt(line,lineNum);
	}
	public String[] readFileLines(int startLine,int endLine) {
		if (startLine < 0 || endLine >= fileLines.size()) {return (String[])null;}
		String[] lines = new String[(endLine - startLine)];
		Vector v = new Vector();
		for (int i=startLine;i<=endLine;i++) {
			v.addElement((String)fileLines.elementAt(i));
		}
		v.copyInto(lines);
		
		return lines;
	}
	public int numberFileLines() {
		return fileLines.size();
	}
	public void setdirectory(String tmp) {
		ehsDir = tmp;
	}
	public void deleteFile() {
		(new File(ehsDir+"/"+basicFile1)).delete();
	}
	public void chmod(String data) {
		return;
	}
	public void mkdir(String newdir) {
		(new File(newdir)).mkdir();
		return;
	}
	public String getcwd() {
		return "";
	}
	public Vector getFiles() {return files;}
	public Vector getFileTypes() {return filetypes;}
	public String listFiles() {
		files.removeAllElements();
		filetypes.removeAllElements();
		String tmp = "";
		File dir = new File(ehsDir);
		String[] files1 = dir.list();
		for (int i=0;i<files1.length;i++) {
			File f = new File(dir,files1[i]);
			files.addElement(files1[i]);
			tmp = tmp + "," + files1[i];
			if (f.isDirectory()) {
				filetypes.addElement("dir");
				tmp = tmp + ",dir";
			} else {
				filetypes.addElement("file");
				tmp = tmp + ",file";
			}
		}
		return tmp;
	}
	public String loadFile() {
		return supportFunctions.readTextFile(ehsDir + "/" + basicFile1,true);
	}
	public void saveFile(String data) {
	}
	public void appendFile(String data) {
	}
}
