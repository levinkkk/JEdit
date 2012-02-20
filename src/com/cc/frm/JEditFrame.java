package com.cc.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.Document;
import javax.swing.text.Element;

@SuppressWarnings("serial")
public class JEditFrame extends JFrame implements ActionListener {

	private PageFormat pageFormat;
	//JEditorPane jedit;
	
	JTextArea jedit;

	JLabel backgroundlabel;

	JInternalFrame userInfo, personInfo, dpartmentInfo;

	Container contentpane;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	JMenu[] menu = { new JMenu("文件"), new JMenu("编辑"), new JMenu("格式"),
			new JMenu("查看"), new JMenu("帮助") };

	JMenuItem[] menuItem = { new JMenuItem("新建"), new JMenuItem("打开"),
			new JMenuItem("保存"), new JMenuItem("另保存"), new JMenuItem("页面设置"),
			new JMenuItem("打印"), new JMenuItem("退出") };

	JMenuItem[] menuItem1 = { new JMenuItem("撤销"), new JMenuItem("剪切"),
			new JMenuItem("复制"), new JMenuItem("粘贴"), new JMenuItem("删除"),
			new JMenuItem("查找"), new JMenuItem("查找下一个"), new JMenuItem("替换"),
			new JMenuItem("转到"), new JMenuItem("全选"), new JMenuItem("时间/日期") };

	JCheckBoxMenuItem jcitm =new JCheckBoxMenuItem("自动换行");
	JMenuItem  fontItm =  new JMenuItem("字体") ;

	JMenuItem[] menuItem3 = { new JMenuItem("状态栏") };

	JMenuItem[] menuItem4 = { new JMenuItem("查看帮助"), new JMenuItem("关于记事本") };

	JMenuBar menuBar = new JMenuBar();

	JFileChooser filechooser = new JFileChooser();

	private File file = null;

	private JLabel stateLabel = new JLabel();

	private int visblesta = 0;
	
	private JTextArea jt=new JTextArea();
	
	private JScrollPane js=new JScrollPane(jedit);
	
	//private JScrollPane jst=new JScrollPane(jt);

