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

public class compiler {
		private boolean varPreDefined,multilineComment;
		protected String syntaxError,commentString,statmentTerminator;
		protected String startMultiLineCommentString,endMultiLineCommentString;
		protected String transTableName;
		protected translationTable transTable;
		private modelessStatusDialog 	msgD;
		private int passNumber;
		private int lineNumber;
		public compilerSymbolTable symbolTable;
		private int errorLineNumber;
		private String exErrorMsg;
		protected String	moduleName;
		protected int 		indentionCount;
		protected Vector	extraCompileFiles = new Vector();
		public Vector extraLines = new Vector();
		protected boolean	bProcessHeaders = false;
		protected boolean	bCompileLine = false;	
		public basicFile f = null;
		public int tmpNameID = 0;
		public Properties placeHolders = new Properties();
		private int numBlankLines,numCommentLines;
		private String sourceFileLine = "";
		
		public void updateSourceFileLine(String s) {
			sourceFileLine = s;
		}
			
		public compiler(String transTableName,String symbolTableName) {
			symbolTable = new compilerSymbolTable(symbolTableName);
			
			// new tokenizer and set reserved words in dirved class
			this.transTableName = transTableName;
			commentString = "////";
			startMultiLineCommentString = "//*";
			endMultiLineCommentString = "*//";
			statmentTerminator = ";";
			varPreDefined = true;
			multilineComment = false;
			msgD = null;
			passNumber = -1;
			lineNumber = -1;
			transTable = null;
		}
		public translationTable getTransTable() {return transTable;}
		public String currentScope() {return "";}
		public String getEntityName() {return "";}
		public String getTmpName(String prefix) {
			return prefix + "_" + "tmpName" + "_" + String.valueOf(tmpNameID++);
		}
		public void writeDirectToOutput(String line) {;}
		public basicFile getCompilerFile() {return f;}
		public compilerSymbolTable getSymbolTable() {return symbolTable;}
		public String getstatmentTerminator() {return statmentTerminator;}
		public void setstatmentTerminator(String s) {statmentTerminator = s;}
		public boolean getCompileLine() {return bCompileLine;}
		public void setCompileLine(boolean b) {bCompileLine = b;}
		public void setPassNumber(int i) {passNumber = i;}
		public void setLineNumber(int i) {lineNumber = i;}
		public int getPassNumber() {return passNumber;}
		public int getLineNumber() {return lineNumber;}
		public void setVarPreDefined(Boolean b) {varPreDefined = b;}
		public void setCommentString(String s) {commentString = s;}
		public void setStartMultiLineCommentString(String s) {startMultiLineCommentString = s;}
		public void setEndMultiLineCommentString(String s) {endMultiLineCommentString = s;}
		public boolean getVarPreDefined() {return varPreDefined;}
		public String getCommentString() {return commentString;}
		public String getStartMultiLineCommentString() {return startMultiLineCommentString;}
		public String getEndMultiLineCommentString() {return endMultiLineCommentString;}
		public int getErrorLineNumber() {return errorLineNumber;}
		public void setErrorLineNumber(int i) {errorLineNumber = i;}
		public String getExErrorMsg() {return exErrorMsg;}
		public void setExErrorMsg(String s) {exErrorMsg = s;}
		public String getErrorString() {return syntaxError;}
		public String stripComments(String s) {
			if (s == null) {return (String)null;}
			s = s.trim();
			int ci = s.indexOf(getStartMultiLineCommentString());
			if (ci != -1) {
				multilineComment = true;
				numCommentLines++;
				if (s.indexOf(getEndMultiLineCommentString()) != -1) {
					multilineComment = false;
				}
				s = s.substring(0,ci);
			}
			ci = s.indexOf(getEndMultiLineCommentString());
			if (ci != -1) {
				multilineComment = false;
				numCommentLines++;
				if (ci == s.length() - getEndMultiLineCommentString().length()) {
					s = "";
				} else {
					s.substring(ci + getEndMultiLineCommentString().length());
				}
			}
			if (multilineComment) {return "";}
			ci = s.indexOf(getCommentString());
			if (ci != -1) {
				ehsRegExp r = new ehsRegExp();
				if (r.regExpMatch(s,"\\[(.*)\\]")) {
					String[] groups = r.getFoundGroupsArray();
					processingInstructions(supportFunctions.splitIntoTokens(groups[0],","));
					return "";
				}
				numCommentLines++;
				s = s.substring(0,ci);
			}
			
			return s;
		}
		public String stripComments1(String s) {
			int commentIndex = s.indexOf(getCommentString());
			if (commentIndex != -1) {
				ehsRegExp r = new ehsRegExp();
				if (r.regExpMatch(s,"\\[(.*)\\]")) {
					String[] groups = r.getFoundGroupsArray();
					processingInstructions(supportFunctions.splitIntoTokens(groups[0],","));
					return "";
				}
				numCommentLines++;
				if (commentIndex == 0) {s = "";} else {s = s.substring(0,commentIndex);}
				s = s.trim();
			}
			return s;
		}
		public void processingInstructions(Vector instructions) {
			
		}
		public void setErrorString(String line,String token) {
			if (line.length() != 0) {
				syntaxError = "Syntax Error: " + line + " near " + token;
			} else {
				syntaxError = "";
			}
			String tmp = getExErrorMsg();
			if (tmp.length() != 0) {
				syntaxError = syntaxError + "\n" + tmp;
			}
			setErrorLineNumber(getLineNumber()+1);
		}
		public boolean compile(String filename) {
			return compile(filename,true);
		}
		public int getNumBlankLines() {return numBlankLines;}
		public int getNumCommentLines() {return numCommentLines;}
		public void removeAllExtraLines() {extraLines.removeAllElements();}
		public void addExtraLine(String line) {
			//TRACE("addExtraLine:"+line,4);
			extraLines.addElement(line);
		}
		public boolean completeLine(String line) {return true;}
		public boolean compile(String filename,boolean bHeaders) {
			//supportFunctions.displayMessageDialog(null,filename);
			multilineComment = false;
			extraLines.removeAllElements();
			moduleName = supportFunctions.getFilenameNoExt(filename);
			msgD = supportFunctions.displayModelessStatusDialog("Compile Status");
			setCompilerStatusMsg("Initalizing");
			bProcessHeaders = bHeaders;
			if (!preCompile(filename,bHeaders)) {return false;}
			syntaxError = "";
			exErrorMsg = "";
			errorLineNumber = 0;
			numBlankLines = 0;
			numCommentLines = 0;
			if (!supportFunctions.urlExists(filename)) {
				setExErrorMsg("Source file not found: " + filename);
				setErrorString("","");
				msgD.destory();
				msgD.dispose();
				msgD = null;
				return false;
			}
			f = new basicFile(filename);
			for (int pass=1;pass<3;pass++) {
			if (pass == 2) {
				if (!betweenPassProcesses()) {
					msgD.destory();
					msgD.dispose();
					msgD = null;
					setErrorString("","");
					return false;
				}
			}
			setPassNumber(pass);
			int line = 0;
			f.resetReadFlag();
			do {
				if (extraLines.size() == 0) {
					sourceFileLine = f.readFileLine();
				} else {
					sourceFileLine = (String)extraLines.elementAt(0);
					extraLines.removeElementAt(0);
					//TRACE("compile:extralines:"+sourceFileLine,4);
					//supportFunctions.displayMessageDialog(null,"compiler:" + sourceFileLine);
				}
				
				sourceFileLine = stripComments(sourceFileLine);
				
				if (sourceFileLine != null && sourceFileLine.length() == 0) {numBlankLines++;}
				if (sourceFileLine != null && sourceFileLine.length() != 0) {
					preCompleteLine(sourceFileLine);
//					supportFunctions.displayMessageDialog(null,"Line Read:"+sourceFileLine);
					do {
						if (completeLine(sourceFileLine)) {break;}
						String tmp1 = f.readFileLine();
						if ( tmp1 == null ) {break;}
						sourceFileLine = sourceFileLine + " " + tmp1;
						sourceFileLine = sourceFileLine.replaceAll("\\n","");
					} while (true);
					postCompleteLine(sourceFileLine);
					//TRACE("Complete Line:"+sourceFileLine,4);
					setLineNumber(line++);
					setCompilerStatusMsg("Compiling Pass " + String.valueOf(getPassNumber()) + " Line " + String.valueOf(getLineNumber()+1) + " (" + moduleName + ")");
					if (getPassNumber() == 1) {firstPassLine(getLineNumber(),sourceFileLine);}
					if (sourceFileLine.charAt(0) == '&') {
						writeDirectToOutput(sourceFileLine.substring(1));
					} else {
						if (!syntaxCheck(sourceFileLine)) {
							msgD.destory();
							msgD.dispose();
							msgD = null;
							return false;
						}
					}
				}
			} while (sourceFileLine != null);
			}
			msgD.destory();
			msgD.dispose();
			msgD = null;
			symbolTable.unresolvedForwardDecls();
			return postCompile(filename,bHeaders);
		}
		public void postCompleteLine(String line) {}
		public void preCompleteLine(String line) {}
		public void firstPassLine(int lineNumber,String line) {;}
		public boolean betweenPassProcesses() {
			return true;
		}
		public void setCompilerStatusMsg(String msg) {
			if (msgD != null) {msgD.setText(msg);}
		}
		public int processLine(String line,String keyword,Vector tokens) {
			int retStatus = 0;
			Vector tmp = transTable.getEntries();
			for (int i=0;i<tmp.size();i++) {
				translationTableEntry entry = (translationTableEntry)tmp.elementAt(i);
				if (keyword.toLowerCase().equals(entry.getKeyword())) {
					retStatus = processTokens(line,keyword,tokens,entry);
					if (retStatus == -1) {return retStatus;}
				}
			}
			return retStatus; // for failure 0 or greater - token index
		}
		public boolean isValidID(String ident) {
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(ident,"^[a-zA-Z0-9][\\w]*$");
			String[] tokens;
			tokens = p.getFoundStringsArray();
			if (tokens.length != 0) {
				return true;
			}
			return false;
		}
		// the following ten functions need to be overridden in dervied class
		public String getKeywordFromLine(Vector tokens,String line) {
			return "";
		}
		public String compileLine(String line) {
			return line;
		}
		public boolean syntaxCheck(String line) {
			return false;
		}
		public boolean isValidIdentifier(String ident) {
			return isValidID(ident);
		}
		public boolean syntaxCheckInternal(String line,Vector tokens) {
			supportFunctions.displayMessageDialog(null,"syntaxCheckInternal - " + line);
			return true;
		}
		public boolean preCompile(String filename,boolean bHeaders) {
			if (bHeaders) {
				transTable = new translationTable();
				transTable.loadTranslationTable(transTableName);
			}
			return true;
		}
		public boolean postCompile(String filename,boolean bHeaders) {return true;}
		public String getName() {return "Undefined";}
		public String getFileExt() {return "xxx";}
		public int processTokens(String line,String keyword,Vector lineTokens,translationTableEntry entry) {
			return -1; // for success
		}
	}
