package com.jooink.gwtejml.sample.client;

import java.util.logging.Level;

import org.ejml.example.EquationCustomFunction;
import org.ejml.example.ExampleComplexMath;
import org.ejml.example.ExampleFixedSizedMatrix;
import org.ejml.example.StatisticsMatrix;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.jooink.gwtejml.client.logPanel.LogPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtEjmlSample implements EntryPoint, UncaughtExceptionHandler {


	@Override
	public void onModuleLoad() {

		//GWT.setUncaughtExceptionHandler(this);

		//sometime ejm uses System.out so 
		//to get the output we should redirect somewhere



		final ListBox lb=new ListBox();
		lb.addItem("Select an operation");
		lb.addItem("Performace Kalman Benchmark");
		lb.addItem("Equation Custom Function");
		lb.addItem("Statistics Matrix");
		lb.addItem("Fixed Sized Matrix");
		lb.addItem("ExampleComplexMath");


		RootPanel.get().add(lb);

		final LogPanel lp=new LogPanel(Level.ALL,false,false);
		lp.setTitle("Results");
		RootPanel.get().add(lp.getWidget());
		//final Logger lg=Logger.getLogger("");

		System.setOut( lp.getPrintStream(Level.INFO));
		System.setErr( lp.getPrintStream(Level.SEVERE));



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
							//EquationCustomFunction.init();
							EquationCustomFunction.main(null);
							
						}
					});
					break;
				case 3:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							//StatisticsMatrix.init();
							StatisticsMatrix.main(null);
						}
					});
					break;
				case 4:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							//ExampleFixedSizedMatrix.init();
							ExampleFixedSizedMatrix.main(null);
						}
					});
					break;
				case 5:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleComplexMath.main(null);
						}
					});
					break;
				}


				try {
					System.err.flush();
					System.out.flush();
				} catch( Exception e) {

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