	public JEditFrame() {

		jedit = new JTextArea();
		jedit.addKeyListener(new   KeyAdapter()   { 
			public   void   keyPressed(KeyEvent   e)   
		    {   
		     if(e.getKeyCode() == KeyEvent.VK_ENTER) 
		     {
		    	 try{
		    		    Document doc = jedit.getDocument();

		    			Element baseElement = doc.getDefaultRootElement();

		    			int pos = jedit.getCaretPosition();

		    			int totalLines = baseElement.getElementCount();

		    			int line = baseElement.getElementIndex(pos);

		    			Element lineElement = baseElement.getElement(line);

		    			int Column=pos - lineElement.getStartOffset() + 1;
		    			stateLabel.setText(" TotalLines: " + totalLines + " Line: " + (line + 1)
		    					+ " Column: " + Column);

		    			String ss="";
		    			for(int k=0;k<=totalLines;k++)
		    			{
		    				ss+=k+"\n";
		    				
		    			}
		    			jt.setText(ss);
		    			int startingIndex = lineElement.getStartOffset();
		    			int endingIndex = lineElement.getEndOffset();
		    			
		    			   String lineText = doc.getText(startingIndex, endingIndex
		    						- startingIndex);
		    			   System.out.println(lineText);

		    			   ArrayList<String> x=new ArrayList<String>();
		    			   for( int i=0; i<lineText.length(); i++)
		    			   {
		    				  x.add(lineText.substring(i,i+1));
		    			   }
		    			   int n=0;
		    			   for( int j=0; j<x.size()-1; j++)
		    			   {
		    				   System.out.println(x.get(j));
		    				   if(x.get(j).indexOf(' ')>=0)
		    				   {
		    					   n=n+1;
		    				   }else
		    				   {
		    					   break;
		    				   }
		    			   }
		    			   StringBuffer xx=new StringBuffer();
		    				for(int i=0;i<n;i++)
		    				{
		    				 xx.append(" ");
		    				}
		    				//jedit.setText(jedit.getText()+"\n"+xx);   
		    				line=line+1;
		    				//jt.setText(jt.getText()+"\n"+line);
		    			} catch (Exception ex) {
		    				ex.printStackTrace();
		    			}
		     }
		}});
		jedit.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (jedit.getSelectedText() != null) {
					menuItem1[0].setEnabled(true);
					menuItem1[1].setEnabled(true);
					menuItem1[2].setEnabled(true);
					menuItem1[4].setEnabled(true);
				}
			}
		}
		);
		jt.setBorder(BorderFactory.createLineBorder(Color.blue));
		jt.setText("0");
		jt.setForeground(Color.black);
		jt.setEditable(false);
		stateLabel.setVisible(false);
		stateLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		stateLabel.setText(" TotalLines: 1 Line: 1 Column: 1");
		js.setAutoscrolls(true);
		js.setViewportView(jedit);
		//jSt.setAutoscrolls(false);
		contentpane = this.getContentPane();
		contentpane.setLayout(new BorderLayout());
		contentpane.add(js, BorderLayout.CENTER);
		contentpane.add(stateLabel, BorderLayout.SOUTH);
		contentpane.add(jt, BorderLayout.WEST);
		Menu();
		this.setJMenuBar(menuBar);

		mainFrameInfo();
	}

	
	private void Menu() {
		for (int i = 0; i < menu.length; i++) {
			menuBar.add(menu[i]);
			menu[i].addActionListener(this);
		}
		for (int i = 0; i < menuItem.length; i++) {
			menu[0].add(menuItem[i]);
			if (i == 3 || i == 5) {
				menu[0].addSeparator();
			}
			menuItem[i].addActionListener(this);
		}
		for (int i = 0; i < menuItem1.length; i++) {
			menu[1].add(menuItem1[i]);
			if (i == 0 || i == 4 || i == 8) {
				menu[1].addSeparator();
			}
			menuItem1[i].addActionListener(this);
		}
			menu[2].add(jcitm);
			menu[2].add(fontItm);
			jcitm.addActionListener(this);
			fontItm.addActionListener(this);
		for (int i = 0; i < menuItem3.length; i++) {
			menu[3].add(menuItem3[i]);
			menuItem3[i].addActionListener(this);
		}
		for (int i = 0; i < menuItem4.length; i++) {
			menu[4].add(menuItem4[i]);
			menuItem4[i].addActionListener(this);
		}
		menuItem1[0].setEnabled(false);
		menuItem1[1].setEnabled(false);
		menuItem1[2].setEnabled(false);
		menuItem1[4].setEnabled(false);
		menuItem1[8].setEnabled(false);

		menuItem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		menuItem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		menuItem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		menuItem[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
		menuItem[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
		menuItem[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
		menuItem[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.ALT_MASK));
		menuItem1[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		menuItem1[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		menuItem1[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		menuItem1[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		menuItem1[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		menuItem1[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		menuItem1[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
		menuItem1[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		menuItem1[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		menuItem1[9].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		menuItem1[10].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		fontItm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		menuItem3[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		menuItem4[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		menuItem4[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
	}

	private void mainFrameInfo()// 界面信息
	{
		this.setTitle("记事本");
		this.setLocation((screenSize.width - this.getWidth()) / 4,
				(screenSize.height - this.getHeight()) / 6);
		this.setSize(800, 600);
		this.setResizable(true); // 可以设置最大化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JEditFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 新建
		if (e.getSource() == menuItem[0]) {
			file = null;
			if (!("".equals(jedit.getText()))) {
				Object[] options = { "保存(S)", "不保存(N)", "取消" };
				int x = JOptionPane.showOptionDialog(this.getContentPane(),
						"是否将要保存到 无标题?", "记事本", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				switch (x) {
				case 0:
					int s = filechooser.showSaveDialog(this);
					if (s == JFileChooser.APPROVE_OPTION) {
						file = filechooser.getSelectedFile();
						try {
							FileWriter fw = new FileWriter(file);
							fw.write(jedit.getText());
							setTitle(filechooser.getSelectedFile().getName()
									+ " - 记事本");
							fw.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						break;
					}
				case 1:
					jedit.setText("");
					setTitle("无标题 -记事本");
				}
			}
		}

		// 打开
		if (e.getSource() == menuItem[1]) {
			try {
				file = null;
				int s = filechooser.showOpenDialog(this);
				if (s == JFileChooser.APPROVE_OPTION) {
					file = filechooser.getSelectedFile();
					FileReader fr = new FileReader(file);
					int len = (int) file.length();
					char[] buffer = new char[len];
					fr.read(buffer, 0, len);
					fr.close();
					jedit.setText(new String(buffer));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 保存
		if (e.getSource() == menuItem[2]) {
			int s = filechooser.showSaveDialog(this);
			if (s == JFileChooser.APPROVE_OPTION) {
				file = filechooser.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(jedit.getText());
					setTitle(filechooser.getSelectedFile().getName() + " -记事本");
					fw.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		// 另存为
		if (e.getSource() == menuItem[3]) {
			filechooser.setDialogTitle("另存为...");
			int s = filechooser.showSaveDialog(this);
			if (s == JFileChooser.APPROVE_OPTION) {
				file = filechooser.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(jedit.getText());
					setTitle(filechooser.getSelectedFile().getName() + " - 记事本");
					fw.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		if (e.getSource() == menuItem[4]) {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			if (pageFormat == null)
				pageFormat = printJob.defaultPage();
			pageFormat = printJob.pageDialog(pageFormat);
		}

		if (e.getSource() == menuItem[5]) {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			if (pageFormat == null)
				pageFormat = printJob.defaultPage();
			if (printJob.printDialog()) {
				try {
					printJob.print();
				} catch (PrinterException exception) {
					JOptionPane.showMessageDialog(this, exception);
				}
			}
		}

		// 退出
		if (e.getSource() == menuItem[6]) {
			if (!jedit.getText().equals("")) {
				file = null;
				if (!("".equals(jedit.getText()))) {
					Object[] options = { "保存(S)", "不保存(N)", "取消" };
					int x = JOptionPane.showOptionDialog(this.getContentPane(),
							"是否将要保存到 无标题?", "记事本", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
					switch (x) {
					case 0:
						int s = filechooser.showSaveDialog(this);
						if (s == JFileChooser.APPROVE_OPTION) {
							file = filechooser.getSelectedFile();
							try {
								FileWriter fw = new FileWriter(file);
								fw.write(jedit.getText());
								setTitle(filechooser.getSelectedFile()
										.getName()
										+ " - 记事本");
								fw.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							System.exit(0);
						} else {
							break;
						}

					case 1:
						System.exit(0);
					}
				}
			} else {
				System.exit(0);
			}
		}
		if (e.getSource() == menuItem1[0]) {

		}
		if (e.getSource() == menuItem1[1]) {
			jedit.cut();
		}
		if (e.getSource() == menuItem1[2]) {
			jedit.copy();
		}
		if (e.getSource() == menuItem1[3]) {
			jedit.paste();
		}
		if (e.getSource() == menuItem1[4]) {
			// StringBuffer tmp = new StringBuffer(jedit.getText());
			// int start = jedit.getSelectionStart();
			// int len = jedit.getSelectedText().length();
			// tmp.delete(start, start + len);
			// jedit.setText(tmp.toString());

			jedit.replaceSelection("");
		}
		if (e.getSource() == menuItem1[5]) {
			JEditFindDialog findDlg = new JEditFindDialog(this, jedit);
			findDlg.setTitle("查找");
			findDlg.setSize(280, 60);
			findDlg.setVisible(true);
		}
		if (e.getSource() == menuItem1[6]) {
			JEditFindDialog findDlg = new JEditFindDialog(this, jedit);
			findDlg.setTitle("查找替换");
			findDlg.setSize(280, 110);
			findDlg.setVisible(true);
		}
		if (e.getSource() == menuItem1[9]) {
			jedit.setSelectionStart(0);
			jedit.setSelectionEnd(jedit.getText().length());

		}
		if (e.getSource() == menuItem1[10]) {
			Date Nowtime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jedit.setText(jedit.getText() + sdf.format(Nowtime));
		}
		if (e.getSource() == jcitm) {
			if(jcitm.getState()==true)
			{
			  jedit.setLineWrap(true);
			}else
			{
			  jedit.setLineWrap(false);
			}
		}
		if (e.getSource() == fontItm) {
			JEditFont fontdialog=new JEditFont(jedit);
			fontdialog.setVisible(true);
		}
		if (e.getSource() == menuItem3[0]) {
			if (visblesta == 0) {
				stateLabel.setVisible(true);
				visblesta = 1;
			} else {
				stateLabel.setVisible(false);
				visblesta = 0;
			}
		}
		if(e.getSource()==menuItem4[0])
		{
			try {
				Runtime.getRuntime().exec("hh.exe res/help.doc"); // 打开帮助文档
			} catch (Exception r) {
				r.printStackTrace();
			}
		}
		
		if(e.getSource()==menuItem4[1])
		{
			JOptionPane.showMessageDialog(this, "欢迎使用本记事本，如有不懂，请看帮助？" + "\n"
					+ "本软件暂时功能不齐全，正在完善。" + "\n" + "版权所有！仅供学习所用！", "关于记事本",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
