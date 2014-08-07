package oneOone.lang.test.ide;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oneOone.lang.common.Common;

public class AutoCompleter {
	
	public AutoCompleter() {
		Common.init();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField txtField = new JTextField(10);
		
		new AutoSuggestor(txtField, frame);
		
		JPanel panel = new JPanel();
		
		panel.add(txtField);
		
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AutoCompleter();
	}
}