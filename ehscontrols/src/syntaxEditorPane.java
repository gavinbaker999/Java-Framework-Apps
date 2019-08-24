import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
import java.util.Map;
import java.text.*;
import java.lang.reflect.*;
import java.beans.*;
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
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.script.*;
import javax.swing.filechooser.*;
import javax.xml.ws.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;
import javax.xml.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
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
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.html.ParagraphView;
import java.awt.datatransfer.*;

import javax.swing.text.Element;
import javax.swing.text.Document;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

//import sun.misc.Unsafe; 
//import sun.reflect.ReflectionFactory;  

public class syntaxEditorPane extends JTextPane implements DocumentListener,KeyListener,CaretListener,MouseListener,MouseMotionListener {
		private	Vector 			formats = new Vector();
		private	boolean 		applyFormats = true;
		private	HTMLEditorKit 	kit;
		private int				documentPos = 0;
		private	String 			currentWord = "";
		private int				currentRow = 0;
		private int				currentCol = 0;
		protected boolean		bInit = false;
		private	int				maxLineNumber = 0;
		private SEPScrollPane 	sp = null;
		private String			hoverWord = "",lastHoverWord="";
		private boolean 		bAutoComplete = true;
		private boolean			bIntelliSense = true;
		private	floatingWindow	intelliSenseWindow = null,sugsWindow = null;
		
