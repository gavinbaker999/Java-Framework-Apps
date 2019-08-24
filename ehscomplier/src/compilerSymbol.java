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

public class compilerSymbol {
	private String name = "";
	private String value = "";
	private String scopeID = "";
	private	String subScope = "";
	 private symType type = symType.NONE;
	private symClass sclass = symClass.NONE;
	private int lowerBound = 0;
	private int upperBound = 0;
	private int lowerRange = 0;
	private int upperRange = 0;
	private int extra = 0;
	private	int code = 0;
	private String subType = "";
	private String stringLowerRange = "";
	private String stringUpperRange = "";
	private boolean bEnum = false;
	private boolean bArray = false;
	private boolean bDynamic = false;	
	private boolean bForwardDeclFlag = false;
	
	public boolean getForwardDecl() {return bForwardDeclFlag;}
	public void setForwardDecl(boolean b) {bForwardDeclFlag = b;}
	public void setDynamic(boolean b) {
		bDynamic = b;
	}
	public boolean isDynamic() {return bDynamic;}
	
	public int getEnumCount() {
		String[] vals = getEnumValues();
		if (vals != null) {return vals.length;}
		
		return 0;
	}
	public int getEnumIndex(String s) {
		String[] vals = getEnumValues();
		for (int i=0;i<vals.length;i++) {
			if (s.equals(vals[i])) {return i;}
		}
		
		return -1;
	}
	public String getEnumValue(int index) {
		String[] vals = getEnumValues();
		if (index >= vals.length) {return "";}
		return vals[index];
	}
	public String[] getEnumValues() {
		if (getEnum() == true) {
			Vector v = supportFunctions.splitIntoTokens(getSymbolValue(),"#");
			String[] values = new String[v.size()];
			v.copyInto(values);
			return values;
		}
		
		return null;
	}
	public String[] getEnumSlice(String start,String end) {
		String[] enumValues = getEnumValues();
		int startIndex = supportFunctions.indexOf(enumValues,start);
		int endIndex = supportFunctions.indexOf(enumValues,end);
		if (startIndex == -1 || endIndex == -1) {
			String[] enumSliceValues = new String[0];
			return enumSliceValues;
		}
		String[] enumSliceValues = new String[Math.abs(endIndex - startIndex)];
		if (endIndex > startIndex) {
			for (int i=startIndex,j=0;i<=endIndex;i++,j++) {
				enumSliceValues[j] = enumValues[i];
			}
		} else {
			for (int i=startIndex,j=0;i>=endIndex;i--,j++) {
				enumSliceValues[j] = enumValues[i];
			}
		}
		
		return enumSliceValues;
	}
	public void setEnum(boolean b) {bEnum = b;}
	public boolean getEnum() {return bEnum;}
	public String getSubType() {return subType;}
	public void setSubType(String s) {subType = s;}
	public compilerSymbol(String scope,String subScope,String name,String value,symType type,symClass sclass) {
		this.scopeID = scope;
		this.subScope = subScope;
		this.name = name;
		this.value = value;
		this.type = type;
		this.sclass = sclass;
	}
	public void setCode(int i) {code = i;}
	public int getCode() {return code;}
	public void setExtra(int i) {extra = i;}
	public int getExtra() {return extra;}
	public void setScope(String s) {scopeID = s;}
	public String getScope() {return scopeID;}
	public void setSubScope(String s) {subScope = s;}
	public String getSubScope() {return subScope;}
	public void setRanges(int low,int high) {setLowerRange(low);setUpperRange(high);}
	public void setUpperRange(int i) {upperRange = i;}
	public int getUpperRange() {return upperRange;}
	public void setLowerRange(int i) {upperRange = i;}
	public int getLowerRange() {return lowerRange;}
	public void setBounds(int low,int high) {setLowerBound(low);setUpperBound(high);}
	public void setUpperBound(int i) {upperBound = i;}
	public int getUpperBound() {return upperBound;}
	public void setLowerBound(int i) {upperBound = i;}
	public int getLowerBound() {return lowerBound;}
	public void setStringUpperRange(String s) {stringUpperRange = s;}
	public String getStringUpperRange() {return stringUpperRange;}
	public void setStringLowerRange(String s) {stringLowerRange = s;}
	public String getStringLowerRange() {return stringLowerRange;}
	public void setSymbolValue(String s) {value=s;}
	public String getSymbolValue() {return value;}
	public String getSymbolName() {return name;}
	public symType getSymbolType() {return type;}
	public symClass getSymbolClass() {return sclass;}
	public void setSymbolClass(symClass s) {sclass=s;}
	public void setSymbolType(symType s) {type=s;}
	public boolean isConstant() {if (type == symType.CONSTANT) {return true;} else {return false;}}
	public int getHighestBound() {if (getUpperBound() > getLowerBound()) {return getUpperBound();} else {return getLowerBound();}}
	public int getLowestBound() {if (getUpperBound() < getLowerBound()) {return getUpperBound();} else {return getLowerBound();}}
	public boolean isArray() {return bArray;}
	public void setArray(boolean b) {bArray = b;}
	public boolean hasRange() {if (getUpperRange() != getLowerRange()) {return true;} else {return false;}}
	public int size() {return length();}
	public int length() {return Math.abs(getUpperBound() - getLowerBound());}
	public boolean isAscending() {
		if ((getUpperBound() > getLowerBound()) || (getUpperRange() > getLowerRange())) {return true;}
		return false;
	}
	public String dumpSymbol() {
		String extra = ",Enum:"+supportFunctions.valueOf(bEnum)+",Array:"+supportFunctions.valueOf(bArray)+",Dynamic:"+supportFunctions.valueOf(bDynamic);
		if (isArray()) {extra = " (Array low:"+String.valueOf(getLowerBound())+" High:"+String.valueOf(getUpperBound())+")";}
		if (hasRange()) {extra = extra + " (Range low:"+String.valueOf(getLowerRange())+" High:"+String.valueOf(getUpperRange())+")";}
		return "Entity:" + getScope() + ",SubEntity:" + getSubScope() + ",Name:" + getSymbolName() + ",Value:" + getSymbolValue() + ",Type:" + getSymbolType().getDescription() + ",Class:" + getSymbolClass().getDescription() + extra;			
	}
}
