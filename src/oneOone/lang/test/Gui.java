package oneOone.lang.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import oneOone.lang.Compiler;
import oneOone.lang.Decompiler;
import oneOone.lang.Executer;
import oneOone.lang.common.exception.CompileException;
import oneOone.lang.common.exception.DecompileException;

import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;

public class Gui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Gui thiss = this;
	
	private JTextPane codeArea;
	private JTextArea compiledArea;
	private JTextArea lineNrHeader;
	private JTextArea consoleArea;
	
	private JCheckBox chckbxShowStack;
	
	SimpleAttributeSet red = new SimpleAttributeSet();
	SimpleAttributeSet black = new SimpleAttributeSet();
	
	public boolean isCompiled = true;
	public long lastChange = 0;
	
	Thread liveCompiler = new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(true){
				if(System.currentTimeMillis() - lastChange > 100 && isCompiled == false){
					compile(false);
					isCompiled = true;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	
	public Gui() {
		setTitle("101-Script IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StyleConstants.setForeground(red, Color.RED);
		StyleConstants.setForeground(black, Color.BLACK);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel codePanel = new JPanel();
		codePanel.setBorder(new TitledBorder(null, "Source Code", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(codePanel);
		codePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		codePanel.add(scrollPane, BorderLayout.CENTER);
		
		lineNrHeader = new JTextArea();
		lineNrHeader.setBackground(UIManager.getColor("Panel.background"));
		lineNrHeader.setEditable(false);
		lineNrHeader.setText("1  ");
		scrollPane.setRowHeaderView(lineNrHeader);
		
		codeArea = new JTextPane();
		scrollPane.setViewportView(codeArea);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setResizeWeight(0.5);
		splitPane.setRightComponent(splitPane_1);
		

		codeArea.getDocument().addDocumentListener(new DocumentListener() {
			
			public String getText() {
				String text = "1  " + System.lineSeparator();
				int lines = codeArea.getText().split("\\r?\\n", -1).length;
				for (int i = 2; i <= lines; i++) {
					text += i + "  " + System.getProperty("line.separator");
				}
				lastChange = System.currentTimeMillis();
				isCompiled = false;
				return text;
			}
			
			@Override
			public void changedUpdate(DocumentEvent de) {
				lineNrHeader.setText(getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent de) {
				lineNrHeader.setText(getText());
			}
			
			@Override
			public void removeUpdate(DocumentEvent de) {
				lineNrHeader.setText(getText());
			}
			
		});
		
		JPanel compiledPanel = new JPanel();
		compiledPanel.setBorder(new TitledBorder(null, "Compiled Code", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setLeftComponent(compiledPanel);
		compiledPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane compiledScroller = new JScrollPane();
		compiledPanel.add(compiledScroller, BorderLayout.CENTER);
		
		compiledArea = new JTextArea();
		compiledScroller.setViewportView(compiledArea);
		
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setRightComponent(consolePanel);
		consolePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		consolePanel.add(scrollPane_1, BorderLayout.CENTER);
		
		consoleArea = new JTextArea();
		scrollPane_1.setViewportView(consoleArea);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnCompile = new JButton("Compile  =>");
		panel.add(btnCompile);
		btnCompile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				compiledArea.setText(compile(true));
			}
		});
		
		JButton btnNewButton = new JButton("<= Decompile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decompiler comp = new Decompiler();
				try{
					codeArea.setText(comp.decompile(compiledArea.getText()));
				}catch(DecompileException f){
					JOptionPane.showMessageDialog(thiss, "An error happend at code part " + f.getCodeNumber() + ". Message: " + f.getMessage());
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Executer exe = new Executer();
				consoleArea.setText(exe.Execute(compiledArea.getText(), chckbxShowStack.isSelected()));
				
			}
		});
		panel.add(btnRun);
		
		chckbxShowStack = new JCheckBox("show stack");
		panel.add(chckbxShowStack);
		lastChange = System.currentTimeMillis();
		liveCompiler.start();
	}
	
	public String compile(boolean showErrorDialog){

		int errLineNumber = -1;
		String in = codeArea.getText();
		Compiler comp = new Compiler();
		String out = null;
		try {
			out = comp.compile(in);
		} catch (CompileException e1) {
			errLineNumber = e1.getLineNumber();
			if(showErrorDialog)
				JOptionPane.showMessageDialog(thiss, "An error happend at line " + e1.getLineNumber() + ". Message: " + e1.getMessage());
		}
		int cur = codeArea.getCaretPosition();
		codeArea.setText("");
		
		try{
			String[] lines = in.split("\\r?\\n");
			for(int i = 0; i < lines.length; i++){
				if(i+1 == errLineNumber){
					codeArea.getDocument().insertString(codeArea.getDocument().getLength(), lines[i] + "\n", red);
				}else{
					codeArea.getDocument().insertString(codeArea.getDocument().getLength(), lines[i] + "\n", black);
				}

			}
		}catch(Exception e2){
			e2.printStackTrace();
		}
		codeArea.setCaretPosition(cur);
		return out;
	}
	
	public static void main(String[] args) {
		new Gui().setVisible(true);
	}
}
