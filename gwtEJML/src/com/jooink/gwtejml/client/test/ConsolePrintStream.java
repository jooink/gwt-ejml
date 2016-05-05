package com.jooink.gwtejml.client.test;

import java.io.OutputStream;
import java.io.PrintStream;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

/**
 * Print stream for GWT that prints to the browser console.
 * 
 * @author Stefan Haustein
 */
public class ConsolePrintStream extends PrintStream {
	
	StringBuilder buf = new StringBuilder();
	
	public ConsolePrintStream()  {
		super((OutputStream) null);
	}
	
	public void print(String s) {
		
		while(true) {
			int cut = s.indexOf('\n');
			if(cut == -1) {
				break;
			}
			println(s.substring(0, cut));
			s = s.substring(cut + 1);
		}
		
		buf.append(s);
	}
	
	 public native void consoleLog(String msg) /*-{
	 	if (window.console) {
     	    window.console.log(msg);
	 	} else {
	 		document.title = "LOG:" + msg;
	 	}
	  }-*/;

	public void print(char c) {
		if (c == '\n') {
			println("");
		} else {
			buf.append(c);
		}
	}
	 
	public void println() {
		println("");
	}
	
	public void printf(String s, double d) {
		print(""+d);
	}
	    

	
	@Override
	public void println(String s) {
		buf.append(s);
		consoleLog(buf.toString());
		buf.setLength(0);
	}

}
