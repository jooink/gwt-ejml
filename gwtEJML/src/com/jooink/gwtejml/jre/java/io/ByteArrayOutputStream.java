package java.io;

import java.io.OutputStream;

import com.google.gwt.user.client.Window;

public class ByteArrayOutputStream extends OutputStream {
	
	public ByteArrayOutputStream() {
		Window.alert("Alert! ByteArrayOutputStream() called. ");
	}

	public void write(int arg0) {
		Window.alert("Alert! ByteArrayOutputStream.write("+arg0+") called."); 
	}

}
