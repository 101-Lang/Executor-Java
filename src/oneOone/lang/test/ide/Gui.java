package oneOone.lang.test.ide;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import oneOone.lang.Compiler;
import oneOone.lang.Decompiler;
import oneOone.lang.Executer;
import oneOone.lang.common.Common;
import oneOone.lang.common.exception.CompileException;
import oneOone.lang.common.exception.DecompileException;

public class Gui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Gui thiss = this;
	
	private CodeTextPane codeArea;
	private JTextArea compiledArea;
	public JTextArea lineNrHeader;
	private JTextArea consoleArea;
	
	private JCheckBox chckbxShowStack;
	
	public Gui() {
		Common.init();
		setTitle("101-Script IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
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
		
		codeArea = new CodeTextPane(this);
		scrollPane.setViewportView(codeArea);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setResizeWeight(0.5);
		splitPane.setRightComponent(splitPane_1);
		
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
				String in = codeArea.getText();
				Compiler comp = new Compiler();
				try {
					compiledArea.setText(comp.compile(in));
				} catch (CompileException e1) {
					JOptionPane.showMessageDialog(thiss, "An error happend at line " + e1.getLineNumber() + ". Message: " + e1.getMessage());
				}
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
				consoleArea.setText(exe.Execute(compiledArea.getText(), chckbxShowStack.isSelected(), System.in));
				
			}
		});
		panel.add(btnRun);
		
		chckbxShowStack = new JCheckBox("show stack");
		panel.add(chckbxShowStack);
		
	}
	
	public static void main(String[] args) {
		new Gui().setVisible(true);
	}
}
