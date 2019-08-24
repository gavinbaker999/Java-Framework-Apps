import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.*;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.List;
import java.awt.print.*;
import java.util.Date;

import javax.swing.JOptionPane;

import java.text.*;
import java.beans.*;

	public class appletframe extends Frame implements AppletStub,AppletContext {
		private Applet applet = null;
		
		appletframe(Applet a) {
			applet = a;
			setTitle(a.getClass().getName());
			a.setStub(this);
		}
		appletframe(Applet a,int x,int y) {
			applet = a;
			setTitle(a.getClass().getName());
			setSize(x,y);
			add("Center",a);
			a.setStub(this);
//			bApplet = false;
			a.init();
			setVisible(true);
			a.start();
		}
		public boolean isActive() {return true;}
		public URL getDocumentBase() {return null;}
		public URL getCodeBase() {JOptionPane.showMessageDialog(null, "getCodeBase", "Applet Frame", JOptionPane.PLAIN_MESSAGE);return null;}
		public String getParameter(String name) {return "";}
		public AppletContext getAppletContext() {return this;}
		public void appletResize(int width,int height) {}
		public AudioClip getAudioClip(URL u) {return null;}
		public Image getImage(URL u) {JOptionPane.showMessageDialog(null, "getImage", "Applet Frame", JOptionPane.PLAIN_MESSAGE);return null;}
		public Applet getApplet(String name) {return null;}
		public Enumeration getApplets() {return null;}
		public void showDocument(URL u) {JOptionPane.showMessageDialog(null, "showDocument", "Applet Frame", JOptionPane.PLAIN_MESSAGE);}
		public void showDocument(URL u,String target) {JOptionPane.showMessageDialog(null, "showDocument", "Applet Frame", JOptionPane.PLAIN_MESSAGE);}
		public void showStatus(String status) {}
		public void setStream(String key,InputStream is) {}
		public InputStream getStream(String key) {return null;}
		public Iterator getStreamKeys() {return null;}
		
		public Applet getApplet() {return  applet;}
	}

