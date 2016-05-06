/*
 * Copyright 2006 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jooink.gwtejml.client.logPanel;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @skip
 */
public class LoggerPrintStream extends PrintStream {

  private LogPanel logPanel;
private Level level;

public LoggerPrintStream(LogPanel logPanel, Level Level) {
    super((OutputStream)null);
	this.logPanel = logPanel;
	level = Level;   
  }


  public void print(boolean x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(char x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(char[] x) {
	  logPanel.publish(new LogRecord(level, x.toString()));
  }

  public void print(double x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(float x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(int x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(long x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(Object x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void print(String s) {
	  logPanel.publish(new LogRecord(level, s));
  }

  public void println() {
	  logPanel.publish(new LogRecord(level, ""));
  }

  public void println(boolean x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(char x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(char[] x) {
	  logPanel.publish(new LogRecord(level, x.toString()));

  }

  public void println(double x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(float x) {
	  logPanel.publish(new LogRecord(level, ""+x));
 }

  public void println(int x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(long x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(Object x) {
	  logPanel.publish(new LogRecord(level, ""+x));
  }

  public void println(String s) {
	  logPanel.publish(new LogRecord(level, s));
  }

  public void printf(String s, double d) {
	  logPanel.publish(new LogRecord(level, ""+d));
  }
  
  public void printf(String s, double d1, double d2) {
	  logPanel.publish(new LogRecord(level, ""+d1+" "+d2));
  }
  
  public void close() {
  }
  
}
