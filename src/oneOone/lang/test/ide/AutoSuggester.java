package oneOone.lang.test.ide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;

class AutoSuggestor {
	
	public final JTextComponent textField;
	public final Window container;
	public final JPanel suggestionsPanel;
	
	public JWindow popupWindow;
	
	public Color suggestionsTextColor;
	public Color suggestionFocusedColor;
	
	public int carotPos = 0;
	public float opacity = 0.8f;
	
	public ArrayList<SuggestionLabel> labels = new ArrayList<>();
	
	public boolean listen = true;
	private DocumentListener documentListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent de) {
			if(!listen)
				return;
			carotPos = textField.getCaretPosition() + 1;
			showMenu();
			setFocusToTextField();
			System.out.println("adsf");
		}
		
		@Override
		public void removeUpdate(DocumentEvent de) {
			if(!listen)
				return;
			carotPos = textField.getCaretPosition() - 1;
			showMenu();
			setFocusToTextField();
		}
		
		@Override
		public void changedUpdate(DocumentEvent de) {
			if(!listen)
				return;
			carotPos = textField.getCaretPosition();
			showMenu();
			setFocusToTextField();
		}
	};
	
	/**
	 * fired when button down is released when the focus is on the textField.
	 */
	private AbstractAction onButtonDownInTextfield = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			showMenu();
			for (SuggestionLabel label : labels) {
				// only the first label should be focused.
				label.setFocused(true);
				label.requestFocusInWindow();
				break;
			}
		}
	};
	
	/**
	 * fired when button down is released when the focus is on the drop down
	 * thingy.
	 */
	private AbstractAction onButtonDwonInDownPanel = new AbstractAction() {
		
		private static final long serialVersionUID = 1L;

		
		@Override
		public void actionPerformed(ActionEvent ae) {

			if (labels.size() > 1) {
				
				int i = 0;
				int newFocused = 0;
				for(SuggestionLabel label : labels){
					if(label.isFocused()){
						label.setFocused(false);
						newFocused = i+1;
					}
					i++;
				}
				if(newFocused > labels.size()-1)
					setFocusToTextField();
				else
					labels.get(newFocused).setFocused(true);

			} else {
				// if there is just 1 suggestion, a button press down always
				// result in setting the focus to the text field.
				setFocusToTextField();
			}
		}
		
	};
	
	public AutoSuggestor(JTextComponent textField, Window mainWindow) {
		this.textField = textField;
		this.suggestionsTextColor = Color.black;
		this.container = mainWindow;
		this.suggestionFocusedColor = Color.red;
		this.textField.getDocument().addDocumentListener(documentListener);
		
		popupWindow = new JWindow(mainWindow);
		popupWindow.setOpacity(this.opacity);
		
		suggestionsPanel = new JPanel();
		suggestionsPanel.setLayout(new GridLayout(0, 1));
		suggestionsPanel.setBackground(Color.white);
		
		popupWindow.getContentPane().add(suggestionsPanel);
		
		this.textField.getInputMap(JComponent.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
		this.textField.getActionMap().put("Down released", onButtonDownInTextfield);
		
		suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
		suggestionsPanel.getActionMap().put("Down released", onButtonDwonInDownPanel);
	}
	
	public void removeDropdownMenu(){
		labels.clear();
		popupWindow.setVisible(false);
	}

	private void setFocusToTextField() {
		container.toFront();
		container.requestFocusInWindow();
		textField.requestFocusInWindow();
		textField.requestFocus();
	}
	
	private void showMenu() {
		addSuggestions(getCurrentlyTypedWord());
		
		if(labels.size() == 0){
			removeDropdownMenu();
			return;
		}
		
		suggestionsPanel.removeAll();
		
		for(SuggestionLabel label : labels){
			suggestionsPanel.add(label);
		}
		
		popupWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
		popupWindow.pack();
		popupWindow.setVisible(true);
		
		int windowX = (int) textField.getLocationOnScreen().getX();
		int windowY = (int) textField.getLocationOnScreen().getY()+textField.getHeight();
		
		popupWindow.setLocation(windowX, windowY);
		popupWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
		popupWindow.revalidate();
		popupWindow.repaint();
		
	}
	
	/**
	 * checks whether to show the button thingy or not.
	 * 
	 * @param typedWord the wordt to check
	 * @return
	 */
	public boolean addSuggestions(String typedWord) {
		labels.clear();
		if (typedWord.isEmpty()) {
			return false;
		}
		
		boolean suggestionAdded = false;
		
		for (ICommand cmd : Common.commands) {
			String word = cmd.getDecompiledPrefix();
			if (word.toLowerCase().startsWith(typedWord.toLowerCase())) {
				labels.add(new SuggestionLabel(word, this));
				suggestionAdded = true;
			}
		}
		return suggestionAdded;
	}
	
	/**
	 * Get the word that is currently being typed.
	 * 
	 * @return the word without spaces.
	 */
	public String getCurrentlyTypedWord() {
		if (carotPos <= 0)
			return "";
		return textField.getText().substring(getWordBegin(), getWordEnd());
	}
	
	public int getWordBegin(){
		int wordBegin = carotPos;
		String text = textField.getText();
		while (true) {
			if (wordBegin <= 0)
				break;
			System.out.println("value.lenght: " + text.length() + "  wordBegin: " + wordBegin + "  cartoPos: " + carotPos);
			if (text.substring(wordBegin, carotPos).startsWith(" ") ||text.substring(wordBegin, carotPos).startsWith(";")) {
				wordBegin++;
				break;
			}
			wordBegin--;
		}
		return wordBegin;
	}
	
	public int getWordEnd(){
		return carotPos;
	}

}
