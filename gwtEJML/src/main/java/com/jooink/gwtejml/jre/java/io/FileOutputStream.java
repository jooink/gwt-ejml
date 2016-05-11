
package java.io;

import com.google.gwt.user.client.Window;
import java.io.OutputStream;
import java.io.IOException;

public class FileOutputStream extends OutputStream {

	public FileOutputStream(String in) {
		Window.alert("Alert! FileOutputStream c'tor called");
	}

	@Override
	public void write(int b) throws IOException {
		Window.alert("Alert! FileOutputStream Write");
	}

}