		public void loadAsXML(String filename) {
			removeAllFormats();
			xmlDataFile f = new xmlDataFile();
			f.openXMLDataFile(filename,"syntaxeditorpaneformats");
			NodeList nl = f.buildNodeList("syntaxeditorpaneformat");
			for (int i=0;i<nl.getLength();i++) {
				org.w3c.dom.Node n = nl.item(i);
				String name = n.getNodeName();
				org.w3c.dom.Element e = (org.w3c.dom.Element)n;

				String nodeAttribs = "";
				nodeAttribs = e.getAttribute("name");
				nodeAttribs = nodeAttribs + "," + e.getAttribute("regex");
				nodeAttribs = nodeAttribs + "," + e.getAttribute("starthtml");
				nodeAttribs = nodeAttribs + "," + e.getAttribute("endhtml");
						
				Vector v1 = supportFunctions.splitIntoTokens(nodeAttribs);
				addFormat(new syntaxEditorPaneFormat((String)v1.elementAt(0),(String)v1.elementAt(1),(String)v1.elementAt(2),(String)v1.elementAt(3)));
			}
			f.closeXMLDataFile();
		}
		public void saveAsXML(String filename) {
			xmlDataFile f = new xmlDataFile();
			f.openXMLDataFile(filename,"syntaxeditorpaneformats");
			org.w3c.dom.Element root = f.getRootElement();
			org.w3c.dom.Document doc = f.getXMLDocument();
			for (int i=0;i<formats.size();i++) {
				syntaxEditorPaneFormat format = (syntaxEditorPaneFormat)formats.elementAt(i);
				format.saveAsXML(doc,root);
			}
			f.closeXMLDataFile();
		}
		public void suggestionSelected(String suggestion) {
			SEPSuggestionSelected(suggestion);
			getSugsWindow().destory();
		}
		public void SEPSuggestionSelected(String suggestion) {
			Document doc = getDocument();
			try {
				doc.insertString(getCaretPosition(), suggestion, null);
			} catch (Exception e) {;}
		}
		public boolean getAutoComplete() {return bAutoComplete;}
		public boolean getIntelliSense() {return bIntelliSense;}
		public void setAutoComplete(boolean b) {bAutoComplete = b;}
		public void setIntelliSense(boolean b) {bIntelliSense = b;}
		public String[] SEPAutoCompleteSuggestions(String text) {
			return null;
		}
		public String SEPIntelliSenseString(String s) {return null;}
		public String getHoverWord() {return hoverWord;}
		public void setScrollPane(SEPScrollPane sp) {this.sp = sp;}
		public SEPScrollPane getScrollPane() {return sp;}
		public void gotoLine() {
			String r = supportFunctions.getDataAsDialog(null,"Enter Line Number",
					"Line Number (1-"+String.valueOf(getMaxLineNumber())+"):",
					String.valueOf(getCurrentRow()+1));
			int gotoLine = Integer.parseInt(r);
			if (gotoLine > 0 && gotoLine < getMaxLineNumber()+1) {
				int pos = 1; // 1st documentPos is 1
				gotoLine = gotoLine - 1; // make zero based
				Vector lines = supportFunctions.splitIntoTokens(getRawText(),"\n");
				for(int i=0;i<gotoLine;i++) {
					String line = (String)lines.elementAt(i);
					pos = pos + line.length() + 1;
				}
				setCaretPosition(pos);
			} else {
				supportFunctions.displayMessageDialog(null,"Invalid Line Number");
			}
		}
		public int getMaxLineNumber() {return maxLineNumber;}
		public void setMaxLineNumber(int num) {
			SEPUpdateMaxLineNumber(num);
			maxLineNumber = num;
		}
		public void SEPUpdateMaxLineNumber(int num) {
		}
		
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
				}
		}
		public void mousePressed(MouseEvent evt) {
			  int button = evt.getModifiers();
			  if (evt.getClickCount() == 2) {return;}
			  if ((button & MouseEvent.BUTTON3_MASK) != 0) {
				  SEPRightClickAction(evt);
			  } else {
			  }
		}
		public void mouseReleased(MouseEvent evt) {}
		public floatingWindow getSugsWindow() {return sugsWindow;}
		public void doSuggestions(DocumentEvent e) {
			if (getAutoComplete() == true) {
				String word = getWordAtPos(getRawText(),e.getOffset());
				String[] suggestions = SEPAutoCompleteSuggestions(word);
				if (suggestions != null) {
					if (sugsWindow != null) {sugsWindow.destory();}
					suggestionPanel p = new suggestionPanel(this);
					List sugs = new List(5);
					for (int i=0;i<suggestions.length;i++) {
						sugs.add(suggestions[i]);
					}
					sugs.addItemListener(p);
					p.add(sugs);
					sugs.select(0);
					sugsWindow = new floatingWindow(p,"Suggestions");
				}
			}						
		}
		public void mouseMoved(MouseEvent evt) {
			JTextPane editor = (JTextPane) evt.getSource();
			Document doc = editor.getDocument();
			if (doc instanceof HTMLDocument) {
				HTMLDocument htmlDoc = (HTMLDocument) doc;
			    Point loc = evt.getPoint();
			    int pos = editor.viewToModel(loc);
			    //TRACE("Document Position:"+String.valueOf(pos),4);
				hoverWord = getWordAtPos(getRawText(),pos);
				//TRACE("Document Word:"+hoverWord,4);
			}
			if (getIntelliSense()) {
				String text = SEPIntelliSenseString(hoverWord);
				if (text != null) 
				{
					if (intelliSenseWindow != null) {
						if (!intelliSenseWindow.isValid()) {intelliSenseWindow = null;}
					}
					if (intelliSenseWindow != null && lastHoverWord.equals(hoverWord)) {
					} else {
						if (intelliSenseWindow != null) {
							intelliSenseWindow.destory();
							intelliSenseWindow = null;
						}
						lastHoverWord = hoverWord;
						intelliSenseWindow = new floatingWindow(text,"title",20,5);
					}
				} else {
					// remove any existing msg window
					if (intelliSenseWindow != null) {
							intelliSenseWindow.destory();
							intelliSenseWindow = null;
							lastHoverWord = "";
					}
				}
			}
			SEPHoverAction(evt);
	    }
		public void mouseDragged(MouseEvent evt) {
	    }

		public String getCurrentWord() {return currentWord;}
		public String getWordAtPos(String line,int pos) {
			String word = "";
			if (pos < 0 || pos >= line.length()) {return "";}
			int beginIndex = 0;
			int endIndex = line.length() - 1;
			for (int i=pos;i>-1;i--) {
				if (line.charAt(i) == ' ' || line.charAt(i) == '\n') {
					beginIndex = i + 1;
					break;
				}
			}
			for (int i=pos+1;i<line.length();i++) {
				if (line.charAt(i) == ' ' || line.charAt(i) == '\n') {
					endIndex = i - 1;
					break;
				}
			}
			
			return line.substring(beginIndex, endIndex+1);
		}
		public String getLineText(int lineNumber) {
			Vector lines = supportFunctions.splitIntoTokens(getRawText(),"\n");
			if (lineNumber<0 || lineNumber>lines.size()) {return "";}
			return (String)lines.elementAt(lineNumber);
		}
		public void updateCurrentWord(int row,int col) {
			Vector lines = supportFunctions.splitIntoTokens(getRawText(),"\n");
			setMaxLineNumber(lines.size());
			if (lines.size() == 0) {currentWord="";return;} // no text!!!
			currentWord = getWordAtPos((String)lines.elementAt(row),col);
		}
		public syntaxEditorPane() {
			super();

			setEditable(true);
			setEditorKitForContentType("text/html",new HTMLEditorKit());
			//setEditorKitForContentType("text/html",new syntaxPaneEditorKit());
	        //setEditorKitForContentType("text/html",new CollapsibleEditorKit());
			setContentType("text/html");
			addKeyListener(this);
			addCaretListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			getDocument().addDocumentListener(this);
			
			kit = (HTMLEditorKit)getEditorKit();
			StyleSheet ss = new StyleSheet();
			try {
				URL ssURL = new URL("../classes/common/syntaxeditorpane.css");
				BufferedReader in = new BufferedReader(new InputStreamReader(ssURL.openStream()));
				ss.loadRules(in,null);
				kit.setStyleSheet(ss);
			} catch (Exception e) {;}
			//StyleSheet styles = kit.getStyleSheet();
			//Enumeration rules = styles.getStyleNames();
			//while (rules.hasMoreElements()) {
             //String name = (String) rules.nextElement();
             //Style rule = styles.getStyle(name);
             //supportFunctions.displayMessageDialog(null,rule.toString());
			//}
		}
		public int getRow(int pos, syntaxEditorPane editor) {
			int rn = (pos==0) ? 1 : 0;
			try {
				int offs=pos;
				while( offs>0) {
					offs=Utilities.getRowStart(editor, offs)-1;
					rn++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rn;
		}
		public int getColumn(int pos, syntaxEditorPane editor) {
			try {
				return pos-Utilities.getRowStart(editor, pos)+1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		public void caretUpdate(CaretEvent e) {
			documentPos = e.getDot();
			clickedAt(getRow(e.getDot(), (syntaxEditorPane)e.getSource()),getColumn(e.getDot(), (syntaxEditorPane)e.getSource()));
		}
		public void textUpdated(DocumentEvent e) {
			
		}
		public void textUpdated1(DocumentEvent e) {
			doSuggestions(e);
			Vector lines = supportFunctions.splitIntoTokens(getRawText(),"\n");
			setMaxLineNumber(lines.size());
			textUpdated(e);
		}
		public void insertUpdate(DocumentEvent e) {
			textUpdated1(e);
		}
	    public void removeUpdate(DocumentEvent e) {
			textUpdated1(e);
	    }
	    public void changedUpdate(DocumentEvent e) {
	        //Plain text components do not fire these events
			textUpdated1(e);
	    }
		public void keyTyped(KeyEvent evt) {
			char keyTyped = evt.getKeyChar();
		}	
		public void keyReleased(KeyEvent evt) {
		}	
		public void keyPressed(KeyEvent evt) {
			int mods = evt.getModifiers();
			int keyCode = evt.getKeyCode();
			boolean shiftDown = false;
			boolean ctrlDown = false;
			if ((mods & InputEvent.SHIFT_MASK) != 0) {shiftDown = true;}
			if ((mods & InputEvent.CTRL_MASK) != 0) {ctrlDown = true;}

			if (keyCode == KeyEvent.VK_G && ctrlDown == true) {
				gotoLine();
			}
			if (keyCode == KeyEvent.VK_F && ctrlDown == true) {
				searchReplace();
			}
			if (keyCode == KeyEvent.VK_A && ctrlDown == true) {
				makeCollapsibleArea();
			}
			if (keyCode == KeyEvent.VK_B && ctrlDown == true) {
				clearCollapsibleArea();
			}
			if (keyCode == KeyEvent.VK_H && ctrlDown == true) {
				//setTextNew("",0);
				setText("I Love\nJoide <font color='red'>Foster</font>");
			}
			if (keyCode == KeyEvent.VK_ENTER) {
				try {
					kit.insertHTML((HTMLDocument)getDocument(),documentPos,"<font>&nbsp;<br>&nbsp;</font>",0,0,HTML.Tag.FONT);
				} catch (Exception e) {;}
				evt.consume(); // dispose of VK_ENTER key event
				return;				
			}
		}
		public void searchReplace() { // override in derived class
		}
		public boolean getApplyFormats() {return applyFormats;}
		public void setApplyFormats(boolean b) {applyFormats = b;}
		public String stringRepeat(String s,int count) {
			String ret = "";
			for (int i=0;i<count;i++) {ret = ret + s;}
			return ret;
		}
		public String doFormats(String s) {
			if (!applyFormats) {return s;}
			String searchString = s;
			for (int i=0;i<formats.size();i++) {
				syntaxEditorPaneFormat format = (syntaxEditorPaneFormat)formats.elementAt(i);
				if (format.getEnabled() == true) {
					Pattern p = Pattern.compile(format.getFormatRegEx());
					Matcher m = p.matcher(searchString);
					int offset = 0;
					String tmp = searchString;
					if (m.groupCount() != 1) {
						supportFunctions.displayMessageDialog(null,"More then one capture group");
					}
					while (m.find()) {
						int startPos = m.start(1) + offset;
						int endPos = startPos + m.group(1).length();
						offset = offset + format.getFormatStartHTML().length() +  format.getFormatEndHTML().length();
						s = s.substring(0, startPos) + format.getFormatStartHTML() + m.group(1) + format.getFormatEndHTML() + s.substring(endPos);
						tmp = tmp.substring(0,startPos) + supportFunctions.stringRepeat("#",format.getFormatStartHTML().length()+m.group(1).length()+format.getFormatEndHTML().length())+tmp.substring(endPos);
					}
					searchString = tmp;
				}
			}

			return s;
		}
		public Vector getFormats() {return formats;}
		public void addFormat(syntaxEditorPaneFormat format) {
			formats.addElement(format);
		}
		public void removeFormat(String name) {
			for (int i=0;i<formats.size();i++) {
				syntaxEditorPaneFormat format = (syntaxEditorPaneFormat)formats.elementAt(i);
				if (name.equals(format.getFormatName())) {
					formats.removeElement(format);
					return; // as we now have changed size of vector
				}
			}
		}
		public void removeAllFormats() {
			formats.removeAllElements();
		}
		public String getTextNew() {
			HTMLDocument doc = (HTMLDocument)getDocument();
			int length = doc.getLength();
			String text = "";
			try {
				text = doc.getText(0, length);
			} catch (Exception e) {;}
			
			return text;
		}
		public void setTextNew(String text,int nOffset) {
			HTMLDocument doc = (HTMLDocument)getDocument();
			//Vector lines = supportFunctions.splitIntoTokens(text,"\n");
			//setMaxLineNumber(lines.size());

			//setText(text);

			try {
				 kit.insertHTML(doc, doc.getLength(), "<span><b>hello</span>", 0, 0, HTML.Tag.SPAN);
				 kit.insertHTML(doc, doc.getLength(), "<span><font color='red'><u>world</u></font></span>", 0, 0, HTML.Tag.SPAN);
//				Element[] roots = doc.getRootElements(); // #0 is the HTML element, #1 the bidi-root
//				Element body = null;
//				for( int i = 0; i < roots[0].getElementCount(); i++ ) {
//				    Element element = roots[0].getElement( i );
//				    if( element.getAttributes().getAttribute( StyleConstants.NameAttribute ) == HTML.Tag.BODY ) {
//				        body = element;
//				        break;
//				    }
//				}
//				doc.insertAfterStart( body, text );

				//kit.insertHTML((HTMLDocument)getDocument(),nOffset,text,0,0,HTML.Tag.BR);
				//doc.insertString(nOffset, text, new SimpleAttributeSet());
			} catch (Exception e) {;}
		}
		public String getRawText() {
			//String text = getTextNew();
			
			String text = getText();
			text = text.replace("\n", "");
			text = text.replace("<br>","\n");
			text = stripHTMLStrutureTags(text);
			text = stripHTMLTags(text);
			text = text.replaceAll("\\s{2,}"," ");
			text = text.trim();			
			return text;
		}
		public void setRawText(String s) {
			Vector lines = supportFunctions.splitIntoTokens(s,"\n");
			setMaxLineNumber(lines.size());

			s = s.replaceAll("<","&lt"); 			
			s = s.replaceAll(">","&gt"); 			
			s = s.replaceAll("\\n","<br>\n"); // as we loading text into an HTML control
			
			s = doFormats(s);
			setText(s);
		}
		public String stripHTMLStrutureTags(String s) {
			s = s.replaceAll("<html>","");
			s = s.replaceAll("</html>","");
			s = s.replaceAll("<head>","");
			s = s.replaceAll("</head>","");
			s = s.replaceAll("<body>","");
			s = s.replaceAll("</body>","");
			return s;
		}
		public String stripHTMLTags(String s) {
			s = s.replaceAll("<p.*?>","");
			s = s.replaceAll("</p>","");
			s = s.replaceAll("<font.*?>","");
			s = s.replaceAll("</font>","");
			s = s.replaceAll("<b>","");
			s = s.replaceAll("</b>","");
			s = s.replaceAll("<i>","");
			s = s.replaceAll("</i>","");
			s = s.replaceAll("&quot;","\"");
			s = s.replaceAll("&lt;","<");
			s = s.replaceAll("&gt;",">");
			return s;
		}
		public void clickedAt(int row,int col) {
			if (bInit == true) {return;} 
			currentRow = row-1;
			currentCol = col-1;
			updateCurrentWord(row-1,col-1);
			supportFunctions.displayMessageDialog(null,getCurrentWord());
			//supportFunctions.displayMessageDialog(null,"Clicked on row " + String.valueOf(row-1) + " Column " + String.valueOf(col-1));		
			//supportFunctions.displayMessageDialog(null,"documentPos:"+String.valueOf(documentPos));
			SEPClickedAt(row-1,col-1);
		}
		public int getDocumentPos() {return documentPos;}
		public int getCurrentRow() {return currentRow;}
		public int getCurrentCol() {return currentCol;}
		public void SEPHoverAction(MouseEvent evt){
			
		}
		public void SEPRightClickAction(MouseEvent evt){
			
		}
		public void SEPClickedAt(int row,int col){
		}
		protected CollapsibleView getCollapsibleView(int offset) {
	        View v=getUI().getRootView(this);
	        while( v!=null && v.getViewCount()>0) {
	            if (v instanceof CollapsibleView) {
	                return (CollapsibleView)v;
	            }
	            int i=v.getViewIndex(offset, Position.Bias.Forward);
	            if (i>=0 && i<v.getViewCount()) {
	                v=v.getView(i);
	            }
	            else {
	                break;
	            }
	        }

	        return null;
	    }
		protected void insertSpecs(DefaultStyledDocument doc, int offset, DefaultStyledDocument.ElementSpec[] specs) {
	        try {
//	            doc.insert(0, specs);  method is protected so we have to
	            //extend document or use such a hack
	            Method m=DefaultStyledDocument.class.getDeclaredMethod("insert", new Class[] {int.class, DefaultStyledDocument.ElementSpec[].class});
	            m.setAccessible(true);
	            m.invoke(doc, new Object[] {offset, specs});
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		protected void makeCollapsibleArea() {
	        try {
	            int start=getSelectionStart();
	            int end=getSelectionEnd();
	            if (start==end) {
	                return;
	            }
	            clearCollapsibleArea();
	            if (start>end) {
	                int tmp=start;
	                start=end;
	                end=tmp;
	            }

	            DefaultStyledDocument doc=(DefaultStyledDocument)getDocument();
	            start=(doc).getParagraphElement(start).getStartOffset();
	            end=doc.getParagraphElement(end).getEndOffset();

	            ArrayList<DefaultStyledDocument.ElementSpec> specs=new ArrayList<DefaultStyledDocument.ElementSpec>();
	            DefaultStyledDocument.ElementSpec spec;
	            int offs=start;
	            while (offs<end) {
	                Element par=doc.getParagraphElement(offs);

	                spec=new DefaultStyledDocument.ElementSpec(par.getAttributes(), DefaultStyledDocument.ElementSpec.StartTagType);
	                specs.add(spec);
	                for (int i=0; i<par.getElementCount(); i++) {
	                    Element leaf=par.getElement(i);
	                    String text=doc.getText(leaf.getStartOffset(), leaf.getEndOffset()-leaf.getStartOffset());
	                    spec=new DefaultStyledDocument.ElementSpec(leaf.getAttributes(),DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length());
	                    specs.add(spec);
	                }
	                spec=new DefaultStyledDocument.ElementSpec(par.getAttributes(), DefaultStyledDocument.ElementSpec.EndTagType);
	                specs.add(spec);

	                offs=par.getEndOffset();
	            }

	            doc.remove(start, end-start);
	            DefaultStyledDocument.ElementSpec[] specArray = new DefaultStyledDocument.ElementSpec[specs.size()+4];
	            DefaultStyledDocument.ElementSpec closePar = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
	            specArray[0]=closePar;

	            SimpleAttributeSet attrs=new SimpleAttributeSet();
	            attrs.addAttribute(DefaultStyledDocument.ElementNameAttribute, ehsConstants.COLLAPSIBLE_AREA_ELEMENT);
	            DefaultStyledDocument.ElementSpec areaStart = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
	            specArray[1]=areaStart;

	            for (int i=0; i<specs.size(); i++) {
	                specArray[i+2]=specs.get(i);
	            }

	            DefaultStyledDocument.ElementSpec areaEnd = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.EndTagType);
	            specArray[specArray.length-2]=areaEnd;

	            DefaultStyledDocument.ElementSpec openPar = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
	            specArray[specArray.length-1]=openPar;

	            insertSpecs(doc, start, specArray);
	        } catch (BadLocationException e) {
	            e.printStackTrace();  
	        }
	    }    
	    protected void clearCollapsibleArea() {
	        try {
	            DefaultStyledDocument doc=(DefaultStyledDocument)getDocument();
	            Element root=doc.getDefaultRootElement(); 
	            for (int i=0; i<root.getElementCount(); i++) {
	                Element elem=root.getElement(i);
	                if (ehsConstants.COLLAPSIBLE_AREA_ELEMENT.equals(elem.getName())) {
	                    ArrayList<DefaultStyledDocument.ElementSpec> specs=new ArrayList<DefaultStyledDocument.ElementSpec>();
	                    DefaultStyledDocument.ElementSpec spec;
	                    for (int j=0; j<elem.getElementCount(); j++) {
	                        Element par=elem.getElement(j);
	                        spec=new DefaultStyledDocument.ElementSpec(par.getAttributes(), DefaultStyledDocument.ElementSpec.StartTagType);
	                        specs.add(spec);
	                        for (int k=0; k<par.getElementCount(); k++) {
	                            Element leaf=par.getElement(k);
	                            String text=doc.getText(leaf.getStartOffset(), leaf.getEndOffset()-leaf.getStartOffset());
	                            spec=new DefaultStyledDocument.ElementSpec(leaf.getAttributes(),DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length());
	                            specs.add(spec);
	                        }
	                        spec=new DefaultStyledDocument.ElementSpec(par.getAttributes(), DefaultStyledDocument.ElementSpec.EndTagType);
	                        specs.add(spec);
	                    }

	                    int start=elem.getStartOffset();
	                    doc.remove(start, elem.getEndOffset()-start);

	                    DefaultStyledDocument.ElementSpec[] specArray = new DefaultStyledDocument.ElementSpec[specs.size()+2];
	                    DefaultStyledDocument.ElementSpec closePar = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
	                    specArray[0]=closePar;

	                    for (int j=0; j<specs.size(); j++) {
	                        specArray[j+1]=specs.get(j);
	                    }

	                    DefaultStyledDocument.ElementSpec openPar = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
	                    specArray[specArray.length-1]=openPar;

	                    insertSpecs(doc, start, specArray);
	                    break;
	                }
	            }
	        } catch (BadLocationException e) {
	            e.printStackTrace();  
	        }
	    }
	}
