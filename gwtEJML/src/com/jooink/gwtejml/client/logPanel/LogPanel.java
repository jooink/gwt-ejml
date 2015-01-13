package com.jooink.gwtejml.client.logPanel;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;


public class LogPanel extends Handler {
	Logger lg=null;
	Level minLevel=Level.ALL;
	boolean showLevel=true;
	boolean showInfo=true;	
	LogPanelUI messagePanel=new LogPanelUI();
	
	public LogPanel(Level minLevel) {
		lg=Logger.getLogger("");
		lg.addHandler(this);
		this.minLevel=minLevel;
	}
	
	public LogPanel(Level minLevel, boolean showLevel, boolean showInfo) {
		lg=Logger.getLogger("");
		lg.addHandler(this);
		this.minLevel=minLevel;
		this.showLevel=showLevel;
		this.showInfo=showInfo;
	}

	@Override
	public void close() {
		//Chiusura dei log
	}

	@Override
	public void flush() {
		//pulizia del buffer di log ... che noi non intendiamo mantenere per ora
	}

	@Override
	public void publish(LogRecord record) {
		//Inserimento del messaggio nel pannello
		if(record.getLevel().intValue()>=minLevel.intValue()) {
			if(showInfo&&showLevel) {
				messagePanel.addCompleteMessage(record);
			} else {
				if(showInfo)
					messagePanel.addMessageInfo(record);
				else if(showLevel)
					messagePanel.addMessageLevel(record);
				else
					messagePanel.addMessage(record);
			}
		}
	}
	
	public Widget getWidget() {
		return messagePanel;
	}
	
	public void setTitle(String title) {
		this.messagePanel.setTitle(title);
	}
	
	/**
	 * Pulisce il pannello dai messaggi di log
	 */
	public void clear() {
		messagePanel.clear();
	}
}
