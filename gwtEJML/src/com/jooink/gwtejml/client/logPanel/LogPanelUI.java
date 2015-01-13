package com.jooink.gwtejml.client.logPanel;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LogPanelUI extends Composite {

	private static LogPanelUIUiBinder uiBinder = GWT
			.create(LogPanelUIUiBinder.class);

	interface LogPanelUIUiBinder extends UiBinder<Widget, LogPanelUI> {
	}

	interface LogPanelUIStyle extends CssResource {
		public String warning();
		public String error();
		public String info();
		public String message();
	}
	
	@UiField 
	LogPanelUIStyle style;
	
	@UiField 
	FlowPanel messageList;
	
	@UiField
	HTML title;
	
	public LogPanelUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public interface MessageHTML extends SafeHtmlTemplates {
		@Template("<div>{0}</div> ")
		SafeHtml message(String message);
		
		@Template("<div>{0}</div> <div>{1} - {2}</div> <div> {3}</div>")
		SafeHtml completeMessage(String logLevel, String dt, String loggerName, String message);

		@Template("<div>{0} - {1}</div> <div> {2}</div>")
		SafeHtml messageInfo(String dt, String loggerName, String message);

		@Template("<div>{0}</div> <div> {1}</div>")
		SafeHtml messageLevel(String logLevel, String message);
	}
	
	private static final MessageHTML TEMPLATE = GWT.create(MessageHTML.class);
	public void addMessage(LogRecord record) {
		HTML item = new HTML();
		
		item.setHTML(TEMPLATE.message(record.getMessage()));
		item.addStyleName(style.message());
		if(record.getLevel()==Level.SEVERE) {
			item.addStyleName(style.error());
		} else if(record.getLevel()==Level.WARNING) {
			item.addStyleName(style.warning());
		} else {
			item.addStyleName(style.info());
		}
		messageList.add(item);
	}
	
	public void addMessageLevel(LogRecord record) {
		HTML item = new HTML();

		item.setHTML(TEMPLATE.messageLevel(record.getLevel().toString(),record.getMessage()));
		item.addStyleName(style.message());
		if(record.getLevel()==Level.SEVERE) {
			item.addStyleName(style.error());
		} else if(record.getLevel()==Level.WARNING) {
			item.addStyleName(style.warning());
		} else {
			item.addStyleName(style.info());
		}
		messageList.add(item);
	}

	public void addMessageInfo(LogRecord record) {
		HTML item = new HTML();
		Date dt=new Date(record.getMillis());

		item.setHTML(TEMPLATE.messageInfo(dt.toString(),record.getLoggerName(),record.getMessage()));
		item.addStyleName(style.message());
		if(record.getLevel()==Level.SEVERE) {
			item.addStyleName(style.error());
		} else if(record.getLevel()==Level.WARNING) {
			item.addStyleName(style.warning());
		} else {
			item.addStyleName(style.info());
		}
		messageList.add(item);
	}

	public void addCompleteMessage(LogRecord record) {
		HTML item = new HTML();
		Date dt=new Date(record.getMillis());

		item.setHTML(TEMPLATE.completeMessage(record.getLevel().toString(),dt.toString(),record.getLoggerName(),record.getMessage()));
		item.addStyleName(style.message());
		if(record.getLevel()==Level.SEVERE) {
			item.addStyleName(style.error());
		} else if(record.getLevel()==Level.WARNING) {
			item.addStyleName(style.warning());
		} else {
			item.addStyleName(style.info());
		}
		messageList.add(item);
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}
	
	public void clear() {
		messageList.clear();
	}
}
