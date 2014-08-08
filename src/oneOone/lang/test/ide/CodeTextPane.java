package oneOone.lang.test.ide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import oneOone.lang.Compiler;
import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.exception.CompileException;

public class CodeTextPane extends JTextPane implements DocumentListener, CaretListener {
	
	private static final long serialVersionUID = -108366431673530974L;
	
	public final JWindow popupWindow;
	public final JPanel suggestionsPanel;
	
	public ArrayList<SuggestionLabel> labels = new ArrayList<>();
	public Color suggestionFocusedColor;
	public Color suggestionsTextColor;
	public Point magicCaretPos = null;
	
	public boolean codeIsChaningText = false;
	public float opacity = 0.8f;
	public int carotPos = 0;
	
	Runnable onChane = new Runnable() {
		@Override
		public synchronized void run() {
			codeIsChaningText = true;
			updateLineNumbers();
			colorErrors();
			showMenu();
			setFocusToTextField();
			codeIsChaningText = false;
		}
	};
	
	/**
	 * fired when button down is released when the focus is on the textField.
	 */
	private AbstractAction onButtonDownInTextfield = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			if(isMenuVisable()){
				for (SuggestionLabel label : labels) {
					// only the first label should be focused.
					label.setFocused(true);
					label.requestFocusInWindow();
					break;
				}
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
	
	SimpleAttributeSet red = new SimpleAttributeSet();
	SimpleAttributeSet black = new SimpleAttributeSet();
	
	private Gui parent;
	
	public CodeTextPane(Gui parent) {
		getDocument().addDocumentListener(this);
		addCaretListener(this);
		this.parent = parent;
		
		StyleConstants.setForeground(red, Color.RED);
		StyleConstants.setForeground(black, Color.BLACK);
		
		this.suggestionsTextColor = Color.black;
		this.suggestionFocusedColor = Color.red;
		
		popupWindow = new JWindow(parent);
		popupWindow.setOpacity(this.opacity);
		
		suggestionsPanel = new JPanel();
		suggestionsPanel.setLayout(new GridLayout(0, 1));
		suggestionsPanel.setBackground(Color.white);
		
		popupWindow.getContentPane().add(suggestionsPanel);
		
		getInputMap(JComponent.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
		getActionMap().put("Down released", onButtonDownInTextfield);
		
		suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
		suggestionsPanel.getActionMap().put("Down released", onButtonDwonInDownPanel);
	}
	
	protected boolean isMenuVisable() {
		return popupWindow.isVisible();
	}

	@Override
	public String getText() {
		try {
			return getDocument().getText(0, getDocument().getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void setText(String t) {
		super.setText(t);
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		Point temp = getCaret().getMagicCaretPosition();
		if(temp == null || codeIsChaningText)
			return;
		magicCaretPos = temp;
		SwingUtilities.invokeLater(onChane);
		
	}
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		Point temp = getCaret().getMagicCaretPosition();
		if(temp == null || codeIsChaningText)
			return;
		magicCaretPos = temp;
		SwingUtilities.invokeLater(onChane);
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		Point temp = getCaret().getMagicCaretPosition();
		if(temp == null || codeIsChaningText)
			return;
		magicCaretPos = temp;
		SwingUtilities.invokeLater(onChane);
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		carotPos = e.getDot();
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
		
		popupWindow.setMinimumSize(new Dimension(getWidth(), 30));
		popupWindow.pack();
		popupWindow.setVisible(true);
		
		int windowX = (int) magicCaretPos.x + getLocationOnScreen().x;
		int windowY = (int) magicCaretPos.y + getLocationOnScreen().y+20;
		
		popupWindow.setLocation(windowX, windowY);
		popupWindow.setMinimumSize(new Dimension(getWidth(), 30));
		popupWindow.revalidate();
		popupWindow.repaint();
		
	}
	
	/**
	 * Adds all the suggestions to the {@link #labels} arraylist.
	 * 
	 * @param typedWord the wordt to check
	 * @return wether or not a new suggestion is added.
	 */
	public void addSuggestions(String typedWord) {
		labels.clear();
		if (typedWord.isEmpty()) {
			return;
		}
		
		for (ICommand cmd : Common.commands) {
			String word = cmd.getDecompiledPrefix();
			if (word.toLowerCase().startsWith(typedWord.toLowerCase())) {
				labels.add(new SuggestionLabel(word, this));
			}
		}
	}
	
	/**
	 * Colors the lines that contain an error. 
	 */
	public void colorErrors() {
		
		int errLineNumber = -1;
		String in = getText();
		Compiler comp = new Compiler();
		try {
			comp.compile(in);
		} catch (CompileException e1) {
			errLineNumber = e1.getLineNumber();
		}
		int cur = carotPos;
		
		setText("");
		try {
			String[] lines = in.split("\\r?\\n");
			for (int i = 0; i < lines.length; i++) {
				if (i + 1 == errLineNumber) {
					getDocument().insertString(getDocument().getLength(), lines[i] + "\n", red);
				} else {
					getDocument().insertString(getDocument().getLength(), lines[i] + "\n", black);
				}
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try{
			setCaretPosition(cur);
		}catch(Exception e){
			
		}
	}
	
	/**
	 * Updates the line numbers.
	 */
	public void updateLineNumbers() {
		StringBuffer text = new StringBuffer("1  " + System.lineSeparator());
		int lines = getText().split("\\r?\\n", -1).length;
		for (int i = 2; i <= lines; i++) {
			text.append(i + "  " + System.getProperty("line.separator"));
		}
		parent.lineNrHeader.setText(text.toString());;
	}
	
	/**
	 * Get the word that is currently being typed.
	 * 
	 * @return the word without spaces.
	 */
	public String getCurrentlyTypedWord() {
		if (carotPos <= 0)
			return "";
		return getText().substring(getWordBegin(), getWordEnd());
	}
	
	/**
	 * 
	 * @return the beginning of the word.
	 */
	public int getWordBegin(){
		int wordBegin = carotPos;
		String text = getText();
		while (true) {
			if (wordBegin <= 0)
				break;
			String sub = text.substring(wordBegin, carotPos);
			System.out.println(sub);
			if (sub.startsWith(" ") || sub.startsWith(";")) {
				wordBegin++;
				break;
			}
			wordBegin--;
		}
		return wordBegin;
	}
	
	/**
	 * @return the end of the word.
	 */
	public int getWordEnd(){
		return carotPos;
	}
	
	/**
	 * Sets the focus to the current textPane.
	 */
	private void setFocusToTextField() {
		parent.toFront();
		parent.requestFocusInWindow();
		requestFocusInWindow();
		requestFocus();
	}
	
	/**
	 * Removes the dropdown menu from the screen.
	 */
	public void removeDropdownMenu(){
		labels.clear();
		popupWindow.setVisible(false);
	}
}
