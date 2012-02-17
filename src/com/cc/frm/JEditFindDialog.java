package com.cc.frm;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JEditorPane;


public class JEditFindDialog extends  Dialog implements ActionListener{

	 private Label lFind=new Label("²éÕÒ×Ö·û´®");
	    private Label lReplace=new Label("Ìæ»»×Ö·û´®");
	    private TextField tFind=new TextField(10);
	    private TextField tReplace=new TextField(10);
	    private Button bFind=new Button("²éÕÒ");
	    private Button bReplace=new Button("Ìæ»»");
	    private JEditorPane ta;
	    public JEditFindDialog(Frame owner,JEditorPane ta){
	        super(owner,"²éÕÒ",false);
	        this.ta=ta;
	        setLayout(null);
	        lFind.setBounds(10,30,80,20);
	        lReplace.setBounds(10,70,80,20);
	        tFind.setBounds(90,30,90,20);
	        tReplace.setBounds(90,70,90,20);
	        bFind.setBounds(190,30,80,20);
	        bReplace.setBounds(190,70,80,20);
	        add(lFind);
	        add(tFind);
	        add(bFind);
	        add(lReplace);
	        add(tReplace);
	        add(bReplace);
	        setResizable(false);
	        bFind.addActionListener(this);
	        bReplace.addActionListener(this);
	        addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent e){
	            	JEditFindDialog.this.dispose();
	            }
	        });
}
	    private void find(){
	        String text=ta.getText();
	        String str=tFind.getText();
	        int end=text.length();
	        int len=str.length();
	        int start=ta.getSelectionEnd();
	        if(start==end) start=0;
	        for(;start<=end-len;start++){
	            if(text.substring(start,start+len).equals(str)){
	                ta.setSelectionStart(start);
	                ta.setSelectionEnd(start+len);
	                return;
	            }
	        }
	        //ÈôÕÒ²»µ½´ý²é×Ö·û´®£¬Ôò½«¹â±êÖÃÓÚÄ©Î²
	        ta.setSelectionStart(end);
	        ta.setSelectionEnd(end);
	    }

	    private void replace(){
	        String str=tReplace.getText();
	        if(ta.getSelectedText().equals(tFind.getText()))
	            ta.replaceSelection(str);
	        else find();
	    }
	    
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bFind)
	            find();
	        else if(e.getSource()==bReplace)
	            replace();
		}
}
