package com.jooink.gwtejml.client;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.jooink.gwtejml.client.logPanel.LogPanel;
import com.jooink.gwtejml.client.test.BenchmarkKalmanPerformance;
import com.jooink.gwtejml.client.test.ConsolePrintStream;
import com.jooink.gwtejml.client.test.EquationCustomFunction;
import com.jooink.gwtejml.client.test.ExampleFixedSizedMatrix;
import com.jooink.gwtejml.client.test.StatisticsMatrix;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtEjml implements EntryPoint, UncaughtExceptionHandler {

	@Override
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(this);
		
		//sometime ejm uses System.out so 
		//to get the output we should redirect somewhere
		
		System.setOut( new ConsolePrintStream());
		
		
		
		final ListBox lb=new ListBox();
		lb.addItem("Select an operation");
		lb.addItem("Performace Kalman Benchmark");
		lb.addItem("Equation Custom Function");
		lb.addItem("Statistics Matrix");
		lb.addItem("Fixed Sized Matrix");
		RootPanel.get().add(lb);

		final LogPanel lp=new LogPanel(Level.ALL,false,false);
		lp.setTitle("Results");
		RootPanel.get().add(lp.getWidget());
		final Logger lg=Logger.getLogger("");

		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				lp.clear();
				//lg.log(Level.INFO,"Starting ...");
				int index=lb.getSelectedIndex();
				
				switch (index) {
				case 1:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							BenchmarkKalmanPerformance.init();
						}
					});
					break;
				case 2:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							EquationCustomFunction.init();
						}
					});
					break;
				case 3:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							StatisticsMatrix.init();
						}
					});
					break;
				case 4:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleFixedSizedMatrix.init();
						}
					});
					break;
				}
			}
		});
	}

	@Override
	public void onUncaughtException(Throwable e) {
		String s = new String();			
		
		StackTraceElement[] st = e.getStackTrace();
		
		for(StackTraceElement ste :  st) {
				s += ste.toString() + "\n";
		}
		
		
		Window.alert(s);
		
	}
}
