
package java.io;

import com.google.gwt.user.client.Window;

public class FileNotFoundException extends Exception  {

  public FileNotFoundException(String m) {
	  super(m);
      Window.alert("Alert! FileNotFoundException c'tor called");
  }

}
