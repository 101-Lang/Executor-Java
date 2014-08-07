package oneOone.lang.test.ide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class SuggestionLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private boolean focused = false;
	
	private final AutoSuggestor parent;
	
	private Color suggestionsTextColor, suggestionBorderColor;
	
	private Action replaceAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent ae) {
			replaceWithSuggestedText();
			parent.removeDropdownMenu();
		}
	};
	
	public SuggestionLabel(String txt, AutoSuggestor parent) {
		super(txt);
		
		this.suggestionsTextColor = parent.suggestionsTextColor;
		this.parent = parent;
		this.suggestionBorderColor = parent.suggestionFocusedColor;
		
		initComponent();
	}
	
	private void initComponent() {
		setFocusable(true);
		setForeground(suggestionsTextColor);
		
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
		getActionMap().put("Enter released", replaceAction);
	}
	
	public void setFocused(boolean focused) {
		if (focused) {
			setBorder(new LineBorder(suggestionBorderColor));
			requestFocusInWindow();
			requestFocus();
		} else {
			setBorder(null);
		}
		repaint();
		this.focused = focused;
	}
	
	public boolean isFocused() {
		return focused;
	}
	
	private void replaceWithSuggestedText() {
		String txt = parent.textField.getText();
		String before = "", after = "";
		
		try{
			before = txt.substring(0, parent.getWordBegin());
		}catch(Exception e){}
		
		try{
			after = txt.substring(parent.getWordEnd(), txt.length()-1);
		}catch(Exception e){}
		parent.listen = false;
		parent.textField.setText(before + getText() + after);
		parent.listen = true;
		System.out.println("new text after setting: " + parent.textField.getText());
		parent.removeDropdownMenu();
	}
}
