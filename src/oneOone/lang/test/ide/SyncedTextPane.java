package oneOone.lang.test.ide;

import java.io.IOException;
import java.io.StringWriter;

import javax.swing.JTextPane;

public class SyncedTextPane extends JTextPane {

	private static final long serialVersionUID = -108366431673530974L;

	@Override
	public String getText() {
        String txt;
        try {
            StringWriter buf = new StringWriter();
            write(buf);
            txt = buf.toString();
        } catch (IOException ioe) {
            txt = null;
        }
        return txt;
	}
}
