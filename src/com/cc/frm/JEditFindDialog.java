package com.cc.frm;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;

public class JEditFindDialog extends Dialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lFind = new Label("�����ַ���");
	private Label lReplace = new Label("�滻�ַ���");
	private TextField tFind = new TextField(10);
	private TextField tReplace = new TextField(10);
	private Button bFind = new Button("����");
	private Button bReplace = new Button("�滻");
	private JTextArea ta;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public JEditFindDialog(Frame owner, JTextArea ta) {
		super(owner, "����", false);
		this.ta = ta;
		setLocation((screenSize.width - this.getWidth()) / 3,
				(screenSize.height - this.getHeight()) / 3);
		setLayout(null);
		lFind.setBounds(10, 30, 80, 20);
		lReplace.setBounds(10, 70, 80, 20);
		tFind.setBounds(90, 30, 90, 20);
		tReplace.setBounds(90, 70, 90, 20);
		bFind.setBounds(190, 30, 80, 20);
		bReplace.setBounds(190, 70, 80, 20);
		add(lFind);
		add(tFind);
		add(bFind);
		add(lReplace);
		add(tReplace);
		add(bReplace);
		setResizable(false);
		bFind.addActionListener(this);
		bReplace.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JEditFindDialog.this.dispose();
			}
		});
	}

	private void find() {
		String text = ta.getText();
		String str = tFind.getText();
		int end = text.length();
		int len = str.length();
		int start = ta.getSelectionEnd();
		if (start == end)
			start = 0;
		for (; start <= end - len; start++) {
			if (text.substring(start, start + len).equals(str)) {
				ta.setSelectionStart(start);
				ta.setSelectionEnd(start + len);
				return;
			}
		}
		// ���Ҳ��������ַ������򽫹������ĩβ
		ta.setSelectionStart(end);
		ta.setSelectionEnd(end);
	}

	private void replace() {
		String str = tReplace.getText();
		if (ta.getSelectedText().equals(tFind.getText()))
			ta.replaceSelection(str);
		else
			find();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bFind)
			find();
		else if (e.getSource() == bReplace)
			replace();
	}
}
